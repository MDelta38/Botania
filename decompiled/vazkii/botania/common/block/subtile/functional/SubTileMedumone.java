/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 */
package vazkii.botania.common.block.subtile.functional;

import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileMedumone
extends SubTileFunctional {
    private static final int RANGE = 6;

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.mana > 0) {
            List entities = this.supertile.func_145831_w().func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a((double)(this.supertile.field_145851_c - 6), (double)this.supertile.field_145848_d, (double)(this.supertile.field_145849_e - 6), (double)(this.supertile.field_145851_c + 6 + 1), (double)(this.supertile.field_145848_d + 1), (double)(this.supertile.field_145849_e + 6 + 1)));
            for (EntityLivingBase entity : entities) {
                if (entity instanceof EntityPlayer) continue;
                entity.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 2, 100));
                --this.mana;
                if (this.mana != 0) continue;
                return;
            }
        }
    }

    @Override
    public boolean acceptsRedstone() {
        return true;
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 6);
    }

    @Override
    public int getColor() {
        return 4006404;
    }

    @Override
    public int getMaxMana() {
        return 4000;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.medumone;
    }
}

