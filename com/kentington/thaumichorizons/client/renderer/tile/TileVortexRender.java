/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Vec3
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.QuadHelper
 *  thaumcraft.client.lib.UtilsFX
 */
package com.kentington.thaumichorizons.client.renderer.tile;

import com.kentington.thaumichorizons.common.tiles.TileVortex;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.client.lib.QuadHelper;
import thaumcraft.client.lib.UtilsFX;

@SideOnly(value=Side.CLIENT)
public class TileVortexRender
extends TileEntitySpecialRenderer {
    public static final ResourceLocation nodetex = new ResourceLocation("thaumcraft", "textures/misc/nodes.png");
    public static final ResourceLocation vortextex = new ResourceLocation("thaumcraft", "textures/misc/vortex.png");

    public void func_147500_a(TileEntity tile, double x, double y, double z, float partialTicks) {
        if (!(tile instanceof TileVortex)) {
            return;
        }
        float size = 10.0f;
        TileVortex node = (TileVortex)tile;
        double viewDistance = 64.0;
        EntityLivingBase viewer = Minecraft.func_71410_x().field_71451_h;
        boolean condition = true;
        boolean depthIgnore = false;
        TileVortexRender.renderNode(viewer, viewDistance, condition, depthIgnore, size, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, partialTicks, ((TileVortex)tile).aspects, node.count, node.collapsing, node.beams, node.createdDimension, node.cheat);
    }

    public static void renderNode(EntityLivingBase viewer, double viewDistance, boolean visible, boolean depthIgnore, float size, int x, int y, int z, float partialTicks, AspectList aspects, int timeOpen, boolean collapsing, int beams, boolean plane, boolean cheat) {
        long nt = System.nanoTime();
        int frames = 32;
        if (aspects.size() > 0 && visible) {
            double distance = viewer.func_70011_f((double)x + 0.5, (double)y + 0.5, (double)z + 0.5);
            if (distance > viewDistance) {
                return;
            }
            float alpha = (float)((viewDistance - distance) / viewDistance);
            GL11.glPushMatrix();
            GL11.glAlphaFunc((int)516, (float)0.003921569f);
            GL11.glDepthMask((boolean)false);
            if (depthIgnore) {
                GL11.glDisable((int)2929);
            }
            GL11.glDisable((int)2884);
            long time = nt / 5000000L;
            float bscale = 0.25f;
            GL11.glPushMatrix();
            float rad = 6.283186f;
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
            int i = (int)((nt / 40000000L + (long)x) % (long)frames);
            int count = 0;
            float scale = 0.0f;
            float angle = 0.0f;
            float average = 0.0f;
            UtilsFX.bindTexture((ResourceLocation)nodetex);
            for (Aspect aspect : aspects.getAspects()) {
                if (aspect == null) {
                    aspect = Aspect.WATER;
                }
                if (aspect.getBlend() == 771) {
                    alpha = (float)((double)alpha * 1.5);
                }
                average += (float)aspects.getAmount(aspect);
                GL11.glPushMatrix();
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)aspect.getBlend());
                scale = MathHelper.func_76126_a((float)((float)viewer.field_70173_aa / (14.0f - (float)count))) * bscale + bscale * 2.0f;
                scale = 0.4f;
                scale *= size;
                angle = (float)(time % (long)(5000 + 500 * count)) / (5000.0f + (float)(500 * count)) * rad;
                if (beams < 6 || timeOpen < 50) {
                    UtilsFX.renderFacingStrip((double)((double)x + 0.5), (double)((double)y + 0.5), (double)((double)z + 0.5), (float)angle, (float)scale, (float)(alpha / Math.max(1.0f, (float)aspects.size() / 2.0f)), (int)frames, (int)0, (int)i, (float)partialTicks, (int)aspect.getColor());
                } else {
                    UtilsFX.renderFacingStrip((double)((double)x + 0.5), (double)((double)y + 0.5), (double)((double)z + 0.5), (float)angle, (float)(scale / 3.0f), (float)(alpha / Math.max(1.0f, (float)aspects.size() / 2.0f)), (int)frames, (int)0, (int)i, (float)partialTicks, (int)aspect.getColor());
                }
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
                ++count;
                if (aspect.getBlend() != 771) continue;
                alpha = (float)((double)alpha / 1.5);
            }
            average /= (float)aspects.size();
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glColor4f((float)1.0f, (float)0.0f, (float)1.0f, (float)alpha);
            float corescale = 1.0f;
            if (timeOpen < 50 && !collapsing) {
                corescale = (float)timeOpen / 50.0f;
            } else if (collapsing) {
                corescale = 1.0f - (float)timeOpen / 25.0f;
            }
            if (!(cheat || beams >= 6 && timeOpen >= 50)) {
                UtilsFX.bindTexture((ResourceLocation)vortextex);
                TileVortexRender.renderVortex((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, angle * 20.0f * corescale / (float)(1 + 2 * beams), scale / 5.0f * corescale, 0.8f, partialTicks, 0xFFFFFF);
            } else {
                UtilsFX.bindTexture((ResourceLocation)nodetex);
                UtilsFX.renderFacingStrip((double)((double)x + 0.5), (double)((double)y + 0.5), (double)((double)z + 0.5), (float)angle, (float)(scale * 0.75f), (float)alpha, (int)frames, (int)2, (int)i, (float)partialTicks, (int)0xFFFFFF);
            }
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
            if (plane) {
                for (Aspect aspect : aspects.getAspects()) {
                    if (aspect == null) {
                        aspect = Aspect.WATER;
                    }
                    if (aspect.getBlend() == 771) {
                        alpha = (float)((double)alpha * 1.5);
                    }
                    average += (float)aspects.getAmount(aspect);
                    GL11.glPushMatrix();
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)771);
                    scale = MathHelper.func_76126_a((float)((float)viewer.field_70173_aa / (14.0f - (float)count))) * bscale + bscale * 2.0f;
                    scale = 0.4f;
                    scale *= size;
                    angle = (float)(time % (long)(5000 + 500 * count)) / (5000.0f + (float)(500 * count)) * rad;
                    UtilsFX.renderFacingStrip((double)((double)x + 0.5), (double)((double)y + 0.5), (double)((double)z + 0.5), (float)angle, (float)0.5f, (float)(alpha / Math.max(1.0f, (float)aspects.size() / 2.0f)), (int)frames, (int)0, (int)i, (float)partialTicks, (int)aspect.getColor());
                    GL11.glDisable((int)3042);
                    GL11.glPopMatrix();
                    ++count;
                    if (aspect.getBlend() != 771) continue;
                    alpha = (float)((double)alpha / 1.5);
                }
            }
            GL11.glPopMatrix();
            GL11.glEnable((int)2884);
            if (depthIgnore) {
                GL11.glEnable((int)2929);
            }
            GL11.glDepthMask((boolean)true);
            GL11.glAlphaFunc((int)516, (float)0.1f);
            GL11.glPopMatrix();
        }
    }

    static void renderVortex(double px, double py, double pz, float angle, float scale, float alpha, float partialTicks, int color) {
        Tessellator tessellator = Tessellator.field_78398_a;
        float arX = ActiveRenderInfo.field_74588_d;
        float arZ = ActiveRenderInfo.field_74586_f;
        float arYZ = ActiveRenderInfo.field_74587_g;
        float arXY = ActiveRenderInfo.field_74596_h;
        float arXZ = ActiveRenderInfo.field_74589_e;
        EntityPlayer player = (EntityPlayer)Minecraft.func_71410_x().field_71451_h;
        double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * (double)partialTicks;
        double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * (double)partialTicks;
        double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * (double)partialTicks;
        GL11.glTranslated((double)(-iPX), (double)(-iPY), (double)(-iPZ));
        tessellator.func_78382_b();
        tessellator.func_78380_c(220);
        tessellator.func_78384_a(color, (int)(alpha * 255.0f));
        Vec3 v1 = Vec3.func_72443_a((double)(-arX * scale - arYZ * scale), (double)(-arXZ * scale), (double)(-arZ * scale - arXY * scale));
        Vec3 v2 = Vec3.func_72443_a((double)(-arX * scale + arYZ * scale), (double)(arXZ * scale), (double)(-arZ * scale + arXY * scale));
        Vec3 v3 = Vec3.func_72443_a((double)(arX * scale + arYZ * scale), (double)(arXZ * scale), (double)(arZ * scale + arXY * scale));
        Vec3 v4 = Vec3.func_72443_a((double)(arX * scale - arYZ * scale), (double)(-arXZ * scale), (double)(arZ * scale - arXY * scale));
        if (angle != 0.0f) {
            Vec3 pvec = Vec3.func_72443_a((double)iPX, (double)iPY, (double)iPZ);
            Vec3 tvec = Vec3.func_72443_a((double)px, (double)py, (double)pz);
            Vec3 qvec = pvec.func_72444_a(tvec).func_72432_b();
            QuadHelper.setAxis((Vec3)qvec, (double)angle).rotate(v1);
            QuadHelper.setAxis((Vec3)qvec, (double)angle).rotate(v2);
            QuadHelper.setAxis((Vec3)qvec, (double)angle).rotate(v3);
            QuadHelper.setAxis((Vec3)qvec, (double)angle).rotate(v4);
        }
        float f2 = 0.0f;
        float f3 = 1.0f;
        float f4 = 0.0f;
        float f5 = 1.0f;
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        tessellator.func_78374_a(px + v1.field_72450_a, py + v1.field_72448_b, pz + v1.field_72449_c, (double)f2, (double)f5);
        tessellator.func_78374_a(px + v2.field_72450_a, py + v2.field_72448_b, pz + v2.field_72449_c, (double)f3, (double)f5);
        tessellator.func_78374_a(px + v3.field_72450_a, py + v3.field_72448_b, pz + v3.field_72449_c, (double)f3, (double)f4);
        tessellator.func_78374_a(px + v4.field_72450_a, py + v4.field_72448_b, pz + v4.field_72449_c, (double)f2, (double)f4);
        tessellator.func_78381_a();
    }
}

