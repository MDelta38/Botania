/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 */
package com.kentington.thaumichorizons.client.gui;

import com.kentington.thaumichorizons.common.container.ContainerSoulforge;
import com.kentington.thaumichorizons.common.tiles.TileSoulforge;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;

@SideOnly(value=Side.CLIENT)
public class GuiSoulforge
extends GuiContainer {
    TileSoulforge tile;

    public GuiSoulforge(EntityPlayer player, TileSoulforge tile) {
        super((Container)new ContainerSoulforge(player, tile));
        this.field_146999_f = 0;
        this.field_147000_g = 0;
    }

    protected void func_146976_a(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        UtilsFX.bindTexture((ResourceLocation)new ResourceLocation("thaumichorizons", "textures/gui/guidynamo.png"));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        int var5 = (this.field_146294_l - this.field_146999_f) / 2;
        int var6 = (this.field_146295_m - this.field_147000_g) / 2;
        this.func_73729_b(var5, var6, 0, 0, this.field_146999_f, this.field_147000_g);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    protected void func_146979_b(int par1, int par2) {
    }
}

