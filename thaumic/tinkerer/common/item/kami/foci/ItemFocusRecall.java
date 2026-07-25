/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChunkCoordinates
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.api.wands.FocusUpgradeType
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.items.wands.ItemWandCasting
 */
package thaumic.tinkerer.common.item.kami.foci;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.tile.kami.TileWarpGate;
import thaumic.tinkerer.common.item.kami.ItemKamiResource;
import thaumic.tinkerer.common.item.kami.ItemSkyPearl;
import thaumic.tinkerer.common.item.kami.foci.ItemModKamiFocus;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.KamiResearchItem;
import thaumic.tinkerer.common.research.ResearchHelper;

public class ItemFocusRecall
extends ItemModKamiFocus {
    AspectList cost = new AspectList().add(Aspect.AIR, 4000).add(Aspect.EARTH, 4000).add(Aspect.ORDER, 4000);

    @Override
    protected boolean hasDepth() {
        return true;
    }

    @Override
    public boolean isUseItem(ItemStack stack) {
        return true;
    }

    @Override
    public void onUsingFocusTick(ItemStack paramItemStack, EntityPlayer paramEntityPlayer, int paramInt) {
        ItemWandCasting wand = (ItemWandCasting)paramItemStack.func_77973_b();
        if (Integer.MAX_VALUE - paramInt > 60) {
            int dim;
            ItemStack stackToCount = null;
            for (int i = 0; i < 9; ++i) {
                ItemStack stackInSlot = paramEntityPlayer.field_71071_by.func_70301_a(i);
                if (stackInSlot == null || !(stackInSlot.func_77973_b() instanceof ItemSkyPearl) || !ItemSkyPearl.isAttuned(stackInSlot)) continue;
                stackToCount = stackInSlot;
                break;
            }
            if (stackToCount != null && (dim = ItemSkyPearl.getDim(stackToCount)) == paramEntityPlayer.field_71093_bK) {
                int x = ItemSkyPearl.getX(stackToCount);
                int y = ItemSkyPearl.getY(stackToCount);
                int z = ItemSkyPearl.getZ(stackToCount);
                if (wand.consumeAllVis(paramItemStack, paramEntityPlayer, this.getVisCost(paramItemStack), false, false) && TileWarpGate.teleportPlayer(paramEntityPlayer, new ChunkCoordinates(x, y, z))) {
                    wand.consumeAllVis(paramItemStack, paramEntityPlayer, this.getVisCost(paramItemStack), true, false);
                }
            }
            paramEntityPlayer.func_71041_bz();
        }
    }

    public int getFocusColor(ItemStack stack) {
        return 10287359;
    }

    public AspectList getVisCost(ItemStack stack) {
        return this.cost;
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemStack, int i) {
        return new FocusUpgradeType[0];
    }

    @Override
    public String getSortingHelper(ItemStack paramItemStack) {
        return "RECALL";
    }

    @Override
    public String getItemName() {
        return "focusRecall";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        if (!Config.allowMirrors) {
            return null;
        }
        return (IRegisterableResearch)new KamiResearchItem("FOCUS_RECALL", new AspectList().add(Aspect.TRAVEL, 2).add(Aspect.ELDRITCH, 1).add(Aspect.FLIGHT, 1).add(Aspect.MAGIC, 1), 20, 8, 5, new ItemStack((Item)this)).setParents(new String[]{"WARP_GATE"}).setParentsHidden(new String[]{"ROD_ICHORCLOTH"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.infusionPage("FOCUS_RECALL")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("FOCUS_RECALL", new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemFocusRecall.class)), 10, new AspectList().add(Aspect.TRAVEL, 100).add(Aspect.ELDRITCH, 64).add(Aspect.MAGIC, 50), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemSkyPearl.class)), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class)), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class)), new ItemStack(Items.field_151079_bi), new ItemStack(Items.field_151045_i), new ItemStack(ConfigBlocks.blockMirror), new ItemStack(ConfigItems.itemFocusPortableHole));
    }
}

