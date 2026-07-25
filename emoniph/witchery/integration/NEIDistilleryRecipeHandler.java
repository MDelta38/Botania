/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.nei.PositionedStack
 *  codechicken.nei.recipe.TemplateRecipeHandler
 *  codechicken.nei.recipe.TemplateRecipeHandler$CachedRecipe
 *  codechicken.nei.recipe.TemplateRecipeHandler$RecipeTransferRect
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 */
package com.emoniph.witchery.integration;

import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockDistilleryGUI;
import com.emoniph.witchery.crafting.DistilleryRecipes;
import java.awt.Rectangle;
import java.util.ArrayList;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class NEIDistilleryRecipeHandler
extends TemplateRecipeHandler {
    public Class<? extends GuiContainer> getGuiClass() {
        return BlockDistilleryGUI.class;
    }

    public String getRecipeName() {
        return StatCollector.func_74838_a((String)"tile.witchery:distilleryidle.name");
    }

    public void loadTransferRects() {
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(63, 4, 39, 35), "witchery_distilling", new Object[0]));
    }

    public void loadCraftingRecipes(String outputId, Object ... results) {
        if (outputId.equals("witchery_distilling") && ((Object)((Object)this)).getClass() == NEIDistilleryRecipeHandler.class) {
            for (DistilleryRecipes.DistilleryRecipe recipe : DistilleryRecipes.instance().recipes) {
                this.arecipes.add(new CachedDistillingRecipe(recipe.outputs[0], recipe));
            }
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    public void loadCraftingRecipes(ItemStack result) {
        DistilleryRecipes.DistilleryRecipe recipe = DistilleryRecipes.instance().findRecipeFor(result);
        if (recipe != null) {
            this.arecipes.add(new CachedDistillingRecipe(result, recipe));
        }
    }

    public void loadUsageRecipes(ItemStack ingredient) {
        DistilleryRecipes.DistilleryRecipe recipe = DistilleryRecipes.instance().findRecipeUsing(ingredient);
        if (recipe != null) {
            this.arecipes.add(new CachedDistillingRecipe(ingredient, recipe));
        }
    }

    public String getGuiTexture() {
        return "witchery:textures/gui/distiller.png";
    }

    public void drawExtras(int recipe) {
        this.drawProgressBar(63, 3, 176, 29, 39, 35, 200, 0);
        this.drawProgressBar(28, 8, 185, -2, 12, 30, 35, 3);
    }

    public String getOverlayIdentifier() {
        return "witchery_distilling";
    }

    public class CachedDistillingRecipe
    extends TemplateRecipeHandler.CachedRecipe {
        PositionedStack ingred1;
        PositionedStack ingred2;
        PositionedStack jars;
        PositionedStack[] outputs;

        public CachedDistillingRecipe(ItemStack result, DistilleryRecipes.DistilleryRecipe recipe) {
            super((TemplateRecipeHandler)NEIDistilleryRecipeHandler.this);
            this.outputs = new PositionedStack[6];
            this.ingred1 = new PositionedStack((Object)recipe.inputs[0], 43, 5);
            if (recipe.inputs[1] != null) {
                this.ingred2 = new PositionedStack((Object)recipe.inputs[1], 43, 23);
            }
            if (recipe.jars > 0) {
                this.jars = new PositionedStack((Object)Witchery.Items.GENERIC.itemEmptyClayJar.createStack(recipe.jars), 43, 43);
            }
            if (recipe.outputs[0] != null) {
                this.outputs[0] = new PositionedStack((Object)recipe.outputs[0], 105, 5);
            }
            if (recipe.outputs[1] != null) {
                this.outputs[1] = new PositionedStack((Object)recipe.outputs[1], 123, 5);
            }
            if (recipe.outputs[2] != null) {
                this.outputs[2] = new PositionedStack((Object)recipe.outputs[2], 105, 23);
            }
            if (recipe.outputs[3] != null) {
                this.outputs[3] = new PositionedStack((Object)recipe.outputs[3], 123, 23);
            }
        }

        public PositionedStack getResult() {
            return this.outputs[0];
        }

        public ArrayList<PositionedStack> getIngredients() {
            ArrayList<PositionedStack> recipestacks = new ArrayList<PositionedStack>();
            recipestacks.add(this.ingred1);
            if (this.ingred2 != null) {
                recipestacks.add(this.ingred2);
            }
            if (this.jars != null) {
                recipestacks.add(this.jars);
            }
            for (PositionedStack posStack : this.outputs) {
                if (posStack == null) continue;
                recipestacks.add(posStack);
            }
            return recipestacks;
        }
    }
}

