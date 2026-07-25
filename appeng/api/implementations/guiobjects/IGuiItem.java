/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package appeng.api.implementations.guiobjects;

import appeng.api.implementations.guiobjects.IGuiItemObject;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IGuiItem {
    public IGuiItemObject getGuiObject(ItemStack var1, World var2, int var3, int var4, int var5);
}

