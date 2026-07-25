/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 */
package witchinggadgets.common.blocks.tiles;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import witchinggadgets.common.blocks.tiles.TileEntityWGBase;

public class TileEntityAgeingStone
extends TileEntityWGBase {
    public void func_145845_h() {
        AxisAlignedBB box = AxisAlignedBB.func_72330_a((double)(this.field_145851_c - 3), (double)(this.field_145848_d - 2), (double)(this.field_145849_e - 3), (double)(this.field_145851_c + 4), (double)(this.field_145848_d + 3), (double)(this.field_145849_e + 4));
        List hitEntities = this.field_145850_b.func_72872_a(Entity.class, box);
        for (int i = 0; i < hitEntities.size(); ++i) {
            Object ent = hitEntities.get(i);
            if (!(ent instanceof EntityAgeable)) continue;
            int age = ((EntityAgeable)ent).func_70874_b();
            if (age < 0) {
                ((EntityAgeable)ent).func_70873_a(++age);
                continue;
            }
            if (age <= 0) continue;
            ((EntityAgeable)ent).func_70873_a(--age);
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound tags) {
    }

    @Override
    public void writeCustomNBT(NBTTagCompound tags) {
    }
}

