/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 */
package vazkii.botania.common.item.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

public class ItemBlockFloatingSpecialFlower
extends ItemBlockSpecialFlower {
    public ItemBlockFloatingSpecialFlower(Block block1) {
        super(block1);
    }

    public String func_77653_i(ItemStack stack) {
        String flowerName = this.func_77667_c(stack) + ".name";
        return String.format(StatCollector.func_74838_a((String)"botaniamisc.floatingPrefix"), StatCollector.func_74838_a((String)flowerName));
    }
}

