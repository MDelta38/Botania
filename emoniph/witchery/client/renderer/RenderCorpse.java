/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.entity.EntityCorpse;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderCorpse
extends RenderLiving {
    public RenderCorpse() {
        super((ModelBase)new ModelBiped(){

            public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
            }
        }, 0.0f);
    }

    public void doRenderCorpse(EntityCorpse entity, double par2, double par4, double par6, float par8, float par9) {
        GL11.glPushMatrix();
        super.func_76986_a((EntityLiving)entity, par2, par4, par6, par8, par9);
        GL11.glPopMatrix();
    }

    protected ResourceLocation getEntityTexture(EntityCorpse entity) {
        return entity.getLocationSkin();
    }

    protected void func_77043_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
        GL11.glTranslatef((float)0.9f, (float)0.25f, (float)0.0f);
        GL11.glRotatef((float)this.func_77037_a(par1EntityLivingBase), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)this.func_77037_a(par1EntityLivingBase), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(180.0f - par3), (float)0.0f, (float)1.0f, (float)0.0f);
        if (par1EntityLivingBase.field_70725_aQ > 0) {
            float f3 = ((float)par1EntityLivingBase.field_70725_aQ + par4 - 1.0f) / 20.0f * 1.6f;
            if ((f3 = MathHelper.func_76129_c((float)f3)) > 1.0f) {
                f3 = 1.0f;
            }
            GL11.glRotatef((float)(f3 * this.func_77037_a(par1EntityLivingBase)), (float)0.0f, (float)1.0f, (float)0.0f);
        } else {
            String s = EnumChatFormatting.func_110646_a((String)par1EntityLivingBase.func_70005_c_());
            if (!(!s.equals("Dinnerbone") && !s.equals("Grumm") || par1EntityLivingBase instanceof EntityPlayer && ((EntityPlayer)par1EntityLivingBase).func_82238_cc())) {
                GL11.glTranslatef((float)0.0f, (float)(par1EntityLivingBase.field_70131_O + 0.1f), (float)0.0f);
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            }
        }
    }

    protected float func_77040_d(EntityLivingBase par1EntityLivingBase, float par2) {
        return 0.0f;
    }

    public void func_76986_a(EntityLiving entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderCorpse((EntityCorpse)entity, par2, par4, par6, par8, par9);
    }

    public void func_76986_a(EntityLivingBase par1, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderCorpse((EntityCorpse)par1, par2, par4, par6, par8, par9);
    }

    public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderCorpse((EntityCorpse)entity, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return this.getEntityTexture((EntityCorpse)par1Entity);
    }
}

