/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 */
package thaumcraft.client.renderers.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import thaumcraft.common.entities.monster.EntityTaintChicken;

public class RenderTaintChicken
extends RenderLiving {
    private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/chicken.png");

    public RenderTaintChicken(ModelBase par1ModelBase, float par2) {
        super(par1ModelBase, par2);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return rl;
    }

    public void renderChicken(EntityTaintChicken par1EntityChicken, double par2, double par4, double par6, float par8, float par9) {
        super.func_76986_a((EntityLiving)par1EntityChicken, par2, par4, par6, par8, par9);
    }

    protected float getWingRotation(EntityTaintChicken par1EntityChicken, float par2) {
        float var3 = par1EntityChicken.field_756_e + (par1EntityChicken.field_752_b - par1EntityChicken.field_756_e) * par2;
        float var4 = par1EntityChicken.field_757_d + (par1EntityChicken.destPos - par1EntityChicken.field_757_d) * par2;
        return (MathHelper.func_76126_a((float)var3) + 1.0f) * var4;
    }

    protected float func_77044_a(EntityLivingBase par1EntityLiving, float par2) {
        return this.getWingRotation((EntityTaintChicken)par1EntityLiving, par2);
    }

    public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
        this.renderChicken((EntityTaintChicken)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderChicken((EntityTaintChicken)par1Entity, par2, par4, par6, par8, par9);
    }
}

