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
package vazkii.botania.client.render.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.client.render.tile.RenderTileAltar;
import vazkii.botania.common.block.tile.TileAltar;

public class RenderAltar
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)-0.5f, (float)-0.6f, (float)-0.5f);
        GL11.glScalef((float)0.9f, (float)0.9f, (float)0.9f);
        RenderTileAltar.forceMeta = metadata;
        TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileAltar(), 0.0, 0.0, 0.0, 0.0f);
        GL11.glPopMatrix();
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    public int getRenderId() {
        return LibRenderIDs.idAltar;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }
}

