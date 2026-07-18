/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.common.block.tile.string;

import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.common.block.tile.string.TileRedString;

public class TileRedStringComparator
extends TileRedString {
    int comparatorValue = 0;

    @Override
    public void func_145845_h() {
        int val;
        super.func_145845_h();
        ChunkCoordinates binding = this.getBinding();
        ForgeDirection dir = this.getOrientation();
        Block block = this.getBlockAtBinding();
        int origVal = this.comparatorValue;
        this.comparatorValue = block.func_149740_M() ? (val = block.func_149736_g(this.field_145850_b, binding.field_71574_a, binding.field_71572_b, binding.field_71573_c, dir.getOpposite().ordinal())) : 0;
        if (origVal != this.comparatorValue) {
            this.field_145850_b.func_147453_f(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e));
        }
    }

    public int getComparatorValue() {
        return this.comparatorValue;
    }

    @Override
    public boolean acceptBlock(int x, int y, int z) {
        Block block = this.field_145850_b.func_147439_a(x, y, z);
        return block.func_149740_M();
    }
}

