/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.oredict.OreDictionary
 */
package thaumic.tinkerer.common.registry;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import thaumic.tinkerer.common.core.handler.ConfigHandler;

public abstract class ThaumicTinkererRecipe {
    public static Object oreDictOrStack(ItemStack stack, String oreDict) {
        return OreDictionary.getOres((String)oreDict).isEmpty() && ConfigHandler.useOreDictMetal ? stack : oreDict;
    }

    public abstract void registerRecipe();
}

