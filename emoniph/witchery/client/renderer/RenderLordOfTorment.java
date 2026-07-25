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

import com.emoniph.witchery.client.model.ModelLordOfTorment;
import com.emoniph.witchery.entity.EntityLordOfTorment;
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
public class RenderLordOfTorment
extends RenderLiving {
    private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/lordoftorment.png");

    public RenderLordOfTorment() {
        super((ModelBase)new ModelLordOfTorment(), 0.5f);
    }

    public void doRenderDemon(EntityLordOfTorment entity, double par2, double par4, double par6, float par8, float par9) {
        BossStatus.func_82824_a((IBossDisplayData)entity, (boolean)true);
        super.func_76986_a((EntityLiving)entity, par2, par4, par6, par8, par9);
    }

    protected void rotateDemonCorpse(EntityLordOfTorment entity, float par2, float par3, float par4) {
        super.func_77043_a((EntityLivingBase)entity, par2, par3, par4);
    }

    public void func_76986_a(EntityLiving entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderDemon((EntityLordOfTorment)entity, par2, par4, par6, par8, par9);
    }

    protected void func_77043_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
        this.rotateDemonCorpse((EntityLordOfTorment)par1EntityLivingBase, par2, par3, par4);
    }

    public void func_76986_a(EntityLivingBase par1, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderDemon((EntityLordOfTorment)par1, par2, par4, par6, par8, par9);
    }

    public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderDemon((EntityLordOfTorment)entity, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return this.func_110832_a((EntityLordOfTorment)par1Entity);
    }

    protected ResourceLocation func_110832_a(EntityLordOfTorment par1Entity) {
        return TEXTURE_URL;
    }
}

