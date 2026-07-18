/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.ARBShaderObjects
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.render.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.internal.ShaderCallback;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.client.model.ModelPixie;
import vazkii.botania.common.entity.EntityPixie;

public class RenderPixie
extends RenderLiving {
    ShaderCallback callback = new ShaderCallback(){

        @Override
        public void call(int shader) {
            int disfigurationUniform = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"disfiguration");
            ARBShaderObjects.glUniform1fARB((int)disfigurationUniform, (float)0.025f);
            int grainIntensityUniform = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"grainIntensity");
            ARBShaderObjects.glUniform1fARB((int)grainIntensityUniform, (float)0.05f);
        }
    };

    public RenderPixie() {
        super((ModelBase)new ModelPixie(), 0.25f);
        this.func_77042_a(new ModelPixie());
        this.field_76989_e = 0.0f;
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return new ResourceLocation("botania:textures/model/pixie.png");
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        EntityPixie pixie = (EntityPixie)par1Entity;
        if (pixie.getType() == 1) {
            ShaderHelper.useShader(ShaderHelper.doppleganger, this.callback);
        }
        super.func_76986_a(par1Entity, par2, par4, par6, par8, par9);
        if (pixie.getType() == 1) {
            ShaderHelper.releaseShader();
        }
    }

    protected int setPixieBrightness(EntityPixie par1EntityPixie, int par2, float par3) {
        if (par2 != 0) {
            return -1;
        }
        this.func_110776_a(this.func_110775_a((Entity)par1EntityPixie));
        float f1 = 1.0f;
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3008);
        GL11.glBlendFunc((int)1, (int)1);
        if (par1EntityPixie.func_82150_aj()) {
            GL11.glDepthMask((boolean)false);
        } else {
            GL11.glDepthMask((boolean)true);
        }
        int c0 = 61680;
        int j = c0 % 65536;
        int k = c0 / 65536;
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)j / 1.0f), (float)((float)k / 1.0f));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)f1);
        return 1;
    }

    protected int func_77032_a(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
        return this.setPixieBrightness((EntityPixie)par1EntityLivingBase, par2, par3);
    }
}

