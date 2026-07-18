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
 *  net.minecraft.item.Item
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeManaInfusion;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.client.render.tile.RenderTilePool;
import vazkii.botania.common.block.ModBlocks;

public class RecipeHandlerManaPool
extends TemplateRecipeHandler {
    public String getRecipeName() {
        return StatCollector.func_74838_a((String)"botania.nei.manaPool");
    }

    public String getGuiTexture() {
        return "botania:textures/gui/neiBlank.png";
    }

    public int recipiesPerPage() {
        return 1;
    }

    public void loadTransferRects() {
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(70, 36, 18, 18), "botania.manaPool", new Object[0]));
    }

    public void drawBackground(int recipe) {
        super.drawBackground(recipe);
        GL11.glEnable((int)3042);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.5f);
        GuiDraw.changeTexture((String)"botania:textures/gui/manaInfusionOverlay.png");
        GuiDraw.drawTexturedModalRect((int)45, (int)20, (int)38, (int)35, (int)92, (int)50);
        HUDHandler.renderManaBar(32, 80, 255, 0.75f, ((CachedManaPoolRecipe)((Object)this.arecipes.get((int)recipe))).mana, 100000);
        RenderTilePool.forceMana = true;
    }

    public void loadCraftingRecipes(String outputId, Object ... results) {
        if (outputId.equals("botania.manaPool")) {
            for (RecipeManaInfusion recipe : BotaniaAPI.manaInfusionRecipes) {
                if (recipe == null) continue;
                this.arecipes.add(new CachedManaPoolRecipe(recipe));
            }
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    public void loadCraftingRecipes(ItemStack result) {
        for (RecipeManaInfusion recipe : BotaniaAPI.manaInfusionRecipes) {
            if (recipe == null || !NEIServerUtils.areStacksSameTypeCrafting((ItemStack)recipe.getOutput(), (ItemStack)result)) continue;
            this.arecipes.add(new CachedManaPoolRecipe(recipe));
        }
    }

    public void loadUsageRecipes(ItemStack ingredient) {
        for (RecipeManaInfusion recipe : BotaniaAPI.manaInfusionRecipes) {
            CachedManaPoolRecipe crecipe;
            if (recipe == null || !(crecipe = new CachedManaPoolRecipe(recipe)).contains(crecipe.getIngredients(), ingredient) && !crecipe.contains(crecipe.getOtherStacks(), ingredient)) continue;
            this.arecipes.add(crecipe);
        }
    }

    public class CachedManaPoolRecipe
    extends TemplateRecipeHandler.CachedRecipe {
        public List<PositionedStack> inputs;
        public PositionedStack output;
        public List<PositionedStack> otherStacks;
        public int mana;

        public CachedManaPoolRecipe(RecipeManaInfusion recipe) {
            super((TemplateRecipeHandler)RecipeHandlerManaPool.this);
            this.inputs = new ArrayList<PositionedStack>();
            this.otherStacks = new ArrayList<PositionedStack>();
            if (recipe == null) {
                return;
            }
            this.inputs.add(new PositionedStack((Object)new ItemStack(ModBlocks.pool, 1, recipe.getOutput().func_77973_b() == Item.func_150898_a((Block)ModBlocks.pool) ? 2 : 0), 71, 37));
            if (recipe.getInput() instanceof String) {
                this.inputs.add(new PositionedStack((Object)OreDictionary.getOres((String)((String)recipe.getInput())), 42, 37));
            } else {
                this.inputs.add(new PositionedStack(recipe.getInput(), 42, 37));
            }
            if (recipe.isAlchemy()) {
                this.otherStacks.add(new PositionedStack((Object)new ItemStack(ModBlocks.alchemyCatalyst), 10, 37));
            } else if (recipe.isConjuration()) {
                this.otherStacks.add(new PositionedStack((Object)new ItemStack(ModBlocks.conjurationCatalyst), 10, 37));
            }
            this.output = new PositionedStack((Object)recipe.getOutput(), 101, 37);
            this.mana = recipe.getManaToConsume();
        }

        public List<PositionedStack> getIngredients() {
            return this.getCycledIngredients(RecipeHandlerManaPool.this.cycleticks / 20, this.inputs);
        }

        public PositionedStack getResult() {
            return this.output;
        }

        public List<PositionedStack> getOtherStacks() {
            return this.otherStacks;
        }

        public boolean contains(Collection<PositionedStack> ingredients, ItemStack ingredient) {
            if (ingredients == this.inputs) {
                boolean skippedPool = false;
                for (PositionedStack stack : ingredients) {
                    if (!skippedPool) {
                        skippedPool = true;
                        continue;
                    }
                    if (!stack.contains(ingredient)) continue;
                    return true;
                }
            }
            return super.contains(ingredients, ingredient);
        }
    }
}

