/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.client.model;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockWitchesOven;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

@SideOnly(value=Side.CLIENT)
public class ModelFumeFunnel
extends ModelBase {
    ModelRenderer chimney;
    ModelRenderer chimneyTop;
    ModelRenderer base;
    ModelRenderer body;
    ModelRenderer tubeLeft;
    ModelRenderer tubeRight;
    ModelRenderer pipeTop2;
    ModelRenderer pipeTop3;
    ModelRenderer pipeTop4;
    ModelRenderer pipeTop5;
    ModelRenderer pipeBottom1;
    ModelRenderer pipeBottom2;
    ModelRenderer pipeBottom3;
    ModelRenderer pipeBottom4;
    ModelRenderer pipeTop1;
    ModelRenderer top1;
    ModelRenderer pipeBottom5;
    ModelRenderer top2;
    ModelRenderer filterLeft;
    ModelRenderer filterRight;
    ModelRenderer filterMid;
    ModelRenderer filterCase;
    final boolean filtered;

    public ModelFumeFunnel(boolean filtered) {
        this.filtered = filtered;
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.base = new ModelRenderer((ModelBase)this, 0, 51);
        this.base.func_78789_a(0.0f, 0.0f, 0.0f, 12, 1, 12);
        this.base.func_78793_a(-6.0f, 23.0f, -6.0f);
        this.base.func_78787_b(64, 64);
        this.base.field_78809_i = true;
        this.setRotation(this.base, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 4, 27);
        this.body.func_78789_a(0.0f, 0.0f, 0.0f, 10, 11, 10);
        this.body.func_78793_a(-5.0f, 12.0f, -5.0f);
        this.body.func_78787_b(64, 64);
        this.body.field_78809_i = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.tubeLeft = new ModelRenderer((ModelBase)this, 1, 18);
        this.tubeLeft.func_78789_a(0.0f, 0.0f, 0.0f, 5, 2, 2);
        this.tubeLeft.func_78793_a(-10.0f, 17.0f, -1.0f);
        this.tubeLeft.func_78787_b(64, 64);
        this.tubeLeft.field_78809_i = true;
        this.setRotation(this.tubeLeft, 0.0f, 0.0f, 0.0f);
        this.tubeRight = new ModelRenderer((ModelBase)this, 1, 18);
        this.tubeRight.func_78789_a(0.0f, 1.0f, 0.0f, 5, 2, 2);
        this.tubeRight.func_78793_a(5.0f, 18.0f, 1.0f);
        this.tubeRight.func_78787_b(64, 64);
        this.tubeRight.field_78809_i = true;
        this.setRotation(this.tubeRight, 0.0f, 0.0f, 0.0f);
        this.pipeTop2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.pipeTop2.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 6);
        this.pipeTop2.func_78793_a(-4.0f, 8.0f, -3.0f);
        this.pipeTop2.func_78787_b(64, 64);
        this.pipeTop2.field_78809_i = true;
        this.setRotation(this.pipeTop2, 0.0f, 0.0f, 0.0f);
        this.pipeTop3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.pipeTop3.func_78789_a(0.0f, 0.0f, 0.0f, 11, 1, 1);
        this.pipeTop3.func_78793_a(-3.0f, 8.0f, -3.0f);
        this.pipeTop3.func_78787_b(64, 64);
        this.pipeTop3.field_78809_i = true;
        this.setRotation(this.pipeTop3, 0.0f, 0.0f, 0.0f);
        this.pipeTop4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.pipeTop4.func_78789_a(0.0f, 0.0f, 0.0f, 1, 11, 1);
        this.pipeTop4.func_78793_a(7.0f, 9.0f, -3.0f);
        this.pipeTop4.func_78787_b(64, 64);
        this.pipeTop4.field_78809_i = true;
        this.setRotation(this.pipeTop4, 0.0f, 0.0f, 0.0f);
        this.pipeTop5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.pipeTop5.func_78789_a(0.0f, 0.0f, 0.0f, 2, 3, 3);
        this.pipeTop5.func_78793_a(5.0f, 18.0f, -4.0f);
        this.pipeTop5.func_78787_b(64, 64);
        this.pipeTop5.field_78809_i = true;
        this.setRotation(this.pipeTop5, 0.0f, 0.0f, 0.0f);
        this.pipeBottom1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.pipeBottom1.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 1);
        this.pipeBottom1.func_78793_a(-7.0f, 13.0f, -3.0f);
        this.pipeBottom1.func_78787_b(64, 64);
        this.pipeBottom1.field_78809_i = true;
        this.setRotation(this.pipeBottom1, 0.0f, 0.0f, 0.0f);
        this.pipeBottom2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.pipeBottom2.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 4);
        this.pipeBottom2.func_78793_a(-7.0f, 20.0f, -7.0f);
        this.pipeBottom2.func_78787_b(64, 64);
        this.pipeBottom2.field_78809_i = true;
        this.setRotation(this.pipeBottom2, 0.0f, 0.0f, 0.0f);
        this.pipeBottom3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.pipeBottom3.func_78789_a(0.0f, 0.0f, 0.0f, 5, 1, 1);
        this.pipeBottom3.func_78793_a(-6.0f, 20.0f, -7.0f);
        this.pipeBottom3.func_78787_b(64, 64);
        this.pipeBottom3.field_78809_i = true;
        this.setRotation(this.pipeBottom3, 0.0f, 0.0f, 0.0f);
        this.pipeBottom4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.pipeBottom4.func_78789_a(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.pipeBottom4.func_78793_a(-2.0f, 21.0f, -7.0f);
        this.pipeBottom4.func_78787_b(64, 64);
        this.pipeBottom4.field_78809_i = true;
        this.setRotation(this.pipeBottom4, 0.0f, 0.0f, 0.0f);
        this.pipeTop1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.pipeTop1.func_78789_a(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.pipeTop1.func_78793_a(-4.0f, 8.0f, 3.0f);
        this.pipeTop1.func_78787_b(64, 64);
        this.pipeTop1.field_78809_i = true;
        this.setRotation(this.pipeTop1, 0.0f, 0.0f, 0.0f);
        this.top1 = new ModelRenderer((ModelBase)this, 0, 51);
        this.top1.func_78789_a(0.0f, 0.0f, 0.0f, 12, 1, 12);
        this.top1.func_78793_a(-6.0f, 11.0f, -6.0f);
        this.top1.func_78787_b(64, 64);
        this.top1.field_78809_i = true;
        this.setRotation(this.top1, 0.0f, 0.0f, 0.0f);
        this.pipeBottom5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.pipeBottom5.func_78789_a(0.0f, 0.0f, 0.0f, 1, 7, 1);
        this.pipeBottom5.func_78793_a(-7.0f, 14.0f, -3.0f);
        this.pipeBottom5.func_78787_b(64, 64);
        this.pipeBottom5.field_78809_i = true;
        this.setRotation(this.pipeBottom5, 0.0f, 0.0f, 0.0f);
        this.top2 = new ModelRenderer((ModelBase)this, 37, 55);
        this.top2.func_78789_a(0.0f, 0.0f, 0.0f, 6, 1, 6);
        this.top2.func_78793_a(-3.0f, 10.0f, -3.0f);
        this.top2.func_78787_b(64, 64);
        this.top2.field_78809_i = true;
        this.setRotation(this.top2, 0.0f, 0.0f, 0.0f);
        this.filterLeft = new ModelRenderer((ModelBase)this, 0, 0);
        this.filterLeft.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 2);
        this.filterLeft.func_78793_a(-4.0f, 14.0f, -7.0f);
        this.filterLeft.func_78787_b(64, 64);
        this.filterLeft.field_78809_i = true;
        this.setRotation(this.filterLeft, 0.0f, 0.0f, 0.0f);
        this.filterRight = new ModelRenderer((ModelBase)this, 0, 0);
        this.filterRight.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 2);
        this.filterRight.func_78793_a(3.0f, 14.0f, -7.0f);
        this.filterRight.func_78787_b(64, 64);
        this.filterRight.field_78809_i = true;
        this.setRotation(this.filterRight, 0.0f, 0.0f, 0.0f);
        this.filterMid = new ModelRenderer((ModelBase)this, 24, 0);
        this.filterMid.func_78789_a(0.0f, 0.0f, 0.0f, 6, 1, 1);
        this.filterMid.func_78793_a(-3.0f, 14.0f, -7.0f);
        this.filterMid.func_78787_b(64, 64);
        this.filterMid.field_78809_i = true;
        this.setRotation(this.filterMid, 0.0f, 0.0f, 0.0f);
        this.filterCase = new ModelRenderer((ModelBase)this, 25, 3);
        this.filterCase.func_78789_a(0.0f, 0.0f, 0.0f, 4, 3, 2);
        this.filterCase.func_78793_a(-2.0f, 13.0f, -8.0f);
        this.filterCase.func_78787_b(64, 64);
        this.filterCase.field_78809_i = true;
        this.setRotation(this.filterCase, 0.0f, 0.0f, 0.0f);
        this.chimney = new ModelRenderer((ModelBase)this, 27, 13);
        this.chimney.func_78789_a(0.0f, 0.0f, 0.0f, 4, 10, 4);
        this.chimney.func_78793_a(-2.0f, 14.0f, 3.0f);
        this.chimney.func_78787_b(64, 128);
        this.chimney.field_78809_i = true;
        this.setRotation(this.chimney, 0.0f, 0.0f, 0.0f);
        this.chimneyTop = new ModelRenderer((ModelBase)this, 40, 7);
        this.chimneyTop.func_78789_a(0.0f, 0.0f, 0.0f, 6, 3, 6);
        this.chimneyTop.func_78793_a(-3.0f, 11.0f, 2.0f);
        this.chimneyTop.func_78787_b(64, 128);
        this.chimneyTop.field_78809_i = true;
        this.setRotation(this.chimneyTop, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, TileEntity tile) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        boolean validTileEntity = tile != null && tile.func_145831_w() != null;
        boolean renderWideBody = true;
        if (validTileEntity) {
            int meta = tile.func_145832_p();
            switch (meta) {
                case 2: {
                    this.renderLeftGubbinsIfConnected(tile.func_145831_w(), tile.field_145851_c + 1, tile.field_145848_d, tile.field_145849_e, f5);
                    this.renderRightGubbinsIfConnected(tile.func_145831_w(), tile.field_145851_c - 1, tile.field_145848_d, tile.field_145849_e, f5);
                    break;
                }
                case 5: {
                    this.renderLeftGubbinsIfConnected(tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e + 1, f5);
                    this.renderRightGubbinsIfConnected(tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e - 1, f5);
                    break;
                }
                case 3: {
                    this.renderLeftGubbinsIfConnected(tile.func_145831_w(), tile.field_145851_c - 1, tile.field_145848_d, tile.field_145849_e, f5);
                    this.renderRightGubbinsIfConnected(tile.func_145831_w(), tile.field_145851_c + 1, tile.field_145848_d, tile.field_145849_e, f5);
                    break;
                }
                case 4: {
                    this.renderLeftGubbinsIfConnected(tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e - 1, f5);
                    this.renderRightGubbinsIfConnected(tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e + 1, f5);
                }
            }
            Block block = tile.func_145831_w().func_147439_a(tile.field_145851_c, tile.field_145848_d - 1, tile.field_145849_e);
            if (BlockWitchesOven.isOven(block)) {
                this.chimney.func_78785_a(f5);
                this.chimneyTop.func_78785_a(f5);
                renderWideBody = false;
            }
        }
        if (renderWideBody) {
            this.base.func_78785_a(f5);
            this.body.func_78785_a(f5);
            this.top1.func_78785_a(f5);
            this.top2.func_78785_a(f5);
            if (this.filtered || validTileEntity && tile.func_145838_q() == Witchery.Blocks.OVEN_FUMEFUNNEL_FILTERED) {
                this.filterLeft.func_78785_a(f5);
                this.filterRight.func_78785_a(f5);
                this.filterMid.func_78785_a(f5);
                this.filterCase.func_78785_a(f5);
            }
        }
    }

    private void renderLeftGubbinsIfConnected(World world, int xCoord, int yCoord, int zCoord, float f5) {
        Block block = world.func_147439_a(xCoord, yCoord, zCoord);
        if (BlockWitchesOven.isOven(block)) {
            this.tubeLeft.func_78785_a(f5);
            this.pipeTop1.func_78785_a(f5);
            this.pipeTop2.func_78785_a(f5);
            this.pipeTop3.func_78785_a(f5);
            this.pipeTop4.func_78785_a(f5);
            this.pipeTop5.func_78785_a(f5);
        }
    }

    private void renderRightGubbinsIfConnected(World world, int xCoord, int yCoord, int zCoord, float f5) {
        Block block = world.func_147439_a(xCoord, yCoord, zCoord);
        if (BlockWitchesOven.isOven(block)) {
            this.tubeRight.func_78785_a(f5);
            this.pipeBottom1.func_78785_a(f5);
            this.pipeBottom2.func_78785_a(f5);
            this.pipeBottom3.func_78785_a(f5);
            this.pipeBottom4.func_78785_a(f5);
            this.pipeBottom5.func_78785_a(f5);
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

