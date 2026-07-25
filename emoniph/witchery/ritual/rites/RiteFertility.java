/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemDye
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.ritual.rites.RiteExpandingEffect;
import com.emoniph.witchery.util.Dye;
import com.emoniph.witchery.util.Log;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class RiteFertility
extends RiteExpandingEffect {
    public RiteFertility(int radius, int height) {
        super(radius, height, false);
    }

    @Override
    public void doBlockAction(World world, int posX, int posY, int posZ, int currentRadius, EntityPlayer player, boolean enhanced) {
        Block blockID = world.func_147439_a(posX, posY, posZ);
        if ((blockID != Blocks.field_150346_d || blockID != Blocks.field_150349_c || blockID != Blocks.field_150391_bh || blockID != Blocks.field_150458_ak || world.field_73012_v.nextInt(5) == 0) && player != null) {
            ItemDye.applyBonemeal((ItemStack)Dye.BONE_MEAL.createStack(), (World)world, (int)posX, (int)posY, (int)posZ, (EntityPlayer)player);
        }
    }

    @Override
    public boolean doRadiusAction(World world, int posX, int posY, int posZ, int radius, EntityPlayer player, boolean enhanced) {
        double radiusSq = radius * radius;
        double minSq = Math.max(0, (radius - 1) * (radius - 1));
        for (Object obj : world.field_73010_i) {
            EntityPlayer entityPlayer = (EntityPlayer)obj;
            double distanceSq = entityPlayer.func_70092_e(0.5 + (double)posX, 0.5 + (double)posY, 0.5 + (double)posZ);
            if (!(distanceSq > minSq) || !(distanceSq <= radiusSq)) continue;
            if (entityPlayer.func_70644_a(Potion.field_76431_k)) {
                entityPlayer.func_82170_o(Potion.field_76431_k.field_76415_H);
            }
            if (entityPlayer.func_70644_a(Potion.field_76440_q)) {
                entityPlayer.func_82170_o(Potion.field_76440_q.field_76415_H);
            }
            if (entityPlayer.func_70644_a(Potion.field_76436_u)) {
                entityPlayer.func_82170_o(Potion.field_76436_u.field_76415_H);
            }
            if (!enhanced) continue;
            entityPlayer.func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 300, 1));
            entityPlayer.func_70690_d(new PotionEffect(Potion.field_76443_y.field_76415_H, 2400));
        }
        ArrayList<EntityZombie> villagersToZombify = new ArrayList<EntityZombie>();
        for (Object e : world.field_72996_f) {
            double distanceSq;
            EntityZombie victim;
            if (!(e instanceof EntityZombie) || !(victim = (EntityZombie)e).func_82231_m() || !((distanceSq = victim.func_70092_e(0.5 + (double)posX, 0.5 + (double)posY, 0.5 + (double)posZ)) > minSq) || !(distanceSq <= radiusSq)) continue;
            Log.instance().debug(String.format("Try curing zombie %f %f %f", distanceSq, minSq, radiusSq));
            villagersToZombify.add(victim);
        }
        for (EntityZombie entityZombie : villagersToZombify) {
            EntityVillager entityvillager = new EntityVillager(world);
            entityvillager.func_82149_j((Entity)entityZombie);
            entityvillager.func_110161_a((IEntityLivingData)null);
            entityvillager.func_82187_q();
            if (entityZombie.func_70631_g_()) {
                entityvillager.func_70873_a(-24000);
            }
            world.func_72900_e((Entity)entityZombie);
            world.func_72838_d((Entity)entityvillager);
            entityvillager.func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, 200, 0));
            world.func_72889_a((EntityPlayer)null, 1017, (int)entityvillager.field_70165_t, (int)entityvillager.field_70163_u, (int)entityvillager.field_70161_v, 0);
        }
        return true;
    }
}

