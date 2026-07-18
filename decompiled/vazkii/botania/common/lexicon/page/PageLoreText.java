/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.common.lexicon.page;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.internal.IGuiLexiconEntry;
import vazkii.botania.common.lexicon.page.PageText;

public class PageLoreText
extends PageText {
    private static final ResourceLocation paperOverlay = new ResourceLocation("botania:textures/gui/paper.png");

    public PageLoreText(String unlocalizedName) {
        super(unlocalizedName);
    }

    @Override
    public void renderScreen(IGuiLexiconEntry gui, int mx, int my) {
        Minecraft.func_71410_x().field_71446_o.func_110577_a(paperOverlay);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ((GuiScreen)gui).func_73729_b(gui.getLeft(), gui.getTop(), 0, 0, gui.getWidth(), gui.getHeight());
        GL11.glDisable((int)3042);
        super.renderScreen(gui, mx, my);
    }
}

