/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelQuadruped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 */
package thaumcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import thaumcraft.common.entities.monster.EntityTaintSheep;

public class ModelTaintSheep2
extends ModelQuadruped {
    private float field_44017_o;

    public ModelTaintSheep2() {
        super(12, 0.0f);
        this.field_78150_a = new ModelRenderer((ModelBase)this, 0, 0);
        this.field_78150_a.func_78790_a(-3.0f, -4.0f, -6.0f, 6, 6, 8, 0.0f);
        this.field_78150_a.func_78793_a(0.0f, 6.0f, -8.0f);
        this.field_78148_b = new ModelRenderer((ModelBase)this, 28, 8);
        this.field_78148_b.func_78790_a(-4.0f, -10.0f, -7.0f, 8, 16, 6, 0.0f);
        this.field_78148_b.func_78793_a(0.0f, 5.0f, 2.0f);
    }

    public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
        super.func_78086_a(par1EntityLiving, par2, par3, par4);
        this.field_78150_a.field_78797_d = 6.0f + ((EntityTaintSheep)par1EntityLiving).func_44003_c(par4) * 9.0f;
        this.field_44017_o = ((EntityTaintSheep)par1EntityLiving).func_44002_d(par4);
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity e) {
        super.func_78087_a(par1, par2, par3, par4, par5, par6, e);
        this.field_78150_a.field_78795_f = this.field_44017_o;
    }
}

