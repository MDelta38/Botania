/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.block.subtile;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.common.Botania;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibMisc;

public class SubTileManastar
extends SubTileEntity {
    int manaLastTick = -1;

    @Override
    public void onUpdate() {
        super.onUpdate();
        int mana = 0;
        for (ForgeDirection dir : LibMisc.CARDINAL_DIRECTIONS) {
            TileEntity tile = this.supertile.func_145831_w().func_147438_o(this.supertile.field_145851_c + dir.offsetX, this.supertile.field_145848_d, this.supertile.field_145849_e + dir.offsetZ);
            if (!(tile instanceof IManaPool)) continue;
            mana += ((IManaPool)tile).getCurrentMana();
        }
        if (this.manaLastTick != -1 && mana != this.manaLastTick && Math.random() > 0.6) {
            boolean more = mana > this.manaLastTick;
            Botania.proxy.wispFX(this.supertile.func_145831_w(), (double)this.supertile.field_145851_c + 0.55 + Math.random() * 0.2 - 0.1, (double)this.supertile.field_145848_d + 0.75 + Math.random() * 0.2 - 0.1, (double)this.supertile.field_145849_e + 0.5, more ? 0.05f : 1.0f, 0.05f, more ? 1.0f : 0.05f, (float)Math.random() / 7.0f, (float)(-Math.random()) / 50.0f);
        }
        if (this.ticksExisted % 60 == 0) {
            this.manaLastTick = mana;
        }
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.manastar;
    }
}

