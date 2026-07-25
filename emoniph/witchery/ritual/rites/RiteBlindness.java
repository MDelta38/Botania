/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.ritual.rites.RiteExpandingEffect;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class RiteBlindness
extends RiteExpandingEffect {
    public RiteBlindness(int radius, int height) {
        super(radius, height, true);
    }

    @Override
    public boolean doRadiusAction(World world, int posX, int posY, int posZ, int radius, EntityPlayer player, boolean enhanced) {
        double distanceSq;
        EntityPlayer victim;
        double radiusSq = radius * radius;
        double minSq = Math.max(0, (radius - 1) * (radius - 1));
        for (Object obj : world.field_73010_i) {
            victim = (EntityPlayer)obj;
            distanceSq = victim.func_70092_e(0.5 + (double)posX, 0.5 + (double)posY, 0.5 + (double)posZ);
            if (!(distanceSq > minSq) || !(distanceSq <= radiusSq)) continue;
            if (Witchery.Items.POPPET.voodooProtectionActivated(player, null, (EntityLivingBase)victim, 6)) {
                return false;
            }
            if (victim.func_70644_a(Potion.field_76440_q)) continue;
            victim.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, (enhanced ? 5 : 2) * 1200, 0));
        }
        for (Object obj : world.field_72996_f) {
            if (!(obj instanceof EntityLiving) || !((distanceSq = (victim = (EntityLiving)obj).func_70092_e(0.5 + (double)posX, 0.5 + (double)posY, 0.5 + (double)posZ)) > minSq) || !(distanceSq <= radiusSq) || victim.func_70644_a(Potion.field_76440_q)) continue;
            victim.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, (enhanced ? 5 : 2) * 1200, 0));
        }
        return true;
    }

    @Override
    public void doBlockAction(World world, int posX, int posY, int posZ, int currentRadius, EntityPlayer player, boolean enhanced) {
    }
}

