/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockGrass
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.WeightedRandomChestContent
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.gen.structure.StructureBoundingBox
 */
package com.emoniph.witchery.worldgen;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.entity.EntityCovenWitch;
import com.emoniph.witchery.worldgen.WitcheryComponent;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockGrass;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.StructureBoundingBox;

public class ComponentShack
extends WitcheryComponent {
    public static final int DIM_X = 7;
    public static final int DIM_Y = 10;
    public static final int DIM_Z = 7;
    private int witchesSpawned = 0;
    public static final WeightedRandomChestContent[] shackChestContents = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.field_151069_bo, 0, 1, 1, 10), new WeightedRandomChestContent(Items.field_151025_P, 0, 1, 3, 15), new WeightedRandomChestContent(Items.field_151034_e, 0, 1, 3, 15), new WeightedRandomChestContent(Items.field_151101_aQ, 0, 1, 3, 10), new WeightedRandomChestContent(Item.func_150898_a((Block)Blocks.field_150345_g), 1, 1, 1, 15), new WeightedRandomChestContent((Item)Witchery.Items.GENERIC, Witchery.Items.GENERIC.itemRowanBerries.damageValue, 1, 2, 10), new WeightedRandomChestContent(Items.field_151037_a, 0, 1, 1, 5), new WeightedRandomChestContent(Items.field_151035_b, 0, 1, 1, 5)};
    private boolean hasMadeChest;
    private static final String CHEST_KEY = "WITCShackChest";

    public ComponentShack() {
    }

    public ComponentShack(int direction, Random random, int x, int z) {
        super(direction, random, x, z, 7, 10, 7);
    }

    @Override
    public boolean addComponentParts(World world, Random random) {
        BiomeGenBase biom = world.func_72807_a(this.func_74865_a(0, 0), this.func_74873_b(0, 0));
        int groundAvg = this.calcGroundHeight(world, this.field_74887_e);
        if (groundAvg < 0) {
            return true;
        }
        this.field_74887_e.func_78886_a(0, groundAvg - this.field_74887_e.field_78894_e + 10 - 1, 0);
        if (this.isWaterBelow(world, 0, -1, 0, this.field_74887_e) || this.isWaterBelow(world, 0, -1, 6, this.field_74887_e) || this.isWaterBelow(world, 6, -1, 0, this.field_74887_e) || this.isWaterBelow(world, 6, -1, 6, this.field_74887_e)) {
            return false;
        }
        BlockGrass groundID = Blocks.field_150349_c;
        Block undergroundID = Blocks.field_150346_d;
        if (biom.field_76756_M == BiomeGenBase.field_76769_d.field_76756_M || biom.field_76756_M == BiomeGenBase.field_76786_s.field_76756_M || biom.field_76756_M == BiomeGenBase.field_76787_r.field_76756_M) {
            groundID = Blocks.field_150354_m;
            undergroundID = Blocks.field_150354_m;
        }
        this.func_74878_a(world, this.field_74887_e, 0, 1, 0, 6, 9, 6);
        this.func_151549_a(world, this.field_74887_e, 0, 0, 1, 6, 1, 5, Blocks.field_150347_e, Blocks.field_150347_e, false);
        this.func_151549_a(world, this.field_74887_e, 0, 2, 1, 6, 3, 5, Blocks.field_150344_f, Blocks.field_150344_f, false);
        this.func_74878_a(world, this.field_74887_e, 1, 1, 2, 5, 3, 4);
        this.place(Blocks.field_150364_r, 1, 0, 1, 1, this.field_74887_e, world);
        this.place(Blocks.field_150364_r, 1, 0, 2, 1, this.field_74887_e, world);
        this.place(Blocks.field_150364_r, 1, 0, 3, 1, this.field_74887_e, world);
        this.place(Blocks.field_150364_r, 1, 0, 1, 5, this.field_74887_e, world);
        this.place(Blocks.field_150364_r, 1, 0, 2, 5, this.field_74887_e, world);
        this.place(Blocks.field_150364_r, 1, 0, 3, 5, this.field_74887_e, world);
        this.place(Blocks.field_150364_r, 1, 6, 1, 1, this.field_74887_e, world);
        this.place(Blocks.field_150364_r, 1, 6, 2, 1, this.field_74887_e, world);
        this.place(Blocks.field_150364_r, 1, 6, 3, 1, this.field_74887_e, world);
        this.place(Blocks.field_150364_r, 1, 6, 1, 5, this.field_74887_e, world);
        this.place(Blocks.field_150364_r, 1, 6, 2, 5, this.field_74887_e, world);
        this.place(Blocks.field_150364_r, 1, 6, 3, 5, this.field_74887_e, world);
        int meta = this.field_74885_f == 3 || this.field_74885_f == 1 ? 4 : 8;
        this.place(Blocks.field_150364_r, 1 | meta, 0, 4, 2, this.field_74887_e, world);
        this.place(Blocks.field_150364_r, 1 | meta, 0, 4, 3, this.field_74887_e, world);
        this.place(Blocks.field_150364_r, 1 | meta, 0, 4, 4, this.field_74887_e, world);
        this.place(Blocks.field_150364_r, 1 | meta, 6, 4, 2, this.field_74887_e, world);
        this.place(Blocks.field_150364_r, 1 | meta, 6, 4, 3, this.field_74887_e, world);
        this.place(Blocks.field_150364_r, 1 | meta, 6, 4, 4, this.field_74887_e, world);
        for (int x = 0; x < 7; ++x) {
            this.place(Blocks.field_150485_bF, this.func_151555_a(Blocks.field_150476_ad, 3), x, 3, 0, this.field_74887_e, world);
            this.place(Blocks.field_150485_bF, this.func_151555_a(Blocks.field_150476_ad, 3), x, 4, 1, this.field_74887_e, world);
            this.place(Blocks.field_150485_bF, this.func_151555_a(Blocks.field_150476_ad, 3), x, 5, 2, this.field_74887_e, world);
            this.place(Blocks.field_150344_f, 1, x, 5, 3, this.field_74887_e, world);
            this.place(Blocks.field_150485_bF, this.func_151555_a(Blocks.field_150476_ad, 2), x, 5, 4, this.field_74887_e, world);
            this.place(Blocks.field_150485_bF, this.func_151555_a(Blocks.field_150476_ad, 2), x, 4, 5, this.field_74887_e, world);
            this.place(Blocks.field_150485_bF, this.func_151555_a(Blocks.field_150476_ad, 2), x, 3, 6, this.field_74887_e, world);
        }
        this.place(Blocks.field_150410_aZ, 0, 2, 2, 1, this.field_74887_e, world);
        this.place(Blocks.field_150410_aZ, 0, 2, 2, 5, this.field_74887_e, world);
        this.place(Blocks.field_150410_aZ, 0, 4, 2, 5, this.field_74887_e, world);
        this.place(Blocks.field_150410_aZ, 0, 0, 2, 3, this.field_74887_e, world);
        this.place(Blocks.field_150410_aZ, 0, 6, 2, 3, this.field_74887_e, world);
        this.func_74881_a(world, this.field_74887_e, random, 4, 1, 1, this.func_151555_a(Blocks.field_150466_ao, 1));
        this.place(Blocks.field_150344_f, 2, 1, 1, 4, this.field_74887_e, world);
        this.place(Blocks.field_150478_aa, 0, 1, 2, 4, this.field_74887_e, world);
        this.place(Blocks.field_150487_bG, this.func_151555_a(Blocks.field_150476_ad, 1), 1, 1, 3, this.field_74887_e, world);
        this.place(Blocks.field_150487_bG, this.func_151555_a(Blocks.field_150476_ad, 3), 2, 1, 4, this.field_74887_e, world);
        this.place(Blocks.field_150422_aJ, 0, 2, 1, 3, this.field_74887_e, world);
        this.place(Blocks.field_150452_aw, 0, 2, 2, 3, this.field_74887_e, world);
        if (!this.hasMadeChest) {
            int kc;
            int ic = this.func_74862_a(0);
            int jc = this.func_74865_a(7, 1);
            if (this.field_74887_e.func_78890_b(jc, ic, kc = this.func_74873_b(7, 1))) {
                this.hasMadeChest = true;
                this.func_74879_a(world, this.field_74887_e, random, 1, 1, 2, shackChestContents, 1 + random.nextInt(3));
            }
        }
        for (int i = 0; i < 7; ++i) {
            for (int j = 0; j < 7; ++j) {
                this.func_74871_b(world, j, 6, i, this.field_74887_e);
                this.func_151554_b(world, undergroundID, 0, j, 0, i, this.field_74887_e);
            }
        }
        this.spawnWitches(world, this.field_74887_e, 4, 1, 3, 1);
        return true;
    }

    private void spawnWitches(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3, int par4, int par5, int par6) {
        if (this.witchesSpawned < par6) {
            int l1;
            int k1;
            int j1;
            for (int i1 = this.witchesSpawned; i1 < par6 && par2StructureBoundingBox.func_78890_b(j1 = this.func_74865_a(par3 + i1, par5), k1 = this.func_74862_a(par4), l1 = this.func_74873_b(par3 + i1, par5)); ++i1) {
                ++this.witchesSpawned;
                if (par1World.field_73012_v.nextInt(4) == 0) continue;
                EntityCovenWitch entityvillager = new EntityCovenWitch(par1World);
                entityvillager.func_110163_bv();
                entityvillager.func_70012_b((double)j1 + 0.5, k1, (double)l1 + 0.5, 0.0f, 0.0f);
                par1World.func_72838_d((Entity)entityvillager);
            }
        }
    }

    @Override
    protected void func_143012_a(NBTTagCompound par1NBTTagCompound) {
        super.func_143012_a(par1NBTTagCompound);
        par1NBTTagCompound.func_74757_a(CHEST_KEY, this.hasMadeChest);
        par1NBTTagCompound.func_74768_a("WITCWCount", this.witchesSpawned);
    }

    @Override
    protected void func_143011_b(NBTTagCompound par1NBTTagCompound) {
        super.func_143011_b(par1NBTTagCompound);
        this.hasMadeChest = par1NBTTagCompound.func_74767_n(CHEST_KEY);
        this.witchesSpawned = par1NBTTagCompound.func_74764_b("WITCWCount") ? par1NBTTagCompound.func_74762_e("WITCWCount") : 0;
    }
}

