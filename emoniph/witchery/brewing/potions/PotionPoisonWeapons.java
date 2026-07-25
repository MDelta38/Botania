/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.brewing.potions.IHandleLivingHurt;
import com.emoniph.witchery.brewing.potions.PotionBase;
import com.emoniph.witchery.util.TimeUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PotionPoisonWeapons
extends PotionBase
implements IHandleLivingHurt {
    public PotionPoisonWeapons(int id, int color) {
        super(id, color);
    }

    @Override
    public void onLivingHurt(World world, EntityLivingBase entity, LivingHurtEvent event, int amplifier) {
        EntityLivingBase attacker;
        PotionEffect poisonedAttack;
        if (!world.field_72995_K && event.source.func_76346_g() != null && event.source.func_76346_g() instanceof EntityLivingBase && this.isValidDamageType(event.source.func_76355_l()) && (poisonedAttack = (attacker = (EntityLivingBase)event.source.func_76346_g()).func_70660_b((Potion)this)) != null) {
            switch (poisonedAttack.func_76458_c()) {
                case 0: {
                    entity.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, TimeUtil.secsToTicks(5), 0));
                    break;
                }
                case 1: {
                    entity.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, TimeUtil.secsToTicks(5), 1));
                    break;
                }
                case 2: {
                    entity.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, TimeUtil.secsToTicks(15), 1));
                    break;
                }
                default: {
                    entity.func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, TimeUtil.secsToTicks(20), 0));
                }
            }
        }
    }

    private boolean isValidDamageType(String damageType) {
        return damageType.equals("mob") || damageType.equals("player");
    }

    @Override
    public boolean handleAllHurtEvents() {
        return true;
    }
}

