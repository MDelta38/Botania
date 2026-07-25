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
 *  net.minecraft.client.particle.EntitySpellParticleFX
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.lib;

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
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.world.World;

public class PacketFXDeadCreature
implements IMessage,
IMessageHandler<PacketFXDeadCreature, IMessage> {
    double x;
    double y;
    double z;

    public PacketFXDeadCreature() {
    }

    public PacketFXDeadCreature(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @SideOnly(value=Side.CLIENT)
    public IMessage onMessage(PacketFXDeadCreature message, MessageContext ctx) {
        WorldClient world = Minecraft.func_71410_x().field_71441_e;
        for (int i = 0; i < 36; ++i) {
            EntitySpellParticleFX fb = new EntitySpellParticleFX((World)world, this.x, this.y, this.z, 0.0, 0.0, 0.0);
            fb.func_70538_b(0.8f, 0.2f, 0.2f);
            fb.field_70159_w = (world.field_73012_v.nextFloat() - 0.5f) * 0.4f;
            fb.field_70181_x = (world.field_73012_v.nextFloat() - 0.5f) * 0.2f;
            fb.field_70179_y = (world.field_73012_v.nextFloat() - 0.5f) * 0.4f;
            fb.field_70145_X = true;
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fb);
        }
        world.func_72908_a(this.x + 0.5, this.y, this.z + 0.5, "thaumcraft:gore", 2.0f, 1.0f);
        return null;
    }

    public void fromBytes(ByteBuf buf) {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
    }
}

