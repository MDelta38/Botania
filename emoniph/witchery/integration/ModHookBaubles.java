/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaublesApi
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 */
package com.emoniph.witchery.integration;

import baubles.api.BaublesApi;
import com.emoniph.witchery.integration.ModHook;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class ModHookBaubles
extends ModHook {
    private static final String[] BANNED_ITEMS = new String[]{"item.superLavaPendant", "item.lavaPendant", "item.odinRing", "item.aesirRing"};

    @Override
    public String getModID() {
        return "Baubles";
    }

    @Override
    protected void doInit() {
    }

    @Override
    protected void doPostInit() {
    }

    @Override
    protected void doReduceMagicPower(EntityLivingBase entity, float factor) {
    }

    @Override
    public boolean canVampireBeKilled(EntityPlayer player) {
        return IntegrateBaubles.canVampireBeVilled(player);
    }

    private static class IntegrateBaubles {
        private IntegrateBaubles() {
        }

        public static boolean canVampireBeVilled(EntityPlayer player) {
            IInventory inv = BaublesApi.getBaubles((EntityPlayer)player);
            if (inv == null) {
                return false;
            }
            for (int slot = 0; slot < inv.func_70302_i_(); ++slot) {
                ItemStack stack = inv.func_70301_a(slot);
                if (stack == null) continue;
                for (String badItem : BANNED_ITEMS) {
                    if (!badItem.equals(stack.func_77977_a())) continue;
                    return true;
                }
            }
            return false;
        }
    }
}

