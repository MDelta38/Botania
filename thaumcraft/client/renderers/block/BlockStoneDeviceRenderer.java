/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.block.BlockRenderer;
import thaumcraft.common.blocks.BlockStoneDevice;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileFluxScrubber;
import thaumcraft.common.tiles.TileFocalManipulator;
import thaumcraft.common.tiles.TileInfusionMatrix;
import thaumcraft.common.tiles.TileNodeConverter;
import thaumcraft.common.tiles.TileNodeStabilizer;

public class BlockStoneDeviceRenderer
extends BlockRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        if (metadata == 0) {
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            renderer.func_147775_a(block);
            BlockStoneDeviceRenderer.drawFaces(renderer, block, ((BlockStoneDevice)block).iconFurnace[1], ((BlockStoneDevice)block).iconFurnace[1], ((BlockStoneDevice)block).iconFurnace[2], ((BlockStoneDevice)block).iconFurnace[2], ((BlockStoneDevice)block).iconFurnace[2], ((BlockStoneDevice)block).iconFurnace[2], true);
        } else if (metadata == 1) {
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.25f, 1.0f);
            renderer.func_147775_a(block);
            BlockStoneDeviceRenderer.drawFaces(renderer, block, ((BlockStoneDevice)block).iconPedestal[1], ((BlockStoneDevice)block).iconPedestal[1], ((BlockStoneDevice)block).iconPedestal[0], ((BlockStoneDevice)block).iconPedestal[0], ((BlockStoneDevice)block).iconPedestal[0], ((BlockStoneDevice)block).iconPedestal[0], true);
            block.func_149676_a(0.25f, 0.25f, 0.25f, 0.75f, 0.75f, 0.75f);
            renderer.func_147775_a(block);
            BlockStoneDeviceRenderer.drawFaces(renderer, block, ((BlockStoneDevice)block).iconPedestal[1], ((BlockStoneDevice)block).iconPedestal[1], ((BlockStoneDevice)block).iconPedestal[0], ((BlockStoneDevice)block).iconPedestal[0], ((BlockStoneDevice)block).iconPedestal[0], ((BlockStoneDevice)block).iconPedestal[0], true);
            block.func_149676_a(0.125f, 0.75f, 0.125f, 0.875f, 1.0f, 0.875f);
            renderer.func_147775_a(block);
            BlockStoneDeviceRenderer.drawFaces(renderer, block, ((BlockStoneDevice)block).iconPedestal[1], ((BlockStoneDevice)block).iconPedestal[1], ((BlockStoneDevice)block).iconPedestal[0], ((BlockStoneDevice)block).iconPedestal[0], ((BlockStoneDevice)block).iconPedestal[0], ((BlockStoneDevice)block).iconPedestal[0], true);
        } else if (metadata == 2) {
            GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
            TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileInfusionMatrix(), 0.0, 0.0, 0.0, 0.0f);
        } else if (metadata == 5) {
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.25f, 1.0f);
            renderer.func_147775_a(block);
            BlockStoneDeviceRenderer.drawFaces(renderer, block, ((BlockStoneDevice)block).iconPedestal[1], ((BlockStoneDevice)block).iconWandPedestal[1], ((BlockStoneDevice)block).iconWandPedestal[0], ((BlockStoneDevice)block).iconWandPedestal[0], ((BlockStoneDevice)block).iconWandPedestal[0], ((BlockStoneDevice)block).iconWandPedestal[0], true);
            block.func_149676_a(0.125f, 0.25f, 0.125f, 0.875f, 0.5f, 0.875f);
            renderer.func_147775_a(block);
            BlockStoneDeviceRenderer.drawFaces(renderer, block, ((BlockStoneDevice)block).iconPedestal[1], ((BlockStoneDevice)block).iconWandPedestal[1], ((BlockStoneDevice)block).iconWandPedestal[0], ((BlockStoneDevice)block).iconWandPedestal[0], ((BlockStoneDevice)block).iconWandPedestal[0], ((BlockStoneDevice)block).iconWandPedestal[0], true);
            block.func_149676_a(0.25f, 0.5f, 0.25f, 0.75f, 1.0f, 0.75f);
            renderer.func_147775_a(block);
            BlockStoneDeviceRenderer.drawFaces(renderer, block, ((BlockStoneDevice)block).iconPedestal[1], ((BlockStoneDevice)block).iconWandPedestal[1], ((BlockStoneDevice)block).iconWandPedestal[0], ((BlockStoneDevice)block).iconWandPedestal[0], ((BlockStoneDevice)block).iconWandPedestal[0], ((BlockStoneDevice)block).iconWandPedestal[0], true);
        } else if (metadata == 8) {
            block.func_149676_a(W5, 0.0f, W5, W11, W1, W11);
            renderer.func_147775_a(block);
            BlockStoneDeviceRenderer.drawFaces(renderer, block, ((BlockStoneDevice)block).iconWandPedestalFocus[2], ((BlockStoneDevice)block).iconWandPedestalFocus[1], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], true);
            block.func_149676_a(W1, 0.0f, W7, W5, W1, W9);
            renderer.func_147775_a(block);
            BlockStoneDeviceRenderer.drawFaces(renderer, block, ((BlockStoneDevice)block).iconWandPedestalFocus[2], ((BlockStoneDevice)block).iconWandPedestalFocus[1], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], true);
            block.func_149676_a(W11, 0.0f, W7, W15, W1, W9);
            renderer.func_147775_a(block);
            BlockStoneDeviceRenderer.drawFaces(renderer, block, ((BlockStoneDevice)block).iconWandPedestalFocus[2], ((BlockStoneDevice)block).iconWandPedestalFocus[1], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], true);
            block.func_149676_a(W7, 0.0f, W1, W9, W1, W5);
            renderer.func_147775_a(block);
            BlockStoneDeviceRenderer.drawFaces(renderer, block, ((BlockStoneDevice)block).iconWandPedestalFocus[2], ((BlockStoneDevice)block).iconWandPedestalFocus[1], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], true);
            block.func_149676_a(W7, 0.0f, W11, W9, W1, W15);
            renderer.func_147775_a(block);
            BlockStoneDeviceRenderer.drawFaces(renderer, block, ((BlockStoneDevice)block).iconWandPedestalFocus[2], ((BlockStoneDevice)block).iconWandPedestalFocus[1], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], true);
            block.func_149676_a(W1, W1, W7, W3, W7, W9);
            renderer.func_147775_a(block);
            BlockStoneDeviceRenderer.drawFaces(renderer, block, ((BlockStoneDevice)block).iconWandPedestalFocus[2], ((BlockStoneDevice)block).iconWandPedestalFocus[1], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], true);
            block.func_149676_a(W7, W1, W1, W9, W7, W3);
            renderer.func_147775_a(block);
            BlockStoneDeviceRenderer.drawFaces(renderer, block, ((BlockStoneDevice)block).iconWandPedestalFocus[2], ((BlockStoneDevice)block).iconWandPedestalFocus[1], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], true);
            block.func_149676_a(W13, W1, W7, W15, W7, W9);
            renderer.func_147775_a(block);
            BlockStoneDeviceRenderer.drawFaces(renderer, block, ((BlockStoneDevice)block).iconWandPedestalFocus[2], ((BlockStoneDevice)block).iconWandPedestalFocus[1], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], true);
            block.func_149676_a(W7, W1, W13, W9, W7, W15);
            renderer.func_147775_a(block);
            BlockStoneDeviceRenderer.drawFaces(renderer, block, ((BlockStoneDevice)block).iconWandPedestalFocus[2], ((BlockStoneDevice)block).iconWandPedestalFocus[1], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], ((BlockStoneDevice)block).iconWandPedestalFocus[0], true);
        } else if (metadata == 9 || metadata == 10) {
            GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
            TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileNodeStabilizer(metadata), 0.0, 0.0, 0.0, 0.0f);
        } else if (metadata == 9 || metadata == 11) {
            GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
            TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileNodeConverter(), 0.0, 0.0, 0.0, 0.0f);
        } else if (metadata == 12) {
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            renderer.func_147775_a(block);
            BlockStoneDeviceRenderer.drawFaces(renderer, block, ((BlockStoneDevice)block).iconPedestal[1], ((BlockStoneDevice)block).iconSpa[1], ((BlockStoneDevice)block).iconSpa[0], ((BlockStoneDevice)block).iconSpa[0], ((BlockStoneDevice)block).iconSpa[0], ((BlockStoneDevice)block).iconSpa[0], true);
        } else if (metadata == 13) {
            GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
            TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileFocalManipulator(), 0.0, 0.0, 0.0, 0.0f);
        } else if (metadata == 14) {
            GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
            TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileFluxScrubber(), 0.0, 0.0, 0.0, 0.0f);
        }
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int metadata = world.func_72805_g(x, y, z);
        if (metadata == 0 || metadata == 12) {
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
        } else if (metadata == 1) {
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.25f, 1.0f);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
            block.func_149676_a(0.25f, 0.25f, 0.25f, 0.75f, 0.75f, 0.75f);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
            block.func_149676_a(0.125f, 0.75f, 0.125f, 0.875f, 1.0f, 0.875f);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
        } else if (metadata == 5) {
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.25f, 1.0f);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
            block.func_149676_a(0.125f, 0.25f, 0.125f, 0.875f, 0.5f, 0.875f);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
            block.func_149676_a(0.25f, 0.5f, 0.25f, 0.75f, 1.0f, 0.75f);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
        } else if (metadata == 8) {
            block.func_149676_a(W5, 0.0f, W5, W11, W1, W11);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
            block.func_149676_a(W1, 0.0f, W7, W5, W1, W9);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
            block.func_149676_a(W11, 0.0f, W7, W15, W1, W9);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
            block.func_149676_a(W7, 0.0f, W1, W9, W1, W5);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
            block.func_149676_a(W7, 0.0f, W11, W9, W1, W15);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
            block.func_149676_a(W1, W1, W7, W3, W7, W9);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
            block.func_149676_a(W7, W1, W1, W9, W7, W3);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
            block.func_149676_a(W13, W1, W7, W15, W7, W9);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
            block.func_149676_a(W7, W1, W13, W9, W7, W15);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
        }
        renderer.func_147771_a();
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        renderer.func_147775_a(block);
        return true;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return ConfigBlocks.blockStoneDeviceRI;
    }
}

