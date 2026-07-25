/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  thaumcraft.common.tiles.TileArcaneLampLight
 */
package witchinggadgets.common.blocks.tiles;

import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.common.tiles.TileArcaneLampLight;

public class TileEntityTempLight
extends TileArcaneLampLight {
    int tick = 0;
    public int tickMax = 400;

    public void func_145845_h() {
        if (!this.field_145850_b.field_72995_K) {
            if (this.tick < this.tickMax) {
                ++this.tick;
            } else {
                this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }
    }

    public void readCustomNBT(NBTTagCompound tag) {
        this.tick = tag.func_74762_e("tickCount");
    }

    public void writeCustomNBT(NBTTagCompound tag) {
        tag.func_74768_a("tickCount", this.tick);
    }
}

