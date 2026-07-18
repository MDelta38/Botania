/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  org.apache.logging.log4j.Level
 *  powercrystals.minefactoryreloaded.api.IDeepStorageUnit
 */
package vazkii.botania.common.integration.corporea;

import cpw.mods.fml.common.FMLLog;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.Level;
import powercrystals.minefactoryreloaded.api.IDeepStorageUnit;
import vazkii.botania.api.corporea.CorporeaRequest;
import vazkii.botania.api.corporea.ICorporeaSpark;
import vazkii.botania.api.corporea.IWrappedInventory;
import vazkii.botania.common.integration.corporea.WrappedInventoryBase;

public class WrappedDeepStorage
extends WrappedInventoryBase {
    private static boolean checkedInterface = false;
    private static boolean deepStoragePresent = false;
    private IDeepStorageUnit inv;

    private WrappedDeepStorage(IDeepStorageUnit inv, ICorporeaSpark spark) {
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
        ItemStack prototype = this.inv.getStoredItemType();
        if (prototype == null) {
            return stacks;
        }
        int storedCount = prototype.field_77994_a;
        if (this.isMatchingItemStack(request.matcher, request.checkNBT, prototype)) {
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
                this.decreaseStoredCount(this.inv, rem);
                removedAny = true;
                if (this.spark != null) {
                    this.spark.onItemExtracted(prototype);
                }
            }
            if (request.count != -1) {
                request.count -= rem;
            }
        }
        if (removedAny) {
            // empty if block
        }
        return stacks;
    }

    private void decreaseStoredCount(IDeepStorageUnit inventory, int rem) {
        inventory.setStoredItemCount(inventory.getStoredItemType().field_77994_a - rem);
    }

    public static IWrappedInventory wrap(IInventory inv, ICorporeaSpark spark) {
        if (!WrappedDeepStorage.isDeepStorageNeeded()) {
            return null;
        }
        return inv instanceof IDeepStorageUnit ? new WrappedDeepStorage((IDeepStorageUnit)inv, spark) : null;
    }

    private static boolean isDeepStorageNeeded() {
        if (!checkedInterface) {
            try {
                deepStoragePresent = Class.forName("powercrystals.minefactoryreloaded.api.IDeepStorageUnit") != null;
            }
            catch (ClassNotFoundException e) {
                deepStoragePresent = false;
            }
            checkedInterface = true;
            FMLLog.log((Level)Level.INFO, (String)"[Botania] Corporea support for Deep Storage: %b", (Object[])new Object[]{deepStoragePresent});
        }
        return deepStoragePresent;
    }
}

