/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 */
package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityMandrake;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

@SideOnly(value=Side.CLIENT)
public class ModelMandrake
extends ModelBase {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;

    public ModelMandrake() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.func_78085_a("head.face", 0, 8);
        this.func_78085_a("head.leaves", 0, 0);
        this.func_78085_a("body.bodyChest", 21, 0);
        this.func_78085_a("body.bodyBelly", 17, 7);
        this.head = new ModelRenderer((ModelBase)this, "head");
        this.head.func_78793_a(0.0f, 16.0f, 0.0f);
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.head.field_78809_i = true;
        this.head.func_78786_a("face", -2.0f, -4.0f, -2.0f, 4, 4, 4);
        this.head.func_78786_a("leaves", -4.0f, -12.0f, 0.0f, 8, 8, 0);
        this.body = new ModelRenderer((ModelBase)this, "body");
        this.body.func_78793_a(0.0f, 16.0f, 0.0f);
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.body.field_78809_i = true;
        this.body.func_78786_a("bodyChest", -2.5f, 0.0f, -2.5f, 5, 2, 5);
        this.body.func_78786_a("bodyBelly", -3.5f, 2.0f, -3.5f, 7, 3, 7);
        this.rightarm = new ModelRenderer((ModelBase)this, 37, 0);
        this.rightarm.func_78789_a(-1.0f, 0.0f, -0.5f, 1, 3, 1);
        this.rightarm.func_78793_a(-2.0f, 17.0f, 0.0f);
        this.rightarm.func_78787_b(64, 32);
        this.rightarm.field_78809_i = true;
        this.setRotation(this.rightarm, 0.0f, 0.0f, 1.047198f);
        this.leftarm = new ModelRenderer((ModelBase)this, 37, 0);
        this.leftarm.func_78789_a(0.0f, 0.0f, -0.5f, 1, 3, 1);
        this.leftarm.func_78793_a(2.0f, 17.0f, 0.0f);
        this.leftarm.func_78787_b(64, 32);
        this.leftarm.field_78809_i = true;
        this.setRotation(this.leftarm, 0.0f, 0.0f, -1.047198f);
        this.rightleg = new ModelRenderer((ModelBase)this, 27, 18);
        this.rightleg.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 3, 2);
        this.rightleg.func_78793_a(-1.0f, 21.0f, 0.0f);
        this.rightleg.func_78787_b(64, 32);
        this.rightleg.field_78809_i = true;
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 27, 18);
        this.leftleg.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 3, 2);
        this.leftleg.func_78793_a(1.0f, 21.0f, 0.0f);
        this.leftleg.func_78787_b(64, 32);
        this.leftleg.field_78809_i = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.head.func_78785_a(f5);
        this.body.func_78785_a(f5);
        this.rightarm.func_78785_a(f5);
        this.leftarm.func_78785_a(f5);
        this.rightleg.func_78785_a(f5);
        this.leftleg.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
    }

    public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
        EntityMandrake entityDemon = (EntityMandrake)par1EntityLiving;
        this.rightarm.field_78795_f = (-0.2f + 1.5f * this.func_78172_a(par2, 13.0f)) * par3;
        this.leftarm.field_78795_f = (-0.2f - 1.5f * this.func_78172_a(par2, 13.0f)) * par3;
    }

    private float func_78172_a(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5f) - par2 * 0.25f) / (par2 * 0.25f);
    }
}

