/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.util.ResourceLocation
 */
package flaxbeard.thaumicexploration.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class GuiButtonSelector
extends GuiButton {
    private static final ResourceLocation gui = new ResourceLocation("thaumicexploration:textures/gui/checkBox.png");
    int myID;

    public GuiButtonSelector(int par1, int par2, int par3, boolean enable, int id) {
        super(par1, par2, par3, 11, 11, "");
        this.field_146124_l = enable;
        this.myID = id;
    }

    public void func_146112_a(Minecraft par1Minecraft, int par2, int par3) {
        par1Minecraft.field_71446_o.func_110577_a(gui);
        int y = 0;
        if (this.field_146124_l) {
            y = 11;
        }
        this.func_73729_b(this.field_146128_h, this.field_146129_i, 0, y, 11, 11);
    }
}

