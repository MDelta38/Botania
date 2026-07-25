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
 *  thaumcraft.client.renderers.block.BlockRenderer
 */
package flaxbeard.thaumicexploration.client.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.tile.TileEntitySoulBrazier;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import thaumcraft.client.renderers.block.BlockRenderer;

public class BlockSoulBrazierRenderer
extends BlockRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileEntitySoulBrazier(), 0.0, 0.0, 0.0, 0.0f);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return modelId == ThaumicExploration.soulBrazierRenderID;
    }

    public int getRenderId() {
        return ThaumicExploration.soulBrazierRenderID;
    }
}

