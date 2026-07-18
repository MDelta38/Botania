/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import org.lwjgl.opengl.GL11;

public class ModelBellows
extends ModelBase {
    ModelRenderer Base;
    ModelRenderer Top;
    ModelRenderer Funnel;
    ModelRenderer Pipe;
    ModelRenderer Handle1;
    ModelRenderer Handle2;
    ModelRenderer Handle3;

    public ModelBellows() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.Base = new ModelRenderer((ModelBase)this, 0, 0);
        this.Base.func_78789_a(0.0f, 0.0f, 0.0f, 10, 2, 10);
        this.Base.func_78793_a(-5.0f, 22.0f, -5.0f);
        this.Base.func_78787_b(64, 32);
        this.Base.field_78809_i = true;
        this.Top = new ModelRenderer((ModelBase)this, 0, 14);
        this.Top.func_78789_a(0.0f, 0.0f, 0.0f, 8, 1, 8);
        this.Top.func_78793_a(-4.0f, 14.0f, -4.0f);
        this.Top.func_78787_b(64, 32);
        this.Top.field_78809_i = true;
        this.Funnel = new ModelRenderer((ModelBase)this, 34, 14);
        this.Funnel.func_78789_a(0.0f, 0.0f, 0.0f, 6, 7, 6);
        this.Funnel.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Funnel.func_78787_b(64, 32);
        this.Funnel.field_78809_i = true;
        this.Pipe = new ModelRenderer((ModelBase)this, 43, 1);
        this.Pipe.func_78789_a(0.0f, 0.0f, 0.0f, 2, 2, 3);
        this.Pipe.func_78793_a(-1.0f, 22.0f, -8.0f);
        this.Pipe.func_78787_b(64, 32);
        this.Pipe.field_78809_i = true;
        this.Handle1 = new ModelRenderer((ModelBase)this, 43, 8);
        this.Handle1.func_78789_a(0.0f, 0.0f, -0.5f, 1, 2, 1);
        this.Handle1.func_78793_a(-2.0f, 12.0f, 0.0f);
        this.Handle1.func_78787_b(64, 32);
        this.Handle1.field_78809_i = true;
        this.Handle2 = new ModelRenderer((ModelBase)this, 48, 8);
        this.Handle2.func_78789_a(1.0f, 0.0f, -0.5f, 2, 1, 1);
        this.Handle2.func_78793_a(-2.0f, 12.0f, 0.0f);
        this.Handle2.func_78787_b(64, 32);
        this.Handle2.field_78809_i = true;
        this.Handle3 = new ModelRenderer((ModelBase)this, 55, 8);
        this.Handle3.func_78789_a(3.0f, 0.0f, -0.5f, 1, 2, 1);
        this.Handle3.func_78793_a(-2.0f, 12.0f, 0.0f);
        this.Handle3.func_78787_b(64, 32);
        this.Handle3.field_78809_i = true;
    }

    public void render(float fract) {
        float f5 = 0.0625f;
        this.Base.func_78785_a(f5);
        this.Pipe.func_78785_a(f5);
        float mov = (1.0f - fract) * 0.5f;
        GL11.glTranslatef((float)0.0f, (float)mov, (float)0.0f);
        this.Top.func_78785_a(f5);
        this.Handle1.func_78785_a(f5);
        this.Handle2.func_78785_a(f5);
        this.Handle3.func_78785_a(f5);
        GL11.glTranslatef((float)0.0f, (float)(-mov), (float)0.0f);
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.19f, (float)-1.375f, (float)-0.19f);
        GL11.glScalef((float)1.0f, (float)fract, (float)1.0f);
        this.Funnel.func_78785_a(f5);
        GL11.glScalef((float)1.0f, (float)(1.0f / fract), (float)1.0f);
    }
}

