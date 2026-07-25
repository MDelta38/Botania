/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.util.ResourceLocation
 */
package thaumic.tinkerer.client.gui.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class GuiButtonAT
extends GuiButton {
    private static final ResourceLocation gui = new ResourceLocation("ttinkerer:textures/gui/animationTablet.png");
    public boolean buttonEnabled = false;

    public GuiButtonAT(int par1, int par2, int par3, boolean buttonEnabled) {
        super(par1, par2, par3, 13, 13, "");
        this.buttonEnabled = buttonEnabled;
    }

    public void func_146112_a(Minecraft par1Minecraft, int par2, int par3) {
        if (this.field_146124_l) {
            par1Minecraft.field_71446_o.func_110577_a(gui);
            int y = this.buttonEnabled ? 13 : 0;
            this.func_73729_b(this.field_146128_h, this.field_146129_i, 176, y, this.field_146120_f, this.field_146121_g);
        }
    }
}

