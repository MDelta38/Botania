/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.world.World
 */
package thaumcraft.common.lib.world.dim;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import thaumcraft.common.lib.world.dim.Cell;
import thaumcraft.common.lib.world.dim.CellLoc;
import thaumcraft.common.lib.world.dim.GenBossRoom;
import thaumcraft.common.lib.world.dim.GenCommon;
import thaumcraft.common.lib.world.dim.GenKeyRoom;
import thaumcraft.common.lib.world.dim.GenLibraryRoom;
import thaumcraft.common.lib.world.dim.GenNestRoom;
import thaumcraft.common.lib.world.dim.GenPassage;
import thaumcraft.common.lib.world.dim.GenPortal;

public class MazeHandler {
    public static ConcurrentHashMap<CellLoc, Short> labyrinth = new ConcurrentHashMap();

    public static synchronized void putToHashMap(CellLoc key, Cell cell) {
        labyrinth.put(key, cell.pack());
    }

    public static synchronized void putToHashMapRaw(CellLoc key, short cell) {
        labyrinth.put(key, cell);
    }

    public static synchronized Cell getFromHashMap(CellLoc key) {
        return labyrinth.containsKey(key) ? new Cell(labyrinth.get(key)) : null;
    }

    public static synchronized void removeFromHashMap(CellLoc key) {
        labyrinth.remove(key);
    }

    public static synchronized short getFromHashMapRaw(CellLoc key) {
        return labyrinth.containsKey(key) ? labyrinth.get(key) : (short)0;
    }

    public static synchronized void clearHashMap() {
        labyrinth.clear();
    }

    private static void readNBT(NBTTagCompound nbt) {
        NBTTagList tagList = nbt.func_150295_c("cells", 10);
        for (int a = 0; a < tagList.func_74745_c(); ++a) {
            NBTTagCompound cell = tagList.func_150305_b(a);
            int x = cell.func_74762_e("x");
            int z = cell.func_74762_e("z");
            short v = cell.func_74765_d("cell");
            MazeHandler.putToHashMapRaw(new CellLoc(x, z), v);
        }
    }

    private static NBTTagCompound writeNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        NBTTagList tagList = new NBTTagList();
        for (CellLoc loc : labyrinth.keySet()) {
            short v = MazeHandler.getFromHashMapRaw(loc);
            if (v <= 0) continue;
            NBTTagCompound cell = new NBTTagCompound();
            cell.func_74768_a("x", loc.x);
            cell.func_74768_a("z", loc.z);
            cell.func_74777_a("cell", v);
            tagList.func_74742_a((NBTBase)cell);
        }
        nbt.func_74782_a("cells", (NBTBase)tagList);
        return nbt;
    }

    public static void loadMaze(World world) {
        MazeHandler.clearHashMap();
        File file1 = new File(world.func_72860_G().func_75765_b(), "labyrinth.dat");
        if (file1.exists()) {
            try {
                NBTTagCompound nbttagcompound = CompressedStreamTools.func_74796_a((InputStream)new FileInputStream(file1));
                NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("Data");
                MazeHandler.readNBT(nbttagcompound1);
                return;
            }
            catch (Exception exception1) {
                exception1.printStackTrace();
            }
        }
        if ((file1 = new File(world.func_72860_G().func_75765_b(), "labyrinth.dat_old")).exists()) {
            try {
                NBTTagCompound nbttagcompound = CompressedStreamTools.func_74796_a((InputStream)new FileInputStream(file1));
                NBTTagCompound nbttagcompound1 = nbttagcompound.func_74775_l("Data");
                MazeHandler.readNBT(nbttagcompound1);
                return;
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public static void saveMaze(World world) {
        NBTTagCompound nbttagcompound = MazeHandler.writeNBT();
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74782_a("Data", (NBTBase)nbttagcompound);
        try {
            File file1 = new File(world.func_72860_G().func_75765_b(), "labyrinth.dat_new");
            File file2 = new File(world.func_72860_G().func_75765_b(), "labyrinth.dat_old");
            File file3 = new File(world.func_72860_G().func_75765_b(), "labyrinth.dat");
            CompressedStreamTools.func_74799_a((NBTTagCompound)nbttagcompound1, (OutputStream)new FileOutputStream(file1));
            if (file2.exists()) {
                file2.delete();
            }
            file3.renameTo(file2);
            if (file3.exists()) {
                file3.delete();
            }
            file1.renameTo(file3);
            if (file1.exists()) {
                file1.delete();
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static boolean mazesInRange(int chunkX, int chunkZ, int w, int h) {
        for (int x = -w; x <= w; ++x) {
            for (int z = -h; z <= h; ++z) {
                if (MazeHandler.getFromHashMap(new CellLoc(chunkX + x, chunkZ + z)) == null) continue;
                return true;
            }
        }
        return false;
    }

    public static void generateEldritch(World world, Random random, int cx, int cz) {
        CellLoc loc = new CellLoc(cx, cz);
        Cell cell = MazeHandler.getFromHashMap(loc);
        if (cell != null) {
            switch (cell.feature) {
                case 1: {
                    GenPortal.generatePortal(world, random, cx, cz, 50, cell);
                    break;
                }
                case 2: 
                case 3: 
                case 4: 
                case 5: {
                    GenBossRoom.generateRoom(world, random, cx, cz, 50, cell);
                    break;
                }
                case 6: {
                    GenKeyRoom.generateRoom(world, random, cx, cz, 50, cell);
                    break;
                }
                case 7: {
                    GenNestRoom.generateRoom(world, random, cx, cz, 50, cell);
                    break;
                }
                case 8: {
                    GenLibraryRoom.generateRoom(world, random, cx, cz, 50, cell);
                    break;
                }
                default: {
                    MazeHandler.generatePassage(world, random, cx, cz, 50, cell);
                }
            }
            GenCommon.processDecorations(world);
        }
    }

    private static void generatePassage(World world, Random random, int cx, int cz, int y, Cell cell) {
        switch (random.nextInt(1)) {
            case 0: {
                GenPassage.generateDefaultPassage(world, random, cx, cz, y, cell);
            }
        }
    }
}

