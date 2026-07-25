/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class BlockRenderer {
    public static float W1 = 0.0625f;
    public static float W2 = 0.125f;
    public static float W3 = 0.1875f;
    public static float W4 = 0.25f;
    public static float W5 = 0.3125f;
    public static float W6 = 0.375f;
    public static float W7 = 0.4375f;
    public static float W8 = 0.5f;
    public static float W9 = 0.5625f;
    public static float W10 = 0.625f;
    public static float W11 = 0.6875f;
    public static float W12 = 0.75f;
    public static float W13 = 0.8125f;
    public static float W14 = 0.875f;
    public static float W15 = 0.9375f;

    public static void drawFaces(RenderBlocks renderblocks, Block block, IIcon icon, boolean st) {
        BlockRenderer.drawFaces(renderblocks, block, icon, icon, icon, icon, icon, icon, st);
    }

    public static void drawFaces(RenderBlocks renderblocks, Block block, IIcon i1, IIcon i2, IIcon i3, IIcon i4, IIcon i5, IIcon i6, boolean solidtop) {
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
        renderblocks.func_147768_a(block, 0.0, 0.0, 0.0, i1);
        tessellator.func_78381_a();
        if (solidtop) {
            GL11.glDisable((int)3008);
        }
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
        renderblocks.func_147806_b(block, 0.0, 0.0, 0.0, i2);
        tessellator.func_78381_a();
        if (solidtop) {
            GL11.glEnable((int)3008);
        }
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        renderblocks.func_147798_e(block, 0.0, 0.0, 0.0, i3);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        renderblocks.func_147764_f(block, 0.0, 0.0, 0.0, i4);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
        renderblocks.func_147761_c(block, 0.0, 0.0, 0.0, i5);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
        renderblocks.func_147734_d(block, 0.0, 0.0, 0.0, i6);
        tessellator.func_78381_a();
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
    }

    public static int setBrightness(IBlockAccess blockAccess, int i, int j, int k, Block block) {
        Tessellator tessellator = Tessellator.field_78398_a;
        int mb = block.func_149677_c(blockAccess, i, j, k);
        tessellator.func_78380_c(mb);
        float f = 1.0f;
        int l = block.func_149720_d(blockAccess, i, j, k);
        float f1 = (float)(l >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(l >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(l & 0xFF) / 255.0f;
        if (EntityRenderer.field_78517_a) {
            float f6 = (f1 * 30.0f + f2 * 59.0f + f3 * 11.0f) / 100.0f;
            float f4 = (f1 * 30.0f + f2 * 70.0f) / 100.0f;
            float f7 = (f1 * 30.0f + f3 * 70.0f) / 100.0f;
            f1 = f6;
            f2 = f4;
            f3 = f7;
        }
        tessellator.func_78386_a(f * f1, f * f2, f * f3);
        return mb;
    }

    protected static void renderAllSides(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer, IIcon tex) {
        BlockRenderer.renderAllSides(world, x, y, z, block, renderer, tex, true);
    }

    protected static void renderAllSides(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer, IIcon tex, boolean allsides) {
        if (allsides || block.func_149646_a(world, x + 1, y, z, 6)) {
            renderer.func_147764_f(block, (double)x, (double)y, (double)z, tex);
        }
        if (allsides || block.func_149646_a(world, x - 1, y, z, 6)) {
            renderer.func_147798_e(block, (double)x, (double)y, (double)z, tex);
        }
        if (allsides || block.func_149646_a(world, x, y, z + 1, 6)) {
            renderer.func_147734_d(block, (double)x, (double)y, (double)z, tex);
        }
        if (allsides || block.func_149646_a(world, x, y, z - 1, 6)) {
            renderer.func_147761_c(block, (double)x, (double)y, (double)z, tex);
        }
        if (allsides || block.func_149646_a(world, x, y + 1, z, 6)) {
            renderer.func_147806_b(block, (double)x, (double)y, (double)z, tex);
        }
        if (allsides || block.func_149646_a(world, x, y - 1, z, 6)) {
            renderer.func_147768_a(block, (double)x, (double)y, (double)z, tex);
        }
    }

    protected static void renderAllSides(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer, boolean allsides) {
        if (allsides || block.func_149646_a(world, x + 1, y, z, 6)) {
            renderer.func_147764_f(block, (double)x, (double)y, (double)z, block.func_149673_e(world, x, y, z, 5));
        }
        if (allsides || block.func_149646_a(world, x - 1, y, z, 6)) {
            renderer.func_147798_e(block, (double)x, (double)y, (double)z, block.func_149673_e(world, x, y, z, 4));
        }
        if (allsides || block.func_149646_a(world, x, y, z + 1, 6)) {
            renderer.func_147734_d(block, (double)x, (double)y, (double)z, block.func_149673_e(world, x, y, z, 3));
        }
        if (allsides || block.func_149646_a(world, x, y, z - 1, 6)) {
            renderer.func_147761_c(block, (double)x, (double)y, (double)z, block.func_149673_e(world, x, y, z, 2));
        }
        if (allsides || block.func_149646_a(world, x, y + 1, z, 6)) {
            renderer.func_147806_b(block, (double)x, (double)y, (double)z, block.func_149673_e(world, x, y, z, 1));
        }
        if (allsides || block.func_149646_a(world, x, y - 1, z, 6)) {
            renderer.func_147768_a(block, (double)x, (double)y, (double)z, block.func_149673_e(world, x, y, z, 0));
        }
    }

    protected static void renderAllSidesInverted(IBlockAccess world, int x, int y, int z, Block block, RenderBlocks renderer, IIcon tex, boolean allsides) {
        if (allsides || !block.func_149646_a(world, x - 1, y, z, 6)) {
            renderer.func_147764_f(block, (double)(x - 1), (double)y, (double)z, tex);
        }
        if (allsides || !block.func_149646_a(world, x + 1, y, z, 6)) {
            renderer.func_147798_e(block, (double)(x + 1), (double)y, (double)z, tex);
        }
        if (allsides || !block.func_149646_a(world, x, y, z - 1, 6)) {
            renderer.func_147734_d(block, (double)x, (double)y, (double)(z - 1), tex);
        }
        if (allsides || !block.func_149646_a(world, x, y, z + 1, 6)) {
            renderer.func_147761_c(block, (double)x, (double)y, (double)(z + 1), tex);
        }
        if (allsides || !block.func_149646_a(world, x, y - 1, z, 6)) {
            renderer.func_147806_b(block, (double)x, (double)(y - 1), (double)z, tex);
        }
        if (allsides || !block.func_149646_a(world, x, y + 1, z, 6)) {
            renderer.func_147768_a(block, (double)x, (double)(y + 1), (double)z, tex);
        }
    }

    protected static void renderAllSides(int x, int y, int z, Block block, RenderBlocks renderer, IIcon tex) {
        renderer.func_147764_f(block, (double)(x - 1), (double)y, (double)z, tex);
        renderer.func_147798_e(block, (double)(x + 1), (double)y, (double)z, tex);
        renderer.func_147734_d(block, (double)x, (double)y, (double)(z - 1), tex);
        renderer.func_147761_c(block, (double)x, (double)y, (double)(z + 1), tex);
        renderer.func_147806_b(block, (double)x, (double)(y - 1), (double)z, tex);
        renderer.func_147768_a(block, (double)x, (double)(y + 1), (double)z, tex);
    }
}

