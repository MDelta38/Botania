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
 *  net.minecraft.entity.passive.EntityOcelot
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.entity.EntityFamiliar;
import com.emoniph.witchery.util.RenderUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderFamiliar
extends RenderLiving {
    private static final ResourceLocation pigTextures = new ResourceLocation("textures/entity/pig/pig.png");

    public RenderFamiliar(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
        this.field_76989_e = 0.0f;
    }

    public void renderLivingFamiliar(EntityFamiliar familiarEntity, double par2, double par4, double par6, float par8, float par9) {
        GL11.glPushMatrix();
        RenderUtil.blend(true);
        if (familiarEntity != null && familiarEntity.getItemIDToFind() != -1) {
            GL11.glColor4f((float)0.7f, (float)0.3f, (float)1.0f, (float)0.51f);
        } else {
            GL11.glColor4f((float)0.5f, (float)0.5f, (float)1.0f, (float)0.51f);
        }
        super.func_76986_a((EntityLiving)familiarEntity, par2, par4, par6, par8, par9);
        RenderUtil.blend(false);
        GL11.glPopMatrix();
    }

    protected ResourceLocation func_110874_a(EntityOcelot par1EntityOcelot) {
        return pigTextures;
    }

    protected void preRenderFamiliar(EntityFamiliar par1EntityFamiliar, float par2) {
        super.func_77041_b((EntityLivingBase)par1EntityFamiliar, par2);
        if (par1EntityFamiliar.func_70909_n()) {
            GL11.glScalef((float)0.8f, (float)0.8f, (float)0.8f);
        }
    }

    public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
        this.renderLivingFamiliar((EntityFamiliar)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    protected void func_77041_b(EntityLivingBase par1EntityLivingBase, float par2) {
        this.preRenderFamiliar((EntityFamiliar)par1EntityLivingBase, par2);
    }

    public void func_76986_a(EntityLivingBase par1EntityLivingBase, double par2, double par4, double par6, float par8, float par9) {
        this.renderLivingFamiliar((EntityFamiliar)par1EntityLivingBase, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return this.func_110874_a((EntityFamiliar)par1Entity);
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderLivingFamiliar((EntityFamiliar)par1Entity, par2, par4, par6, par8, par9);
    }
}

