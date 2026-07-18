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

public interface ICorporeaSpark {
    public void registerConnections(ICorporeaSpark var1, ICorporeaSpark var2, List<ICorporeaSpark> var3);

    public IInventory getInventory();

    public List<ICorporeaSpark> getConnections();

    public List<ICorporeaSpark> getRelatives();

    public ICorporeaSpark getMaster();

    public void onItemExtracted(ItemStack var1);

    public void onItemsRequested(List<ItemStack> var1);

    public boolean isMaster();

    public int getNetwork();
}

