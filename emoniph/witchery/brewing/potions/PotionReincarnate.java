/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityBlaze
 *  net.minecraft.entity.monster.EntityCaveSpider
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.monster.EntitySkeleton
 *  net.minecraft.entity.monster.EntitySpider
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.brewing.potions.IHandleLivingDeath;
import com.emoniph.witchery.brewing.potions.PotionBase;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class PotionReincarnate
extends PotionBase
implements IHandleLivingDeath {
    public PotionReincarnate(int id, int color) {
        super(id, color);
    }

    @Override
    public void onLivingDeath(World world, EntityLivingBase entity, LivingDeathEvent event, int amplifier) {
        if (!world.field_72995_K) {
            Class creatureToSpawn = null;
            creatureToSpawn = entity instanceof EntityAnimal || entity instanceof EntitySpider ? (amplifier > 2 ? EntityCreeper.class : (amplifier > 1 ? EntityCaveSpider.class : EntitySpider.class)) : (amplifier > 2 ? EntityBlaze.class : (amplifier > 1 ? EntitySkeleton.class : EntityZombie.class));
            Entity attacker = event.source.func_76346_g();
            Infusion.spawnCreature(world, creatureToSpawn, (int)entity.field_70165_t, (int)entity.field_70163_u, (int)entity.field_70161_v, attacker != null && attacker instanceof EntityLivingBase ? (EntityLivingBase)attacker : null, 0, 0, ParticleEffect.INSTANT_SPELL, SoundEffect.MOB_ZOMBIE_SAY);
        }
    }
}

