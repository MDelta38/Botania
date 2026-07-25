/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.util.IProgressUpdate
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldType
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 */
package com.kentington.thaumichorizons.common.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderPocketPlane
implements IChunkProvider {
    private Random rand;
    private World worldObj;
    private WorldType worldType;
    private BiomeGenBase[] biomesForGeneration;

    public ChunkProviderPocketPlane(World p_i2005_1_, long p_i2005_2_) {
        this.worldObj = p_i2005_1_;
        this.worldType = p_i2005_1_.func_72912_H().func_76067_t();
        this.rand = new Random(p_i2005_2_);
    }

    public boolean func_73149_a(int p_73149_1_, int p_73149_2_) {
        return true;
    }

    public Chunk func_73154_d(int p_73154_1_, int p_73154_2_) {
        this.rand.setSeed((long)p_73154_1_ * 341873128712L + (long)p_73154_2_ * 132897987541L);
        Block[] ablock = new Block[32768];
        byte[] meta = new byte[ablock.length];
        for (int k = 0; k < 16; ++k) {
            for (int l = 0; l < 16; ++l) {
                for (int j1 = 127; j1 >= 0; --j1) {
                    int k1 = (l * 16 + k) * 128 + j1;
                    ablock[k1] = null;
                    meta[k1] = 0;
                }
            }
        }
        this.biomesForGeneration = this.worldObj.func_72959_q().func_76933_b(this.biomesForGeneration, p_73154_1_ * 16, p_73154_2_ * 16, 16, 16);
        Chunk chunk = new Chunk(this.worldObj, ablock, meta, p_73154_1_, p_73154_2_);
        byte[] abyte = chunk.func_76605_m();
        for (int k = 0; k < abyte.length; ++k) {
            abyte[k] = (byte)this.biomesForGeneration[k].field_76756_M;
        }
        chunk.func_76603_b();
        return chunk;
    }

    public Chunk func_73158_c(int p_73158_1_, int p_73158_2_) {
        return this.func_73154_d(p_73158_1_, p_73158_2_);
    }

    public void func_73153_a(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {
    }

    public boolean func_73151_a(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
        return true;
    }

    public boolean func_73156_b() {
        return false;
    }

    public boolean func_73157_c() {
        return true;
    }

    public String func_73148_d() {
        return "RandomLevelSource";
    }

    public List func_73155_a(EnumCreatureType p_73155_1_, int p_73155_2_, int p_73155_3_, int p_73155_4_) {
        return new ArrayList();
    }

    public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_) {
        return null;
    }

    public int func_73152_e() {
        return 0;
    }

    public void func_82695_e(int p_82695_1_, int p_82695_2_) {
    }

    public void func_104112_b() {
    }
}

