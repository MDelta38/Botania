/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaublesApi
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 */
package com.kentington.thaumichorizons.common.lib;

import baubles.api.BaublesApi;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public class PacketNoMoreItems
implements IMessage,
IMessageHandler<PacketNoMoreItems, IMessage> {
    @SideOnly(value=Side.CLIENT)
    public IMessage onMessage(PacketNoMoreItems message, MessageContext ctx) {
        Minecraft.func_71410_x().field_71439_g.field_71071_by.func_146027_a(null, -1);
        IInventory baubles = BaublesApi.getBaubles((EntityPlayer)Minecraft.func_71410_x().field_71439_g);
        baubles.func_70299_a(0, null);
        baubles.func_70299_a(1, null);
        baubles.func_70299_a(2, null);
        baubles.func_70299_a(3, null);
        return null;
    }

    public void fromBytes(ByteBuf buf) {
    }

    public void toBytes(ByteBuf buf) {
    }
}

