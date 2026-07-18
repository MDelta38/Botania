/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.common.block.subtile.functional;

import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.subtile.functional.SubTileTangleberrie;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileJiyuulia
extends SubTileTangleberrie {
    @Override
    double getMaxDistance() {
        return 0.0;
    }

    @Override
    double getRange() {
        return 8.0;
    }

    @Override
    float getMotionVelocity() {
        return -super.getMotionVelocity() * 2.0f;
    }

    @Override
    public int getColor() {
        return 12425930;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.jiyuulia;
    }
}

