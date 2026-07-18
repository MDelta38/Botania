/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDispenser
 *  net.minecraft.dispenser.BehaviorDefaultDispenseItem
 *  net.minecraft.dispenser.IBlockSource
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.block.dispenser;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.wand.IWandable;

public class BehaviourWand
extends BehaviorDefaultDispenseItem {
    protected ItemStack func_82487_b(IBlockSource par1IBlockSource, ItemStack par2ItemStack) {
        ForgeDirection facing = ForgeDirection.getOrientation((int)BlockDispenser.func_149937_b((int)par1IBlockSource.func_82620_h()).ordinal());
        int x = par1IBlockSource.func_82623_d() + facing.offsetX;
        int y = par1IBlockSource.func_82622_e() + facing.offsetY;
        int z = par1IBlockSource.func_82621_f() + facing.offsetZ;
        World world = par1IBlockSource.func_82618_k();
        Block block = world.func_147439_a(x, y, z);
        if (block instanceof IWandable) {
            ((IWandable)block).onUsedByWand(null, par2ItemStack, world, x, y, z, facing.getOpposite().ordinal());
            return par2ItemStack;
        }
        return super.func_82487_b(par1IBlockSource, par2ItemStack);
    }
}

