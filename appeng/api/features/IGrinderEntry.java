/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package appeng.api.features;

import net.minecraft.item.ItemStack;

public interface IGrinderEntry {
    public ItemStack getInput();

    public void setInput(ItemStack var1);

    public ItemStack getOutput();

    public ItemStack getOptionalOutput();

    public ItemStack getSecondOptionalOutput();

    public void setOutput(ItemStack var1);

    public void setOptionalOutput(ItemStack var1, float var2);

    public float getOptionalChance();

    public void setSecondOptionalOutput(ItemStack var1, float var2);

    public float getSecondOptionalChance();

    public int getEnergyCost();

    public void setEnergyCost(int var1);
}

