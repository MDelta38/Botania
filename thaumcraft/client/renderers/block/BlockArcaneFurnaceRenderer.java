/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.IBlockAccess
 */
package thaumcraft.client.renderers.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import thaumcraft.client.renderers.block.BlockRenderer;
import thaumcraft.common.blocks.BlockArcaneFurnace;
import thaumcraft.common.config.ConfigBlocks;

public class BlockArcaneFurnaceRenderer
extends BlockRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        BlockArcaneFurnaceRenderer.setBrightness(world, x, y, z, block);
        int md = world.func_72805_g(x, y, z);
        if (md <= 9) {
            if (md == 0) {
                BlockArcaneFurnaceRenderer.setBrightness(world, x, y, z, block);
                renderer.field_147840_d = Blocks.field_150353_l.func_149733_h(0);
            }
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
        } else if (md == 10) {
            if (world.func_147439_a(x - 1, y, z) == block && world.func_72805_g(x - 1, y, z) == 0) {
                renderer.func_147764_f(block, (double)((float)x - W10), (double)y, (double)z, ((BlockArcaneFurnace)block).icon[13]);
                renderer.func_147764_f(block, (double)((float)x - 0.8f), (double)y, (double)z, ((BlockArcaneFurnace)block).icon[15]);
                BlockArcaneFurnaceRenderer.setBrightness(world, x, y, z, block);
                block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.5f, 1.0f);
                renderer.func_147775_a(block);
                renderer.func_147764_f(block, (double)((float)x - 0.9f), (double)y, (double)z, Blocks.field_150480_ab.func_149733_h(0));
            } else if (world.func_147439_a(x + 1, y, z) == block && world.func_72805_g(x + 1, y, z) == 0) {
                renderer.func_147798_e(block, (double)((float)x + W10), (double)y, (double)z, ((BlockArcaneFurnace)block).icon[13]);
                renderer.func_147798_e(block, (double)((float)x + 0.8f), (double)y, (double)z, ((BlockArcaneFurnace)block).icon[15]);
                BlockArcaneFurnaceRenderer.setBrightness(world, x, y, z, block);
                block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.5f, 1.0f);
                renderer.func_147775_a(block);
                renderer.func_147798_e(block, (double)((float)x + 0.9f), (double)y, (double)z, Blocks.field_150480_ab.func_149733_h(0));
            } else if (world.func_147439_a(x, y, z - 1) == block && world.func_72805_g(x, y, z - 1) == 0) {
                renderer.func_147734_d(block, (double)x, (double)y, (double)((float)z - W10), ((BlockArcaneFurnace)block).icon[13]);
                renderer.func_147734_d(block, (double)x, (double)y, (double)((float)z - 0.8f), ((BlockArcaneFurnace)block).icon[15]);
                BlockArcaneFurnaceRenderer.setBrightness(world, x, y, z, block);
                block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.5f, 1.0f);
                renderer.func_147775_a(block);
                renderer.func_147734_d(block, (double)x, (double)y, (double)((float)z - 0.9f), Blocks.field_150480_ab.func_149733_h(0));
            } else {
                renderer.func_147761_c(block, (double)x, (double)y, (double)((float)z + W10), ((BlockArcaneFurnace)block).icon[13]);
                renderer.func_147761_c(block, (double)x, (double)y, (double)((float)z + 0.8f), ((BlockArcaneFurnace)block).icon[15]);
                BlockArcaneFurnaceRenderer.setBrightness(world, x, y, z, block);
                block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.5f, 1.0f);
                renderer.func_147775_a(block);
                renderer.func_147761_c(block, (double)x, (double)y, (double)((float)z + 0.9f), Blocks.field_150480_ab.func_149733_h(0));
            }
        }
        renderer.func_147771_a();
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        renderer.func_147775_a(block);
        return true;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return ConfigBlocks.blockArcaneFurnaceRI;
    }
}

