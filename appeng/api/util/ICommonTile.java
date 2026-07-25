/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package appeng.api.util;

import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ICommonTile {
    public void getDrops(World var1, int var2, int var3, int var4, ArrayList<ItemStack> var5);
}

