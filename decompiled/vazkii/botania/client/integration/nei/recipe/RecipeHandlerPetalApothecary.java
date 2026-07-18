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
 *  net.minecraft.init.Items
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
import java.util.List;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.common.block.ModBlocks;

public class RecipeHandlerPetalApothecary
extends TemplateRecipeHandler {
    public String getRecipeName() {
        return StatCollector.func_74838_a((String)"botania.nei.petalApothecary");
    }

    public String getRecipeID() {
        return "botania.petalApothecary";
    }

    public String getGuiTexture() {
        return "botania:textures/gui/neiBlank.png";
    }

    public void loadTransferRects() {
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(72, 54, 18, 18), this.getRecipeID(), new Object[0]));
    }

    public int recipiesPerPage() {
        return 1;
    }

    public void drawBackground(int recipe) {
        super.drawBackground(recipe);
        GL11.glEnable((int)3042);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.5f);
        GuiDraw.changeTexture((String)"botania:textures/gui/petalOverlay.png");
        GuiDraw.drawTexturedModalRect((int)45, (int)10, (int)38, (int)7, (int)92, (int)92);
    }

    public List<? extends RecipePetals> getRecipes() {
        return BotaniaAPI.petalRecipes;
    }

    public CachedPetalApothecaryRecipe getCachedRecipe(RecipePetals recipe) {
        return new CachedPetalApothecaryRecipe(recipe);
    }

    public void loadCraftingRecipes(String outputId, Object ... results) {
        if (outputId.equals(this.getRecipeID())) {
            for (RecipePetals recipePetals : this.getRecipes()) {
                if (recipePetals.getOutput().func_77973_b() == Items.field_151144_bL) continue;
                this.arecipes.add(this.getCachedRecipe(recipePetals));
            }
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    public void loadCraftingRecipes(ItemStack result) {
        for (RecipePetals recipePetals : this.getRecipes()) {
            if (recipePetals == null || (recipePetals.getOutput().field_77990_d == null || !NEIServerUtils.areStacksSameType((ItemStack)recipePetals.getOutput(), (ItemStack)result)) && (recipePetals.getOutput().field_77990_d != null || !NEIServerUtils.areStacksSameTypeCrafting((ItemStack)recipePetals.getOutput(), (ItemStack)result) || recipePetals.getOutput().func_77973_b() == Items.field_151144_bL)) continue;
            this.arecipes.add(this.getCachedRecipe(recipePetals));
        }
    }

    public void loadUsageRecipes(ItemStack ingredient) {
        for (RecipePetals recipePetals : this.getRecipes()) {
            CachedPetalApothecaryRecipe crecipe;
            if (recipePetals == null || !(crecipe = this.getCachedRecipe(recipePetals)).contains(crecipe.inputs, ingredient) || recipePetals.getOutput().func_77973_b() == Items.field_151144_bL) continue;
            this.arecipes.add(crecipe);
        }
    }

    public class CachedPetalApothecaryRecipe
    extends TemplateRecipeHandler.CachedRecipe {
        public List<PositionedStack> inputs;
        public PositionedStack output;

        public CachedPetalApothecaryRecipe(RecipePetals recipe, boolean addCenterItem) {
            super((TemplateRecipeHandler)RecipeHandlerPetalApothecary.this);
            this.inputs = new ArrayList<PositionedStack>();
            this.setIngredients(recipe.getInputs());
            this.output = new PositionedStack((Object)recipe.getOutput(), 111, 21);
            if (addCenterItem) {
                this.inputs.add(new PositionedStack((Object)new ItemStack(ModBlocks.altar), 73, 55));
            }
        }

        public CachedPetalApothecaryRecipe(RecipePetals recipe) {
            this(recipe, true);
        }

        public void setIngredients(List<Object> inputs) {
            float degreePerInput = 360.0f / (float)inputs.size();
            float currentDegree = -90.0f;
            for (Object o : inputs) {
                int posX = (int)Math.round(73.0 + Math.cos((double)currentDegree * Math.PI / 180.0) * 32.0);
                int posY = (int)Math.round(55.0 + Math.sin((double)currentDegree * Math.PI / 180.0) * 32.0);
                if (o instanceof String) {
                    this.inputs.add(new PositionedStack((Object)OreDictionary.getOres((String)((String)o)), posX, posY));
                } else {
                    this.inputs.add(new PositionedStack(o, posX, posY));
                }
                currentDegree += degreePerInput;
            }
        }

        public List<PositionedStack> getIngredients() {
            return this.getCycledIngredients(RecipeHandlerPetalApothecary.this.cycleticks / 20, this.inputs);
        }

        public PositionedStack getResult() {
            return this.output;
        }
    }
}

