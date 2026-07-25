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

public class BlockFireIgnis
extends BlockFireBase {
    @Override
    public String getBlockName() {
        return "fireFire";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        if (!ConfigHandler.enableFire) {
            return null;
        }
        return (TTResearchItem)new TTResearchItem("FIRE_IGNIS", new AspectList().add(Aspect.FIRE, 10), 4, -4, 2, new ItemStack((Block)this), new ResearchPage[0]).setParents(new String[]{"BRIGHT_NITOR"}).setConcealed().setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.crucibleRecipePage("FIRE_IGNIS")}).setSecondary();
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        if (!ConfigHandler.enableFire) {
            return null;
        }
        return new ThaumicTinkererCrucibleRecipe("FIRE_IGNIS", new ItemStack((Block)this), new ItemStack(ConfigItems.itemShard, 1, 1), new AspectList().add(Aspect.FIRE, 10).add(Aspect.AIR, 5));
    }

    @Override
    public HashMap<BlockTuple, BlockTuple> getBlockTransformation() {
        HashMap<BlockTuple, BlockTuple> result = new HashMap<BlockTuple, BlockTuple>();
        result.put(new BlockTuple((Block)Blocks.field_150349_c), new BlockTuple(Blocks.field_150424_aL));
        result.put(new BlockTuple(Blocks.field_150346_d), new BlockTuple(Blocks.field_150424_aL));
        result.put(new BlockTuple((Block)Blocks.field_150354_m), new BlockTuple(Blocks.field_150425_aM));
        result.put(new BlockTuple(Blocks.field_150351_n), new BlockTuple(Blocks.field_150425_aM));
        result.put(new BlockTuple(Blocks.field_150435_aG), new BlockTuple(Blocks.field_150426_aN));
        result.put(new BlockTuple(Blocks.field_150365_q), new BlockTuple(Blocks.field_150449_bY));
        result.put(new BlockTuple(Blocks.field_150366_p), new BlockTuple(Blocks.field_150449_bY));
        result.put(new BlockTuple(Blocks.field_150482_ag), new BlockTuple(Blocks.field_150449_bY));
        result.put(new BlockTuple(Blocks.field_150412_bA), new BlockTuple(Blocks.field_150449_bY));
        result.put(new BlockTuple(Blocks.field_150340_R), new BlockTuple(Blocks.field_150449_bY));
        result.put(new BlockTuple(Blocks.field_150369_x), new BlockTuple(Blocks.field_150449_bY));
        result.put(new BlockTuple(Blocks.field_150450_ax), new BlockTuple(Blocks.field_150449_bY));
        result.put(new BlockTuple(Blocks.field_150439_ay), new BlockTuple(Blocks.field_150449_bY));
        result.put(new BlockTuple(Blocks.field_150355_j), new BlockTuple(Blocks.field_150353_l));
        result.put(new BlockTuple(Blocks.field_150464_aj), new BlockTuple(Blocks.field_150388_bm));
        result.put(new BlockTuple(Blocks.field_150469_bN), new BlockTuple(Blocks.field_150388_bm));
        result.put(new BlockTuple(Blocks.field_150459_bM), new BlockTuple(Blocks.field_150388_bm));
        result.put(new BlockTuple((Block)Blocks.field_150328_O), new BlockTuple((Block)Blocks.field_150338_P));
        result.put(new BlockTuple((Block)Blocks.field_150327_N), new BlockTuple((Block)Blocks.field_150327_N));
        return result;
    }

    @Override
    public HashMap<BlockTuple, BlockTuple> getBlockTransformation(World w, int x, int y, int z) {
        return this.getBlockTransformation();
    }
}

