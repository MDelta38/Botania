/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.lib.vec.Cuboid6
 *  codechicken.multipart.IRandomDisplayTick
 *  codechicken.multipart.minecraft.McMetaPart
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemStack
 *  thaumcraft.common.config.ConfigBlocks
 */
package thaumic.tinkerer.common.multipart;

import codechicken.lib.vec.Cuboid6;
import codechicken.multipart.IRandomDisplayTick;
import codechicken.multipart.minecraft.McMetaPart;
import java.util.Arrays;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import thaumcraft.common.config.ConfigBlocks;

public class PartCandle
extends McMetaPart
implements IRandomDisplayTick {
    public PartCandle(int meta) {
        super(meta);
    }

    public PartCandle() {
    }

    public Cuboid6 getBounds() {
        return new Cuboid6(0.375, 0.0, 0.375, 0.625, 0.5, 0.625);
    }

    public void randomDisplayTick(Random arg0) {
        this.getBlock().func_149734_b(this.world(), this.x(), this.y(), this.z(), arg0);
    }

    public Block getBlock() {
        return ConfigBlocks.blockCandle;
    }

    public Iterable<ItemStack> getDrops() {
        return Arrays.asList(new ItemStack(this.getBlock(), 1, (int)this.meta));
    }

    public String getType() {
        return this.getBlock().func_149739_a();
    }
}

