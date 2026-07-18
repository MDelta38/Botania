/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.client.particle.EffectRenderer
 *  net.minecraft.util.ResourceLocation
 */
package vazkii.botania.common.core.helper;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.util.ResourceLocation;
import vazkii.botania.common.lib.LibObfuscation;

public class ObfuscationHelper {
    public static ResourceLocation getParticleTexture() {
        return (ResourceLocation)ReflectionHelper.getPrivateValue(EffectRenderer.class, null, (String[])LibObfuscation.PARTICLE_TEXTURES);
    }
}

