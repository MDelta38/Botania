/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderWither
 *  net.minecraft.entity.boss.BossStatus
 *  net.minecraft.entity.boss.EntityWither
 *  net.minecraft.util.ResourceLocation
 */
package vazkii.botania.client.render.entity;

import net.minecraft.client.renderer.entity.RenderWither;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.util.ResourceLocation;

public class RenderPinkWither
extends RenderWither {
    private static final ResourceLocation resource = new ResourceLocation("botania:textures/model/pinkWither.png");
    int idk = -1;

    public void func_76986_a(EntityWither p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        super.func_76986_a(p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
        if (BossStatus.field_82827_c.equals(p_76986_1_.func_145748_c_().func_150254_d())) {
            BossStatus.field_82826_b = -1;
            BossStatus.field_82825_d = false;
        }
    }

    protected ResourceLocation func_110775_a(EntityWither p_110775_1_) {
        return resource;
    }
}

