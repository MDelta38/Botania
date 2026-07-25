/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 */
package thaumic.tinkerer.common.block.tile;

import net.minecraft.tileentity.TileEntity;
import thaumic.tinkerer.common.ThaumicTinkerer;

public class TileForcefield
extends TileEntity {
    public int ticks = 60;

    public void func_145845_h() {
        if (this.ticks < 0) {
            this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
        --this.ticks;
        ThaumicTinkerer.tcProxy.blockSparkle(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, 255, 1);
    }
}

