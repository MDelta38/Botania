/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderLiving
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.util.ResourceLocation
 */
package thaumcraft.client.renderers.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import thaumcraft.common.entities.monster.EntityTaintPig;

public class RenderTaintPig
extends RenderLiving {
    private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/pig.png");

    public RenderTaintPig(ModelBase par1ModelBase, float par3) {
        super(par1ModelBase, par3);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return rl;
    }

    public void func_40286_a(EntityTaintPig par1EntityPig, double par2, double par4, double par6, float par8, float par9) {
        super.func_76986_a((EntityLiving)par1EntityPig, par2, par4, par6, par8, par9);
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.func_40286_a((EntityTaintPig)par1Entity, par2, par4, par6, par8, par9);
    }
}

