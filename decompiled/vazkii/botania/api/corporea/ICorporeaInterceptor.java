/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.api.corporea;

import java.util.List;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.corporea.ICorporeaSpark;

public interface ICorporeaInterceptor {
    public void interceptRequest(Object var1, int var2, ICorporeaSpark var3, ICorporeaSpark var4, List<ItemStack> var5, List<IInventory> var6, boolean var7);

    public void interceptRequestLast(Object var1, int var2, ICorporeaSpark var3, ICorporeaSpark var4, List<ItemStack> var5, List<IInventory> var6, boolean var7);
}

