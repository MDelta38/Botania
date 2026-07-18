/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.common.block;

import vazkii.botania.api.lexicon.multiblock.MultiblockSet;
import vazkii.botania.common.block.tile.TileAlfPortal;
import vazkii.botania.common.block.tile.TileEnchanter;
import vazkii.botania.common.block.tile.TileTerraPlate;
import vazkii.botania.common.entity.EntityDoppleganger;

public final class ModMultiblocks {
    public static MultiblockSet enchanter;
    public static MultiblockSet alfPortal;
    public static MultiblockSet terrasteelPlate;
    public static MultiblockSet gaiaRitual;

    public static void init() {
        enchanter = TileEnchanter.makeMultiblockSet();
        alfPortal = TileAlfPortal.makeMultiblockSet();
        terrasteelPlate = TileTerraPlate.makeMultiblockSet();
        gaiaRitual = EntityDoppleganger.makeMultiblockSet();
    }
}

