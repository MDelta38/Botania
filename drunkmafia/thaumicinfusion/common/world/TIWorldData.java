/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.ChunkCoordIntPair
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.DimensionManager
 *  thaumcraft.api.WorldCoordinates
 */
package drunkmafia.thaumicinfusion.common.world;

import drunkmafia.thaumicinfusion.common.util.helper.ReflectionLookup;
import drunkmafia.thaumicinfusion.common.util.quadtree.QuadTree;
import drunkmafia.thaumicinfusion.common.world.ChunkData;
import drunkmafia.thaumicinfusion.common.world.ISavable;
import drunkmafia.thaumicinfusion.common.world.IWorldDataProvider;
import drunkmafia.thaumicinfusion.common.world.SavableHelper;
import drunkmafia.thaumicinfusion.common.world.data.BlockSavable;
import drunkmafia.thaumicinfusion.net.ChannelHandler;
import drunkmafia.thaumicinfusion.net.packet.server.BlockSyncPacketC;
import drunkmafia.thaumicinfusion.net.packet.server.DataRemovePacketC;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import thaumcraft.api.WorldCoordinates;

public class TIWorldData
implements ISavable {
    private static ReflectionLookup<World> worldLookup = new ReflectionLookup<World>(World.class);
    public WorldCoordinates surveyPosition;
    public World world;
    public QuadTree<ChunkData> chunkDatas = new QuadTree<ChunkData>(ChunkData.class, -2000000.0, -2000000.0, 2000000.0, 2000000.0);

    public static TIWorldData getWorldData(World world) {
        if (world == null || !(world instanceof IWorldDataProvider)) {
            return null;
        }
        IWorldDataProvider dataProvider = (IWorldDataProvider)world;
        TIWorldData worldData = dataProvider.getWorldData();
        if (!world.field_72995_K) {
            world = DimensionManager.getWorld((int)world.field_73011_w.field_76574_g);
        }
        if (worldData == null) {
            worldData = new TIWorldData();
            dataProvider.setWorldData(worldData);
        }
        worldData.world = world;
        return worldData;
    }

    public static World getWorld(IBlockAccess blockAccess) {
        if (worldLookup == null) {
            worldLookup = new ReflectionLookup<World>(World.class);
        }
        return blockAccess != null ? (blockAccess instanceof World ? (World)blockAccess : worldLookup.getObjectFrom(blockAccess)) : null;
    }

    public void addBlock(BlockSavable block, boolean init, boolean packet) {
        if (block == null) {
            return;
        }
        if (this.world == null) {
            this.world = DimensionManager.getWorld((int)block.getCoords().dim);
        }
        WorldCoordinates coordinates = block.getCoords();
        ChunkCoordIntPair chunkPos = new ChunkCoordIntPair(coordinates.x >> 4, coordinates.z >> 4);
        ChunkData chunkData = this.chunkDatas.get(chunkPos.func_77273_a(), chunkPos.func_77274_b(), null);
        if (chunkData == null) {
            chunkData = new ChunkData(chunkPos);
            this.chunkDatas.set(chunkPos.func_77273_a(), chunkPos.func_77274_b(), chunkData);
        }
        chunkData.addBlock(block, coordinates.x, coordinates.y, coordinates.z);
        if (init && !block.isInit()) {
            block.dataLoad(this.world);
        }
        if (!this.world.field_72995_K && packet) {
            ChannelHandler.instance().sendToDimension(new BlockSyncPacketC(block), this.world.field_73011_w.field_76574_g);
        }
    }

    public List<ChunkData> getChunksInRange(int xMin, int zMin, int xMax, int zMax) {
        return this.chunkDatas.searchWithinObject(xMin, zMin, xMax, zMax);
    }

    public void addBlock(BlockSavable block) {
        this.addBlock(block, false, false);
    }

    public void postLoad() {
        for (BlockSavable savable : this.getAllStoredData()) {
            if (savable == null) continue;
            if (this.world == null) {
                this.world = DimensionManager.getWorld((int)savable.getCoords().dim);
            } else {
                savable.getCoords().dim = this.world.field_73011_w.field_76574_g;
            }
            if (savable.isInit()) continue;
            savable.dataLoad(this.world);
        }
    }

    public <T> T getBlock(Class<T> type, WorldCoordinates coords) {
        ChunkCoordIntPair chunkPos = new ChunkCoordIntPair(coords.x >> 4, coords.z >> 4);
        ChunkData chunkData = this.chunkDatas.get(chunkPos.func_77273_a(), chunkPos.func_77274_b(), null);
        return chunkData != null ? (T)chunkData.getBlock(type, coords.x, coords.y, coords.z) : null;
    }

    public void removeData(Class<? extends BlockSavable> type, WorldCoordinates coords, boolean sendPacket) {
        ChunkCoordIntPair chunkPos = new ChunkCoordIntPair(coords.x >> 4, coords.z >> 4);
        ChunkData chunkData = this.chunkDatas.get(chunkPos.func_77273_a(), chunkPos.func_77274_b(), null);
        if (chunkData != null) {
            chunkData.removeData(type, coords.x, coords.y, coords.z);
            if (sendPacket) {
                coords.dim = this.world.field_73011_w.field_76574_g;
                ChannelHandler.instance().sendToAll(new DataRemovePacketC(type, coords));
            }
        }
    }

    public BlockSavable[] getAllStoredData() {
        ArrayList savables = new ArrayList();
        for (ChunkData chunks : this.chunkDatas.getValues()) {
            Collections.addAll(savables, chunks.getAllBlocks());
        }
        return savables.size() != 0 ? savables.toArray(new BlockSavable[1]) : new BlockSavable[]{};
    }

    @Override
    public void readNBT(NBTTagCompound tag) {
        int size = tag.func_74762_e("Chunks");
        for (int i = 0; i < size; ++i) {
            ChunkData chunkData;
            if (!tag.func_74764_b("Chunk:" + i) || (chunkData = (ChunkData)SavableHelper.loadDataFromNBT(tag.func_74775_l("Chunk:" + i))) == null) continue;
            for (BlockSavable data : chunkData.getAllBlocks()) {
                this.addBlock(data);
            }
        }
    }

    @Override
    public void writeNBT(NBTTagCompound tag) {
        ChunkData[] chunks = this.chunkDatas.getValues();
        tag.func_74768_a("Chunks", chunks.length);
        for (int i = 0; i < chunks.length; ++i) {
            ChunkData chunkData = chunks[i];
            if (chunkData == null) continue;
            tag.func_74782_a("Chunk:" + i, (NBTBase)SavableHelper.saveDataToNBT(chunkData));
        }
    }
}

