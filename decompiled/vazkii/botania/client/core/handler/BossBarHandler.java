/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.ARBShaderObjects
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.core.handler;

import java.awt.Rectangle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.boss.IBotaniaBoss;
import vazkii.botania.api.boss.IBotaniaBossWithShader;
import vazkii.botania.api.internal.ShaderCallback;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.common.core.helper.MathHelper;

public final class BossBarHandler {
    public static final ResourceLocation defaultBossBar = new ResourceLocation("botania:textures/gui/bossBar.png");
    static IBotaniaBoss currentBoss;
    private static final BarCallback barUniformCallback;

    public static void setCurrentBoss(IBotaniaBoss status) {
        currentBoss = status;
    }

    public static void render(ScaledResolution res) {
        if (currentBoss == null) {
            return;
        }
        Minecraft mc = Minecraft.func_71410_x();
        Rectangle bgRect = currentBoss.getBossBarTextureRect();
        Rectangle fgRect = currentBoss.getBossBarHPTextureRect();
        String name = currentBoss.func_145748_c_().func_150254_d();
        int c = res.func_78326_a() / 2;
        int x = c - bgRect.width / 2;
        int y = 20;
        int xf = x + (bgRect.width - fgRect.width) / 2;
        int yf = y + (bgRect.height - fgRect.height) / 2;
        int fw = (int)((double)fgRect.width * (double)(currentBoss.func_110143_aJ() / currentBoss.func_110138_aP()));
        int tx = c - mc.field_71466_p.func_78256_a(name) / 2;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        currentBoss.bossBarRenderCallback(res, x, y);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        mc.field_71446_o.func_110577_a(currentBoss.getBossBarTexture());
        BossBarHandler.drawBar(x, y, bgRect.x, bgRect.y, bgRect.width, bgRect.height, true);
        BossBarHandler.drawBar(xf, yf, fgRect.x, fgRect.y, fw, fgRect.height, false);
        mc.field_71466_p.func_78261_a(name, tx, y - 10, 10617228);
        GL11.glEnable((int)3042);
        Entity e = (Entity)currentBoss;
        EntityClientPlayerMP p = mc.field_71439_g;
        if (e.field_70128_L || !p.field_70170_p.field_72996_f.contains(e) || MathHelper.pointDistanceSpace(e.field_70165_t, e.field_70163_u, e.field_70161_v, p.field_70165_t, p.field_70163_u, p.field_70161_v) > 32.0f) {
            currentBoss = null;
        }
    }

    public static void drawBar(int x, int y, int u, int v, int w, int h, boolean bg) {
        boolean useShader = currentBoss instanceof IBotaniaBossWithShader;
        if (useShader) {
            IBotaniaBossWithShader shader = (IBotaniaBossWithShader)currentBoss;
            int program = shader.getBossBarShaderProgram(bg);
            ShaderCallback callback = program == 0 ? null : shader.getBossBarShaderCallback(bg, program);
            barUniformCallback.set(u, v, callback);
            ShaderHelper.useShader(program, barUniformCallback);
        }
        RenderHelper.drawTexturedModalRect(x, y, 0.0f, u, v, w, h);
        if (useShader) {
            ShaderHelper.releaseShader();
        }
    }

    static {
        barUniformCallback = new BarCallback();
    }

    static class BarCallback
    extends ShaderCallback {
        int x;
        int y;
        ShaderCallback callback;

        BarCallback() {
        }

        @Override
        public void call(int shader) {
            int startXUniform = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"startX");
            int startYUniform = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"startY");
            ARBShaderObjects.glUniform1iARB((int)startXUniform, (int)this.x);
            ARBShaderObjects.glUniform1iARB((int)startYUniform, (int)this.y);
            if (this.callback != null) {
                this.callback.call(shader);
            }
        }

        void set(int x, int y, ShaderCallback callback) {
            this.x = x;
            this.y = y;
            this.callback = callback;
        }
    }
}

