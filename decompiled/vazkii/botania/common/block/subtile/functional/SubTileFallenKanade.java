/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 */
package vazkii.botania.common.block.subtile.functional;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileFallenKanade
extends SubTileFunctional {
    private static final int RANGE = 2;

    @Override
    public void onUpdate() {
        super.onUpdate();
        int cost = 120;
        if (!this.supertile.func_145831_w().field_72995_K && this.supertile.func_145831_w().field_73011_w.field_76574_g != 1) {
            List players = this.supertile.func_145831_w().func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)(this.supertile.field_145851_c - 2), (double)(this.supertile.field_145848_d - 2), (double)(this.supertile.field_145849_e - 2), (double)(this.supertile.field_145851_c + 2 + 1), (double)(this.supertile.field_145848_d + 2 + 1), (double)(this.supertile.field_145849_e + 2 + 1)));
            for (EntityPlayer player : players) {
                if (player.func_70660_b(Potion.field_76428_l) != null || this.mana < 120) continue;
                player.func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 60, 2));
                this.mana -= 120;
            }
        }
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 2);
    }

    @Override
    public int getColor() {
        return 0xFFFF00;
    }

    @Override
    public int getMaxMana() {
        return 900;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.fallenKanade;
    }
}

