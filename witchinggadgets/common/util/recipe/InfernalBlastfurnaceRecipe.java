/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.oredict.OreDictionary
 */
package witchinggadgets.common.util.recipe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import witchinggadgets.common.util.Utilities;

public class InfernalBlastfurnaceRecipe {
    private final Object input;
    private final ItemStack output;
    private final int time;
    private final boolean isSpecial;
    private ItemStack bonus;
    public static List<InfernalBlastfurnaceRecipe> recipes = new ArrayList<InfernalBlastfurnaceRecipe>();

    public InfernalBlastfurnaceRecipe(ItemStack output, Object input, int time, boolean isSpecial) {
        if (input instanceof ItemStack) {
            this.input = (ItemStack)input;
        } else if (input instanceof Utilities.OreDictStack) {
            this.input = (Utilities.OreDictStack)input;
        } else if (input instanceof String) {
            this.input = new Utilities.OreDictStack((String)input, 1);
        } else {
            throw new RuntimeException("Infernal Blast Furance Recipes MUST be initialized with ItemStack, OreDictStack or String");
        }
        this.output = output;
        this.time = time;
        this.isSpecial = isSpecial;
        this.bonus = null;
    }

    public boolean isSpecial() {
        return this.isSpecial;
    }

    public void addBonus(ItemStack bonus) {
        this.bonus = bonus;
    }

    public ItemStack getBonus() {
        return this.bonus;
    }

    public Object getInput() {
        return this.input;
    }

    public ItemStack getOutput() {
        return this.output;
    }

    public int getSmeltingTime() {
        return this.time;
    }

    public boolean matches(ItemStack stack) {
        if (this.input instanceof Utilities.OreDictStack) {
            return ((Utilities.OreDictStack)this.input).matches(stack);
        }
        if (this.input instanceof ItemStack) {
            return OreDictionary.itemMatches((ItemStack)((ItemStack)this.input), (ItemStack)stack, (boolean)false) && stack.field_77994_a >= ((ItemStack)this.input).field_77994_a;
        }
        return false;
    }

    public static InfernalBlastfurnaceRecipe getRecipeForInput(ItemStack stack) {
        for (InfernalBlastfurnaceRecipe ir : recipes) {
            if (!ir.matches(stack)) continue;
            return ir;
        }
        return null;
    }

    public static InfernalBlastfurnaceRecipe getRecipeForOutput(ItemStack stack) {
        for (InfernalBlastfurnaceRecipe ir : recipes) {
            if (OreDictionary.itemMatches((ItemStack)ir.getOutput(), (ItemStack)stack, (boolean)true)) {
                return ir;
            }
            if (!OreDictionary.itemMatches((ItemStack)ir.getBonus(), (ItemStack)stack, (boolean)true)) continue;
            return ir;
        }
        return null;
    }

    public static InfernalBlastfurnaceRecipe addRecipe(ItemStack output, String input, int inputSize, int time, boolean isSpecial) {
        return InfernalBlastfurnaceRecipe.addRecipe(output, input, time, isSpecial);
    }

    public static InfernalBlastfurnaceRecipe addRecipe(ItemStack output, Object input, int time, boolean isSpecial) {
        InfernalBlastfurnaceRecipe recipe = new InfernalBlastfurnaceRecipe(output, input, time, isSpecial);
        return InfernalBlastfurnaceRecipe.addRecipe(recipe);
    }

    public static InfernalBlastfurnaceRecipe addRecipe(InfernalBlastfurnaceRecipe recipe) {
        recipes.add(recipe);
        return recipe;
    }

    public static void removeRecipe(InfernalBlastfurnaceRecipe recipe) {
        recipes.remove(recipe);
    }

    public static List<InfernalBlastfurnaceRecipe> removeRecipes(ItemStack stack) {
        ArrayList<InfernalBlastfurnaceRecipe> list = new ArrayList<InfernalBlastfurnaceRecipe>();
        Iterator<InfernalBlastfurnaceRecipe> it = recipes.iterator();
        while (it.hasNext()) {
            InfernalBlastfurnaceRecipe ir = it.next();
            if (!OreDictionary.itemMatches((ItemStack)ir.output, (ItemStack)stack, (boolean)true)) continue;
            list.add(ir);
            it.remove();
        }
        return list;
    }

    public static void tryAddSpecialOreMelting(String ore, String ingot, boolean isSpecial) {
        if (OreDictionary.getOres((String)("ore" + ore)).isEmpty() || OreDictionary.getOres((String)("ingot" + ingot)).isEmpty()) {
            return;
        }
        InfernalBlastfurnaceRecipe.addRecipe(Utilities.copyStackWithSize((ItemStack)OreDictionary.getOres((String)("ingot" + ingot)).get(0), 1), "ore" + ore, 1, 1200, isSpecial);
    }

    public static void tryAddIngotImprovement(String base, String result, boolean isSpecial) {
        if (OreDictionary.getOres((String)("ingot" + base)).isEmpty() || OreDictionary.getOres((String)("ingot" + result)).isEmpty()) {
            return;
        }
        InfernalBlastfurnaceRecipe.addRecipe(Utilities.copyStackWithSize((ItemStack)OreDictionary.getOres((String)("ingot" + result)).get(0), 1), "ingot" + base, 1, 1200, isSpecial);
        if (!OreDictionary.getOres((String)("block" + base)).isEmpty()) {
            if (!OreDictionary.getOres((String)("block" + result)).isEmpty()) {
                InfernalBlastfurnaceRecipe.addRecipe(Utilities.copyStackWithSize((ItemStack)OreDictionary.getOres((String)("block" + result)).get(0), 1), "block" + base, 1, 1200, isSpecial);
            } else {
                InfernalBlastfurnaceRecipe.addRecipe(Utilities.copyStackWithSize((ItemStack)OreDictionary.getOres((String)("ingot" + result)).get(0), 9), "block" + base, 1, 1200, isSpecial);
            }
        }
    }

    public boolean equals(Object o) {
        if (!(o instanceof InfernalBlastfurnaceRecipe)) {
            return false;
        }
        InfernalBlastfurnaceRecipe r = (InfernalBlastfurnaceRecipe)o;
        boolean b_out = ItemStack.func_77989_b((ItemStack)r.output, (ItemStack)this.output);
        boolean b_in_IS = this.input instanceof ItemStack && r.input instanceof ItemStack && OreDictionary.itemMatches((ItemStack)((ItemStack)this.input), (ItemStack)((ItemStack)r.input), (boolean)true);
        boolean b_in_OD = this.input instanceof Utilities.OreDictStack && r.input instanceof Utilities.OreDictStack && ((Utilities.OreDictStack)this.input).key.equals(((Utilities.OreDictStack)r.input).key);
        return b_out && (b_in_IS || b_in_OD);
    }
}

