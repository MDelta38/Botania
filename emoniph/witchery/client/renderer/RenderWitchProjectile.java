/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderSnowball
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.util.IIcon
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.entity.EntityWitchProjectile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderWitchProjectile
extends RenderSnowball {
    public RenderWitchProjectile(Item item) {
        this(item, 0);
    }

    public RenderWitchProjectile(Item item, int damageValue) {
        super(item, damageValue);
    }

    public void func_76986_a(Entity entity, double par2, double par4, double par6, float par8, float par9) {
        if (entity instanceof EntityWitchProjectile) {
            EntityWitchProjectile entityProjectile = (EntityWitchProjectile)entity;
            IIcon icon = Witchery.Items.GENERIC.func_77617_a(entityProjectile.getDamageValue());
            if (icon != null) {
                GL11.glPushMatrix();
                GL11.glTranslatef((float)((float)par2), (float)((float)par4), (float)((float)par6));
                GL11.glEnable((int)32826);
                GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
                this.func_110777_b(entity);
                Tessellator tessellator = Tessellator.field_78398_a;
                if (entityProjectile.getDamageValue() != Witchery.Items.GENERIC.itemQuicklime.damageValue) {
                    GL11.glPushMatrix();
                    this.func_77026_22(tessellator, Items.field_151068_bn.func_77618_c(16384, 1));
                    GL11.glPopMatrix();
                }
                this.func_77026_22(tessellator, icon);
                GL11.glDisable((int)32826);
                GL11.glPopMatrix();
            }
        } else {
            super.func_76986_a(entity, par2, par4, par6, par8, par9);
        }
    }

    private void func_77026_22(Tessellator par1Tessellator, IIcon par2Icon) {
        float f = par2Icon.func_94209_e();
        float f1 = par2Icon.func_94212_f();
        float f2 = par2Icon.func_94206_g();
        float f3 = par2Icon.func_94210_h();
        float f4 = 1.0f;
        float f5 = 0.5f;
        float f6 = 0.25f;
        GL11.glRotatef((float)(180.0f - this.field_76990_c.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-this.field_76990_c.field_78732_j), (float)1.0f, (float)0.0f, (float)0.0f);
        par1Tessellator.func_78382_b();
        par1Tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
        par1Tessellator.func_78374_a((double)(0.0f - f5), (double)(0.0f - f6), 0.0, (double)f, (double)f3);
        par1Tessellator.func_78374_a((double)(f4 - f5), (double)(0.0f - f6), 0.0, (double)f1, (double)f3);
        par1Tessellator.func_78374_a((double)(f4 - f5), (double)(f4 - f6), 0.0, (double)f1, (double)f2);
        par1Tessellator.func_78374_a((double)(0.0f - f5), (double)(f4 - f6), 0.0, (double)f, (double)f2);
        par1Tessellator.func_78381_a();
    }
}

