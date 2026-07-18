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
import vazkii.botania.api.corporea.CorporeaRequest;
import vazkii.botania.api.corporea.ICorporeaSpark;

public interface IWrappedInventory {
    public IInventory getWrappedObject();

    public List<ItemStack> countItems(CorporeaRequest var1);

    public ICorporeaSpark getSpark();

    public List<ItemStack> extractItems(CorporeaRequest var1);
}

