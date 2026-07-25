/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.ChunkCoordIntPair
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.event.world.ChunkEvent$Load
 *  net.minecraftforge.event.world.ChunkEvent$Unload
 *  net.minecraftforge.event.world.WorldEvent$Load
 *  net.minecraftforge.event.world.WorldEvent$Save
 *  org.apache.commons.io.FileUtils
 */
package drunkmafia.thaumicinfusion.common.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import drunkmafia.thaumicinfusion.common.world.IWorldDataProvider;
import drunkmafia.thaumicinfusion.common.world.SavableHelper;
import drunkmafia.thaumicinfusion.common.world.TIWorldData;
import drunkmafia.thaumicinfusion.common.world.data.BlockSavable;
import drunkmafia.thaumicinfusion.net.ChannelHandler;
import drunkmafia.thaumicinfusion.net.packet.client.ChunkRequestPacketS;
import drunkmafia.thaumicinfusion.net.packet.server.BlockSyncPacketC;
import java.io.File;
import java.io.IOException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import org.apache.commons.io.FileUtils;

public class CommonEventContainer {
    @SubscribeEvent
    public void onPlayerJoin(EntityJoinWorldEvent event) {
        if (event.world == null || event.world.field_72995_K || !(event.entity instanceof EntityPlayer)) {
            return;
        }
        TIWorldData worldData = TIWorldData.getWorldData(event.world);
        if (worldData == null) {
            return;
        }
        for (BlockSavable savable : worldData.getAllStoredData()) {
            if (savable == null) continue;
            ChannelHandler.instance().sendTo(new BlockSyncPacketC(savable), (EntityPlayerMP)event.entity);
        }
    }

    @SubscribeEvent
    public void loadChunk(ChunkEvent.Load event) {
        World world = event.world;
        if (world != null && world.field_72995_K) {
            ChannelHandler.instance().sendToServer(new ChunkRequestPacketS(event.getChunk().func_76632_l(), world.field_73011_w.field_76574_g));
        }
    }

    @SubscribeEvent
    public void unloadChunk(ChunkEvent.Unload event) {
        if (event.world == null) {
            return;
        }
        World world = event.world;
        if (!world.field_72995_K) {
            return;
        }
        ChunkCoordIntPair pos = event.getChunk().func_76632_l();
        TIWorldData.getWorldData((World)world).chunkDatas.remove(pos.func_77273_a(), pos.func_77274_b());
    }

    @SubscribeEvent
    public void load(WorldEvent.Load event) {
        World world = event.world;
        if (world == null || world.field_72995_K) {
            return;
        }
        try {
            File file = new File("TIWorldData/" + world.func_72912_H().func_76065_j() + "_" + world.field_73011_w.field_76574_g + "_TIWorldData.dat");
            if (!file.exists()) {
                return;
            }
            NBTTagCompound tagCompound = CompressedStreamTools.func_74797_a((File)file);
            if (tagCompound == null) {
                return;
            }
            TIWorldData data = (TIWorldData)SavableHelper.loadDataFromNBT(tagCompound);
            if (data != null) {
                data.world = world;
                ((IWorldDataProvider)world).setWorldData(data);
                data.postLoad();
            }
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public void save(WorldEvent.Save event) {
        World world = event.world;
        if (world == null || world.field_72995_K) {
            return;
        }
        try {
            TIWorldData worldData = TIWorldData.getWorldData(world);
            NBTTagCompound tagCompound = SavableHelper.saveDataToNBT(worldData);
            if (tagCompound != null) {
                File file = new File("TIWorldData/" + world.func_72912_H().func_76065_j() + "_" + world.field_73011_w.field_76574_g + "_TIWorldData.dat");
                FileUtils.forceMkdir((File)new File("TIWorldData"));
                CompressedStreamTools.func_74795_b((NBTTagCompound)tagCompound, (File)file);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

