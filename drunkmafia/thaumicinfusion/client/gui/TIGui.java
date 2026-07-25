/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiScreen
 */
package drunkmafia.thaumicinfusion.client.gui;

import drunkmafia.thaumicinfusion.client.gui.IGUI;
import java.util.List;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;

public abstract class TIGui
extends GuiScreen
implements IGUI {
    protected int guiLeft;
    protected int guiTop;
    protected int xSize;
    protected int ySize;

    public void func_73866_w_() {
        super.func_73866_w_();
        this.guiLeft = (this.field_146294_l - this.xSize) / 2;
        this.guiTop = (this.field_146295_m - this.ySize) / 2;
    }

    public void drawHoveringText(List list, int x, int y, FontRenderer font) {
        super.drawHoveringText(list, x, y, font);
    }

    public FontRenderer getFontRenderer() {
        return this.field_146289_q;
    }

    @Override
    public int getGuiLeft() {
        return this.guiLeft;
    }

    @Override
    public int getGuiTop() {
        return this.guiTop;
    }

    @Override
    public int getXSize() {
        return this.xSize;
    }

    @Override
    public int getYSize() {
        return this.ySize;
    }
}

