/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package witchinggadgets.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ModelKama
extends ModelBiped {
    int colour;
    static ResourceLocation texBelt = new ResourceLocation("witchinggadgets:textures/models/magicalBaubles.png");

    public ModelKama(int colour) {
        this.colour = colour;
        this.field_78116_c.field_78806_j = false;
        this.field_78114_d.field_78806_j = false;
        this.field_78115_e.field_78806_j = false;
        this.field_78112_f.field_78806_j = false;
        this.field_78113_g.field_78806_j = false;
        this.field_78123_h.field_78806_j = false;
        this.field_78124_i.field_78806_j = false;
    }

    public void func_78088_a(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        double mod;
        super.func_78088_a(entity, par2, par3, par4, par5, par6, par7);
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glTranslatef((float)0.0f, (float)1.45f, (float)0.0f);
        GL11.glScalef((float)1.0f, (float)-1.0f, (float)1.0f);
        GL11.glColor3f((float)((float)(this.colour >> 16 & 0xFF) / 255.0f), (float)((float)(this.colour >> 8 & 0xFF) / 255.0f), (float)((float)(this.colour & 0xFF) / 255.0f));
        float angleMax = entity.func_70051_ag() ? 60.0f : 70.0f;
        double ap = 0.8125;
        double hL = 0.0;
        double hR = 0.0;
        double dL = 0.25;
        double dR = 0.25;
        double h = 0.5625;
        double w = 0.038;
        if (this.field_78124_i.field_78795_f > 0.0f) {
            mod = Math.toDegrees(this.field_78124_i.field_78795_f) / (double)angleMax;
            dL += 0.4375 * mod;
            hL += 0.4375 * mod;
        }
        if (this.field_78123_h.field_78795_f > 0.0f) {
            mod = Math.toDegrees(this.field_78123_h.field_78795_f) / (double)angleMax;
            dR += 0.4375 * mod;
            hR += 0.4375 * mod;
        }
        tessellator.func_78382_b();
        tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
        tessellator.func_78374_a(0.4375, ap - h + 0.1, -0.125, 0.0, 1.0);
        tessellator.func_78374_a(0.25 + w, ap, -0.125, 0.0, 0.0);
        tessellator.func_78374_a(0.25 + w, ap, 0.125 + w, 0.25, 0.0);
        tessellator.func_78374_a(0.375, ap - h + hL, dL, 0.25, 1.0);
        tessellator.func_78381_a();
        tessellator.func_78371_b(9);
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        tessellator.func_78374_a(0.375, ap - h + hL, dL, 0.25, 1.0);
        tessellator.func_78374_a(-0.0, ap - h - 0.1 + hL, dL, 0.515625, 1.0);
        tessellator.func_78374_a(-0.03125, ap, 0.125 + w, 0.515625, 0.0);
        tessellator.func_78374_a(0.25 + w, ap, 0.125 + w, 0.25, 0.0);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        tessellator.func_78374_a(-0.0376, ap - h + hR, dR, 0.515625, 1.0);
        tessellator.func_78374_a(-0.0376, ap, 0.125 + w, 0.515625, 0.0);
        tessellator.func_78374_a(-0.0376, ap, 0.125 + w, 0.484375, 0.0);
        tessellator.func_78374_a(-0.0376, ap - h + hL, dL, 0.484375, 1.0);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        tessellator.func_78374_a(0.0376, ap - h + hL, dL, 0.515625, 1.0);
        tessellator.func_78374_a(0.0376, ap, 0.125 + w, 0.515625, 0.0);
        tessellator.func_78374_a(0.0376, ap, 0.125 + w, 0.484375, 0.0);
        tessellator.func_78374_a(0.0376, ap - h + hL, dL, 0.484375, 1.0);
        tessellator.func_78381_a();
        tessellator.func_78371_b(9);
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        tessellator.func_78374_a(-0.375, ap - h + hR, dR, 0.75, 1.0);
        tessellator.func_78374_a(-0.25 - w, ap, 0.125 + w, 0.75, 0.0);
        tessellator.func_78374_a(0.03125, ap, 0.125 + w, 0.484375, 0.0);
        tessellator.func_78374_a(0.0, ap - h - 0.1 + hR, dR, 0.484375, 1.0);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
        tessellator.func_78374_a(-0.375, ap - h + hR, dR, 0.75, 1.0);
        tessellator.func_78374_a(-0.25 - w, ap, 0.125 + w, 0.75, 0.0);
        tessellator.func_78374_a(-0.25 - w, ap, -0.125, 1.0, 0.0);
        tessellator.func_78374_a(-0.4375, ap - h + 0.1, -0.125, 1.0, 1.0);
        tessellator.func_78381_a();
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        Minecraft.func_71410_x().func_110434_K().func_110577_a(texBelt);
        tessellator.func_78382_b();
        tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
        tessellator.func_78374_a(-0.25 - w, ap - 0.125, 0.125 + w, 0.0, 1.0);
        tessellator.func_78374_a(-0.25 - w, ap + 0.125, 0.125 + w, 0.0, 0.875);
        tessellator.func_78374_a(-0.25 - w, ap + 0.125, -0.125 - w, 0.0625, 0.875);
        tessellator.func_78374_a(-0.25 - w, ap - 0.125, -0.125 - w, 0.0625, 1.0);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        tessellator.func_78374_a(-0.25 - w, ap - 0.125, -0.125 - w, 0.0625, 1.0);
        tessellator.func_78374_a(-0.25 - w, ap + 0.125, -0.125 - w, 0.0625, 0.875);
        tessellator.func_78374_a(0.25 + w, ap + 0.125, -0.125 - w, 0.1875, 0.875);
        tessellator.func_78374_a(0.25 + w, ap - 0.125, -0.125 - w, 0.1875, 1.0);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
        tessellator.func_78374_a(0.25 + w, ap - 0.125, -0.125 - w, 0.1875, 1.0);
        tessellator.func_78374_a(0.25 + w, ap + 0.125, -0.125 - w, 0.1875, 0.875);
        tessellator.func_78374_a(0.25 + w, ap + 0.125, 0.125 + w, 0.25, 0.875);
        tessellator.func_78374_a(0.25 + w, ap - 0.125, 0.125 + w, 0.25, 1.0);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        tessellator.func_78374_a(0.25 + w, ap - 0.125, 0.125 + w, 0.25, 1.0);
        tessellator.func_78374_a(0.25 + w, ap + 0.125, 0.125 + w, 0.25, 0.875);
        tessellator.func_78374_a(-0.25 - w, ap + 0.125, 0.125 + w, 0.375, 0.875);
        tessellator.func_78374_a(-0.25 - w, ap - 0.125, 0.125 + w, 0.375, 1.0);
        tessellator.func_78381_a();
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }
}

