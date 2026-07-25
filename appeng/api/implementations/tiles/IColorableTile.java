/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.common.util.ForgeDirection
 */
package appeng.api.implementations.tiles;

import appeng.api.util.AEColor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.util.ForgeDirection;

public interface IColorableTile {
    public AEColor getColor();

    public boolean recolourBlock(ForgeDirection var1, AEColor var2, EntityPlayer var3);
}

