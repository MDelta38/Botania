/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.biome.BiomeGenBase
 *  org.lwjgl.opengl.GL11
 */
package com.kentington.thaumichorizons.client.renderer.tile;

import com.kentington.thaumichorizons.common.tiles.TileCloud;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;
import org.lwjgl.opengl.GL11;

public class TileCloudRender
extends TileEntitySpecialRenderer {
    private Minecraft mc = Minecraft.func_71410_x();
    Random random = new Random();
    private int rendererUpdateCount;
    private static final ResourceLocation locationRainPng = new ResourceLocation("thaumichorizons", "textures/environment/rain.png");
    private static final ResourceLocation locationEmberPng = new ResourceLocation("thaumichorizons", "textures/environment/firerain.png");
    private static final ResourceLocation locationSnowPng = new ResourceLocation("textures/environment/snow.png");

    public void func_147500_a(TileEntity tile, double x, double y, double z, float partial) {
        if (((TileCloud)tile).isRaining()) {
            this.renderRainSnowToo((TileCloud)tile, x, y, z, partial);
            ++this.rendererUpdateCount;
        }
    }

    public void renderRainSnowToo(TileCloud tco, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)p_147500_2_ + 0.5f), (float)((float)p_147500_4_ + 1.5f), (float)((float)p_147500_6_ + 0.5f));
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glPopMatrix();
        if (tco.func_145831_w() == null) {
            return;
        }
        float f1 = 1.0f;
        GL11.glAlphaFunc((int)516, (float)0.1f);
        if (f1 > 0.0f) {
            Tessellator tessellator = Tessellator.field_78398_a;
            BiomeGenBase biomegenbase = tco.func_145831_w().func_72807_a(tco.field_145851_c, tco.field_145849_e);
            if (tco.md == 1) {
                this.func_147499_a(locationEmberPng);
            } else if ((double)biomegenbase.func_150564_a(tco.field_145851_c, tco.field_145848_d, tco.field_145849_e) >= 0.15) {
                this.func_147499_a(locationRainPng);
            } else {
                this.func_147499_a(locationSnowPng);
            }
            GL11.glTexParameterf((int)3553, (int)10242, (float)10497.0f);
            GL11.glTexParameterf((int)3553, (int)10243, (float)10497.0f);
            GL11.glDisable((int)2896);
            GL11.glDisable((int)2884);
            GL11.glDisable((int)3042);
            GL11.glDepthMask((boolean)true);
            OpenGlHelper.func_148821_a((int)770, (int)1, (int)1, (int)0);
            float f2 = (float)tco.func_145831_w().func_82737_E() + p_147500_8_ + (float)tco.field_145851_c + (float)(16 * tco.field_145849_e);
            float f3 = -f2 * 0.2f - (float)MathHelper.func_76141_d((float)(-f2 * 0.1f));
            boolean b0 = true;
            double d3 = (double)f2 * 0.025 * (1.0 - (double)(b0 & true) * 2.5);
            tessellator.func_78382_b();
            switch (tco.md) {
                case 1: {
                    tessellator.func_78370_a(255, 255, 255, 255);
                    break;
                }
                case 3: {
                    tessellator.func_78370_a(32, 255, 64, 255);
                    break;
                }
                case 4: {
                    tessellator.func_78370_a(64, 64, 64, 255);
                    break;
                }
                case 5: {
                    tessellator.func_78370_a(255, 64, 32, 255);
                    break;
                }
                case 6: {
                    tessellator.func_78370_a(170, 64, 200, 255);
                    break;
                }
                case 7: {
                    tessellator.func_78370_a(255, 255, 255, 255);
                    break;
                }
                case 8: {
                    tessellator.func_78370_a(160, 255, 160, 255);
                    break;
                }
                case 9: {
                    tessellator.func_78370_a(255, 230, 64, 255);
                    break;
                }
                default: {
                    tessellator.func_78370_a(32, 64, 255, 255);
                }
            }
            double d30 = 0.0;
            double d4 = 0.0;
            double d6 = 1.0;
            double d8 = 0.0;
            double d10 = 0.0;
            double d12 = 1.0;
            double d14 = 1.0;
            double d16 = 1.0;
            double d18 = tco.howManyDown != -1 ? (double)(tco.howManyDown - 1) : (double)(256.0f * f1);
            double d20 = 0.0;
            double d22 = 1.0;
            double d24 = -1.0f + f3;
            double d26 = (double)tco.howManyDown / 4.0 + d24;
            tessellator.func_78374_a(p_147500_2_ + d30, p_147500_4_ - d18, p_147500_6_ + d4, d22, d26);
            tessellator.func_78374_a(p_147500_2_ + d30, p_147500_4_, p_147500_6_ + d4, d22, d24);
            tessellator.func_78374_a(p_147500_2_ + d6, p_147500_4_, p_147500_6_ + d8, d20, d24);
            tessellator.func_78374_a(p_147500_2_ + d6, p_147500_4_ - d18, p_147500_6_ + d8, d20, d26);
            tessellator.func_78374_a(p_147500_2_ + d14, p_147500_4_ - d18, p_147500_6_ + d16, d22, d26);
            tessellator.func_78374_a(p_147500_2_ + d14, p_147500_4_, p_147500_6_ + d16, d22, d24);
            tessellator.func_78374_a(p_147500_2_ + d10, p_147500_4_, p_147500_6_ + d12, d20, d24);
            tessellator.func_78374_a(p_147500_2_ + d10, p_147500_4_ - d18, p_147500_6_ + d12, d20, d26);
            tessellator.func_78374_a(p_147500_2_ + d6, p_147500_4_ - d18, p_147500_6_ + d8, d22, d26);
            tessellator.func_78374_a(p_147500_2_ + d6, p_147500_4_, p_147500_6_ + d8, d22, d24);
            tessellator.func_78374_a(p_147500_2_ + d14, p_147500_4_, p_147500_6_ + d16, d20, d24);
            tessellator.func_78374_a(p_147500_2_ + d14, p_147500_4_ - d18, p_147500_6_ + d16, d20, d26);
            tessellator.func_78374_a(p_147500_2_ + d10, p_147500_4_ - d18, p_147500_6_ + d12, d22, d26);
            tessellator.func_78374_a(p_147500_2_ + d10, p_147500_4_, p_147500_6_ + d12, d22, d24);
            tessellator.func_78374_a(p_147500_2_ + d30, p_147500_4_, p_147500_6_ + d4, d20, d24);
            tessellator.func_78374_a(p_147500_2_ + d30, p_147500_4_ - d18, p_147500_6_ + d4, d20, d26);
            tessellator.func_78381_a();
            GL11.glEnable((int)2896);
            GL11.glEnable((int)3553);
            GL11.glDepthMask((boolean)true);
        }
    }
}

