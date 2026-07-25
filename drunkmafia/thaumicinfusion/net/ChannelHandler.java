/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.DimensionManager
 */
package drunkmafia.thaumicinfusion.net;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import drunkmafia.thaumicinfusion.common.lib.ModInfo;
import drunkmafia.thaumicinfusion.net.packet.client.ChunkRequestPacketS;
import drunkmafia.thaumicinfusion.net.packet.client.WandAspectPacketS;
import drunkmafia.thaumicinfusion.net.packet.server.BlockSyncPacketC;
import drunkmafia.thaumicinfusion.net.packet.server.ChunkSyncPacketC;
import drunkmafia.thaumicinfusion.net.packet.server.DataRemovePacketC;
import drunkmafia.thaumicinfusion.net.packet.server.EffectSyncPacketC;
import drunkmafia.thaumicinfusion.net.packet.server.EntitySyncPacketC;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

public class ChannelHandler
extends SimpleNetworkWrapper {
    private static ChannelHandler instance;
    private int ordinal;

    public ChannelHandler(String channelName) {
        super(channelName);
    }

    public static void registerPackets() {
        Side S = Side.SERVER;
        Side C = Side.CLIENT;
        ChannelHandler handler = ChannelHandler.instance();
        handler.registerMessage(ChunkRequestPacketS.Handler.class, ChunkRequestPacketS.class, handler.getOrdinal(), S);
        handler.registerMessage(WandAspectPacketS.Handler.class, WandAspectPacketS.class, handler.getOrdinal(), S);
        handler.registerMessage(ChunkSyncPacketC.Handler.class, ChunkSyncPacketC.class, handler.getOrdinal(), C);
        handler.registerMessage(BlockSyncPacketC.Handler.class, BlockSyncPacketC.class, handler.getOrdinal(), C);
        handler.registerMessage(EffectSyncPacketC.Handler.class, EffectSyncPacketC.class, handler.getOrdinal(), C);
        handler.registerMessage(EntitySyncPacketC.Handler.class, EntitySyncPacketC.class, handler.getOrdinal(), C);
        handler.registerMessage(DataRemovePacketC.Handler.class, DataRemovePacketC.class, handler.getOrdinal(), C);
    }

    public static ChannelHandler instance() {
        return instance != null ? instance : (instance = new ChannelHandler(ModInfo.CHANNEL));
    }

    @SideOnly(value=Side.CLIENT)
    public static World getClientWorld() {
        return FMLClientHandler.instance().getClient().field_71441_e;
    }

    public static WorldServer getServerWorld(int dim) {
        return DimensionManager.getWorld((int)dim);
    }

    private int getOrdinal() {
        return this.ordinal++;
    }
}

