/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.lexicon.page;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.api.lexicon.LexiconPage;
import vazkii.botania.api.lexicon.multiblock.Multiblock;
import vazkii.botania.api.lexicon.multiblock.MultiblockSet;
import vazkii.botania.client.core.handler.MultiblockRenderHandler;
import vazkii.botania.client.core.helper.RenderHelper;

public class PageMultiblock
extends LexiconPage {
    private static final ResourceLocation multiblockOverlay = new ResourceLocation("botania:textures/gui/multiblockOverlay.png");
    GuiButton button;
    MultiblockSet set;
    Multiblock mb;
    int ticksElapsed;

    public PageMultiblock(String unlocalizedName, MultiblockSet set) {
        super(unlocalizedName);
        this.mb = set.getForIndex(0);
        this.set = set;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void renderScreen(IGuiLexiconEntry gui, int mx, int my) {
        TextureManager render = Minecraft.func_71410_x().field_71446_o;
        render.func_110577_a(multiblockOverlay);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDisable((int)3008);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ((GuiScreen)gui).func_73729_b(gui.getLeft(), gui.getTop(), 0, 0, gui.getWidth(), gui.getHeight());
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3008);
        float maxX = 90.0f;
        float maxY = 60.0f;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(gui.getLeft() + gui.getWidth() / 2), (float)(gui.getTop() + 90), (float)(gui.getZLevel() + 100.0f));
        float diag = (float)Math.sqrt(this.mb.getXSize() * this.mb.getXSize() + this.mb.getZSize() * this.mb.getZSize());
        float height = this.mb.getYSize();
        float scaleX = 90.0f / diag;
        float scaleY = 60.0f / height;
        float scale = -Math.min(scaleY, scaleX);
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        GL11.glRotatef((float)-20.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)gui.getElapsedTicks(), (float)0.0f, (float)1.0f, (float)0.0f);
        MultiblockRenderHandler.renderMultiblockOnPage(this.mb);
        GL11.glPopMatrix();
        FontRenderer font = Minecraft.func_71410_x().field_71466_p;
        boolean unicode = font.func_82883_a();
        String s = EnumChatFormatting.BOLD + StatCollector.func_74838_a((String)this.getUnlocalizedName());
        font.func_78264_a(true);
        font.func_78276_b(s, gui.getLeft() + gui.getWidth() / 2 - font.func_78256_a(s) / 2, gui.getTop() + 16, 0);
        font.func_78264_a(unicode);
        GL11.glEnable((int)32826);
        net.minecraft.client.renderer.RenderHelper.func_74520_c();
        int x = gui.getLeft() + 15;
        int y = gui.getTop() + 25;
        RenderItem.getInstance().func_77015_a(font, render, new ItemStack(Blocks.field_150417_aV), x, y);
        net.minecraft.client.renderer.RenderHelper.func_74518_a();
        GL11.glDisable((int)32826);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)200.0f);
        if (mx >= x && mx < x + 16 && my >= y && my < y + 16) {
            ArrayList<String> mats = new ArrayList<String>();
            mats.add(StatCollector.func_74838_a((String)"botaniamisc.materialsRequired"));
            for (ItemStack stack : this.mb.materials) {
                String size = "" + stack.field_77994_a;
                if (size.length() < 2) {
                    size = "0" + size;
                }
                mats.add(" " + EnumChatFormatting.AQUA + size + " " + EnumChatFormatting.GRAY + stack.func_82833_r());
            }
            RenderHelper.renderTooltip(mx, my, mats);
        }
        GL11.glPopMatrix();
    }

    @Override
    public void onOpened(IGuiLexiconEntry gui) {
        this.button = new GuiButton(101, gui.getLeft() + 30, gui.getTop() + gui.getHeight() - 50, gui.getWidth() - 60, 20, this.getButtonStr());
        gui.getButtonList().add(this.button);
    }

    String getButtonStr() {
        return StatCollector.func_74838_a((String)(MultiblockRenderHandler.currentMultiblock == this.set ? "botaniamisc.unvisualize" : "botaniamisc.visualize"));
    }

    @Override
    public void onClosed(IGuiLexiconEntry gui) {
        gui.getButtonList().remove(this.button);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void onActionPerformed(IGuiLexiconEntry gui, GuiButton button) {
        if (button == this.button) {
            if (MultiblockRenderHandler.currentMultiblock == this.set) {
                MultiblockRenderHandler.setMultiblock(null);
            } else {
                MultiblockRenderHandler.setMultiblock(this.set);
            }
            button.field_146126_j = this.getButtonStr();
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void updateScreen() {
        ++this.ticksElapsed;
    }
}

