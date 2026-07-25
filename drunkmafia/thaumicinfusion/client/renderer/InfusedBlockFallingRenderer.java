/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package drunkmafia.thaumicinfusion.client.renderer;

import drunkmafia.thaumicinfusion.common.aspect.entity.InfusedBlockFalling;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class InfusedBlockFallingRenderer
extends Render {
    public void func_76986_a(Entity entity, double x, double y, double z, float p_76986_8_, float p_76986_9_) {
        InfusedBlockFalling movingEntity = (InfusedBlockFalling)entity;
        GL11.glPushMatrix();
        GL11.glTranslated((double)x, (double)y, (double)z);
        GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glScalef((float)4.0f, (float)4.0f, (float)4.0f);
        EntityItem entityitem = new EntityItem(entity.field_70170_p, 0.0, 0.0, 0.0, new ItemStack(Block.func_149729_e((int)movingEntity.id), 1, movingEntity.meta));
        entityitem.field_70290_d = 0.0f;
        RenderManager.field_78727_a.func_147940_a((Entity)entityitem, 0.0, 0.0, 0.0, 0.0f, 0.0f);
        if (!Minecraft.func_71375_t()) {
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            RenderManager.field_78727_a.func_147940_a((Entity)entityitem, 0.0, 0.0, 0.0, 0.0f, 0.0f);
        }
        GL11.glPopMatrix();
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_) {
        return null;
    }
}

