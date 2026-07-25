/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package thaumic.tinkerer.common.registry;

import java.util.Comparator;
import net.minecraft.item.ItemStack;

public class ItemStackCompatator
implements Comparator<ItemStack> {
    @Override
    public int compare(ItemStack o1, ItemStack o2) {
        return o1.func_82833_r().compareToIgnoreCase(o2.func_82833_r());
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}

