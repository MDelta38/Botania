/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderDart
extends Render {
    private static final ResourceLocation rl = new ResourceLocation("textures/entity/arrow.png");

    public void renderArrow(EntityArrow par1EntityArrow, double par2, double par4, double par6, float par8, float par9) {
        this.func_110777_b((Entity)par1EntityArrow);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)par2), (float)((float)par4), (float)((float)par6));
        GL11.glRotatef((float)(par1EntityArrow.field_70126_B + (par1EntityArrow.field_70177_z - par1EntityArrow.field_70126_B) * par9 - 90.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(par1EntityArrow.field_70127_C + (par1EntityArrow.field_70125_A - par1EntityArrow.field_70127_C) * par9), (float)0.0f, (float)0.0f, (float)1.0f);
        Tessellator var10 = Tessellator.field_78398_a;
        int var11 = 0;
        float var12 = 0.0f;
        float var13 = 0.5f;
        float var14 = (float)(0 + var11 * 10) / 32.0f;
        float var15 = (float)(5 + var11 * 10) / 32.0f;
        float var16 = 0.0f;
        float var17 = 0.15625f;
        float var18 = (float)(5 + var11 * 10) / 32.0f;
        float var19 = (float)(10 + var11 * 10) / 32.0f;
        float var20 = 0.025625f;
        GL11.glEnable((int)32826);
        float var21 = (float)par1EntityArrow.field_70249_b - par9;
        if (var21 > 0.0f) {
            float var22 = -MathHelper.func_76126_a((float)(var21 * 3.0f)) * var21;
            GL11.glRotatef((float)var22, (float)0.0f, (float)0.0f, (float)1.0f);
        }
        GL11.glColor3f((float)0.5f, (float)0.5f, (float)0.6f);
        GL11.glRotatef((float)45.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glScalef((float)(var20 * 0.75f), (float)var20, (float)var20);
        GL11.glTranslatef((float)-4.0f, (float)0.0f, (float)0.0f);
        GL11.glNormal3f((float)var20, (float)0.0f, (float)0.0f);
        var10.func_78382_b();
        var10.func_78374_a(-7.0, -2.0, -2.0, (double)var16, (double)var18);
        var10.func_78374_a(-7.0, -2.0, 2.0, (double)var17, (double)var18);
        var10.func_78374_a(-7.0, 2.0, 2.0, (double)var17, (double)var19);
        var10.func_78374_a(-7.0, 2.0, -2.0, (double)var16, (double)var19);
        var10.func_78381_a();
        GL11.glNormal3f((float)(-var20), (float)0.0f, (float)0.0f);
        var10.func_78382_b();
        var10.func_78374_a(-7.0, 2.0, -2.0, (double)var16, (double)var18);
        var10.func_78374_a(-7.0, 2.0, 2.0, (double)var17, (double)var18);
        var10.func_78374_a(-7.0, -2.0, 2.0, (double)var17, (double)var19);
        var10.func_78374_a(-7.0, -2.0, -2.0, (double)var16, (double)var19);
        var10.func_78381_a();
        for (int var23 = 0; var23 < 4; ++var23) {
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glNormal3f((float)0.0f, (float)0.0f, (float)var20);
            var10.func_78382_b();
            var10.func_78374_a(-8.0, -2.0, 0.0, (double)var12, (double)var14);
            var10.func_78374_a(8.0, -2.0, 0.0, (double)var13, (double)var14);
            var10.func_78374_a(8.0, 2.0, 0.0, (double)var13, (double)var15);
            var10.func_78374_a(-8.0, 2.0, 0.0, (double)var12, (double)var15);
            var10.func_78381_a();
        }
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderArrow((EntityArrow)par1Entity, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return rl;
    }
}

