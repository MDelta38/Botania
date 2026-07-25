/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.world.World
 *  thaumcraft.client.fx.ParticleEngine
 */
package com.kentington.thaumichorizons.common.lib;

import com.kentington.thaumichorizons.client.fx.FXEssentiaBubble;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;
import thaumcraft.client.fx.ParticleEngine;

public class PacketFXEssentiaBubble
implements IMessage,
IMessageHandler<PacketFXEssentiaBubble, IMessage> {
    double x;
    double y;
    double z;
    int color;

    public PacketFXEssentiaBubble() {
    }

    public PacketFXEssentiaBubble(double x, double y, double z, int color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = color;
    }

    @SideOnly(value=Side.CLIENT)
    public IMessage onMessage(PacketFXEssentiaBubble message, MessageContext ctx) {
        for (int i = 0; i < 10; ++i) {
            FXEssentiaBubble fb = new FXEssentiaBubble((World)Minecraft.func_71410_x().field_71441_e, message.x + (double)(1.5f * (Minecraft.func_71410_x().field_71441_e.field_73012_v.nextFloat() - 0.5f)), message.y, message.z + (double)(1.5f * (Minecraft.func_71410_x().field_71441_e.field_73012_v.nextFloat() - 0.5f)), Minecraft.func_71410_x().field_71439_g.field_70173_aa, message.color, 0.3f, i * 2 + 2);
            fb.field_70145_X = true;
            ParticleEngine.instance.addEffect((World)Minecraft.func_71410_x().field_71441_e, (EntityFX)fb);
        }
        return null;
    }

    public void fromBytes(ByteBuf buf) {
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        this.color = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
        buf.writeInt(this.color);
    }
}

