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
import com.emoniph.witchery.crafting.KettleRecipes;
import java.awt.Rectangle;
import java.util.ArrayList;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class NEIKettleRecipeHandler
extends TemplateRecipeHandler {
    public void loadTransferRects() {
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(84, 23, 24, 18), "witchery_brewing", new Object[0]));
    }

    public Class<? extends GuiContainer> getGuiClass() {
        return GuiCrafting.class;
    }

    public String getRecipeName() {
        return StatCollector.func_74838_a((String)"tile.witchery:kettle.name");
    }

    public void loadCraftingRecipes(String outputId, Object ... results) {
        if (outputId.equals("witchery_brewing") && ((Object)((Object)this)).getClass() == NEIKettleRecipeHandler.class) {
            for (KettleRecipes.KettleRecipe recipe : KettleRecipes.instance().recipes) {
                this.arecipes.add(new CachedKettleRecipe(recipe.output, recipe));
            }
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    public void loadCraftingRecipes(ItemStack result) {
        KettleRecipes.KettleRecipe recipe = KettleRecipes.instance().findRecipeFor(result);
        if (recipe != null) {
            this.arecipes.add(new CachedKettleRecipe(recipe.output, recipe));
        }
    }

    public void loadUsageRecipes(ItemStack ingredient) {
    }

    public String getGuiTexture() {
        return "textures/gui/container/crafting_table.png";
    }

    public void drawExtras(int recipe) {
    }

    public String getOverlayIdentifier() {
        return "witchery_brewing";
    }

    public class CachedKettleRecipe
    extends TemplateRecipeHandler.CachedRecipe {
        PositionedStack result;
        PositionedStack[] inputs;

        public CachedKettleRecipe(ItemStack result, KettleRecipes.KettleRecipe recipe) {
            super((TemplateRecipeHandler)NEIKettleRecipeHandler.this);
            this.inputs = new PositionedStack[6];
            this.result = new PositionedStack((Object)result, 119, 24);
            for (int i = 0; i < recipe.inputs.length; ++i) {
                this.inputs[i] = recipe.inputs[i] != null ? new PositionedStack((Object)recipe.inputs[i], i < 3 ? 25 : 43, i * 18 % 54 + 6) : null;
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

