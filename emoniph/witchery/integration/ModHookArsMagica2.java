/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  am2.api.ArsMagicaApi
 *  am2.api.IExtendedProperties
 *  am2.api.enchantment.IAMEnchantmentHelper
 *  am2.api.events.ReconstructorRepairEvent
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.common.MinecraftForge
 */
package com.emoniph.witchery.integration;

import am2.api.ArsMagicaApi;
import am2.api.IExtendedProperties;
import am2.api.enchantment.IAMEnchantmentHelper;
import am2.api.events.ReconstructorRepairEvent;
import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.integration.ModHook;
import com.emoniph.witchery.item.ItemChalk;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class ModHookArsMagica2
extends ModHook {
    @Override
    public String getModID() {
        return "arsmagica2";
    }

    @Override
    protected void doInit() {
    }

    @Override
    protected void doPostInit() {
        Witchery.modHooks.isAM2Present = true;
        MinecraftForge.EVENT_BUS.register((Object)new EventHooks());
    }

    @Override
    protected void doReduceMagicPower(EntityLivingBase entity, float factor) {
        IntegrateAM2.doReduceMagicPower(entity, factor);
    }

    @Override
    protected void makeItemModProof(ItemStack stack) {
        IntegrateAM2.makeItemModProof(stack);
    }

    public static class EventHooks {
        @SubscribeEvent
        public void onReconstructorRepair(ReconstructorRepairEvent event) {
            Item item;
            if (event.item != null && !event.isCanceled() && ((item = event.item.func_77973_b()) == Witchery.Items.POPPET || item instanceof ItemChalk)) {
                event.setCanceled(true);
            }
        }
    }

    private static class IntegrateAM2 {
        private IntegrateAM2() {
        }

        public static void doReduceMagicPower(EntityLivingBase entity, float factor) {
            IExtendedProperties props = ArsMagicaApi.instance.getExtendedProperties(entity);
            if (props != null) {
                float maxMana = props.getMaxMana();
                float mana = props.getCurrentMana();
                if (maxMana > 0.0f && mana > 0.0f) {
                    float reduction = Math.max(maxMana * factor, 1.0f);
                    float newMana = Math.max(mana - reduction, 0.0f);
                    props.setCurrentMana(newMana);
                }
            }
        }

        public static void makeItemModProof(ItemStack stack) {
            int id;
            IAMEnchantmentHelper helper;
            if (stack.func_77956_u() && ArsMagicaApi.instance != null && (helper = ArsMagicaApi.instance.getEnchantHelper()) != null && (id = helper.getSoulboundID()) >= 0 && id < Enchantment.field_77331_b.length) {
                stack.func_77966_a(Enchantment.field_77331_b[id], 1);
            }
        }
    }
}

