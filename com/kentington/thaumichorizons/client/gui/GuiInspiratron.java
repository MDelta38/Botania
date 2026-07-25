/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 */
package com.kentington.thaumichorizons.client.gui;

import com.kentington.thaumichorizons.common.container.ContainerInspiratron;
import com.kentington.thaumichorizons.common.tiles.TileInspiratron;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;

public class GuiInspiratron
extends GuiContainer {
    TileInspiratron tile;

    public GuiInspiratron(InventoryPlayer player, TileInspiratron tile) {
        super((Container)new ContainerInspiratron(player, tile));
        this.tile = tile;
        this.field_146999_f = 175;
        this.field_147000_g = 219;
    }

    protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        UtilsFX.bindTexture((ResourceLocation)new ResourceLocation("thaumichorizons", "textures/gui/guiinspiratron.png"));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int var5 = (this.field_146294_l - this.field_146999_f) / 2;
        int var6 = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(var5, var6, 0, 0, this.field_146999_f, this.field_147000_g);
        int i1 = this.tile.getTimeRemainingScaled(28);
        this.func_73729_b(var5 + 66, var6 + 102, 176, 158 - i1, 44, i1);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }
}

