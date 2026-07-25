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
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;

public class BlockTaintFibreRenderer
extends BlockRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        boolean fix = true;
        int metadata = world.func_72805_g(x, y, z);
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        renderer.func_147775_a(block);
        BlockTaintFibreRenderer.setBrightness(world, x, y, z, block);
        Tessellator t = Tessellator.field_78398_a;
        if (metadata <= 4) {
            if (world.isSideSolid(x - 1, y, z, ForgeDirection.EAST, true) && world.func_147439_a(x - 1, y, z) != ConfigBlocks.blockTaint) {
                fix = false;
                renderer.func_147764_f(block, (double)((float)x - 0.995f), (double)y, (double)z, block.func_149691_a(0, 0));
            }
            if (world.isSideSolid(x + 1, y, z, ForgeDirection.WEST, true) && world.func_147439_a(x + 1, y, z) != ConfigBlocks.blockTaint) {
                fix = false;
                renderer.func_147798_e(block, (double)((float)x + 0.995f), (double)y, (double)z, block.func_149691_a(0, 0));
            }
            if (world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH, true) && world.func_147439_a(x, y, z - 1) != ConfigBlocks.blockTaint) {
                fix = false;
                renderer.func_147734_d(block, (double)x, (double)y, (double)((float)z - 0.995f), block.func_149691_a(0, 0));
            }
            if (world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH, true) && world.func_147439_a(x, y, z + 1) != ConfigBlocks.blockTaint) {
                fix = false;
                renderer.func_147761_c(block, (double)x, (double)y, (double)((float)z + 0.995f), block.func_149691_a(0, 0));
            }
            if (world.isSideSolid(x, y - 1, z, ForgeDirection.UP, true) && world.func_147439_a(x, y - 1, z) != ConfigBlocks.blockTaint) {
                fix = false;
                renderer.func_147806_b(block, (double)x, (double)((float)y - 0.995f), (double)z, block.func_149691_a(0, 0));
            }
            if (world.isSideSolid(x, y + 1, z, ForgeDirection.DOWN, true) && world.func_147439_a(x, y + 1, z) != ConfigBlocks.blockTaint) {
                fix = false;
                renderer.func_147768_a(block, (double)x, (double)((float)y + 0.995f), (double)z, block.func_149691_a(0, 0));
            }
        }
        if (metadata == 0 && Config.glowyTaint) {
            t.func_78386_a(1.0f, 1.0f, 1.0f);
            t.func_78380_c(200);
            if (world.isSideSolid(x - 1, y, z, ForgeDirection.EAST, true) && world.func_147439_a(x - 1, y, z) != ConfigBlocks.blockTaint) {
                fix = false;
                renderer.func_147764_f(block, (double)((float)x - 0.98f), (double)y, (double)z, ((BlockTaintFibres)block).getOverlayBlockTexture(x, y, z, 4));
            }
            if (world.isSideSolid(x + 1, y, z, ForgeDirection.WEST, true) && world.func_147439_a(x + 1, y, z) != ConfigBlocks.blockTaint) {
                fix = false;
                renderer.func_147798_e(block, (double)((float)x + 0.98f), (double)y, (double)z, ((BlockTaintFibres)block).getOverlayBlockTexture(x, y, z, 5));
            }
            if (world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH, true) && world.func_147439_a(x, y, z - 1) != ConfigBlocks.blockTaint) {
                fix = false;
                renderer.func_147734_d(block, (double)x, (double)y, (double)((float)z - 0.98f), ((BlockTaintFibres)block).getOverlayBlockTexture(x, y, z, 2));
            }
            if (world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH, true) && world.func_147439_a(x, y, z + 1) != ConfigBlocks.blockTaint) {
                fix = false;
                renderer.func_147761_c(block, (double)x, (double)y, (double)((float)z + 0.98f), ((BlockTaintFibres)block).getOverlayBlockTexture(x, y, z, 3));
            }
            if (world.isSideSolid(x, y - 1, z, ForgeDirection.UP, true) && world.func_147439_a(x, y - 1, z) != ConfigBlocks.blockTaint) {
                fix = false;
                renderer.func_147806_b(block, (double)x, (double)((float)y - 0.98f), (double)z, ((BlockTaintFibres)block).getOverlayBlockTexture(x, y, z, 0));
            }
            if (world.isSideSolid(x, y + 1, z, ForgeDirection.DOWN, true) && world.func_147439_a(x, y + 1, z) != ConfigBlocks.blockTaint) {
                fix = false;
                renderer.func_147768_a(block, (double)x, (double)((float)y + 0.98f), (double)z, ((BlockTaintFibres)block).getOverlayBlockTexture(x, y, z, 1));
            }
        }
        if ((metadata == 1 || metadata == 2) && world.isSideSolid(x, y - 1, z, ForgeDirection.UP, true)) {
            double d0 = x;
            double d1 = y;
            double d2 = z;
            long i1 = (long)(x * 3129871) ^ (long)z * 116129781L ^ (long)y;
            i1 = i1 * i1 * 42317861L + i1 * 11L;
            fix = false;
            renderer.func_147765_a(block.func_149691_a(0, metadata), d0 += ((double)((float)(i1 >> 16 & 0xFL) / 15.0f) - 0.5) * 0.5, d1, d2 += ((double)((float)(i1 >> 24 & 0xFL) / 15.0f) - 0.5) * 0.5, 1.0f);
        }
        if (metadata == 3 || metadata == 4) {
            fix = false;
            renderer.func_147746_l(block, x, y, z);
        }
        if (fix) {
            block.func_149676_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
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
        return ConfigBlocks.blockTaintFibreRI;
    }
}

