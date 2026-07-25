/*
 * Decompiled with CFR 0.152.
 */
package thaumcraft.common.tiles;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.tiles.TileTube;

public class TileTubeRestrict
extends TileTube {
    @Override
    void calculateSuction(Aspect filter, boolean restrict, boolean dir) {
        super.calculateSuction(filter, true, dir);
    }
}

