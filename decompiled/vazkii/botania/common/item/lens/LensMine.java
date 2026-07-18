/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.lens;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.IManaBlock;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.lens.ItemLens;
import vazkii.botania.common.item.lens.Lens;

public class LensMine
extends Lens {
    @Override
    public boolean collideBurst(IManaBurst burst, EntityThrowable entity, MovingObjectPosition pos, boolean isManaBlock, boolean dead, ItemStack stack) {
        boolean warp;
        World world = entity.field_70170_p;
        int x = pos.field_72311_b;
        int y = pos.field_72312_c;
        int z = pos.field_72309_d;
        Block block = world.func_147439_a(x, y, z);
        int meta = world.func_72805_g(x, y, z);
        ItemStack composite = ((ItemLens)ModItems.lens).getCompositeLens(stack);
        boolean bl = warp = composite != null && composite.func_77973_b() == ModItems.lens && composite.func_77960_j() == 18;
        if (warp && (block == ModBlocks.pistonRelay || block == Blocks.field_150331_J || block == Blocks.field_150326_M || block == Blocks.field_150332_K)) {
            return false;
        }
        int harvestLevel = ConfigHandler.harvestLevelBore;
        TileEntity tile = world.func_147438_o(x, y, z);
        float hardness = block.func_149712_f(world, x, y, z);
        int neededHarvestLevel = block.getHarvestLevel(meta);
        int mana = burst.getMana();
        ChunkCoordinates coords = burst.getBurstSourceChunkCoordinates();
        if (!(coords.field_71574_a == x && coords.field_71572_b == y && coords.field_71573_c == z || tile instanceof IManaBlock || neededHarvestLevel > harvestLevel || hardness == -1.0f || !(hardness < 50.0f) || !burst.isFake() && mana < 24)) {
            ArrayList items = new ArrayList();
            items.addAll(block.getDrops(world, x, y, z, meta, 0));
            if (!(burst.hasAlreadyCollidedAt(x, y, z) || burst.isFake() || entity.field_70170_p.field_72995_K)) {
                world.func_147468_f(x, y, z);
                if (ConfigHandler.blockBreakParticles) {
                    entity.field_70170_p.func_72926_e(2001, x, y, z, Block.func_149682_b((Block)block) + (meta << 12));
                }
                boolean offBounds = coords.field_71572_b < 0;
                boolean doWarp = warp && !offBounds;
                int dropX = doWarp ? coords.field_71574_a : x;
                int dropY = doWarp ? coords.field_71572_b : y;
                int dropZ = doWarp ? coords.field_71573_c : z;
                for (ItemStack stack_ : items) {
                    world.func_72838_d((Entity)new EntityItem(world, (double)dropX + 0.5, (double)dropY + 0.5, (double)dropZ + 0.5, stack_));
                }
                burst.setMana(mana - 24);
            }
            dead = false;
        }
        return dead;
    }
}

