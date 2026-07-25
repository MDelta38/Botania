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
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.tiles.TileArcaneWorkbench;
import thaumcraft.common.tiles.TileDeconstructionTable;
import thaumcraft.common.tiles.TileResearchTable;
import thaumcraft.common.tiles.TileTable;

public class BlockTable
extends BlockContainer
implements IWandable {
    public IIcon icon;
    public IIcon iconQuill;

    public BlockTable() {
        super(Material.field_151575_d);
        this.func_149711_c(2.5f);
        this.func_149672_a(field_149766_f);
        this.func_149647_a(Thaumcraft.tabTC);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("thaumcraft:woodplain");
        this.iconQuill = ir.func_94245_a("thaumcraft:tablequill");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        return this.icon;
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        if (side == ForgeDirection.UP) {
            return true;
        }
        return super.isSideSolid(world, x, y, z, side);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 14));
        par3List.add(new ItemStack(par1, 1, 15));
    }

    public TileEntity createTileEntity(World world, int metadata) {
        if ((metadata <= 1 || metadata >= 6) && metadata < 14) {
            return new TileTable();
        }
        if (metadata == 14) {
            return new TileDeconstructionTable();
        }
        if (metadata == 15) {
            return new TileArcaneWorkbench();
        }
        return new TileResearchTable();
    }

    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack is) {
        int md = par1World.func_72805_g(par2, par3, par4);
        if (md < 14) {
            int var7 = MathHelper.func_76128_c((double)((double)(par5EntityLiving.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
            int out = var7 == 0 ? 0 : (var7 == 1 ? 1 : (var7 == 2 ? 0 : (var7 == 3 ? 1 : 0)));
            par1World.func_147465_d(par2, par3, par4, (Block)this, out, 3);
        }
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public int func_149645_b() {
        return ConfigBlocks.blockTableRI;
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        InventoryUtils.dropItems(par1World, par2, par3, par4);
        super.func_149749_a(par1World, par2, par3, par4, par5, par6);
    }

    public int func_149643_k(World par1World, int par2, int par3, int par4) {
        int md = par1World.func_72805_g(par2, par3, par4);
        if (md >= 2 && md <= 9) {
            return 2;
        }
        return super.func_149643_k(par1World, par2, par3, par4);
    }

    public int func_149692_a(int par1) {
        if (par1 == 14) {
            return 14;
        }
        if (par1 == 15) {
            return 15;
        }
        return 0;
    }

    public void func_149695_a(World world, int x, int y, int z, Block par5) {
        TileEntity tile2;
        TileEntity tile = world.func_147438_o(x, y, z);
        int md = world.func_72805_g(x, y, z);
        if (tile != null && tile instanceof TileResearchTable) {
            int mm = world.func_72805_g(x + ForgeDirection.getOrientation((int)md).offsetX, y + ForgeDirection.getOrientation((int)md).offsetY, z + ForgeDirection.getOrientation((int)md).offsetZ);
            if (mm < 6) {
                InventoryUtils.dropItems(world, x, y, z);
                world.func_147455_a(x, y, z, (TileEntity)new TileTable());
                world.func_147465_d(x, y, z, (Block)this, 0, 3);
            }
        } else if (!(md < 6 || md >= 14 || (tile2 = world.func_147438_o(x + ForgeDirection.getOrientation((int)(md - 4)).offsetX, y + ForgeDirection.getOrientation((int)(md - 4)).offsetY, z + ForgeDirection.getOrientation((int)(md - 4)).offsetZ)) != null && tile2 instanceof TileResearchTable)) {
            world.func_147465_d(x, y, z, (Block)this, 0, 3);
        }
        super.func_149695_a(world, x, y, z, par5);
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int idk, float what, float these, float are) {
        TileEntity tileEntity = world.func_147438_o(x, y, z);
        int md = world.func_72805_g(x, y, z);
        if (md <= 1 || tileEntity == null || player.func_70093_af()) {
            return false;
        }
        if (world.field_72995_K) {
            return true;
        }
        if (tileEntity instanceof TileArcaneWorkbench) {
            player.openGui((Object)Thaumcraft.instance, 13, world, x, y, z);
            return true;
        }
        if (tileEntity instanceof TileDeconstructionTable) {
            player.openGui((Object)Thaumcraft.instance, 8, world, x, y, z);
            return true;
        }
        if (tileEntity instanceof TileResearchTable) {
            player.openGui((Object)Thaumcraft.instance, 10, world, x, y, z);
        } else {
            for (int a = 2; a < 6; ++a) {
                TileEntity tile = world.func_147438_o(x + ForgeDirection.getOrientation((int)a).offsetX, y + ForgeDirection.getOrientation((int)a).offsetY, z + ForgeDirection.getOrientation((int)a).offsetZ);
                if (tile == null || !(tile instanceof TileResearchTable)) continue;
                player.openGui((Object)Thaumcraft.instance, 10, world, x + ForgeDirection.getOrientation((int)a).offsetX, y + ForgeDirection.getOrientation((int)a).offsetY, z + ForgeDirection.getOrientation((int)a).offsetZ);
                break;
            }
        }
        return true;
    }

    public TileEntity func_149915_a(World var1, int md) {
        return null;
    }

    public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
        int md = world.func_72805_g(x, y, z);
        switch (md) {
            case 5: 
            case 9: {
                return AxisAlignedBB.func_72330_a((double)((double)x + this.field_149759_B), (double)((double)y + this.field_149760_C), (double)((double)z + this.field_149754_D), (double)((double)x + this.field_149755_E + 1.0), (double)((double)y + this.field_149756_F), (double)((double)z + this.field_149757_G));
            }
            case 4: 
            case 8: {
                return AxisAlignedBB.func_72330_a((double)((double)x + this.field_149759_B - 1.0), (double)((double)y + this.field_149760_C), (double)((double)z + this.field_149754_D), (double)((double)x + this.field_149755_E), (double)((double)y + this.field_149756_F), (double)((double)z + this.field_149757_G));
            }
            case 3: 
            case 7: {
                return AxisAlignedBB.func_72330_a((double)((double)x + this.field_149759_B), (double)((double)y + this.field_149760_C), (double)((double)z + this.field_149754_D), (double)((double)x + this.field_149755_E), (double)((double)y + this.field_149756_F), (double)((double)z + this.field_149757_G + 1.0));
            }
            case 2: 
            case 6: {
                return AxisAlignedBB.func_72330_a((double)((double)x + this.field_149759_B), (double)((double)y + this.field_149760_C), (double)((double)z + this.field_149754_D - 1.0), (double)((double)x + this.field_149755_E), (double)((double)y + this.field_149756_F), (double)((double)z + this.field_149757_G));
            }
        }
        return super.func_149633_g(world, x, y, z);
    }

    @Override
    public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
        if (md <= 1) {
            ItemWandCasting wand = (ItemWandCasting)wandstack.func_77973_b();
            world.func_147465_d(x, y, z, ConfigBlocks.blockTable, 15, 3);
            world.func_147455_a(x, y, z, (TileEntity)new TileArcaneWorkbench());
            TileArcaneWorkbench tawb = (TileArcaneWorkbench)world.func_147438_o(x, y, z);
            if (tawb != null && !wand.isStaff(wandstack)) {
                tawb.func_70299_a(10, wandstack.func_77946_l());
                player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
            }
            tawb.func_70296_d();
            world.func_147471_g(x, y, z);
            world.func_72908_a((double)x + 0.5, (double)y + 0.1, (double)z + 0.5, "random.click", 0.15f, 0.5f);
            return 0;
        }
        return -1;
    }

    @Override
    public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player) {
        return null;
    }

    @Override
    public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {
    }

    @Override
    public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {
    }
}

