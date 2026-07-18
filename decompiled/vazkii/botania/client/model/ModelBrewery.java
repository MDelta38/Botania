/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.render.tile.RenderTileBrewery;

public class ModelBrewery
extends ModelBase {
    ModelRenderer Pole;
    ModelRenderer Top;
    ModelRenderer Bottom;
    ModelRenderer Plate;

    public ModelBrewery() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.Pole = new ModelRenderer((ModelBase)this, 0, 6);
        this.Pole.func_78789_a(0.0f, 0.0f, 0.0f, 2, 10, 2);
        this.Pole.func_78793_a(-1.0f, 10.0f, -1.0f);
        this.Pole.func_78787_b(64, 32);
        this.Top = new ModelRenderer((ModelBase)this, 18, 0);
        this.Top.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 4);
        this.Top.func_78793_a(-2.0f, 9.0f, -2.0f);
        this.Top.func_78787_b(64, 32);
        this.Bottom = new ModelRenderer((ModelBase)this, 18, 7);
        this.Bottom.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 4);
        this.Bottom.func_78793_a(-2.0f, 20.0f, -2.0f);
        this.Bottom.func_78787_b(64, 32);
        this.Plate = new ModelRenderer((ModelBase)this, 0, 0);
        this.Plate.func_78789_a(5.0f, 0.0f, -2.0f, 4, 1, 4);
        this.Plate.func_78793_a(0.0f, 17.0f, 0.0f);
        this.Plate.func_78787_b(64, 32);
    }

    public void render(RenderTileBrewery render, double time) {
        float f = 0.0625f;
        float offset = (float)Math.sin(time / 40.0) * 0.1f + 0.05f;
        int plates = render.brewery.func_70302_i_() - 1;
        float deg = (float)time / 16.0f;
        float polerot = -deg * 25.0f;
        GL11.glTranslatef((float)0.0f, (float)offset, (float)0.0f);
        GL11.glRotatef((float)polerot, (float)0.0f, (float)1.0f, (float)0.0f);
        if (render.brewery.func_70301_a(0) != null) {
            GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.125f, (float)-0.5f, (float)0.0078125f);
            render.renderItemStack(render.brewery.func_70301_a(0));
            GL11.glTranslatef((float)0.125f, (float)0.5f, (float)-0.0078125f);
            GL11.glRotatef((float)-180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        }
        this.Pole.func_78785_a(f);
        this.Top.func_78785_a(f);
        this.Bottom.func_78785_a(f);
        GL11.glRotatef((float)(-polerot), (float)0.0f, (float)1.0f, (float)0.0f);
        float degper = (float)Math.PI * 2 / (float)plates;
        for (int i = 0; i < plates; ++i) {
            this.Plate.field_78796_g = deg;
            float offset1 = (float)Math.sin(time / 20.0 + (double)((float)i * 40.0f)) * 0.2f - 0.2f;
            if (time == -1.0) {
                offset1 = 0.0f;
            }
            GL11.glTranslatef((float)0.0f, (float)offset1, (float)0.0f);
            if (render.brewery.func_70301_a(i + 1) != null) {
                float rot = this.Plate.field_78796_g * 180.0f / (float)Math.PI;
                float transX = 0.3125f;
                float transY = 1.06f;
                float transZ = 0.1245f;
                GL11.glRotatef((float)rot, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)transX, (float)transY, (float)transZ);
                GL11.glRotatef((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                render.renderItemStack(render.brewery.func_70301_a(i + 1));
                GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GL11.glTranslatef((float)(-transX), (float)(-transY), (float)(-transZ));
                GL11.glRotatef((float)(-rot), (float)0.0f, (float)1.0f, (float)0.0f);
            }
            this.Plate.func_78785_a(f);
            GL11.glTranslatef((float)0.0f, (float)(-offset1), (float)0.0f);
            deg += degper;
        }
        GL11.glTranslatef((float)0.0f, (float)(-offset), (float)0.0f);
    }
}

