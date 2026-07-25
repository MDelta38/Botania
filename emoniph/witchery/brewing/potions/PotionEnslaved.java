/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAITasks$EntityAITaskEntry
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.entity.monster.EntityGolem
 *  net.minecraft.entity.monster.EntityWitch
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.potions.EntityAIEnslaverHurtByTarget;
import com.emoniph.witchery.brewing.potions.IHandleLivingSetAttackTarget;
import com.emoniph.witchery.brewing.potions.IHandleLivingUpdate;
import com.emoniph.witchery.brewing.potions.PotionBase;
import com.emoniph.witchery.entity.EntityDemon;
import com.emoniph.witchery.entity.EntityEnt;
import com.emoniph.witchery.entity.EntityImp;
import com.emoniph.witchery.util.EntityUtil;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;

public class PotionEnslaved
extends PotionBase
implements IHandleLivingSetAttackTarget,
IHandleLivingUpdate {
    private static final String ENSLAVER_KEY = "WITCEnslaverName";

    public PotionEnslaved(int id, int color) {
        super(id, true, color);
    }

    @Override
    public void onLivingSetAttackTarget(World world, EntityLiving entity, LivingSetAttackTargetEvent event, int amplifier) {
        String enslaverName;
        if (event.target != null && event.target instanceof EntityPlayer && entity instanceof EntityLiving && (enslaverName = PotionEnslaved.getMobEnslaverName(entity)).equals(event.target.func_70005_c_())) {
            entity.func_70624_b(null);
        }
    }

    public static boolean setEnslaverForMob(EntityLiving entity, EntityPlayer player) {
        boolean isEnslaved;
        if (entity == null || player == null) {
            return false;
        }
        String enslaverName = entity.getEntityData().func_74779_i(ENSLAVER_KEY);
        boolean bl = isEnslaved = enslaverName != null && !enslaverName.isEmpty();
        if (!isEnslaved || !player.func_70005_c_().equals(enslaverName)) {
            entity.getEntityData().func_74778_a(ENSLAVER_KEY, player.func_70005_c_());
            entity.func_70690_d(new PotionEffect(Witchery.Potions.ENSLAVED.field_76415_H, Integer.MAX_VALUE));
            EntityUtil.dropAttackTarget(entity);
            return true;
        }
        return false;
    }

    public static boolean isMobEnslavedBy(EntityLiving entity, EntityPlayer player) {
        return player != null && entity != null && entity.getEntityData() != null && player.func_70005_c_().equals(entity.getEntityData().func_74779_i(ENSLAVER_KEY));
    }

    public static boolean canCreatureBeEnslaved(EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityLiving) {
            return !(entityLiving instanceof IBossDisplayData) && !(entityLiving instanceof EntityGolem) && !(entityLiving instanceof EntityDemon) && !(entityLiving instanceof EntityWitch) && !(entityLiving instanceof EntityImp) && !(entityLiving instanceof EntityEnt);
        }
        return false;
    }

    public static boolean isMobEnslaved(EntityLiving entity) {
        if (entity == null) {
            return false;
        }
        String enslaverName = entity.getEntityData().func_74779_i(ENSLAVER_KEY);
        return enslaverName != null && !enslaverName.isEmpty();
    }

    public static String getMobEnslaverName(EntityLiving entity) {
        if (entity == null) {
            return "";
        }
        String enslaverName = entity.getEntityData().func_74779_i(ENSLAVER_KEY);
        return enslaverName;
    }

    @Override
    public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration) {
        if (!world.field_72995_K && world.func_82737_E() % 20L == 3L && entity instanceof EntityCreature) {
            EntityCreature creature = (EntityCreature)entity;
            for (Object obj : creature.field_70715_bh.field_75782_a) {
                EntityAITasks.EntityAITaskEntry task = (EntityAITasks.EntityAITaskEntry)obj;
                if (!(task.field_75733_a instanceof EntityAIEnslaverHurtByTarget)) continue;
                return;
            }
            creature.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIEnslaverHurtByTarget(creature));
        }
    }
}

