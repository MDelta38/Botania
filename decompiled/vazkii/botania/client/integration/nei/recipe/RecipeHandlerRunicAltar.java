/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.nei.PositionedStack
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 */
package vazkii.botania.client.integration.nei.recipe;

import codechicken.nei.PositionedStack;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.api.recipe.RecipeRuneAltar;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.client.integration.nei.recipe.RecipeHandlerPetalApothecary;
import vazkii.botania.common.block.ModBlocks;

public class RecipeHandlerRunicAltar
extends RecipeHandlerPetalApothecary {
    @Override
    public String getRecipeName() {
        return StatCollector.func_74838_a((String)"botania.nei.runicAltar");
    }

    @Override
    public String getRecipeID() {
        return "botania.runicAltar";
    }

    @Override
    public void drawBackground(int recipe) {
        super.drawBackground(recipe);
        HUDHandler.renderManaBar(32, 113, 255, 0.75f, ((CachedRunicAltarRecipe)((Object)this.arecipes.get((int)recipe))).manaUsage, 100000);
    }

    @Override
    public List<? extends RecipePetals> getRecipes() {
        return BotaniaAPI.runeAltarRecipes;
    }

    @Override
    public RecipeHandlerPetalApothecary.CachedPetalApothecaryRecipe getCachedRecipe(RecipePetals recipe) {
        return new CachedRunicAltarRecipe((RecipeRuneAltar)recipe);
    }

    public class CachedRunicAltarRecipe
    extends RecipeHandlerPetalApothecary.CachedPetalApothecaryRecipe {
        public int manaUsage;

        public CachedRunicAltarRecipe(RecipeRuneAltar recipe) {
            super(recipe, false);
            if (recipe == null) {
                return;
            }
            this.manaUsage = recipe.getManaUsage();
            this.inputs.add(new PositionedStack((Object)new ItemStack(ModBlocks.runeAltar), 73, 55));
        }
    }
}

