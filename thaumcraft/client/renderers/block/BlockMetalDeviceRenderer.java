/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.block.BlockRenderer;
import thaumcraft.common.blocks.BlockMetalDevice;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileAlembic;
import thaumcraft.common.tiles.TileBrainbox;
import thaumcraft.common.tiles.TileCrucible;
import thaumcraft.common.tiles.TileMagicWorkbenchCharger;
import thaumcraft.common.tiles.TileVisRelay;

public class BlockMetalDeviceRenderer
extends BlockRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        if (metadata == 0 || metadata == 6) {
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            renderer.func_147775_a(block);
            BlockMetalDeviceRenderer.drawFaces(renderer, block, ((BlockMetalDevice)block).icon[2], ((BlockMetalDevice)block).icon[4], ((BlockMetalDevice)block).icon[3], ((BlockMetalDevice)block).icon[3], ((BlockMetalDevice)block).icon[3], ((BlockMetalDevice)block).icon[3], true);
        } else if (metadata == 1) {
            GL11.glTranslatef((float)-0.5f, (float)0.0f, (float)-0.5f);
            TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileAlembic(), 0.0, 0.0, 0.0, 0.0f);
            GL11.glEnable((int)32826);
        } else if (metadata == 5) {
            GL11.glTranslatef((float)0.0f, (float)-0.3f, (float)0.0f);
            block.func_149676_a(0.0f, 0.8125f, 0.0f, 1.0f, 1.0f, 1.0f);
            renderer.func_147775_a(block);
            BlockMetalDeviceRenderer.drawFaces(renderer, block, ((BlockMetalDevice)block).icon[8], false);
            BlockMetalDeviceRenderer.drawFaces(renderer, block, ((BlockMetalDevice)block).icon[9], false);
        } else if (metadata == 7) {
            block.func_149676_a(W4, W2, W4, W12, W14, W12);
            renderer.func_147775_a(block);
            BlockMetalDeviceRenderer.drawFaces(renderer, block, ((BlockMetalDevice)block).icon[11], ((BlockMetalDevice)block).icon[11], ((BlockMetalDevice)block).icon[10], ((BlockMetalDevice)block).icon[10], ((BlockMetalDevice)block).icon[10], ((BlockMetalDevice)block).icon[10], true);
        } else if (metadata == 8) {
            block.func_149676_a(W4, W2, W4, W12, W14, W12);
            renderer.func_147775_a(block);
            BlockMetalDeviceRenderer.drawFaces(renderer, block, ((BlockMetalDevice)block).icon[13], ((BlockMetalDevice)block).icon[13], ((BlockMetalDevice)block).icon[12], ((BlockMetalDevice)block).icon[12], ((BlockMetalDevice)block).icon[12], ((BlockMetalDevice)block).icon[12], true);
        } else if (metadata == 9) {
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            renderer.func_147775_a(block);
            BlockMetalDeviceRenderer.drawFaces(renderer, block, ((BlockMetalDevice)block).icon[16], true);
        } else if (metadata == 3) {
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            renderer.func_147775_a(block);
            BlockMetalDeviceRenderer.drawFaces(renderer, block, ((BlockMetalDevice)block).icon[22], true);
        } else if (metadata == 12) {
            block.func_149676_a(W3, W3, W3, W13, W13, W13);
            renderer.func_147775_a(block);
            BlockMetalDeviceRenderer.drawFaces(renderer, block, ((BlockMetalDevice)block).icon[17], true);
        } else if (metadata == 13) {
            block.func_149676_a(W4, W2, W4, W12, W14, W12);
            renderer.func_147775_a(block);
            BlockMetalDeviceRenderer.drawFaces(renderer, block, ((BlockMetalDevice)block).icon[19], ((BlockMetalDevice)block).icon[19], ((BlockMetalDevice)block).icon[18], ((BlockMetalDevice)block).icon[18], ((BlockMetalDevice)block).icon[18], ((BlockMetalDevice)block).icon[18], true);
        } else if (metadata == 14) {
            GL11.glPushMatrix();
            GL11.glScaled((double)1.5, (double)1.5, (double)1.5);
            GL11.glTranslatef((float)-0.5f, (float)-0.25f, (float)-0.5f);
            TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileVisRelay(), 0.0, 0.0, 0.0, 0.0f);
            GL11.glEnable((int)32826);
            GL11.glPopMatrix();
        } else if (metadata == 2) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
            TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileMagicWorkbenchCharger(), 0.0, 0.0, 0.0, 0.0f);
            GL11.glEnable((int)32826);
            GL11.glPopMatrix();
        }
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int metadata = world.func_72805_g(x, y, z);
        if (metadata == 0) {
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
            IIcon innerSide = ((BlockMetalDevice)block).icon[5];
            IIcon bottom = ((BlockMetalDevice)block).icon[6];
            float f5 = 0.123f;
            TileEntity te = world.func_147438_o(x, y, z);
            if (te != null && te instanceof TileCrucible && ((TileCrucible)te).aspects.size() > 0) {
                BlockMetalDeviceRenderer.setBrightness(world, x, y, z, block);
            }
            renderer.func_147764_f(block, (double)((float)x - 1.0f + f5), (double)y, (double)z, innerSide);
            renderer.func_147798_e(block, (double)((float)x + 1.0f - f5), (double)y, (double)z, innerSide);
            renderer.func_147734_d(block, (double)x, (double)y, (double)((float)z - 1.0f + f5), innerSide);
            renderer.func_147761_c(block, (double)x, (double)y, (double)((float)z + 1.0f - f5), innerSide);
            renderer.func_147806_b(block, (double)x, (double)((float)y - 1.0f + 0.25f), (double)z, bottom);
            renderer.func_147768_a(block, (double)x, (double)((float)y + 1.0f - 0.75f), (double)z, bottom);
        } else if (metadata == 5 || metadata == 6) {
            BlockMetalDeviceRenderer.setBrightness(world, x, y, z, block);
            block.func_149676_a(0.0f, 0.8125f, 0.0f, 1.0f, 1.0f, 1.0f);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147764_f(block, (double)((float)x - 1.002f + W3), (double)y, (double)z, block.func_149733_h(0));
            renderer.func_147798_e(block, (double)((float)x + 1.002f - W3), (double)y, (double)z, block.func_149733_h(0));
            renderer.func_147734_d(block, (double)x, (double)y, (double)((float)z - 1.002f + W3), block.func_149733_h(0));
            renderer.func_147761_c(block, (double)x, (double)y, (double)((float)z + 1.002f - W3), block.func_149733_h(0));
            renderer.func_147764_f(block, (double)((float)x - 1.002f + W9), (double)y, (double)z, block.func_149733_h(0));
            renderer.func_147798_e(block, (double)((float)x + 1.002f - W9), (double)y, (double)z, block.func_149733_h(0));
            renderer.func_147734_d(block, (double)x, (double)y, (double)((float)z - 1.002f + W9), block.func_149733_h(0));
            renderer.func_147761_c(block, (double)x, (double)y, (double)((float)z + 1.002f - W9), block.func_149733_h(0));
            if (metadata == 6) {
                block.func_149676_a(W1, W14, W1, W15, W15, W15);
                renderer.func_147775_a(block);
                renderer.field_147840_d = ((BlockMetalDevice)block).icon[9];
                renderer.func_147784_q(block, x, y, z);
            }
        } else if (metadata == 7 || metadata == 8 || metadata == 13) {
            block.func_149676_a(W4, W2, W4, W12, W14, W12);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
        } else if (metadata == 3 || metadata == 9) {
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
        } else if (metadata == 12) {
            block.func_149676_a(W3, W3, W3, W13, W13, W13);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
            TileEntity te = world.func_147438_o(x, y, z);
            if (te != null && te instanceof TileBrainbox) {
                switch (((TileBrainbox)te).facing) {
                    case UP: {
                        block.func_149676_a(W6, W13, W6, W10, 1.0f, W10);
                        renderer.func_147775_a(block);
                        renderer.func_147784_q(block, x, y, z);
                        break;
                    }
                    case DOWN: {
                        block.func_149676_a(W6, 0.0f, W6, W10, W3, W10);
                        renderer.func_147775_a(block);
                        renderer.func_147784_q(block, x, y, z);
                        break;
                    }
                    case EAST: {
                        block.func_149676_a(W13, W6, W6, 1.0f, W10, W10);
                        renderer.func_147775_a(block);
                        renderer.func_147784_q(block, x, y, z);
                        break;
                    }
                    case WEST: {
                        block.func_149676_a(0.0f, W6, W6, W3, W10, W10);
                        renderer.func_147775_a(block);
                        renderer.func_147784_q(block, x, y, z);
                        break;
                    }
                    case SOUTH: {
                        block.func_149676_a(W6, W6, W13, W10, W10, 1.0f);
                        renderer.func_147775_a(block);
                        renderer.func_147784_q(block, x, y, z);
                        break;
                    }
                    case NORTH: {
                        block.func_149676_a(W6, W6, 0.0f, W10, W10, W3);
                        renderer.func_147775_a(block);
                        renderer.func_147784_q(block, x, y, z);
                    }
                }
            }
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
        return ConfigBlocks.blockMetalDeviceRI;
    }
}

