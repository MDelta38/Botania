/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  thaumcraft.api.ThaumcraftApi
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.crafting.CrucibleRecipe
 *  thaumcraft.common.config.ConfigResearch
 */
package thaumic.tinkerer.common.registry;

import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.common.config.ConfigResearch;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;

public class ThaumicTinkererCrucibleRecipe
extends ThaumicTinkererRecipe {
    private final String name;
    private final ItemStack output;
    private final ItemStack input;
    private final AspectList aspects;

    public ThaumicTinkererCrucibleRecipe(String name, ItemStack output, ItemStack input, AspectList aspects) {
        this.name = name;
        this.output = output;
        this.input = input;
        this.aspects = aspects;
    }

    @Override
    public void registerRecipe() {
        CrucibleRecipe recipe = ThaumcraftApi.addCrucibleRecipe((String)this.name, (ItemStack)this.output, (Object)this.input, (AspectList)this.aspects);
        ConfigResearch.recipes.put(this.name, recipe);
    }
}

