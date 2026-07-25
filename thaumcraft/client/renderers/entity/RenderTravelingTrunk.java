/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.models.entities.ModelTrunk;
import thaumcraft.common.entities.golems.EntityTravelingTrunk;

public class RenderTravelingTrunk
extends RenderLiving {
    private ModelTrunk trunkModel;
    private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/trunk.png");
    private static final ResourceLocation rl_a = new ResourceLocation("thaumcraft", "textures/models/trunkangry.png");

    public RenderTravelingTrunk(ModelBase modelbase, float f) {
        super(modelbase, f);
        this.trunkModel = (ModelTrunk)modelbase;
    }

    protected void adjustTrunk(EntityTravelingTrunk entity, float f) {
        int i = 2;
        float f1 = (entity.field_767_b + (entity.field_768_a - entity.field_767_b) * f) / ((float)i * 0.5f + 1.0f);
        float f2 = 1.0f / (f1 + 1.0f);
        float f3 = i;
        f1 = (float)((double)f1 / 1.5);
        f2 = (float)((double)f2 / 1.4);
        f3 = entity.getUpgrade() == 1 ? (float)((double)f3 / 1.33) : (float)((double)f3 / 1.5);
        GL11.glScalef((float)(f2 * f3), (float)(0.5f / f2 * f3), (float)(f2 * f3));
        GL11.glTranslatef((float)-0.5f, (float)0.5f, (float)-0.5f);
        f1 = 1.0f - entity.lidrot;
        f1 = 1.0f - f1 * f1 * f1;
        this.trunkModel.chestLid.field_78795_f = -(f1 * 3.141593f / 2.0f);
    }

    protected void func_77041_b(EntityLivingBase par1EntityLivingBase, float f) {
        this.adjustTrunk((EntityTravelingTrunk)par1EntityLivingBase, f);
    }

    public void func_76986_a(EntityLiving entityliving, double d, double d1, double d2, float f, float f1) {
        super.func_76986_a(entityliving, d, d1, d2, f, f1);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)0.0f);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        if (((EntityTravelingTrunk)entity).getAnger() > 0) {
            return rl_a;
        }
        return rl;
    }
}

