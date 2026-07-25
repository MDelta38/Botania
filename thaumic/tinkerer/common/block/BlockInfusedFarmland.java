/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFarmland
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumic.tinkerer.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.block.BlockInfusedGrain;
import thaumic.tinkerer.common.block.tile.TileInfusedFarmland;
import thaumic.tinkerer.common.registry.ITTinkererBlock;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public class BlockInfusedFarmland
extends BlockFarmland
implements ITTinkererBlock {
    @SideOnly(value=Side.CLIENT)
    private IIcon iconWet;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconDry;

    public BlockInfusedFarmland() {
        this.func_149711_c(0.6f);
        this.func_149672_a(field_149767_g);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
    }

    public boolean func_149710_n() {
        return true;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new TileInfusedFarmland();
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getBlockName() {
        return "infusedFarmland";
    }

    @Override
    public boolean shouldRegister() {
        return true;
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
        return TileInfusedFarmland.class;
    }

    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plantable) {
        return plantable instanceof BlockInfusedGrain;
    }

    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return null;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return null;
    }

    public void func_149746_a(World p_149746_1_, int p_149746_2_, int p_149746_3_, int p_149746_4_, Entity p_149746_5_, float p_149746_6_) {
    }

    public void func_149674_a(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_) {
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int p_149691_1_, int p_149691_2_) {
        return p_149691_1_ == 1 ? this.iconWet : Blocks.field_150346_d.func_149733_h(p_149691_1_);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister p_149651_1_) {
        this.iconWet = IconHelper.forBlock(p_149651_1_, (Block)this);
    }
}

