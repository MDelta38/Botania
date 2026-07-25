/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.recipes;

import appeng.api.recipes.IRecipeLoader;

public interface IRecipeHandler {
    public void parseRecipes(IRecipeLoader var1, String var2);

    public void injectRecipes();
}

