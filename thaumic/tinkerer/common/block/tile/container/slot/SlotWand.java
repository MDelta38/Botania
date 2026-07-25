/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  thaumcraft.common.items.wands.ItemWandCasting
 */
package thaumic.tinkerer.common.block.tile.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumcraft.common.items.wands.ItemWandCasting;

public class SlotWand
extends Slot {
    public SlotWand(IInventory par1iInventory, int par2, int par3, int par4) {
        super(par1iInventory, par2, par3, par4);
    }

    public boolean func_75214_a(ItemStack par1ItemStack) {
        if (!(par1ItemStack.func_77973_b() instanceof ItemWandCasting) || ((ItemWandCasting)par1ItemStack.func_77973_b()).isStaff(par1ItemStack)) {
            return false;
        }
        ItemWandCasting wand = (ItemWandCasting)par1ItemStack.func_77973_b();
        return wand.getCap(par1ItemStack).getBaseCostModifier() <= 1.0f;
    }

    public int func_75219_a() {
        return 1;
    }
}

