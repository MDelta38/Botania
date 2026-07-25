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
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.fire.BlockFireAir;
import thaumic.tinkerer.common.block.fire.BlockFireBase;
import thaumic.tinkerer.common.block.fire.BlockFireEarth;
import thaumic.tinkerer.common.block.fire.BlockFireIgnis;
import thaumic.tinkerer.common.block.fire.BlockFireOrder;
import thaumic.tinkerer.common.block.fire.BlockFireWater;
import thaumic.tinkerer.common.core.handler.ConfigHandler;
import thaumic.tinkerer.common.core.helper.BlockTuple;
import thaumic.tinkerer.common.registry.ThaumicTinkererCrucibleRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class BlockFireChaos
extends BlockFireBase {
    @Override
    public String getBlockName() {
        return "fireChaos";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        if (!ConfigHandler.enableFire) {
            return null;
        }
        return (TTResearchItem)new TTResearchItem("FIRE_PERDITIO", new AspectList().add(Aspect.FIRE, 5).add(Aspect.ENTROPY, 5), 2, -8, 3, new ItemStack((Block)this), new ResearchPage[0]).setParents(new String[]{"BRIGHT_NITOR"}).setConcealed().setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.crucibleRecipePage("FIRE_PERDITIO")}).setSecondary();
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        if (!ConfigHandler.enableFire) {
            return null;
        }
        return new ThaumicTinkererCrucibleRecipe("FIRE_PERDITIO", new ItemStack((Block)this), new ItemStack(ConfigItems.itemShard, 1, 5), new AspectList().add(Aspect.FIRE, 5).add(Aspect.MAGIC, 5).add(Aspect.ENTROPY, 5));
    }

    @Override
    public int func_149738_a(World p_149738_1_) {
        return 1;
    }

    @Override
    public HashMap<BlockTuple, BlockTuple> getBlockTransformation() {
        HashMap<BlockTuple, BlockTuple> result = new HashMap<BlockTuple, BlockTuple>();
        result.put(new BlockTuple(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockFireAir.class)), new BlockTuple((Block)Blocks.field_150480_ab));
        result.put(new BlockTuple(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockFireWater.class)), new BlockTuple((Block)Blocks.field_150480_ab));
        result.put(new BlockTuple(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockFireEarth.class)), new BlockTuple((Block)Blocks.field_150480_ab));
        result.put(new BlockTuple(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockFireIgnis.class)), new BlockTuple((Block)Blocks.field_150480_ab));
        result.put(new BlockTuple(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockFireOrder.class)), new BlockTuple((Block)Blocks.field_150480_ab));
        return result;
    }

    @Override
    public HashMap<BlockTuple, BlockTuple> getBlockTransformation(World w, int x, int y, int z) {
        return this.getBlockTransformation();
    }
}

