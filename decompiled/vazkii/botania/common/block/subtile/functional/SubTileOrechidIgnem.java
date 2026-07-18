/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 */
package vazkii.botania.common.block.subtile.functional;

import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.subtile.functional.SubTileOrechid;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileOrechidIgnem
extends SubTileOrechid {
    private static final int COST = 20000;

    @Override
    public boolean canOperate() {
        return this.supertile.func_145831_w().field_73011_w.field_76575_d;
    }

    @Override
    public Map<String, Integer> getOreMap() {
        return BotaniaAPI.oreWeightsNether;
    }

    @Override
    public Block getSourceBlock() {
        return Blocks.field_150424_aL;
    }

    @Override
    public int getCost() {
        return 20000;
    }

    @Override
    public int getColor() {
        return 11415600;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.orechidIgnem;
    }
}

