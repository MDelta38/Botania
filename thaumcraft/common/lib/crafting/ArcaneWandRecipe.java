/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package thaumcraft.common.lib.crafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;

public class ArcaneWandRecipe
implements IArcaneRecipe {
    private static final int MAX_CRAFT_GRID_WIDTH = 3;
    private static final int MAX_CRAFT_GRID_HEIGHT = 3;
    private boolean mirrored = true;

    @Override
    public ItemStack getCraftingResult(IInventory inv) {
        ItemStack out = null;
        String bc = null;
        String br = null;
        int cc = 0;
        int cr = 0;
        ItemStack cap1 = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 2);
        ItemStack cap2 = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 0);
        ItemStack rod = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 1);
        if (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 0) != null || ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 1) != null || ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 0) != null || ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 2) != null || ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 1) != null || ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 2) != null) {
            return null;
        }
        if (cap1 != null && cap2 != null && rod != null && this.checkItemEquals(cap1, cap2)) {
            for (WandCap wc : WandCap.caps.values()) {
                if (!this.checkItemEquals(cap1, wc.getItem())) continue;
                bc = wc.getTag();
                cc = wc.getCraftCost();
                break;
            }
            for (WandRod wr : WandRod.rods.values()) {
                if (!this.checkItemEquals(rod, wr.getItem())) continue;
                br = wr.getTag();
                cr = wr.getCraftCost();
                break;
            }
            if (!(bc == null || br == null || br.equals("wood") && bc.equals("iron"))) {
                int cost = cc * cr;
                out = new ItemStack(ConfigItems.itemWandCasting, 1, cost);
                ((ItemWandCasting)out.func_77973_b()).setCap(out, WandCap.caps.get(bc));
                ((ItemWandCasting)out.func_77973_b()).setRod(out, WandRod.rods.get(br));
            }
        }
        return out;
    }

    @Override
    public AspectList getAspects(IInventory inv) {
        AspectList al = new AspectList();
        int cc = -1;
        int cr = -1;
        ItemStack cap1 = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 2);
        ItemStack cap2 = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 0);
        ItemStack rod = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 1);
        if (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 0) != null || ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 1) != null || ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 0) != null || ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 2) != null || ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 1) != null || ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 2) != null) {
            return al;
        }
        if (cap1 != null && cap2 != null && rod != null && this.checkItemEquals(cap1, cap2)) {
            for (WandCap wc : WandCap.caps.values()) {
                if (!this.checkItemEquals(cap1, wc.getItem())) continue;
                cc = wc.getCraftCost();
                break;
            }
            for (WandRod wr : WandRod.rods.values()) {
                if (!this.checkItemEquals(rod, wr.getItem())) continue;
                cr = wr.getCraftCost();
                break;
            }
            if (cc >= 0 && cr >= 0) {
                int cost = cc * cr;
                for (Aspect as : Aspect.getPrimalAspects()) {
                    al.add(as, cost);
                }
            }
        }
        return al;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return null;
    }

    @Override
    public boolean matches(IInventory inv, World world, EntityPlayer player) {
        ItemStack cap1 = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 2);
        ItemStack cap2 = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 0);
        ItemStack rod = ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 1);
        if (ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 0) != null || ThaumcraftApiHelper.getStackInRowAndColumn(inv, 0, 1) != null || ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 0) != null || ThaumcraftApiHelper.getStackInRowAndColumn(inv, 1, 2) != null || ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 1) != null || ThaumcraftApiHelper.getStackInRowAndColumn(inv, 2, 2) != null) {
            return false;
        }
        return this.checkMatch(cap1, cap2, rod, player);
    }

    private boolean checkMatch(ItemStack cap1, ItemStack cap2, ItemStack rod, EntityPlayer player) {
        boolean bc = false;
        boolean br = false;
        if (cap1 != null && cap2 != null && rod != null && this.checkItemEquals(cap1, cap2)) {
            for (WandCap wc : WandCap.caps.values()) {
                if (!this.checkItemEquals(cap1, wc.getItem()) || !ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), wc.getResearch())) continue;
                bc = true;
                break;
            }
            for (WandRod wr : WandRod.rods.values()) {
                if (!this.checkItemEquals(rod, wr.getItem()) || !ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), wr.getResearch())) continue;
                br = true;
                break;
            }
        }
        return br && bc;
    }

    private boolean checkItemEquals(ItemStack target, ItemStack input) {
        if (input == null && target != null || input != null && target == null) {
            return false;
        }
        return !(target.func_77973_b() != input.func_77973_b() || target.func_77942_o() && !ItemStack.func_77970_a((ItemStack)target, (ItemStack)input) || target.func_77960_j() != Short.MAX_VALUE && target.func_77960_j() != input.func_77960_j());
    }

    @Override
    public int getRecipeSize() {
        return 9;
    }

    @Override
    public AspectList getAspects() {
        return null;
    }

    @Override
    public String getResearch() {
        return "";
    }
}

