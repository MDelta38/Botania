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
import vazkii.botania.client.render.tile.RenderTileCorporeaIndex;
import vazkii.botania.common.block.tile.corporea.TileCorporeaIndex;

public class RenderCorporeaIndex
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)-0.5f, (float)-0.3f, (float)-0.5f);
        GL11.glScalef((float)1.4f, (float)1.4f, (float)1.4f);
        RenderTileCorporeaIndex.move = false;
        TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileCorporeaIndex(), 0.0, 0.0, 0.0, 0.0f);
        RenderTileCorporeaIndex.move = true;
        GL11.glPopMatrix();
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return LibRenderIDs.idCorporeaIndex;
    }
}

