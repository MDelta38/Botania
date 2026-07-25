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
import witchinggadgets.common.util.recipe.SpinningRecipe;

@ZenClass(value="mods.witchinggadgets.SpinningWheel")
public class SpinningWheel {
    @ZenMethod
    public static void addRecipe(IItemStack output, IIngredient[] input) {
        Object[] oInput = WGMinetweaker.toObjects(input);
        if (oInput == null) {
            return;
        }
        SpinningRecipe r = new SpinningRecipe(WGMinetweaker.toStack(output), oInput);
        MineTweakerAPI.apply((IUndoableAction)new Add(r));
    }

    @ZenMethod
    public static void removeRecipe(IItemStack output) {
        MineTweakerAPI.apply((IUndoableAction)new Remove(WGMinetweaker.toStack(output)));
    }

    private static class Remove
    implements IUndoableAction {
        private final ItemStack output;
        List<SpinningRecipe> removedRecipes;

        public Remove(ItemStack output) {
            this.output = output;
        }

        public void apply() {
            this.removedRecipes = SpinningRecipe.removeRecipes(this.output);
        }

        public void undo() {
            if (this.removedRecipes != null) {
                for (SpinningRecipe recipe : this.removedRecipes) {
                    if (recipe == null) continue;
                    SpinningRecipe.addRecipe(recipe);
                }
            }
        }

        public String describe() {
            return "Removing Spinning Recipe for " + this.output.func_82833_r();
        }

        public String describeUndo() {
            return "Re-Adding Spinning Recipe for " + this.output.func_82833_r();
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
        private final SpinningRecipe recipe;

        public Add(SpinningRecipe recipe) {
            this.recipe = recipe;
        }

        public void apply() {
            SpinningRecipe.addRecipe(this.recipe);
        }

        public boolean canUndo() {
            return true;
        }

        public void undo() {
            SpinningRecipe.removeRecipe(this.recipe);
        }

        public String describe() {
            return "Adding Spinning Recipe for " + this.recipe.getOutput().func_82833_r();
        }

        public String describeUndo() {
            return "Removing Spinning Recipe for " + this.recipe.getOutput().func_82833_r();
        }

        public Object getOverrideKey() {
            return null;
        }
    }
}

