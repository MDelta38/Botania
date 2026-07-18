/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.api.item;

import net.minecraft.item.ItemStack;

public interface ISortableTool {
    public ToolType getSortingType(ItemStack var1);

    public int getSortingPriority(ItemStack var1);

    public static enum ToolType {
        PICK,
        AXE,
        SHOVEL;

    }
}

