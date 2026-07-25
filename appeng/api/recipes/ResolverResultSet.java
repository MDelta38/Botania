/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package appeng.api.recipes;

import java.util.Arrays;
import java.util.List;
import net.minecraft.item.ItemStack;

public class ResolverResultSet {
    public final String name;
    public final List<ItemStack> results;

    public ResolverResultSet(String myName, ItemStack ... set) {
        this.results = Arrays.asList(set);
        this.name = myName;
    }
}

