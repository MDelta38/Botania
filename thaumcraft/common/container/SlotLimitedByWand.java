/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package thaumcraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumcraft.common.items.wands.ItemWandCasting;

public class SlotLimitedByWand
extends Slot {
    int limit = 64;

    public SlotLimitedByWand(IInventory par2IInventory, int par3, int par4, int par5) {
        super(par2IInventory, par3, par4, par5);
    }

    public boolean func_75214_a(ItemStack stack) {
        return stack != null && stack.func_77973_b() != null && stack.func_77973_b() instanceof ItemWandCasting && !((ItemWandCasting)stack.func_77973_b()).isStaff(stack);
    }

    public int func_75219_a() {
        return this.limit;
    }

    public void func_82870_a(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack) {
        super.func_82870_a(par1EntityPlayer, par2ItemStack);
    }
}

