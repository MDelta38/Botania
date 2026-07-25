/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.items.wands.ItemWandCasting
 */
package thaumic.tinkerer.common.item.foci;

import cpw.mods.fml.common.Loader;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumic.tinkerer.common.compat.EnderStorageFunctions;
import thaumic.tinkerer.common.item.foci.ItemModFocus;
import thaumic.tinkerer.common.registry.ThaumicTinkererArcaneRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class ItemFocusEnderChest
extends ItemModFocus {
    public static final AspectList visUsage = new AspectList().add(Aspect.ENTROPY, 100).add(Aspect.ORDER, 100);

    @Override
    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        super.func_77624_a(stack, player, list, par4);
        if (Loader.isModLoaded((String)"EnderStorage")) {
            EnderStorageFunctions.addFocusInformation(stack, player, list, par4);
        }
    }

    @Override
    public ItemStack onFocusRightClick(ItemStack stack, World world, EntityPlayer p, MovingObjectPosition pos) {
        if (Loader.isModLoaded((String)"EnderStorage")) {
            return EnderStorageFunctions.onFocusRightClick(stack, world, p, pos);
        }
        ItemWandCasting wand = (ItemWandCasting)stack.func_77973_b();
        if (wand.consumeAllVis(stack, p, visUsage, true, false)) {
            p.func_71007_a((IInventory)p.func_71005_bN());
            world.func_72956_a((Entity)p, "mob.endermen.portal", 1.0f, 1.0f);
        }
        return stack;
    }

    public int getFocusColor(ItemStack stack) {
        return 0x132223;
    }

    @Override
    protected boolean hasDepth() {
        return true;
    }

    @Override
    public String getSortingHelper(ItemStack paramItemStack) {
        if (Loader.isModLoaded((String)"EnderStorage")) {
            return EnderStorageFunctions.getSortingHelper(paramItemStack);
        }
        return "ENDERCHEST";
    }

    public AspectList getVisCost(ItemStack stack) {
        return visUsage;
    }

    @Override
    public String getItemName() {
        return "focusEnderChest";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        if (!Config.allowMirrors) {
            return null;
        }
        TTResearchItem research = (TTResearchItem)new TTResearchItem("FOCUS_ENDER_CHEST", new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.VOID, 1).add(Aspect.MAGIC, 1), -6, -2, 2, new ItemStack((Item)this), new ResearchPage[0]).setWarp(1).setParents(new String[]{"FOCUS_DEFLECT"}).setConcealed();
        if (Loader.isModLoaded((String)"EnderStorage")) {
            research.setPages(new ResearchPage("ES"), ResearchHelper.arcaneRecipePage("FOCUS_ENDER_CHEST"));
        } else {
            research.setPages(new ResearchPage("0"), ResearchHelper.arcaneRecipePage("FOCUS_ENDER_CHEST"));
        }
        return research;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        if (Config.allowMirrors) {
            return new ThaumicTinkererArcaneRecipe("FOCUS_ENDER_CHEST", "FOCUS_ENDER_CHEST", new ItemStack((Item)this), new AspectList().add(Aspect.ORDER, 10).add(Aspect.ENTROPY, 10), "M", "E", "P", Character.valueOf('M'), new ItemStack(ConfigBlocks.blockMirror), Character.valueOf('E'), new ItemStack(Items.field_151061_bv), Character.valueOf('P'), new ItemStack(ConfigItems.itemFocusPortableHole));
        }
        return null;
    }
}

