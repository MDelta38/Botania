/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.block.BlockRenderer;
import thaumcraft.common.blocks.BlockJar;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileJarBrain;

public class BlockJarRenderer
extends BlockRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        if (metadata == 1) {
            GL11.glPushMatrix();
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
            TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileJarBrain(), 0.0, 0.0, 0.0, 0.0f);
            GL11.glEnable((int)32826);
            GL11.glPopMatrix();
        }
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(TextureMap.field_110575_b);
        IIcon i1 = ((BlockJar)block).iconJarTop;
        IIcon i2 = ((BlockJar)block).iconJarSide;
        if (metadata == 3) {
            i1 = ((BlockJar)block).iconJarTopVoid;
            i2 = ((BlockJar)block).iconJarSideVoid;
        }
        block.func_149676_a(W3, 0.0f, W3, W13, W12, W13);
        renderer.func_147775_a(block);
        BlockJarRenderer.drawFaces(renderer, block, ((BlockJar)block).iconJarBottom, i1, i2, i2, i2, i2, true);
        block.func_149676_a(W5, W12, W5, W11, W14, W11);
        renderer.func_147775_a(block);
        BlockJarRenderer.drawFaces(renderer, block, ((BlockJar)block).iconJarBottom, i1, i2, i2, i2, i2, true);
        GL11.glPopMatrix();
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        int bb = BlockJarRenderer.setBrightness(world, x, y, z, block);
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
        return ConfigBlocks.blockJarRI;
    }
}

