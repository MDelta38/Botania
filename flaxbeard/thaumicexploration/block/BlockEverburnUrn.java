/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.fluids.FluidContainerRegistry
 *  net.minecraftforge.fluids.FluidStack
 *  net.minecraftforge.fluids.IFluidContainerItem
 */
package flaxbeard.thaumicexploration.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.tile.TileEntityEverburnUrn;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;

public class BlockEverburnUrn
extends BlockContainer {
    public static IIcon middleSide;
    public static IIcon topSide;
    public static IIcon topTop;
    public static IIcon bottomTop;
    public static IIcon bottomBottom;
    public static IIcon topBottom;
    public IIcon texture;

    public BlockEverburnUrn(int par1) {
        super(Material.field_151576_e);
    }

    public TileEntity createNewTileEntity(World par1World) {
        return new TileEntityEverburnUrn();
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IIconRegister) {
        super.func_149651_a(par1IIconRegister);
        middleSide = par1IIconRegister.func_94245_a("thaumicExploration:everfullUrnMS");
        topSide = par1IIconRegister.func_94245_a("thaumicExploration:everfullUrnTS");
        topTop = par1IIconRegister.func_94245_a("thaumicExploration:everfullUrnTT");
        bottomTop = par1IIconRegister.func_94245_a("thaumicExploration:everfullUrnBT");
        bottomBottom = par1IIconRegister.func_94245_a("thaumicExploration:everfullUrnBB");
        topBottom = par1IIconRegister.func_94245_a("thaumicExploration:everfullUrnTB");
    }

    public void func_149719_a(IBlockAccess world, int i, int j, int k) {
        float f = 0.875f;
        float f1 = 0.125f;
        float f2 = 0.5625f;
        float f3 = 0.375f;
        float f4 = 0.3125f;
        this.func_149676_a(f1, 0.0f, f1, f, 1.0f, f);
    }

    public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity) {
        float f = 0.875f;
        float f1 = 0.125f;
        float f2 = 0.5625f;
        float f3 = 0.375f;
        float f4 = 0.3125f;
        this.func_149676_a(f1, 0.0f, f1, f, 1.0f, f);
        super.func_149743_a(world, i, j, k, axisalignedbb, arraylist, par7Entity);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World par1World, int par2, int par3, int par4, Random par5Random) {
        int l = par1World.func_72805_g(par2, par3, par4);
        float f = (float)par2 + 0.5f;
        float f1 = (float)par3 + 1.0f;
        float f2 = (float)par4 + 0.5f;
        float f3 = 0.52f;
        float f4 = par5Random.nextFloat() * 0.6f - 0.3f;
        if (Math.random() < 0.1) {
            // empty if block
        }
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return ThaumicExploration.everfullUrnRenderID;
    }

    public boolean func_149727_a(World world, int par2, int par3, int par4, EntityPlayer entityPlayer, int par6, float par7, float par8, float par9) {
        TileEntityEverburnUrn entity = (TileEntityEverburnUrn)world.func_147438_o(par2, par3, par4);
        if (entityPlayer.field_71071_by.func_70448_g() != null) {
            IFluidContainerItem itm;
            int fill;
            if (FluidContainerRegistry.isEmptyContainer((ItemStack)entityPlayer.field_71071_by.func_70448_g())) {
                ItemStack newStack = entityPlayer.field_71071_by.func_70448_g();
                ItemStack filledStack = FluidContainerRegistry.fillFluidContainer((FluidStack)entity.getFluid(), (ItemStack)newStack);
                if (filledStack != null) {
                    entityPlayer.field_71071_by.func_70298_a(entityPlayer.field_71071_by.field_70461_c, 1);
                    entity.drain(FluidContainerRegistry.getFluidForFilledItem((ItemStack)filledStack).amount, true);
                    if (!entityPlayer.field_71071_by.func_70441_a(filledStack)) {
                        entityPlayer.func_71019_a(filledStack, false);
                    }
                    world.func_72956_a((Entity)entityPlayer, "liquid.swim", 0.5f, 1.0f);
                    world.func_147471_g(par2, par3, par4);
                }
            } else if (entityPlayer.field_71071_by.func_70448_g().func_77973_b() instanceof IFluidContainerItem && (fill = (itm = (IFluidContainerItem)entityPlayer.field_71071_by.func_70448_g().func_77973_b()).fill(entityPlayer.field_71071_by.func_70448_g(), entity.getFluid(), true)) > 0) {
                entity.drain(fill, true);
                world.func_72956_a((Entity)entityPlayer, "liquid.swim", 0.5f, 1.0f);
                world.func_147471_g(par2, par3, par4);
            }
        }
        return true;
    }

    public TileEntity func_149915_a(World var1, int var2) {
        return new TileEntityEverburnUrn();
    }
}

