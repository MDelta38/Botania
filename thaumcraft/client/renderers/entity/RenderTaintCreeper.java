/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelCreeper
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.common.entities.monster.EntityTaintCreeper;

public class RenderTaintCreeper
extends RenderLiving {
    private ModelBase field_27008_a = new ModelCreeper(2.0f);
    private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/creeper.png");
    private static final ResourceLocation field_110831_a = new ResourceLocation("thaumcraft", "textures/entity/creeper/creeper_armor.png");

    public RenderTaintCreeper() {
        super((ModelBase)new ModelCreeper(), 0.5f);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return rl;
    }

    protected void updateCreeperScale(EntityTaintCreeper par1EntityCreeper, float par2) {
        float var4 = par1EntityCreeper.getCreeperFlashIntensity(par2);
        float var5 = 1.0f + MathHelper.func_76126_a((float)(var4 * 100.0f)) * var4 * 0.01f;
        if (var4 < 0.0f) {
            var4 = 0.0f;
        }
        if (var4 > 1.0f) {
            var4 = 1.0f;
        }
        var4 *= var4;
        var4 *= var4;
        float var6 = (1.0f + var4 * 0.4f) * var5;
        float var7 = (1.0f + var4 * 0.1f) / var5;
        GL11.glScalef((float)var6, (float)var7, (float)var6);
    }

    protected int updateCreeperColorMultiplier(EntityTaintCreeper par1EntityCreeper, float par2, float par3) {
        float var5 = par1EntityCreeper.getCreeperFlashIntensity(par3);
        if ((int)(var5 * 10.0f) % 2 == 0) {
            return 0;
        }
        int var6 = (int)(var5 * 0.2f * 255.0f);
        if (var6 < 0) {
            var6 = 0;
        }
        if (var6 > 255) {
            var6 = 255;
        }
        int var7 = 255;
        int var8 = 255;
        int var9 = 255;
        return var6 << 24 | var7 << 16 | var8 << 8 | var9;
    }

    protected int func_27007_b(EntityTaintCreeper par1EntityCreeper, int par2, float par3) {
        return -1;
    }

    protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2) {
        this.updateCreeperScale((EntityTaintCreeper)par1EntityLiving, par2);
    }

    protected int func_77030_a(EntityLivingBase par1EntityLiving, float par2, float par3) {
        return this.updateCreeperColorMultiplier((EntityTaintCreeper)par1EntityLiving, par2, par3);
    }

    protected int func_77035_b(EntityLivingBase par1EntityLiving, int par2, float par3) {
        return this.func_27007_b((EntityTaintCreeper)par1EntityLiving, par2, par3);
    }
}

