/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderEntity
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.render.entity;

import java.util.Random;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.common.item.ItemSpark;

public class RenderSparkBase<T extends Entity>
extends RenderEntity {
    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        Entity tEntity = par1Entity;
        IIcon iicon = this.getBaseIcon(tEntity);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)par2), (float)((float)par4), (float)((float)par6));
        GL11.glEnable((int)32826);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glAlphaFunc((int)516, (float)0.05f);
        double time = (float)ClientTickHandler.ticksInGame + par9;
        float a = 0.1f + (float)(1 - par1Entity.func_70096_w().func_75679_c(27)) * 0.8f;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)((0.7f + 0.3f * (float)(Math.sin((time += (double)new Random(par1Entity.func_145782_y()).nextInt()) / 5.0) + 0.5) * 2.0f) * a));
        float scale = 0.75f + 0.1f * (float)Math.sin(time / 10.0);
        GL11.glScalef((float)scale, (float)scale, (float)scale);
        this.func_110777_b(par1Entity);
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glPushMatrix();
        float r = 180.0f - this.field_76990_c.field_78735_i;
        GL11.glRotatef((float)r, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-this.field_76990_c.field_78732_j), (float)1.0f, (float)0.0f, (float)0.0f);
        this.func_77026_a(tessellator, iicon);
        IIcon spinningIcon = this.getSpinningIcon(tEntity);
        if (spinningIcon != null) {
            GL11.glTranslatef((float)(-0.02f + (float)Math.sin(time / 20.0) * 0.2f), (float)(0.24f + (float)Math.cos(time / 20.0) * 0.2f), (float)0.005f);
            GL11.glScalef((float)0.2f, (float)0.2f, (float)0.2f);
            this.colorSpinningIcon(tEntity, a);
            this.func_77026_a(tessellator, spinningIcon);
        }
        GL11.glPopMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.renderCallback(tEntity, par9);
        GL11.glDisable((int)3042);
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
    }

    public IIcon getBaseIcon(T entity) {
        return ItemSpark.worldIcon;
    }

    public void colorSpinningIcon(T entity, float a) {
    }

    public IIcon getSpinningIcon(T entity) {
        return null;
    }

    public void renderCallback(T entity, float pticks) {
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_) {
        return TextureMap.field_110576_c;
    }

    private void func_77026_a(Tessellator p_77026_1_, IIcon p_77026_2_) {
        float f = p_77026_2_.func_94209_e();
        float f1 = p_77026_2_.func_94212_f();
        float f2 = p_77026_2_.func_94206_g();
        float f3 = p_77026_2_.func_94210_h();
        float f4 = 1.0f;
        float f5 = 0.5f;
        float f6 = 0.25f;
        p_77026_1_.func_78382_b();
        p_77026_1_.func_78375_b(0.0f, 1.0f, 0.0f);
        p_77026_1_.func_78380_c(240);
        p_77026_1_.func_78374_a((double)(0.0f - f5), (double)(0.0f - f6), 0.0, (double)f, (double)f3);
        p_77026_1_.func_78374_a((double)(f4 - f5), (double)(0.0f - f6), 0.0, (double)f1, (double)f3);
        p_77026_1_.func_78374_a((double)(f4 - f5), (double)(f4 - f6), 0.0, (double)f1, (double)f2);
        p_77026_1_.func_78374_a((double)(0.0f - f5), (double)(f4 - f6), 0.0, (double)f, (double)f2);
        p_77026_1_.func_78381_a();
    }
}

