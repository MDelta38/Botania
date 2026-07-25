/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package thaumcraft.common.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotLimitedByItemstack
extends Slot {
    ItemStack limitItem = null;

    public SlotLimitedByItemstack(ItemStack item, IInventory par2IInventory, int par3, int par4, int par5) {
        super(par2IInventory, par3, par4, par5);
        this.limitItem = item;
    }

    public boolean func_75214_a(ItemStack par1ItemStack) {
        return par1ItemStack.func_77969_a(this.limitItem);
    }
}

