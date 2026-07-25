/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.ColorizerFoliage
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IShearable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;

public class BlockMagicalLeaves
extends Block
implements IShearable {
    public static final String[] leafType = new String[]{"greatwood", "silverwood"};
    int[] adjacentTreeBlocks;
    public IIcon[] icon = new IIcon[4];

    public BlockMagicalLeaves() {
        super(Material.field_151584_j);
        this.func_149675_a(true);
        this.func_149647_a(Thaumcraft.tabTC);
        this.func_149711_c(0.2f);
        this.func_149713_g(1);
        this.func_149672_a(field_149779_h);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon[0] = ir.func_94245_a("thaumcraft:greatwoodleaves");
        this.icon[1] = ir.func_94245_a("thaumcraft:greatwoodleaveslow");
        this.icon[2] = ir.func_94245_a("thaumcraft:silverwoodleaves");
        this.icon[3] = ir.func_94245_a("thaumcraft:silverwoodleaveslow");
    }

    public IIcon func_149691_a(int par1, int par2) {
        int idx = !Blocks.field_150362_t.func_149662_c() ? 0 : 1;
        return (par2 & 1) == 1 ? this.icon[idx + 2] : this.icon[idx];
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        Block var6 = par1IBlockAccess.func_147439_a(par2, par3, par4);
        return Blocks.field_150362_t.func_149662_c() && var6 == this ? false : super.func_149646_a(par1IBlockAccess, par2, par3, par4, par5);
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149635_D() {
        double var1 = 0.5;
        double var3 = 1.0;
        return ColorizerFoliage.func_77470_a((double)var1, (double)var3);
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149741_i(int par1) {
        return (par1 & 1) == 0 ? ColorizerFoliage.func_77468_c() : 0x8899AA;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149720_d(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int var5 = par1IBlockAccess.func_72805_g(par2, par3, par4);
        if ((var5 & 1) == 1) {
            return 0x8899AA;
        }
        int var6 = 0;
        int var7 = 0;
        int var8 = 0;
        for (int var9 = -1; var9 <= 1; ++var9) {
            for (int var10 = -1; var10 <= 1; ++var10) {
                int var11 = par1IBlockAccess.func_72807_a(par2 + var10, par4 + var9).func_150571_c(par2, par3, par4);
                var6 += (var11 & 0xFF0000) >> 16;
                var7 += (var11 & 0xFF00) >> 8;
                var8 += var11 & 0xFF;
            }
        }
        return (var6 / 9 & 0xFF) << 16 | (var7 / 9 & 0xFF) << 8 | var8 / 9 & 0xFF;
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        if ((world.func_72805_g(x, y, z) & 1) == 1) {
            return 7;
        }
        return super.getLightValue(world, x, y, z);
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        int var7 = 1;
        int var8 = var7 + 1;
        if (par1World.func_72904_c(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8)) {
            for (int var9 = -var7; var9 <= var7; ++var9) {
                for (int var10 = -var7; var10 <= var7; ++var10) {
                    for (int var11 = -var7; var11 <= var7; ++var11) {
                        Block var12 = par1World.func_147439_a(par2 + var9, par3 + var10, par4 + var11);
                        if (var12 == Blocks.field_150350_a) continue;
                        var12.beginLeavesDecay(par1World, par2 + var9, par3 + var10, par4 + var11);
                    }
                }
            }
        }
    }

    public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random) {
        int var6;
        if (!par1World.field_72995_K && ((var6 = par1World.func_72805_g(par2, par3, par4)) & 8) != 0 && (var6 & 4) == 0) {
            int var12;
            int var7 = 4;
            int var8 = var7 + 1;
            int var9 = 32;
            int var10 = var9 * var9;
            int var11 = var9 / 2;
            if (this.adjacentTreeBlocks == null) {
                this.adjacentTreeBlocks = new int[var9 * var9 * var9];
            }
            if (par1World.func_72904_c(par2 - var8, par3 - var8, par4 - var8, par2 + var8, par3 + var8, par4 + var8)) {
                int var14;
                int var13;
                for (var12 = -var7; var12 <= var7; ++var12) {
                    for (var13 = -var7; var13 <= var7; ++var13) {
                        for (var14 = -var7; var14 <= var7; ++var14) {
                            Block block = par1World.func_147439_a(par2 + var12, par3 + var13, par4 + var14);
                            this.adjacentTreeBlocks[(var12 + var11) * var10 + (var13 + var11) * var9 + var14 + var11] = block != null && block.canSustainLeaves((IBlockAccess)par1World, par2 + var12, par3 + var13, par4 + var14) ? 0 : (block != null && block.isLeaves((IBlockAccess)par1World, par2 + var12, par3 + var13, par4 + var14) ? -2 : -1);
                        }
                    }
                }
                int var15 = 0;
                for (var12 = 1; var12 <= 4; ++var12) {
                    for (var13 = -var7; var13 <= var7; ++var13) {
                        for (var14 = -var7; var14 <= var7; ++var14) {
                            for (var15 = -var7; var15 <= var7; ++var15) {
                                if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11] != var12 - 1) continue;
                                if (this.adjacentTreeBlocks[(var13 + var11 - 1) * var10 + (var14 + var11) * var9 + var15 + var11] == -2) {
                                    this.adjacentTreeBlocks[(var13 + var11 - 1) * var10 + (var14 + var11) * var9 + var15 + var11] = var12;
                                }
                                if (this.adjacentTreeBlocks[(var13 + var11 + 1) * var10 + (var14 + var11) * var9 + var15 + var11] == -2) {
                                    this.adjacentTreeBlocks[(var13 + var11 + 1) * var10 + (var14 + var11) * var9 + var15 + var11] = var12;
                                }
                                if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 - 1) * var9 + var15 + var11] == -2) {
                                    this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 - 1) * var9 + var15 + var11] = var12;
                                }
                                if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 + 1) * var9 + var15 + var11] == -2) {
                                    this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11 + 1) * var9 + var15 + var11] = var12;
                                }
                                if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + (var15 + var11 - 1)] == -2) {
                                    this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + (var15 + var11 - 1)] = var12;
                                }
                                if (this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 + 1] != -2) continue;
                                this.adjacentTreeBlocks[(var13 + var11) * var10 + (var14 + var11) * var9 + var15 + var11 + 1] = var12;
                            }
                        }
                    }
                }
            }
            if ((var12 = this.adjacentTreeBlocks[var11 * var10 + var11 * var9 + var11]) >= 0) {
                par1World.func_147465_d(par2, par3, par4, (Block)this, var6 & 0xFFFFFFF7, 3);
            } else {
                this.removeLeaves(par1World, par2, par3, par4);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random) {
        int md;
        if (par1World.func_72951_B(par2, par3 + 1, par4)) {
            if (!World.func_147466_a((IBlockAccess)par1World, (int)par2, (int)(par3 - 1), (int)par4) && par5Random.nextInt(15) == 1) {
                double var6 = (float)par2 + par5Random.nextFloat();
                double var8 = (double)par3 - 0.05;
                double var10 = (float)par4 + par5Random.nextFloat();
                par1World.func_72869_a("dripWater", var6, var8, var10, 0.0, 0.0, 0.0);
            }
        }
        if (((md = par1World.func_72805_g(par2, par3, par4)) & 1) == 1 && par5Random.nextInt(500) == 0) {
            Thaumcraft.proxy.sparkle((float)par2 + 0.5f + par1World.field_73012_v.nextFloat() - par1World.field_73012_v.nextFloat(), (float)par3 + 0.5f + par1World.field_73012_v.nextFloat() - par1World.field_73012_v.nextFloat(), (float)par4 + 0.5f + par1World.field_73012_v.nextFloat() - par1World.field_73012_v.nextFloat(), 2.0f, 7, 0.0f);
        }
    }

    private void removeLeaves(World par1World, int par2, int par3, int par4) {
        this.func_149697_b(par1World, par2, par3, par4, par1World.func_72805_g(par2, par3, par4), 0);
        par1World.func_147468_f(par2, par3, par4);
    }

    public void func_149690_a(World par1World, int par2, int par3, int par4, int meta, float par6, int par7) {
        if (!par1World.field_72995_K && (meta & 8) != 0 && (meta & 4) == 0) {
            if ((meta & 1) == 0 && par1World.field_73012_v.nextInt(200) == 0) {
                this.func_149642_a(par1World, par2, par3, par4, new ItemStack(ConfigBlocks.blockCustomPlant, 1, 0));
            } else if ((meta & 1) == 1 && par1World.field_73012_v.nextInt(250) == 0) {
                this.func_149642_a(par1World, par2, par3, par4, new ItemStack(ConfigBlocks.blockCustomPlant, 1, 1));
            }
        }
    }

    public void func_149636_a(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6) {
        super.func_149636_a(par1World, par2EntityPlayer, par3, par4, par5, par6);
    }

    public int func_149692_a(int par1) {
        return par1 & 1;
    }

    public int func_149745_a(Random par1Random) {
        return 0;
    }

    public Item func_149650_a(int par1, Random par2Random, int par3) {
        return Item.func_150899_d((int)0);
    }

    public boolean func_149662_c() {
        return Blocks.field_150362_t.func_149662_c();
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack((Block)this, 1, world.func_72805_g(x, y, z) & 3));
        return ret;
    }

    public void beginLeavesDecay(World world, int x, int y, int z) {
        world.func_72921_c(x, y, z, world.func_72805_g(x, y, z) | 8, 4);
    }

    public boolean isLeaves(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        int md = world.func_72805_g(x, y, z);
        return new ItemStack((Block)this, 1, md & 1);
    }

    public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 60;
    }

    public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face) {
        return 30;
    }
}

