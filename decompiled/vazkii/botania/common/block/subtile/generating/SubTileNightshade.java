/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.common.block.subtile.generating;

import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.signature.PassiveFlower;
import vazkii.botania.common.block.subtile.generating.SubTileDaybloom;
import vazkii.botania.common.lexicon.LexiconData;

@PassiveFlower
public class SubTileNightshade
extends SubTileDaybloom {
    @Override
    public int getDelayBetweenPassiveGeneration() {
        return super.getDelayBetweenPassiveGeneration();
    }

    @Override
    public boolean canGeneratePassively() {
        return !super.canGeneratePassively() && !this.supertile.func_145831_w().func_72935_r();
    }

    @Override
    public int getColor() {
        return 4008592;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.nightshade;
    }

    public static class Prime
    extends SubTileNightshade {
        @Override
        public boolean isPrime() {
            return true;
        }

        @Override
        public LexiconEntry getEntry() {
            return LexiconData.primusLoci;
        }
    }
}

