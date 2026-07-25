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

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelSoulSieve
extends ModelBase {
    ModelRenderer TopSide1;
    ModelRenderer TopSide2;
    ModelRenderer TopSide3;
    ModelRenderer TopSide4;
    ModelRenderer TopBottom;
    ModelRenderer Middle;
    ModelRenderer Bottom;
    ModelRenderer Sand;
    ModelRenderer SieveSide4;
    ModelRenderer SieveSide3;
    ModelRenderer SieveSide2;
    ModelRenderer SieveSide1;
    ModelRenderer SieveCenter4;
    ModelRenderer SieveCenter3;
    ModelRenderer SieveCenter2;
    ModelRenderer SieveCenter1;
    ModelRenderer SieveOuter4B;
    ModelRenderer SieveOuter4A;
    ModelRenderer SieveOuter3B;
    ModelRenderer SieveOuter3A;
    ModelRenderer SieveOuter2A;
    ModelRenderer SieveOuter2B;
    ModelRenderer SieveOuter1B;
    ModelRenderer SieveOuter1A;

    public ModelSoulSieve() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.TopSide1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.TopSide1.func_78789_a(0.0f, 0.0f, 0.0f, 16, 6, 1);
        this.TopSide1.func_78793_a(-8.0f, 8.0f, 7.0f);
        this.TopSide1.func_78787_b(64, 32);
        this.TopSide1.field_78809_i = true;
        this.setRotation(this.TopSide1, 0.0f, 0.0f, 0.0f);
        this.TopSide2 = new ModelRenderer((ModelBase)this, 0, 9);
        this.TopSide2.func_78789_a(0.0f, 0.0f, 0.0f, 16, 6, 1);
        this.TopSide2.func_78793_a(-8.0f, 8.0f, -8.0f);
        this.TopSide2.func_78787_b(64, 32);
        this.TopSide2.field_78809_i = true;
        this.setRotation(this.TopSide2, 0.0f, 0.0f, 0.0f);
        this.TopSide3 = new ModelRenderer((ModelBase)this, 0, 44);
        this.TopSide3.func_78789_a(0.0f, 0.0f, 0.0f, 1, 6, 14);
        this.TopSide3.func_78793_a(7.0f, 8.0f, -7.0f);
        this.TopSide3.func_78787_b(64, 32);
        this.TopSide3.field_78809_i = true;
        this.setRotation(this.TopSide3, 0.0f, 0.0f, 0.0f);
        this.TopSide4 = new ModelRenderer((ModelBase)this, 0, 18);
        this.TopSide4.func_78789_a(0.0f, 0.0f, 0.0f, 1, 6, 14);
        this.TopSide4.func_78793_a(-8.0f, 8.0f, -7.0f);
        this.TopSide4.func_78787_b(64, 32);
        this.TopSide4.field_78809_i = true;
        this.setRotation(this.TopSide4, 0.0f, 0.0f, 0.0f);
        this.TopBottom = new ModelRenderer((ModelBase)this, 48, 0);
        this.TopBottom.func_78789_a(0.0f, 0.0f, 0.0f, 14, 1, 14);
        this.TopBottom.func_78793_a(-7.0f, 13.0f, -7.0f);
        this.TopBottom.func_78787_b(64, 32);
        this.TopBottom.field_78809_i = true;
        this.setRotation(this.TopBottom, 0.0f, 0.0f, 0.0f);
        this.Middle = new ModelRenderer((ModelBase)this, 32, 32);
        this.Middle.func_78789_a(0.0f, 0.0f, 0.0f, 8, 6, 8);
        this.Middle.func_78793_a(-4.0f, 14.0f, -4.0f);
        this.Middle.func_78787_b(64, 32);
        this.Middle.field_78809_i = true;
        this.setRotation(this.Middle, 0.0f, 0.0f, 0.0f);
        this.Bottom = new ModelRenderer((ModelBase)this, 32, 18);
        this.Bottom.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Bottom.func_78793_a(-2.0f, 20.0f, -2.0f);
        this.Bottom.func_78787_b(64, 32);
        this.Bottom.field_78809_i = true;
        this.setRotation(this.Bottom, 0.0f, 0.0f, 0.0f);
        this.Sand = new ModelRenderer((ModelBase)this, 72, 16);
        this.Sand.func_78789_a(0.0f, 0.0f, 0.0f, 14, 3, 14);
        this.Sand.func_78793_a(-7.0f, 10.0f, -7.0f);
        this.Sand.func_78787_b(64, 32);
        this.Sand.field_78809_i = true;
        this.setRotation(this.Sand, 0.0f, 0.0f, 0.0f);
        this.SieveSide4 = new ModelRenderer((ModelBase)this, 64, 36);
        this.SieveSide4.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 5);
        this.SieveSide4.func_78793_a(-1.0f, 8.0f, 2.0f);
        this.SieveSide4.func_78787_b(64, 32);
        this.SieveSide4.field_78809_i = true;
        this.setRotation(this.SieveSide4, 0.0f, 0.0f, 0.0f);
        this.SieveSide3 = new ModelRenderer((ModelBase)this, 64, 36);
        this.SieveSide3.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 5);
        this.SieveSide3.func_78793_a(-1.0f, 8.0f, -7.0f);
        this.SieveSide3.func_78787_b(64, 32);
        this.SieveSide3.field_78809_i = true;
        this.setRotation(this.SieveSide3, 0.0f, 0.0f, 0.0f);
        this.SieveSide2 = new ModelRenderer((ModelBase)this, 48, 18);
        this.SieveSide2.func_78789_a(0.0f, 0.0f, 0.0f, 5, 1, 2);
        this.SieveSide2.func_78793_a(-7.0f, 8.0f, -1.0f);
        this.SieveSide2.func_78787_b(64, 32);
        this.SieveSide2.field_78809_i = true;
        this.setRotation(this.SieveSide2, 0.0f, 0.0f, 0.0f);
        this.SieveSide1 = new ModelRenderer((ModelBase)this, 48, 18);
        this.SieveSide1.func_78789_a(0.0f, 0.0f, 0.0f, 5, 1, 2);
        this.SieveSide1.func_78793_a(2.0f, 8.0f, -1.0f);
        this.SieveSide1.func_78787_b(64, 32);
        this.SieveSide1.field_78809_i = true;
        this.setRotation(this.SieveSide1, 0.0f, 0.0f, 0.0f);
        this.SieveCenter4 = new ModelRenderer((ModelBase)this, 36, 0);
        this.SieveCenter4.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 2);
        this.SieveCenter4.func_78793_a(1.0f, 8.0f, -1.0f);
        this.SieveCenter4.func_78787_b(64, 32);
        this.SieveCenter4.field_78809_i = true;
        this.setRotation(this.SieveCenter4, 0.0f, 0.0f, 0.0f);
        this.SieveCenter3 = new ModelRenderer((ModelBase)this, 36, 0);
        this.SieveCenter3.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 2);
        this.SieveCenter3.func_78793_a(-2.0f, 8.0f, -1.0f);
        this.SieveCenter3.func_78787_b(64, 32);
        this.SieveCenter3.field_78809_i = true;
        this.setRotation(this.SieveCenter3, 0.0f, 0.0f, 0.0f);
        this.SieveCenter2 = new ModelRenderer((ModelBase)this, 32, 28);
        this.SieveCenter2.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 1);
        this.SieveCenter2.func_78793_a(-2.0f, 8.0f, 1.0f);
        this.SieveCenter2.func_78787_b(64, 32);
        this.SieveCenter2.field_78809_i = true;
        this.setRotation(this.SieveCenter2, 0.0f, 0.0f, 0.0f);
        this.SieveCenter1 = new ModelRenderer((ModelBase)this, 32, 28);
        this.SieveCenter1.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 1);
        this.SieveCenter1.func_78793_a(-2.0f, 8.0f, -2.0f);
        this.SieveCenter1.func_78787_b(64, 32);
        this.SieveCenter1.field_78809_i = true;
        this.setRotation(this.SieveCenter1, 0.0f, 0.0f, 0.0f);
        this.SieveOuter4B = new ModelRenderer((ModelBase)this, 62, 24);
        this.SieveOuter4B.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 1);
        this.SieveOuter4B.func_78793_a(-5.0f, 8.0f, -5.0f);
        this.SieveOuter4B.func_78787_b(128, 64);
        this.SieveOuter4B.field_78809_i = true;
        this.setRotation(this.SieveOuter4B, 0.0f, 0.0f, 0.0f);
        this.SieveOuter4A = new ModelRenderer((ModelBase)this, 64, 32);
        this.SieveOuter4A.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 3);
        this.SieveOuter4A.func_78793_a(-5.0f, 8.0f, -4.0f);
        this.SieveOuter4A.func_78787_b(128, 64);
        this.SieveOuter4A.field_78809_i = true;
        this.setRotation(this.SieveOuter4A, 0.0f, 0.0f, 0.0f);
        this.SieveOuter3B = new ModelRenderer((ModelBase)this, 62, 24);
        this.SieveOuter3B.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 1);
        this.SieveOuter3B.func_78793_a(1.0f, 8.0f, -5.0f);
        this.SieveOuter3B.func_78787_b(128, 64);
        this.SieveOuter3B.field_78809_i = true;
        this.setRotation(this.SieveOuter3B, 0.0f, 0.0f, 0.0f);
        this.SieveOuter3A = new ModelRenderer((ModelBase)this, 64, 32);
        this.SieveOuter3A.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 3);
        this.SieveOuter3A.func_78793_a(4.0f, 8.0f, -4.0f);
        this.SieveOuter3A.func_78787_b(128, 64);
        this.SieveOuter3A.field_78809_i = true;
        this.setRotation(this.SieveOuter3A, 0.0f, 0.0f, 0.0f);
        this.SieveOuter2A = new ModelRenderer((ModelBase)this, 64, 32);
        this.SieveOuter2A.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 3);
        this.SieveOuter2A.func_78793_a(4.0f, 8.0f, 1.0f);
        this.SieveOuter2A.func_78787_b(128, 64);
        this.SieveOuter2A.field_78809_i = true;
        this.setRotation(this.SieveOuter2A, 0.0f, 0.0f, 0.0f);
        this.SieveOuter2B = new ModelRenderer((ModelBase)this, 62, 24);
        this.SieveOuter2B.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 1);
        this.SieveOuter2B.func_78793_a(1.0f, 8.0f, 4.0f);
        this.SieveOuter2B.func_78787_b(128, 64);
        this.SieveOuter2B.field_78809_i = true;
        this.setRotation(this.SieveOuter2B, 0.0f, 0.0f, 0.0f);
        this.SieveOuter1B = new ModelRenderer((ModelBase)this, 62, 24);
        this.SieveOuter1B.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 1);
        this.SieveOuter1B.func_78793_a(-5.0f, 8.0f, 4.0f);
        this.SieveOuter1B.func_78787_b(128, 64);
        this.SieveOuter1B.field_78809_i = true;
        this.setRotation(this.SieveOuter1B, 0.0f, 0.0f, 0.0f);
        this.SieveOuter1A = new ModelRenderer((ModelBase)this, 64, 32);
        this.SieveOuter1A.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 3);
        this.SieveOuter1A.func_78793_a(-5.0f, 8.0f, 1.0f);
        this.SieveOuter1A.func_78787_b(128, 64);
        this.SieveOuter1A.field_78809_i = true;
        this.setRotation(this.SieveOuter1A, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, float sieveMovement, float sandScale) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.TopSide1.func_78785_a(f5);
        this.TopSide2.func_78785_a(f5);
        this.TopSide3.func_78785_a(f5);
        this.TopSide4.func_78785_a(f5);
        this.TopBottom.func_78785_a(f5);
        this.Middle.func_78785_a(f5);
        this.Bottom.func_78785_a(f5);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)(-sieveMovement), (float)0.0f);
        this.SieveSide4.func_78785_a(f5);
        this.SieveSide3.func_78785_a(f5);
        this.SieveSide2.func_78785_a(f5);
        this.SieveSide1.func_78785_a(f5);
        this.SieveCenter4.func_78785_a(f5);
        this.SieveCenter3.func_78785_a(f5);
        this.SieveCenter2.func_78785_a(f5);
        this.SieveCenter1.func_78785_a(f5);
        this.SieveOuter4B.func_78785_a(f5);
        this.SieveOuter4A.func_78785_a(f5);
        this.SieveOuter3B.func_78785_a(f5);
        this.SieveOuter3A.func_78785_a(f5);
        this.SieveOuter2A.func_78785_a(f5);
        this.SieveOuter2B.func_78785_a(f5);
        this.SieveOuter1B.func_78785_a(f5);
        this.SieveOuter1A.func_78785_a(f5);
        GL11.glPopMatrix();
        if (sandScale > 0.0f) {
            this.Sand.func_78785_a(f5);
        }
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

