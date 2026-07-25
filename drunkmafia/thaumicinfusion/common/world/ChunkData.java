/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.ChunkCoordIntPair
 */
package drunkmafia.thaumicinfusion.common.world;

import drunkmafia.thaumicinfusion.common.world.ISavable;
import drunkmafia.thaumicinfusion.common.world.SavableHelper;
import drunkmafia.thaumicinfusion.common.world.data.BlockSavable;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.ChunkCoordIntPair;

public class ChunkData
implements ISavable {
    private final List<BlockSavable>[][][] blockdata = new ArrayList[16][256][16];
    public int instability;
    protected ChunkCoordIntPair chunkPos;

    public ChunkData() {
    }

    public ChunkData(ChunkCoordIntPair chunkPos) {
        this.chunkPos = chunkPos;
    }

    public BlockSavable[] getAllBlocks() {
        ArrayList<BlockSavable> allData = new ArrayList<BlockSavable>();
        for (int x = 0; x < this.blockdata.length; ++x) {
            for (int y = 0; y < this.blockdata[x].length; ++y) {
                for (int z = 0; z < this.blockdata[x][y].length; ++z) {
                    List<BlockSavable> savables = this.blockdata[x][y][z];
                    if (savables == null) continue;
                    for (BlockSavable data : savables) {
                        allData.add(data);
                    }
                }
            }
        }
        return allData.toArray(new BlockSavable[allData.size()]);
    }

    public boolean addBlock(BlockSavable data, int x, int y, int z) {
        return y >= 0 && y <= 256 && (this.blockdata[x & 0xF][y][z & 0xF] != null ? this.blockdata[x & 0xF][y][z & 0xF] : (this.blockdata[x & 0xF][y][z & 0xF] = new ArrayList<BlockSavable>())).add(data);
    }

    public void removeBlock(int x, int y, int z) {
        this.blockdata[x & 0xF][y][z & 0xF] = null;
    }

    public boolean removeData(Class<? extends BlockSavable> type, int x, int y, int z) {
        if (y < 0 || y > 256) {
            return false;
        }
        List<BlockSavable> datas = this.blockdata[x & 0xF][y][z & 0xF];
        if (datas == null) {
            return false;
        }
        for (int i = 0; i < datas.size(); ++i) {
            BlockSavable block = datas.get(i);
            if (!type.isAssignableFrom(block.getClass())) continue;
            datas.remove(block);
        }
        if (datas.size() == 0) {
            this.blockdata[x & 0xF][y][z & 0xF] = null;
        }
        return true;
    }

    public <T> T getBlock(Class<T> type, int x, int y, int z) {
        if (y < 0 || y >= 256) {
            return null;
        }
        if (this.blockdata[x & 0xF][y][z & 0xF] != null) {
            for (BlockSavable block : this.blockdata[x & 0xF][y][z & 0xF]) {
                if (!type.isAssignableFrom(block.getClass())) continue;
                return type.cast(block);
            }
        }
        return null;
    }

    public ChunkCoordIntPair getChunkPos() {
        return this.chunkPos;
    }

    @Override
    public void writeNBT(NBTTagCompound tagCompound) {
        tagCompound.func_74768_a("ChunkX", this.chunkPos.field_77276_a);
        tagCompound.func_74768_a("ChunkZ", this.chunkPos.field_77275_b);
        tagCompound.func_74768_a("instability", this.instability);
        for (int x = 0; x < this.blockdata.length; ++x) {
            for (int y = 0; y < this.blockdata[x].length; ++y) {
                for (int z = 0; z < this.blockdata[x][y].length; ++z) {
                    if (this.blockdata[x][y][z] == null) continue;
                    tagCompound.func_74768_a("SIZE X:" + x + "Y:" + y + "Z:" + z, this.blockdata[x][y][z].size());
                    for (int i = 0; i < this.blockdata[x][y][z].size(); ++i) {
                        tagCompound.func_74782_a("Chunk:" + ChunkCoordIntPair.func_77272_a((int)this.chunkPos.field_77276_a, (int)this.chunkPos.field_77275_b) + "X:" + x + "Y:" + y + "Z:" + z + "ID:" + i, (NBTBase)SavableHelper.saveDataToNBT(this.blockdata[x][y][z].get(i)));
                    }
                }
            }
        }
    }

    @Override
    public void readNBT(NBTTagCompound tagCompound) {
        this.chunkPos = new ChunkCoordIntPair(tagCompound.func_74762_e("ChunkX"), tagCompound.func_74762_e("ChunkZ"));
        this.instability = tagCompound.func_74762_e("instability");
        for (int x = 0; x < this.blockdata.length; ++x) {
            for (int y = 0; y < this.blockdata[x].length; ++y) {
                for (int z = 0; z < this.blockdata[x][y].length; ++z) {
                    if (!tagCompound.func_74764_b("SIZE X:" + x + "Y:" + y + "Z:" + z)) continue;
                    int Size = tagCompound.func_74762_e("SIZE X:" + x + "Y:" + y + "Z:" + z);
                    ArrayList<BlockSavable> datas = new ArrayList<BlockSavable>();
                    for (int i = 0; i < Size; ++i) {
                        datas.add((BlockSavable)SavableHelper.loadDataFromNBT(tagCompound.func_74775_l("Chunk:" + ChunkCoordIntPair.func_77272_a((int)this.chunkPos.field_77276_a, (int)this.chunkPos.field_77275_b) + "X:" + x + "Y:" + y + "Z:" + z + "ID:" + i)));
                    }
                    this.blockdata[x][y][z] = datas;
                }
            }
        }
    }
}

