/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 */
package vazkii.botania.api.internal;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import vazkii.botania.api.internal.IManaNetwork;
import vazkii.botania.api.mana.TileSignature;

public class DummyManaNetwork
implements IManaNetwork {
    public static final DummyManaNetwork instance = new DummyManaNetwork();

    @Override
    public void clear() {
    }

    @Override
    public TileEntity getClosestPool(ChunkCoordinates pos, World world, int limit) {
        return null;
    }

    @Override
    public TileEntity getClosestCollector(ChunkCoordinates pos, World world, int limit) {
        return null;
    }

    @Override
    public List<TileSignature> getAllCollectorsInWorld(World world) {
        return new ArrayList<TileSignature>();
    }

    @Override
    public List<TileSignature> getAllPoolsInWorld(World world) {
        return new ArrayList<TileSignature>();
    }
}

