/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package vazkii.botania.common.block.tile.corporea;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import vazkii.botania.api.corporea.CorporeaHelper;
import vazkii.botania.api.corporea.ICorporeaRequestor;
import vazkii.botania.api.corporea.ICorporeaSpark;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.common.block.tile.corporea.TileCorporeaBase;

public class TileCorporeaCrystalCube
extends TileCorporeaBase
implements ICorporeaRequestor {
    private static final String TAG_REQUEST_TARGET = "requestTarget";
    private static final String TAG_ITEM_COUNT = "itemCount";
    private static final double LOG_2 = Math.log(2.0);
    ItemStack requestTarget;
    int itemCount = 0;
    int ticks = 0;
    public int compValue = 0;

    public void func_145845_h() {
        ++this.ticks;
        if (this.ticks % 20 == 0) {
            this.updateCount();
        }
    }

    public void setRequestTarget(ItemStack stack) {
        if (stack != null) {
            ItemStack copy = stack.func_77946_l();
            copy.field_77994_a = 1;
            this.requestTarget = copy;
            this.updateCount();
            if (!this.field_145850_b.field_72995_K) {
                VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
            }
        }
    }

    public ItemStack getRequestTarget() {
        return this.requestTarget;
    }

    public int getItemCount() {
        return this.itemCount;
    }

    public void doRequest(boolean fullStack) {
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        ICorporeaSpark spark = this.getSpark();
        if (spark != null && spark.getMaster() != null && this.requestTarget != null) {
            int count = fullStack ? this.requestTarget.func_77976_d() : 1;
            this.doCorporeaRequest(this.requestTarget, count, spark);
        }
    }

    private void updateCount() {
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        int oldCount = this.itemCount;
        this.itemCount = 0;
        ICorporeaSpark spark = this.getSpark();
        if (spark != null && spark.getMaster() != null && this.requestTarget != null) {
            List<ItemStack> stacks = CorporeaHelper.requestItem(this.requestTarget, -1, spark, true, false);
            for (ItemStack stack : stacks) {
                this.itemCount += stack.field_77994_a;
            }
        }
        if (this.itemCount != oldCount) {
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
            this.onUpdateCount();
        }
    }

    private void onUpdateCount() {
        this.compValue = this.getComparatorValue();
        this.field_145850_b.func_147453_f(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e));
    }

    @Override
    public void writeCustomNBT(NBTTagCompound par1nbtTagCompound) {
        super.writeCustomNBT(par1nbtTagCompound);
        NBTTagCompound cmp = new NBTTagCompound();
        if (this.requestTarget != null) {
            this.requestTarget.func_77955_b(cmp);
        }
        par1nbtTagCompound.func_74782_a(TAG_REQUEST_TARGET, (NBTBase)cmp);
        par1nbtTagCompound.func_74768_a(TAG_ITEM_COUNT, this.itemCount);
    }

    @Override
    public void readCustomNBT(NBTTagCompound par1nbtTagCompound) {
        super.readCustomNBT(par1nbtTagCompound);
        NBTTagCompound cmp = par1nbtTagCompound.func_74775_l(TAG_REQUEST_TARGET);
        this.requestTarget = ItemStack.func_77949_a((NBTTagCompound)cmp);
        this.itemCount = par1nbtTagCompound.func_74762_e(TAG_ITEM_COUNT);
    }

    public int func_70302_i_() {
        return 1;
    }

    public int getComparatorValue() {
        if (this.itemCount == 0) {
            return 0;
        }
        return Math.min(15, (int)Math.floor(Math.log(this.itemCount) / LOG_2) + 1);
    }

    @Override
    public boolean func_94041_b(int i, ItemStack itemstack) {
        return false;
    }

    public String func_145825_b() {
        return "corporeaCrystalCube";
    }

    @Override
    public void doCorporeaRequest(Object request, int count, ICorporeaSpark spark) {
        if (!(request instanceof ItemStack)) {
            return;
        }
        List<ItemStack> stacks = CorporeaHelper.requestItem(request, count, spark, true, true);
        spark.onItemsRequested(stacks);
        boolean did = false;
        for (ItemStack reqStack : stacks) {
            if (this.requestTarget == null) continue;
            EntityItem item = new EntityItem(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 1.5, (double)this.field_145849_e + 0.5, reqStack);
            this.field_145850_b.func_72838_d((Entity)item);
            this.itemCount -= reqStack.field_77994_a;
            did = true;
        }
        if (did) {
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
            this.onUpdateCount();
        }
    }
}

