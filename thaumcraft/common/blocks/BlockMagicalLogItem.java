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
import thaumcraft.common.blocks.BlockMagicalLog;

public class BlockMagicalLogItem
extends ItemBlock {
    public BlockMagicalLogItem(Block par1) {
        super(par1);
        this.func_77656_e(0);
        this.func_77627_a(true);
    }

    public int func_77647_b(int par1) {
        return par1;
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        int var2 = par1ItemStack.func_77960_j();
        if (var2 < 0 || var2 >= BlockMagicalLog.woodType.length) {
            var2 = 0;
        }
        return super.func_77658_a() + "." + BlockMagicalLog.woodType[var2];
    }
}

