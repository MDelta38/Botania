/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.common;

import com.emoniph.witchery.common.INullSource;
import com.emoniph.witchery.common.IPowerSource;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.Log;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class PowerSources {
    private static PowerSources INSTANCE_CLIENT;
    private static PowerSources INSTANCE_SERVER;
    private final ArrayList<IPowerSource> powerSources = new ArrayList();
    private final ArrayList<INullSource> nullSources = new ArrayList();

    public static PowerSources instance() {
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
            return INSTANCE_SERVER;
        }
        return INSTANCE_CLIENT;
    }

    public static void initiate() {
        INSTANCE_CLIENT = new PowerSources();
        INSTANCE_SERVER = new PowerSources();
    }

    private PowerSources() {
    }

    public String getDebugData() {
        StringBuilder sb = new StringBuilder();
        for (IPowerSource source : this.powerSources) {
            if (sb.length() > 0) {
                sb.append('\n');
            }
            sb.append(String.format("Altar (%s) [dim=%d] power=%f", source.getLocation(), source.getWorld().field_73011_w.field_76574_g, Float.valueOf(source.getCurrentPower())));
        }
        return sb.length() > 0 ? sb.insert(0, "** ALTARS **\n").toString() : "No power sources";
    }

    public void registerPowerSource(IPowerSource powerSource) {
        if (!this.powerSources.contains(powerSource)) {
            try {
                Iterator<IPowerSource> it = this.powerSources.iterator();
                while (it.hasNext()) {
                    IPowerSource source = it.next();
                    if (source != null && !source.isPowerInvalid() && !source.getLocation().equals(powerSource.getLocation())) continue;
                    it.remove();
                }
            }
            catch (Throwable e) {
                Log.instance().warning(e, "Exception occured validating existing power source entries");
            }
            this.powerSources.add(powerSource);
        }
    }

    public void removePowerSource(IPowerSource powerSource) {
        if (this.powerSources.contains(powerSource)) {
            this.powerSources.remove(powerSource);
        }
        try {
            Iterator<IPowerSource> it = this.powerSources.iterator();
            while (it.hasNext()) {
                IPowerSource source = it.next();
                if (source == null || source.isPowerInvalid()) {
                    it.remove();
                    continue;
                }
                if (source.getLocation().getBlockTileEntity(source.getWorld()) == source) continue;
                it.remove();
            }
        }
        catch (Throwable e) {
            Log.instance().warning(e, "Exception occured removing existing power source entries");
        }
    }

    public ArrayList<RelativePowerSource> get(World world, Coord location, int radius) {
        ArrayList<RelativePowerSource> nearbyPowerSources = new ArrayList<RelativePowerSource>();
        double radiusSq = radius * radius;
        for (IPowerSource registeredSource : this.powerSources) {
            RelativePowerSource powerSource = new RelativePowerSource(registeredSource, location);
            if (!powerSource.isInWorld(world) || !powerSource.isInRange()) continue;
            nearbyPowerSources.add(powerSource);
        }
        Collections.sort(nearbyPowerSources, new Comparator<RelativePowerSource>(){

            @Override
            public int compare(RelativePowerSource a, RelativePowerSource b) {
                return Double.compare(a.distanceSq, b.distanceSq);
            }
        });
        return nearbyPowerSources;
    }

    public void registerNullSource(INullSource nullSource) {
        if (!this.nullSources.contains(nullSource)) {
            Coord newLocation = new Coord(nullSource);
            try {
                Iterator<INullSource> it = this.nullSources.iterator();
                while (it.hasNext()) {
                    INullSource source = it.next();
                    if (source != null && !source.isPowerInvalid() && !new Coord(source).equals(newLocation)) continue;
                    it.remove();
                }
            }
            catch (Throwable e) {
                Log.instance().warning(e, "Exception occured validating existing null source entries");
            }
            this.nullSources.add(nullSource);
        }
    }

    public void removeNullSource(INullSource nullSource) {
        if (this.nullSources.contains(nullSource)) {
            this.nullSources.remove(nullSource);
        }
        try {
            Iterator<INullSource> it = this.nullSources.iterator();
            while (it.hasNext()) {
                INullSource source = it.next();
                if (source == null || source.isPowerInvalid()) {
                    it.remove();
                    continue;
                }
                if (new Coord(source).getBlockTileEntity(source.getWorld()) == source) continue;
                it.remove();
            }
        }
        catch (Throwable e) {
            Log.instance().warning(e, "Exception occured removing existing null source entries");
        }
    }

    public boolean isAreaNulled(World world, int posX, int posY, int posZ) {
        for (INullSource source : this.nullSources) {
            double rangeSq = source.getRange() * source.getRange();
            if (!(Coord.distanceSq(posX, posY, posZ, source.getPosX(), source.getPosY(), source.getPosZ()) < rangeSq)) continue;
            return true;
        }
        return false;
    }

    public static IPowerSource findClosestPowerSource(World world, int posX, int posY, int posZ) {
        ArrayList<RelativePowerSource> sources = PowerSources.instance() != null ? PowerSources.instance().get(world, new Coord(posX, posY, posZ), 16) : null;
        return sources != null && sources.size() > 0 ? ((RelativePowerSource)sources.get(0)).source() : null;
    }

    public static IPowerSource findClosestPowerSource(World world, Coord coord) {
        return PowerSources.findClosestPowerSource(world, coord.x, coord.y, coord.z);
    }

    public static IPowerSource findClosestPowerSource(TileEntity tile) {
        return PowerSources.findClosestPowerSource(tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
    }

    public static class RelativePowerSource {
        private final IPowerSource powerSource;
        private final double distanceSq;
        private final double rangeSq;

        public RelativePowerSource(IPowerSource powerSource, Coord relativeLocation) {
            this.powerSource = powerSource;
            this.distanceSq = relativeLocation.distanceSqTo(this.powerSource.getLocation());
            double range = powerSource.getRange();
            this.rangeSq = range * range;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || obj.getClass() != this.getClass()) {
                return false;
            }
            return ((RelativePowerSource)obj).powerSource == this.powerSource;
        }

        public boolean isInWorld(World world) {
            return this.powerSource.getWorld() == world;
        }

        public IPowerSource source() {
            return this.powerSource;
        }

        public boolean isInRange() {
            return this.distanceSq <= this.rangeSq;
        }
    }
}

