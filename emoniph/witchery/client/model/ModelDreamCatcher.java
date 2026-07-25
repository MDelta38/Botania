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
import com.emoniph.witchery.blocks.BlockDreamCatcher;
import com.emoniph.witchery.item.ItemGeneral;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(value=Side.CLIENT)
public class ModelDreamCatcher
extends ModelBase {
    final ModelRenderer frameLeft;
    final ModelRenderer frameRight;
    final ModelRenderer frameTop;
    final ModelRenderer frameBottom;
    final ModelRenderer[] nets;
    final ModelRenderer decoration;

    public ModelDreamCatcher() {
        this.field_78090_t = 32;
        this.field_78089_u = 32;
        this.frameLeft = new ModelRenderer((ModelBase)this, 0, 2);
        this.frameLeft.func_78789_a(0.0f, 0.0f, 0.0f, 1, 8, 1);
        this.frameLeft.func_78793_a(-4.0f, 10.0f, 7.0f);
        this.frameLeft.func_78787_b(32, 32);
        this.frameLeft.field_78809_i = true;
        this.setRotation(this.frameLeft, 0.0f, 0.0f, 0.0f);
        this.frameRight = new ModelRenderer((ModelBase)this, 0, 2);
        this.frameRight.func_78789_a(0.0f, 0.0f, 0.0f, 1, 8, 1);
        this.frameRight.func_78793_a(3.0f, 10.0f, 7.0f);
        this.frameRight.func_78787_b(32, 32);
        this.frameRight.field_78809_i = true;
        this.setRotation(this.frameRight, 0.0f, 0.0f, 0.0f);
        this.frameTop = new ModelRenderer((ModelBase)this, 0, 0);
        this.frameTop.func_78789_a(0.0f, 0.0f, 0.0f, 6, 1, 1);
        this.frameTop.func_78793_a(-3.0f, 10.0f, 7.0f);
        this.frameTop.func_78787_b(32, 32);
        this.frameTop.field_78809_i = true;
        this.setRotation(this.frameTop, 0.0f, 0.0f, 0.0f);
        this.frameBottom = new ModelRenderer((ModelBase)this, 0, 0);
        this.frameBottom.func_78789_a(0.0f, 0.0f, 0.0f, 6, 1, 1);
        this.frameBottom.func_78793_a(-3.0f, 17.0f, 7.0f);
        this.frameBottom.func_78787_b(32, 32);
        this.frameBottom.field_78809_i = true;
        this.setRotation(this.frameBottom, 0.0f, 0.0f, 0.0f);
        this.nets = new ModelRenderer[Witchery.Items.GENERIC.weaves.size()];
        for (int i = 0; i < Witchery.Items.GENERIC.weaves.size(); ++i) {
            ItemGeneral.DreamWeave weave = Witchery.Items.GENERIC.weaves.get(i);
            this.nets[i] = new ModelRenderer((ModelBase)this, weave.textureOffsetX, weave.textureOffsetY);
            this.nets[i].func_78789_a(0.0f, 0.0f, 0.0f, 6, 6, 0);
            this.nets[i].func_78793_a(-3.0f, 11.0f, 8.0f);
            this.nets[i].func_78787_b(32, 32);
            this.nets[i].field_78809_i = true;
            this.setRotation(this.nets[i], 0.0f, 0.0f, 0.0f);
        }
        this.decoration = new ModelRenderer((ModelBase)this, 0, 12);
        this.decoration.func_78789_a(0.0f, 0.0f, 0.0f, 8, 6, 0);
        this.decoration.func_78793_a(-4.0f, 18.0f, 7.0f);
        this.decoration.func_78787_b(32, 32);
        this.decoration.field_78809_i = true;
        this.setRotation(this.decoration, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockDreamCatcher.TileEntityDreamCatcher tileEntity) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.frameLeft.func_78785_a(f5);
        this.frameRight.func_78785_a(f5);
        this.frameTop.func_78785_a(f5);
        this.frameBottom.func_78785_a(f5);
        ItemGeneral.DreamWeave weave = tileEntity.getWeave();
        if (weave != null) {
            this.nets[weave.weaveID].func_78785_a(f5);
        }
        this.decoration.func_78785_a(f5);
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

