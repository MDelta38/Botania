/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 */
package thaumcraft.common.tiles;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;

public class TileLifter
extends TileEntity {
    private int counter = 0;
    public int rangeAbove = 0;
    public boolean requiresUpdate = true;
    public boolean lastPowerState = false;

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        List targets;
        super.func_145845_h();
        ++this.counter;
        if (this.requiresUpdate || this.counter % 100 == 0) {
            this.lastPowerState = this.gettingPower();
            this.requiresUpdate = false;
            int max = 10;
            int count = 1;
            while (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - count, this.field_145849_e) == ConfigBlocks.blockLifter && !this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d - count, this.field_145849_e)) {
                ++count;
                max += 10;
            }
            this.rangeAbove = 0;
            while (this.rangeAbove < max && !this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1 + this.rangeAbove, this.field_145849_e).func_149662_c()) {
                ++this.rangeAbove;
            }
        }
        if (this.rangeAbove > 0 && !this.gettingPower() && (targets = this.field_145850_b.func_72872_a(Entity.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)(this.field_145848_d + 1), (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1 + this.rangeAbove), (double)(this.field_145849_e + 1)))).size() > 0) {
            for (Entity e : targets) {
                if (!(e instanceof EntityItem) && !e.func_70104_M() && !(e instanceof EntityHorse)) continue;
                if (Thaumcraft.proxy.isShiftKeyDown()) {
                    if (e.field_70181_x < 0.0) {
                        e.field_70181_x *= (double)0.9f;
                    }
                } else if (e.field_70181_x < (double)0.35f) {
                    e.field_70181_x += (double)0.1f;
                }
                e.field_70143_R = 0.0f;
            }
        }
    }

    public boolean gettingPower() {
        return this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e) || this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
    }
}

