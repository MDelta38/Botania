/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDispenser
 *  net.minecraft.dispenser.BehaviorDefaultDispenseItem
 *  net.minecraft.dispenser.IBlockSource
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.dispenser;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BehaviourSeeds
extends BehaviorDefaultDispenseItem {
    Block block;

    public BehaviourSeeds(Block block) {
        this.block = block;
    }

    public ItemStack func_82487_b(IBlockSource par1IBlockSource, ItemStack par2ItemStack) {
        EnumFacing facing = BlockDispenser.func_149937_b((int)par1IBlockSource.func_82620_h());
        int x = par1IBlockSource.func_82623_d() + facing.func_82601_c();
        int y = par1IBlockSource.func_82622_e() + facing.func_96559_d();
        int z = par1IBlockSource.func_82621_f() + facing.func_82599_e();
        World world = par1IBlockSource.func_82618_k();
        if (world.func_147439_a(x, y, z).isAir((IBlockAccess)world, x, y, z) && this.block.func_149718_j(world, x, y, z)) {
            world.func_147449_b(x, y, z, this.block);
            --par2ItemStack.field_77994_a;
            return par2ItemStack;
        }
        return super.func_82487_b(par1IBlockSource, par2ItemStack);
    }
}

