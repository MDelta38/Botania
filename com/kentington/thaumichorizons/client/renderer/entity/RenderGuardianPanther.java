/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderOcelot
 *  net.minecraft.entity.passive.EntityOcelot
 *  org.lwjgl.opengl.GL11
 */
package com.kentington.thaumichorizons.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderOcelot;
import net.minecraft.entity.passive.EntityOcelot;
import org.lwjgl.opengl.GL11;

public class RenderGuardianPanther
extends RenderOcelot {
    public RenderGuardianPanther(ModelBase p_i1264_1_, float p_i1264_2_) {
        super(p_i1264_1_, p_i1264_2_);
    }

    protected void func_77041_b(EntityOcelot p_77041_1_, float p_77041_2_) {
        super.func_77041_b(p_77041_1_, p_77041_2_);
        GL11.glScalef((float)2.0f, (float)2.0f, (float)2.0f);
    }
}

