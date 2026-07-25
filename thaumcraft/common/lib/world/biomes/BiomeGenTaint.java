/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 *  net.minecraft.world.gen.feature.WorldGenBlockBlob
 */
package thaumcraft.common.lib.world.biomes;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.entities.monster.EntityTaintacle;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
import thaumcraft.common.lib.world.WorldGenBigMagicTree;

public class BiomeGenTaint
extends BiomeGenBase {
    public static WorldGenBlockBlob blobs = null;
    protected WorldGenBigMagicTree bigTree = new WorldGenBigMagicTree(false);

    public BiomeGenTaint(int par1) {
        super(par1);
        this.field_76760_I.field_76832_z = 2;
        this.field_76760_I.field_76802_A = -999;
        this.field_76760_I.field_76803_B = 2;
        this.field_76760_I.field_76799_E = -999;
        this.func_76735_a("Tainted Land");
        this.func_76739_b(7160201);
        this.field_76762_K.clear();
        this.field_76761_J.clear();
        this.field_76755_L.clear();
        if (Config.spawnTaintacle) {
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityTaintacle.class, 1, 1, 1));
        }
    }

    public void func_76728_a(World world, Random random, int x, int z) {
        super.func_76728_a(world, random, x, z);
        this.decorateSpecial(world, random, x, z);
    }

    public WorldGenAbstractTree func_150567_a(Random par1Random) {
        return par1Random.nextInt(8) == 0 ? this.bigTree : super.func_150567_a(par1Random);
    }

    public void decorateSpecial(World world, Random random, int x, int z) {
        int zz;
        int yy;
        int xx;
        int a;
        int k = random.nextInt(3);
        for (int l = 0; l < k; ++l) {
            int i1 = x + random.nextInt(16) + 8;
            int j1 = z + random.nextInt(16) + 8;
            int k1 = world.func_72976_f(i1, j1);
            blobs.func_76484_a(world, random, i1, k1, j1);
        }
        for (a = 0; a < 10; ++a) {
            xx = x + random.nextInt(16);
            Block l1 = world.func_147439_a(xx, yy = world.func_72976_f(xx, zz = z + random.nextInt(16)) - 1, zz);
            if (l1 == Blocks.field_150350_a) continue;
            if (l1 == Blocks.field_150349_c) {
                world.func_147465_d(xx, yy + 1, zz, ConfigBlocks.blockTaintFibres, 0, 2);
                continue;
            }
            if (!l1.isReplaceable((IBlockAccess)world, xx, yy, zz) || world.func_147439_a(xx, yy - 1, zz) != Blocks.field_150349_c) continue;
            world.func_147465_d(xx, yy, zz, ConfigBlocks.blockTaintFibres, 0, 2);
        }
        for (a = 0; a < 8; ++a) {
            xx = x + random.nextInt(16);
            zz = z + random.nextInt(16);
            yy = Utils.getFirstUncoveredBlockHeight(world, xx, zz) + 1;
            if (world.func_72807_a(xx, zz) != this) {
                Utils.setBiomeAt(world, xx, zz, ThaumcraftWorldGenerator.biomeTaint);
            }
            if (!world.func_147437_c(xx, yy, zz) || !BlockUtils.isAdjacentToSolidBlock(world, xx, yy, zz)) continue;
            world.func_147465_d(xx, yy, zz, ConfigBlocks.blockTaintFibres, 0, 2);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public int func_150558_b(int x, int y, int z) {
        return 7160201;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_150571_c(int x, int y, int z) {
        return 8154503;
    }

    public int func_76731_a(float par1) {
        return 8144127;
    }

    public int getWaterColorMultiplier() {
        return 0xCC1188;
    }

    public BiomeGenBase func_150566_k() {
        return null;
    }
}

