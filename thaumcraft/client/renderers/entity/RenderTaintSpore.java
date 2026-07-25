/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.models.entities.ModelTaintSpore;
import thaumcraft.common.entities.monster.EntityTaintSpore;

@SideOnly(value=Side.CLIENT)
public class RenderTaintSpore
extends RenderLiving {
    private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/taint_spore.png");

    public RenderTaintSpore() {
        super((ModelBase)new ModelTaintSpore(), 0.25f);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return rl;
    }

    protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2) {
        this.scaleSpore((EntityTaintSpore)par1EntityLiving, par2);
    }

    protected void scaleSpore(EntityTaintSpore spore, float par2) {
        float f1 = spore.displaySize;
        if (spore.displaySize < (float)spore.getSporeSize()) {
            f1 += 0.02f * par2;
        }
        float f3 = -0.12f;
        float pulse = 0.025f * MathHelper.func_76126_a((float)((float)spore.field_70173_aa * 0.075f));
        GL11.glScalef((float)(f3 * f1 - pulse), (float)(f3 * f1 + pulse), (float)(f3 * f1 - pulse));
    }

    protected float func_77037_a(EntityLivingBase par1EntityLiving) {
        return 0.0f;
    }
}

