/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.implementations.guiobjects;

import appeng.api.implementations.guiobjects.IGuiItemObject;
import appeng.api.networking.energy.IEnergySource;
import appeng.api.storage.IMEMonitor;
import appeng.api.storage.ITerminalHost;
import appeng.api.storage.data.IAEItemStack;

public interface IPortableCell
extends ITerminalHost,
IMEMonitor<IAEItemStack>,
IEnergySource,
IGuiItemObject {
}

