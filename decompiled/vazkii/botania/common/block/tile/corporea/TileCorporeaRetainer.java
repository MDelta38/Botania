/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 */
package vazkii.botania.common.block.tile.corporea;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import vazkii.botania.api.corporea.CorporeaHelper;
import vazkii.botania.api.corporea.ICorporeaRequestor;
import vazkii.botania.api.corporea.ICorporeaSpark;
import vazkii.botania.common.block.tile.TileMod;

public class TileCorporeaRetainer
extends TileMod {
    private static final String TAG_PENDING_REQUEST = "pendingRequest";
    private static final String TAG_REQUEST_X = "requestX";
    private static final String TAG_REQUEST_Y = "requestY";
    private static final String TAG_REQUEST_Z = "requestZ";
    private static final String TAG_REQUEST_TYPE = "requestType";
    private static final String TAG_REQUEST_CONTENTS = "requestContents";
    private static final String TAG_REQUEST_STACK = "requestStack";
    private static final String TAG_REQUEST_COUNT = "requestCount";
    private static final int REQUEST_NULL = 0;
    private static final int REQUEST_ITEMSTACK = 1;
    private static final int REQUEST_STRING = 2;
    boolean pendingRequest = false;
    int requestX;
    int requestY;
    int requestZ;
    Object request;
    int requestCount;

    public void setPendingRequest(int x, int y, int z, Object request, int requestCount) {
        if (this.pendingRequest) {
            return;
        }
        this.requestX = x;
        this.requestY = y;
        this.requestZ = z;
        this.request = request;
        this.requestCount = requestCount;
        this.pendingRequest = true;
        this.field_145850_b.func_147453_f(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e));
    }

    public boolean hasPendingRequest() {
        return this.pendingRequest;
    }

    public void fulfilRequest() {
        IInventory inv;
        if (!this.hasPendingRequest()) {
            return;
        }
        ICorporeaSpark spark = CorporeaHelper.getSparkForBlock(this.field_145850_b, this.requestX, this.requestY, this.requestZ);
        if (spark != null && (inv = spark.getInventory()) != null && inv instanceof ICorporeaRequestor) {
            ICorporeaRequestor requestor = (ICorporeaRequestor)inv;
            requestor.doCorporeaRequest(this.request, this.requestCount, spark);
            this.pendingRequest = false;
            this.field_145850_b.func_147453_f(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e));
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound cmp) {
        super.writeCustomNBT(cmp);
        cmp.func_74757_a(TAG_PENDING_REQUEST, this.pendingRequest);
        cmp.func_74768_a(TAG_REQUEST_X, this.requestX);
        cmp.func_74768_a(TAG_REQUEST_Y, this.requestY);
        cmp.func_74768_a(TAG_REQUEST_Z, this.requestZ);
        int reqType = 0;
        if (this.request != null) {
            reqType = this.request instanceof String ? 2 : 1;
        }
        cmp.func_74768_a(TAG_REQUEST_TYPE, reqType);
        switch (reqType) {
            case 2: {
                cmp.func_74778_a(TAG_REQUEST_CONTENTS, (String)this.request);
                break;
            }
            case 1: {
                NBTTagCompound cmp1 = new NBTTagCompound();
                ((ItemStack)this.request).func_77955_b(cmp1);
                cmp.func_74782_a(TAG_REQUEST_STACK, (NBTBase)cmp1);
                break;
            }
        }
        cmp.func_74768_a(TAG_REQUEST_COUNT, this.requestCount);
    }

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        super.readCustomNBT(cmp);
        this.pendingRequest = cmp.func_74767_n(TAG_PENDING_REQUEST);
        this.requestX = cmp.func_74762_e(TAG_REQUEST_X);
        this.requestY = cmp.func_74762_e(TAG_REQUEST_Y);
        this.requestZ = cmp.func_74762_e(TAG_REQUEST_Z);
        int reqType = cmp.func_74762_e(TAG_REQUEST_TYPE);
        switch (reqType) {
            case 2: {
                this.request = cmp.func_74779_i(TAG_REQUEST_CONTENTS);
                break;
            }
            case 1: {
                NBTTagCompound cmp1 = cmp.func_74775_l(TAG_REQUEST_STACK);
                this.request = ItemStack.func_77949_a((NBTTagCompound)cmp1);
                break;
            }
        }
        this.requestCount = cmp.func_74762_e(TAG_REQUEST_COUNT);
    }
}

