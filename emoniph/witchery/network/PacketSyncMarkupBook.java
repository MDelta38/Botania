/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 */
package com.emoniph.witchery.network;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.item.ItemMarkupBook;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;

public class PacketSyncMarkupBook
implements IMessage {
    private int slot;
    private List<String> pages;

    public PacketSyncMarkupBook() {
    }

    public PacketSyncMarkupBook(int slot, List<String> pageStack) {
        this.slot = slot;
        this.pages = new ArrayList<String>(pageStack);
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.slot);
        buffer.writeInt(this.pages.size());
        for (String s : this.pages) {
            ByteBufUtils.writeUTF8String((ByteBuf)buffer, (String)s);
        }
    }

    public void fromBytes(ByteBuf buffer) {
        this.slot = buffer.readInt();
        int size = buffer.readInt();
        this.pages = new ArrayList<String>(size);
        for (int i = 0; i < size; ++i) {
            this.pages.add(ByteBufUtils.readUTF8String((ByteBuf)buffer));
        }
    }

    public static class Handler
    implements IMessageHandler<PacketSyncMarkupBook, IMessage> {
        public IMessage onMessage(PacketSyncMarkupBook message, MessageContext ctx) {
            ItemStack stack;
            EntityPlayer player = Witchery.proxy.getPlayer(ctx);
            if (message.slot >= 0 && message.slot < player.field_71071_by.func_70302_i_() && (stack = player.field_71071_by.func_70301_a(message.slot)) != null && stack.func_77973_b() instanceof ItemMarkupBook) {
                if (!stack.func_77942_o()) {
                    stack.func_77982_d(new NBTTagCompound());
                }
                NBTTagList pageStack = new NBTTagList();
                for (String s : message.pages) {
                    pageStack.func_74742_a((NBTBase)new NBTTagString(s));
                }
                stack.func_77978_p().func_74782_a("pageStack", (NBTBase)pageStack);
                ((ItemMarkupBook)stack.func_77973_b()).onBookRead(stack, player.field_70170_p, player);
            }
            return null;
        }
    }
}

