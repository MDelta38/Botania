/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.kentington.thaumichorizons.client.renderer.entity;

import com.kentington.thaumichorizons.client.renderer.model.ModelSyringe;
import com.kentington.thaumichorizons.common.entities.EntitySyringe;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderSyringe
extends Render {
    private ModelSyringe model;

    public RenderSyringe() {
        this.field_76989_e = 0.0f;
        this.model = new ModelSyringe();
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_) {
        return new ResourceLocation("thaumichorizons", "textures/models/syringe.png");
    }

    public void func_76986_a(Entity ent, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        if (ent instanceof EntitySyringe) {
            EntitySyringe syringe = (EntitySyringe)ent;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)p_76986_2_), (float)((float)p_76986_4_), (float)((float)p_76986_6_));
            GL11.glRotatef((float)(syringe.field_70177_z + 90.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(-syringe.field_70125_A), (float)0.0f, (float)0.0f, (float)1.0f);
            this.func_110777_b(syringe);
            this.model.render(syringe, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, null);
            GL11.glPopMatrix();
        }
    }
}

