/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 */
package vazkii.botania.common.block.subtile.generating;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.core.helper.ExperienceHelper;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileArcaneRose
extends SubTileGenerating {
    private static final int RANGE = 1;

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.mana >= this.getMaxMana()) {
            return;
        }
        List players = this.supertile.func_145831_w().func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)(this.supertile.field_145851_c - 1), (double)this.supertile.field_145848_d, (double)(this.supertile.field_145849_e - 1), (double)(this.supertile.field_145851_c + 1 + 1), (double)(this.supertile.field_145848_d + 1), (double)(this.supertile.field_145849_e + 1 + 1)));
        for (EntityPlayer player : players) {
            if (ExperienceHelper.getPlayerXP(player) < 1 || !player.field_70122_E) continue;
            ExperienceHelper.drainPlayerXP(player, 1);
            this.mana += 50;
            return;
        }
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 1);
    }

    @Override
    public int getColor() {
        return 0xFF8EF8;
    }

    @Override
    public int getMaxMana() {
        return 6000;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.arcaneRose;
    }
}

