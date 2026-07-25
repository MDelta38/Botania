/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.common.tiles.TileJarFillable
 */
package flaxbeard.thaumicexploration.tile;

import flaxbeard.thaumicexploration.ThaumicExploration;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.tiles.TileJarFillable;

public class TileEntityTrashJar
extends TileJarFillable {
    int ticks = 0;

    public int getMinimumSuction() {
        return 8;
    }

    public Aspect getSuctionType(ForgeDirection loc) {
        return this.aspect;
    }

    public int getSuctionAmount(ForgeDirection loc) {
        if (this.amount < this.maxAmount) {
            return 8;
        }
        return 0;
    }

    public Aspect getEssentiaType(ForgeDirection loc) {
        return this.aspect;
    }

    public int getEssentiaAmount(ForgeDirection loc) {
        return this.amount;
    }

    public boolean takeFromContainer(Aspect tt, int am) {
        return false;
    }

    public void func_145845_h() {
        if (this.field_145850_b.field_72995_K) {
            for (int i = 0; i < 3; ++i) {
                double[] pos1 = new double[]{(double)this.field_145851_c + Math.random(), (double)this.field_145848_d + Math.random(), (double)this.field_145849_e + Math.random()};
                double[] pos2 = new double[]{pos1[0] + Math.random() / 2.0 - 0.25, pos1[1] + Math.random() / 2.0 - 0.25, pos1[2] + Math.random() / 2.0 - 0.25};
                ThaumicExploration.proxy.spawnLightningBolt(this.field_145850_b, pos1[0], pos1[1], pos1[2], (float)this.field_145851_c + 0.5f, (float)this.field_145848_d + 0.4f, (float)this.field_145849_e + 0.5f);
                ThaumicExploration.proxy.spawnLightningBolt(this.field_145850_b, pos2[0], pos2[1], pos2[2], pos1[0], pos1[1], pos1[2]);
            }
        }
        if (this.amount > 0) {
            ++this.ticks;
            if (this.ticks > 5) {
                --this.amount;
                this.ticks = 0;
            }
        }
        if (this.amount == 0) {
            this.aspect = null;
            this.ticks = 0;
        }
        super.func_145845_h();
    }
}

