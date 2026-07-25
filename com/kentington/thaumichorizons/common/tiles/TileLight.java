/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 */
package com.kentington.thaumichorizons.common.tiles;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.items.ItemFocusIllumination;
import java.awt.Color;
import net.minecraft.tileentity.TileEntity;

public class TileLight
extends TileEntity {
    int md = -1;
    Color col = null;

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        super.func_145845_h();
        if (this.field_145850_b.field_72995_K) {
            if (this.md == -1) {
                this.md = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.col = new Color(ItemFocusIllumination.colors[this.md]);
            }
            ThaumicHorizons.proxy.illuminationFX(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.md, this.col);
        }
    }
}

