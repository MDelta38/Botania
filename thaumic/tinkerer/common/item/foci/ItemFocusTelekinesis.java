/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.api.wands.FocusUpgradeType
 *  thaumcraft.codechicken.lib.vec.Vector3
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.items.wands.ItemWandCasting
 */
package thaumic.tinkerer.common.item.foci;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.codechicken.lib.vec.Vector3;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.core.helper.MiscHelper;
import thaumic.tinkerer.common.item.foci.ItemModFocus;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class ItemFocusTelekinesis
extends ItemModFocus {
    private static final AspectList visUsage = new AspectList().add(Aspect.AIR, 5).add(Aspect.ENTROPY, 5);

    @Override
    public void onUsingFocusTick(ItemStack stack, EntityPlayer player, int ticks) {
        ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
        Vector3 target = Vector3.fromEntityCenter((Entity)player);
        int range = 6;
        double distance = 5.0;
        if (!player.func_70093_af()) {
            target.add(new Vector3(player.func_70040_Z()).multiply(5.0));
        }
        target.y += 0.5;
        List entities = player.field_70170_p.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)(target.x - 6.0), (double)(target.y - 6.0), (double)(target.z - 6.0), (double)(target.x + 6.0), (double)(target.y + 6.0), (double)(target.z + 6.0)));
        if (!entities.isEmpty() && wand.consumeAllVis(stack, player, this.getVisCost(stack), true, false)) {
            for (EntityItem item : entities) {
                MiscHelper.setEntityMotionFromVector((Entity)item, target, 0.3333f);
                ThaumicTinkerer.tcProxy.sparkle((float)item.field_70165_t, (float)item.field_70163_u, (float)item.field_70161_v, 0);
            }
        }
    }

    @Override
    public String getSortingHelper(ItemStack itemstack) {
        return "TELEKINESIS";
    }

    @Override
    protected boolean hasOrnament() {
        return true;
    }

    public int getFocusColor(ItemStack stack) {
        return 10223806;
    }

    public AspectList getVisCost(ItemStack stack) {
        return visUsage;
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemStack, int i) {
        return new FocusUpgradeType[0];
    }

    @Override
    public boolean isVisCostPerTick(ItemStack stack) {
        return true;
    }

    @Override
    public String getItemName() {
        return "focusTelekinesis";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (TTResearchItem)new TTResearchItem("FOCUS_TELEKINESIS", new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.MAGIC, 1).add(Aspect.MOTION, 1), -4, -6, 2, new ItemStack((Item)this), new ResearchPage[0]).setParents(new String[]{"FOCUS_FLIGHT"}).setConcealed().setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.infusionPage("FOCUS_TELEKINESIS")}).setSecondary();
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("FOCUS_TELEKINESIS", new ItemStack((Item)this), 5, new AspectList().add(Aspect.MOTION, 10).add(Aspect.AIR, 20).add(Aspect.ENTROPY, 20).add(Aspect.MIND, 10), new ItemStack(Items.field_151079_bi), new ItemStack(Items.field_151128_bU), new ItemStack(Items.field_151128_bU), new ItemStack(Items.field_151128_bU), new ItemStack(Items.field_151128_bU), new ItemStack(Items.field_151042_j), new ItemStack(Items.field_151043_k), new ItemStack(ConfigItems.itemShard, 1, 0));
    }
}

