/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiButton
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Locale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
class GuiButtonUrl
extends GuiButton {
    public final String nextPage;

    public GuiButtonUrl(int commandID, int x, int y, String page, String label) {
        super(commandID, x, y, 10, 10, label);
        this.nextPage = !page.isEmpty() ? page.toLowerCase(Locale.ROOT).replace(" ", "") : label.toLowerCase(Locale.ROOT).replace(" ", "");
    }

    public void func_146112_a(Minecraft mc, int mouseX, int mouseY) {
        if (this.field_146125_m) {
            String FORMAT_CHAR = "\u00a7";
            String FORMAT_CLEAR = "\u00a7r";
            boolean flag = mouseX >= this.field_146128_h && mouseY >= this.field_146129_i && mouseX < this.field_146128_h + this.field_146120_f && mouseY < this.field_146129_i + this.field_146121_g;
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            mc.field_71466_p.func_78276_b("\u00a7n" + this.field_146126_j + "\u00a7r", this.field_146128_h, this.field_146129_i, flag ? 0xFF0000 : 255);
        }
    }
}

