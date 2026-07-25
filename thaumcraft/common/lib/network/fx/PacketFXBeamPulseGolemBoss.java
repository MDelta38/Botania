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
 *  net.minecraft.entity.EntityLivingBase
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
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import thaumcraft.client.fx.beams.FXBeamGolemBoss;

public class PacketFXBeamPulseGolemBoss
implements IMessage,
IMessageHandler<PacketFXBeamPulseGolemBoss, IMessage> {
    private int source;
    private int target;

    public PacketFXBeamPulseGolemBoss() {
    }

    public PacketFXBeamPulseGolemBoss(int source, int target) {
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
    public IMessage onMessage(PacketFXBeamPulseGolemBoss message, MessageContext ctx) {
        Minecraft mc = FMLClientHandler.instance().getClient();
        WorldClient world = mc.field_71441_e;
        EntityLivingBase var2 = (EntityLivingBase)this.getEntityByID(message.source, mc, world);
        EntityLivingBase var3 = (EntityLivingBase)this.getEntityByID(message.target, mc, world);
        if (var2 != null && var3 != null) {
            FXBeamGolemBoss beamcon = new FXBeamGolemBoss((World)world, var2, (Entity)var3, 0.07f, 0.376f, 0.325f, 20);
            beamcon.blendmode = 1;
            beamcon.field_70130_N = 3.0f;
            beamcon.setType(2);
            beamcon.setReverse(false);
            beamcon.setPulse(true);
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)beamcon);
            FXBeamGolemBoss beamcon2 = new FXBeamGolemBoss((World)world, var2, (Entity)var3, 1.0f, 0.5f, 0.5f, 20);
            beamcon2.blendmode = 1;
            beamcon2.field_70130_N = 1.5f;
            beamcon2.setType(1);
            beamcon2.setReverse(false);
            beamcon2.setPulse(true);
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)beamcon2);
        }
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    private Entity getEntityByID(int par1, Minecraft mc, WorldClient world) {
        return par1 == mc.field_71439_g.func_145782_y() ? mc.field_71439_g : world.func_73045_a(par1);
    }
}

