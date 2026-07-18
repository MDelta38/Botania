/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.block.tile;

import net.minecraft.entity.passive.EntitySheep;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.tile.TileMod;

public class TileManaBeacon
extends TileMod {
    public void func_145845_h() {
        boolean redstone = false;
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            int redstoneSide = this.field_145850_b.func_72878_l(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ, dir.ordinal());
            if (redstoneSide <= 0) continue;
            redstone = true;
        }
        if (!redstone) {
            float[] color = EntitySheep.field_70898_d[this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e)];
            Botania.proxy.setWispFXDistanceLimit(false);
            Botania.proxy.wispFX(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, color[0], color[1], color[2], (float)Math.random() * 5.0f + 1.0f, (float)(Math.random() - 0.5), 10.0f * (float)Math.sqrt(256.0f / (256.0f - (float)this.field_145848_d)), (float)(Math.random() - 0.5));
            for (int i = 0; i < 2; ++i) {
                Botania.proxy.wispFX(this.field_145850_b, (double)this.field_145851_c + 0.5, 256.0, (double)this.field_145849_e + 0.5, color[0], color[1], color[2], (float)Math.random() * 15.0f + 8.0f, (float)(Math.random() - 0.5) * 8.0f, 0.0f, (float)(Math.random() - 0.5) * 8.0f);
            }
            Botania.proxy.setWispFXDistanceLimit(true);
        }
    }
}

