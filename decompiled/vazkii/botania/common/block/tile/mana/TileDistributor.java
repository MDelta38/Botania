/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.block.tile.mana;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.mana.IManaReceiver;
import vazkii.botania.common.block.tile.TileMod;
import vazkii.botania.common.lib.LibMisc;

public class TileDistributor
extends TileMod
implements IManaReceiver {
    List<IManaReceiver> validPools = new ArrayList<IManaReceiver>();

    public void func_145845_h() {
        this.validPools.clear();
        for (ForgeDirection dir : LibMisc.CARDINAL_DIRECTIONS) {
            IManaReceiver receiver;
            TileEntity tileAt = this.field_145850_b.func_147438_o(this.field_145851_c + dir.offsetX, this.field_145848_d, this.field_145849_e + dir.offsetZ);
            if (tileAt == null || !(tileAt instanceof IManaPool) || (receiver = (IManaReceiver)tileAt).isFull()) continue;
            this.validPools.add(receiver);
        }
    }

    @Override
    public int getCurrentMana() {
        return 0;
    }

    @Override
    public boolean isFull() {
        return this.validPools.isEmpty();
    }

    @Override
    public void recieveMana(int mana) {
        int tiles = this.validPools.size();
        if (tiles != 0) {
            int manaForEach = mana / tiles;
            for (IManaReceiver pool : this.validPools) {
                pool.recieveMana(manaForEach);
                TileEntity tile = (TileEntity)pool;
                VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this.field_145850_b, tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
            }
        }
    }

    @Override
    public boolean canRecieveManaFromBursts() {
        return !this.isFull();
    }
}

