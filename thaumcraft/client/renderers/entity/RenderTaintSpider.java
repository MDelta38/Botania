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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.entities.monster.EntityTaintSpider;

@SideOnly(value=Side.CLIENT)
public class RenderTaintSpider
extends RenderLiving {
    private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/taint_spider.png");

    public RenderTaintSpider() {
        super((ModelBase)new ModelSpider(), 0.5f);
        this.func_77042_a((ModelBase)new ModelSpider());
    }

    protected float setSpiderDeathMaxRotation(EntitySpider par1EntitySpider) {
        return 180.0f;
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return rl;
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

    protected void scaleSpider(EntityTaintSpider par1EntitySpider, float par2) {
        float f1 = par1EntitySpider.spiderScaleAmount();
        GL11.glScalef((float)f1, (float)(f1 * 1.25f), (float)f1);
    }

    protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2) {
        this.scaleSpider((EntityTaintSpider)par1EntityLiving, par2);
    }

    protected float func_77037_a(EntityLivingBase par1EntityLiving) {
        return this.setSpiderDeathMaxRotation((EntitySpider)par1EntityLiving);
    }

    protected int func_77032_a(EntityLivingBase par1EntityLiving, int par2, float par3) {
        return this.setSpiderEyeBrightness((EntitySpider)par1EntityLiving, par2, par3);
    }
}

