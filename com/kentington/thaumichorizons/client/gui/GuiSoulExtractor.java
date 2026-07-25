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

import com.kentington.thaumichorizons.common.container.ContainerSoulExtractor;
import com.kentington.thaumichorizons.common.tiles.TileSoulExtractor;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;

public class GuiSoulExtractor
extends GuiContainer {
    TileSoulExtractor tile;

    public GuiSoulExtractor(InventoryPlayer player, TileSoulExtractor tile) {
        super((Container)new ContainerSoulExtractor(player, tile));
        this.tile = tile;
        this.field_146999_f = 175;
        this.field_147000_g = 165;
    }

    protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        UtilsFX.bindTexture((ResourceLocation)new ResourceLocation("thaumichorizons", "textures/gui/guisieve.png"));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int var5 = (this.field_146294_l - this.field_146999_f) / 2;
        int var6 = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(var5, var6, 0, 0, this.field_146999_f, this.field_147000_g);
        if (this.tile.isExtracting() || this.tile.ticksLeft > 0) {
            int i1 = this.tile.getTimeRemainingScaled(39);
            this.func_73729_b(var5 + 91, var6 + 58 - i1, 176, 166 - i1, 35, i1);
        }
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }
}

