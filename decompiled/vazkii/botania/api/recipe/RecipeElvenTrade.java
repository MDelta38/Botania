/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.oredict.OreDictionary
 */
package vazkii.botania.api.recipe;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeElvenTrade {
    ItemStack output;
    List<Object> inputs;

    public RecipeElvenTrade(ItemStack output, Object ... inputs) {
        this.output = output;
        ArrayList<Object> inputsToSet = new ArrayList<Object>();
        for (Object obj : inputs) {
            if (!(obj instanceof String) && !(obj instanceof ItemStack)) {
                throw new IllegalArgumentException("Invalid input");
            }
            inputsToSet.add(obj);
        }
        this.inputs = inputsToSet;
    }

    public boolean matches(List<ItemStack> stacks, boolean remove) {
        ArrayList<Object> inputsMissing = new ArrayList<Object>(this.inputs);
        ArrayList<ItemStack> stacksToRemove = new ArrayList<ItemStack>();
        for (ItemStack stack : stacks) {
            if (stack == null) continue;
            if (inputsMissing.isEmpty()) break;
            int stackIndex = -1;
            int oredictIndex = -1;
            for (int j = 0; j < inputsMissing.size(); ++j) {
                Object input = inputsMissing.get(j);
                if (input instanceof String) {
                    ArrayList validStacks = OreDictionary.getOres((String)((String)input));
                    boolean found = false;
                    for (ItemStack ostack : validStacks) {
                        ItemStack cstack = ostack.func_77946_l();
                        if (cstack.func_77960_j() == Short.MAX_VALUE) {
                            cstack.func_77964_b(stack.func_77960_j());
                        }
                        if (!stack.func_77969_a(cstack)) continue;
                        if (!stacksToRemove.contains(stack)) {
                            stacksToRemove.add(stack);
                        }
                        oredictIndex = j;
                        found = true;
                        break;
                    }
                    if (!found) continue;
                    break;
                }
                if (!(input instanceof ItemStack) || !this.simpleAreStacksEqual((ItemStack)input, stack)) continue;
                if (!stacksToRemove.contains(stack)) {
                    stacksToRemove.add(stack);
                }
                stackIndex = j;
                break;
            }
            if (stackIndex != -1) {
                inputsMissing.remove(stackIndex);
                continue;
            }
            if (oredictIndex == -1) continue;
            inputsMissing.remove(oredictIndex);
        }
        if (remove) {
            for (ItemStack r : stacksToRemove) {
                stacks.remove(r);
            }
        }
        return inputsMissing.isEmpty();
    }

    boolean simpleAreStacksEqual(ItemStack stack, ItemStack stack2) {
        return stack.func_77973_b() == stack2.func_77973_b() && stack.func_77960_j() == stack2.func_77960_j();
    }

    public List<Object> getInputs() {
        return new ArrayList<Object>(this.inputs);
    }

    public ItemStack getOutput() {
        return this.output;
    }
}

