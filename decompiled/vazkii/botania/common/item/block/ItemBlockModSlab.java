/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemSlab
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.item.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import vazkii.botania.common.block.decor.slabs.BlockModSlab;

public class ItemBlockModSlab
extends ItemSlab {
    public ItemBlockModSlab(Block par1) {
        super(par1, ((BlockModSlab)par1).getSingleBlock(), ((BlockModSlab)par1).getFullBlock(), false);
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return this.field_150939_a.func_149739_a().replaceAll("tile.", "tile.botania:");
    }
}

