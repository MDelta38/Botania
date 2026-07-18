/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.integration.corporea;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.corporea.CorporeaHelper;
import vazkii.botania.api.corporea.CorporeaRequest;
import vazkii.botania.api.corporea.ICorporeaSpark;
import vazkii.botania.api.corporea.IWrappedInventory;
import vazkii.botania.common.integration.corporea.WrappedInventoryBase;

public class WrappedIInventory
extends WrappedInventoryBase {
    private IInventory inv;

    private WrappedIInventory(IInventory inv, ICorporeaSpark spark) {
        this.inv = inv;
        this.spark = spark;
    }

    @Override
    public IInventory getWrappedObject() {
        return this.inv;
    }

    @Override
    public List<ItemStack> countItems(CorporeaRequest request) {
        return this.iterateOverSlots(request, false);
    }

    @Override
    public List<ItemStack> extractItems(CorporeaRequest request) {
        return this.iterateOverSlots(request, true);
    }

    private List<ItemStack> iterateOverSlots(CorporeaRequest request, boolean doit) {
        ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
        boolean removedAny = false;
        for (int i = this.inv.func_70302_i_() - 1; i >= 0; --i) {
            ItemStack stackAt;
            if (!CorporeaHelper.isValidSlot(this.inv, i) || !this.isMatchingItemStack(request.matcher, request.checkNBT, stackAt = this.inv.func_70301_a(i))) continue;
            int rem = Math.min(stackAt.field_77994_a, request.count == -1 ? stackAt.field_77994_a : request.count);
            if (rem > 0) {
                ItemStack copy = stackAt.func_77946_l();
                if (rem < copy.field_77994_a) {
                    copy.field_77994_a = rem;
                }
                stacks.add(copy);
            }
            request.foundItems += stackAt.field_77994_a;
            request.extractedItems += rem;
            if (doit && rem > 0) {
                this.inv.func_70298_a(i, rem);
                removedAny = true;
                if (this.spark != null) {
                    this.spark.onItemExtracted(stackAt);
                }
            }
            if (request.count == -1) continue;
            request.count -= rem;
        }
        if (removedAny) {
            this.inv.func_70296_d();
        }
        return stacks;
    }

    public static IWrappedInventory wrap(IInventory inv, ICorporeaSpark spark) {
        return new WrappedIInventory(inv, spark);
    }
}

