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

public class BlockFireAir
extends BlockFireBase {
    @Override
    public String getBlockName() {
        return "fireAir";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        if (!ConfigHandler.enableFire) {
            return null;
        }
        return (TTResearchItem)new TTResearchItem("FIRE_AER", new AspectList().add(Aspect.FIRE, 5).add(Aspect.AIR, 5), 3, -7, 2, new ItemStack((Block)this), new ResearchPage[0]).setParents(new String[]{"BRIGHT_NITOR"}).setConcealed().setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.crucibleRecipePage("FIRE_AER")}).setSecondary();
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        if (!ConfigHandler.enableFire) {
            return null;
        }
        return new ThaumicTinkererCrucibleRecipe("FIRE_AER", new ItemStack((Block)this), new ItemStack(ConfigItems.itemShard, 1, 0), new AspectList().add(Aspect.FIRE, 5).add(Aspect.MAGIC, 5).add(Aspect.AIR, 5));
    }

    @Override
    public HashMap<BlockTuple, BlockTuple> getBlockTransformation() {
        HashMap<BlockTuple, BlockTuple> result = new HashMap<BlockTuple, BlockTuple>();
        result.put(new BlockTuple(Blocks.field_150364_r), new BlockTuple((Block)Blocks.field_150354_m));
        result.put(new BlockTuple((Block)Blocks.field_150362_t), new BlockTuple(Blocks.field_150322_A));
        result.put(new BlockTuple((Block)Blocks.field_150361_u), new BlockTuple(Blocks.field_150322_A));
        result.put(new BlockTuple(Blocks.field_150363_s), new BlockTuple((Block)Blocks.field_150354_m));
        result.put(new BlockTuple(Blocks.field_150432_aD), new BlockTuple(Blocks.field_150359_w));
        if (ConfigHandler.enableCake) {
            result.put(new BlockTuple(Blocks.field_150355_j), new BlockTuple(Blocks.field_150414_aQ));
        }
        result.put(new BlockTuple(Blocks.field_150346_d), new BlockTuple((Block)Blocks.field_150354_m));
        result.put(new BlockTuple((Block)Blocks.field_150349_c), new BlockTuple((Block)Blocks.field_150354_m));
        return result;
    }

    @Override
    public HashMap<BlockTuple, BlockTuple> getBlockTransformation(World w, int x, int y, int z) {
        return this.getBlockTransformation();
    }
}

