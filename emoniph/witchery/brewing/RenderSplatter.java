/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderSnowball
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.Item
 *  net.minecraft.util.IIcon
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.brewing;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.EntitySplatter;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderSplatter
extends RenderSnowball {
    public RenderSplatter(Item item) {
        this(item, 0);
    }

    public RenderSplatter(Item item, int damageValue) {
        super(item, damageValue);
    }

    public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9) {
        EntitySplatter brew = (EntitySplatter)entity;
        IIcon icon = Witchery.Items.GENERIC.func_77617_a(Witchery.Items.GENERIC.itemQuartzSphere.damageValue);
        if (icon != null) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)par2), (float)((float)par4), (float)((float)par6));
            GL11.glEnable((int)32826);
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
            this.func_110777_b(entity);
            Tessellator tessellator = Tessellator.field_78398_a;
            int color = brew.getColor();
            if (color != -1) {
                float red = (float)(color >> 16 & 0xFF) / 255.0f;
                float green = (float)(color >> 8 & 0xFF) / 255.0f;
                float blue = (float)(color & 0xFF) / 255.0f;
                GL11.glColor3f((float)red, (float)green, (float)blue);
            }
            this.drawIcon(tessellator, icon);
            GL11.glDisable((int)32826);
            GL11.glPopMatrix();
        }
    }

    private void drawIcon(Tessellator tessalator, IIcon icon) {
        float f = icon.func_94209_e();
        float f1 = icon.func_94212_f();
        float f2 = icon.func_94206_g();
        float f3 = icon.func_94210_h();
        float f4 = 1.0f;
        float f5 = 0.5f;
        float f6 = 0.25f;
        GL11.glRotatef((float)(180.0f - this.field_76990_c.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-this.field_76990_c.field_78732_j), (float)1.0f, (float)0.0f, (float)0.0f);
        tessalator.func_78382_b();
        tessalator.func_78375_b(0.0f, 1.0f, 0.0f);
        tessalator.func_78374_a((double)(0.0f - f5), (double)(0.0f - f6), 0.0, (double)f, (double)f3);
        tessalator.func_78374_a((double)(f4 - f5), (double)(0.0f - f6), 0.0, (double)f1, (double)f3);
        tessalator.func_78374_a((double)(f4 - f5), (double)(f4 - f6), 0.0, (double)f1, (double)f2);
        tessalator.func_78374_a((double)(0.0f - f5), (double)(f4 - f6), 0.0, (double)f, (double)f2);
        tessalator.func_78381_a();
    }
}

