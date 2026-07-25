/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package thaumcraft.common.lib.world;

import java.util.Random;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileManaPod;

public class WorldGenManaPods
extends WorldGenerator {
    public boolean func_76484_a(World par1World, Random par2Random, int x, int y, int z) {
        int l = x;
        int i1 = z;
        while (y < Math.min(128, par1World.func_72976_f(x, z))) {
            if (par1World.func_147437_c(x, y, z) && par1World.func_147437_c(x, y - 1, z)) {
                if (ConfigBlocks.blockManaPod.func_149707_d(par1World, x, y, z, 0)) {
                    par1World.func_147465_d(x, y, z, ConfigBlocks.blockManaPod, 2 + par2Random.nextInt(5), 2);
                    TileEntity tile = par1World.func_147438_o(x, y, z);
                    if (tile == null || !(tile instanceof TileManaPod)) break;
                    ((TileManaPod)tile).checkGrowth();
                    break;
                }
            } else {
                x = l + par2Random.nextInt(4) - par2Random.nextInt(4);
                z = i1 + par2Random.nextInt(4) - par2Random.nextInt(4);
            }
            ++y;
        }
        return true;
    }
}

