/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.crafting;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.familiar.Familiar;
import com.emoniph.witchery.util.Const;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class KettleRecipes {
    private static final KettleRecipes INSTANCE = new KettleRecipes();
    public final ArrayList<KettleRecipe> recipes = new ArrayList();

    public static KettleRecipes instance() {
        return INSTANCE;
    }

    private KettleRecipes() {
    }

    public KettleRecipe addRecipe(ItemStack output, int hatBonus, int familiarType, float powerRequired, int color, int dimension, boolean inBook, ItemStack ... inputs) {
        KettleRecipe recipe = new KettleRecipe(output, hatBonus, familiarType, powerRequired, color, dimension, inBook, inputs);
        this.recipes.add(recipe);
        return recipe;
    }

    public KettleRecipe addRecipe(ItemStack output, int hatBonus, int familiarType, float powerRequired, int color, int dimension, ItemStack ... inputs) {
        return this.addRecipe(output, hatBonus, familiarType, powerRequired, color, dimension, true, inputs);
    }

    public KettleRecipe getResult(ItemStack[] inputs, int length, boolean partial, World world) {
        for (KettleRecipe recipe : this.recipes) {
            if (!recipe.isMatch(inputs, length, partial, world)) continue;
            return recipe;
        }
        return null;
    }

    public int getHatBonus(ItemStack stack) {
        for (KettleRecipe recipe : this.recipes) {
            if (!recipe.output.func_77969_a(stack)) continue;
            return recipe.hatBonus;
        }
        return 0;
    }

    public boolean isBrewableBy(ItemStack stack, EntityPlayer player) {
        for (KettleRecipe recipe : this.recipes) {
            if (!recipe.output.func_77969_a(stack)) continue;
            return recipe.isBrewableBy(player);
        }
        return false;
    }

    public KettleRecipe findRecipeFor(ItemStack result) {
        for (KettleRecipe recipe : this.recipes) {
            if (!recipe.output.func_77969_a(result)) continue;
            return recipe;
        }
        return null;
    }

    public static class KettleRecipe {
        public final ItemStack[] inputs;
        public final ItemStack output;
        public final float power;
        final int color;
        final int hatBonus;
        final int familiarType;
        final int dimension;
        public final boolean inBook;
        private String unlocalizedName;

        private KettleRecipe(ItemStack output, int hatBonus, int familiarType, float power, int color, int dimension, boolean inBook, ItemStack ... inputs) {
            this.inputs = inputs;
            this.output = output;
            this.power = power;
            this.color = color;
            this.hatBonus = hatBonus;
            this.familiarType = familiarType;
            this.dimension = dimension;
            this.inBook = inBook;
        }

        public int getColor() {
            return this.color;
        }

        private boolean isMatch(ItemStack[] current, int currentLength, boolean partial, World world) {
            if (this.dimension != 0 && this.dimension != world.field_73011_w.field_76574_g) {
                return false;
            }
            if (!partial && currentLength != this.inputs.length) {
                return false;
            }
            ArrayList<ItemStack> inputsToFind = new ArrayList<ItemStack>(Arrays.asList(this.inputs));
            for (int j = 0; j < currentLength; ++j) {
                ItemStack itemstack = current[j];
                boolean foundOne = false;
                for (int i = 0; i < inputsToFind.size(); ++i) {
                    ItemStack input = inputsToFind.get(i);
                    if (itemstack == null || input == null || !itemstack.func_77969_a(input)) continue;
                    inputsToFind.remove(i);
                    foundOne = true;
                    break;
                }
                if (foundOne) continue;
                if (itemstack == null) break;
                return false;
            }
            return inputsToFind.size() == 0 || partial && inputsToFind.size() < this.inputs.length;
        }

        private boolean allEmpty(ArrayList<ItemStack> items) {
            for (ItemStack stack : items) {
                if (stack == null) continue;
                return false;
            }
            return true;
        }

        public ItemStack getOutput(EntityPlayer player, boolean createCopy) {
            if (this.hatBonus > 0 && player != null && player.field_71071_by != null && player.field_71071_by.func_70440_f(3) != null && player.field_71071_by.func_70440_f(3).func_77973_b() == Witchery.Items.WITCH_HAT) {
                ItemStack stack = this.output.func_77946_l();
                stack.field_77994_a += this.hatBonus;
                return stack;
            }
            return createCopy ? this.output.func_77946_l() : this.output;
        }

        public float getRequiredPower() {
            return this.power;
        }

        public String getDescription() {
            String localizedName;
            StringBuffer sb = new StringBuffer();
            sb.append("\u00a7n");
            sb.append(this.output.func_82833_r());
            sb.append("\u00a7r");
            sb.append(Const.BOOK_NEWLINE);
            sb.append(Const.BOOK_NEWLINE);
            if (this.unlocalizedName != null && !this.unlocalizedName.isEmpty() && !(localizedName = Witchery.resource(this.unlocalizedName)).isEmpty()) {
                sb.append(localizedName);
                sb.append(Const.BOOK_NEWLINE);
                sb.append(Const.BOOK_NEWLINE);
            }
            for (ItemStack stack : this.inputs) {
                sb.append("\u00a78>\u00a70 ");
                if (stack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150337_Q)) {
                    sb.append(Witchery.resource("witchery.book.mushroomred"));
                } else if (stack.func_77973_b() == Item.func_150898_a((Block)Blocks.field_150338_P)) {
                    sb.append(Witchery.resource("witchery.book.mushroombrown"));
                } else if (stack.func_77973_b() == Items.field_151068_bn) {
                    List list = Items.field_151068_bn.func_77832_l(stack);
                    if (list != null && !list.isEmpty()) {
                        PotionEffect effect = (PotionEffect)list.get(0);
                        String s = stack.func_82833_r();
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
            if (this.power > 0.0f) {
                sb.append(String.format("\n\u00a78%s\u00a70 %s\n", Witchery.resource("witchery.book.altarpower"), MathHelper.func_76141_d((float)this.power)));
            }
            return sb.toString();
        }

        public KettleRecipe setUnlocalizedName(String unlocalizedName) {
            this.unlocalizedName = unlocalizedName;
            return this;
        }

        public boolean isBrewableBy(EntityPlayer player) {
            if (this.familiarType == 0) {
                return true;
            }
            if (player == null) {
                return false;
            }
            int familiarOfPlayer = Familiar.getActiveFamiliarType(player);
            return familiarOfPlayer == this.familiarType;
        }
    }
}

