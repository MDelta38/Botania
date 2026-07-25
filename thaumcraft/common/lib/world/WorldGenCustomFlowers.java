/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package thaumcraft.common.lib.world;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCustomFlowers
extends WorldGenerator {
    private Block plantBlock;
    private int plantBlockMeta;

    public WorldGenCustomFlowers(Block bi, int md) {
        this.plantBlock = bi;
        this.plantBlockMeta = md;
    }

    public boolean func_76484_a(World world, Random par2Random, int par3, int par4, int par5) {
        for (int var6 = 0; var6 < 18; ++var6) {
            int var9;
            int var8;
            int var7 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
            if (!world.func_147437_c(var7, var8 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4), var9 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8)) || world.func_147439_a(var7, var8 - 1, var9) != Blocks.field_150349_c && world.func_147439_a(var7, var8 - 1, var9) != Blocks.field_150354_m) continue;
            world.func_147465_d(var7, var8, var9, this.plantBlock, this.plantBlockMeta, 3);
        }
        return true;
    }
}

