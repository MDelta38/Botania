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
 *  net.minecraft.util.ResourceLocation
 */
package thaumcraft.client.renderers.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import thaumcraft.client.renderers.models.entities.ModelTaintSporeSwarmer;

@SideOnly(value=Side.CLIENT)
public class RenderTaintSporeSwarmer
extends RenderLiving {
    private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/taint_spore.png");

    public RenderTaintSporeSwarmer() {
        super((ModelBase)new ModelTaintSporeSwarmer(), 0.25f);
    }

    protected float func_77037_a(EntityLivingBase par1EntityLiving) {
        return 0.0f;
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return rl;
    }
}

