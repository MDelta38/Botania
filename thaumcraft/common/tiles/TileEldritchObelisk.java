/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.entities.IEldritchMob;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.utils.EntityUtils;

public class TileEldritchObelisk
extends TileThaumcraft {
    private int counter = 0;

    public boolean canUpdate() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 5), (double)(this.field_145849_e + 1));
    }

    public double func_145833_n() {
        return 9216.0;
    }

    public void func_145845_h() {
        ArrayList<Entity> list;
        if (!this.field_145850_b.field_72995_K && this.counter % 20 == 0 && (list = EntityUtils.getEntitiesInRange(this.func_145831_w(), (double)this.field_145851_c + 0.5, this.field_145848_d, (double)this.field_145849_e + 0.5, null, EntityLivingBase.class, 6.0)) != null && list.size() > 0) {
            for (Entity e : list) {
                if (!(e instanceof IEldritchMob) || !(e instanceof EntityLivingBase) || ((EntityLivingBase)e).func_82165_m(Potion.field_76428_l.field_76415_H)) continue;
                try {
                    ((EntityLivingBase)e).func_70690_d(new PotionEffect(Potion.field_76420_g.func_76396_c(), 40, 0, true));
                    ((EntityLivingBase)e).func_70690_d(new PotionEffect(Potion.field_76428_l.func_76396_c(), 40, 0, true));
                }
                catch (Exception e1) {}
            }
        }
        if (this.field_145850_b.field_72995_K && (list = EntityUtils.getEntitiesInRange(this.func_145831_w(), (double)this.field_145851_c + 0.5, this.field_145848_d, (double)this.field_145849_e + 0.5, null, EntityLivingBase.class, 6.0)) != null && list.size() > 0) {
            for (Entity e : list) {
                if (!(e instanceof IEldritchMob) || !(e instanceof EntityLivingBase)) continue;
                Thaumcraft.proxy.wispFX4(this.func_145831_w(), (double)this.field_145851_c + 0.5, (float)(this.field_145848_d + 1) + this.field_145850_b.field_73012_v.nextFloat() * 3.0f, (double)this.field_145849_e + 0.5, e, 5, true, 1.0f);
            }
        }
    }
}

