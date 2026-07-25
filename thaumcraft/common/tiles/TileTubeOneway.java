/*
 * Decompiled with CFR 0.152.
 */
package thaumcraft.common.tiles;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.tiles.TileTube;

public class TileTubeOneway
extends TileTube {
    @Override
    void calculateSuction(Aspect filter, boolean restrict, boolean directional) {
        super.calculateSuction(filter, restrict, true);
    }

    @Override
    void equalizeWithNeighbours(boolean directional) {
        super.equalizeWithNeighbours(true);
    }
}

