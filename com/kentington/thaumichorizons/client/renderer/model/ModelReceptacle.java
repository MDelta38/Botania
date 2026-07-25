/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 */
package com.kentington.thaumichorizons.client.renderer.model;

import com.kentington.thaumichorizons.common.lib.PocketPlaneData;
import java.awt.Color;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelReceptacle
extends ModelBase {
    ModelRenderer Corner1;
    ModelRenderer Corner2;
    ModelRenderer Corner3;
    ModelRenderer Corner4;
    ModelRenderer Side1A;
    ModelRenderer Corner5;
    ModelRenderer Corner6;
    ModelRenderer Corner7;
    ModelRenderer Corner8;
    ModelRenderer Side1B;
    ModelRenderer Side1C;
    ModelRenderer Side1D;
    ModelRenderer Side2A;
    ModelRenderer Side2B;
    ModelRenderer Side2C;
    ModelRenderer Side2D;
    ModelRenderer Side3A;
    ModelRenderer Side3B;
    ModelRenderer Side3C;
    ModelRenderer Side3D;
    ModelRenderer Keystone;

    public ModelReceptacle() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Corner1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Corner1.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Corner1.func_78793_a(-8.0f, 20.0f, 4.0f);
        this.Corner1.func_78787_b(64, 64);
        this.Corner1.field_78809_i = true;
        this.setRotation(this.Corner1, 0.0f, 0.0f, 0.0f);
        this.Corner2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Corner2.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Corner2.func_78793_a(-8.0f, 20.0f, -8.0f);
        this.Corner2.func_78787_b(64, 64);
        this.Corner2.field_78809_i = true;
        this.setRotation(this.Corner2, 0.0f, 0.0f, 0.0f);
        this.Corner3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Corner3.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Corner3.func_78793_a(4.0f, 20.0f, -8.0f);
        this.Corner3.func_78787_b(64, 64);
        this.Corner3.field_78809_i = true;
        this.setRotation(this.Corner3, 0.0f, 0.0f, 0.0f);
        this.Corner4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Corner4.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Corner4.func_78793_a(4.0f, 20.0f, 4.0f);
        this.Corner4.func_78787_b(64, 64);
        this.Corner4.field_78809_i = true;
        this.setRotation(this.Corner4, 0.0f, 0.0f, 0.0f);
        this.Side1A = new ModelRenderer((ModelBase)this, 0, 8);
        this.Side1A.func_78789_a(0.0f, 0.0f, 0.0f, 8, 4, 4);
        this.Side1A.func_78793_a(-4.0f, 8.0f, 4.0f);
        this.Side1A.func_78787_b(64, 64);
        this.Side1A.field_78809_i = true;
        this.setRotation(this.Side1A, 0.0f, 0.0f, 0.0f);
        this.Corner5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Corner5.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Corner5.func_78793_a(-8.0f, 8.0f, 4.0f);
        this.Corner5.func_78787_b(64, 64);
        this.Corner5.field_78809_i = true;
        this.setRotation(this.Corner5, 0.0f, 0.0f, 0.0f);
        this.Corner6 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Corner6.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Corner6.func_78793_a(-8.0f, 8.0f, -8.0f);
        this.Corner6.func_78787_b(64, 64);
        this.Corner6.field_78809_i = true;
        this.setRotation(this.Corner6, 0.0f, 0.0f, 0.0f);
        this.Corner7 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Corner7.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Corner7.func_78793_a(4.0f, 8.0f, -8.0f);
        this.Corner7.func_78787_b(64, 64);
        this.Corner7.field_78809_i = true;
        this.setRotation(this.Corner7, 0.0f, 0.0f, 0.0f);
        this.Corner8 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Corner8.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Corner8.func_78793_a(4.0f, 8.0f, 4.0f);
        this.Corner8.func_78787_b(64, 64);
        this.Corner8.field_78809_i = true;
        this.setRotation(this.Corner8, 0.0f, 0.0f, 0.0f);
        this.Side1B = new ModelRenderer((ModelBase)this, 0, 8);
        this.Side1B.func_78789_a(0.0f, 0.0f, 0.0f, 8, 4, 4);
        this.Side1B.func_78793_a(-4.0f, 8.0f, -8.0f);
        this.Side1B.func_78787_b(64, 64);
        this.Side1B.field_78809_i = true;
        this.setRotation(this.Side1B, 0.0f, 0.0f, 0.0f);
        this.Side1C = new ModelRenderer((ModelBase)this, 0, 8);
        this.Side1C.func_78789_a(0.0f, 0.0f, 0.0f, 8, 4, 4);
        this.Side1C.func_78793_a(-4.0f, 20.0f, -8.0f);
        this.Side1C.func_78787_b(64, 64);
        this.Side1C.field_78809_i = true;
        this.setRotation(this.Side1C, 0.0f, 0.0f, 0.0f);
        this.Side1D = new ModelRenderer((ModelBase)this, 0, 8);
        this.Side1D.func_78789_a(0.0f, 0.0f, 0.0f, 8, 4, 4);
        this.Side1D.func_78793_a(-4.0f, 20.0f, 4.0f);
        this.Side1D.func_78787_b(64, 64);
        this.Side1D.field_78809_i = true;
        this.setRotation(this.Side1D, 0.0f, 0.0f, 0.0f);
        this.Side2A = new ModelRenderer((ModelBase)this, 0, 16);
        this.Side2A.func_78789_a(0.0f, 0.0f, 0.0f, 4, 8, 4);
        this.Side2A.func_78793_a(-8.0f, 12.0f, -8.0f);
        this.Side2A.func_78787_b(64, 64);
        this.Side2A.field_78809_i = true;
        this.setRotation(this.Side2A, 0.0f, 0.0f, 0.0f);
        this.Side2B = new ModelRenderer((ModelBase)this, 0, 16);
        this.Side2B.func_78789_a(0.0f, 0.0f, 0.0f, 4, 8, 4);
        this.Side2B.func_78793_a(4.0f, 12.0f, 4.0f);
        this.Side2B.func_78787_b(64, 64);
        this.Side2B.field_78809_i = true;
        this.setRotation(this.Side2B, 0.0f, 0.0f, 0.0f);
        this.Side2C = new ModelRenderer((ModelBase)this, 0, 16);
        this.Side2C.func_78789_a(0.0f, 0.0f, 0.0f, 4, 8, 4);
        this.Side2C.func_78793_a(-8.0f, 12.0f, 4.0f);
        this.Side2C.func_78787_b(64, 64);
        this.Side2C.field_78809_i = true;
        this.setRotation(this.Side2C, 0.0f, 0.0f, 0.0f);
        this.Side2D = new ModelRenderer((ModelBase)this, 0, 16);
        this.Side2D.func_78789_a(0.0f, 0.0f, 0.0f, 4, 8, 4);
        this.Side2D.func_78793_a(4.0f, 12.0f, -8.0f);
        this.Side2D.func_78787_b(64, 64);
        this.Side2D.field_78809_i = true;
        this.setRotation(this.Side2D, 0.0f, 0.0f, 0.0f);
        this.Side3A = new ModelRenderer((ModelBase)this, 0, 28);
        this.Side3A.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 8);
        this.Side3A.func_78793_a(-8.0f, 8.0f, -4.0f);
        this.Side3A.func_78787_b(64, 64);
        this.Side3A.field_78809_i = true;
        this.setRotation(this.Side3A, 0.0f, 0.0f, 0.0f);
        this.Side3B = new ModelRenderer((ModelBase)this, 0, 28);
        this.Side3B.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 8);
        this.Side3B.func_78793_a(4.0f, 8.0f, -4.0f);
        this.Side3B.func_78787_b(64, 64);
        this.Side3B.field_78809_i = true;
        this.setRotation(this.Side3B, 0.0f, 0.0f, 0.0f);
        this.Side3C = new ModelRenderer((ModelBase)this, 0, 28);
        this.Side3C.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 8);
        this.Side3C.func_78793_a(-8.0f, 20.0f, -4.0f);
        this.Side3C.func_78787_b(64, 64);
        this.Side3C.field_78809_i = true;
        this.setRotation(this.Side3C, 0.0f, 0.0f, 0.0f);
        this.Side3D = new ModelRenderer((ModelBase)this, 0, 28);
        this.Side3D.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 8);
        this.Side3D.func_78793_a(4.0f, 20.0f, -4.0f);
        this.Side3D.func_78787_b(64, 64);
        this.Side3D.field_78809_i = true;
        this.setRotation(this.Side3D, 0.0f, 0.0f, 0.0f);
        this.Keystone = new ModelRenderer((ModelBase)this, 24, 0);
        this.Keystone.func_78789_a(0.0f, 0.0f, 0.0f, 8, 8, 8);
        this.Keystone.func_78793_a(-4.0f, 12.0f, -4.0f);
        this.Keystone.func_78787_b(64, 64);
        this.Keystone.field_78809_i = true;
        this.setRotation(this.Keystone, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, boolean keystone, int plane) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.Corner1.func_78785_a(f5);
        this.Corner2.func_78785_a(f5);
        this.Corner3.func_78785_a(f5);
        this.Corner4.func_78785_a(f5);
        this.Side1A.func_78785_a(f5);
        this.Corner5.func_78785_a(f5);
        this.Corner6.func_78785_a(f5);
        this.Corner7.func_78785_a(f5);
        this.Corner8.func_78785_a(f5);
        this.Side1B.func_78785_a(f5);
        this.Side1C.func_78785_a(f5);
        this.Side1D.func_78785_a(f5);
        this.Side2A.func_78785_a(f5);
        this.Side2B.func_78785_a(f5);
        this.Side2C.func_78785_a(f5);
        this.Side2D.func_78785_a(f5);
        this.Side3A.func_78785_a(f5);
        this.Side3B.func_78785_a(f5);
        this.Side3C.func_78785_a(f5);
        this.Side3D.func_78785_a(f5);
        if (keystone) {
            Color col = new Color(PocketPlaneData.planes.get((int)plane).color);
            GL11.glColor4f((float)((float)col.getRed() / 255.0f), (float)((float)col.getGreen() / 255.0f), (float)((float)col.getBlue() / 255.0f), (float)1.0f);
            this.Keystone.func_78785_a(f5);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, null);
    }
}

