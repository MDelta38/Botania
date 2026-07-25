/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileArcaneLamp;
import thaumcraft.common.tiles.TileArcaneLampFertility;
import thaumcraft.common.tiles.TileArcaneLampGrowth;
import thaumcraft.common.tiles.TileBrainbox;
import thaumcraft.common.tiles.TileVisRelay;

public class BlockMetalDeviceItem
extends ItemBlock {
    public BlockMetalDeviceItem(Block par1) {
        super(par1);
        this.func_77656_e(0);
        this.func_77627_a(true);
    }

    public int func_77647_b(int par1) {
        return par1;
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return super.func_77658_a() + "." + par1ItemStack.func_77960_j();
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {
        if (stack.func_77960_j() == 0 || stack.func_77960_j() == 1 || stack.func_77960_j() == 2 || stack.func_77960_j() == 3 || stack.func_77960_j() == 5 || stack.func_77960_j() == 6 || stack.func_77960_j() == 7 || stack.func_77960_j() == 8 || stack.func_77960_j() == 9 || stack.func_77960_j() == 13 || stack.func_77960_j() == 14) {
            return super.func_77648_a(stack, player, world, x, y, z, side, par8, par9, par10);
        }
        Block bi = world.func_147439_a(x, y, z);
        int md = world.func_72805_g(x, y, z);
        if (stack.func_77960_j() == 12) {
            if (bi == ConfigBlocks.blockMetalDevice && (md == 10 || md == 11)) {
                return super.func_77648_a(stack, player, world, x, y, z, side, par8, par9, par10);
            }
            return false;
        }
        if (bi == ConfigBlocks.blockMetalDevice && md == 0) {
            if (side == 0 || side == 1) {
                return false;
            }
            if (side == 2) {
                --z;
            }
            if (side == 3) {
                ++z;
            }
            if (side == 4) {
                --x;
            }
            if (side == 5) {
                ++x;
            }
        }
        if (stack.field_77994_a == 0) {
            return false;
        }
        if (!player.func_82247_a(x, y, z, side, stack)) {
            return false;
        }
        if (y == 255 && this.field_150939_a.func_149688_o().func_76220_a()) {
            return false;
        }
        Block var11 = world.func_147439_a(x, y, z);
        if (world.func_147437_c(x, y, z) || var11.isReplaceable((IBlockAccess)world, x, y, z) || var11 == Blocks.field_150395_bd || var11 == Blocks.field_150329_H || var11 == Blocks.field_150330_I || var11 == Blocks.field_150431_aC) {
            for (int a = 2; a < 6; ++a) {
                ForgeDirection dir = ForgeDirection.getOrientation((int)a);
                int xx = x + dir.offsetX;
                int yy = y + dir.offsetY;
                int zz = z + dir.offsetZ;
                Block bid = world.func_147439_a(xx, yy, zz);
                int meta = world.func_72805_g(xx, yy, zz);
                if (bid != ConfigBlocks.blockMetalDevice || meta != 0 || !this.placeBlockAt(stack, player, world, x, y, z, side, par8, par9, par10, stack.func_77960_j())) continue;
                world.func_72908_a((double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), this.field_150939_a.field_149762_H.func_150498_e(), (this.field_150939_a.field_149762_H.func_150497_c() + 1.0f) / 2.0f, this.field_150939_a.field_149762_H.func_150494_d() * 0.8f);
                --stack.field_77994_a;
                world.func_147465_d(x, y, z, ConfigBlocks.blockMetalDevice, dir.getOpposite().ordinal() - 1, 3);
                return true;
            }
        }
        return false;
    }

    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        TileVisRelay tile;
        boolean ret = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
        if (metadata == 7) {
            TileArcaneLamp tile2 = (TileArcaneLamp)world.func_147438_o(x, y, z);
            if (tile2 != null && tile2 instanceof TileArcaneLamp) {
                tile2.facing = ForgeDirection.getOrientation((int)side).getOpposite();
                world.func_147471_g(x, y, x);
            }
        } else if (metadata == 8) {
            TileArcaneLampGrowth tile3 = (TileArcaneLampGrowth)world.func_147438_o(x, y, z);
            if (tile3 != null && tile3 instanceof TileArcaneLampGrowth) {
                tile3.facing = ForgeDirection.getOrientation((int)side).getOpposite();
                world.func_147471_g(x, y, x);
            }
        } else if (metadata == 12) {
            TileBrainbox tile4 = (TileBrainbox)world.func_147438_o(x, y, z);
            if (tile4 != null && tile4 instanceof TileBrainbox) {
                tile4.facing = ForgeDirection.getOrientation((int)side).getOpposite();
                world.func_147471_g(x, y, x);
            }
        } else if (metadata == 13) {
            TileArcaneLampFertility tile5 = (TileArcaneLampFertility)world.func_147438_o(x, y, z);
            if (tile5 != null && tile5 instanceof TileArcaneLampFertility) {
                tile5.facing = ForgeDirection.getOrientation((int)side).getOpposite();
                world.func_147471_g(x, y, x);
            }
        } else if (metadata == 14 && (tile = (TileVisRelay)world.func_147438_o(x, y, z)) != null && tile instanceof TileVisRelay) {
            tile.orientation = (short)side;
            world.func_147471_g(x, y, x);
        }
        return ret;
    }
}

