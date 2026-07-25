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
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.crafting.IInfusionStabiliser;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.utils.Utils;

public class BlockCandle
extends Block
implements IInfusionStabiliser {
    public IIcon icon;
    public IIcon iconStub;

    public BlockCandle() {
        super(Material.field_151594_q);
        this.func_149711_c(0.1f);
        this.func_149672_a(field_149775_l);
        this.func_149647_a(Thaumcraft.tabTC);
        this.func_149715_a(0.95f);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int var4 = 0; var4 < 16; ++var4) {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:candle");
        this.iconStub = ir.func_94245_a("thaumcraft:candlestub");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        return this.icon;
    }

    public int func_149741_i(int par1) {
        return Utils.colors[par1];
    }

    public boolean func_149742_c(World par1World, int par2, int par3, int par4) {
        return World.func_147466_a((IBlockAccess)par1World, (int)par2, (int)par3, (int)par4);
    }

    public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5) {
        int var6 = par1World.func_72805_g(par2, par3, par4);
        boolean var7 = this.func_149742_c(par1World, par2, par3 - 1, par4);
        if (!var7) {
            this.func_149697_b(par1World, par2, par3, par4, var6, 0);
            par1World.func_147468_f(par2, par3, par4);
        }
        super.func_149695_a(par1World, par2, par3, par4, par5);
    }

    public boolean func_149707_d(World par1World, int par2, int par3, int par4, int par5) {
        return this.func_149742_c(par1World, par2, par3 - 1, par4);
    }

    public int func_149720_d(IBlockAccess par1iBlockAccess, int par2, int par3, int par4) {
        int md = par1iBlockAccess.func_72805_g(par2, par3, par4);
        return Utils.colors[md];
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    public void func_149719_a(IBlockAccess par1iBlockAccess, int par2, int par3, int par4) {
        this.func_149676_a(0.375f, 0.0f, 0.375f, 0.625f, 0.5f, 0.625f);
        super.func_149719_a(par1iBlockAccess, par2, par3, par4);
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return false;
    }

    public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
        return null;
    }

    public int func_149645_b() {
        return ConfigBlocks.blockCandleRI;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random) {
        double var7 = (float)par2 + 0.5f;
        double var9 = (float)par3 + 0.7f;
        double var11 = (float)par4 + 0.5f;
        par1World.func_72869_a("smoke", var7, var9, var11, 0.0, 0.0, 0.0);
        par1World.func_72869_a("flame", var7, var9, var11, 0.0, 0.0, 0.0);
    }

    @Override
    public boolean canStabaliseInfusion(World world, int x, int y, int z) {
        return true;
    }
}

