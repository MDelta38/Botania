/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 */
package witchinggadgets.common.blocks;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import witchinggadgets.common.blocks.BlockWGStoneDevice;

public class ItemBlockStoneDevice
extends ItemBlock {
    public ItemBlockStoneDevice(Block b) {
        super(b);
        this.func_77627_a(true);
    }

    public int func_77647_b(int damageValue) {
        return damageValue;
    }

    public void func_150895_a(Item item, CreativeTabs tab, List itemList) {
        itemList.add(new ItemStack(item, 1, 0));
        itemList.add(new ItemStack(item, 1, 1));
        itemList.add(new ItemStack(item, 1, 2));
        itemList.add(new ItemStack(item, 1, 3));
        itemList.add(new ItemStack(item, 1, 6));
        itemList.add(new ItemStack(item, 1, 7));
    }

    public String func_77667_c(ItemStack itemstack) {
        return this.func_77658_a() + "." + BlockWGStoneDevice.subNames[itemstack.func_77960_j()];
    }
}

