/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.renderer;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockStockade;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class RenderStockade
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
        this.drawPost(0.3, 0.3, 0.7, 0.7, renderer, block, metadata, false, false);
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        BlockStockade blockStockade = (BlockStockade)block;
        boolean flag = true;
        boolean drawLR = false;
        boolean drawUD = false;
        if (blockStockade.canConnectFenceTo(world, x - 1, y, z) || blockStockade.canConnectFenceTo(world, x + 1, y, z)) {
            drawLR = true;
        }
        if (blockStockade.canConnectFenceTo(world, x, y, z - 1) || blockStockade.canConnectFenceTo(world, x, y, z + 1)) {
            drawUD = true;
        }
        boolean oneAbove = blockStockade.canConnectFenceTo(world, x, y + 1, z);
        if (!drawLR && !drawUD) {
            this.drawPost(0.3, 0.3, 0.7, 0.7, x, y, z, renderer, blockStockade, oneAbove);
        }
        boolean diagonal = false;
        if (drawLR && drawUD && diagonal > true) {
            this.drawPost(0.05, 0.05, 0.45, 0.45, x, y, z, renderer, blockStockade, oneAbove);
            this.drawPost(0.55, 0.05, 0.95, 0.45, x, y, z, renderer, blockStockade, oneAbove);
            this.drawPost(0.05, 0.55, 0.45, 0.95, x, y, z, renderer, blockStockade, oneAbove);
            this.drawPost(0.55, 0.55, 0.95, 0.95, x, y, z, renderer, blockStockade, oneAbove);
            flag = true;
        } else if (drawLR && drawUD) {
            this.drawPost(0.05, 0.3, 0.45, 0.7, x, y, z, renderer, blockStockade, oneAbove, true);
            this.drawPost(0.55, 0.3, 0.95, 0.7, x, y, z, renderer, blockStockade, oneAbove, false);
            this.drawPost(0.3, 0.05, 0.7, 0.45, x, y, z, renderer, blockStockade, oneAbove, true);
            this.drawPost(0.3, 0.55, 0.7, 0.95, x, y, z, renderer, blockStockade, oneAbove, false);
            flag = true;
        } else {
            if (drawLR) {
                this.drawPost(0.05, 0.3, 0.45, 0.7, x, y, z, renderer, blockStockade, oneAbove);
                this.drawPost(0.55, 0.3, 0.95, 0.7, x, y, z, renderer, blockStockade, oneAbove);
                flag = true;
            }
            if (drawUD) {
                this.drawPost(0.3, 0.05, 0.7, 0.45, x, y, z, renderer, blockStockade, oneAbove);
                this.drawPost(0.3, 0.55, 0.7, 0.95, x, y, z, renderer, blockStockade, oneAbove);
                flag = true;
            }
        }
        blockStockade.func_149719_a(world, x, y, z);
        return flag;
    }

    private void drawPost(double xMin, double zMin, double xMax, double zMax, int x, int y, int z, RenderBlocks renderer, Block block, boolean oneAbove) {
        this.drawPost(xMin, zMin, xMax, zMax, x, y, z, renderer, block, oneAbove, false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void drawPost(double xMin, double zMin, double xMax, double zMax, int x, int y, int z, RenderBlocks renderer, Block block, boolean oneAbove, boolean extra) {
        if (!oneAbove) {
            double startY = extra ? 0.6 : 0.5;
            renderer.func_147782_a(xMin, 0.0, zMin, xMax, startY, zMax);
            renderer.func_147784_q(block, x, y, z);
            try {
                ((BlockStockade)block).setTipTexture(true);
                double dx = 0.04;
                double dy = 0.084;
                for (int i = 0; i < 4; ++i) {
                    double reduce = (double)(i + 1) * dx;
                    renderer.func_147782_a(xMin + reduce, startY + (double)i * dy, zMin + reduce, xMax - reduce, startY + (double)(i + 1) * dy, zMax - reduce);
                    renderer.func_147784_q(block, x, y, z);
                }
            }
            finally {
                ((BlockStockade)block).setTipTexture(false);
            }
        } else {
            renderer.func_147782_a(xMin, 0.0, zMin, xMax, 1.0, zMax);
            renderer.func_147784_q(block, x, y, z);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void drawPost(double xMin, double zMin, double xMax, double zMax, RenderBlocks renderer, Block block, int meta, boolean oneAbove, boolean extra) {
        if (!oneAbove) {
            double startY = extra ? 0.6 : 0.5;
            renderer.func_147782_a(xMin, 0.0, zMin, xMax, startY, zMax);
            RenderStockade.renderStandardInvBlock(renderer, block, meta);
            try {
                ((BlockStockade)block).setTipTexture(true);
                double dx = 0.04;
                double dy = 0.084;
                for (int i = 0; i < 4; ++i) {
                    double reduce = (double)(i + 1) * dx;
                    renderer.func_147782_a(xMin + reduce, startY + (double)i * dy, zMin + reduce, xMax - reduce, startY + (double)(i + 1) * dy, zMax - reduce);
                    RenderStockade.renderStandardInvBlock(renderer, block, meta);
                }
            }
            finally {
                ((BlockStockade)block).setTipTexture(false);
            }
        } else {
            renderer.func_147782_a(xMin, 0.0, zMin, xMax, 1.0, zMax);
            RenderStockade.renderStandardInvBlock(renderer, block, meta);
        }
    }

    public static void renderStandardInvBlock(RenderBlocks renderblocks, Block block, int meta) {
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
        renderblocks.func_147768_a(block, 0.0, 0.0, 0.0, RenderStockade.getIcon(block.func_149691_a(0, meta)));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
        renderblocks.func_147806_b(block, 0.0, 0.0, 0.0, RenderStockade.getIcon(block.func_149691_a(1, meta)));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        renderblocks.func_147761_c(block, 0.0, 0.0, 0.0, RenderStockade.getIcon(block.func_149691_a(2, meta)));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        renderblocks.func_147734_d(block, 0.0, 0.0, 0.0, RenderStockade.getIcon(block.func_149691_a(3, meta)));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
        renderblocks.func_147798_e(block, 0.0, 0.0, 0.0, RenderStockade.getIcon(block.func_149691_a(4, meta)));
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
        renderblocks.func_147764_f(block, 0.0, 0.0, 0.0, RenderStockade.getIcon(block.func_149691_a(5, meta)));
        tessellator.func_78381_a();
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
    }

    private static IIcon getIcon(IIcon icon) {
        if (icon != null) {
            return icon;
        }
        return ((TextureMap)Minecraft.func_71410_x().func_110434_K().func_110581_b(TextureMap.field_110575_b)).func_110572_b("missingno");
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    public int getRenderId() {
        return Witchery.proxy.getStockageRenderId();
    }
}

