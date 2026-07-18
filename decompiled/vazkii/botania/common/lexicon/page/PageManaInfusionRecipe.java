/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
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
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.lexicon.LexiconRecipeMappings;
import vazkii.botania.api.recipe.RecipeManaInfusion;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.client.render.tile.RenderTilePool;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.lexicon.page.PageRecipe;

public class PageManaInfusionRecipe
extends PageRecipe {
    private static final ResourceLocation manaInfusionOverlay = new ResourceLocation("botania:textures/gui/manaInfusionOverlay.png");
    List<RecipeManaInfusion> recipes;
    int ticksElapsed = 0;
    int recipeAt = 0;

    public PageManaInfusionRecipe(String unlocalizedName, List<RecipeManaInfusion> recipes) {
        super(unlocalizedName);
        this.recipes = recipes;
    }

    public PageManaInfusionRecipe(String unlocalizedName, RecipeManaInfusion recipe) {
        this(unlocalizedName, Arrays.asList(recipe));
    }

    @Override
    public void onPageAdded(LexiconEntry entry, int index) {
        for (RecipeManaInfusion recipe : this.recipes) {
            LexiconRecipeMappings.map(recipe.getOutput(), entry, index);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void renderRecipe(IGuiLexiconEntry gui, int mx, int my) {
        RecipeManaInfusion recipe = this.recipes.get(this.recipeAt);
        TextureManager render = Minecraft.func_71410_x().field_71446_o;
        FontRenderer font = Minecraft.func_71410_x().field_71466_p;
        Object input = recipe.getInput();
        if (input instanceof String) {
            input = OreDictionary.getOres((String)((String)input)).get(0);
        }
        this.renderItemAtGridPos(gui, 1, 1, (ItemStack)input, false);
        RenderTilePool.forceMana = true;
        this.renderItemAtGridPos(gui, 2, 1, new ItemStack(ModBlocks.pool, 1, recipe.getOutput().func_77973_b() == Item.func_150898_a((Block)ModBlocks.pool) ? 2 : 0), false);
        this.renderItemAtGridPos(gui, 3, 1, recipe.getOutput(), false);
        if (recipe.isAlchemy()) {
            this.renderItemAtGridPos(gui, 1, 2, new ItemStack(ModBlocks.alchemyCatalyst), false);
        } else if (recipe.isConjuration()) {
            this.renderItemAtGridPos(gui, 1, 2, new ItemStack(ModBlocks.conjurationCatalyst), false);
        }
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        String manaUsage = StatCollector.func_74838_a((String)"botaniamisc.manaUsage");
        font.func_78276_b(manaUsage, gui.getLeft() + gui.getWidth() / 2 - font.func_78256_a(manaUsage) / 2, gui.getTop() + 105, 0x66000000);
        int ratio = 10;
        int x = gui.getLeft() + gui.getWidth() / 2 - 50;
        int y = gui.getTop() + 115;
        if (mx > x + 1 && mx <= x + 101 && my > y - 14 && my <= y + 11) {
            ratio = 1;
        }
        HUDHandler.renderManaBar(x, y, 255, 0.75f, recipe.getManaToConsume(), 1000000 / ratio);
        String ratioString = String.format(StatCollector.func_74838_a((String)"botaniamisc.ratio"), ratio);
        String dropString = StatCollector.func_74838_a((String)"botaniamisc.drop") + " " + EnumChatFormatting.BOLD + "(?)";
        boolean hoveringOverDrop = false;
        boolean unicode = font.func_82883_a();
        font.func_78264_a(true);
        int dw = font.func_78256_a(dropString);
        int dx = x + 35 - dw / 2;
        int dy = gui.getTop() + 30;
        if (mx > dx && mx <= dx + dw && my > dy && my <= dy + 10) {
            hoveringOverDrop = true;
        }
        font.func_78276_b(dropString, dx, dy, 0x77000000);
        font.func_78276_b(ratioString, x + 50 - font.func_78256_a(ratioString) / 2, y + 5, -1728053248);
        font.func_78264_a(unicode);
        GL11.glDisable((int)3042);
        render.func_110577_a(manaInfusionOverlay);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ((GuiScreen)gui).func_73729_b(gui.getLeft(), gui.getTop(), 0, 0, gui.getWidth(), gui.getHeight());
        GL11.glDisable((int)3042);
        if (hoveringOverDrop) {
            String key = RenderHelper.getKeyDisplayString("key.drop");
            String tip0 = StatCollector.func_74838_a((String)"botaniamisc.dropTip0").replaceAll("%key%", EnumChatFormatting.GREEN + key + EnumChatFormatting.WHITE);
            String tip1 = StatCollector.func_74838_a((String)"botaniamisc.dropTip1").replaceAll("%key%", EnumChatFormatting.GREEN + key + EnumChatFormatting.WHITE);
            RenderHelper.renderTooltip(mx, my, Arrays.asList(tip0, tip1));
        }
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
        for (RecipeManaInfusion r : this.recipes) {
            list.add(r.getOutput());
        }
        return list;
    }
}

