/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  thaumcraft.client.renderers.block.BlockRenderer
 */
package flaxbeard.thaumicexploration.client.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.block.BlockReplicator;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import thaumcraft.client.renderers.block.BlockRenderer;

public class BlockReplicatorRenderer
extends BlockRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        renderer.func_147775_a(block);
        BlockReplicatorRenderer.drawFaces((RenderBlocks)renderer, (Block)block, (IIcon)((BlockReplicator)block).icon[0], (IIcon)((BlockReplicator)block).icon[0], (IIcon)((BlockReplicator)block).icon[1], (IIcon)((BlockReplicator)block).icon[1], (IIcon)((BlockReplicator)block).icon[1], (IIcon)((BlockReplicator)block).icon[1], (boolean)true);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int metadata = world.func_72805_g(x, y, z);
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        BlockReplicatorRenderer.setBrightness((IBlockAccess)world, (int)x, (int)y, (int)z, (Block)block);
        renderer.func_147775_a(block);
        renderer.func_147784_q(block, x, y, z);
        IIcon top = ((BlockReplicator)block).icon[2];
        float f5 = 0.001f;
        renderer.func_147764_f(block, (double)((float)x + f5), (double)((float)y + 1.0f), (double)z, top);
        renderer.func_147798_e(block, (double)((float)x - f5), (double)((float)y + 1.0f), (double)z, top);
        renderer.func_147734_d(block, (double)x, (double)((float)y + 1.0f), (double)((float)z + f5), top);
        renderer.func_147761_c(block, (double)x, (double)((float)y + 1.0f), (double)((float)z - f5), top);
        f5 = 0.0f;
        renderer.func_147764_f(block, (double)((float)x + f5), (double)((float)y + 1.0f), (double)z, top);
        renderer.func_147798_e(block, (double)((float)x - f5), (double)((float)y + 1.0f), (double)z, top);
        renderer.func_147734_d(block, (double)x, (double)((float)y + 1.0f), (double)((float)z + f5), top);
        renderer.func_147761_c(block, (double)x, (double)((float)y + 1.0f), (double)((float)z - f5), top);
        f5 = -0.999f;
        renderer.func_147764_f(block, (double)((float)x + f5), (double)((float)y + 1.0f), (double)z, top);
        renderer.func_147798_e(block, (double)((float)x - f5), (double)((float)y + 1.0f), (double)z, top);
        renderer.func_147734_d(block, (double)x, (double)((float)y + 1.0f), (double)((float)z + f5), top);
        renderer.func_147761_c(block, (double)x, (double)((float)y + 1.0f), (double)((float)z - f5), top);
        renderer.func_147771_a();
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        renderer.func_147775_a(block);
        return true;
    }

    public boolean shouldRender3DInInventory() {
        return true;
    }

    public int getRenderId() {
        return ThaumicExploration.replicatorRenderID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return modelId == ThaumicExploration.replicatorRenderID;
    }
}

