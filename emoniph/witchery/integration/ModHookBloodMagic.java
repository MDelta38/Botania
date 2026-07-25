/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  WayofTime.alchemicalWizardry.api.event.SacrificeKnifeUsedEvent
 *  WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.common.MinecraftForge
 */
package com.emoniph.witchery.integration;

import WayofTime.alchemicalWizardry.api.event.SacrificeKnifeUsedEvent;
import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.integration.ModHook;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Log;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;

public class ModHookBloodMagic
extends ModHook {
    @Override
    public String getModID() {
        return "AWWayofTime";
    }

    @Override
    protected void doInit() {
    }

    @Override
    protected void doPostInit() {
        try {
            MinecraftForge.EVENT_BUS.register((Object)new EventHooks());
        }
        catch (Throwable ex) {
            Log.instance().debug(String.format("Tried and failed to install hooks for Blood Magic dagger event. %s", ex.toString()));
        }
    }

    @Override
    protected void doReduceMagicPower(EntityLivingBase entity, float factor) {
        IntegrateBloodMagic.reduceMagicPower(entity, factor);
    }

    @Override
    public void boostBloodPowers(EntityPlayer player, float health) {
        IntegrateBloodMagic.boostBloodPowers(player, health);
    }

    private static class IntegrateBloodMagic {
        private IntegrateBloodMagic() {
        }

        public static void reduceMagicPower(EntityLivingBase entity, float factor) {
            if (entity != null && entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer)entity;
                int essence = SoulNetworkHandler.getCurrentEssence((String)player.func_70005_c_());
                if (Config.instance().isDebugging()) {
                    Log.instance().debug(String.format("reduceMagicPower for Blood Magic (%s lp=%d)", player.func_70005_c_(), essence));
                }
                float reduction = essence <= 5000 ? 5000.0f * factor : (essence <= 25000 ? 25000.0f * factor : (essence <= 150000 ? 150000.0f * factor : (essence <= 1000000 ? 1000000.0f * factor : (essence <= 10000000 ? 1.0E7f * factor : 2.14748365E9f * factor))));
                reduction = Math.max(reduction, 1.0f);
                int newEssence = Math.max((int)((float)essence - reduction), 0);
                SoulNetworkHandler.setCurrentEssence((String)player.func_70005_c_(), (int)newEssence);
            }
        }

        public static void boostBloodPowers(EntityPlayer player, float health) {
            int LP_PER_LIFE = 100;
            String playerName = player.func_70005_c_();
            int newlevel = SoulNetworkHandler.getCurrentEssence((String)playerName) + (int)health * 100;
            SoulNetworkHandler.setCurrentEssence((String)playerName, (int)newlevel);
        }
    }

    public static class EventHooks {
        @SubscribeEvent
        public void onSacrificeKnifeUsed(SacrificeKnifeUsedEvent event) {
            ExtendedPlayer playerEx = ExtendedPlayer.get(event.player);
            if (playerEx != null && playerEx.isVampire()) {
                event.shouldDrainHealth = false;
                if (!event.player.field_70170_p.field_72995_K && !playerEx.decreaseBloodPower(event.healthDrained * 100, true)) {
                    event.shouldFillAltar = false;
                }
            }
        }
    }
}

