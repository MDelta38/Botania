/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  thaumcraft.common.blocks.JarStepSound
 */
package flaxbeard.thaumicexploration.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.tile.TileEntityThinkTank;
import java.util.Random;
import javax.swing.Icon;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import thaumcraft.common.blocks.JarStepSound;

public class BlockThinkTank
extends BlockContainer {
    private final Random furnaceRand = new Random();
    private final boolean isActive;
    private static boolean keepFurnaceInventory;
    @SideOnly(value=Side.CLIENT)
    private Icon furnaceIconTop;
    @SideOnly(value=Side.CLIENT)
    private Icon furnaceIconFront;

    public BlockThinkTank(int par1, boolean par2) {
        super(Material.field_151592_s);
        this.isActive = par2;
        this.func_149672_a((Block.SoundType)new JarStepSound("jar", 1.0f, 1.0f));
        this.func_149711_c(0.3f);
        this.func_149715_a(0.66f);
    }

    public Item func_149650_a(int par1, Random par2Random, int par3) {
        return Item.func_150898_a((Block)ThaumicExploration.thinkTankJar);
    }

    public void func_149726_b(World par1World, int par2, int par3, int par4) {
        super.func_149726_b(par1World, par2, par3, par4);
        this.setDefaultDirection(par1World, par2, par3, par4);
    }

    private void setDefaultDirection(World par1World, int par2, int par3, int par4) {
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return -1;
    }

    public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        if (par1World.field_72995_K) {
            return true;
        }
        TileEntityThinkTank tileentityfurnace = (TileEntityThinkTank)par1World.func_147438_o(par2, par3, par4);
        if (tileentityfurnace != null) {
            par5EntityPlayer.openGui((Object)ThaumicExploration.instance, 0, par1World, par2, par3, par4);
        }
        return true;
    }

    public static void updateFurnaceBlockState(boolean par0, World par1World, int par2, int par3, int par4) {
        int l = par1World.func_72805_g(par2, par3, par4);
        TileEntity tileentity = par1World.func_147438_o(par2, par3, par4);
        keepFurnaceInventory = true;
        keepFurnaceInventory = false;
        par1World.func_72921_c(par2, par3, par4, l, 2);
        if (tileentity != null) {
            tileentity.func_145829_t();
            par1World.func_147455_a(par2, par3, par4, tileentity);
        }
    }

    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        int l = MathHelper.func_76128_c((double)((double)(par5EntityLivingBase.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        if (l == 0) {
            par1World.func_72921_c(par2, par3, par4, 2, 2);
        }
        if (l == 1) {
            par1World.func_72921_c(par2, par3, par4, 5, 2);
        }
        if (l == 2) {
            par1World.func_72921_c(par2, par3, par4, 3, 2);
        }
        if (l == 3) {
            par1World.func_72921_c(par2, par3, par4, 4, 2);
        }
        if (par6ItemStack.func_82837_s()) {
            ((TileEntityThinkTank)par1World.func_147438_o(par2, par3, par4)).setGuiDisplayName(par6ItemStack.func_82833_r());
        }
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        TileEntityThinkTank tileentityfurnace;
        if (!keepFurnaceInventory && (tileentityfurnace = (TileEntityThinkTank)par1World.func_147438_o(par2, par3, par4)) != null) {
            for (int j1 = 0; j1 < tileentityfurnace.func_70302_i_(); ++j1) {
                ItemStack itemstack = tileentityfurnace.func_70301_a(j1);
                if (itemstack == null) continue;
                float f = this.furnaceRand.nextFloat() * 0.8f + 0.1f;
                float f1 = this.furnaceRand.nextFloat() * 0.8f + 0.1f;
                float f2 = this.furnaceRand.nextFloat() * 0.8f + 0.1f;
                while (itemstack.field_77994_a > 0) {
                    int k1 = this.furnaceRand.nextInt(21) + 10;
                    if (k1 > itemstack.field_77994_a) {
                        k1 = itemstack.field_77994_a;
                    }
                    itemstack.field_77994_a -= k1;
                    EntityItem entityitem = new EntityItem(par1World, (double)((float)par2 + f), (double)((float)par3 + f1), (double)((float)par4 + f2), new ItemStack(itemstack.func_77973_b(), k1, itemstack.func_77960_j()));
                    if (itemstack.func_77942_o()) {
                        entityitem.func_92059_d().func_77982_d((NBTTagCompound)itemstack.func_77978_p().func_74737_b());
                    }
                    float f3 = 0.05f;
                    entityitem.field_70159_w = (float)this.furnaceRand.nextGaussian() * f3;
                    entityitem.field_70181_x = (float)this.furnaceRand.nextGaussian() * f3 + 0.2f;
                    entityitem.field_70179_y = (float)this.furnaceRand.nextGaussian() * f3;
                    par1World.func_72838_d((Entity)entityitem);
                }
            }
            par1World.func_147453_f(par2, par3, par4, par5);
        }
        super.func_149749_a(par1World, par2, par3, par4, par5, par6);
    }

    public boolean func_149740_M() {
        return true;
    }

    public int func_149736_g(World par1World, int par2, int par3, int par4, int par5) {
        return Container.func_94526_b((IInventory)((IInventory)par1World.func_147438_o(par2, par3, par4)));
    }

    public int func_149745_a(Random par1Random) {
        return 1;
    }

    public TileEntity func_149915_a(World var1, int var2) {
        return new TileEntityThinkTank();
    }
}

