/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.models.entities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;

public class ModelRendererTaintacle
extends ModelRenderer {
    private int textureOffsetX;
    private int textureOffsetY;
    private boolean compiled;
    private int displayList;
    private ModelBase baseModel;

    public ModelRendererTaintacle(ModelBase par1ModelBase) {
        super(par1ModelBase);
    }

    public ModelRendererTaintacle(ModelBase par1ModelBase, int par2, int par3) {
        this(par1ModelBase);
        this.func_78784_a(par2, par3);
    }

    @SideOnly(value=Side.CLIENT)
    public void render(float par1, float scale) {
        if (!this.field_78807_k && this.field_78806_j) {
            if (!this.compiled) {
                this.compileDisplayList(par1);
            }
            GL11.glTranslatef((float)this.field_82906_o, (float)this.field_82908_p, (float)this.field_82907_q);
            if (this.field_78795_f == 0.0f && this.field_78796_g == 0.0f && this.field_78808_h == 0.0f) {
                if (this.field_78800_c == 0.0f && this.field_78797_d == 0.0f && this.field_78798_e == 0.0f) {
                    if (this.field_78805_m == null) {
                        int j = 0xF000F0;
                        int k = j % 65536;
                        int l = j / 65536;
                        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)k / 1.0f), (float)((float)l / 1.0f));
                    }
                    GL11.glCallList((int)this.displayList);
                    if (this.field_78805_m != null) {
                        for (int i = 0; i < this.field_78805_m.size(); ++i) {
                            GL11.glPushMatrix();
                            GL11.glScalef((float)scale, (float)scale, (float)scale);
                            ((ModelRendererTaintacle)((Object)this.field_78805_m.get(i))).render(par1, scale);
                            GL11.glPopMatrix();
                        }
                    }
                } else {
                    GL11.glTranslatef((float)(this.field_78800_c * par1), (float)(this.field_78797_d * par1), (float)(this.field_78798_e * par1));
                    if (this.field_78805_m == null) {
                        int j = 0xF000F0;
                        int k = j % 65536;
                        int l = j / 65536;
                        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)k / 1.0f), (float)((float)l / 1.0f));
                    }
                    GL11.glCallList((int)this.displayList);
                    if (this.field_78805_m != null) {
                        for (int i = 0; i < this.field_78805_m.size(); ++i) {
                            GL11.glPushMatrix();
                            GL11.glScalef((float)scale, (float)scale, (float)scale);
                            ((ModelRendererTaintacle)((Object)this.field_78805_m.get(i))).render(par1, scale);
                            GL11.glPopMatrix();
                        }
                    }
                    GL11.glTranslatef((float)(-this.field_78800_c * par1), (float)(-this.field_78797_d * par1), (float)(-this.field_78798_e * par1));
                }
            } else {
                GL11.glPushMatrix();
                GL11.glTranslatef((float)(this.field_78800_c * par1), (float)(this.field_78797_d * par1), (float)(this.field_78798_e * par1));
                if (this.field_78808_h != 0.0f) {
                    GL11.glRotatef((float)(this.field_78808_h * 57.295776f), (float)0.0f, (float)0.0f, (float)1.0f);
                }
                if (this.field_78796_g != 0.0f) {
                    GL11.glRotatef((float)(this.field_78796_g * 57.295776f), (float)0.0f, (float)1.0f, (float)0.0f);
                }
                if (this.field_78795_f != 0.0f) {
                    GL11.glRotatef((float)(this.field_78795_f * 57.295776f), (float)1.0f, (float)0.0f, (float)0.0f);
                }
                if (this.field_78805_m == null) {
                    int j = 0xF000F0;
                    int k = j % 65536;
                    int l = j / 65536;
                    OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)k / 1.0f), (float)((float)l / 1.0f));
                }
                GL11.glCallList((int)this.displayList);
                if (this.field_78805_m != null) {
                    for (int i = 0; i < this.field_78805_m.size(); ++i) {
                        GL11.glPushMatrix();
                        GL11.glScalef((float)scale, (float)scale, (float)scale);
                        ((ModelRendererTaintacle)((Object)this.field_78805_m.get(i))).render(par1, scale);
                        GL11.glPopMatrix();
                    }
                }
                GL11.glPopMatrix();
            }
            GL11.glTranslatef((float)(-this.field_82906_o), (float)(-this.field_82908_p), (float)(-this.field_82907_q));
        }
    }

    @SideOnly(value=Side.CLIENT)
    private void compileDisplayList(float par1) {
        this.displayList = GLAllocation.func_74526_a((int)1);
        GL11.glNewList((int)this.displayList, (int)4864);
        Tessellator tessellator = Tessellator.field_78398_a;
        for (int i = 0; i < this.field_78804_l.size(); ++i) {
            ((ModelBox)this.field_78804_l.get(i)).func_78245_a(tessellator, par1);
        }
        GL11.glEndList();
        this.compiled = true;
    }
}

