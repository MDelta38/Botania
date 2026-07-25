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
 *  thaumcraft.client.renderers.block.BlockRenderer
 */
package flaxbeard.thaumicexploration.client.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.tile.TileEntityTrashJar;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.block.BlockRenderer;

public class BlockTrashJarRenderer
extends BlockRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileEntityTrashJar(), 0.0, 0.0, 0.0, 0.0f);
        GL11.glEnable((int)32826);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return ThaumicExploration.trashJarRenderID;
    }
}

