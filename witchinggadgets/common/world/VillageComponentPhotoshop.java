/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.VillagerRegistry$IVillageCreationHandler
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityHanging
 *  net.minecraft.entity.item.EntityItemFrame
 *  net.minecraft.entity.item.EntityPainting
 *  net.minecraft.entity.item.EntityPainting$EnumArt
 *  net.minecraft.entity.monster.EntityBlaze
 *  net.minecraft.entity.monster.EntityCaveSpider
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.entity.monster.EntityPigZombie
 *  net.minecraft.entity.monster.EntitySkeleton
 *  net.minecraft.entity.monster.EntitySlime
 *  net.minecraft.entity.monster.EntitySpider
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.Direction
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.WeightedRandomChestContent
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.structure.StructureBoundingBox
 *  net.minecraft.world.gen.structure.StructureComponent
 *  net.minecraft.world.gen.structure.StructureVillagePieces$PieceWeight
 *  net.minecraft.world.gen.structure.StructureVillagePieces$Start
 *  net.minecraft.world.gen.structure.StructureVillagePieces$Village
 *  net.minecraftforge.common.ChestGenHooks
 *  thaumcraft.api.research.ScanResult
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.entities.monster.EntityBrainyZombie
 *  thaumcraft.common.entities.monster.EntityFireBat
 */
package witchinggadgets.common.world;

import cpw.mods.fml.common.registry.VillagerRegistry;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.common.ChestGenHooks;
import thaumcraft.api.research.ScanResult;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.monster.EntityBrainyZombie;
import thaumcraft.common.entities.monster.EntityFireBat;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.util.Utilities;

public class VillageComponentPhotoshop
extends StructureVillagePieces.Village {
    static ChestGenHooks chestContents = new ChestGenHooks("WG:PHOTOWORKSHOP", new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.field_151121_aF, 0, 2, 7, 10), new WeightedRandomChestContent(Items.field_151100_aR, 0, 2, 7, 10), new WeightedRandomChestContent(ConfigItems.itemResource, 10, 1, 1, 1)}, 3, 9);
    static List<ChunkCoordinates> framesHung = new ArrayList<ChunkCoordinates>();
    private int groundLevel = -1;

    public VillageComponentPhotoshop() {
    }

    public VillageComponentPhotoshop(StructureVillagePieces.Start villagePiece, int par2, Random par3Random, StructureBoundingBox par4StructureBoundingBox, int par5) {
        this.field_74885_f = par5;
        this.field_74887_e = par4StructureBoundingBox;
    }

    public boolean func_74875_a(World world, Random rand, StructureBoundingBox box) {
        if (this.groundLevel < 0) {
            this.groundLevel = this.func_74889_b(world, box);
            if (this.groundLevel < 0) {
                return true;
            }
            this.field_74887_e.func_78886_a(0, this.groundLevel - this.field_74887_e.field_78894_e + 9 - 2, 0);
        }
        int x = this.field_74887_e.field_78897_a;
        int y = this.field_74887_e.field_78895_b;
        int z = this.field_74887_e.field_78896_c;
        this.func_151556_a(world, box, 0, 0, 0, 6, 7, 6, Blocks.field_150344_f, 2, Blocks.field_150344_f, 2, false);
        this.func_151549_a(world, box, 1, 1, 1, 5, 7, 5, Blocks.field_150350_a, Blocks.field_150350_a, false);
        this.func_151549_a(world, box, 0, 1, 0, 0, 8, 0, Blocks.field_150364_r, Blocks.field_150364_r, false);
        this.func_151549_a(world, box, 6, 1, 0, 6, 8, 0, Blocks.field_150364_r, Blocks.field_150364_r, false);
        this.func_151549_a(world, box, 0, 1, 6, 0, 8, 6, Blocks.field_150364_r, Blocks.field_150364_r, false);
        this.func_151549_a(world, box, 6, 1, 6, 6, 8, 6, Blocks.field_150364_r, Blocks.field_150364_r, false);
        int metaHallLogsA = this.field_74885_f == 1 || this.field_74885_f == 3 ? 8 : 4;
        int metaHallLogsB = this.field_74885_f == 1 || this.field_74885_f == 3 ? 4 : 8;
        for (int rz = 1; rz <= 5; ++rz) {
            this.func_151550_a(world, Blocks.field_150364_r, metaHallLogsA, rz, 4, 0, box);
            this.func_151550_a(world, Blocks.field_150364_r, metaHallLogsA, rz, 4, 6, box);
            this.func_151550_a(world, Blocks.field_150364_r, metaHallLogsB, 0, 4, rz, box);
            this.func_151550_a(world, Blocks.field_150364_r, metaHallLogsB, 6, 4, rz, box);
        }
        this.func_151556_a(world, box, 1, 4, 1, 5, 4, 5, (Block)Blocks.field_150376_bx, 8, (Block)Blocks.field_150376_bx, 8, false);
        this.func_151549_a(world, box, 2, 4, 5, 4, 4, 5, Blocks.field_150350_a, Blocks.field_150350_a, false);
        this.func_151550_a(world, Blocks.field_150350_a, 0, 3, 4, 4, box);
        this.func_151556_a(world, box, 1, 0, 1, 5, 0, 5, Blocks.field_150325_L, 1, Blocks.field_150325_L, 1, false);
        this.func_151550_a(world, Blocks.field_150325_L, 1, 3, 0, 0, box);
        int doorMeta = this.field_74885_f == 0 ? 1 : (this.field_74885_f == 1 ? 2 : (this.field_74885_f == 2 ? 3 : 0));
        this.func_74881_a(world, box, rand, 3, 1, 0, doorMeta);
        this.func_151550_a(world, Blocks.field_150410_aZ, 0, 1, 2, 0, box);
        this.func_151550_a(world, Blocks.field_150410_aZ, 0, 5, 2, 0, box);
        this.func_151550_a(world, Blocks.field_150410_aZ, 0, 0, 2, 1, box);
        this.func_151550_a(world, Blocks.field_150410_aZ, 0, 0, 2, 3, box);
        this.func_151550_a(world, Blocks.field_150410_aZ, 0, 0, 2, 5, box);
        this.func_151550_a(world, Blocks.field_150410_aZ, 0, 6, 2, 1, box);
        this.func_151550_a(world, Blocks.field_150410_aZ, 0, 6, 2, 3, box);
        this.func_151550_a(world, Blocks.field_150410_aZ, 0, 6, 2, 5, box);
        this.func_151549_a(world, box, 2, 6, 0, 4, 6, 0, Blocks.field_150410_aZ, Blocks.field_150410_aZ, false);
        this.func_151550_a(world, Blocks.field_150410_aZ, 0, 0, 6, 2, box);
        this.func_151550_a(world, Blocks.field_150410_aZ, 0, 0, 6, 4, box);
        this.func_151550_a(world, Blocks.field_150410_aZ, 0, 2, 6, 6, box);
        this.func_151550_a(world, Blocks.field_150410_aZ, 0, 4, 6, 6, box);
        this.func_151550_a(world, Blocks.field_150476_ad, this.func_151555_a(Blocks.field_150476_ad, 6), 1, 1, 3, box);
        this.func_151550_a(world, (Block)Blocks.field_150376_bx, 8, 2, 1, 3, box);
        this.func_151550_a(world, (Block)Blocks.field_150376_bx, 8, 3, 1, 3, box);
        this.func_151550_a(world, Blocks.field_150426_aN, 0, 3, 4, 3, box);
        int stairMeta = this.func_151555_a(Blocks.field_150476_ad, 1);
        this.func_151550_a(world, Blocks.field_150476_ad, stairMeta, 4, 1, 5, box);
        this.func_151550_a(world, Blocks.field_150476_ad, stairMeta, 3, 2, 5, box);
        this.func_151550_a(world, Blocks.field_150476_ad, stairMeta, 2, 3, 5, box);
        this.func_151550_a(world, Blocks.field_150476_ad, stairMeta, 1, 4, 5, box);
        int metaRoofA = this.func_151555_a(Blocks.field_150476_ad, 0);
        int metaRoofB = this.func_151555_a(Blocks.field_150476_ad, 1);
        int metaRoofC = this.func_151555_a(Blocks.field_150476_ad, 3);
        int metaRoofD = this.func_151555_a(Blocks.field_150476_ad, 2);
        for (int off = 1; off <= 5; ++off) {
            this.func_151550_a(world, Blocks.field_150487_bG, metaRoofA, 0, 8, off, box);
            this.func_151550_a(world, Blocks.field_150487_bG, metaRoofB, 6, 8, off, box);
            this.func_151550_a(world, Blocks.field_150487_bG, metaRoofC, off, 8, 0, box);
            this.func_151550_a(world, Blocks.field_150487_bG, metaRoofD, off, 8, 6, box);
        }
        this.func_151556_a(world, box, 1, 9, 1, 5, 9, 5, (Block)Blocks.field_150376_bx, 2, (Block)Blocks.field_150376_bx, 2, false);
        try {
            this.func_74879_a(world, box, rand, 1, 1, 5, chestContents.getItems(rand), chestContents.getCount(rand));
            int px = this.func_74865_a(0, 3);
            int pz = this.func_74873_b(0, 3);
            int side = this.field_74885_f == 0 ? 5 : (this.field_74885_f == 1 ? 3 : (this.field_74885_f == 2 ? 5 : 3));
            this.placePainting(rand, world, px, y + 6, pz, side, rand.nextInt(7));
            side = this.field_74885_f == 0 ? 4 : (this.field_74885_f == 1 ? 2 : (this.field_74885_f == 2 ? 4 : 2));
            for (int rz = 2; rz <= 4; ++rz) {
                px = this.func_74865_a(6, rz);
                pz = this.func_74873_b(6, rz);
                ItemStack photo = new ItemStack(WGContent.ItemMaterial, 1, 10);
                if (!world.field_72995_K) {
                    ScanResult scan = this.getRandomScan(world, rand);
                    photo.func_77982_d(new NBTTagCompound());
                    if (scan != null) {
                        NBTTagCompound scanTag = Utilities.writeScanResultToNBT(scan);
                        photo.func_77978_p().func_74782_a("scanResult", (NBTBase)scanTag);
                    }
                }
                this.placeItemframe(rand, world, px, y + 6, pz, side, photo);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public void placePainting(Random random, World world, int x, int y, int z, int side, int art) {
        int i1 = Direction.field_71579_d[side];
        EntityPainting e = new EntityPainting(world, x, y, z, i1);
        e.field_70522_e = EntityPainting.EnumArt.values()[art];
        if (e.func_70518_d() && world.func_72872_a(EntityHanging.class, AxisAlignedBB.func_72330_a((double)((double)x - 0.5), (double)y, (double)((double)z - 0.5), (double)((double)x + 1.5), (double)(y + 1), (double)((double)z + 1.5))).isEmpty() && !world.field_72995_K) {
            world.func_72838_d((Entity)e);
        }
    }

    public void placeItemframe(Random random, World world, int x, int y, int z, int side, ItemStack stack) {
        int i1 = Direction.field_71579_d[side];
        EntityItemFrame e = new EntityItemFrame(world, x, y, z, i1);
        e.func_82334_a(stack);
        if (e.func_70518_d() && world.func_72872_a(EntityHanging.class, AxisAlignedBB.func_72330_a((double)((double)x - 0.125), (double)y, (double)((double)z - 0.125), (double)((double)x + 1.125), (double)(y + 1), (double)((double)z + 1.125))).isEmpty() && !world.field_72995_K) {
            world.func_72838_d((Entity)e);
        }
    }

    public ScanResult getRandomScan(World world, Random rand) {
        int c = rand.nextInt(33);
        switch (c) {
            default: {
                return null;
            }
            case 0: 
            case 1: 
            case 2: 
            case 3: {
                return new ScanResult(2, 0, 0, (Entity)new EntityZombie(world), "");
            }
            case 4: 
            case 5: 
            case 6: 
            case 7: {
                return new ScanResult(2, 0, 0, (Entity)new EntitySkeleton(world), "");
            }
            case 8: 
            case 9: 
            case 10: 
            case 11: {
                return new ScanResult(2, 0, 0, (Entity)new EntityCreeper(world), "");
            }
            case 12: 
            case 13: 
            case 14: 
            case 15: {
                return new ScanResult(2, 0, 0, (Entity)new EntitySpider(world), "");
            }
            case 16: 
            case 17: 
            case 18: {
                return new ScanResult(2, 0, 0, (Entity)new EntityCaveSpider(world), "");
            }
            case 19: 
            case 20: {
                return new ScanResult(2, 0, 0, (Entity)new EntityEnderman(world), "");
            }
            case 21: {
                EntitySkeleton skel = new EntitySkeleton(world);
                skel.func_82201_a(1);
                return new ScanResult(2, 0, 0, (Entity)skel, "");
            }
            case 22: 
            case 23: 
            case 24: {
                return new ScanResult(2, 0, 0, (Entity)new EntityPigZombie(world), "");
            }
            case 25: 
            case 26: {
                return new ScanResult(2, 0, 0, (Entity)new EntitySlime(world), "");
            }
            case 27: 
            case 28: {
                return new ScanResult(2, 0, 0, (Entity)new EntityBlaze(world), "");
            }
            case 29: 
            case 30: {
                return new ScanResult(2, 0, 0, (Entity)new EntityBrainyZombie(world), "");
            }
            case 31: 
            case 32: 
        }
        return new ScanResult(2, 0, 0, (Entity)new EntityFireBat(world), "");
    }

    public static class VillageManager
    implements VillagerRegistry.IVillageCreationHandler {
        public Object buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List pieces, Random random, int p1, int p2, int p3, int p4, int p5) {
            StructureBoundingBox box = StructureBoundingBox.func_78889_a((int)p1, (int)p2, (int)p3, (int)0, (int)0, (int)0, (int)7, (int)9, (int)7, (int)p4);
            return !VillageComponentPhotoshop.func_74895_a((StructureBoundingBox)box) || StructureComponent.func_74883_a((List)pieces, (StructureBoundingBox)box) != null ? null : new VillageComponentPhotoshop(startPiece, p5, random, box, p4);
        }

        public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i) {
            return new StructureVillagePieces.PieceWeight(VillageComponentPhotoshop.class, 15, MathHelper.func_76136_a((Random)random, (int)(0 + i), (int)(1 + i)));
        }

        public Class<?> getComponentClass() {
            return VillageComponentPhotoshop.class;
        }
    }
}

