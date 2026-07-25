/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.model.AdvancedModelLoader
 *  net.minecraftforge.client.model.IModelCustom
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import thaumcraft.common.entities.projectile.EntityFrostShard;

@SideOnly(value=Side.CLIENT)
public class RenderFrostShard
extends Render {
    private IModelCustom model = AdvancedModelLoader.loadModel((ResourceLocation)ORB);
    private static final ResourceLocation ORB = new ResourceLocation("thaumcraft", "textures/models/orb.obj");
    private static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/blocks/frostshard.png");

    public void renderShard(EntityFrostShard shard, double par2, double par4, double par6, float par8, float par9) {
        this.func_110777_b((Entity)shard);
        GL11.glPushMatrix();
        GL11.glEnable((int)32826);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glTranslatef((float)((float)par2), (float)((float)par4), (float)((float)par6));
        Random rnd = new Random(shard.func_145782_y());
        GL11.glRotatef((float)(shard.field_70126_B + (shard.field_70177_z - shard.field_70126_B) * par9), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(shard.field_70127_C + (shard.field_70125_A - shard.field_70127_C) * par9), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glPushMatrix();
        float base = shard.getDamage() * 0.1f;
        GL11.glScalef((float)(base + rnd.nextFloat() * 0.1f), (float)(base + rnd.nextFloat() * 0.1f), (float)(base + rnd.nextFloat() * 0.1f));
        this.model.renderAll();
        GL11.glPopMatrix();
        GL11.glDisable((int)3042);
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderShard((EntityFrostShard)par1Entity, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return rl;
    }
}

