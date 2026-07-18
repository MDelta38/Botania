/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package vazkii.botania.common.block.tile;

import net.minecraft.nbt.NBTTagCompound;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.tile.TileMod;

public class TileBifrost
extends TileMod {
    private static final String TAG_TICKS = "ticks";
    public int ticks = 0;

    public void func_145845_h() {
        if (!this.field_145850_b.field_72995_K) {
            if (this.ticks <= 0) {
                this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            } else {
                --this.ticks;
            }
        } else if (Math.random() < 0.1) {
            Botania.proxy.sparkleFX(this.field_145850_b, (double)this.field_145851_c + Math.random(), (double)this.field_145848_d + Math.random(), (double)this.field_145849_e + Math.random(), (float)Math.random(), (float)Math.random(), (float)Math.random(), 0.45f + 0.2f * (float)Math.random(), 6);
        }
    }

    @Override
    public void func_145841_b(NBTTagCompound par1nbtTagCompound) {
        super.func_145841_b(par1nbtTagCompound);
        par1nbtTagCompound.func_74768_a(TAG_TICKS, this.ticks);
    }

    @Override
    public void func_145839_a(NBTTagCompound par1nbtTagCompound) {
        super.func_145839_a(par1nbtTagCompound);
        this.ticks = par1nbtTagCompound.func_74762_e(TAG_TICKS);
    }
}

