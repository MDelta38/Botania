/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.block.BlockRenderer;
import thaumcraft.common.blocks.BlockCustomOre;
import thaumcraft.common.blocks.BlockCustomOreItem;
import thaumcraft.common.config.ConfigBlocks;

public class BlockCustomOreRenderer
extends BlockRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        renderer.func_147775_a(block);
        if (metadata == 0) {
            BlockCustomOreRenderer.drawFaces(renderer, block, ((BlockCustomOre)block).icon[0], false);
        } else if (metadata == 7) {
            BlockCustomOreRenderer.drawFaces(renderer, block, ((BlockCustomOre)block).icon[3], false);
        } else if (metadata < 7) {
            BlockCustomOreRenderer.drawFaces(renderer, block, ((BlockCustomOre)block).icon[1], false);
            Color c = new Color(BlockCustomOreItem.colors[metadata]);
            float r = (float)c.getRed() / 255.0f;
            float g = (float)c.getGreen() / 255.0f;
            float b = (float)c.getBlue() / 255.0f;
            GL11.glColor3f((float)r, (float)g, (float)b);
            block.func_149676_a(0.005f, 0.005f, 0.005f, 0.995f, 0.995f, 0.995f);
            renderer.func_147775_a(block);
            BlockCustomOreRenderer.drawFaces(renderer, block, ((BlockCustomOre)block).icon[2], false);
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        }
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int bb = BlockCustomOreRenderer.setBrightness(world, x, y, z, block);
        int metadata = world.func_72805_g(x, y, z);
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        renderer.func_147775_a(block);
        renderer.func_147784_q(block, x, y, z);
        if (metadata != 0 && metadata != 7 && metadata < 7) {
            Tessellator t = Tessellator.field_78398_a;
            t.func_78378_d(BlockCustomOreItem.colors[metadata]);
            t.func_78380_c(Math.max(bb, 160));
            BlockCustomOreRenderer.renderAllSides(world, x, y, z, block, renderer, ((BlockCustomOre)block).icon[2], false);
            if (Minecraft.func_71410_x().field_71474_y.field_151443_J > 1) {
                block.func_149676_a(0.005f, 0.005f, 0.005f, 0.995f, 0.995f, 0.995f);
                renderer.func_147775_a(block);
                t.func_78380_c(bb);
                BlockCustomOreRenderer.renderAllSides(world, x, y, z, block, renderer, Blocks.field_150348_b.func_149691_a(0, 0), false);
            }
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
        return ConfigBlocks.blockCustomOreRI;
    }
}

