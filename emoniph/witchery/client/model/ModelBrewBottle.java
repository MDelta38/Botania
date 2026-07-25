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
public class ModelBrewBottle
extends ModelBase {
    private ModelRenderer Bottle;
    private ModelRenderer Stopper;

    public ModelBrewBottle() {
        this.field_78090_t = 32;
        this.field_78089_u = 32;
        this.func_78085_a("Bottle.BodyOuter", 0, 14);
        this.func_78085_a("Bottle.BodyInner", 2, 8);
        this.func_78085_a("Bottle.Neck", 4, 4);
        this.func_78085_a("Bottle.Stopper", 2, 0);
        this.Bottle = new ModelRenderer((ModelBase)this, "Bottle");
        this.Bottle.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.Bottle, 0.0f, 0.0f, 0.0f);
        this.Bottle.field_78809_i = true;
        this.Bottle.func_78786_a("BodyOuter", -1.5f, -2.0f, -1.5f, 3, 2, 3);
        this.Bottle.func_78786_a("BodyInner", -1.0f, -2.5f, -1.0f, 2, 3, 2);
        this.Bottle.func_78786_a("Neck", -0.5f, -4.0f, -0.5f, 1, 2, 1);
        this.Stopper = new ModelRenderer((ModelBase)this, 2, 0);
        this.Stopper.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 2);
        this.Stopper.func_78787_b(32, 32);
        this.Stopper.func_78793_a(-1.0f, -4.5f, -1.0f);
        this.setRotation(this.Stopper, 0.0f, 0.0f, 0.0f);
        this.Stopper.field_78809_i = true;
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Bottle.func_78785_a(f5);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        this.Stopper.func_78785_a(f5);
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

