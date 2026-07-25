/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  org.lwjgl.opengl.GL11
 */
package thaumic.tinkerer.client.core.helper;

import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

public final class ClientHelper {
    public static Minecraft minecraft() {
        return Minecraft.func_71410_x();
    }

    public static FontRenderer fontRenderer() {
        return ClientHelper.minecraft().field_71466_p;
    }

    public static EntityClientPlayerMP clientPlayer() {
        return ClientHelper.minecraft().field_71439_g;
    }

    public static void renderTooltip(int x, int y, List<String> tooltipData) {
        int color = 0x505000FF;
        int color2 = -267386864;
        GL11.glDisable((int)32826);
        RenderHelper.func_74518_a();
        GL11.glDisable((int)2896);
        GL11.glDisable((int)2929);
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
            ClientHelper.drawGradientRect(var6 - 3, var7 - 4, z, var6 + var5 + 3, var7 - 3, color2, color2);
            ClientHelper.drawGradientRect(var6 - 3, var7 + var9 + 3, z, var6 + var5 + 3, var7 + var9 + 4, color2, color2);
            ClientHelper.drawGradientRect(var6 - 3, var7 - 3, z, var6 + var5 + 3, var7 + var9 + 3, color2, color2);
            ClientHelper.drawGradientRect(var6 - 4, var7 - 3, z, var6 - 3, var7 + var9 + 3, color2, color2);
            ClientHelper.drawGradientRect(var6 + var5 + 3, var7 - 3, z, var6 + var5 + 4, var7 + var9 + 3, color2, color2);
            int var12 = (color & 0xFFFFFF) >> 1 | color & 0xFF000000;
            ClientHelper.drawGradientRect(var6 - 3, var7 - 3 + 1, z, var6 - 3 + 1, var7 + var9 + 3 - 1, color, var12);
            ClientHelper.drawGradientRect(var6 + var5 + 2, var7 - 3 + 1, z, var6 + var5 + 3, var7 + var9 + 3 - 1, color, var12);
            ClientHelper.drawGradientRect(var6 - 3, var7 - 3, z, var6 + var5 + 3, var7 - 3 + 1, color, color);
            ClientHelper.drawGradientRect(var6 - 3, var7 + var9 + 2, z, var6 + var5 + 3, var7 + var9 + 3, var12, var12);
            for (int var13 = 0; var13 < tooltipData.size(); ++var13) {
                String var14 = tooltipData.get(var13);
                fontRenderer.func_78261_a(var14, var6, var7, -1);
                if (var13 == 0) {
                    var7 += 2;
                }
                var7 += 10;
            }
        }
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
}

