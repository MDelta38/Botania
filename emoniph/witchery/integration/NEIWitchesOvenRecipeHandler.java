/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.nei.ItemList
 *  codechicken.nei.NEIServerUtils
 *  codechicken.nei.PositionedStack
 *  codechicken.nei.recipe.TemplateRecipeHandler
 *  codechicken.nei.recipe.TemplateRecipeHandler$CachedRecipe
 *  codechicken.nei.recipe.TemplateRecipeHandler$RecipeTransferRect
 *  net.minecraft.block.Block
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.FurnaceRecipes
 *  net.minecraft.tileentity.TileEntityFurnace
 *  net.minecraft.util.StatCollector
 */
package com.emoniph.witchery.integration;

import codechicken.nei.ItemList;
import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockWitchesOvenGUI;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.StatCollector;

public class NEIWitchesOvenRecipeHandler
extends TemplateRecipeHandler {
    public static ArrayList<FuelPair> afuels;
    public static TreeSet<Integer> efuels;

    public Class<? extends GuiContainer> getGuiClass() {
        return BlockWitchesOvenGUI.class;
    }

    public String getRecipeName() {
        return StatCollector.func_74838_a((String)"tile.witchery:witchesovenidle.name");
    }

    public void loadCraftingRecipes(String outputId, Object ... results) {
        if (outputId.equals("witchery_cooking") && ((Object)((Object)this)).getClass() == NEIWitchesOvenRecipeHandler.class) {
            Map recipes = FurnaceRecipes.func_77602_a().func_77599_b();
            this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 0), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack()));
            this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 1), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemHintOfRebirth.createStack()));
            this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 2), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemBreathOfTheGoddess.createStack()));
            this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 3), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemFoulFume.createStack()));
            this.arecipes.add(new SmeltingPair(new ItemStack(Witchery.Blocks.SAPLING, 1, 0), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemWhiffOfMagic.createStack()));
            this.arecipes.add(new SmeltingPair(new ItemStack(Witchery.Blocks.SAPLING, 1, 1), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemReekOfMisfortune.createStack()));
            this.arecipes.add(new SmeltingPair(new ItemStack(Witchery.Blocks.SAPLING, 1, 2), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemOdourOfPurity.createStack()));
            for (Map.Entry recipe : recipes.entrySet()) {
                ItemStack result = (ItemStack)recipe.getValue();
                ItemStack ingred = new ItemStack(((ItemStack)recipe.getKey()).func_77973_b(), 1, -1);
                ItemStack byproduct = Witchery.Items.GENERIC.itemFoulFume.createStack();
                if (ingred.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150345_g)) {
                    byproduct = ingred.func_77960_j() == 0 ? Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack() : (ingred.func_77960_j() == 2 ? Witchery.Items.GENERIC.itemBreathOfTheGoddess.createStack() : (ingred.func_77960_j() == 1 ? Witchery.Items.GENERIC.itemHintOfRebirth.createStack() : Witchery.Items.GENERIC.itemFoulFume.createStack()));
                    this.arecipes.add(new SmeltingPair(ingred, result, byproduct));
                    continue;
                }
                if (ingred.func_77973_b() == Item.func_150898_a((Block)Witchery.Blocks.SAPLING)) {
                    byproduct = ingred.func_77960_j() == 0 ? Witchery.Items.GENERIC.itemWhiffOfMagic.createStack() : (ingred.func_77960_j() == 2 ? Witchery.Items.GENERIC.itemOdourOfPurity.createStack() : (ingred.func_77960_j() == 1 ? Witchery.Items.GENERIC.itemReekOfMisfortune.createStack() : Witchery.Items.GENERIC.itemFoulFume.createStack()));
                    this.arecipes.add(new SmeltingPair(ingred, result, byproduct));
                    continue;
                }
                if (!Witchery.Items.GENERIC.itemAshWood.isMatch(result) && (result.func_77973_b() != Items.field_151044_h || result.func_77960_j() != 1) && !(ingred.func_77973_b() instanceof ItemFood)) continue;
                this.arecipes.add(new SmeltingPair(ingred, result, byproduct));
            }
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    public void loadCraftingRecipes(ItemStack result2) {
        Map recipes = FurnaceRecipes.func_77602_a().func_77599_b();
        for (Map.Entry recipe : recipes.entrySet()) {
            ItemStack result = (ItemStack)recipe.getKey();
            if (!NEIServerUtils.areStacksSameType((ItemStack)result, (ItemStack)result2)) continue;
            ItemStack ingred = new ItemStack(((ItemStack)recipe.getValue()).func_77973_b(), 1, -1);
            ItemStack byproduct = Witchery.Items.GENERIC.itemFoulFume.createStack();
            if (ingred.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150345_g)) {
                byproduct = ingred.func_77960_j() == 0 ? Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack() : (ingred.func_77960_j() == 2 ? Witchery.Items.GENERIC.itemBreathOfTheGoddess.createStack() : (ingred.func_77960_j() == 1 ? Witchery.Items.GENERIC.itemHintOfRebirth.createStack() : Witchery.Items.GENERIC.itemFoulFume.createStack()));
                this.arecipes.add(new SmeltingPair(ingred, result, byproduct));
                continue;
            }
            if (ingred.func_77973_b() == Item.func_150898_a((Block)Witchery.Blocks.SAPLING)) {
                byproduct = ingred.func_77960_j() == 0 ? Witchery.Items.GENERIC.itemWhiffOfMagic.createStack() : (ingred.func_77960_j() == 2 ? Witchery.Items.GENERIC.itemOdourOfPurity.createStack() : (ingred.func_77960_j() == 1 ? Witchery.Items.GENERIC.itemReekOfMisfortune.createStack() : Witchery.Items.GENERIC.itemFoulFume.createStack()));
                this.arecipes.add(new SmeltingPair(ingred, result, byproduct));
                continue;
            }
            if (!Witchery.Items.GENERIC.itemAshWood.isMatch(result) && ingred.func_77973_b() != Items.field_151044_h && !(ingred.func_77973_b() instanceof ItemFood)) continue;
            this.arecipes.add(new SmeltingPair(ingred, result, byproduct));
        }
        if (Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.isMatch(result2)) {
            this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 0), Witchery.Items.GENERIC.itemAshWood.createStack(), result2));
        } else if (Witchery.Items.GENERIC.itemBreathOfTheGoddess.isMatch(result2)) {
            this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 2), Witchery.Items.GENERIC.itemAshWood.createStack(), result2));
        } else if (Witchery.Items.GENERIC.itemHintOfRebirth.isMatch(result2)) {
            this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 1), Witchery.Items.GENERIC.itemAshWood.createStack(), result2));
        } else if (Witchery.Items.GENERIC.itemWhiffOfMagic.isMatch(result2)) {
            this.arecipes.add(new SmeltingPair(new ItemStack(Witchery.Blocks.SAPLING, 1, 0), Witchery.Items.GENERIC.itemAshWood.createStack(), result2));
        } else if (Witchery.Items.GENERIC.itemOdourOfPurity.isMatch(result2)) {
            this.arecipes.add(new SmeltingPair(new ItemStack(Witchery.Blocks.SAPLING, 1, 2), Witchery.Items.GENERIC.itemAshWood.createStack(), result2));
        } else if (Witchery.Items.GENERIC.itemReekOfMisfortune.isMatch(result2)) {
            this.arecipes.add(new SmeltingPair(new ItemStack(Witchery.Blocks.SAPLING, 1, 1), Witchery.Items.GENERIC.itemAshWood.createStack(), result2));
        } else if (Witchery.Items.GENERIC.itemFoulFume.isMatch(result2)) {
            this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150364_r), new ItemStack(Items.field_151044_h, 1), result2));
        }
    }

    public void loadUsageRecipes(String inputId, Object ... ingredients) {
        if (inputId.equals("fuel") && ((Object)((Object)this)).getClass() == NEIWitchesOvenRecipeHandler.class) {
            this.loadCraftingRecipes("witchery_cooking", new Object[0]);
        } else {
            super.loadUsageRecipes(inputId, ingredients);
        }
    }

    public void loadUsageRecipes(ItemStack ingred) {
        Map recipes = FurnaceRecipes.func_77602_a().func_77599_b();
        for (Map.Entry recipe : recipes.entrySet()) {
            ItemStack result = (ItemStack)recipe.getValue();
            if (ingred.func_77973_b() != ((ItemStack)recipe.getKey()).func_77973_b()) continue;
            ItemStack byproduct = Witchery.Items.GENERIC.itemFoulFume.createStack();
            if (ingred.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150345_g)) {
                byproduct = ingred.func_77960_j() == 0 ? Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack() : (ingred.func_77960_j() == 1 ? Witchery.Items.GENERIC.itemBreathOfTheGoddess.createStack() : (ingred.func_77960_j() == 2 ? Witchery.Items.GENERIC.itemHintOfRebirth.createStack() : Witchery.Items.GENERIC.itemFoulFume.createStack()));
                this.arecipes.add(new SmeltingPair(ingred, result, byproduct));
                continue;
            }
            if (ingred.func_77973_b() == Item.func_150898_a((Block)Witchery.Blocks.SAPLING)) {
                byproduct = ingred.func_77960_j() == 0 ? Witchery.Items.GENERIC.itemWhiffOfMagic.createStack() : (ingred.func_77960_j() == 1 ? Witchery.Items.GENERIC.itemOdourOfPurity.createStack() : (ingred.func_77960_j() == 2 ? Witchery.Items.GENERIC.itemReekOfMisfortune.createStack() : Witchery.Items.GENERIC.itemFoulFume.createStack()));
                this.arecipes.add(new SmeltingPair(ingred, result, byproduct));
                continue;
            }
            if (!Witchery.Items.GENERIC.itemAshWood.isMatch(result) && ingred.func_77973_b() != Items.field_151044_h && !(ingred.func_77973_b() instanceof ItemFood)) continue;
            this.arecipes.add(new SmeltingPair(ingred, result, byproduct));
        }
        if (Witchery.Items.GENERIC.itemEmptyClayJar.isMatch(ingred)) {
            this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 0), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemExhaleOfTheHornedOne.createStack()));
            this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 1), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemHintOfRebirth.createStack()));
            this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 2), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemBreathOfTheGoddess.createStack()));
            this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150345_g, 1, 3), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemFoulFume.createStack()));
            this.arecipes.add(new SmeltingPair(new ItemStack(Witchery.Blocks.SAPLING, 1, 0), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemWhiffOfMagic.createStack()));
            this.arecipes.add(new SmeltingPair(new ItemStack(Witchery.Blocks.SAPLING, 1, 1), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemReekOfMisfortune.createStack()));
            this.arecipes.add(new SmeltingPair(new ItemStack(Witchery.Blocks.SAPLING, 1, 2), Witchery.Items.GENERIC.itemAshWood.createStack(), Witchery.Items.GENERIC.itemOdourOfPurity.createStack()));
            this.arecipes.add(new SmeltingPair(new ItemStack(Blocks.field_150364_r), new ItemStack(Items.field_151044_h, 1, 1), Witchery.Items.GENERIC.itemFoulFume.createStack()));
        }
    }

    public String getGuiTexture() {
        return "witchery:textures/gui/witchesOven.png";
    }

    public void loadTransferRects() {
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(50, 23, 18, 18), "fuel", new Object[0]));
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(74, 9, 24, 18), "witchery_cooking", new Object[0]));
    }

    public void drawExtras(int recipe) {
        this.drawProgressBar(51, 25, 176, 0, 14, 14, 48, 7);
        this.drawProgressBar(74, 9, 176, 14, 24, 16, 48, 0);
    }

    private static Set<Item> excludedFuels() {
        HashSet<Item> efuels = new HashSet<Item>();
        efuels.add(Item.func_150898_a((Block)Blocks.field_150338_P));
        efuels.add(Item.func_150898_a((Block)Blocks.field_150337_Q));
        efuels.add(Item.func_150898_a((Block)Blocks.field_150472_an));
        efuels.add(Item.func_150898_a((Block)Blocks.field_150444_as));
        efuels.add(Item.func_150898_a((Block)Blocks.field_150466_ao));
        efuels.add(Item.func_150898_a((Block)Blocks.field_150447_bR));
        return efuels;
    }

    private static void findFuels() {
        afuels = new ArrayList();
        Set<Item> efuels = NEIWitchesOvenRecipeHandler.excludedFuels();
        for (ItemStack item : ItemList.items) {
            int burnTime;
            if (item == null || efuels.contains(item.func_77973_b()) || (burnTime = TileEntityFurnace.func_145952_a((ItemStack)item)) <= 0) continue;
            afuels.add(new FuelPair(item.func_77946_l(), burnTime));
        }
    }

    private static void findFuelsOnce() {
        if (afuels == null) {
            NEIWitchesOvenRecipeHandler.findFuels();
        }
    }

    public String getOverlayIdentifier() {
        return "witchery_cooking";
    }

    public TemplateRecipeHandler newInstance() {
        NEIWitchesOvenRecipeHandler.findFuelsOnce();
        return super.newInstance();
    }

    public static class FuelPair {
        public PositionedStack stack;
        public int burnTime;

        public FuelPair(ItemStack ingred, int burnTime) {
            this.stack = new PositionedStack((Object)ingred, 51, 42);
            this.burnTime = burnTime;
        }
    }

    public class SmeltingPair
    extends TemplateRecipeHandler.CachedRecipe {
        PositionedStack ingred;
        PositionedStack result;
        PositionedStack byproduct;
        PositionedStack jar;

        public SmeltingPair(ItemStack ingred, ItemStack result, ItemStack byproduct) {
            super((TemplateRecipeHandler)NEIWitchesOvenRecipeHandler.this);
            ingred.field_77994_a = 1;
            this.ingred = new PositionedStack((Object)ingred, 51, 6);
            this.result = new PositionedStack((Object)result, 113, 10);
            this.byproduct = new PositionedStack((Object)byproduct, 113, 42);
            this.jar = new PositionedStack((Object)Witchery.Items.GENERIC.itemEmptyClayJar.createStack(), 78, 42);
        }

        public List<PositionedStack> getIngredients() {
            return this.getCycledIngredients(NEIWitchesOvenRecipeHandler.this.cycleticks / 48, Arrays.asList(this.ingred));
        }

        public PositionedStack getResult() {
            return this.result;
        }

        public PositionedStack getOtherStack() {
            NEIWitchesOvenRecipeHandler.findFuelsOnce();
            if (afuels != null && afuels.size() > 0) {
                return NEIWitchesOvenRecipeHandler.afuels.get((int)(NEIWitchesOvenRecipeHandler.this.cycleticks / 48 % NEIWitchesOvenRecipeHandler.afuels.size())).stack;
            }
            return null;
        }

        public List<PositionedStack> getOtherStacks() {
            ArrayList<PositionedStack> stacks = new ArrayList<PositionedStack>();
            PositionedStack stack = this.getOtherStack();
            if (stack != null) {
                stacks.add(stack);
            }
            stacks.add(this.byproduct);
            stacks.add(this.jar);
            return stacks;
        }
    }
}

