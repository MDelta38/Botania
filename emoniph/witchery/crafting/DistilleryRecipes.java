/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.StatCollector
 */
package com.emoniph.witchery.crafting;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.util.Const;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;

public class DistilleryRecipes {
    private static final DistilleryRecipes INSTANCE = new DistilleryRecipes();
    public final ArrayList<DistilleryRecipe> recipes = new ArrayList();

    public static DistilleryRecipes instance() {
        return INSTANCE;
    }

    private DistilleryRecipes() {
    }

    public DistilleryRecipe addRecipe(ItemStack input1, ItemStack input2, int jars, ItemStack output1, ItemStack output2, ItemStack output3, ItemStack output4) {
        DistilleryRecipe recipe = new DistilleryRecipe(input1, input2, jars, output1, output2, output3, output4);
        this.recipes.add(recipe);
        return recipe;
    }

    public DistilleryRecipe getDistillingResult(ItemStack input1, ItemStack intput2, ItemStack jars) {
        for (DistilleryRecipe recipe : this.recipes) {
            if (!recipe.isMatch(input1, intput2, jars)) continue;
            return recipe;
        }
        return null;
    }

    public DistilleryRecipe findRecipeFor(ItemStack result) {
        for (DistilleryRecipe recipe : this.recipes) {
            if (!recipe.resultsIn(result)) continue;
            return recipe;
        }
        return null;
    }

    public DistilleryRecipe findRecipeUsing(ItemStack ingredient) {
        for (DistilleryRecipe recipe : this.recipes) {
            if (!recipe.uses(ingredient)) continue;
            return recipe;
        }
        return null;
    }

    public static class DistilleryRecipe {
        public final ItemStack[] inputs;
        public final int jars;
        public final ItemStack[] outputs;

        private DistilleryRecipe(ItemStack input1, ItemStack input2, int jars, ItemStack output1, ItemStack output2, ItemStack output3, ItemStack output4) {
            this.inputs = new ItemStack[]{input1, input2};
            this.jars = jars;
            this.outputs = new ItemStack[]{output1, output2, output3, output4};
        }

        private boolean isMatch(ItemStack input1, ItemStack input2, ItemStack jars) {
            return (this.jars == 0 || jars != null && jars.field_77994_a >= this.jars) && (this.isMatch(input1, this.inputs[0]) && this.isMatch(input2, this.inputs[1]) || this.isMatch(input1, this.inputs[1]) && this.isMatch(input2, this.inputs[0]));
        }

        private boolean isMatch(ItemStack a, ItemStack b) {
            return a == null && b == null || a != null && b != null && a.func_77973_b() == b.func_77973_b() && (!a.func_77981_g() || a.func_77960_j() == b.func_77960_j());
        }

        public int getJars() {
            return this.jars;
        }

        public ItemStack[] getOutputs() {
            return this.outputs;
        }

        public String getDescription() {
            String s;
            PotionEffect effect;
            List list;
            StringBuffer sb = new StringBuffer();
            sb.append(Witchery.resource("witchery.book.distillery.items"));
            sb.append(Const.BOOK_NEWLINE);
            sb.append(Const.BOOK_NEWLINE);
            for (ItemStack stack : this.inputs) {
                if (stack == null) continue;
                sb.append("\u00a78>\u00a70 ");
                if (stack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150337_Q)) {
                    sb.append(Witchery.resource("witchery.book.mushroomred"));
                } else if (stack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150338_P)) {
                    sb.append(Witchery.resource("witchery.book.mushroombrown"));
                } else if (stack.func_77973_b() == Items.field_151068_bn) {
                    list = Items.field_151068_bn.func_77832_l(stack);
                    if (list != null && !list.isEmpty()) {
                        effect = (PotionEffect)list.get(0);
                        s = stack.func_82833_r();
                        if (effect.func_76458_c() > 0) {
                            s = s + " " + StatCollector.func_74838_a((String)("potion.potency." + effect.func_76458_c())).trim();
                        }
                        if (effect.func_76459_b() > 20) {
                            s = s + " (" + Potion.func_76389_a((PotionEffect)effect) + ")";
                        }
                        sb.append(s);
                    } else {
                        sb.append(stack.func_82833_r());
                    }
                } else {
                    sb.append(stack.func_82833_r());
                }
                sb.append(Const.BOOK_NEWLINE);
            }
            sb.append(String.format("\n\u00a78%s\u00a70 %d\n", Witchery.resource("witchery.book.distillery.jars"), this.jars));
            sb.append(Const.BOOK_NEWLINE);
            sb.append(Witchery.resource("witchery.book.distillery.results"));
            sb.append(Const.BOOK_NEWLINE);
            sb.append(Const.BOOK_NEWLINE);
            for (ItemStack stack : this.outputs) {
                if (stack == null) continue;
                sb.append("\u00a78>\u00a70 ");
                if (stack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150337_Q)) {
                    sb.append(Witchery.resource("witchery.book.mushroomred"));
                } else if (stack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150338_P)) {
                    sb.append(Witchery.resource("witchery.book.mushroombrown"));
                } else if (stack.func_77973_b() == Items.field_151068_bn) {
                    list = Items.field_151068_bn.func_77832_l(stack);
                    if (list != null && !list.isEmpty()) {
                        effect = (PotionEffect)list.get(0);
                        s = stack.func_82833_r();
                        if (effect.func_76458_c() > 0) {
                            s = s + " " + StatCollector.func_74838_a((String)("potion.potency." + effect.func_76458_c())).trim();
                        }
                        if (effect.func_76459_b() > 20) {
                            s = s + " (" + Potion.func_76389_a((PotionEffect)effect) + ")";
                        }
                        sb.append(s);
                    } else {
                        sb.append(stack.func_82833_r());
                    }
                } else {
                    sb.append(stack.func_82833_r());
                }
                sb.append(Const.BOOK_NEWLINE);
            }
            return sb.toString();
        }

        public boolean resultsIn(ItemStack result) {
            for (ItemStack stack : this.outputs) {
                if (stack == null || !stack.func_77969_a(result)) continue;
                return true;
            }
            return false;
        }

        public boolean uses(ItemStack ingredient) {
            for (ItemStack stack : this.inputs) {
                if (stack == null || !stack.func_77969_a(ingredient)) continue;
                return true;
            }
            return Witchery.Items.GENERIC.itemEmptyClayJar.isMatch(ingredient) && this.jars > 0;
        }
    }
}

