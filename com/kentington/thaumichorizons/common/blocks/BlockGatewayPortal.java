/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  thaumcraft.common.config.ConfigBlocks
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileSlot;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.common.config.ConfigBlocks;

public class BlockGatewayPortal
extends Block {
    public IIcon CornerTR;
    public IIcon CornerTL;
    public IIcon CornerBR;
    public IIcon CornerBL;
    public IIcon topR;
    public IIcon topL;
    public IIcon R;
    public IIcon L;
    public IIcon B;
    public IIcon lapiz;
    public IIcon stone;

    public BlockGatewayPortal() {
        super(Material.field_151576_e);
        this.func_149711_c(2.5f);
        this.func_149752_b(2.5f);
        this.func_149663_c("ThaumicHorizons_gateway");
    }

    public void func_149749_a(World world, int x, int y, int z, Block block, int md) {
        int slotX = 0;
        int slotY = 0;
        int slotZ = 0;
        if (md < 5) {
            slotY = y + md;
            if (world.func_147439_a(x + 1, y, z) == ThaumicHorizons.blockPortal || world.func_147439_a(x + 1, y, z) == ThaumicHorizons.blockGateway) {
                slotX = x + 2;
                slotZ = z;
            } else {
                slotX = x;
                slotZ = z + 2;
            }
        } else if (md == 5) {
            slotY = y;
            if (world.func_147439_a(x + 1, y, z) == ThaumicHorizons.blockSlot) {
                slotX = x + 1;
                slotZ = z;
            } else {
                slotX = x;
                slotZ = z + 1;
            }
        } else if (md == 8) {
            slotY = y;
            if (world.func_147439_a(x - 1, y, z) == ThaumicHorizons.blockSlot) {
                slotX = x - 1;
                slotZ = z;
            } else {
                slotX = x;
                slotZ = z - 1;
            }
        } else if (md == 6 || md == 7 || md == 9) {
            slotY = y + 4;
            if (world.func_147439_a(x + 1, y, z) == ThaumicHorizons.blockGateway) {
                slotZ = z;
                switch (md) {
                    case 6: {
                        slotX = x + 1;
                        break;
                    }
                    case 7: {
                        slotX = x;
                        break;
                    }
                    case 9: {
                        slotX = x - 1;
                    }
                }
            } else {
                slotX = x;
                switch (md) {
                    case 6: {
                        slotZ = z + 1;
                        break;
                    }
                    case 7: {
                        slotZ = z;
                        break;
                    }
                    case 9: {
                        slotZ = z - 1;
                    }
                }
            }
        } else {
            slotY = y + md - 10;
            if (world.func_147439_a(x - 1, y, z) == ThaumicHorizons.blockPortal || world.func_147439_a(x - 1, y, z) == ThaumicHorizons.blockGateway) {
                slotX = x - 2;
                slotZ = z;
            } else {
                slotX = x;
                slotZ = z - 2;
            }
        }
        TileEntity te = world.func_147438_o(slotX, slotY, slotZ);
        if (te instanceof TileSlot) {
            ((TileSlot)te).destroyPortal();
        }
    }

    @SideOnly(value=Side.CLIENT)
    public Item func_149694_d(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_) {
        return Item.func_150899_d((int)0);
    }

    public int func_149745_a(Random p_149745_1_) {
        return 0;
    }

    public void func_149651_a(IIconRegister ir) {
        this.CornerTR = ir.func_94245_a("thaumichorizons:gateway_topright");
        this.CornerTL = ir.func_94245_a("thaumichorizons:gateway_topleft");
        this.CornerBR = ir.func_94245_a("thaumichorizons:gateway_bottomright");
        this.CornerBL = ir.func_94245_a("thaumichorizons:gateway_bottomleft");
        this.topR = ir.func_94245_a("thaumichorizons:gateway_top2");
        this.topL = ir.func_94245_a("thaumichorizons:gateway_top");
        this.R = ir.func_94245_a("thaumichorizons:gateway_right");
        this.L = ir.func_94245_a("thaumichorizons:gateway_left");
        this.B = ir.func_94245_a("thaumichorizons:gateway_bottom");
        this.lapiz = Blocks.field_150368_y.func_149691_a(0, 0);
        this.stone = ConfigBlocks.blockCosmeticSolid.func_149691_a(0, 11);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int side) {
        boolean isXAligned = world.func_147439_a(x + 1, y, z) == ThaumicHorizons.blockGateway || world.func_147439_a(x + 1, y, z) == ThaumicHorizons.blockPortal || world.func_147439_a(x - 1, y, z) == ThaumicHorizons.blockGateway || world.func_147439_a(x - 1, y, z) == ThaumicHorizons.blockPortal;
        switch (world.func_72805_g(x, y, z)) {
            case 0: {
                switch (side) {
                    case 0: {
                        return this.lapiz;
                    }
                    case 1: {
                        return this.stone;
                    }
                    case 2: {
                        if (isXAligned) {
                            return this.CornerTR;
                        }
                        return this.stone;
                    }
                    case 3: {
                        if (isXAligned) {
                            return this.CornerTL;
                        }
                        return this.stone;
                    }
                    case 4: {
                        if (isXAligned) {
                            return this.stone;
                        }
                        return this.CornerTL;
                    }
                    case 5: {
                        if (isXAligned) {
                            return this.stone;
                        }
                        return this.CornerTR;
                    }
                }
            }
            case 1: {
                return this.leftSide(isXAligned, side);
            }
            case 2: {
                return this.leftSide(isXAligned, side);
            }
            case 3: {
                return this.leftSide(isXAligned, side);
            }
            case 4: {
                switch (side) {
                    case 0: {
                        return this.stone;
                    }
                    case 1: {
                        return this.lapiz;
                    }
                    case 2: {
                        if (isXAligned) {
                            return this.CornerBR;
                        }
                        return this.stone;
                    }
                    case 3: {
                        if (isXAligned) {
                            return this.CornerBL;
                        }
                        return this.stone;
                    }
                    case 4: {
                        if (isXAligned) {
                            return this.stone;
                        }
                        return this.CornerBL;
                    }
                    case 5: {
                        if (isXAligned) {
                            return this.stone;
                        }
                        return this.CornerBR;
                    }
                }
            }
            case 5: {
                switch (side) {
                    case 0: {
                        return this.lapiz;
                    }
                    case 1: {
                        return this.stone;
                    }
                    case 2: {
                        if (isXAligned) {
                            return this.topR;
                        }
                    }
                    case 3: {
                        if (isXAligned) {
                            return this.topL;
                        }
                    }
                    case 4: {
                        if (!isXAligned) {
                            return this.topL;
                        }
                    }
                    case 5: {
                        if (isXAligned) break;
                        return this.topR;
                    }
                }
                return this.lapiz;
            }
            case 6: {
                return this.bottomSide(isXAligned, side);
            }
            case 7: {
                return this.bottomSide(isXAligned, side);
            }
            case 8: {
                switch (side) {
                    case 0: {
                        return this.lapiz;
                    }
                    case 1: {
                        return this.stone;
                    }
                    case 2: {
                        if (isXAligned) {
                            return this.topL;
                        }
                    }
                    case 3: {
                        if (isXAligned) {
                            return this.topR;
                        }
                    }
                    case 4: {
                        if (!isXAligned) {
                            return this.topR;
                        }
                    }
                    case 5: {
                        if (isXAligned) break;
                        return this.topL;
                    }
                }
                return this.lapiz;
            }
            case 9: {
                return this.bottomSide(isXAligned, side);
            }
            case 10: {
                switch (side) {
                    case 0: {
                        return this.stone;
                    }
                    case 1: {
                        return this.lapiz;
                    }
                    case 2: {
                        if (isXAligned) {
                            return this.CornerTL;
                        }
                        return this.stone;
                    }
                    case 3: {
                        if (isXAligned) {
                            return this.CornerTR;
                        }
                        return this.stone;
                    }
                    case 4: {
                        if (isXAligned) {
                            return this.stone;
                        }
                        return this.CornerTR;
                    }
                    case 5: {
                        if (isXAligned) {
                            return this.stone;
                        }
                        return this.CornerTL;
                    }
                }
            }
            case 11: {
                return this.rightSide(isXAligned, side);
            }
            case 12: {
                return this.rightSide(isXAligned, side);
            }
            case 13: {
                return this.rightSide(isXAligned, side);
            }
            case 14: {
                switch (side) {
                    case 0: {
                        return this.stone;
                    }
                    case 1: {
                        return this.lapiz;
                    }
                    case 2: {
                        if (isXAligned) {
                            return this.CornerBL;
                        }
                        return this.stone;
                    }
                    case 3: {
                        if (isXAligned) {
                            return this.CornerBR;
                        }
                        return this.stone;
                    }
                    case 4: {
                        if (isXAligned) {
                            return this.stone;
                        }
                        return this.CornerBR;
                    }
                    case 5: {
                        if (isXAligned) {
                            return this.stone;
                        }
                        return this.CornerBL;
                    }
                }
            }
        }
        return this.lapiz;
    }

    IIcon leftSide(boolean xAligned, int side) {
        if (xAligned) {
            switch (side) {
                case 2: {
                    return this.R;
                }
                case 3: {
                    return this.L;
                }
                case 4: {
                    return this.stone;
                }
            }
            return this.lapiz;
        }
        switch (side) {
            case 2: {
                return this.stone;
            }
            case 4: {
                return this.L;
            }
            case 5: {
                return this.R;
            }
        }
        return this.lapiz;
    }

    IIcon rightSide(boolean xAligned, int side) {
        if (xAligned) {
            switch (side) {
                case 2: {
                    return this.L;
                }
                case 3: {
                    return this.R;
                }
                case 5: {
                    return this.stone;
                }
            }
            return this.lapiz;
        }
        switch (side) {
            case 3: {
                return this.stone;
            }
            case 4: {
                return this.R;
            }
            case 5: {
                return this.L;
            }
        }
        return this.lapiz;
    }

    IIcon bottomSide(boolean xAligned, int side) {
        switch (side) {
            case 0: {
                return this.stone;
            }
            case 1: {
                return this.lapiz;
            }
        }
        return this.B;
    }
}

