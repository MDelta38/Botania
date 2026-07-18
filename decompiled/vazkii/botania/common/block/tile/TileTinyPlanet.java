/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.common.block.tile;

import vazkii.botania.api.mana.IManaCollisionGhost;
import vazkii.botania.common.block.tile.TileMod;
import vazkii.botania.common.item.equipment.bauble.ItemTinyPlanet;

public class TileTinyPlanet
extends TileMod
implements IManaCollisionGhost {
    public void func_145845_h() {
        ItemTinyPlanet.applyEffect(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5);
    }

    @Override
    public boolean isGhost() {
        return true;
    }
}

