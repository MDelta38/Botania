/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFalling
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.util.IProgressUpdate
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldType
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.terraingen.PopulateChunkEvent$Post
 *  net.minecraftforge.event.terraingen.PopulateChunkEvent$Pre
 */
package thaumcraft.common.lib.world.dim;

import cpw.mods.fml.common.eventhandler.Event;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

public class ChunkProviderOuter
implements IChunkProvider {
    private Random rand;
    private World worldObj;
    private WorldType worldType;
    private BiomeGenBase[] biomesForGeneration;

    public ChunkProviderOuter(World p_i2006_1_, long p_i2006_2_, boolean p_i2006_4_) {
        this.worldObj = p_i2006_1_;
        this.worldType = p_i2006_1_.func_72912_H().func_76067_t();
        this.rand = new Random(p_i2006_2_);
    }

    public Chunk func_73158_c(int p_73158_1_, int p_73158_2_) {
        return this.func_73154_d(p_73158_1_, p_73158_2_);
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

    public boolean func_73149_a(int p_73149_1_, int p_73149_2_) {
        return true;
    }

    public void func_73153_a(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_) {
        BlockFalling.field_149832_M = true;
        MinecraftForge.EVENT_BUS.post((Event)new PopulateChunkEvent.Pre(p_73153_1_, this.worldObj, this.worldObj.field_73012_v, p_73153_2_, p_73153_3_, false));
        int k = p_73153_2_ * 16;
        int l = p_73153_3_ * 16;
        BiomeGenBase biomegenbase = this.worldObj.func_72807_a(k + 16, l + 16);
        biomegenbase.func_76728_a(this.worldObj, this.worldObj.field_73012_v, k, l);
        MinecraftForge.EVENT_BUS.post((Event)new PopulateChunkEvent.Post(p_73153_1_, this.worldObj, this.worldObj.field_73012_v, p_73153_2_, p_73153_3_, false));
        BlockFalling.field_149832_M = false;
    }

    public boolean func_73151_a(boolean p_73151_1_, IProgressUpdate p_73151_2_) {
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
        return "RandomLevelSource";
    }

    public List func_73155_a(EnumCreatureType p_73155_1_, int p_73155_2_, int p_73155_3_, int p_73155_4_) {
        BiomeGenBase biomegenbase = this.worldObj.func_72807_a(p_73155_2_, p_73155_4_);
        return biomegenbase.func_76747_a(p_73155_1_);
    }

    public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_) {
        return null;
    }

    public int func_73152_e() {
        return 0;
    }

    public void func_82695_e(int p_82695_1_, int p_82695_2_) {
    }
}

