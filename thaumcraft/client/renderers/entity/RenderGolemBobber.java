/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Vec3
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import thaumcraft.common.entities.golems.EntityGolemBobber;

@SideOnly(value=Side.CLIENT)
public class RenderGolemBobber
extends Render {
    private static final ResourceLocation tex = new ResourceLocation("textures/particle/particles.png");

    public void doRender(EntityGolemBobber bobber, double xx, double yy, double zz, float p_147922_8_, float p_147922_9_) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)xx), (float)((float)yy), (float)((float)zz));
        GL11.glEnable((int)32826);
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        this.func_110777_b(bobber);
        Tessellator tessellator = Tessellator.field_78398_a;
        int b0 = 1;
        int b1 = 2;
        float f2 = (float)(b0 * 8 + 0) / 128.0f;
        float f3 = (float)(b0 * 8 + 8) / 128.0f;
        float f4 = (float)(b1 * 8 + 0) / 128.0f;
        float f5 = (float)(b1 * 8 + 8) / 128.0f;
        float f6 = 1.0f;
        float f7 = 0.5f;
        float f8 = 0.5f;
        GL11.glRotatef((float)(180.0f - this.field_76990_c.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-this.field_76990_c.field_78732_j), (float)1.0f, (float)0.0f, (float)0.0f);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
        tessellator.func_78374_a((double)(0.0f - f7), (double)(0.0f - f8), 0.0, (double)f2, (double)f5);
        tessellator.func_78374_a((double)(f6 - f7), (double)(0.0f - f8), 0.0, (double)f3, (double)f5);
        tessellator.func_78374_a((double)(f6 - f7), (double)(1.0f - f8), 0.0, (double)f3, (double)f4);
        tessellator.func_78374_a((double)(0.0f - f7), (double)(1.0f - f8), 0.0, (double)f2, (double)f4);
        tessellator.func_78381_a();
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
        if (bobber.fisher != null) {
            float f9 = (float)bobber.fisher.rightArm / 3.0f;
            float f10 = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)f9) * (float)Math.PI));
            Vec3 vec3 = Vec3.func_72443_a((double)-0.5, (double)0.03, (double)0.8);
            vec3.func_72440_a(-(bobber.fisher.field_70127_C + (bobber.fisher.field_70125_A - bobber.fisher.field_70127_C) * p_147922_9_) * (float)Math.PI / 180.0f);
            vec3.func_72442_b(-(bobber.fisher.field_70126_B + (bobber.fisher.field_70177_z - bobber.fisher.field_70126_B) * p_147922_9_) * (float)Math.PI / 180.0f);
            vec3.func_72442_b(f10 * 0.5f);
            vec3.func_72440_a(-f10 * 0.7f);
            double d3 = bobber.fisher.field_70169_q + (bobber.fisher.field_70165_t - bobber.fisher.field_70169_q) * (double)p_147922_9_ + vec3.field_72450_a;
            double d4 = bobber.fisher.field_70167_r + (bobber.fisher.field_70163_u - bobber.fisher.field_70167_r) * (double)p_147922_9_ + vec3.field_72448_b;
            double d5 = bobber.fisher.field_70166_s + (bobber.fisher.field_70161_v - bobber.fisher.field_70166_s) * (double)p_147922_9_ + vec3.field_72449_c;
            double d6 = bobber.fisher.func_70047_e();
            float f11 = (bobber.fisher.field_70760_ar + (bobber.fisher.field_70761_aq - bobber.fisher.field_70760_ar) * p_147922_9_) * (float)Math.PI / 180.0f;
            double d7 = MathHelper.func_76126_a((float)f11);
            double d9 = MathHelper.func_76134_b((float)f11);
            d3 = bobber.fisher.field_70169_q + (bobber.fisher.field_70165_t - bobber.fisher.field_70169_q) * (double)p_147922_9_ - d9 * 0.25 - d7 * 0.7;
            d4 = bobber.fisher.field_70167_r + d6 + (bobber.fisher.field_70163_u - bobber.fisher.field_70167_r) * (double)p_147922_9_ - 0.4;
            d5 = bobber.fisher.field_70166_s + (bobber.fisher.field_70161_v - bobber.fisher.field_70166_s) * (double)p_147922_9_ - d7 * 0.25 + d9 * 0.7;
            double d14 = bobber.field_70169_q + (bobber.field_70165_t - bobber.field_70169_q) * (double)p_147922_9_;
            double d8 = bobber.field_70167_r + (bobber.field_70163_u - bobber.field_70167_r) * (double)p_147922_9_ + 0.25;
            double d10 = bobber.field_70166_s + (bobber.field_70161_v - bobber.field_70166_s) * (double)p_147922_9_;
            double d11 = (float)(d3 - d14);
            double d12 = (float)(d4 - d8);
            double d13 = (float)(d5 - d10);
            GL11.glDisable((int)3553);
            GL11.glDisable((int)2896);
            tessellator.func_78371_b(3);
            tessellator.func_78378_d(0);
            int b2 = 16;
            for (int i = 0; i <= b2; ++i) {
                float f12 = (float)i / (float)b2;
                tessellator.func_78377_a(xx + d11 * (double)f12, yy + d12 * (double)(f12 * f12 + f12) * 0.5 + 0.25, zz + d13 * (double)f12);
            }
            tessellator.func_78381_a();
            GL11.glEnable((int)2896);
            GL11.glEnable((int)3553);
        }
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return tex;
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRender((EntityGolemBobber)par1Entity, par2, par4, par6, par8, par9);
    }
}

