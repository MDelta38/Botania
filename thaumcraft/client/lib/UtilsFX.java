/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.particle.EffectRenderer
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Timer
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.lib;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.ReflectionHelper;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Timer;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXScorch;
import thaumcraft.client.fx.particles.FXSparkle;
import thaumcraft.client.lib.QuadHelper;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;

public class UtilsFX {
    public static final String[] colorNames = new String[]{"White", "Orange", "Magenta", "Light Blue", "Yellow", "Lime", "Pink", "Gray", "Light Gray", "Cyan", "Purple", "Blue", "Brown", "Green", "Red", "Black"};
    public static final String[] colorCodes = new String[]{"\u00a7f", "\u00a76", "\u00a7d", "\u00a79", "\u00a7e", "\u00a7a", "\u00a7d", "\u00a78", "\u00a77", "\u00a7b", "\u00a75", "\u00a79", "\u00a74", "\u00a72", "\u00a7c", "\u00a78"};
    public static final int[] colors = new int[]{0xF0F0F0, 15435844, 12801229, 6719955, 14602026, 4312372, 14188952, 0x434343, 0xA0A0A0, 2651799, 8073150, 2437522, 5320730, 3887386, 11743532, 0x1E1B1B};
    public static int[] connectedTextureRefByID = new int[]{0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 4, 4, 5, 5, 4, 4, 5, 5, 17, 17, 22, 26, 17, 17, 22, 26, 16, 16, 20, 20, 16, 16, 28, 28, 21, 21, 46, 42, 21, 21, 43, 38, 4, 4, 5, 5, 4, 4, 5, 5, 9, 9, 30, 12, 9, 9, 30, 12, 16, 16, 20, 20, 16, 16, 28, 28, 25, 25, 45, 37, 25, 25, 40, 32, 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 0, 0, 6, 6, 0, 0, 6, 6, 3, 3, 19, 15, 3, 3, 19, 15, 1, 1, 18, 18, 1, 1, 13, 13, 2, 2, 23, 31, 2, 2, 27, 14, 4, 4, 5, 5, 4, 4, 5, 5, 17, 17, 22, 26, 17, 17, 22, 26, 7, 7, 24, 24, 7, 7, 10, 10, 29, 29, 44, 41, 29, 29, 39, 33, 4, 4, 5, 5, 4, 4, 5, 5, 9, 9, 30, 12, 9, 9, 30, 12, 7, 7, 24, 24, 7, 7, 10, 10, 8, 8, 36, 35, 8, 8, 34, 11};
    public static float[] lightBrightnessTable = null;
    private static Map textureSizeCache = new HashMap();
    static Map<String, ResourceLocation> boundTextures = new HashMap<String, ResourceLocation>();
    static DecimalFormat myFormatter = new DecimalFormat("#######.##");

    public static float getBrightnessFromLight(int light) {
        if (lightBrightnessTable == null) {
            lightBrightnessTable = new float[16];
            float f = 0.0f;
            for (int i = 0; i <= 15; ++i) {
                float f1 = 1.0f - (float)i / 15.0f;
                UtilsFX.lightBrightnessTable[i] = (1.0f - f1) / (f1 * 3.0f + 1.0f) * (1.0f - f) + f;
            }
        }
        return lightBrightnessTable[light];
    }

    public static void infusedStoneSparkle(World world, int x, int y, int z, int md) {
        if (!world.field_72995_K) {
            return;
        }
        int color = 0;
        switch (md) {
            case 1: {
                color = 1;
                break;
            }
            case 2: {
                color = 4;
                break;
            }
            case 3: {
                color = 2;
                break;
            }
            case 4: {
                color = 3;
                break;
            }
            case 5: {
                color = 6;
                break;
            }
            case 6: {
                color = 5;
            }
        }
        for (int a = 0; a < Thaumcraft.proxy.particleCount(3); ++a) {
            FXSparkle fx = new FXSparkle(world, (float)x + world.field_73012_v.nextFloat(), (float)y + world.field_73012_v.nextFloat(), (float)z + world.field_73012_v.nextFloat(), 1.75f, color == -1 ? world.field_73012_v.nextInt(5) : color, 3 + world.field_73012_v.nextInt(3));
            fx.setGravity(0.1f);
            ParticleEngine.instance.addEffect(world, fx);
        }
    }

    public static void shootFire(World world, EntityPlayer p, boolean offset, int range, boolean lance) {
        Vec3 vec3d = p.func_70676_i((float)range);
        double px = p.field_70165_t - (double)(MathHelper.func_76134_b((float)(p.field_70177_z / 180.0f * 3.141593f)) * 0.1f);
        double py = p.field_70163_u - (double)0.08f;
        double pz = p.field_70161_v - (double)(MathHelper.func_76126_a((float)(p.field_70177_z / 180.0f * 3.141593f)) * 0.1f);
        if (p.func_145782_y() != FMLClientHandler.instance().getClient().field_71439_g.func_145782_y()) {
            py = p.field_70121_D.field_72338_b + (double)(p.field_70131_O / 2.0f) + 0.25;
        }
        for (int q = 0; q < 3; ++q) {
            FXScorch ef = new FXScorch(p.field_70170_p, px, py, pz, vec3d, range, lance);
            ef.field_70165_t += vec3d.field_72450_a * (double)0.3f;
            ef.field_70163_u += vec3d.field_72448_b * (double)0.3f;
            ef.field_70161_v += vec3d.field_72449_c * (double)0.3f;
            ef.field_70169_q = ef.field_70165_t;
            ef.field_70167_r = ef.field_70163_u;
            ef.field_70166_s = ef.field_70161_v;
            ef.field_70165_t += vec3d.field_72450_a * (double)0.3f;
            ef.field_70163_u += vec3d.field_72448_b * (double)0.3f;
            ef.field_70161_v += vec3d.field_72449_c * (double)0.3f;
            ParticleEngine.instance.addEffect(world, ef);
        }
    }

    public static void renderFacingQuad(double px, double py, double pz, float angle, float scale, float alpha, int frames, int cframe, float partialTicks, int color) {
        if (Minecraft.func_71410_x().field_71451_h instanceof EntityPlayer) {
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
                QuadHelper.setAxis(qvec, angle).rotate(v1);
                QuadHelper.setAxis(qvec, angle).rotate(v2);
                QuadHelper.setAxis(qvec, angle).rotate(v3);
                QuadHelper.setAxis(qvec, angle).rotate(v4);
            }
            float f2 = (float)cframe / (float)frames;
            float f3 = (float)(cframe + 1) / (float)frames;
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

    public static void renderFacingStrip(double px, double py, double pz, float angle, float scale, float alpha, int frames, int strip, int frame, float partialTicks, int color) {
        if (Minecraft.func_71410_x().field_71451_h instanceof EntityPlayer) {
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
                QuadHelper.setAxis(qvec, angle).rotate(v1);
                QuadHelper.setAxis(qvec, angle).rotate(v2);
                QuadHelper.setAxis(qvec, angle).rotate(v3);
                QuadHelper.setAxis(qvec, angle).rotate(v4);
            }
            float f2 = (float)frame / (float)frames;
            float f3 = (float)(frame + 1) / (float)frames;
            float f4 = (float)strip / (float)frames;
            float f5 = ((float)strip + 1.0f) / (float)frames;
            tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
            tessellator.func_78374_a(px + v1.field_72450_a, py + v1.field_72448_b, pz + v1.field_72449_c, (double)f3, (double)f5);
            tessellator.func_78374_a(px + v2.field_72450_a, py + v2.field_72448_b, pz + v2.field_72449_c, (double)f3, (double)f4);
            tessellator.func_78374_a(px + v3.field_72450_a, py + v3.field_72448_b, pz + v3.field_72449_c, (double)f2, (double)f4);
            tessellator.func_78374_a(px + v4.field_72450_a, py + v4.field_72448_b, pz + v4.field_72449_c, (double)f2, (double)f5);
            tessellator.func_78381_a();
        }
    }

    public static void renderAnimatedQuad(float scale, float alpha, int frames, int cframe, float partialTicks, int color) {
        if (Minecraft.func_71410_x().field_71451_h instanceof EntityPlayer) {
            Tessellator tessellator = Tessellator.field_78398_a;
            tessellator.func_78382_b();
            tessellator.func_78380_c(220);
            tessellator.func_78384_a(color, (int)(alpha * 255.0f));
            float f2 = (float)cframe / (float)frames;
            float f3 = (float)(cframe + 1) / (float)frames;
            float f4 = 0.0f;
            float f5 = 1.0f;
            tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
            tessellator.func_78374_a(-0.5 * (double)scale, 0.5 * (double)scale, 0.0, (double)f2, (double)f5);
            tessellator.func_78374_a(0.5 * (double)scale, 0.5 * (double)scale, 0.0, (double)f3, (double)f5);
            tessellator.func_78374_a(0.5 * (double)scale, -0.5 * (double)scale, 0.0, (double)f3, (double)f4);
            tessellator.func_78374_a(-0.5 * (double)scale, -0.5 * (double)scale, 0.0, (double)f2, (double)f4);
            tessellator.func_78381_a();
        }
    }

    public static void renderAnimatedQuadStrip(float scale, float alpha, int frames, int strip, int cframe, float partialTicks, int color) {
        if (Minecraft.func_71410_x().field_71451_h instanceof EntityPlayer) {
            Tessellator tessellator = Tessellator.field_78398_a;
            tessellator.func_78382_b();
            tessellator.func_78380_c(220);
            tessellator.func_78384_a(color, (int)(alpha * 255.0f));
            float f2 = (float)cframe / (float)frames;
            float f3 = (float)(cframe + 1) / (float)frames;
            float f4 = (float)strip / (float)frames;
            float f5 = (float)(strip + 1) / (float)frames;
            tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
            tessellator.func_78374_a(-0.5 * (double)scale, 0.5 * (double)scale, 0.0, (double)f2, (double)f5);
            tessellator.func_78374_a(0.5 * (double)scale, 0.5 * (double)scale, 0.0, (double)f3, (double)f5);
            tessellator.func_78374_a(0.5 * (double)scale, -0.5 * (double)scale, 0.0, (double)f3, (double)f4);
            tessellator.func_78374_a(-0.5 * (double)scale, -0.5 * (double)scale, 0.0, (double)f2, (double)f4);
            tessellator.func_78381_a();
        }
    }

    public static Vec3 perpendicular(Vec3 v) {
        if (v.field_72449_c == 0.0) {
            return UtilsFX.zCrossProduct(v);
        }
        return UtilsFX.xCrossProduct(v);
    }

    public static Vec3 xCrossProduct(Vec3 v) {
        double d = v.field_72449_c;
        double d1 = -v.field_72448_b;
        v.field_72450_a = 0.0;
        v.field_72448_b = d;
        v.field_72449_c = d1;
        return v;
    }

    public static Vec3 zCrossProduct(Vec3 v) {
        double d = v.field_72448_b;
        double d1 = -v.field_72450_a;
        v.field_72450_a = d;
        v.field_72448_b = d1;
        v.field_72449_c = 0.0;
        return v;
    }

    public static void drawTexturedQuad(int par1, int par2, int par3, int par4, int par5, int par6, double zLevel) {
        float var7 = 0.00390625f;
        float var8 = 0.00390625f;
        Tessellator var9 = Tessellator.field_78398_a;
        var9.func_78382_b();
        var9.func_78374_a((double)(par1 + 0), (double)(par2 + par6), zLevel, (double)((float)(par3 + 0) * var7), (double)((float)(par4 + par6) * var8));
        var9.func_78374_a((double)(par1 + par5), (double)(par2 + par6), zLevel, (double)((float)(par3 + par5) * var7), (double)((float)(par4 + par6) * var8));
        var9.func_78374_a((double)(par1 + par5), (double)(par2 + 0), zLevel, (double)((float)(par3 + par5) * var7), (double)((float)(par4 + 0) * var8));
        var9.func_78374_a((double)(par1 + 0), (double)(par2 + 0), zLevel, (double)((float)(par3 + 0) * var7), (double)((float)(par4 + 0) * var8));
        var9.func_78381_a();
    }

    public static void drawTexturedQuadFull(int par1, int par2, double zLevel) {
        Tessellator var9 = Tessellator.field_78398_a;
        var9.func_78382_b();
        var9.func_78374_a((double)(par1 + 0), (double)(par2 + 16), zLevel, 0.0, 1.0);
        var9.func_78374_a((double)(par1 + 16), (double)(par2 + 16), zLevel, 1.0, 1.0);
        var9.func_78374_a((double)(par1 + 16), (double)(par2 + 0), zLevel, 1.0, 0.0);
        var9.func_78374_a((double)(par1 + 0), (double)(par2 + 0), zLevel, 0.0, 0.0);
        var9.func_78381_a();
    }

    public static void renderQuad(String texture) {
        UtilsFX.renderQuad(texture, 1, 0.66f);
    }

    public static void renderQuad(String texture, int blend, float trans) {
        UtilsFX.renderQuad(texture, blend, trans, 1.0f, 1.0f, 1.0f);
    }

    public static void renderQuad(String texture, int blend, float trans, float r, float g, float b) {
        UtilsFX.bindTexture(texture);
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glEnable((int)32826);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)blend);
        GL11.glColor4f((float)r, (float)g, (float)b, (float)trans);
        tessellator.func_78382_b();
        tessellator.func_78369_a(r, g, b, trans);
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        tessellator.func_78374_a(0.0, 1.0, 0.0, 0.0, 1.0);
        tessellator.func_78374_a(1.0, 1.0, 0.0, 1.0, 1.0);
        tessellator.func_78374_a(1.0, 0.0, 0.0, 1.0, 0.0);
        tessellator.func_78374_a(0.0, 0.0, 0.0, 0.0, 0.0);
        tessellator.func_78381_a();
        GL11.glDisable((int)3042);
        GL11.glDisable((int)32826);
    }

    public static void renderQuadCenteredFromTexture(String texture, float scale, float red, float green, float blue, int brightness, int blend, float opacity) {
        UtilsFX.bindTexture(texture);
        UtilsFX.renderQuadCenteredFromTexture(scale, red, green, blue, brightness, blend, opacity);
    }

    public static void renderQuadCenteredFromTexture(ResourceLocation texture, float scale, float red, float green, float blue, int brightness, int blend, float opacity) {
        Minecraft.func_71410_x().field_71446_o.func_110577_a(texture);
        UtilsFX.renderQuadCenteredFromTexture(scale, red, green, blue, brightness, blend, opacity);
    }

    public static void renderQuadCenteredFromTexture(float scale, float red, float green, float blue, int brightness, int blend, float opacity) {
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)blend);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)opacity);
        tessellator.func_78382_b();
        if (brightness > 0) {
            tessellator.func_78380_c(brightness);
        }
        tessellator.func_78369_a(red, green, blue, opacity);
        tessellator.func_78374_a(-0.5, 0.5, 0.0, 0.0, 1.0);
        tessellator.func_78374_a(0.5, 0.5, 0.0, 1.0, 1.0);
        tessellator.func_78374_a(0.5, -0.5, 0.0, 1.0, 0.0);
        tessellator.func_78374_a(-0.5, -0.5, 0.0, 0.0, 0.0);
        tessellator.func_78381_a();
        GL11.glDisable((int)3042);
    }

    public static void renderQuadFromTexture(String texture, int tileSize, int icon, float scale, float red, float green, float blue, int brightness, int blend, float opacity) {
        UtilsFX.bindTexture(texture);
        int size = UtilsFX.getTextureSize(texture, tileSize);
        float size16 = size * tileSize;
        float float_sizeMinus0_01 = (float)size - 0.01f;
        float float_texNudge = 1.0f / ((float)size * (float)size * 2.0f);
        float float_reciprocal = 1.0f / (float)size;
        Tessellator tessellator = Tessellator.field_78398_a;
        int i = icon;
        float f = ((float)(i % tileSize * size) + 0.0f) / size16;
        float f1 = ((float)(i % tileSize * size) + float_sizeMinus0_01) / size16;
        float f2 = ((float)(i / tileSize * size) + 0.0f) / size16;
        float f3 = ((float)(i / tileSize * size) + float_sizeMinus0_01) / size16;
        float f5 = 0.0f;
        float f6 = 0.3f;
        GL11.glEnable((int)32826);
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)blend);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)opacity);
        tessellator.func_78382_b();
        tessellator.func_78380_c(brightness);
        tessellator.func_78369_a(red, green, blue, opacity);
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        tessellator.func_78374_a(0.0, 1.0, 0.0, (double)f1, (double)f2);
        tessellator.func_78374_a(1.0, 1.0, 0.0, (double)f, (double)f2);
        tessellator.func_78374_a(1.0, 0.0, 0.0, (double)f, (double)f3);
        tessellator.func_78374_a(0.0, 0.0, 0.0, (double)f1, (double)f3);
        tessellator.func_78381_a();
        GL11.glDisable((int)3042);
        GL11.glDisable((int)32826);
    }

    public static void renderQuadFromIcon(boolean isBlock, IIcon icon, float scale, float red, float green, float blue, int brightness, int blend, float opacity) {
        if (isBlock) {
            Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
        } else {
            Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
        }
        Tessellator tessellator = Tessellator.field_78398_a;
        float f1 = icon.func_94212_f();
        float f2 = icon.func_94206_g();
        float f3 = icon.func_94209_e();
        float f4 = icon.func_94210_h();
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)blend);
        GL11.glColor4f((float)red, (float)green, (float)blue, (float)opacity);
        tessellator.func_78382_b();
        if (brightness > -1) {
            tessellator.func_78380_c(brightness);
        }
        tessellator.func_78369_a(red, green, blue, opacity);
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        tessellator.func_78374_a(0.0, 0.0, 0.0, (double)f1, (double)f4);
        tessellator.func_78374_a(1.0, 0.0, 0.0, (double)f3, (double)f4);
        tessellator.func_78374_a(1.0, 1.0, 0.0, (double)f3, (double)f2);
        tessellator.func_78374_a(0.0, 1.0, 0.0, (double)f1, (double)f2);
        tessellator.func_78381_a();
        GL11.glDisable((int)3042);
    }

    public static void renderQuadCenteredFromIcon(boolean isBlock, IIcon icon, float scale, float red, float green, float blue, int brightness, int blend, float opacity) {
        if (isBlock) {
            Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
        } else {
            Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110576_c);
        }
        Tessellator tessellator = Tessellator.field_78398_a;
        float f1 = icon.func_94212_f();
        float f2 = icon.func_94206_g();
        float f3 = icon.func_94209_e();
        float f4 = icon.func_94210_h();
        GL11.glEnable((int)32826);
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)blend);
        GL11.glColor4f((float)red, (float)green, (float)blue, (float)opacity);
        tessellator.func_78382_b();
        tessellator.func_78380_c(brightness);
        tessellator.func_78369_a(red, green, blue, opacity);
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        tessellator.func_78374_a(-0.5, 0.5, 0.0, (double)f1, (double)f4);
        tessellator.func_78374_a(0.5, 0.5, 0.0, (double)f3, (double)f4);
        tessellator.func_78374_a(0.5, -0.5, 0.0, (double)f3, (double)f2);
        tessellator.func_78374_a(-0.5, -0.5, 0.0, (double)f1, (double)f2);
        tessellator.func_78381_a();
        GL11.glDisable((int)3042);
        GL11.glDisable((int)32826);
    }

    public static int getTextureAnimationSize(String s) {
        if (textureSizeCache.get(s) != null) {
            return (Integer)textureSizeCache.get(s);
        }
        try {
            InputStream inputstream = Minecraft.func_71410_x().func_110442_L().func_110536_a(new ResourceLocation("thaumcraft", s)).func_110527_b();
            if (inputstream == null) {
                throw new Exception("Image not found: " + s);
            }
            BufferedImage bi = ImageIO.read(inputstream);
            int size = bi.getWidth() / bi.getHeight();
            textureSizeCache.put(s, size);
            return size;
        }
        catch (Exception e) {
            e.printStackTrace();
            return 16;
        }
    }

    public static int getTextureSize(String s, int dv) {
        if (textureSizeCache.get(Arrays.asList(s, dv)) != null) {
            return (Integer)textureSizeCache.get(Arrays.asList(s, dv));
        }
        try {
            InputStream inputstream = Minecraft.func_71410_x().func_110442_L().func_110536_a(new ResourceLocation("thaumcraft", s)).func_110527_b();
            if (inputstream == null) {
                throw new Exception("Image not found: " + s);
            }
            BufferedImage bi = ImageIO.read(inputstream);
            int size = bi.getWidth() / dv;
            textureSizeCache.put(Arrays.asList(s, dv), size);
            return size;
        }
        catch (Exception e) {
            e.printStackTrace();
            return 16;
        }
    }

    public static int getBrightnessForRender(Entity entity, double x, double z) {
        int var3;
        int var2 = MathHelper.func_76128_c((double)x);
        if (entity.field_70170_p.func_72899_e(var2, 0, var3 = MathHelper.func_76128_c((double)z))) {
            double var4 = (entity.field_70121_D.field_72337_e - entity.field_70121_D.field_72338_b) * 0.66;
            int var6 = MathHelper.func_76128_c((double)(entity.field_70163_u - (double)entity.field_70129_M + var4));
            return entity.field_70170_p.func_72802_i(var2, var6, var3, 2);
        }
        return 0;
    }

    public static void bindTexture(String texture) {
        ResourceLocation rl = null;
        rl = boundTextures.containsKey(texture) ? boundTextures.get(texture) : new ResourceLocation("thaumcraft", texture);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(rl);
    }

    public static void bindTexture(String mod, String texture) {
        ResourceLocation rl = null;
        rl = boundTextures.containsKey(mod + ":" + texture) ? boundTextures.get(mod + ":" + texture) : new ResourceLocation(mod, texture);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(rl);
    }

    public static void bindTexture(ResourceLocation resource) {
        Minecraft.func_71410_x().field_71446_o.func_110577_a(resource);
    }

    public static void drawTag(int x, int y, Aspect aspect, float amount, int bonus, double z, int blend, float alpha) {
        UtilsFX.drawTag(x, y, aspect, amount, bonus, z, blend, alpha, false);
    }

    public static void drawTag(int x, int y, Aspect aspect, float amt, int bonus, double z) {
        UtilsFX.drawTag(x, y, aspect, amt, bonus, z, 771, 1.0f, false);
    }

    public static void drawTag(int x, int y, Aspect aspect) {
        UtilsFX.drawTag(x, y, aspect, 0.0f, 0, 0.0, 771, 1.0f, true);
    }

    public static void drawTag(int x, int y, Aspect aspect, float amount, int bonus, double z, int blend, float alpha, boolean bw) {
        UtilsFX.drawTag((double)x, (double)y, aspect, amount, bonus, z, blend, alpha, bw);
    }

    public static void drawTag(double x, double y, Aspect aspect, float amount, int bonus, double z, int blend, float alpha, boolean bw) {
        if (aspect == null) {
            return;
        }
        Minecraft mc = Minecraft.func_71410_x();
        Color color = new Color(aspect.getColor());
        GL11.glPushMatrix();
        GL11.glDisable((int)2896);
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)blend);
        GL11.glPushMatrix();
        mc.field_71446_o.func_110577_a(aspect.getImage());
        if (!bw) {
            GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)alpha);
        } else {
            GL11.glColor4f((float)0.1f, (float)0.1f, (float)0.1f, (float)(alpha * 0.8f));
        }
        Tessellator var9 = Tessellator.field_78398_a;
        var9.func_78382_b();
        if (!bw) {
            var9.func_78369_a((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, alpha);
        } else {
            var9.func_78369_a(0.1f, 0.1f, 0.1f, alpha * 0.8f);
        }
        var9.func_78374_a(x + 0.0, y + 16.0, z, 0.0, 1.0);
        var9.func_78374_a(x + 16.0, y + 16.0, z, 1.0, 1.0);
        var9.func_78374_a(x + 16.0, y + 0.0, z, 1.0, 0.0);
        var9.func_78374_a(x + 0.0, y + 0.0, z, 0.0, 0.0);
        var9.func_78381_a();
        GL11.glPopMatrix();
        if (amount > 0.0f) {
            GL11.glPushMatrix();
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            String am = myFormatter.format(amount);
            int sw = mc.field_71466_p.func_78256_a(am);
            if (blend > 1) {
                for (int a = -1; a <= 1; ++a) {
                    for (int b = -1; b <= 1; ++b) {
                        if (a != 0 && b != 0 || a == 0 && b == 0) continue;
                        mc.field_71466_p.func_78276_b(am, a + 32 - sw + (int)x * 2, b + 32 - mc.field_71466_p.field_78288_b + (int)y * 2, 0);
                    }
                }
            }
            mc.field_71466_p.func_78276_b(am, 32 - sw + (int)x * 2, 32 - mc.field_71466_p.field_78288_b + (int)y * 2, 0xFFFFFF);
            GL11.glPopMatrix();
        }
        if (bonus > 0) {
            GL11.glPushMatrix();
            UtilsFX.bindTexture(ParticleEngine.particleTexture);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            int px = 16 * (mc.field_71439_g.field_70173_aa % 16);
            UtilsFX.drawTexturedQuad((int)x - 4, (int)y - 4, px, 80, 16, 16, z);
            if (bonus > 1) {
                GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
                String am = "" + bonus;
                int sw = mc.field_71466_p.func_78256_a(am) / 2;
                if (blend > 1) {
                    for (int a = -1; a <= 1; ++a) {
                        for (int b = -1; b <= 1; ++b) {
                            if (a != 0 && b != 0 || a == 0 && b == 0) continue;
                            mc.field_71466_p.func_78276_b(am, 8 - sw + a + (int)x * 2, 15 + b - mc.field_71466_p.field_78288_b + (int)y * 2, 0);
                        }
                    }
                }
                mc.field_71466_p.func_78276_b(am, 8 - sw + (int)x * 2, 15 - mc.field_71466_p.field_78288_b + (int)y * 2, 0xFFFFFF);
            }
            GL11.glPopMatrix();
        }
        GL11.glDisable((int)3042);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glEnable((int)2896);
        GL11.glPopMatrix();
    }

    public static boolean isVisibleTo(float fov, Entity ent, double x, double y, double z) {
        double dz;
        double dist = ent.func_70011_f(x, y, z);
        if (dist < 2.0) {
            return true;
        }
        Minecraft mc = FMLClientHandler.instance().getClient();
        double vT = fov + mc.field_71474_y.field_74334_X / 2.0f;
        int j = 512;
        if (j > 400) {
            j = 400;
        }
        double rD = j;
        float f1 = MathHelper.func_76134_b((float)(-ent.field_70177_z * 0.01745329f - 3.141593f));
        float f3 = MathHelper.func_76126_a((float)(-ent.field_70177_z * 0.01745329f - 3.141593f));
        float f5 = -MathHelper.func_76134_b((float)(-ent.field_70125_A * 0.01745329f));
        float f7 = MathHelper.func_76126_a((float)(-ent.field_70125_A * 0.01745329f));
        double lx = f3 * f5;
        double ly = f7;
        double lz = f1 * f5;
        double dx = x + 0.5 - ent.field_70165_t;
        double dy = y + 0.5 - ent.field_70163_u - (double)ent.func_70047_e();
        double len = Math.sqrt(dx * dx + dy * dy + (dz = z + 0.5 - ent.field_70161_v) * dz);
        double dot = dx / len * lx + dy / len * ly + dz / len * lz;
        double angle = Math.acos(dot);
        return angle < vT && mc.field_71474_y.field_74320_O == 0 && dist < rD || mc.field_71474_y.field_74320_O > 0 && dist < rD;
    }

    public static void drawCustomTooltip(GuiScreen gui, RenderItem itemRenderer, FontRenderer fr, List var4, int par2, int par3, int subTipColor) {
        GL11.glDisable((int)32826);
        GL11.glDisable((int)2929);
        if (!var4.isEmpty()) {
            int var5 = 0;
            for (String var7 : var4) {
                int var8 = fr.func_78256_a(var7);
                if (var8 <= var5) continue;
                var5 = var8;
            }
            int var15 = par2 + 12;
            int var16 = par3 - 12;
            int var9 = 8;
            if (var4.size() > 1) {
                var9 += 2 + (var4.size() - 1) * 10;
            }
            itemRenderer.field_77023_b = 300.0f;
            int var10 = -267386864;
            UtilsFX.drawGradientRect(var15 - 3, var16 - 4, var15 + var5 + 3, var16 - 3, var10, var10);
            UtilsFX.drawGradientRect(var15 - 3, var16 + var9 + 3, var15 + var5 + 3, var16 + var9 + 4, var10, var10);
            UtilsFX.drawGradientRect(var15 - 3, var16 - 3, var15 + var5 + 3, var16 + var9 + 3, var10, var10);
            UtilsFX.drawGradientRect(var15 - 4, var16 - 3, var15 - 3, var16 + var9 + 3, var10, var10);
            UtilsFX.drawGradientRect(var15 + var5 + 3, var16 - 3, var15 + var5 + 4, var16 + var9 + 3, var10, var10);
            int var11 = 0x505000FF;
            int var12 = (var11 & 0xFEFEFE) >> 1 | var11 & 0xFF000000;
            UtilsFX.drawGradientRect(var15 - 3, var16 - 3 + 1, var15 - 3 + 1, var16 + var9 + 3 - 1, var11, var12);
            UtilsFX.drawGradientRect(var15 + var5 + 2, var16 - 3 + 1, var15 + var5 + 3, var16 + var9 + 3 - 1, var11, var12);
            UtilsFX.drawGradientRect(var15 - 3, var16 - 3, var15 + var5 + 3, var16 - 3 + 1, var11, var11);
            UtilsFX.drawGradientRect(var15 - 3, var16 + var9 + 2, var15 + var5 + 3, var16 + var9 + 3, var12, var12);
            for (int var13 = 0; var13 < var4.size(); ++var13) {
                String var14 = (String)var4.get(var13);
                var14 = var13 == 0 ? "\u00a7" + Integer.toHexString(subTipColor) + var14 : "\u00a77" + var14;
                fr.func_78261_a(var14, var15, var16, -1);
                if (var13 == 0) {
                    var16 += 2;
                }
                var16 += 10;
            }
        }
        itemRenderer.field_77023_b = 0.0f;
        GL11.glEnable((int)2929);
    }

    public static void drawGradientRect(int par1, int par2, int par3, int par4, int par5, int par6) {
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
        var15.func_78377_a((double)par3, (double)par2, 300.0);
        var15.func_78377_a((double)par1, (double)par2, 300.0);
        var15.func_78369_a(var12, var13, var14, var11);
        var15.func_78377_a((double)par1, (double)par4, 300.0);
        var15.func_78377_a((double)par3, (double)par4, 300.0);
        var15.func_78381_a();
        GL11.glShadeModel((int)7424);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)3008);
        GL11.glEnable((int)3553);
    }

    public static void drawFloatyLine(double x, double y, double z, double x2, double y2, double z2, float partialTicks, int color, String texture, float speed, float distance) {
        UtilsFX.drawFloatyLine(x, y, z, x2, y2, z2, partialTicks, color, texture, speed, distance, 0.15f);
    }

    public static void drawFloatyLine(double x, double y, double z, double x2, double y2, double z2, float partialTicks, int color, String texture, float speed, float distance, float width) {
        float f13;
        double dz;
        double dy;
        double dx;
        float f3;
        float f2a;
        float f2;
        EntityLivingBase player = Minecraft.func_71410_x().field_71451_h;
        double iPX = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * (double)partialTicks;
        double iPY = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * (double)partialTicks;
        double iPZ = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * (double)partialTicks;
        double ePX = x2;
        double ePY = y2;
        double ePZ = z2;
        GL11.glTranslated((double)(-iPX + ePX), (double)(-iPY + ePY), (double)(-iPZ + ePZ));
        float time = System.nanoTime() / 30000000L;
        Color co = new Color(color);
        float r = (float)co.getRed() / 255.0f;
        float g = (float)co.getGreen() / 255.0f;
        float b = (float)co.getBlue() / 255.0f;
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        Tessellator tessellator = Tessellator.field_78398_a;
        double ds1x = ePX;
        double ds1y = ePY;
        double ds1z = ePZ;
        double dd1x = x;
        double dd1y = y;
        double dd1z = z;
        double dc1x = (float)(dd1x - ds1x);
        double dc1y = (float)(dd1y - ds1y);
        double dc1z = (float)(dd1z - ds1z);
        UtilsFX.bindTexture(texture);
        GL11.glDisable((int)2884);
        tessellator.func_78371_b(5);
        double dx2 = 0.0;
        double dy2 = 0.0;
        double dz2 = 0.0;
        double d3 = x - ePX;
        double d4 = y - ePY;
        double d5 = z - ePZ;
        float dist = MathHelper.func_76133_a((double)(d3 * d3 + d4 * d4 + d5 * d5));
        float blocks = Math.round(dist);
        float length = blocks * ((float)Config.golemLinkQuality / 2.0f);
        float f9 = 0.0f;
        float f10 = 1.0f;
        int i = 0;
        while ((float)i <= length * distance) {
            f2 = (float)i / length;
            f2a = (float)i * 1.5f / length;
            f2a = Math.min(0.75f, f2a);
            f3 = 1.0f - Math.abs((float)i - length / 2.0f) / (length / 2.0f);
            dx = dc1x + (double)(MathHelper.func_76126_a((float)((float)((z % 16.0 + (double)(dist * (1.0f - f2) * (float)Config.golemLinkQuality / 2.0f) - (double)(time % 32767.0f / 5.0f)) / 4.0))) * 0.5f * f3);
            dy = dc1y + (double)(MathHelper.func_76126_a((float)((float)((x % 16.0 + (double)(dist * (1.0f - f2) * (float)Config.golemLinkQuality / 2.0f) - (double)(time % 32767.0f / 5.0f)) / 3.0))) * 0.5f * f3);
            dz = dc1z + (double)(MathHelper.func_76126_a((float)((float)((y % 16.0 + (double)(dist * (1.0f - f2) * (float)Config.golemLinkQuality / 2.0f) - (double)(time % 32767.0f / 5.0f)) / 2.0))) * 0.5f * f3);
            tessellator.func_78369_a(r, g, b, f3);
            f13 = (1.0f - f2) * dist - time * speed;
            tessellator.func_78374_a(dx * (double)f2, dy * (double)f2 - (double)width, dz * (double)f2, (double)f13, (double)f10);
            tessellator.func_78374_a(dx * (double)f2, dy * (double)f2 + (double)width, dz * (double)f2, (double)f13, (double)f9);
            ++i;
        }
        tessellator.func_78381_a();
        tessellator.func_78371_b(5);
        i = 0;
        while ((float)i <= length * distance) {
            f2 = (float)i / length;
            f2a = (float)i * 1.5f / length;
            f2a = Math.min(0.75f, f2a);
            f3 = 1.0f - Math.abs((float)i - length / 2.0f) / (length / 2.0f);
            dx = dc1x + (double)(MathHelper.func_76126_a((float)((float)((z % 16.0 + (double)(dist * (1.0f - f2) * (float)Config.golemLinkQuality / 2.0f) - (double)(time % 32767.0f / 5.0f)) / 4.0))) * 0.5f * f3);
            dy = dc1y + (double)(MathHelper.func_76126_a((float)((float)((x % 16.0 + (double)(dist * (1.0f - f2) * (float)Config.golemLinkQuality / 2.0f) - (double)(time % 32767.0f / 5.0f)) / 3.0))) * 0.5f * f3);
            dz = dc1z + (double)(MathHelper.func_76126_a((float)((float)((y % 16.0 + (double)(dist * (1.0f - f2) * (float)Config.golemLinkQuality / 2.0f) - (double)(time % 32767.0f / 5.0f)) / 2.0))) * 0.5f * f3);
            tessellator.func_78369_a(r, g, b, f3);
            f13 = (1.0f - f2) * dist - time * speed;
            tessellator.func_78374_a(dx * (double)f2 - (double)width, dy * (double)f2, dz * (double)f2, (double)f13, (double)f10);
            tessellator.func_78374_a(dx * (double)f2 + (double)width, dy * (double)f2, dz * (double)f2, (double)f13, (double)f9);
            ++i;
        }
        tessellator.func_78381_a();
        GL11.glEnable((int)2884);
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)true);
    }

    public static void drawFloatyGUILine(double x, double y, double x2, double y2, float partialTicks, int color, String texture, float speed, float distance) {
        GL11.glPushMatrix();
        GL11.glTranslated((double)x2, (double)y2, (double)0.0);
        float time = System.nanoTime() / 30000000L;
        Color co = new Color(color);
        float r = (float)co.getRed() / 255.0f;
        float g = (float)co.getGreen() / 255.0f;
        float b = (float)co.getBlue() / 255.0f;
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        Tessellator tessellator = Tessellator.field_78398_a;
        double dc1x = (float)(x - x2);
        double dc1y = (float)(y - y2);
        UtilsFX.bindTexture(texture);
        tessellator.func_78371_b(5);
        double d3 = x - x2;
        double d4 = y - y2;
        float dist = MathHelper.func_76133_a((double)(d3 * d3 + d4 * d4));
        double dx = d3 / (double)dist;
        double dy = d4 / (double)dist;
        GL11.glRotated((double)((float)(-(Math.atan2(d3, d4) * 180.0 / Math.PI)) + 90.0f), (double)0.0, (double)0.0, (double)1.0);
        float blocks = Math.round(dist);
        float length = blocks * distance;
        float f9 = 0.0f;
        float f10 = 1.0f;
        float sec = 1.0f / length;
        int i = 0;
        while ((float)i <= length) {
            float f2 = (float)i / length;
            tessellator.func_78369_a(r, g, b, 1.0f);
            float f13 = (1.0f - f2) * length;
            float f14 = (1.0f - f2) * length + sec;
            float width = 1.0f;
            tessellator.func_78374_a(dx * (double)i, (double)(0.0f - width), 0.0, (double)(f13 / width), (double)f10);
            tessellator.func_78374_a(dx * (double)i, (double)(0.0f + width), 0.0, (double)(f14 / width), (double)f9);
            ++i;
        }
        tessellator.func_78381_a();
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    public static float getEquippedProgress(ItemRenderer ir) {
        try {
            return ((Float)ReflectionHelper.getPrivateValue(ItemRenderer.class, (Object)ir, (String[])new String[]{"equippedProgress", "f", "field_78454_c"})).floatValue();
        }
        catch (Exception e) {
            return 0.0f;
        }
    }

    public static float getPrevEquippedProgress(ItemRenderer ir) {
        try {
            return ((Float)ReflectionHelper.getPrivateValue(ItemRenderer.class, (Object)ir, (String[])new String[]{"prevEquippedProgress", "g", "field_78451_d"})).floatValue();
        }
        catch (Exception e) {
            return 0.0f;
        }
    }

    public static Timer getTimer(Minecraft mc) {
        try {
            return (Timer)ReflectionHelper.getPrivateValue(Minecraft.class, (Object)mc, (String[])new String[]{"timer", "Q", "field_71428_T"});
        }
        catch (Exception e) {
            return new Timer(20.0f);
        }
    }

    public static int getGuiXSize(GuiContainer gui) {
        try {
            return (Integer)ReflectionHelper.getPrivateValue(GuiContainer.class, (Object)gui, (String[])new String[]{"xSize", "f", "field_146999_f"});
        }
        catch (Exception e) {
            return 0;
        }
    }

    public static int getGuiYSize(GuiContainer gui) {
        try {
            return (Integer)ReflectionHelper.getPrivateValue(GuiContainer.class, (Object)gui, (String[])new String[]{"ySize", "g", "field_147000_g"});
        }
        catch (Exception e) {
            return 0;
        }
    }

    public static float getGuiZLevel(Gui gui) {
        try {
            return ((Float)ReflectionHelper.getPrivateValue(Gui.class, (Object)gui, (String[])new String[]{"zLevel", "e", "field_73735_i"})).floatValue();
        }
        catch (Exception e) {
            return 0.0f;
        }
    }

    public static ResourceLocation getParticleTexture() {
        try {
            return (ResourceLocation)ReflectionHelper.getPrivateValue(EffectRenderer.class, null, (String[])new String[]{"particleTextures", "b", "field_110737_b"});
        }
        catch (Exception e) {
            return null;
        }
    }
}

