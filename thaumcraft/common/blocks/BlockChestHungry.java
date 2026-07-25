/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.BlockPistonBase
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.tiles.TileChestHungry;

public class BlockChestHungry
extends BlockContainer {
    private Random random = new Random();
    public IIcon icon;

    public BlockChestHungry() {
        super(Material.field_151575_d);
        this.func_149711_c(2.5f);
        this.func_149672_a(field_149766_f);
        this.func_149647_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:woodplain");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        return this.icon;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return ConfigBlocks.blockChestHungryRI;
    }

    public boolean func_149740_M() {
        return true;
    }

    public int func_149736_g(World world, int x, int y, int z, int rs) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null && te instanceof IInventory) {
            return Container.func_94526_b((IInventory)((IInventory)te));
        }
        return 0;
    }

    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack is) {
        int var6 = par1World.func_72805_g(par2, par3, par4) & 3;
        int var7 = BlockPistonBase.func_150071_a((World)par1World, (int)par2, (int)par3, (int)par4, (EntityLivingBase)((EntityPlayer)par5EntityLiving));
        par1World.func_147465_d(par2, par3, par4, (Block)this, var7, 3);
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        TileChestHungry var7 = (TileChestHungry)par1World.func_147438_o(par2, par3, par4);
        if (var7 != null) {
            for (int var8 = 0; var8 < var7.func_70302_i_(); ++var8) {
                ItemStack var9 = var7.func_70301_a(var8);
                if (var9 == null) continue;
                float var10 = this.random.nextFloat() * 0.8f + 0.1f;
                float var11 = this.random.nextFloat() * 0.8f + 0.1f;
                float var12 = this.random.nextFloat() * 0.8f + 0.1f;
                while (var9.field_77994_a > 0) {
                    int var13 = this.random.nextInt(21) + 10;
                    if (var13 > var9.field_77994_a) {
                        var13 = var9.field_77994_a;
                    }
                    var9.field_77994_a -= var13;
                    EntityItem var14 = new EntityItem(par1World, (double)((float)par2 + var10), (double)((float)par3 + var11), (double)((float)par4 + var12), new ItemStack(var9.func_77973_b(), var13, var9.func_77960_j()));
                    float var15 = 0.05f;
                    var14.field_70159_w = (float)this.random.nextGaussian() * var15;
                    var14.field_70181_x = (float)this.random.nextGaussian() * var15 + 0.2f;
                    var14.field_70179_y = (float)this.random.nextGaussian() * var15;
                    if (var9.func_77942_o()) {
                        var14.func_92059_d().func_77982_d((NBTTagCompound)var9.func_77978_p().func_74737_b());
                    }
                    par1World.func_72838_d((Entity)var14);
                }
            }
        }
        super.func_149749_a(par1World, par2, par3, par4, par5, par6);
    }

    public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
        float var5 = 0.0625f;
        return AxisAlignedBB.func_72330_a((double)((float)par2 + var5), (double)par3, (double)((float)par4 + var5), (double)((float)(par2 + 1) - var5), (double)((float)(par3 + 1) - var5), (double)((float)(par4 + 1) - var5));
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        this.func_149676_a(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.875f, 0.9375f);
    }

    public void func_149670_a(World world, int x, int y, int z, Entity entity) {
        TileChestHungry var10 = (TileChestHungry)world.func_147438_o(x, y, z);
        if (var10 == null) {
            return;
        }
        if (world.field_72995_K) {
            return;
        }
        if (entity instanceof EntityItem && !entity.field_70128_L) {
            ItemStack leftovers = InventoryUtils.placeItemStackIntoInventory(((EntityItem)entity).func_92059_d(), var10, 1, true);
            if (leftovers == null || leftovers.field_77994_a != ((EntityItem)entity).func_92059_d().field_77994_a) {
                world.func_72956_a(entity, "random.eat", 0.25f, (world.field_73012_v.nextFloat() - world.field_73012_v.nextFloat()) * 0.2f + 1.0f);
                world.func_147452_c(x, y, z, ConfigBlocks.blockChestHungry, 2, 2);
            }
            if (leftovers != null) {
                ((EntityItem)entity).func_92058_a(leftovers);
            } else {
                entity.func_70106_y();
            }
            var10.func_70296_d();
        }
    }

    public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        TileChestHungry var10 = (TileChestHungry)par1World.func_147438_o(par2, par3, par4);
        if (var10 == null) {
            return true;
        }
        if (par1World.field_72995_K) {
            return true;
        }
        par5EntityPlayer.func_71007_a((IInventory)var10);
        return true;
    }

    public TileEntity func_149915_a(World par1World, int m) {
        return new TileChestHungry();
    }
}

