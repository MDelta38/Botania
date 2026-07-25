/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 */
package thaumcraft.common.tiles;

import net.minecraft.tileentity.TileEntity;
import thaumcraft.common.config.ConfigBlocks;

public class TileWardingStoneFence
extends TileEntity {
    int count = 0;

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        if (!this.field_145850_b.field_72995_K) {
            if (this.count == 0) {
                this.count = this.field_145850_b.field_73012_v.nextInt(100);
            }
            if (!(++this.count % 100 != 0 || this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == ConfigBlocks.blockCosmeticSolid && this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == 3 || this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e) == ConfigBlocks.blockCosmeticSolid && this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e) == 3)) {
                this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }
    }
}

