/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Items
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.entity.EntitySpellEffect;
import com.emoniph.witchery.infusion.infusions.symbols.EffectRegistry;
import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffectProjectile;
import com.emoniph.witchery.util.RenderUtil;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderSpellEffect
extends Render {
    private float field_77002_a;
    private static final ResourceLocation RESOURCE_LOCATION = new ResourceLocation("witchery", "textures/entities/spelleffect.png");

    public RenderSpellEffect(float par1) {
        this.field_77002_a = par1;
    }

    public void doRenderSpellEffect(EntitySpellEffect effectEntity, double par2, double par4, double par6, float par8, float par9) {
        GL11.glPushMatrix();
        this.func_110777_b(effectEntity);
        GL11.glTranslatef((float)((float)par2), (float)((float)par4), (float)((float)par6));
        RenderUtil.blend(true);
        float scale = 1.0f;
        int color = 0xFF0000;
        IIcon icon2 = Items.field_151126_ay.func_77617_a(0);
        SymbolEffect effect = EffectRegistry.instance().getEffect(effectEntity.getEffectID());
        if (effect != null && effect instanceof SymbolEffectProjectile) {
            SymbolEffectProjectile projectileEffect = (SymbolEffectProjectile)effect;
            color = projectileEffect.getColor();
            scale = projectileEffect.getSize();
        }
        float f2 = this.field_77002_a * scale * 0.65f;
        GL11.glScalef((float)(f2 / 1.0f), (float)(f2 / 1.0f), (float)(f2 / 1.0f));
        float red = (float)(color >>> 16 & 0xFF) / 256.0f;
        float green = (float)(color >>> 8 & 0xFF) / 256.0f;
        float blue = (float)(color & 0xFF) / 256.0f;
        GL11.glColor4f((float)red, (float)green, (float)blue, (float)0.55f);
        Tessellator tessellator = Tessellator.field_78398_a;
        float f3 = icon2.func_94209_e();
        float f4 = icon2.func_94212_f();
        float f5 = icon2.func_94206_g();
        float f6 = icon2.func_94210_h();
        float f7 = 1.0f;
        float f8 = 0.5f;
        float f9 = 0.25f;
        GL11.glRotatef((float)(180.0f - this.field_76990_c.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-this.field_76990_c.field_78732_j), (float)1.0f, (float)0.0f, (float)0.0f);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
        tessellator.func_78374_a((double)(0.0f - f8), (double)(0.0f - f9), 0.0, (double)f3, (double)f6);
        tessellator.func_78374_a((double)(f7 - f8), (double)(0.0f - f9), 0.0, (double)f4, (double)f6);
        tessellator.func_78374_a((double)(f7 - f8), (double)(1.0f - f9), 0.0, (double)f4, (double)f5);
        tessellator.func_78374_a((double)(0.0f - f8), (double)(1.0f - f9), 0.0, (double)f3, (double)f5);
        tessellator.func_78381_a();
        RenderUtil.blend(false);
        GL11.glPopMatrix();
    }

    protected ResourceLocation getSpellEffectTextures(EntitySpellEffect effect) {
        return TextureMap.field_110576_c;
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return this.getSpellEffectTextures((EntitySpellEffect)par1Entity);
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderSpellEffect((EntitySpellEffect)par1Entity, par2, par4, par6, par8, par9);
    }
}

