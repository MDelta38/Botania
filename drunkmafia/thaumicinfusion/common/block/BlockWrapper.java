/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  thaumcraft.api.WorldCoordinates
 */
package drunkmafia.thaumicinfusion.common.block;

import drunkmafia.thaumicinfusion.common.util.IBlockHook;
import drunkmafia.thaumicinfusion.common.world.TIWorldData;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.WorldCoordinates;

public final class BlockWrapper {
    public static Block block;
    private static IBlockHook lastHook;
    private static int lastX;
    private static int lastY;
    private static int lastZ;
    private static int lastMethod;

    public static boolean hasWorldData(IBlockAccess access, int x, int y, int z, Block block, int methodHash) {
        World world = TIWorldData.getWorld(access);
        if (world == null || block == Blocks.field_150350_a) {
            return false;
        }
        TIWorldData worldData = TIWorldData.getWorldData(world);
        if (worldData == null) {
            return false;
        }
        IBlockHook hook = worldData.getBlock(IBlockHook.class, new WorldCoordinates(x, y, z, world.field_73011_w.field_76574_g));
        if (hook == null) {
            return false;
        }
        for (int method : hook.hookMethods(block)) {
            if (method != methodHash) continue;
            BlockWrapper.block = hook.getBlock(methodHash);
            lastHook = hook;
            lastX = x;
            lastY = y;
            lastZ = z;
            lastMethod = methodHash;
            return BlockWrapper.block != null;
        }
        return false;
    }

    public static boolean overrideBlockFunctionality(IBlockAccess access, int x, int y, int z, int methodName) {
        World world = TIWorldData.getWorld(access);
        IBlockHook hook = lastHook == null || lastX == x || lastY == y || lastZ == z ? TIWorldData.getWorldData(world).getBlock(IBlockHook.class, new WorldCoordinates(x, y, z, world.field_73011_w.field_76574_g)) : lastHook;
        return hook != null && hook.shouldOverride(methodName);
    }
}

