/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 */
package drunkmafia.thaumicinfusion.common.util;

import net.minecraft.block.Block;

public interface IBlockHook {
    public int[] hookMethods(Block var1);

    public Block getBlock(int var1);

    public boolean shouldOverride(int var1);
}

