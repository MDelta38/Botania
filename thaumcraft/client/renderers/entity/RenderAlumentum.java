/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package thaumcraft.client.renderers.entity;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import thaumcraft.common.entities.projectile.EntityAlumentum;

public class RenderAlumentum
extends Render {
    public RenderAlumentum() {
        this.field_76989_e = 0.1f;
    }

    public void renderEntityAt(EntityAlumentum tg, double x, double y, double z, float fq) {
    }

    public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) {
        this.renderEntityAt((EntityAlumentum)entity, d, d1, d2, f);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return AbstractClientPlayer.field_110314_b;
    }
}

