/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 */
package thaumic.tinkerer.common.core.helper;

import net.minecraft.block.Block;

public class BlockTuple {
    public Block block;
    public int meta;

    public BlockTuple(Block b, int meta) {
        this.block = b;
        this.meta = meta;
    }

    public BlockTuple(Block b) {
        this.block = b;
        this.meta = 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BlockTuple)) {
            return false;
        }
        return ((BlockTuple)obj).block == this.block && ((BlockTuple)obj).meta == this.meta;
    }

    public int hashCode() {
        return this.block.hashCode() + this.meta;
    }
}

