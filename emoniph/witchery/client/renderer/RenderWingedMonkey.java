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

import com.emoniph.witchery.entity.EntityWingedMonkey;
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
public class RenderWingedMonkey
extends RenderLiving {
    private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/wingedmonkey.png");
    public static final float[][] fleeceColorTable = new float[][]{{1.0f, 1.0f, 1.0f}, {0.85f, 0.5f, 0.2f}, {0.7f, 0.3f, 0.85f}, {0.4f, 0.6f, 0.85f}, {0.9f, 0.9f, 0.2f}, {0.5f, 0.8f, 0.1f}, {0.95f, 0.5f, 0.65f}, {0.3f, 0.3f, 0.3f}, {0.6f, 0.6f, 0.6f}, {0.3f, 0.5f, 0.6f}, {0.5f, 0.25f, 0.7f}, {0.2f, 0.3f, 0.7f}, {0.4f, 0.3f, 0.2f}, {0.4f, 0.5f, 0.2f}, {0.6f, 0.2f, 0.2f}, {0.1f, 0.1f, 0.1f}};

    public RenderWingedMonkey(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    public void doRenderMonkey(EntityWingedMonkey entity, double par2, double par4, double par6, float par8, float par9) {
        float f1 = 1.0f;
        int j = entity.getFeatherColor();
        if (j != 0) {
            float alpha = 0.84313726f;
            float bR = 0.41568628f;
            float bG = 0.3137255f;
            float bB = 0.24313726f;
            GL11.glColor3f((float)(f1 * fleeceColorTable[j][0] * 0.15686274f + 0.41568628f), (float)(f1 * fleeceColorTable[j][1] * 0.15686274f + 0.3137255f), (float)(f1 * fleeceColorTable[j][2] * 0.15686274f + 0.24313726f));
        }
        super.func_76986_a((EntityLiving)entity, par2, par4, par6, par8, par9);
    }

    public void func_76986_a(EntityLiving entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderMonkey((EntityWingedMonkey)entity, par2, par4, par6, par8, par9);
    }

    public void func_76986_a(EntityLivingBase par1, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderMonkey((EntityWingedMonkey)par1, par2, par4, par6, par8, par9);
    }

    public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderMonkey((EntityWingedMonkey)entity, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return TEXTURE_URL;
    }
}

