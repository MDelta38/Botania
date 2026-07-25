/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.renderers.block.BlockRenderer
 */
package com.kentington.thaumichorizons.client.renderer.block;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.blocks.BlockSoulJar;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.block.BlockRenderer;

public class BlockJarTHRenderer
extends BlockRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
        IIcon i1 = ((BlockSoulJar)block).iconJarTop;
        IIcon i2 = ((BlockSoulJar)block).iconJarSide;
        block.func_149676_a(W3, 0.0f, W3, W13, W12, W13);
        renderer.func_147775_a(block);
        BlockJarTHRenderer.drawFaces((RenderBlocks)renderer, (Block)block, (IIcon)((BlockSoulJar)block).iconJarBottom, (IIcon)i1, (IIcon)i2, (IIcon)i2, (IIcon)i2, (IIcon)i2, (boolean)true);
        block.func_149676_a(W5, W12, W5, W11, W14, W11);
        renderer.func_147775_a(block);
        BlockJarTHRenderer.drawFaces((RenderBlocks)renderer, (Block)block, (IIcon)((BlockSoulJar)block).iconJarBottom, (IIcon)i1, (IIcon)i2, (IIcon)i2, (IIcon)i2, (IIcon)i2, (boolean)true);
        GL11.glPopMatrix();
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int bb = BlockJarTHRenderer.setBrightness((IBlockAccess)world, (int)x, (int)y, (int)z, (Block)block);
        int metadata = world.func_72805_g(x, y, z);
        block.func_149676_a(W3, 0.0f, W3, W13, W12, W13);
        renderer.func_147775_a(block);
        renderer.func_147784_q(block, x, y, z);
        block.func_149676_a(W5, W12, W5, W11, W14, W11);
        renderer.func_147775_a(block);
        renderer.func_147784_q(block, x, y, z);
        renderer.func_147771_a();
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        renderer.func_147775_a(block);
        return true;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return ThaumicHorizons.blockJarRI;
    }
}

