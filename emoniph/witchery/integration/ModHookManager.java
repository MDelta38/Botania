/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package com.emoniph.witchery.integration;

import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.integration.ModHook;
import com.emoniph.witchery.util.Log;
import java.util.ArrayList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ModHookManager {
    private ArrayList<ModHook> hooks = new ArrayList();
    public boolean isTinkersPresent;
    public boolean isAM2Present;
    public boolean isMorphPresent;

    public void register(Class<? extends ModHook> clazz) {
        try {
            ModHook hook = clazz.newInstance();
            this.hooks.add(hook);
        }
        catch (Throwable e) {
            Log.instance().warning(e, "unhandled exception loading ModHook");
        }
    }

    public void init() {
        for (ModHook hook : this.hooks) {
            try {
                hook.init();
            }
            catch (Throwable e) {
                Log.instance().warning(e, String.format("unhandled exception init for hook %s", hook.getModID()));
            }
        }
    }

    public void postInit() {
        for (ModHook hook : this.hooks) {
            try {
                hook.postInit();
            }
            catch (Throwable e) {
                Log.instance().warning(e, String.format("unhandled exception post init for hook %s", hook.getModID()));
            }
        }
    }

    public void reducePowerLevels(EntityLivingBase entity, float reduction) {
        if (entity == null || entity.field_70170_p == null || entity.field_70170_p.field_72995_K) {
            return;
        }
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            int maxEnergy = Infusion.getMaxEnergy(player);
            int currentEnergy = Infusion.getCurrentEnergy(player);
            if (maxEnergy > 0 && currentEnergy > 0) {
                int reduceBy = Math.max((int)((float)maxEnergy * reduction), 1);
                int newMana = Math.max(currentEnergy - reduceBy, 0);
                Infusion.setCurrentEnergy(player, newMana);
            }
        }
        for (ModHook hook : this.hooks) {
            try {
                hook.reduceMagicPower(entity, reduction);
            }
            catch (Throwable e) {
                Log.instance().warning(e, String.format("unhandled exception post init for hook %s", hook.getModID()));
            }
        }
    }

    public void boostBloodPowers(EntityPlayer player, float health) {
        for (ModHook hook : this.hooks) {
            try {
                hook.boostBloodPowers(player, health);
            }
            catch (Throwable e) {
                Log.instance().warning(e, String.format("unhandled exception post init for hook %s", hook.getModID()));
            }
        }
    }

    public boolean canVampireBeKilled(EntityPlayer player) {
        for (ModHook hook : this.hooks) {
            try {
                if (!hook.canVampireBeKilled(player)) continue;
                return true;
            }
            catch (Throwable e) {
                Log.instance().warning(e, String.format("unhandled exception post init for hook %s", hook.getModID()));
            }
        }
        return false;
    }

    public void makeItemModProof(ItemStack stack) {
        for (ModHook hook : this.hooks) {
            try {
                hook.tryMakeItemModProof(stack);
            }
            catch (Throwable e) {
                Log.instance().warning(e, String.format("unhandled exception post init for hook %s", hook.getModID()));
            }
        }
    }
}

