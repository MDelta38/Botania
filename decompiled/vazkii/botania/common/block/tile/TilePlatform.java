/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.block.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.mana.IManaCollisionGhost;
import vazkii.botania.common.block.tile.TileCamo;

public class TilePlatform
extends TileCamo
implements IManaCollisionGhost {
    @Override
    public boolean isGhost() {
        return true;
    }

    public boolean onWanded(EntityPlayer player) {
        if (player != null) {
            if (this.camo == null || player.func_70093_af()) {
                this.swapSelfAndPass(this, true);
            } else {
                this.swapSurroudings(this, false);
            }
            return true;
        }
        return false;
    }

    void swapSelfAndPass(TilePlatform tile, boolean empty) {
        this.swap(tile, empty);
        this.swapSurroudings(tile, empty);
    }

    void swapSurroudings(TilePlatform tile, boolean empty) {
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            int x = tile.field_145851_c + dir.offsetX;
            int y = tile.field_145848_d + dir.offsetY;
            int z = tile.field_145849_e + dir.offsetZ;
            TileEntity tileAt = this.field_145850_b.func_147438_o(x, y, z);
            if (tileAt == null || !(tileAt instanceof TilePlatform)) continue;
            TilePlatform platform = (TilePlatform)tileAt;
            if (!(empty ? platform.camo != null : platform.camo == null)) continue;
            this.swapSelfAndPass(platform, empty);
        }
    }

    void swap(TilePlatform tile, boolean empty) {
        tile.camo = empty ? null : this.camo;
        tile.camoMeta = empty ? 0 : this.camoMeta;
        this.field_145850_b.func_147471_g(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
    }
}

