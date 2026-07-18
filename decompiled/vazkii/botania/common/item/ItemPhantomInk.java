/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraftforge.oredict.RecipeSorter
 *  net.minecraftforge.oredict.RecipeSorter$Category
 */
package vazkii.botania.common.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.RecipeSorter;
import vazkii.botania.common.crafting.recipe.PhantomInkRecipe;
import vazkii.botania.common.item.ItemMod;

public class ItemPhantomInk
extends ItemMod {
    public ItemPhantomInk() {
        this.func_77655_b("phantomInk");
        GameRegistry.addRecipe((IRecipe)new PhantomInkRecipe());
        RecipeSorter.register((String)"botania:phantomInk", PhantomInkRecipe.class, (RecipeSorter.Category)RecipeSorter.Category.SHAPELESS, (String)"");
    }
}

