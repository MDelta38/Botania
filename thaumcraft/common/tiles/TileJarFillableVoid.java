/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.tiles.TileJarFillable;

public class TileJarFillableVoid
extends TileJarFillable {
    int count = 0;

    @Override
    public int addToContainer(Aspect tt, int am) {
        boolean up;
        boolean bl = up = this.amount < this.maxAmount;
        if (am == 0) {
            return am;
        }
        if (tt == this.aspect || this.amount == 0) {
            this.aspect = tt;
            this.amount += am;
            am = 0;
            if (this.amount > this.maxAmount) {
                this.amount = this.maxAmount;
            }
        }
        if (up) {
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
        }
        return am;
    }

    @Override
    public int getMinimumSuction() {
        return this.aspectFilter != null ? 48 : 32;
    }

    @Override
    public int getSuctionAmount(ForgeDirection loc) {
        if (this.aspectFilter != null && this.amount < this.maxAmount) {
            return 48;
        }
        return 32;
    }

    @Override
    public void func_145845_h() {
        if (!this.field_145850_b.field_72995_K && ++this.count % 5 == 0) {
            this.fillJar();
        }
    }
}

