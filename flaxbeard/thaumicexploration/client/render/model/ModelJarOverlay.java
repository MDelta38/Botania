/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package flaxbeard.thaumicexploration.client.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelJarOverlay
extends ModelBase {
    ModelRenderer Core;
    ModelRenderer Brine;
    ModelRenderer Lid;

    public ModelJarOverlay() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.Core = new ModelRenderer((ModelBase)this, 0, 0);
        this.Core.func_78789_a(-6.0f, -12.0f, -6.0f, 12, 12, 12);
        this.Core.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Core.func_78787_b(64, 32);
        this.Core.field_78809_i = true;
        this.Lid = new ModelRenderer((ModelBase)this, 0, 24);
        this.Lid.func_78789_a(-3.0f, -0.25f, -3.0f, 6, 2, 6);
        this.Lid.func_78793_a(0.0f, -14.0f, 0.0f);
        this.Lid.func_78787_b(64, 32);
        this.Lid.field_78809_i = true;
    }

    public void renderAll() {
        this.Lid.func_78785_a(0.0625f);
        this.Core.func_78785_a(0.0625f);
    }
}

