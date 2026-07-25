/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.Explosion
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package thaumic.tinkerer.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.block.BlockMod;
import thaumic.tinkerer.common.registry.ITTinkererBlock;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public abstract class BlockGas
extends BlockMod
implements ITTinkererBlock {
    public BlockGas() {
        super(Material.field_151579_a);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        this.func_149675_a(true);
    }

    public BlockGas(Material par2Material) {
        super(par2Material);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
        this.field_149761_L = IconHelper.emptyTexture(par1IconRegister);
    }

    public int func_149645_b() {
        return -1;
    }

    public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random) {
        int meta = par1World.func_72805_g(par2, par3, par4);
        if (meta != 0) {
            this.setAt(par1World, par2 - 1, par3, par4, meta - 1);
            this.setAt(par1World, par2 + 1, par3, par4, meta - 1);
            this.setAt(par1World, par2, par3 - 1, par4, meta - 1);
            this.setAt(par1World, par2, par3 + 1, par4, meta - 1);
            this.setAt(par1World, par2, par3, par4 - 1, meta - 1);
            this.setAt(par1World, par2, par3, par4 + 1, meta - 1);
            par1World.func_72921_c(par2, par3, par4, 0, 2);
            this.placeParticle(par1World, par2, par3, par4);
        }
    }

    public void placeParticle(World world, int par2, int par3, int par4) {
    }

    private void setAt(World world, int x, int y, int z, int meta) {
        if (world.func_147437_c(x, y, z) && world.func_147439_a(x, y, z) != this) {
            if (!world.field_72995_K) {
                world.func_147465_d(x, y, z, (Block)this, meta, 2);
            }
            world.func_147464_a(x, y, z, (Block)this, 10);
        }
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

    @Override
    boolean registerInCreative() {
        return false;
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public boolean shouldRegister() {
        return true;
    }

    public boolean func_149730_j() {
        return super.func_149730_j();
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return null;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return null;
    }

    @Override
    public boolean shouldDisplayInTab() {
        return false;
    }

    @Override
    public Class<? extends ItemBlock> getItemBlock() {
        return null;
    }

    @Override
    public Class<? extends TileEntity> getTileEntity() {
        return null;
    }
}

