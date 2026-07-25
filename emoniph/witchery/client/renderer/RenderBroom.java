/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.client.model.ModelBroom;
import com.emoniph.witchery.entity.EntityBroom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderBroom
extends Render {
    private static final ResourceLocation TEXTURE_URL = new ResourceLocation("witchery", "textures/entities/Broom.png");
    protected ModelBase modelBroom;

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return this.func_110832_a((EntityBroom)par1Entity);
    }

    public RenderBroom() {
        this.field_76989_e = 0.5f;
        this.modelBroom = new ModelBroom();
    }

    protected ResourceLocation func_110832_a(EntityBroom par1Entity) {
        return TEXTURE_URL;
    }

    public void renderBroom(EntityBroom par1EntityBoat, double par2, double par4, double par6, float par8, float par9) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)par2), (float)((float)par4 + 1.0f), (float)((float)par6));
        GL11.glRotatef((float)(90.0f - par8), (float)0.0f, (float)1.0f, (float)0.0f);
        float f2 = (float)par1EntityBoat.getTimeSinceHit() - par9;
        float f3 = par1EntityBoat.getDamageTaken() - par9;
        if (f3 < 0.0f) {
            f3 = 0.0f;
        }
        if (f2 > 0.0f) {
            GL11.glRotatef((float)(MathHelper.func_76126_a((float)f2) * f2 * f3 / 10.0f * (float)par1EntityBoat.getForwardDirection()), (float)1.0f, (float)0.0f, (float)0.0f);
        }
        float f4 = 0.75f;
        GL11.glScalef((float)f4, (float)f4, (float)f4);
        GL11.glScalef((float)(1.0f / f4), (float)(1.0f / f4), (float)(1.0f / f4));
        this.func_110777_b(par1EntityBoat);
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        this.modelBroom.func_78088_a((Entity)par1EntityBoat, 0.0f, 0.0f, -0.1f, 0.0f, 0.0f, 0.0625f);
        GL11.glPopMatrix();
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderBroom((EntityBroom)par1Entity, par2, par4, par6, par8, par9);
    }
}

