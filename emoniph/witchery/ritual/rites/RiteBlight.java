/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityCow
 *  net.minecraft.entity.passive.EntityMooshroom
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.ritual.rites.RiteExpandingEffect;
import com.emoniph.witchery.util.Log;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class RiteBlight
extends RiteExpandingEffect {
    public RiteBlight(int radius, int height) {
        super(radius, height, true);
    }

    @Override
    public boolean doRadiusAction(World world, int posX, int posY, int posZ, int radius, EntityPlayer player, boolean enhanced) {
        EntityZombie entityzombie;
        double radiusSq = radius * radius;
        double minSq = Math.max(0, (radius - 1) * (radius - 1));
        for (Object obj : world.field_73010_i) {
            EntityPlayer victim = (EntityPlayer)obj;
            double distanceSq = victim.func_70092_e(0.5 + (double)posX, 0.5 + (double)posY, 0.5 + (double)posZ);
            if (!(distanceSq > minSq) || !(distanceSq <= radiusSq)) continue;
            if (Witchery.Items.POPPET.voodooProtectionActivated(player, null, (EntityLivingBase)victim, 6)) {
                return false;
            }
            if (victim.func_70644_a(Potion.field_76431_k)) continue;
            victim.func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, 2400, 1));
        }
        ArrayList<EntityVillager> villagersToZombify = new ArrayList<EntityVillager>();
        ArrayList<EntityVillager> cowsToSchroom = new ArrayList<EntityVillager>();
        ArrayList<EntityVillager> animalsToSlay = new ArrayList<EntityVillager>();
        for (Object e : world.field_72996_f) {
            double distanceSq;
            EntityVillager victim;
            if (e instanceof EntityVillager) {
                victim = (EntityVillager)e;
                distanceSq = victim.func_70092_e(0.5 + (double)posX, 0.5 + (double)posY, 0.5 + (double)posZ);
                if (!(distanceSq > minSq) || !(distanceSq <= radiusSq)) continue;
                Log.instance().debug(String.format("Try Adding zombie %f %f %f", distanceSq, minSq, radiusSq));
                if (world.field_73012_v.nextInt(10) != 0) continue;
                Log.instance().debug("Added zombie");
                villagersToZombify.add(victim);
                continue;
            }
            if (e instanceof EntityCow) {
                victim = (EntityCow)e;
                distanceSq = victim.func_70092_e(0.5 + (double)posX, 0.5 + (double)posY, 0.5 + (double)posZ);
                if (!(distanceSq > minSq) || !(distanceSq <= radiusSq)) continue;
                Log.instance().debug(String.format("Try Adding mooschroom %f %f %f", distanceSq, minSq, radiusSq));
                if (world.field_73012_v.nextInt(20) == 0) {
                    Log.instance().debug("Added mooschroom");
                    cowsToSchroom.add(victim);
                    continue;
                }
                if (world.field_73012_v.nextInt(3) != 0) continue;
                animalsToSlay.add(victim);
                continue;
            }
            if (!(e instanceof EntityAnimal) || !((distanceSq = (victim = (EntityAnimal)e).func_70092_e(0.5 + (double)posX, 0.5 + (double)posY, 0.5 + (double)posZ)) > minSq) || !(distanceSq <= radiusSq) || world.field_73012_v.nextInt(3) != 0) continue;
            animalsToSlay.add(victim);
        }
        for (EntityVillager entityVillager : villagersToZombify) {
            entityzombie = new EntityZombie(world);
            entityzombie.func_82149_j((Entity)entityVillager);
            world.func_72900_e((Entity)entityVillager);
            entityzombie.func_110161_a((IEntityLivingData)null);
            entityzombie.func_82229_g(true);
            if (entityVillager.func_70631_g_()) {
                entityzombie.func_82227_f(true);
            }
            world.func_72838_d((Entity)entityzombie);
            world.func_72889_a((EntityPlayer)null, 1016, (int)entityzombie.field_70165_t, (int)entityzombie.field_70163_u, (int)entityzombie.field_70161_v, 0);
        }
        for (EntityCow entityCow : cowsToSchroom) {
            entityzombie = new EntityMooshroom(world);
            entityzombie.func_82149_j((Entity)entityCow);
            world.func_72900_e((Entity)entityCow);
            entityzombie.func_110161_a((IEntityLivingData)null);
            world.func_72838_d((Entity)entityzombie);
            world.func_72889_a((EntityPlayer)null, 1016, (int)entityzombie.field_70165_t, (int)entityzombie.field_70163_u, (int)entityzombie.field_70161_v, 0);
        }
        for (EntityAnimal entityAnimal : animalsToSlay) {
            entityAnimal.func_70097_a(DamageSource.field_76376_m, 20.0f);
        }
        return true;
    }

    @Override
    public void doBlockAction(World world, int posX, int posY, int posZ, int currentRadius, EntityPlayer player, boolean enhanced) {
        if (!world.field_72995_K) {
            Block blockID = world.func_147439_a(posX, posY, posZ);
            Block blockBelowID = world.func_147439_a(posX, posY - 1, posZ);
            if (blockID == Blocks.field_150329_H) {
                world.func_147468_f(posX, posY, posZ);
                this.blightGround(world, posX, posY - 1, posZ, blockBelowID, enhanced);
            } else if (blockID == Blocks.field_150328_O || blockID == Blocks.field_150327_N || blockID == Blocks.field_150459_bM || blockID == Blocks.field_150464_aj || blockID == Blocks.field_150469_bN || blockID == Blocks.field_150393_bb || blockID == Blocks.field_150394_bc || blockID == Blocks.field_150440_ba || blockID == Blocks.field_150423_aK) {
                world.func_147449_b(posX, posY, posZ, (Block)Blocks.field_150330_I);
                this.blightGround(world, posX, posY - 1, posZ, blockBelowID, enhanced);
            } else if (blockID == Blocks.field_150458_ak) {
                world.func_147449_b(posX, posY, posZ, (Block)Blocks.field_150354_m);
            } else if (blockID.func_149688_o().func_76220_a()) {
                this.blightGround(world, posX, posY, posZ, blockID, enhanced);
            } else if (blockBelowID.func_149688_o().func_76220_a()) {
                this.blightGround(world, posX, posY - 1, posZ, blockBelowID, enhanced);
            }
        }
    }

    public void blightGround(World world, int posX, int posY, int posZ, Block blockBelowID, boolean enhanced) {
        if (blockBelowID == Blocks.field_150346_d || blockBelowID == Blocks.field_150349_c || blockBelowID == Blocks.field_150391_bh || blockBelowID == Blocks.field_150458_ak) {
            int rand = world.field_73012_v.nextInt(enhanced ? 4 : 5);
            if (rand == 0) {
                world.func_147449_b(posX, posY, posZ, (Block)Blocks.field_150354_m);
            } else if (rand == 1) {
                world.func_147449_b(posX, posY, posZ, Blocks.field_150346_d);
            }
        }
    }
}

