/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.lib.gui.GuiDraw
 *  codechicken.nei.NEIServerUtils
 *  codechicken.nei.PositionedStack
 *  codechicken.nei.recipe.TemplateRecipeHandler
 *  codechicken.nei.recipe.TemplateRecipeHandler$CachedRecipe
 *  codechicken.nei.recipe.TemplateRecipeHandler$RecipeTransferRect
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.oredict.OreDictionary
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.integration.nei.recipe;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipePureDaisy;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

public class RecipeHandlerPureDaisy
extends TemplateRecipeHandler {
    public String getRecipeName() {
        return StatCollector.func_74838_a((String)"botania.nei.pureDaisy");
    }

    public String getGuiTexture() {
        return "botania:textures/gui/neiBlank.png";
    }

    public int recipiesPerPage() {
        return 2;
    }

    public void loadTransferRects() {
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(70, 22, 18, 18), "botania.pureDaisy", new Object[0]));
    }

    public void drawBackground(int recipe) {
        super.drawBackground(recipe);
        GL11.glEnable((int)3042);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.5f);
        GuiDraw.changeTexture((String)"botania:textures/gui/pureDaisyOverlay.png");
        GuiDraw.drawTexturedModalRect((int)45, (int)10, (int)0, (int)0, (int)65, (int)44);
    }

    public void loadCraftingRecipes(String outputId, Object ... results) {
        if (outputId.equals("botania.pureDaisy")) {
            for (RecipePureDaisy recipe : BotaniaAPI.pureDaisyRecipes) {
                if (recipe == null) continue;
                this.arecipes.add(new CachedPureDaisyRecipe(recipe));
            }
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    public void loadCraftingRecipes(ItemStack result) {
        for (RecipePureDaisy recipe : BotaniaAPI.pureDaisyRecipes) {
            if (recipe == null || !NEIServerUtils.areStacksSameTypeCrafting((ItemStack)new ItemStack(recipe.getOutput()), (ItemStack)result)) continue;
            this.arecipes.add(new CachedPureDaisyRecipe(recipe));
        }
    }

    public void loadUsageRecipes(ItemStack ingredient) {
        for (RecipePureDaisy recipe : BotaniaAPI.pureDaisyRecipes) {
            CachedPureDaisyRecipe crecipe;
            if (recipe == null || !(crecipe = new CachedPureDaisyRecipe(recipe)).contains(crecipe.getIngredients(), ingredient) && !crecipe.contains(crecipe.getOtherStacks(), ingredient)) continue;
            this.arecipes.add(crecipe);
        }
    }

    public class CachedPureDaisyRecipe
    extends TemplateRecipeHandler.CachedRecipe {
        public List<PositionedStack> inputs;
        public PositionedStack output;
        public List<PositionedStack> otherStacks;

        public CachedPureDaisyRecipe(RecipePureDaisy recipe) {
            super((TemplateRecipeHandler)RecipeHandlerPureDaisy.this);
            this.inputs = new ArrayList<PositionedStack>();
            this.otherStacks = new ArrayList<PositionedStack>();
            if (recipe == null) {
                return;
            }
            this.inputs.add(new PositionedStack((Object)ItemBlockSpecialFlower.ofType("puredaisy"), 71, 23));
            if (recipe.getInput() instanceof String) {
                this.inputs.add(new PositionedStack((Object)OreDictionary.getOres((String)((String)recipe.getInput())), 42, 23));
            } else {
                this.inputs.add(new PositionedStack((Object)new ItemStack((Block)recipe.getInput()), 42, 23));
            }
            this.output = new PositionedStack((Object)new ItemStack(recipe.getOutput()), 101, 23);
        }

        public List<PositionedStack> getIngredients() {
            return this.getCycledIngredients(RecipeHandlerPureDaisy.this.cycleticks / 20, this.inputs);
        }

        public PositionedStack getResult() {
            return this.output;
        }

        public List<PositionedStack> getOtherStacks() {
            return this.otherStacks;
        }

        public boolean contains(Collection<PositionedStack> ingredients, ItemStack ingredient) {
            if (ingredients == this.inputs) {
                for (PositionedStack stack : ingredients) {
                    if (!stack.contains(ingredient)) continue;
                    return true;
                }
            }
            return super.contains(ingredients, ingredient);
        }
    }
}

