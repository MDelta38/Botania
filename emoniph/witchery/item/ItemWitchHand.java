/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityWitch
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 */
package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.entity.EntityCovenWitch;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.item.ItemBase;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class ItemWitchHand
extends ItemBase {
    public ItemWitchHand() {
        this.func_77625_d(1);
        this.func_77664_n();
    }

    @SideOnly(value=Side.CLIENT)
    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.uncommon;
    }

    public void func_77663_a(ItemStack itemstack, World world, Entity entity, int par4, boolean par5) {
        if (entity instanceof EntityPlayer) {
            Infusion.Registry.instance().get((EntityPlayer)entity).onUpdate(itemstack, world, (EntityPlayer)entity, par4, par5);
        }
    }

    public boolean onLeftClickEntity(ItemStack itemstack, EntityPlayer player, Entity entity) {
        Infusion.Registry.instance().get(player).onLeftClickEntity(itemstack, player.field_70170_p, player, entity);
        return true;
    }

    public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer player) {
        player.func_71008_a(itemstack, this.func_77626_a(itemstack));
        return itemstack;
    }

    public int func_77626_a(ItemStack itemstack) {
        return 400;
    }

    public void onUsingTick(ItemStack itemstack, EntityPlayer player, int countdown) {
        Infusion.Registry.instance().get(player).onUsingItemTick(itemstack, player.field_70170_p, player, countdown);
    }

    public void func_77615_a(ItemStack itemstack, World world, EntityPlayer player, int countdown) {
        if (world.field_72995_K || !Infusion.isOnCooldown(world, itemstack)) {
            Infusion.Registry.instance().get(player).onPlayerStoppedUsing(itemstack, world, player, countdown);
        }
    }

    public static class EventHooks {
        @SubscribeEvent
        public void onLivingDeath(LivingDeathEvent event) {
            Entity entitySource;
            if (!event.entityLiving.field_70170_p.field_72995_K && (event.entityLiving instanceof EntityWitch || event.entityLiving instanceof EntityCovenWitch) && (entitySource = event.source.func_76364_f()) != null && entitySource instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer)entitySource;
                boolean hasArthana = player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() == Witchery.Items.ARTHANA;
                double d = player.field_70170_p.field_73012_v.nextDouble();
                double d2 = hasArthana ? 0.5 : 0.33;
                if (d < d2) {
                    ItemStack itemstack = new ItemStack(Witchery.Items.WITCH_HAND);
                    EntityItem entityItem = new EntityItem(event.entityLiving.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u, event.entityLiving.field_70161_v, itemstack);
                    event.entityLiving.field_70170_p.func_72838_d((Entity)entityItem);
                }
            }
        }
    }
}

