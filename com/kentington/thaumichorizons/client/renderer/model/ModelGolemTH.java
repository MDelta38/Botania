/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.renderers.models.entities.ModelGolem
 *  thaumcraft.common.entities.golems.EntityGolemBase
 */
package com.kentington.thaumichorizons.client.renderer.model;

import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.models.entities.ModelGolem;
import thaumcraft.common.entities.golems.EntityGolemBase;

public class ModelGolemTH
extends ModelGolem {
    public ModelGolemTH(boolean p) {
        super(p);
    }

    public void setRotationAngles(Entity en, float par1, float par2, float par3, float par4, float par5, float par6) {
        byte core = 0;
        if (en instanceof EntityGolemBase) {
            core = ((EntityGolemBase)en).getCore();
            if (this.pass == 0 && ((EntityGolemBase)en).healing > 0) {
                float h1 = (float)((EntityGolemBase)en).healing / 10.0f;
                float h2 = (float)((EntityGolemBase)en).healing / 5.0f;
                GL11.glColor3f((float)(0.5f + h1), (float)(0.9f + h2), (float)(0.5f + h1));
            }
        }
        this.golemHead.field_78796_g = par4 / 57.295776f;
        this.golemHead.field_78795_f = par5 / 57.295776f;
        this.golemRightLeg.field_78795_f = -1.5f * this.func_78172_a(par1, 13.0f) * par2;
        this.golemLeftLeg.field_78795_f = 1.5f * this.func_78172_a(par1, 13.0f) * par2;
        this.golemRightLeg.field_78796_g = 0.0f;
        this.golemLeftLeg.field_78796_g = 0.0f;
        this.golemLeftArm.field_78808_h = 0.0f;
        this.golemRightArm.field_78808_h = 0.0f;
        if (core == 6) {
            float s = (1.0f - (0.5f + (float)Math.min(64, ((EntityGolemBase)en).getCarryLimit()) / 128.0f)) * 25.0f;
            this.golemLeftArm.field_78808_h = s / 57.295776f;
            this.golemRightArm.field_78808_h = -s / 57.295776f;
        }
    }

    private float func_78172_a(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5f) - par2 * 0.25f) / (par2 * 0.25f);
    }
}

