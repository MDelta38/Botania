/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.oredict.OreDictionary
 */
package vazkii.botania.common.lexicon.page;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.api.lexicon.ILexicon;
import vazkii.botania.api.lexicon.ITwoNamedPage;
import vazkii.botania.api.recipe.RecipeBrew;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lexicon.page.PageRecipe;
import vazkii.botania.common.lexicon.page.PageText;

public class PageBrew
extends PageRecipe
implements ITwoNamedPage {
    RecipeBrew recipe;
    String text;

    public PageBrew(RecipeBrew recipe, String unlocalizedName, String bottomText) {
        super(bottomText);
        this.recipe = recipe;
        this.text = unlocalizedName;
    }

    /*
     * WARNING - void declaration
     */
    @Override
    public void renderRecipe(IGuiLexiconEntry gui, int mx, int my) {
        int width = gui.getWidth() - 30;
        int height = gui.getHeight();
        int x = gui.getLeft() + 16;
        int y = gui.getTop() + 12;
        Brew brew = this.recipe.getBrew();
        FontRenderer renderer = Minecraft.func_71410_x().field_71466_p;
        boolean unicode = renderer.func_82883_a();
        renderer.func_78264_a(true);
        String s = EnumChatFormatting.BOLD + String.format(StatCollector.func_74838_a((String)"botaniamisc.brewOf"), StatCollector.func_74838_a((String)brew.getUnlocalizedName()));
        renderer.func_78276_b(s, gui.getLeft() + gui.getWidth() / 2 - renderer.func_78256_a(s) / 2, y, 0x222222);
        renderer.func_78264_a(unicode);
        PageText.renderText(x, y + 22, width, height, this.text);
        ItemStack book = Minecraft.func_71410_x().field_71439_g.func_71045_bC();
        if (book != null && book.func_77973_b() instanceof ILexicon && ((ILexicon)book.func_77973_b()).isKnowledgeUnlocked(book, BotaniaAPI.elvenKnowledge)) {
            this.renderItemAtLinePos(gui, 20, 2, y + 12, this.recipe.getOutput(new ItemStack(ModItems.vial)));
            this.renderItemAtLinePos(gui, 20, 3, y + 12, this.recipe.getOutput(new ItemStack(ModItems.vial, 1, 1)));
        } else {
            this.renderItemAtLinePos(gui, 0, -1, y + 12, this.recipe.getOutput(new ItemStack(ModItems.vial)));
        }
        int i = 0;
        y = gui.getTop() + gui.getHeight() - 54;
        ArrayList<Object> inputs = new ArrayList<Object>(this.recipe.getInputs());
        int offset = gui.getWidth() / 2 - inputs.size() * 9;
        for (Object e : inputs) {
            void var17_17;
            if (e instanceof String) {
                Object e2 = OreDictionary.getOres((String)((String)e)).get(0);
            }
            this.renderItemAtLinePos(gui, offset, i, y, (ItemStack)var17_17);
            ++i;
        }
        super.renderRecipe(gui, mx, my);
    }

    @SideOnly(value=Side.CLIENT)
    public void renderItemAtLinePos(IGuiLexiconEntry gui, int offset, int pos, int yPos, ItemStack stack) {
        if (stack == null || stack.func_77973_b() == null) {
            return;
        }
        if ((stack = stack.func_77946_l()).func_77960_j() == Short.MAX_VALUE) {
            stack.func_77964_b(0);
        }
        int xPos = gui.getLeft() + (pos == -1 ? gui.getWidth() / 2 - 8 : pos * 18) + offset;
        ItemStack stack1 = stack.func_77946_l();
        if (stack1.func_77960_j() == -1) {
            stack1.func_77964_b(0);
        }
        this.renderItem(gui, xPos, yPos, stack1, false);
    }

    @Override
    public List<ItemStack> getDisplayedRecipes() {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        list.add(this.recipe.getOutput(new ItemStack(ModItems.vial)));
        return list;
    }

    @Override
    public void setSecondUnlocalizedName(String name) {
        this.text = name;
    }

    @Override
    public String getSecondUnlocalizedName() {
        return this.text;
    }
}

