/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelVillager
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.common.entities.monster.EntityTaintVillager;

public class RenderTaintVillager
extends RenderLiving {
    protected ModelVillager field_40295_c;
    private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/villager.png");

    public RenderTaintVillager() {
        super((ModelBase)new ModelVillager(0.0f), 0.5f);
        this.field_40295_c = (ModelVillager)this.field_77045_g;
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return rl;
    }

    protected int func_40293_a(EntityTaintVillager par1EntityVillager, int par2, float par3) {
        return -1;
    }

    public void renderVillager(EntityTaintVillager par1EntityVillager, double par2, double par4, double par6, float par8, float par9) {
        super.func_76986_a((EntityLiving)par1EntityVillager, par2, par4, par6, par8, par9);
    }

    protected void func_40290_a(EntityTaintVillager par1EntityVillager, double par2, double par4, double par6) {
    }

    protected void func_40291_a(EntityTaintVillager par1EntityVillager, float par2) {
        super.func_77029_c((EntityLivingBase)par1EntityVillager, par2);
    }

    protected void func_40292_b(EntityTaintVillager par1EntityVillager, float par2) {
        float var3 = 0.9375f;
        this.field_76989_e = 0.5f;
        GL11.glScalef((float)var3, (float)var3, (float)var3);
    }

    protected void func_77033_b(EntityLivingBase par1EntityLiving, double par2, double par4, double par6) {
        this.func_40290_a((EntityTaintVillager)par1EntityLiving, par2, par4, par6);
    }

    protected void preRenderCallback(EntityLiving par1EntityLiving, float par2) {
        this.func_40292_b((EntityTaintVillager)par1EntityLiving, par2);
    }

    protected int func_77032_a(EntityLivingBase par1EntityLiving, int par2, float par3) {
        return this.func_40293_a((EntityTaintVillager)par1EntityLiving, par2, par3);
    }

    protected void func_77029_c(EntityLivingBase par1EntityLiving, float par2) {
        this.func_40291_a((EntityTaintVillager)par1EntityLiving, par2);
    }

    public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
        this.renderVillager((EntityTaintVillager)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderVillager((EntityTaintVillager)par1Entity, par2, par4, par6, par8, par9);
    }
}

