/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 */
package vazkii.botania.common.block.subtile.functional;

import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileHyacidus
extends SubTileFunctional {
    private static final int RANGE = 6;

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.redstoneSignal > 0) {
            return;
        }
        int cost = 20;
        List entities = this.supertile.func_145831_w().func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a((double)(this.supertile.field_145851_c - 6), (double)(this.supertile.field_145848_d - 6), (double)(this.supertile.field_145849_e - 6), (double)(this.supertile.field_145851_c + 6 + 1), (double)(this.supertile.field_145848_d + 6 + 1), (double)(this.supertile.field_145849_e + 6 + 1)));
        for (EntityLivingBase entity : entities) {
            if (entity instanceof EntityPlayer || entity.func_70660_b(Potion.field_76436_u) != null || this.mana < 20 || entity.field_70170_p.field_72995_K || entity.func_70668_bt() == EnumCreatureAttribute.UNDEAD) continue;
            entity.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, 60, 0));
            this.mana -= 20;
        }
    }

    @Override
    public boolean acceptsRedstone() {
        return true;
    }

    @Override
    public int getColor() {
        return 9126799;
    }

    @Override
    public int getMaxMana() {
        return 180;
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 6);
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.hyacidus;
    }
}

