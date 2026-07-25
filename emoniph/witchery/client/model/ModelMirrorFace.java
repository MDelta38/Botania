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

import com.emoniph.witchery.util.RenderUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class ModelMirrorFace
extends ModelBase {
    public ModelRenderer bipedHead;

    public ModelMirrorFace() {
        this(0.0f);
    }

    public ModelMirrorFace(float p_i1148_1_) {
        this(p_i1148_1_, 0.0f, 64, 32);
    }

    public ModelMirrorFace(float p_i1149_1_, float p_i1149_2_, int p_i1149_3_, int p_i1149_4_) {
        this.field_78090_t = p_i1149_3_;
        this.field_78089_u = p_i1149_4_;
        this.bipedHead = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHead.func_78790_a(-4.0f, -8.0f, -4.0f, 8, 8, 8, p_i1149_1_);
        this.bipedHead.func_78793_a(0.0f, 24.0f + p_i1149_2_, 0.0f);
    }

    public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        this.func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
        this.bipedHead.func_78793_a(0.0f, 24.0f, 0.0f);
        float scale = 0.75f;
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        GL11.glTranslatef((float)0.0f, (float)0.4f, (float)0.0f);
        RenderUtil.blend(true);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.8f);
        this.bipedHead.func_78785_a(p_78088_7_);
        RenderUtil.blend(false);
    }

    public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
        this.bipedHead.field_78796_g = p_78087_4_ / 57.295776f;
        this.bipedHead.field_78795_f = p_78087_5_ / 57.295776f;
    }
}

