/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.IItemRenderer$ItemRenderType
 *  net.minecraftforge.client.IItemRenderer$ItemRendererHelper
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.render.item;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileFloatingFlower;
import vazkii.botania.common.item.block.ItemBlockFloatingSpecialFlower;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

public class RenderFloatingFlowerItem
implements IItemRenderer {
    public boolean handleRenderType(ItemStack item, IItemRenderer.ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(IItemRenderer.ItemRenderType type, ItemStack item, IItemRenderer.ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(IItemRenderer.ItemRenderType type, ItemStack stack, Object ... data) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        float s = 1.4f;
        GL11.glScalef((float)s, (float)s, (float)s);
        GL11.glRotatef((float)-5.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        Item item = stack.func_77973_b();
        TileFloatingFlower.forcedStack = item instanceof ItemBlockFloatingSpecialFlower ? ItemBlockSpecialFlower.ofType(ItemBlockSpecialFlower.getType(stack)) : new ItemStack(ModBlocks.flower, 1, stack.func_77960_j());
        TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileFloatingFlower(), 0.0, 0.0, 0.0, 0.0f);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }
}

