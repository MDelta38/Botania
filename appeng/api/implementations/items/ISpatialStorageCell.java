/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package appeng.api.implementations.items;

import appeng.api.implementations.TransitionResult;
import appeng.api.util.WorldCoord;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ISpatialStorageCell {
    public boolean isSpatialStorage(ItemStack var1);

    public int getMaxStoredDim(ItemStack var1);

    public World getWorld(ItemStack var1);

    public WorldCoord getStoredSize(ItemStack var1);

    public WorldCoord getMin(ItemStack var1);

    public WorldCoord getMax(ItemStack var1);

    public TransitionResult doSpatialTransition(ItemStack var1, World var2, WorldCoord var3, WorldCoord var4, boolean var5);
}

