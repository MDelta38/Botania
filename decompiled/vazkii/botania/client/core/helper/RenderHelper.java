/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.core.helper;

import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.handler.ClientTickHandler;

public final class RenderHelper {
    public static void renderTooltip(int x, int y, List<String> tooltipData) {
        int color = 0x505000FF;
        int color2 = -267386864;
        RenderHelper.renderTooltip(x, y, tooltipData, color, color2);
    }

    public static void renderTooltipOrange(int x, int y, List<String> tooltipData) {
        int color = 1352689152;
        int color2 = -266464768;
        RenderHelper.renderTooltip(x, y, tooltipData, color, color2);
    }

    public static void renderTooltipGreen(int x, int y, List<String> tooltipData) {
        int color = 0x5000A000;
        int color2 = -268427776;
        RenderHelper.renderTooltip(x, y, tooltipData, color, color2);
    }

    public static void renderTooltip(int x, int y, List<String> tooltipData, int color, int color2) {
        boolean lighting = GL11.glGetBoolean((int)2896);
        if (lighting) {
            net.minecraft.client.renderer.RenderHelper.func_74518_a();
        }
        if (!tooltipData.isEmpty()) {
            int var7;
            int var6;
            int var5 = 0;
            FontRenderer fontRenderer = Minecraft.func_71410_x().field_71466_p;
            for (var6 = 0; var6 < tooltipData.size(); ++var6) {
                var7 = fontRenderer.func_78256_a(tooltipData.get(var6));
                if (var7 <= var5) continue;
                var5 = var7;
            }
            var6 = x + 12;
            var7 = y - 12;
            int var9 = 8;
            if (tooltipData.size() > 1) {
                var9 += 2 + (tooltipData.size() - 1) * 10;
            }
            float z = 300.0f;
            RenderHelper.drawGradientRect(var6 - 3, var7 - 4, z, var6 + var5 + 3, var7 - 3, color2, color2);
            RenderHelper.drawGradientRect(var6 - 3, var7 + var9 + 3, z, var6 + var5 + 3, var7 + var9 + 4, color2, color2);
            RenderHelper.drawGradientRect(var6 - 3, var7 - 3, z, var6 + var5 + 3, var7 + var9 + 3, color2, color2);
            RenderHelper.drawGradientRect(var6 - 4, var7 - 3, z, var6 - 3, var7 + var9 + 3, color2, color2);
            RenderHelper.drawGradientRect(var6 + var5 + 3, var7 - 3, z, var6 + var5 + 4, var7 + var9 + 3, color2, color2);
            int var12 = (color & 0xFFFFFF) >> 1 | color & 0xFF000000;
            RenderHelper.drawGradientRect(var6 - 3, var7 - 3 + 1, z, var6 - 3 + 1, var7 + var9 + 3 - 1, color, var12);
            RenderHelper.drawGradientRect(var6 + var5 + 2, var7 - 3 + 1, z, var6 + var5 + 3, var7 + var9 + 3 - 1, color, var12);
            RenderHelper.drawGradientRect(var6 - 3, var7 - 3, z, var6 + var5 + 3, var7 - 3 + 1, color, color);
            RenderHelper.drawGradientRect(var6 - 3, var7 + var9 + 2, z, var6 + var5 + 3, var7 + var9 + 3, var12, var12);
            GL11.glDisable((int)2929);
            for (int var13 = 0; var13 < tooltipData.size(); ++var13) {
                String var14 = tooltipData.get(var13);
                fontRenderer.func_78261_a(var14, var6, var7, -1);
                if (var13 == 0) {
                    var7 += 2;
                }
                var7 += 10;
            }
            GL11.glEnable((int)2929);
        }
        if (!lighting) {
            net.minecraft.client.renderer.RenderHelper.func_74518_a();
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public static void drawGradientRect(int par1, int par2, float z, int par3, int par4, int par5, int par6) {
        float var7 = (float)(par5 >> 24 & 0xFF) / 255.0f;
        float var8 = (float)(par5 >> 16 & 0xFF) / 255.0f;
        float var9 = (float)(par5 >> 8 & 0xFF) / 255.0f;
        float var10 = (float)(par5 & 0xFF) / 255.0f;
        float var11 = (float)(par6 >> 24 & 0xFF) / 255.0f;
        float var12 = (float)(par6 >> 16 & 0xFF) / 255.0f;
        float var13 = (float)(par6 >> 8 & 0xFF) / 255.0f;
        float var14 = (float)(par6 & 0xFF) / 255.0f;
        GL11.glDisable((int)3553);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3008);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glShadeModel((int)7425);
        Tessellator var15 = Tessellator.field_78398_a;
        var15.func_78382_b();
        var15.func_78369_a(var8, var9, var10, var7);
        var15.func_78377_a((double)par3, (double)par2, (double)z);
        var15.func_78377_a((double)par1, (double)par2, (double)z);
        var15.func_78369_a(var12, var13, var14, var11);
        var15.func_78377_a((double)par1, (double)par4, (double)z);
        var15.func_78377_a((double)par3, (double)par4, (double)z);
        var15.func_78381_a();
        GL11.glShadeModel((int)7424);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3008);
        GL11.glEnable((int)3553);
    }

    public static void drawTexturedModalRect(int par1, int par2, float z, int par3, int par4, int par5, int par6) {
        RenderHelper.drawTexturedModalRect(par1, par2, z, par3, par4, par5, par6, 0.00390625f, 0.00390625f);
    }

    public static void drawTexturedModalRect(int par1, int par2, float z, int par3, int par4, int par5, int par6, float f, float f1) {
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78382_b();
        tessellator.func_78374_a((double)(par1 + 0), (double)(par2 + par6), (double)z, (double)((float)(par3 + 0) * f), (double)((float)(par4 + par6) * f1));
        tessellator.func_78374_a((double)(par1 + par5), (double)(par2 + par6), (double)z, (double)((float)(par3 + par5) * f), (double)((float)(par4 + par6) * f1));
        tessellator.func_78374_a((double)(par1 + par5), (double)(par2 + 0), (double)z, (double)((float)(par3 + par5) * f), (double)((float)(par4 + 0) * f1));
        tessellator.func_78374_a((double)(par1 + 0), (double)(par2 + 0), (double)z, (double)((float)(par3 + 0) * f), (double)((float)(par4 + 0) * f1));
        tessellator.func_78381_a();
    }

    public static void renderStar(int color, float xScale, float yScale, float zScale, long seed) {
        Tessellator tessellator = Tessellator.field_78398_a;
        int ticks = ClientTickHandler.ticksInGame % 200;
        if (ticks >= 100) {
            ticks = 200 - ticks - 1;
        }
        float f1 = (float)ticks / 200.0f;
        float f2 = 0.0f;
        if (f1 > 0.7f) {
            f2 = (f1 - 0.7f) / 0.2f;
        }
        Random random = new Random(seed);
        GL11.glPushMatrix();
        GL11.glDisable((int)3553);
        GL11.glShadeModel((int)7425);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        GL11.glDisable((int)3008);
        GL11.glEnable((int)2884);
        GL11.glDepthMask((boolean)false);
        GL11.glScalef((float)xScale, (float)yScale, (float)zScale);
        int i = 0;
        while ((float)i < (f1 + f1 * f1) / 2.0f * 90.0f + 30.0f) {
            GL11.glRotatef((float)(random.nextFloat() * 360.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)(random.nextFloat() * 360.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(random.nextFloat() * 360.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)(random.nextFloat() * 360.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)(random.nextFloat() * 360.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(random.nextFloat() * 360.0f + f1 * 90.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            tessellator.func_78371_b(6);
            float f3 = random.nextFloat() * 20.0f + 5.0f + f2 * 10.0f;
            float f4 = random.nextFloat() * 2.0f + 1.0f + f2 * 2.0f;
            tessellator.func_78384_a(color, (int)(255.0f * (1.0f - f2)));
            tessellator.func_78377_a(0.0, 0.0, 0.0);
            tessellator.func_78369_a(0.0f, 0.0f, 0.0f, 0.0f);
            tessellator.func_78377_a(-0.866 * (double)f4, (double)f3, (double)(-0.5f * f4));
            tessellator.func_78377_a(0.866 * (double)f4, (double)f3, (double)(-0.5f * f4));
            tessellator.func_78377_a(0.0, (double)f3, (double)(1.0f * f4));
            tessellator.func_78377_a(-0.866 * (double)f4, (double)f3, (double)(-0.5f * f4));
            tessellator.func_78381_a();
            ++i;
        }
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)2884);
        GL11.glDisable((int)3042);
        GL11.glShadeModel((int)7424);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)3008);
        GL11.glPopMatrix();
    }

    public static void renderProgressPie(int x, int y, float progress, ItemStack stack) {
        Minecraft mc = Minecraft.func_71410_x();
        RenderItem.getInstance().func_82406_b(mc.field_71466_p, mc.field_71446_o, stack, x, y);
        GL11.glClear((int)256);
        GL11.glEnable((int)2960);
        GL11.glColorMask((boolean)false, (boolean)false, (boolean)false, (boolean)false);
        GL11.glDepthMask((boolean)false);
        GL11.glStencilFunc((int)512, (int)1, (int)255);
        GL11.glStencilOp((int)7681, (int)7680, (int)7680);
        GL11.glStencilMask((int)255);
        RenderItem.getInstance().func_82406_b(mc.field_71466_p, mc.field_71446_o, stack, x, y);
        mc.field_71446_o.func_110577_a(new ResourceLocation("botania:textures/gui/manaHud.png"));
        int r = 10;
        int centerX = x + 8;
        int centerY = y + 8;
        int degs = (int)(360.0f * progress);
        float a = 0.5f + 0.2f * ((float)Math.cos((double)((float)ClientTickHandler.ticksInGame + ClientTickHandler.partialTicks) / 10.0) * 0.5f + 0.5f);
        GL11.glDisable((int)2896);
        GL11.glDisable((int)3553);
        GL11.glShadeModel((int)7425);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
        GL11.glDepthMask((boolean)true);
        GL11.glStencilMask((int)0);
        GL11.glStencilFunc((int)514, (int)1, (int)255);
        GL11.glBegin((int)6);
        GL11.glColor4f((float)0.0f, (float)0.5f, (float)0.5f, (float)a);
        GL11.glVertex2i((int)centerX, (int)centerY);
        GL11.glColor4f((float)0.0f, (float)1.0f, (float)0.5f, (float)a);
        for (int i = degs; i > 0; --i) {
            double rad = (double)((float)(i - 90) / 180.0f) * Math.PI;
            GL11.glVertex2d((double)((double)centerX + Math.cos(rad) * (double)r), (double)((double)centerY + Math.sin(rad) * (double)r));
        }
        GL11.glVertex2i((int)centerX, (int)centerY);
        GL11.glEnd();
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3553);
        GL11.glShadeModel((int)7424);
        GL11.glDisable((int)2960);
    }

    public static String getKeyDisplayString(String keyName) {
        KeyBinding[] keys;
        String key = null;
        for (KeyBinding otherKey : keys = Minecraft.func_71410_x().field_71474_y.field_74324_K) {
            if (!otherKey.func_151464_g().equals(keyName)) continue;
            key = Keyboard.getKeyName((int)otherKey.func_151463_i());
            break;
        }
        return key;
    }
}

