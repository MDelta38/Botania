/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import java.awt.Color;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.block.BlockRenderer;
import thaumcraft.common.blocks.BlockCandle;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.utils.Utils;

public class BlockCandleRenderer
extends BlockRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        Color c = new Color(Utils.colors[metadata]);
        float r = (float)c.getRed() / 255.0f;
        float g = (float)c.getGreen() / 255.0f;
        float b = (float)c.getBlue() / 255.0f;
        GL11.glColor3f((float)r, (float)g, (float)b);
        block.func_149676_a(W6, 0.0f, W6, W10, 0.5f, W10);
        renderer.func_147775_a(block);
        BlockCandleRenderer.drawFaces(renderer, block, ((BlockCandle)block).icon, true);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        block.func_149676_a(0.475f, 0.5f, 0.475f, 0.525f, W10, 0.525f);
        renderer.func_147775_a(block);
        BlockCandleRenderer.drawFaces(renderer, block, ((BlockCandle)block).iconStub, true);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        boolean type = false;
        block.func_149676_a(W6, 0.0f, W6, W10, 0.5f, W10);
        renderer.func_147775_a(block);
        renderer.func_147784_q(block, x, y, z);
        Random rr = new Random(x + y * z);
        int q = 1 + rr.nextInt(5);
        for (int a = 0; a < q; ++a) {
            boolean side = rr.nextBoolean();
            int loc = 2 + rr.nextInt(2);
            if (a % 2 == 0) {
                block.func_149676_a(W5 + W1 * (float)loc, 0.0f, side ? W5 : W10, W6 + W1 * (float)loc, W1 * (float)(1 + rr.nextInt(3)), side ? W6 : W11);
                renderer.func_147775_a(block);
                renderer.func_147784_q(block, x, y, z);
                continue;
            }
            block.func_149676_a(side ? W5 : W10, 0.0f, W5 + W1 * (float)loc, side ? W6 : W11, W1 * (float)(1 + rr.nextInt(3)), W6 + W1 * (float)loc);
            renderer.func_147775_a(block);
            renderer.func_147784_q(block, x, y, z);
        }
        renderer.field_147840_d = ((BlockCandle)block).iconStub;
        block.func_149676_a(0.475f, 0.5f, 0.475f, 0.525f, W10, 0.525f);
        renderer.func_147775_a(block);
        renderer.func_147736_d(block, x, y, z, 1.0f, 1.0f, 1.0f);
        renderer.func_147771_a();
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        renderer.func_147775_a(block);
        return true;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return ConfigBlocks.blockCandleRI;
    }
}

