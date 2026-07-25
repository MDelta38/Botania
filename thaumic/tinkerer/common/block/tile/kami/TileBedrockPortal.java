/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 */
package thaumic.tinkerer.common.block.tile.kami;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import thaumic.tinkerer.common.core.handler.ConfigHandler;

public class TileBedrockPortal
extends TileEntity {
    public void func_145845_h() {
        for (Object e : this.field_145850_b.func_72872_a(Entity.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)))) {
            if (!(e instanceof Entity)) continue;
            ((Entity)e).func_71027_c(ConfigHandler.bedrockDimensionID);
        }
    }
}

