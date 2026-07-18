/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.item.EntityItemFrame
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.block.tile.corporea;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.corporea.CorporeaHelper;
import vazkii.botania.api.corporea.ICorporeaInterceptor;
import vazkii.botania.api.corporea.ICorporeaSpark;
import vazkii.botania.common.block.tile.corporea.TileCorporeaBase;
import vazkii.botania.common.block.tile.corporea.TileCorporeaRetainer;
import vazkii.botania.common.lib.LibMisc;

public class TileCorporeaInterceptor
extends TileCorporeaBase
implements ICorporeaInterceptor {
    @Override
    public boolean func_94041_b(int i, ItemStack itemstack) {
        return false;
    }

    public int func_70302_i_() {
        return 1;
    }

    public String func_145825_b() {
        return "corporeaInterceptor";
    }

    @Override
    public void interceptRequest(Object request, int count, ICorporeaSpark spark, ICorporeaSpark source, List<ItemStack> stacks, List<IInventory> inventories, boolean doit) {
    }

    @Override
    public void interceptRequestLast(Object request, int count, ICorporeaSpark spark, ICorporeaSpark source, List<ItemStack> stacks, List<IInventory> inventories, boolean doit) {
        List<ItemStack> filter = this.getFilter();
        for (ItemStack stack : filter) {
            if (!this.requestMatches(request, stack)) continue;
            int missing = count;
            for (ItemStack stack_ : stacks) {
                missing -= stack_.field_77994_a;
            }
            if (missing <= 0 || this.func_145832_p() != 0) continue;
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, 3);
            this.field_145850_b.func_147464_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.func_145838_q(), 2);
            TileEntity requestor = (TileEntity)source.getInventory();
            for (ForgeDirection dir : LibMisc.CARDINAL_DIRECTIONS) {
                TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c + dir.offsetX, this.field_145848_d, this.field_145849_e + dir.offsetZ);
                if (tile == null || !(tile instanceof TileCorporeaRetainer)) continue;
                ((TileCorporeaRetainer)tile).setPendingRequest(requestor.field_145851_c, requestor.field_145848_d, requestor.field_145849_e, request, count);
            }
            return;
        }
    }

    public boolean requestMatches(Object request, ItemStack filter) {
        if (filter == null) {
            return false;
        }
        if (request instanceof ItemStack) {
            ItemStack stack = (ItemStack)request;
            return stack != null && stack.func_77969_a(filter) && ItemStack.func_77970_a((ItemStack)filter, (ItemStack)stack);
        }
        String name = (String)request;
        return CorporeaHelper.stacksMatch(filter, name);
    }

    public List<ItemStack> getFilter() {
        ArrayList<ItemStack> filter = new ArrayList<ItemStack>();
        int[] orientationToDir = new int[]{3, 4, 2, 5};
        for (ForgeDirection dir : LibMisc.CARDINAL_DIRECTIONS) {
            List frames = this.field_145850_b.func_72872_a(EntityItemFrame.class, AxisAlignedBB.func_72330_a((double)(this.field_145851_c + dir.offsetX), (double)(this.field_145848_d + dir.offsetY), (double)(this.field_145849_e + dir.offsetZ), (double)(this.field_145851_c + dir.offsetX + 1), (double)(this.field_145848_d + dir.offsetY + 1), (double)(this.field_145849_e + dir.offsetZ + 1)));
            for (EntityItemFrame frame : frames) {
                int orientation = frame.field_82332_a;
                if (orientationToDir[orientation] != dir.ordinal()) continue;
                filter.add(frame.func_82335_i());
            }
        }
        return filter;
    }
}

