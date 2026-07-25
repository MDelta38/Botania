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
 *  org.lwjgl.opengl.GL11
 */
package flaxbeard.thaumicexploration.client.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.block.BlockEverfullUrn;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;

public class BlockEverfullUrnRenderer
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        float f = 0.875f;
        float f1 = 0.125f;
        GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
        float x = 0.0f;
        float y = 0.0f;
        float z = 0.0f;
        IIcon icon = block.func_149733_h(2);
        float f4 = 0.0f;
        Tessellator tessellator = Tessellator.field_78398_a;
        block.func_149676_a(f1, 0.0f, f1, f, 1.0f, f);
        tessellator.func_78382_b();
        tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
        renderer.func_147764_f(block, (double)x, (double)y, (double)z, icon);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
        renderer.func_147798_e(block, (double)x, (double)y, (double)z, icon);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        renderer.func_147761_c(block, (double)x, (double)y, (double)z, icon);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        renderer.func_147734_d(block, (double)x, (double)y, (double)z, icon);
        tessellator.func_78381_a();
        f4 = 0.1875f;
        IIcon icon1 = BlockEverfullUrn.middleSide;
        tessellator.func_78382_b();
        tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
        renderer.func_147764_f(block, (double)(x - f4), (double)y, (double)z, icon1);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
        renderer.func_147798_e(block, (double)(x + f4), (double)y, (double)z, icon1);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        renderer.func_147761_c(block, (double)x, (double)y, (double)(z + f4), icon1);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        renderer.func_147734_d(block, (double)x, (double)y, (double)(z - f4), icon1);
        tessellator.func_78381_a();
        f4 = 0.125f;
        IIcon icon2 = BlockEverfullUrn.topSide;
        tessellator.func_78382_b();
        tessellator.func_78375_b(1.0f, 0.0f, 0.0f);
        renderer.func_147764_f(block, (double)(x - f4), (double)y, (double)z, icon2);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(-1.0f, 0.0f, 0.0f);
        renderer.func_147798_e(block, (double)(x + f4), (double)y, (double)z, icon2);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, -1.0f);
        renderer.func_147761_c(block, (double)x, (double)y, (double)(z + f4), icon2);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 0.0f, 1.0f);
        renderer.func_147734_d(block, (double)x, (double)y, (double)(z - f4), icon2);
        tessellator.func_78381_a();
        IIcon icon3 = BlockEverfullUrn.topTop;
        IIcon icon4 = BlockEverfullUrn.bottomTop;
        f4 = 0.4375f;
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
        renderer.func_147806_b(block, (double)x, (double)(y - f4), (double)z, icon4);
        tessellator.func_78381_a();
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
        renderer.func_147806_b(block, (double)x, (double)y, (double)z, icon3);
        tessellator.func_78381_a();
        IIcon icon5 = BlockEverfullUrn.bottomBottom;
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
        renderer.func_147768_a(block, (double)x, (double)y, (double)z, icon5);
        tessellator.func_78381_a();
        IIcon icon6 = BlockEverfullUrn.topBottom;
        f4 = 0.8125f;
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, -1.0f, 0.0f);
        renderer.func_147768_a(block, (double)x, (double)(y + f4), (double)z, icon6);
        tessellator.func_78381_a();
        GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.5f);
    }

    public boolean renderWorldBlock(IBlockAccess world, int par2, int par3, int par4, Block block, int modelId, RenderBlocks renderer) {
        float f4;
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78380_c(block.func_149677_c(world, par2, par3, par4));
        float f = 1.0f;
        int l = block.func_149720_d(world, par2, par3, par4);
        float f1 = (float)(l >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(l >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(l & 0xFF) / 255.0f;
        float xMove = 0.125f;
        xMove = 0.0f;
        float y = par3;
        float x = (float)par2 + xMove;
        float z = (float)par4 - xMove;
        if (EntityRenderer.field_78517_a) {
            float f5 = (f1 * 30.0f + f2 * 59.0f + f3 * 11.0f) / 100.0f;
            f4 = (f1 * 30.0f + f2 * 70.0f) / 100.0f;
            float f6 = (f1 * 30.0f + f3 * 70.0f) / 100.0f;
            f1 = f5;
            f2 = f4;
            f3 = f6;
        }
        tessellator.func_78386_a(f * f1, f * f2, f * f3);
        IIcon icon = block.func_149733_h(2);
        f4 = 0.125f;
        f4 = 0.0f;
        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
        renderer.func_147764_f(block, (double)(x - f4), (double)y, (double)z, icon);
        renderer.func_147798_e(block, (double)(x + f4), (double)y, (double)z, icon);
        renderer.func_147761_c(block, (double)x, (double)y, (double)(z + f4), icon);
        renderer.func_147734_d(block, (double)x, (double)y, (double)(z - f4), icon);
        f4 = 0.1875f;
        IIcon icon1 = BlockEverfullUrn.middleSide;
        renderer.func_147764_f(block, (double)(x - f4), (double)y, (double)z, icon1);
        renderer.func_147798_e(block, (double)(x + f4), (double)y, (double)z, icon1);
        renderer.func_147761_c(block, (double)x, (double)y, (double)(z + f4), icon1);
        renderer.func_147734_d(block, (double)x, (double)y, (double)(z - f4), icon1);
        f4 = 0.125f;
        IIcon icon2 = BlockEverfullUrn.topSide;
        renderer.func_147764_f(block, (double)(x - f4), (double)y, (double)z, icon2);
        renderer.func_147798_e(block, (double)(x + f4), (double)y, (double)z, icon2);
        renderer.func_147761_c(block, (double)x, (double)y, (double)(z + f4), icon2);
        renderer.func_147734_d(block, (double)x, (double)y, (double)(z - f4), icon2);
        IIcon icon3 = BlockEverfullUrn.topTop;
        IIcon icon4 = BlockEverfullUrn.bottomTop;
        f4 = 0.4375f;
        renderer.func_147806_b(block, (double)x, (double)(y - f4), (double)z, icon4);
        renderer.func_147806_b(block, (double)x, (double)y, (double)z, icon3);
        f4 = 0.8125f;
        IIcon icon5 = BlockEverfullUrn.bottomBottom;
        IIcon icon6 = BlockEverfullUrn.topBottom;
        renderer.func_147768_a(block, (double)x, (double)y, (double)z, icon5);
        renderer.func_147768_a(block, (double)x, (double)(y + f4), (double)z, icon6);
        return true;
    }

    public int getRenderId() {
        return ThaumicExploration.everfullUrnRenderID;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return modelId == ThaumicExploration.everfullUrnRenderID;
    }
}

