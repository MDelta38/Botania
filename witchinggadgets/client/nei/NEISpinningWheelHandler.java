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
import witchinggadgets.common.util.recipe.SpinningRecipe;

public class NEISpinningWheelHandler
extends TemplateRecipeHandler {
    public void loadTransferRects() {
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(81, 46, 32, 16), "wgSpinningWheel", new Object[0]));
    }

    public void loadCraftingRecipes(String outputId, Object ... results) {
        if (outputId == this.getOverlayIdentifier()) {
            for (SpinningRecipe recipe : SpinningRecipe.recipeList) {
                if (recipe == null || recipe.getOutput() == null) continue;
                this.arecipes.add(new CachedSpinningWheelRecipe(recipe));
            }
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    public String getRecipeName() {
        return StatCollector.func_74838_a((String)"tile.WG_WoodenDevice.spinningWheel.name");
    }

    public String getGuiTexture() {
        return "witchinggadgets:textures/gui/nei/spinningwheel.png";
    }

    public String getOverlayIdentifier() {
        return "wgSpinningWheel";
    }

    public int recipiesPerPage() {
        return 1;
    }

    public void loadCraftingRecipes(ItemStack result) {
        SpinningRecipe recipe = SpinningRecipe.getSpinningRecipe(result);
        if (recipe != null) {
            this.arecipes.add(new CachedSpinningWheelRecipe(recipe));
        }
    }

    public void loadUsageRecipes(ItemStack ingredient) {
        block0: for (SpinningRecipe recipe : SpinningRecipe.recipeList) {
            if (recipe == null || recipe.getOutput() == null) continue;
            for (Object ss : recipe.getInput()) {
                if (ss instanceof List && ((List)ss).contains(ingredient)) {
                    this.arecipes.add(new CachedSpinningWheelRecipe(recipe));
                    continue block0;
                }
                if (!(ss instanceof ItemStack) || !OreDictionary.itemMatches((ItemStack)((ItemStack)ss), (ItemStack)ingredient, (boolean)true)) continue;
                this.arecipes.add(new CachedSpinningWheelRecipe(recipe));
                continue block0;
            }
        }
    }

    public void drawBackground(int recipe) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GuiDraw.changeTexture((String)this.getGuiTexture());
        GuiDraw.drawTexturedModalRect((int)0, (int)0, (int)5, (int)11, (int)166, (int)106);
    }

    public class CachedSpinningWheelRecipe
    extends TemplateRecipeHandler.CachedRecipe {
        List<PositionedStack> inputs;
        PositionedStack output;

        public CachedSpinningWheelRecipe(SpinningRecipe recipe) {
            super((TemplateRecipeHandler)NEISpinningWheelHandler.this);
            this.inputs = new ArrayList<PositionedStack>();
            for (int i = 0; i < recipe.getInput().length; ++i) {
                this.inputs.add(new PositionedStack(recipe.getInput()[i], 21, 9 + 18 * i, true));
            }
            this.output = new PositionedStack((Object)recipe.getOutput(), 126, 46, false);
        }

        public List<PositionedStack> getIngredients() {
            return this.getCycledIngredients(NEISpinningWheelHandler.this.cycleticks / 20, this.inputs);
        }

        public PositionedStack getResult() {
            return this.output;
        }
    }
}

