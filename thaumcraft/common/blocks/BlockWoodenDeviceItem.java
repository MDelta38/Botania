/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockPistonBase
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileArcaneBore;
import thaumcraft.common.tiles.TileArcaneBoreBase;
import thaumcraft.common.tiles.TileBanner;
import thaumcraft.common.tiles.TileBellows;

public class BlockWoodenDeviceItem
extends ItemBlock {
    public BlockWoodenDeviceItem(Block par1) {
        super(par1);
        this.func_77656_e(0);
        this.func_77627_a(true);
    }

    public int func_77647_b(int par1) {
        return par1;
    }

    public String func_77667_c(ItemStack stack) {
        if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("color")) {
            return super.func_77658_a() + "." + stack.func_77960_j() + "." + stack.field_77990_d.func_74771_c("color");
        }
        return super.func_77658_a() + "." + stack.func_77960_j();
    }

    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
        boolean ret = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
        if (ret) {
            TileEntity tile;
            if (metadata == 0 && (tile = world.func_147438_o(x, y, z)) != null && tile instanceof TileBellows) {
                ForgeDirection dir = ForgeDirection.getOrientation((int)side).getOpposite();
                ((TileBellows)tile).orientation = (byte)dir.ordinal();
                int xx = x + dir.offsetX;
                int yy = y + dir.offsetY;
                int zz = z + dir.offsetZ;
                Block bi = world.func_147439_a(xx, yy, zz);
                if (bi == Blocks.field_150460_al || bi == Blocks.field_150470_am) {
                    ((TileBellows)tile).onVanillaFurnace = true;
                }
                tile.func_70296_d();
                world.func_147471_g(x, y, x);
            }
            if (metadata == 4 && (tile = (TileArcaneBoreBase)world.func_147438_o(x, y, z)) != null && tile instanceof TileArcaneBoreBase) {
                int var6 = MathHelper.func_76128_c((double)((double)(player.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
                switch (var6) {
                    case 0: {
                        tile.orientation = ForgeDirection.getOrientation((int)2);
                        break;
                    }
                    case 1: {
                        tile.orientation = ForgeDirection.getOrientation((int)5);
                        break;
                    }
                    case 2: {
                        tile.orientation = ForgeDirection.getOrientation((int)3);
                        break;
                    }
                    case 3: {
                        tile.orientation = ForgeDirection.getOrientation((int)4);
                    }
                }
                tile.func_70296_d();
                world.func_147471_g(x, y, x);
            }
            if (metadata == 5 && (tile = (TileArcaneBore)world.func_147438_o(x, y, z)) != null && tile instanceof TileArcaneBore) {
                tile.baseOrientation = ForgeDirection.getOrientation((int)side);
                int var6 = BlockPistonBase.func_150071_a((World)world, (int)x, (int)y, (int)z, (EntityLivingBase)player);
                tile.orientation = ForgeDirection.getOrientation((int)var6);
                world.func_147471_g(x, y, x);
                tile.func_70296_d();
            }
            if (metadata == 8 && (tile = (TileBanner)world.func_147438_o(x, y, z)) != null) {
                if (side <= 1) {
                    int i = MathHelper.func_76128_c((double)((double)((player.field_70177_z + 180.0f) * 16.0f / 360.0f) + 0.5)) & 0xF;
                    tile.setFacing((byte)i);
                } else {
                    tile.setWall(true);
                    int i = 0;
                    if (side == 2) {
                        i = 8;
                    }
                    if (side == 4) {
                        i = 4;
                    }
                    if (side == 5) {
                        i = 12;
                    }
                    tile.setFacing((byte)i);
                }
                if (stack.func_77942_o()) {
                    if (stack.field_77990_d.func_74779_i("aspect") != null) {
                        tile.setAspect(Aspect.getAspect(stack.field_77990_d.func_74779_i("aspect")));
                    }
                    if (stack.field_77990_d.func_74764_b("color")) {
                        tile.setColor(stack.field_77990_d.func_74771_c("color"));
                    }
                }
                tile.func_70296_d();
                world.func_147471_g(x, y, z);
            }
        }
        return ret;
    }

    public boolean func_150936_a(World world, int x, int y, int z, int side, EntityPlayer par6EntityPlayer, ItemStack par7ItemStack) {
        if (par7ItemStack.func_77960_j() == 5) {
            if (side > 1) {
                return false;
            }
            if (world.func_147439_a(x, y, z) != ConfigBlocks.blockWoodenDevice && world.func_72805_g(x, y, z) != 4) {
                return false;
            }
        }
        return super.func_150936_a(world, x, y, z, side, par6EntityPlayer, par7ItemStack);
    }
}

