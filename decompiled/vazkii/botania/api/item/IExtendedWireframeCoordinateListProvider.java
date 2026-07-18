/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChunkCoordinates
 */
package vazkii.botania.api.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import vazkii.botania.api.item.IWireframeCoordinateListProvider;

public interface IExtendedWireframeCoordinateListProvider
extends IWireframeCoordinateListProvider {
    public ChunkCoordinates getSourceWireframe(EntityPlayer var1, ItemStack var2);
}

