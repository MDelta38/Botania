/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  minetweaker.IUndoableAction
 *  minetweaker.MineTweakerAPI
 *  minetweaker.api.item.IIngredient
 *  minetweaker.api.item.IItemStack
 *  net.minecraft.item.ItemStack
 *  stanhebben.zenscript.annotations.ZenClass
 *  stanhebben.zenscript.annotations.ZenMethod
 */
package witchinggadgets.common.minetweaker;

import java.util.List;
import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import witchinggadgets.common.minetweaker.WGMinetweaker;
import witchinggadgets.common.util.Utilities;
import witchinggadgets.common.util.recipe.InfernalBlastfurnaceRecipe;

@ZenClass(value="mods.witchinggadgets.InfernalBlastfurnace")
public class InfernalBlastfurnace {
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient input, int time, IItemStack bonus, boolean isSpecial) {
        Object oInput = WGMinetweaker.toObject(input);
        if (oInput == null) {
            return;
        }
        Utilities.OreDictStack inputStack = oInput instanceof String ? new Utilities.OreDictStack((String)oInput, input.getAmount()) : (ItemStack)oInput;
        InfernalBlastfurnaceRecipe r = new InfernalBlastfurnaceRecipe(WGMinetweaker.toStack(output), inputStack, time, isSpecial);
        if (bonus != null) {
            r.addBonus(WGMinetweaker.toStack(bonus));
        }
        MineTweakerAPI.apply((IUndoableAction)new Add(r));
    }

    @ZenMethod
    public static void removeRecipe(IItemStack output) {
        MineTweakerAPI.apply((IUndoableAction)new Remove(WGMinetweaker.toStack(output)));
    }

    private static class Remove
    implements IUndoableAction {
        private final ItemStack output;
        List<InfernalBlastfurnaceRecipe> removedRecipes;

        public Remove(ItemStack output) {
            this.output = output;
        }

        public void apply() {
            this.removedRecipes = InfernalBlastfurnaceRecipe.removeRecipes(this.output);
        }

        public void undo() {
            if (this.removedRecipes != null) {
                for (InfernalBlastfurnaceRecipe recipe : this.removedRecipes) {
                    if (recipe == null) continue;
                    InfernalBlastfurnaceRecipe.addRecipe(recipe);
                }
            }
        }

        public String describe() {
            return "Removing Infernal Blastfurnace Recipe for " + this.output.func_82833_r();
        }

        public String describeUndo() {
            return "Re-Adding Infernal Blastfurnace Recipe for " + this.output.func_82833_r();
        }

        public Object getOverrideKey() {
            return null;
        }

        public boolean canUndo() {
            return true;
        }
    }

    private static class Add
    implements IUndoableAction {
        private final InfernalBlastfurnaceRecipe recipe;

        public Add(InfernalBlastfurnaceRecipe recipe) {
            this.recipe = recipe;
        }

        public void apply() {
            InfernalBlastfurnaceRecipe.addRecipe(this.recipe);
        }

        public boolean canUndo() {
            return true;
        }

        public void undo() {
            InfernalBlastfurnaceRecipe.removeRecipe(this.recipe);
        }

        public String describe() {
            return "Adding Infernal Blastfurnace Recipe for " + this.recipe.getOutput().func_82833_r();
        }

        public String describeUndo() {
            return "Removing Infernal Blastfurnace Recipe for " + this.recipe.getOutput().func_82833_r();
        }

        public Object getOverrideKey() {
            return null;
        }
    }
}

