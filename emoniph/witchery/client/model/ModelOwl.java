/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityOwl;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class ModelOwl
extends ModelBase {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;

    public ModelOwl() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.func_78085_a("head.beak", 30, 0);
        this.func_78085_a("head.hornRight", 37, 0);
        this.func_78085_a("head.hornLeft", 37, 0);
        this.func_78085_a("head.head1", 0, 0);
        this.head = new ModelRenderer((ModelBase)this, "head");
        this.head.func_78793_a(0.0f, 15.0f, 0.0f);
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.head.field_78809_i = true;
        this.head.func_78786_a("hornRight", -5.0f, -7.0f, -1.0f, 1, 3, 2);
        this.head.func_78786_a("hornLeft", 4.0f, -7.0f, -1.0f, 1, 3, 2);
        this.head.func_78786_a("beak", -1.0f, -3.0f, -4.0f, 2, 3, 1);
        this.head.func_78786_a("head1", -4.0f, -6.0f, -3.0f, 8, 6, 6);
        this.body = new ModelRenderer((ModelBase)this, 16, 16);
        this.body.func_78789_a(-3.0f, 0.0f, -2.0f, 6, 8, 4);
        this.body.func_78793_a(0.0f, 15.0f, 0.0f);
        this.body.func_78787_b(64, 32);
        this.body.field_78809_i = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm.func_78789_a(-1.0f, -1.0f, -2.0f, 1, 8, 4);
        this.rightarm.func_78793_a(-3.0f, 16.0f, 0.0f);
        this.rightarm.func_78787_b(64, 32);
        this.rightarm.field_78809_i = true;
        this.setRotation(this.rightarm, 0.0f, 0.0f, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm.func_78789_a(0.0f, -1.0f, -2.0f, 1, 8, 4);
        this.leftarm.func_78793_a(3.0f, 16.0f, 0.0f);
        this.leftarm.func_78787_b(64, 32);
        this.leftarm.field_78809_i = true;
        this.setRotation(this.leftarm, 0.0f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg.func_78789_a(-1.0f, 0.0f, -2.0f, 2, 1, 4);
        this.rightleg.func_78793_a(-2.0f, 23.0f, -1.0f);
        this.rightleg.func_78787_b(64, 32);
        this.rightleg.field_78809_i = true;
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg.func_78789_a(-1.0f, 0.0f, -2.0f, 2, 1, 4);
        this.leftleg.func_78793_a(2.0f, 23.0f, -1.0f);
        this.leftleg.func_78787_b(64, 32);
        this.leftleg.field_78809_i = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.rightleg.func_78793_a(-2.0f, 8.0f, -1.0f);
        this.leftleg.func_78793_a(2.0f, 8.0f, -1.0f);
        this.body.func_78792_a(this.leftleg);
        this.body.func_78792_a(this.rightleg);
    }

    public static boolean isLanded(Entity entity) {
        Block block = entity.field_70170_p.func_147439_a(MathHelper.func_76128_c((double)entity.field_70165_t), (int)(entity.field_70163_u - 0.01), MathHelper.func_76128_c((double)entity.field_70161_v));
        Material material = block.func_149688_o();
        return material == Material.field_151584_j || material.func_76220_a();
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        EntityOwl entitybat = (EntityOwl)entity;
        if (entity.field_70181_x == 0.0 && entity.field_70159_w == 0.0 && entity.field_70179_y == 0.0 && ModelOwl.isLanded(entity)) {
            this.body.field_78795_f = 0.0f;
            this.leftarm.field_78808_h = 0.0f;
            this.rightarm.field_78808_h = 0.0f;
            this.rightleg.field_78795_f = 0.0f;
            this.leftleg.field_78795_f = 0.0f;
        } else {
            float f6 = 57.295776f;
            this.body.field_78795_f = 0.7853982f + MathHelper.func_76134_b((float)(f2 * 0.1f)) * 0.15f;
            this.body.field_78796_g = 0.0f;
            this.rightleg.field_78795_f = 0.7853982f + MathHelper.func_76134_b((float)(f2 * 0.1f)) * 0.15f;
            this.leftleg.field_78795_f = 0.7853982f + MathHelper.func_76134_b((float)(f2 * 0.1f)) * 0.15f;
            this.rightarm.field_78808_h = MathHelper.func_76134_b((float)(f2 * 0.5f)) * (float)Math.PI * 0.2f * 2.0f + 1.4f;
            this.leftarm.field_78808_h = -this.rightarm.field_78808_h;
        }
        if (entitybat.func_70906_o()) {
            this.rightleg.field_78796_g = 0.5f;
            this.leftleg.field_78796_g = -this.rightleg.field_78796_g;
        } else {
            this.rightleg.field_78796_g = 0.1f;
            this.leftleg.field_78796_g = -this.rightleg.field_78796_g;
        }
        this.head.field_78796_g = f3 / 57.295776f;
        this.head.field_78795_f = f4 / 57.295776f;
        if (this.field_78091_s) {
            float p6 = 2.0f;
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.5f / p6), (float)(1.5f / p6), (float)(1.5f / p6));
            GL11.glTranslatef((float)0.0f, (float)(11.0f * f5), (float)0.0f);
            this.head.func_78785_a(f5);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / p6), (float)(1.0f / p6), (float)(1.0f / p6));
            GL11.glTranslatef((float)0.0f, (float)(24.0f * f5), (float)0.0f);
            this.body.func_78785_a(f5);
            this.rightarm.func_78785_a(f5);
            this.leftarm.func_78785_a(f5);
            GL11.glPopMatrix();
        } else {
            this.head.func_78785_a(f5);
            this.body.func_78785_a(f5);
            this.rightarm.func_78785_a(f5);
            this.leftarm.func_78785_a(f5);
        }
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

