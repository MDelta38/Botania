/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.common.block.tile;

import vazkii.botania.common.block.tile.TileMod;

public class TileTeruTeruBozu
extends TileMod {
    public boolean wasRaining = false;

    public void func_145845_h() {
        boolean isRaining = this.field_145850_b.func_72896_J();
        if (isRaining && this.field_145850_b.field_73012_v.nextInt(9600) == 0) {
            this.field_145850_b.func_72912_H().func_76084_b(false);
        }
        if (this.wasRaining != isRaining) {
            this.field_145850_b.func_147453_f(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e));
        }
        this.wasRaining = isRaining;
    }
}

