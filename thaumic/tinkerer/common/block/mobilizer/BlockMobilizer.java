/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigBlocks
 */
package thaumic.tinkerer.common.block.mobilizer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigBlocks;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.block.BlockMod;
import thaumic.tinkerer.common.block.tile.TileEntityMobilizer;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class BlockMobilizer
extends BlockMod {
    @SideOnly(value=Side.CLIENT)
    private IIcon iconTop;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconBottom;
    @SideOnly(value=Side.CLIENT)
    private IIcon iconSide;

    public BlockMobilizer() {
        super(Material.field_151573_f);
    }

    public void func_149725_f(World par1World, int par2, int par3, int par4, int par5) {
        TileEntity tile = par1World.func_147438_o(par2, par3, par4);
        if (tile != null && tile instanceof TileEntityMobilizer) {
            ((TileEntityMobilizer)tile).dead = true;
        }
        super.func_149725_f(par1World, par2, par3, par4, par5);
    }

    public boolean hasTileEntity(int metadata) {
        return true;
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntityMobilizer();
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister iconRegister) {
        this.iconBottom = IconHelper.forBlock(iconRegister, (Block)this, 0);
        this.iconTop = IconHelper.forBlock(iconRegister, (Block)this, 1);
        this.iconSide = IconHelper.forBlock(iconRegister, (Block)this, 2);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int meta) {
        return par1 == ForgeDirection.UP.ordinal() ? this.iconTop : (par1 == ForgeDirection.DOWN.ordinal() ? this.iconBottom : this.iconSide);
    }

    @Override
    public String getBlockName() {
        return "Levitational Locomotive";
    }

    @Override
    public Class<? extends ItemBlock> getItemBlock() {
        return null;
    }

    @Override
    public Class<? extends TileEntity> getTileEntity() {
        return TileEntityMobilizer.class;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch)new TTResearchItem("LEVITATOR", new AspectList().add(Aspect.MOTION, 2).add(Aspect.ORDER, 2), -7, 5, 3, new ItemStack((Block)this), new ResearchPage[0]).setParents(new String[]{"MAGNETS"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.infusionPage("LEVITATOR"), ResearchHelper.arcaneRecipePage("LEVITATOR_RELAY")}).setSecondary();
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("LEVITATOR", new ItemStack((Block)this), 4, new AspectList().add(Aspect.MOTION, 15).add(Aspect.ORDER, 20).add(Aspect.MAGIC, 15), new ItemStack(ConfigBlocks.blockLifter), new ItemStack(Items.field_151042_j), new ItemStack(Items.field_151008_G), new ItemStack(Items.field_151042_j), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 1));
    }
}

