/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.block.fire;

import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.common.block.fire.BlockFireBase;
import thaumic.tinkerer.common.core.handler.ConfigHandler;
import thaumic.tinkerer.common.core.helper.BlockTuple;
import thaumic.tinkerer.common.registry.ThaumicTinkererCrucibleRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class BlockFireWater
extends BlockFireBase {
    @Override
    public String getBlockName() {
        return "fireWater";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        if (!ConfigHandler.enableFire) {
            return null;
        }
        return (TTResearchItem)new TTResearchItem("FIRE_AQUA", new AspectList().add(Aspect.FIRE, 5).add(Aspect.WATER, 5), 2, -2, 2, new ItemStack((Block)this), new ResearchPage[0]).setParents(new String[]{"BRIGHT_NITOR"}).setConcealed().setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.crucibleRecipePage("FIRE_AQUA")}).setSecondary();
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        if (!ConfigHandler.enableFire) {
            return null;
        }
        return new ThaumicTinkererCrucibleRecipe("FIRE_AQUA", new ItemStack((Block)this), new ItemStack(ConfigItems.itemShard, 1, 2), new AspectList().add(Aspect.FIRE, 5).add(Aspect.MAGIC, 5).add(Aspect.WATER, 5));
    }

    @Override
    public HashMap<BlockTuple, BlockTuple> getBlockTransformation() {
        HashMap<BlockTuple, BlockTuple> result = new HashMap<BlockTuple, BlockTuple>();
        result.put(new BlockTuple((Block)Blocks.field_150354_m), new BlockTuple(Blocks.field_150432_aD));
        result.put(new BlockTuple(Blocks.field_150424_aL), new BlockTuple(Blocks.field_150433_aE));
        result.put(new BlockTuple(Blocks.field_150425_aM), new BlockTuple(Blocks.field_150432_aD));
        result.put(new BlockTuple(Blocks.field_150426_aN), new BlockTuple(Blocks.field_150432_aD));
        result.put(new BlockTuple(Blocks.field_150353_l), new BlockTuple(Blocks.field_150343_Z));
        result.put(new BlockTuple((Block)Blocks.field_150356_k), new BlockTuple(Blocks.field_150343_Z));
        return result;
    }

    @Override
    public HashMap<BlockTuple, BlockTuple> getBlockTransformation(World w, int x, int y, int z) {
        return this.getBlockTransformation();
    }
}

