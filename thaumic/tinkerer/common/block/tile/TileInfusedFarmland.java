/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.aspects.IAspectContainer
 */
package thaumic.tinkerer.common.block.tile;

import java.util.Random;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;

public class TileInfusedFarmland
extends TileEntity
implements IAspectContainer {
    public static final int MAX_ASPECTS = 20;
    public static final String NBT_ASPECT_LIST = "aspectList";
    public AspectList aspectList = new AspectList();

    public void func_145839_a(NBTTagCompound nbt) {
        super.func_145839_a(nbt);
        this.readCustomNBT(nbt);
    }

    public void readCustomNBT(NBTTagCompound nbt) {
        this.aspectList.readFromNBT(nbt.func_74775_l(NBT_ASPECT_LIST));
    }

    public void func_145841_b(NBTTagCompound nbt) {
        super.func_145841_b(nbt);
        this.writeCustomNBT(nbt);
    }

    public void writeCustomNBT(NBTTagCompound nbt) {
        NBTTagCompound compound = new NBTTagCompound();
        this.aspectList.writeToNBT(compound);
        nbt.func_74782_a(NBT_ASPECT_LIST, (NBTBase)compound);
    }

    public Packet func_145844_m() {
        NBTTagCompound nbt = new NBTTagCompound();
        this.writeCustomNBT(nbt);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, -999, nbt);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        this.readCustomNBT(pkt.func_148857_g());
    }

    public void reduceSaturatedAspects() {
        int sum = 0;
        for (Integer i : this.aspectList.aspects.values()) {
            sum += i.intValue();
        }
        if (sum > 20) {
            for (int toRemove = sum - 20; toRemove > 0; --toRemove) {
                Random rand = new Random();
                Aspect target = this.aspectList.getAspects()[rand.nextInt(this.aspectList.getAspects().length)];
                this.aspectList.remove(target, 1);
            }
        }
    }

    public AspectList getAspects() {
        return this.aspectList;
    }

    public void setAspects(AspectList aspectList) {
        this.aspectList = aspectList;
    }

    public boolean doesContainerAccept(Aspect aspect) {
        return false;
    }

    public int addToContainer(Aspect aspect, int i) {
        return 0;
    }

    public boolean takeFromContainer(Aspect aspect, int i) {
        return false;
    }

    public boolean takeFromContainer(AspectList aspectList) {
        return false;
    }

    public boolean doesContainerContainAmount(Aspect aspect, int i) {
        return false;
    }

    public boolean doesContainerContain(AspectList aspectList) {
        return false;
    }

    public int containerContains(Aspect aspect) {
        return 0;
    }
}

