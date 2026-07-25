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

import com.kentington.thaumichorizons.client.fx.FXContainment;
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

public class PacketFXContainment
implements IMessage,
IMessageHandler<PacketFXContainment, IMessage> {
    double x;
    double y;
    double z;

    public PacketFXContainment() {
    }

    public PacketFXContainment(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @SideOnly(value=Side.CLIENT)
    public IMessage onMessage(PacketFXContainment message, MessageContext ctx) {
        FXContainment fb = new FXContainment((World)Minecraft.func_71410_x().field_71441_e, message.x, message.y, message.z);
        ParticleEngine.instance.addEffect((World)Minecraft.func_71410_x().field_71441_e, (EntityFX)fb);
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

