/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package appeng.api.networking.crafting;

import appeng.api.storage.data.IAEItemStack;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ICraftingPatternDetails {
    public ItemStack getPattern();

    public boolean isValidItemForSlot(int var1, ItemStack var2, World var3);

    public boolean isCraftable();

    public IAEItemStack[] getInputs();

    public IAEItemStack[] getCondensedInputs();

    public IAEItemStack[] getCondensedOutputs();

    public IAEItemStack[] getOutputs();

    public boolean canSubstitute();

    public ItemStack getOutput(InventoryCrafting var1, World var2);

    public void setPriority(int var1);

    public int getPriority();
}

