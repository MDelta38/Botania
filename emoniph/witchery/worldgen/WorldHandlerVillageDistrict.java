/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.common.eventhandler.Event$Result
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.registry.VillagerRegistry
 *  cpw.mods.fml.common.registry.VillagerRegistry$IVillageCreationHandler
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.gen.structure.MapGenStructureIO
 *  net.minecraft.world.gen.structure.StructureBoundingBox
 *  net.minecraft.world.gen.structure.StructureComponent
 *  net.minecraft.world.gen.structure.StructureVillagePieces$Church
 *  net.minecraft.world.gen.structure.StructureVillagePieces$Field1
 *  net.minecraft.world.gen.structure.StructureVillagePieces$Field2
 *  net.minecraft.world.gen.structure.StructureVillagePieces$Hall
 *  net.minecraft.world.gen.structure.StructureVillagePieces$House1
 *  net.minecraft.world.gen.structure.StructureVillagePieces$House2
 *  net.minecraft.world.gen.structure.StructureVillagePieces$House3
 *  net.minecraft.world.gen.structure.StructureVillagePieces$House4Garden
 *  net.minecraft.world.gen.structure.StructureVillagePieces$Path
 *  net.minecraft.world.gen.structure.StructureVillagePieces$PieceWeight
 *  net.minecraft.world.gen.structure.StructureVillagePieces$Start
 *  net.minecraft.world.gen.structure.StructureVillagePieces$Village
 *  net.minecraft.world.gen.structure.StructureVillagePieces$WoodHut
 *  net.minecraftforge.common.BiomeDictionary
 *  net.minecraftforge.common.BiomeDictionary$Type
 *  net.minecraftforge.common.BiomeManager
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.terraingen.BiomeEvent$GetVillageBlockID
 *  net.minecraftforge.event.terraingen.BiomeEvent$GetVillageBlockMeta
 */
package com.emoniph.witchery.worldgen;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockBaseContainer;
import com.emoniph.witchery.blocks.TileEntityBase;
import com.emoniph.witchery.entity.EntityVillageGuard;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.worldgen.ComponentVillageKeep;
import com.emoniph.witchery.worldgen.ComponentVillageWatchTower;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.VillagerRegistry;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.BiomeEvent;

public class WorldHandlerVillageDistrict
implements VillagerRegistry.IVillageCreationHandler {
    private final Class<? extends StructureVillagePieces.Village> pieceClazz;
    private final int weight;
    private final int quantityMin;
    private final int quantityMax;

    public WorldHandlerVillageDistrict(Class<? extends StructureVillagePieces.Village> clazz, int weight, int min) {
        this(clazz, weight, min, min);
    }

    public WorldHandlerVillageDistrict(Class<? extends StructureVillagePieces.Village> clazz, int weight, int min, int max) {
        this.pieceClazz = clazz;
        this.weight = weight;
        this.quantityMin = min;
        this.quantityMax = max;
    }

    public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random rand, int size) {
        return new StructureVillagePieces.PieceWeight(this.pieceClazz, this.weight, this.quantityMax <= this.quantityMin ? this.quantityMin : this.quantityMin + rand.nextInt(this.quantityMax - this.quantityMin + 1));
    }

    public Class getComponentClass() {
        return this.pieceClazz;
    }

    public Object buildComponent(StructureVillagePieces.PieceWeight weight, StructureVillagePieces.Start startPiece, List pieces, Random rand, int p1, int p2, int p3, int p4, int p5) {
        Object object = null;
        if (this.pieceClazz == StructureVillagePieces.House4Garden.class) {
            object = StructureVillagePieces.House4Garden.func_74912_a((StructureVillagePieces.Start)startPiece, (List)pieces, (Random)rand, (int)p1, (int)p2, (int)p3, (int)p4, (int)p5);
        } else if (this.pieceClazz == StructureVillagePieces.Church.class) {
            object = StructureVillagePieces.Church.func_74919_a((StructureVillagePieces.Start)startPiece, (List)pieces, (Random)rand, (int)p1, (int)p2, (int)p3, (int)p4, (int)p5);
        } else if (this.pieceClazz == StructureVillagePieces.House1.class) {
            object = StructureVillagePieces.House1.func_74898_a((StructureVillagePieces.Start)startPiece, (List)pieces, (Random)rand, (int)p1, (int)p2, (int)p3, (int)p4, (int)p5);
        } else if (this.pieceClazz == StructureVillagePieces.WoodHut.class) {
            object = StructureVillagePieces.WoodHut.func_74908_a((StructureVillagePieces.Start)startPiece, (List)pieces, (Random)rand, (int)p1, (int)p2, (int)p3, (int)p4, (int)p5);
        } else if (this.pieceClazz == StructureVillagePieces.Hall.class) {
            object = StructureVillagePieces.Hall.func_74906_a((StructureVillagePieces.Start)startPiece, (List)pieces, (Random)rand, (int)p1, (int)p2, (int)p3, (int)p4, (int)p5);
        } else if (this.pieceClazz == StructureVillagePieces.Field1.class) {
            object = StructureVillagePieces.Field1.func_74900_a((StructureVillagePieces.Start)startPiece, (List)pieces, (Random)rand, (int)p1, (int)p2, (int)p3, (int)p4, (int)p5);
        } else if (this.pieceClazz == StructureVillagePieces.Field2.class) {
            object = StructureVillagePieces.Field2.func_74902_a((StructureVillagePieces.Start)startPiece, (List)pieces, (Random)rand, (int)p1, (int)p2, (int)p3, (int)p4, (int)p5);
        } else if (this.pieceClazz == StructureVillagePieces.House2.class) {
            object = StructureVillagePieces.House2.func_74915_a((StructureVillagePieces.Start)startPiece, (List)pieces, (Random)rand, (int)p1, (int)p2, (int)p3, (int)p4, (int)p5);
        } else if (this.pieceClazz == StructureVillagePieces.House3.class) {
            object = StructureVillagePieces.House3.func_74921_a((StructureVillagePieces.Start)startPiece, (List)pieces, (Random)rand, (int)p1, (int)p2, (int)p3, (int)p4, (int)p5);
        } else if (this.pieceClazz == Wall.class) {
            object = Wall.func_74921_a(startPiece, pieces, rand, p1, p2, p3, p4, p5);
        } else if (this.pieceClazz == ComponentVillageWatchTower.class) {
            object = ComponentVillageWatchTower.construct(startPiece, pieces, rand, p1, p2, p3, p4, p5);
        } else if (this.pieceClazz == ComponentVillageKeep.class) {
            object = ComponentVillageKeep.construct(startPiece, pieces, rand, p1, p2, p3, p4, p5);
        }
        return object == null ? null : (StructureVillagePieces.Village)object;
    }

    public static void registerComponent(Class<? extends StructureVillagePieces.Village> clazz, int weight, int min, int max) {
        VillagerRegistry.instance().registerVillageCreationHandler((VillagerRegistry.IVillageCreationHandler)new WorldHandlerVillageDistrict(clazz, weight, min, max));
    }

    public static void preInit() {
        try {
            MapGenStructureIO.func_143031_a(Wall.class, (String)"witchery:villagewall");
            MapGenStructureIO.func_143031_a(ComponentVillageKeep.class, (String)"witchery:villagekeep");
            MapGenStructureIO.func_143031_a(ComponentVillageWatchTower.class, (String)"witchery:villagewatchtower");
        }
        catch (Throwable e) {
            // empty catch block
        }
        if (Config.instance().townWallChance > 0) {
            WorldHandlerVillageDistrict.registerComponent(Wall.class, Config.instance().townWallWeight, Config.instance().townWallChance == 2 ? 1 : 0, 1);
        }
        if (Config.instance().townKeepChance > 0) {
            WorldHandlerVillageDistrict.registerComponent(ComponentVillageKeep.class, Config.instance().townKeepWeight, Config.instance().townKeepChance == 2 ? 1 : 0, 1);
        }
        VillagerRegistry register = VillagerRegistry.instance();
        for (Config.Building building : Config.instance().townParts) {
            for (int i = 0; i < building.groups; ++i) {
                register.registerVillageCreationHandler((VillagerRegistry.IVillageCreationHandler)new WorldHandlerVillageDistrict(building.clazz, building.weight, building.min, building.max));
            }
        }
    }

    public static void init() {
        for (BiomeGenBase biome : BiomeGenBase.func_150565_n()) {
            boolean disallowed;
            if (biome == null || BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.WET) || BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.OCEAN) || BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.BEACH) || BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.END) || BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.JUNGLE) || BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.NETHER) || BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.RIVER) || BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.WATER)) continue;
            boolean bl = disallowed = !Config.instance().townAllowSandy && BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.SANDY) || !Config.instance().townAllowPlains && BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.PLAINS) || !Config.instance().townAllowMountain && BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.MOUNTAIN) || !Config.instance().townAllowHills && BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.HILLS) || !Config.instance().townAllowForest && BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.FOREST) || !Config.instance().townAllowSnowy && BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.SNOWY) || !Config.instance().townAllowWasteland && BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.WASTELAND) || !Config.instance().townAllowJungle && BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.JUNGLE) || !Config.instance().townAllowMesa && BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.MESA);
            if (disallowed) continue;
            BiomeManager.addVillageBiome((BiomeGenBase)biome, (boolean)true);
        }
    }

    public static class Wall
    extends StructureVillagePieces.Village {
        private StructureVillagePieces.Start start;
        private List pieces;
        private boolean hasMadeWallBlock;

        public Wall() {
        }

        public Wall(StructureVillagePieces.Start start, int componentType, Random rand, StructureBoundingBox bounds, int baseMode) {
            super(start, componentType);
            this.field_74885_f = baseMode;
            this.field_74887_e = bounds;
            this.start = start;
        }

        public static Wall func_74921_a(StructureVillagePieces.Start startPiece, List pieces, Random rand, int p1, int p2, int p3, int p4, int p5) {
            StructureBoundingBox bounds = StructureBoundingBox.func_78889_a((int)p1, (int)p2, (int)p3, (int)0, (int)0, (int)0, (int)2, (int)7, (int)2, (int)p4);
            boolean create = Wall.func_74895_a((StructureBoundingBox)bounds) && StructureComponent.func_74883_a((List)pieces, (StructureBoundingBox)bounds) == null && !Wall.containsWalls(pieces);
            return create ? new Wall(startPiece, p5, rand, bounds, p4) : null;
        }

        private static boolean containsWalls(List pieces2) {
            return false;
        }

        public void func_74861_a(StructureComponent component, List pieces, Random rand) {
            super.func_74861_a(component, pieces, rand);
            this.pieces = pieces;
        }

        public boolean func_74875_a(World world, Random rand, StructureBoundingBox bounds) {
            if (this.field_143015_k < 0) {
                this.field_143015_k = this.func_74889_b(world, bounds);
                if (this.field_143015_k < 0) {
                    return true;
                }
                this.field_74887_e.func_78886_a(0, this.field_143015_k - this.field_74887_e.field_78894_e + 7 - 1, 0);
            }
            if (!this.hasMadeWallBlock) {
                int x = 1;
                int z = 1;
                int xCoord = this.func_74865_a(x, z);
                int yCoord = this.func_74862_a(1);
                int zCoord = this.func_74873_b(x, z);
                if (this.pieces != null && bounds.func_78890_b(xCoord, yCoord, zCoord)) {
                    this.hasMadeWallBlock = true;
                    world.func_147449_b(xCoord, yCoord, zCoord, Witchery.Blocks.WALLGEN);
                    BlockVillageWallGen.TileEntityVillageWallGen tile = BlockUtil.getTileEntity((IBlockAccess)world, xCoord, yCoord, zCoord, BlockVillageWallGen.TileEntityVillageWallGen.class);
                    if (tile != null) {
                        tile.setStructure(this.pieces, this.start);
                    }
                }
            }
            return true;
        }

        protected void func_143012_a(NBTTagCompound nbtRoot) {
            super.func_143012_a(nbtRoot);
            nbtRoot.func_74757_a("WallBlock", this.hasMadeWallBlock);
        }

        protected void func_143011_b(NBTTagCompound nbtRoot) {
            super.func_143011_b(nbtRoot);
            this.hasMadeWallBlock = nbtRoot.func_74767_n("WallBlock");
        }

        public static void placeWalls(World world, List<StructureBounds> bb, int xCoord, int yCoord, int zCoord, BiomeGenBase biome, boolean desert) {
            int minX = Integer.MAX_VALUE;
            int minZ = Integer.MAX_VALUE;
            int maxX = Integer.MIN_VALUE;
            int maxZ = Integer.MIN_VALUE;
            Log.instance().debug(String.format("Generating town walls at [%d %d %d]", xCoord, yCoord, zCoord));
            for (int i = 0; i < bb.size(); ++i) {
                minX = Math.min(bb.get((int)i).field_78897_a, minX);
                minZ = Math.min(bb.get((int)i).field_78896_c, minZ);
                maxX = Math.max(bb.get((int)i).field_78893_d, maxX);
                maxZ = Math.max(bb.get((int)i).field_78892_f, maxZ);
            }
            if (maxX != Integer.MIN_VALUE && minX != Integer.MAX_VALUE && maxZ != Integer.MIN_VALUE && minZ != Integer.MAX_VALUE) {
                int z;
                int x;
                byte[][] a = new byte[maxX - minX + 3][maxZ - minZ + 3];
                short[][] b = new short[maxX - minX + 3][maxZ - minZ + 3];
                for (int i = 0; i < bb.size(); ++i) {
                    int w = bb.get((int)i).field_78893_d - bb.get((int)i).field_78897_a + 1;
                    int wMid = w / 2 + bb.get((int)i).field_78897_a - 1;
                    int h = bb.get((int)i).field_78892_f - bb.get((int)i).field_78896_c + 1;
                    int hMid = h / 2 + bb.get((int)i).field_78896_c - 1;
                    for (int x2 = bb.get((int)i).field_78897_a; x2 <= bb.get((int)i).field_78893_d; ++x2) {
                        for (int z2 = bb.get((int)i).field_78896_c; z2 <= bb.get((int)i).field_78892_f; ++z2) {
                            int mx = x2 - minX + 1;
                            int mz = z2 - minZ + 1;
                            a[mx][mz] = !bb.get((int)i).ew && (z2 == bb.get((int)i).field_78896_c || z2 == bb.get((int)i).field_78892_f) && x2 >= wMid - 1 && x2 <= wMid + 1 ? 3 : (bb.get((int)i).ew && (x2 == bb.get((int)i).field_78897_a || x2 == bb.get((int)i).field_78893_d) && z2 >= hMid - 1 && z2 <= hMid + 1 ? 3 : 2);
                        }
                    }
                }
                int range = 7;
                for (x = 1; x < a.length - range; ++x) {
                    for (z = 1; z < a[x].length - range; ++z) {
                        if (a[x][z] != 2) continue;
                        for (int p = 1; p < range; ++p) {
                            int p2;
                            if (a[x + p][z] == 2 && a[x + p - 1][z] == 0) {
                                for (p2 = p; p2 > 0; --p2) {
                                    a[x + p2][z] = 2;
                                }
                            }
                            if (a[x][z + p] != 2 || a[x][z + p - 1] != 0) continue;
                            for (p2 = p; p2 > 0; --p2) {
                                a[x][z + p2] = 2;
                            }
                        }
                    }
                }
                for (x = 1; x < a.length - 1; ++x) {
                    for (z = 1; z < a[x].length - 1; ++z) {
                        boolean nw;
                        boolean n = a[x][z - 1] == 0;
                        boolean s = a[x][z + 1] == 0;
                        boolean e = a[x + 1][z] == 0;
                        boolean w = a[x - 1][z] == 0;
                        boolean ne = a[x + 1][z - 1] == 0;
                        boolean sw = a[x - 1][z + 1] == 0;
                        boolean se = a[x + 1][z + 1] == 0;
                        boolean bl = nw = a[x - 1][z - 1] == 0;
                        if (n || s || e || w || ne || se || nw || sw) continue;
                        a[x][z] = 1;
                    }
                }
                Block blockBase = Blocks.field_150417_aV;
                Block blockFence = Blocks.field_150422_aJ;
                Block stairsBlock = Blocks.field_150390_bg;
                int blockBaseMeta = 0;
                BiomeEvent.GetVillageBlockID event = new BiomeEvent.GetVillageBlockID(biome, blockBase, blockBaseMeta);
                MinecraftForge.TERRAIN_GEN_BUS.post((Event)event);
                if (event.getResult() == Event.Result.DENY) {
                    blockBase = event.replacement;
                } else if (desert) {
                    blockBase = Blocks.field_150322_A;
                }
                event = new BiomeEvent.GetVillageBlockID(biome, blockFence, 0);
                MinecraftForge.TERRAIN_GEN_BUS.post((Event)event);
                if (event.getResult() == Event.Result.DENY) {
                    blockFence = event.replacement;
                }
                event = new BiomeEvent.GetVillageBlockID(biome, stairsBlock, 0);
                MinecraftForge.TERRAIN_GEN_BUS.post((Event)event);
                if (event.getResult() == Event.Result.DENY) {
                    stairsBlock = event.replacement;
                } else if (desert) {
                    stairsBlock = Blocks.field_150372_bz;
                }
                BiomeEvent.GetVillageBlockMeta event2 = new BiomeEvent.GetVillageBlockMeta(biome, blockBase, blockBaseMeta);
                MinecraftForge.TERRAIN_GEN_BUS.post((Event)event2);
                if (event2.getResult() == Event.Result.DENY) {
                    blockBaseMeta = event2.replacement;
                } else if (desert) {
                    blockBaseMeta = 2;
                }
                int guardDist = 0;
                for (int x3 = 1; x3 < a.length - 1; ++x3) {
                    for (int z3 = 1; z3 < a[x3].length - 1; ++z3) {
                        int lowestY;
                        int dy;
                        boolean nw;
                        boolean n = a[x3][z3 - 1] >= 2;
                        boolean s = a[x3][z3 + 1] >= 2;
                        boolean e = a[x3 + 1][z3] >= 2;
                        boolean w = a[x3 - 1][z3] >= 2;
                        boolean ne = a[x3 + 1][z3 - 1] >= 2;
                        boolean sw = a[x3 - 1][z3 + 1] >= 2;
                        boolean se = a[x3 + 1][z3 + 1] >= 2;
                        boolean bl = nw = a[x3 - 1][z3 - 1] >= 2;
                        if (a[x3][z3] < 2) continue;
                        int dx = minX + x3;
                        int dz = minZ + z3;
                        int solidCount = 0;
                        for (dy = yCoord; dy > 1 && solidCount < 9; --dy) {
                            solidCount = 0;
                            for (int ddx = dx - 1; ddx <= dx + 1; ++ddx) {
                                for (int ddz = dz - 1; ddz <= dz + 1; ++ddz) {
                                    boolean replaceable;
                                    Block block = world.func_147439_a(ddx, dy, ddz);
                                    boolean bl2 = replaceable = block.func_149688_o().func_76222_j() || block.func_149688_o() == Material.field_151584_j || block.func_149688_o() == Material.field_151575_d || block.func_149688_o() == Material.field_151585_k;
                                    if (!block.func_149721_r() || replaceable) continue;
                                    ++solidCount;
                                }
                            }
                        }
                        int minHeight = 9;
                        int startY = dy + 9;
                        int near = Math.max(Math.max(Math.max(b[x3 - 1][z3], b[x3 + 1][z3]), b[x3][z3 + 1]), b[x3][z3 - 1]);
                        if (near > 0) {
                            if (near > startY) {
                                startY = near - 1;
                            } else if (near < startY) {
                                startY = near + 1;
                            }
                        }
                        if (startY - (lowestY = dy) > 0) {
                            b[x3][z3] = (short)Math.min(Math.max(startY, 0), Short.MAX_VALUE);
                        }
                        for (dy = startY; dy > lowestY; --dy) {
                            boolean wg;
                            boolean gate;
                            if (dy == startY) {
                                if (!(ne || n || e)) {
                                    Wall.setBlock(world, dx + 2, dy, dz - 2, blockBase, blockBaseMeta);
                                    Wall.setBlock(world, dx + 2, dy, dz - 1, blockBase, blockBaseMeta);
                                    Wall.setBlock(world, dx + 1, dy, dz - 2, blockBase, blockBaseMeta);
                                    Wall.setBlock(world, dx + 2, dy + 1, dz - 2, blockBase, blockBaseMeta, false);
                                    Wall.setBlock(world, dx + 2, dy + 1, dz - 1, blockBase, blockBaseMeta, false);
                                    Wall.setBlock(world, dx + 1, dy + 1, dz - 2, blockBase, blockBaseMeta, false);
                                }
                                if (!(nw || n || w)) {
                                    Wall.setBlock(world, dx - 2, dy, dz - 2, blockBase, blockBaseMeta);
                                    Wall.setBlock(world, dx - 1, dy, dz - 2, blockBase, blockBaseMeta);
                                    Wall.setBlock(world, dx - 2, dy, dz - 1, blockBase, blockBaseMeta);
                                    Wall.setBlock(world, dx - 2, dy + 1, dz - 2, blockBase, blockBaseMeta, false);
                                    Wall.setBlock(world, dx - 1, dy + 1, dz - 2, blockBase, blockBaseMeta, false);
                                    Wall.setBlock(world, dx - 2, dy + 1, dz - 1, blockBase, blockBaseMeta, false);
                                }
                                if (!(se || s || e)) {
                                    Wall.setBlock(world, dx + 2, dy, dz + 2, blockBase, blockBaseMeta);
                                    Wall.setBlock(world, dx + 1, dy, dz + 2, blockBase, blockBaseMeta);
                                    Wall.setBlock(world, dx + 2, dy, dz + 1, blockBase, blockBaseMeta);
                                    Wall.setBlock(world, dx + 2, dy + 1, dz + 2, blockBase, blockBaseMeta, false);
                                    Wall.setBlock(world, dx + 1, dy + 1, dz + 2, blockBase, blockBaseMeta, false);
                                    Wall.setBlock(world, dx + 2, dy + 1, dz + 1, blockBase, blockBaseMeta, false);
                                }
                                if (!(sw || s || w)) {
                                    Wall.setBlock(world, dx - 2, dy, dz + 2, blockBase, blockBaseMeta);
                                    Wall.setBlock(world, dx - 1, dy, dz + 2, blockBase, blockBaseMeta);
                                    Wall.setBlock(world, dx - 2, dy, dz + 1, blockBase, blockBaseMeta);
                                    Wall.setBlock(world, dx - 2, dy + 1, dz + 2, blockBase, blockBaseMeta, false);
                                    Wall.setBlock(world, dx - 1, dy + 1, dz + 2, blockBase, blockBaseMeta, false);
                                    Wall.setBlock(world, dx - 2, dy + 1, dz + 1, blockBase, blockBaseMeta, false);
                                }
                                if (!(n || ne || nw)) {
                                    Wall.setBlock(world, dx, dy, dz - 2, blockBase, blockBaseMeta);
                                    Wall.setBlock(world, dx, dy + 1, dz - 2, stairsBlock, 0, false);
                                }
                                if (!(e || se || ne)) {
                                    Wall.setBlock(world, dx + 2, dy, dz, blockBase, blockBaseMeta);
                                    Wall.setBlock(world, dx + 2, dy + 1, dz, stairsBlock, 2, false);
                                }
                                if (!(s || se || sw)) {
                                    Wall.setBlock(world, dx, dy, dz + 2, blockBase, blockBaseMeta);
                                    Wall.setBlock(world, dx, dy + 1, dz + 2, stairsBlock, 0, false);
                                }
                                if (!(w || nw || sw)) {
                                    Wall.setBlock(world, dx - 2, dy, dz, blockBase, blockBaseMeta);
                                    Wall.setBlock(world, dx - 2, dy + 1, dz, stairsBlock, 2, false);
                                }
                                if (++guardDist <= 200) continue;
                                Wall.spawnGuard(world, dx, dy, dz);
                                guardDist = 0;
                                continue;
                            }
                            int distCheck = 4;
                            boolean bl3 = gate = a[x3][z3] == 3 && (x3 > distCheck && x3 < a.length - distCheck && a[x3 - distCheck][z3] == 2 && a[x3 + distCheck][z3] == 2 || z3 > distCheck && z3 < a[x3].length - distCheck && a[x3][z3 - distCheck] == 2 && a[x3][z3 + distCheck] == 2);
                            if (gate && dy == startY - 3) {
                                world.func_147449_b(dx, dy, dz, blockFence);
                                if (a[x3 + 1][z3] != 3 || a[x3 - 1][z3] != 3) {
                                    if (a[x3 + 1][z3] == 3) {
                                        world.func_147465_d(dx, dy, dz - 1, stairsBlock, 5, 2);
                                        world.func_147465_d(dx, dy, dz + 1, stairsBlock, 5, 2);
                                    } else if (a[x3 - 1][z3] == 3) {
                                        world.func_147465_d(dx, dy, dz - 1, stairsBlock, 4, 2);
                                        world.func_147465_d(dx, dy, dz + 1, stairsBlock, 4, 2);
                                    } else if (a[x3][z3 + 1] != 3 || a[x3][z3 - 1] != 3) {
                                        if (a[x3][z3 - 1] == 3) {
                                            world.func_147465_d(dx - 1, dy, dz, stairsBlock, 6, 2);
                                            world.func_147465_d(dx + 1, dy, dz, stairsBlock, 6, 2);
                                        } else if (a[x3][z3 + 1] == 3) {
                                            world.func_147465_d(dx - 1, dy, dz, stairsBlock, 7, 2);
                                            world.func_147465_d(dx + 1, dy, dz, stairsBlock, 7, 2);
                                        }
                                    }
                                }
                            }
                            if (gate && dy <= startY - 3) continue;
                            Wall.setBlock(world, dx, dy, dz, blockBase, blockBaseMeta);
                            boolean ng = a[x3][z3 - 1] == 3;
                            boolean sg = a[x3][z3 + 1] == 3;
                            boolean eg = a[x3 + 1][z3] == 3;
                            boolean bl4 = wg = a[x3 - 1][z3] == 3;
                            if (!ng) {
                                Wall.setBlock(world, dx, dy, dz - 1, blockBase, blockBaseMeta);
                            }
                            if (!ng && !eg) {
                                Wall.setBlock(world, dx + 1, dy, dz - 1, blockBase, blockBaseMeta);
                            }
                            if (!ng && !wg) {
                                Wall.setBlock(world, dx - 1, dy, dz - 1, blockBase, blockBaseMeta);
                            }
                            if (!eg) {
                                Wall.setBlock(world, dx + 1, dy, dz, blockBase, blockBaseMeta);
                            }
                            if (!sg) {
                                Wall.setBlock(world, dx, dy, dz + 1, blockBase, blockBaseMeta);
                            }
                            if (!sg && !eg) {
                                Wall.setBlock(world, dx + 1, dy, dz + 1, blockBase, blockBaseMeta);
                            }
                            if (!sg && !wg) {
                                Wall.setBlock(world, dx - 1, dy, dz + 1, blockBase, blockBaseMeta);
                            }
                            if (wg) continue;
                            Wall.setBlock(world, dx - 1, dy, dz, blockBase, blockBaseMeta);
                        }
                    }
                }
            }
        }

        private static void spawnGuard(World world, int x, int y, int z) {
            EntityVillageGuard guard = new EntityVillageGuard(world);
            guard.func_70012_b((double)x + 0.5, y, (double)z + 0.5, 0.0f, 0.0f);
            guard.func_110163_bv();
            guard.func_110161_a(null);
            world.func_72838_d((Entity)guard);
        }

        private static void setBlock(World world, int x, int y, int z, Block block, int meta) {
            Wall.setBlock(world, x, y, z, block, meta, true);
        }

        private static void setBlock(World world, int x, int y, int z, Block block, int meta, boolean notStacked) {
            Block replaceBlock = world.func_147439_a(x, y, z);
            Material material = replaceBlock.func_149688_o();
            if (material.func_76222_j() || material == Material.field_151584_j || material == Material.field_151575_d || material == Material.field_151585_k) {
                world.func_147465_d(x, y, z, block, meta, 2);
            }
        }

        public static class BlockVillageWallGen
        extends BlockBaseContainer {
            public BlockVillageWallGen() {
                super(Material.field_151576_e, TileEntityVillageWallGen.class);
                this.registerWithCreateTab = false;
                this.func_149722_s();
                this.func_149752_b(10000.0f);
            }

            public static class TileEntityVillageWallGen
            extends TileEntityBase {
                private List<StructureBounds> bb;
                private BiomeGenBase biome;
                private boolean desert;

                @Override
                public void func_145845_h() {
                    super.func_145845_h();
                    if (!this.field_145850_b.field_72995_K && this.bb != null && this.ticks > 40L) {
                        Wall.placeWalls(this.field_145850_b, this.bb, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.biome, this.desert);
                        this.bb = null;
                        this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                    } else if (!this.field_145850_b.field_72995_K && this.ticks > 1000L) {
                        this.bb = null;
                        this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                    }
                }

                public void setStructure(List pieces, StructureVillagePieces.Start start) {
                    this.biome = start.biome;
                    this.desert = start.field_74927_b;
                    this.bb = new ArrayList<StructureBounds>();
                    for (Object obj : pieces) {
                        if (!(obj instanceof StructureVillagePieces.Path)) continue;
                        this.bb.add(new StructureBounds((StructureVillagePieces.Path)obj, 20, 7));
                    }
                }
            }
        }

        public static class StructureBounds
        extends StructureBoundingBox {
            public final boolean ew;

            public StructureBounds(StructureVillagePieces.Path path, int expansionX, int expansionZ) {
                this(path.func_74874_b(), expansionX, expansionZ);
            }

            public StructureBounds(StructureBoundingBox bb, int expansionX, int expansionZ) {
                this(bb.field_78897_a, bb.field_78895_b, bb.field_78896_c, bb.field_78893_d, bb.field_78894_e, bb.field_78892_f, expansionX, expansionZ);
            }

            public StructureBounds(int x, int y, int z, int x2, int y2, int z2, int expansionX, int expansionZ) {
                boolean bl = this.ew = x2 - x > z2 - z;
                if (this.ew) {
                    this.field_78897_a = x - expansionZ;
                    this.field_78893_d = x2 + expansionZ;
                    this.field_78896_c = z - expansionX;
                    this.field_78892_f = z2 + expansionX;
                } else {
                    this.field_78897_a = x - expansionX;
                    this.field_78893_d = x2 + expansionX;
                    this.field_78896_c = z - expansionZ;
                    this.field_78892_f = z2 + expansionZ;
                }
                this.field_78895_b = y;
                this.field_78894_e = y2;
            }
        }
    }

    public static class EventHooks {
        @SubscribeEvent
        public void onGetVillageBlock(BiomeEvent.GetVillageBlockID event) {
            if (event.biome == null) {
                return;
            }
            Block b = event.original;
            if (BiomeDictionary.isBiomeOfType((BiomeGenBase)event.biome, (BiomeDictionary.Type)BiomeDictionary.Type.SANDY)) {
                if (b == Blocks.field_150364_r || b == Blocks.field_150363_s) {
                    event.replacement = Blocks.field_150322_A;
                } else if (b == Blocks.field_150347_e) {
                    event.replacement = Blocks.field_150322_A;
                } else if (b == Blocks.field_150344_f) {
                    event.replacement = Blocks.field_150344_f;
                    event.setResult(Event.Result.DENY);
                } else if (b == Blocks.field_150476_ad) {
                    event.replacement = Blocks.field_150487_bG;
                } else if (b == Blocks.field_150446_ar) {
                    event.replacement = Blocks.field_150372_bz;
                } else if (b == Blocks.field_150351_n) {
                    event.replacement = Blocks.field_150322_A;
                } else if (b == Blocks.field_150417_aV) {
                    event.replacement = Blocks.field_150322_A;
                } else if (b == Blocks.field_150376_bx) {
                    event.replacement = Blocks.field_150376_bx;
                } else if (b == Blocks.field_150390_bg) {
                    event.replacement = Blocks.field_150372_bz;
                }
            } else if (BiomeDictionary.isBiomeOfType((BiomeGenBase)event.biome, (BiomeDictionary.Type)BiomeDictionary.Type.SNOWY)) {
                if (b == Blocks.field_150364_r || b == Blocks.field_150363_s) {
                    event.replacement = Blocks.field_150403_cj;
                } else if (b == Blocks.field_150347_e) {
                    event.replacement = Blocks.field_150433_aE;
                } else if (b == Blocks.field_150344_f) {
                    event.replacement = Blocks.field_150433_aE;
                } else if (b == Blocks.field_150476_ad) {
                    event.replacement = Witchery.Blocks.SNOW_STAIRS;
                } else if (b == Blocks.field_150446_ar) {
                    event.replacement = Witchery.Blocks.SNOW_STAIRS;
                } else if (b == Blocks.field_150351_n) {
                    event.replacement = Blocks.field_150403_cj;
                } else if (b == Blocks.field_150417_aV) {
                    event.replacement = Blocks.field_150433_aE;
                } else if (b == Blocks.field_150333_U) {
                    event.replacement = Witchery.Blocks.SNOW_SLAB_SINGLE;
                } else if (b == Blocks.field_150376_bx) {
                    event.replacement = Witchery.Blocks.SNOW_SLAB_SINGLE;
                } else if (b == Blocks.field_150422_aJ) {
                    event.replacement = Witchery.Blocks.PERPETUAL_ICE_FENCE;
                } else if (b == Blocks.field_150346_d) {
                    event.replacement = Blocks.field_150433_aE;
                } else if (b == Blocks.field_150452_aw) {
                    event.replacement = Witchery.Blocks.SNOW_PRESSURE_PLATE;
                } else if (b == Blocks.field_150390_bg) {
                    event.replacement = Witchery.Blocks.SNOW_STAIRS;
                }
            }
            if (event.replacement != null && event.replacement != event.original) {
                event.setResult(Event.Result.DENY);
            }
        }

        @SubscribeEvent
        public void onGetVillageBlockMeta(BiomeEvent.GetVillageBlockMeta event) {
            Block b = event.original;
            if (event.biome == null) {
                return;
            }
            if (BiomeDictionary.isBiomeOfType((BiomeGenBase)event.biome, (BiomeDictionary.Type)BiomeDictionary.Type.SANDY)) {
                if (b == Blocks.field_150364_r || b == Blocks.field_150363_s) {
                    event.replacement = 2;
                    event.setResult(Event.Result.DENY);
                } else if (b == Blocks.field_150347_e) {
                    event.replacement = 0;
                    event.setResult(Event.Result.DENY);
                } else if (b == Blocks.field_150344_f) {
                    event.replacement = 2;
                    event.setResult(Event.Result.DENY);
                } else if (b == Blocks.field_150376_bx) {
                    event.replacement = 2;
                    event.setResult(Event.Result.DENY);
                } else if (b == Blocks.field_150333_U) {
                    if (event.type == 3 || event.type == 0) {
                        event.replacement = 1;
                        event.setResult(Event.Result.DENY);
                    } else if (event.type == 11 || event.type == 8) {
                        event.replacement = 9;
                        event.setResult(Event.Result.DENY);
                    }
                } else if (b == Blocks.field_150417_aV) {
                    event.replacement = 2;
                    event.setResult(Event.Result.DENY);
                }
            } else if (BiomeDictionary.isBiomeOfType((BiomeGenBase)event.biome, (BiomeDictionary.Type)BiomeDictionary.Type.SNOWY)) {
                if (b == Blocks.field_150364_r || b == Blocks.field_150363_s) {
                    event.replacement = 0;
                    event.setResult(Event.Result.DENY);
                } else if (b == Blocks.field_150347_e) {
                    event.replacement = 0;
                    event.setResult(Event.Result.DENY);
                } else if (b == Blocks.field_150344_f) {
                    event.replacement = 0;
                    event.setResult(Event.Result.DENY);
                } else if (b == Blocks.field_150333_U) {
                    if (event.type >= 8) {
                        event.replacement = 8;
                        event.setResult(Event.Result.DENY);
                    } else {
                        event.replacement = 0;
                        event.setResult(Event.Result.DENY);
                    }
                } else if (b == Blocks.field_150417_aV) {
                    event.replacement = 0;
                    event.setResult(Event.Result.DENY);
                }
            }
        }
    }
}

