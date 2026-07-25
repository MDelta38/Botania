/*
 * Decompiled with CFR 0.152.
 */
package thaumic.tinkerer.common.registry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;

public class ThaumicTinkererRecipeMulti
extends ThaumicTinkererRecipe {
    private List<ThaumicTinkererRecipe> recipes;

    public ThaumicTinkererRecipeMulti(ThaumicTinkererRecipe ... recipes) {
        this.recipes = Arrays.asList(recipes);
    }

    public ThaumicTinkererRecipeMulti() {
        this.recipes = new ArrayList<ThaumicTinkererRecipe>();
    }

    public void addRecipe(ThaumicTinkererRecipe recipe) {
        this.recipes.add(recipe);
    }

    @Override
    public void registerRecipe() {
        for (ThaumicTinkererRecipe recipe : this.recipes) {
            recipe.registerRecipe();
        }
    }
}

