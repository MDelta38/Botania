/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.models;

import java.awt.Color;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import org.lwjgl.opengl.GL11;

public class ModelResearchTable
extends ModelBase {
    ModelRenderer Top;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Leg4;
    ModelRenderer Crossbar;
    ModelRenderer Inkwell;
    ModelRenderer ScrollTube;
    ModelRenderer ScrollRibbon;

    public ModelResearchTable() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Top = new ModelRenderer((ModelBase)this, 0, 0);
        this.Top.func_78789_a(0.0f, 0.0f, 0.0f, 32, 4, 16);
        this.Top.func_78793_a(-8.0f, 0.0f, -8.0f);
        this.Top.func_78787_b(128, 64);
        this.Top.field_78809_i = true;
        this.setRotation(this.Top, 0.0f, 0.0f, 0.0f);
        this.Leg1 = new ModelRenderer((ModelBase)this, 0, 24);
        this.Leg1.func_78789_a(0.0f, 0.0f, 0.0f, 4, 12, 4);
        this.Leg1.func_78793_a(-6.0f, 4.0f, -6.0f);
        this.Leg1.func_78787_b(128, 64);
        this.Leg1.field_78809_i = true;
        this.setRotation(this.Leg1, 0.0f, 0.0f, 0.0f);
        this.Leg2 = new ModelRenderer((ModelBase)this, 0, 24);
        this.Leg2.func_78789_a(0.0f, 0.0f, 0.0f, 4, 12, 4);
        this.Leg2.func_78793_a(-6.0f, 4.0f, 2.0f);
        this.Leg2.func_78787_b(128, 64);
        this.Leg2.field_78809_i = true;
        this.setRotation(this.Leg2, 0.0f, 0.0f, 0.0f);
        this.Leg3 = new ModelRenderer((ModelBase)this, 0, 24);
        this.Leg3.func_78789_a(0.0f, 0.0f, 0.0f, 4, 12, 4);
        this.Leg3.func_78793_a(18.0f, 4.0f, -6.0f);
        this.Leg3.func_78787_b(128, 64);
        this.Leg3.field_78809_i = true;
        this.setRotation(this.Leg3, 0.0f, 0.0f, 0.0f);
        this.Leg4 = new ModelRenderer((ModelBase)this, 0, 24);
        this.Leg4.func_78789_a(0.0f, 0.0f, 0.0f, 4, 12, 4);
        this.Leg4.func_78793_a(18.0f, 4.0f, 2.0f);
        this.Leg4.func_78787_b(128, 64);
        this.Leg4.field_78809_i = true;
        this.setRotation(this.Leg4, 0.0f, 0.0f, 0.0f);
        this.Crossbar = new ModelRenderer((ModelBase)this, 24, 24);
        this.Crossbar.func_78789_a(0.0f, 0.0f, 0.0f, 24, 4, 4);
        this.Crossbar.func_78793_a(-4.0f, 10.0f, -2.0f);
        this.Crossbar.func_78787_b(128, 64);
        this.Crossbar.field_78809_i = true;
        this.setRotation(this.Crossbar, 0.0f, 0.0f, 0.0f);
        this.Inkwell = new ModelRenderer((ModelBase)this, 0, 44);
        this.Inkwell.func_78789_a(0.0f, 0.0f, 0.0f, 3, 2, 3);
        this.Inkwell.func_78793_a(-6.0f, -2.0f, 3.0f);
        this.Inkwell.func_78787_b(128, 64);
        this.Inkwell.field_78809_i = true;
        this.setRotation(this.Inkwell, 0.0f, 0.0f, 0.0f);
        this.ScrollTube = new ModelRenderer((ModelBase)this, 0, 0);
        this.ScrollTube.func_78789_a(-21.0f, -0.5f, -8.0f, 8, 2, 2);
        this.ScrollTube.func_78793_a(-2.0f, -2.0f, 2.0f);
        this.ScrollTube.func_78787_b(128, 64);
        this.ScrollTube.field_78809_i = true;
        this.setRotation(this.ScrollTube, 0.0f, 10.0f, 0.0f);
        this.ScrollRibbon = new ModelRenderer((ModelBase)this, 0, 4);
        this.ScrollRibbon.func_78789_a(-15.1f, -0.275f, -6.75f, 1, 2, 2);
        this.ScrollRibbon.func_78793_a(-2.0f, -2.0f, 2.0f);
        this.ScrollRibbon.func_78787_b(128, 64);
        this.ScrollRibbon.field_78809_i = true;
        this.setRotation(this.ScrollRibbon, 0.0f, 10.0f, 0.0f);
    }

    public void renderAll() {
        this.Top.func_78785_a(0.0625f);
        this.Leg1.func_78785_a(0.0625f);
        this.Leg2.func_78785_a(0.0625f);
        this.Leg3.func_78785_a(0.0625f);
        this.Leg4.func_78785_a(0.0625f);
        this.Crossbar.func_78785_a(0.0625f);
    }

    public void renderInkwell() {
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        this.Inkwell.func_78785_a(0.0625f);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    public void renderScroll(int color) {
        GL11.glPushMatrix();
        this.ScrollTube.func_78785_a(0.0625f);
        Color c = new Color(color);
        GL11.glColor4f((float)((float)c.getRed() / 255.0f), (float)((float)c.getGreen() / 255.0f), (float)((float)c.getBlue() / 255.0f), (float)1.0f);
        GL11.glScalef((float)1.2f, (float)1.2f, (float)1.2f);
        this.ScrollRibbon.func_78785_a(0.0625f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glPopMatrix();
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

