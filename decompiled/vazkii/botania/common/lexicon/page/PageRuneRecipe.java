/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.lexicon.page;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.api.recipe.RecipeRuneAltar;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.lexicon.page.PagePetalRecipe;

public class PageRuneRecipe
extends PagePetalRecipe<RecipeRuneAltar> {
    public PageRuneRecipe(String unlocalizedName, List<RecipeRuneAltar> recipes) {
        super(unlocalizedName, recipes);
    }

    public PageRuneRecipe(String unlocalizedName, RecipeRuneAltar recipes) {
        super(unlocalizedName, recipes);
    }

    @Override
    ItemStack getMiddleStack() {
        return new ItemStack(ModBlocks.runeAltar);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void renderManaBar(IGuiLexiconEntry gui, RecipeRuneAltar recipe, int mx, int my) {
        FontRenderer font = Minecraft.func_71410_x().field_71466_p;
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        String manaUsage = StatCollector.func_74838_a((String)"botaniamisc.manaUsage");
        font.func_78276_b(manaUsage, gui.getLeft() + gui.getWidth() / 2 - font.func_78256_a(manaUsage) / 2, gui.getTop() + 110, 0x66000000);
        int ratio = 10;
        int x = gui.getLeft() + gui.getWidth() / 2 - 50;
        int y = gui.getTop() + 120;
        if (mx > x + 1 && mx <= x + 101 && my > y - 14 && my <= y + 11) {
            ratio = 1;
        }
        HUDHandler.renderManaBar(x, y, 255, 0.75f, recipe.getManaUsage(), 1000000 / ratio);
        String ratioString = String.format(StatCollector.func_74838_a((String)"botaniamisc.ratio"), ratio);
        String stopStr = StatCollector.func_74838_a((String)"botaniamisc.shiftToStopSpin");
        boolean unicode = font.func_82883_a();
        font.func_78264_a(true);
        font.func_78276_b(stopStr, x + 50 - font.func_78256_a(stopStr) / 2, y + 15, -1728053248);
        font.func_78276_b(ratioString, x + 50 - font.func_78256_a(ratioString) / 2, y + 5, -1728053248);
        font.func_78264_a(unicode);
        GL11.glDisable((int)3042);
    }
}

