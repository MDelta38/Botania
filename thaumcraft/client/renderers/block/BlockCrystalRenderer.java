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
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileCrystal;
import thaumcraft.common.tiles.TileEldritchCrystal;

public class BlockCrystalRenderer
extends BlockRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        if (metadata <= 6) {
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
            TileCrystal tc = new TileCrystal();
            tc.field_145847_g = metadata;
            TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)tc, 0.0, 0.0, 0.0, 0.0f);
            GL11.glEnable((int)32826);
        }
        if (metadata == 7) {
            GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
            TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileEldritchCrystal(), 0.0, 0.0, 0.0, 0.0f);
            GL11.glEnable((int)32826);
        }
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return ConfigBlocks.blockCrystalRI;
    }
}

