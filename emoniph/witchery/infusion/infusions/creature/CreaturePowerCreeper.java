/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 */
package com.emoniph.witchery.infusion.infusions.creature;

import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePower;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class CreaturePowerCreeper
extends CreaturePower {
    private static final float WEB_DAMAGE = 1.0f;
    private final int explosionRadius = 3;

    public CreaturePowerCreeper(int powerID) {
        super(powerID, EntityCreeper.class);
    }

    @Override
    public int activateCost(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop) {
        int baseCost = super.activateCost(world, player, elapsedTicks, mop);
        return elapsedTicks >= 60 ? baseCost * 2 : baseCost;
    }

    @Override
    public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop) {
        if (!world.field_72995_K) {
            float f;
            double d = player.field_70165_t;
            double d2 = player.field_70163_u;
            double d3 = player.field_70161_v;
            if (elapsedTicks >= 60) {
                this.getClass();
                f = 3.0f * 2.0f;
            } else {
                this.getClass();
                f = 3.0f;
            }
            world.func_72876_a((Entity)player, d, d2, d3, f, world.func_82736_K().func_82766_b("mobGriefing"));
        }
    }

    @Override
    public void onDamage(World world, EntityPlayer player, LivingHurtEvent event) {
        if (event.source.func_76347_k()) {
            StackTraceElement[] stack;
            for (StackTraceElement element : stack = Thread.currentThread().getStackTrace()) {
                if (!element.getMethodName().equals("onStruckByLightning")) continue;
                int power = Infusion.getNBT((Entity)player).func_74762_e("witcheryInfusionCharges");
                Infusion.getNBT((Entity)player).func_74768_a("witcheryInfusionCharges", Math.min(power + 25, 200));
                Infusion.syncPlayer(world, player);
                if (event.isCancelable()) {
                    event.setCanceled(true);
                }
                return;
            }
        }
    }
}

