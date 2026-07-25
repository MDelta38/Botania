/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 */
package appeng.api.storage;

import appeng.api.networking.security.BaseActionSource;
import appeng.api.storage.IExternalStorageHandler;
import appeng.api.storage.StorageChannel;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public interface IExternalStorageRegistry {
    public void addExternalStorageInterface(IExternalStorageHandler var1);

    public IExternalStorageHandler getHandler(TileEntity var1, ForgeDirection var2, StorageChannel var3, BaseActionSource var4);
}

