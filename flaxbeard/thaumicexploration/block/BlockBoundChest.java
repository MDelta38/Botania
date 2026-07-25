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
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.passive.EntityOcelot
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package flaxbeard.thaumicexploration.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.tile.TileEntityBoundChest;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockBoundChest
extends BlockContainer {
    private final Random random = new Random();
    public final int chestType;

    public BlockBoundChest(int par1, int par2) {
        super(Material.field_151575_d);
        this.chestType = par2;
        this.func_149647_a(CreativeTabs.field_78031_c);
        this.func_149676_a(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.875f, 0.9375f);
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return 22;
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        this.func_149676_a(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.875f, 0.9375f);
    }

    public void func_149726_b(World par1World, int par2, int par3, int par4) {
        super.func_149726_b(par1World, par2, par3, par4);
    }

    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        int b0 = 0;
        int l1 = MathHelper.func_76128_c((double)((double)(par5EntityLivingBase.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        if (l1 == 0) {
            b0 = 2;
        }
        if (l1 == 1) {
            b0 = 5;
        }
        if (l1 == 2) {
            b0 = 3;
        }
        if (l1 == 3) {
            b0 = 4;
        }
        par1World.func_72921_c(par2, par3, par4, b0, 3);
        if (par6ItemStack.func_82837_s()) {
            ((TileEntityBoundChest)par1World.func_147438_o(par2, par3, par4)).setChestGuiName(par6ItemStack.func_82833_r());
        }
    }

    public void unifyAdjacentChests(World par1World, int par2, int par3, int par4) {
    }

    public boolean func_149742_c(World par1World, int par2, int par3, int par4) {
        return true;
    }

    public Item func_149650_a(int par1, Random par2Random, int par3) {
        return Item.func_150898_a((Block)Blocks.field_150486_ae);
    }

    public Item func_149694_d(World par1World, int par2, int par3, int par4) {
        return Item.func_150898_a((Block)Blocks.field_150486_ae);
    }

    private boolean isThereANeighborChest(World par1World, int par2, int par3, int par4) {
        return par1World.func_147439_a(par2, par3, par4) != this ? false : (par1World.func_147439_a(par2 - 1, par3, par4) == this ? true : (par1World.func_147439_a(par2 + 1, par3, par4) == this ? true : (par1World.func_147439_a(par2, par3, par4 - 1) == this ? true : par1World.func_147439_a(par2, par3, par4 + 1) == this)));
    }

    public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5) {
        super.func_149695_a(par1World, par2, par3, par4, par5);
        TileEntityBoundChest tileentitychest = (TileEntityBoundChest)par1World.func_147438_o(par2, par3, par4);
        if (tileentitychest != null) {
            tileentitychest.func_145836_u();
        }
    }

    public void dropItem(ItemStack itemstack, World par1World, int par2, int par3, int par4) {
        if (itemstack != null) {
            float f = this.random.nextFloat() * 0.8f + 0.1f;
            float f1 = this.random.nextFloat() * 0.8f + 0.1f;
            float f2 = this.random.nextFloat() * 0.8f + 0.1f;
            while (itemstack.field_77994_a > 0) {
                int k1 = this.random.nextInt(21) + 10;
                if (k1 > itemstack.field_77994_a) {
                    k1 = itemstack.field_77994_a;
                }
                itemstack.field_77994_a -= k1;
                EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.func_77973_b(), k1, itemstack.func_77960_j()));
                float f3 = 0.05f;
                entityitem.field_70159_w = (float)this.random.nextGaussian() * f3;
                entityitem.field_70181_x = (float)this.random.nextGaussian() * f3 + 0.2f;
                entityitem.field_70179_y = (float)this.random.nextGaussian() * f3;
                if (itemstack.func_77942_o()) {
                    entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
                }
                par1World.func_72838_d((Entity)entityitem);
            }
        }
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        TileEntityBoundChest tileentitychest = (TileEntityBoundChest)par1World.func_147438_o(par2, par3, par4);
        if (tileentitychest != null) {
            this.dropItem(new ItemStack(ThaumicExploration.blankSeal, 1, 15 - ((TileEntityBoundChest)par1World.func_147438_o(par2, par3, par4)).getSealColor()), par1World, par2, par3, par4);
            for (int j1 = 0; j1 < tileentitychest.func_70302_i_(); ++j1) {
                ItemStack itemstack = tileentitychest.func_70301_a(j1);
                this.dropItem(itemstack, par1World, par2, par3, par4);
            }
            par1World.func_147453_f(par2, par3, par4, par5);
        }
        super.func_149749_a(par1World, par2, par3, par4, par5, par6);
    }

    public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        if (par1World.field_72995_K) {
            return true;
        }
        IInventory iinventory = this.getInventory(par1World, par2, par3, par4);
        if (iinventory != null) {
            par5EntityPlayer.func_71007_a(iinventory);
        }
        return true;
    }

    public IInventory getInventory(World par1World, int par2, int par3, int par4) {
        TileEntityBoundChest object = (TileEntityBoundChest)par1World.func_147438_o(par2, par3, par4);
        if (object == null) {
            return null;
        }
        if (par1World.isSideSolid(par2, par3 + 1, par4, ForgeDirection.DOWN)) {
            return null;
        }
        if (BlockBoundChest.isOcelotBlockingChest(par1World, par2, par3, par4)) {
            return null;
        }
        return object;
    }

    public TileEntity createNewTileEntity(World par1World) {
        TileEntityBoundChest tileentitychest = new TileEntityBoundChest();
        return tileentitychest;
    }

    public boolean func_149744_f() {
        return this.chestType == 1;
    }

    public int func_149709_b(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        if (!this.func_149744_f()) {
            return 0;
        }
        int i1 = ((TileEntityBoundChest)par1IBlockAccess.func_147438_o((int)par2, (int)par3, (int)par4)).numUsingPlayers;
        return MathHelper.func_76125_a((int)i1, (int)0, (int)15);
    }

    public int func_149748_c(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return par5 == 1 ? this.func_149709_b(par1IBlockAccess, par2, par3, par4, par5) : 0;
    }

    public static boolean isOcelotBlockingChest(World par0World, int par1, int par2, int par3) {
        EntityOcelot entityocelot1;
        EntityOcelot entityocelot;
        Iterator iterator = par0World.func_72872_a(EntityOcelot.class, AxisAlignedBB.func_72330_a((double)par1, (double)(par2 + 1), (double)par3, (double)(par1 + 1), (double)(par2 + 2), (double)(par3 + 1))).iterator();
        do {
            if (iterator.hasNext()) continue;
            return false;
        } while (!(entityocelot = (entityocelot1 = (EntityOcelot)iterator.next())).func_70906_o());
        return true;
    }

    public boolean func_149740_M() {
        return true;
    }

    public int func_149736_g(World par1World, int par2, int par3, int par4, int par5) {
        return Container.func_94526_b((IInventory)this.getInventory(par1World, par2, par3, par4));
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.field_149761_L = par1IconRegister.func_94245_a("planks_oak");
    }

    public TileEntity func_149915_a(World var1, int var2) {
        TileEntityBoundChest tileentitychest = new TileEntityBoundChest();
        return tileentitychest;
    }
}

