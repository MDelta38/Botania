/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.item.lens;

import net.minecraft.block.Block;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileIncensePlate;
import vazkii.botania.common.item.lens.Lens;

public class LensFire
extends Lens {
    @Override
    public boolean collideBurst(IManaBurst burst, EntityThrowable entity, MovingObjectPosition pos, boolean isManaBlock, boolean dead, ItemStack stack) {
        ChunkCoordinates coords = burst.getBurstSourceChunkCoordinates();
        if (!(coords.field_71574_a == pos.field_72311_b && coords.field_71572_b == pos.field_72312_c && coords.field_71573_c == pos.field_72309_d || burst.isFake() || isManaBlock)) {
            ForgeDirection dir = ForgeDirection.getOrientation((int)pos.field_72310_e);
            int x = pos.field_72311_b + dir.offsetX;
            int y = pos.field_72312_c + dir.offsetY;
            int z = pos.field_72309_d + dir.offsetZ;
            Block blockAt = entity.field_70170_p.func_147439_a(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
            Block blockAt_ = entity.field_70170_p.func_147439_a(x, y, z);
            if (blockAt == Blocks.field_150427_aO) {
                entity.field_70170_p.func_147449_b(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d, Blocks.field_150350_a);
            } else if (blockAt == ModBlocks.incensePlate) {
                TileIncensePlate plate = (TileIncensePlate)entity.field_70170_p.func_147438_o(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
                plate.ignite();
            } else if (blockAt_.isAir((IBlockAccess)entity.field_70170_p, x, y, z)) {
                entity.field_70170_p.func_147449_b(x, y, z, (Block)Blocks.field_150480_ab);
            }
        }
        return dead;
    }
}

