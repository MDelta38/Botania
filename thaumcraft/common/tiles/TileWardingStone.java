/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 */
package thaumcraft.common.tiles;

import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import thaumcraft.common.config.ConfigBlocks;

public class TileWardingStone
extends TileEntity {
    int count = 0;

    public boolean gettingPower() {
        return this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        if (!this.field_145850_b.field_72995_K) {
            List targets;
            if (this.count == 0) {
                this.count = this.field_145850_b.field_73012_v.nextInt(100);
            }
            if (this.count % 5 == 0 && !this.gettingPower() && (targets = this.field_145850_b.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 3), (double)(this.field_145849_e + 1)).func_72314_b(0.1, 0.1, 0.1))).size() > 0) {
                for (EntityLivingBase e : targets) {
                    if (e.field_70122_E || e instanceof EntityPlayer) continue;
                    e.func_70024_g((double)(-MathHelper.func_76126_a((float)((e.field_70177_z + 180.0f) * (float)Math.PI / 180.0f)) * 0.2f), -0.1, (double)(MathHelper.func_76134_b((float)((e.field_70177_z + 180.0f) * (float)Math.PI / 180.0f)) * 0.2f));
                }
            }
            if (++this.count % 100 == 0) {
                if ((this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) != ConfigBlocks.blockAiry || this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) != 3) && this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e).isReplaceable((IBlockAccess)this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e)) {
                    this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, ConfigBlocks.blockAiry, 4, 3);
                }
                if ((this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 2, this.field_145849_e) != ConfigBlocks.blockAiry || this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d + 2, this.field_145849_e) != 3) && this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 2, this.field_145849_e).isReplaceable((IBlockAccess)this.field_145850_b, this.field_145851_c, this.field_145848_d + 2, this.field_145849_e)) {
                    this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + 2, this.field_145849_e, ConfigBlocks.blockAiry, 4, 3);
                }
            }
        }
    }
}

