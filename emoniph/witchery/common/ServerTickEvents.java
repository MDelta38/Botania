/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerChangedDimensionEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerLoggedInEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$PlayerRespawnEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ServerTickEvent
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.common;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.common.Shapeshift;
import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.network.PacketPlayerStyle;
import com.emoniph.witchery.util.Config;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import java.util.ArrayList;
import java.util.Collection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class ServerTickEvents {
    public static final ArrayList<ServerTickTask> TASKS = new ArrayList();

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.side == Side.SERVER && event.phase == TickEvent.Phase.START && TASKS.size() > 0) {
            ArrayList<ServerTickTask> completedTasks = new ArrayList<ServerTickTask>();
            for (ServerTickTask task : TASKS) {
                if (!task.process()) continue;
                completedTasks.add(task);
            }
            for (ServerTickTask task : completedTasks) {
                TASKS.remove(task);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == Side.SERVER && !event.player.field_70170_p.field_72995_K) {
            ExtendedPlayer playerExt;
            if (event.phase == TickEvent.Phase.START) {
                Collection activeEffects = event.player.func_70651_bq();
                ExtendedPlayer playerExt2 = ExtendedPlayer.get(event.player);
                if (playerExt2 != null) {
                    playerExt2.updateWorship();
                    if (activeEffects.size() > 0) {
                        playerExt2.cacheIncurablePotionEffect(activeEffects);
                    }
                    playerExt2.checkSleep(true);
                }
            } else if (event.phase == TickEvent.Phase.END && (playerExt = ExtendedPlayer.get(event.player)) != null) {
                playerExt.restoreIncurablePotionEffects();
                playerExt.checkSleep(false);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        EntityPlayer player = event.player;
        World world = event.player.field_70170_p;
        Shapeshift.INSTANCE.initCurrentShift(player);
        Infusion.syncPlayer(world, player);
        ExtendedPlayer.get(player).scheduleSync();
        Witchery.packetPipeline.sendToDimension(new PacketPlayerStyle(player), world.field_73011_w.field_76574_g);
        if (player.field_71093_bK != Config.instance().dimensionDreamID && WorldProviderDreamWorld.getPlayerIsSpiritWalking(player) && !WorldProviderDreamWorld.getPlayerIsGhost(player)) {
            WorldProviderDreamWorld.setPlayerMustAwaken(player, true);
        } else if (player.field_71093_bK == Config.instance().dimensionDreamID && !WorldProviderDreamWorld.getPlayerIsSpiritWalking(player)) {
            WorldProviderDreamWorld.changeDimension(player, 0);
            WorldProviderDreamWorld.findTopAndSetPosition(player.field_70170_p, player);
        }
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (!event.player.field_70170_p.field_72995_K) {
            NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)event.player);
            if (nbtPlayer.func_74764_b("WITCPoSpawn")) {
                NBTTagList nbtRestoredEffectList = nbtPlayer.func_150295_c("WITCPoSpawn", 10);
                if (nbtRestoredEffectList.func_74745_c() > 0) {
                    for (int i = 0; i < nbtRestoredEffectList.func_74745_c(); ++i) {
                        PotionEffect restoredEffect = PotionEffect.func_82722_b((NBTTagCompound)nbtRestoredEffectList.func_150305_b(i));
                        if (event.player.func_82165_m(restoredEffect.func_76456_a())) continue;
                        event.player.func_70690_d(restoredEffect);
                    }
                }
                nbtPlayer.func_82580_o("WITCPoSpawn");
            }
            EntityPlayer player = event.player;
            World world = event.player.field_70170_p;
            Shapeshift.INSTANCE.initCurrentShift(player);
            Infusion.syncPlayer(world, player);
            ExtendedPlayer.get(player).scheduleSync();
            Witchery.packetPipeline.sendToDimension(new PacketPlayerStyle(player), world.field_73011_w.field_76574_g);
        }
    }

    @SubscribeEvent
    public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        EntityPlayer player;
        if (!event.player.field_70170_p.field_72995_K && (player = event.player) != null && player.field_70170_p != null && !player.field_70170_p.field_72995_K) {
            long nextUpdate = MinecraftServer.func_130071_aq() + 1500L;
            ExtendedPlayer.get(player).scheduleSync();
            for (Object obj : player.field_70170_p.field_73010_i) {
                EntityPlayer otherPlayer = (EntityPlayer)obj;
                NBTTagCompound nbtOtherPlayer = Infusion.getNBT((Entity)otherPlayer);
                if (otherPlayer == player) continue;
                nbtOtherPlayer.func_74772_a("WITCResyncLook", nextUpdate);
            }
            NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
            Witchery.packetPipeline.sendToDimension(new PacketPlayerStyle(player), player.field_70170_p.field_73011_w.field_76574_g);
            if (player.field_71093_bK != Config.instance().dimensionDreamID && WorldProviderDreamWorld.getPlayerIsSpiritWalking(player) && !WorldProviderDreamWorld.getPlayerIsGhost(player)) {
                WorldProviderDreamWorld.setPlayerMustAwaken(player, true);
            }
        }
    }

    public static abstract class ServerTickTask {
        protected final World world;

        public ServerTickTask(World world) {
            this.world = world;
        }

        public abstract boolean process();
    }
}

