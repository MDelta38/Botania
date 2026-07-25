/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.util.MathHelper
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Post
 *  org.lwjgl.opengl.GL11
 */
package thaumic.tinkerer.client.core.handler.kami;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;
import thaumic.tinkerer.client.core.handler.ClientTickHandler;

public final class ToolModeHUDHandler {
    private static String currentTooltip;
    private static int tooltipDisplayTicks;

    public static void setTooltip(String tooltip) {
        if (!tooltip.equals(currentTooltip)) {
            currentTooltip = tooltip;
            tooltipDisplayTicks = 400;
        }
    }

    @SideOnly(value=Side.CLIENT)
    public static void clientTick() {
        if (tooltipDisplayTicks > 0) {
            --tooltipDisplayTicks;
        }
    }

    @SubscribeEvent
    @SideOnly(value=Side.CLIENT)
    public void drawDislocationFocusHUD(RenderGameOverlayEvent.Post event) {
        if (event.type == RenderGameOverlayEvent.ElementType.ALL && tooltipDisplayTicks > 0 && !MathHelper.func_76139_a((String)currentTooltip)) {
            Minecraft mc = Minecraft.func_71410_x();
            ScaledResolution var5 = new ScaledResolution(mc, mc.field_71443_c, mc.field_71440_d);
            int var6 = var5.func_78326_a();
            int var7 = var5.func_78328_b();
            FontRenderer var8 = mc.field_71466_p;
            int tooltipStartX = (var6 - var8.func_78256_a(currentTooltip)) / 2;
            int tooltipStartY = var7 - 72;
            int opacity = (int)((float)tooltipDisplayTicks * 256.0f / 10.0f);
            if (opacity > 160) {
                opacity = 160;
            }
            if (opacity > 0) {
                GL11.glPushMatrix();
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
                int color = Color.getHSBColor((float)Math.cos((double)ClientTickHandler.elapsedTicks / 250.0), 0.6f, 1.0f).getRGB();
                var8.func_78261_a(currentTooltip, tooltipStartX, tooltipStartY, color + (opacity << 24));
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
            }
        }
    }
}

