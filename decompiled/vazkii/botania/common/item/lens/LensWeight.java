/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityFallingBlock
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 */
package vazkii.botania.common.item.lens;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.lens.Lens;

public class LensWeight
extends Lens {
    @Override
    public boolean collideBurst(IManaBurst burst, EntityThrowable entity, MovingObjectPosition pos, boolean isManaBlock, boolean dead, ItemStack stack) {
        if (!burst.isFake()) {
            int x = pos.field_72311_b;
            int y = pos.field_72312_c;
            int z = pos.field_72309_d;
            int harvestLevel = ConfigHandler.harvestLevelWeight;
            Block block = entity.field_70170_p.func_147439_a(x, y, z);
            Block blockBelow = entity.field_70170_p.func_147439_a(x, y - 1, z);
            int meta = entity.field_70170_p.func_72805_g(x, y, z);
            int neededHarvestLevel = block.getHarvestLevel(meta);
            if (blockBelow.isAir((IBlockAccess)entity.field_70170_p, x, y - 1, z) && block.func_149712_f(entity.field_70170_p, x, y, z) != -1.0f && neededHarvestLevel <= harvestLevel && entity.field_70170_p.func_147438_o(x, y, z) == null && block.canSilkHarvest(entity.field_70170_p, null, x, y, z, meta)) {
                EntityFallingBlock falling = new EntityFallingBlock(entity.field_70170_p, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, block, meta);
                if (!entity.field_70170_p.field_72995_K) {
                    entity.field_70170_p.func_72838_d((Entity)falling);
                }
            }
        }
        return dead;
    }
}

