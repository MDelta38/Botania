/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemPickaxe
 */
package com.emoniph.witchery.item;

import com.emoniph.witchery.WitcheryCreativeTab;
import com.emoniph.witchery.util.ItemUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;

public class ItemKobolditePickaxe
extends ItemPickaxe {
    public ItemKobolditePickaxe() {
        super(Item.ToolMaterial.EMERALD);
        this.func_77637_a(WitcheryCreativeTab.INSTANCE);
    }

    public Item func_77655_b(String itemName) {
        ItemUtil.registerItem((Item)this, itemName);
        return super.func_77655_b(itemName);
    }
}

