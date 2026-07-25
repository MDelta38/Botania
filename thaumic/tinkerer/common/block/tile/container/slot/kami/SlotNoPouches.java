/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  thaumcraft.common.items.wands.ItemFocusPouch
 */
package thaumic.tinkerer.common.block.tile.container.slot.kami;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.common.items.wands.ItemFocusPouch;

public class SlotNoPouches
extends Slot {
    public SlotNoPouches(IInventory par1iInventory, int par2, int par3, int par4) {
        super(par1iInventory, par2, par3, par4);
    }

    public boolean func_75214_a(ItemStack par1ItemStack) {
        Item item = par1ItemStack.func_77973_b();
        return !(item instanceof ItemFocusPouch);
    }
}

