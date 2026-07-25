/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.world.IBlockAccess
 */
package thaumcraft.client.renderers.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import thaumcraft.client.renderers.block.BlockRenderer;
import thaumcraft.common.config.ConfigBlocks;

public class BlockEldritchRenderer
extends BlockRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        if (metadata == 4 || metadata == 5) {
            block.func_149676_a(W2, W2, W2, W14, W14, W14);
            renderer.func_147775_a(block);
            BlockEldritchRenderer.drawFaces(renderer, block, block.func_149691_a(0, metadata), false);
        }
        if (metadata == 6) {
            block.func_149676_a(W2, W2, W2, W14, W14, W14);
            renderer.func_147775_a(block);
            BlockEldritchRenderer.drawFaces(renderer, block, block.func_149691_a(0, metadata), false);
        }
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int metadata = world.func_72805_g(x, y, z);
        if (metadata == 4 || metadata == 5 || metadata == 6) {
            renderer.field_152631_f = true;
            BlockEldritchRenderer.setBrightness(world, x, y, z, block);
            float s1 = 0.0f;
            float s2 = 0.0f;
            float s3 = 0.0f;
            float s4 = 1.0f;
            float s5 = 1.0f;
            float s6 = 1.0f;
            if (!block.func_149747_d(world, x + 1, y, z, 4)) {
                s4 -= W2;
            }
            if (!block.func_149747_d(world, x - 1, y, z, 5)) {
                s1 += W2;
            }
            if (!block.func_149747_d(world, x, y, z + 1, 2)) {
                s6 -= W2;
            }
            if (!block.func_149747_d(world, x, y, z - 1, 3)) {
                s3 += W2;
            }
            if (!block.func_149747_d(world, x, y + 1, z, 0)) {
                s5 -= W2;
            }
            if (!block.func_149747_d(world, x, y - 1, z, 1)) {
                s2 += W2;
            }
            block.func_149676_a(s1, s2, s3, s4, s5, s6);
            renderer.func_147775_a(block);
            BlockEldritchRenderer.renderAllSides(world, x, y, z, block, renderer, false);
            renderer.field_152631_f = false;
            renderer.func_147771_a();
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            renderer.func_147775_a(block);
        }
        if (metadata == 7 || metadata == 8 || metadata == 9 || metadata == 10) {
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147771_a();
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            renderer.func_147775_a(block);
        }
        return true;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return ConfigBlocks.blockEldritchRI;
    }
}

