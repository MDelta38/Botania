/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.model;

import com.emoniph.witchery.blocks.BlockBeartrap;
import com.emoniph.witchery.util.Config;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class ModelBeartrap
extends ModelBase {
    public ModelRenderer plate;
    public ModelRenderer base;
    public ModelRenderer armFront;
    public ModelRenderer armBack;
    public ModelRenderer diskLeft;
    public ModelRenderer diskRight;
    public ModelRenderer armRightFront;
    public ModelRenderer armLeftFront;
    public ModelRenderer armTooth1Front;
    public ModelRenderer armTooth2Front;
    public ModelRenderer armTooth3Front;
    public ModelRenderer armTooth4Front;
    public ModelRenderer armTooth5Front;
    public ModelRenderer armRightBack;
    public ModelRenderer armLeftBack;
    public ModelRenderer armTooth1Back;
    public ModelRenderer armTooth2Back;
    public ModelRenderer armTooth3Back;
    public ModelRenderer armTooth4Back;
    public ModelRenderer armTooth5Back;

    public ModelBeartrap() {
        this.field_78090_t = 32;
        this.field_78089_u = 32;
        this.armTooth4Back = new ModelRenderer((ModelBase)this, 0, 0);
        this.armTooth4Back.func_78793_a(0.0f, 0.0f, 0.0f);
        this.armTooth4Back.func_78790_a(1.5f, -2.0f, 6.0f, 1, 1, 1, 0.0f);
        this.plate = new ModelRenderer((ModelBase)this, 1, 0);
        this.plate.func_78793_a(0.0f, 24.0f, 0.0f);
        this.plate.func_78790_a(-2.0f, -1.5f, -2.0f, 4, 1, 4, 0.0f);
        this.armFront = new ModelRenderer((ModelBase)this, 0, 9);
        this.armFront.func_78793_a(0.0f, 23.99f, 0.0f);
        this.armFront.func_78790_a(-4.5f, -1.0f, -7.0f, 9, 1, 1, 0.0f);
        this.armTooth2Front = new ModelRenderer((ModelBase)this, 0, 0);
        this.armTooth2Front.func_78793_a(0.0f, 0.0f, 0.0f);
        this.armTooth2Front.func_78790_a(-2.5f, -2.0f, -7.0f, 1, 1, 1, 0.0f);
        this.armLeftFront = new ModelRenderer((ModelBase)this, 0, 12);
        this.armLeftFront.func_78793_a(0.0f, 0.0f, 0.0f);
        this.armLeftFront.func_78790_a(3.5f, -1.0f, -6.0f, 1, 1, 6, 0.0f);
        this.armTooth4Front = new ModelRenderer((ModelBase)this, 0, 0);
        this.armTooth4Front.func_78793_a(0.0f, 0.0f, 0.0f);
        this.armTooth4Front.func_78790_a(1.5f, -2.0f, -7.0f, 1, 1, 1, 0.0f);
        this.armLeftBack = new ModelRenderer((ModelBase)this, 0, 12);
        this.armLeftBack.func_78793_a(0.0f, 0.0f, 0.0f);
        this.armLeftBack.func_78790_a(3.5f, -1.0f, 0.0f, 1, 1, 6, 0.0f);
        this.armTooth3Front = new ModelRenderer((ModelBase)this, 0, 0);
        this.armTooth3Front.func_78793_a(0.0f, 0.0f, 0.0f);
        this.armTooth3Front.func_78790_a(-0.5f, -2.0f, -7.0f, 1, 1, 1, 0.0f);
        this.armTooth3Back = new ModelRenderer((ModelBase)this, 0, 0);
        this.armTooth3Back.func_78793_a(0.0f, 0.0f, 0.0f);
        this.armTooth3Back.func_78790_a(-0.5f, -2.0f, 6.0f, 1, 1, 1, 0.0f);
        this.armTooth5Front = new ModelRenderer((ModelBase)this, 0, 0);
        this.armTooth5Front.func_78793_a(0.0f, 0.0f, 0.0f);
        this.armTooth5Front.func_78790_a(3.5f, -2.0f, -7.0f, 1, 1, 1, 0.0f);
        this.base = new ModelRenderer((ModelBase)this, 0, 20);
        this.base.func_78793_a(0.0f, 23.99f, 0.0f);
        this.base.func_78790_a(-5.0f, -1.0f, -0.5f, 10, 1, 1, 0.0f);
        this.armBack = new ModelRenderer((ModelBase)this, 0, 9);
        this.armBack.func_78793_a(0.0f, 23.99f, 0.0f);
        this.armBack.func_78790_a(-4.5f, -1.0f, 6.0f, 9, 1, 1, 0.0f);
        this.diskLeft = new ModelRenderer((ModelBase)this, 19, 3);
        this.diskLeft.func_78793_a(0.0f, 24.0f, 0.0f);
        this.diskLeft.func_78790_a(3.7f, -2.0f, -1.0f, 1, 2, 2, 0.0f);
        this.armTooth2Back = new ModelRenderer((ModelBase)this, 0, 0);
        this.armTooth2Back.func_78793_a(0.0f, 0.0f, 0.0f);
        this.armTooth2Back.func_78790_a(-2.5f, -2.0f, 6.0f, 1, 1, 1, 0.0f);
        this.armTooth1Back = new ModelRenderer((ModelBase)this, 0, 0);
        this.armTooth1Back.func_78793_a(0.0f, 0.0f, 0.0f);
        this.armTooth1Back.func_78790_a(-4.5f, -2.0f, 6.0f, 1, 1, 1, 0.0f);
        this.armTooth5Back = new ModelRenderer((ModelBase)this, 0, 0);
        this.armTooth5Back.func_78793_a(0.0f, 0.0f, 0.0f);
        this.armTooth5Back.func_78790_a(3.5f, -2.0f, 6.0f, 1, 1, 1, 0.0f);
        this.armRightBack = new ModelRenderer((ModelBase)this, 0, 12);
        this.armRightBack.func_78793_a(0.0f, 0.0f, 0.0f);
        this.armRightBack.func_78790_a(-4.5f, -1.0f, 0.0f, 1, 1, 6, 0.0f);
        this.armTooth1Front = new ModelRenderer((ModelBase)this, 0, 0);
        this.armTooth1Front.func_78793_a(0.0f, 0.0f, 0.0f);
        this.armTooth1Front.func_78790_a(-4.5f, -2.0f, -7.0f, 1, 1, 1, 0.0f);
        this.armRightFront = new ModelRenderer((ModelBase)this, 0, 12);
        this.armRightFront.func_78793_a(0.0f, 0.0f, 0.0f);
        this.armRightFront.func_78790_a(-4.5f, -1.0f, -6.0f, 1, 1, 6, 0.0f);
        this.diskRight = new ModelRenderer((ModelBase)this, 19, 3);
        this.diskRight.func_78793_a(0.0f, 24.0f, 0.0f);
        this.diskRight.func_78790_a(-4.7f, -2.0f, -1.0f, 1, 2, 2, 0.0f);
        this.armBack.func_78792_a(this.armTooth4Back);
        this.armFront.func_78792_a(this.armTooth2Front);
        this.armFront.func_78792_a(this.armLeftFront);
        this.armFront.func_78792_a(this.armTooth4Front);
        this.armBack.func_78792_a(this.armLeftBack);
        this.armFront.func_78792_a(this.armTooth3Front);
        this.armBack.func_78792_a(this.armTooth3Back);
        this.armFront.func_78792_a(this.armTooth5Front);
        this.armBack.func_78792_a(this.armTooth2Back);
        this.armBack.func_78792_a(this.armTooth1Back);
        this.armBack.func_78792_a(this.armTooth5Back);
        this.armBack.func_78792_a(this.armRightBack);
        this.armFront.func_78792_a(this.armTooth1Front);
        this.armFront.func_78792_a(this.armRightFront);
    }

    private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockBeartrap.TileEntityBeartrap tile) {
        boolean inWorld;
        boolean bl = inWorld = tile != null && tile.func_145831_w() != null;
        if (inWorld && !tile.isVisibleTo((EntityPlayer)Minecraft.func_71410_x().field_71439_g)) {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)Config.instance().mantrapAlpha);
        }
        this.base.func_78785_a(f5);
        this.diskLeft.func_78785_a(f5);
        this.diskRight.func_78785_a(f5);
        this.plate.field_78797_d = inWorld && tile.isSprung() ? 23.8f : 23.2f;
        this.plate.func_78785_a(f5);
        this.armFront.field_78795_f = inWorld && tile.isSprung() ? -1.2f : 0.0f;
        this.armFront.func_78785_a(f5);
        this.armBack.field_78795_f = inWorld && tile.isSprung() ? 1.2f : 0.0f;
        this.armBack.func_78785_a(f5);
    }
}

