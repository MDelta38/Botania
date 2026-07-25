/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.entity.RenderBiped
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.client.model.ModelVampire;
import com.emoniph.witchery.entity.EntityVillageGuard;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(value=Side.CLIENT)
public class RenderVampire
extends RenderBiped {
    private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/vampire.png");

    public RenderVampire() {
        super((ModelBiped)new ModelVampire(), 0.5f);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return TEXTURE_URL;
    }

    protected ResourceLocation func_110832_a(EntityVillageGuard entity) {
        return TEXTURE_URL;
    }
}

