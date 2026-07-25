/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.tiles.TileBanner;

public class ItemBannerRenderer
implements IItemRenderer {
    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        if (!item.func_77942_o() || item.func_77960_j() != 8) {
            return false;
        }
        return item.field_77990_d.func_74779_i("aspect") != null && item.field_77990_d.func_74771_c("color") >= 0;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        Minecraft mc = Minecraft.func_71410_x();
        GL11.glPushMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        boolean var11 = false;
        if (type == IItemRenderer.ItemRenderType.EQUIPPED || type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON) {
            GL11.glTranslatef((float)1.0f, (float)1.0f, (float)1.0f);
        } else {
            GL11.glRotatef((float)((float)var11), (float)0.0f, (float)1.0f, (float)0.0f);
        }
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.5f, (float)-1.0f, (float)-0.5f);
        TileBanner tb = new TileBanner();
        tb.setColor(item.field_77990_d.func_74771_c("color"));
        if (item.field_77990_d.func_74779_i("aspect") != null) {
            tb.setAspect(Aspect.getAspect(item.field_77990_d.func_74779_i("aspect")));
        }
        TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)tb, 0.0, 0.0, 0.0, 0.0f);
        GL11.glPopMatrix();
    }
}

