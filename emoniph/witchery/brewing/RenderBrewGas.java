/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 */
package com.emoniph.witchery.brewing;

import com.emoniph.witchery.Witchery;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class RenderBrewGas
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        float f11;
        float f10;
        float f9;
        Tessellator tessellator = Tessellator.field_78398_a;
        int l = block.func_149720_d(world, x, y, z);
        float red = (float)(l >> 16 & 0xFF) / 255.0f;
        float green = (float)(l >> 8 & 0xFF) / 255.0f;
        float blue = (float)(l & 0xFF) / 255.0f;
        boolean flag = block.func_149646_a(world, x, y + 1, z, 1);
        boolean flag1 = block.func_149646_a(world, x, y - 1, z, 0);
        boolean[] aboolean = new boolean[]{block.func_149646_a(world, x, y, z - 1, 2), block.func_149646_a(world, x, y, z + 1, 3), block.func_149646_a(world, x - 1, y, z, 4), block.func_149646_a(world, x + 1, y, z, 5)};
        float opacityInner = 0.2f;
        float opacityOuter = 0.4f;
        renderer.field_147837_f = true;
        if (!(renderer.field_147837_f || flag || flag1 || aboolean[0] || aboolean[1] || aboolean[2] || aboolean[3])) {
            return false;
        }
        boolean flag2 = false;
        float f3 = 0.5f;
        float f4 = 1.0f;
        float f5 = 0.8f;
        float f6 = 0.6f;
        double d0 = 0.0;
        double d1 = 1.0;
        Material material = block.func_149688_o();
        int i1 = world.func_72805_g(x, y, z);
        double d2 = 1.0;
        double d3 = 1.0;
        double d4 = 1.0;
        double d5 = 1.0;
        double d6 = 0.001f;
        if (renderer.field_147837_f || flag) {
            double d20;
            double d12;
            double d18;
            double d10;
            double d16;
            double d8;
            double d14;
            double d7;
            flag2 = true;
            IIcon iicon = renderer.func_147787_a(block, 1, i1);
            float f7 = 0.0f;
            if (f7 > -999.0f) {
                iicon = renderer.func_147787_a(block, 2, i1);
            }
            d2 -= d6;
            d3 -= d6;
            d4 -= d6;
            d5 -= d6;
            if (f7 < -999.0f) {
                d7 = iicon.func_94214_a(0.0);
                d14 = iicon.func_94207_b(0.0);
                d8 = d7;
                d16 = iicon.func_94207_b(16.0);
                d10 = iicon.func_94214_a(16.0);
                d18 = d16;
                d12 = d10;
                d20 = d14;
            } else {
                f9 = MathHelper.func_76126_a((float)f7) * 0.25f;
                f10 = MathHelper.func_76134_b((float)f7) * 0.25f;
                f11 = 8.0f;
                d7 = iicon.func_94214_a((double)(8.0f + (-f10 - f9) * 16.0f));
                d14 = iicon.func_94207_b((double)(8.0f + (-f10 + f9) * 16.0f));
                d8 = iicon.func_94214_a((double)(8.0f + (-f10 + f9) * 16.0f));
                d16 = iicon.func_94207_b((double)(8.0f + (f10 + f9) * 16.0f));
                d10 = iicon.func_94214_a((double)(8.0f + (f10 + f9) * 16.0f));
                d18 = iicon.func_94207_b((double)(8.0f + (f10 - f9) * 16.0f));
                d12 = iicon.func_94214_a((double)(8.0f + (f10 - f9) * 16.0f));
                d20 = iicon.func_94207_b((double)(8.0f + (-f10 - f9) * 16.0f));
            }
            tessellator.func_78380_c(block.func_149677_c(world, x, y, z));
            tessellator.func_78369_a(f4 * red, f4 * green, f4 * blue, flag ? opacityOuter : opacityInner);
            tessellator.func_78374_a((double)(x + 0), (double)y + d2, (double)(z + 0), d7, d14);
            tessellator.func_78374_a((double)(x + 0), (double)y + d3, (double)(z + 1), d8, d16);
            tessellator.func_78374_a((double)(x + 1), (double)y + d4, (double)(z + 1), d10, d18);
            tessellator.func_78374_a((double)(x + 1), (double)y + d5, (double)(z + 0), d12, d20);
            tessellator.func_78374_a((double)(x + 0), (double)y + d2, (double)(z + 0), d7, d14);
            tessellator.func_78374_a((double)(x + 1), (double)y + d5, (double)(z + 0), d12, d20);
            tessellator.func_78374_a((double)(x + 1), (double)y + d4, (double)(z + 1), d10, d18);
            tessellator.func_78374_a((double)(x + 0), (double)y + d3, (double)(z + 1), d8, d16);
        }
        if (renderer.field_147837_f || flag1) {
            tessellator.func_78380_c(block.func_149677_c(world, x, y - 1, z));
            tessellator.func_78369_a(f4 * red, f4 * green, f4 * blue, flag1 ? opacityOuter : opacityInner);
            renderer.func_147768_a(block, (double)x, (double)y + d6, (double)z, renderer.func_147777_a(block, 0));
            flag2 = true;
        }
        for (int k1 = 0; k1 < 4; ++k1) {
            double d19;
            double d15;
            double d17;
            double d13;
            double d11;
            double d9;
            int l1 = x;
            int j1 = z;
            if (k1 == 0) {
                j1 = z - 1;
            }
            if (k1 == 1) {
                ++j1;
            }
            if (k1 == 2) {
                l1 = x - 1;
            }
            if (k1 == 3) {
                ++l1;
            }
            IIcon iicon1 = renderer.func_147787_a(block, k1 + 2, i1);
            if (!renderer.field_147837_f && !aboolean[k1]) continue;
            if (k1 == 0) {
                d9 = d2;
                d11 = d5;
                d13 = x;
                d17 = x + 1;
                d15 = (double)z + d6;
                d19 = (double)z + d6;
            } else if (k1 == 1) {
                d9 = d4;
                d11 = d3;
                d13 = x + 1;
                d17 = x;
                d15 = (double)(z + 1) - d6;
                d19 = (double)(z + 1) - d6;
            } else if (k1 == 2) {
                d9 = d3;
                d11 = d2;
                d13 = (double)x + d6;
                d17 = (double)x + d6;
                d15 = z + 1;
                d19 = z;
            } else {
                d9 = d5;
                d11 = d4;
                d13 = (double)(x + 1) - d6;
                d17 = (double)(x + 1) - d6;
                d15 = z;
                d19 = z + 1;
            }
            flag2 = true;
            float f8 = iicon1.func_94214_a(0.0);
            f9 = iicon1.func_94214_a(8.0);
            f10 = iicon1.func_94207_b((1.0 - d9) * 16.0 * 0.5);
            f11 = iicon1.func_94207_b((1.0 - d11) * 16.0 * 0.5);
            float f12 = iicon1.func_94207_b(8.0);
            tessellator.func_78380_c(block.func_149677_c(world, l1, y, j1));
            float f13 = 1.0f;
            f13 *= k1 < 2 ? f5 : f6;
            tessellator.func_78369_a(f4 * red, f4 * green, f4 * blue, aboolean[k1] ? opacityOuter : opacityInner);
            tessellator.func_78374_a(d13, (double)y + d9, d15, (double)f8, (double)f10);
            tessellator.func_78374_a(d17, (double)y + d11, d19, (double)f9, (double)f11);
            tessellator.func_78374_a(d17, (double)(y + 0), d19, (double)f9, (double)f12);
            tessellator.func_78374_a(d13, (double)(y + 0), d15, (double)f8, (double)f12);
            tessellator.func_78374_a(d13, (double)(y + 0), d15, (double)f8, (double)f12);
            tessellator.func_78374_a(d17, (double)(y + 0), d19, (double)f9, (double)f12);
            tessellator.func_78374_a(d17, (double)y + d11, d19, (double)f9, (double)f11);
            tessellator.func_78374_a(d13, (double)y + d9, d15, (double)f8, (double)f10);
        }
        renderer.field_147855_j = d0;
        renderer.field_147857_k = d1;
        return flag2;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return Witchery.proxy.getGasRenderId();
    }
}

