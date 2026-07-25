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

import com.emoniph.witchery.entity.EntityBroom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class ModelBroom
extends ModelBase {
    ModelRenderer handle;
    ModelRenderer bristle1;
    ModelRenderer bristle2;
    ModelRenderer bristle3;
    ModelRenderer bristle4;
    ModelRenderer bristle5;
    ModelRenderer bristle6;
    ModelRenderer bristle7;
    ModelRenderer bristle8;
    ModelRenderer bristle9;
    public static final float[][] fleeceColorTable = new float[][]{{1.0f, 1.0f, 1.0f}, {0.85f, 0.5f, 0.2f}, {0.7f, 0.3f, 0.85f}, {0.4f, 0.6f, 0.85f}, {0.9f, 0.9f, 0.2f}, {0.5f, 0.8f, 0.1f}, {0.95f, 0.5f, 0.65f}, {0.3f, 0.3f, 0.3f}, {0.6f, 0.6f, 0.6f}, {0.3f, 0.5f, 0.6f}, {0.5f, 0.25f, 0.7f}, {0.2f, 0.3f, 0.7f}, {0.4f, 0.3f, 0.2f}, {0.4f, 0.5f, 0.2f}, {0.6f, 0.2f, 0.2f}, {0.1f, 0.1f, 0.1f}};

    public ModelBroom() {
        this.field_78090_t = 32;
        this.field_78089_u = 32;
        this.handle = new ModelRenderer((ModelBase)this, 24, 0);
        this.handle.func_78789_a(-1.0f, -10.0f, -1.0f, 2, 24, 2);
        this.handle.func_78793_a(0.0f, 11.0f, -5.0f);
        this.handle.func_78787_b(32, 32);
        this.handle.field_78809_i = true;
        this.setRotation(this.handle, 1.570796f, 0.0f, 0.0f);
        this.bristle1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.bristle1.func_78789_a(-0.5f, -0.5f, 0.0f, 1, 1, 10);
        this.bristle1.func_78793_a(-1.0f, 10.0f, 9.0f);
        this.bristle1.func_78787_b(32, 32);
        this.bristle1.field_78809_i = true;
        this.setRotation(this.bristle1, 0.1858931f, -0.1487144f, 0.0f);
        this.bristle2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.bristle2.func_78789_a(-0.5f, -0.5f, 0.0f, 1, 1, 10);
        this.bristle2.func_78793_a(1.0f, 12.0f, 9.0f);
        this.bristle2.func_78787_b(32, 32);
        this.bristle2.field_78809_i = true;
        this.setRotation(this.bristle2, -0.1487144f, 0.1858931f, 0.0f);
        this.bristle3 = new ModelRenderer((ModelBase)this, 0, 12);
        this.bristle3.func_78789_a(-0.5f, -0.5f, 0.0f, 1, 1, 9);
        this.bristle3.func_78793_a(1.0f, 10.0f, 9.0f);
        this.bristle3.func_78787_b(32, 32);
        this.bristle3.field_78809_i = true;
        this.setRotation(this.bristle3, 0.2230717f, 0.1858931f, 0.0f);
        this.bristle4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.bristle4.func_78789_a(-0.5f, -0.5f, 0.0f, 1, 1, 10);
        this.bristle4.func_78793_a(0.0f, 10.0f, 9.0f);
        this.bristle4.func_78787_b(32, 32);
        this.bristle4.field_78809_i = true;
        this.setRotation(this.bristle4, 0.2230717f, 0.0743572f, 0.0f);
        this.bristle5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.bristle5.func_78789_a(-0.5f, -0.5f, 0.0f, 1, 1, 10);
        this.bristle5.func_78793_a(-1.0f, 12.0f, 9.0f);
        this.bristle5.func_78787_b(32, 32);
        this.bristle5.field_78809_i = true;
        this.setRotation(this.bristle5, -0.2230717f, -0.1487144f, 0.0f);
        this.bristle6 = new ModelRenderer((ModelBase)this, 0, 0);
        this.bristle6.func_78789_a(-0.5f, -0.5f, 0.0f, 1, 1, 10);
        this.bristle6.func_78793_a(0.0f, 11.0f, 9.0f);
        this.bristle6.func_78787_b(32, 32);
        this.bristle6.field_78809_i = true;
        this.setRotation(this.bristle6, -0.0371786f, 0.0743572f, 0.0f);
        this.bristle7 = new ModelRenderer((ModelBase)this, 0, 0);
        this.bristle7.func_78789_a(-0.5f, -0.5f, 0.0f, 1, 1, 10);
        this.bristle7.func_78793_a(1.0f, 11.0f, 9.0f);
        this.bristle7.func_78787_b(32, 32);
        this.bristle7.field_78809_i = true;
        this.setRotation(this.bristle7, -0.0371786f, 0.2230717f, 0.0f);
        this.bristle8 = new ModelRenderer((ModelBase)this, 0, 12);
        this.bristle8.func_78789_a(-0.5f, -0.5f, 0.0f, 1, 1, 9);
        this.bristle8.func_78793_a(-1.0f, 11.0f, 9.0f);
        this.bristle8.func_78787_b(32, 32);
        this.bristle8.field_78809_i = true;
        this.setRotation(this.bristle8, -0.0743572f, -0.1487144f, 0.0f);
        this.bristle9 = new ModelRenderer((ModelBase)this, 0, 12);
        this.bristle9.func_78789_a(-0.5333334f, -0.5f, 0.0f, 1, 1, 9);
        this.bristle9.func_78793_a(0.0f, 12.0f, 9.0f);
        this.bristle9.func_78787_b(32, 32);
        this.bristle9.field_78809_i = true;
        this.setRotation(this.bristle9, -0.1858931f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.handle.func_78785_a(f5);
        if (entity != null && entity instanceof EntityBroom) {
            int j = ((EntityBroom)entity).getBrushColor();
            if (j < 0 || j > 15) {
                j = 12;
            }
            GL11.glColor3f((float)fleeceColorTable[j][0], (float)fleeceColorTable[j][1], (float)fleeceColorTable[j][2]);
        }
        this.bristle1.func_78785_a(f5);
        this.bristle2.func_78785_a(f5);
        this.bristle3.func_78785_a(f5);
        this.bristle4.func_78785_a(f5);
        this.bristle5.func_78785_a(f5);
        this.bristle6.func_78785_a(f5);
        this.bristle7.func_78785_a(f5);
        this.bristle8.func_78785_a(f5);
        this.bristle9.func_78785_a(f5);
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

