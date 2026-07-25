/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.world.IBlockAccess
 */
package com.kentington.thaumichorizons.client.renderer.block;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.blocks.BlockVatSolid;
import com.kentington.thaumichorizons.common.tiles.TileVat;
import com.kentington.thaumichorizons.common.tiles.TileVatSlave;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

public class BlockVatSolidRender
implements ISimpleBlockRenderingHandler {
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    }

    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        if (world.func_147438_o(x, y, z) instanceof TileVatSlave) {
            TileVatSlave tco = (TileVatSlave)world.func_147438_o(x, y, z);
            TileVat boss = tco.getBoss(-1);
            if (boss == null) {
                return false;
            }
            Tessellator.field_78398_a.func_78386_a(1.0f, 1.0f, 1.0f);
            Tessellator.field_78398_a.func_78380_c(150);
            renderer.field_147863_w = false;
            int dx = boss.field_145851_c - tco.field_145851_c;
            int dy = boss.field_145848_d - tco.field_145848_d;
            int dz = boss.field_145849_e - tco.field_145849_e;
            if (world.func_72805_g(x, y, z) == 6) {
                renderer.func_147798_e(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconBaseSide);
                renderer.func_147761_c(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconBaseSide);
                renderer.func_147764_f(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconBaseSide);
                renderer.func_147734_d(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconBaseSide);
                renderer.func_147768_a(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconBaseCenter);
                renderer.func_147806_b(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconInnerCenter);
            } else {
                if (world.func_72805_g(x, y, z) == 4) {
                    renderer.func_147798_e(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconBaseSide);
                    renderer.func_147761_c(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconBaseSide);
                    renderer.func_147764_f(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconBaseSide);
                    renderer.func_147734_d(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconBaseSide);
                    renderer.func_147768_a(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconBaseSideBottom);
                    if (dx == 0) {
                        if (dz == -1) {
                            renderer.func_147806_b(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconInnerPosZ);
                        } else if (dz == 1) {
                            renderer.func_147806_b(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconInnerNegZ);
                        }
                    } else if (dz == 0) {
                        if (dx == -1) {
                            renderer.func_147806_b(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconInnerPosX);
                        } else if (dx == 1) {
                            renderer.func_147806_b(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconInnerNegX);
                        }
                    }
                    return true;
                }
                if (world.func_72805_g(x, y, z) == 5 && dy == 3) {
                    renderer.func_147798_e(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconBaseLeftRight);
                    renderer.func_147761_c(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconBaseLeftRight);
                    renderer.func_147764_f(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconBaseLeftRight);
                    renderer.func_147734_d(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconBaseLeftRight);
                    renderer.func_147768_a(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconBaseCenter);
                    if (dx == -1) {
                        if (dz == -1) {
                            renderer.func_147806_b(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconInnerCornerD);
                        } else if (dz == 1) {
                            renderer.func_147806_b(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconInnerCornerC);
                        }
                    } else if (dx == 1) {
                        if (dz == -1) {
                            renderer.func_147806_b(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconInnerCornerA);
                        } else if (dz == 1) {
                            renderer.func_147806_b(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconInnerCornerB);
                        }
                    }
                    return true;
                }
                if (world.func_72805_g(x, y, z) == 5) {
                    renderer.func_147806_b(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconGreatwood);
                    if (dx == 0 || dz == 0) {
                        renderer.func_147798_e(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconLidSideCenter);
                        renderer.func_147761_c(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconLidSideCenter);
                        renderer.func_147764_f(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconLidSideCenter);
                        renderer.func_147734_d(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconLidSideCenter);
                        if (dz == -1) {
                            renderer.func_147768_a(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconInnerPosZ);
                        } else if (dz == 1) {
                            renderer.func_147768_a(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconInnerNegZ);
                        } else if (dx == -1) {
                            renderer.func_147768_a(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconInnerPosX);
                        } else if (dx == 1) {
                            renderer.func_147768_a(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconInnerNegX);
                        }
                    } else {
                        renderer.func_147798_e(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconLidLeftRight);
                        renderer.func_147761_c(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconLidLeftRight);
                        renderer.func_147764_f(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconLidLeftRight);
                        renderer.func_147734_d(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconLidLeftRight);
                        if (dx == -1) {
                            if (dz == -1) {
                                renderer.func_147768_a(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconInnerCornerD);
                            } else if (dz == 1) {
                                renderer.func_147768_a(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconInnerCornerC);
                            }
                        } else if (dx == 1) {
                            if (dz == -1) {
                                renderer.func_147768_a(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconInnerCornerA);
                            } else if (dz == 1) {
                                renderer.func_147768_a(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconInnerCornerB);
                            }
                        }
                    }
                    renderer.field_147863_w = true;
                    return true;
                }
            }
        } else if (world.func_72805_g(x, y, z) == 7) {
            Tessellator.field_78398_a.func_78386_a(1.0f, 1.0f, 1.0f);
            renderer.field_147863_w = false;
            renderer.func_147806_b(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconLidCenterTop);
            renderer.func_147768_a(block, (double)x, (double)y, (double)z, ((BlockVatSolid)ThaumicHorizons.blockVatSolid).iconInnerCenter);
            renderer.field_147863_w = true;
            return true;
        }
        renderer.field_147863_w = true;
        return false;
    }

    public boolean shouldRender3DInInventory(int modelId) {
        return false;
    }

    public int getRenderId() {
        return ThaumicHorizons.blockVatSolidRI;
    }
}

