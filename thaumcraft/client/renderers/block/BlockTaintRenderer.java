/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.client.renderers.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.client.renderers.block.BlockRenderer;
import thaumcraft.common.blocks.BlockTaintFibres;
import thaumcraft.common.config.ConfigBlocks;

public class BlockTaintRenderer
extends BlockRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        renderer.func_147775_a(block);
        BlockTaintRenderer.drawFaces(renderer, block, block.func_149691_a(0, metadata), block.func_149691_a(1, metadata), block.func_149691_a(2, metadata), block.func_149691_a(3, metadata), block.func_149691_a(4, metadata), block.func_149691_a(5, metadata), false);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int metadata = world.func_72805_g(x, y, z);
        if (block.func_149701_w() == 0) {
            if (metadata == 0 || metadata == 1 || metadata == 2) {
                block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                renderer.func_147775_a(block);
                renderer.func_147784_q(block, x, y, z);
            }
        } else if (block.func_149701_w() == 1 && (metadata == 0 || metadata == 1)) {
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            renderer.func_147775_a(block);
            BlockTaintFibres b = (BlockTaintFibres)ConfigBlocks.blockTaintFibres;
            Tessellator t = Tessellator.field_78398_a;
            t.func_78386_a(1.0f, 1.0f, 1.0f);
            t.func_78380_c(200);
            if (block.func_149646_a(world, x + 1, y, z, ForgeDirection.EAST.ordinal())) {
                renderer.func_147764_f(block, (double)x, (double)y, (double)z, b.getOverlayBlockTexture(x, y, z, 4));
            }
            if (block.func_149646_a(world, x - 1, y, z, ForgeDirection.WEST.ordinal())) {
                renderer.func_147798_e(block, (double)x, (double)y, (double)z, b.getOverlayBlockTexture(x, y, z, 5));
            }
            if (block.func_149646_a(world, x, y, z + 1, ForgeDirection.SOUTH.ordinal())) {
                renderer.func_147734_d(block, (double)x, (double)y, (double)z, b.getOverlayBlockTexture(x, y, z, 2));
            }
            if (block.func_149646_a(world, x, y, z - 1, ForgeDirection.NORTH.ordinal())) {
                renderer.func_147761_c(block, (double)x, (double)y, (double)z, b.getOverlayBlockTexture(x, y, z, 3));
            }
            if (block.func_149646_a(world, x, y + 1, z, ForgeDirection.UP.ordinal())) {
                renderer.func_147806_b(block, (double)x, (double)y, (double)z, b.getOverlayBlockTexture(x, y, z, 0));
            }
            if (block.func_149646_a(world, x, y - 1, z, ForgeDirection.DOWN.ordinal())) {
                renderer.func_147768_a(block, (double)x, (double)y, (double)z, b.getOverlayBlockTexture(x, y, z, 1));
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
        return ConfigBlocks.blockTaintRI;
    }
}

