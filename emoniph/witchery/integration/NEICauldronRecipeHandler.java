/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.nei.PositionedStack
 *  codechicken.nei.recipe.TemplateRecipeHandler
 *  codechicken.nei.recipe.TemplateRecipeHandler$CachedRecipe
 *  codechicken.nei.recipe.TemplateRecipeHandler$RecipeTransferRect
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.gui.inventory.GuiCrafting
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 */
package com.emoniph.witchery.integration;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
import com.emoniph.witchery.brewing.action.BrewActionRitualRecipe;
import java.awt.Rectangle;
import java.util.ArrayList;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class NEICauldronRecipeHandler
extends TemplateRecipeHandler {
    public void loadTransferRects() {
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(92, 31, 24, 18), "witchery_brewing_plus", new Object[0]));
    }

    public Class<? extends GuiContainer> getGuiClass() {
        return GuiCrafting.class;
    }

    public String getRecipeName() {
        return StatCollector.func_74838_a((String)"tile.witchery:cauldron.name");
    }

    public void loadCraftingRecipes(String outputId, Object ... results) {
        if (outputId.equals("witchery_brewing_plus") && ((Object)((Object)this)).getClass() == NEICauldronRecipeHandler.class) {
            for (BrewActionRitualRecipe ritual : WitcheryBrewRegistry.INSTANCE.getRecipes()) {
                for (BrewActionRitualRecipe.Recipe recipe : ritual.getExpandedRecipes()) {
                    this.arecipes.add(new CachedKettleRecipe(recipe.result, recipe.ingredients));
                }
            }
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    public void loadCraftingRecipes(ItemStack result) {
        for (BrewActionRitualRecipe ritual : WitcheryBrewRegistry.INSTANCE.getRecipes()) {
            for (BrewActionRitualRecipe.Recipe recipe : ritual.getExpandedRecipes()) {
                if (!result.func_77969_a(recipe.result)) continue;
                this.arecipes.add(new CachedKettleRecipe(recipe.result, recipe.ingredients));
            }
        }
    }

    public void loadUsageRecipes(ItemStack ingredient) {
        for (BrewActionRitualRecipe ritual : WitcheryBrewRegistry.INSTANCE.getRecipes()) {
            for (BrewActionRitualRecipe.Recipe recipe : ritual.getExpandedRecipes()) {
                for (ItemStack stack : recipe.ingredients) {
                    if (!stack.func_77969_a(ingredient)) continue;
                    this.arecipes.add(new CachedKettleRecipe(recipe.result, recipe.ingredients));
                }
            }
        }
    }

    public String getGuiTexture() {
        return "witchery:textures/gui/witchesCauldron.png";
    }

    public void drawExtras(int recipe) {
    }

    public String getOverlayIdentifier() {
        return "witchery_brewing_plus";
    }

    public class CachedKettleRecipe
    extends TemplateRecipeHandler.CachedRecipe {
        PositionedStack result;
        PositionedStack[] inputs;

        public CachedKettleRecipe(ItemStack result, ItemStack[] recipe) {
            super((TemplateRecipeHandler)NEICauldronRecipeHandler.this);
            this.inputs = new PositionedStack[6];
            this.result = new PositionedStack((Object)result, 119, 31);
            for (int i = 0; i < recipe.length; ++i) {
                this.inputs[i] = recipe[i] != null ? new PositionedStack((Object)recipe[i], i * 18 + 10, 6) : null;
            }
        }

        public PositionedStack getResult() {
            return this.result;
        }

        public ArrayList<PositionedStack> getIngredients() {
            ArrayList<PositionedStack> recipestacks = new ArrayList<PositionedStack>();
            recipestacks.add(this.result);
            for (PositionedStack posStack : this.inputs) {
                if (posStack == null) continue;
                recipestacks.add(posStack);
            }
            return recipestacks;
        }
    }
}

