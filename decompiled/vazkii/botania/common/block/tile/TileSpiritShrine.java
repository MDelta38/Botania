/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.common.block.tile;

import vazkii.botania.common.Botania;
import vazkii.botania.common.block.tile.TileMod;

public class TileSpiritShrine
extends TileMod {
    int ticks;

    public void func_145845_h() {
        if (this.field_145850_b.field_72995_K) {
            if (this.ticks >= 40) {
                float[][] colors = new float[][]{{0.0f, 0.25f, 1.0f}, {1.0f, 0.0f, 0.2f}, {0.0f, 1.0f, 0.25f}, {1.0f, 1.0f, 0.25f}, {1.0f, 0.25f, 1.0f}, {0.25f, 1.0f, 1.0f}};
                int totalSpiritCount = 6;
                double tickIncrement = 360.0 / (double)totalSpiritCount;
                int liftTicks = 40 * (totalSpiritCount + 1);
                int existTicks = liftTicks * 2;
                int lowerTicks = existTicks + liftTicks;
                if (this.ticks < lowerTicks) {
                    int speed = 5;
                    double wticks = (double)(this.ticks * speed) - tickIncrement;
                    double r = Math.sin((this.ticks >= liftTicks ? (double)((this.ticks - liftTicks) * speed) - tickIncrement : -tickIncrement) * Math.PI / 180.0 * 0.75) + 1.25 + 0.5;
                    double g = Math.sin(wticks * Math.PI / 180.0 * 0.55);
                    for (int i = 0; i < totalSpiritCount; ++i) {
                        double x = (double)this.field_145851_c + Math.sin(wticks * Math.PI / 180.0) * r + 0.5;
                        double y = (double)this.field_145848_d + (this.ticks > existTicks ? 40.0 - (double)(this.ticks - existTicks) : (double)(Math.min(80 + 40 * i, this.ticks) - 40 * (i + 1))) * 0.1;
                        double z = (double)this.field_145849_e + Math.cos(wticks * Math.PI / 180.0) * r + 0.5;
                        wticks += tickIncrement;
                        float[] colorsfx = colors[i >= colors.length ? 0 : i];
                        Botania.proxy.wispFX(this.field_145850_b, x, y, z, colorsfx[0], colorsfx[1], colorsfx[2], 0.85f, (float)g * 0.05f, 0.25f);
                        Botania.proxy.wispFX(this.field_145850_b, x, y, z, colorsfx[0], colorsfx[1], colorsfx[2], (float)Math.random() * 0.1f + 0.1f, (float)(Math.random() - 0.5) * 0.05f, (float)(Math.random() - 0.5) * 0.05f, (float)(Math.random() - 0.5) * 0.05f, 0.9f);
                    }
                }
            }
            ++this.ticks;
        }
    }
}

