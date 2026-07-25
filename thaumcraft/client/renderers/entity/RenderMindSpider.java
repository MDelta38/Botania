/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelSpider
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntitySpider
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.entities.monster.EntityMindSpider;

@SideOnly(value=Side.CLIENT)
public class RenderMindSpider
extends RenderLiving {
    private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/taint_spider.png");

    public RenderMindSpider() {
        super((ModelBase)new ModelSpider(), 0.0f);
        this.func_77042_a((ModelBase)new ModelSpider());
    }

    protected float setSpiderDeathMaxRotation(EntitySpider par1EntitySpider) {
        return 180.0f;
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return rl;
    }

    public void func_76986_a(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        if (((EntityMindSpider)p_76986_1_).getViewer().length() == 0 || ((EntityMindSpider)p_76986_1_).getViewer().equals(this.field_76990_c.field_78734_h.func_70005_c_())) {
            super.func_76986_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
        }
    }

    public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        if (((EntityMindSpider)p_76986_1_).getViewer().length() == 0 || ((EntityMindSpider)p_76986_1_).getViewer().equals(this.field_76990_c.field_78734_h.func_70005_c_())) {
            super.func_76986_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
        }
    }

    public void func_76986_a(EntityLivingBase entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        if (((EntityMindSpider)entity).getViewer().length() == 0 || ((EntityMindSpider)entity).getViewer().equals(this.field_76990_c.field_78734_h.func_70005_c_())) {
            super.func_76986_a(entity, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
        }
    }

    protected void func_77036_a(EntityLivingBase entity, float p_77036_2_, float p_77036_3_, float p_77036_4_, float p_77036_5_, float p_77036_6_, float p_77036_7_) {
        this.func_110777_b((Entity)entity);
        GL11.glPushMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)Math.min(0.1f, (float)entity.field_70173_aa / 100.0f));
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        this.field_77045_g.func_78088_a((Entity)entity, p_77036_2_, p_77036_3_, p_77036_4_, p_77036_5_, p_77036_6_, p_77036_7_);
        GL11.glDisable((int)3042);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glPopMatrix();
        GL11.glDepthMask((boolean)true);
    }

    protected void func_77033_b(EntityLivingBase p_77033_1_, double p_77033_2_, double p_77033_4_, double p_77033_6_) {
        super.func_77033_b(p_77033_1_, p_77033_2_, p_77033_4_, p_77033_6_);
    }

    protected int setSpiderEyeBrightness(EntitySpider par1EntitySpider, int par2, float par3) {
        if (par2 != 0) {
            return -1;
        }
        UtilsFX.bindTexture("textures/models/taint_spider_eyes.png");
        float f1 = 1.0f;
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3008);
        GL11.glBlendFunc((int)1, (int)1);
        if (par1EntitySpider.func_82150_aj()) {
            GL11.glDepthMask((boolean)false);
        } else {
            GL11.glDepthMask((boolean)true);
        }
        int c0 = 61680;
        int j = c0 % 65536;
        int k = c0 / 65536;
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)j / 1.0f), (float)((float)k / 1.0f));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)f1);
        return 1;
    }

    protected void scaleSpider(EntityMindSpider par1EntitySpider, float par2) {
        float f1 = par1EntitySpider.spiderScaleAmount();
        GL11.glScalef((float)f1, (float)f1, (float)f1);
    }

    protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2) {
        this.scaleSpider((EntityMindSpider)par1EntityLiving, par2);
    }

    protected float func_77037_a(EntityLivingBase par1EntityLiving) {
        return this.setSpiderDeathMaxRotation((EntitySpider)par1EntityLiving);
    }

    protected int func_77032_a(EntityLivingBase par1EntityLiving, int par2, float par3) {
        return this.setSpiderEyeBrightness((EntitySpider)par1EntityLiving, par2, par3);
    }
}

