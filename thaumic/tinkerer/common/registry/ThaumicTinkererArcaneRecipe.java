/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  thaumcraft.api.ThaumcraftApi
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.crafting.ShapedArcaneRecipe
 *  thaumcraft.common.config.ConfigResearch
 */
package thaumic.tinkerer.common.registry;

import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.common.config.ConfigResearch;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;

public class ThaumicTinkererArcaneRecipe
extends ThaumicTinkererRecipe {
    private final Object[] stuff;
    public String name;
    public String research;
    public ItemStack output;
    public AspectList aspects;
    public Object[] recipies;

    public ThaumicTinkererArcaneRecipe(String name, String research, ItemStack output, AspectList aspects, Object ... stuff) {
        this.name = name;
        this.research = research;
        this.output = output;
        this.aspects = aspects;
        this.stuff = stuff;
    }

    @Override
    public void registerRecipe() {
        ShapedArcaneRecipe recipe = ThaumcraftApi.addArcaneCraftingRecipe((String)this.research, (ItemStack)this.output, (AspectList)this.aspects, (Object[])this.stuff);
        ConfigResearch.recipes.put(this.name, recipe);
    }
}

