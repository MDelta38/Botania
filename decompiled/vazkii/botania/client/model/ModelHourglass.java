/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.model;

import java.awt.Color;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import org.lwjgl.opengl.GL11;

public class ModelHourglass
extends ModelBase {
    public ModelRenderer ring;
    public ModelRenderer base1;
    public ModelRenderer base2;
    public ModelRenderer glass1;
    public ModelRenderer sand1;
    public ModelRenderer glass2;
    public ModelRenderer sand2;

    public ModelHourglass() {
        this.field_78090_t = 48;
        this.field_78089_u = 24;
        this.sand2 = new ModelRenderer((ModelBase)this, 24, 0);
        this.sand2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sand2.func_78790_a(0.0f, 0.0f, 0.0f, 5, 5, 5, 0.0f);
        this.sand1 = new ModelRenderer((ModelBase)this, 24, 0);
        this.sand1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.sand1.func_78790_a(0.0f, 0.0f, 0.0f, 5, 5, 5, 0.0f);
        this.glass1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.glass1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.glass1.func_78790_a(-3.0f, -6.501f, -3.0f, 6, 6, 6, 0.0f);
        this.base2 = new ModelRenderer((ModelBase)this, 0, 12);
        this.base2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.base2.func_78790_a(-3.5f, 6.502f, -3.5f, 7, 1, 7, 0.0f);
        this.base1 = new ModelRenderer((ModelBase)this, 0, 12);
        this.base1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.base1.func_78790_a(-3.5f, -7.502f, -3.5f, 7, 1, 7, 0.0f);
        this.ring = new ModelRenderer((ModelBase)this, 28, 12);
        this.ring.func_78793_a(0.0f, 15.5f, 0.0f);
        this.ring.func_78790_a(-2.0f, -16.0f, -2.0f, 4, 1, 4, 0.0f);
        this.glass2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.glass2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.glass2.func_78790_a(-3.0f, 0.501f, -3.0f, 6, 6, 6, 0.0f);
    }

    public void render(float fract1, float fract2, boolean flip, int color) {
        if (flip) {
            float fract3 = fract1;
            fract1 = fract2;
            fract2 = fract3;
        }
        float f = 0.0625f;
        this.ring.func_78785_a(f);
        this.base1.func_78785_a(f);
        this.base2.func_78785_a(f);
        Color c = new Color(color);
        GL11.glColor3ub((byte)((byte)c.getRed()), (byte)((byte)c.getGreen()), (byte)((byte)c.getBlue()));
        GL11.glPushAttrib((int)2977);
        GL11.glEnable((int)2977);
        if (fract1 > 0.0f) {
            GL11.glPushMatrix();
            if (flip) {
                GL11.glTranslatef((float)(-2.5f * f), (float)(1.0f * f), (float)(-2.5f * f));
            } else {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)(-2.5f * f), (float)(-6.0f * f), (float)(-2.5f * f));
            }
            GL11.glScalef((float)1.0f, (float)fract1, (float)1.0f);
            this.sand1.func_78785_a(f);
            GL11.glPopMatrix();
        }
        if (fract2 > 0.0f) {
            GL11.glPushMatrix();
            if (flip) {
                GL11.glTranslatef((float)(-2.5f * f), (float)(-6.0f * f), (float)(-2.5f * f));
            } else {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)(-2.5f * f), (float)(1.0f * f), (float)(-2.5f * f));
            }
            GL11.glScalef((float)1.0f, (float)fract2, (float)1.0f);
            this.sand2.func_78785_a(f);
            GL11.glPopMatrix();
        }
        GL11.glPopAttrib();
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        this.glass1.func_78785_a(f);
        this.glass2.func_78785_a(f);
    }
}

