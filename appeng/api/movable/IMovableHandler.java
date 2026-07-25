/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package appeng.api.movable;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public interface IMovableHandler {
    public boolean canHandle(Class<? extends TileEntity> var1, TileEntity var2);

    public void moveTile(TileEntity var1, World var2, int var3, int var4, int var5);
}

