/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package vazkii.botania.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSkullOverride
extends ModelBase {
    private final ModelRenderer bipedHead;
    private final ModelRenderer bipedHeadwear;

    public ModelSkullOverride() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.bipedHead = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHead.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f);
        this.bipedHead.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bipedHeadwear = new ModelRenderer((ModelBase)this, 32, 0);
        this.bipedHeadwear.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.5f);
        this.bipedHeadwear.func_78793_a(0.0f, 0.0f, 0.0f);
    }

    public void renderWithoutRotation(float par1) {
        this.bipedHead.func_78785_a(par1);
        this.bipedHeadwear.func_78785_a(par1);
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
        this.bipedHead.func_78785_a(par7);
        this.bipedHeadwear.func_78785_a(par7);
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.bipedHead.field_78796_g = this.bipedHeadwear.field_78796_g = par4 / 57.295776f;
        this.bipedHead.field_78795_f = this.bipedHeadwear.field_78795_f = par5 / 57.295776f;
    }
}

