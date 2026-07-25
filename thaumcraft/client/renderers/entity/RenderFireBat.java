/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBat
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBat;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.models.entities.ModelFireBat;
import thaumcraft.common.entities.monster.EntityFireBat;

@SideOnly(value=Side.CLIENT)
public class RenderFireBat
extends RenderLiving {
    private int renderedBatSize;
    private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/firebat.png");
    private static final ResourceLocation rl2 = new ResourceLocation("thaumcraft", "textures/models/vampirebat.png");

    public RenderFireBat() {
        super((ModelBase)new ModelFireBat(), 0.25f);
        this.renderedBatSize = ((ModelFireBat)this.field_77045_g).getBatSize();
    }

    public void func_82443_a(EntityFireBat par1EntityBat, double par2, double par4, double par6, float par8, float par9) {
        int var10 = ((ModelFireBat)this.field_77045_g).getBatSize();
        if (var10 != this.renderedBatSize) {
            this.renderedBatSize = var10;
            this.field_77045_g = new ModelBat();
        }
        super.func_76986_a((EntityLiving)par1EntityBat, par2, par4, par6, par8, par9);
    }

    protected void func_82442_a(EntityFireBat par1EntityBat, float par2) {
        if (par1EntityBat.getIsDevil() || par1EntityBat.getIsVampire()) {
            GL11.glScalef((float)0.6f, (float)0.6f, (float)0.6f);
        } else {
            GL11.glScalef((float)0.35f, (float)0.35f, (float)0.35f);
        }
    }

    protected void func_82445_a(EntityFireBat par1EntityBat, double par2, double par4, double par6) {
        super.func_77039_a((EntityLivingBase)par1EntityBat, par2, par4, par6);
    }

    protected void func_82444_a(EntityFireBat par1EntityBat, float par2, float par3, float par4) {
        if (!par1EntityBat.getIsBatHanging()) {
            GL11.glTranslatef((float)0.0f, (float)(MathHelper.func_76134_b((float)(par2 * 0.3f)) * 0.1f), (float)0.0f);
        } else {
            GL11.glTranslatef((float)0.0f, (float)-0.1f, (float)0.0f);
        }
        super.func_77043_a((EntityLivingBase)par1EntityBat, par2, par3, par4);
    }

    protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2) {
        this.func_82442_a((EntityFireBat)par1EntityLiving, par2);
    }

    protected void func_77043_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
        this.func_82444_a((EntityFireBat)par1EntityLiving, par2, par3, par4);
    }

    protected void func_77039_a(EntityLivingBase par1EntityLiving, double par2, double par4, double par6) {
        this.func_82445_a((EntityFireBat)par1EntityLiving, par2, par4, par6);
    }

    public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
        this.func_82443_a((EntityFireBat)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.func_82443_a((EntityFireBat)par1Entity, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        if (entity instanceof EntityFireBat && ((EntityFireBat)entity).getIsVampire()) {
            return rl2;
        }
        return rl;
    }
}

