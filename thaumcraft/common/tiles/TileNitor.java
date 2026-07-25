/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 */
package thaumcraft.common.tiles;

import net.minecraft.tileentity.TileEntity;
import thaumcraft.common.Thaumcraft;

public class TileNitor
extends TileEntity {
    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        super.func_145845_h();
        if (this.field_145850_b.field_72995_K) {
            if (this.field_145850_b.field_73012_v.nextInt(9 - Thaumcraft.proxy.particleCount(2)) == 0) {
                Thaumcraft.proxy.wispFX3(this.field_145850_b, (float)this.field_145851_c + 0.5f, (float)this.field_145848_d + 0.5f, (float)this.field_145849_e + 0.5f, (float)this.field_145851_c + 0.3f + this.field_145850_b.field_73012_v.nextFloat() * 0.4f, (float)this.field_145848_d + 0.5f, (float)this.field_145849_e + 0.3f + this.field_145850_b.field_73012_v.nextFloat() * 0.4f, 0.5f, 4, true, -0.025f);
            }
            if (this.field_145850_b.field_73012_v.nextInt(15 - Thaumcraft.proxy.particleCount(4)) == 0) {
                Thaumcraft.proxy.wispFX3(this.field_145850_b, (float)this.field_145851_c + 0.5f, (float)this.field_145848_d + 0.5f, (float)this.field_145849_e + 0.5f, (float)this.field_145851_c + 0.4f + this.field_145850_b.field_73012_v.nextFloat() * 0.2f, (float)this.field_145848_d + 0.5f, (float)this.field_145849_e + 0.4f + this.field_145850_b.field_73012_v.nextFloat() * 0.2f, 0.25f, 1, true, -0.02f);
            }
        }
    }
}

