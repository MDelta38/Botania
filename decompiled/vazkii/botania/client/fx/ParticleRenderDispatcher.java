/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.profiler.Profiler
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.profiler.Profiler;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.fx.FXSparkle;
import vazkii.botania.client.fx.FXWisp;

public final class ParticleRenderDispatcher {
    public static int wispFxCount = 0;
    public static int depthIgnoringWispFxCount = 0;
    public static int sparkleFxCount = 0;
    public static int fakeSparkleFxCount = 0;
    public static int lightningCount = 0;

    public static void dispatch() {
        Tessellator tessellator = Tessellator.field_78398_a;
        Profiler profiler = Minecraft.func_71410_x().field_71424_I;
        GL11.glPushAttrib((int)2896);
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        GL11.glDisable((int)2896);
        profiler.func_76320_a("sparkle");
        FXSparkle.dispatchQueuedRenders(tessellator);
        profiler.func_76318_c("wisp");
        FXWisp.dispatchQueuedRenders(tessellator);
        profiler.func_76319_b();
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)true);
        GL11.glPopAttrib();
    }
}

