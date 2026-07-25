/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import thaumcraft.common.entities.monster.EntityTaintSporeSwarmer;

public class ModelTaintSporeSwarmer
extends ModelBase {
    ModelRenderer cube;
    ModelRenderer cube2;

    public ModelTaintSporeSwarmer() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.cube = new ModelRenderer((ModelBase)this, 0, 0);
        this.cube.func_78789_a(-8.0f, 0.0f, -8.0f, 16, 16, 16);
        this.cube.func_78793_a(0.0f, 0.0f, 0.0f);
        this.cube2 = new ModelRenderer((ModelBase)this, 0, 32);
        this.cube2.func_78789_a(-8.0f, -8.0f, -8.0f, 16, 16, 16);
        this.cube2.func_78793_a(0.0f, 16.0f, 0.0f);
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
        EntityTaintSporeSwarmer spore = (EntityTaintSporeSwarmer)par1Entity;
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glPushMatrix();
        float f1 = spore.displaySize;
        float f3 = -0.07f;
        float pulse = 0.025f * MathHelper.func_76126_a((float)((float)spore.field_70173_aa * 0.075f));
        GL11.glTranslatef((float)0.0f, (float)1.6f, (float)0.0f);
        GL11.glScalef((float)(f3 * f1 - pulse), (float)(f3 * f1 + pulse), (float)(f3 * f1 - pulse));
        GL11.glTranslatef((float)0.0f, (float)(-(f3 * f1 + pulse) / 2.0f), (float)0.0f);
        int j = 0xF000F0;
        int k = j % 65536;
        int l = j / 65536;
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)k / 1.0f), (float)((float)l / 1.0f));
        this.cube.func_78785_a(par7);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        j = spore.func_70070_b(par7);
        k = j % 65536;
        l = j / 65536;
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)k / 1.0f), (float)((float)l / 1.0f));
        this.cube2.func_78785_a(par7);
        GL11.glPopMatrix();
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

