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
 *  net.minecraft.entity.boss.BossStatus
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.util.ResourceLocation
 */
package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.client.model.ModelDeath;
import com.emoniph.witchery.entity.EntityDeath;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.util.ResourceLocation;

@SideOnly(value=Side.CLIENT)
public class RenderDeath
extends RenderLiving {
    private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/death.png");

    public RenderDeath() {
        super((ModelBase)new ModelDeath(), 0.5f);
    }

    public void doRenderDeath(EntityDeath entity, double par2, double par4, double par6, float par8, float par9) {
        BossStatus.func_82824_a((IBossDisplayData)entity, (boolean)true);
        super.func_76986_a((EntityLiving)entity, par2, par4, par6, par8, par9);
    }

    protected void rotateDeathCorpse(EntityDeath entity, float par2, float par3, float par4) {
        super.func_77043_a((EntityLivingBase)entity, par2, par3, par4);
    }

    public void func_76986_a(EntityLiving entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderDeath((EntityDeath)entity, par2, par4, par6, par8, par9);
    }

    protected void func_77043_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
        this.rotateDeathCorpse((EntityDeath)par1EntityLivingBase, par2, par3, par4);
    }

    public void func_76986_a(EntityLivingBase par1, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderDeath((EntityDeath)par1, par2, par4, par6, par8, par9);
    }

    public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderDeath((EntityDeath)entity, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return this.func_110832_a((EntityDeath)par1Entity);
    }

    protected ResourceLocation func_110832_a(EntityDeath par1Entity) {
        return TEXTURE_URL;
    }
}

