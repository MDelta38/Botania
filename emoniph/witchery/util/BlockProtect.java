/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.world.BlockEvent$BreakEvent
 */
package com.emoniph.witchery.util;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.util.Config;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;

public class BlockProtect {
    private BlockProtect() {
    }

    public static boolean canBreak(Block block, World world) {
        return BlockProtect.canBreak(block, world, true);
    }

    public static boolean canBreak(Block block, World world, boolean denyContainers) {
        if (block != null && block.hasTileEntity(0)) {
            return false;
        }
        return block != Blocks.field_150380_bt && block != Blocks.field_150357_h && block != Witchery.Blocks.FORCE && block != Witchery.Blocks.BARRIER;
    }

    public static boolean canBreak(int x, int y, int z, World world) {
        return BlockProtect.canBreak(x, y, z, world, true);
    }

    public static boolean canBreak(int x, int y, int z, World world, boolean denyContainers) {
        Block block = world.func_147439_a(x, y, z);
        return BlockProtect.canBreak(block, world, denyContainers);
    }

    public static boolean checkModsForBreakOK(World world, int x, int y, int z, EntityLivingBase entity) {
        return BlockProtect.checkModsForBreakOK(world, x, y, z, world.func_147439_a(x, y, z), world.func_72805_g(x, y, z), entity);
    }

    public static boolean checkModsForBreakOK(World world, int x, int y, int z, Block block, int meta, EntityLivingBase entity) {
        boolean allowBreak;
        boolean bl = allowBreak = block.func_149712_f(world, x, y, z) != -1.0f;
        if (allowBreak && entity != null && entity instanceof EntityPlayer && Config.instance().allowBlockBreakEvents) {
            BlockEvent.BreakEvent event = new BlockEvent.BreakEvent(x, y, z, world, block, meta, (EntityPlayer)entity);
            event.setCanceled(false);
            MinecraftForge.EVENT_BUS.post((Event)event);
            allowBreak = !event.isCanceled();
        }
        return allowBreak;
    }
}

