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
package witchinggadgets.client.nei;

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
import witchinggadgets.common.util.Utilities;
import witchinggadgets.common.util.recipe.InfernalBlastfurnaceRecipe;

public class NEIInfernalBlastfurnaceHandler
extends TemplateRecipeHandler {
    public void loadTransferRects() {
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(59, 8, 48, 48), "wgInfernalBlastfurnace", new Object[0]));
    }

    public void loadCraftingRecipes(String outputId, Object ... results) {
        if (outputId == this.getOverlayIdentifier()) {
            for (InfernalBlastfurnaceRecipe recipe : InfernalBlastfurnaceRecipe.recipes) {
                if (recipe == null || recipe.getOutput() == null) continue;
                this.arecipes.add(new CachedInfernalBlastfurnaceRecipe(recipe));
            }
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    public String getRecipeName() {
        return StatCollector.func_74838_a((String)"tile.WG_StoneDevice.blastFurnace.name");
    }

    public String getGuiTexture() {
        return "witchinggadgets:textures/gui/nei/blastfurnace.png";
    }

    public String getOverlayIdentifier() {
        return "wgInfernalBlastfurnace";
    }

    public int recipiesPerPage() {
        return 1;
    }

    public void loadCraftingRecipes(ItemStack result) {
        InfernalBlastfurnaceRecipe recipe = InfernalBlastfurnaceRecipe.getRecipeForOutput(result);
        if (recipe != null) {
            this.arecipes.add(new CachedInfernalBlastfurnaceRecipe(recipe));
        }
    }

    public void loadUsageRecipes(ItemStack ingredient) {
        for (InfernalBlastfurnaceRecipe recipe : InfernalBlastfurnaceRecipe.recipes) {
            if (recipe == null || !(recipe.getInput() instanceof Utilities.OreDictStack ? Utilities.compareToOreName(ingredient, ((Utilities.OreDictStack)recipe.getInput()).key) : OreDictionary.itemMatches((ItemStack)ingredient, (ItemStack)((ItemStack)recipe.getInput()), (boolean)true))) continue;
            this.arecipes.add(new CachedInfernalBlastfurnaceRecipe(recipe));
        }
    }

    public void drawBackground(int recipe) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GuiDraw.changeTexture((String)this.getGuiTexture());
        GuiDraw.drawTexturedModalRect((int)0, (int)0, (int)5, (int)11, (int)166, (int)106);
    }

    public class CachedInfernalBlastfurnaceRecipe
    extends TemplateRecipeHandler.CachedRecipe {
        PositionedStack input;
        PositionedStack output;
        PositionedStack bonus;

        public CachedInfernalBlastfurnaceRecipe(InfernalBlastfurnaceRecipe recipe) {
            Object oInput;
            super((TemplateRecipeHandler)NEIInfernalBlastfurnaceHandler.this);
            Object object = oInput = recipe.getInput() instanceof Utilities.OreDictStack ? OreDictionary.getOres((String)((Utilities.OreDictStack)recipe.getInput()).key) : recipe.getInput();
            if (oInput instanceof ArrayList) {
                oInput = ((ArrayList)oInput).clone();
                for (ItemStack is : (ArrayList)oInput) {
                    is.field_77994_a = ((Utilities.OreDictStack)recipe.getInput()).amount;
                }
            }
            this.input = new PositionedStack(oInput, 24, 24, true);
            this.output = new PositionedStack((Object)recipe.getOutput(), 126, 14, false);
            if (recipe.getBonus() != null) {
                this.bonus = new PositionedStack((Object)recipe.getBonus(), 126, 39, false);
            }
        }

        public List<PositionedStack> getIngredients() {
            return this.getCycledIngredients(NEIInfernalBlastfurnaceHandler.this.cycleticks / 20, super.getIngredients());
        }

        public PositionedStack getIngredient() {
            return this.input;
        }

        public PositionedStack getResult() {
            return this.output;
        }

        public PositionedStack getOtherStack() {
            return this.bonus;
        }
    }
}

