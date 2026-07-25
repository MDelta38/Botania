/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.Item
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  thaumcraft.common.config.Config
 */
package com.kentington.thaumichorizons.common.blocks;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileLight;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.common.config.Config;

public class BlockLight
extends BlockContainer {
    public IIcon blankIcon;

    public BlockLight() {
        super(Config.airyMaterial);
        this.func_149663_c("ThaumicHorizons_light");
        this.func_149672_a(new Block.SoundType("cloth", 0.0f, 1.0f));
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister ir) {
        this.blankIcon = ir.func_94245_a("thaumcraft:blank");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int side, int meta) {
        return this.blankIcon;
    }

    public float func_149712_f(World world, int x, int y, int z) {
        return 0.0f;
    }

    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        return 0.0f;
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return 14;
    }

    public void func_149719_a(IBlockAccess ba, int x, int y, int z) {
        this.func_149676_a(0.3f, 0.3f, 0.3f, 0.7f, 0.7f, 0.7f);
    }

    public boolean func_149655_b(IBlockAccess world, int x, int y, int z) {
        return false;
    }

    public int func_149645_b() {
        return ThaumicHorizons.blockLightRI;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public Item func_149650_a(int par1, Random par2Random, int par3) {
        return Item.func_150899_d((int)0);
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new TileLight();
    }

    public TileEntity func_149915_a(World var1, int md) {
        return null;
    }

    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        return null;
    }
}

