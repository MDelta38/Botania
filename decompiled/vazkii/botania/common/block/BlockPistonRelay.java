/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$Phase
 *  cpw.mods.fml.common.gameevent.TickEvent$Type
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldSavedData
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.event.world.WorldEvent$Load
 *  net.minecraftforge.event.world.WorldEvent$Unload
 */
package vazkii.botania.common.block;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.world.WorldEvent;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.common.block.BlockMod;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockPistonRelay
extends BlockMod
implements IWandable,
ILexiconable {
    public static Map<String, String> playerPositions = new HashMap<String, String>();
    public static Map<String, String> mappedPositions = new HashMap<String, String>();
    static List<String> removeThese = new ArrayList<String>();
    static List<String> checkedCoords = new ArrayList<String>();
    static Map<String, Integer> coordsToCheck = new HashMap<String, Integer>();

    public BlockPistonRelay() {
        super(Material.field_151572_C);
        this.func_149663_c("pistonRelay");
        this.func_149711_c(2.0f);
        this.func_149752_b(10.0f);
        this.func_149672_a(field_149777_j);
        MinecraftForge.EVENT_BUS.register((Object)this);
        FMLCommonHandler.instance().bus().register((Object)this);
    }

    public int quantityDropped(int meta, int fortune, Random random) {
        return 0;
    }

    public boolean func_149662_c() {
        return false;
    }

    public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        BlockPistonRelay.mapCoords(par1World.field_73011_w.field_76574_g, par2, par3, par4, 2);
    }

    public static String getCoordsAsString(int world, int x, int y, int z) {
        return world + ":" + x + ":" + y + ":" + z;
    }

    static void mapCoords(int world, int x, int y, int z, int time) {
        String coords = BlockPistonRelay.getCoordsAsString(world, x, y, z);
        coordsToCheck.put(coords, time);
    }

    static void decrCoords(String key) {
        int time = BlockPistonRelay.getTimeInCoords(key);
        if (time <= 0) {
            removeThese.add(key);
        } else {
            coordsToCheck.put(key, time - 1);
        }
    }

    static int getTimeInCoords(String key) {
        return coordsToCheck.get(key);
    }

    static Block getBlockAt(String key) {
        MinecraftServer server = MinecraftServer.func_71276_C();
        if (server == null) {
            return Blocks.field_150350_a;
        }
        String[] tokens = key.split(":");
        int worldId = Integer.parseInt(tokens[0]);
        int x = Integer.parseInt(tokens[1]);
        int y = Integer.parseInt(tokens[2]);
        int z = Integer.parseInt(tokens[3]);
        WorldServer world = server.func_71218_a(worldId);
        return world.func_147439_a(x, y, z);
    }

    static int getBlockMetaAt(String key) {
        MinecraftServer server = MinecraftServer.func_71276_C();
        if (server == null) {
            return 0;
        }
        String[] tokens = key.split(":");
        int worldId = Integer.parseInt(tokens[0]);
        int x = Integer.parseInt(tokens[1]);
        int y = Integer.parseInt(tokens[2]);
        int z = Integer.parseInt(tokens[3]);
        WorldServer world = server.func_71218_a(worldId);
        return world.func_72805_g(x, y, z);
    }

    @Override
    public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side) {
        if (player == null) {
            return false;
        }
        if (!player.func_70093_af()) {
            playerPositions.put(player.func_70005_c_(), BlockPistonRelay.getCoordsAsString(world.field_73011_w.field_76574_g, x, y, z));
            world.func_72908_a((double)x, (double)y, (double)z, "botania:ding", 0.5f, 1.0f);
        } else {
            this.func_149642_a(world, x, y, z, new ItemStack((Block)this));
            world.func_147468_f(x, y, z);
            if (!world.field_72995_K) {
                world.func_72926_e(2001, x, y, z, Block.func_149682_b((Block)this));
            }
        }
        return true;
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        WorldData.get(event.world);
    }

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {
        WorldData.get(event.world).func_76185_a();
    }

    @SubscribeEvent
    public void tickEnd(TickEvent event) {
        if (event.type == TickEvent.Type.SERVER && event.phase == TickEvent.Phase.END) {
            ArrayList<String> coordsToCheckCopy = new ArrayList<String>(coordsToCheck.keySet());
            for (String s : coordsToCheckCopy) {
                Block block;
                BlockPistonRelay.decrCoords(s);
                if (checkedCoords.contains(s) || (block = BlockPistonRelay.getBlockAt(s)) != Blocks.field_150326_M) continue;
                int meta = BlockPistonRelay.getBlockMetaAt(s);
                boolean sticky = (meta & 8) == 8;
                ForgeDirection dir = ForgeDirection.getOrientation((int)(meta & 0xFFFFFFF7));
                MinecraftServer server = MinecraftServer.func_71276_C();
                if (server == null || BlockPistonRelay.getTimeInCoords(s) != 0) continue;
                String[] tokens = s.split(":");
                int worldId = Integer.parseInt(tokens[0]);
                int x = Integer.parseInt(tokens[1]);
                int y = Integer.parseInt(tokens[2]);
                int z = Integer.parseInt(tokens[3]);
                WorldServer world = server.func_71218_a(worldId);
                if (world.func_147437_c(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ)) {
                    world.func_147449_b(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ, ModBlocks.pistonRelay);
                } else if (!world.field_72995_K) {
                    ItemStack stack = new ItemStack(ModBlocks.pistonRelay);
                    world.func_72838_d((Entity)new EntityItem((World)world, (double)(x + dir.offsetX), (double)(y + dir.offsetY), (double)(z + dir.offsetZ), stack));
                }
                checkedCoords.add(s);
                String newPos = BlockPistonRelay.getCoordsAsString(world.field_73011_w.field_76574_g, x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
                if (!mappedPositions.containsKey(s)) continue;
                String pos = mappedPositions.get(s);
                String[] tokens2 = pos.split(":");
                int worldId2 = Integer.parseInt(tokens2[0]);
                int x2 = Integer.parseInt(tokens2[1]);
                int y2 = Integer.parseInt(tokens2[2]);
                int z2 = Integer.parseInt(tokens2[3]);
                WorldServer world2 = server.func_71218_a(worldId2);
                Block srcBlock = world2.func_147439_a(x2, y2, z2);
                int srcMeta = world2.func_72805_g(x2, y2, z2);
                TileEntity tile = world2.func_147438_o(x2, y2, z2);
                Material mat = srcBlock.func_149688_o();
                if (!sticky && tile == null && mat.func_76227_m() == 0 && srcBlock.func_149712_f((World)world2, x2, y2, z2) != -1.0f && !srcBlock.isAir((IBlockAccess)world2, x2, y2, z2)) {
                    Material destMat = world2.func_147439_a(x2 + dir.offsetX, y2 + dir.offsetY, z2 + dir.offsetZ).func_149688_o();
                    if (world2.func_147437_c(x2 + dir.offsetX, y2 + dir.offsetY, z2 + dir.offsetZ) || destMat.func_76222_j()) {
                        world2.func_147449_b(x2, y2, z2, Blocks.field_150350_a);
                        world2.func_147465_d(x2 + dir.offsetX, y2 + dir.offsetY, z2 + dir.offsetZ, srcBlock, srcMeta, 3);
                        mappedPositions.put(s, BlockPistonRelay.getCoordsAsString(world2.field_73011_w.field_76574_g, x2 + dir.offsetX, y2 + dir.offsetY, z2 + dir.offsetZ));
                    }
                }
                pos = mappedPositions.get(s);
                mappedPositions.remove(s);
                mappedPositions.put(newPos, pos);
                this.save((World)world2);
            }
        }
        ArrayList<String> remove = new ArrayList<String>(removeThese);
        for (String s : remove) {
            coordsToCheck.remove(s);
            if (!checkedCoords.contains(s)) continue;
            checkedCoords.remove(s);
        }
        removeThese.clear();
    }

    public void save(World world) {
        WorldData data = WorldData.get(world);
        if (data != null) {
            data.func_76185_a();
        }
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.pistonRelay;
    }

    public static class WorldData
    extends WorldSavedData {
        private static final String ID = "PistonRelayPairs";

        public WorldData(String id) {
            super(id);
        }

        public void func_76184_a(NBTTagCompound nbttagcompound) {
            mappedPositions.clear();
            Set tags = nbttagcompound.func_150296_c();
            for (String key : tags) {
                NBTBase tag = nbttagcompound.func_74781_a(key);
                if (!(tag instanceof NBTTagString)) continue;
                String value = ((NBTTagString)tag).func_150285_a_();
                mappedPositions.put(key, value);
            }
        }

        public void func_76187_b(NBTTagCompound nbttagcompound) {
            for (String s : mappedPositions.keySet()) {
                nbttagcompound.func_74778_a(s, mappedPositions.get(s));
            }
        }

        public static WorldData get(World world) {
            if (world.field_72988_C == null) {
                return null;
            }
            WorldData data = (WorldData)world.field_72988_C.func_75742_a(WorldData.class, ID);
            if (data == null) {
                data = new WorldData(ID);
                data.func_76185_a();
                world.field_72988_C.func_75745_a(ID, (WorldSavedData)data);
            }
            return data;
        }
    }
}

