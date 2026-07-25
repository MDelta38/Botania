/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.common.util.ForgeDirection
 */
package appeng.api.networking;

import appeng.api.networking.GridFlags;
import appeng.api.networking.GridNotification;
import appeng.api.networking.IGrid;
import appeng.api.networking.IGridHost;
import appeng.api.util.AEColor;
import appeng.api.util.DimensionalCoord;
import java.util.EnumSet;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

public interface IGridBlock {
    public double getIdlePowerUsage();

    public EnumSet<GridFlags> getFlags();

    public boolean isWorldAccessible();

    public DimensionalCoord getLocation();

    public AEColor getGridColor();

    public void onGridNotification(GridNotification var1);

    public void setNetworkStatus(IGrid var1, int var2);

    public EnumSet<ForgeDirection> getConnectableSides();

    public IGridHost getMachine();

    public void gridChanged();

    public ItemStack getMachineRepresentation();
}

