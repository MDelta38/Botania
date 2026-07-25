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
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package witchinggadgets.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
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
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.blocks.tiles.TileEntityWallMirror;

public class BlockWallMirror
extends BlockContainer {
    public BlockWallMirror() {
        super(Material.field_151592_s);
        this.func_149647_a(WitchingGadgets.tabWG);
    }

    public void func_149651_a(IIconRegister iconRegister) {
        this.field_149761_L = iconRegister.func_94245_a("witchinggadgets:nil");
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149666_a(Item item, CreativeTabs tab, List list) {
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!player.func_70093_af()) {
            TileEntityWallMirror tile = !((TileEntityWallMirror)world.func_147438_o((int)x, (int)y, (int)z)).isDummy ? (TileEntityWallMirror)world.func_147438_o(x, y, z) : (TileEntityWallMirror)world.func_147438_o(x, y - 1, z);
            tile.toggleState();
        }
        return true;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return -1;
    }

    public boolean func_149646_a(IBlockAccess iBlockAccess, int x, int y, int z, int side) {
        return true;
    }

    public void func_149695_a(World world, int x, int y, int z, Block par5) {
        super.func_149695_a(world, x, y, z, par5);
        TileEntityWallMirror tile = (TileEntityWallMirror)world.func_147438_o(x, y, z);
        if (tile.isDummy) {
            if (world.func_147437_c(x, y - 1, z)) {
                world.func_147468_f(x, y, z);
            }
        } else if (world.func_147437_c(x, y + 1, z)) {
            world.func_147468_f(x, y, z);
        }
    }

    public void func_149681_a(World world, int x, int y, int z, int par5, EntityPlayer player) {
        this.func_149697_b(world, x, y, z, par5, 0);
        super.func_149681_a(world, x, y, z, par5, player);
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        TileEntityWallMirror tile = (TileEntityWallMirror)world.func_147438_o(x, y, z);
        if (tile != null && !tile.isDummy) {
            ret.add(new ItemStack((Block)this, 1, this.func_149692_a(metadata)));
        }
        return ret;
    }

    public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        int l = ((TileEntityWallMirror)par1IBlockAccess.func_147438_o((int)par2, (int)par3, (int)par4)).facing;
        switch (l) {
            default: {
                this.func_149676_a(0.0f, 0.0f, 0.9375f, 1.0f, 1.0f, 1.0f);
                break;
            }
            case 3: {
                this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0625f);
                break;
            }
            case 4: {
                this.func_149676_a(0.9375f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                break;
            }
            case 5: {
                this.func_149676_a(0.0f, 0.0f, 0.0f, 0.0625f, 1.0f, 1.0f);
            }
        }
    }

    public boolean func_149742_c(World world, int x, int y, int z) {
        return world.func_147437_c(x, y + 1, z);
    }

    public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack) {
        int l = MathHelper.func_76128_c((double)((double)(par5EntityLiving.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
        if (l == 0) {
            ((TileEntityWallMirror)par1World.func_147438_o((int)par2, (int)par3, (int)par4)).facing = 2;
            par1World.func_147449_b(par2, par3 + 1, par4, (Block)this);
            ((TileEntityWallMirror)par1World.func_147438_o((int)par2, (int)(par3 + 1), (int)par4)).facing = 2;
            ((TileEntityWallMirror)par1World.func_147438_o((int)par2, (int)(par3 + 1), (int)par4)).isDummy = true;
        }
        if (l == 1) {
            ((TileEntityWallMirror)par1World.func_147438_o((int)par2, (int)par3, (int)par4)).facing = 5;
            par1World.func_147449_b(par2, par3 + 1, par4, (Block)this);
            ((TileEntityWallMirror)par1World.func_147438_o((int)par2, (int)(par3 + 1), (int)par4)).facing = 5;
            ((TileEntityWallMirror)par1World.func_147438_o((int)par2, (int)(par3 + 1), (int)par4)).isDummy = true;
        }
        if (l == 2) {
            ((TileEntityWallMirror)par1World.func_147438_o((int)par2, (int)par3, (int)par4)).facing = 3;
            par1World.func_147449_b(par2, par3 + 1, par4, (Block)this);
            ((TileEntityWallMirror)par1World.func_147438_o((int)par2, (int)(par3 + 1), (int)par4)).facing = 3;
            ((TileEntityWallMirror)par1World.func_147438_o((int)par2, (int)(par3 + 1), (int)par4)).isDummy = true;
        }
        if (l == 3) {
            ((TileEntityWallMirror)par1World.func_147438_o((int)par2, (int)par3, (int)par4)).facing = 4;
            par1World.func_147449_b(par2, par3 + 1, par4, (Block)this);
            ((TileEntityWallMirror)par1World.func_147438_o((int)par2, (int)(par3 + 1), (int)par4)).facing = 4;
            ((TileEntityWallMirror)par1World.func_147438_o((int)par2, (int)(par3 + 1), (int)par4)).isDummy = true;
        }
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileEntityWallMirror();
    }
}

