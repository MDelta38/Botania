/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 */
package com.emoniph.witchery.client.model;

import com.emoniph.witchery.blocks.BlockDistillery;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

@SideOnly(value=Side.CLIENT)
public class ModelDistillery
extends ModelBase {
    ModelRenderer stillBase;
    ModelRenderer stillMiddle;
    ModelRenderer stillTop;
    ModelRenderer stillBend;
    ModelRenderer stillTube;
    ModelRenderer frameTop;
    ModelRenderer frameLeft;
    ModelRenderer frameRight;
    ModelRenderer frameBase;
    ModelRenderer bottle1;
    ModelRenderer bottle2;
    ModelRenderer bottle3;
    ModelRenderer bottle4;

    public ModelDistillery() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.func_78085_a("bottle1.bottle1Body", 52, 26);
        this.func_78085_a("bottle1.bottle1Neck", 60, 24);
        this.func_78085_a("bottle1.bottle1Top", 56, 21);
        this.func_78085_a("bottle2.bottle2Body", 52, 26);
        this.func_78085_a("bottle2.bottle2Neck", 60, 24);
        this.func_78085_a("bottle2.bottle2Top", 56, 21);
        this.func_78085_a("bottle3.bottle3Body", 52, 26);
        this.func_78085_a("bottle3.bottle3Neck", 60, 24);
        this.func_78085_a("bottle3.bottle3Top", 56, 21);
        this.func_78085_a("bottle4.bottle4Body", 52, 26);
        this.func_78085_a("bottle4.bottle4Neck", 60, 24);
        this.func_78085_a("bottle4.bottle4Top", 56, 21);
        this.stillBase = new ModelRenderer((ModelBase)this, 0, 16);
        this.stillBase.func_78789_a(0.0f, 0.0f, 0.0f, 10, 6, 10);
        this.stillBase.func_78793_a(-5.0f, 18.0f, -2.0f);
        this.stillBase.func_78787_b(64, 64);
        this.stillBase.field_78809_i = true;
        this.setRotation(this.stillBase, 0.0f, 0.0f, 0.0f);
        this.stillMiddle = new ModelRenderer((ModelBase)this, 0, 6);
        this.stillMiddle.func_78789_a(0.0f, 0.0f, 0.0f, 6, 4, 6);
        this.stillMiddle.func_78793_a(-3.0f, 14.0f, 0.0f);
        this.stillMiddle.func_78787_b(64, 64);
        this.stillMiddle.field_78809_i = true;
        this.setRotation(this.stillMiddle, 0.0f, 0.0f, 0.0f);
        this.stillTop = new ModelRenderer((ModelBase)this, 25, 9);
        this.stillTop.func_78789_a(0.0f, 0.0f, 0.0f, 4, 3, 4);
        this.stillTop.func_78793_a(-2.0f, 11.0f, 1.0f);
        this.stillTop.func_78787_b(64, 64);
        this.stillTop.field_78809_i = true;
        this.setRotation(this.stillTop, 0.0f, 0.0f, 0.0f);
        this.stillBend = new ModelRenderer((ModelBase)this, 0, 0);
        this.stillBend.func_78789_a(0.0f, 0.0f, 0.0f, 2, 2, 4);
        this.stillBend.func_78793_a(-1.0f, 9.0f, -1.0f);
        this.stillBend.func_78787_b(64, 64);
        this.stillBend.field_78809_i = true;
        this.setRotation(this.stillBend, 0.0f, 0.0f, 0.0f);
        this.stillTube = new ModelRenderer((ModelBase)this, 46, 10);
        this.stillTube.func_78789_a(-0.5f, -0.5f, 0.0f, 1, 1, 8);
        this.stillTube.func_78793_a(0.0f, 10.0f, 0.0f);
        this.stillTube.func_78787_b(64, 64);
        this.stillTube.field_78809_i = true;
        this.setRotation(this.stillTube, -2.341978f, 0.0f, 0.0f);
        this.frameTop = new ModelRenderer((ModelBase)this, 30, 6);
        this.frameTop.func_78789_a(0.0f, 0.0f, 0.0f, 16, 1, 1);
        this.frameTop.func_78793_a(-8.0f, 15.0f, -6.0f);
        this.frameTop.func_78787_b(64, 64);
        this.frameTop.field_78809_i = true;
        this.setRotation(this.frameTop, 0.0f, 0.0f, 0.0f);
        this.frameLeft = new ModelRenderer((ModelBase)this, 47, 24);
        this.frameLeft.func_78789_a(0.0f, 0.0f, 0.0f, 1, 7, 1);
        this.frameLeft.func_78793_a(-8.0f, 16.0f, -6.0f);
        this.frameLeft.func_78787_b(64, 64);
        this.frameLeft.field_78809_i = true;
        this.setRotation(this.frameLeft, 0.0f, 0.0f, 0.0f);
        this.frameRight = new ModelRenderer((ModelBase)this, 47, 24);
        this.frameRight.func_78789_a(0.0f, 0.0f, 0.0f, 1, 7, 1);
        this.frameRight.func_78793_a(7.0f, 16.0f, -6.0f);
        this.frameRight.func_78787_b(64, 64);
        this.frameRight.field_78809_i = true;
        this.setRotation(this.frameRight, 0.0f, 0.0f, 0.0f);
        this.frameBase = new ModelRenderer((ModelBase)this, 22, 0);
        this.frameBase.func_78789_a(0.0f, 0.0f, 0.0f, 16, 1, 5);
        this.frameBase.func_78793_a(-8.0f, 23.0f, -8.0f);
        this.frameBase.func_78787_b(64, 64);
        this.frameBase.field_78809_i = true;
        this.setRotation(this.frameBase, 0.0f, 0.0f, 0.0f);
        this.bottle1 = new ModelRenderer((ModelBase)this, "bottle1");
        this.bottle1.func_78793_a(-7.0f, 16.0f, -7.0f);
        this.setRotation(this.bottle1, 0.0f, 0.0f, 0.0f);
        this.bottle1.field_78809_i = true;
        this.bottle1.func_78786_a("bottle1Body", 0.0f, 2.0f, 0.0f, 3, 3, 3);
        this.bottle1.func_78786_a("bottle1Neck", 1.0f, 1.0f, 1.0f, 1, 1, 1);
        this.bottle1.func_78786_a("bottle1Top", 0.5f, 0.0f, 0.5f, 2, 1, 2);
        this.bottle2 = new ModelRenderer((ModelBase)this, "bottle2");
        this.bottle2.func_78793_a(-3.3f, 16.0f, -7.0f);
        this.setRotation(this.bottle2, 0.0174533f, 0.0f, 0.0f);
        this.bottle2.field_78809_i = true;
        this.bottle2.func_78786_a("bottle2Body", 0.0f, 2.0f, 0.0f, 3, 3, 3);
        this.bottle2.func_78786_a("bottle2Neck", 1.0f, 1.0f, 1.0f, 1, 1, 1);
        this.bottle2.func_78786_a("bottle2Top", 0.5f, 0.0f, 0.5f, 2, 1, 2);
        this.bottle3 = new ModelRenderer((ModelBase)this, "bottle3");
        this.bottle3.func_78793_a(0.4f, 16.0f, -7.0f);
        this.setRotation(this.bottle3, 0.0f, 0.0f, 0.0f);
        this.bottle3.field_78809_i = true;
        this.bottle3.func_78786_a("bottle3Body", 0.0f, 2.0f, 0.0f, 3, 3, 3);
        this.bottle3.func_78786_a("bottle3Neck", 1.0f, 1.0f, 1.0f, 1, 1, 1);
        this.bottle3.func_78786_a("bottle3Top", 0.5f, 0.0f, 0.5f, 2, 1, 2);
        this.bottle4 = new ModelRenderer((ModelBase)this, "bottle4");
        this.bottle4.func_78793_a(4.0f, 16.0f, -7.0f);
        this.setRotation(this.bottle4, 0.0f, 0.0f, 0.0f);
        this.bottle4.field_78809_i = true;
        this.bottle4.func_78786_a("bottle4Body", 0.0f, 2.0f, 0.0f, 3, 3, 3);
        this.bottle4.func_78786_a("bottle4Neck", 1.0f, 1.0f, 1.0f, 1, 1, 1);
        this.bottle4.func_78786_a("bottle4Top", 0.5f, 0.0f, 0.5f, 2, 1, 2);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, TileEntity tileEntity) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.stillBase.func_78785_a(f5);
        this.stillMiddle.func_78785_a(f5);
        this.stillTop.func_78785_a(f5);
        this.stillBend.func_78785_a(f5);
        this.stillTube.func_78785_a(f5);
        this.frameTop.func_78785_a(f5);
        this.frameLeft.func_78785_a(f5);
        this.frameRight.func_78785_a(f5);
        this.frameBase.func_78785_a(f5);
        if (tileEntity != null && tileEntity.func_145831_w() != null) {
            BlockDistillery.TileEntityDistillery te = (BlockDistillery.TileEntityDistillery)tileEntity;
            ModelRenderer[] bottles = new ModelRenderer[]{this.bottle1, this.bottle2, this.bottle3, this.bottle4};
            ItemStack jars = te.func_70301_a(2);
            if (jars != null) {
                for (int i = 0; i < jars.field_77994_a && i < bottles.length; ++i) {
                    bottles[i].func_78785_a(f5);
                }
            }
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

