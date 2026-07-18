/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.integration.corporea;

import java.util.ArrayList;
import java.util.Collection;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.corporea.CorporeaHelper;
import vazkii.botania.api.corporea.ICorporeaSpark;
import vazkii.botania.api.corporea.IWrappedInventory;

public abstract class WrappedInventoryBase
implements IWrappedInventory {
    protected ICorporeaSpark spark;

    @Override
    public ICorporeaSpark getSpark() {
        return this.spark;
    }

    protected boolean isMatchingItemStack(Object matcher, boolean checkNBT, ItemStack stackAt) {
        return matcher instanceof ItemStack ? CorporeaHelper.stacksMatch((ItemStack)matcher, stackAt, checkNBT) : (matcher instanceof String ? CorporeaHelper.stacksMatch(stackAt, (String)matcher) : false);
    }

    protected Collection<? extends ItemStack> breakDownBigStack(ItemStack stack) {
        ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
        int additionalStacks = stack.field_77994_a / stack.func_77976_d();
        int lastStackSize = stack.field_77994_a % stack.func_77976_d();
        if (additionalStacks > 0) {
            ItemStack fullStack = stack.func_77946_l();
            fullStack.field_77994_a = stack.func_77976_d();
            for (int i = 0; i < additionalStacks; ++i) {
                stacks.add(fullStack.func_77946_l());
            }
        }
        ItemStack lastStack = stack.func_77946_l();
        lastStack.field_77994_a = lastStackSize;
        stacks.add(lastStack);
        return stacks;
    }
}

