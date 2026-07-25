/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.ItemRenderer
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 */
package com.kentington.thaumichorizons.client.renderer.item;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;

public class ItemCorpseEffigyRender
implements IItemRenderer {
    private ModelBiped corpse = new ModelBiped();
    private String tx1 = "textures/models/corpseeffigy.png";

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return helper != IItemRenderer.ItemRendererHelper.BLOCK_3D;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        ItemRenderer ir = RenderManager.field_78727_a.field_78721_f;
        GL11.glPushMatrix();
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        if (type != IItemRenderer.ItemRenderType.INVENTORY) {
            if (type == IItemRenderer.ItemRenderType.ENTITY) {
                GL11.glTranslated((double)0.0, (double)-3.0, (double)0.0);
            } else {
                GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
                GL11.glTranslated((double)-1.0, (double)-4.0, (double)-1.0);
            }
        } else {
            GL11.glTranslated((double)0.0, (double)-0.9, (double)0.0);
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        }
        UtilsFX.bindTexture((ResourceLocation)new ResourceLocation("thaumichorizons", this.tx1));
        this.corpse.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.125f);
        GL11.glPopMatrix();
    }
}

