/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 */
package vazkii.botania.common.core.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import vazkii.botania.api.internal.IManaNetwork;
import vazkii.botania.api.mana.ManaNetworkEvent;
import vazkii.botania.api.mana.TileSignature;
import vazkii.botania.common.core.helper.MathHelper;

public final class ManaNetworkHandler
implements IManaNetwork {
    public static final ManaNetworkHandler instance = new ManaNetworkHandler();
    public WeakHashMap<World, List<TileSignature>> manaPools = new WeakHashMap();
    public WeakHashMap<World, List<TileSignature>> manaCollectors = new WeakHashMap();

    @SubscribeEvent
    public void onNetworkEvent(ManaNetworkEvent event) {
        WeakHashMap<World, List<TileSignature>> map;
        WeakHashMap<World, List<TileSignature>> weakHashMap = map = event.type == ManaNetworkEvent.ManaBlockType.COLLECTOR ? this.manaCollectors : this.manaPools;
        if (event.action == ManaNetworkEvent.Action.ADD) {
            this.add(map, event.tile);
        } else {
            this.remove(map, event.tile);
        }
    }

    @Override
    public void clear() {
        this.manaPools.clear();
        this.manaCollectors.clear();
    }

    @Override
    public TileEntity getClosestPool(ChunkCoordinates pos, World world, int limit) {
        if (this.manaPools.containsKey(world)) {
            return this.getClosest(this.manaPools.get(world), pos, world.field_72995_K, limit);
        }
        return null;
    }

    @Override
    public TileEntity getClosestCollector(ChunkCoordinates pos, World world, int limit) {
        if (this.manaCollectors.containsKey(world)) {
            return this.getClosest(this.manaCollectors.get(world), pos, world.field_72995_K, limit);
        }
        return null;
    }

    public boolean isCollectorIn(TileEntity tile) {
        return this.isIn(tile, this.manaCollectors);
    }

    public boolean isPoolIn(TileEntity tile) {
        return this.isIn(tile, this.manaPools);
    }

    private synchronized boolean isIn(TileEntity tile, Map<World, List<TileSignature>> map) {
        List<TileSignature> list = map.get(tile.func_145831_w());
        if (list == null) {
            return false;
        }
        for (TileSignature sig : list) {
            if (sig.tile != tile) continue;
            return true;
        }
        return false;
    }

    private synchronized TileEntity getClosest(List<TileSignature> tiles, ChunkCoordinates pos, boolean remoteCheck, int limit) {
        float closest = Float.MAX_VALUE;
        TileEntity closestTile = null;
        for (TileSignature sig : tiles) {
            float distance;
            TileEntity tile;
            if (sig.remoteWorld != remoteCheck || (tile = sig.tile).func_145837_r() || (distance = MathHelper.pointDistanceSpace(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, pos.field_71574_a, pos.field_71572_b, pos.field_71573_c)) > (float)limit || !(distance < closest)) continue;
            closest = distance;
            closestTile = tile;
        }
        return closestTile;
    }

    private synchronized void remove(Map<World, List<TileSignature>> map, TileEntity tile) {
        World world = tile.func_145831_w();
        if (!map.containsKey(world)) {
            return;
        }
        List<TileSignature> sigs = map.get(world);
        for (TileSignature sig : sigs) {
            if (!sig.tile.equals(tile)) continue;
            sigs.remove(sig);
            break;
        }
    }

    private synchronized void add(Map<World, List<TileSignature>> map, TileEntity tile) {
        List<TileSignature> tiles;
        World world = tile.func_145831_w();
        if (!map.containsKey(world)) {
            map.put(world, new ArrayList());
        }
        if (!(tiles = map.get(world)).contains(tile)) {
            tiles.add(new TileSignature(tile, tile.func_145831_w().field_72995_K));
        }
    }

    @Override
    public List<TileSignature> getAllCollectorsInWorld(World world) {
        return this.getAllInWorld(this.manaCollectors, world);
    }

    @Override
    public List<TileSignature> getAllPoolsInWorld(World world) {
        return this.getAllInWorld(this.manaPools, world);
    }

    private List<TileSignature> getAllInWorld(Map<World, List<TileSignature>> map, World world) {
        if (!map.containsKey(world)) {
            return new ArrayList<TileSignature>();
        }
        return map.get(world);
    }
}

