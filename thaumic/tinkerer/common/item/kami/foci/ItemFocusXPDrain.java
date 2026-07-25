/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.api.wands.FocusUpgradeType
 *  thaumcraft.common.items.wands.ItemWandCasting
 */
package thaumic.tinkerer.common.item.kami.foci;

import java.awt.Color;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.core.helper.ExperienceHelper;
import thaumic.tinkerer.common.core.proxy.TTCommonProxy;
import thaumic.tinkerer.common.item.ItemXPTalisman;
import thaumic.tinkerer.common.item.kami.ItemKamiResource;
import thaumic.tinkerer.common.item.kami.foci.ItemModKamiFocus;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.KamiResearchItem;
import thaumic.tinkerer.common.research.ResearchHelper;

public class ItemFocusXPDrain
extends ItemModKamiFocus {
    AspectList cost = new AspectList();
    private int lastGiven = 0;

    @Override
    public boolean isVisCostPerTick(ItemStack stack) {
        return true;
    }

    @Override
    public void onUsingFocusTick(ItemStack paramItemStack, EntityPlayer paramEntityPlayer, int paramInt) {
        int xpUse;
        if (paramEntityPlayer.field_70170_p.field_72995_K) {
            return;
        }
        ItemWandCasting wand = (ItemWandCasting)paramItemStack.func_77973_b();
        AspectList aspects = wand.getAllVis(paramItemStack);
        Aspect aspectToAdd = null;
        for (int takes = 0; aspectToAdd == null && takes < 7; ++takes) {
            this.lastGiven = this.lastGiven == 5 ? 0 : this.lastGiven + 1;
            Aspect aspect = (Aspect)Aspect.getPrimalAspects().get(this.lastGiven);
            if (aspects.getAmount(aspect) >= wand.getMaxVis(paramItemStack)) continue;
            aspectToAdd = aspect;
        }
        if (aspectToAdd != null && paramEntityPlayer.field_71067_cb >= (xpUse = this.getXpUse(paramItemStack))) {
            ExperienceHelper.drainPlayerXP(paramEntityPlayer, xpUse);
            int amount = wand.getVis(paramItemStack, aspectToAdd) + 500;
            ThaumicTinkerer.log.info((Object)amount);
            wand.storeVis(paramItemStack, aspectToAdd, Math.min(wand.getMaxVis(paramItemStack), amount));
        }
    }

    public int func_82790_a(ItemStack par1ItemStack, int par2) {
        return this.getFocusColor(par1ItemStack);
    }

    public int getFocusColor(ItemStack stack) {
        EntityPlayer player = ThaumicTinkerer.proxy.getClientPlayer();
        return player == null ? 0xFFFFFF : Color.HSBtoRGB((float)(player.field_70173_aa * 2 % 360) / 360.0f, 1.0f, 1.0f);
    }

    int getXpUse(ItemStack stack) {
        return 15;
    }

    @Override
    protected void addVisCostTooltip(AspectList cost, ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(" " + EnumChatFormatting.GREEN + StatCollector.func_74838_a((String)"ttmisc.experience") + EnumChatFormatting.WHITE + " x " + this.getXpUse(stack));
    }

    public AspectList getVisCost(ItemStack stack) {
        return this.cost;
    }

    @Override
    public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemStack, int i) {
        return new FocusUpgradeType[0];
    }

    @Override
    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return TTCommonProxy.kamiRarity;
    }

    @Override
    public String getSortingHelper(ItemStack paramItemStack) {
        return "XPDRAIN";
    }

    @Override
    public String getItemName() {
        return "focusXPDrain";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return (IRegisterableResearch)new KamiResearchItem("FOCUS_XP_DRAIN", new AspectList().add(Aspect.MIND, 2).add(Aspect.MAGIC, 1).add(Aspect.AURA, 1).add(Aspect.MAN, 1), 12, 3, 5, new ItemStack((Item)this)).setParents(new String[]{"ROD_ICHORCLOTH"}).setPages(new ResearchPage[]{new ResearchPage("0"), ResearchHelper.infusionPage("FOCUS_XP_DRAIN")});
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererInfusionRecipe("FOCUS_XP_DRAIN", new ItemStack((Item)this), 12, new AspectList().add(Aspect.MIND, 65).add(Aspect.TAINT, 16).add(Aspect.MAGIC, 50).add(Aspect.AURA, 32), new ItemStack(Items.field_151079_bi), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class)), new ItemStack(Items.field_151062_by), new ItemStack(Items.field_151045_i), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemXPTalisman.class)), new ItemStack(Blocks.field_150381_bn), new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class)));
    }
}

