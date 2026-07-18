/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.VillagerRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.passive.EntityChicken
 *  net.minecraft.entity.passive.EntityCow
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.entity.passive.EntityMooshroom
 *  net.minecraft.entity.passive.EntityOcelot
 *  net.minecraft.entity.passive.EntityPig
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.nbt.NBTTagCompound
 */
package vazkii.botania.common.block.tile;

import cpw.mods.fml.common.registry.VillagerRegistry;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.nbt.NBTTagCompound;
import vazkii.botania.common.block.tile.TileMod;

public class TileCocoon
extends TileMod {
    private static final String TAG_TIME_PASSED = "timePassed";
    private static final String TAG_EMERALDS_GIVEN = "emeraldsGiven";
    public static final int TOTAL_TIME = 2400;
    public static final int MAX_EMERALDS = 20;
    public int timePassed;
    public int emeraldsGiven;

    public void func_145845_h() {
        ++this.timePassed;
        if (this.timePassed >= 2400) {
            this.hatch();
        }
    }

    public void hatch() {
        if (!this.field_145850_b.field_72995_K) {
            this.field_145850_b.func_72926_e(2001, this.field_145851_c, this.field_145848_d, this.field_145849_e, Block.func_149682_b((Block)this.func_145838_q()));
            this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            EntityVillager entity = null;
            float villagerChance = Math.min(1.0f, (float)this.emeraldsGiven / 20.0f);
            if (Math.random() < (double)villagerChance) {
                EntityVillager villager = new EntityVillager(this.field_145850_b);
                VillagerRegistry.applyRandomTrade((EntityVillager)villager, (Random)this.field_145850_b.field_73012_v);
                entity = villager;
            } else {
                float specialChance = 0.05f;
                if (Math.random() < (double)specialChance) {
                    int entityType = this.field_145850_b.field_73012_v.nextInt(3);
                    switch (entityType) {
                        case 0: {
                            entity = new EntityHorse(this.field_145850_b);
                            break;
                        }
                        case 1: {
                            entity = new EntityWolf(this.field_145850_b);
                            break;
                        }
                        case 2: {
                            entity = new EntityOcelot(this.field_145850_b);
                        }
                    }
                } else {
                    int entityType = this.field_145850_b.field_73012_v.nextInt(4);
                    switch (entityType) {
                        case 0: {
                            entity = new EntitySheep(this.field_145850_b);
                            break;
                        }
                        case 1: {
                            if (Math.random() < 0.01) {
                                entity = new EntityMooshroom(this.field_145850_b);
                                break;
                            }
                            entity = new EntityCow(this.field_145850_b);
                            break;
                        }
                        case 2: {
                            entity = new EntityPig(this.field_145850_b);
                            break;
                        }
                        case 3: {
                            entity = new EntityChicken(this.field_145850_b);
                        }
                    }
                }
            }
            if (entity != null) {
                entity.func_70107_b((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5);
                entity.func_70873_a(-24000);
                this.field_145850_b.func_72838_d((Entity)entity);
                entity.func_70656_aK();
            }
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound cmp) {
        cmp.func_74768_a(TAG_TIME_PASSED, this.timePassed);
        cmp.func_74768_a(TAG_EMERALDS_GIVEN, this.emeraldsGiven);
    }

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        this.timePassed = cmp.func_74762_e(TAG_TIME_PASSED);
        this.emeraldsGiven = cmp.func_74762_e(TAG_EMERALDS_GIVEN);
    }
}

