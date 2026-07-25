/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.World
 */
package thaumcraft.common.lib.network.fx;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import thaumcraft.client.fx.bolt.FXLightningBolt;

public class PacketFXZap
implements IMessage,
IMessageHandler<PacketFXZap, IMessage> {
    private int source;
    private int target;

    public PacketFXZap() {
    }

    public PacketFXZap(int source, int target) {
        this.source = source;
        this.target = target;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.source);
        buffer.writeInt(this.target);
    }

    public void fromBytes(ByteBuf buffer) {
        this.source = buffer.readInt();
        this.target = buffer.readInt();
    }

    @SideOnly(value=Side.CLIENT)
    public IMessage onMessage(PacketFXZap message, MessageContext ctx) {
        Minecraft mc = FMLClientHandler.instance().getClient();
        WorldClient world = mc.field_71441_e;
        Entity var2 = this.getEntityByID(message.source, mc, world);
        Entity var3 = this.getEntityByID(message.target, mc, world);
        if (var2 != null && var3 != null) {
            FXLightningBolt bolt = new FXLightningBolt((World)world, var2.field_70165_t, var2.field_70121_D.field_72338_b + (double)(var2.field_70131_O / 2.0f), var2.field_70161_v, var3.field_70165_t, var3.field_70121_D.field_72338_b + (double)(var3.field_70131_O / 2.0f), var3.field_70161_v, world.field_73012_v.nextLong(), 6, 0.5f, 8);
            bolt.defaultFractal();
            bolt.setType(2);
            bolt.setWidth(0.125f);
            bolt.finalizeBolt();
        }
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    private Entity getEntityByID(int par1, Minecraft mc, WorldClient world) {
        return par1 == mc.field_71439_g.func_145782_y() ? mc.field_71439_g : world.func_73045_a(par1);
    }
}

