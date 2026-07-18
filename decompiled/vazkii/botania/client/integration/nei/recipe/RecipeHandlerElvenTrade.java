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
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
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
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeElvenTrade;
import vazkii.botania.common.block.BlockAlfPortal;

public class RecipeHandlerElvenTrade
extends TemplateRecipeHandler {
    public String getRecipeName() {
        return StatCollector.func_74838_a((String)"botania.nei.elvenTrade");
    }

    public String getGuiTexture() {
        return "botania:textures/gui/neiBlank.png";
    }

    public void loadTransferRects() {
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(35, 30, 48, 48), "botania.elvenTrade", new Object[0]));
    }

    public int recipiesPerPage() {
        return 1;
    }

    public void drawBackground(int recipe) {
        super.drawBackground(recipe);
        GL11.glEnable((int)3042);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.7f);
        GuiDraw.changeTexture((String)"botania:textures/gui/elvenTradeOverlay.png");
        GuiDraw.drawTexturedModalRect((int)30, (int)10, (int)17, (int)17, (int)100, (int)80);
        GL11.glDisable((int)3042);
        GuiDraw.changeTexture((ResourceLocation)TextureMap.field_110575_b);
        RenderItem.getInstance().func_94149_a(35, 29, BlockAlfPortal.portalTex, 48, 48);
    }

    private static boolean hasElvenKnowledge() {
        return true;
    }

    public void loadCraftingRecipes(String outputId, Object ... results) {
        if (outputId.equals("botania.elvenTrade") && RecipeHandlerElvenTrade.hasElvenKnowledge()) {
            if (RecipeHandlerElvenTrade.hasElvenKnowledge()) {
                for (RecipeElvenTrade recipe : BotaniaAPI.elvenTradeRecipes) {
                    if (recipe == null) continue;
                    this.arecipes.add(new CachedElvenTradeRecipe(recipe));
                }
            }
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    public void loadCraftingRecipes(ItemStack result) {
        if (RecipeHandlerElvenTrade.hasElvenKnowledge()) {
            for (RecipeElvenTrade recipe : BotaniaAPI.elvenTradeRecipes) {
                if (recipe == null || !NEIServerUtils.areStacksSameTypeCrafting((ItemStack)recipe.getOutput(), (ItemStack)result)) continue;
                this.arecipes.add(new CachedElvenTradeRecipe(recipe));
            }
        }
    }

    public void loadUsageRecipes(ItemStack ingredient) {
        if (RecipeHandlerElvenTrade.hasElvenKnowledge()) {
            for (RecipeElvenTrade recipe : BotaniaAPI.elvenTradeRecipes) {
                CachedElvenTradeRecipe crecipe;
                if (recipe == null || !(crecipe = new CachedElvenTradeRecipe(recipe)).contains(crecipe.inputs, ingredient)) continue;
                this.arecipes.add(crecipe);
            }
        }
    }

    public class CachedElvenTradeRecipe
    extends TemplateRecipeHandler.CachedRecipe {
        public List<PositionedStack> inputs;
        public PositionedStack output;

        public CachedElvenTradeRecipe(RecipeElvenTrade recipe) {
            super((TemplateRecipeHandler)RecipeHandlerElvenTrade.this);
            this.inputs = new ArrayList<PositionedStack>();
            if (recipe == null) {
                return;
            }
            this.setIngredients(recipe.getInputs());
            this.output = new PositionedStack((Object)recipe.getOutput(), 107, 46);
        }

        public void setIngredients(List<Object> inputs) {
            int i = 0;
            for (Object o : inputs) {
                if (o instanceof String) {
                    this.inputs.add(new PositionedStack((Object)OreDictionary.getOres((String)((String)o)), 60 + i * 18, 6));
                } else {
                    this.inputs.add(new PositionedStack(o, 60 + i * 18, 6));
                }
                ++i;
            }
        }

        public List<PositionedStack> getIngredients() {
            return this.getCycledIngredients(RecipeHandlerElvenTrade.this.cycleticks / 20, this.inputs);
        }

        public PositionedStack getResult() {
            return this.output;
        }
    }
}

