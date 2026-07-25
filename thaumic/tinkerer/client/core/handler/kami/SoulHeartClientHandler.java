/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.RenderGameOverlayEvent
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Post
 *  org.lwjgl.opengl.GL11
 */
package thaumic.tinkerer.client.core.handler.kami;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

public final class SoulHeartClientHandler {
    private static final ResourceLocation iconsResource = new ResourceLocation("textures/gui/icons.png");
    private static final ResourceLocation heartsResource = new ResourceLocation("ttinkerer:textures/gui/soulHearts.png");
    @SideOnly(value=Side.CLIENT)
    public static int clientPlayerHP = 0;

    @SideOnly(value=Side.CLIENT)
    private static void renderHeart(int x, int y, boolean full) {
        Tessellator tess = Tessellator.field_78398_a;
        float size = 0.0625f;
        float startX = full ? 0.0f : 9.0f * size;
        float endX = full ? 9.0f * size : 1.0f;
        float startY = 0.0f;
        float endY = 9.0f * size;
        tess.func_78382_b();
        tess.func_78374_a((double)x, (double)(y + 9), 0.0, (double)startX, (double)endY);
        tess.func_78374_a((double)(x + (full ? 9 : 7)), (double)(y + 9), 0.0, (double)endX, (double)endY);
        tess.func_78374_a((double)(x + (full ? 9 : 7)), (double)y, 0.0, (double)endX, (double)startY);
        tess.func_78374_a((double)x, (double)y, 0.0, (double)startX, (double)startY);
        tess.func_78381_a();
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void renderHealthBar(RenderGameOverlayEvent event) {
        if (event.type == RenderGameOverlayEvent.ElementType.FOOD && clientPlayerHP > 0) {
            if (event instanceof RenderGameOverlayEvent.Post) {
                Minecraft mc = Minecraft.func_71410_x();
                int x = event.resolution.func_78326_a() / 2 + 10;
                int y = event.resolution.func_78328_b() - 39;
                GL11.glTranslatef((float)0.0f, (float)10.0f, (float)0.0f);
                mc.field_71446_o.func_110577_a(heartsResource);
                int it = 0;
                for (int i = 0; i < clientPlayerHP; ++i) {
                    boolean half;
                    boolean bl = half = i == clientPlayerHP - 1 && clientPlayerHP % 2 != 0;
                    if (!half && i % 2 != 0) continue;
                    SoulHeartClientHandler.renderHeart(x + it * 8, y, !half);
                    ++it;
                }
                mc.field_71446_o.func_110577_a(iconsResource);
            }
            GL11.glTranslatef((float)0.0f, (float)-10.0f, (float)0.0f);
        }
        if (event.type == RenderGameOverlayEvent.ElementType.AIR && event instanceof RenderGameOverlayEvent.Post && clientPlayerHP > 0) {
            GL11.glTranslatef((float)0.0f, (float)10.0f, (float)0.0f);
        }
    }
}

