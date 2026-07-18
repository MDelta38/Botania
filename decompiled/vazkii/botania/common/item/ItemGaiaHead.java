/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockSkull
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntitySkull
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.item;

import net.minecraft.block.BlockSkull;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ItemMod;

public class ItemGaiaHead
extends ItemMod {
    public ItemGaiaHead() {
        this.func_77655_b("gaiaHead");
    }

    public boolean func_77648_a(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float sideX, float sideY, float sideZ) {
        TileEntity tileentity;
        ForgeDirection sideDir = ForgeDirection.getOrientation((int)side);
        if (world.func_147439_a(x, y, z).isReplaceable((IBlockAccess)world, x, y, z) && sideDir != ForgeDirection.DOWN) {
            sideDir = ForgeDirection.UP;
            --y;
        }
        if (sideDir == ForgeDirection.DOWN) {
            return false;
        }
        if (!world.isSideSolid(x, y, z, sideDir)) {
            return false;
        }
        switch (sideDir) {
            case UP: {
                ++y;
                break;
            }
            case NORTH: {
                --z;
                break;
            }
            case SOUTH: {
                ++z;
                break;
            }
            case WEST: {
                --x;
                break;
            }
            case EAST: {
                ++x;
                break;
            }
            default: {
                return false;
            }
        }
        if (world.field_72995_K) {
            return true;
        }
        if (!ModBlocks.gaiaHead.func_149707_d(world, x, y, z, side)) {
            return false;
        }
        world.func_147465_d(x, y, z, ModBlocks.gaiaHead, sideDir.ordinal(), 2);
        int headAngle = 0;
        if (sideDir == ForgeDirection.UP) {
            headAngle = MathHelper.func_76128_c((double)((double)(player.field_70177_z * 16.0f / 360.0f) + 0.5)) & 0xF;
        }
        if ((tileentity = world.func_147438_o(x, y, z)) != null && tileentity instanceof TileEntitySkull) {
            ((TileEntitySkull)tileentity).func_145903_a(headAngle);
            ((BlockSkull)Blocks.field_150465_bP).func_149965_a(world, x, y, z, (TileEntitySkull)tileentity);
        }
        --stack.field_77994_a;
        return true;
    }
}

