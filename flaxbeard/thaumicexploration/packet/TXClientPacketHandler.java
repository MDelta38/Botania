/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.network.FMLNetworkEvent$ClientCustomPacketEvent
 *  cpw.mods.fml.common.network.internal.FMLProxyPacket
 *  io.netty.buffer.ByteBuf
 *  io.netty.buffer.ByteBufInputStream
 *  io.netty.buffer.ByteBufOutputStream
 *  io.netty.buffer.Unpooled
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.DimensionManager
 *  thaumcraft.client.fx.bolt.FXLightningBolt
 */
package flaxbeard.thaumicexploration.packet;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.packet.TXServerPacketHandler;
import flaxbeard.thaumicexploration.tile.TileEntityAutoSorter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import java.io.IOException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import thaumcraft.client.fx.bolt.FXLightningBolt;

public class TXClientPacketHandler
extends TXServerPacketHandler {
    public static void sendTypeChangePacket(TileEntityAutoSorter te, ChunkCoordinates cc, int p, int side) {
        ByteBuf buf = Unpooled.buffer();
        ByteBufOutputStream out = new ByteBufOutputStream(buf);
        try {
            out.writeByte(44);
            out.writeInt(te.func_145831_w().field_73011_w.field_76574_g);
            out.writeInt(te.field_145851_c);
            out.writeInt(te.field_145848_d);
            out.writeInt(te.field_145849_e);
            out.writeInt(cc.field_71574_a);
            out.writeInt(cc.field_71572_b);
            out.writeInt(cc.field_71573_c);
            out.writeInt(p);
            out.writeInt(side);
        }
        catch (IOException iOException) {
            // empty catch block
        }
        FMLProxyPacket packet = new FMLProxyPacket(buf, "tExploration");
        ThaumicExploration.channel.sendToServer(packet);
        try {
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public void onClientPacket(FMLNetworkEvent.ClientCustomPacketEvent event) {
        EntityClientPlayerMP player = Minecraft.func_71410_x().field_71439_g;
        ByteBufInputStream bbis = new ByteBufInputStream(event.packet.payload());
        try {
            int readInt;
            byte packetID = bbis.readByte();
            int dimension = bbis.readInt();
            WorldServer world = DimensionManager.getWorld((int)dimension);
            if (packetID == 3 && world != null && world.func_73045_a(readInt = bbis.readInt()) != null) {
                EntityLivingBase target = (EntityLivingBase)world.func_73045_a(readInt);
                readInt = bbis.readInt();
                if (world.func_73045_a(readInt) != null && player.func_110124_au() != Minecraft.func_71410_x().field_71439_g.func_110124_au()) {
                    FXLightningBolt bolt = new FXLightningBolt(player.field_70170_p, player.field_70165_t, player.field_70121_D.field_72338_b + (double)(player.field_70131_O / 2.0f) + 0.75, player.field_70161_v, target.field_70165_t, target.field_70121_D.field_72337_e - 0.5, target.field_70161_v, player.field_70170_p.field_73012_v.nextLong(), 6, 0.5f, 5);
                    bolt.defaultFractal();
                    bolt.setType(5);
                    bolt.setWidth(0.0625f);
                    bolt.finalizeBolt();
                }
            }
            bbis.close();
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}

