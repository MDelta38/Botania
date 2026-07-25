/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.tileentity.TileEntity
 */
package appeng.api.movable;

import appeng.api.movable.IMovableHandler;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;

public interface IMovableRegistry {
    public void blacklistBlock(Block var1);

    public void whiteListTileEntity(Class<? extends TileEntity> var1);

    public boolean askToMove(TileEntity var1);

    public void doneMoving(TileEntity var1);

    public void addHandler(IMovableHandler var1);

    public IMovableHandler getHandler(TileEntity var1);

    public IMovableHandler getDefaultHandler();

    public boolean isBlacklisted(Block var1);
}

