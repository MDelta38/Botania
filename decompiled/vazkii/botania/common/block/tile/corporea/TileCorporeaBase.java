/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.common.block.tile.corporea;

import vazkii.botania.api.corporea.CorporeaHelper;
import vazkii.botania.api.corporea.ICorporeaSpark;
import vazkii.botania.common.block.tile.TileSimpleInventory;

public abstract class TileCorporeaBase
extends TileSimpleInventory {
    public ICorporeaSpark getSpark() {
        return CorporeaHelper.getSparkForBlock(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }
}

