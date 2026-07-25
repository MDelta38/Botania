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
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

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
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.models.entities.ModelEldritchGolem;
import thaumcraft.common.entities.monster.boss.EntityEldritchGolem;

@SideOnly(value=Side.CLIENT)
public class RenderEldritchGolem
extends RenderLiving {
    protected ModelEldritchGolem modelMain;
    private static final ResourceLocation skin = new ResourceLocation("thaumcraft", "textures/models/eldritch_golem.png");

    public RenderEldritchGolem(ModelEldritchGolem par1ModelBiped, float par2) {
        super((ModelBase)par1ModelBiped, par2);
        this.modelMain = par1ModelBiped;
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return skin;
    }

    protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2) {
        BossStatus.func_82824_a((IBossDisplayData)((EntityEldritchGolem)par1EntityLiving), (boolean)false);
        GL11.glScalef((float)2.15f, (float)2.15f, (float)2.15f);
    }

    public void doRenderLiving(EntityLiving golem, double par2, double par4, double par6, float par8, float par9) {
        GL11.glEnable((int)3042);
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        GL11.glBlendFunc((int)770, (int)771);
        double d3 = par4 - (double)golem.field_70129_M;
        super.func_76986_a(golem, par2, d3, par6, par8, par9);
        GL11.glDisable((int)3042);
        GL11.glAlphaFunc((int)516, (float)0.1f);
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderLiving((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
    }
}

