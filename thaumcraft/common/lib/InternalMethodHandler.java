/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package thaumcraft.common.lib;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.internal.IInternalMethodHandler;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.items.wands.WandManager;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.tiles.TileMagicWorkbench;

public class InternalMethodHandler
implements IInternalMethodHandler {
    @Override
    public void generateVisEffect(int dim, int x, int y, int z, int x2, int y2, int z2, int color) {
        Utils.generateVisEffect(dim, x, y, z, x2, y2, z2, color);
    }

    @Override
    public boolean isResearchComplete(String username, String researchkey) {
        return ResearchManager.isResearchComplete(username, researchkey);
    }

    @Override
    public boolean hasDiscoveredAspect(String username, Aspect aspect) {
        return Thaumcraft.proxy.getPlayerKnowledge().hasDiscoveredAspect(username, aspect);
    }

    @Override
    public AspectList getDiscoveredAspects(String username) {
        return Thaumcraft.proxy.getPlayerKnowledge().getAspectsDiscovered(username);
    }

    @Override
    public ItemStack getStackInRowAndColumn(Object instance, int row, int column) {
        return ((TileMagicWorkbench)((Object)instance)).getStackInRowAndColumn(row, column);
    }

    @Override
    public AspectList getObjectAspects(ItemStack is) {
        return ThaumcraftCraftingManager.getObjectTags(is);
    }

    @Override
    public AspectList getBonusObjectTags(ItemStack is, AspectList ot) {
        return ThaumcraftCraftingManager.getBonusTags(is, ot);
    }

    @Override
    public AspectList generateTags(Item item, int meta) {
        return ThaumcraftCraftingManager.generateTags(item, meta);
    }

    @Override
    public boolean consumeVisFromWand(ItemStack wand, EntityPlayer player, AspectList cost, boolean doit, boolean crafting) {
        if (wand.func_77973_b() instanceof ItemWandCasting) {
            return ((ItemWandCasting)wand.func_77973_b()).consumeAllVis(wand, player, cost, doit, crafting);
        }
        return false;
    }

    @Override
    public boolean consumeVisFromWandCrafting(ItemStack wand, EntityPlayer player, AspectList cost, boolean doit) {
        if (wand.func_77973_b() instanceof ItemWandCasting) {
            return ((ItemWandCasting)wand.func_77973_b()).consumeAllVisCrafting(wand, player, cost, doit);
        }
        return false;
    }

    @Override
    public boolean consumeVisFromInventory(EntityPlayer player, AspectList cost) {
        return WandManager.consumeVisFromInventory(player, cost);
    }

    @Override
    public void addWarpToPlayer(EntityPlayer player, int amount, boolean temporary) {
        Thaumcraft.addWarpToPlayer(player, amount, temporary);
    }

    @Override
    public void addStickyWarpToPlayer(EntityPlayer player, int amount) {
        Thaumcraft.addStickyWarpToPlayer(player, amount);
    }
}

