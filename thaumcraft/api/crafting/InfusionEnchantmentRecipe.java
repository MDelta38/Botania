/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraftforge.oredict.OreDictionary
 */
package thaumcraft.api.crafting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.AspectList;

public class InfusionEnchantmentRecipe {
    public AspectList aspects;
    public String research;
    public ItemStack[] components;
    public Enchantment enchantment;
    public int recipeXP;
    public int instability;

    public InfusionEnchantmentRecipe(String research, Enchantment input, int inst, AspectList aspects2, ItemStack[] recipe) {
        this.research = research;
        this.enchantment = input;
        this.aspects = aspects2;
        this.components = recipe;
        this.instability = inst;
        this.recipeXP = Math.max(1, input.func_77321_a(1) / 3);
    }

    public boolean matches(ArrayList<ItemStack> input, ItemStack central, World world, EntityPlayer player) {
        if (this.research.length() > 0 && !ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), this.research)) {
            return false;
        }
        if (!this.enchantment.func_92089_a(central) || !central.func_77973_b().func_77616_k(central)) {
            return false;
        }
        Map map1 = EnchantmentHelper.func_82781_a((ItemStack)central);
        Iterator iterator = map1.keySet().iterator();
        while (iterator.hasNext()) {
            int j1 = (Integer)iterator.next();
            Enchantment ench = Enchantment.field_77331_b[j1];
            if (j1 == this.enchantment.field_77352_x && EnchantmentHelper.func_77506_a((int)j1, (ItemStack)central) >= ench.func_77325_b()) {
                return false;
            }
            if (this.enchantment.field_77352_x == ench.field_77352_x || this.enchantment.func_77326_a(ench) && ench.func_77326_a(this.enchantment)) continue;
            return false;
        }
        ItemStack i2 = null;
        ArrayList<ItemStack> ii = new ArrayList<ItemStack>();
        for (ItemStack is : input) {
            ii.add(is.func_77946_l());
        }
        for (ItemStack comp : this.components) {
            boolean b = false;
            for (int a = 0; a < ii.size(); ++a) {
                i2 = ((ItemStack)ii.get(a)).func_77946_l();
                if (comp.func_77960_j() == Short.MAX_VALUE) {
                    i2.func_77964_b(Short.MAX_VALUE);
                }
                if (!this.areItemStacksEqual(i2, comp, true)) continue;
                ii.remove(a);
                b = true;
                break;
            }
            if (b) continue;
            return false;
        }
        return ii.size() == 0;
    }

    protected boolean areItemStacksEqual(ItemStack stack0, ItemStack stack1, boolean fuzzy) {
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
        return stack0.func_77973_b() != stack1.func_77973_b() ? false : (stack0.func_77960_j() != stack1.func_77960_j() ? false : stack0.field_77994_a <= stack0.func_77976_d());
    }

    public Enchantment getEnchantment() {
        return this.enchantment;
    }

    public AspectList getAspects() {
        return this.aspects;
    }

    public String getResearch() {
        return this.research;
    }

    public int calcInstability(ItemStack recipeInput) {
        int i = 0;
        Map map1 = EnchantmentHelper.func_82781_a((ItemStack)recipeInput);
        Iterator iterator = map1.keySet().iterator();
        while (iterator.hasNext()) {
            int j1 = (Integer)iterator.next();
            i += EnchantmentHelper.func_77506_a((int)j1, (ItemStack)recipeInput);
        }
        return i / 2 + this.instability;
    }

    public int calcXP(ItemStack recipeInput) {
        return this.recipeXP * (1 + EnchantmentHelper.func_77506_a((int)this.enchantment.field_77352_x, (ItemStack)recipeInput));
    }

    public float getEssentiaMod(ItemStack recipeInput) {
        float mod = EnchantmentHelper.func_77506_a((int)this.enchantment.field_77352_x, (ItemStack)recipeInput);
        Map map1 = EnchantmentHelper.func_82781_a((ItemStack)recipeInput);
        Iterator iterator = map1.keySet().iterator();
        while (iterator.hasNext()) {
            int j1 = (Integer)iterator.next();
            if (j1 == this.enchantment.field_77352_x) continue;
            mod += (float)EnchantmentHelper.func_77506_a((int)j1, (ItemStack)recipeInput) * 0.1f;
        }
        return mod;
    }
}

