/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.common.container.InventoryBaubles
 *  baubles.common.lib.PlayerHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 */
package vazkii.botania.common.core.handler;

import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import vazkii.botania.api.item.IPixieSpawner;
import vazkii.botania.common.entity.EntityPixie;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.armor.elementium.ItemElementiumHelm;

public class PixieHandler {
    @SubscribeEvent
    public void onDamageTaken(LivingHurtEvent event) {
        if (!event.entityLiving.field_70170_p.field_72995_K && event.entityLiving instanceof EntityPlayer && event.source.func_76346_g() != null && event.source.func_76346_g() instanceof EntityLivingBase) {
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            ItemStack stack = player.func_71045_bC();
            float chance = this.getChance(stack);
            for (ItemStack element : player.field_71071_by.field_70460_b) {
                chance += this.getChance(element);
            }
            InventoryBaubles baubles = PlayerHandler.getPlayerBaubles((EntityPlayer)player);
            for (int i = 0; i < baubles.func_70302_i_(); ++i) {
                chance += this.getChance(baubles.func_70301_a(i));
            }
            if (Math.random() < (double)chance) {
                EntityPixie pixie = new EntityPixie(player.field_70170_p);
                pixie.func_70107_b(player.field_70165_t, player.field_70163_u + 2.0, player.field_70161_v);
                if (((ItemElementiumHelm)ModItems.elementiumHelm).hasArmorSet(player)) {
                    int[] potions = new int[]{Potion.field_76440_q.field_76415_H, Potion.field_82731_v.field_76415_H, Potion.field_76421_d.field_76415_H, Potion.field_76437_t.field_76415_H};
                    pixie.setApplyPotionEffect(new PotionEffect(potions[event.entity.field_70170_p.field_73012_v.nextInt(potions.length)], 40, 0));
                }
                float dmg = 4.0f;
                if (stack != null && stack.func_77973_b() == ModItems.elementiumSword) {
                    dmg += 2.0f;
                }
                pixie.setProps((EntityLivingBase)event.source.func_76346_g(), (EntityLivingBase)player, 0, dmg);
                player.field_70170_p.func_72838_d((Entity)pixie);
            }
        }
    }

    float getChance(ItemStack stack) {
        if (stack == null) {
            return 0.0f;
        }
        Item item = stack.func_77973_b();
        if (item instanceof IPixieSpawner) {
            return ((IPixieSpawner)item).getPixieChance(stack);
        }
        return 0.0f;
    }
}

