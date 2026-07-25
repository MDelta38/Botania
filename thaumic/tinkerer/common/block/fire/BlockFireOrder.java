/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.CraftingManager
 *  net.minecraft.item.crafting.FurnaceRecipes
 *  net.minecraft.world.World
 *  net.minecraftforge.oredict.OreDictionary
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.block.fire;

import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
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

public class BlockFireOrder
extends BlockFireBase {
    public HashMap<BlockTuple, BlockTuple> fireResults = new HashMap();
    private HashMap<Block, Block> oreDictinaryOresCache;

    public BlockFireOrder() {
        this.fireResults.put(new BlockTuple(Blocks.field_150348_b), null);
        this.fireResults.put(new BlockTuple(Blocks.field_150359_w), null);
        this.fireResults.put(new BlockTuple((Block)Blocks.field_150354_m), null);
        this.fireResults.put(new BlockTuple(Blocks.field_150351_n), null);
    }

    @Override
    public String getBlockName() {
        return "fireOrder";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        if (!ConfigHandler.enableFire) {
            return null;
        }
        return (TTResearchItem)new TTResearchItem("FIRE_ORDO", new AspectList().add(Aspect.FIRE, 5).add(Aspect.ORDER, 5), 3, -3, 2, new ItemStack((Block)this), new ResearchPage[0]).setParents(new String[]{"BRIGHT_NITOR"}).setConcealed().setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.crucibleRecipePage("FIRE_ORDO")}).setSecondary();
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        if (!ConfigHandler.enableFire) {
            return null;
        }
        return new ThaumicTinkererCrucibleRecipe("FIRE_ORDO", new ItemStack((Block)this), new ItemStack(ConfigItems.itemShard, 1, 4), new AspectList().add(Aspect.FIRE, 5).add(Aspect.MAGIC, 5).add(Aspect.ORDER, 5));
    }

    @Override
    public int getDecayChance(World world, int x, int y, int z) {
        int dropSize = world.func_147439_a(x, y, z).getDrops(world, x, y, z, world.func_72805_g(x, y, z), 0).size();
        if (dropSize == 2) {
            return 2;
        }
        if (dropSize >= 3) {
            return 1;
        }
        return 3;
    }

    public HashMap<Block, Block> getOreDictionaryOres() {
        if (this.oreDictinaryOresCache == null) {
            HashMap<Block, Block> result = new HashMap<Block, Block>();
            for (String ore : OreDictionary.getOreNames()) {
                if (!ore.startsWith("ore")) continue;
                for (String block : OreDictionary.getOreNames()) {
                    if (!block.startsWith("block") || !block.substring(5).equalsIgnoreCase(ore.substring(3)) || OreDictionary.getOres((String)block).size() <= 0 || OreDictionary.getOres((String)ore).size() <= 0) continue;
                    result.put(((ItemBlock)((ItemStack)OreDictionary.getOres((String)ore).get((int)0)).func_77973_b()).field_150939_a, ((ItemBlock)((ItemStack)OreDictionary.getOres((String)block).get((int)0)).func_77973_b()).field_150939_a);
                }
            }
            this.oreDictinaryOresCache = result;
        }
        return this.oreDictinaryOresCache;
    }

    @Override
    public HashMap<BlockTuple, BlockTuple> getBlockTransformation() {
        return this.fireResults;
    }

    @Override
    public boolean isTransmutationResult(BlockTuple block, World w, int x, int y, int z) {
        return this.getBlockTransformation(w, x, y, z).values().contains(block);
    }

    public ItemStack getBlockCraftingResult(World w, ItemStack itemStack) {
        InventoryCrafting blockCraftInventory = new InventoryCrafting(new Container(){

            public boolean func_75145_c(EntityPlayer entityPlayer) {
                return false;
            }
        }, 3, 3);
        for (int i = 0; i < 9; ++i) {
            blockCraftInventory.func_70299_a(i, itemStack);
        }
        return CraftingManager.func_77594_a().func_82787_a(blockCraftInventory, w);
    }

    private boolean allEqual(ArrayList<ItemStack> list) {
        for (ItemStack o : list) {
            if (o.func_77969_a(list.get(0))) continue;
            return false;
        }
        return true;
    }

    @Override
    public HashMap<BlockTuple, BlockTuple> getBlockTransformation(World w, int x, int y, int z) {
        int meta;
        Block block = w.func_147439_a(x, y, z);
        if (!this.fireResults.containsKey(new BlockTuple(block, meta = w.func_72805_g(x, y, z)))) {
            Block result = null;
            int resultMeta = 0;
            ArrayList drops = block.getDrops(w, x, y, z, meta, 0);
            if (drops.size() > 0 && (drops.size() == 1 || this.allEqual(drops))) {
                ItemStack stack = (ItemStack)drops.get(0);
                ItemStack blockStack = this.getBlockCraftingResult(w, stack);
                if (blockStack == null) {
                    ItemStack ingotStack = FurnaceRecipes.func_77602_a().func_151395_a(stack);
                    blockStack = this.getBlockCraftingResult(w, ingotStack);
                }
                if (blockStack != null && Block.func_149634_a((Item)blockStack.func_77973_b()) != null) {
                    result = Block.func_149634_a((Item)blockStack.func_77973_b());
                    resultMeta = blockStack.func_77960_j();
                }
            }
            this.fireResults.put(new BlockTuple(block, meta), new BlockTuple(result, resultMeta));
        }
        return this.fireResults;
    }

    @Override
    public boolean isTransmutationTarget(BlockTuple block, World w, int x, int y, int z) {
        return this.getBlockTransformation(w, x, y, z).keySet().contains(block) && this.getBlockTransformation().get(block) != null && this.getBlockTransformation().get((Object)block).block != null;
    }
}

