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
package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.Witchery;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class RenderPitGrass
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        renderer.func_147800_a((Block)Blocks.field_150349_c, metadata, 1.0f);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return renderer.func_147784_q((Block)Blocks.field_150349_c, x, y, z);
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return Witchery.proxy.getPitGrassRenderId();
    }
}

