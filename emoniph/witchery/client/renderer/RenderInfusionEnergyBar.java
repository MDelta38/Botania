/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.util.RenderUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderInfusionEnergyBar {
    int xsize = 8;
    int ysize = 32;
    private static final ResourceLocation GLASS = new ResourceLocation("witchery", "textures/gui/glass.png");
    private static final ResourceLocation BLOCK_TEXTURES = new ResourceLocation("textures/atlas/blocks.png");
    private static final ResourceLocation CREATURES = new ResourceLocation("witchery", "textures/gui/creatures.png");
    final boolean primary;

    public RenderInfusionEnergyBar(boolean primary) {
        this.primary = primary;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void draw(double xpos, double ypos, double value, EntityPlayer player, int powerID) {
        Minecraft mc = Minecraft.func_71410_x();
        mc.func_110434_K().func_110577_a(BLOCK_TEXTURES);
        GL11.glPushMatrix();
        try {
            RenderUtil.blend(true);
            this.drawFluid(xpos, ypos, value, this.primary ? Infusion.Registry.instance().get(powerID).getPowerBarIcon(player, 0) : Blocks.field_150435_aG.func_149691_a(0, 0));
            int iconOffsetX = 0;
            int iconOffsetY = (powerID - 1) * 8;
            if (this.primary) {
                this.drawGlass(xpos, ypos);
                iconOffsetX = 8;
            }
            int width = 8;
            int height = 8;
            int xPosition = MathHelper.func_76128_c((double)xpos);
            int yPosition = MathHelper.func_76128_c((double)ypos) + 33;
            mc.func_110434_K().func_110577_a(CREATURES);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.drawTexturedModalRect(xPosition, yPosition, iconOffsetX, iconOffsetY, width, height);
        }
        finally {
            RenderUtil.blend(false);
            GL11.glPopMatrix();
        }
    }

    public void drawFluid(double xpos, double ypos, double value, IIcon icon) {
        double bottomY = ypos + (double)this.ysize;
        double topY = ypos + (double)this.ysize * (1.0 - value);
        GL11.glScaled((double)0.5, (double)0.5, (double)0.5);
        if (this.primary) {
            while (bottomY - 8.0 > topY) {
                RenderInfusionEnergyBar.drawIconPartial(xpos * 2.0, (bottomY - 8.0) * 2.0, icon, 0.0, 0.0, 16.0, 16.0);
                bottomY -= 8.0;
            }
            RenderInfusionEnergyBar.drawIconPartial(xpos * 2.0, (bottomY - 8.0) * 2.0, icon, 0.0, (topY - bottomY + 8.0) * 2.0, 16.0, 16.0);
        } else {
            int i = 0;
            while ((double)i < value) {
                RenderInfusionEnergyBar.drawIconPartial(xpos * 2.0, (bottomY - (double)(i * 2)) * 2.0 - 2.0, icon, 0.0, 0.0, 16.0, 2.0);
                ++i;
            }
        }
        GL11.glScaled((double)2.0, (double)2.0, (double)2.0);
    }

    public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6) {
        double zLevel = 0.0;
        float f = 0.00390625f;
        float f1 = 0.00390625f;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78374_a((double)(par1 + 0), (double)(par2 + par6), zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + par6) * f1));
        tessellator.func_78374_a((double)(par1 + par5), (double)(par2 + par6), zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + par6) * f1));
        tessellator.func_78374_a((double)(par1 + par5), (double)(par2 + 0), zLevel, (double)((float)(par3 + par5) * f), (double)((float)(par4 + 0) * f1));
        tessellator.func_78374_a((double)(par1 + 0), (double)(par2 + 0), zLevel, (double)((float)(par3 + 0) * f), (double)((float)(par4 + 0) * f1));
        tessellator.func_78381_a();
    }

    public static void drawIconPartial(double x, double y, IIcon icon, double left, double top, double right, double bottom) {
        if (icon == null) {
            return;
        }
        RenderUtil.render2d(true);
        GL11.glColor4d((double)1.0, (double)1.0, (double)1.0, (double)1.0);
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78382_b();
        float u1 = icon.func_94209_e();
        float v1 = icon.func_94206_g();
        float u2 = icon.func_94212_f();
        float v2 = icon.func_94210_h();
        double xoffset1 = left * (double)(u2 - u1) / 16.0;
        double yoffset1 = top * (double)(v2 - v1) / 16.0;
        double xoffset2 = right * (double)(u2 - u1) / 16.0;
        double yoffset2 = bottom * (double)(v2 - v1) / 16.0;
        tess.func_78374_a(x + left, y + top, 0.0, (double)u1 + xoffset1, (double)v1 + yoffset1);
        tess.func_78374_a(x + left, y + bottom, 0.0, (double)u1 + xoffset1, (double)v1 + yoffset2);
        tess.func_78374_a(x + right, y + bottom, 0.0, (double)u1 + xoffset2, (double)v1 + yoffset2);
        tess.func_78374_a(x + right, y + top, 0.0, (double)u1 + xoffset2, (double)v1 + yoffset1);
        tess.func_78381_a();
        RenderUtil.render2d(false);
    }

    public void drawGlass(double xpos, double ypos) {
        Minecraft.func_71410_x().func_110434_K().func_110577_a(GLASS);
        GL11.glBegin((int)7);
        GL11.glTexCoord2d((double)0.0, (double)0.0);
        GL11.glVertex2d((double)xpos, (double)ypos);
        GL11.glTexCoord2d((double)0.0, (double)1.0);
        GL11.glVertex2d((double)xpos, (double)(ypos + (double)this.ysize));
        GL11.glTexCoord2d((double)1.0, (double)1.0);
        GL11.glVertex2d((double)(xpos + (double)this.xsize), (double)(ypos + (double)this.ysize));
        GL11.glTexCoord2d((double)1.0, (double)0.0);
        GL11.glVertex2d((double)(xpos + (double)this.xsize), (double)ypos);
        GL11.glEnd();
    }
}

