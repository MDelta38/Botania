/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntitySummonedUndead;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(value=Side.CLIENT)
public class ModelSpectre
extends ModelBase {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer robeUpper;
    ModelRenderer robeLower;
    ModelRenderer mouth;
    private final boolean reachingArms;

    public ModelSpectre(boolean reachingArms) {
        this.reachingArms = reachingArms;
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.head = new ModelRenderer((ModelBase)this, 0, 16);
        this.head.func_78789_a(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.head.func_78787_b(64, 32);
        this.head.field_78809_i = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.mouth = new ModelRenderer((ModelBase)this, 56, 0);
        this.mouth.func_78789_a(0.0f, 0.0f, 0.0f, 4, 5, 0);
        this.mouth.func_78793_a(-2.0f, -4.0f, -4.02f);
        this.mouth.func_78787_b(64, 32);
        this.mouth.field_78809_i = true;
        this.setRotation(this.mouth, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 16, 0);
        this.body.func_78789_a(-4.0f, 0.0f, -2.0f, 8, 10, 4);
        this.body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.body.func_78787_b(64, 32);
        this.body.field_78809_i = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 0, 0);
        this.rightarm.func_78789_a(-3.0f, -2.0f, -2.0f, 4, 12, 4);
        this.rightarm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.rightarm.func_78787_b(64, 32);
        this.rightarm.field_78809_i = true;
        this.setRotation(this.rightarm, -1.396263f, 0.0f, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 0, 0);
        this.leftarm.func_78789_a(-1.0f, -2.0f, -2.0f, 4, 12, 4);
        this.leftarm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.leftarm.func_78787_b(64, 32);
        this.leftarm.field_78809_i = true;
        this.setRotation(this.leftarm, -1.396263f, 0.0f, 0.0f);
        this.robeUpper = new ModelRenderer((ModelBase)this, 38, 9);
        this.robeUpper.func_78789_a(-4.0f, 0.0f, -2.0f, 8, 6, 5);
        this.robeUpper.func_78793_a(0.0f, 10.0f, 0.0f);
        this.robeUpper.func_78787_b(64, 32);
        this.robeUpper.field_78809_i = true;
        this.setRotation(this.robeUpper, 0.0f, 0.0f, 0.0f);
        this.robeLower = new ModelRenderer((ModelBase)this, 32, 20);
        this.robeLower.func_78789_a(-5.0f, 0.0f, -2.0f, 10, 6, 6);
        this.robeLower.func_78793_a(0.0f, 16.0f, 0.0f);
        this.robeLower.func_78787_b(64, 32);
        this.robeLower.field_78809_i = true;
        this.setRotation(this.robeLower, 0.0f, 0.0f, 0.0f);
        this.head.func_78792_a(this.mouth);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        boolean screaming = entity != null && entity instanceof EntitySummonedUndead && ((EntitySummonedUndead)entity).isScreaming();
        this.mouth.field_78807_k = !screaming;
        this.mouth.func_78793_a(-2.0f, -4.0f, -4.02f);
        this.head.func_78785_a(f5);
        this.body.func_78785_a(f5);
        this.rightarm.func_78785_a(f5);
        this.leftarm.func_78785_a(f5);
        this.robeUpper.func_78785_a(f5);
        this.robeLower.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        super.func_78087_a(par1, par2, par3, par4, par5, par6, entity);
        this.head.field_78796_g = par4 / 57.295776f;
        this.head.field_78795_f = par5 / 57.295776f;
        if (this.reachingArms) {
            this.rightarm.field_78795_f = -1.5f;
            this.leftarm.field_78795_f = -1.5f;
        } else {
            if (entity != null && entity instanceof EntitySummonedUndead && ((EntitySummonedUndead)entity).isScreaming()) {
                this.rightarm.field_78808_h = 1.0f;
                this.leftarm.field_78808_h = -1.0f;
            } else {
                this.rightarm.field_78808_h = 0.0f;
                this.leftarm.field_78808_h = 0.0f;
            }
            this.rightarm.field_78795_f = -0.2f;
            this.leftarm.field_78795_f = -0.2f;
        }
        this.rightarm.field_78796_g = 0.0f;
        this.leftarm.field_78796_g = 0.0f;
        this.rightarm.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        this.leftarm.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
    }
}

