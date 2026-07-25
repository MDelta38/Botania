/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
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

import com.kentington.thaumichorizons.client.renderer.model.ModelInjector;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;

public class ItemInjectorRender
implements IItemRenderer {
    private ModelBase injector = new ModelInjector();
    private String tx1 = "textures/models/injector.png";

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return helper != IItemRenderer.ItemRendererHelper.BLOCK_3D;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        int ticksUsed = 0;
        int rotation = 0;
        if (item.field_77990_d != null) {
            int rotationTarget;
            ticksUsed = item.field_77990_d.func_74762_e("usetime");
            rotation = item.field_77990_d.func_74762_e("rotation");
            if (rotation < (rotationTarget = item.field_77990_d.func_74762_e("rotationTarget"))) {
                item.field_77990_d.func_74768_a("rotation", ++rotation);
            } else if (rotation > rotationTarget) {
                item.field_77990_d.func_74768_a("rotation", --rotation);
            }
        }
        float f = (float)ticksUsed / 30.0f;
        if ((f = (f * f + f * 2.0f) / 3.0f) > 1.0f) {
            f = 1.0f;
        }
        ItemRenderer ir = RenderManager.field_78727_a.field_78721_f;
        GL11.glPushMatrix();
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        if (type != IItemRenderer.ItemRenderType.INVENTORY) {
            if (type == IItemRenderer.ItemRenderType.ENTITY) {
                GL11.glTranslated((double)0.0, (double)-0.5, (double)0.0);
            } else if (type != IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
                GL11.glTranslated((double)0.25, (double)-1.0, (double)-1.25);
                GL11.glRotated((double)-45.0, (double)0.0, (double)0.0, (double)1.0);
                GL11.glRotated((double)-90.0, (double)0.0, (double)1.0, (double)0.0);
            } else {
                GL11.glScaled((double)4.0, (double)4.0, (double)4.0);
                GL11.glTranslated((double)1.5, (double)0.0, (double)0.0);
                GL11.glRotated((double)230.0, (double)0.0, (double)1.0, (double)0.0);
            }
        } else {
            GL11.glTranslated((double)0.38, (double)-0.1, (double)0.38);
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        UtilsFX.bindTexture((ResourceLocation)new ResourceLocation("thaumichorizons", this.tx1));
        this.injector.func_78088_a(null, f * (float)Math.PI / 16.0f, f * (float)Math.PI / 4.0f, (float)rotation, f, 0.0f, 0.125f);
        GL11.glPopMatrix();
    }
}

