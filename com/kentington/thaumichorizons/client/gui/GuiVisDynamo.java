/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.fx.ParticleEngine
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.common.config.Config
 */
package com.kentington.thaumichorizons.client.gui;

import com.kentington.thaumichorizons.common.container.ContainerVisDynamo;
import com.kentington.thaumichorizons.common.tiles.TileVisDynamo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.config.Config;

@SideOnly(value=Side.CLIENT)
public class GuiVisDynamo
extends GuiContainer {
    TileVisDynamo tile;
    int flashX;
    int flashY;
    Color flashColor = null;
    int flashTimer = 0;

    public GuiVisDynamo(EntityPlayer player, TileVisDynamo tileEntity) {
        super((Container)new ContainerVisDynamo(player, tileEntity));
        this.tile = tileEntity;
        this.field_146999_f = 111;
        this.field_147000_g = 104;
    }

    protected void func_146976_a(float par1, int par2, int par3) {
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
        if (this.tile.provideAer) {
            UtilsFX.drawTag((int)11, (int)12, (Aspect)Aspect.AIR, (float)0.0f, (int)0, (double)this.field_73735_i, (int)771, (float)1.0f, (boolean)false);
        } else {
            UtilsFX.drawTag((int)11, (int)12, (Aspect)Aspect.AIR, (float)0.0f, (int)0, (double)this.field_73735_i, (int)771, (float)1.0f, (boolean)true);
        }
        if (this.tile.provideTerra) {
            UtilsFX.drawTag((int)83, (int)11, (Aspect)Aspect.EARTH, (float)0.0f, (int)0, (double)this.field_73735_i, (int)771, (float)1.0f, (boolean)false);
        } else {
            UtilsFX.drawTag((int)83, (int)11, (Aspect)Aspect.EARTH, (float)0.0f, (int)0, (double)this.field_73735_i, (int)771, (float)1.0f, (boolean)true);
        }
        if (this.tile.provideIgnis) {
            UtilsFX.drawTag((int)11, (int)45, (Aspect)Aspect.FIRE, (float)0.0f, (int)0, (double)this.field_73735_i, (int)771, (float)1.0f, (boolean)false);
        } else {
            UtilsFX.drawTag((int)11, (int)45, (Aspect)Aspect.FIRE, (float)0.0f, (int)0, (double)this.field_73735_i, (int)771, (float)1.0f, (boolean)true);
        }
        if (this.tile.provideAqua) {
            UtilsFX.drawTag((int)83, (int)45, (Aspect)Aspect.WATER, (float)0.0f, (int)0, (double)this.field_73735_i, (int)771, (float)1.0f, (boolean)false);
        } else {
            UtilsFX.drawTag((int)83, (int)45, (Aspect)Aspect.WATER, (float)0.0f, (int)0, (double)this.field_73735_i, (int)771, (float)1.0f, (boolean)true);
        }
        if (this.tile.provideOrdo) {
            UtilsFX.drawTag((int)11, (int)78, (Aspect)Aspect.ORDER, (float)0.0f, (int)0, (double)this.field_73735_i, (int)771, (float)1.0f, (boolean)false);
        } else {
            UtilsFX.drawTag((int)11, (int)78, (Aspect)Aspect.ORDER, (float)0.0f, (int)0, (double)this.field_73735_i, (int)771, (float)1.0f, (boolean)true);
        }
        if (this.tile.providePerditio) {
            UtilsFX.drawTag((int)83, (int)78, (Aspect)Aspect.ENTROPY, (float)0.0f, (int)0, (double)this.field_73735_i, (int)771, (float)1.0f, (boolean)false);
        } else {
            UtilsFX.drawTag((int)83, (int)78, (Aspect)Aspect.ENTROPY, (float)0.0f, (int)0, (double)this.field_73735_i, (int)771, (float)1.0f, (boolean)true);
        }
        if (this.flashTimer > 0) {
            --this.flashTimer;
            this.drawFlash();
        }
    }

    protected void func_73864_a(int par1, int par2, int par3) {
        super.func_73864_a(par1, par2, par3);
        int gx = (this.field_146294_l - this.field_146999_f) / 2;
        int gy = (this.field_146295_m - this.field_147000_g) / 2;
        int x = par1 - (gx + 11);
        int y = par2 - (gy + 12);
        if (x >= 0 && y >= 0 && x <= 16 && y <= 16) {
            this.tile.provideAer = !this.tile.provideAer;
            this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 1);
            this.flashTimer = 8;
            this.flashColor = new Color(Aspect.AIR.getColor());
            this.flashX = par1 - gx - 8;
            this.flashY = par2 - gy - 8;
            this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:hhoff", 0.2f, 1.0f + this.field_146297_k.field_71451_h.field_70170_p.field_73012_v.nextFloat() * 0.1f, false);
            return;
        }
        x = par1 - (gx + 83);
        if (x >= 0 && y >= 0 && x <= 16 && y <= 16) {
            this.tile.provideTerra = !this.tile.provideTerra;
            this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 2);
            this.flashTimer = 8;
            this.flashColor = new Color(Aspect.EARTH.getColor());
            this.flashX = par1 - gx - 8;
            this.flashY = par2 - gy - 8;
            this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:hhoff", 0.2f, 1.0f + this.field_146297_k.field_71451_h.field_70170_p.field_73012_v.nextFloat() * 0.1f, false);
            return;
        }
        x = par1 - (gx + 11);
        y = par2 - (gy + 43);
        if (x >= 0 && y >= 0 && x <= 16 && y <= 16) {
            this.tile.provideIgnis = !this.tile.provideIgnis;
            this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 3);
            this.flashTimer = 8;
            this.flashColor = new Color(Aspect.FIRE.getColor());
            this.flashX = par1 - gx - 8;
            this.flashY = par2 - gy - 8;
            this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:hhoff", 0.2f, 1.0f + this.field_146297_k.field_71451_h.field_70170_p.field_73012_v.nextFloat() * 0.1f, false);
            return;
        }
        x = par1 - (gx + 83);
        if (x >= 0 && y >= 0 && x <= 16 && y <= 16) {
            this.tile.provideAqua = !this.tile.provideAqua;
            this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 4);
            this.flashTimer = 8;
            this.flashColor = new Color(Aspect.WATER.getColor());
            this.flashX = par1 - gx - 8;
            this.flashY = par2 - gy - 8;
            this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:hhoff", 0.2f, 1.0f + this.field_146297_k.field_71451_h.field_70170_p.field_73012_v.nextFloat() * 0.1f, false);
            return;
        }
        x = par1 - (gx + 11);
        y = par2 - (gy + 78);
        if (x >= 0 && y >= 0 && x <= 16 && y <= 16) {
            this.tile.provideOrdo = !this.tile.provideOrdo;
            this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 5);
            this.flashTimer = 8;
            this.flashColor = new Color(Aspect.ORDER.getColor());
            this.flashX = par1 - gx - 8;
            this.flashY = par2 - gy - 8;
            this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:hhoff", 0.2f, 1.0f + this.field_146297_k.field_71451_h.field_70170_p.field_73012_v.nextFloat() * 0.1f, false);
            return;
        }
        x = par1 - (gx + 83);
        if (x >= 0 && y >= 0 && x <= 16 && y <= 16) {
            this.tile.providePerditio = !this.tile.providePerditio;
            this.field_146297_k.field_71442_b.func_78756_a(this.field_147002_h.field_75152_c, 6);
            this.flashTimer = 8;
            this.flashColor = new Color(Aspect.ENTROPY.getColor());
            this.flashX = par1 - gx - 8;
            this.flashY = par2 - gy - 8;
            this.field_146297_k.field_71451_h.field_70170_p.func_72980_b(this.field_146297_k.field_71451_h.field_70165_t, this.field_146297_k.field_71451_h.field_70163_u, this.field_146297_k.field_71451_h.field_70161_v, "thaumcraft:hhoff", 0.2f, 1.0f + this.field_146297_k.field_71451_h.field_70170_p.field_73012_v.nextFloat() * 0.1f, false);
            return;
        }
    }

    private void drawFlash() {
        float red = (float)this.flashColor.getRed() / 255.0f;
        float green = (float)this.flashColor.getGreen() / 255.0f;
        float blue = (float)this.flashColor.getBlue() / 255.0f;
        if (Config.colorBlind) {
            red /= 1.8f;
            green /= 1.8f;
            blue /= 1.8f;
        }
        GL11.glPushMatrix();
        UtilsFX.bindTexture((ResourceLocation)ParticleEngine.particleTexture);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslated((double)this.flashX, (double)this.flashY, (double)0.0);
        Tessellator tessellator = Tessellator.field_78398_a;
        int part = this.flashTimer;
        float var8 = 0.5f + (float)part / 8.0f;
        float var9 = var8 + 0.0624375f;
        float var10 = 0.5f;
        float var11 = var10 + 0.0624375f;
        tessellator.func_78382_b();
        tessellator.func_78380_c(240);
        tessellator.func_78369_a(red, green, blue, 1.0f);
        tessellator.func_78374_a(0.0, 16.0, (double)this.field_73735_i, (double)var9, (double)var11);
        tessellator.func_78374_a(16.0, 16.0, (double)this.field_73735_i, (double)var9, (double)var10);
        tessellator.func_78374_a(16.0, 0.0, (double)this.field_73735_i, (double)var8, (double)var10);
        tessellator.func_78374_a(0.0, 0.0, (double)this.field_73735_i, (double)var8, (double)var11);
        tessellator.func_78381_a();
        GL11.glPopMatrix();
    }
}

