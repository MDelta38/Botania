/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package appeng.api.networking;

import appeng.api.networking.GridFlags;
import appeng.api.networking.IGrid;
import appeng.api.networking.IGridBlock;
import appeng.api.networking.IGridConnection;
import appeng.api.networking.IGridHost;
import appeng.api.networking.IGridVisitor;
import appeng.api.util.IReadOnlyCollection;
import java.util.EnumSet;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public interface IGridNode {
    public void beginVisit(IGridVisitor var1);

    public void updateState();

    public IGridHost getMachine();

    public IGrid getGrid();

    public void destroy();

    public World getWorld();

    public EnumSet<ForgeDirection> getConnectedSides();

    public IReadOnlyCollection<IGridConnection> getConnections();

    public IGridBlock getGridBlock();

    public boolean isActive();

    public void loadFromNBT(String var1, NBTTagCompound var2);

    public void saveToNBT(String var1, NBTTagCompound var2);

    public boolean meetsChannelRequirements();

    public boolean hasFlag(GridFlags var1);

    public void setPlayerID(int var1);

    public int getPlayerID();
}

