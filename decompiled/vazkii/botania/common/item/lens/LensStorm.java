/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MovingObjectPosition
 */
package vazkii.botania.common.item.lens;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MovingObjectPosition;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.common.item.lens.Lens;

public class LensStorm
extends Lens {
    @Override
    public boolean collideBurst(IManaBurst burst, EntityThrowable entity, MovingObjectPosition pos, boolean isManaBlock, boolean dead, ItemStack stack) {
        if (!burst.isFake()) {
            ChunkCoordinates coords = burst.getBurstSourceChunkCoordinates();
            if (!(entity.field_70170_p.field_72995_K || pos.field_72308_g != null || isManaBlock || pos.field_72311_b == coords.field_71574_a && pos.field_72312_c == coords.field_71572_b && pos.field_72309_d == coords.field_71573_c)) {
                entity.field_70170_p.func_72876_a((Entity)entity, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, 5.0f, true);
            }
        } else {
            dead = false;
        }
        return dead;
    }
}

