/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityFallingBlock
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.Teleporter
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.blocks.BlockAiry
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.entities.monster.EntityWisp
 *  thaumcraft.common.items.wands.ItemWandCasting
 */
package com.kentington.thaumichorizons.common.tiles;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityGolemTH;
import com.kentington.thaumichorizons.common.lib.PocketPlaneData;
import com.kentington.thaumichorizons.common.lib.PocketPlaneThread;
import com.kentington.thaumichorizons.common.lib.VortexTeleporter;
import cpw.mods.fml.common.FMLCommonHandler;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.BlockAiry;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.monster.EntityWisp;
import thaumcraft.common.items.wands.ItemWandCasting;

public class TileVortex
extends TileThaumcraft
implements IWandable,
IAspectContainer {
    final int MAX_COUNT = 2400;
    public int count;
    public int beams;
    public int dimensionID;
    public AspectList aspects = new AspectList();
    boolean ateDevices = false;
    public boolean collapsing = false;
    public boolean createdDimension = false;
    public boolean generating = false;
    public boolean cheat = false;
    ArrayList<ItemStack> items = new ArrayList();
    Thread ppThread = null;

    public void func_145845_h() {
        super.func_145845_h();
        if (this.generating) {
            this.field_145850_b.func_72876_a(null, (double)((float)this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat()), (double)((float)this.field_145848_d + this.field_145850_b.field_73012_v.nextFloat()), (double)((float)this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat()), 1.0f, false);
            if (this.ppThread == null) {
                this.createDimension(null);
                return;
            }
            if (!this.ppThread.isAlive()) {
                this.generating = false;
                this.createdDimension = true;
                this.func_70296_d();
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
            return;
        }
        if (this.collapsing) {
            ++this.count;
            if (this.count > 25) {
                if (this.createdDimension) {
                    MinecraftServer.func_71276_C().func_71218_a(ThaumicHorizons.dimensionPocketId).func_147468_f(0, 129, this.dimensionID * 256);
                }
                this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                Thaumcraft.proxy.burst(this.field_145850_b, (double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, 4.0f);
                BlockAiry.explodify((World)this.field_145850_b, (int)this.field_145851_c, (int)this.field_145848_d, (int)this.field_145849_e);
            }
            return;
        }
        if (this.count < 50) {
            ++this.count;
        } else {
            List ents;
            if (!this.ateDevices) {
                if (!this.cheat) {
                    for (int dx = -1; dx < 2; ++dx) {
                        for (int dy = -1; dy < 2; ++dy) {
                            for (int dz = -1; dz < 2; ++dz) {
                                if (dx == 0 && dy == 0 && dz == 0) continue;
                                this.field_145850_b.func_147468_f(this.field_145851_c + dx, this.field_145848_d + dy, this.field_145849_e + dz);
                                Thaumcraft.proxy.burst(this.field_145850_b, (double)(this.field_145851_c + dx), (double)(this.field_145848_d + dy), (double)(this.field_145849_e + dz), 2.0f);
                            }
                        }
                    }
                }
                this.ateDevices = true;
            }
            if (this.beams < 6 && !this.cheat) {
                this.handleHungryNode();
            } else if (!this.createdDimension && !this.generating) {
                this.handlePocketPlaneStuff();
            }
            if (!this.cheat) {
                this.count += 6 - this.beams;
            }
            if (this.count > 2400) {
                this.collapsing = true;
                this.count = 0;
            }
            if (this.createdDimension && (ents = this.field_145850_b.func_72872_a(EntityPlayerMP.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)))).size() > 0) {
                for (Object e : ents) {
                    EntityPlayerMP player = (EntityPlayerMP)e;
                    if (player.field_70154_o != null || player.field_70153_n != null) continue;
                    MinecraftServer mServer = FMLCommonHandler.instance().getMinecraftServerInstance();
                    if (player.field_71088_bW > 0) {
                        player.field_71088_bW = 100;
                        continue;
                    }
                    if (player.field_71093_bK != ThaumicHorizons.dimensionPocketId) {
                        player.field_71088_bW = 100;
                        player.field_71133_b.func_71203_ab().transferPlayerToDimension(player, ThaumicHorizons.dimensionPocketId, (Teleporter)new VortexTeleporter(mServer.func_71218_a(ThaumicHorizons.dimensionPocketId), this.dimensionID));
                        continue;
                    }
                    player.field_71088_bW = 100;
                    player.field_71133_b.func_71203_ab().transferPlayerToDimension(player, 0, (Teleporter)new VortexTeleporter(mServer.func_71218_a(0), this.dimensionID));
                }
            }
        }
    }

    void handlePocketPlaneStuff() {
        List ents = this.field_145850_b.func_72872_a(Entity.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)).func_72314_b(1.0, 1.0, 1.0));
        if (ents != null && ents.size() > 0) {
            for (Object ent : ents) {
                Entity eo = (Entity)ent;
                if (!(eo instanceof EntityItem)) continue;
                EntityItem item = (EntityItem)eo;
                if (ThaumicHorizons.enablePocket && item.func_92059_d().func_77973_b() == ConfigItems.itemEldritchObject && item.func_92059_d().func_77960_j() == 3) {
                    this.createDimension(item);
                    item.func_70106_y();
                    continue;
                }
                this.handleVoidCrafting(item);
            }
        }
    }

    void handleVoidCrafting(EntityItem item) {
        if (item.func_92059_d().func_77973_b() == ConfigItems.itemResource && item.func_92059_d().func_77960_j() == 16) {
            this.items.add(new ItemStack(ThaumicHorizons.itemVoidPutty, item.func_92059_d().field_77994_a));
            item.func_70106_y();
        } else if (item.func_92059_d().func_77973_b() == ConfigItems.itemResource && item.func_92059_d().func_77960_j() == 14) {
            for (int i = 0; i < item.func_92059_d().field_77994_a; ++i) {
                this.spawnWisps();
            }
            item.func_70106_y();
        } else if (item.func_92059_d().func_77973_b() == ThaumicHorizons.itemCrystalWand) {
            ItemStack theWand = new ItemStack(ThaumicHorizons.itemWandCastingDisposable);
            ((ItemWandCasting)theWand.func_77973_b()).setRod(theWand, ThaumicHorizons.ROD_CRYSTAL);
            ((ItemWandCasting)theWand.func_77973_b()).setCap(theWand, ThaumicHorizons.CAP_CRYSTAL);
            ((ItemWandCasting)theWand.func_77973_b()).storeVis(theWand, Aspect.EARTH, 25000);
            ((ItemWandCasting)theWand.func_77973_b()).storeVis(theWand, Aspect.AIR, 25000);
            ((ItemWandCasting)theWand.func_77973_b()).storeVis(theWand, Aspect.FIRE, 25000);
            ((ItemWandCasting)theWand.func_77973_b()).storeVis(theWand, Aspect.WATER, 25000);
            ((ItemWandCasting)theWand.func_77973_b()).storeVis(theWand, Aspect.ORDER, 25000);
            ((ItemWandCasting)theWand.func_77973_b()).storeVis(theWand, Aspect.ENTROPY, 25000);
            this.items.add(theWand);
            item.func_70106_y();
        } else if (item.func_92059_d().func_77973_b() == ThaumicHorizons.itemGolemPowder && item.func_145800_j() != null) {
            if (!this.field_145850_b.field_72995_K) {
                for (int i = 0; i < item.func_92059_d().field_77994_a; ++i) {
                    EntityGolemTH golem = new EntityGolemTH(this.field_145850_b);
                    golem.setOwner(item.func_145800_j());
                    golem.loadGolem((double)this.field_145851_c + 0.5, this.field_145848_d, (double)this.field_145849_e + 0.5, null, 0, -420, false, false, false);
                    this.field_145850_b.func_72908_a((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "thaumcraft:wand", 1.0f, 1.0f);
                    golem.func_110171_b((int)golem.field_70165_t, (int)golem.field_70163_u, (int)golem.field_70161_v, 32);
                    this.field_145850_b.func_72838_d((Entity)golem);
                    this.field_145850_b.func_72960_a((Entity)golem, (byte)7);
                }
            }
            item.func_70106_y();
        }
    }

    void spawnWisps() {
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        int wisps = this.field_145850_b.field_73012_v.nextInt(4) + 1;
        for (int i = 0; i < wisps; ++i) {
            EntityWisp wisp = new EntityWisp(this.field_145850_b);
            wisp.func_70107_b((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5);
            if (this.aspects.size() > 0 && this.aspects.getAspects()[0] != null) {
                wisp.setType(this.aspects.getAspects()[this.field_145850_b.field_73012_v.nextInt(this.aspects.size())].getTag());
            }
            this.field_145850_b.func_72838_d((Entity)wisp);
        }
    }

    void createDimension(EntityItem pearl) {
        PocketPlaneData data = new PocketPlaneData();
        String name = "";
        if (pearl != null && pearl.func_92059_d().func_77942_o()) {
            name = pearl.func_92059_d().func_82833_r();
        } else if (pearl != null) {
            name = pearl.func_145800_j() + StatCollector.func_74838_a((String)"thaumichorizons.pocketplane");
        }
        data.name = name;
        this.dimensionID = PocketPlaneData.planes.size();
        MinecraftServer server = MinecraftServer.func_71276_C();
        if (server.func_71218_a(ThaumicHorizons.dimensionPocketId) == null) {
            WorldServer pocket = new WorldServer(server, null, null, ThaumicHorizons.dimensionPocketId, null, server.field_71304_b);
        }
        this.generating = true;
        if (!this.field_145850_b.field_72995_K) {
            this.ppThread = new Thread(new PocketPlaneThread(data, this.aspects, (World)MinecraftServer.func_71276_C().func_71218_a(ThaumicHorizons.dimensionPocketId), this.field_145851_c, this.field_145848_d, this.field_145849_e));
            this.ppThread.run();
        }
        this.func_70296_d();
    }

    void handleHungryNode() {
        List ents;
        if (this.field_145850_b.field_72995_K && this.beams < 6) {
            for (int a = 0; a < Thaumcraft.proxy.particleCount(1); ++a) {
                Vec3 v2;
                Vec3 v1;
                MovingObjectPosition mop;
                int tz;
                int tx = this.field_145851_c + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
                int ty = this.field_145848_d + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
                if (ty > this.field_145850_b.func_72976_f(tx, tz = this.field_145849_e + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16))) {
                    ty = this.field_145850_b.func_72976_f(tx, tz);
                }
                if ((mop = ThaumcraftApiHelper.rayTraceIgnoringSource(this.field_145850_b, v1 = Vec3.func_72443_a((double)((double)this.field_145851_c + 0.5), (double)((double)this.field_145848_d + 0.5), (double)((double)this.field_145849_e + 0.5)), v2 = Vec3.func_72443_a((double)((double)tx + 0.5), (double)((double)ty + 0.5), (double)((double)tz + 0.5)), true, false, false)) == null || !(this.func_145835_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) < 256.0)) continue;
                tx = mop.field_72311_b;
                ty = mop.field_72312_c;
                tz = mop.field_72309_d;
                Block bi = this.field_145850_b.func_147439_a(tx, ty, tz);
                int md = this.field_145850_b.func_72805_g(tx, ty, tz);
                if (bi.isAir((IBlockAccess)this.field_145850_b, tx, ty, tz)) continue;
                Thaumcraft.proxy.hungryNodeFX(this.field_145850_b, tx, ty, tz, this.field_145851_c, this.field_145848_d, this.field_145849_e, bi, md);
            }
        }
        if ((ents = this.field_145850_b.func_72872_a(Entity.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)).func_72314_b(15.0, 15.0, 15.0))) != null && ents.size() > 0) {
            for (Object ent : ents) {
                double d;
                Entity eo = (Entity)ent;
                if (eo instanceof EntityPlayer && ((EntityPlayer)eo).field_71075_bZ.field_75102_a) continue;
                if (eo.func_70089_S() && !eo.func_85032_ar() && (d = this.getDistanceTo(eo.field_70165_t, eo.field_70163_u, eo.field_70161_v)) < 2.0) {
                    if (eo instanceof EntityFallingBlock) {
                        eo.func_70106_y();
                    } else {
                        eo.func_70097_a(DamageSource.field_76380_i, 3.0f - (float)this.beams / 2.0f);
                    }
                }
                double var3 = ((double)this.field_145851_c + 0.5 - eo.field_70165_t) / 15.0;
                double var5 = ((double)this.field_145848_d + 0.5 - eo.field_70163_u) / 15.0;
                double var7 = ((double)this.field_145849_e + 0.5 - eo.field_70161_v) / 15.0;
                double var9 = Math.sqrt(var3 * var3 + var5 * var5 + var7 * var7);
                double var11 = 1.0 - var9;
                double modifier = 2.0 - (double)this.beams / 3.0;
                if (!(var11 > 0.0)) continue;
                var11 *= var11;
                eo.field_70159_w += var3 / var9 * var11 * 0.15 * modifier;
                eo.field_70181_x += var5 / var9 * var11 * 0.25 * modifier;
                eo.field_70179_y += var7 / var9 * var11 * 0.15 * modifier;
            }
        }
        for (int i = 0; i < 3; ++i) {
            float h;
            Vec3 v2;
            Vec3 v1;
            MovingObjectPosition mop;
            int tz;
            if (this.field_145850_b.field_72995_K || this.beams > 0 && this.field_145850_b.field_73012_v.nextInt(this.beams) != 0) continue;
            int tx = this.field_145851_c + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
            int ty = this.field_145848_d + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16);
            if (ty > this.field_145850_b.func_72976_f(tx, tz = this.field_145849_e + this.field_145850_b.field_73012_v.nextInt(16) - this.field_145850_b.field_73012_v.nextInt(16))) {
                ty = this.field_145850_b.func_72976_f(tx, tz);
            }
            if ((mop = ThaumcraftApiHelper.rayTraceIgnoringSource(this.field_145850_b, v1 = Vec3.func_72443_a((double)((double)this.field_145851_c + 0.5), (double)((double)this.field_145848_d + 0.5), (double)((double)this.field_145849_e + 0.5)), v2 = Vec3.func_72443_a((double)((double)tx + 0.5), (double)((double)ty + 0.5), (double)((double)tz + 0.5)), true, false, false)) == null || !(this.func_145835_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) < 256.0)) continue;
            tx = mop.field_72311_b;
            ty = mop.field_72312_c;
            tz = mop.field_72309_d;
            Block bi = this.field_145850_b.func_147439_a(tx, ty, tz);
            int md = this.field_145850_b.func_72805_g(tx, ty, tz);
            if (bi.isAir((IBlockAccess)this.field_145850_b, tx, ty, tz) || !((h = bi.func_149712_f(this.field_145850_b, tx, ty, tz)) >= 0.0f) || !(h < 10.0f)) continue;
            this.field_145850_b.func_147480_a(tx, ty, tz, true);
        }
    }

    public double getDistanceTo(double par1, double par3, double par5) {
        double var7 = (double)this.field_145851_c + 0.5 - par1;
        double var9 = (double)this.field_145848_d + 0.5 - par3;
        double var11 = (double)this.field_145849_e + 0.5 - par5;
        return var7 * var7 + var9 * var9 + var11 * var11;
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.func_74768_a("count", this.count);
        nbttagcompound.func_74768_a("beams", this.beams);
        nbttagcompound.func_74768_a("dimensionID", this.dimensionID);
        nbttagcompound.func_74757_a("ateDevices", this.ateDevices);
        nbttagcompound.func_74757_a("collapsing", this.collapsing);
        nbttagcompound.func_74757_a("createdDimension", this.createdDimension);
        nbttagcompound.func_74757_a("isGenerating", this.generating);
        nbttagcompound.func_74757_a("cheat", this.cheat);
        NBTTagList tlist = new NBTTagList();
        nbttagcompound.func_74782_a("aspects", (NBTBase)tlist);
        for (Aspect aspect : this.aspects.getAspects()) {
            if (aspect == null) continue;
            NBTTagCompound f = new NBTTagCompound();
            f.func_74778_a("key", aspect.getTag());
            f.func_74768_a("amount", this.aspects.getAmount(aspect));
            tlist.func_74742_a((NBTBase)f);
        }
        NBTTagList itemz = new NBTTagList();
        for (ItemStack item : this.items) {
            NBTTagCompound itemTag = new NBTTagCompound();
            item.func_77955_b(itemTag);
            itemz.func_74742_a((NBTBase)itemTag);
        }
        nbttagcompound.func_74782_a("items", (NBTBase)itemz);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.count = nbttagcompound.func_74762_e("count");
        this.beams = nbttagcompound.func_74762_e("beams");
        this.dimensionID = nbttagcompound.func_74762_e("dimensionID");
        this.ateDevices = nbttagcompound.func_74767_n("ateDevices");
        this.collapsing = nbttagcompound.func_74767_n("collapsing");
        this.createdDimension = nbttagcompound.func_74767_n("createdDimension");
        this.generating = nbttagcompound.func_74767_n("isGenerating");
        this.cheat = nbttagcompound.func_74767_n("cheat");
        AspectList al = new AspectList();
        NBTTagList tlist = nbttagcompound.func_150295_c("aspects", 10);
        for (int j = 0; j < tlist.func_74745_c(); ++j) {
            NBTTagCompound rs = tlist.func_150305_b(j);
            if (!rs.func_74764_b("key")) continue;
            al.add(Aspect.getAspect(rs.func_74779_i("key")), rs.func_74762_e("amount"));
        }
        this.aspects = al.copy();
        this.items.clear();
        NBTTagList itemz = nbttagcompound.func_150295_c("items", 10);
        for (int i = 0; i < itemz.func_74745_c(); ++i) {
            ItemStack item = ItemStack.func_77949_a((NBTTagCompound)itemz.func_150305_b(i));
            this.items.add(item);
        }
    }

    @Override
    public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
        if (!this.field_145850_b.field_72995_K && this.items.size() > 0) {
            ItemStack item = this.items.get(0);
            EntityItem key = new EntityItem(this.field_145850_b);
            key.func_92058_a(item);
            key.func_70107_b((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5);
            this.field_145850_b.func_72838_d((Entity)key);
            this.items.remove(0);
            player.field_70170_p.func_72980_b((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:wand", 0.5f, 0.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
            player.func_71038_i();
            this.func_70296_d();
        }
        player.field_70170_p.func_72980_b((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:wand", 0.5f, 0.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
        player.func_71038_i();
        this.func_70296_d();
        return 0;
    }

    @Override
    public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player) {
        return null;
    }

    @Override
    public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {
    }

    @Override
    public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {
    }

    @Override
    public AspectList getAspects() {
        if (this.aspects.getAspects()[0] != null) {
            return this.aspects;
        }
        return new AspectList();
    }

    @Override
    public void setAspects(AspectList aspects) {
    }

    @Override
    public boolean doesContainerAccept(Aspect tag) {
        return false;
    }

    @Override
    public int addToContainer(Aspect tag, int amount) {
        return 0;
    }

    @Override
    public boolean takeFromContainer(Aspect tag, int amount) {
        return false;
    }

    @Override
    public boolean takeFromContainer(AspectList ot) {
        return false;
    }

    @Override
    public boolean doesContainerContainAmount(Aspect tag, int amount) {
        return false;
    }

    @Override
    public boolean doesContainerContain(AspectList ot) {
        return false;
    }

    @Override
    public int containerContains(Aspect tag) {
        return 0;
    }
}

