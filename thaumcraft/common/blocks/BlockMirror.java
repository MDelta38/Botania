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
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagInt
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.DimensionManager
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.CustomStepSound;
import thaumcraft.common.tiles.TileMirror;
import thaumcraft.common.tiles.TileMirrorEssentia;

public class BlockMirror
extends BlockContainer {
    public IIcon icon;
    public IIcon iconEss;

    public BlockMirror() {
        super(Material.field_151592_s);
        this.func_149711_c(1.0f);
        this.func_149752_b(10.0f);
        this.func_149672_a(new CustomStepSound("jar", 0.5f, 2.0f));
        this.func_149647_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:mirrorframe");
        this.iconEss = ir.func_94245_a("thaumcraft:mirrorframe2");
    }

    public IIcon func_149691_a(int i, int m) {
        return m < 6 ? this.icon : this.iconEss;
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 6));
    }

    public TileEntity createTileEntity(World world, int metadata) {
        if (metadata <= 5) {
            new TileMirror();
        }
        if (metadata > 5 && metadata <= 11) {
            return new TileMirrorEssentia();
        }
        return super.createTileEntity(world, metadata);
    }

    public TileEntity func_149915_a(World var1, int md) {
        return new TileMirror();
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return -1;
    }

    public boolean func_149686_d() {
        return false;
    }

    public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
        this.func_149697_b(par1World, par2, par3, par4, par5, 0);
        super.func_149681_a(par1World, par2, par3, par4, par5, par6EntityPlayer);
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        int md = world.func_72805_g(x, y, z);
        if (md < 6) {
            TileMirror tm = (TileMirror)world.func_147438_o(x, y, z);
            ItemStack drop = new ItemStack((Block)this, 1, 0);
            if (tm != null && tm instanceof TileMirror) {
                if (tm.linked) {
                    drop.func_77983_a("linkX", (NBTBase)new NBTTagInt(tm.linkX));
                    drop.func_77983_a("linkY", (NBTBase)new NBTTagInt(tm.linkY));
                    drop.func_77983_a("linkZ", (NBTBase)new NBTTagInt(tm.linkZ));
                    drop.func_77983_a("linkDim", (NBTBase)new NBTTagInt(tm.linkDim));
                    drop.func_77983_a("dimname", (NBTBase)new NBTTagString(DimensionManager.getProvider((int)world.field_73011_w.field_76574_g).func_80007_l()));
                    drop.func_77964_b(1);
                    tm.invalidateLink();
                }
                drops.add(drop);
            }
            return drops;
        }
        TileMirrorEssentia tm = (TileMirrorEssentia)world.func_147438_o(x, y, z);
        ItemStack drop = new ItemStack((Block)this, 1, 6);
        if (tm != null && tm instanceof TileMirrorEssentia) {
            if (tm.linked) {
                drop.func_77983_a("linkX", (NBTBase)new NBTTagInt(tm.linkX));
                drop.func_77983_a("linkY", (NBTBase)new NBTTagInt(tm.linkY));
                drop.func_77983_a("linkZ", (NBTBase)new NBTTagInt(tm.linkZ));
                drop.func_77983_a("linkDim", (NBTBase)new NBTTagInt(tm.linkDim));
                drop.func_77983_a("dimname", (NBTBase)new NBTTagString(DimensionManager.getProvider((int)world.field_73011_w.field_76574_g).func_80007_l()));
                drop.func_77964_b(7);
                tm.invalidateLink();
            }
            drops.add(drop);
        }
        return drops;
    }

    public void func_149670_a(World world, int x, int y, int z, Entity entity) {
        TileMirror taf;
        int md = world.func_72805_g(x, y, z);
        if (md < 6 && !world.field_72995_K && entity instanceof EntityItem && !entity.field_70128_L && ((EntityItem)entity).field_71088_bW == 0 && (taf = (TileMirror)world.func_147438_o(x, y, z)) != null) {
            taf.transport((EntityItem)entity);
        }
    }

    public int func_149660_a(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9) {
        if (par9 > 6) {
            par9 = 6;
        } else if (par9 > 0 && par9 < 6) {
            par9 = 0;
        }
        return par9 + par5;
    }

    public void func_149695_a(World world, int i, int j, int k, Block l) {
        if (!world.field_72995_K) {
            int i1 = world.func_72805_g(i, j, k);
            boolean flag = false;
            if (!world.isSideSolid(i - 1, j, k, ForgeDirection.getOrientation((int)5)) && i1 % 6 == 5) {
                flag = true;
            }
            if (!world.isSideSolid(i + 1, j, k, ForgeDirection.getOrientation((int)4)) && i1 % 6 == 4) {
                flag = true;
            }
            if (!world.isSideSolid(i, j, k - 1, ForgeDirection.getOrientation((int)3)) && i1 % 6 == 3) {
                flag = true;
            }
            if (!world.isSideSolid(i, j, k + 1, ForgeDirection.getOrientation((int)2)) && i1 % 6 == 2) {
                flag = true;
            }
            if (!world.isSideSolid(i, j - 1, k, ForgeDirection.getOrientation((int)1)) && i1 % 6 == 1) {
                flag = true;
            }
            if (!world.isSideSolid(i, j + 1, k, ForgeDirection.getOrientation((int)0)) && i1 % 6 == 0) {
                flag = true;
            }
            if (flag) {
                this.func_149697_b(world, i, j, k, i1, 0);
                world.func_147468_f(i, j, k);
            }
        }
    }

    private boolean checkIfAttachedToBlock(World world, int i, int j, int k) {
        return this.func_149742_c(world, i, j, k);
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

    public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        return true;
    }

    public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
        return null;
    }

    public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4) {
        this.func_149719_a((IBlockAccess)par1World, par2, par3, par4);
        return super.func_149633_g(par1World, par2, par3, par4);
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        this.setBlockBoundsForBlockRender(par1IBlockAccess.func_72805_g(par2, par3, par4));
    }

    public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity) {
    }

    public void setBlockBoundsForBlockRender(int par1) {
        float w = 0.0625f;
        switch (par1 % 6) {
            case 0: {
                this.func_149676_a(0.0f, 1.0f - w, 0.0f, 1.0f, 1.0f, 1.0f);
                break;
            }
            case 1: {
                this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, w, 1.0f);
                break;
            }
            case 2: {
                this.func_149676_a(0.0f, 0.0f, 1.0f - w, 1.0f, 1.0f, 1.0f);
                break;
            }
            case 3: {
                this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, w);
                break;
            }
            case 4: {
                this.func_149676_a(1.0f - w, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                break;
            }
            case 5: {
                this.func_149676_a(0.0f, 0.0f, 0.0f, w, 1.0f, 1.0f);
            }
        }
    }
}

