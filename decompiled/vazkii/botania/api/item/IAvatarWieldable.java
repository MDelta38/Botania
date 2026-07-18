/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 */
package vazkii.botania.api.item;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import vazkii.botania.api.item.IAvatarTile;

public interface IAvatarWieldable {
    public void onAvatarUpdate(IAvatarTile var1, ItemStack var2);

    public ResourceLocation getOverlayResource(IAvatarTile var1, ItemStack var2);
}

