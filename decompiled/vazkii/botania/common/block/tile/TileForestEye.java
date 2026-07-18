/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.util.AxisAlignedBB
 */
package vazkii.botania.common.block.tile;

import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.common.block.tile.TileMod;

public class TileForestEye
extends TileMod {
    public int entities = 0;

    public void func_145845_h() {
        int range = 6;
        int entityCount = this.field_145850_b.func_72872_a(EntityAnimal.class, AxisAlignedBB.func_72330_a((double)(this.field_145851_c - range), (double)(this.field_145848_d - range), (double)(this.field_145849_e - range), (double)(this.field_145851_c + range + 1), (double)(this.field_145848_d + range + 1), (double)(this.field_145849_e + range + 1))).size();
        if (entityCount != this.entities) {
            this.entities = entityCount;
            this.field_145850_b.func_147453_f(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e));
        }
    }
}

