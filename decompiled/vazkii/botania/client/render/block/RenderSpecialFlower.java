/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 */
package vazkii.botania.client.render.block;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import vazkii.botania.api.lexicon.multiblock.IMultiblockRenderHook;
import vazkii.botania.api.lexicon.multiblock.Multiblock;
import vazkii.botania.api.lexicon.multiblock.component.MultiblockComponent;

public class RenderSpecialFlower
implements ISimpleBlockRenderingHandler,
IMultiblockRenderHook {
    int id;

    public RenderSpecialFlower(int id) {
        this.id = id;
    }

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess blockAccess, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return RenderSpecialFlower.renderCrossedSquares(blockAccess, block, x, y, z, renderer);
    }

    public static boolean renderCrossedSquares(IBlockAccess blockAccess, Block par1Block, int par2, int par3, int par4, RenderBlocks render) {
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78380_c(par1Block.func_149677_c(blockAccess, par2, par3, par4));
        float f = 1.0f;
        int l = par1Block.func_149720_d(blockAccess, par2, par3, par4);
        float f1 = (float)(l >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(l >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(l & 0xFF) / 255.0f;
        if (EntityRenderer.field_78517_a) {
            float f4 = (f1 * 30.0f + f2 * 59.0f + f3 * 11.0f) / 100.0f;
            float f5 = (f1 * 30.0f + f2 * 70.0f) / 100.0f;
            float f6 = (f1 * 30.0f + f3 * 70.0f) / 100.0f;
            f1 = f4;
            f2 = f5;
            f3 = f6;
        }
        tessellator.func_78386_a(f * f1, f * f2, f * f3);
        double d1 = par2;
        double d2 = par3;
        double d0 = par4;
        long sh = (long)(par2 * 3129871) ^ (long)par4 * 116129781L ^ (long)par3;
        sh = sh * sh * 42317861L + sh * 11L;
        IIcon icon = render.func_147793_a(par1Block, blockAccess, par2, par3, par4, 0);
        RenderSpecialFlower.drawCrossedSquares(blockAccess, par1Block, icon, par2, par3, par4, d1 += ((double)((float)(sh >> 16 & 0xFL) / 15.0f) - 0.5) * 0.3, d2 += (double)((float)(sh >> 32 & 0xFL) / 15.0f) * -0.15, d0 += ((double)((float)(sh >> 24 & 0xFL) / 15.0f) - 0.5) * 0.3, 1.0f, render);
        return true;
    }

    public static void drawCrossedSquares(IBlockAccess blockAccess, Block par1Block, IIcon icon, int x, int y, int z, double par3, double par5, double par7, float par9, RenderBlocks render) {
        Tessellator tessellator = Tessellator.field_78398_a;
        double d3 = icon.func_94209_e();
        double d4 = icon.func_94206_g();
        double d5 = icon.func_94212_f();
        double d6 = icon.func_94210_h();
        double d7 = 0.45 * (double)par9;
        double d8 = par3 + 0.5 - d7;
        double d9 = par3 + 0.5 + d7;
        double d10 = par7 + 0.5 - d7;
        double d11 = par7 + 0.5 + d7;
        tessellator.func_78374_a(d8, par5 + (double)par9, d10, d3, d4);
        tessellator.func_78374_a(d8, par5 + 0.0, d10, d3, d6);
        tessellator.func_78374_a(d9, par5 + 0.0, d11, d5, d6);
        tessellator.func_78374_a(d9, par5 + (double)par9, d11, d5, d4);
        tessellator.func_78374_a(d9, par5 + (double)par9, d11, d3, d4);
        tessellator.func_78374_a(d9, par5 + 0.0, d11, d3, d6);
        tessellator.func_78374_a(d8, par5 + 0.0, d10, d5, d6);
        tessellator.func_78374_a(d8, par5 + (double)par9, d10, d5, d4);
        tessellator.func_78374_a(d8, par5 + (double)par9, d11, d3, d4);
        tessellator.func_78374_a(d8, par5 + 0.0, d11, d3, d6);
        tessellator.func_78374_a(d9, par5 + 0.0, d10, d5, d6);
        tessellator.func_78374_a(d9, par5 + (double)par9, d10, d5, d4);
        tessellator.func_78374_a(d9, par5 + (double)par9, d10, d3, d4);
        tessellator.func_78374_a(d9, par5 + 0.0, d10, d3, d6);
        tessellator.func_78374_a(d8, par5 + 0.0, d11, d5, d6);
        tessellator.func_78374_a(d8, par5 + (double)par9, d11, d5, d4);
    }

    public int getRenderId() {
        return this.id;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    @Override
    public void renderBlockForMultiblock(IBlockAccess world, Multiblock mb, Block block, int meta, RenderBlocks renderBlocks, MultiblockComponent comp, float alpha) {
        Tessellator tess = Tessellator.field_78398_a;
        tess.func_78382_b();
        RenderSpecialFlower.drawCrossedSquares(world, block, block.func_149691_a(0, meta), 0, 0, 0, -0.5, -0.5, -0.5, 1.0f, renderBlocks);
        tess.func_78381_a();
    }

    @Override
    public boolean needsTranslate(Block block) {
        return true;
    }
}

