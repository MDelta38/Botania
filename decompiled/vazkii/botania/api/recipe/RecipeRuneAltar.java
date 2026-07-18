/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.api.recipe;

import net.minecraft.item.ItemStack;
import vazkii.botania.api.recipe.RecipePetals;

public class RecipeRuneAltar
extends RecipePetals {
    int mana;

    public RecipeRuneAltar(ItemStack output, int mana, Object ... inputs) {
        super(output, inputs);
        this.mana = mana;
    }

    public int getManaUsage() {
        return this.mana;
    }
}

