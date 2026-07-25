/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.lib.events;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.WorldCoordinates;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IAspectSource;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXEssentiaSource;
import thaumcraft.common.tiles.TileMirrorEssentia;

public class EssentiaHandler {
    static final int DELAY = 5000;
    private static HashMap<WorldCoordinates, ArrayList<WorldCoordinates>> sources = new HashMap();
    private static HashMap<WorldCoordinates, Long> sourcesDelay = new HashMap();
    public static HashMap<String, EssentiaSourceFX> sourceFX = new HashMap();

    public static boolean drainEssentia(TileEntity tile, Aspect aspect, ForgeDirection direction, int range) {
        return EssentiaHandler.drainEssentia(tile, aspect, direction, range, false);
    }

    public static boolean drainEssentia(TileEntity tile, Aspect aspect, ForgeDirection direction, int range, boolean ignoreMirror) {
        WorldCoordinates tileLoc = new WorldCoordinates(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, tile.func_145831_w().field_73011_w.field_76574_g);
        if (!sources.containsKey(tileLoc)) {
            EssentiaHandler.getSources(tile.func_145831_w(), tileLoc, direction, range);
            if (sources.containsKey(tileLoc)) {
                return EssentiaHandler.drainEssentia(tile, aspect, direction, range);
            }
            return false;
        }
        ArrayList<WorldCoordinates> es = sources.get(tileLoc);
        for (WorldCoordinates source : es) {
            IAspectSource as;
            TileEntity sourceTile = tile.func_145831_w().func_147438_o(source.x, source.y, source.z);
            if (sourceTile == null || !(sourceTile instanceof IAspectSource)) break;
            if (ignoreMirror && sourceTile instanceof TileMirrorEssentia || !(as = (IAspectSource)sourceTile).takeFromContainer(aspect, 1)) continue;
            PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXEssentiaSource(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, (byte)(tile.field_145851_c - source.x), (byte)(tile.field_145848_d - source.y), (byte)(tile.field_145849_e - source.z), aspect.getColor()), new NetworkRegistry.TargetPoint(tile.func_145831_w().field_73011_w.field_76574_g, (double)tile.field_145851_c, (double)tile.field_145848_d, (double)tile.field_145849_e, 32.0));
            return true;
        }
        sources.remove(tileLoc);
        sourcesDelay.put(tileLoc, System.currentTimeMillis() + 5000L);
        return false;
    }

    public static boolean findEssentia(TileEntity tile, Aspect aspect, ForgeDirection direction, int range) {
        WorldCoordinates tileLoc = new WorldCoordinates(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, tile.func_145831_w().field_73011_w.field_76574_g);
        if (!sources.containsKey(tileLoc)) {
            EssentiaHandler.getSources(tile.func_145831_w(), tileLoc, direction, range);
            if (sources.containsKey(tileLoc)) {
                return EssentiaHandler.findEssentia(tile, aspect, direction, range);
            }
            return false;
        }
        ArrayList<WorldCoordinates> es = sources.get(tileLoc);
        for (WorldCoordinates source : es) {
            TileEntity sourceTile = tile.func_145831_w().func_147438_o(source.x, source.y, source.z);
            if (sourceTile == null || !(sourceTile instanceof IAspectSource)) break;
            IAspectSource as = (IAspectSource)sourceTile;
            if (!as.doesContainerContainAmount(aspect, 1)) continue;
            return true;
        }
        sources.remove(tileLoc);
        sourcesDelay.put(tileLoc, System.currentTimeMillis() + 5000L);
        return false;
    }

    private static void getSources(World world, WorldCoordinates tileLoc, ForgeDirection direction, int range) {
        if (sourcesDelay.containsKey(tileLoc)) {
            long d = sourcesDelay.get(tileLoc);
            if (d <= System.currentTimeMillis()) {
                sourcesDelay.remove(tileLoc);
            } else {
                return;
            }
        }
        TileEntity sourceTile = world.func_147438_o(tileLoc.x, tileLoc.y, tileLoc.z);
        ArrayList<WorldCoordinates> sourceList = new ArrayList<WorldCoordinates>();
        int start = 0;
        if (direction == ForgeDirection.UNKNOWN) {
            start = -range;
            direction = ForgeDirection.UP;
        }
        int xx = 0;
        int yy = 0;
        int zz = 0;
        for (int aa = -range; aa <= range; ++aa) {
            for (int bb = -range; bb <= range; ++bb) {
                for (int cc = start; cc < range; ++cc) {
                    if (aa == 0 && bb == 0 && cc == 0) continue;
                    xx = tileLoc.x;
                    yy = tileLoc.y;
                    zz = tileLoc.z;
                    if (direction.offsetY != 0) {
                        xx += aa;
                        yy += cc * direction.offsetY;
                        zz += bb;
                    } else if (direction.offsetX == 0) {
                        xx += aa;
                        yy += bb;
                        zz += cc * direction.offsetZ;
                    } else {
                        xx += cc * direction.offsetX;
                        yy += aa;
                        zz += bb;
                    }
                    TileEntity te = world.func_147438_o(xx, yy, zz);
                    if (te == null || !(te instanceof IAspectSource) || sourceTile instanceof TileMirrorEssentia && te instanceof TileMirrorEssentia && sourceTile.field_145851_c == ((TileMirrorEssentia)te).linkX && sourceTile.field_145848_d == ((TileMirrorEssentia)te).linkY && sourceTile.field_145849_e == ((TileMirrorEssentia)te).linkZ && sourceTile.func_145831_w().field_73011_w.field_76574_g == ((TileMirrorEssentia)te).linkDim) continue;
                    sourceList.add(new WorldCoordinates(xx, yy, zz, world.field_73011_w.field_76574_g));
                }
            }
        }
        if (sourceList.size() > 0) {
            sources.put(tileLoc, sourceList);
        } else {
            sourcesDelay.put(tileLoc, System.currentTimeMillis() + 5000L);
        }
    }

    public static void refreshSources(TileEntity tile) {
        sources.remove(new WorldCoordinates(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, tile.func_145831_w().field_73011_w.field_76574_g));
    }

    public static class EssentiaSourceFX {
        public ChunkCoordinates start;
        public ChunkCoordinates end;
        public int ticks;
        public int color;

        public EssentiaSourceFX(ChunkCoordinates start, ChunkCoordinates end, int ticks, int color) {
            this.start = start;
            this.end = end;
            this.ticks = ticks;
            this.color = color;
        }
    }
}

