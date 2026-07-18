/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.client.gui.bag;

import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vazkii.botania.client.gui.bag.InventoryFlowerBag;
import vazkii.botania.common.block.ModBlocks;

public class SlotFlower
extends Slot {
    InventoryFlowerBag inv;
    int color;

    public SlotFlower(InventoryFlowerBag p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_, int color) {
        super((IInventory)p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
        this.color = color;
        this.inv = p_i1824_1_;
    }

    public void func_75220_a(ItemStack oldStack, ItemStack newStack) {
        this.inv.func_70299_a(this.color, newStack);
    }

    public boolean func_75214_a(ItemStack stack) {
        return stack.func_77973_b() == Item.func_150898_a((Block)ModBlocks.flower) && stack.func_77960_j() == this.color;
    }
}

