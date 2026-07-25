/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 */
package witchinggadgets.common.blocks.tiles;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityVoidWalkway
extends TileEntity {
    public void func_145845_h() {
        double minX = (double)this.field_145851_c - 2.5;
        double maxX = (double)this.field_145851_c + 2.5;
        double minZ = (double)this.field_145849_e - 2.5;
        double maxZ = (double)this.field_145849_e + 2.5;
        double minY = this.field_145848_d;
        double maxY = (double)this.field_145848_d + 1.5;
        AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)minX, (double)minY, (double)minZ, (double)maxX, (double)maxY, (double)maxZ);
        List list = this.field_145850_b.func_72872_a(EntityPlayer.class, aabb);
        boolean flag = false;
        for (EntityPlayer p : list) {
            if (!p.func_70093_af()) continue;
            flag = true;
        }
        if (list.isEmpty() || flag) {
            this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }
}

