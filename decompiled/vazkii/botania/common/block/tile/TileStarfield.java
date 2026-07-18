/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.common.block.tile;

import vazkii.botania.common.Botania;
import vazkii.botania.common.block.tile.TileMod;

public class TileStarfield
extends TileMod {
    public void func_145845_h() {
        int meta = this.func_145832_p();
        if (!this.field_145850_b.field_72995_K) {
            int newMeta;
            int n = newMeta = this.field_145850_b.func_72935_r() ? 0 : 1;
            if (newMeta != meta) {
                this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, newMeta, 3);
                meta = newMeta;
            }
        }
        if (meta == 1) {
            double radius = 512.0;
            int iter = 2;
            for (int i = 0; i < iter; ++i) {
                double x = (double)this.field_145851_c + 0.5 + (Math.random() - 0.5) * radius;
                double y = this.field_145848_d + 256;
                double z = (double)this.field_145849_e + 0.5 + (Math.random() - 0.5) * radius;
                float w = 0.6f;
                float c = 1.0f - w;
                float r = w + (float)Math.random() * c;
                float g = w + (float)Math.random() * c;
                float b = w + (float)Math.random() * c;
                float s = 20.0f + (float)Math.random() * 20.0f;
                int m = 50;
                Botania.proxy.sparkleFX(this.field_145850_b, x, y, z, r, g, b, s, m);
            }
        }
    }
}

