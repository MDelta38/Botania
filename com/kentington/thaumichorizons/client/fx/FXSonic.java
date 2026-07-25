/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.client.model.AdvancedModelLoader
 *  net.minecraftforge.client.model.IModelCustom
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 */
package com.kentington.thaumichorizons.client.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;

public class FXSonic
extends EntityFX {
    float yaw = 0.0f;
    float pitch = 0.0f;
    private IModelCustom model;
    private static final ResourceLocation MODEL = new ResourceLocation("thaumcraft", "textures/models/hemis.obj");

    public FXSonic(World world, double d, double d1, double d2, int age, int dir) {
        super(world, d, d1, d2, 0.0, 0.0, 0.0);
        this.field_70552_h = 0.0f;
        this.field_70553_i = 1.0f;
        this.field_70551_j = 1.0f;
        this.field_70545_g = 0.0f;
        this.field_70179_y = 0.0;
        this.field_70181_x = 0.0;
        this.field_70159_w = 0.0;
        this.field_70547_e = age + this.field_70146_Z.nextInt(age / 2);
        this.field_70145_X = false;
        this.func_70105_a(0.01f, 0.01f);
        this.field_70145_X = true;
        this.field_70544_f = 1.0f;
        switch (dir) {
            case 0: {
                this.pitch = 90.0f;
                break;
            }
            case 1: {
                this.pitch = -90.0f;
                break;
            }
            case 2: {
                this.yaw = 180.0f;
                break;
            }
            case 3: {
                this.yaw = 0.0f;
                break;
            }
            case 4: {
                this.yaw = 90.0f;
                break;
            }
            case 5: {
                this.yaw = 270.0f;
            }
        }
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
    }

    public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        tessellator.func_78381_a();
        GL11.glPushMatrix();
        GL11.glDisable((int)2884);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        if (this.model == null) {
            this.model = AdvancedModelLoader.loadModel((ResourceLocation)MODEL);
        }
        float fade = ((float)this.field_70546_d + f) / (float)this.field_70547_e;
        float xx = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)f - field_70556_an);
        float yy = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)f - field_70554_ao);
        float zz = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)f - field_70555_ap);
        GL11.glTranslated((double)xx, (double)yy, (double)zz);
        float b = 1.0f;
        int frame = Math.min(15, (int)(14.0f * fade) + 1);
        UtilsFX.bindTexture((String)("textures/models/ripple" + frame + ".png"));
        b = 0.5f;
        int i = 220;
        int j = i % 65536;
        int k = i / 65536;
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)j / 1.0f), (float)((float)k / 1.0f));
        GL11.glRotatef((float)(-this.yaw), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)this.pitch, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glScaled((double)0.5, (double)0.5, (double)-0.5);
        GL11.glColor4f((float)0.0f, (float)b, (float)b, (float)1.0f);
        this.model.renderAll();
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2884);
        GL11.glPopMatrix();
        Minecraft.func_71410_x().field_71446_o.func_110577_a(UtilsFX.getParticleTexture());
        tessellator.func_78382_b();
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_70106_y();
        }
    }
}

