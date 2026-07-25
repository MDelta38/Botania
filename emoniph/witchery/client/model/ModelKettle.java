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
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.model;

import com.emoniph.witchery.blocks.BlockKettle;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class ModelKettle
extends ModelBase {
    ModelRenderer sideFront;
    ModelRenderer sideBack;
    ModelRenderer sideLeft;
    ModelRenderer sideRight;
    ModelRenderer sideBottom;
    ModelRenderer crossbar;
    ModelRenderer[] liquid;
    ModelRenderer chainLF;
    ModelRenderer chainLB;
    ModelRenderer chainRF;
    ModelRenderer chainRB;
    ModelRenderer bottle1;
    ModelRenderer bottle2;
    private int ticks;

    public ModelKettle() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.func_78085_a("bottle1.bottle1Body", 52, 5);
        this.func_78085_a("bottle1.bottle1Neck", 60, 3);
        this.func_78085_a("bottle1.bottle1Top", 56, 0);
        this.func_78085_a("bottle2.bottle2Body", 52, 5);
        this.func_78085_a("bottle2.bottle2Neck", 60, 3);
        this.func_78085_a("bottle2.bottle2Top", 56, 0);
        this.sideFront = new ModelRenderer((ModelBase)this, 0, 0);
        this.sideFront.func_78789_a(0.0f, 0.0f, 0.0f, 9, 6, 1);
        this.sideFront.func_78793_a(-5.0f, 18.0f, -5.0f);
        this.sideFront.func_78787_b(64, 64);
        this.sideFront.field_78809_i = true;
        this.setRotation(this.sideFront, 0.0f, 0.0f, 0.0f);
        this.sideBack = new ModelRenderer((ModelBase)this, 0, 0);
        this.sideBack.func_78789_a(0.0f, 0.0f, 0.0f, 9, 6, 1);
        this.sideBack.func_78793_a(-4.0f, 18.0f, 4.0f);
        this.sideBack.func_78787_b(64, 64);
        this.sideBack.field_78809_i = true;
        this.setRotation(this.sideBack, 0.0f, 0.0f, 0.0f);
        this.sideLeft = new ModelRenderer((ModelBase)this, 0, 0);
        this.sideLeft.func_78789_a(0.0f, 0.0f, 0.0f, 9, 6, 1);
        this.sideLeft.func_78793_a(-5.0f, 18.0f, 5.0f);
        this.sideLeft.func_78787_b(64, 64);
        this.sideLeft.field_78809_i = true;
        this.setRotation(this.sideLeft, 0.0f, 1.570796f, 0.0f);
        this.sideRight = new ModelRenderer((ModelBase)this, 0, 0);
        this.sideRight.func_78789_a(0.0f, 0.0f, 0.0f, 9, 6, 1);
        this.sideRight.func_78793_a(4.0f, 18.0f, 4.0f);
        this.sideRight.func_78787_b(64, 64);
        this.sideRight.field_78809_i = true;
        this.setRotation(this.sideRight, 0.0f, 1.570796f, 0.0f);
        this.sideBottom = new ModelRenderer((ModelBase)this, 13, 0);
        this.sideBottom.func_78789_a(0.0f, 0.0f, 0.0f, 8, 1, 8);
        this.sideBottom.func_78793_a(-4.0f, 23.0f, -4.0f);
        this.sideBottom.func_78787_b(64, 64);
        this.sideBottom.field_78809_i = true;
        this.setRotation(this.sideBottom, 0.0f, 0.0f, 0.0f);
        this.crossbar = new ModelRenderer((ModelBase)this, 0, 10);
        this.crossbar.func_78789_a(-4.0f, 0.0f, 0.0f, 24, 2, 2);
        this.crossbar.func_78793_a(-8.0f, 8.05f, -1.0f);
        this.crossbar.func_78787_b(64, 64);
        this.crossbar.field_78809_i = true;
        this.setRotation(this.crossbar, 0.0f, 0.0f, 0.0f);
        this.liquid = new ModelRenderer[8];
        for (int i = 0; i < this.liquid.length; ++i) {
            this.liquid[i] = new ModelRenderer((ModelBase)this, i < 4 ? i * 16 - 8 : (i - 4) * 16 - 8, i < 4 ? 16 : 32);
            this.liquid[i].func_78789_a(0.0f, 0.0f, 0.0f, 8, 0, 8);
            this.liquid[i].func_78793_a(-4.0f, 20.0f, -4.0f);
            this.liquid[i].func_78787_b(64, 64);
            this.liquid[i].field_78809_i = true;
            this.setRotation(this.liquid[i], 0.0f, 0.0f, 0.0f);
        }
        this.chainLF = new ModelRenderer((ModelBase)this, 0, 15);
        this.chainLF.func_78789_a(0.0f, -0.5f, 0.0f, 11, 1, 0);
        this.chainLF.func_78793_a(0.0f, 9.0f, 0.0f);
        this.chainLF.func_78787_b(64, 64);
        this.chainLF.field_78809_i = true;
        this.chainLB = new ModelRenderer((ModelBase)this, 0, 15);
        this.chainLB.func_78789_a(0.0f, -0.5f, 0.0f, 11, 1, 0);
        this.chainLB.func_78793_a(0.0f, 9.0f, 0.0f);
        this.chainLB.func_78787_b(64, 64);
        this.chainLB.field_78809_i = true;
        this.chainRF = new ModelRenderer((ModelBase)this, 0, 15);
        this.chainRF.func_78789_a(0.0f, -0.5f, 0.0f, 11, 1, 0);
        this.chainRF.func_78793_a(0.0f, 9.0f, 0.0f);
        this.chainRF.func_78787_b(64, 64);
        this.chainRF.field_78809_i = true;
        this.chainRB = new ModelRenderer((ModelBase)this, 0, 15);
        this.chainRB.func_78789_a(0.0f, -0.5f, 0.0f, 11, 1, 0);
        this.chainRB.func_78793_a(0.0f, 9.0f, 0.0f);
        this.chainRB.func_78787_b(64, 64);
        this.chainRB.field_78809_i = true;
        this.chainRB.field_78809_i = false;
        this.setRotation(this.chainRB, 0.0f, -0.4f, 1.1f);
        this.setRotation(this.chainLB, 0.0f, 0.4f, 1.1f);
        this.setRotation(this.chainRF, 0.0f, 0.4f, 2.05f);
        this.setRotation(this.chainLF, 0.0f, -2.75f, -1.1f);
        this.bottle1 = new ModelRenderer((ModelBase)this, "bottle1");
        this.bottle1.func_78793_a(-4.0f, 13.0f, -6.0f);
        this.setRotation(this.bottle1, 0.0f, 0.0f, 0.0f);
        this.bottle1.field_78809_i = true;
        this.bottle1.func_78786_a("bottle1Body", 0.0f, 2.0f, 0.0f, 3, 3, 3);
        this.bottle1.func_78786_a("bottle1Neck", 1.0f, 1.0f, 1.0f, 1, 1, 1);
        this.bottle1.func_78786_a("bottle1Top", 0.5f, 0.0f, 0.5f, 2, 1, 2);
        this.bottle2 = new ModelRenderer((ModelBase)this, "bottle2");
        this.bottle2.func_78793_a(0.0f, 13.0f, -6.0f);
        this.setRotation(this.bottle2, 0.0f, 0.0f, 0.0f);
        this.bottle2.field_78809_i = true;
        this.bottle2.func_78786_a("bottle2Body", 0.0f, 2.0f, 0.0f, 3, 3, 3);
        this.bottle2.func_78786_a("bottle2Neck", 1.0f, 1.0f, 1.0f, 1, 1, 1);
        this.bottle2.func_78786_a("bottle2Top", 0.5f, 0.0f, 0.5f, 2, 1, 2);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockKettle.TileEntityKettle kettleTileEntity) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.sideFront.func_78785_a(f5);
        this.sideBack.func_78785_a(f5);
        this.sideLeft.func_78785_a(f5);
        this.sideRight.func_78785_a(f5);
        this.sideBottom.func_78785_a(f5);
        if (kettleTileEntity != null && kettleTileEntity.func_145831_w() != null) {
            int posX = MathHelper.func_76128_c((double)kettleTileEntity.field_145851_c);
            int posY = MathHelper.func_76128_c((double)kettleTileEntity.field_145848_d);
            int posZ = MathHelper.func_76128_c((double)kettleTileEntity.field_145849_e);
            if (!kettleTileEntity.func_145831_w().func_147439_a(posX, posY + 1, posZ).func_149688_o().func_76220_a()) {
                this.crossbar.func_78785_a(f5);
            }
            this.chainLF.func_78785_a(f5);
            this.chainLB.func_78785_a(f5);
            this.chainRF.func_78785_a(f5);
            this.chainRB.func_78785_a(f5);
            int bottles = kettleTileEntity.bottleCount();
            if (bottles > 0) {
                this.bottle1.func_78785_a(f5);
                if (bottles > 1) {
                    this.bottle2.func_78785_a(f5);
                }
            }
            this.setRotation(this.chainRB, 0.0f, -0.4f, 1.1f);
            this.setRotation(this.chainLB, 0.0f, 0.4f, 1.1f);
            this.setRotation(this.chainRF, 0.0f, 0.4f, 2.05f);
            this.setRotation(this.chainLF, 0.0f, -2.75f, -1.1f);
            if (kettleTileEntity.isFilled()) {
                if (this.ticks >= 79) {
                    this.ticks = 0;
                }
                ++this.ticks;
                int color = 0;
                float factor = 1.0f;
                if (kettleTileEntity.isRuined()) {
                    color = -8429824;
                    GL11.glColor4f((float)1.0f, (float)0.5f, (float)0.2f, (float)0.5f);
                } else if (kettleTileEntity.isReady()) {
                    color = kettleTileEntity.getLiquidColor();
                } else if (kettleTileEntity.isBrewing()) {
                    color = kettleTileEntity.getLiquidColor();
                    factor = 0.5f;
                }
                if (color == 0) {
                    color = -13148989;
                    factor = 1.0f;
                }
                float red = (float)(color >>> 16 & 0xFF) / 256.0f * factor;
                float green = (float)(color >>> 8 & 0xFF) / 256.0f * factor;
                float blue = (float)(color & 0xFF) / 256.0f * factor;
                GL11.glColor4f((float)red, (float)green, (float)blue, (float)1.0f);
                this.liquid[(int)Math.floor(this.ticks / 20)].func_78785_a(f5);
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

