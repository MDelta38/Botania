/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 */
package witchinggadgets.common.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.tileentity.TileEntity;
import witchinggadgets.common.blocks.tiles.TileEntityEtherealWall;

public class EtherealWallMaster {
    public List<TileEntityEtherealWall> tileMap = new ArrayList<TileEntityEtherealWall>();

    public boolean isAnyTileInNetPowered() {
        for (TileEntityEtherealWall tile : this.tileMap) {
            if (!tile.func_145831_w().func_72864_z(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e)) continue;
            return true;
        }
        return false;
    }

    public boolean addTileToNet(TileEntityEtherealWall tile) {
        if (this.tileMap.contains((Object)tile)) {
            return false;
        }
        this.tileMap.add(tile);
        tile.master = this;
        return true;
    }

    public boolean removeTileFromNet(TileEntityEtherealWall tile) {
        this.tileMap.remove((Object)tile);
        tile.master = null;
        return true;
    }

    public void freeSlaves() {
        for (TileEntityEtherealWall tile : this.tileMap) {
            tile.master = null;
        }
        this.tileMap = new ArrayList<TileEntityEtherealWall>();
    }

    public void checkNetIntegrity(TileEntityEtherealWall tile) {
    }

    public TileEntityEtherealWall[] sortTilesByDistanceTo(int x, int y, int z) {
        TileEntityEtherealWall[] result = new TileEntityEtherealWall[this.tileMap.size()];
        int counter = 0;
        Iterator<TileEntityEtherealWall> i$ = this.tileMap.iterator();
        while (i$.hasNext()) {
            TileEntityEtherealWall tile;
            result[counter] = tile = i$.next();
            ++counter;
        }
        return result;
    }

    private boolean areTilesAdjacent(TileEntity par1, TileEntity par2) {
        boolean sameZ;
        boolean sameX = par1.field_145851_c == par2.field_145851_c;
        boolean sameY = par1.field_145848_d == par2.field_145848_d;
        boolean bl = sameZ = par1.field_145849_e == par2.field_145849_e;
        if (sameX && sameY) {
            return Math.abs(par1.field_145849_e - par2.field_145849_e) == 1;
        }
        if (sameZ && sameY) {
            return Math.abs(par1.field_145851_c - par2.field_145851_c) == 1;
        }
        if (sameX && sameZ) {
            return Math.abs(par1.field_145848_d - par2.field_145848_d) == 1;
        }
        return false;
    }

    public void integrateOtherNet(EtherealWallMaster net) {
        this.tileMap.addAll(net.tileMap);
    }
}

