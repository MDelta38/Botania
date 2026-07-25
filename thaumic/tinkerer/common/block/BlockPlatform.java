/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigBlocks
 */
package thaumic.tinkerer.common.block;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigBlocks;
import thaumic.tinkerer.common.block.BlockCamo;
import thaumic.tinkerer.common.block.tile.TileCamo;
import thaumic.tinkerer.common.registry.ThaumicTinkererArcaneRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class BlockPlatform
extends BlockCamo {
    public BlockPlatform() {
        super(Material.field_151575_d);
        this.func_149711_c(2.0f);
        this.func_149752_b(5.0f);
        this.func_149672_a(Block.field_149766_f);
    }

    public void func_149743_a(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {
        if (par7Entity != null) {
            double d = par7Entity.field_70163_u;
            int n = par7Entity instanceof EntityPlayer ? 2 : 0;
            if (!(!(d > (double)(par3 + n)) || par7Entity instanceof EntityPlayer && par7Entity.func_70093_af())) {
                super.func_149743_a(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
            }
        }
    }

    public boolean func_149655_b(IBlockAccess par1iBlockAccess, int par2, int par3, int par4) {
        return false;
    }

    public TileCamo createNewTileEntity(World world, int meta) {
        return new TileCamo();
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getBlockName() {
        return "platform";
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
        return TileCamo.class;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch)new TTResearchItem("PLATFORM", new AspectList().add(Aspect.SENSES, 2).add(Aspect.TREE, 1).add(Aspect.MOTION, 1), -2, 6, 3, new ItemStack((Block)this), new ResearchPage[0]).setConcealed().setParents(new String[]{"CLEANSING_TALISMAN"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.arcaneRecipePage("PLATFORM")}).setSecondary();
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererArcaneRecipe("PLATFORM", "PLATFORM", new ItemStack((Block)this, 2), new AspectList().add(Aspect.AIR, 2).add(Aspect.ENTROPY, 4), " S ", "G G", Character.valueOf('G'), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6), Character.valueOf('S'), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 7));
    }
}

