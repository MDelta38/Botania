/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 */
package thaumic.tinkerer.common.block.quartz;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.quartz.BlockDarkQuartz;
import thaumic.tinkerer.common.core.handler.ModCreativeTab;
import thaumic.tinkerer.common.item.quartz.ItemDarkQuartzSlab;
import thaumic.tinkerer.common.registry.ITTinkererBlock;
import thaumic.tinkerer.common.registry.ThaumicTinkererCraftingBenchRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public class BlockDarkQuartzSlab
extends BlockSlab
implements ITTinkererBlock {
    public BlockDarkQuartzSlab(boolean par2) {
        super(par2, Material.field_151576_e);
        this.func_149711_c(0.8f);
        this.func_149752_b(10.0f);
        if (!par2) {
            this.func_149713_g(0);
            this.func_149647_a(ModCreativeTab.INSTANCE);
        }
    }

    public BlockDarkQuartzSlab(Boolean par2) {
        this((boolean)par2);
    }

    public BlockDarkQuartzSlab() {
        this(false);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int par2) {
        return ThaumicTinkerer.registry.getFirstBlockFromClass(BlockDarkQuartz.class).func_149733_h(par1);
    }

    public Item func_149650_a(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.func_150898_a((Block)ThaumicTinkerer.registry.getFirstBlockFromClass(BlockDarkQuartzSlab.class));
    }

    public ItemStack func_149644_j(int par1) {
        return new ItemStack(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockDarkQuartzSlab.class));
    }

    public String func_150002_b(int i) {
        return "tile.darkQuartzSlab";
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister par1IconRegister) {
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        ArrayList<Object> result = new ArrayList<Object>();
        result.add(true);
        return result;
    }

    @Override
    public String getBlockName() {
        return this.field_150004_a ? "darkQuartzSlabFull" : "darkQuartzSlab";
    }

    @Override
    public boolean shouldRegister() {
        return true;
    }

    @Override
    public boolean shouldDisplayInTab() {
        return !this.field_150004_a;
    }

    @Override
    public Class<? extends ItemBlock> getItemBlock() {
        return ItemDarkQuartzSlab.class;
    }

    @Override
    public Class<? extends TileEntity> getTileEntity() {
        return null;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return null;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        if (this.func_149662_c()) {
            return null;
        }
        return new ThaumicTinkererCraftingBenchRecipe("DARK_QUARTZ2", new ItemStack(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockDarkQuartzSlab.class), 6), "QQQ", Character.valueOf('Q'), ThaumicTinkerer.registry.getFirstBlockFromClass(BlockDarkQuartz.class));
    }
}

