/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.common.util.ForgeDirection
 */
package appeng.api.implementations.parts;

import appeng.api.networking.IGridHost;
import appeng.api.parts.BusSupport;
import appeng.api.parts.IPart;
import appeng.api.util.AECableType;
import appeng.api.util.AEColor;
import java.util.EnumSet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.util.ForgeDirection;

public interface IPartCable
extends IPart,
IGridHost {
    public BusSupport supportsBuses();

    public AEColor getCableColor();

    public AECableType getCableConnectionType();

    public boolean changeColor(AEColor var1, EntityPlayer var2);

    public void setValidSides(EnumSet<ForgeDirection> var1);

    public boolean isConnected(ForgeDirection var1);
}

