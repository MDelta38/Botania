/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.oredict.OreDictionary
 */
package vazkii.botania.api.recipe;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.api.brew.IBrewContainer;

public class RecipeBrew {
    Brew brew;
    List<Object> inputs;

    public RecipeBrew(Brew brew, Object ... inputs) {
        this.brew = brew;
        ArrayList<Object> inputsToSet = new ArrayList<Object>();
        for (Object obj : inputs) {
            if (!(obj instanceof String) && !(obj instanceof ItemStack)) {
                throw new IllegalArgumentException("Invalid input");
            }
            inputsToSet.add(obj);
        }
        this.inputs = inputsToSet;
    }

    public boolean matches(IInventory inv) {
        ItemStack stack;
        ArrayList<Object> inputsMissing = new ArrayList<Object>(this.inputs);
        for (int i = 0; i < inv.func_70302_i_() && (stack = inv.func_70301_a(i)) != null; ++i) {
            if (stack.func_77973_b() instanceof IBrewContainer) continue;
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
                        oredictIndex = j;
                        found = true;
                        break;
                    }
                    if (!found) continue;
                    break;
                }
                if (!(input instanceof ItemStack) || !this.simpleAreStacksEqual((ItemStack)input, stack)) continue;
                stackIndex = j;
                break;
            }
            if (stackIndex != -1) {
                inputsMissing.remove(stackIndex);
                continue;
            }
            if (oredictIndex != -1) {
                inputsMissing.remove(oredictIndex);
                continue;
            }
            return false;
        }
        return inputsMissing.isEmpty();
    }

    boolean simpleAreStacksEqual(ItemStack stack, ItemStack stack2) {
        return stack.func_77973_b() == stack2.func_77973_b() && stack.func_77960_j() == stack2.func_77960_j();
    }

    public List<Object> getInputs() {
        return new ArrayList<Object>(this.inputs);
    }

    public Brew getBrew() {
        return this.brew;
    }

    public int getManaUsage() {
        return this.brew.getManaCost();
    }

    public ItemStack getOutput(ItemStack stack) {
        if (stack == null || !(stack.func_77973_b() instanceof IBrewContainer)) {
            return new ItemStack(Items.field_151069_bo);
        }
        IBrewContainer container = (IBrewContainer)stack.func_77973_b();
        return container.getItemForBrew(this.brew, stack);
    }
}

