/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package appeng.api.parts;

import appeng.api.parts.CableRenderMode;
import appeng.api.parts.IPartItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IPartHelper {
    public boolean registerNewLayer(String var1, String var2);

    public void setItemBusRenderer(IPartItem var1);

    public boolean placeBus(ItemStack var1, int var2, int var3, int var4, int var5, EntityPlayer var6, World var7);

    public CableRenderMode getCableRenderMode();
}

