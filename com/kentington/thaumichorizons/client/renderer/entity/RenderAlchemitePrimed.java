/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.entity.RenderEntity
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.kentington.thaumichorizons.client.renderer.entity;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityAlchemitePrimed;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderAlchemitePrimed
extends RenderEntity {
    private RenderBlocks blockRenderer = new RenderBlocks();

    public RenderAlchemitePrimed() {
        this.field_76989_e = 0.5f;
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_) {
        return this.getEntityTexture((EntityAlchemitePrimed)p_110775_1_);
    }

    protected ResourceLocation getEntityTexture(EntityAlchemitePrimed p_110775_1_) {
        return TextureMap.field_110575_b;
    }

    public void doRenderStuff(EntityAlchemitePrimed entity, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        float f2;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)p_76986_2_), (float)((float)p_76986_4_), (float)((float)p_76986_6_));
        if ((float)entity.fuse - p_76986_9_ + 1.0f < 10.0f) {
            f2 = 1.0f - ((float)entity.fuse - p_76986_9_ + 1.0f) / 10.0f;
            if (f2 < 0.0f) {
                f2 = 0.0f;
            }
            if (f2 > 1.0f) {
                f2 = 1.0f;
            }
            f2 *= f2;
            f2 *= f2;
            float f3 = 1.0f + f2 * 0.3f;
            GL11.glScalef((float)f3, (float)f3, (float)f3);
        }
        f2 = (1.0f - ((float)entity.fuse - p_76986_9_ + 1.0f) / 100.0f) * 0.8f;
        this.func_110777_b(entity);
        this.blockRenderer.func_147800_a(ThaumicHorizons.blockAlchemite, 0, entity.func_70013_c(p_76986_9_));
        if (entity.fuse / 5 % 2 == 0) {
            GL11.glDisable((int)3553);
            GL11.glDisable((int)2896);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)772);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)f2);
            this.blockRenderer.func_147800_a(ThaumicHorizons.blockAlchemite, 0, 1.0f);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glDisable((int)3042);
            GL11.glEnable((int)2896);
            GL11.glEnable((int)3553);
        }
        GL11.glPopMatrix();
    }

    public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRenderStuff((EntityAlchemitePrimed)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}

