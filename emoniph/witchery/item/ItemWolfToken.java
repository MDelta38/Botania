/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.item;

import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.item.ItemBase;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemWolfToken
extends ItemBase {
    public ItemWolfToken() {
        this.autoGenerateTooltip = true;
        this.func_77625_d(1);
        this.func_77656_e(0);
    }

    @SideOnly(value=Side.CLIENT)
    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.epic;
    }

    public EnumAction func_77661_b(ItemStack itemstack) {
        return EnumAction.bow;
    }

    public int func_77626_a(ItemStack itemstack) {
        return TimeUtil.secsToTicks(1);
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int countdown) {
        if (!player.field_70170_p.field_72995_K && countdown == 1) {
            if (player.func_70093_af()) {
                ExtendedPlayer playerEx = ExtendedPlayer.get(player);
                int vampLevel = playerEx.getVampireLevel() + 1;
                if (vampLevel > 10) {
                    vampLevel = 0;
                }
                playerEx.setVampireLevel(vampLevel);
                ChatUtil.sendTranslated(EnumChatFormatting.GREEN, (ICommandSender)player, "witchery.vampire.setlevel", Integer.valueOf(vampLevel).toString());
                ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_FIZZ, (Entity)player, 1.5, 1.5, 16);
            } else {
                ExtendedPlayer playerEx = ExtendedPlayer.get(player);
                int wolfLevel = playerEx.getWerewolfLevel() + 1;
                if (wolfLevel > 10) {
                    wolfLevel = 0;
                }
                playerEx.setWerewolfLevel(wolfLevel);
                ChatUtil.sendTranslated(EnumChatFormatting.GREEN, (ICommandSender)player, "witchery.werewolf.setlevel", Integer.valueOf(wolfLevel).toString());
                ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_FIZZ, (Entity)player, 1.5, 1.5, 16);
            }
        }
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        player.func_71008_a(stack, this.func_77626_a(stack));
        return stack;
    }
}

