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
 */
package com.emoniph.witchery.brewing;

import com.emoniph.witchery.Witchery;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class RenderWitchVine
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        Tessellator tessellator = Tessellator.field_78398_a;
        IIcon iicon = renderer.func_147777_a(block, 0);
        if (renderer.func_147744_b()) {
            iicon = renderer.field_147840_d;
        }
        tessellator.func_78380_c(block.func_149677_c(renderer.field_147845_a, x, y, z));
        int color = block.func_149720_d(renderer.field_147845_a, x, y, z);
        float f = (float)(color >> 16 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f2 = (float)(color & 0xFF) / 255.0f;
        tessellator.func_78386_a(f, f1, f2);
        double d0 = iicon.func_94209_e();
        double d1 = iicon.func_94206_g();
        double d2 = iicon.func_94212_f();
        double d3 = iicon.func_94210_h();
        int l = renderer.field_147845_a.func_72805_g(x, y, z);
        double d4 = 0.0;
        double d5 = 0.05f;
        if (l == 5) {
            tessellator.func_78374_a((double)x + d5, (double)(y + 1) + d4, (double)(z + 1) + d4, d0, d1);
            tessellator.func_78374_a((double)x + d5, (double)(y + 0) - d4, (double)(z + 1) + d4, d0, d3);
            tessellator.func_78374_a((double)x + d5, (double)(y + 0) - d4, (double)(z + 0) - d4, d2, d3);
            tessellator.func_78374_a((double)x + d5, (double)(y + 1) + d4, (double)(z + 0) - d4, d2, d1);
            tessellator.func_78381_a();
            tessellator.func_78382_b();
            tessellator.func_78380_c(block.func_149677_c(renderer.field_147845_a, x, y, z));
            tessellator.func_78386_a(f, f1, f2);
            tessellator.func_78374_a((double)x - d5, (double)(y + 0) - d4, (double)(z + 1) + d4, d2, d3);
            tessellator.func_78374_a((double)x - d5, (double)(y + 1) + d4, (double)(z + 1) + d4, d2, d1);
            tessellator.func_78374_a((double)x - d5, (double)(y + 1) + d4, (double)(z + 0) - d4, d0, d1);
            tessellator.func_78374_a((double)x - d5, (double)(y + 0) - d4, (double)(z + 0) - d4, d0, d3);
        }
        if (l == 4) {
            tessellator.func_78374_a((double)(x + 1) - d5, (double)(y + 0) - d4, (double)(z + 1) + d4, d2, d3);
            tessellator.func_78374_a((double)(x + 1) - d5, (double)(y + 1) + d4, (double)(z + 1) + d4, d2, d1);
            tessellator.func_78374_a((double)(x + 1) - d5, (double)(y + 1) + d4, (double)(z + 0) - d4, d0, d1);
            tessellator.func_78374_a((double)(x + 1) - d5, (double)(y + 0) - d4, (double)(z + 0) - d4, d0, d3);
            tessellator.func_78381_a();
            tessellator.func_78382_b();
            tessellator.func_78380_c(block.func_149677_c(renderer.field_147845_a, x, y, z));
            tessellator.func_78386_a(f, f1, f2);
            tessellator.func_78374_a((double)x + 1.0 + d5, (double)(y + 1) + d4, (double)(z + 1) + d4, d0, d1);
            tessellator.func_78374_a((double)x + 1.0 + d5, (double)(y + 0) - d4, (double)(z + 1) + d4, d0, d3);
            tessellator.func_78374_a((double)x + 1.0 + d5, (double)(y + 0) - d4, (double)(z + 0) - d4, d2, d3);
            tessellator.func_78374_a((double)x + 1.0 + d5, (double)(y + 1) + d4, (double)(z + 0) - d4, d2, d1);
        }
        if (l == 3) {
            tessellator.func_78374_a((double)(x + 1) + d4, (double)(y + 0) - d4, (double)z + d5, d2, d3);
            tessellator.func_78374_a((double)(x + 1) + d4, (double)(y + 1) + d4, (double)z + d5, d2, d1);
            tessellator.func_78374_a((double)(x + 0) - d4, (double)(y + 1) + d4, (double)z + d5, d0, d1);
            tessellator.func_78374_a((double)(x + 0) - d4, (double)(y + 0) - d4, (double)z + d5, d0, d3);
            tessellator.func_78381_a();
            tessellator.func_78382_b();
            tessellator.func_78380_c(block.func_149677_c(renderer.field_147845_a, x, y, z));
            tessellator.func_78386_a(f, f1, f2);
            tessellator.func_78374_a((double)(x + 1) + d4, (double)(y + 1) + d4, (double)z - d5, d0, d1);
            tessellator.func_78374_a((double)(x + 1) + d4, (double)(y + 0) - d4, (double)z - d5, d0, d3);
            tessellator.func_78374_a((double)(x + 0) - d4, (double)(y + 0) - d4, (double)z - d5, d2, d3);
            tessellator.func_78374_a((double)(x + 0) - d4, (double)(y + 1) + d4, (double)z - d5, d2, d1);
        }
        if (l == 2) {
            tessellator.func_78374_a((double)(x + 1) + d4, (double)(y + 1) + d4, (double)(z + 1) - d5, d0, d1);
            tessellator.func_78374_a((double)(x + 1) + d4, (double)(y + 0) - d4, (double)(z + 1) - d5, d0, d3);
            tessellator.func_78374_a((double)(x + 0) - d4, (double)(y + 0) - d4, (double)(z + 1) - d5, d2, d3);
            tessellator.func_78374_a((double)(x + 0) - d4, (double)(y + 1) + d4, (double)(z + 1) - d5, d2, d1);
            tessellator.func_78381_a();
            tessellator.func_78382_b();
            tessellator.func_78380_c(block.func_149677_c(renderer.field_147845_a, x, y, z));
            tessellator.func_78386_a(f, f1, f2);
            tessellator.func_78374_a((double)(x + 1) + d4, (double)(y + 0) - d4, (double)z + 1.0 + d5, d2, d3);
            tessellator.func_78374_a((double)(x + 1) + d4, (double)(y + 1) + d4, (double)z + 1.0 + d5, d2, d1);
            tessellator.func_78374_a((double)(x + 0) - d4, (double)(y + 1) + d4, (double)z + 1.0 + d5, d0, d1);
            tessellator.func_78374_a((double)(x + 0) - d4, (double)(y + 0) - d4, (double)z + 1.0 + d5, d0, d3);
        }
        return true;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return Witchery.proxy.getVineRenderId();
    }
}

