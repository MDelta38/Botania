/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.models.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelTaintSpore
extends ModelBase {
    ModelRenderer cube;

    public ModelTaintSpore() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.cube = new ModelRenderer((ModelBase)this, 0, 0);
        this.cube.func_78789_a(-6.0f, 2.0f, -6.0f, 12, 12, 12);
        this.cube.func_78789_a(-8.0f, 0.0f, -8.0f, 16, 16, 16);
        this.cube.func_78793_a(0.0f, 24.0f, 0.0f);
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        this.cube.func_78785_a(par7);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        float intensity = 0.02f;
        if (((EntityLivingBase)entity).field_70737_aN > 0) {
            intensity = 0.04f;
        }
        this.cube.field_78795_f = intensity * MathHelper.func_76126_a((float)(par3 * 0.05f));
        this.cube.field_78808_h = intensity * MathHelper.func_76126_a((float)(par3 * 0.1f));
    }
}

