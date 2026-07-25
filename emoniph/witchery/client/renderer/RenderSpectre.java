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

import com.emoniph.witchery.client.model.ModelSpectre;
import com.emoniph.witchery.entity.EntitySpectre;
import com.emoniph.witchery.util.RenderUtil;
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
public class RenderSpectre
extends RenderLiving {
    private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/spectre.png");

    public RenderSpectre() {
        super((ModelBase)new ModelSpectre(true), 0.0f);
    }

    public void doRenderNightmare(EntitySpectre entity, double par2, double par4, double par6, float par8, float par9) {
        GL11.glPushMatrix();
        RenderUtil.blend(true);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(entity.isObscured() ? 0.15f : 0.6f));
        super.func_76986_a((EntityLiving)entity, par2, par4, par6, par8, par9);
        RenderUtil.blend(false);
        GL11.glPopMatrix();
    }

    protected void rotateNightmareCorpse(EntitySpectre entity, float par2, float par3, float par4) {
        super.func_77043_a((EntityLivingBase)entity, par2, par3, par4);
    }

    public void func_76986_a(EntityLiving entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderNightmare((EntitySpectre)entity, par2, par4, par6, par8, par9);
    }

    protected void func_77043_a(EntityLivingBase entity, float par2, float par3, float par4) {
        this.rotateNightmareCorpse((EntitySpectre)entity, par2, par3, par4);
    }

    public void func_76986_a(EntityLivingBase par1, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderNightmare((EntitySpectre)par1, par2, par4, par6, par8, par9);
    }

    public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderNightmare((EntitySpectre)entity, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return this.func_110832_a((EntitySpectre)par1Entity);
    }

    protected ResourceLocation func_110832_a(EntitySpectre par1Entity) {
        return TEXTURE_URL;
    }
}

