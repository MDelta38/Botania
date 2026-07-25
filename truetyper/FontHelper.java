/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.util.vector.Matrix4f
 */
package truetyper;

import java.nio.FloatBuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import truetyper.Formatter;
import truetyper.TrueTypeFont;

public class FontHelper {
    private static String formatEscape = "\u00a7";

    public static void drawString(String s, float x, float y, TrueTypeFont font, float scaleX, float scaleY, int format, float ... rgba) {
        Minecraft mc = Minecraft.func_71410_x();
        ScaledResolution sr = new ScaledResolution(Minecraft.func_71410_x(), mc.field_71443_c, mc.field_71440_d);
        if (mc.field_71474_y.field_74319_N) {
            return;
        }
        int amt = 1;
        if (sr.func_78325_e() == 1) {
            amt = 2;
        }
        FloatBuffer matrixData = BufferUtils.createFloatBuffer((int)16);
        GL11.glGetFloat((int)2982, (FloatBuffer)matrixData);
        Matrix4f matrix = new Matrix4f();
        matrix.load(matrixData);
        FontHelper.set2DMode();
        y = (float)mc.field_71440_d - y * (float)sr.func_78325_e() - font.getLineHeight() / (float)amt;
        GL11.glEnable((int)3042);
        if (s.contains(formatEscape)) {
            String[] pars = s.split(formatEscape);
            float totalOffset = 0.0f;
            for (int i = 0; i < pars.length; ++i) {
                String par = pars[i];
                float[] c = rgba;
                if (i > 0) {
                    c = Formatter.getFormatted(par.charAt(0));
                    par = par.substring(1, par.length());
                }
                font.drawString(x * (float)sr.func_78325_e() + totalOffset, y - matrix.m31 * (float)sr.func_78325_e(), par, scaleX / (float)amt, scaleY / (float)amt, format, c);
                totalOffset += font.getWidth(par);
            }
        } else {
            font.drawString(x * (float)sr.func_78325_e(), y - matrix.m31 * (float)sr.func_78325_e(), s, scaleX / (float)amt, scaleY / (float)amt, format, rgba);
        }
        GL11.glDisable((int)3042);
        FontHelper.set3DMode();
    }

    private static void set2DMode() {
        Minecraft mc = Minecraft.func_71410_x();
        GL11.glMatrixMode((int)5889);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        GL11.glOrtho((double)0.0, (double)mc.field_71443_c, (double)0.0, (double)mc.field_71440_d, (double)-1.0, (double)1.0);
        GL11.glMatrixMode((int)5888);
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
    }

    private static void set3DMode() {
        GL11.glMatrixMode((int)5889);
        GL11.glPopMatrix();
        GL11.glMatrixMode((int)5888);
        GL11.glPopMatrix();
    }
}

