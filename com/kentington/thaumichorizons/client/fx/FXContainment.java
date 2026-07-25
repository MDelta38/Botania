/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 */
package com.kentington.thaumichorizons.client.fx;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;

public class FXContainment
extends EntityFX {
    public static final ResourceLocation portaltex = new ResourceLocation("thaumcraft", "textures/misc/eldritch_portal.png");

    public FXContainment(World par1World, double par2, double par4, double par6) {
        super(par1World, par2, par4, par6, 0.0, 0.0, 0.0);
        this.field_70551_j = 0.6f;
        this.field_70553_i = 0.6f;
        this.field_70552_h = 0.6f;
        this.field_82339_as = 0.5f;
        this.field_70544_f = 1.0f;
        this.field_70547_e = 40;
        this.field_70179_y = 0.0;
        this.field_70181_x = 0.0;
        this.field_70159_w = 0.0;
        this.field_70552_h = 1.0f;
        this.field_70553_i = 1.0f;
        this.field_70551_j = 1.0f;
        this.field_70545_g = 0.0f;
        this.field_70145_X = true;
    }

    public void func_70539_a(Tessellator p_70539_1_, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_) {
        long nt = System.nanoTime();
        long time = nt / 50000000L;
        int frame = (int)time % 16;
        float f2 = (float)frame / 16.0f;
        float f3 = f2 + 0.0625f;
        float f4 = 0.0f;
        float f5 = 1.0f;
        UtilsFX.bindTexture((ResourceLocation)portaltex);
        GL11.glPushMatrix();
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        float f10 = 3.0f * this.field_70544_f;
        float f11 = (float)(this.field_70165_t - field_70556_an);
        float f12 = (float)(this.field_70163_u - field_70554_ao);
        float f13 = (float)(this.field_70161_v - field_70555_ap);
        p_70539_1_.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.1f);
        p_70539_1_.func_78374_a((double)(f11 - p_70539_3_ * f10 - p_70539_6_ * f10), (double)(f12 - p_70539_4_ * f10), (double)(f13 - p_70539_5_ * f10 - p_70539_7_ * f10), (double)f2, (double)f4);
        p_70539_1_.func_78374_a((double)(f11 - p_70539_3_ * f10 + p_70539_6_ * f10), (double)(f12 + p_70539_4_ * f10), (double)(f13 - p_70539_5_ * f10 + p_70539_7_ * f10), (double)f3, (double)f4);
        p_70539_1_.func_78374_a((double)(f11 + p_70539_3_ * f10 + p_70539_6_ * f10), (double)(f12 + p_70539_4_ * f10), (double)(f13 + p_70539_5_ * f10 + p_70539_7_ * f10), (double)f3, (double)f5);
        p_70539_1_.func_78374_a((double)(f11 + p_70539_3_ * f10 - p_70539_6_ * f10), (double)(f12 - p_70539_4_ * f10), (double)(f13 + p_70539_5_ * f10 - p_70539_7_ * f10), (double)f2, (double)f5);
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
    }

    public int func_70537_b() {
        return 1;
    }

    public void func_70071_h_() {
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_70106_y();
            return;
        }
        this.field_70544_f /= 1.15f;
    }
}

