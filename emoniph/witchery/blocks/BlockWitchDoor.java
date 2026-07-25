/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDoor
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.blocks;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.util.BlockUtil;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWitchDoor
extends BlockDoor {
    public BlockWitchDoor() {
        super(Material.field_151575_d);
        this.disableStatsThunk();
        this.func_149672_a(Block.field_149766_f);
    }

    public Block func_149663_c(String blockName) {
        BlockUtil.registerBlock((Block)this, blockName);
        return super.func_149663_c(blockName);
    }

    public void func_149749_a(World world, int posX, int posY, int posZ, Block block, int metadata) {
        if (block == Witchery.Blocks.DOOR_ALDER) {
            int i1 = this.func_150012_g((IBlockAccess)world, posX, posY, posZ);
            if ((i1 & 8) != 0) {
                --posY;
            }
            this.notifyNeighborsOfBlockChange(world, posX, posY, posZ);
        }
        super.func_149749_a(world, posX, posY, posZ, block, metadata);
    }

    public boolean func_149727_a(World world, int posX, int posY, int posZ, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (this == Witchery.Blocks.DOOR_ALDER) {
            boolean result = super.func_149727_a(world, posX, posY, posZ, player, par6, par7, par8, par9);
            int i1 = this.func_150012_g((IBlockAccess)world, posX, posY, posZ);
            if ((i1 & 8) != 0) {
                --posY;
            }
            this.notifyNeighborsOfBlockChange(world, posX, posY, posZ);
            return result;
        }
        if (this.hasKeyForDoor(world, posX, posY, posZ, player)) {
            return super.func_149727_a(world, posX, posY, posZ, player, par6, par7, par8, par9);
        }
        return false;
    }

    public void func_149681_a(World world, int posX, int posY, int posZ, int par5, EntityPlayer player) {
        if (this == Witchery.Blocks.DOOR_ROWAN) {
            ItemStack stack = this.hasKeyForDoor(world, posX, posY, posZ, player) ? Witchery.Items.GENERIC.itemDoorRowan.createStack() : new ItemStack(Items.field_151055_y, 24);
            float f = 0.7f;
            double d0 = (double)(world.field_73012_v.nextFloat() * 0.7f) + (double)0.15f;
            double d1 = (double)(world.field_73012_v.nextFloat() * 0.7f) + (double)0.15f;
            double d2 = (double)(world.field_73012_v.nextFloat() * 0.7f) + (double)0.15f;
            EntityItem entityitem = new EntityItem(world, (double)posX + d0, (double)posY + d1, (double)posZ + d2, stack);
            entityitem.field_145804_b = 10;
            if (!world.field_72995_K) {
                world.func_72838_d((Entity)entityitem);
            }
        }
        super.func_149681_a(world, posX, posY, posZ, par5, player);
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        if (this == Witchery.Blocks.DOOR_ROWAN) {
            ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
            return drops;
        }
        return super.getDrops(world, x, y, z, metadata, fortune);
    }

    private boolean hasKeyForDoor(World world, int posX, int posY, int posZ, EntityPlayer player) {
        for (int slot = 0; slot < player.field_71071_by.field_70462_a.length; ++slot) {
            NBTTagList keyList;
            ItemStack itemstack = player.field_71071_by.field_70462_a[slot];
            if (itemstack == null) continue;
            NBTTagCompound nbtTag = itemstack.func_77978_p();
            if (itemstack == null || nbtTag == null) continue;
            int i1 = this.func_150012_g((IBlockAccess)world, posX, posY, posZ);
            if (Witchery.Items.GENERIC.itemDoorKey.isMatch(itemstack)) {
                if (!nbtTag.func_74764_b("doorX") || !nbtTag.func_74764_b("doorY") || !nbtTag.func_74764_b("doorZ")) continue;
                int doorX = nbtTag.func_74762_e("doorX");
                int doorY = nbtTag.func_74762_e("doorY") + ((i1 & 8) != 0 ? 1 : 0);
                int doorZ = nbtTag.func_74762_e("doorZ");
                if (doorX != posX || doorY != posY || doorZ != posZ || nbtTag.func_74764_b("doorD") && nbtTag.func_74762_e("doorD") != world.field_73011_w.field_76574_g) continue;
                return true;
            }
            if (!Witchery.Items.GENERIC.itemDoorKeyring.isMatch(itemstack) || !nbtTag.func_74764_b("doorKeys") || (keyList = nbtTag.func_150295_c("doorKeys", 10)) == null) continue;
            for (int i = 0; i < keyList.func_74745_c(); ++i) {
                NBTTagCompound keyTag = keyList.func_150305_b(i);
                if (keyTag == null || !keyTag.func_74764_b("doorX") || !keyTag.func_74764_b("doorY") || !keyTag.func_74764_b("doorZ")) continue;
                int doorX = keyTag.func_74762_e("doorX");
                int doorY = keyTag.func_74762_e("doorY") + ((i1 & 8) != 0 ? 1 : 0);
                int doorZ = keyTag.func_74762_e("doorZ");
                if (doorX != posX || doorY != posY || doorZ != posZ || keyTag.func_74764_b("doorD") && keyTag.func_74762_e("doorD") != world.field_73011_w.field_76574_g) continue;
                return true;
            }
        }
        return false;
    }

    public void func_149695_a(World world, int x, int y, int z, Block block) {
        int l = world.func_72805_g(x, y, z);
        if ((l & 8) == 0) {
            boolean flag = false;
            if (world.func_147439_a(x, y + 1, z) != this) {
                world.func_147468_f(x, y, z);
                flag = true;
            }
            if (!World.func_147466_a((IBlockAccess)world, (int)x, (int)(y - 1), (int)z)) {
                // empty if block
            }
            if (flag) {
                if (!world.field_72995_K) {
                    this.func_149697_b(world, x, y, z, l, 0);
                }
            } else {
                boolean flag1;
                boolean bl = flag1 = world.func_72864_z(x, y, z) || world.func_72864_z(x, y + 1, z);
                if ((flag1 || block.func_149744_f()) && block != this) {
                    this.func_150014_a(world, x, y, z, flag1);
                }
            }
        } else {
            super.func_149695_a(world, x, y, z, block);
        }
    }

    protected void func_149642_a(World world, int x, int y, int z, ItemStack stack) {
        super.func_149642_a(world, x, y, z, stack);
    }

    public boolean onBlockActivatedNormally(World world, int posX, int posY, int posZ, EntityPlayer player, int par6, float par7, float par8, float par9) {
        boolean result = super.func_149727_a(world, posX, posY, posZ, player, par6, par7, par8, par9);
        if (this == Witchery.Blocks.DOOR_ALDER) {
            int i1 = this.func_150012_g((IBlockAccess)world, posX, posY, posZ);
            if ((i1 & 8) != 0) {
                --posY;
            }
            this.notifyNeighborsOfBlockChange(world, posX, posY, posZ);
        }
        return result;
    }

    private void notifyNeighborsOfBlockChange(World world, int posX, int posY, int posZ) {
        world.func_147459_d(posX, posY, posZ, (Block)this);
        world.func_147459_d(posX, posY - 1, posZ, (Block)this);
    }

    public Block disableStatsThunk() {
        return this.func_149649_H();
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        Block block = world.func_147439_a(x, y, z);
        if (block == Witchery.Blocks.DOOR_ALDER) {
            return Witchery.Items.GENERIC.itemDoorAlder.createStack();
        }
        return Witchery.Items.GENERIC.itemDoorRowan.createStack();
    }

    public void func_150014_a(World world, int x, int y, int z, boolean par5) {
        if (this != Witchery.Blocks.DOOR_ALDER && !par5) {
            super.func_150014_a(world, x, y, z, par5);
        }
    }

    public int func_149709_b(IBlockAccess world, int x, int y, int z, int side) {
        if (this == Witchery.Blocks.DOOR_ALDER) {
            return this.func_150015_f(world, x, y, z) ? 15 : 0;
        }
        return super.func_149709_b(world, x, y, z, side);
    }

    public int func_149748_c(IBlockAccess par1IBlockAccess, int posX, int posY, int posZ, int side) {
        if (this == Witchery.Blocks.DOOR_ALDER) {
            return side == 1 ? this.func_149709_b(par1IBlockAccess, posX, posY, posZ, side) : 0;
        }
        return super.func_149748_c(par1IBlockAccess, posX, posY, posZ, side);
    }

    public boolean func_149744_f() {
        return this == Witchery.Blocks.DOOR_ALDER;
    }

    public Item func_149650_a(int metadata, Random rand, int fortune) {
        return (metadata & 8) != 0 ? null : Witchery.Items.GENERIC;
    }

    public int func_149692_a(int metadata) {
        return (metadata & 8) != 0 ? 0 : (this == Witchery.Blocks.DOOR_ALDER ? Witchery.Items.GENERIC.itemDoorAlder.damageValue : Witchery.Items.GENERIC.itemDoorRowan.damageValue);
    }
}

