/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityFallingBlock
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 */
package vazkii.botania.common.item.lens;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.lens.Lens;

public class LensInfluence
extends Lens {
    @Override
    public void updateBurst(IManaBurst burst, EntityThrowable entity, ItemStack stack) {
        if (!burst.isFake()) {
            double range = 3.5;
            List movables = entity.field_70170_p.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)(entity.field_70165_t - range), (double)(entity.field_70163_u - range), (double)(entity.field_70161_v - range), (double)(entity.field_70165_t + range), (double)(entity.field_70163_u + range), (double)(entity.field_70161_v + range)));
            movables.addAll(entity.field_70170_p.func_72872_a(EntityXPOrb.class, AxisAlignedBB.func_72330_a((double)(entity.field_70165_t - range), (double)(entity.field_70163_u - range), (double)(entity.field_70161_v - range), (double)(entity.field_70165_t + range), (double)(entity.field_70163_u + range), (double)(entity.field_70161_v + range))));
            movables.addAll(entity.field_70170_p.func_72872_a(EntityArrow.class, AxisAlignedBB.func_72330_a((double)(entity.field_70165_t - range), (double)(entity.field_70163_u - range), (double)(entity.field_70161_v - range), (double)(entity.field_70165_t + range), (double)(entity.field_70163_u + range), (double)(entity.field_70161_v + range))));
            movables.addAll(entity.field_70170_p.func_72872_a(EntityFallingBlock.class, AxisAlignedBB.func_72330_a((double)(entity.field_70165_t - range), (double)(entity.field_70163_u - range), (double)(entity.field_70161_v - range), (double)(entity.field_70165_t + range), (double)(entity.field_70163_u + range), (double)(entity.field_70161_v + range))));
            movables.addAll(entity.field_70170_p.func_72872_a(IManaBurst.class, AxisAlignedBB.func_72330_a((double)(entity.field_70165_t - range), (double)(entity.field_70163_u - range), (double)(entity.field_70161_v - range), (double)(entity.field_70165_t + range), (double)(entity.field_70163_u + range), (double)(entity.field_70161_v + range))));
            for (Entity movable : movables) {
                if (movable == burst) continue;
                if (movable instanceof IManaBurst) {
                    IManaBurst otherBurst = (IManaBurst)movable;
                    ItemStack lens = otherBurst.getSourceLens();
                    if (lens != null && lens.func_77973_b() == ModItems.lens && lens.func_77960_j() == 12) continue;
                    ((IManaBurst)movable).setMotion(entity.field_70159_w, entity.field_70181_x, entity.field_70179_y);
                    continue;
                }
                movable.field_70159_w = entity.field_70159_w;
                movable.field_70181_x = entity.field_70181_x;
                movable.field_70179_y = entity.field_70179_y;
            }
        }
    }
}

