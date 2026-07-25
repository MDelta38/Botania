/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.oredict.OreDictionary
 */
package witchinggadgets.common.util.recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class SpinningRecipe {
    public static List<SpinningRecipe> recipeList = new ArrayList<SpinningRecipe>();
    ItemStack output;
    Object[] input;

    public SpinningRecipe(ItemStack r_output, Object ... r_recipe) {
        this.output = r_output;
        this.input = new Object[r_recipe.length];
        for (int i = 0; i < r_recipe.length; ++i) {
            Object in = r_recipe[i];
            if (in instanceof ItemStack) {
                this.input[i] = ((ItemStack)in).func_77946_l();
                continue;
            }
            if (in instanceof Item) {
                this.input[i] = new ItemStack((Item)in);
                continue;
            }
            if (in instanceof Block) {
                this.input[i] = new ItemStack((Block)in, 1, Short.MAX_VALUE);
                continue;
            }
            if (in instanceof String) {
                this.input[i] = OreDictionary.getOres((String)((String)in));
                continue;
            }
            String ret = "Invalid SpinningWheel recipe for: " + r_output.func_82833_r() + " input should be ItemStack, Item, Block or String";
            throw new RuntimeException(ret);
        }
    }

    public boolean inputsMatch(ItemStack[] query) {
        if (query == null || this.input == null) {
            return false;
        }
        if (query.length != this.input.length) {
            return false;
        }
        ArrayList<Object> inputList = new ArrayList<Object>(Arrays.asList(this.input));
        boolean match = false;
        for (ItemStack stack : query) {
            Iterator iterator = inputList.iterator();
            block1: while (iterator.hasNext()) {
                Object next = iterator.next();
                if (next instanceof ItemStack) {
                    if (!OreDictionary.itemMatches((ItemStack)((ItemStack)next), (ItemStack)stack, (boolean)false)) continue;
                    match = true;
                    iterator.remove();
                    break;
                }
                if (!(next instanceof ArrayList)) continue;
                ArrayList oreDict = (ArrayList)next;
                for (ItemStack oreStack : oreDict) {
                    if (!OreDictionary.itemMatches((ItemStack)oreStack, (ItemStack)stack, (boolean)false)) continue;
                    match = true;
                    iterator.remove();
                    continue block1;
                }
            }
            if (match) continue;
            return false;
        }
        return inputList.isEmpty();
    }

    public ItemStack getOutput() {
        return this.output;
    }

    public Object[] getInput() {
        return this.input;
    }

    public static void addRecipe(SpinningRecipe recipe) {
        recipeList.add(recipe);
    }

    public static SpinningRecipe getSpinningRecipe(ItemStack[] input) {
        Iterator<SpinningRecipe> i = recipeList.iterator();
        int factualLength = 0;
        for (ItemStack temp : input) {
            if (temp == null) continue;
            ++factualLength;
        }
        ItemStack[] inputCopy = new ItemStack[factualLength];
        int ix = 0;
        for (ItemStack temp : input) {
            if (temp == null) continue;
            inputCopy[ix] = temp;
            ++ix;
        }
        while (i.hasNext()) {
            SpinningRecipe s = i.next();
            if (!s.inputsMatch(inputCopy)) continue;
            return s;
        }
        return null;
    }

    public static SpinningRecipe getSpinningRecipe(ItemStack output) {
        for (SpinningRecipe s : recipeList) {
            if (!OreDictionary.itemMatches((ItemStack)s.getOutput(), (ItemStack)output, (boolean)false)) continue;
            return s;
        }
        return null;
    }

    public static void removeRecipe(SpinningRecipe recipe) {
        recipeList.remove(recipe);
    }

    public static List<SpinningRecipe> removeRecipes(ItemStack stack) {
        ArrayList<SpinningRecipe> list = new ArrayList<SpinningRecipe>();
        Iterator<SpinningRecipe> i = recipeList.iterator();
        while (i.hasNext()) {
            SpinningRecipe s = i.next();
            if (!OreDictionary.itemMatches((ItemStack)s.output, (ItemStack)stack, (boolean)false)) continue;
            list.add(s);
            i.remove();
        }
        return list;
    }
}

