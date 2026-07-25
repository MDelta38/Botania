/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.item.Item
 */
package com.emoniph.witchery.util;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ItemUtil {
    public static void registerItem(Item item, String itemName) {
        int index = itemName.indexOf(58);
        if (index != -1) {
            itemName = itemName.substring(index + 1);
        }
        GameRegistry.registerItem((Item)item, (String)itemName);
    }
}

