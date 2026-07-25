/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.Item
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileEldritchPortal;

public class BlockEldritchPortal
extends Block {
    public IIcon blankIcon;

    public BlockEldritchPortal() {
        super(Config.airyMaterial);
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
        return -1.0f;
    }

    public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        return 200000.0f;
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        return 15;
    }

    public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
        return false;
    }

    public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
        return false;
    }

    public boolean func_149655_b(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
        return null;
    }

    public void func_149743_a(World world, int x, int y, int z, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {
    }

    public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4) {
        return AxisAlignedBB.func_72330_a((double)0.0, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (double)0.0);
    }

    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return false;
    }

    public int func_149645_b() {
        return -1;
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public int func_149692_a(int par1) {
        return par1;
    }

    public Item func_149650_a(int par1, Random par2Random, int par3) {
        return Item.func_150899_d((int)0);
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEldritchPortal();
    }

    public boolean hasTileEntity(int metadata) {
        return true;
    }

    public void func_149695_a(World world, int x, int y, int z, Block p_149695_5_) {
        if (world.func_147439_a(x, y + 1, z) != ConfigBlocks.blockEldritch || world.func_147439_a(x, y - 1, z) != ConfigBlocks.blockEldritch) {
            world.func_147468_f(x, y, z);
        }
        super.func_149695_a(world, x, y, z, p_149695_5_);
    }
}

