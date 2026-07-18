/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.item.crafting.ShapedRecipes
 *  net.minecraft.item.crafting.ShapelessRecipes
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.oredict.ShapedOreRecipe
 *  net.minecraftforge.oredict.ShapelessOreRecipe
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.lexicon.page;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.lexicon.LexiconRecipeMappings;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.common.lexicon.page.PageRecipe;

public class PageCraftingRecipe
extends PageRecipe {
    private static final ResourceLocation craftingOverlay = new ResourceLocation("botania:textures/gui/craftingOverlay.png");
    List<IRecipe> recipes;
    int ticksElapsed = 0;
    int recipeAt = 0;
    boolean oreDictRecipe;
    boolean shapelessRecipe;

    public PageCraftingRecipe(String unlocalizedName, List<IRecipe> recipes) {
        super(unlocalizedName);
        this.recipes = recipes;
    }

    public PageCraftingRecipe(String unlocalizedName, IRecipe recipe) {
        this(unlocalizedName, Arrays.asList(recipe));
    }

    @Override
    public void onPageAdded(LexiconEntry entry, int index) {
        for (IRecipe recipe : this.recipes) {
            LexiconRecipeMappings.map(recipe.func_77571_b(), entry, index);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void renderRecipe(IGuiLexiconEntry gui, int mx, int my) {
        this.shapelessRecipe = false;
        this.oreDictRecipe = false;
        IRecipe recipe = this.recipes.get(this.recipeAt);
        this.renderCraftingRecipe(gui, recipe);
        TextureManager render = Minecraft.func_71410_x().field_71446_o;
        render.func_110577_a(craftingOverlay);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ((GuiScreen)gui).func_73729_b(gui.getLeft(), gui.getTop(), 0, 0, gui.getWidth(), gui.getHeight());
        int iconX = gui.getLeft() + 115;
        int iconY = gui.getTop() + 12;
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        if (this.shapelessRecipe) {
            ((GuiScreen)gui).func_73729_b(iconX, iconY, 240, 0, 16, 16);
            if (mx >= iconX && my >= iconY && mx < iconX + 16 && my < iconY + 16) {
                RenderHelper.renderTooltip(mx, my, Arrays.asList(StatCollector.func_74838_a((String)"botaniamisc.shapeless")));
            }
            iconY += 20;
        }
        render.func_110577_a(craftingOverlay);
        GL11.glEnable((int)3042);
        if (this.oreDictRecipe) {
            ((GuiScreen)gui).func_73729_b(iconX, iconY, 240, 16, 16, 16);
            if (mx >= iconX && my >= iconY && mx < iconX + 16 && my < iconY + 16) {
                RenderHelper.renderTooltip(mx, my, Arrays.asList(StatCollector.func_74838_a((String)"botaniamisc.oredict")));
            }
        }
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

    @SideOnly(value=Side.CLIENT)
    public void renderCraftingRecipe(IGuiLexiconEntry gui, IRecipe recipe) {
        if (recipe instanceof ShapedRecipes) {
            ShapedRecipes shaped = (ShapedRecipes)recipe;
            for (int y = 0; y < shaped.field_77577_c; ++y) {
                for (int x = 0; x < shaped.field_77576_b; ++x) {
                    this.renderItemAtGridPos(gui, 1 + x, 1 + y, shaped.field_77574_d[y * shaped.field_77576_b + x], true);
                }
            }
        } else if (recipe instanceof ShapedOreRecipe) {
            ShapedOreRecipe shaped = (ShapedOreRecipe)recipe;
            int width = (Integer)ReflectionHelper.getPrivateValue(ShapedOreRecipe.class, (Object)shaped, (int)4);
            int height = (Integer)ReflectionHelper.getPrivateValue(ShapedOreRecipe.class, (Object)shaped, (int)5);
            for (int y = 0; y < height; ++y) {
                for (int x = 0; x < width; ++x) {
                    Object input = shaped.getInput()[y * width + x];
                    if (input == null) continue;
                    this.renderItemAtGridPos(gui, 1 + x, 1 + y, input instanceof ItemStack ? (ItemStack)input : (ItemStack)((ArrayList)input).get(0), true);
                }
            }
            this.oreDictRecipe = true;
        } else if (recipe instanceof ShapelessRecipes) {
            ShapelessRecipes shapeless = (ShapelessRecipes)recipe;
            block4: for (int y = 0; y < 3; ++y) {
                for (int x = 0; x < 3; ++x) {
                    int index = y * 3 + x;
                    if (index >= shapeless.field_77579_b.size()) break block4;
                    this.renderItemAtGridPos(gui, 1 + x, 1 + y, (ItemStack)shapeless.field_77579_b.get(index), true);
                }
            }
            this.shapelessRecipe = true;
        } else if (recipe instanceof ShapelessOreRecipe) {
            ShapelessOreRecipe shapeless = (ShapelessOreRecipe)recipe;
            block6: for (int y = 0; y < 3; ++y) {
                for (int x = 0; x < 3; ++x) {
                    int index = y * 3 + x;
                    if (index >= shapeless.func_77570_a()) break block6;
                    Object input = shapeless.getInput().get(index);
                    if (input == null) continue;
                    this.renderItemAtGridPos(gui, 1 + x, 1 + y, input instanceof ItemStack ? (ItemStack)input : (ItemStack)((ArrayList)input).get(0), true);
                }
            }
            this.shapelessRecipe = true;
            this.oreDictRecipe = true;
        }
        this.renderItemAtGridPos(gui, 2, 0, recipe.func_77571_b(), false);
    }

    @Override
    public List<ItemStack> getDisplayedRecipes() {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        for (IRecipe r : this.recipes) {
            list.add(r.func_77571_b());
        }
        return list;
    }
}

