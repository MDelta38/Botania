/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 */
package appeng.api.implementations.guiobjects;

import appeng.api.implementations.guiobjects.IGuiItemObject;
import appeng.api.networking.IGridHost;
import net.minecraft.inventory.IInventory;

public interface INetworkTool
extends IInventory,
IGuiItemObject {
    public IGridHost getGridHost();
}

