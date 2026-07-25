/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.block.BlockRenderer;
import thaumcraft.common.blocks.BlockCustomOreItem;
import thaumcraft.common.blocks.BlockLifter;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileLifter;

public class BlockLifterRenderer
extends BlockRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        renderer.func_147775_a(block);
        BlockLifterRenderer.drawFaces(renderer, block, ((BlockLifter)block).iconBottom, ((BlockLifter)block).iconTop, ((BlockLifter)block).iconSide, ((BlockLifter)block).iconSide, ((BlockLifter)block).iconSide, ((BlockLifter)block).iconSide, false);
        Color c = new Color(BlockCustomOreItem.colors[4]);
        float r = (float)c.getRed() / 255.0f;
        float g = (float)c.getGreen() / 255.0f;
        float b = (float)c.getBlue() / 255.0f;
        GL11.glColor3f((float)r, (float)g, (float)b);
        block.func_149676_a(0.01f, 0.9f, 0.01f, 0.99f, 0.99f, 0.99f);
        renderer.func_147775_a(block);
        BlockLifterRenderer.drawFaces(renderer, block, ((BlockLifter)block).iconGlow, false);
        c = new Color(BlockCustomOreItem.colors[5]);
        r = (float)c.getRed() / 255.0f;
        g = (float)c.getGreen() / 255.0f;
        b = (float)c.getBlue() / 255.0f;
        GL11.glColor3f((float)r, (float)g, (float)b);
        block.func_149676_a(0.01f, 0.1f, 0.01f, 0.99f, 0.9f, 0.99f);
        renderer.func_147775_a(block);
        BlockLifterRenderer.drawFaces(renderer, block, ((BlockLifter)block).iconGlow, false);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int bb = BlockLifterRenderer.setBrightness(world, x, y, z, block);
        int metadata = world.func_72805_g(x, y, z);
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        renderer.func_147775_a(block);
        renderer.func_147784_q(block, x, y, z);
        Tessellator t = Tessellator.field_78398_a;
        t.func_78378_d(BlockCustomOreItem.colors[4]);
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null && te instanceof TileLifter && !((TileLifter)te).gettingPower()) {
            bb = 180;
        }
        t.func_78380_c(bb);
        if (block.func_149646_a(world, x, y + 1, z, 6)) {
            renderer.func_147806_b(block, (double)x, (double)((float)y - 0.01f), (double)z, ((BlockLifter)block).iconGlow);
        }
        t.func_78378_d(0xDD11FF);
        if (block.func_149646_a(world, x + 1, y, z, 6)) {
            renderer.func_147764_f(block, (double)((float)x - 0.01f), (double)y, (double)z, ((BlockLifter)block).iconGlow);
        }
        if (block.func_149646_a(world, x - 1, y, z, 6)) {
            renderer.func_147798_e(block, (double)((float)x + 0.01f), (double)y, (double)z, ((BlockLifter)block).iconGlow);
        }
        if (block.func_149646_a(world, x, y, z + 1, 6)) {
            renderer.func_147734_d(block, (double)x, (double)y, (double)((float)z - 0.01f), ((BlockLifter)block).iconGlow);
        }
        if (block.func_149646_a(world, x, y, z - 1, 6)) {
            renderer.func_147761_c(block, (double)x, (double)y, (double)((float)z + 0.01f), ((BlockLifter)block).iconGlow);
        }
        renderer.func_147771_a();
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        renderer.func_147775_a(block);
        return true;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return ConfigBlocks.blockLifterRI;
    }
}

