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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(value=Side.CLIENT)
public class ModelLouse
extends ModelBase {
    private ModelRenderer[] silverfishBodyParts = new ModelRenderer[7];
    private ModelRenderer[] silverfishWings;
    private float[] field_78170_c = new float[7];
    private static final int[][] silverfishBoxLength = new int[][]{{3, 2, 2}, {4, 3, 2}, {4, 3, 2}, {3, 3, 3}, {2, 2, 3}, {2, 1, 2}, {1, 1, 2}};
    private static final int[][] silverfishTexturePositions = new int[][]{{0, 0}, {0, 4}, {0, 9}, {0, 16}, {0, 22}, {11, 0}, {13, 4}};

    public ModelLouse() {
        float f = -3.5f;
        for (int i = 0; i < this.silverfishBodyParts.length; ++i) {
            this.silverfishBodyParts[i] = new ModelRenderer((ModelBase)this, silverfishTexturePositions[i][0], silverfishTexturePositions[i][1]);
            this.silverfishBodyParts[i].func_78789_a((float)silverfishBoxLength[i][0] * -0.5f, 0.0f, (float)silverfishBoxLength[i][2] * -0.5f, silverfishBoxLength[i][0], silverfishBoxLength[i][1], silverfishBoxLength[i][2]);
            this.silverfishBodyParts[i].func_78793_a(0.0f, (float)(24 - silverfishBoxLength[i][1]), f);
            this.field_78170_c[i] = f;
            if (i >= this.silverfishBodyParts.length - 1) continue;
            f += (float)(silverfishBoxLength[i][2] + silverfishBoxLength[i + 1][2]) * 0.5f;
        }
        this.silverfishWings = new ModelRenderer[3];
        this.silverfishWings[0] = new ModelRenderer((ModelBase)this, 20, 0);
        this.silverfishWings[0].func_78789_a(-5.0f, 0.0f, (float)silverfishBoxLength[2][2] * -0.5f, 10, 8, silverfishBoxLength[2][2]);
        this.silverfishWings[0].func_78793_a(0.0f, 16.0f, this.field_78170_c[2]);
        this.silverfishWings[1] = new ModelRenderer((ModelBase)this, 20, 11);
        this.silverfishWings[1].func_78789_a(-3.0f, 0.0f, (float)silverfishBoxLength[4][2] * -0.5f, 6, 4, silverfishBoxLength[4][2]);
        this.silverfishWings[1].func_78793_a(0.0f, 20.0f, this.field_78170_c[4]);
        this.silverfishWings[2] = new ModelRenderer((ModelBase)this, 20, 18);
        this.silverfishWings[2].func_78789_a(-3.0f, 0.0f, (float)silverfishBoxLength[4][2] * -0.5f, 6, 5, silverfishBoxLength[1][2]);
        this.silverfishWings[2].func_78793_a(0.0f, 19.0f, this.field_78170_c[1]);
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        int i;
        this.func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
        for (i = 0; i < this.silverfishBodyParts.length; ++i) {
            this.silverfishBodyParts[i].func_78785_a(par7);
        }
        for (i = 0; i < this.silverfishWings.length; ++i) {
            this.silverfishWings[i].func_78785_a(par7);
        }
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        for (int i = 0; i < this.silverfishBodyParts.length; ++i) {
            this.silverfishBodyParts[i].field_78796_g = MathHelper.func_76134_b((float)(par3 * 0.9f + (float)i * 0.15f * (float)Math.PI)) * (float)Math.PI * 0.05f * (float)(1 + Math.abs(i - 2));
            this.silverfishBodyParts[i].field_78800_c = MathHelper.func_76126_a((float)(par3 * 0.9f + (float)i * 0.15f * (float)Math.PI)) * (float)Math.PI * 0.2f * (float)Math.abs(i - 2);
        }
        this.silverfishWings[0].field_78796_g = this.silverfishBodyParts[2].field_78796_g;
        this.silverfishWings[1].field_78796_g = this.silverfishBodyParts[4].field_78796_g;
        this.silverfishWings[1].field_78800_c = this.silverfishBodyParts[4].field_78800_c;
        this.silverfishWings[2].field_78796_g = this.silverfishBodyParts[1].field_78796_g;
        this.silverfishWings[2].field_78800_c = this.silverfishBodyParts[1].field_78800_c;
    }
}

