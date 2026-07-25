/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.IIcon
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import org.lwjgl.opengl.GL11;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.golems.EntityTravelingTrunk;

public class ModelTrunk
extends ModelBase {
    public ModelRenderer chestLid = new ModelRenderer((ModelBase)this, 0, 0).func_78787_b(64, 64);
    public ModelRenderer chestBelow;
    public ModelRenderer chestKnob;

    public ModelTrunk() {
        this.chestLid.func_78790_a(0.0f, -5.0f, -14.0f, 14, 5, 14, 0.0f);
        this.chestLid.field_78800_c = 1.0f;
        this.chestLid.field_78797_d = 7.0f;
        this.chestLid.field_78798_e = 15.0f;
        this.chestKnob = new ModelRenderer((ModelBase)this, 0, 0).func_78787_b(64, 64);
        this.chestKnob.func_78790_a(-1.0f, -2.0f, -15.0f, 2, 4, 1, 0.0f);
        this.chestKnob.field_78800_c = 8.0f;
        this.chestKnob.field_78797_d = 7.0f;
        this.chestKnob.field_78798_e = 15.0f;
        this.chestBelow = new ModelRenderer((ModelBase)this, 0, 19).func_78787_b(64, 64);
        this.chestBelow.func_78790_a(0.0f, 0.0f, 0.0f, 14, 10, 14, 0.0f);
        this.chestBelow.field_78800_c = 1.0f;
        this.chestBelow.field_78797_d = 6.0f;
        this.chestBelow.field_78798_e = 1.0f;
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.chestKnob.field_78795_f = this.chestLid.field_78795_f;
        this.chestLid.func_78785_a(0.0625f);
        this.chestBelow.func_78785_a(0.0625f);
        this.chestKnob.func_78785_a(0.0625f);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)this.chestKnob.field_82906_o, (float)this.chestKnob.field_82908_p, (float)this.chestKnob.field_82907_q);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)(this.chestKnob.field_78800_c * 0.0625f), (float)(this.chestKnob.field_78797_d * 0.0625f), (float)(this.chestKnob.field_78798_e * 0.0625f));
        if (this.chestKnob.field_78808_h != 0.0f) {
            GL11.glRotatef((float)(this.chestKnob.field_78808_h * 57.295776f), (float)0.0f, (float)0.0f, (float)1.0f);
        }
        if (this.chestKnob.field_78796_g != 0.0f) {
            GL11.glRotatef((float)(this.chestKnob.field_78796_g * 57.295776f), (float)0.0f, (float)1.0f, (float)0.0f);
        }
        if (this.chestKnob.field_78795_f != 0.0f) {
            GL11.glRotatef((float)(this.chestKnob.field_78795_f * 57.295776f), (float)1.0f, (float)0.0f, (float)0.0f);
        }
        GL11.glTranslatef((float)-0.075f, (float)-0.115f, (float)-0.94301f);
        GL11.glScaled((double)0.15, (double)0.15, (double)0.15);
        Tessellator tessellator = Tessellator.field_78398_a;
        IIcon icon = ConfigItems.itemGolemUpgrade.func_77617_a(((EntityTravelingTrunk)entity).getUpgrade());
        float ff1 = icon.func_94212_f();
        float ff2 = icon.func_94206_g();
        float ff3 = icon.func_94209_e();
        float ff4 = icon.func_94210_h();
        RenderManager.field_78727_a.field_78724_e.func_110577_a(TextureMap.field_110576_c);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        tessellator.func_78374_a(0.0, 0.0, 0.0, (double)ff1, (double)ff4);
        tessellator.func_78374_a(1.0, 0.0, 0.0, (double)ff3, (double)ff4);
        tessellator.func_78374_a(1.0, 1.0, 0.0, (double)ff3, (double)ff2);
        tessellator.func_78374_a(0.0, 1.0, 0.0, (double)ff1, (double)ff2);
        tessellator.func_78381_a();
        GL11.glPopMatrix();
        GL11.glDisable((int)3042);
        GL11.glTranslatef((float)(-this.chestKnob.field_82906_o), (float)(-this.chestKnob.field_82908_p), (float)(-this.chestKnob.field_82907_q));
        GL11.glPopMatrix();
    }
}

