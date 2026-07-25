/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 *  org.lwjgl.opengl.GL11
 */
package com.kentington.thaumichorizons.client.renderer.model;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntitySyringe;
import java.awt.Color;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class ModelSyringe
extends ModelBase {
    ModelRenderer Body;
    ModelRenderer Needle;
    ModelRenderer PlungerA;
    ModelRenderer PlungerB;

    public ModelSyringe() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.Body = new ModelRenderer((ModelBase)this, 0, 0);
        this.Body.func_78789_a(0.0f, 0.0f, 0.0f, 9, 3, 3);
        this.Body.func_78793_a(-3.0f, 21.0f, -1.0f);
        this.Body.func_78787_b(64, 32);
        this.Body.field_78809_i = true;
        this.setRotation(this.Body, 0.0f, 0.0f, 0.0f);
        this.Needle = new ModelRenderer((ModelBase)this, 25, 0);
        this.Needle.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 1);
        this.Needle.func_78793_a(-7.0f, 22.0f, 0.0f);
        this.Needle.func_78787_b(64, 32);
        this.Needle.field_78809_i = true;
        this.setRotation(this.Needle, 0.0f, 0.0f, 0.0f);
        this.PlungerA = new ModelRenderer((ModelBase)this, 0, 8);
        this.PlungerA.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.PlungerA.func_78793_a(6.0f, 22.0f, 0.0f);
        this.PlungerA.func_78787_b(64, 32);
        this.PlungerA.field_78809_i = true;
        this.setRotation(this.PlungerA, 0.0f, 0.0f, 0.0f);
        this.PlungerB = new ModelRenderer((ModelBase)this, 0, 12);
        this.PlungerB.func_78789_a(0.0f, 0.0f, 0.0f, 1, 3, 3);
        this.PlungerB.func_78793_a(7.0f, 21.0f, -1.0f);
        this.PlungerB.func_78787_b(64, 32);
        this.PlungerB.field_78809_i = true;
        this.setRotation(this.PlungerB, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, ItemStack item) {
        if (item != null) {
            Color col = new Color(ThaumicHorizons.itemSyringeInjection.func_82790_a(item, 0));
            float red = (float)col.getRed() / 255.0f;
            float green = (float)col.getGreen() / 255.0f;
            float blue = (float)col.getBlue() / 255.0f;
            if (item.func_77973_b() != ThaumicHorizons.itemSyringeEmpty) {
                GL11.glColor4f((float)red, (float)green, (float)blue, (float)0.75f);
            } else {
                GL11.glColor4f((float)red, (float)green, (float)blue, (float)0.5f);
            }
        } else if (entity != null && entity instanceof EntitySyringe) {
            Color col = new Color(((EntitySyringe)entity).color);
            float red = (float)col.getRed() / 255.0f;
            float green = (float)col.getGreen() / 255.0f;
            float blue = (float)col.getBlue() / 255.0f;
            GL11.glColor4f((float)red, (float)green, (float)blue, (float)0.75f);
            GL11.glTranslatef((float)0.0f, (float)-1.36f, (float)0.0f);
        }
        this.Body.func_78785_a(f5);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.Needle.func_78785_a(f5);
        this.PlungerA.func_78785_a(f5);
        this.PlungerB.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity ent) {
        this.Body.field_78795_f = ent.field_70125_A;
        this.Body.field_78796_g = ent.field_70177_z;
        this.Needle.field_78795_f = ent.field_70125_A;
        this.Needle.field_78796_g = ent.field_70177_z;
        this.PlungerA.field_78795_f = ent.field_70125_A;
        this.PlungerA.field_78796_g = ent.field_70177_z;
        this.PlungerB.field_78795_f = ent.field_70125_A;
        this.PlungerB.field_78796_g = ent.field_70177_z;
    }
}

