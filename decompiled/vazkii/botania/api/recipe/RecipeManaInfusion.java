/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.oredict.OreDictionary
 */
package vazkii.botania.api.recipe;

import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class RecipeManaInfusion {
    ItemStack output;
    Object input;
    int mana;
    boolean isAlchemy = false;
    boolean isConjuration = false;

    public RecipeManaInfusion(ItemStack output, Object input, int mana) {
        this.output = output;
        this.input = input;
        this.mana = mana;
    }

    public boolean matches(ItemStack stack) {
        if (this.input instanceof ItemStack) {
            ItemStack inputCopy = ((ItemStack)this.input).func_77946_l();
            if (inputCopy.func_77960_j() == Short.MAX_VALUE) {
                inputCopy.func_77964_b(stack.func_77960_j());
            }
            return stack.func_77969_a(inputCopy);
        }
        if (this.input instanceof String) {
            ArrayList validStacks = OreDictionary.getOres((String)((String)this.input));
            for (ItemStack ostack : validStacks) {
                ItemStack cstack = ostack.func_77946_l();
                if (cstack.func_77960_j() == Short.MAX_VALUE) {
                    cstack.func_77964_b(stack.func_77960_j());
                }
                if (!stack.func_77969_a(cstack)) continue;
                return true;
            }
        }
        return false;
    }

    public void setAlchemy(boolean alchemy) {
        this.isAlchemy = alchemy;
    }

    public boolean isAlchemy() {
        return this.isAlchemy;
    }

    public void setConjuration(boolean conjuration) {
        this.isConjuration = conjuration;
    }

    public boolean isConjuration() {
        return this.isConjuration;
    }

    public Object getInput() {
        return this.input;
    }

    public ItemStack getOutput() {
        return this.output;
    }

    public int getManaToConsume() {
        return this.mana;
    }
}

