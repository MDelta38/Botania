/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package thaumic.tinkerer.common.block.tile.container.slot.kami;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.item.kami.ItemSkyPearl;

public class SlotSkyPearl
extends Slot {
    public SlotSkyPearl(IInventory par1iInventory, int par2, int par3, int par4) {
        super(par1iInventory, par2, par3, par4);
    }

    public boolean func_75214_a(ItemStack par1ItemStack) {
        return par1ItemStack.func_77973_b() == ThaumicTinkerer.registry.getFirstItemFromClass(ItemSkyPearl.class);
    }

    public int func_75219_a() {
        return 1;
    }
}

