/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package thaumcraft.api.wands;

import java.util.ArrayList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.BlockCoordinates;

public interface IFocusArchitect {
    public ArrayList<BlockCoordinates> getArchitectBlocks(ItemStack var1, World var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9, EntityPlayer var10);

    public boolean is3D(ItemStack var1, World var2, EntityPlayer var3);
}

