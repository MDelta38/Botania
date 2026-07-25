/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntitySkeleton
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package witchinggadgets.client.render;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelRobeSkirted
extends ModelBiped {
    List<ModelRenderer> parts = new ArrayList<ModelRenderer>();
    static ModelRobeSkirted model;

    public static ModelRobeSkirted getModel() {
        if (model == null) {
            model = new ModelRobeSkirted();
        }
        return model;
    }

    protected ModelRobeSkirted() {
        super(0.5f, 0.0f, 128, 64);
        this.field_78114_d.field_78806_j = false;
        this.parts.clear();
        this.field_78112_f.field_78804_l.clear();
        this.field_78112_f.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 11, 4, 0.5f);
        ModelRenderer temp = new ModelRenderer((ModelBase)this, 40, 32);
        temp.func_78790_a(-2.5f, 4.2f, 2.0f, 3, 5, 1, 0.25f);
        temp.func_78793_a(0.0f, 0.0f, 0.0f);
        temp.func_78787_b(128, 64);
        this.setRotation(temp, 0.0f, 0.0f, 0.0f);
        this.field_78112_f.func_78792_a(temp);
        temp = new ModelRenderer((ModelBase)this, 48, 32);
        temp.func_78790_a(-2.0f, 6.4f, 2.0f, 2, 3, 1, 0.25f);
        temp.func_78793_a(0.0f, 0.0f, 0.0f);
        temp.func_78787_b(128, 64);
        this.setRotation(temp, 0.125f, 0.0f, 0.0f);
        this.field_78112_f.func_78792_a(temp);
        this.field_78113_g.field_78804_l.clear();
        this.field_78113_g.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 11, 4, 0.5f);
        temp = new ModelRenderer((ModelBase)this, 40, 32);
        temp.func_78790_a(-0.5f, 4.2f, 2.0f, 3, 5, 1, 0.25f);
        temp.func_78793_a(0.0f, 0.0f, 0.0f);
        temp.func_78787_b(128, 64);
        this.setRotation(temp, 0.0f, 0.0f, 0.0f);
        this.field_78113_g.func_78792_a(temp);
        temp = new ModelRenderer((ModelBase)this, 48, 32);
        temp.func_78790_a(-0.0f, 6.4f, 2.0f, 2, 3, 1, 0.25f);
        temp.func_78793_a(0.0f, 0.0f, 0.0f);
        temp.func_78787_b(128, 64);
        this.setRotation(temp, 0.125f, 0.0f, 0.0f);
        this.field_78113_g.func_78792_a(temp);
        temp = new ModelRenderer((ModelBase)this, 32, 0);
        temp.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 7, 1.0f);
        temp.func_78793_a(0.0f, 0.0f, 0.0f);
        temp.func_78787_b(128, 64);
        this.field_78116_c.func_78792_a(temp);
        temp = new ModelRenderer((ModelBase)this, 64, 0);
        temp.func_78790_a(-4.0f, -7.8f, 3.0f, 8, 6, 2, 0.5f);
        temp.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(temp, -0.0875f, 0.0f, 0.0f);
        this.field_78116_c.func_78792_a(temp);
        temp = new ModelRenderer((ModelBase)this, 64, 8);
        temp.func_78790_a(-3.0f, -7.5f, 5.0f, 6, 6, 1, 0.25f);
        temp.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(temp, -0.125f, 0.0f, 0.0f);
        this.field_78116_c.func_78792_a(temp);
        temp = new ModelRenderer((ModelBase)this, 4, 32);
        temp.func_78790_a(-0.5f, 0.0f, 1.4f, 2, 8, 1, 0.25f);
        temp.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(temp, 0.125f, 0.0f, 0.0f);
        this.field_78123_h.func_78792_a(temp);
        temp = new ModelRenderer((ModelBase)this, 0, 32);
        temp.func_78790_a(-2.0f, 0.0f, 1.4f, 1, 7, 1, 0.25f);
        temp.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(temp, 0.125f, 0.0f, 0.0f);
        this.field_78123_h.func_78792_a(temp);
        temp = new ModelRenderer((ModelBase)this, 10, 32);
        temp.func_78790_a(-2.0f, 12.5f, -2.0f, 1, 6, 4, 0.25f);
        temp.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(temp, 0.0f, 0.0f, 0.1875f);
        this.field_78115_e.func_78792_a(temp);
        temp = new ModelRenderer((ModelBase)this, 10, 32);
        temp.func_78790_a(-4.3f, 11.0f, -2.0f, 1, 1, 4, 0.25f);
        temp.func_78793_a(0.0f, 0.0f, 0.0f);
        this.field_78115_e.func_78792_a(temp);
        temp = new ModelRenderer((ModelBase)this, 4, 32);
        temp.func_78790_a(-1.5f, 0.0f, 1.4f, 2, 8, 1, 0.25f);
        temp.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(temp, 0.125f, 0.0f, 0.0f);
        this.field_78124_i.func_78792_a(temp);
        temp = new ModelRenderer((ModelBase)this, 0, 32);
        temp.func_78790_a(1.0f, 0.0f, 1.4f, 1, 7, 1, 0.25f);
        temp.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(temp, 0.125f, 0.0f, 0.0f);
        this.field_78124_i.func_78792_a(temp);
        temp = new ModelRenderer((ModelBase)this, 10, 32);
        temp.func_78790_a(1.0f, 12.5f, -2.0f, 1, 6, 4, 0.25f);
        temp.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(temp, 0.0f, 0.0f, -0.1875f);
        this.field_78115_e.func_78792_a(temp);
        temp = new ModelRenderer((ModelBase)this, 10, 32);
        temp.func_78790_a(3.3f, 11.0f, -2.0f, 1, 1, 4, 0.25f);
        temp.func_78793_a(0.0f, 0.0f, 0.0f);
        this.field_78115_e.func_78792_a(temp);
        temp = new ModelRenderer((ModelBase)this, 64, 8);
        temp.func_78790_a(-3.0f, 4.0f, 1.0f, 6, 8, 1, 0.25f);
        temp.func_78793_a(0.0f, 0.0f, 0.0f);
        this.field_78115_e.func_78792_a(temp);
    }

    public void func_78088_a(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        float f6;
        this.func_78087_a(par2, par3, par4, par5, par6, par7, entity);
        if (entity instanceof EntitySkeleton || entity instanceof EntityZombie) {
            f6 = MathHelper.func_76126_a((float)(this.field_78095_p * 3.141593f));
            float f7 = MathHelper.func_76126_a((float)((1.0f - (1.0f - this.field_78095_p) * (1.0f - this.field_78095_p)) * 3.141593f));
            this.field_78112_f.field_78808_h = 0.0f;
            this.field_78113_g.field_78808_h = 0.0f;
            this.field_78112_f.field_78796_g = -(0.1f - f6 * 0.6f);
            this.field_78113_g.field_78796_g = 0.1f - f6 * 0.6f;
            this.field_78112_f.field_78795_f = -1.570796f;
            this.field_78113_g.field_78795_f = -1.570796f;
            this.field_78112_f.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
            this.field_78113_g.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
            this.field_78112_f.field_78808_h += MathHelper.func_76134_b((float)(par4 * 0.09f)) * 0.05f + 0.05f;
            this.field_78113_g.field_78808_h -= MathHelper.func_76134_b((float)(par4 * 0.09f)) * 0.05f + 0.05f;
            this.field_78112_f.field_78795_f += MathHelper.func_76126_a((float)(par4 * 0.067f)) * 0.05f;
            this.field_78113_g.field_78795_f -= MathHelper.func_76126_a((float)(par4 * 0.067f)) * 0.05f;
        }
        this.field_78117_n = entity.func_70093_af();
        this.field_78093_q = entity.func_70115_ae();
        if (entity instanceof EntityLivingBase) {
            this.field_78091_s = ((EntityLivingBase)entity).func_70631_g_();
            int n = this.field_78120_m = ((EntityLivingBase)entity).func_70694_bm() != null ? 1 : 0;
            if (entity instanceof EntityPlayer) {
                boolean bl = this.field_78118_o = ((EntityPlayer)entity).func_71057_bx() > 0;
            }
            if (!(((EntityLivingBase)entity).func_71124_b(4) == null || ((EntityLivingBase)entity).func_71124_b(4).func_77977_a().contains("goggles") || ((EntityLivingBase)entity).func_71124_b(4).func_77977_a().contains("Goggles") || ((EntityLivingBase)entity).func_71124_b(4).func_77977_a().contains("glasses"))) {
                this.field_78116_c.field_78806_j = false;
            }
        }
        if (this.field_78091_s) {
            f6 = 2.0f;
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.5f / f6), (float)(1.5f / f6), (float)(1.5f / f6));
            GL11.glTranslatef((float)0.0f, (float)(16.0f * par7), (float)0.0f);
            this.field_78116_c.func_78785_a(par7);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
            GL11.glTranslatef((float)0.0f, (float)(24.0f * par7), (float)0.0f);
            this.field_78115_e.func_78785_a(par7);
            this.field_78112_f.func_78785_a(par7);
            this.field_78113_g.func_78785_a(par7);
            this.field_78123_h.func_78785_a(par7);
            this.field_78124_i.func_78785_a(par7);
            this.field_78114_d.func_78785_a(par7);
            GL11.glPopMatrix();
        } else {
            this.field_78116_c.func_78785_a(par7);
            this.field_78115_e.func_78785_a(par7);
            this.field_78112_f.func_78785_a(par7);
            this.field_78113_g.func_78785_a(par7);
            this.field_78123_h.func_78785_a(par7);
            this.field_78124_i.func_78785_a(par7);
            this.field_78114_d.func_78785_a(par7);
        }
    }

    void setRotation(ModelRenderer mr, float x, float y, float z) {
        mr.field_78795_f = x;
        mr.field_78796_g = y;
        mr.field_78808_h = z;
    }
}

