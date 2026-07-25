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
package thaumic.tinkerer.client.render.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import thaumic.tinkerer.client.lib.LibRenderIDs;
import thaumic.tinkerer.client.render.tile.RenderTileMagnet;
import thaumic.tinkerer.common.block.tile.TileMagnet;

public class RenderMagnet
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        RenderTileMagnet.mob = metadata == 1;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileMagnet(), 0.0, 0.0, 0.0, 0.0f);
        GL11.glPopMatrix();
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return LibRenderIDs.idMagnet;
    }
}

