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
 *  net.minecraft.client.particle.EntityFX
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
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import thaumcraft.client.fx.beams.FXBeam;

public class PacketFXBeamPulse
implements IMessage,
IMessageHandler<PacketFXBeamPulse, IMessage> {
    private int source;
    private int target;
    private int color;

    public PacketFXBeamPulse() {
    }

    public PacketFXBeamPulse(int source, int target, int color) {
        this.source = source;
        this.target = target;
        this.color = color;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.source);
        buffer.writeInt(this.target);
        buffer.writeInt(this.color);
    }

    public void fromBytes(ByteBuf buffer) {
        this.source = buffer.readInt();
        this.target = buffer.readInt();
        this.color = buffer.readInt();
    }

    @SideOnly(value=Side.CLIENT)
    public IMessage onMessage(PacketFXBeamPulse message, MessageContext ctx) {
        Minecraft mc = FMLClientHandler.instance().getClient();
        WorldClient world = mc.field_71441_e;
        Entity var2 = this.getEntityByID(message.source, mc, world);
        Entity var3 = this.getEntityByID(message.target, mc, world);
        if (var2 != null && var3 != null) {
            Color c = new Color(message.color);
            FXBeam beamcon = new FXBeam((World)world, var2.field_70165_t, var2.field_70163_u + (double)var2.func_70047_e(), var2.field_70161_v, var3, (float)c.getRed() / 255.0f, (float)c.getGreen() / 255.0f, (float)c.getBlue() / 255.0f, 20);
            beamcon.blendmode = 771;
            beamcon.field_70130_N = 2.5f;
            beamcon.setType(1);
            beamcon.setReverse(true);
            beamcon.setPulse(true);
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)beamcon);
        }
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    private Entity getEntityByID(int par1, Minecraft mc, WorldClient world) {
        return par1 == mc.field_71439_g.func_145782_y() ? mc.field_71439_g : world.func_73045_a(par1);
    }
}

