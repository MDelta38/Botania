/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.AxisAlignedBB
 */
package vazkii.botania.common.block.tile.mana;

import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.IManaCollisionGhost;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.tile.TileMod;

public class TileManaDetector
extends TileMod
implements IManaCollisionGhost {
    public void func_145845_h() {
        if (!this.field_145850_b.field_72995_K) {
            int expectedMeta;
            int meta = this.func_145832_p();
            int n = expectedMeta = this.field_145850_b.func_72872_a(IManaBurst.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1))).size() != 0 ? 1 : 0;
            if (meta != expectedMeta) {
                this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, expectedMeta, 3);
            }
            if (expectedMeta == 1) {
                for (int i = 0; i < 4; ++i) {
                    Botania.proxy.sparkleFX(this.func_145831_w(), (double)this.field_145851_c + Math.random(), (double)this.field_145848_d + Math.random(), (double)this.field_145849_e + Math.random(), 1.0f, 0.2f, 0.2f, 0.7f + 0.5f * (float)Math.random(), 5);
                }
            }
        }
    }

    @Override
    public boolean isGhost() {
        return true;
    }
}

