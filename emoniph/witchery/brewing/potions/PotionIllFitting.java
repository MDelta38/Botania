/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.brewing.potions.PotionBase;
import com.emoniph.witchery.entity.EntityFollower;
import com.emoniph.witchery.entity.EntityReflection;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class PotionIllFitting
extends PotionBase {
    public PotionIllFitting(int id, int color) {
        super(id, true, color);
    }

    @Override
    public void postContructInitialize() {
        this.setPermenant();
        this.setIncurable();
    }

    public boolean func_76397_a(int duration, int amplifier) {
        if (duration % 15 == 0) {
            switch (amplifier) {
                case 3: {
                    return duration <= 60;
                }
                case 2: {
                    return duration <= 45;
                }
                case 1: {
                    return duration <= 30;
                }
            }
            return duration <= 15;
        }
        return false;
    }

    public void func_76394_a(EntityLivingBase entity, int amplifier) {
        int slot;
        ItemStack armorPiece;
        World world = entity.field_70170_p;
        if (!world.field_72995_K && !PotionIllFitting.isTargetBanned(entity) && (armorPiece = entity.func_71124_b(slot = world.field_73012_v.nextInt(4) + 1)) != null) {
            entity.func_70062_b(slot, null);
            EntityItem droppedItem = entity.func_70099_a(armorPiece, 0.0f);
            droppedItem.field_145804_b = 5 + 5 * amplifier;
        }
    }

    public static boolean isTargetBanned(EntityLivingBase entity) {
        return entity instanceof EntityReflection || entity instanceof EntityFollower;
    }
}

