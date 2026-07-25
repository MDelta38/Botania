/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraftforge.client.event.RenderGameOverlayEvent
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.lib;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.lib.PlayerNotifications;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.config.Config;

public class REHNotifyHandler {
    public void handleNotifications(Minecraft mc, long time, RenderGameOverlayEvent event) {
        if (PlayerNotifications.getListAndUpdate(time).size() > 0) {
            this.renderNotifyHUD(event.resolution.func_78327_c(), event.resolution.func_78324_d(), time);
        }
        if (PlayerNotifications.getAspectListAndUpdate(time).size() > 0) {
            this.renderAspectHUD(event.resolution.func_78327_c(), event.resolution.func_78324_d(), time);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void renderNotifyHUD(double sw, double sh, long time) {
        Minecraft mc = Minecraft.func_71410_x();
        GL11.glPushMatrix();
        GL11.glClear((int)256);
        GL11.glMatrixMode((int)5889);
        GL11.glLoadIdentity();
        GL11.glOrtho((double)0.0, (double)sw, (double)sh, (double)0.0, (double)1000.0, (double)3000.0);
        GL11.glMatrixMode((int)5888);
        GL11.glLoadIdentity();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-2000.0f);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glDisable((int)3008);
        GL11.glEnable((int)2881);
        GL11.glHint((int)3155, (int)4354);
        int k = (int)sw;
        int l = (int)sh;
        ArrayList<PlayerNotifications.Notification> notifications = PlayerNotifications.getListAndUpdate(time);
        float shift = -8.0f;
        for (int entry = 0; entry < notifications.size() && entry < Config.notificationMax; ++entry) {
            PlayerNotifications.Notification li = notifications.get(entry);
            String text = li.text;
            int size = mc.field_71466_p.func_78256_a(text) / 2;
            int alpha = 255;
            if (entry == notifications.size() - 1 && li.created > time) {
                alpha = 255 - (int)((float)(li.created - time) / (float)(Config.notificationDelay / 4) * 240.0f);
            }
            if (li.expire < time + (long)Config.notificationDelay) {
                alpha = (int)(255.0f - (float)(time + (long)Config.notificationDelay - li.expire) / (float)Config.notificationDelay * 240.0f);
                shift = -8.0f * ((float)alpha / 255.0f);
            }
            int color = (alpha / 2 << 24) + 0xFF0000 + 65280 + 255;
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glTranslatef((float)(k - size - 10), (float)((float)(l - entry * 8) + shift), (float)0.0f);
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
            mc.field_71456_v.func_73731_b(mc.field_71466_p, text, -4, -8, color);
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            if (li.image != null) {
                GL11.glPushMatrix();
                GL11.glTranslatef((float)(k - 9), (float)((float)(l - entry * 8) + shift - 6.0f), (float)0.0f);
                GL11.glScalef((float)0.03125f, (float)0.03125f, (float)0.03125f);
                mc.field_71446_o.func_110577_a(li.image);
                Tessellator tessellator = Tessellator.field_78398_a;
                Color c = new Color(li.color);
                GL11.glColor4f((float)((float)c.getRed() / 255.0f), (float)((float)c.getGreen() / 255.0f), (float)((float)c.getBlue() / 255.0f), (float)((float)alpha / 511.0f));
                UtilsFX.drawTexturedQuad(0, 0, 0, 0, 256, 256, -90.0);
                GL11.glPopMatrix();
            }
            if (entry != notifications.size() - 1 || li.created <= time) continue;
            float scale = (float)(li.created - time) / (float)(Config.notificationDelay / 4);
            alpha = 255 - (int)(scale * 240.0f);
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)(k - 5) - 8.0f * scale - (1.0f - scale) * (1.0f - scale) * (1.0f - scale) * (float)size * 3.0f), (float)((float)(l - entry * 8) + shift - 2.0f - 8.0f * scale), (float)0.0f);
            GL11.glScalef((float)scale, (float)scale, (float)scale);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(0.5f - (float)alpha / 511.0f));
            UtilsFX.bindTexture(ParticleEngine.particleTexture);
            int px = 16 * ((mc.field_71439_g.field_70173_aa + entry * 3) % 16);
            UtilsFX.drawTexturedQuad(0, 0, px, 80, 16, 16, -90 - notifications.size());
            GL11.glPopMatrix();
        }
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2881);
        GL11.glEnable((int)3008);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }

    @SideOnly(value=Side.CLIENT)
    public void renderAspectHUD(double sw, double sh, long time) {
        Minecraft mc = Minecraft.func_71410_x();
        GL11.glPushMatrix();
        GL11.glClear((int)256);
        GL11.glMatrixMode((int)5889);
        GL11.glLoadIdentity();
        GL11.glOrtho((double)0.0, (double)sw, (double)sh, (double)0.0, (double)1000.0, (double)3000.0);
        GL11.glMatrixMode((int)5888);
        GL11.glLoadIdentity();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-2000.0f);
        GL11.glDisable((int)2929);
        GL11.glDepthMask((boolean)false);
        GL11.glDisable((int)3008);
        GL11.glEnable((int)2881);
        GL11.glHint((int)3155, (int)4354);
        int k = (int)sw;
        int l = (int)sh;
        float mainAlpha = 0.0f;
        ArrayList<PlayerNotifications.AspectNotification> notifications = PlayerNotifications.getAspectListAndUpdate(time);
        float shift = -8.0f;
        for (int entry = 0; entry < notifications.size(); ++entry) {
            PlayerNotifications.AspectNotification li = notifications.get(entry);
            if (li.created > time) continue;
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            if (li.aspect.getImage() == null) continue;
            GL11.glPushMatrix();
            int startX = (int)(sw * (double)li.startX);
            int startY = (int)(sh * (double)li.startY);
            int endX = k;
            int endY = -8;
            int bezierX = (int)((float)k * (0.25f + li.startX));
            int bezierY = (int)((float)l * li.startY);
            double t = (double)(time - li.created) / (double)(li.expire - li.created);
            double x = (1.0 - t) * (1.0 - t) * (double)startX + 2.0 * (1.0 - t) * t * (double)bezierX + t * t * (double)endX;
            double y = (1.0 - t) * (1.0 - t) * (double)startY + 2.0 * (1.0 - t) * t * (double)bezierY + t * t * (double)endY;
            float alpha = 1.0f;
            if (t < (double)0.3f) {
                alpha = (float)(t / (double)0.3f);
            } else if (t > (double)0.66f) {
                alpha = (float)(1.0 - (t - (double)0.66f) / (double)0.34f);
            }
            if (alpha > mainAlpha) {
                mainAlpha = alpha;
            }
            GL11.glTranslated((double)x, (double)y, (double)0.0);
            GL11.glScaled((double)(0.075f * alpha), (double)(0.075 * (double)alpha), (double)(0.075 * (double)alpha));
            mc.field_71446_o.func_110577_a(li.aspect.getImage());
            Color c = new Color(li.aspect.getColor());
            GL11.glColor4f((float)((float)c.getRed() / 255.0f), (float)((float)c.getGreen() / 255.0f), (float)((float)c.getBlue() / 255.0f), (float)(alpha * 0.66f));
            UtilsFX.drawTexturedQuad(0, 0, 0, 0, 256, 256, -90.0);
            GL11.glPopMatrix();
        }
        if (mainAlpha > 0.0f) {
            try {
                GL11.glPushMatrix();
                UtilsFX.bindTexture("textures/items/thaumonomicon.png");
                GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)mainAlpha);
                GL11.glTranslated((double)(k - 16), (double)0.0, (double)0.0);
                GL11.glScaled((double)0.0625, (double)0.0625, (double)0.0625);
                UtilsFX.drawTexturedQuad(0, 0, 0, 0, 256, 256, -90.0);
                GL11.glPopMatrix();
            }
            catch (Exception e) {
                // empty catch block
            }
        }
        GL11.glDepthMask((boolean)true);
        GL11.glEnable((int)2929);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)2881);
        GL11.glEnable((int)3008);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }
}

