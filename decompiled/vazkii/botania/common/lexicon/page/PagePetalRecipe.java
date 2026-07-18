/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
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
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.lexicon.LexiconRecipeMappings;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.lexicon.page.PageRecipe;

public class PagePetalRecipe<T extends RecipePetals>
extends PageRecipe {
    private static final ResourceLocation petalOverlay = new ResourceLocation("botania:textures/gui/petalOverlay.png");
    List<T> recipes;
    int ticksElapsed = 0;
    int recipeAt = 0;

    public PagePetalRecipe(String unlocalizedName, List<T> recipes) {
        super(unlocalizedName);
        this.recipes = recipes;
    }

    public PagePetalRecipe(String unlocalizedName, T recipe) {
        this(unlocalizedName, Arrays.asList(recipe));
    }

    @Override
    public void onPageAdded(LexiconEntry entry, int index) {
        for (RecipePetals recipe : this.recipes) {
            LexiconRecipeMappings.map(recipe.getOutput(), entry, index);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void renderRecipe(IGuiLexiconEntry gui, int mx, int my) {
        RecipePetals recipe = (RecipePetals)this.recipes.get(this.recipeAt);
        TextureManager render = Minecraft.func_71410_x().field_71446_o;
        this.renderItemAtGridPos(gui, 3, 0, recipe.getOutput(), false);
        this.renderItemAtGridPos(gui, 2, 1, this.getMiddleStack(), false);
        List<Object> inputs = recipe.getInputs();
        int degreePerInput = (int)(360.0f / (float)inputs.size());
        float currentDegree = ConfigHandler.lexiconRotatingItems ? (GuiScreen.func_146272_n() ? (float)this.ticksElapsed : (float)this.ticksElapsed + ClientTickHandler.partialTicks) : 0.0f;
        for (Object obj : inputs) {
            Object input = obj;
            if (input instanceof String) {
                input = OreDictionary.getOres((String)((String)input)).get(0);
            }
            this.renderItemAtAngle(gui, currentDegree, (ItemStack)input);
            currentDegree += (float)degreePerInput;
        }
        this.renderManaBar(gui, recipe, mx, my);
        render.func_110577_a(petalOverlay);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ((GuiScreen)gui).func_73729_b(gui.getLeft(), gui.getTop(), 0, 0, gui.getWidth(), gui.getHeight());
        GL11.glDisable((int)3042);
    }

    ItemStack getMiddleStack() {
        return new ItemStack(ModBlocks.altar);
    }

    @SideOnly(value=Side.CLIENT)
    public void renderManaBar(IGuiLexiconEntry gui, T recipe, int mx, int my) {
        FontRenderer font = Minecraft.func_71410_x().field_71466_p;
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        int x = gui.getLeft() + gui.getWidth() / 2 - 50;
        int y = gui.getTop() + 120;
        boolean unicode = font.func_82883_a();
        font.func_78264_a(true);
        String stopStr = StatCollector.func_74838_a((String)"botaniamisc.shiftToStopSpin");
        font.func_78276_b(stopStr, x + 50 - font.func_78256_a(stopStr) / 2, y + 15, -1728053248);
        font.func_78264_a(unicode);
        GL11.glDisable((int)3042);
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
        for (RecipePetals r : this.recipes) {
            list.add(r.getOutput());
        }
        return list;
    }
}

