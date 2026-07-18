/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.lexicon.page;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.lexicon.LexiconRecipeMappings;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lexicon.page.PageRecipe;

public class PageTerrasteel
extends PageRecipe {
    private static final ResourceLocation terrasteelOverlay = new ResourceLocation("botania:textures/gui/terrasteelOverlay.png");

    public PageTerrasteel(String unlocalizedName) {
        super(unlocalizedName);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void renderRecipe(IGuiLexiconEntry gui, int mx, int my) {
        Block block1 = ModBlocks.livingrock;
        Block block2 = Blocks.field_150368_y;
        Block block3 = ModBlocks.terraPlate;
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-10.0f);
        this.renderItem(gui, gui.getLeft() + gui.getWidth() / 2 - 8, gui.getTop() + 103, new ItemStack(block1), false);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)5.0f);
        this.renderItem(gui, gui.getLeft() + gui.getWidth() / 2 - 8 + 7, gui.getTop() + 106, new ItemStack(block2), false);
        this.renderItem(gui, gui.getLeft() + gui.getWidth() / 2 - 8 - 6, gui.getTop() + 106, new ItemStack(block2), false);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)5.0f);
        this.renderItem(gui, gui.getLeft() + gui.getWidth() / 2 - 8, gui.getTop() + 110, new ItemStack(block1), false);
        this.renderItem(gui, gui.getLeft() + gui.getWidth() / 2 - 8 + 14, gui.getTop() + 110, new ItemStack(block1), false);
        this.renderItem(gui, gui.getLeft() + gui.getWidth() / 2 - 8 - 13, gui.getTop() + 110, new ItemStack(block1), false);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)5.0f);
        this.renderItem(gui, gui.getLeft() + gui.getWidth() / 2 - 8 - 6, gui.getTop() + 114, new ItemStack(block2), false);
        this.renderItem(gui, gui.getLeft() + gui.getWidth() / 2 - 8 + 7, gui.getTop() + 114, new ItemStack(block2), false);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)5.0f);
        this.renderItem(gui, gui.getLeft() + gui.getWidth() / 2 - 8 + 1, gui.getTop() + 117, new ItemStack(block1), false);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)5.0f);
        this.renderItem(gui, gui.getLeft() + gui.getWidth() / 2 - 8, gui.getTop() + 102, new ItemStack(block3), false);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-10.0f);
        this.renderItem(gui, gui.getLeft() + gui.getWidth() / 2 - 8, gui.getTop() + 30, new ItemStack(ModItems.manaResource, 1, 4), false);
        this.renderItem(gui, gui.getLeft() + gui.getWidth() / 2 - 8, gui.getTop() + 80, new ItemStack(ModItems.manaResource, 1, 0), false);
        this.renderItem(gui, gui.getLeft() + gui.getWidth() / 2 - 8 - 20, gui.getTop() + 86, new ItemStack(ModItems.manaResource, 1, 1), false);
        this.renderItem(gui, gui.getLeft() + gui.getWidth() / 2 - 8 + 19, gui.getTop() + 86, new ItemStack(ModItems.manaResource, 1, 2), false);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(terrasteelOverlay);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ((GuiScreen)gui).func_73729_b(gui.getLeft(), gui.getTop(), 0, 0, gui.getWidth(), gui.getHeight());
        GL11.glDisable((int)3042);
    }

    @Override
    public void onPageAdded(LexiconEntry entry, int index) {
        LexiconRecipeMappings.map(new ItemStack(ModItems.manaResource, 1, 4), entry, index);
    }

    @Override
    public List<ItemStack> getDisplayedRecipes() {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        list.add(new ItemStack(ModItems.manaResource, 1, 4));
        return list;
    }
}

