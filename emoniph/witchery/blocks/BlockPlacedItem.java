/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockBaseContainer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPlacedItem
extends BlockBaseContainer {
    public static void placeItemInWorld(ItemStack stack, EntityPlayer player, World world, int x, int y, int z) {
        int meta = 0;
        if (player != null) {
            int l = MathHelper.func_76128_c((double)((double)(player.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3;
            if (l == 0) {
                meta = 2;
            }
            if (l == 1) {
                meta = 5;
            }
            if (l == 2) {
                meta = 3;
            }
            if (l == 3) {
                meta = 4;
            }
        }
        world.func_147465_d(x, y, z, Witchery.Blocks.PLACED_ITEMSTACK, meta, 3);
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null && tile instanceof TileEntityPlacedItem) {
            ((TileEntityPlacedItem)tile).setStack(stack);
        }
    }

    public BlockPlacedItem() {
        super(Material.field_151578_c, TileEntityPlacedItem.class);
        this.registerWithCreateTab = false;
        this.func_149711_c(0.0f);
        this.func_149672_a(field_149777_j);
        this.func_149676_a(0.2f, 0.0f, 0.2f, 0.8f, 0.05f, 0.8f);
    }

    public void func_149651_a(IIconRegister p_149651_1_) {
    }

    protected String func_149641_N() {
        return null;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean func_149686_d() {
        return false;
    }

    public void func_149681_a(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
        if (par6EntityPlayer.field_71075_bZ.field_75098_d) {
            par1World.func_72921_c(par2, par3, par4, par5 |= 8, 4);
        }
        this.func_149697_b(par1World, par2, par3, par4, par5, 0);
        super.func_149681_a(par1World, par2, par3, par4, par5, par6EntityPlayer);
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        TileEntity tile;
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        if ((metadata & 8) == 0 && (tile = world.func_147438_o(x, y, z)) != null && tile instanceof TileEntityPlacedItem && ((TileEntityPlacedItem)tile).getStack() != null) {
            drops.add(((TileEntityPlacedItem)tile).getStack());
        }
        return drops;
    }

    public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5) {
        this.func_111046_k(par1World, par2, par3, par4);
    }

    private boolean func_111046_k(World par1World, int par2, int par3, int par4) {
        if (!this.func_149718_j(par1World, par2, par3, par4)) {
            if (!par1World.field_72995_K) {
                this.func_149697_b(par1World, par2, par3, par4, par1World.func_72805_g(par2, par3, par4), 0);
                par1World.func_147468_f(par2, par3, par4);
            }
            return false;
        }
        return true;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null && tile instanceof TileEntityPlacedItem && ((TileEntityPlacedItem)tile).getStack() != null) {
            return ((TileEntityPlacedItem)tile).getStack().func_77946_l();
        }
        return new ItemStack(Witchery.Items.ARTHANA);
    }

    public boolean func_149718_j(World world, int x, int y, int z) {
        Material material = world.func_147439_a(x, y - 1, z).func_149688_o();
        return !world.func_147437_c(x, y - 1, z) && material != null && material.func_76218_k() && material.func_76220_a();
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149734_b(World world, int x, int y, int z, Random rand) {
    }

    @SideOnly(value=Side.CLIENT)
    public String func_149702_O() {
        return this.func_149641_N();
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return Blocks.field_150339_S.func_149733_h(0);
    }

    public static class TileEntityPlacedItem
    extends TileEntity {
        private static final String ITEM_KEY = "WITCPlacedItem";
        private ItemStack stack;

        public boolean canUpdate() {
            return false;
        }

        public void func_145841_b(NBTTagCompound nbtRoot) {
            super.func_145841_b(nbtRoot);
            if (this.stack != null) {
                NBTTagCompound nbtItem = new NBTTagCompound();
                this.stack.func_77955_b(nbtItem);
                nbtRoot.func_74782_a(ITEM_KEY, (NBTBase)nbtItem);
            }
        }

        public void func_145839_a(NBTTagCompound nbtRoot) {
            super.func_145839_a(nbtRoot);
            if (nbtRoot.func_74764_b(ITEM_KEY)) {
                ItemStack stack;
                NBTTagCompound nbtItem = nbtRoot.func_74775_l(ITEM_KEY);
                this.stack = stack = ItemStack.func_77949_a((NBTTagCompound)nbtItem);
            }
        }

        public void setStack(ItemStack stack) {
            this.stack = stack;
            if (!this.field_145850_b.field_72995_K) {
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }

        public ItemStack getStack() {
            return this.stack;
        }

        public Packet func_145844_m() {
            NBTTagCompound nbtTag = new NBTTagCompound();
            this.func_145841_b(nbtTag);
            return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbtTag);
        }

        public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
            super.onDataPacket(net, packet);
            this.func_145839_a(packet.func_148857_g());
            this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }
}

