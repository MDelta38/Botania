/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.common.Shapeshift;
import com.emoniph.witchery.item.ItemBase;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import com.emoniph.witchery.util.TransformCreature;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemMoonCharm
extends ItemBase {
    public ItemMoonCharm() {
        this.autoGenerateTooltip = true;
        this.func_77625_d(1);
        this.func_77656_e(49);
    }

    public boolean func_82789_a(ItemStack item, ItemStack otherMaterial) {
        return otherMaterial.func_77969_a(new ItemStack(Items.field_151043_k));
    }

    @SideOnly(value=Side.CLIENT)
    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.rare;
    }

    public EnumAction func_77661_b(ItemStack itemstack) {
        return EnumAction.bow;
    }

    public int func_77626_a(ItemStack itemstack) {
        return TimeUtil.secsToTicks(3);
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int countdown) {
        ExtendedPlayer playerEx;
        int level;
        if (!player.field_70170_p.field_72995_K && countdown == Math.max(((level = (playerEx = ExtendedPlayer.get(player)).getWerewolfLevel()) - 1) * 4, 1)) {
            if (!ItemMoonCharm.isWolfsbaneActive(player, playerEx) && Shapeshift.INSTANCE.canControlTransform(playerEx)) {
                switch (playerEx.getCreatureType()) {
                    case NONE: {
                        if (player.func_70093_af() && Shapeshift.INSTANCE.isWolfmanAllowed(playerEx)) {
                            Shapeshift.INSTANCE.shiftTo(player, TransformCreature.WOLFMAN);
                        } else {
                            Shapeshift.INSTANCE.shiftTo(player, TransformCreature.WOLF);
                        }
                        ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_FIZZ, (Entity)player, 1.5, 1.5, 16);
                        break;
                    }
                    case WOLF: {
                        if (player.func_70093_af() && Shapeshift.INSTANCE.isWolfmanAllowed(playerEx)) {
                            Shapeshift.INSTANCE.shiftTo(player, TransformCreature.WOLFMAN);
                        } else {
                            Shapeshift.INSTANCE.shiftTo(player, TransformCreature.NONE);
                        }
                        ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_FIZZ, (Entity)player, 1.5, 1.5, 16);
                        break;
                    }
                    case WOLFMAN: {
                        if (player.func_70093_af()) {
                            Shapeshift.INSTANCE.shiftTo(player, TransformCreature.NONE);
                        } else {
                            Shapeshift.INSTANCE.shiftTo(player, TransformCreature.WOLF);
                        }
                        ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_FIZZ, (Entity)player, 1.5, 1.5, 16);
                        break;
                    }
                    default: {
                        ParticleEffect.SMOKE.send(SoundEffect.NOTE_SNARE, (Entity)player, 0.5, 0.5, 8);
                        break;
                    }
                }
            } else {
                ParticleEffect.SMOKE.send(SoundEffect.NOTE_PLING, (Entity)player, 0.5, 0.5, 8);
            }
            stack.func_77972_a(1, (EntityLivingBase)player);
        }
    }

    public ItemStack func_77659_a(ItemStack stack, World world, EntityPlayer player) {
        player.func_71008_a(stack, this.func_77626_a(stack));
        return stack;
    }

    public static boolean isWolfsbaneActive(EntityPlayer player, ExtendedPlayer playerEx) {
        PotionEffect potion = player.func_70660_b(Witchery.Potions.WOLFSBANE);
        if (potion == null) {
            return false;
        }
        int amplifier = 1 + Math.max(0, potion.func_76458_c() * 3 - 1);
        return amplifier >= playerEx.getWerewolfLevel();
    }
}

