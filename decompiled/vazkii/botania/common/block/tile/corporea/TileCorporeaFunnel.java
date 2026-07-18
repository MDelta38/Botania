/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityItemFrame
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.block.tile.corporea;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.corporea.CorporeaHelper;
import vazkii.botania.api.corporea.ICorporeaRequestor;
import vazkii.botania.api.corporea.ICorporeaSpark;
import vazkii.botania.common.block.tile.corporea.TileCorporeaBase;
import vazkii.botania.common.core.helper.InventoryHelper;
import vazkii.botania.common.lib.LibMisc;

public class TileCorporeaFunnel
extends TileCorporeaBase
implements ICorporeaRequestor {
    public void doRequest() {
        ItemStack stack;
        List<ItemStack> filter;
        ICorporeaSpark spark = this.getSpark();
        if (spark != null && spark.getMaster() != null && !(filter = this.getFilter()).isEmpty() && (stack = filter.get(this.field_145850_b.field_73012_v.nextInt(filter.size()))) != null) {
            this.doCorporeaRequest(stack, stack.field_77994_a, spark);
        }
    }

    public List<ItemStack> getFilter() {
        ArrayList<ItemStack> filter = new ArrayList<ItemStack>();
        int[] orientationToDir = new int[]{3, 4, 2, 5};
        int[] rotationToStackSize = new int[]{1, 16, 32, 64};
        for (ForgeDirection dir : LibMisc.CARDINAL_DIRECTIONS) {
            List frames = this.field_145850_b.func_72872_a(EntityItemFrame.class, AxisAlignedBB.func_72330_a((double)(this.field_145851_c + dir.offsetX), (double)(this.field_145848_d + dir.offsetY), (double)(this.field_145849_e + dir.offsetZ), (double)(this.field_145851_c + dir.offsetX + 1), (double)(this.field_145848_d + dir.offsetY + 1), (double)(this.field_145849_e + dir.offsetZ + 1)));
            for (EntityItemFrame frame : frames) {
                ItemStack stack;
                int orientation = frame.field_82332_a;
                if (orientationToDir[orientation] != dir.ordinal() || (stack = frame.func_82335_i()) == null) continue;
                ItemStack copy = stack.func_77946_l();
                copy.field_77994_a = rotationToStackSize[frame.func_82333_j()];
                filter.add(copy);
            }
        }
        return filter;
    }

    @Override
    public boolean func_94041_b(int i, ItemStack itemstack) {
        return false;
    }

    public int func_70302_i_() {
        return 1;
    }

    public String func_145825_b() {
        return "corporeaFunnel";
    }

    @Override
    public void doCorporeaRequest(Object request, int count, ICorporeaSpark spark) {
        if (!(request instanceof ItemStack)) {
            return;
        }
        IInventory inv = InventoryHelper.getInventory(this.field_145850_b, this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
        if (inv == null || inv instanceof TileCorporeaFunnel) {
            inv = InventoryHelper.getInventory(this.field_145850_b, this.field_145851_c, this.field_145848_d - 2, this.field_145849_e);
        }
        List<ItemStack> stacks = CorporeaHelper.requestItem(request, count, spark, true, true);
        spark.onItemsRequested(stacks);
        for (ItemStack reqStack : stacks) {
            if (request == null) continue;
            if (inv != null && !(inv instanceof TileCorporeaFunnel) && reqStack.field_77994_a == InventoryHelper.testInventoryInsertion(inv, reqStack, ForgeDirection.UP)) {
                InventoryHelper.insertItemIntoInventory(inv, reqStack);
                continue;
            }
            EntityItem item = new EntityItem(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 1.5, (double)this.field_145849_e + 0.5, reqStack);
            this.field_145850_b.func_72838_d((Entity)item);
        }
    }
}

