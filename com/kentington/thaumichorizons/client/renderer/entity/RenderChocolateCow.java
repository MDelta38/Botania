/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderCow
 *  net.minecraft.entity.passive.EntityCow
 *  net.minecraft.util.ResourceLocation
 */
package com.kentington.thaumichorizons.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderCow;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.util.ResourceLocation;

public class RenderChocolateCow
extends RenderCow {
    private static final ResourceLocation cowTextures = new ResourceLocation("thaumichorizons", "textures/entity/chocolatecow.png");

    public RenderChocolateCow(ModelBase p_i1253_1_, float p_i1253_2_) {
        super(p_i1253_1_, p_i1253_2_);
    }

    protected ResourceLocation func_110775_a(EntityCow p_110775_1_) {
        return cowTextures;
    }
}

