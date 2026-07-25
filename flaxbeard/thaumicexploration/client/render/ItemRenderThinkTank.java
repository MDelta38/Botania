/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package flaxbeard.thaumicexploration.client.render;

import flaxbeard.thaumicexploration.client.render.TileEntityThinkTankRender;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemRenderThinkTank
implements IItemRenderer {
    TileEntityThinkTankRender render;
    private TileEntity dummytile;

    public ItemRenderThinkTank(TileEntitySpecialRenderer render, TileEntity dummy) {
        this.render = (TileEntityThinkTankRender)render;
        this.dummytile = dummy;
    }

    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack item, Object ... data) {
        if (type == IItemRenderer.ItemRenderType.ENTITY) {
            GL11.glTranslatef((float)-0.5f, (float)0.0f, (float)-0.5f);
        }
        this.render.renderInventoryIcon(0.0, 0.0, 0.0, 0.0f);
    }
}

