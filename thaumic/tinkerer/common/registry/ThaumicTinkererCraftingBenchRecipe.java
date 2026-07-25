/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.CraftingManager
 *  thaumcraft.common.config.ConfigResearch
 */
package thaumic.tinkerer.common.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import thaumcraft.common.config.ConfigResearch;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;

public class ThaumicTinkererCraftingBenchRecipe
extends ThaumicTinkererRecipe {
    private final String name;
    private final ItemStack output;
    private final Object[] stuff;

    public ThaumicTinkererCraftingBenchRecipe(String name, ItemStack output, Object ... stuff) {
        this.name = name;
        this.output = output;
        this.stuff = stuff;
    }

    @Override
    public void registerRecipe() {
        GameRegistry.addRecipe((ItemStack)this.output, (Object[])this.stuff);
        List recipeList = CraftingManager.func_77594_a().func_77592_b();
        if (this.name != null && this.name.length() != 0) {
            ConfigResearch.recipes.put(this.name, recipeList.get(recipeList.size() - 1));
        }
    }
}

