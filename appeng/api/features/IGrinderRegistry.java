/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package appeng.api.features;

import appeng.api.features.IGrinderEntry;
import java.util.List;
import net.minecraft.item.ItemStack;

public interface IGrinderRegistry {
    public List<IGrinderEntry> getRecipes();

    public void addRecipe(ItemStack var1, ItemStack var2, int var3);

    public void addRecipe(ItemStack var1, ItemStack var2, ItemStack var3, float var4, int var5);

    public void addRecipe(ItemStack var1, ItemStack var2, ItemStack var3, float var4, ItemStack var5, float var6, int var7);

    public IGrinderEntry getRecipeForInput(ItemStack var1);
}

