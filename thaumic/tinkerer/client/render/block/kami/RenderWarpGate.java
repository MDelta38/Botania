/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.renderers.block.BlockRenderer
 */
package thaumic.tinkerer.client.render.block.kami;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.block.BlockRenderer;
import thaumic.tinkerer.client.lib.LibRenderIDs;
import thaumic.tinkerer.common.block.kami.BlockWarpGate;

public class RenderWarpGate
extends BlockRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        IIcon topIcon = BlockWarpGate.icons[0];
        IIcon sideIcon = BlockWarpGate.icons[1];
        RenderWarpGate.drawFaces((RenderBlocks)renderer, (Block)block, (IIcon)sideIcon, (IIcon)topIcon, (IIcon)sideIcon, (IIcon)sideIcon, (IIcon)sideIcon, (IIcon)sideIcon, (boolean)false);
        GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        Tessellator t = Tessellator.field_78398_a;
        t.func_78382_b();
        t.func_78380_c(255);
        t.func_78375_b(0.0f, 1.0f, 0.0f);
        renderer.func_147806_b(block, 0.0, 0.0, 0.0, BlockWarpGate.icons[2]);
        t.func_78381_a();
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        renderer.func_147775_a(block);
        renderer.func_147784_q(block, x, y, z);
        Tessellator t = Tessellator.field_78398_a;
        t.func_78378_d(0xFFFFFF);
        t.func_78380_c(255);
        renderer.func_147806_b(block, (double)x, (double)y, (double)z, BlockWarpGate.icons[2]);
        renderer.func_147771_a();
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        renderer.func_147784_q(block, x, y, z);
        return true;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return LibRenderIDs.idWarpGate;
    }
}

