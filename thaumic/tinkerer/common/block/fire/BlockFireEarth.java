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

public class BlockFireEarth
extends BlockFireBase {
    @Override
    public String getBlockName() {
        return "fireEarth";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        if (!ConfigHandler.enableFire) {
            return null;
        }
        return (TTResearchItem)new TTResearchItem("FIRE_TERRA", new AspectList().add(Aspect.FIRE, 5).add(Aspect.EARTH, 5), 4, -6, 2, new ItemStack((Block)this), new ResearchPage[0]).setParents(new String[]{"BRIGHT_NITOR"}).setConcealed().setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.crucibleRecipePage("FIRE_TERRA")}).setSecondary();
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        if (!ConfigHandler.enableFire) {
            return null;
        }
        return new ThaumicTinkererCrucibleRecipe("FIRE_TERRA", new ItemStack((Block)this), new ItemStack(ConfigItems.itemShard, 1, 3), new AspectList().add(Aspect.FIRE, 5).add(Aspect.MAGIC, 5).add(Aspect.EARTH, 5));
    }

    @Override
    public HashMap<BlockTuple, BlockTuple> getBlockTransformation() {
        HashMap<BlockTuple, BlockTuple> result = new HashMap<BlockTuple, BlockTuple>();
        result.put(new BlockTuple((Block)Blocks.field_150354_m), new BlockTuple(Blocks.field_150346_d));
        result.put(new BlockTuple(Blocks.field_150351_n), new BlockTuple(Blocks.field_150435_aG));
        result.put(new BlockTuple(Blocks.field_150385_bj), new BlockTuple(Blocks.field_150344_f));
        result.put(new BlockTuple(Blocks.field_150386_bk), new BlockTuple(Blocks.field_150422_aJ));
        result.put(new BlockTuple(Blocks.field_150387_bl), new BlockTuple(Blocks.field_150476_ad));
        result.put(new BlockTuple(Blocks.field_150434_aF), new BlockTuple(Blocks.field_150364_r));
        result.put(new BlockTuple(Blocks.field_150431_aC), new BlockTuple((Block)Blocks.field_150329_H));
        result.put(new BlockTuple(Blocks.field_150348_b), new BlockTuple(Blocks.field_150346_d));
        result.put(new BlockTuple(Blocks.field_150474_ac), new BlockTuple(Blocks.field_150339_S));
        result.put(new BlockTuple(Blocks.field_150364_r), new BlockTuple(Blocks.field_150346_d));
        result.put(new BlockTuple(Blocks.field_150363_s), new BlockTuple(Blocks.field_150346_d));
        result.put(new BlockTuple((Block)Blocks.field_150362_t), new BlockTuple(Blocks.field_150346_d));
        result.put(new BlockTuple((Block)Blocks.field_150361_u), new BlockTuple(Blocks.field_150346_d));
        result.put(new BlockTuple(Blocks.field_150347_e), new BlockTuple(Blocks.field_150346_d));
        result.put(new BlockTuple(Blocks.field_150344_f), new BlockTuple(Blocks.field_150346_d));
        result.put(new BlockTuple(Blocks.field_150359_w), new BlockTuple(Blocks.field_150346_d));
        return result;
    }

    @Override
    public HashMap<BlockTuple, BlockTuple> getBlockTransformation(World w, int x, int y, int z) {
        return this.getBlockTransformation();
    }
}

