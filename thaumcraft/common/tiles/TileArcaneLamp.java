/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.common.config.ConfigBlocks;

public class TileArcaneLamp
extends TileThaumcraft {
    public ForgeDirection facing = ForgeDirection.getOrientation((int)0);

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        if (!this.field_145850_b.field_72995_K) {
            int z;
            int x = this.field_145851_c + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
            int y = this.field_145848_d + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
            if (y > this.field_145850_b.func_72976_f(x, z = this.field_145849_e + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16)) + 4) {
                y = this.field_145850_b.func_72976_f(x, z) + 4;
            }
            if (y < 5) {
                y = 5;
            }
            if (this.field_145850_b.func_147437_c(x, y, z) && this.field_145850_b.func_147439_a(x, y, z) != ConfigBlocks.blockAiry && this.field_145850_b.func_72957_l(x, y, z) < 9) {
                this.field_145850_b.func_147465_d(x, y, z, ConfigBlocks.blockAiry, 3, 3);
            }
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.facing = ForgeDirection.getOrientation((int)nbttagcompound.func_74762_e("orientation"));
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74768_a("orientation", this.facing.ordinal());
    }

    public void removeLights() {
        for (int x = -15; x <= 15; ++x) {
            for (int y = -15; y <= 15; ++y) {
                for (int z = -15; z <= 15; ++z) {
                    if (this.field_145850_b.func_147439_a(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z) != ConfigBlocks.blockAiry || this.field_145850_b.func_72805_g(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z) != 3) continue;
                    this.field_145850_b.func_147468_f(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z);
                }
            }
        }
    }
}

