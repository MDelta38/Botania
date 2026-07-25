/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.util;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderUtil {
    public static void blend(boolean on) {
        if (on) {
            GL11.glPushAttrib((int)16448);
            GL11.glShadeModel((int)7425);
            GL11.glDisable((int)3008);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
        } else {
            GL11.glPopAttrib();
        }
    }

    public static void render2d(boolean on) {
        if (on) {
            GL11.glPushAttrib((int)8192);
            GL11.glDisable((int)2929);
            GL11.glDisable((int)2884);
            GL11.glDisable((int)2896);
        } else {
            GL11.glPopAttrib();
        }
    }
}

