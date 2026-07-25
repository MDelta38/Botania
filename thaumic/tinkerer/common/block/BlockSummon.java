/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.init.Blocks
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
package thaumic.tinkerer.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
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
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.tile.TileSummon;
import thaumic.tinkerer.common.registry.ITTinkererBlock;
import thaumic.tinkerer.common.registry.ThaumicTinkererArcaneRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class BlockSummon
extends Block
implements ITTinkererBlock {
    IIcon iconTop;
    IIcon iconSide;
    Random random;

    public BlockSummon() {
        super(Material.field_151573_f);
        this.func_149676_a(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f);
        this.func_149711_c(3.0f);
        this.func_149752_b(50.0f);
        this.func_149672_a(Block.field_149777_j);
        this.random = new Random();
    }

    public boolean func_149686_d() {
        return false;
    }

    public boolean func_149662_c() {
        return false;
    }

    public boolean hasTileEntity(int metadata) {
        return true;
    }

    public TileEntity createTileEntity(World world, int meta) {
        return new TileSummon();
    }

    @SideOnly(value=Side.CLIENT)
    public void func_149651_a(IIconRegister iconRegister) {
        this.iconTop = IconHelper.forBlock(iconRegister, (Block)this, 1);
        this.iconSide = IconHelper.forBlock(iconRegister, (Block)this, 2);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_149691_a(int par1, int meta) {
        return par1 == ForgeDirection.UP.ordinal() ? this.iconTop : (par1 == ForgeDirection.DOWN.ordinal() ? Block.func_149684_b((String)"obsidian").func_149691_a(0, 0) : this.iconSide);
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getBlockName() {
        return "spawner";
    }

    @Override
    public boolean shouldRegister() {
        return true;
    }

    @Override
    public boolean shouldDisplayInTab() {
        return true;
    }

    @Override
    public Class<? extends ItemBlock> getItemBlock() {
        return null;
    }

    @Override
    public Class<? extends TileEntity> getTileEntity() {
        return TileSummon.class;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch)new TTResearchItem("SUMMON", new AspectList().add(Aspect.WEAPON, 1).add(Aspect.BEAST, 3).add(Aspect.MAGIC, 3), -5, 8, 3, new ItemStack(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockSummon.class)), new ResearchPage[0]).setWarp(3).setParents(new String[]{"BLOOD_SWORD"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.arcaneRecipePage("SUMMON0"), ResearchHelper.recipePage("SUMMON1"), ResearchHelper.infusionPage("SUMMON"), new ResearchPage("1")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererArcaneRecipe("SUMMON0", "SUMMON", new ItemStack(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockSummon.class)), new AspectList().add(Aspect.ORDER, 50).add(Aspect.ENTROPY, 50), "WWW", "SSS", Character.valueOf('S'), new ItemStack(Blocks.field_150348_b), Character.valueOf('W'), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 1));
    }
}

