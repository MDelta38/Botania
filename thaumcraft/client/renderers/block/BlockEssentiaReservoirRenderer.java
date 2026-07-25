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
import thaumcraft.common.blocks.BlockEssentiaReservoir;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileEssentiaReservoir;

public class BlockEssentiaReservoirRenderer
extends BlockRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        block.func_149676_a(W2, W2, W2, W14, W14, W14);
        renderer.func_147775_a(block);
        BlockEssentiaReservoirRenderer.drawFaces(renderer, block, ((BlockEssentiaReservoir)block).icon, true);
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileEssentiaReservoir(), 0.0, 0.0, 0.0, 0.0f);
        GL11.glEnable((int)32826);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        block.func_149676_a(W2, W2, W2, W14, W14, W14);
        renderer.func_147775_a(block);
        renderer.func_147784_q(block, x, y, z);
        renderer.func_147771_a();
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        renderer.func_147775_a(block);
        return true;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return ConfigBlocks.blockEssentiaReservoirRI;
    }
}

