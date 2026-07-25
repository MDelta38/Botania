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
 *  net.minecraft.potion.Potion
 */
package com.kentington.thaumichorizons.common.lib;

import com.kentington.thaumichorizons.common.items.lenses.LensManager;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;

public class PacketRemoveNightvision
implements IMessage,
IMessageHandler<PacketRemoveNightvision, IMessage> {
    @SideOnly(value=Side.CLIENT)
    public IMessage onMessage(PacketRemoveNightvision message, MessageContext ctx) {
        Minecraft.func_71410_x().field_71439_g.func_82170_o(Potion.field_76439_r.field_76415_H);
        Minecraft.func_71410_x();
        LensManager.nightVisionOffTime = Minecraft.func_71386_F() + 100L;
        return null;
    }

    public void fromBytes(ByteBuf buf) {
    }

    public void toBytes(ByteBuf buf) {
    }
}

