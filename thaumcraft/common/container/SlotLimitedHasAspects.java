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
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;

public class SlotLimitedHasAspects
extends Slot {
    public SlotLimitedHasAspects(IInventory par2IInventory, int par3, int par4, int par5) {
        super(par2IInventory, par3, par4, par5);
    }

    public boolean func_75214_a(ItemStack par1ItemStack) {
        AspectList al = ThaumcraftCraftingManager.getObjectTags(par1ItemStack);
        return (al = ThaumcraftCraftingManager.getBonusTags(par1ItemStack, al)) != null && al.size() > 0;
    }
}

