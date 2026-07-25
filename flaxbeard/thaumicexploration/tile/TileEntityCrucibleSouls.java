/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.ThaumcraftApi
 *  thaumcraft.api.ThaumcraftApi$EntityTags
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.aspects.IAspectContainer
 *  thaumcraft.api.aspects.IEssentiaTransport
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.entities.golems.EntityGolemBase
 *  thaumcraft.common.entities.monster.EntityThaumicSlime
 */
package flaxbeard.thaumicexploration.tile;

import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.event.DamageSourceTX;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.entities.monster.EntityThaumicSlime;

public class TileEntityCrucibleSouls
extends TileEntity
implements IAspectContainer,
IEssentiaTransport {
    private int ticks = 0;
    public int drainTicks = 0;
    private EntityLivingBase targetMob = null;
    private int range = 5;
    private int yRange = 5;
    private float distance;
    public AspectList myAspects = new AspectList();
    public float myFlux = 0.0f;

    public void func_145841_b(NBTTagCompound par1NBTTagCompound) {
        super.func_145841_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74776_a("myFlux", this.myFlux);
        if (this.drainTicks > 0) {
            // empty if block
        }
        NBTTagCompound aspects = new NBTTagCompound();
        for (Object next : Aspect.aspects.keySet()) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.func_74768_a("Amount", this.myAspects.getAmount(Aspect.getAspect((String)((String)next))));
            aspects.func_74782_a((String)next, (NBTBase)tag);
        }
        par1NBTTagCompound.func_74782_a("Aspects", (NBTBase)aspects);
    }

    public void func_145839_a(NBTTagCompound par1NBTTagCompound) {
        super.func_145839_a(par1NBTTagCompound);
        this.myFlux = par1NBTTagCompound.func_74760_g("myFlux");
        AspectList readAspects = new AspectList();
        NBTTagCompound aspects = par1NBTTagCompound.func_74775_l("Aspects");
        for (Object next : Aspect.aspects.keySet()) {
            NBTTagCompound aspect = aspects.func_74775_l((String)next);
            int amount = aspect.func_74762_e("Amount");
            if (amount <= 0) continue;
            readAspects.add(Aspect.getAspect((String)((String)next)), amount);
        }
        this.myAspects = readAspects;
    }

    public Packet func_145844_m() {
        super.func_145844_m();
        NBTTagCompound access = new NBTTagCompound();
        access.func_74768_a("drainTicks", this.drainTicks);
        access.func_74776_a("myFlux", this.myFlux);
        if (this.drainTicks > 0) {
            access.func_74768_a("targetID", this.targetMob.func_145782_y());
        }
        NBTTagCompound aspects = new NBTTagCompound();
        for (Object next : Aspect.aspects.keySet()) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.func_74768_a("Amount", this.myAspects.getAmount(Aspect.getAspect((String)((String)next))));
            aspects.func_74782_a((String)next, (NBTBase)tag);
        }
        access.func_74782_a("Aspects", (NBTBase)aspects);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, access);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        NBTTagCompound access = pkt.func_148857_g();
        this.drainTicks = access.func_74762_e("drainTicks");
        this.myFlux = access.func_74760_g("myFlux");
        this.targetMob = this.drainTicks > 0 ? (EntityLivingBase)this.field_145850_b.func_73045_a(access.func_74762_e("targetID")) : null;
        AspectList readAspects = new AspectList();
        NBTTagCompound aspects = access.func_74775_l("Aspects");
        for (Object next : Aspect.aspects.keySet()) {
            NBTTagCompound aspect = aspects.func_74775_l((String)next);
            int amount = aspect.func_74762_e("Amount");
            if (amount <= 0) continue;
            readAspects.add(Aspect.getAspect((String)((String)next)), amount);
        }
        this.myAspects = readAspects;
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public float getFluidHeight() {
        float out = 0.3f + 0.7f * (this.myFlux / 1.0f);
        if (out > 1.0f) {
            out = 1.001f;
        }
        if (out == 1.0f) {
            out = 0.9999f;
        }
        if (out == 0.3f) {
            out = 0.2f;
        }
        return out;
    }

    private void spill() {
        if (this.field_145850_b.func_147437_c(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e)) {
            if (this.field_145850_b.field_73012_v.nextBoolean()) {
                this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, ConfigBlocks.blockFluxGas, 0, 3);
            } else {
                this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, ConfigBlocks.blockFluxGoo, 0, 3);
            }
        } else {
            Block bi = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
            int md = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
            if (bi == ConfigBlocks.blockFluxGoo && md < 7) {
                this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, ConfigBlocks.blockFluxGoo, md + 1, 3);
            } else if (bi == ConfigBlocks.blockFluxGas && md < 7) {
                this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, ConfigBlocks.blockFluxGas, md + 1, 3);
            } else {
                int z;
                int y;
                int x = -1 + this.field_145850_b.field_73012_v.nextInt(3);
                if (this.field_145850_b.func_147437_c(this.field_145851_c + x, this.field_145848_d + (y = -1 + this.field_145850_b.field_73012_v.nextInt(3)), this.field_145849_e + (z = -1 + this.field_145850_b.field_73012_v.nextInt(3)))) {
                    if (this.field_145850_b.field_73012_v.nextBoolean()) {
                        this.field_145850_b.func_147465_d(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z, ConfigBlocks.blockFluxGas, 0, 3);
                    } else {
                        this.field_145850_b.func_147465_d(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z, ConfigBlocks.blockFluxGoo, 0, 3);
                    }
                }
            }
        }
    }

    private float tagAmount() {
        int amount = 0;
        for (Object next : this.myAspects.aspects.keySet()) {
            amount += this.myAspects.getAmount((Aspect)next);
        }
        return amount;
    }

    public void func_145845_h() {
        super.func_145845_h();
        if (this.field_145850_b.func_147437_c(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) || this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) == ConfigBlocks.blockFluxGas || this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) == ConfigBlocks.blockFluxGoo) {
            if (this.myFlux > 1.0f) {
                this.spill();
                this.myFlux -= 1.0f;
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
            ++this.ticks;
            if (this.drainTicks > 0) {
                if (this.targetMob != null && this.targetMob.func_110143_aJ() > 0.0f) {
                    double slope = ((double)((float)this.field_145849_e + 0.5f) - this.targetMob.field_70161_v) / ((double)((float)this.field_145851_c + 0.5f) - this.targetMob.field_70165_t);
                    float myDistance = (float)Math.sqrt(Math.pow((double)this.field_145851_c - this.targetMob.field_70165_t, 2.0) + Math.pow((double)this.field_145848_d - this.targetMob.field_70163_u, 2.0) + Math.pow((double)this.field_145849_e - this.targetMob.field_70161_v, 2.0));
                    double xChange = Math.cos(slope) / 75.0 * (double)myDistance;
                    double zChange = Math.sin(slope) / 75.0 * (double)myDistance;
                    if ((double)this.field_145849_e > this.targetMob.field_70161_v && zChange < 0.0) {
                        zChange *= -1.0;
                    }
                    if ((double)this.field_145851_c > this.targetMob.field_70165_t && xChange < 0.0) {
                        xChange *= -1.0;
                    }
                    if ((double)this.field_145849_e < this.targetMob.field_70161_v && zChange > 0.0) {
                        zChange *= -1.0;
                    }
                    if ((double)this.field_145851_c < this.targetMob.field_70165_t && xChange > 0.0) {
                        xChange *= -1.0;
                    }
                    if (myDistance < (float)this.range) {
                        if (myDistance > 1.5f) {
                            // empty if block
                        }
                        if (this.field_145850_b.field_72995_K) {
                            if (this.field_145850_b.field_73012_v.nextInt(2) == 0) {
                                ThaumicExploration.proxy.spawnHarvestParticle(this.field_145850_b, this.targetMob.field_70165_t, this.targetMob.field_70121_D.field_72337_e - 0.5, this.targetMob.field_70161_v, (float)this.field_145851_c + 0.5f, (float)this.field_145848_d + 0.5f, (float)this.field_145849_e + 0.5f);
                            } else {
                                ThaumicExploration.proxy.spawnBoreSparkle(this.field_145850_b, this.targetMob.field_70165_t, this.targetMob.field_70121_D.field_72337_e - 0.5, this.targetMob.field_70161_v, (float)this.field_145851_c + 0.5f, (float)this.field_145848_d + 0.5f, (float)this.field_145849_e + 0.5f);
                            }
                        }
                        if (this.drainTicks % 10 == 0) {
                            if (this.targetMob.func_110143_aJ() <= 1.0f) {
                                if (!this.field_145850_b.field_72995_K) {
                                    String name = EntityList.func_75621_b((Entity)this.targetMob);
                                    for (ThaumcraftApi.EntityTags tag : ThaumcraftApi.scanEntities) {
                                        if (tag.entityName != name) continue;
                                        tag.aspects.aspects.keySet().iterator();
                                        Iterator iterator = tag.aspects.aspects.keySet().iterator();
                                        int i = 0;
                                        while (iterator.hasNext()) {
                                            Object next = iterator.next();
                                            if (next != null) {
                                                for (int z = 0; z < tag.aspects.getAmount((Aspect)next); ++z) {
                                                    if (this.field_145850_b.field_73012_v.nextInt(3) == 0) {
                                                        this.myAspects.add((Aspect)next, 1);
                                                        continue;
                                                    }
                                                    if (this.field_145850_b.field_73012_v.nextBoolean()) {
                                                        if (!((Aspect)next).isPrimal()) {
                                                            if (this.field_145850_b.field_73012_v.nextBoolean()) {
                                                                this.myAspects.add(((Aspect)next).getComponents()[0], 1);
                                                                continue;
                                                            }
                                                            this.myAspects.add(((Aspect)next).getComponents()[1], 1);
                                                            continue;
                                                        }
                                                        this.myAspects.add((Aspect)next, 1);
                                                        continue;
                                                    }
                                                    this.myFlux += 0.4f;
                                                }
                                            }
                                            ++i;
                                        }
                                    }
                                    this.drainTicks = 0;
                                    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                                }
                                this.targetMob.func_70097_a(DamageSourceTX.soulCrucible, 100.0f);
                            }
                            this.targetMob.func_70097_a(DamageSourceTX.soulCrucible, 1.0f);
                        }
                        if (this.drainTicks > 0) {
                            --this.drainTicks;
                        }
                    } else {
                        this.drainTicks = 0;
                    }
                } else {
                    this.drainTicks = 0;
                }
            }
            if (this.ticks % 5 == 0) {
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                if (this.drainTicks == 0) {
                    this.distance = (float)this.range + 1.0f;
                    List mobs = this.field_145850_b.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a((double)(this.field_145851_c - this.range), (double)(this.field_145848_d - this.yRange), (double)(this.field_145849_e - this.range), (double)(this.field_145851_c + this.range), (double)(this.field_145848_d + this.yRange), (double)(this.field_145849_e + this.range)));
                    for (EntityLivingBase mob : mobs) {
                        float myDistance;
                        if (mob instanceof EntityPlayer || mob instanceof EntityThaumicSlime || mob instanceof EntityGolemBase || !((myDistance = (float)Math.sqrt(Math.pow((double)this.field_145851_c - mob.field_70165_t, 2.0) + Math.pow((double)this.field_145848_d - mob.field_70163_u, 2.0) + Math.pow((double)this.field_145849_e - mob.field_70161_v, 2.0))) < this.distance)) continue;
                        this.drainTicks = (int)(mob.func_110138_aP() * 10.0f);
                        this.targetMob = mob;
                        this.distance = myDistance;
                        if (this.field_145850_b.field_72995_K) continue;
                        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                    }
                }
            }
        } else {
            this.drainTicks = 0;
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }

    public AspectList getAspects() {
        return this.myAspects;
    }

    public void setAspects(AspectList aspects) {
        this.myAspects = aspects;
    }

    public boolean doesContainerAccept(Aspect tag) {
        return false;
    }

    public int addToContainer(Aspect tag, int amount) {
        return amount;
    }

    public boolean takeFromContainer(Aspect tag, int amount) {
        if (!this.field_145850_b.field_72995_K) {
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
        if (this.myAspects.getAmount(tag) >= amount) {
            this.myAspects.reduce(tag, amount);
            return true;
        }
        return false;
    }

    public boolean takeFromContainer(AspectList ot) {
        boolean hasIt = true;
        if (!this.field_145850_b.field_72995_K) {
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
        ot.aspects.keySet().iterator();
        for (Object next : ot.aspects.keySet()) {
            if (this.myAspects.getAmount((Aspect)next) >= ot.getAmount((Aspect)next)) continue;
            hasIt = false;
        }
        if (hasIt) {
            for (Object next : ot.aspects.keySet()) {
                this.myAspects.reduce((Aspect)next, ot.getAmount((Aspect)next));
            }
            return true;
        }
        return false;
    }

    public boolean doesContainerContainAmount(Aspect tag, int amount) {
        return this.myAspects.getAmount(tag) > amount;
    }

    public boolean doesContainerContain(AspectList ot) {
        boolean hasIt = true;
        ot.aspects.keySet().iterator();
        for (Object next : ot.aspects.keySet()) {
            if (this.myAspects.getAmount((Aspect)next) >= ot.getAmount((Aspect)next)) continue;
            hasIt = false;
        }
        return hasIt;
    }

    public int containerContains(Aspect tag) {
        return this.myAspects.getAmount(tag);
    }

    public boolean isConnectable(ForgeDirection face) {
        return face != ForgeDirection.UP;
    }

    public boolean canInputFrom(ForgeDirection face) {
        return false;
    }

    public boolean canOutputTo(ForgeDirection face) {
        return face != ForgeDirection.UP;
    }

    public void setSuction(Aspect aspect, int amount) {
    }

    public int takeEssentia(Aspect aspect, int amount, ForgeDirection arg2) {
        if (arg2 != ForgeDirection.UP) {
            if (!this.field_145850_b.field_72995_K) {
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
            if (amount > this.myAspects.getAmount(aspect)) {
                int total = this.myAspects.getAmount(aspect);
                this.myAspects.reduce(aspect, total);
                return total;
            }
            this.myAspects.reduce(aspect, amount);
            return amount;
        }
        return 0;
    }

    public int getMinimumSuction() {
        return 0;
    }

    public boolean renderExtendedTube() {
        return false;
    }

    public Aspect getSuctionType(ForgeDirection face) {
        return null;
    }

    public int getSuctionAmount(ForgeDirection face) {
        return 0;
    }

    public Aspect getEssentiaType(ForgeDirection face) {
        return this.myAspects.size() > 0 ? this.myAspects.getAspects()[this.field_145850_b.field_73012_v.nextInt(this.myAspects.getAspects().length)] : null;
    }

    public int getEssentiaAmount(ForgeDirection face) {
        return this.myAspects.visSize();
    }

    public int addEssentia(Aspect arg0, int arg1, ForgeDirection arg2) {
        return 0;
    }
}

