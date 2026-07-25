/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.block.BlockRenderer;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.tiles.TileArcaneWorkbench;
import thaumcraft.common.tiles.TileDeconstructionTable;
import thaumcraft.common.tiles.TileResearchTable;
import thaumcraft.common.tiles.TileTable;

public class BlockTableRenderer
extends BlockRenderer
implements ISimpleBlockRenderingHandler {
    TileResearchTable trt = new TileResearchTable();

    public BlockTableRenderer() {
        this.trt.contents[0] = new ItemStack(ConfigItems.itemInkwell);
        this.trt.contents[1] = new ItemStack(ConfigItems.itemResearchNotes);
    }

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        if (metadata == 0) {
            TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileTable(), 0.0, 0.0, 0.0, 0.0f);
        } else if (metadata == 14) {
            TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileDeconstructionTable(), 0.0, 0.0, 0.0, 0.0f);
        } else if (metadata == 15) {
            TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileArcaneWorkbench(), 0.0, 0.0, 0.0, 0.0f);
        } else {
            GL11.glTranslatef((float)-0.5f, (float)0.0f, (float)0.0f);
            TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)this.trt, 0.0, 0.0, 0.0, 0.0f);
        }
        GL11.glEnable((int)32826);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return ConfigBlocks.blockTableRI;
    }
}

