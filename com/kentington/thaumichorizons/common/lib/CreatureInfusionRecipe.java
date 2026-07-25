/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraftforge.oredict.OreDictionary
 */
package com.kentington.thaumichorizons.common.lib;

import java.util.ArrayList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.AspectList;

public class CreatureInfusionRecipe {
    protected AspectList aspects;
    protected String research;
    private ItemStack[] components;
    private Class<EntityLivingBase> recipeInput;
    protected Object recipeOutput;
    protected int instability;
    protected int id;

    public CreatureInfusionRecipe(String research, Object output, int inst, AspectList aspects2, Class input, ItemStack[] recipe, int id) {
        this.research = research;
        this.recipeOutput = output;
        this.recipeInput = input;
        this.aspects = aspects2;
        this.components = recipe;
        this.instability = inst;
        this.id = id;
    }

    public boolean matches(ArrayList<ItemStack> input, Class central, World world, EntityPlayer player) {
        if (this.research.length() > 0 && !ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), this.research)) {
            return false;
        }
        if (this.recipeInput != null && !central.isAssignableFrom(this.recipeInput)) {
            return false;
        }
        ArrayList<ItemStack> ii = new ArrayList<ItemStack>();
        for (ItemStack is : input) {
            ii.add(is.func_77946_l());
        }
        for (ItemStack comp : this.getComponents()) {
            boolean b = false;
            for (int a = 0; a < ii.size(); ++a) {
                ItemStack i2 = ((ItemStack)ii.get(a)).func_77946_l();
                if (comp.func_77960_j() == Short.MAX_VALUE) {
                    i2.func_77964_b(Short.MAX_VALUE);
                }
                if (!CreatureInfusionRecipe.areItemStacksEqual(i2, comp, true)) continue;
                ii.remove(a);
                b = true;
                break;
            }
            if (b) continue;
            return false;
        }
        return ii.size() == 0;
    }

    public static boolean areItemStacksEqual(ItemStack stack0, ItemStack stack1, boolean fuzzy) {
        boolean damage;
        ItemStack[] ores;
        int od;
        if (stack0 == null && stack1 != null) {
            return false;
        }
        if (stack0 != null && stack1 == null) {
            return false;
        }
        if (stack0 == null && stack1 == null) {
            return true;
        }
        boolean t1 = ThaumcraftApiHelper.areItemStackTagsEqualForCrafting(stack0, stack1);
        if (!t1) {
            return false;
        }
        if (fuzzy && (od = OreDictionary.getOreID((ItemStack)stack0)) != -1 && ThaumcraftApiHelper.containsMatch(false, new ItemStack[]{stack1}, ores = OreDictionary.getOres((Integer)od).toArray(new ItemStack[0]))) {
            return true;
        }
        boolean bl = damage = stack0.func_77960_j() == stack1.func_77960_j() || stack1.func_77960_j() == Short.MAX_VALUE;
        return stack0.func_77973_b() != stack1.func_77973_b() ? false : (!damage ? false : stack0.field_77994_a <= stack0.func_77976_d());
    }

    public Object getRecipeOutput() {
        return this.getRecipeOutput(this.getRecipeInput());
    }

    public AspectList getAspects() {
        return this.getAspects(this.getRecipeInput());
    }

    public int getInstability() {
        return this.getInstability(this.getRecipeInput());
    }

    public String getResearch() {
        return this.research;
    }

    public Class getRecipeInput() {
        return this.recipeInput;
    }

    public ItemStack[] getComponents() {
        return this.components;
    }

    public Object getRecipeOutput(Class input) {
        return this.recipeOutput;
    }

    public AspectList getAspects(Class input) {
        return this.aspects;
    }

    public int getInstability(Class input) {
        return this.instability;
    }

    public int getID(Class input) {
        return this.id;
    }
}

