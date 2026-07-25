/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockPane
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.renderers.block.BlockRenderer
 */
package witchinggadgets.client.render;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPane;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.renderers.block.BlockRenderer;
import witchinggadgets.common.blocks.tiles.TileEntityEssentiaPump;
import witchinggadgets.common.blocks.tiles.TileEntityTerraformFocus;
import witchinggadgets.common.blocks.tiles.TileEntityTerraformer;

public class BlockRenderMetalDevice
implements ISimpleBlockRenderingHandler {
    public static int renderID = RenderingRegistry.getNextAvailableRenderId();

    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        if (metadata == 7) {
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            renderer.func_147775_a(block);
            BlockRenderer.drawFaces((RenderBlocks)renderer, (Block)block, (IIcon)block.func_149691_a(0, metadata), (boolean)true);
        } else {
            GL11.glPushMatrix();
            try {
                if (metadata == 0) {
                    GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
                    TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileEntityEssentiaPump(), 0.0, 0.0, 0.0, 0.0f);
                } else if (metadata == 1) {
                    GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
                    TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)new TileEntityTerraformer(), 0.0, 0.0, 0.0, 0.0f);
                } else if (metadata >= 2 && metadata <= 6 || metadata > 7) {
                    GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)-0.5f);
                    TileEntityTerraformFocus tetf = new TileEntityTerraformFocus();
                    tetf.field_145854_h = block;
                    tetf.field_145847_g = metadata;
                    TileEntityRendererDispatcher.field_147556_a.func_147549_a((TileEntity)tetf, 0.0, 0.0, 0.0, 0.0f);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            GL11.glEnable((int)32826);
            GL11.glPopMatrix();
        }
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        if (world.func_72805_g(x, y, z) == 7) {
            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            renderer.func_147775_a(block);
            return renderer.func_147784_q(block, x, y, z);
        }
        return false;
    }

    public boolean shouldRender3DInInventory(int modelID) {
        return true;
    }

    public int getRenderId() {
        return renderID;
    }

    public static boolean renderPane(IBlockAccess world, Block block, int x, int y, int z, RenderBlocks renderer) {
        IIcon iicon1;
        IIcon iicon;
        int l = renderer.field_147845_a.func_72800_K();
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78380_c(block.func_149677_c(renderer.field_147845_a, x, y, z));
        int i1 = block.func_149720_d(renderer.field_147845_a, x, y, z);
        float f = (float)(i1 >> 16 & 0xFF) / 255.0f;
        float f1 = (float)(i1 >> 8 & 0xFF) / 255.0f;
        float f2 = (float)(i1 & 0xFF) / 255.0f;
        if (EntityRenderer.field_78517_a) {
            float f3 = (f * 30.0f + f1 * 59.0f + f2 * 11.0f) / 100.0f;
            float f4 = (f * 30.0f + f1 * 70.0f) / 100.0f;
            float f5 = (f * 30.0f + f2 * 70.0f) / 100.0f;
            f = f3;
            f1 = f4;
            f2 = f5;
        }
        tessellator.func_78386_a(f, f1, f2);
        if (renderer.func_147744_b()) {
            iicon = renderer.field_147840_d;
            iicon1 = renderer.field_147840_d;
        } else {
            int j1 = renderer.field_147845_a.func_72805_g(x, y, z);
            iicon = renderer.func_147787_a(block, 0, j1);
            iicon1 = block.func_149673_e(world, x, y, z, 0);
        }
        double d21 = iicon.func_94209_e();
        double d0 = iicon.func_94214_a(8.0);
        double d1 = iicon.func_94212_f();
        double d2 = iicon.func_94206_g();
        double d3 = iicon.func_94210_h();
        double d4 = iicon1.func_94214_a(7.0);
        double d5 = iicon1.func_94214_a(9.0);
        double d6 = iicon1.func_94206_g();
        double d7 = iicon1.func_94207_b(8.0);
        double d8 = iicon1.func_94210_h();
        double d9 = x;
        double d10 = (double)x + 0.5;
        double d11 = x + 1;
        double d12 = z;
        double d13 = (double)z + 0.5;
        double d14 = z + 1;
        double d15 = (double)x + 0.5 - 0.0625;
        double d16 = (double)x + 0.5 + 0.0625;
        double d17 = (double)z + 0.5 - 0.0625;
        double d18 = (double)z + 0.5 + 0.0625;
        boolean flag = BlockRenderMetalDevice.canPaneConnectTo(block, renderer.field_147845_a, x, y, z - 1, ForgeDirection.NORTH);
        boolean flag1 = BlockRenderMetalDevice.canPaneConnectTo(block, renderer.field_147845_a, x, y, z + 1, ForgeDirection.SOUTH);
        boolean flag2 = BlockRenderMetalDevice.canPaneConnectTo(block, renderer.field_147845_a, x - 1, y, z, ForgeDirection.WEST);
        boolean flag3 = BlockRenderMetalDevice.canPaneConnectTo(block, renderer.field_147845_a, x + 1, y, z, ForgeDirection.EAST);
        boolean flag4 = block.func_149646_a(renderer.field_147845_a, x, y + 1, z, 1);
        boolean flag5 = block.func_149646_a(renderer.field_147845_a, x, y - 1, z, 0);
        if ((!flag2 || !flag3) && (flag2 || flag3 || flag || flag1)) {
            if (flag2 && !flag3) {
                tessellator.func_78374_a(d9, (double)(y + 1), d13, d21, d2);
                tessellator.func_78374_a(d9, (double)(y + 0), d13, d21, d3);
                tessellator.func_78374_a(d10, (double)(y + 0), d13, d0, d3);
                tessellator.func_78374_a(d10, (double)(y + 1), d13, d0, d2);
                tessellator.func_78374_a(d10, (double)(y + 1), d13, d21, d2);
                tessellator.func_78374_a(d10, (double)(y + 0), d13, d21, d3);
                tessellator.func_78374_a(d9, (double)(y + 0), d13, d0, d3);
                tessellator.func_78374_a(d9, (double)(y + 1), d13, d0, d2);
                if (!flag1 && !flag) {
                    tessellator.func_78374_a(d10, (double)(y + 1), d18, d4, d6);
                    tessellator.func_78374_a(d10, (double)(y + 0), d18, d4, d8);
                    tessellator.func_78374_a(d10, (double)(y + 0), d17, d5, d8);
                    tessellator.func_78374_a(d10, (double)(y + 1), d17, d5, d6);
                    tessellator.func_78374_a(d10, (double)(y + 1), d17, d4, d6);
                    tessellator.func_78374_a(d10, (double)(y + 0), d17, d4, d8);
                    tessellator.func_78374_a(d10, (double)(y + 0), d18, d5, d8);
                    tessellator.func_78374_a(d10, (double)(y + 1), d18, d5, d6);
                }
                if (flag4 || y < l - 1 && renderer.field_147845_a.func_147437_c(x - 1, y + 1, z)) {
                    tessellator.func_78374_a(d9, (double)(y + 1) + 0.01, d18, d5, d7);
                    tessellator.func_78374_a(d10, (double)(y + 1) + 0.01, d18, d5, d8);
                    tessellator.func_78374_a(d10, (double)(y + 1) + 0.01, d17, d4, d8);
                    tessellator.func_78374_a(d9, (double)(y + 1) + 0.01, d17, d4, d7);
                    tessellator.func_78374_a(d10, (double)(y + 1) + 0.01, d18, d5, d7);
                    tessellator.func_78374_a(d9, (double)(y + 1) + 0.01, d18, d5, d8);
                    tessellator.func_78374_a(d9, (double)(y + 1) + 0.01, d17, d4, d8);
                    tessellator.func_78374_a(d10, (double)(y + 1) + 0.01, d17, d4, d7);
                }
                if (flag5 || y > 1 && renderer.field_147845_a.func_147437_c(x - 1, y - 1, z)) {
                    tessellator.func_78374_a(d9, (double)y - 0.01, d18, d5, d7);
                    tessellator.func_78374_a(d10, (double)y - 0.01, d18, d5, d8);
                    tessellator.func_78374_a(d10, (double)y - 0.01, d17, d4, d8);
                    tessellator.func_78374_a(d9, (double)y - 0.01, d17, d4, d7);
                    tessellator.func_78374_a(d10, (double)y - 0.01, d18, d5, d7);
                    tessellator.func_78374_a(d9, (double)y - 0.01, d18, d5, d8);
                    tessellator.func_78374_a(d9, (double)y - 0.01, d17, d4, d8);
                    tessellator.func_78374_a(d10, (double)y - 0.01, d17, d4, d7);
                }
            } else if (!flag2 && flag3) {
                tessellator.func_78374_a(d10, (double)(y + 1), d13, d0, d2);
                tessellator.func_78374_a(d10, (double)(y + 0), d13, d0, d3);
                tessellator.func_78374_a(d11, (double)(y + 0), d13, d1, d3);
                tessellator.func_78374_a(d11, (double)(y + 1), d13, d1, d2);
                tessellator.func_78374_a(d11, (double)(y + 1), d13, d0, d2);
                tessellator.func_78374_a(d11, (double)(y + 0), d13, d0, d3);
                tessellator.func_78374_a(d10, (double)(y + 0), d13, d1, d3);
                tessellator.func_78374_a(d10, (double)(y + 1), d13, d1, d2);
                if (!flag1 && !flag) {
                    tessellator.func_78374_a(d10, (double)(y + 1), d17, d4, d6);
                    tessellator.func_78374_a(d10, (double)(y + 0), d17, d4, d8);
                    tessellator.func_78374_a(d10, (double)(y + 0), d18, d5, d8);
                    tessellator.func_78374_a(d10, (double)(y + 1), d18, d5, d6);
                    tessellator.func_78374_a(d10, (double)(y + 1), d18, d4, d6);
                    tessellator.func_78374_a(d10, (double)(y + 0), d18, d4, d8);
                    tessellator.func_78374_a(d10, (double)(y + 0), d17, d5, d8);
                    tessellator.func_78374_a(d10, (double)(y + 1), d17, d5, d6);
                }
                if (flag4 || y < l - 1 && renderer.field_147845_a.func_147437_c(x + 1, y + 1, z)) {
                    tessellator.func_78374_a(d10, (double)(y + 1) + 0.01, d18, d5, d6);
                    tessellator.func_78374_a(d11, (double)(y + 1) + 0.01, d18, d5, d7);
                    tessellator.func_78374_a(d11, (double)(y + 1) + 0.01, d17, d4, d7);
                    tessellator.func_78374_a(d10, (double)(y + 1) + 0.01, d17, d4, d6);
                    tessellator.func_78374_a(d11, (double)(y + 1) + 0.01, d18, d5, d6);
                    tessellator.func_78374_a(d10, (double)(y + 1) + 0.01, d18, d5, d7);
                    tessellator.func_78374_a(d10, (double)(y + 1) + 0.01, d17, d4, d7);
                    tessellator.func_78374_a(d11, (double)(y + 1) + 0.01, d17, d4, d6);
                }
                if (flag5 || y > 1 && renderer.field_147845_a.func_147437_c(x + 1, y - 1, z)) {
                    tessellator.func_78374_a(d10, (double)y - 0.01, d18, d5, d6);
                    tessellator.func_78374_a(d11, (double)y - 0.01, d18, d5, d7);
                    tessellator.func_78374_a(d11, (double)y - 0.01, d17, d4, d7);
                    tessellator.func_78374_a(d10, (double)y - 0.01, d17, d4, d6);
                    tessellator.func_78374_a(d11, (double)y - 0.01, d18, d5, d6);
                    tessellator.func_78374_a(d10, (double)y - 0.01, d18, d5, d7);
                    tessellator.func_78374_a(d10, (double)y - 0.01, d17, d4, d7);
                    tessellator.func_78374_a(d11, (double)y - 0.01, d17, d4, d6);
                }
            }
        } else {
            tessellator.func_78374_a(d9, (double)(y + 1), d13, d21, d2);
            tessellator.func_78374_a(d9, (double)(y + 0), d13, d21, d3);
            tessellator.func_78374_a(d11, (double)(y + 0), d13, d1, d3);
            tessellator.func_78374_a(d11, (double)(y + 1), d13, d1, d2);
            tessellator.func_78374_a(d11, (double)(y + 1), d13, d21, d2);
            tessellator.func_78374_a(d11, (double)(y + 0), d13, d21, d3);
            tessellator.func_78374_a(d9, (double)(y + 0), d13, d1, d3);
            tessellator.func_78374_a(d9, (double)(y + 1), d13, d1, d2);
            if (flag4) {
                tessellator.func_78374_a(d9, (double)(y + 1) + 0.01, d18, d5, d8);
                tessellator.func_78374_a(d11, (double)(y + 1) + 0.01, d18, d5, d6);
                tessellator.func_78374_a(d11, (double)(y + 1) + 0.01, d17, d4, d6);
                tessellator.func_78374_a(d9, (double)(y + 1) + 0.01, d17, d4, d8);
                tessellator.func_78374_a(d11, (double)(y + 1) + 0.01, d18, d5, d8);
                tessellator.func_78374_a(d9, (double)(y + 1) + 0.01, d18, d5, d6);
                tessellator.func_78374_a(d9, (double)(y + 1) + 0.01, d17, d4, d6);
                tessellator.func_78374_a(d11, (double)(y + 1) + 0.01, d17, d4, d8);
            } else {
                if (y < l - 1 && renderer.field_147845_a.func_147437_c(x - 1, y + 1, z)) {
                    tessellator.func_78374_a(d9, (double)(y + 1) + 0.01, d18, d5, d7);
                    tessellator.func_78374_a(d10, (double)(y + 1) + 0.01, d18, d5, d8);
                    tessellator.func_78374_a(d10, (double)(y + 1) + 0.01, d17, d4, d8);
                    tessellator.func_78374_a(d9, (double)(y + 1) + 0.01, d17, d4, d7);
                    tessellator.func_78374_a(d10, (double)(y + 1) + 0.01, d18, d5, d7);
                    tessellator.func_78374_a(d9, (double)(y + 1) + 0.01, d18, d5, d8);
                    tessellator.func_78374_a(d9, (double)(y + 1) + 0.01, d17, d4, d8);
                    tessellator.func_78374_a(d10, (double)(y + 1) + 0.01, d17, d4, d7);
                }
                if (y < l - 1 && renderer.field_147845_a.func_147437_c(x + 1, y + 1, z)) {
                    tessellator.func_78374_a(d10, (double)(y + 1) + 0.01, d18, d5, d6);
                    tessellator.func_78374_a(d11, (double)(y + 1) + 0.01, d18, d5, d7);
                    tessellator.func_78374_a(d11, (double)(y + 1) + 0.01, d17, d4, d7);
                    tessellator.func_78374_a(d10, (double)(y + 1) + 0.01, d17, d4, d6);
                    tessellator.func_78374_a(d11, (double)(y + 1) + 0.01, d18, d5, d6);
                    tessellator.func_78374_a(d10, (double)(y + 1) + 0.01, d18, d5, d7);
                    tessellator.func_78374_a(d10, (double)(y + 1) + 0.01, d17, d4, d7);
                    tessellator.func_78374_a(d11, (double)(y + 1) + 0.01, d17, d4, d6);
                }
            }
            if (flag5) {
                tessellator.func_78374_a(d9, (double)y - 0.01, d18, d5, d8);
                tessellator.func_78374_a(d11, (double)y - 0.01, d18, d5, d6);
                tessellator.func_78374_a(d11, (double)y - 0.01, d17, d4, d6);
                tessellator.func_78374_a(d9, (double)y - 0.01, d17, d4, d8);
                tessellator.func_78374_a(d11, (double)y - 0.01, d18, d5, d8);
                tessellator.func_78374_a(d9, (double)y - 0.01, d18, d5, d6);
                tessellator.func_78374_a(d9, (double)y - 0.01, d17, d4, d6);
                tessellator.func_78374_a(d11, (double)y - 0.01, d17, d4, d8);
            } else {
                if (y > 1 && renderer.field_147845_a.func_147437_c(x - 1, y - 1, z)) {
                    tessellator.func_78374_a(d9, (double)y - 0.01, d18, d5, d7);
                    tessellator.func_78374_a(d10, (double)y - 0.01, d18, d5, d8);
                    tessellator.func_78374_a(d10, (double)y - 0.01, d17, d4, d8);
                    tessellator.func_78374_a(d9, (double)y - 0.01, d17, d4, d7);
                    tessellator.func_78374_a(d10, (double)y - 0.01, d18, d5, d7);
                    tessellator.func_78374_a(d9, (double)y - 0.01, d18, d5, d8);
                    tessellator.func_78374_a(d9, (double)y - 0.01, d17, d4, d8);
                    tessellator.func_78374_a(d10, (double)y - 0.01, d17, d4, d7);
                }
                if (y > 1 && renderer.field_147845_a.func_147437_c(x + 1, y - 1, z)) {
                    tessellator.func_78374_a(d10, (double)y - 0.01, d18, d5, d6);
                    tessellator.func_78374_a(d11, (double)y - 0.01, d18, d5, d7);
                    tessellator.func_78374_a(d11, (double)y - 0.01, d17, d4, d7);
                    tessellator.func_78374_a(d10, (double)y - 0.01, d17, d4, d6);
                    tessellator.func_78374_a(d11, (double)y - 0.01, d18, d5, d6);
                    tessellator.func_78374_a(d10, (double)y - 0.01, d18, d5, d7);
                    tessellator.func_78374_a(d10, (double)y - 0.01, d17, d4, d7);
                    tessellator.func_78374_a(d11, (double)y - 0.01, d17, d4, d6);
                }
            }
        }
        if ((!flag || !flag1) && (flag2 || flag3 || flag || flag1)) {
            if (flag && !flag1) {
                tessellator.func_78374_a(d10, (double)(y + 1), d12, d21, d2);
                tessellator.func_78374_a(d10, (double)(y + 0), d12, d21, d3);
                tessellator.func_78374_a(d10, (double)(y + 0), d13, d0, d3);
                tessellator.func_78374_a(d10, (double)(y + 1), d13, d0, d2);
                tessellator.func_78374_a(d10, (double)(y + 1), d13, d21, d2);
                tessellator.func_78374_a(d10, (double)(y + 0), d13, d21, d3);
                tessellator.func_78374_a(d10, (double)(y + 0), d12, d0, d3);
                tessellator.func_78374_a(d10, (double)(y + 1), d12, d0, d2);
                if (!flag3 && !flag2) {
                    tessellator.func_78374_a(d15, (double)(y + 1), d13, d4, d6);
                    tessellator.func_78374_a(d15, (double)(y + 0), d13, d4, d8);
                    tessellator.func_78374_a(d16, (double)(y + 0), d13, d5, d8);
                    tessellator.func_78374_a(d16, (double)(y + 1), d13, d5, d6);
                    tessellator.func_78374_a(d16, (double)(y + 1), d13, d4, d6);
                    tessellator.func_78374_a(d16, (double)(y + 0), d13, d4, d8);
                    tessellator.func_78374_a(d15, (double)(y + 0), d13, d5, d8);
                    tessellator.func_78374_a(d15, (double)(y + 1), d13, d5, d6);
                }
                if (flag4 || y < l - 1 && renderer.field_147845_a.func_147437_c(x, y + 1, z - 1)) {
                    tessellator.func_78374_a(d15, (double)(y + 1) + 0.005, d12, d5, d6);
                    tessellator.func_78374_a(d15, (double)(y + 1) + 0.005, d13, d5, d7);
                    tessellator.func_78374_a(d16, (double)(y + 1) + 0.005, d13, d4, d7);
                    tessellator.func_78374_a(d16, (double)(y + 1) + 0.005, d12, d4, d6);
                    tessellator.func_78374_a(d15, (double)(y + 1) + 0.005, d13, d5, d6);
                    tessellator.func_78374_a(d15, (double)(y + 1) + 0.005, d12, d5, d7);
                    tessellator.func_78374_a(d16, (double)(y + 1) + 0.005, d12, d4, d7);
                    tessellator.func_78374_a(d16, (double)(y + 1) + 0.005, d13, d4, d6);
                }
                if (flag5 || y > 1 && renderer.field_147845_a.func_147437_c(x, y - 1, z - 1)) {
                    tessellator.func_78374_a(d15, (double)y - 0.005, d12, d5, d6);
                    tessellator.func_78374_a(d15, (double)y - 0.005, d13, d5, d7);
                    tessellator.func_78374_a(d16, (double)y - 0.005, d13, d4, d7);
                    tessellator.func_78374_a(d16, (double)y - 0.005, d12, d4, d6);
                    tessellator.func_78374_a(d15, (double)y - 0.005, d13, d5, d6);
                    tessellator.func_78374_a(d15, (double)y - 0.005, d12, d5, d7);
                    tessellator.func_78374_a(d16, (double)y - 0.005, d12, d4, d7);
                    tessellator.func_78374_a(d16, (double)y - 0.005, d13, d4, d6);
                }
            } else if (!flag && flag1) {
                tessellator.func_78374_a(d10, (double)(y + 1), d13, d0, d2);
                tessellator.func_78374_a(d10, (double)(y + 0), d13, d0, d3);
                tessellator.func_78374_a(d10, (double)(y + 0), d14, d1, d3);
                tessellator.func_78374_a(d10, (double)(y + 1), d14, d1, d2);
                tessellator.func_78374_a(d10, (double)(y + 1), d14, d0, d2);
                tessellator.func_78374_a(d10, (double)(y + 0), d14, d0, d3);
                tessellator.func_78374_a(d10, (double)(y + 0), d13, d1, d3);
                tessellator.func_78374_a(d10, (double)(y + 1), d13, d1, d2);
                if (!flag3 && !flag2) {
                    tessellator.func_78374_a(d16, (double)(y + 1), d13, d4, d6);
                    tessellator.func_78374_a(d16, (double)(y + 0), d13, d4, d8);
                    tessellator.func_78374_a(d15, (double)(y + 0), d13, d5, d8);
                    tessellator.func_78374_a(d15, (double)(y + 1), d13, d5, d6);
                    tessellator.func_78374_a(d15, (double)(y + 1), d13, d4, d6);
                    tessellator.func_78374_a(d15, (double)(y + 0), d13, d4, d8);
                    tessellator.func_78374_a(d16, (double)(y + 0), d13, d5, d8);
                    tessellator.func_78374_a(d16, (double)(y + 1), d13, d5, d6);
                }
                if (flag4 || y < l - 1 && renderer.field_147845_a.func_147437_c(x, y + 1, z + 1)) {
                    tessellator.func_78374_a(d15, (double)(y + 1) + 0.005, d13, d4, d7);
                    tessellator.func_78374_a(d15, (double)(y + 1) + 0.005, d14, d4, d8);
                    tessellator.func_78374_a(d16, (double)(y + 1) + 0.005, d14, d5, d8);
                    tessellator.func_78374_a(d16, (double)(y + 1) + 0.005, d13, d5, d7);
                    tessellator.func_78374_a(d15, (double)(y + 1) + 0.005, d14, d4, d7);
                    tessellator.func_78374_a(d15, (double)(y + 1) + 0.005, d13, d4, d8);
                    tessellator.func_78374_a(d16, (double)(y + 1) + 0.005, d13, d5, d8);
                    tessellator.func_78374_a(d16, (double)(y + 1) + 0.005, d14, d5, d7);
                }
                if (flag5 || y > 1 && renderer.field_147845_a.func_147437_c(x, y - 1, z + 1)) {
                    tessellator.func_78374_a(d15, (double)y - 0.005, d13, d4, d7);
                    tessellator.func_78374_a(d15, (double)y - 0.005, d14, d4, d8);
                    tessellator.func_78374_a(d16, (double)y - 0.005, d14, d5, d8);
                    tessellator.func_78374_a(d16, (double)y - 0.005, d13, d5, d7);
                    tessellator.func_78374_a(d15, (double)y - 0.005, d14, d4, d7);
                    tessellator.func_78374_a(d15, (double)y - 0.005, d13, d4, d8);
                    tessellator.func_78374_a(d16, (double)y - 0.005, d13, d5, d8);
                    tessellator.func_78374_a(d16, (double)y - 0.005, d14, d5, d7);
                }
            }
        } else {
            tessellator.func_78374_a(d10, (double)(y + 1), d14, d21, d2);
            tessellator.func_78374_a(d10, (double)(y + 0), d14, d21, d3);
            tessellator.func_78374_a(d10, (double)(y + 0), d12, d1, d3);
            tessellator.func_78374_a(d10, (double)(y + 1), d12, d1, d2);
            tessellator.func_78374_a(d10, (double)(y + 1), d12, d21, d2);
            tessellator.func_78374_a(d10, (double)(y + 0), d12, d21, d3);
            tessellator.func_78374_a(d10, (double)(y + 0), d14, d1, d3);
            tessellator.func_78374_a(d10, (double)(y + 1), d14, d1, d2);
            if (flag4) {
                tessellator.func_78374_a(d16, (double)(y + 1) + 0.005, d14, d5, d8);
                tessellator.func_78374_a(d16, (double)(y + 1) + 0.005, d12, d5, d6);
                tessellator.func_78374_a(d15, (double)(y + 1) + 0.005, d12, d4, d6);
                tessellator.func_78374_a(d15, (double)(y + 1) + 0.005, d14, d4, d8);
                tessellator.func_78374_a(d16, (double)(y + 1) + 0.005, d12, d5, d8);
                tessellator.func_78374_a(d16, (double)(y + 1) + 0.005, d14, d5, d6);
                tessellator.func_78374_a(d15, (double)(y + 1) + 0.005, d14, d4, d6);
                tessellator.func_78374_a(d15, (double)(y + 1) + 0.005, d12, d4, d8);
            } else {
                if (y < l - 1 && renderer.field_147845_a.func_147437_c(x, y + 1, z - 1)) {
                    tessellator.func_78374_a(d15, (double)(y + 1) + 0.005, d12, d5, d6);
                    tessellator.func_78374_a(d15, (double)(y + 1) + 0.005, d13, d5, d7);
                    tessellator.func_78374_a(d16, (double)(y + 1) + 0.005, d13, d4, d7);
                    tessellator.func_78374_a(d16, (double)(y + 1) + 0.005, d12, d4, d6);
                    tessellator.func_78374_a(d15, (double)(y + 1) + 0.005, d13, d5, d6);
                    tessellator.func_78374_a(d15, (double)(y + 1) + 0.005, d12, d5, d7);
                    tessellator.func_78374_a(d16, (double)(y + 1) + 0.005, d12, d4, d7);
                    tessellator.func_78374_a(d16, (double)(y + 1) + 0.005, d13, d4, d6);
                }
                if (y < l - 1 && renderer.field_147845_a.func_147437_c(x, y + 1, z + 1)) {
                    tessellator.func_78374_a(d15, (double)(y + 1) + 0.005, d13, d4, d7);
                    tessellator.func_78374_a(d15, (double)(y + 1) + 0.005, d14, d4, d8);
                    tessellator.func_78374_a(d16, (double)(y + 1) + 0.005, d14, d5, d8);
                    tessellator.func_78374_a(d16, (double)(y + 1) + 0.005, d13, d5, d7);
                    tessellator.func_78374_a(d15, (double)(y + 1) + 0.005, d14, d4, d7);
                    tessellator.func_78374_a(d15, (double)(y + 1) + 0.005, d13, d4, d8);
                    tessellator.func_78374_a(d16, (double)(y + 1) + 0.005, d13, d5, d8);
                    tessellator.func_78374_a(d16, (double)(y + 1) + 0.005, d14, d5, d7);
                }
            }
            if (flag5) {
                tessellator.func_78374_a(d16, (double)y - 0.005, d14, d5, d8);
                tessellator.func_78374_a(d16, (double)y - 0.005, d12, d5, d6);
                tessellator.func_78374_a(d15, (double)y - 0.005, d12, d4, d6);
                tessellator.func_78374_a(d15, (double)y - 0.005, d14, d4, d8);
                tessellator.func_78374_a(d16, (double)y - 0.005, d12, d5, d8);
                tessellator.func_78374_a(d16, (double)y - 0.005, d14, d5, d6);
                tessellator.func_78374_a(d15, (double)y - 0.005, d14, d4, d6);
                tessellator.func_78374_a(d15, (double)y - 0.005, d12, d4, d8);
            } else {
                if (y > 1 && renderer.field_147845_a.func_147437_c(x, y - 1, z - 1)) {
                    tessellator.func_78374_a(d15, (double)y - 0.005, d12, d5, d6);
                    tessellator.func_78374_a(d15, (double)y - 0.005, d13, d5, d7);
                    tessellator.func_78374_a(d16, (double)y - 0.005, d13, d4, d7);
                    tessellator.func_78374_a(d16, (double)y - 0.005, d12, d4, d6);
                    tessellator.func_78374_a(d15, (double)y - 0.005, d13, d5, d6);
                    tessellator.func_78374_a(d15, (double)y - 0.005, d12, d5, d7);
                    tessellator.func_78374_a(d16, (double)y - 0.005, d12, d4, d7);
                    tessellator.func_78374_a(d16, (double)y - 0.005, d13, d4, d6);
                }
                if (y > 1 && renderer.field_147845_a.func_147437_c(x, y - 1, z + 1)) {
                    tessellator.func_78374_a(d15, (double)y - 0.005, d13, d4, d7);
                    tessellator.func_78374_a(d15, (double)y - 0.005, d14, d4, d8);
                    tessellator.func_78374_a(d16, (double)y - 0.005, d14, d5, d8);
                    tessellator.func_78374_a(d16, (double)y - 0.005, d13, d5, d7);
                    tessellator.func_78374_a(d15, (double)y - 0.005, d14, d4, d7);
                    tessellator.func_78374_a(d15, (double)y - 0.005, d13, d4, d8);
                    tessellator.func_78374_a(d16, (double)y - 0.005, d13, d5, d8);
                    tessellator.func_78374_a(d16, (double)y - 0.005, d14, d5, d7);
                }
            }
        }
        return true;
    }

    static boolean canPaneConnectToBlock(Block block, Block target) {
        return target.func_149730_j() || target == block || target == Blocks.field_150359_w || target == Blocks.field_150399_cn || target == Blocks.field_150397_co || target instanceof BlockPane;
    }

    static boolean canPaneConnectTo(Block block, IBlockAccess world, int x, int y, int z, ForgeDirection dir) {
        return BlockRenderMetalDevice.canPaneConnectToBlock(block, world.func_147439_a(x, y, z)) || world.isSideSolid(x, y, z, dir.getOpposite(), false);
    }
}

