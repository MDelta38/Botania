/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import thaumcraft.common.entities.EntityFallingTaint;

@SideOnly(value=Side.CLIENT)
public class RenderFallingTaint
extends Render {
    private RenderBlocks renderBlocks = new RenderBlocks();

    public RenderFallingTaint() {
        this.field_76989_e = 0.5f;
    }

    public void doRenderFalling(EntityFallingTaint entity, double par2, double par4, double par6, float par8, float par9) {
        World world = entity.getWorld();
        if (world.func_147439_a(MathHelper.func_76128_c((double)entity.field_70165_t), MathHelper.func_76128_c((double)entity.field_70163_u), MathHelper.func_76128_c((double)entity.field_70161_v)) != entity.block) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)par2), (float)((float)par4), (float)((float)par6));
            Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
            GL11.glDisable((int)2896);
            if (entity.block != null) {
                this.renderBlocks.func_147775_a(entity.block);
                this.renderBlocks.func_147749_a(entity.block, world, MathHelper.func_76128_c((double)entity.field_70165_t), MathHelper.func_76128_c((double)entity.field_70163_u), MathHelper.func_76128_c((double)entity.field_70161_v), entity.metadata);
            }
            GL11.glEnable((int)2896);
            GL11.glPopMatrix();
        }
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.doRenderFalling((EntityFallingTaint)par1Entity, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return AbstractClientPlayer.field_110314_b;
    }
}

