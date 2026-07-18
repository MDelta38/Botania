/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileCacophonium;

public class BlockCacophonium
extends BlockModContainer {
    IIcon top;

    protected BlockCacophonium() {
        super(Material.field_151575_d);
        this.func_149663_c("cacophoniumBlock");
        this.func_149711_c(0.8f);
    }

    @Override
    public void func_149651_a(IIconRegister reg) {
        this.field_149761_L = IconHelper.forBlock(reg, (Block)this, 0);
        this.top = IconHelper.forBlock(reg, (Block)this, 1);
    }

    public IIcon func_149691_a(int side, int meta) {
        return side == 1 ? this.field_149761_L : this.top;
    }

    protected boolean func_149700_E() {
        return false;
    }

    @Override
    public boolean registerInCreative() {
        return false;
    }

    public void func_149695_a(World world, int x, int y, int z, Block block) {
        boolean powered;
        boolean power = world.func_72864_z(x, y, z) || world.func_72864_z(x, y + 1, z);
        int meta = world.func_72805_g(x, y, z);
        boolean bl = powered = (meta & 8) != 0;
        if (power && !powered) {
            TileEntity tile = world.func_147438_o(x, y, z);
            if (tile != null && tile instanceof TileCacophonium) {
                ((TileCacophonium)tile).annoyDirewolf();
            }
            world.func_72921_c(x, y, z, meta | 8, 4);
        } else if (!power && powered) {
            world.func_72921_c(x, y, z, meta & 0xFFFFFFF7, 4);
        }
    }

    public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
        if (!par6EntityPlayer.field_71075_bZ.field_75098_d) {
            this.func_149697_b(par1World, par2, par3, par4, par5, 0);
        }
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null && tile instanceof TileCacophonium) {
            stacks.add(new ItemStack(Blocks.field_150323_B));
            ItemStack thingy = ((TileCacophonium)tile).stack;
            if (thingy != null) {
                stacks.add(thingy.func_77946_l());
            }
        }
        return stacks;
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileCacophonium();
    }
}

