/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.emoniph.witchery.brewing.action;

import com.emoniph.witchery.brewing.AltarPower;
import com.emoniph.witchery.brewing.BrewItemKey;
import com.emoniph.witchery.brewing.EffectLevelCounter;
import com.emoniph.witchery.brewing.ModifiersEffect;
import com.emoniph.witchery.brewing.ModifiersImpact;
import com.emoniph.witchery.brewing.ModifiersRitual;
import com.emoniph.witchery.brewing.Probability;
import com.emoniph.witchery.brewing.RitualStatus;
import com.emoniph.witchery.brewing.action.BrewAction;
import com.emoniph.witchery.brewing.action.BrewActionList;
import com.emoniph.witchery.util.EntityUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BrewActionRitualRecipe
extends BrewAction {
    private final Recipe[] recipes;
    private final List<Recipe> expandedRecipes = new ArrayList<Recipe>();

    public BrewActionRitualRecipe(BrewItemKey itemKey, AltarPower powerCost, Recipe ... recipes) {
        super(itemKey, null, powerCost, new Probability(1.0), false);
        this.recipes = recipes;
        for (Recipe recipe : recipes) {
            this.expandedRecipes.add(recipe.getExpandedRecipe(itemKey));
        }
    }

    public List<Recipe> getExpandedRecipes() {
        return this.expandedRecipes;
    }

    @Override
    public final void applyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack stack) {
    }

    @Override
    public final void applyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect effectModifiers, ItemStack stack) {
    }

    @Override
    public final void prepareSplashPotion(World world, BrewActionList actionList, ModifiersImpact modifiers) {
    }

    @Override
    public final boolean triggersRitual() {
        return true;
    }

    @Override
    public final boolean canAdd(BrewActionList actionList, boolean isCauldronFull, boolean hasEffects) {
        return isCauldronFull && this.getRecipeResult(actionList) != null;
    }

    @Override
    public final RitualStatus updateRitual(MinecraftServer server, BrewActionList actionList, World world, int x, int y, int z, ModifiersRitual modifiers, ModifiersImpact impactModifiers) {
        ItemStack result = this.getRecipeResult(actionList);
        if (result != null) {
            EntityItem item = new EntityItem(world, (double)x + 0.5, (double)y + 1.5, (double)z + 0.5, result.func_77946_l());
            item.field_70159_w = 0.0;
            item.field_70181_x = 0.2;
            item.field_70179_y = 0.0;
            EntityUtil.spawnEntityInWorld(world, (Entity)item);
            return RitualStatus.COMPLETE;
        }
        return RitualStatus.FAILED;
    }

    private ItemStack getRecipeResult(BrewActionList actionList) {
        for (Recipe recipe : this.recipes) {
            if (recipe.ingredients.length > 0) {
                ArrayList<ItemStack> neededItems = new ArrayList<ItemStack>();
                neededItems.addAll(Arrays.asList(recipe.ingredients));
                for (BrewAction action : actionList.actions) {
                    this.removeFromNeededItems(neededItems, action.ITEM_KEY);
                }
                if (neededItems.size() != 0) continue;
                return recipe.result;
            }
            return recipe.result;
        }
        return null;
    }

    private void removeFromNeededItems(ArrayList<ItemStack> neededItems, BrewItemKey item) {
        Iterator<ItemStack> iterator = neededItems.iterator();
        while (iterator.hasNext()) {
            ItemStack stack = iterator.next();
            if (stack.func_77973_b() != item.ITEM || stack.func_77960_j() != item.DAMAGE) continue;
            iterator.remove();
            return;
        }
    }

    @Override
    public final boolean augmentEffectLevels(EffectLevelCounter totalEffects) {
        return true;
    }

    @Override
    public final void augmentEffectModifiers(ModifiersEffect modifiers) {
    }

    @Override
    public final void prepareRitual(World world, int x, int y, int z, ModifiersRitual modifiers, ItemStack stack) {
    }

    public static class Recipe {
        public final ItemStack result;
        public final ItemStack[] ingredients;

        public Recipe(ItemStack result, ItemStack ... ingredients) {
            this.result = result;
            this.ingredients = ingredients;
        }

        public Recipe(ItemStack result, ItemStack[] ingredients, ItemStack finalIngredient) {
            this.result = result;
            this.ingredients = Arrays.copyOf(ingredients, ingredients.length + 1);
            this.ingredients[this.ingredients.length - 1] = finalIngredient;
        }

        private Recipe getExpandedRecipe(BrewItemKey itemKey) {
            return new Recipe(this.result, this.ingredients, itemKey.toStack());
        }
    }
}

