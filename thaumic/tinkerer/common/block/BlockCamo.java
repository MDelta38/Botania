/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDirectional
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package thaumic.tinkerer.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumic.tinkerer.common.block.BlockModContainer;
import thaumic.tinkerer.common.block.tile.TileCamo;

public abstract class BlockCamo
extends BlockModContainer<TileCamo> {
    static List<Integer> validRenderTypes = Arrays.asList(0, 31, 39);

    protected BlockCamo(Material par2Material) {
        super(par2Material);
    }

    public IIcon func_149673_e(IBlockAccess world, int x, int y, int z, int side) {
        TileEntity tile = world.func_147438_o(x, y, z);
        int meta = world.func_72805_g(x, y, z);
        if (tile instanceof TileCamo) {
            TileCamo camo = (TileCamo)tile;
            Block block = camo.camo;
            if (block != null && this.isValidRenderType(block.func_149645_b())) {
                return block.func_149691_a(side, camo.camoMeta);
            }
        }
        return this.getIconFromSideAfterCheck(tile, meta, side);
    }

    public boolean isValidRenderType(int type) {
        return validRenderTypes.contains(type);
    }

    public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        TileEntity tile = par1World.func_147438_o(par2, par3, par4);
        if (tile instanceof TileCamo) {
            TileCamo camo = (TileCamo)tile;
            ItemStack currentStack = par5EntityPlayer.func_71045_bC();
            boolean doChange = true;
            Block block = null;
            if (currentStack != null) {
                if (Item.func_150891_b((Item)currentStack.func_77973_b()) == 0) {
                    doChange = false;
                } else {
                    block = Block.func_149634_a((Item)currentStack.func_77973_b());
                    if (block == null || !this.isValidRenderType(block.func_149645_b()) || block instanceof BlockCamo || block.func_149688_o() == Material.field_151579_a) {
                        doChange = false;
                    }
                }
            }
            if (doChange) {
                int metadata;
                int n = metadata = currentStack == null ? 0 : currentStack.func_77960_j();
                if (block instanceof BlockDirectional) {
                    switch (par6) {
                        case 0: 
                        case 1: {
                            break;
                        }
                        case 2: {
                            metadata = metadata & 0xC | 2;
                            break;
                        }
                        case 3: {
                            metadata &= 0xC;
                            break;
                        }
                        case 4: {
                            metadata = metadata & 0xC | 1;
                            break;
                        }
                        case 5: {
                            metadata = metadata & 0xC | 3;
                        }
                    }
                }
                camo.camo = currentStack == null ? Blocks.field_150350_a : Block.func_149634_a((Item)currentStack.func_77973_b());
                camo.camoMeta = metadata;
                par1World.func_147471_g(par2, par3, par4);
                return true;
            }
        }
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149720_d(IBlockAccess par1World, int par2, int par3, int par4) {
        TileEntity tile = par1World.func_147438_o(par2, par3, par4);
        if (tile instanceof TileCamo) {
            TileCamo camo = (TileCamo)tile;
            Block block = camo.camo;
            if (block != null) {
                return block instanceof BlockCamo ? 0xFFFFFF : block.func_149720_d(par1World, par2, par3, par4);
            }
        }
        return 0xFFFFFF;
    }

    public IIcon getIconFromSideAfterCheck(TileEntity tile, int meta, int side) {
        return this.field_149761_L;
    }

    @Override
    public Class<? extends ItemBlock> getItemBlock() {
        return null;
    }

    @Override
    public Class<? extends TileEntity> getTileEntity() {
        return null;
    }
}

