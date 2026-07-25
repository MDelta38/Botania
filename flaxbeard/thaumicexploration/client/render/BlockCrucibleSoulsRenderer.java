/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  thaumcraft.client.renderers.block.BlockRenderer
 */
package flaxbeard.thaumicexploration.client.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.block.BlockCrucibleSouls;
import flaxbeard.thaumicexploration.tile.TileEntityCrucibleSouls;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import thaumcraft.client.renderers.block.BlockRenderer;

public class BlockCrucibleSoulsRenderer
extends BlockRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        renderer.func_147775_a(block);
        BlockCrucibleSoulsRenderer.drawFaces((RenderBlocks)renderer, (Block)block, (IIcon)((BlockCrucibleSouls)block).IIcon[2], (IIcon)((BlockCrucibleSouls)block).IIcon[4], (IIcon)((BlockCrucibleSouls)block).IIcon[3], (IIcon)((BlockCrucibleSouls)block).IIcon[3], (IIcon)((BlockCrucibleSouls)block).IIcon[3], (IIcon)((BlockCrucibleSouls)block).IIcon[3], (boolean)true);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int metadata = world.func_72805_g(x, y, z);
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        TileEntityCrucibleSouls te = (TileEntityCrucibleSouls)world.func_147438_o(x, y, z);
        renderer.func_147775_a(block);
        renderer.func_147784_q(block, x, y, z);
        IIcon outerSide = null;
        IIcon innerSide = ((BlockCrucibleSouls)block).IIcon[5];
        IIcon bottom = ((BlockCrucibleSouls)block).IIcon[6];
        outerSide = te.drainTicks > 0 ? ((BlockCrucibleSouls)block).IIcon[7] : ((BlockCrucibleSouls)block).IIcon[3];
        float f5 = 0.123f;
        BlockCrucibleSoulsRenderer.setBrightness((IBlockAccess)world, (int)x, (int)y, (int)z, (Block)block);
        renderer.func_147764_f(block, (double)((float)x - 1.0f + f5), (double)y, (double)z, innerSide);
        renderer.func_147798_e(block, (double)((float)x + 1.0f - f5), (double)y, (double)z, innerSide);
        renderer.func_147734_d(block, (double)x, (double)y, (double)((float)z - 1.0f + f5), innerSide);
        renderer.func_147761_c(block, (double)x, (double)y, (double)((float)z + 1.0f - f5), innerSide);
        renderer.func_147806_b(block, (double)x, (double)((float)y - 1.0f + 0.25f), (double)z, bottom);
        renderer.func_147768_a(block, (double)x, (double)((float)y + 1.0f - 0.75f), (double)z, bottom);
        renderer.func_147764_f(block, (double)x, (double)y, (double)z, outerSide);
        renderer.func_147798_e(block, (double)x, (double)y, (double)z, outerSide);
        renderer.func_147734_d(block, (double)x, (double)y, (double)z, outerSide);
        renderer.func_147761_c(block, (double)x, (double)y, (double)z, outerSide);
        renderer.func_147771_a();
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        renderer.func_147775_a(block);
        return true;
    }

    public boolean shouldRender3DInInventory() {
        return true;
    }

    public int getRenderId() {
        return ThaumicExploration.crucibleSoulsRenderID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return modelId == ThaumicExploration.crucibleSoulsRenderID;
    }
}

