/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.models.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.models.entities.ModelRendererTaintacle;
import thaumcraft.common.entities.monster.EntityTaintacle;

public class ModelTaintacle
extends ModelBase {
    public ModelRenderer tentacle = new ModelRendererTaintacle(this);
    public ModelRenderer[] tents;
    public ModelRenderer orb = new ModelRendererTaintacle(this);
    private int length = 10;

    public ModelTaintacle(int length) {
        boolean var3 = false;
        this.length = length;
        this.field_78089_u = 64;
        this.field_78090_t = 64;
        this.tentacle = new ModelRendererTaintacle(this, 0, 0);
        this.tentacle.func_78789_a(-4.0f, -4.0f, -4.0f, 8, 8, 8);
        this.tentacle.field_78800_c = 0.0f;
        this.tentacle.field_78798_e = 0.0f;
        this.tentacle.field_78797_d = 12.0f;
        this.tents = new ModelRendererTaintacle[length];
        for (int k = 0; k < length - 1; ++k) {
            this.tents[k] = new ModelRendererTaintacle(this, 0, 16);
            this.tents[k].func_78789_a(-4.0f, -4.0f, -4.0f, 8, 8, 8);
            this.tents[k].field_78797_d = -8.0f;
            if (k == 0) {
                this.tentacle.func_78792_a(this.tents[k]);
                continue;
            }
            this.tents[k - 1].func_78792_a(this.tents[k]);
        }
        this.orb = new ModelRendererTaintacle(this, 0, 56);
        this.orb.func_78789_a(-2.0f, -2.0f, -2.0f, 4, 4, 4);
        this.orb.field_78797_d = -8.0f;
        this.tents[length - 2].func_78792_a(this.orb);
        this.tents[length - 1] = new ModelRendererTaintacle(this, 0, 32);
        this.tents[length - 1].func_78789_a(-6.0f, -6.0f, -6.0f, 12, 12, 12);
        this.tents[length - 1].field_78797_d = -8.0f;
        this.tents[length - 2].func_78792_a(this.tents[length - 1]);
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        boolean agi = false;
        float flail = 0.0f;
        int ht = 0;
        int at = 0;
        if (entity instanceof EntityTaintacle) {
            EntityTaintacle tentacle = (EntityTaintacle)entity;
            agi = tentacle.getAgitationState();
            flail = tentacle.flailIntensity;
            ht = tentacle.field_70737_aN;
            at = tentacle.field_70724_aR;
        }
        float mod = par6 * 0.2f;
        float fs = agi ? 3.0f : 1.0f + (agi ? mod : -mod);
        float fi = flail + (ht > 0 || at > 0 ? mod : -mod);
        this.tentacle.field_78795_f = 0.0f;
        for (int k = 0; k < this.length - 1; ++k) {
            this.tents[k].field_78795_f = 0.15f * fi * MathHelper.func_76126_a((float)(par3 * 0.1f * fs - (float)k / 2.0f));
            this.tents[k].field_78808_h = 0.1f / fi * MathHelper.func_76126_a((float)(par3 * 0.15f - (float)k / 2.0f));
        }
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        float height = 0.0f;
        float hc = par1Entity.field_70131_O * 10.0f;
        if ((float)par1Entity.field_70173_aa < hc) {
            height = (hc - (float)par1Entity.field_70173_aa) / hc * par1Entity.field_70131_O;
        }
        GL11.glTranslatef((float)0.0f, (float)((par1Entity.field_70131_O == 3.0f ? 0.6f : 1.2f) + height), (float)0.0f);
        GL11.glScalef((float)(par1Entity.field_70131_O / 3.0f), (float)(par1Entity.field_70131_O / 3.0f), (float)(par1Entity.field_70131_O / 3.0f));
        ((ModelRendererTaintacle)this.tentacle).render(par7, 0.88f);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }
}

