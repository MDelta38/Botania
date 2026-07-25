/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.block;

import cpw.mods.fml.common.Loader;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.common.block.BlockCamo;
import thaumic.tinkerer.common.block.tile.TileCamo;
import thaumic.tinkerer.common.block.tile.TileGolemConnector;
import thaumic.tinkerer.common.registry.ThaumicTinkererArcaneRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class BlockGolemConnector
extends BlockCamo {
    public BlockGolemConnector() {
        super(Material.field_151575_d);
    }

    public TileCamo createNewTileEntity(World world, int meta) {
        return new TileGolemConnector();
    }

    @Override
    public boolean func_149662_c() {
        return true;
    }

    @Override
    public boolean func_149686_d() {
        return true;
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getBlockName() {
        return "golemConnector";
    }

    @Override
    public boolean shouldRegister() {
        return Loader.isModLoaded((String)"ComputerCraft");
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
        return TileGolemConnector.class;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch)new TTResearchItem("GOLEM_CONNECTOR", new AspectList().add(Aspect.ORDER, 1).add(Aspect.TRAVEL, 2).add(Aspect.TOOL, 1), 1, 0, 0, new ItemStack((Block)this), new ResearchPage[0]).setParents(new String[]{"PERIPHERALS"}).setParentsHidden(new String[]{"GOLEMBELL"}).setConcealed().setRound().setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.arcaneRecipePage("GOLEM_CONNECTOR"), new ResearchPage("1"), ResearchHelper.arcaneRecipePage("INTERFACE1"), new ResearchPage("2"), new ResearchPage("3")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererArcaneRecipe("GOLEM_CONNECTOR", "GOLEM_CONNECTOR", new ItemStack((Block)this), new AspectList().add(Aspect.AIR, 20).add(Aspect.ORDER, 5).add(Aspect.ENTROPY, 15), "WFW", "sIs", "WFW", Character.valueOf('I'), new ItemStack(ConfigItems.itemGolemBell), Character.valueOf('s'), new ItemStack(Items.field_151079_bi), Character.valueOf('W'), new ItemStack(ConfigBlocks.blockMagicalLog), Character.valueOf('F'), new ItemStack(Blocks.field_150451_bX));
    }
}

