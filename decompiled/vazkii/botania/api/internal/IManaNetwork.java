/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 */
package vazkii.botania.api.internal;

import java.util.List;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import vazkii.botania.api.mana.TileSignature;

public interface IManaNetwork {
    public void clear();

    public TileEntity getClosestCollector(ChunkCoordinates var1, World var2, int var3);

    public TileEntity getClosestPool(ChunkCoordinates var1, World var2, int var3);

    public List<TileSignature> getAllCollectorsInWorld(World var1);

    public List<TileSignature> getAllPoolsInWorld(World var1);
}

