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
import thaumcraft.common.blocks.BlockWoodenDevice;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileArcaneBore;
import thaumcraft.common.tiles.TileArcaneBoreBase;
import thaumcraft.common.tiles.TileBanner;
import thaumcraft.common.tiles.TileBellows;
import thaumcraft.common.tiles.TileSensor;

public class BlockWoodenDeviceRenderer
extends BlockRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderblocks) {
        if (metadata == 0) {
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
            TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileBellows(), 0.0, 0.0, 0.0, 0.0f);
        } else if (metadata == 4) {
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
            TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileArcaneBoreBase(), 0.0, 0.0, 0.0, 0.0f);
        } else if (metadata == 5) {
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.5f, (float)-0.75f, (float)-0.5f);
            TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileArcaneBore(), 0.0, 0.0, 0.0, 0.0f);
        } else if (metadata == 1) {
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, W3, 1.0f);
            renderblocks.func_147775_a(block);
            BlockWoodenDeviceRenderer.drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconAEar[2], ((BlockWoodenDevice)block).iconAEar[3], ((BlockWoodenDevice)block).iconAEar[0], ((BlockWoodenDevice)block).iconAEar[0], ((BlockWoodenDevice)block).iconAEar[0], ((BlockWoodenDevice)block).iconAEar[0], true);
            block.func_149676_a(W4, W3, W4, W12, 1.0f, W12);
            renderblocks.func_147775_a(block);
            BlockWoodenDeviceRenderer.drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconAEar[2], ((BlockWoodenDevice)block).iconAEar[3], ((BlockWoodenDevice)block).iconAEar[0], ((BlockWoodenDevice)block).iconAEar[0], ((BlockWoodenDevice)block).iconAEar[0], ((BlockWoodenDevice)block).iconAEar[0], true);
            block.func_149676_a(W4, 0.5f, W1, W12, 1.0f, W3);
            renderblocks.func_147775_a(block);
            BlockWoodenDeviceRenderer.drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], true);
            block.func_149676_a(W5, 0.5f, W3, W11, W15, W4);
            renderblocks.func_147775_a(block);
            BlockWoodenDeviceRenderer.drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], true);
            block.func_149676_a(W1, 0.5f, W4, W3, 1.0f, W12);
            renderblocks.func_147775_a(block);
            BlockWoodenDeviceRenderer.drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], true);
            block.func_149676_a(W3, 0.5f, W5, W4, W15, W11);
            renderblocks.func_147775_a(block);
            BlockWoodenDeviceRenderer.drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], true);
            block.func_149676_a(W4, 0.5f, W13, W12, 1.0f, W15);
            renderblocks.func_147775_a(block);
            BlockWoodenDeviceRenderer.drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], true);
            block.func_149676_a(W5, 0.5f, W12, W11, W15, W13);
            renderblocks.func_147775_a(block);
            BlockWoodenDeviceRenderer.drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], true);
            block.func_149676_a(W13, 0.5f, W4, W15, 1.0f, W12);
            renderblocks.func_147775_a(block);
            BlockWoodenDeviceRenderer.drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], true);
            block.func_149676_a(W12, 0.5f, W5, W13, W15, W11);
            renderblocks.func_147775_a(block);
            BlockWoodenDeviceRenderer.drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[6], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], ((BlockWoodenDevice)block).iconAEar[5], true);
        } else if (metadata == 2) {
            GL11.glTranslatef((float)0.0f, (float)0.6f, (float)0.0f);
            GL11.glScalef((float)1.3f, (float)1.3f, (float)1.3f);
            float var6 = 0.0625f;
            block.func_149676_a(var6, 0.0f, var6, 1.0f - var6, 0.125f, 1.0f - var6);
            renderblocks.func_147775_a(block);
            BlockWoodenDeviceRenderer.drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconAPPlate[0], true);
        } else if (metadata == 6) {
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            renderblocks.func_147775_a(block);
            BlockWoodenDeviceRenderer.drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconGreatwood, true);
        } else if (metadata == 7) {
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            renderblocks.func_147775_a(block);
            BlockWoodenDeviceRenderer.drawFaces(renderblocks, block, ((BlockWoodenDevice)block).iconSilverwood, true);
        } else if (metadata == 8) {
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.5f, (float)-1.0f, (float)-0.5f);
            TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileBanner(), 0.0, 0.0, 0.0, 0.0f);
        }
        GL11.glEnable((int)32826);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderblocks) {
        int md = world.func_72805_g(x, y, z);
        if (md == 1) {
            ((BlockWoodenDevice)block).renderState = 0;
            TileEntity tile = world.func_147438_o(x, y, z);
            if (tile != null && tile instanceof TileSensor && ((TileSensor)tile).redstoneSignal > 0) {
                ((BlockWoodenDevice)block).renderState = 1;
            }
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, W3, 1.0f);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, x, y, z);
            block.func_149676_a(W4, W3, W4, W12, 1.0f, W12);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, x, y, z);
            ((BlockWoodenDevice)block).renderState = 2;
            block.func_149676_a(W4, 0.5f, W1, W12, 1.0f, W3);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, x, y, z);
            block.func_149676_a(W5, 0.5f, W3, W11, W15, W4);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, x, y, z);
            block.func_149676_a(W1, 0.5f, W4, W3, 1.0f, W12);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, x, y, z);
            block.func_149676_a(W3, 0.5f, W5, W4, W15, W11);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, x, y, z);
            block.func_149676_a(W4, 0.5f, W13, W12, 1.0f, W15);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, x, y, z);
            block.func_149676_a(W5, 0.5f, W12, W11, W15, W13);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, x, y, z);
            block.func_149676_a(W13, 0.5f, W4, W15, 1.0f, W12);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, x, y, z);
            block.func_149676_a(W12, 0.5f, W5, W13, W15, W11);
            renderblocks.func_147775_a(block);
            renderblocks.func_147784_q(block, x, y, z);
            ((BlockWoodenDevice)block).renderState = 0;
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            renderblocks.func_147775_a(block);
            return true;
        }
        if (md == 2 || md == 3 || md == 6 || md == 7) {
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            renderblocks.func_147784_q(block, x, y, z);
            return true;
        }
        return false;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return ConfigBlocks.blockWoodenDeviceRI;
    }
}

