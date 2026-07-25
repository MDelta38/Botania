/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 */
package thaumcraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import thaumcraft.common.blocks.BlockMagicalLeaves;

public class BlockMagicalLeavesItem
extends ItemBlock {
    public BlockMagicalLeavesItem(Block par1) {
        super(par1);
        this.func_77656_e(0);
        this.func_77627_a(true);
    }

    public int func_77647_b(int par1) {
        return par1 | 4;
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        int var2 = par1ItemStack.func_77960_j();
        return super.func_77658_a() + "." + BlockMagicalLeaves.leafType[var2 & 1];
    }
}

