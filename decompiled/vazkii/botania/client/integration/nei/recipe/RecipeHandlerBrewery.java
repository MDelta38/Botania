/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.lib.gui.GuiDraw
 *  codechicken.nei.PositionedStack
 *  codechicken.nei.recipe.TemplateRecipeHandler
 *  codechicken.nei.recipe.TemplateRecipeHandler$CachedRecipe
 *  codechicken.nei.recipe.TemplateRecipeHandler$RecipeTransferRect
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.oredict.OreDictionary
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.integration.nei.recipe;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.brew.IBrewContainer;
import vazkii.botania.api.brew.IBrewItem;
import vazkii.botania.api.recipe.RecipeBrew;
import vazkii.botania.common.item.ModItems;

public class RecipeHandlerBrewery
extends TemplateRecipeHandler {
    public String getRecipeName() {
        return StatCollector.func_74838_a((String)"botania.nei.brewery");
    }

    public String getGuiTexture() {
        return "botania:textures/gui/neiBrewery.png";
    }

    public void loadTransferRects() {
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(87, 25, 15, 14), "botania.brewery", new Object[0]));
    }

    public void drawBackground(int recipe) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GuiDraw.changeTexture((String)this.getGuiTexture());
        GuiDraw.drawTexturedModalRect((int)0, (int)0, (int)0, (int)0, (int)166, (int)65);
    }

    public void loadCraftingRecipes(String outputId, Object ... results) {
        if (outputId.equals("botania.brewery")) {
            for (RecipeBrew recipe : BotaniaAPI.brewRecipes) {
                this.arecipes.add(new CachedBreweryRecipe(recipe));
            }
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    public void loadCraftingRecipes(ItemStack result) {
        if (result.func_77973_b() instanceof IBrewItem) {
            for (RecipeBrew recipe : BotaniaAPI.brewRecipes) {
                if (recipe == null || ((IBrewItem)result.func_77973_b()).getBrew(result) != recipe.getBrew()) continue;
                this.arecipes.add(new CachedBreweryRecipe(recipe));
            }
        }
    }

    public void loadUsageRecipes(ItemStack ingredient) {
        if (ingredient.func_77973_b() instanceof IBrewContainer) {
            for (RecipeBrew recipe : BotaniaAPI.brewRecipes) {
                if (recipe == null || recipe.getOutput(ingredient) == null) continue;
                this.arecipes.add(new CachedBreweryRecipe(recipe, ingredient));
            }
        } else {
            for (RecipeBrew recipe : BotaniaAPI.brewRecipes) {
                CachedBreweryRecipe crecipe;
                if (recipe == null || !(crecipe = new CachedBreweryRecipe(recipe)).contains(crecipe.inputs, ingredient)) continue;
                this.arecipes.add(crecipe);
            }
        }
    }

    public class CachedBreweryRecipe
    extends TemplateRecipeHandler.CachedRecipe {
        public List<PositionedStack> inputs;
        public PositionedStack output;
        public int mana;

        public CachedBreweryRecipe(RecipeBrew recipe, ItemStack vial) {
            super((TemplateRecipeHandler)RecipeHandlerBrewery.this);
            this.inputs = new ArrayList<PositionedStack>();
            if (recipe == null) {
                return;
            }
            this.setIngredients(recipe.getInputs());
            ItemStack toVial = vial == null ? new ItemStack(ModItems.vial) : vial.func_77946_l();
            toVial.field_77994_a = 1;
            this.inputs.add(new PositionedStack((Object)toVial, 39, 42));
            this.output = new PositionedStack((Object)recipe.getOutput(toVial), 87, 42);
        }

        public CachedBreweryRecipe(RecipeBrew recipe) {
            this(recipe, null);
        }

        public void setIngredients(List<Object> inputs) {
            int left = 96 - inputs.size() * 18 / 2;
            int i = 0;
            for (Object o : inputs) {
                if (o instanceof String) {
                    this.inputs.add(new PositionedStack((Object)OreDictionary.getOres((String)((String)o)), left + i * 18, 6));
                } else {
                    this.inputs.add(new PositionedStack(o, left + i * 18, 6));
                }
                ++i;
            }
        }

        public List<PositionedStack> getIngredients() {
            return this.getCycledIngredients(RecipeHandlerBrewery.this.cycleticks / 20, this.inputs);
        }

        public PositionedStack getResult() {
            return this.output;
        }
    }
}

