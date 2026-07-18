/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemMultiTexture
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.item.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import vazkii.botania.common.block.decor.quartz.BlockSpecialQuartz;

public class ItemBlockSpecialQuartz
extends ItemMultiTexture {
    public ItemBlockSpecialQuartz(Block par1) {
        super(par1, par1, new String[]{""});
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return par1ItemStack.func_77960_j() >= 3 ? "" : ((BlockSpecialQuartz)this.field_150939_a).getNames()[par1ItemStack.func_77960_j()];
    }
}

