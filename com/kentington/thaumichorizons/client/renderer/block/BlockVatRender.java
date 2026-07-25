/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.IBlockAccess
 */
package com.kentington.thaumichorizons.client.renderer.block;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.blocks.BlockVat;
import com.kentington.thaumichorizons.common.tiles.TileVat;
import com.kentington.thaumichorizons.common.tiles.TileVatSlave;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;

public class BlockVatRender
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        if (world.func_147438_o(x, y, z) instanceof TileVatSlave) {
            TileVatSlave tco = (TileVatSlave)world.func_147438_o(x, y, z);
            TileVat boss = tco.getBoss(-1);
            if (boss == null) {
                return false;
            }
            Tessellator.field_78398_a.func_78370_a(255, 255, 255, 192);
            Tessellator.field_78398_a.func_78380_c(241);
            renderer.field_147863_w = false;
            int dx = boss.field_145851_c - tco.field_145851_c;
            int dy = boss.field_145848_d - tco.field_145848_d;
            int dz = boss.field_145849_e - tco.field_145849_e;
            if (world.func_72805_g(x, y, z) == 10) {
                if (dy == 1) {
                    if (dx == -1 && dz == -1) {
                        renderer.func_147764_f(block, (double)x, (double)y, (double)z, ((BlockVat)ThaumicHorizons.blockVat).iconGlassTL);
                        renderer.func_147734_d(block, (double)x, (double)y, (double)z, ((BlockVat)ThaumicHorizons.blockVat).iconGlassTR);
                        block.func_149676_a(0.0f, 0.0f, 0.0f, 0.75f, 1.0f, 0.75f);
                        renderer.func_147775_a(block);
                        Tessellator.field_78398_a.func_78370_a(255, 255, 255, 255);
                        renderer.func_147764_f(block, (double)x, (double)y, (double)z, Blocks.field_150355_j.func_149733_h(0));
                        renderer.func_147734_d(block, (double)x, (double)y, (double)z, Blocks.field_150355_j.func_149733_h(0));
                        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                        renderer.func_147775_a(block);
                    } else if (dx == 1 && dz == -1) {
                        renderer.func_147798_e(block, (double)x, (double)y, (double)z, ((BlockVat)ThaumicHorizons.blockVat).iconGlassTR);
                        renderer.func_147734_d(block, (double)x, (double)y, (double)z, ((BlockVat)ThaumicHorizons.blockVat).iconGlassTL);
                        block.func_149676_a(0.25f, 0.0f, 0.0f, 1.0f, 1.0f, 0.75f);
                        renderer.func_147775_a(block);
                        Tessellator.field_78398_a.func_78370_a(255, 255, 255, 255);
                        renderer.func_147798_e(block, (double)x, (double)y, (double)z, Blocks.field_150355_j.func_149733_h(0));
                        renderer.func_147734_d(block, (double)x, (double)y, (double)z, Blocks.field_150355_j.func_149733_h(0));
                        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                        renderer.func_147775_a(block);
                    } else if (dx == 1 && dz == 1) {
                        renderer.func_147798_e(block, (double)x, (double)y, (double)z, ((BlockVat)ThaumicHorizons.blockVat).iconGlassTL);
                        renderer.func_147761_c(block, (double)x, (double)y, (double)z, ((BlockVat)ThaumicHorizons.blockVat).iconGlassTR);
                        block.func_149676_a(0.25f, 0.0f, 0.25f, 1.0f, 1.0f, 1.0f);
                        renderer.func_147775_a(block);
                        Tessellator.field_78398_a.func_78370_a(255, 255, 255, 255);
                        renderer.func_147798_e(block, (double)x, (double)y, (double)z, Blocks.field_150355_j.func_149733_h(0));
                        renderer.func_147761_c(block, (double)x, (double)y, (double)z, Blocks.field_150355_j.func_149733_h(0));
                        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                        renderer.func_147775_a(block);
                    } else if (dx == -1 && dz == 1) {
                        renderer.func_147764_f(block, (double)x, (double)y, (double)z, ((BlockVat)ThaumicHorizons.blockVat).iconGlassTR);
                        renderer.func_147761_c(block, (double)x, (double)y, (double)z, ((BlockVat)ThaumicHorizons.blockVat).iconGlassTL);
                        block.func_149676_a(0.0f, 0.0f, 0.25f, 0.75f, 1.0f, 1.0f);
                        renderer.func_147775_a(block);
                        Tessellator.field_78398_a.func_78370_a(255, 255, 255, 255);
                        renderer.func_147764_f(block, (double)x, (double)y, (double)z, Blocks.field_150355_j.func_149733_h(0));
                        renderer.func_147761_c(block, (double)x, (double)y, (double)z, Blocks.field_150355_j.func_149733_h(0));
                        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                        renderer.func_147775_a(block);
                    } else if (dx == 0) {
                        if (dz == -1) {
                            renderer.func_147734_d(block, (double)x, (double)y, (double)z, ((BlockVat)ThaumicHorizons.blockVat).iconGlassT);
                            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.75f);
                            renderer.func_147775_a(block);
                            Tessellator.field_78398_a.func_78370_a(255, 255, 255, 255);
                            renderer.func_147734_d(block, (double)x, (double)y, (double)z, Blocks.field_150355_j.func_149733_h(0));
                            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                            renderer.func_147775_a(block);
                        } else if (dz == 1) {
                            renderer.func_147761_c(block, (double)x, (double)y, (double)z, ((BlockVat)ThaumicHorizons.blockVat).iconGlassT);
                            block.func_149676_a(0.0f, 0.0f, 0.25f, 1.0f, 1.0f, 1.0f);
                            renderer.func_147775_a(block);
                            Tessellator.field_78398_a.func_78370_a(255, 255, 255, 255);
                            renderer.func_147761_c(block, (double)x, (double)y, (double)z, Blocks.field_150355_j.func_149733_h(0));
                            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                            renderer.func_147775_a(block);
                        }
                    } else if (dz == 0) {
                        if (dx == -1) {
                            renderer.func_147764_f(block, (double)x, (double)y, (double)z, ((BlockVat)ThaumicHorizons.blockVat).iconGlassT);
                            block.func_149676_a(0.0f, 0.0f, 0.0f, 0.75f, 1.0f, 1.0f);
                            renderer.func_147775_a(block);
                            Tessellator.field_78398_a.func_78370_a(255, 255, 255, 255);
                            renderer.func_147764_f(block, (double)x, (double)y, (double)z, Blocks.field_150355_j.func_149733_h(0));
                            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                            renderer.func_147775_a(block);
                        } else if (dx == 1) {
                            renderer.func_147798_e(block, (double)x, (double)y, (double)z, ((BlockVat)ThaumicHorizons.blockVat).iconGlassT);
                            block.func_149676_a(0.25f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                            renderer.func_147775_a(block);
                            Tessellator.field_78398_a.func_78370_a(255, 255, 255, 255);
                            renderer.func_147798_e(block, (double)x, (double)y, (double)z, Blocks.field_150355_j.func_149733_h(0));
                            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                            renderer.func_147775_a(block);
                        }
                    }
                } else if (dy == 2) {
                    if (dx == -1 && dz == -1) {
                        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                        renderer.func_147775_a(block);
                        renderer.func_147764_f(block, (double)x, (double)y, (double)z, ((BlockVat)ThaumicHorizons.blockVat).iconGlassBL);
                        renderer.func_147734_d(block, (double)x, (double)y, (double)z, ((BlockVat)ThaumicHorizons.blockVat).iconGlassBR);
                        block.func_149676_a(0.0f, 0.0f, 0.0f, 0.75f, 1.0f, 0.75f);
                        renderer.func_147775_a(block);
                        Tessellator.field_78398_a.func_78370_a(255, 255, 255, 255);
                        renderer.func_147764_f(block, (double)x, (double)y, (double)z, Blocks.field_150355_j.func_149733_h(0));
                        renderer.func_147734_d(block, (double)x, (double)y, (double)z, Blocks.field_150355_j.func_149733_h(0));
                        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                        renderer.func_147775_a(block);
                    } else if (dx == 1 && dz == -1) {
                        renderer.func_147798_e(block, (double)x, (double)y, (double)z, ((BlockVat)ThaumicHorizons.blockVat).iconGlassBR);
                        renderer.func_147734_d(block, (double)x, (double)y, (double)z, ((BlockVat)ThaumicHorizons.blockVat).iconGlassBL);
                        block.func_149676_a(0.25f, 0.0f, 0.0f, 1.0f, 1.0f, 0.75f);
                        renderer.func_147775_a(block);
                        Tessellator.field_78398_a.func_78370_a(255, 255, 255, 255);
                        renderer.func_147798_e(block, (double)x, (double)y, (double)z, Blocks.field_150355_j.func_149733_h(0));
                        renderer.func_147734_d(block, (double)x, (double)y, (double)z, Blocks.field_150355_j.func_149733_h(0));
                        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                        renderer.func_147775_a(block);
                    } else if (dx == 1 && dz == 1) {
                        renderer.func_147798_e(block, (double)x, (double)y, (double)z, ((BlockVat)ThaumicHorizons.blockVat).iconGlassBL);
                        renderer.func_147761_c(block, (double)x, (double)y, (double)z, ((BlockVat)ThaumicHorizons.blockVat).iconGlassBR);
                        block.func_149676_a(0.25f, 0.0f, 0.25f, 1.0f, 1.0f, 1.0f);
                        renderer.func_147775_a(block);
                        Tessellator.field_78398_a.func_78370_a(255, 255, 255, 255);
                        renderer.func_147798_e(block, (double)x, (double)y, (double)z, Blocks.field_150355_j.func_149733_h(0));
                        renderer.func_147761_c(block, (double)x, (double)y, (double)z, Blocks.field_150355_j.func_149733_h(0));
                        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                        renderer.func_147775_a(block);
                    } else if (dx == -1 && dz == 1) {
                        renderer.func_147764_f(block, (double)x, (double)y, (double)z, ((BlockVat)ThaumicHorizons.blockVat).iconGlassBR);
                        renderer.func_147761_c(block, (double)x, (double)y, (double)z, ((BlockVat)ThaumicHorizons.blockVat).iconGlassBL);
                        block.func_149676_a(0.0f, 0.0f, 0.25f, 0.75f, 1.0f, 1.0f);
                        renderer.func_147775_a(block);
                        Tessellator.field_78398_a.func_78370_a(255, 255, 255, 255);
                        renderer.func_147764_f(block, (double)x, (double)y, (double)z, Blocks.field_150355_j.func_149733_h(0));
                        renderer.func_147761_c(block, (double)x, (double)y, (double)z, Blocks.field_150355_j.func_149733_h(0));
                        block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                        renderer.func_147775_a(block);
                    } else if (dx == 0) {
                        if (dz == -1) {
                            renderer.func_147734_d(block, (double)x, (double)y, (double)z, ((BlockVat)ThaumicHorizons.blockVat).iconGlassB);
                            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.75f);
                            renderer.func_147775_a(block);
                            Tessellator.field_78398_a.func_78370_a(255, 255, 255, 255);
                            renderer.func_147734_d(block, (double)x, (double)y, (double)z, Blocks.field_150355_j.func_149733_h(0));
                            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                            renderer.func_147775_a(block);
                        } else if (dz == 1) {
                            renderer.func_147761_c(block, (double)x, (double)y, (double)z, ((BlockVat)ThaumicHorizons.blockVat).iconGlassB);
                            block.func_149676_a(0.0f, 0.0f, 0.25f, 1.0f, 1.0f, 1.0f);
                            renderer.func_147775_a(block);
                            Tessellator.field_78398_a.func_78370_a(255, 255, 255, 255);
                            renderer.func_147761_c(block, (double)x, (double)y, (double)z, Blocks.field_150355_j.func_149733_h(0));
                            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                            renderer.func_147775_a(block);
                        }
                    } else if (dz == 0) {
                        if (dx == -1) {
                            renderer.func_147764_f(block, (double)x, (double)y, (double)z, ((BlockVat)ThaumicHorizons.blockVat).iconGlassB);
                            block.func_149676_a(0.0f, 0.0f, 0.0f, 0.75f, 1.0f, 1.0f);
                            renderer.func_147775_a(block);
                            Tessellator.field_78398_a.func_78370_a(255, 255, 255, 255);
                            renderer.func_147764_f(block, (double)x, (double)y, (double)z, Blocks.field_150355_j.func_149733_h(0));
                            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                            renderer.func_147775_a(block);
                        } else if (dx == 1) {
                            renderer.func_147798_e(block, (double)x, (double)y, (double)z, ((BlockVat)ThaumicHorizons.blockVat).iconGlassB);
                            block.func_149676_a(0.25f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                            renderer.func_147775_a(block);
                            Tessellator.field_78398_a.func_78370_a(255, 255, 255, 255);
                            renderer.func_147798_e(block, (double)x, (double)y, (double)z, Blocks.field_150355_j.func_149733_h(0));
                            block.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                            renderer.func_147775_a(block);
                        }
                    }
                }
                renderer.field_147863_w = true;
                return true;
            }
        }
        renderer.field_147863_w = true;
        return false;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return ThaumicHorizons.blockVatRI;
    }
}

