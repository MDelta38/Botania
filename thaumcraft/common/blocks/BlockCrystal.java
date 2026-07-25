/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.crafting.IInfusionStabiliser;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXSpark;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.BlockCustomOreItem;
import thaumcraft.common.blocks.CustomStepSound;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.tiles.TileCrystal;
import thaumcraft.common.tiles.TileEldritchCrystal;

public class BlockCrystal
extends BlockContainer
implements IInfusionStabiliser {
    private Random random = new Random();
    public IIcon icon;

    public BlockCrystal() {
        super(Material.field_151592_s);
        this.func_149711_c(0.7f);
        this.func_149752_b(1.0f);
        this.func_149715_a(0.5f);
        this.func_149672_a(new CustomStepSound("crystal", 1.0f, 1.0f));
        this.func_149647_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int var4 = 0; var4 <= 6; ++var4) {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:crystal");
    }

    public IIcon func_149691_a(int par1, int par2) {
        return this.icon;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World world, int i, int j, int k, Random random) {
        int md = world.func_72805_g(i, j, k);
        if (md <= 6 && random.nextInt(17) == 0) {
            FXSpark ef = new FXSpark(world, (double)i + 0.3 + (double)(world.field_73012_v.nextFloat() * 0.4f), (double)j + 0.3 + (double)(world.field_73012_v.nextFloat() * 0.4f), (double)k + 0.3 + (double)(world.field_73012_v.nextFloat() * 0.4f), 0.2f + random.nextFloat() * 0.1f);
            Color c = new Color(md == 6 ? BlockCustomOreItem.colors[random.nextInt(6) + 1] : BlockCustomOreItem.colors[md + 1]);
            ef.func_70538_b((float)c.getRed() / 255.0f, (float)c.getGreen() / 255.0f, (float)c.getBlue() / 255.0f);
            ef.func_82338_g(0.8f);
            ParticleEngine.instance.addEffect(world, ef);
        }
    }

    public int func_149720_d(IBlockAccess par1iBlockAccess, int par2, int par3, int par4) {
        int md = par1iBlockAccess.func_72805_g(par2, par3, par4);
        if (md < 6) {
            return BlockCustomOreItem.colors[md + 1];
        }
        if (md == 6) {
            return BlockCustomOreItem.colors[new Random().nextInt(6) + 1];
        }
        return super.func_149720_d(par1iBlockAccess, par2, par3, par4);
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return ConfigBlocks.blockCrystalRI;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        if (metadata <= 6) {
            return new TileCrystal();
        }
        if (metadata == 7) {
            return new TileEldritchCrystal();
        }
        return super.createTileEntity(world, metadata);
    }

    public TileEntity func_149915_a(World var1, int md) {
        return null;
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int md, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        if (md < 6) {
            for (int t = 0; t < 6; ++t) {
                ret.add(new ItemStack(ConfigItems.itemShard, 1, md));
            }
            return ret;
        }
        if (md == 6) {
            for (int t = 0; t < 6; ++t) {
                ret.add(new ItemStack(ConfigItems.itemShard, 1, t));
            }
            return ret;
        }
        if (md == 7) {
            ret.add(new ItemStack(ConfigItems.itemShard, 1, 6));
            return ret;
        }
        return super.getDrops(world, x, y, z, md, fortune);
    }

    public void func_149695_a(World world, int i, int j, int k, Block l) {
        super.func_149695_a(world, i, j, k, l);
        int md = world.func_72805_g(i, j, k);
        if (md <= 6 && this.checkIfAttachedToBlock(world, i, j, k)) {
            TileCrystal tes = (TileCrystal)world.func_147438_o(i, j, k);
            short i1 = tes.orientation;
            boolean flag = false;
            if (!world.isSideSolid(i - 1, j, k, ForgeDirection.getOrientation((int)5)) && i1 == 5) {
                flag = true;
            }
            if (!world.isSideSolid(i + 1, j, k, ForgeDirection.getOrientation((int)4)) && i1 == 4) {
                flag = true;
            }
            if (!world.isSideSolid(i, j, k - 1, ForgeDirection.getOrientation((int)3)) && i1 == 3) {
                flag = true;
            }
            if (!world.isSideSolid(i, j, k + 1, ForgeDirection.getOrientation((int)2)) && i1 == 2) {
                flag = true;
            }
            if (!world.isSideSolid(i, j - 1, k, ForgeDirection.getOrientation((int)1)) && i1 == 1) {
                flag = true;
            }
            if (!world.isSideSolid(i, j + 1, k, ForgeDirection.getOrientation((int)0)) && i1 == 0) {
                flag = true;
            }
            if (flag) {
                this.func_149697_b(world, i, j, k, world.func_72805_g(i, j, k), 0);
                world.func_147468_f(i, j, k);
            }
            return;
        }
        if (md == 7) {
            TileCrystal tes = (TileCrystal)world.func_147438_o(i, j, k);
            ForgeDirection fd = ForgeDirection.getOrientation((int)tes.orientation).getOpposite();
            if (world.func_147437_c(i + fd.offsetX, j + fd.offsetY, k + fd.offsetZ)) {
                this.func_149697_b(world, i, j, k, world.func_72805_g(i, j, k), 0);
                world.func_147468_f(i, j, k);
            }
            return;
        }
    }

    private boolean checkIfAttachedToBlock(World world, int i, int j, int k) {
        if (!this.func_149742_c(world, i, j, k)) {
            this.func_149697_b(world, i, j, k, world.func_72805_g(i, j, k), 0);
            world.func_147468_f(i, j, k);
            return false;
        }
        return true;
    }

    public boolean func_149707_d(World world, int i, int j, int k, int l) {
        if (l == 0 && world.isSideSolid(i, j + 1, k, ForgeDirection.getOrientation((int)0))) {
            return true;
        }
        if (l == 1 && world.isSideSolid(i, j - 1, k, ForgeDirection.getOrientation((int)1))) {
            return true;
        }
        if (l == 2 && world.isSideSolid(i, j, k + 1, ForgeDirection.getOrientation((int)2))) {
            return true;
        }
        if (l == 3 && world.isSideSolid(i, j, k - 1, ForgeDirection.getOrientation((int)3))) {
            return true;
        }
        if (l == 4 && world.isSideSolid(i + 1, j, k, ForgeDirection.getOrientation((int)4))) {
            return true;
        }
        return l == 5 && world.isSideSolid(i - 1, j, k, ForgeDirection.getOrientation((int)5));
    }

    public boolean func_149742_c(World world, int i, int j, int k) {
        if (world.isSideSolid(i - 1, j, k, ForgeDirection.getOrientation((int)5))) {
            return true;
        }
        if (world.isSideSolid(i + 1, j, k, ForgeDirection.getOrientation((int)4))) {
            return true;
        }
        if (world.isSideSolid(i, j, k - 1, ForgeDirection.getOrientation((int)3))) {
            return true;
        }
        if (world.isSideSolid(i, j, k + 1, ForgeDirection.getOrientation((int)2))) {
            return true;
        }
        if (world.isSideSolid(i, j - 1, k, ForgeDirection.getOrientation((int)1))) {
            return true;
        }
        return world.isSideSolid(i, j + 1, k, ForgeDirection.getOrientation((int)0));
    }

    @Override
    public boolean canStabaliseInfusion(World world, int x, int y, int z) {
        return true;
    }
}

