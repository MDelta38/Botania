/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.aspects.IAspectContainer
 */
package thaumic.tinkerer.common.block.tile;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumic.tinkerer.common.ThaumicTinkerer;

public class TileInfusedGrain
extends TileEntity
implements IAspectContainer {
    private final String NBT_MAIN_ASPECT = "mainAspect";
    private final String NBT_ASPEPCT_TENDENCIES = "aspectTendencies";
    public Aspect aspect;
    public AspectList primalTendencies = new AspectList();

    public void func_145841_b(NBTTagCompound nbt) {
        super.func_145841_b(nbt);
        this.writeCustomNBT(nbt);
    }

    public void func_145845_h() {
        int l;
        if (!this.field_145850_b.field_72995_K && this.field_145850_b.func_72957_l(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) >= 9 && (l = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e)) < 7 && this.field_145850_b.field_73012_v.nextInt((2510 - (int)Math.pow(((TileInfusedGrain)this.field_145850_b.func_147438_o((int)this.field_145851_c, (int)this.field_145848_d, (int)this.field_145849_e)).primalTendencies.getAmount(Aspect.WATER), 2.0)) * 6) == 0) {
            this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ++l, 3);
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
        if (this.field_145850_b.field_73012_v.nextInt((2550 - (int)Math.pow(this.primalTendencies.getAmount(Aspect.AIR), 2.0)) * 10) == 0 && !this.aspect.isPrimal()) {
            for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
                TileEntity entity = this.field_145850_b.func_147438_o(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ);
                if (!(entity instanceof TileInfusedGrain)) continue;
                TileInfusedGrain tileInfusedGrain = (TileInfusedGrain)entity;
                Aspect aspect = tileInfusedGrain.aspect;
                if (aspect.isPrimal()) {
                    if (this.primalTendencies.getAmount(aspect) >= 5) continue;
                    this.primalTendencies.add(aspect, 1);
                    this.reduceSaturatedAspects();
                    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                    if (this.field_145850_b.field_72995_K) {
                        for (int i = 0; i < 50; ++i) {
                            ThaumicTinkerer.tcProxy.essentiaTrailFx(this.field_145850_b, this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ, this.field_145851_c, this.field_145848_d, this.field_145849_e, 50, aspect.getColor(), 1.0f);
                        }
                    }
                    return;
                }
                AspectList targetList = tileInfusedGrain.primalTendencies;
                if (targetList.getAspects().length == 0 || targetList.getAspects()[0] == null) {
                    return;
                }
                aspect = targetList.getAspects()[this.field_145850_b.field_73012_v.nextInt(targetList.getAspects().length)];
                if (targetList.getAmount(aspect) >= this.primalTendencies.getAmount(aspect)) {
                    this.primalTendencies.add(aspect, 1);
                    targetList.reduce(aspect, 1);
                    this.reduceSaturatedAspects();
                    if (this.field_145850_b.field_72995_K) {
                        for (int i = 0; i < 50; ++i) {
                            ThaumicTinkerer.tcProxy.essentiaTrailFx(this.field_145850_b, this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ, this.field_145851_c, this.field_145848_d, this.field_145849_e, 50, aspect.getColor(), 1.0f);
                        }
                    }
                    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                }
                return;
            }
        }
    }

    public boolean shouldRefresh(Block oldBlock, Block newBlock, int oldMeta, int newMeta, World world, int x, int y, int z) {
        return oldBlock != newBlock;
    }

    public void reduceSaturatedAspects() {
        int sum = 0;
        for (Integer i : this.primalTendencies.aspects.values()) {
            sum += i.intValue();
        }
        if (sum > 50) {
            for (int toRemove = sum - 50; toRemove > 0; --toRemove) {
                Random rand = new Random();
                Aspect target = this.primalTendencies.getAspects()[rand.nextInt(this.primalTendencies.getAspects().length)];
                this.primalTendencies.remove(target, 1);
            }
        }
    }

    public void func_145839_a(NBTTagCompound nbt) {
        super.func_145839_a(nbt);
        this.readCustomNBT(nbt);
    }

    public void writeCustomNBT(NBTTagCompound nbt) {
        NBTTagCompound aspectCompound = new NBTTagCompound();
        new AspectList().add(this.aspect, 1).writeToNBT(aspectCompound);
        nbt.func_74782_a("mainAspect", (NBTBase)aspectCompound);
        NBTTagCompound tendencyCompound = new NBTTagCompound();
        this.primalTendencies.writeToNBT(tendencyCompound);
        nbt.func_74782_a("aspectTendencies", (NBTBase)tendencyCompound);
    }

    public void readCustomNBT(NBTTagCompound nbt) {
        AspectList aspectList = new AspectList();
        aspectList.readFromNBT(nbt.func_74775_l("mainAspect"));
        this.aspect = aspectList.getAspects()[0];
        aspectList.readFromNBT(nbt.func_74775_l("aspectTendencies"));
        this.primalTendencies = aspectList;
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        this.readCustomNBT(pkt.func_148857_g());
    }

    public Packet func_145844_m() {
        NBTTagCompound compound = new NBTTagCompound();
        this.writeCustomNBT(compound);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, -999, compound);
    }

    public AspectList getAspects() {
        return this.aspect != null ? new AspectList().add(this.aspect, 1) : null;
    }

    public void setAspects(AspectList paramAspectList) {
    }

    public boolean doesContainerAccept(Aspect paramAspect) {
        return false;
    }

    public int addToContainer(Aspect paramAspect, int paramInt) {
        return 0;
    }

    public boolean takeFromContainer(Aspect paramAspect, int paramInt) {
        return false;
    }

    public boolean takeFromContainer(AspectList paramAspectList) {
        return false;
    }

    public boolean doesContainerContainAmount(Aspect paramAspect, int paramInt) {
        return false;
    }

    public boolean doesContainerContain(AspectList paramAspectList) {
        return false;
    }

    public int containerContains(Aspect paramAspect) {
        return 0;
    }
}

