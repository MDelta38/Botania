/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.recipes;

import appeng.api.exceptions.MissingIngredientError;
import appeng.api.exceptions.RecipeError;
import appeng.api.exceptions.RegistrationError;
import appeng.api.recipes.IIngredient;
import java.util.List;

public interface ICraftHandler {
    public void setup(List<List<IIngredient>> var1, List<List<IIngredient>> var2) throws RecipeError;

    public void register() throws RegistrationError, MissingIngredientError;
}

