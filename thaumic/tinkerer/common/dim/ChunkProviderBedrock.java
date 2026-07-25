/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.IProgressUpdate
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.chunk.storage.ExtendedBlockStorage
 *  net.minecraft.world.gen.FlatLayerInfo
 */
package thaumic.tinkerer.common.dim;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;
import net.minecraft.world.gen.FlatLayerInfo;
import thaumic.tinkerer.common.dim.OreClusterGenerator;

public class ChunkProviderBedrock
implements IChunkProvider {
    private final byte[] cachedBlockIDs = new byte[256];
    private final byte[] cachedBlockMetadata = new byte[256];
    private World worldObj;
    private Random random;
    private OreClusterGenerator generator;

    public ChunkProviderBedrock(World par1World, long par2, boolean par4) {
        this.worldObj = par1World;
        this.random = new Random(par2);
        this.generator = new OreClusterGenerator();
        FlatLayerInfo flatlayerinfo = new FlatLayerInfo(256, Blocks.field_150357_h);
        for (int j = flatlayerinfo.func_82656_d(); j < flatlayerinfo.func_82656_d() + flatlayerinfo.func_82657_a(); ++j) {
            this.cachedBlockIDs[j] = (byte)(Block.func_149682_b((Block)flatlayerinfo.func_151536_b()) & 0xFF);
            this.cachedBlockMetadata[j] = (byte)flatlayerinfo.func_82658_c();
        }
    }

    public Chunk func_73158_c(int par1, int par2) {
        return this.func_73154_d(par1, par2);
    }

    public Chunk func_73154_d(int par1, int par2) {
        Chunk chunk = new Chunk(this.worldObj, par1, par2);
        for (int k = 0; k < this.cachedBlockIDs.length; ++k) {
            int l = k >> 4;
            ExtendedBlockStorage extendedblockstorage = chunk.func_76587_i()[l];
            if (extendedblockstorage == null) {
                chunk.func_76587_i()[l] = extendedblockstorage = new ExtendedBlockStorage(k, !this.worldObj.field_73011_w.field_76576_e);
            }
            for (int i1 = 0; i1 < 16; ++i1) {
                for (int j1 = 0; j1 < 16; ++j1) {
                    extendedblockstorage.func_150818_a(i1, k & 0xF, j1, Block.func_149729_e((int)(this.cachedBlockIDs[k] & 0xFF)));
                    extendedblockstorage.func_76654_b(i1, k & 0xF, j1, (int)this.cachedBlockMetadata[k]);
                }
            }
        }
        chunk.func_76603_b();
        BiomeGenBase[] abiomegenbase = this.worldObj.func_72959_q().func_76933_b(null, par1 * 16, par2 * 16, 16, 16);
        byte[] abyte = chunk.func_76605_m();
        for (int k1 = 0; k1 < abyte.length; ++k1) {
            abyte[k1] = (byte)abiomegenbase[k1].field_76756_M;
        }
        chunk.func_76603_b();
        return chunk;
    }

    public boolean func_73149_a(int par1, int par2) {
        return true;
    }

    public void func_73153_a(IChunkProvider par1IChunkProvider, int par2, int par3) {
        int k = par2 * 16;
        int l = par3 * 16;
        BiomeGenBase biomegenbase = this.worldObj.func_72807_a(k + 16, l + 16);
        boolean flag = false;
        this.random.setSeed(this.worldObj.func_72905_C());
        long i1 = this.random.nextLong() / 2L * 2L + 1L;
        long j1 = this.random.nextLong() / 2L * 2L + 1L;
        this.random.setSeed((long)par2 * i1 + (long)par3 * j1 ^ this.worldObj.func_72905_C());
        if (this.generator != null) {
            int l1 = k + this.random.nextInt(16) + 8;
            int k1 = this.random.nextInt(128);
            int i2 = l + this.random.nextInt(16) + 8;
            this.generator.generate(this.random, par2, par3, this.worldObj, this, this);
        }
    }

    public boolean func_73151_a(boolean par1, IProgressUpdate par2IProgressUpdate) {
        return true;
    }

    public void func_104112_b() {
    }

    public boolean func_73156_b() {
        return false;
    }

    public boolean func_73157_c() {
        return true;
    }

    public String func_73148_d() {
        return "Bedrock";
    }

    public List func_73155_a(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4) {
        BiomeGenBase biomegenbase = this.worldObj.func_72807_a(par2, par4);
        return biomegenbase == null ? null : biomegenbase.func_76747_a(par1EnumCreatureType);
    }

    public ChunkPosition func_147416_a(World var1, String var2, int var3, int var4, int var5) {
        return null;
    }

    public int func_73152_e() {
        return 0;
    }

    public void func_82695_e(int par1, int par2) {
    }
}

