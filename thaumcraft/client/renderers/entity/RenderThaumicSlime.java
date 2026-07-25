/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.common.entities.monster.EntityThaumicSlime;

@SideOnly(value=Side.CLIENT)
public class RenderThaumicSlime
extends RenderLiving {
    private ModelBase scaleAmount;
    private static final ResourceLocation field_110897_a = new ResourceLocation("thaumcraft", "textures/models/tslime.png");

    public RenderThaumicSlime(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3) {
        super(par1ModelBase, par3);
        this.scaleAmount = par2ModelBase;
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return field_110897_a;
    }

    protected int shouldSlimeRenderPass(EntityThaumicSlime par1EntitySlime, int par2, float par3) {
        if (par1EntitySlime.func_82150_aj()) {
            return 0;
        }
        if (par2 == 0) {
            this.func_77042_a(this.scaleAmount);
            GL11.glEnable((int)2977);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            return 1;
        }
        if (par2 == 1) {
            GL11.glDisable((int)3042);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
        return -1;
    }

    protected void scaleSlime(EntityThaumicSlime par1EntitySlime, float par2) {
        float f1 = (float)Math.sqrt(par1EntitySlime.getSlimeSize());
        float f2 = (par1EntitySlime.field_70812_c + (par1EntitySlime.field_70811_b - par1EntitySlime.field_70812_c) * par2) / (f1 * 0.25f + 1.0f);
        float f3 = 1.0f / (f2 + 1.0f);
        GL11.glScalef((float)(f3 * f1 + 0.1f), (float)(1.0f / f3 * f1 + 0.1f), (float)(f3 * f1 + 0.1f));
    }

    protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2) {
        this.scaleSlime((EntityThaumicSlime)par1EntityLiving, par2);
    }

    protected int func_77032_a(EntityLivingBase par1EntityLiving, int par2, float par3) {
        return this.shouldSlimeRenderPass((EntityThaumicSlime)par1EntityLiving, par2, par3);
    }
}

