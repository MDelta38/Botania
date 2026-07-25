/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.WorldServer
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraftforge.common.ForgeModContainer
 */
package com.emoniph.witchery.dimension;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.dimension.WorldChunkManagerTorment;
import com.emoniph.witchery.entity.EntityLordOfTorment;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.ServerUtil;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.ForgeModContainer;

public class WorldProviderTorment
extends WorldProvider {
    public static final String SPIRIT_WORLD_TORMENT_PLAYER_KEY = "WITCForceTorment";
    public static final String SPIRIT_WORLD_TORMENT_LEVEL_KEY = "WITCForceTormentLevel";
    public static final int TORMENT_NONE = 0;
    public static final int TORMENT_BEGIN = 1;
    public static final int TORMENT_BEGIN_WITH_BOSS = 2;
    public static final int TORMENT_END = 3;

    public String func_80007_l() {
        return "Torment";
    }

    public IChunkProvider func_76555_c() {
        return new WorldChunkManagerTorment(this.field_76579_a);
    }

    public boolean func_76567_e() {
        return false;
    }

    public boolean func_76569_d() {
        return false;
    }

    public boolean canDoLightning(Chunk chunk) {
        return false;
    }

    public boolean isBlockHighHumidity(int x, int y, int z) {
        return false;
    }

    public boolean isDaytime() {
        return false;
    }

    public ChunkCoordinates getSpawnPoint() {
        return new ChunkCoordinates(8, 14, 8);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_76561_g() {
        return true;
    }

    public float func_76563_a(long par1, float par3) {
        return 1.0f;
    }

    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
        int multiplier;
        float f1 = this.field_76579_a.func_72826_c(partialTicks);
        float f2 = MathHelper.func_76134_b((float)(f1 * (float)Math.PI * 2.0f)) * 2.0f + 0.5f;
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        int i = MathHelper.func_76128_c((double)cameraEntity.field_70165_t);
        int j = MathHelper.func_76128_c((double)cameraEntity.field_70163_u);
        int k = MathHelper.func_76128_c((double)cameraEntity.field_70161_v);
        GameSettings settings = Minecraft.func_71410_x().field_71474_y;
        int[] ranges = ForgeModContainer.blendRanges;
        int distance = 0;
        if (settings.field_74347_j && settings.field_151451_c >= 0 && settings.field_151451_c < ranges.length) {
            distance = ranges[settings.field_151451_c];
        }
        int r = 0;
        int g = 0;
        int b = 0;
        int divider = 0;
        for (int x = -distance; x <= distance; ++x) {
            for (int z = -distance; z <= distance; ++z) {
                BiomeGenBase biome = this.field_76579_a.func_72807_a(i + x, k + z);
                int colour = 0xFF0000;
                r += (colour & 0xFF0000) >> 16;
                g += (colour & 0xFF00) >> 8;
                b += colour & 0xFF;
                ++divider;
            }
        }
        int l = multiplier = (r / divider & 0xFF) << 16 | (g / divider & 0xFF) << 8 | b / divider & 0xFF;
        float f4 = (float)(l >> 16 & 0xFF) / 255.0f;
        float f5 = (float)(l >> 8 & 0xFF) / 255.0f;
        float f6 = (float)(l & 0xFF) / 255.0f;
        return Vec3.func_72443_a((double)f4, (double)f5, (double)f6);
    }

    public static void setPlayerMustTorment(EntityPlayer player, int torment, int presetLevel) {
        NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
        WorldProviderTorment.setPlayerMustTorment(nbtPlayer, torment, presetLevel);
    }

    public static void setPlayerMustTorment(NBTTagCompound nbtPlayer, int torment, int presetLevel) {
        nbtPlayer.func_74768_a(SPIRIT_WORLD_TORMENT_PLAYER_KEY, torment);
        if (presetLevel > -1) {
            nbtPlayer.func_74768_a(SPIRIT_WORLD_TORMENT_LEVEL_KEY, presetLevel);
        } else if (presetLevel == -2 && nbtPlayer.func_74764_b(SPIRIT_WORLD_TORMENT_LEVEL_KEY)) {
            nbtPlayer.func_82580_o(SPIRIT_WORLD_TORMENT_LEVEL_KEY);
        }
    }

    public static int getRandomTormentLevel(World world) {
        return world.field_73012_v.nextInt(6);
    }

    public static int getPlayerMustTorment(EntityPlayer player) {
        NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
        return WorldProviderTorment.getPlayerMustTorment(nbtPlayer);
    }

    public static int getPlayerMustTorment(NBTTagCompound nbtPlayer) {
        return nbtPlayer.func_74762_e(SPIRIT_WORLD_TORMENT_PLAYER_KEY);
    }

    public static void updatePlayerEffects(World world, EntityPlayer player, NBTTagCompound nbtPlayer, long time, long counter) {
        if (!world.field_72995_K) {
            boolean done = false;
            if (counter % 20L == 0L) {
                int mustTorment = WorldProviderTorment.getPlayerMustTorment(nbtPlayer);
                if (mustTorment == 1 || mustTorment == 2) {
                    int level = mustTorment == 2 ? nbtPlayer.func_74762_e(SPIRIT_WORLD_TORMENT_LEVEL_KEY) : WorldProviderTorment.getRandomTormentLevel(world);
                    WorldProviderTorment.setPlayerMustTorment(nbtPlayer, 0, -1);
                    if (player.func_70115_ae()) {
                        player.func_70078_a(null);
                    }
                    ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, (Entity)player, 1.0, 2.0, 16);
                    int yPos = 12 + level * 15;
                    player.func_70634_a(8.0, (double)yPos, 8.0);
                    ItemGeneral cfr_ignored_0 = Witchery.Items.GENERIC;
                    ItemGeneral.travelToDimension(player, Config.instance().dimensionTormentID);
                    player.func_70634_a(8.0, (double)yPos, 8.0);
                    ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, (Entity)player, 1.0, 2.0, 16);
                    WorldServer tormentWorld = ServerUtil.getWorld(Config.instance().dimensionTormentID);
                    int midX = 8;
                    int midZ = 8;
                    for (int x = midX - 1; x <= midX + 1; ++x) {
                        for (int z = midZ - 1; z <= midZ + 1; ++z) {
                            if (!tormentWorld.func_147437_c(x, yPos, z)) {
                                tormentWorld.func_147468_f(x, yPos, z);
                            }
                            if (tormentWorld.func_147437_c(x, yPos + 1, z)) continue;
                            tormentWorld.func_147468_f(x, yPos + 1, z);
                        }
                    }
                    if (mustTorment == 2) {
                        boolean found = false;
                        if (player.field_70170_p.field_73011_w.field_76574_g == Config.instance().dimensionTormentID) {
                            for (Object obj : player.field_70170_p.field_72996_f) {
                                if (!(obj instanceof EntityLordOfTorment)) continue;
                                EntityLordOfTorment lot = (EntityLordOfTorment)obj;
                                if (!(lot.field_70163_u >= (double)(yPos - 2)) || !(lot.field_70163_u <= (double)(yPos + 6 - 2))) continue;
                                found = true;
                                break;
                            }
                        }
                        if (!found && tormentWorld != null) {
                            EntityLordOfTorment lot = new EntityLordOfTorment((World)tormentWorld);
                            lot.func_70080_a(9.0, yPos - 1, 36.0, 0.0f, 0.0f);
                            lot.func_110163_bv();
                            lot.func_70606_j(lot.func_110138_aP() * 0.5f);
                            tormentWorld.func_72838_d((Entity)lot);
                        }
                    }
                } else if (mustTorment == 3) {
                    WorldProviderTorment.setPlayerMustTorment(nbtPlayer, 0, -2);
                    if (player.func_70115_ae()) {
                        player.func_70078_a(null);
                    }
                    WorldServer overworld = MinecraftServer.func_71276_C().field_71305_c[0];
                    ChunkCoordinates coords = player.getBedLocation(0);
                    int dimension = 0;
                    if (coords == null) {
                        coords = overworld.func_72861_E();
                    }
                    if (coords != null) {
                        int mod = 0;
                        int origY = coords.field_71572_b;
                        while (!WorldProviderTorment.isSafeBlock((World)overworld, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c) && coords.field_71572_b > 1 && coords.field_71572_b < 255) {
                            coords.field_71572_b = origY + mod;
                            if (origY - mod > 1) {
                                mod = -mod;
                            }
                            if (mod < 0) continue;
                            ++mod;
                        }
                        ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, (Entity)player, 1.0, 2.0, 16);
                        ItemGeneral cfr_ignored_1 = Witchery.Items.GENERIC;
                        ItemGeneral.teleportToLocation(player.field_70170_p, coords.field_71574_a, coords.field_71572_b + 1, coords.field_71573_c, dimension, (Entity)player, true);
                        ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, (Entity)player, 1.0, 2.0, 16);
                    }
                }
            }
        }
    }

    private static boolean isSafeBlock(World world, int posX, int posY, int posZ) {
        boolean base = BlockUtil.isSolid(world, posX, posY, posZ);
        boolean air1 = !BlockUtil.isSolid(world, posX, posY + 1, posZ);
        boolean air2 = !BlockUtil.isSolid(world, posX, posY + 2, posZ);
        boolean isSafe = base && air1 && air2;
        return isSafe;
    }
}

