/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S26PacketMapChunkBulk
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.storage.WorldInfo
 *  net.minecraftforge.common.BiomeDictionary
 *  net.minecraftforge.common.BiomeDictionary$Type
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.ritual.Rite;
import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.BiomeDictionary;

public class RiteClimateChange
extends Rite {
    protected final int radius;

    public RiteClimateChange(int radius) {
        this.radius = radius;
    }

    @Override
    public void addSteps(ArrayList<RitualStep> steps, int intialStage) {
        steps.add(new StepClimateChange(this, intialStage));
    }

    private static class StepClimateChange
    extends RitualStep {
        private final RiteClimateChange rite;
        private int stage = 0;
        private boolean activated;

        public StepClimateChange(RiteClimateChange rite, int initialStage) {
            super(false);
            this.rite = rite;
            this.stage = initialStage;
        }

        @Override
        public int getCurrentStage() {
            return (byte)this.stage;
        }

        @Override
        public RitualStep.Result process(World world, int posX, int posY, int posZ, long ticks, BlockCircle.TileEntityCircle.ActivatedRitual ritual) {
            block54: {
                EntityPlayer player;
                block56: {
                    block55: {
                        if (!this.activated) {
                            if (ticks % 20L != 0L) {
                                return RitualStep.Result.STARTING;
                            }
                            this.activated = true;
                            SoundEffect.RANDOM_FIZZ.playAt(world, posX, posY, posZ);
                        }
                        if (world.field_72995_K) break block54;
                        player = ritual.getInitiatingPlayer(world);
                        if (!Config.instance().allowBiomeChanging) {
                            SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
                            if (player != null) {
                                ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)player, "witchery.rite.disabled", new Object[0]);
                            }
                            return RitualStep.Result.ABORTED_REFUND;
                        }
                        BiomeGenBase biome = world.func_72807_a(posX, posZ);
                        if (world.field_73011_w.field_76574_g == 1 || world.field_73011_w.field_76574_g == -1) break block55;
                        if (biome == BiomeGenBase.field_76779_k) break block55;
                        if (biome != BiomeGenBase.field_76778_j) break block56;
                    }
                    SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
                    if (player != null) {
                        ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)player, "witchery.rite.wrongdimension", new Object[0]);
                    }
                    return RitualStep.Result.ABORTED_REFUND;
                }
                if (ritual.covenSize < 4) {
                    SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
                    if (player != null) {
                        ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)player, "witchery.rite.coventoosmall", new Object[0]);
                    }
                    return RitualStep.Result.ABORTED_REFUND;
                }
                if (ticks % 20L == 0L) {
                    ++this.stage;
                    if (this.stage < 5) {
                        ParticleEffect.INSTANT_SPELL.send(SoundEffect.NONE, world, 0.5 + (double)posX, 1.0 + (double)posY, 0.5 + (double)posZ, (float)this.stage * 1.5f, (float)this.stage * 1.1f, 16);
                    } else if (this.stage == 5) {
                        ParticleEffect.HUGE_EXPLOSION.send(SoundEffect.NONE, world, 0.5 + (double)posX, 1.0 + (double)posY, 0.5 + (double)posZ, (float)this.stage * 2.0f, (float)this.stage * 1.5f, 16);
                        double RADIUS = 8.0;
                        List items = world.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)((double)posX - 8.0), (double)(posY - 2), (double)((double)posZ - 8.0), (double)((double)posX + 8.0), (double)(posY + 2), (double)((double)posZ + 8.0)));
                        BiomeDictionary.Type biomeType = BiomeDictionary.Type.END;
                        WeatherChange weather = WeatherChange.NONE;
                        int glowstone = 0;
                        for (Object obj : items) {
                            EntityItem item = (EntityItem)obj;
                            ItemStack stack = item.func_92059_d();
                            if (stack.func_77969_a(new ItemStack(Blocks.field_150345_g, 1, 0))) {
                                biomeType = BiomeDictionary.Type.FOREST;
                            } else if (stack.func_77969_a(new ItemStack((Block)Blocks.field_150329_H, 1, 1))) {
                                biomeType = BiomeDictionary.Type.PLAINS;
                            } else if (stack.func_77969_a(new ItemStack(Blocks.field_150343_Z))) {
                                biomeType = BiomeDictionary.Type.MOUNTAIN;
                            } else if (stack.func_77969_a(new ItemStack(Blocks.field_150348_b))) {
                                biomeType = BiomeDictionary.Type.HILLS;
                            } else if (stack.func_77969_a(new ItemStack(Items.field_151123_aH))) {
                                biomeType = BiomeDictionary.Type.SWAMP;
                            } else if (stack.func_77969_a(new ItemStack(Items.field_151131_as))) {
                                biomeType = BiomeDictionary.Type.WATER;
                            } else if (stack.func_77969_a(new ItemStack(Blocks.field_150434_aF))) {
                                biomeType = BiomeDictionary.Type.DESERT;
                                weather = WeatherChange.SUN;
                            } else if (stack.func_77969_a(Witchery.Items.GENERIC.itemIcyNeedle.createStack())) {
                                biomeType = BiomeDictionary.Type.FROZEN;
                                weather = WeatherChange.RAIN;
                            } else if (stack.func_77969_a(new ItemStack(Blocks.field_150345_g, 1, 3))) {
                                biomeType = BiomeDictionary.Type.JUNGLE;
                            } else if (stack.func_77969_a(new ItemStack(Blocks.field_150424_aL))) {
                                biomeType = BiomeDictionary.Type.WASTELAND;
                            } else if (stack.func_77969_a(new ItemStack((Block)Blocks.field_150354_m))) {
                                biomeType = BiomeDictionary.Type.BEACH;
                            } else if (stack.func_77969_a(new ItemStack((Block)Blocks.field_150337_Q))) {
                                biomeType = BiomeDictionary.Type.MUSHROOM;
                            } else if (stack.func_77969_a(new ItemStack(Items.field_151144_bL))) {
                                biomeType = BiomeDictionary.Type.MAGICAL;
                            } else {
                                if (stack.func_77973_b() != Items.field_151114_aO) continue;
                                glowstone += stack.field_77994_a;
                            }
                            world.func_72900_e((Entity)item);
                            ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_POP, (Entity)item, 0.5, 1.0, 16);
                        }
                        if (biomeType == BiomeDictionary.Type.END) {
                            SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
                            if (player != null) {
                                ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)player, "witchery.rite.missingbiomefoci", new Object[0]);
                            }
                            return RitualStep.Result.ABORTED_REFUND;
                        }
                        BiomeGenBase[] biomes = BiomeDictionary.getBiomesForType((BiomeDictionary.Type)biomeType);
                        if (biomes == null || biomes.length == 0) {
                            SoundEffect.NOTE_SNARE.playAt(world, posX, posY, posZ);
                            if (player != null) {
                                ChatUtil.sendTranslated(EnumChatFormatting.DARK_RED, (ICommandSender)player, "witchery.rite.missingbiomefoci", new Object[0]);
                            }
                            return RitualStep.Result.ABORTED_REFUND;
                        }
                        int biomeID = biomes[glowstone > 0 ? Math.min((int)glowstone, (int)biomes.length) - 1 : (biomes.length >= 3 ? world.field_73012_v.nextInt((int)3) : 0)].field_76756_M;
                        int maxRadius = this.rite.radius * (ritual.covenSize - 3);
                        HashMap<ChunkCoord, byte[]> chunkMap = new HashMap<ChunkCoord, byte[]>();
                        this.drawFilledCircle(world, posX, posZ, maxRadius, chunkMap, weather, biomeID);
                        ArrayList<Chunk> chunks = new ArrayList<Chunk>();
                        for (Map.Entry<ChunkCoord, byte[]> entry : chunkMap.entrySet()) {
                            Chunk chunk = entry.getKey().getChunk(world);
                            chunk.func_76616_a(entry.getValue());
                            chunks.add(chunk);
                        }
                        S26PacketMapChunkBulk packet = new S26PacketMapChunkBulk(chunks);
                        Witchery.packetPipeline.sendToDimension((Packet)packet, world);
                        for (Object e : chunks) {
                            Chunk chunk = (Chunk)e;
                            for (Object tileObj : chunk.field_150816_i.values()) {
                                TileEntity tile = (TileEntity)tileObj;
                                Packet packet2 = tile.func_145844_m();
                                if (packet2 == null) continue;
                                world.func_147471_g(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
                            }
                        }
                        if (world instanceof WorldServer) {
                            WorldInfo worldinfo = ((WorldServer)world).func_72912_H();
                            int n = (300 + world.field_73012_v.nextInt(600)) * 20;
                            switch (weather) {
                                case SUN: {
                                    if (!world.func_72896_J() && !world.func_72911_I()) break;
                                    worldinfo.func_76080_g(0);
                                    worldinfo.func_76090_f(0);
                                    worldinfo.func_76084_b(false);
                                    worldinfo.func_76069_a(false);
                                    break;
                                }
                                case RAIN: {
                                    if (world.func_72896_J() || world.func_72911_I()) break;
                                    worldinfo.func_76080_g(n);
                                    worldinfo.func_76090_f(n);
                                    worldinfo.func_76084_b(true);
                                    worldinfo.func_76069_a(false);
                                    break;
                                }
                                case THUNDER: {
                                    if (world.func_72911_I()) break;
                                    worldinfo.func_76080_g(n);
                                    worldinfo.func_76090_f(n);
                                    worldinfo.func_76084_b(true);
                                    worldinfo.func_76069_a(true);
                                    break;
                                }
                            }
                        }
                        return RitualStep.Result.COMPLETED;
                    }
                    return RitualStep.Result.UPKEEP;
                }
                return RitualStep.Result.UPKEEP;
            }
            return RitualStep.Result.COMPLETED;
        }

        private static byte[] rotateMatrix(byte[] matrix, int n) {
            byte[] ret = new byte[matrix.length];
            for (int i = 0; i < matrix.length / n; ++i) {
                for (int j = 0; j < n; ++j) {
                    ret[j * n + i] = matrix[i * n + n - j];
                }
            }
            return ret;
        }

        protected void drawFilledCircle(World world, int x0, int z0, int radius, HashMap<ChunkCoord, byte[]> chunkMap, WeatherChange weather, int biomeID) {
            int x = radius;
            int radiusError = 1 - x;
            for (int z = 0; x >= z; ++z) {
                this.drawLine(world, -x + x0, x + x0, z + z0, chunkMap, weather, biomeID);
                this.drawLine(world, -z + x0, z + x0, x + z0, chunkMap, weather, biomeID);
                this.drawLine(world, -x + x0, x + x0, -z + z0, chunkMap, weather, biomeID);
                this.drawLine(world, -z + x0, z + x0, -x + z0, chunkMap, weather, biomeID);
                if (radiusError < 0) {
                    radiusError += 2 * z + 1;
                    continue;
                }
                radiusError += 2 * (z - --x + 1);
            }
        }

        protected void drawLine(World world, int x1, int x2, int z, HashMap<ChunkCoord, byte[]> chunkMap, WeatherChange weather, int biomeID) {
            for (int x = x1; x <= x2; ++x) {
                int y;
                ChunkCoord coord = new ChunkCoord(x >> 4, z >> 4);
                byte[] map = chunkMap.get(coord);
                if (map == null) {
                    Chunk chunk = world.func_72938_d(x, z);
                    map = (byte[])chunk.func_76605_m().clone();
                    chunkMap.put(coord, map);
                }
                map[(z & 0xF) << 4 | x & 0xF] = (byte)biomeID;
                if (weather != WeatherChange.SUN || world.func_147439_a(x, y = world.func_72825_h(x, z), z) != Blocks.field_150433_aE) continue;
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

    public static enum WeatherChange {
        NONE,
        SUN,
        RAIN,
        THUNDER;

    }
}

