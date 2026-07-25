/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.renderers.block.BlockRenderer
 *  thaumcraft.common.blocks.BlockCandle
 *  thaumcraft.common.lib.utils.Utils
 */
package flaxbeard.thaumicexploration.client.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import flaxbeard.thaumicexploration.ThaumicExploration;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.block.BlockRenderer;
import thaumcraft.common.blocks.BlockCandle;
import thaumcraft.common.lib.utils.Utils;

public class BlockFloatyCandleRenderer
extends BlockRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        Color c = new Color(Utils.colors[metadata]);
        float r = (float)c.getRed() / 255.0f;
        float g = (float)c.getGreen() / 255.0f;
        float b = (float)c.getBlue() / 255.0f;
        GL11.glColor3f((float)r, (float)g, (float)b);
        block.func_149676_a(BlockRenderer.W6, 0.0f, BlockRenderer.W6, BlockRenderer.W10, 0.5f, BlockRenderer.W10);
        renderer.func_147775_a(block);
        BlockFloatyCandleRenderer.drawFaces((RenderBlocks)renderer, (Block)block, (IIcon)((BlockCandle)block).icon, (boolean)true);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        block.func_149676_a(0.475f, 0.5f, 0.475f, 0.525f, BlockRenderer.W10, 0.525f);
        renderer.func_147775_a(block);
        BlockFloatyCandleRenderer.drawFaces((RenderBlocks)renderer, (Block)block, (IIcon)((BlockCandle)block).iconStub, (boolean)true);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    public boolean shouldRender3DInInventory() {
        return true;
    }

    public int getRenderId() {
        return ThaumicExploration.floatCandleRenderID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return ThaumicExploration.floatCandleRenderID == modelId;
    }
}

