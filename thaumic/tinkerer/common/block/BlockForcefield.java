/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package thaumic.tinkerer.common.block;

import java.util.ArrayList;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumic.tinkerer.common.block.BlockMod;
import thaumic.tinkerer.common.block.tile.TileForcefield;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public class BlockForcefield
extends BlockMod {
    public BlockForcefield() {
        super(Material.field_151579_a);
    }

    public int func_149645_b() {
        return -1;
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        return new ArrayList<ItemStack>();
    }

    public boolean func_149662_c() {
        return false;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new TileForcefield();
    }

    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @Override
    public String getBlockName() {
        return "forcefield";
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
}

