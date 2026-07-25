/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.client.renderers.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.client.renderers.block.BlockRenderer;
import thaumcraft.common.blocks.BlockCosmeticOpaque;
import thaumcraft.common.config.ConfigBlocks;

public class BlockCosmeticOpaqueRenderer
extends BlockRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        renderer.func_147775_a(block);
        BlockCosmeticOpaqueRenderer.drawFaces(renderer, block, block.func_149691_a(0, metadata), false);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        if (block == null || !(block instanceof BlockCosmeticOpaque)) {
            return false;
        }
        int bb = BlockCosmeticOpaqueRenderer.setBrightness(world, x, y, z, block);
        int metadata = world.func_72805_g(x, y, z);
        if (((BlockCosmeticOpaque)block).currentPass == 1) {
            if (metadata <= 1) {
                block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                renderer.func_147775_a(block);
                renderer.func_147784_q(block, x, y, z);
            } else if (metadata == 2) {
                block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                renderer.func_147775_a(block);
                for (int d = 0; d < 6; ++d) {
                    ForgeDirection dir1 = ForgeDirection.getOrientation((int)d);
                    if (!block.func_149646_a(world, x + dir1.offsetX, y + dir1.offsetY, z + dir1.offsetZ, d)) continue;
                    switch (d) {
                        case 0: {
                            renderer.func_147768_a(block, (double)x, (double)y, (double)z, block.func_149673_e(world, x, y, z, d));
                            break;
                        }
                        case 1: {
                            renderer.func_147806_b(block, (double)x, (double)y, (double)z, block.func_149673_e(world, x, y, z, d));
                            break;
                        }
                        case 2: {
                            renderer.func_147761_c(block, (double)x, (double)y, (double)z, block.func_149673_e(world, x, y, z, d));
                            break;
                        }
                        case 3: {
                            renderer.func_147734_d(block, (double)x, (double)y, (double)z, block.func_149673_e(world, x, y, z, d));
                            break;
                        }
                        case 4: {
                            renderer.func_147798_e(block, (double)x, (double)y, (double)z, block.func_149673_e(world, x, y, z, d));
                            break;
                        }
                        case 5: {
                            renderer.func_147764_f(block, (double)x, (double)y, (double)z, block.func_149673_e(world, x, y, z, d));
                        }
                    }
                    renderer.field_147842_e = false;
                }
            }
            renderer.func_147771_a();
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            renderer.func_147775_a(block);
            return true;
        }
        renderer.func_147782_a(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
        return false;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return ConfigBlocks.blockCosmeticOpaqueRI;
    }
}

