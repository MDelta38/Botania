/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.entity.EntityToad;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderToad
extends RenderLiving {
    private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/toad.png");
    public static final float[][] fleeceColorTable = new float[][]{{1.0f, 1.0f, 1.0f}, {0.85f, 0.5f, 0.2f}, {0.7f, 0.3f, 0.85f}, {0.4f, 0.6f, 0.85f}, {0.9f, 0.9f, 0.2f}, {0.5f, 0.8f, 0.1f}, {0.95f, 0.5f, 0.65f}, {0.3f, 0.3f, 0.3f}, {0.6f, 0.6f, 0.6f}, {0.3f, 0.5f, 0.6f}, {0.5f, 0.25f, 0.7f}, {0.2f, 0.3f, 0.7f}, {0.4f, 0.3f, 0.2f}, {0.4f, 0.5f, 0.2f}, {0.6f, 0.2f, 0.2f}, {0.1f, 0.1f, 0.1f}};

    public RenderToad(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
        this.field_76989_e = 0.3f;
    }

    public void doRenderToad(EntityToad entity, double par2, double par4, double par6, float par8, float par9) {
        float f1 = 1.0f;
        int j = entity.getSkinColor();
        if (j > 0) {
            float alpha = 0.84313726f;
            float bR = 0.065205686f;
            float bG = 0.04921184f;
            float bB = 0.038139176f;
            GL11.glColor3f((float)(f1 * fleeceColorTable[j][0] + 0.065205686f), (float)(f1 * fleeceColorTable[j][1] + 0.04921184f), (float)(f1 * fleeceColorTable[j][2] + 0.038139176f));
        }
        super.func_76986_a((EntityLiving)entity, par2, par4, par6, par8, par9);
    }

    protected void rotateToadCorpse(EntityToad entity, float par2, float par3, float par4) {
        super.func_77043_a((EntityLivingBase)entity, par2, par3, par4);
    }

    public void func_76986_a(EntityLiving entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderToad((EntityToad)entity, par2, par4, par6, par8, par9);
    }

    protected void func_77043_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
        this.rotateToadCorpse((EntityToad)par1EntityLivingBase, par2, par3, par4);
    }

    public void func_76986_a(EntityLivingBase par1, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderToad((EntityToad)par1, par2, par4, par6, par8, par9);
    }

    public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderToad((EntityToad)entity, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return TEXTURE_URL;
    }
}

