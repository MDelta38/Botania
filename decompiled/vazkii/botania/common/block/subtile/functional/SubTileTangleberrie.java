/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 */
package vazkii.botania.common.block.subtile.functional;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.MathHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileTangleberrie
extends SubTileFunctional {
    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.mana > 0) {
            double x1 = (double)this.supertile.field_145851_c + 0.5;
            double y1 = (double)this.supertile.field_145848_d + 0.5;
            double z1 = (double)this.supertile.field_145849_e + 0.5;
            double maxDist = this.getMaxDistance();
            double range = this.getRange();
            AxisAlignedBB boundingBox = AxisAlignedBB.func_72330_a((double)(x1 - range), (double)(y1 - range), (double)(z1 - range), (double)(x1 + range + 1.0), (double)(y1 + range + 1.0), (double)(z1 + range + 1.0));
            List entities = this.supertile.func_145831_w().func_72872_a(EntityLivingBase.class, boundingBox);
            for (EntityLivingBase entity : entities) {
                double z2;
                double y2;
                double x2;
                float distance;
                if (entity instanceof EntityPlayer || entity instanceof IBossDisplayData || !((double)(distance = MathHelper.pointDistanceSpace(x1, y1, z1, x2 = entity.field_70165_t, y2 = entity.field_70163_u, z2 = entity.field_70161_v)) > maxDist) || !((double)distance < range)) continue;
                MathHelper.setEntityMotionFromVector((Entity)entity, new Vector3(x1, y1, z1), this.getMotionVelocity());
                if (this.supertile.func_145831_w().field_73012_v.nextInt(3) != 0) continue;
                Botania.proxy.sparkleFX(this.supertile.func_145831_w(), x2 + Math.random() * (double)entity.field_70130_N, y2 + Math.random() * (double)entity.field_70131_O, z2 + Math.random() * (double)entity.field_70130_N, 0.5f, 0.5f, 0.5f, 1.0f, 3);
            }
            if (this.ticksExisted % 4 == 0) {
                --this.mana;
                this.sync();
            }
        }
    }

    double getMaxDistance() {
        return 6.0;
    }

    double getRange() {
        return 7.0;
    }

    float getMotionVelocity() {
        return 0.05f;
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Circle(this.toChunkCoordinates(), this.getRange());
    }

    @Override
    public int getColor() {
        return 4946300;
    }

    @Override
    public int getMaxMana() {
        return 20;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.tangleberrie;
    }
}

