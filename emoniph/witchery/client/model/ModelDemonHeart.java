/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class ModelDemonHeart
extends ModelBase {
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer bigTube1;
    ModelRenderer tube1;
    ModelRenderer tube2;
    ModelRenderer tube3;
    ModelRenderer tube4;
    ModelRenderer tube5;

    public ModelDemonHeart() {
        this.field_78090_t = 32;
        this.field_78089_u = 32;
        this.Shape1 = new ModelRenderer((ModelBase)this, 14, 20);
        this.Shape1.func_78789_a(0.0f, 0.0f, 0.0f, 5, 8, 4);
        this.Shape1.func_78793_a(-3.0f, 14.0f, 0.0f);
        this.Shape1.func_78787_b(32, 32);
        this.Shape1.field_78809_i = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        this.Shape2 = new ModelRenderer((ModelBase)this, 0, 7);
        this.Shape2.func_78789_a(0.0f, 0.0f, 0.0f, 3, 8, 6);
        this.Shape2.func_78793_a(-4.0f, 15.0f, -1.0f);
        this.Shape2.func_78787_b(32, 32);
        this.Shape2.field_78809_i = true;
        this.setRotation(this.Shape2, 0.0f, 0.0f, 0.0f);
        this.Shape3 = new ModelRenderer((ModelBase)this, 13, 0);
        this.Shape3.func_78789_a(0.0f, 0.0f, 0.0f, 1, 6, 4);
        this.Shape3.func_78793_a(-5.0f, 16.0f, 0.0f);
        this.Shape3.func_78787_b(32, 32);
        this.Shape3.field_78809_i = true;
        this.setRotation(this.Shape3, 0.0f, 0.0f, 0.0f);
        this.Shape4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape4.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 2);
        this.Shape4.func_78793_a(-3.0f, 13.0f, 1.0f);
        this.Shape4.func_78787_b(32, 32);
        this.Shape4.field_78809_i = true;
        this.setRotation(this.Shape4, 0.0f, 0.0f, 0.0f);
        this.bigTube1 = new ModelRenderer((ModelBase)this, 3, 3);
        this.bigTube1.func_78789_a(0.0f, 0.0f, 0.0f, 3, 2, 2);
        this.bigTube1.func_78793_a(-2.0f, 15.0f, -1.0f);
        this.bigTube1.func_78787_b(32, 32);
        this.bigTube1.field_78809_i = true;
        this.setRotation(this.bigTube1, 0.0f, 0.3717861f, 0.2230717f);
        this.tube1 = new ModelRenderer((ModelBase)this, 19, 11);
        this.tube1.func_78789_a(0.0f, -3.0f, 1.0f, 1, 3, 1);
        this.tube1.func_78793_a(-3.0f, 14.0f, 1.0f);
        this.tube1.func_78787_b(32, 32);
        this.tube1.field_78809_i = true;
        this.setRotation(this.tube1, 0.4089647f, 0.6320364f, 0.0f);
        this.tube2 = new ModelRenderer((ModelBase)this, 19, 11);
        this.tube2.func_78789_a(0.0f, -3.0f, 1.0f, 1, 3, 1);
        this.tube2.func_78793_a(-2.0f, 14.0f, 1.0f);
        this.tube2.func_78787_b(32, 32);
        this.tube2.field_78809_i = true;
        this.setRotation(this.tube2, -0.2974289f, -0.2230717f, -0.3346075f);
        this.tube3 = new ModelRenderer((ModelBase)this, 19, 11);
        this.tube3.func_78789_a(0.0f, -3.0f, 1.0f, 1, 3, 1);
        this.tube3.func_78793_a(1.0f, 13.0f, -0.8f);
        this.tube3.func_78787_b(32, 32);
        this.tube3.field_78809_i = true;
        this.setRotation(this.tube3, -0.0743572f, 0.1487144f, -0.2602503f);
        this.tube4 = new ModelRenderer((ModelBase)this, 19, 11);
        this.tube4.func_78789_a(0.0f, -3.0f, 1.0f, 1, 3, 1);
        this.tube4.func_78793_a(0.0f, 15.0f, 0.0f);
        this.tube4.func_78787_b(32, 32);
        this.tube4.field_78809_i = true;
        this.setRotation(this.tube4, 0.2602503f, 0.0f, 0.4089647f);
        this.tube5 = new ModelRenderer((ModelBase)this, 19, 11);
        this.tube5.func_78789_a(0.0f, -3.0f, 1.0f, 1, 3, 1);
        this.tube5.func_78793_a(0.0f, 14.0f, 1.0f);
        this.tube5.func_78787_b(32, 32);
        this.tube5.field_78809_i = true;
        this.setRotation(this.tube5, -0.2602503f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, long ticks) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.tube1.func_78785_a(f5);
        this.tube2.func_78785_a(f5);
        this.tube3.func_78785_a(f5);
        this.tube4.func_78785_a(f5);
        this.tube5.func_78785_a(f5);
        this.Shape4.func_78785_a(f5);
        GL11.glTranslatef((float)0.0f, (float)1.0f, (float)0.0f);
        double size = 0.165 * (7.0 + 0.25 * Math.sin(0.25132741228718347 * (double)ticks));
        GL11.glScaled((double)size, (double)size, (double)size);
        GL11.glTranslatef((float)0.0f, (float)-1.0f, (float)0.0f);
        this.bigTube1.func_78785_a(f5);
        this.Shape1.func_78785_a(f5);
        this.Shape2.func_78785_a(f5);
        this.Shape3.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
    }
}

