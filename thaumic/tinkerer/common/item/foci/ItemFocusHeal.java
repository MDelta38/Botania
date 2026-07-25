/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.api.wands.FocusUpgradeType
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.items.wands.ItemWandCasting
 */
package thaumic.tinkerer.common.item.foci;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.item.foci.ItemModFocus;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class ItemFocusHeal
extends ItemModFocus {
    private static final AspectList visUsage = new AspectList().add(Aspect.EARTH, 45).add(Aspect.WATER, 45);
    public static Map<String, Integer> playerHealData = new HashMap<String, Integer>();

    @Override
    public void onUsingFocusTick(ItemStack stack, EntityPlayer p, int time) {
        ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
        if (!wand.consumeAllVis(stack, p, visUsage, false, false) || !p.func_70996_bM()) {
            return;
        }
        int potency = this.getUpgradeLevel(stack, FocusUpgradeType.potency);
        if (!playerHealData.containsKey(p.func_146103_bH().getName())) {
            playerHealData.put(p.func_146103_bH().getName(), 0);
        }
        int progress = playerHealData.get(p.func_146103_bH().getName()) + 1;
        playerHealData.put(p.func_146103_bH().getName(), progress);
        ThaumicTinkerer.tcProxy.sparkle((float)p.field_70165_t + p.field_70170_p.field_73012_v.nextFloat() - 0.5f, (float)p.field_70163_u + p.field_70170_p.field_73012_v.nextFloat(), (float)p.field_70161_v + p.field_70170_p.field_73012_v.nextFloat() - 0.5f, 0);
        if (progress >= 30 - potency * 10 / 3) {
            playerHealData.put(p.func_146103_bH().getName(), 0);
            wand.consumeAllVis(stack, p, visUsage, true, false);
            p.func_70691_i(1.0f);
            p.field_70170_p.func_72956_a((Entity)p, "thaumcraft:wand", 0.5f, 1.0f);
        }
    }

    @Override
    public void onPlayerStoppedUsingFocus(ItemStack paramItemStack, World paramWorld, EntityPlayer paramEntityPlayer, int paramInt) {
        playerHealData.put(paramEntityPlayer.func_146103_bH().getName(), 0);
    }

    @Override
    protected boolean hasDepth() {
        return true;
    }

    @Override
    public boolean isVisCostPerTick(ItemStack stack) {
        return false;
    }

    @Override
    public boolean isUseItem(ItemStack stack) {
        return true;
    }

    @Override
    public String getSortingHelper(ItemStack paramItemStack) {
        return "HEAL";
    }

    public int getFocusColor(ItemStack stack) {
        return 16580822;
    }

    public AspectList getVisCost(ItemStack stack) {
        return visUsage;
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemStack, int i) {
        return new FocusUpgradeType[]{FocusUpgradeType.treasure, FocusUpgradeType.potency};
    }

    @Override
    public String getItemName() {
        return "focusHeal";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        if (!Config.allowMirrors) {
            return null;
        }
        return (TTResearchItem)new TTResearchItem("FOCUS_HEAL", new AspectList().add(Aspect.HEAL, 2).add(Aspect.SOUL, 1).add(Aspect.MAGIC, 1), -6, -4, 2, new ItemStack((Item)this), new ResearchPage[0]).setParents(new String[]{"FOCUS_DEFLECT"}).setConcealed().setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.infusionPage("FOCUS_HEAL")}).setSecondary();
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("FOCUS_HEAL", new ItemStack((Item)this), 4, new AspectList().add(Aspect.HEAL, 10).add(Aspect.SOUL, 10).add(Aspect.LIFE, 15), new ItemStack(ConfigItems.itemFocusPech), new ItemStack(Items.field_151150_bK), new ItemStack(Items.field_151074_bl), new ItemStack(Items.field_151074_bl), new ItemStack(Items.field_151074_bl));
    }
}

