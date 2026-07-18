/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package vazkii.botania.common.block.subtile.generating;

import java.util.ArrayList;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.signature.PassiveFlower;
import vazkii.botania.common.block.subtile.generating.SubTilePassiveGenerating;
import vazkii.botania.common.lexicon.LexiconData;

@PassiveFlower
public class SubTileDaybloom
extends SubTilePassiveGenerating {
    private static final String TAG_PRIME_POSITION_X = "primePositionX";
    private static final String TAG_PRIME_POSITION_Y = "primePositionY";
    private static final String TAG_PRIME_POSITION_Z = "primePositionZ";
    private static final String TAG_SAVED_POSITION = "savedPosition";
    int primePositionX;
    int primePositionY;
    int primePositionZ;
    boolean savedPosition;

    @Override
    public int getColor() {
        return 0xFFFF00;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.isPrime() && (!this.savedPosition || this.primePositionX != this.supertile.field_145851_c || this.primePositionY != this.supertile.field_145848_d || this.primePositionZ != this.supertile.field_145849_e)) {
            this.supertile.func_145831_w().func_147468_f(this.supertile.field_145851_c, this.supertile.field_145848_d, this.supertile.field_145849_e);
        }
    }

    public void setPrimusPosition() {
        this.primePositionX = this.supertile.field_145851_c;
        this.primePositionY = this.supertile.field_145848_d;
        this.primePositionZ = this.supertile.field_145849_e;
        this.savedPosition = true;
    }

    @Override
    public ArrayList<ItemStack> getDrops(ArrayList<ItemStack> list) {
        if (this.isPrime()) {
            list.clear();
        }
        return super.getDrops(list);
    }

    @Override
    public boolean canGeneratePassively() {
        boolean rain = this.supertile.func_145831_w().func_72959_q().func_76935_a(this.supertile.field_145851_c, this.supertile.field_145849_e).func_76744_g() > 0 && (this.supertile.func_145831_w().func_72896_J() || this.supertile.func_145831_w().func_72911_I());
        return this.supertile.func_145831_w().func_72935_r() && !rain && this.supertile.func_145831_w().func_72937_j(this.supertile.field_145851_c, this.supertile.field_145848_d + 1, this.supertile.field_145849_e);
    }

    @Override
    public int getDelayBetweenPassiveGeneration() {
        return this.isPrime() ? 10 : 12;
    }

    @Override
    public void writeToPacketNBT(NBTTagCompound cmp) {
        super.writeToPacketNBT(cmp);
        if (this.isPrime()) {
            cmp.func_74768_a(TAG_PRIME_POSITION_X, this.primePositionX);
            cmp.func_74768_a(TAG_PRIME_POSITION_Y, this.primePositionY);
            cmp.func_74768_a(TAG_PRIME_POSITION_Z, this.primePositionZ);
            cmp.func_74757_a(TAG_SAVED_POSITION, this.savedPosition);
        }
    }

    @Override
    public void readFromPacketNBT(NBTTagCompound cmp) {
        super.readFromPacketNBT(cmp);
        if (this.isPrime()) {
            this.primePositionX = cmp.func_74762_e(TAG_PRIME_POSITION_X);
            this.primePositionY = cmp.func_74762_e(TAG_PRIME_POSITION_Y);
            this.primePositionZ = cmp.func_74762_e(TAG_PRIME_POSITION_Z);
            this.savedPosition = cmp.func_74767_n(TAG_SAVED_POSITION);
        }
    }

    @Override
    public boolean shouldSyncPassiveGeneration() {
        return true;
    }

    @Override
    public boolean isPassiveFlower() {
        return !this.isPrime();
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.daybloom;
    }

    public boolean isPrime() {
        return false;
    }

    public static class Prime
    extends SubTileDaybloom {
        @Override
        public boolean isPrime() {
            return true;
        }

        @Override
        public LexiconEntry getEntry() {
            return LexiconData.primusLoci;
        }
    }
}

