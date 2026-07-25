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

public class GuiButtonEnchanterLevel
extends GuiButton {
    private static final ResourceLocation gui = new ResourceLocation("ttinkerer:textures/gui/enchanter.png");
    boolean plus;

    public GuiButtonEnchanterLevel(int par1, int par2, int par3, boolean plus) {
        super(par1, par2, par3, 7, 7, "");
        this.plus = plus;
    }

    public void func_146112_a(Minecraft par1Minecraft, int par2, int par3) {
        if (!this.field_146124_l) {
            return;
        }
        int x = 218 + (this.plus ? 7 : 0);
        par1Minecraft.field_71446_o.func_110577_a(gui);
        this.func_73729_b(this.field_146128_h, this.field_146129_i, x, 0, 7, 7);
    }
}

