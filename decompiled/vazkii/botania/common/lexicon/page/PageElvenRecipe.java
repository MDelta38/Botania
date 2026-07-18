/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.oredict.OreDictionary
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.lexicon.page;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.lexicon.LexiconRecipeMappings;
import vazkii.botania.api.recipe.RecipeElvenTrade;
import vazkii.botania.common.block.BlockAlfPortal;
import vazkii.botania.common.lexicon.page.PageRecipe;

public class PageElvenRecipe
extends PageRecipe {
    private static final ResourceLocation elvenTradeOverlay = new ResourceLocation("botania:textures/gui/elvenTradeOverlay.png");
    List<RecipeElvenTrade> recipes;
    int ticksElapsed = 0;
    int recipeAt = 0;

    public PageElvenRecipe(String unlocalizedName, List<RecipeElvenTrade> recipes) {
        super(unlocalizedName);
        this.recipes = recipes;
    }

    public PageElvenRecipe(String unlocalizedName, RecipeElvenTrade recipe) {
        this(unlocalizedName, Arrays.asList(recipe));
    }

    @Override
    public void onPageAdded(LexiconEntry entry, int index) {
        for (RecipeElvenTrade recipe : this.recipes) {
            LexiconRecipeMappings.map(recipe.getOutput(), entry, index);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void renderRecipe(IGuiLexiconEntry gui, int mx, int my) {
        RecipeElvenTrade recipe = this.recipes.get(this.recipeAt);
        TextureManager render = Minecraft.func_71410_x().field_71446_o;
        render.func_110577_a(elvenTradeOverlay);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ((GuiScreen)gui).func_73729_b(gui.getLeft(), gui.getTop(), 0, 0, gui.getWidth(), gui.getHeight());
        GL11.glDisable((int)3042);
        this.renderItemAtGridPos(gui, 3, 1, recipe.getOutput(), false);
        List<Object> inputs = recipe.getInputs();
        int i = 0;
        for (Object obj : inputs) {
            Object input = obj;
            if (input instanceof String) {
                input = OreDictionary.getOres((String)((String)input)).get(0);
            }
            this.renderItemAtInputPos(gui, i, (ItemStack)input);
            ++i;
        }
        IIcon portalIcon = BlockAlfPortal.portalTex;
        Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
        RenderItem.getInstance().func_94149_a(gui.getLeft() + 22, gui.getTop() + 36, portalIcon, 48, 48);
    }

    @SideOnly(value=Side.CLIENT)
    public void renderItemAtInputPos(IGuiLexiconEntry gui, int x, ItemStack stack) {
        if (stack == null || stack.func_77973_b() == null) {
            return;
        }
        if ((stack = stack.func_77946_l()).func_77960_j() == Short.MAX_VALUE) {
            stack.func_77964_b(0);
        }
        int xPos = gui.getLeft() + x * 20 + 45;
        int yPos = gui.getTop() + 14;
        ItemStack stack1 = stack.func_77946_l();
        if (stack1.func_77960_j() == -1) {
            stack1.func_77964_b(0);
        }
        this.renderItem(gui, xPos, yPos, stack1, false);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void updateScreen() {
        if (GuiScreen.func_146272_n()) {
            return;
        }
        if (this.ticksElapsed % 20 == 0) {
            ++this.recipeAt;
            if (this.recipeAt == this.recipes.size()) {
                this.recipeAt = 0;
            }
        }
        ++this.ticksElapsed;
    }

    @Override
    public List<ItemStack> getDisplayedRecipes() {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        for (RecipeElvenTrade r : this.recipes) {
            list.add(r.getOutput());
        }
        return list;
    }
}

