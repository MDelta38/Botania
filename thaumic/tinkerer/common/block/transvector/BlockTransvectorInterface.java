/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigBlocks
 */
package thaumic.tinkerer.common.block.transvector;

import java.util.ArrayList;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigBlocks;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.BlockCamo;
import thaumic.tinkerer.common.block.tile.transvector.TileTransvector;
import thaumic.tinkerer.common.block.tile.transvector.TileTransvectorInterface;
import thaumic.tinkerer.common.registry.ThaumicTinkererArcaneRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class BlockTransvectorInterface
extends BlockCamo {
    public BlockTransvectorInterface() {
        super(Material.field_151573_f);
        this.func_149711_c(3.0f);
        this.func_149752_b(10.0f);
    }

    public TileTransvector createNewTileEntity(World var1, int var2) {
        return new TileTransvectorInterface();
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getBlockName() {
        return "interface";
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
        return TileTransvectorInterface.class;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch)new TTResearchItem("INTERFACE", new AspectList().add(Aspect.ENTROPY, 4).add(Aspect.ORDER, 4), -4, 2, 1, new ItemStack(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockTransvectorInterface.class)), new ResearchPage[0]).setParents(new String[]{"DARK_QUARTZ"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.arcaneRecipePage("INTERFACE"), new ResearchPage("1"), ResearchHelper.arcaneRecipePage("INTERFACE1"), new ResearchPage("2")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererArcaneRecipe("INTERFACE", "INTERFACE", new ItemStack(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockTransvectorInterface.class)), new AspectList().add(Aspect.ORDER, 12).add(Aspect.ENTROPY, 16), "BRB", "LEL", "BRB", Character.valueOf('B'), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6), Character.valueOf('E'), new ItemStack(Items.field_151079_bi), Character.valueOf('L'), new ItemStack(Items.field_151100_aR, 1, 4), Character.valueOf('R'), new ItemStack(Items.field_151137_ax));
    }
}

