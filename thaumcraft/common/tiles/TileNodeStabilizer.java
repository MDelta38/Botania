/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.common.config.ConfigBlocks;

public class TileNodeStabilizer
extends TileEntity {
    public int count = 0;
    public int lock = 0;

    public TileNodeStabilizer(int metadata) {
        this.lock = metadata == 9 ? 1 : 2;
    }

    public TileNodeStabilizer() {
    }

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        super.func_145845_h();
        if (this.field_145850_b.field_72995_K && this.field_145848_d < this.field_145850_b.field_73011_w.getHeight() - 1) {
            int md = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
            if (!(this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) != ConfigBlocks.blockAiry || md != 0 && md != 5 || this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e))) {
                if (this.count < 37) {
                    ++this.count;
                }
            } else if (this.count > 0) {
                --this.count;
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 2), (double)(this.field_145849_e + 1));
    }
}

