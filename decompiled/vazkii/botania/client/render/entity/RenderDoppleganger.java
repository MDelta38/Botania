/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.entity.RenderBiped
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.ARBShaderObjects
 */
package vazkii.botania.client.render.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.ARBShaderObjects;
import vazkii.botania.api.internal.ShaderCallback;
import vazkii.botania.client.core.handler.BossBarHandler;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.common.entity.EntityDoppleganger;

public class RenderDoppleganger
extends RenderBiped {
    public static float DEFAULT_GRAIN_INTENSITY = 0.05f;
    public static float DEFAULT_DISFIGURATION = 0.025f;
    public static float grainIntensity = DEFAULT_GRAIN_INTENSITY;
    public static float disfiguration = DEFAULT_DISFIGURATION;
    public static ShaderCallback callback = new ShaderCallback(){

        @Override
        public void call(int shader) {
            int disfigurationUniform = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"disfiguration");
            ARBShaderObjects.glUniform1fARB((int)disfigurationUniform, (float)disfiguration);
            int grainIntensityUniform = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"grainIntensity");
            ARBShaderObjects.glUniform1fARB((int)grainIntensityUniform, (float)grainIntensity);
        }
    };
    public static ShaderCallback defaultCallback = new ShaderCallback(){

        @Override
        public void call(int shader) {
            int disfigurationUniform = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"disfiguration");
            ARBShaderObjects.glUniform1fARB((int)disfigurationUniform, (float)DEFAULT_DISFIGURATION);
            int grainIntensityUniform = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"grainIntensity");
            ARBShaderObjects.glUniform1fARB((int)grainIntensityUniform, (float)DEFAULT_GRAIN_INTENSITY);
        }
    };

    public RenderDoppleganger() {
        super(new ModelBiped(0.5f), 0.0f);
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        EntityDoppleganger dopple = (EntityDoppleganger)par1Entity;
        BossBarHandler.setCurrentBoss(dopple);
        int invulTime = dopple.getInvulTime();
        if (invulTime > 0) {
            grainIntensity = invulTime > 20 ? 1.0f : (float)invulTime * 0.05f;
            disfiguration = grainIntensity * 0.3f;
        } else {
            disfiguration = (0.025f + (float)dopple.field_70737_aN * 0.0425f) / 2.0f;
            grainIntensity = 0.05f + (float)dopple.field_70737_aN * 0.085f;
        }
        ShaderHelper.useShader(ShaderHelper.doppleganger, callback);
        super.func_76986_a(par1Entity, par2, par4, par6, par8, par9);
        ShaderHelper.releaseShader();
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return Minecraft.func_71410_x().field_71439_g.func_110306_p();
    }
}

