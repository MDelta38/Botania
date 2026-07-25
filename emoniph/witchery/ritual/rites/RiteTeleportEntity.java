/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockAreaMarker;
import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.brewing.potions.PotionEnderInhibition;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemHunterClothes;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.rites.RiteTeleportation;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class RiteTeleportEntity
extends RiteTeleportation {
    public RiteTeleportEntity(int radius) {
        super(radius);
    }

    @Override
    protected boolean teleport(World world, int posX, int posY, int posZ, BlockCircle.TileEntityCircle.ActivatedRitual ritual) {
        if (!world.field_72995_K) {
            EntityLivingBase entity;
            ItemStack taglockStack = null;
            for (RitualStep.SacrificedItem item : ritual.sacrificedItems) {
                if (Witchery.Items.TAGLOCK_KIT != item.itemstack.func_77973_b() || item.itemstack.func_77960_j() != 1) continue;
                taglockStack = item.itemstack;
                break;
            }
            if (taglockStack != null && (entity = Witchery.Items.TAGLOCK_KIT.getBoundEntity(world, null, taglockStack, 1)) != null && entity.field_71093_bK != Config.instance().dimensionDreamID && world.field_73011_w.field_76574_g != Config.instance().dimensionDreamID) {
                EntityPlayer player = ritual.getInitiatingPlayer(world);
                boolean isImmune = ItemHunterClothes.isCurseProtectionActive(entity);
                if (!isImmune) {
                    isImmune = BlockAreaMarker.AreaMarkerRegistry.instance().isProtectionActive(entity, this);
                }
                if (!(isImmune || Witchery.Items.POPPET.voodooProtectionActivated(player, null, entity, true, true) || PotionEnderInhibition.isActive((Entity)entity, 3))) {
                    ItemGeneral cfr_ignored_0 = Witchery.Items.GENERIC;
                    ItemGeneral.teleportToLocation(world, posX, posY, posZ, world.field_73011_w.field_76574_g, (Entity)entity, true);
                    return true;
                }
                if (player != null) {
                    if (isImmune) {
                        ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)player, "witchery.rite.blackmagicdampening", new Object[0]);
                    } else {
                        ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)player, "witchery.rite.voodooprotectionactivated", new Object[0]);
                    }
                }
                return false;
            }
        }
        return false;
    }
}

