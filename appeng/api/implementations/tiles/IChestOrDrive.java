/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.implementations.tiles;

import appeng.api.networking.IGridHost;
import appeng.api.storage.ICellContainer;
import appeng.api.util.IOrientable;

public interface IChestOrDrive
extends ICellContainer,
IGridHost,
IOrientable {
    public int getCellCount();

    public int getCellStatus(int var1);

    public boolean isPowered();

    public boolean isCellBlinking(int var1);
}

