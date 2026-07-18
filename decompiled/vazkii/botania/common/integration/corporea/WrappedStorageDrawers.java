/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.jaquadro.minecraft.storagedrawers.api.storage.IDrawer
 *  com.jaquadro.minecraft.storagedrawers.api.storage.IDrawerGroup
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.integration.corporea;

import com.jaquadro.minecraft.storagedrawers.api.storage.IDrawer;
import com.jaquadro.minecraft.storagedrawers.api.storage.IDrawerGroup;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.corporea.CorporeaRequest;
import vazkii.botania.api.corporea.ICorporeaSpark;
import vazkii.botania.api.corporea.IWrappedInventory;
import vazkii.botania.common.integration.corporea.WrappedInventoryBase;

public class WrappedStorageDrawers
extends WrappedInventoryBase {
    private IDrawerGroup inv;

    private WrappedStorageDrawers(IDrawerGroup inv, ICorporeaSpark spark) {
        this.inv = inv;
        this.spark = spark;
    }

    @Override
    public IInventory getWrappedObject() {
        return (IInventory)this.inv;
    }

    @Override
    public List<ItemStack> countItems(CorporeaRequest request) {
        return this.iterateOverStacks(request, false);
    }

    @Override
    public List<ItemStack> extractItems(CorporeaRequest request) {
        return this.iterateOverStacks(request, true);
    }

    private List<ItemStack> iterateOverStacks(CorporeaRequest request, boolean doit) {
        ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
        boolean removedAny = false;
        for (int i = 0; i < this.inv.getDrawerCount(); ++i) {
            IDrawer drawer = this.inv.getDrawer(i);
            if (drawer == null) continue;
            ItemStack prototype = drawer.getStoredItemPrototype();
            int storedCount = drawer.getStoredItemCount();
            if (!this.isMatchingItemStack(request.matcher, request.checkNBT, prototype)) continue;
            int rem = Math.min(storedCount, request.count == -1 ? storedCount : request.count);
            if (rem > 0) {
                ItemStack copy = prototype.func_77946_l();
                copy.field_77994_a = rem;
                if (doit) {
                    stacks.addAll(this.breakDownBigStack(copy));
                } else {
                    stacks.add(copy);
                }
            }
            request.foundItems += storedCount;
            request.extractedItems += rem;
            if (doit && rem > 0) {
                this.decreaseStoredCount(drawer, rem);
                removedAny = true;
                if (this.spark != null) {
                    this.spark.onItemExtracted(prototype);
                }
            }
            if (request.count == -1) continue;
            request.count -= rem;
        }
        if (removedAny) {
            this.inv.markDirtyIfNeeded();
        }
        return stacks;
    }

    private void decreaseStoredCount(IDrawer drawer, int rem) {
        drawer.setStoredItemCount(drawer.getStoredItemCount() - rem);
    }

    public static IWrappedInventory wrap(IInventory inv, ICorporeaSpark spark) {
        return inv instanceof IDrawerGroup ? new WrappedStorageDrawers((IDrawerGroup)inv, spark) : null;
    }
}

