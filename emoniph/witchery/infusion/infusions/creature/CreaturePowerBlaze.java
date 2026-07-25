/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityBlaze
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntitySmallFireball
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 */
package com.emoniph.witchery.infusion.infusions.creature;

import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePower;
import com.emoniph.witchery.util.SoundEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class CreaturePowerBlaze
extends CreaturePower {
    public CreaturePowerBlaze(int powerID) {
        super(powerID, EntityBlaze.class);
    }

    @Override
    public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop) {
        if (!world.field_72995_K) {
            world.func_72889_a((EntityPlayer)null, 1009, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, 0);
            int BALLS = 3;
            for (int i = 0; i < 3; ++i) {
                float f = 1.0f;
                double motionX = -MathHelper.func_76126_a((float)(player.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(player.field_70125_A / 180.0f * (float)Math.PI)) * f;
                double motionZ = MathHelper.func_76134_b((float)(player.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(player.field_70125_A / 180.0f * (float)Math.PI)) * f;
                double motionY = -MathHelper.func_76126_a((float)(player.field_70125_A / 180.0f * (float)Math.PI)) * f;
                EntitySmallFireball fireball = new EntitySmallFireball(world, (EntityLivingBase)player, motionX, motionY, motionZ);
                fireball.func_70012_b(player.field_70165_t, player.field_70163_u + (double)player.func_70047_e(), player.field_70161_v, fireball.field_70177_z, fireball.field_70125_A);
                fireball.func_70107_b(player.field_70165_t, player.field_70163_u + (double)player.func_70047_e(), player.field_70161_v);
                double d6 = MathHelper.func_76133_a((double)((motionX += world.field_73012_v.nextDouble() * 0.2) * motionX + motionY * motionY + (motionZ += world.field_73012_v.nextDouble() * 0.2) * motionZ));
                fireball.field_70232_b = motionX / d6 * 0.1;
                fireball.field_70233_c = motionY / d6 * 0.1;
                fireball.field_70230_d = motionZ / d6 * 0.1;
                double d8 = 1.0;
                Vec3 vec3 = player.func_70676_i(1.0f);
                fireball.field_70165_t = player.field_70165_t + vec3.field_72450_a * d8;
                fireball.field_70163_u = player.field_70163_u + (double)(player.field_70131_O / 2.0f) + 0.5;
                fireball.field_70161_v = player.field_70161_v + vec3.field_72449_c * d8;
                world.func_72838_d((Entity)fireball);
            }
        }
    }

    @Override
    public void onDamage(World world, EntityPlayer player, LivingHurtEvent event) {
        int currentEnergy;
        if (event.source.func_76347_k() && event.isCancelable() && (currentEnergy = Infusion.getCurrentEnergy(player)) >= 3 && !player.func_82165_m(Potion.field_76426_n.field_76415_H)) {
            Infusion.setCurrentEnergy(player, currentEnergy - 3);
            player.func_70690_d(new PotionEffect(Potion.field_76426_n.field_76415_H, 200, 0));
            SoundEffect.RANDOM_FIZZ.playAtPlayer(world, player);
        }
    }
}

