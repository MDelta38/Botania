/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package com.emoniph.witchery.client.model;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.item.ItemGeneral;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(value=Side.CLIENT)
public class ModelHandBow
extends ModelBase {
    ModelRenderer stockTop;
    ModelRenderer stockBottom;
    ModelRenderer stockCatch;
    ModelRenderer grip;
    ModelRenderer cross;
    ModelRenderer drawnCrossOuterR;
    ModelRenderer drawnCrossInnerR;
    ModelRenderer drawnCrossOuterL;
    ModelRenderer drawnCrossInnerL;
    ModelRenderer drawnStringInnerR;
    ModelRenderer drawnStringMidR;
    ModelRenderer drawnStringOuterR;
    ModelRenderer drawnStringInnerL;
    ModelRenderer drawnStringMidL;
    ModelRenderer drawnStringOuterL;
    ModelRenderer drawnStringCenter;
    ModelRenderer boltStake;
    ModelRenderer boltDraining;
    ModelRenderer boltHoly;
    ModelRenderer boltSplitting;
    ModelRenderer boltSplitting2;
    ModelRenderer boltSilver;

    public ModelHandBow() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.stockTop = new ModelRenderer((ModelBase)this, 2, 2);
        this.stockTop.func_78789_a(-1.0f, 0.0f, -5.0f, 2, 1, 7);
        this.stockTop.func_78793_a(0.0f, 0.0f, 0.0f);
        this.stockTop.func_78787_b(64, 32);
        this.stockTop.field_78809_i = true;
        this.setRotation(this.stockTop, 0.0f, 0.0f, 0.0f);
        this.stockBottom = new ModelRenderer((ModelBase)this, 0, 10);
        this.stockBottom.func_78789_a(-0.5f, 0.0f, -6.0f, 1, 1, 8);
        this.stockBottom.func_78793_a(0.0f, 1.0f, 0.0f);
        this.stockBottom.func_78787_b(64, 32);
        this.stockBottom.field_78809_i = true;
        this.setRotation(this.stockBottom, 0.0f, 0.0f, 0.0f);
        this.stockCatch = new ModelRenderer((ModelBase)this, 1, 11);
        this.stockCatch.func_78789_a(-0.5f, 0.0f, 0.0f, 1, 1, 1);
        this.stockCatch.func_78793_a(0.0f, -1.0f, 1.0f);
        this.stockCatch.func_78787_b(64, 32);
        this.stockCatch.field_78809_i = true;
        this.setRotation(this.stockCatch, 0.0f, 0.0f, 0.0f);
        this.grip = new ModelRenderer((ModelBase)this, 0, 3);
        this.grip.func_78789_a(-0.5f, 0.0f, -1.0f, 1, 3, 2);
        this.grip.func_78793_a(0.0f, 2.0f, 0.0f);
        this.grip.func_78787_b(64, 32);
        this.grip.field_78809_i = true;
        this.setRotation(this.grip, 0.0f, 0.0f, 0.0f);
        this.cross = new ModelRenderer((ModelBase)this, 1, 19);
        this.cross.func_78789_a(-3.0f, 0.0f, 0.0f, 6, 1, 2);
        this.cross.func_78793_a(0.0f, 0.0f, -7.0f);
        this.cross.func_78787_b(64, 32);
        this.cross.field_78809_i = true;
        this.setRotation(this.cross, 0.0f, 0.0f, 0.0f);
        this.drawnCrossOuterR = new ModelRenderer((ModelBase)this, 0, 14);
        this.drawnCrossOuterR.func_78789_a(-1.0f, 0.0f, 0.0f, 1, 1, 2);
        this.drawnCrossOuterR.func_78793_a(-4.0f, 0.0f, -4.0f);
        this.drawnCrossOuterR.func_78787_b(64, 32);
        this.drawnCrossOuterR.field_78809_i = true;
        this.setRotation(this.drawnCrossOuterR, 0.0f, 0.0f, 0.0f);
        this.drawnCrossInnerR = new ModelRenderer((ModelBase)this, 0, 14);
        this.drawnCrossInnerR.func_78789_a(-1.0f, 0.0f, 0.0f, 1, 1, 2);
        this.drawnCrossInnerR.func_78793_a(-3.0f, 0.0f, -6.0f);
        this.drawnCrossInnerR.func_78787_b(64, 32);
        this.drawnCrossInnerR.field_78809_i = true;
        this.setRotation(this.drawnCrossInnerR, 0.0f, 0.0f, 0.0f);
        this.drawnCrossOuterL = new ModelRenderer((ModelBase)this, 0, 14);
        this.drawnCrossOuterL.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 2);
        this.drawnCrossOuterL.func_78793_a(4.0f, 0.0f, -4.0f);
        this.drawnCrossOuterL.func_78787_b(64, 32);
        this.drawnCrossOuterL.field_78809_i = true;
        this.setRotation(this.drawnCrossOuterL, 0.0f, 0.0f, 0.0f);
        this.drawnCrossInnerL = new ModelRenderer((ModelBase)this, 0, 14);
        this.drawnCrossInnerL.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 2);
        this.drawnCrossInnerL.func_78793_a(3.0f, 0.0f, -6.0f);
        this.drawnCrossInnerL.func_78787_b(64, 32);
        this.drawnCrossInnerL.field_78809_i = true;
        this.setRotation(this.drawnCrossInnerL, 0.0f, 0.0f, 0.0f);
        this.drawnStringInnerR = new ModelRenderer((ModelBase)this, 0, 0);
        this.drawnStringInnerR.func_78789_a(-1.0f, 0.0f, 0.0f, 1, 1, 1);
        this.drawnStringInnerR.func_78793_a(-2.0f, 0.0f, -1.0f);
        this.drawnStringInnerR.func_78787_b(64, 32);
        this.drawnStringInnerR.field_78809_i = true;
        this.setRotation(this.drawnStringInnerR, 0.0f, 0.0f, 0.0f);
        this.drawnStringMidR = new ModelRenderer((ModelBase)this, 0, 0);
        this.drawnStringMidR.func_78789_a(-1.0f, 0.0f, 0.0f, 1, 1, 1);
        this.drawnStringMidR.func_78793_a(-1.0f, 0.0f, 0.0f);
        this.drawnStringMidR.func_78787_b(64, 32);
        this.drawnStringMidR.field_78809_i = true;
        this.setRotation(this.drawnStringMidR, 0.0f, 0.0f, 0.0f);
        this.drawnStringOuterR = new ModelRenderer((ModelBase)this, 0, 0);
        this.drawnStringOuterR.func_78789_a(-1.0f, 0.0f, 0.0f, 1, 1, 1);
        this.drawnStringOuterR.func_78793_a(-3.0f, 0.0f, -2.0f);
        this.drawnStringOuterR.func_78787_b(64, 32);
        this.drawnStringOuterR.field_78809_i = true;
        this.setRotation(this.drawnStringOuterR, 0.0f, 0.0f, 0.0f);
        this.drawnStringInnerL = new ModelRenderer((ModelBase)this, 0, 0);
        this.drawnStringInnerL.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.drawnStringInnerL.func_78793_a(2.0f, 0.0f, -1.0f);
        this.drawnStringInnerL.func_78787_b(64, 32);
        this.drawnStringInnerL.field_78809_i = true;
        this.setRotation(this.drawnStringInnerL, 0.0f, 0.0f, 0.0f);
        this.drawnStringMidL = new ModelRenderer((ModelBase)this, 0, 0);
        this.drawnStringMidL.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.drawnStringMidL.func_78793_a(1.0f, 0.0f, 0.0f);
        this.drawnStringMidL.func_78787_b(64, 32);
        this.drawnStringMidL.field_78809_i = true;
        this.setRotation(this.drawnStringMidL, 0.0f, 0.0f, 0.0f);
        this.drawnStringOuterL = new ModelRenderer((ModelBase)this, 0, 0);
        this.drawnStringOuterL.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.drawnStringOuterL.func_78793_a(3.0f, 0.0f, -2.0f);
        this.drawnStringOuterL.func_78787_b(64, 32);
        this.drawnStringOuterL.field_78809_i = true;
        this.setRotation(this.drawnStringOuterL, 0.0f, 0.0f, 0.0f);
        this.drawnStringCenter = new ModelRenderer((ModelBase)this, 4, 0);
        this.drawnStringCenter.func_78789_a(-1.5f, 0.0f, -0.5f, 3, 1, 1);
        this.drawnStringCenter.func_78793_a(0.0f, -0.1f, 1.0f);
        this.drawnStringCenter.func_78787_b(64, 32);
        this.drawnStringCenter.field_78809_i = true;
        this.setRotation(this.drawnStringCenter, 0.0f, 0.0174533f, 0.0f);
        this.boltStake = new ModelRenderer((ModelBase)this, 0, 22);
        this.boltStake.func_78789_a(-0.5f, 0.5f, -6.0f, 1, 1, 9);
        this.boltStake.func_78793_a(0.0f, -1.0f, -2.0f);
        this.boltStake.func_78787_b(64, 32);
        this.boltStake.field_78809_i = true;
        this.setRotation(this.boltStake, 0.0f, 0.0f, 0.0f);
        this.boltDraining = new ModelRenderer((ModelBase)this, 20, 22);
        this.boltDraining.func_78789_a(-0.5f, 0.5f, -6.0f, 1, 1, 9);
        this.boltDraining.func_78793_a(0.0f, -1.0f, -2.0f);
        this.boltDraining.func_78787_b(64, 32);
        this.boltDraining.field_78809_i = true;
        this.setRotation(this.boltDraining, 0.0f, 0.0f, 0.0f);
        this.boltHoly = new ModelRenderer((ModelBase)this, 40, 22);
        this.boltHoly.func_78789_a(-0.5f, 0.5f, -6.0f, 1, 1, 9);
        this.boltHoly.func_78793_a(0.0f, -1.0f, -2.0f);
        this.boltHoly.func_78787_b(64, 32);
        this.boltHoly.field_78809_i = true;
        this.setRotation(this.boltHoly, 0.0f, 0.0f, 0.0f);
        this.boltSplitting = new ModelRenderer((ModelBase)this, 20, 12);
        this.boltSplitting.func_78789_a(-0.5f, 0.5f, -6.0f, 1, 1, 9);
        this.boltSplitting.func_78793_a(0.0f, -1.0f, -2.0f);
        this.boltSplitting.func_78787_b(64, 32);
        this.boltSplitting.field_78809_i = true;
        this.setRotation(this.boltSplitting, 0.0f, 0.0f, 0.0f);
        this.boltSplitting2 = new ModelRenderer((ModelBase)this, 17, 11);
        this.boltSplitting2.func_78789_a(-0.5f, 0.5f, -6.0f, 2, 1, 4);
        this.boltSplitting2.func_78793_a(-0.5f, -1.5f, -1.0f);
        this.boltSplitting2.func_78787_b(64, 32);
        this.boltSplitting2.field_78809_i = true;
        this.setRotation(this.boltSplitting2, 0.0f, 0.0f, 0.0f);
        this.boltSilver = new ModelRenderer((ModelBase)this, 40, 12);
        this.boltSilver.func_78789_a(-0.5f, 0.5f, -6.0f, 1, 1, 9);
        this.boltSilver.func_78793_a(0.0f, -1.0f, -2.0f);
        this.boltSilver.func_78787_b(64, 32);
        this.boltSilver.field_78809_i = true;
        this.setRotation(this.boltSplitting, 0.0f, 0.0f, 0.0f);
        this.cross.field_78797_d = -0.3f;
        this.drawnCrossInnerR.field_78797_d = -0.15f;
        this.drawnCrossInnerL.field_78797_d = -0.15f;
        this.drawnStringMidR.field_78797_d = -0.1f;
        this.drawnStringMidL.field_78797_d = -0.1f;
        this.drawnStringInnerR.field_78797_d = -0.05f;
        this.drawnStringInnerL.field_78797_d = -0.05f;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, ItemGeneral.BoltType boltType, int useCount) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.stockTop.func_78785_a(f5);
        this.stockBottom.func_78785_a(f5);
        this.stockCatch.func_78785_a(f5);
        this.grip.func_78785_a(f5);
        this.cross.func_78785_a(f5);
        if (useCount > 10) {
            this.drawnCrossInnerR.field_78798_e = -6.0f;
            this.drawnCrossInnerL.field_78798_e = -6.0f;
            this.drawnCrossOuterR.field_78798_e = -4.0f;
            this.drawnCrossOuterL.field_78798_e = -4.0f;
            this.drawnStringInnerR.field_78798_e = -1.0f;
            this.drawnStringInnerL.field_78798_e = -1.0f;
            this.drawnStringMidR.field_78798_e = 0.0f;
            this.drawnStringMidL.field_78798_e = 0.0f;
            this.drawnStringOuterR.field_78798_e = -2.0f;
            this.drawnStringOuterL.field_78798_e = -2.0f;
            this.drawnStringCenter.field_78798_e = 1.0f;
        } else if (useCount > 5) {
            this.drawnCrossInnerR.field_78798_e = -6.0f;
            this.drawnCrossInnerL.field_78798_e = -6.0f;
            this.drawnCrossOuterR.field_78798_e = -5.0f;
            this.drawnCrossOuterL.field_78798_e = -5.0f;
            this.drawnStringInnerR.field_78798_e = -2.0f;
            this.drawnStringInnerL.field_78798_e = -2.0f;
            this.drawnStringMidR.field_78798_e = -2.0f;
            this.drawnStringMidL.field_78798_e = -2.0f;
            this.drawnStringOuterR.field_78798_e = -3.0f;
            this.drawnStringOuterL.field_78798_e = -3.0f;
            this.drawnStringCenter.field_78798_e = -1.0f;
        } else if (useCount == 0) {
            this.drawnCrossInnerR.field_78798_e = -7.0f;
            this.drawnCrossInnerL.field_78798_e = -7.0f;
            this.drawnCrossOuterR.field_78798_e = -6.0f;
            this.drawnCrossOuterL.field_78798_e = -6.0f;
            this.drawnStringInnerR.field_78798_e = -4.0f;
            this.drawnStringInnerL.field_78798_e = -4.0f;
            this.drawnStringMidR.field_78798_e = -4.0f;
            this.drawnStringMidL.field_78798_e = -4.0f;
            this.drawnStringOuterR.field_78798_e = -4.0f;
            this.drawnStringOuterL.field_78798_e = -4.0f;
            this.drawnStringCenter.field_78798_e = -3.25f;
        }
        this.drawnCrossOuterR.func_78785_a(f5);
        this.drawnCrossOuterL.func_78785_a(f5);
        this.drawnCrossInnerR.func_78785_a(f5);
        this.drawnCrossInnerL.func_78785_a(f5);
        this.drawnStringInnerR.func_78785_a(f5);
        this.drawnStringMidR.func_78785_a(f5);
        this.drawnStringOuterR.func_78785_a(f5);
        this.drawnStringInnerL.func_78785_a(f5);
        this.drawnStringMidL.func_78785_a(f5);
        this.drawnStringOuterL.func_78785_a(f5);
        this.drawnStringCenter.func_78785_a(f5);
        if (boltType == Witchery.Items.GENERIC.itemBoltStake) {
            this.boltStake.func_78785_a(f5);
        } else if (boltType == Witchery.Items.GENERIC.itemBoltAntiMagic) {
            this.boltDraining.func_78785_a(f5);
        } else if (boltType == Witchery.Items.GENERIC.itemBoltHoly) {
            this.boltHoly.func_78785_a(f5);
        } else if (boltType == Witchery.Items.GENERIC.itemBoltSilver) {
            this.boltSilver.func_78785_a(f5);
        } else if (boltType == Witchery.Items.GENERIC.itemBoltSplitting) {
            this.boltSplitting.func_78785_a(f5);
            this.boltSplitting2.func_78785_a(f5);
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

