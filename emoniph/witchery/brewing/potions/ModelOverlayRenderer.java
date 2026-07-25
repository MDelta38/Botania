/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RendererLivingEntity
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Timer
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.potions.PotionResizing;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Timer;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class ModelOverlayRenderer {
    private static Field fieldMainModel;
    private static Field fieldTimer;
    private static Method methodRotateCorpse;
    private static Method methodHandleRotationFloat;
    private static Method methodPreRenderCallback;
    private static Timer timer;

    public static float getRenderPartialTicks() {
        if (fieldTimer == null) {
            fieldTimer = ReflectionHelper.findField(Minecraft.class, (String[])new String[]{"timer", "field_71428_T", "Q"});
            try {
                Minecraft mc = Minecraft.func_71410_x();
                if (timer == null) {
                    timer = (Timer)fieldTimer.get(mc);
                }
            }
            catch (IllegalAccessException illegalAccessException) {
                // empty catch block
            }
        }
        if (timer != null) {
            return ModelOverlayRenderer.timer.field_74281_c;
        }
        return 0.0f;
    }

    private static void ensureInitialized(RendererLivingEntity originalRenderer) {
        if (fieldTimer == null) {
            fieldTimer = ReflectionHelper.findField(Minecraft.class, (String[])new String[]{"timer", "field_71428_T", "Q"});
            try {
                Minecraft mc = Minecraft.func_71410_x();
                if (timer == null) {
                    timer = (Timer)fieldTimer.get(mc);
                }
            }
            catch (IllegalAccessException illegalAccessException) {
                // empty catch block
            }
        }
        if (fieldMainModel == null) {
            fieldMainModel = ReflectionHelper.findField(RendererLivingEntity.class, (String[])new String[]{"mainModel", "field_77045_g", "i"});
        }
        if (methodRotateCorpse == null) {
            methodRotateCorpse = ReflectionHelper.findMethod(RendererLivingEntity.class, (Object)originalRenderer, (String[])new String[]{"rotateCorpse", "func_77043_a", "a"}, (Class[])new Class[]{EntityLivingBase.class, Float.TYPE, Float.TYPE, Float.TYPE});
        }
        if (methodHandleRotationFloat == null) {
            methodHandleRotationFloat = ReflectionHelper.findMethod(RendererLivingEntity.class, (Object)originalRenderer, (String[])new String[]{"handleRotationFloat", "func_77044_a", "b"}, (Class[])new Class[]{EntityLivingBase.class, Float.TYPE});
        }
        if (methodPreRenderCallback == null) {
            methodPreRenderCallback = ReflectionHelper.findMethod(RendererLivingEntity.class, (Object)originalRenderer, (String[])new String[]{"preRenderCallback", "func_77041_b", "a"}, (Class[])new Class[]{EntityLivingBase.class, Float.TYPE});
        }
    }

    public static void render(EntityLivingBase entity, double x, double y, double z, RendererLivingEntity originalRenderer) {
        try {
            ModelOverlayRenderer.ensureInitialized(originalRenderer);
            ModelBase mainModel = (ModelBase)fieldMainModel.get(originalRenderer);
            ModelOverlayRenderer.renderModel(entity, x, y, z, originalRenderer, mainModel);
        }
        catch (IllegalAccessException ex) {
            // empty catch block
        }
    }

    public static void renderModel(EntityLivingBase entity, double x, double y, double z, RendererLivingEntity originalRenderer, ModelBase model) {
        ModelOverlayRenderer.ensureInitialized(originalRenderer);
        if (timer != null) {
            ModelOverlayRenderer.renderModelAsOverlay(entity, model, x, y, z, ModelOverlayRenderer.timer.field_74281_c, originalRenderer);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private static void renderModelAsOverlay(EntityLivingBase entity, ModelBase mainModel, double x, double y, double z, float partialRenderTicks, RendererLivingEntity originalRenderer) {
        try {
            float f4;
            GL11.glPushMatrix();
            mainModel.field_78095_p = ModelOverlayRenderer.renderSwingProgress(entity, partialRenderTicks);
            mainModel.field_78093_q = entity.func_70115_ae();
            mainModel.field_78091_s = entity.func_70631_g_();
            float f2 = ModelOverlayRenderer.interpolateRotation(entity.field_70760_ar, entity.field_70761_aq, partialRenderTicks);
            float f3 = ModelOverlayRenderer.interpolateRotation(entity.field_70758_at, entity.field_70759_as, partialRenderTicks);
            if (entity.func_70115_ae() && entity.field_70154_o instanceof EntityLivingBase) {
                EntityLivingBase entitylivingbase1 = (EntityLivingBase)entity.field_70154_o;
                f2 = ModelOverlayRenderer.interpolateRotation(entitylivingbase1.field_70760_ar, entitylivingbase1.field_70761_aq, partialRenderTicks);
                f4 = MathHelper.func_76142_g((float)(f3 - f2));
                if (f4 < -85.0f) {
                    f4 = -85.0f;
                }
                if (f4 >= 85.0f) {
                    f4 = 85.0f;
                }
                f2 = f3 - f4;
                if (f4 * f4 > 2500.0f) {
                    f2 += f4 * 0.2f;
                }
            }
            float f13 = entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * partialRenderTicks;
            ModelOverlayRenderer.renderLivingAt(entity, x, y, z);
            f4 = ((Float)methodHandleRotationFloat.invoke(originalRenderer, entity, Float.valueOf(partialRenderTicks))).floatValue();
            methodRotateCorpse.invoke(originalRenderer, entity, Float.valueOf(f4), Float.valueOf(f2), Float.valueOf(partialRenderTicks));
            float f5 = 0.0625f;
            GL11.glEnable((int)32826);
            GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
            methodPreRenderCallback.invoke(originalRenderer, entity, Float.valueOf(partialRenderTicks));
            GL11.glTranslatef((float)0.0f, (float)(-24.0f * f5 - 0.0078125f), (float)0.0f);
            float f6 = entity.field_70722_aY + (entity.field_70721_aZ - entity.field_70722_aY) * partialRenderTicks;
            float f7 = entity.field_70754_ba - entity.field_70721_aZ * (1.0f - partialRenderTicks);
            if (entity.func_70631_g_()) {
                f7 *= 3.0f;
            }
            if (f6 > 1.0f) {
                f6 = 1.0f;
            }
            GL11.glEnable((int)3008);
            mainModel.func_78086_a(entity, f7, f6, partialRenderTicks);
            float SCALE = 1.01f;
            GL11.glScalef((float)1.01f, (float)1.01f, (float)1.01f);
            ModelOverlayRenderer.renderModel(entity, f7, f6, f4, f3 - f2, f13, f5, mainModel);
        }
        catch (IllegalAccessException ex) {
        }
        catch (InvocationTargetException ex) {
        }
        finally {
            GL11.glPopMatrix();
        }
    }

    private static float interpolateRotation(float par1, float par2, float partialRenderTicks) {
        float f3;
        for (f3 = par2 - par1; f3 < -180.0f; f3 += 360.0f) {
        }
        while (f3 >= 180.0f) {
            f3 -= 360.0f;
        }
        return par1 + partialRenderTicks * f3;
    }

    private static void renderLivingAt(EntityLivingBase entity, double p_77039_2_, double p_77039_4_, double p_77039_6_) {
        PotionEffect resizing;
        GL11.glTranslatef((float)((float)p_77039_2_), (float)((float)p_77039_4_), (float)((float)p_77039_6_));
        if (entity != null && entity.func_70644_a(Witchery.Potions.RESIZING) && (resizing = entity.func_70660_b(Witchery.Potions.RESIZING)) != null) {
            float scale = PotionResizing.getModifiedScaleFactor(entity, resizing.func_76458_c());
            GL11.glScalef((float)scale, (float)scale, (float)scale);
        }
    }

    private static float renderSwingProgress(EntityLivingBase entity, float partialRenderTicks) {
        return entity.func_70678_g(partialRenderTicks);
    }

    private static void renderModel(EntityLivingBase entity, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_, ModelBase mainModel) {
        if (!entity.func_82150_aj()) {
            mainModel.func_78088_a((Entity)entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
        } else if (!entity.func_98034_c((EntityPlayer)Minecraft.func_71410_x().field_71439_g)) {
            GL11.glPushMatrix();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.15f);
            GL11.glDepthMask((boolean)false);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glAlphaFunc((int)516, (float)0.003921569f);
            mainModel.func_78088_a((Entity)entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
            GL11.glDisable((int)3042);
            GL11.glAlphaFunc((int)516, (float)0.1f);
            GL11.glPopMatrix();
            GL11.glDepthMask((boolean)true);
        } else {
            mainModel.func_78087_a(p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_, (Entity)entity);
        }
    }
}

