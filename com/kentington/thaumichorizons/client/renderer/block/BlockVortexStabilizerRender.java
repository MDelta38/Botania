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
 *  thaumcraft.client.renderers.block.BlockRenderer
 */
package com.kentington.thaumichorizons.client.renderer.block;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileVortexStabilizer;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.block.BlockRenderer;

public class BlockVortexStabilizerRender
extends BlockRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        TileVortexStabilizer tc = new TileVortexStabilizer();
        tc.field_145847_g = metadata;
        TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)tc, 0.0, 0.0, 0.0, 0.0f);
        GL11.glEnable((int)32826);
        GL11.glPopMatrix();
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return ThaumicHorizons.blockVortexStabilizerRI;
    }
}

