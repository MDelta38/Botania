/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 */
package vazkii.botania.common.item.lens;

import net.minecraft.block.Block;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.common.block.BlockPistonRelay;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.lens.Lens;

public class LensWarp
extends Lens {
    @Override
    public boolean collideBurst(IManaBurst burst, EntityThrowable entity, MovingObjectPosition pos, boolean isManaBlock, boolean dead, ItemStack stack) {
        String key;
        if (burst.isFake()) {
            return dead;
        }
        Block block = entity.field_70170_p.func_147439_a(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
        if (block == ModBlocks.pistonRelay && (key = BlockPistonRelay.mappedPositions.get(BlockPistonRelay.getCoordsAsString(entity.field_70170_p.field_73011_w.field_76574_g, pos.field_72311_b, pos.field_72312_c, pos.field_72309_d))) != null) {
            String[] tokens = key.split(":");
            int worldId = Integer.parseInt(tokens[0]);
            int x = Integer.parseInt(tokens[1]);
            int y = Integer.parseInt(tokens[2]);
            int z = Integer.parseInt(tokens[3]);
            if (worldId == entity.field_70170_p.field_73011_w.field_76574_g) {
                entity.func_70107_b((double)x + 0.5, (double)y + 0.5, (double)z + 0.5);
                burst.setCollidedAt(x, y, z);
                return false;
            }
        }
        return dead;
    }
}

