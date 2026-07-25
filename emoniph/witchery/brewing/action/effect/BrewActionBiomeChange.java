/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S26PacketMapChunkBulk
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.emoniph.witchery.brewing.action.effect;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.AltarPower;
import com.emoniph.witchery.brewing.BrewItemKey;
import com.emoniph.witchery.brewing.BrewNamePart;
import com.emoniph.witchery.brewing.EffectLevel;
import com.emoniph.witchery.brewing.ModifiersEffect;
import com.emoniph.witchery.brewing.ModifiersImpact;
import com.emoniph.witchery.brewing.ModifiersRitual;
import com.emoniph.witchery.brewing.Probability;
import com.emoniph.witchery.brewing.action.BrewActionEffect;
import com.emoniph.witchery.brewing.action.BrewActionList;
import com.emoniph.witchery.item.ItemBook;
import com.emoniph.witchery.util.Coord;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.ForgeDirection;

public class BrewActionBiomeChange
extends BrewActionEffect {
    public BrewActionBiomeChange(BrewItemKey itemKey, BrewNamePart namePart, AltarPower powerCost, Probability baseProbability, EffectLevel effectLevel) {
        super(itemKey, namePart, powerCost, baseProbability, effectLevel);
    }

    @Override
    public void prepareSplashPotion(World world, BrewActionList actionList, ModifiersImpact modifiers) {
        super.prepareSplashPotion(world, actionList, modifiers);
        modifiers.setOnlyInstant();
    }

    @Override
    protected void doApplyRitualToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersRitual ritualModifiers, ModifiersEffect modifiers, ItemStack stack) {
        BiomeGenBase biome = ItemBook.getSelectedBiome(stack.func_77960_j());
        int maxRadius = 16 + modifiers.getStrength() * 16;
        this.changeBiome(world, new Coord(x, y, z), maxRadius, biome);
    }

    @Override
    protected void doApplyToBlock(World world, int x, int y, int z, ForgeDirection side, int radius, ModifiersEffect modifiers, ItemStack actionStack) {
        BiomeGenBase biome = ItemBook.getSelectedBiome(actionStack.func_77960_j());
        this.changeBiome(world, new Coord(x, y, z), 1 + modifiers.getStrength(), biome);
    }

    @Override
    protected void doApplyToEntity(World world, EntityLivingBase targetEntity, ModifiersEffect modifiers, ItemStack actionStack) {
    }

    protected void changeBiome(World world, Coord coord, int radius, BiomeGenBase biome) {
        HashMap<ChunkCoord, byte[]> chunkMap = new HashMap<ChunkCoord, byte[]>();
        this.drawFilledCircle(world, coord.x, coord.z, radius, chunkMap, biome);
        ArrayList<Chunk> chunks = new ArrayList<Chunk>();
        for (Map.Entry<ChunkCoord, byte[]> entry : chunkMap.entrySet()) {
            Chunk chunk = entry.getKey().getChunk(world);
            chunk.func_76616_a(entry.getValue());
            chunks.add(chunk);
        }
        S26PacketMapChunkBulk packet = new S26PacketMapChunkBulk(chunks);
        Witchery.packetPipeline.sendToDimension((Packet)packet, world);
        for (Chunk chunk : chunks) {
            for (Object tileObj : chunk.field_150816_i.values()) {
                TileEntity tile = (TileEntity)tileObj;
                Packet packet2 = tile.func_145844_m();
                if (packet2 == null) continue;
                world.func_147471_g(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
            }
        }
    }

    private void drawFilledCircle(World world, int x0, int z0, int radius, HashMap<ChunkCoord, byte[]> chunkMap, BiomeGenBase biome) {
        if (radius == 1) {
            this.drawLine(world, x0, x0, z0, chunkMap, biome);
        } else {
            int x = --radius;
            int radiusError = 1 - x;
            for (int z = 0; x >= z; ++z) {
                this.drawLine(world, -x + x0, x + x0, z + z0, chunkMap, biome);
                this.drawLine(world, -z + x0, z + x0, x + z0, chunkMap, biome);
                this.drawLine(world, -x + x0, x + x0, -z + z0, chunkMap, biome);
                this.drawLine(world, -z + x0, z + x0, -x + z0, chunkMap, biome);
                if (radiusError < 0) {
                    radiusError += 2 * z + 1;
                    continue;
                }
                radiusError += 2 * (z - --x + 1);
            }
        }
    }

    private void drawLine(World world, int x1, int x2, int z, HashMap<ChunkCoord, byte[]> chunkMap, BiomeGenBase biome) {
        for (int x = x1; x <= x2; ++x) {
            int y;
            ChunkCoord coord = new ChunkCoord(x >> 4, z >> 4);
            byte[] map = chunkMap.get(coord);
            if (map == null) {
                Chunk chunk = world.func_72938_d(x, z);
                map = (byte[])chunk.func_76605_m().clone();
                chunkMap.put(coord, map);
            }
            map[(z & 0xF) << 4 | x & 0xF] = (byte)biome.field_76756_M;
            if (biome.field_76751_G != 0.0f || world.func_147439_a(x, y = world.func_72825_h(x, z), z) != Blocks.field_150431_aC) continue;
            world.func_147468_f(x, y, z);
        }
    }

    private static class ChunkCoord {
        public final int X;
        public final int Z;

        public ChunkCoord(int x, int z) {
            this.X = x;
            this.Z = z;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || obj.getClass() != this.getClass()) {
                return false;
            }
            ChunkCoord other = (ChunkCoord)obj;
            return this.X == other.X && this.Z == other.Z;
        }

        public int hashCode() {
            int result = this.X ^ this.X >>> 32;
            result = 31 * result + (this.Z ^ this.Z >>> 32);
            return result;
        }

        public Chunk getChunk(World world) {
            return world.func_72964_e(this.X, this.Z);
        }
    }
}

