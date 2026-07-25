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
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  thaumcraft.common.items.wands.ItemWandCasting
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileVisDynamo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.common.items.wands.ItemWandCasting;

public class BlockVisDynamo
extends BlockContainer {
    IIcon icon;

    public BlockVisDynamo(Material p_i45386_1_) {
        super(p_i45386_1_);
        this.func_149711_c(0.7f);
        this.func_149752_b(1.0f);
        this.func_149715_a(0.5f);
        this.func_149663_c("ThaumicHorizons_visDynamo");
        this.func_149658_d("ThaumicHorizons:visDynamo");
        this.func_149647_a(ThaumicHorizons.tabTH);
    }

    public TileEntity func_149915_a(World p_149915_1_, int p_149915_2_) {
        return this.createTileEntity(p_149915_1_, p_149915_2_);
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new TileVisDynamo();
    }

    public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() instanceof ItemWandCasting) {
            return false;
        }
        player.openGui((Object)ThaumicHorizons.instance, 1, world, x, y, z);
        return true;
    }

    public void func_149749_a(World p_149749_1_, int p_149749_2_, int p_149749_3_, int p_149749_4_, Block p_149749_5_, int p_149749_6_) {
        ((TileVisDynamo)p_149749_1_.func_147438_o(p_149749_2_, p_149749_3_, p_149749_4_)).killMe();
        super.func_149749_a(p_149749_1_, p_149749_2_, p_149749_3_, p_149749_4_, p_149749_5_, p_149749_6_);
    }

    @SideOnly(value=Side.CLIENT)
    public int func_149701_w() {
        return 1;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149645_b() {
        return ThaumicHorizons.blockVisDynamoRI;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.icon = ir.func_94245_a("gold_block");
    }

    public IIcon func_149691_a(int par1, int par2) {
        return this.icon;
    }
}

