/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
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

import com.kentington.thaumichorizons.client.renderer.model.ModelSyringe;
import com.kentington.thaumichorizons.common.items.ItemSyringeBlood;
import com.kentington.thaumichorizons.common.items.ItemSyringeBloodSample;
import com.kentington.thaumichorizons.common.items.ItemSyringeEmpty;
import com.kentington.thaumichorizons.common.items.ItemSyringeInjection;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;

public class ItemSyringeRender
implements IItemRenderer {
    private ModelSyringe syringe = new ModelSyringe();
    private String tx1 = "textures/models/syringe.png";

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return item.func_77960_j() == 0;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return helper != IItemRenderer.ItemRendererHelper.BLOCK_3D;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        if (item == null || !(item.func_77973_b() instanceof ItemSyringeBlood) && !(item.func_77973_b() instanceof ItemSyringeBloodSample) && !(item.func_77973_b() instanceof ItemSyringeInjection) && !(item.func_77973_b() instanceof ItemSyringeEmpty)) {
            return;
        }
        ItemRenderer ir = RenderManager.field_78727_a.field_78721_f;
        GL11.glPushMatrix();
        if (type != IItemRenderer.ItemRenderType.INVENTORY) {
            if (type == IItemRenderer.ItemRenderType.ENTITY) {
                GL11.glTranslated((double)0.0, (double)-2.0, (double)0.0);
            } else {
                GL11.glRotatef((float)-66.0f, (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslated((double)-1.0, (double)-2.25, (double)0.75);
            }
        } else {
            GL11.glTranslated((double)0.0, (double)-2.75, (double)0.0);
        }
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        UtilsFX.bindTexture((ResourceLocation)new ResourceLocation("thaumichorizons", this.tx1));
        this.syringe.render(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.125f, item);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }
}

