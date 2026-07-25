/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  org.lwjgl.opengl.GL11
 */
package flaxbeard.thaumicexploration.client.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import org.lwjgl.opengl.GL11;

public class ModelLargeJar
extends ModelBase {
    ModelRenderer Core;
    ModelRenderer Brine;
    ModelRenderer Lid;

    public ModelLargeJar() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.Core = new ModelRenderer((ModelBase)this, 0, 0);
        this.Core.func_78789_a(-8.0f, -16.0f, -8.0f, 16, 16, 16);
        this.Core.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Core.func_78787_b(64, 32);
        this.Core.field_78809_i = true;
        this.setRotation(this.Core, 0.0f, 0.0f, 0.0f);
        this.Brine = new ModelRenderer((ModelBase)this, 0, 0);
        this.Brine.func_78789_a(-7.0f, -15.0f, -7.0f, 14, 14, 14);
        this.Brine.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Brine.func_78787_b(64, 32);
        this.Brine.field_78809_i = true;
        this.setRotation(this.Brine, 0.0f, 0.0f, 0.0f);
        this.Lid = new ModelRenderer((ModelBase)this, 0, 24);
        this.Lid.func_78789_a(-3.0f, -4.0f, -3.0f, 6, 2, 6);
        this.Lid.func_78793_a(0.0f, -14.0f, 0.0f);
        this.Lid.func_78787_b(64, 32);
        this.Lid.field_78809_i = true;
    }

    public void renderBrine() {
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        this.Brine.func_78785_a(0.0625f);
        GL11.glDisable((int)3042);
    }

    public void renderAll() {
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        this.Core.func_78785_a(0.0625f);
        GL11.glDisable((int)3042);
    }

    public void renderLid() {
        this.Lid.func_78785_a(0.0625f);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

