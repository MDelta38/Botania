/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.common.blocks.BlockMetalDevice
 *  thaumcraft.common.config.ConfigBlocks
 */
package witchinggadgets.client.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import thaumcraft.common.blocks.BlockMetalDevice;
import thaumcraft.common.config.ConfigBlocks;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.common.blocks.tiles.TileEntityCobbleGen;
import witchinggadgets.common.blocks.tiles.TileEntityCuttingTable;
import witchinggadgets.common.blocks.tiles.TileEntityLabelLibrary;
import witchinggadgets.common.blocks.tiles.TileEntitySpinningWheel;

public class BlockRenderWoodenDevice
implements ISimpleBlockRenderingHandler {
    public static int renderID = RenderingRegistry.getNextAvailableRenderId();
    public static IIcon coal;

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        GL11.glPushMatrix();
        try {
            if (metadata == 0) {
                GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)-0.5f, (float)0.0f, (float)-1.0f);
                TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileEntitySpinningWheel(), 0.0, 0.0, 0.0, 0.0f);
            }
            if (metadata == 1) {
                // empty if block
            }
            if (metadata == 2) {
                GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
                TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileEntityCobbleGen(), 0.0, 0.0, 0.0, 0.0f);
            }
            if (metadata == 3) {
                TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileEntityCuttingTable(), 0.0, 0.0, 0.0, 0.0f);
            }
            if (metadata == 4) {
                renderer.func_147782_a(0.0, 0.0, 0.0, 1.0, 0.75, 1.0);
                ClientUtilities.drawStandardBlock(block, metadata, renderer);
                renderer.func_147782_a(0.0, 0.75, 0.0, 1.0, 1.0, 0.1875);
                ClientUtilities.drawStandardBlock(block, metadata, renderer);
                renderer.func_147782_a(0.0, 0.75, 0.8125, 1.0, 1.0, 1.0);
                ClientUtilities.drawStandardBlock(block, metadata, renderer);
                renderer.func_147782_a(0.0, 0.75, 0.1875, 0.1875, 1.0, 0.8125);
                ClientUtilities.drawStandardBlock(block, metadata, renderer);
                renderer.func_147782_a(0.8125, 0.75, 0.1875, 1.0, 1.0, 0.8125);
                ClientUtilities.drawStandardBlock(block, metadata, renderer);
            }
            if (metadata == 5) {
                TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileEntityLabelLibrary(), 0.0, 0.0, 0.0, 0.0f);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        GL11.glEnable((int)32826);
        GL11.glPopMatrix();
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        if (world.func_72805_g(x, y, z) == 1) {
            renderer.func_147757_a(ConfigBlocks.blockWoodenDevice.func_149691_a(0, 0));
            renderer.func_147782_a(0.0, 0.0, 0.0, 1.0, 0.1875, 1.0);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147757_a(ConfigBlocks.blockCosmeticSolid.func_149691_a(0, 7));
            renderer.func_147782_a(0.0, 0.1875, 0.0, 1.0, 0.25, 1.0);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.0, 0.25, 0.0, 1.0, 0.3125, 0.1875);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.0, 0.25, 0.8125, 1.0, 0.3125, 1.0);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.0, 0.25, 0.1875, 0.1875, 0.3125, 0.8125);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.8125, 0.25, 0.1875, 1.0, 0.3125, 0.8125);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147757_a(Blocks.field_150355_j.func_149691_a(0, 0));
            renderer.func_147782_a(0.1875, 0.25, 0.1875, 0.8125, 0.3125, 0.8125);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147757_a(ConfigBlocks.blockCosmeticSolid.func_149691_a(2, 9));
            renderer.func_147782_a(0.0, 0.3125, 0.0, 0.1875, 1.0, 0.1875);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.0, 0.3125, 0.8125, 0.1875, 1.0, 1.0);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.8125, 0.3125, 0.8125, 1.0, 1.0, 1.0);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.8125, 0.3125, 0.0, 1.0, 1.0, 0.1875);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147757_a(((BlockMetalDevice)ConfigBlocks.blockMetalDevice).icon[8]);
            renderer.func_147782_a(0.125, 0.25, 0.0, 0.125, 0.875, 1.0);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.875, 0.25, 0.0, 0.875, 0.875, 1.0);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.0, 0.25, 0.125, 1.0, 0.875, 0.125);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.0, 0.25, 0.875, 1.0, 0.875, 0.875);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.0, 0.9375, 0.0, 1.0, 0.9375, 1.0);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147771_a();
        }
        if (world.func_72805_g(x, y, z) == 4) {
            renderer.func_147782_a(0.0, 0.0, 0.0, 1.0, 0.75, 1.0);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.0, 0.75, 0.0, 1.0, 1.0, 0.1875);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.0, 0.75, 0.8125, 1.0, 1.0, 1.0);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.0, 0.75, 0.1875, 0.1875, 1.0, 0.8125);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147782_a(0.8125, 0.75, 0.1875, 1.0, 1.0, 0.8125);
            renderer.func_147784_q(block, x, y, z);
            renderer.func_147757_a(coal);
            this.renderPartialCoal(renderer, block, x, y, z, 3, 5, 3, 6, 0.125f);
            this.renderPartialCoal(renderer, block, x, y, z, 5, 7, 3, 6, 0.1875f);
            this.renderPartialCoal(renderer, block, x, y, z, 7, 9, 3, 6, 0.15625f);
            this.renderPartialCoal(renderer, block, x, y, z, 9, 12, 3, 7, 0.125f);
            this.renderPartialCoal(renderer, block, x, y, z, 12, 13, 3, 6, 0.09375f);
            this.renderPartialCoal(renderer, block, x, y, z, 3, 4, 6, 7, 0.15625f);
            this.renderPartialCoal(renderer, block, x, y, z, 4, 6, 6, 7, 0.1875f);
            this.renderPartialCoal(renderer, block, x, y, z, 6, 7, 6, 7, 0.15625f);
            this.renderPartialCoal(renderer, block, x, y, z, 7, 9, 6, 7, 0.125f);
            this.renderPartialCoal(renderer, block, x, y, z, 12, 13, 6, 9, 0.15625f);
            this.renderPartialCoal(renderer, block, x, y, z, 3, 4, 7, 9, 0.15625f);
            this.renderPartialCoal(renderer, block, x, y, z, 4, 6, 7, 9, 0.125f);
            this.renderPartialCoal(renderer, block, x, y, z, 6, 9, 7, 8, 0.09375f);
            this.renderPartialCoal(renderer, block, x, y, z, 9, 10, 7, 9, 0.1875f);
            this.renderPartialCoal(renderer, block, x, y, z, 10, 12, 7, 8, 0.125f);
            this.renderPartialCoal(renderer, block, x, y, z, 6, 9, 8, 10, 0.15625f);
            this.renderPartialCoal(renderer, block, x, y, z, 10, 12, 8, 9, 0.15625f);
            this.renderPartialCoal(renderer, block, x, y, z, 3, 6, 9, 11, 0.09375f);
            this.renderPartialCoal(renderer, block, x, y, z, 9, 13, 9, 10, 0.09375f);
            this.renderPartialCoal(renderer, block, x, y, z, 6, 8, 10, 11, 0.125f);
            this.renderPartialCoal(renderer, block, x, y, z, 8, 10, 10, 11, 0.1875f);
            this.renderPartialCoal(renderer, block, x, y, z, 10, 13, 10, 11, 0.125f);
            this.renderPartialCoal(renderer, block, x, y, z, 3, 4, 11, 13, 0.1875f);
            this.renderPartialCoal(renderer, block, x, y, z, 4, 6, 11, 12, 0.15625f);
            this.renderPartialCoal(renderer, block, x, y, z, 6, 9, 11, 13, 0.125f);
            this.renderPartialCoal(renderer, block, x, y, z, 9, 11, 11, 13, 0.15625f);
            this.renderPartialCoal(renderer, block, x, y, z, 11, 13, 11, 13, 0.09375f);
            this.renderPartialCoal(renderer, block, x, y, z, 4, 6, 12, 13, 0.09375f);
            renderer.func_147771_a();
            return true;
        }
        return false;
    }

    void renderPartialCoal(RenderBlocks renderer, Block b, int x, int y, int z, int xMin, int xMax, int zMin, int zMax, float yF) {
        renderer.func_147782_a((double)((float)xMin / 16.0f), 0.75, (double)((float)zMin / 16.0f), (double)((float)xMax / 16.0f), 0.75 + (double)yF, (double)((float)zMax / 16.0f));
        renderer.func_147784_q(b, x, y, z);
    }

    public boolean shouldRender3DInInventory(int modelID) {
        return true;
    }

    public int getRenderId() {
        return renderID;
    }
}

