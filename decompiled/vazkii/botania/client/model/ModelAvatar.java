/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package vazkii.botania.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelAvatar
extends ModelBase {
    public ModelRenderer body;
    public ModelRenderer rightarm;
    public ModelRenderer leftarm;
    public ModelRenderer rightleg;
    public ModelRenderer leftleg;
    public ModelRenderer head;

    public ModelAvatar() {
        this.field_78090_t = 32;
        this.field_78089_u = 32;
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 20);
        this.leftleg.field_78809_i = true;
        this.leftleg.func_78793_a(1.5f, 18.0f, -0.5f);
        this.leftleg.func_78790_a(-1.5f, 0.0f, -1.5f, 3, 6, 3, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 0, 20);
        this.rightarm.func_78793_a(-3.0f, 15.0f, -1.0f);
        this.rightarm.func_78790_a(-2.0f, -1.0f, -1.0f, 2, 6, 3, 0.0f);
        this.setRotateAngle(this.rightarm, 0.0f, -0.0f, 0.08726646f);
        this.leftarm = new ModelRenderer((ModelBase)this, 0, 20);
        this.leftarm.field_78809_i = true;
        this.leftarm.func_78793_a(3.0f, 15.0f, -1.0f);
        this.leftarm.func_78790_a(0.0f, -1.0f, -1.0f, 2, 6, 3, 0.0f);
        this.setRotateAngle(this.leftarm, 0.0f, -0.0f, -0.08726646f);
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.func_78793_a(0.0f, 14.0f, 0.0f);
        this.head.func_78790_a(-3.0f, -6.0f, -3.0f, 6, 6, 6, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 20);
        this.rightleg.func_78793_a(-1.5f, 18.0f, -0.5f);
        this.rightleg.func_78790_a(-1.5f, 0.0f, -1.5f, 3, 6, 3, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 0, 12);
        this.body.func_78793_a(0.0f, 14.0f, 0.0f);
        this.body.func_78790_a(-3.0f, 0.0f, -2.0f, 6, 4, 4, 0.0f);
    }

    public void render() {
        float f5 = 0.06666667f;
        this.leftleg.func_78785_a(f5);
        this.rightarm.func_78785_a(f5);
        this.leftarm.func_78785_a(f5);
        this.head.func_78785_a(f5);
        this.rightleg.func_78785_a(f5);
        this.body.func_78785_a(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

