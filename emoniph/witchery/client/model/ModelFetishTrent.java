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

import com.emoniph.witchery.blocks.BlockFetish;
import com.emoniph.witchery.client.model.ModelBroom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class ModelFetishTrent
extends ModelBase {
    ModelRenderer body;
    ModelRenderer armLeft;
    ModelRenderer armRight;
    ModelRenderer legLeftUpper;
    ModelRenderer legLeftLower;
    ModelRenderer legRightUpper;
    ModelRenderer legRightLower;
    ModelRenderer headdress1;
    ModelRenderer headdress2;
    ModelRenderer headdress3;
    ModelRenderer face;

    public ModelFetishTrent() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.body = new ModelRenderer((ModelBase)this, 0, 14);
        this.body.func_78789_a(-3.0f, 0.0f, -3.0f, 6, 9, 6);
        this.body.func_78793_a(0.0f, 12.0f, 0.0f);
        this.body.func_78787_b(64, 64);
        this.body.field_78809_i = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.face = new ModelRenderer((ModelBase)this, 18, 1);
        this.face.func_78789_a(-3.0f, 1.0f, -2.9f, 6, 7, 0);
        this.face.func_78793_a(0.0f, 12.0f, 0.0f);
        this.face.func_78787_b(64, 64);
        this.face.field_78809_i = true;
        this.setRotation(this.face, 0.0f, 0.0f, 0.0f);
        this.armLeft = new ModelRenderer((ModelBase)this, 0, 0);
        this.armLeft.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 5, 2);
        this.armLeft.func_78793_a(2.0f, 13.0f, 0.0f);
        this.armLeft.func_78787_b(64, 64);
        this.armLeft.field_78809_i = true;
        this.setRotation(this.armLeft, -0.1858931f, 0.0f, -0.7435722f);
        this.armRight = new ModelRenderer((ModelBase)this, 0, 0);
        this.armRight.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 5, 2);
        this.armRight.func_78793_a(-2.0f, 13.0f, 0.0f);
        this.armRight.func_78787_b(64, 64);
        this.armRight.field_78809_i = true;
        this.setRotation(this.armRight, -0.1858931f, 0.0f, 0.8551081f);
        this.legLeftUpper = new ModelRenderer((ModelBase)this, 9, 0);
        this.legLeftUpper.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 5, 2);
        this.legLeftUpper.func_78793_a(2.0f, 18.0f, 0.0f);
        this.legLeftUpper.func_78787_b(64, 64);
        this.legLeftUpper.field_78809_i = true;
        this.setRotation(this.legLeftUpper, -0.1487144f, 0.0f, -0.2602503f);
        this.legLeftLower = new ModelRenderer((ModelBase)this, 11, 8);
        this.legLeftLower.func_78789_a(-0.5f, 0.0f, -0.5f, 1, 3, 1);
        this.legLeftLower.func_78793_a(3.0f, 21.0f, -0.5f);
        this.legLeftLower.func_78787_b(64, 64);
        this.legLeftLower.field_78809_i = true;
        this.setRotation(this.legLeftLower, 0.0743572f, 0.0f, -0.1115358f);
        this.legRightUpper = new ModelRenderer((ModelBase)this, 9, 0);
        this.legRightUpper.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 5, 2);
        this.legRightUpper.func_78793_a(-2.0f, 18.0f, 0.0f);
        this.legRightUpper.func_78787_b(64, 64);
        this.legRightUpper.field_78809_i = true;
        this.setRotation(this.legRightUpper, 0.1858931f, 0.0f, 0.3346075f);
        this.legRightLower = new ModelRenderer((ModelBase)this, 11, 8);
        this.legRightLower.func_78789_a(-0.5f, 0.0f, -0.5f, 1, 3, 1);
        this.legRightLower.func_78793_a(-3.0f, 21.0f, 0.5f);
        this.legRightLower.func_78787_b(64, 64);
        this.legRightLower.field_78809_i = true;
        this.setRotation(this.legRightLower, 0.1858931f, 0.0f, 0.2230717f);
        this.headdress1 = new ModelRenderer((ModelBase)this, 0, 30);
        this.headdress1.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 5, 2);
        this.headdress1.func_78793_a(0.0f, 13.0f, 1.0f);
        this.headdress1.func_78787_b(64, 64);
        this.headdress1.field_78809_i = true;
        this.setRotation(this.headdress1, 0.1115358f, 0.0f, -2.862753f);
        this.headdress2 = new ModelRenderer((ModelBase)this, 0, 30);
        this.headdress2.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 5, 2);
        this.headdress2.func_78793_a(-1.0f, 13.0f, 0.0f);
        this.headdress2.func_78787_b(64, 64);
        this.headdress2.field_78809_i = true;
        this.setRotation(this.headdress2, 0.3717861f, 0.0f, 2.639681f);
        this.headdress3 = new ModelRenderer((ModelBase)this, 0, 30);
        this.headdress3.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 5, 2);
        this.headdress3.func_78793_a(-1.0f, 13.0f, 0.0f);
        this.headdress3.func_78787_b(64, 64);
        this.headdress3.field_78809_i = true;
        this.setRotation(this.headdress3, -0.4461433f, 0.0f, 2.862753f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockFetish.TileEntityFetish tile) {
        int color;
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.body.func_78785_a(f5);
        this.armLeft.func_78785_a(f5);
        this.armRight.func_78785_a(f5);
        this.legLeftUpper.func_78785_a(f5);
        this.legLeftLower.func_78785_a(f5);
        this.legRightUpper.func_78785_a(f5);
        this.legRightLower.func_78785_a(f5);
        this.headdress1.func_78785_a(f5);
        this.headdress2.func_78785_a(f5);
        this.headdress3.func_78785_a(f5);
        int colorIndex = 9;
        if (tile != null && (color = tile.getColor()) >= 0 && color <= 15) {
            colorIndex = color;
        }
        GL11.glColor4f((float)ModelBroom.fleeceColorTable[colorIndex][0], (float)ModelBroom.fleeceColorTable[colorIndex][1], (float)ModelBroom.fleeceColorTable[colorIndex][2], (float)1.0f);
        this.face.func_78785_a(f5);
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

