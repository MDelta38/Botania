/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.common.block.tile.mana;

import vazkii.botania.api.mana.IClientManaHandler;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.tile.TileMod;

public class TileManaVoid
extends TileMod
implements IClientManaHandler {
    public boolean canUpdate() {
        return false;
    }

    @Override
    public int getCurrentMana() {
        return 0;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public void recieveMana(int mana) {
        if (mana > 0) {
            for (int i = 0; i < 10; ++i) {
                Botania.proxy.sparkleFX(this.func_145831_w(), (double)this.field_145851_c + Math.random(), (double)this.field_145848_d + Math.random(), (double)this.field_145849_e + Math.random(), 0.2f, 0.2f, 0.2f, 0.7f + 0.5f * (float)Math.random(), 5);
            }
        }
    }

    @Override
    public boolean canRecieveManaFromBursts() {
        return true;
    }
}

