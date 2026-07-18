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
import vazkii.botania.common.block.tile.mana.TileSpreader;

public class RenderSpreader
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        TileSpreader.staticRedstone = metadata == 1;
        TileSpreader.staticDreamwood = metadata == 2 || metadata == 3;
        TileSpreader.staticUltra = metadata == 3;
        TileSpreader spreader = new TileSpreader();
        spreader.rotationX = -180.0f;
        TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)spreader, 0.0, 0.0, 0.0, 0.0f);
        GL11.glPopMatrix();
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    public int getRenderId() {
        return LibRenderIDs.idSpreader;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }
}

