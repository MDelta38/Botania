/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileVatSlave;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockVatInterior
extends BlockContainer {
    public BlockVatInterior() {
        super(Material.field_151586_h);
        this.func_149711_c(3.0f);
        this.func_149752_b(15.0f);
        this.field_149784_t = 8;
        this.func_149663_c("ThaumicHorizons_vatInterior");
        this.func_149658_d("ThaumicHorizons:vatInterior");
    }

    public TileEntity func_149915_a(World world, int md) {
        return this.createTileEntity(world, md);
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new TileVatSlave();
    }

    public void func_149749_a(World world, int x, int y, int z, Block block, int md) {
        ((TileVatSlave)world.func_147438_o(x, y, z)).killMyBoss(md);
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return ThaumicHorizons.blockVatInteriorRI;
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        return ((TileVatSlave)world.func_147438_o(x, y, z)).activate(player);
    }

    public void func_149670_a(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity p_149670_5_) {
        p_149670_5_.func_70050_g(300);
    }
}

