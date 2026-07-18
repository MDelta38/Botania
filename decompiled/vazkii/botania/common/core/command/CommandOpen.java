/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 */
package vazkii.botania.common.core.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import vazkii.botania.common.item.ItemLexicon;

public class CommandOpen
extends CommandBase {
    public String func_71517_b() {
        return "botania-open";
    }

    public String func_71518_a(ICommandSender p_71518_1_) {
        return "<entry>";
    }

    public void func_71515_b(ICommandSender sender, String[] args) {
        if (sender instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)sender;
            ItemStack stack = player.func_71045_bC();
            if (stack != null && stack.func_77973_b() instanceof ItemLexicon) {
                ItemLexicon.setForcedPage(stack, args[0]);
                ItemLexicon.setQueueTicks(stack, 5);
            } else {
                sender.func_145747_a(new ChatComponentTranslation("botaniamisc.noLexicon", new Object[0]).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.RED)));
            }
        }
    }

    public int func_82362_a() {
        return 0;
    }

    public boolean func_71519_b(ICommandSender p_71519_1_) {
        return p_71519_1_ instanceof EntityPlayer;
    }
}

