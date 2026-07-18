/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.common.item.lens.Lens;

public class LensPiston
extends Lens {
    @Override
    public boolean collideBurst(IManaBurst burst, EntityThrowable entity, MovingObjectPosition pos, boolean isManaBlock, boolean dead, ItemStack stack) {
        ChunkCoordinates coords = burst.getBurstSourceChunkCoordinates();
        if (!(coords.field_71574_a == pos.field_72311_b && coords.field_71572_b == pos.field_72312_c && coords.field_71573_c == pos.field_72309_d || burst.isFake() || isManaBlock || entity.field_70170_p.field_72995_K)) {
            ForgeDirection dir = ForgeDirection.getOrientation((int)pos.field_72310_e).getOpposite();
            int x = pos.field_72311_b + dir.offsetX;
            int y = pos.field_72312_c + dir.offsetY;
            int z = pos.field_72309_d + dir.offsetZ;
            if (entity.field_70170_p.func_147437_c(x, y, z) || entity.field_70170_p.func_147439_a(x, y, z).isReplaceable((IBlockAccess)entity.field_70170_p, x, y, z)) {
                Block block = entity.field_70170_p.func_147439_a(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
                int meta = entity.field_70170_p.func_72805_g(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
                TileEntity tile = entity.field_70170_p.func_147438_o(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
                if (block.func_149656_h() == 0 && block != Blocks.field_150343_Z && block.func_149712_f(entity.field_70170_p, pos.field_72311_b, pos.field_72312_c, pos.field_72309_d) >= 0.0f && tile == null) {
                    entity.field_70170_p.func_147468_f(pos.field_72311_b, pos.field_72312_c, pos.field_72309_d);
                    entity.field_70170_p.func_147465_d(x, y, z, block, meta, 3);
                    entity.field_70170_p.func_72926_e(2001, pos.field_72311_b, pos.field_72312_c, pos.field_72309_d, Block.func_149682_b((Block)block) + (meta << 12));
                }
            }
        }
        return dead;
    }
}

