/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.render.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.common.entity.EntityManaStorm;

public class RenderManaStorm
extends Render {
    public void func_76986_a(Entity e, double x, double y, double z, float something, float pticks) {
        GL11.glPushMatrix();
        GL11.glTranslated((double)x, (double)y, (double)z);
        EntityManaStorm storm = (EntityManaStorm)e;
        float maxScale = 1.95f;
        float scale = 0.05f + ((float)storm.burstsFired / 250.0f - (storm.deathTime == 0 ? 0.0f : (float)storm.deathTime + pticks) / 200.0f) * maxScale;
        RenderHelper.renderStar(65280, scale, scale, scale, e.func_110124_au().getMostSignificantBits());
        GL11.glDisable((int)3042);
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_) {
        return null;
    }
}

