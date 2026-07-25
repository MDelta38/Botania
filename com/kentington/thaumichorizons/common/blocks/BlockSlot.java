/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  thaumcraft.common.items.wands.ItemWandCasting
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileSlot;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.common.items.wands.ItemWandCasting;

public class BlockSlot
extends BlockContainer {
    public BlockSlot() {
        super(Material.field_151576_e);
        this.func_149711_c(2.5f);
        this.func_149752_b(2.5f);
        this.func_149663_c("ThaumicHorizons_slot");
        this.func_149658_d("ThaumicHorizons:void");
        this.func_149647_a(ThaumicHorizons.tabTH);
    }

    public void func_149749_a(World world, int x, int y, int z, Block block, int md) {
        TileSlot tco = (TileSlot)world.func_147438_o(x, y, z);
        if (tco.portalOpen) {
            tco.destroyPortal();
        }
        if (tco.hasKeystone) {
            int dim = tco.removeKeystone();
            ItemStack keystone = new ItemStack(ThaumicHorizons.itemKeystone);
            keystone.field_77990_d = new NBTTagCompound();
            keystone.field_77990_d.func_74768_a("dimension", dim);
            EntityItem dropped = new EntityItem(world);
            dropped.func_92058_a(keystone);
            dropped.func_70107_b((double)x + 0.5, (double)y + 0.5, (double)z + 0.5);
            world.func_72838_d((Entity)dropped);
        }
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        TileSlot tco = (TileSlot)world.func_147438_o(x, y, z);
        ItemStack theItem = player.func_70694_bm();
        if (tco.hasKeystone) {
            if (player.func_70694_bm() == null) {
                int dim = tco.removeKeystone();
                ItemStack keystone = new ItemStack(ThaumicHorizons.itemKeystone);
                keystone.field_77990_d = new NBTTagCompound();
                keystone.field_77990_d.func_74768_a("dimension", dim);
                player.field_71071_by.func_70441_a(keystone);
                if (tco.portalOpen) {
                    tco.destroyPortal();
                }
            } else if (!tco.portalOpen && player.func_70694_bm().func_77973_b() instanceof ItemWandCasting) {
                tco.makePortal(player);
            }
        } else if (theItem != null && theItem.func_77973_b() == ThaumicHorizons.itemKeystone && theItem.field_77990_d != null) {
            tco.insertKeystone(theItem.field_77990_d.func_74762_e("dimension"));
            --theItem.field_77994_a;
        }
        world.func_147471_g(x, y, z);
        return false;
    }

    public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
        return new TileSlot();
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return ThaumicHorizons.blockSlotRI;
    }
}

