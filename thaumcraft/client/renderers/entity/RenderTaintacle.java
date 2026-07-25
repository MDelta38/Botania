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
 *  net.minecraft.entity.boss.BossStatus
 *  net.minecraft.entity.boss.IBossDisplayData
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
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.models.entities.ModelTaintacle;
import thaumcraft.common.entities.monster.boss.EntityTaintacleGiant;

@SideOnly(value=Side.CLIENT)
public class RenderTaintacle
extends RenderLiving {
    private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/models/taintacle.png");

    public RenderTaintacle(float shadow, int length) {
        super((ModelBase)new ModelTaintacle(length), shadow);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return rl;
    }

    protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2) {
        if (par1EntityLiving instanceof EntityTaintacleGiant) {
            BossStatus.func_82824_a((IBossDisplayData)((EntityTaintacleGiant)par1EntityLiving), (boolean)false);
            GL11.glScalef((float)1.33f, (float)1.33f, (float)1.33f);
        }
        super.func_77041_b(par1EntityLiving, par2);
    }
}

