/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.DamageSource
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockZap;

public class TileEldritchTrap
extends TileEntity {
    int count = 20;

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        super.func_145845_h();
        if (!this.field_145850_b.field_72995_K && this.count-- <= 0) {
            this.count = 10 + this.field_145850_b.field_73012_v.nextInt(25);
            EntityPlayer p = this.field_145850_b.func_72977_a((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, 3.0);
            if (p != null) {
                p.func_70097_a(DamageSource.field_76376_m, 2.0f);
                if (this.field_145850_b.field_73012_v.nextBoolean()) {
                    Thaumcraft.addWarpToPlayer(p, 1 + this.field_145850_b.field_73012_v.nextInt(2), true);
                }
                PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlockZap((float)this.field_145851_c + 0.5f, (float)this.field_145848_d + 0.5f, (float)this.field_145849_e + 0.5f, (float)p.field_70165_t, (float)p.field_70121_D.field_72338_b + p.eyeHeight, (float)p.field_70161_v), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, (double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, 32.0));
            }
        }
    }
}

