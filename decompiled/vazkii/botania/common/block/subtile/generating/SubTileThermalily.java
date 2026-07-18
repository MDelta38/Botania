/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 */
package vazkii.botania.common.block.subtile.generating;

import net.minecraft.block.material.Material;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.subtile.generating.SubTileHydroangeas;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileThermalily
extends SubTileHydroangeas {
    @Override
    public int getColor() {
        return 13646848;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.thermalily;
    }

    @Override
    public void doBurnParticles() {
        Botania.proxy.wispFX(this.supertile.func_145831_w(), (double)this.supertile.field_145851_c + 0.55 + Math.random() * 0.2 - 0.1, (double)this.supertile.field_145848_d + 0.9 + Math.random() * 0.2 - 0.1, (double)this.supertile.field_145849_e + 0.5, 0.7f, 0.05f, 0.05f, (float)Math.random() / 6.0f, (float)(-Math.random()) / 60.0f);
    }

    @Override
    public boolean isPassiveFlower() {
        return false;
    }

    @Override
    public Material getMaterialToSearchFor() {
        return Material.field_151587_i;
    }

    @Override
    public void playSound() {
        this.supertile.func_145831_w().func_72908_a((double)this.supertile.field_145851_c, (double)this.supertile.field_145848_d, (double)this.supertile.field_145849_e, "botania:thermalily", 0.2f, 1.0f);
    }

    @Override
    public int getDelayBetweenPassiveGeneration() {
        return 1;
    }

    @Override
    public int getBurnTime() {
        return 900;
    }

    @Override
    public int getValueForPassiveGeneration() {
        return 20;
    }

    @Override
    public int getMaxMana() {
        return 500;
    }

    @Override
    public int getCooldown() {
        return 6000;
    }
}

