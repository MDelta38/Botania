/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 */
package thaumcraft.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.common.tiles.TileArcaneLamp;

public class TileArcaneLampLight
extends TileEntity {
    int x = Integer.MAX_VALUE;
    int y = Integer.MAX_VALUE;
    int z = Integer.MAX_VALUE;
    int count = 0;

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        if (!this.field_145850_b.field_72995_K) {
            if (this.count == 0) {
                this.count = this.field_145850_b.field_73012_v.nextInt(100);
            }
            if (++this.count % 100 == 0 && !(this.field_145850_b.func_147438_o(this.x, this.y, this.z) instanceof TileArcaneLamp)) {
                this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }
    }

    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.x = nbttagcompound.func_74762_e("sourceX");
        this.y = nbttagcompound.func_74762_e("sourceY");
        this.z = nbttagcompound.func_74762_e("sourceZ");
    }

    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        nbttagcompound.func_74768_a("sourceX", this.x);
        nbttagcompound.func_74768_a("sourceY", this.y);
        nbttagcompound.func_74768_a("sourceZ", this.z);
    }
}

