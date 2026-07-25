/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.monster.EntityBlaze
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.entity.passive.EntityCow
 *  net.minecraft.entity.passive.EntityMooshroom
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.entities.EntityAspectOrb
 *  thaumcraft.common.entities.monster.EntityFireBat
 */
package com.kentington.thaumichorizons.common.tiles;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityItemInvulnerable;
import com.kentington.thaumichorizons.common.entities.EntityLightningBoltFinite;
import com.kentington.thaumichorizons.common.items.ItemFocusLiquefaction;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityMooshroom;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.damagesource.DamageSourceThaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.EntityAspectOrb;
import thaumcraft.common.entities.monster.EntityFireBat;

public class TileCloud
extends TileThaumcraft {
    public int md = -1;
    int dropTimer = -1;
    boolean raining = false;
    public int howManyDown;
    public Block cachedBlock;
    int cachedMD;
    static int[] dropTimers = new int[]{-1, -1, 120, -1, 480, 480, 80, 360, 280, 550};

    public boolean isRaining() {
        return this.raining;
    }

    public void func_145845_h() {
        boolean newRain;
        super.func_145845_h();
        if (this.md == -1) {
            this.md = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.dropTimer = dropTimers[this.md];
            this.findBlockBelow();
        }
        if ((newRain = MinecraftServer.func_71276_C().func_71218_a(0).func_72896_J()) != this.raining) {
            this.raining = newRain;
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
        if (this.raining) {
            if (this.field_145850_b.func_82737_E() % 10L == 0L) {
                this.findBlockBelow();
                switch (this.md) {
                    case 1: {
                        int meltable = ((ItemFocusLiquefaction)ThaumicHorizons.itemFocusLiquefaction).isMeltableBlock(this.cachedBlock, this.cachedMD);
                        if (meltable == 1) {
                            this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e, (Block)Blocks.field_150480_ab);
                            ThaumicHorizons.proxy.smeltFX(this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e, this.field_145850_b, 25, false);
                            break;
                        }
                        if (meltable == 2) {
                            if (this.field_145850_b.field_73011_w.field_76574_g != -1) {
                                this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e, Blocks.field_150355_j, 0, 3);
                                break;
                            }
                            this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e);
                            break;
                        }
                        if (meltable == 3) {
                            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e, Blocks.field_150346_d, 0, 3);
                            break;
                        }
                        if (meltable == 4) {
                            Blocks.field_150335_W.func_149664_b(this.field_145850_b, this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e, 1);
                            this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e);
                            break;
                        }
                        if (!this.cachedBlock.isFlammable((IBlockAccess)this.field_145850_b, this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e, ForgeDirection.UNKNOWN)) break;
                        this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e, (Block)Blocks.field_150480_ab);
                        ThaumicHorizons.proxy.smeltFX(this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e, this.field_145850_b, 25, false);
                        break;
                    }
                    case 3: {
                        int meltable = ((ItemFocusLiquefaction)ThaumicHorizons.itemFocusLiquefaction).isMeltableBlock(this.cachedBlock, this.cachedMD);
                        if (meltable == 1) {
                            this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e, Blocks.field_150350_a);
                            ThaumicHorizons.proxy.disintegrateExplodeFX(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)(this.field_145848_d - this.howManyDown) + 0.5, (double)this.field_145849_e + 0.5);
                            break;
                        }
                        if (meltable == 3) {
                            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e, Blocks.field_150346_d, 0, 3);
                            break;
                        }
                        if (!this.cachedBlock.isFlammable((IBlockAccess)this.field_145850_b, this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e, ForgeDirection.UNKNOWN)) break;
                        this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e, Blocks.field_150350_a);
                        ThaumicHorizons.proxy.disintegrateExplodeFX(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)(this.field_145848_d - this.howManyDown) + 0.5, (double)this.field_145849_e + 0.5);
                        break;
                    }
                    case 4: {
                        int meltable = ((ItemFocusLiquefaction)ThaumicHorizons.itemFocusLiquefaction).isMeltableBlock(this.cachedBlock, this.cachedMD);
                        if (meltable == 1) {
                            this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e, (Block)Blocks.field_150480_ab);
                            ThaumicHorizons.proxy.smeltFX(this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e, this.field_145850_b, 25, false);
                            break;
                        }
                        if (meltable == 2) {
                            if (this.field_145850_b.field_73011_w.field_76574_g != -1) {
                                this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e, Blocks.field_150355_j, 0, 3);
                                break;
                            }
                            this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e);
                            break;
                        }
                        if (meltable == 3) {
                            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e, Blocks.field_150346_d, 0, 3);
                            break;
                        }
                        if (meltable == 4) {
                            Blocks.field_150335_W.func_149664_b(this.field_145850_b, this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e, 1);
                            this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e);
                            break;
                        }
                        if (!this.cachedBlock.isFlammable((IBlockAccess)this.field_145850_b, this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e, ForgeDirection.UNKNOWN)) break;
                        this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e, (Block)Blocks.field_150480_ab);
                        ThaumicHorizons.proxy.smeltFX(this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e, this.field_145850_b, 25, false);
                        break;
                    }
                    default: {
                        if ((double)this.field_145850_b.func_72807_a(this.field_145851_c, this.field_145849_e).func_150564_a(this.field_145851_c, this.field_145848_d, this.field_145849_e) >= 0.15) {
                            if (this.cachedBlock == Blocks.field_150458_ak && this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e) != 7) {
                                this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e, 7, 3);
                                break;
                            }
                            if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e) == Blocks.field_150480_ab) {
                                this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e);
                                break;
                            }
                            if (this.cachedBlock != Blocks.field_150383_bp) break;
                            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e, 3, 3);
                            break;
                        }
                        if (!this.cachedBlock.func_149662_c() || !this.field_145850_b.func_147437_c(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e)) break;
                        this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, Blocks.field_150431_aC);
                    }
                }
            }
            switch (this.md) {
                case 1: {
                    List critters = this.getCrittersBelow();
                    for (Entity ent : critters) {
                        ent.func_70015_d(6);
                    }
                    break;
                }
                case 3: {
                    List critters = this.getCrittersBelow();
                    for (Entity ent : critters) {
                        ent.func_70097_a(DamageSourceThaumcraft.dissolve, 1.0f);
                    }
                    break;
                }
                case 4: {
                    List critters = this.getCrittersBelow();
                    for (Entity ent : critters) {
                        ent.func_70015_d(6);
                    }
                    break;
                }
                default: {
                    if (!((double)this.field_145850_b.func_72807_a(this.field_145851_c, this.field_145849_e).func_150564_a(this.field_145851_c, this.field_145848_d, this.field_145849_e) >= 0.15)) break;
                    List critters = this.getCrittersBelow();
                    for (Entity ent : critters) {
                        if (ent instanceof EntityEnderman || ent instanceof EntityBlaze || ent instanceof EntityFireBat) {
                            ent.func_70097_a(DamageSource.field_76369_e, 1.0f);
                        }
                        if (ent.func_70027_ad()) {
                            ent.func_70066_B();
                        }
                        if (this.field_145850_b.field_72995_K || this.md != 8 || !(ent instanceof EntityCow) || ent instanceof EntityMooshroom) continue;
                        ent.func_70106_y();
                        EntityMooshroom entitycow = new EntityMooshroom(this.field_145850_b);
                        entitycow.func_70012_b(ent.field_70165_t, ent.field_70163_u, ent.field_70161_v, ent.field_70177_z, ent.field_70125_A);
                        entitycow.func_70606_j(((EntityCow)ent).func_110143_aJ());
                        entitycow.field_70761_aq = ((EntityCow)ent).field_70761_aq;
                        this.field_145850_b.func_72838_d((Entity)entitycow);
                        this.field_145850_b.func_72869_a("largeexplode", ent.field_70165_t, ent.field_70163_u + (double)(ent.field_70131_O / 2.0f), ent.field_70161_v, 0.0, 0.0, 0.0);
                    }
                }
            }
            if (this.dropTimer != -1) {
                --this.dropTimer;
                if (this.dropTimer == 0 && !this.field_145850_b.field_72995_K) {
                    this.dropTimer = dropTimers[this.md] / 2 + this.field_145850_b.field_73012_v.nextInt(dropTimers[this.md] / 2);
                    block10 : switch (this.md) {
                        case 2: {
                            this.field_145850_b.func_72838_d((Entity)new EntityLightningBoltFinite(this.field_145850_b, (double)this.field_145851_c + 0.5, this.field_145848_d - this.howManyDown, (double)this.field_145849_e + 0.5, this.howManyDown, false));
                            break;
                        }
                        case 4: {
                            int type = this.field_145850_b.field_73012_v.nextInt(75);
                            if (type < 6) {
                                this.entityDropItem(new ItemStack(Items.field_151074_bl), 0.3f);
                                break;
                            }
                            if (type < 12) {
                                if (Config.foundSilverIngot) {
                                    this.entityDropItem(new ItemStack(ConfigItems.itemNugget, 1, 3), 0.3f);
                                    break;
                                }
                                this.entityDropItem(new ItemStack(ConfigItems.itemNugget, 1, 0), 0.3f);
                                break;
                            }
                            if (type < 20) {
                                if (Config.foundCopperIngot) {
                                    this.entityDropItem(new ItemStack(ConfigItems.itemNugget, 1, 1), 0.3f);
                                    break;
                                }
                                this.entityDropItem(new ItemStack(ConfigItems.itemNugget, 1, 0), 0.3f);
                                break;
                            }
                            if (type < 30) {
                                if (Config.foundTinIngot) {
                                    this.entityDropItem(new ItemStack(ConfigItems.itemNugget, 1, 2), 0.3f);
                                    break;
                                }
                                this.entityDropItem(new ItemStack(ConfigItems.itemNugget, 1, 0), 0.3f);
                                break;
                            }
                            if (type < 40) {
                                if (Config.foundLeadIngot) {
                                    this.entityDropItem(new ItemStack(ConfigItems.itemNugget, 1, 4), 0.3f);
                                    break;
                                }
                                this.entityDropItem(new ItemStack(ConfigItems.itemNugget, 1, 0), 0.3f);
                                break;
                            }
                            if (type < 50) {
                                this.entityDropItem(new ItemStack(ConfigItems.itemNugget, 1, 5), 0.3f);
                                break;
                            }
                            this.entityDropItem(new ItemStack(ConfigItems.itemNugget, 1, 0), 0.3f);
                            break;
                        }
                        case 5: {
                            switch (this.field_145850_b.field_73012_v.nextInt(10)) {
                                case 0: {
                                    this.entityDropItem(new ItemStack(Items.field_151082_bd), 0.3f);
                                    break block10;
                                }
                                case 1: {
                                    this.entityDropItem(new ItemStack(Items.field_151147_al), 0.3f);
                                    break block10;
                                }
                                case 2: {
                                    this.entityDropItem(new ItemStack(Items.field_151076_bf), 0.3f);
                                    break block10;
                                }
                                case 3: {
                                    switch (this.field_145850_b.field_73012_v.nextInt(3)) {
                                        case 0: {
                                            this.entityDropItem(new ItemStack(Items.field_151115_aP), 0.3f);
                                            break;
                                        }
                                        case 1: {
                                            this.entityDropItem(new ItemStack(Items.field_151115_aP, 1, 1), 0.3f);
                                            break;
                                        }
                                        case 2: {
                                            this.entityDropItem(new ItemStack(Items.field_151115_aP, 1, 2), 0.3f);
                                        }
                                    }
                                    break block10;
                                }
                            }
                            this.entityDropItem(new ItemStack(ThaumicHorizons.itemMeat), 0.3f);
                            break;
                        }
                        case 6: {
                            Aspect asp;
                            switch (this.field_145850_b.field_73012_v.nextInt(6)) {
                                case 1: {
                                    asp = Aspect.FIRE;
                                    break;
                                }
                                case 2: {
                                    asp = Aspect.ORDER;
                                    break;
                                }
                                case 3: {
                                    asp = Aspect.ENTROPY;
                                    break;
                                }
                                case 4: {
                                    asp = Aspect.AIR;
                                    break;
                                }
                                case 5: {
                                    asp = Aspect.EARTH;
                                    break;
                                }
                                default: {
                                    asp = Aspect.WATER;
                                }
                            }
                            EntityAspectOrb orb = new EntityAspectOrb(this.field_145850_b, (double)this.field_145851_c + this.field_145850_b.field_73012_v.nextDouble(), (double)this.field_145848_d + 0.5, (double)this.field_145849_e + this.field_145850_b.field_73012_v.nextDouble(), asp, 1);
                            this.field_145850_b.func_72838_d((Entity)orb);
                            break;
                        }
                        case 7: {
                            EntityXPOrb xporb = new EntityXPOrb(this.field_145850_b, (double)this.field_145851_c + this.field_145850_b.field_73012_v.nextDouble(), (double)this.field_145848_d + 0.5, (double)this.field_145849_e + this.field_145850_b.field_73012_v.nextDouble(), this.field_145850_b.field_73012_v.nextInt(4));
                            this.field_145850_b.func_72838_d((Entity)xporb);
                            break;
                        }
                        case 8: {
                            this.findBlockBelow();
                            if (!this.field_145850_b.func_147437_c(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e)) break;
                            if (this.cachedBlock == Blocks.field_150458_ak) {
                                switch (this.field_145850_b.field_73012_v.nextInt(8)) {
                                    case 1: {
                                        this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, Blocks.field_150394_bc);
                                        break block10;
                                    }
                                    case 2: {
                                        this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, Blocks.field_150393_bb);
                                        break block10;
                                    }
                                    case 3: {
                                        this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, Blocks.field_150459_bM);
                                    }
                                    case 4: {
                                        this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, Blocks.field_150469_bN);
                                    }
                                }
                                this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, Blocks.field_150464_aj);
                                break;
                            }
                            if (this.cachedBlock == Blocks.field_150346_d) {
                                switch (this.field_145850_b.field_73012_v.nextInt(10)) {
                                    case 4: {
                                        this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e, (Block)Blocks.field_150391_bh);
                                        break block10;
                                    }
                                }
                                this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown, this.field_145849_e, (Block)Blocks.field_150349_c);
                                break;
                            }
                            if (this.cachedBlock == Blocks.field_150348_b || this.cachedBlock == Blocks.field_150391_bh) {
                                switch (this.field_145850_b.field_73012_v.nextInt(3)) {
                                    case 1: {
                                        this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, (Block)Blocks.field_150338_P);
                                        break block10;
                                    }
                                }
                                this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, (Block)Blocks.field_150337_Q);
                                break;
                            }
                            if (this.cachedBlock == Blocks.field_150349_c) {
                                int plant = this.field_145850_b.field_73012_v.nextInt(1000);
                                if (plant == 666) {
                                    this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, ConfigBlocks.blockCustomPlant);
                                    this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, 1, 3);
                                    break;
                                }
                                if (plant < 750) {
                                    switch (this.field_145850_b.field_73012_v.nextInt(14)) {
                                        case 0: {
                                            this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, (Block)Blocks.field_150329_H);
                                            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, 1, 3);
                                            break;
                                        }
                                        case 1: {
                                            this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, (Block)Blocks.field_150329_H);
                                            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, 2, 3);
                                            break;
                                        }
                                        case 2: {
                                            this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, (Block)Blocks.field_150327_N);
                                            break;
                                        }
                                        case 3: {
                                            this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, (Block)Blocks.field_150328_O);
                                            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, 0, 3);
                                            break;
                                        }
                                        case 4: {
                                            this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, (Block)Blocks.field_150328_O);
                                            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, 0, 3);
                                            break;
                                        }
                                        case 5: {
                                            this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, (Block)Blocks.field_150328_O);
                                            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, 1, 3);
                                            break;
                                        }
                                        case 6: {
                                            this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, (Block)Blocks.field_150328_O);
                                            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, 2, 3);
                                            break;
                                        }
                                        case 7: {
                                            this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, (Block)Blocks.field_150328_O);
                                            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, 3, 3);
                                            break;
                                        }
                                        case 8: {
                                            this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, (Block)Blocks.field_150328_O);
                                            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, 4, 3);
                                            break;
                                        }
                                        case 9: {
                                            this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, (Block)Blocks.field_150328_O);
                                            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, 5, 3);
                                            break;
                                        }
                                        case 10: {
                                            this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, (Block)Blocks.field_150328_O);
                                            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, 6, 3);
                                            break;
                                        }
                                        case 11: {
                                            this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, (Block)Blocks.field_150328_O);
                                            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, 7, 3);
                                            break;
                                        }
                                        case 12: {
                                            this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, (Block)Blocks.field_150328_O);
                                            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, 8, 3);
                                            break;
                                        }
                                        case 13: {
                                            this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, Blocks.field_150436_aH);
                                        }
                                    }
                                    break;
                                }
                                if (plant < 950) {
                                    int sapling = this.field_145850_b.field_73012_v.nextInt(100);
                                    if (sapling < 10) {
                                        this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, ConfigBlocks.blockCustomPlant);
                                        this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, 0, 3);
                                        break;
                                    }
                                    this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, Blocks.field_150345_g);
                                    if (sapling < 25) {
                                        this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, 0, 3);
                                        break;
                                    }
                                    if (sapling < 40) {
                                        this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, 1, 3);
                                        break;
                                    }
                                    if (sapling < 55) {
                                        this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, 2, 3);
                                        break;
                                    }
                                    if (sapling < 70) {
                                        this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, 3, 3);
                                        break;
                                    }
                                    if (sapling < 85) {
                                        this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, 4, 3);
                                        break;
                                    }
                                    this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, 5, 3);
                                    break;
                                }
                                if (plant < 975) {
                                    this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, ConfigBlocks.blockCustomPlant);
                                    this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, 2, 3);
                                    break;
                                }
                                this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, ConfigBlocks.blockCustomPlant);
                                this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, 5, 3);
                                break;
                            }
                            if (this.cachedBlock == Blocks.field_150354_m) {
                                switch (this.field_145850_b.field_73012_v.nextInt(10)) {
                                    case 4: {
                                        this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, ConfigBlocks.blockCustomPlant);
                                        this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, 3, 3);
                                        break block10;
                                    }
                                }
                                this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, Blocks.field_150434_aF);
                                break;
                            }
                            if (this.cachedBlock != Blocks.field_150425_aM) break;
                            this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - this.howManyDown + 1, this.field_145849_e, Blocks.field_150388_bm);
                            break;
                        }
                        case 9: {
                            this.field_145850_b.func_72838_d((Entity)new EntityLightningBoltFinite(this.field_145850_b, (double)this.field_145851_c + 0.5, this.field_145848_d - this.howManyDown, (double)this.field_145849_e + 0.5, this.howManyDown, true));
                        }
                    }
                }
            }
        }
    }

    private void entityDropItem(ItemStack itemStack, float f) {
        EntityItemInvulnerable theItem = new EntityItemInvulnerable(this.field_145850_b, (double)this.field_145851_c + this.field_145850_b.field_73012_v.nextDouble(), (double)this.field_145848_d + 0.5, (double)this.field_145849_e + this.field_145850_b.field_73012_v.nextDouble(), itemStack);
        this.field_145850_b.func_72838_d((Entity)theItem);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.func_74757_a("raining", this.raining);
        nbttagcompound.func_74768_a("dropTimer", this.dropTimer);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.raining = nbttagcompound.func_74767_n("raining");
        this.dropTimer = nbttagcompound.func_74762_e("dropTimer");
    }

    @SideOnly(value=Side.CLIENT)
    public double func_145833_n() {
        return 65536.0;
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)(this.field_145851_c - 32), (double)0.0, (double)(this.field_145849_e - 32), (double)(this.field_145851_c + 32), (double)256.0, (double)(this.field_145849_e + 32));
    }

    public void findBlockBelow() {
        MovingObjectPosition mop = this.field_145850_b.func_72933_a(Vec3.func_72443_a((double)((double)this.field_145851_c + 0.5), (double)((double)this.field_145848_d - 0.5), (double)((double)this.field_145849_e + 0.5)), Vec3.func_72443_a((double)((double)this.field_145851_c + 0.5), (double)(this.field_145848_d - 256), (double)((double)this.field_145849_e + 0.5)));
        if (mop != null) {
            this.howManyDown = this.field_145848_d - mop.field_72312_c;
            this.cachedBlock = this.field_145850_b.func_147439_a(this.field_145851_c, mop.field_72312_c, this.field_145849_e);
            this.cachedMD = this.field_145850_b.func_72805_g(this.field_145851_c, mop.field_72312_c, this.field_145849_e);
        } else {
            this.howManyDown = 256;
            this.cachedBlock = Blocks.field_150350_a;
        }
    }

    public List getCrittersBelow() {
        return this.field_145850_b.func_72839_b((Entity)null, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)(this.field_145848_d - this.howManyDown), (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)this.field_145848_d, (double)(this.field_145849_e + 1)));
    }
}

