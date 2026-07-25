/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.potion.Potion
 *  org.lwjgl.opengl.GL11
 */
package witchinggadgets.client.render;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import org.lwjgl.opengl.GL11;
import witchinggadgets.common.WGConfig;

public class ModelCloak
extends ModelBiped {
    private double[] circPos = new double[32];
    public boolean doAnimation = true;
    int colour;

    public ModelCloak(int colour) {
        this.colour = colour;
        this.circPos[0] = 0.5;
        this.circPos[1] = 0.49039;
        this.circPos[2] = 0.46194;
        this.circPos[3] = 0.41573;
        this.circPos[4] = 0.35355;
        this.circPos[5] = 0.27779;
        this.circPos[6] = 0.19134;
        this.circPos[7] = 0.09755;
        this.circPos[8] = 0.0;
        this.circPos[9] = -0.09755;
        this.circPos[10] = -0.19134;
        this.circPos[11] = -0.27779;
        this.circPos[12] = -0.35355;
        this.circPos[13] = -0.41573;
        this.circPos[14] = -0.46194;
        this.circPos[15] = -0.49039;
        this.circPos[16] = -0.5;
        this.circPos[17] = -0.49039;
        this.circPos[18] = -0.46194;
        this.circPos[19] = -0.41573;
        this.circPos[20] = -0.35355;
        this.circPos[21] = -0.27779;
        this.circPos[22] = -0.19134;
        this.circPos[23] = -0.09755;
        this.circPos[24] = 0.0;
        this.circPos[25] = 0.09755;
        this.circPos[26] = 0.19134;
        this.circPos[27] = 0.27779;
        this.circPos[28] = 0.35355;
        this.circPos[29] = 0.41573;
        this.circPos[30] = 0.46194;
        this.circPos[31] = 0.49039;
    }

    public void func_78088_a(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        int it3;
        int it2;
        int it1;
        int it0;
        int i;
        this.field_78116_c.field_78806_j = false;
        this.field_78114_d.field_78806_j = false;
        this.field_78115_e.field_78806_j = false;
        this.field_78112_f.field_78806_j = false;
        this.field_78113_g.field_78806_j = false;
        this.field_78123_h.field_78806_j = false;
        this.field_78124_i.field_78806_j = false;
        super.func_78088_a(entity, par2, par3, par4, par5, par6, par7);
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        boolean drawHood = false;
        Tessellator tessellator = Tessellator.field_78398_a;
        if (this.doAnimation && WGConfig.cloakAnimationMode == 1) {
            if (this.field_78124_i.field_78795_f * 57.295776f > 0.0f) {
                GL11.glRotatef((float)(this.field_78124_i.field_78795_f * 57.295776f * 0.15f), (float)1.0f, (float)0.0f, (float)0.0f);
            }
            if (this.field_78123_h.field_78795_f * 57.295776f > 0.0f) {
                GL11.glRotatef((float)(this.field_78123_h.field_78795_f * 57.295776f * 0.15f), (float)1.0f, (float)0.0f, (float)0.0f);
            }
        }
        GL11.glTranslatef((float)0.0f, (float)1.45f, (float)0.0f);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)1.0f);
        GL11.glColor3f((float)((float)(this.colour >> 16 & 0xFF) / 255.0f), (float)((float)(this.colour >> 8 & 0xFF) / 255.0f), (float)((float)(this.colour & 0xFF) / 255.0f));
        double d0_1 = this.circPos[0] * 1.0;
        double d1_1 = this.circPos[1] * 1.0;
        double d2_1 = this.circPos[24] * 1.0;
        double d3_1 = this.circPos[25] * 1.0;
        if (this.doAnimation && entity instanceof EntityLivingBase && ((EntityLivingBase)entity).func_70660_b(Potion.field_76441_p) != null) {
            GL11.glColor4d((double)1.0, (double)1.0, (double)1.0, (double)0.1001);
        }
        for (i = 0; i < 15; ++i) {
            it0 = i;
            it1 = it0 + 1;
            if (it1 > 31) {
                it1 -= 31;
            }
            if ((it2 = i + 24) > 31) {
                it2 -= 31;
            }
            if ((it3 = it2 + 1) > 31) {
                it3 -= 31;
            }
            for (int j = 0; j < 8; ++j) {
                double offsettingAngle;
                int jt0 = j;
                int jt1 = jt0 + 1;
                double h0 = this.circPos[jt0] * this.circPos[jt0] * 7.0;
                double h1 = this.circPos[jt1] * this.circPos[jt1] * 7.0;
                double[] dividerA = new double[]{0.3, 0.725, 0.75, 0.8, 0.825, 0.9, 1.0, 1.1};
                double divider = dividerA[j];
                double d0 = this.circPos[it0] * 1.5 * divider;
                double d1 = this.circPos[it1] * 1.5 * divider;
                double d2 = this.circPos[it2] * 1.5 * divider;
                double d3 = this.circPos[it3] * 1.5 * divider;
                double f3 = (double)i * 0.0625;
                double f4 = (double)(i + 1) * 0.0625;
                double f5 = (double)j * 0.125;
                double f6 = (double)(j + 1) * 0.125;
                if (j == 2) {
                    h0 *= 0.975;
                }
                if (j == 1) {
                    h1 *= 0.975;
                    h0 *= 0.9;
                }
                if (j == 0) {
                    d0 *= 0.0;
                    d0_1 *= 0.0;
                    d1 *= 0.0;
                    d1_1 *= 0.0;
                    d2 *= 0.0;
                    d2_1 *= 0.0;
                    d3 *= 0.0;
                    d3_1 *= 0.0;
                    h1 *= 0.9;
                    h0 *= 0.9;
                }
                if (this.doAnimation && WGConfig.cloakAnimationMode == 2 && (offsettingAngle = (double)Math.max(this.field_78124_i.field_78795_f * 57.295776f, this.field_78123_h.field_78795_f * 57.295776f)) > 1.0) {
                    double stretch = 0.3 * (offsettingAngle / 90.0);
                    d2 *= (stretch += 1.0);
                    d3 *= stretch;
                }
                tessellator.func_78382_b();
                tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
                tessellator.func_78374_a(d0_1, h0, d2_1, f3, f5);
                tessellator.func_78374_a(d0, h1, d2, f3, f6);
                tessellator.func_78374_a(d1, h1, d3, f4, f6);
                tessellator.func_78374_a(d1_1, h0, d3_1, f4, f5);
                tessellator.func_78381_a();
                d0_1 = d0;
                d1_1 = d1;
                d2_1 = d2;
                d3_1 = d3;
            }
        }
        if (drawHood) {
            GL11.glTranslated((double)0.0, (double)1.4, (double)0.0);
            if (this.field_78116_c.field_78808_h * 57.295776f != 0.0f) {
                GL11.glRotatef((float)(this.field_78116_c.field_78808_h * 57.295776f), (float)0.0f, (float)1.0f, (float)0.0f);
            }
            if (this.field_78116_c.field_78796_g * 57.295776f != 0.0f) {
                GL11.glRotatef((float)(this.field_78116_c.field_78796_g * 57.295776f), (float)0.0f, (float)1.0f, (float)0.0f);
            }
            if (this.field_78114_d.field_78795_f * 57.295776f != 0.0f) {
                GL11.glRotatef((float)(this.field_78114_d.field_78795_f * 57.295776f * -1.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            }
            GL11.glTranslated((double)0.0, (double)0.0, (double)0.3);
            GL11.glScaled((double)1.0, (double)1.0, (double)1.6);
            for (i = 0; i < 15; ++i) {
                int it3_1;
                int it2_1;
                int it0_1;
                int it1_1;
                it0 = i;
                it1 = it0 + 1;
                if (it1 > 31) {
                    it1 -= 31;
                }
                if ((it2 = i + 24) > 31) {
                    it2 -= 31;
                }
                if ((it3 = it2 + 1) > 31) {
                    it3 -= 31;
                }
                if ((it1_1 = (it0_1 = i + 1) + 1) > 31) {
                    it1_1 -= 31;
                }
                if ((it2_1 = i + 24) > 31) {
                    it2_1 -= 31;
                }
                if ((it3_1 = it2_1 + 1) > 31) {
                    it3_1 -= 31;
                }
                for (int j = 0; j < 8; ++j) {
                    int jt0 = j;
                    int jt1 = jt0 + 1;
                    double h0 = this.circPos[jt0] * this.circPos[jt0] * 2.75;
                    double h1 = this.circPos[jt1] * this.circPos[jt1] * 2.75;
                    double[] dividerA = new double[]{0.0, 0.65, 0.675, 0.7, 0.725, 0.775, 0.825, 0.9};
                    double divider = dividerA[j];
                    double d0 = this.circPos[it0] * 0.9 * divider;
                    double d1 = this.circPos[it1] * 0.9 * divider;
                    double d2 = this.circPos[it2] * 0.9 * divider;
                    double d3 = this.circPos[it3] * 0.9 * divider;
                    double f3 = (double)i * 0.0625;
                    double f4 = (double)(i + 1) * 0.0625;
                    double f5 = 0.5;
                    double f6 = 1.0;
                    if (j == 2) {
                        h0 *= 0.975;
                    }
                    if (j == 1) {
                        h1 *= 0.975;
                        h0 *= 0.9;
                    }
                    if (j == 0) {
                        d0 *= 0.25;
                        d0_1 *= 0.25;
                        d1 *= 0.25;
                        d1_1 *= 0.25;
                        d2 *= 0.25;
                        d2_1 *= 0.25;
                        d3 *= 0.25;
                        d3_1 *= 0.25;
                        h1 *= 0.9;
                        h0 *= 0.9;
                    }
                    if (j == 2 || j == 3 || j == 4) {
                        d2 *= 1.25;
                        d3 *= 1.25;
                    }
                    GL11.glColor4d((double)1.0, (double)1.0, (double)1.0, (double)1.0);
                    tessellator.func_78382_b();
                    tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
                    tessellator.func_78374_a(d0, h1, d2, f3, f6);
                    tessellator.func_78374_a(d0_1, h0, d2_1, f3, f6);
                    tessellator.func_78374_a(d1_1, h0, d3_1, f4, f5);
                    tessellator.func_78374_a(d1, h1, d3, f4, f5);
                    tessellator.func_78381_a();
                    d0_1 = d0;
                    d1_1 = d1;
                    d2_1 = d2;
                    d3_1 = d3;
                }
            }
        }
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }
}

