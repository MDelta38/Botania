/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntitySpider
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.infusion.infusions.creature;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.entity.EntityWitchProjectile;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePower;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class CreaturePowerSpider
extends CreaturePower {
    public CreaturePowerSpider(int powerID, Class<? extends EntitySpider> creatureType) {
        super(powerID, creatureType);
    }

    @Override
    public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop) {
        if (!world.field_72995_K) {
            world.func_72956_a((Entity)player, "random.bow", 0.5f, 0.4f / (world.field_73012_v.nextFloat() * 0.4f + 0.8f));
            world.func_72838_d((Entity)new EntityWitchProjectile(world, (EntityLivingBase)player, Witchery.Items.GENERIC.itemWeb));
        }
    }

    @Override
    public void onUpdate(World world, EntityPlayer player) {
        int blockZ;
        int blockY;
        int blockX = MathHelper.func_76128_c((double)player.field_70165_t);
        if (world.func_147439_a(blockX, blockY = MathHelper.func_76128_c((double)(player.field_70163_u + 1.0)), blockZ = MathHelper.func_76128_c((double)player.field_70161_v)).func_149688_o().func_76220_a()) {
            player.field_70181_x *= 0.6;
        }
        if (player.field_70123_F) {
            player.field_70181_x = 0.3;
        }
        if (player.func_70093_af() && player instanceof EntityPlayer && player.field_70123_F) {
            player.field_70181_x = 0.0;
        }
    }
}

