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

public class SlotOutput
extends Slot {
    public SlotOutput(IInventory par2IInventory, int par3, int par4, int par5) {
        super(par2IInventory, par3, par4, par5);
    }

    public boolean func_75214_a(ItemStack par1ItemStack) {
        return false;
    }
}

