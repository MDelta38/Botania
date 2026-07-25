/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.world.BlockEvent$BreakEvent
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.codechicken.lib.vec.Vector3
 */
package thaumic.tinkerer.common.core.helper;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.codechicken.lib.vec.Vector3;
import thaumic.tinkerer.common.ThaumicTinkerer;

public final class MiscHelper {
    public static double pointDistanceSpace(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.sqrt(Math.pow(x1 - x2, 2.0) + Math.pow(y1 - y2, 2.0) + Math.pow(z1 - z2, 2.0));
    }

    public static boolean breakBlockWithCheck(World w, int x, int y, int z, Block block, int meta, int flag, EntityPlayer player) {
        if (!MinecraftForge.EVENT_BUS.post((Event)new BlockEvent.BreakEvent(x, y, z, w, w.func_147439_a(x, y, z), meta, player))) {
            w.func_147465_d(x, y, z, block, meta, flag);
            return true;
        }
        return false;
    }

    public static boolean breakBlockToAirWithCheck(World w, int x, int y, int z, EntityPlayer player) {
        return MiscHelper.breakBlockWithCheck(w, x, y, z, Blocks.field_150350_a, 0, 3, player);
    }

    public static boolean breakBlockWithCheck(World w, int x, int y, int z, Block block, EntityPlayer player) {
        return MiscHelper.breakBlockWithCheck(w, x, y, z, block, 0, 0, player);
    }

    public static MinecraftServer server() {
        return MinecraftServer.func_71276_C();
    }

    public static void setEntityMotionFromVector(Entity entity, Vector3 originalPosVector, float modifier) {
        Vector3 entityVector = Vector3.fromEntityCenter((Entity)entity);
        Vector3 finalVector = originalPosVector.copy().subtract(entityVector);
        if (finalVector.mag() > 1.0) {
            finalVector.normalize();
        }
        entity.field_70159_w = finalVector.x * (double)modifier;
        entity.field_70181_x = finalVector.y * (double)modifier;
        entity.field_70179_y = finalVector.z * (double)modifier;
    }

    public static AspectList multiplyAspectList(AspectList list, double multiplier) {
        AspectList newList = list.copy();
        if (multiplier == 1.0) {
            return newList;
        }
        for (Aspect aspect : newList.aspects.keySet()) {
            newList.aspects.put(aspect, (int)((double)((Integer)newList.aspects.get(aspect)).intValue() * multiplier));
        }
        return newList;
    }

    public static void printCurrentStackTrace(String message) {
        if (message != null) {
            ThaumicTinkerer.log.info(message);
        }
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            ThaumicTinkerer.log.info((Object)element);
        }
    }
}

