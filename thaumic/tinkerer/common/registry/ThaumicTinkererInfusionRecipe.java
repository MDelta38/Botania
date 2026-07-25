/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  thaumcraft.api.ThaumcraftApi
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.crafting.InfusionRecipe
 *  thaumcraft.common.config.ConfigResearch
 */
package thaumic.tinkerer.common.registry;

import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.common.config.ConfigResearch;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;

public class ThaumicTinkererInfusionRecipe
extends ThaumicTinkererRecipe {
    private final String name;
    private final String research;
    private final Object output;
    private final int instability;
    private final AspectList aspects;
    private final ItemStack input;
    private final ItemStack[] stuff;

    public ThaumicTinkererInfusionRecipe(String name, String research, Object output, int instability, AspectList aspects, ItemStack input, ItemStack ... stuff) {
        this.name = name;
        this.research = research;
        this.output = output;
        this.instability = instability;
        this.aspects = aspects;
        this.input = input;
        this.stuff = stuff;
    }

    public ThaumicTinkererInfusionRecipe(String name, Object output, int instability, AspectList aspects, ItemStack input, ItemStack ... stuff) {
        this(name, name, output, instability, aspects, input, stuff);
    }

    @Override
    public void registerRecipe() {
        InfusionRecipe recipe = ThaumcraftApi.addInfusionCraftingRecipe((String)this.research, (Object)this.output, (int)this.instability, (AspectList)this.aspects, (ItemStack)this.input, (ItemStack[])this.stuff);
        ConfigResearch.recipes.put(this.name, recipe);
    }
}

