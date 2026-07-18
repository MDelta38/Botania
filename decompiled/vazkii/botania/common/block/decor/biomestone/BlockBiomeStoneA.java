/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.common.block.decor.biomestone;

import vazkii.botania.common.block.decor.biomestone.BlockBiomeStone;

public class BlockBiomeStoneA
extends BlockBiomeStone {
    public BlockBiomeStoneA() {
        super(0, "biomeStoneA");
    }

    @Override
    public int func_149692_a(int par1) {
        return par1 % 8 + 8;
    }
}

