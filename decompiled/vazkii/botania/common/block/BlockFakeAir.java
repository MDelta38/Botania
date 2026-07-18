/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.tile.TileFakeAir;

public class BlockFakeAir
extends BlockModContainer {
    public BlockFakeAir() {
        super(Material.field_151579_a);
        this.func_149663_c("fakeAir");
        this.func_149676_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.func_149675_a(true);
    }

    public void func_149695_a(World world, int x, int y, int z, Block block) {
        if (this.shouldRemove(world, x, y, z)) {
            world.func_147464_a(x, y, z, (Block)this, this.func_149738_a(world));
        }
    }

    private boolean shouldRemove(World world, int x, int y, int z) {
        return !world.field_72995_K && world.func_147438_o(x, y, z) == null || !(world.func_147438_o(x, y, z) instanceof TileFakeAir) || !((TileFakeAir)world.func_147438_o(x, y, z)).canStay();
    }

    public void func_149674_a(World world, int x, int y, int z, Random rand) {
        if (this.shouldRemove(world, x, y, z)) {
            world.func_147449_b(x, y, z, Blocks.field_150355_j);
        }
    }

    public int func_149738_a(World p_149738_1_) {
        return 4;
    }

    @Override
    public boolean registerInCreative() {
        return false;
    }

    public boolean func_149637_q() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity e) {
        return false;
    }

    public boolean func_149678_a(int par1, boolean par2) {
        return false;
    }

    public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public boolean func_149659_a(Explosion par1Explosion) {
        return false;
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        return new ArrayList<ItemStack>();
    }

    public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
        return null;
    }

    public boolean isAir(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public TileEntity func_149915_a(World world, int meta) {
        return new TileFakeAir();
    }
}

