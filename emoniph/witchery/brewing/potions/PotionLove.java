/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAITasks$EntityAITaskEntry
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.brewing.potions.EntityAIVillagerMateNow;
import com.emoniph.witchery.brewing.potions.EntityAIZombieMateNow;
import com.emoniph.witchery.brewing.potions.IHandleLivingUpdate;
import com.emoniph.witchery.brewing.potions.PotionBase;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;

public class PotionLove
extends PotionBase
implements IHandleLivingUpdate {
    public PotionLove(int id, int color) {
        super(id, color);
    }

    @Override
    public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration) {
        if (!world.field_72995_K && world.func_82737_E() % 20L == 7L) {
            if (entity instanceof EntityAnimal) {
                EntityAnimal animal = (EntityAnimal)entity;
                if (animal.func_70874_b() >= 0 && !animal.func_70880_s()) {
                    animal.func_70873_a(0);
                    animal.func_146082_f(null);
                }
            } else if (entity instanceof EntityZombie) {
                EntityZombie zombie = (EntityZombie)entity;
                if (!zombie.func_70631_g_()) {
                    for (Object obj : zombie.field_70714_bg.field_75782_a) {
                        EntityAITasks.EntityAITaskEntry task = (EntityAITasks.EntityAITaskEntry)obj;
                        if (!(task.field_75733_a instanceof EntityAIZombieMateNow)) continue;
                        ((EntityAIZombieMateNow)task.field_75733_a).beginMating();
                        return;
                    }
                    EntityAIZombieMateNow ai = new EntityAIZombieMateNow(zombie);
                    ai.beginMating();
                    zombie.field_70714_bg.func_75776_a(1, (EntityAIBase)ai);
                }
            } else if (entity instanceof EntityVillager) {
                EntityVillager villager = (EntityVillager)entity;
                if (!villager.func_70631_g_() && !villager.func_70941_o()) {
                    for (Object obj : villager.field_70714_bg.field_75782_a) {
                        EntityAITasks.EntityAITaskEntry task = (EntityAITasks.EntityAITaskEntry)obj;
                        if (!(task.field_75733_a instanceof EntityAIVillagerMateNow)) continue;
                        ((EntityAIVillagerMateNow)task.field_75733_a).beginMating();
                        return;
                    }
                    EntityAIVillagerMateNow ai = new EntityAIVillagerMateNow(villager);
                    ai.beginMating();
                    villager.field_70714_bg.func_75776_a(1, (EntityAIBase)ai);
                }
            } else if (entity instanceof EntityPlayer && world.field_73012_v.nextInt(2) == 0) {
                ParticleEffect.HEART.send(SoundEffect.NONE, world, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + 1.0, event.entityLiving.field_70161_v, 0.6, 2.0, 16);
            }
        }
    }
}

