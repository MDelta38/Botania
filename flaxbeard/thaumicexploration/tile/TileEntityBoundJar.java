/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.common.tiles.TileJarFillable
 */
package flaxbeard.thaumicexploration.tile;

import flaxbeard.thaumicexploration.common.StringID;
import flaxbeard.thaumicexploration.data.BoundJarNetworkManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.tiles.TileJarFillable;

public class TileEntityBoundJar
extends TileJarFillable {
    public int accessTicks = 0;
    public int clientColor = 0;
    public int colour;
    public AspectList aspectList = new AspectList();
    public String networkName = StringID.getName();

    public int getMinimumSuction() {
        return 40;
    }

    public Aspect getSuctionType(ForgeDirection loc) {
        return this.aspect;
    }

    public int getSuctionAmount(ForgeDirection loc) {
        if (this.amount < this.maxAmount) {
            return 40;
        }
        return 0;
    }

    public Aspect getEssentiaType(ForgeDirection loc) {
        return this.aspect;
    }

    public int getEssentiaAmount(ForgeDirection loc) {
        return this.amount;
    }

    public Packet func_145844_m() {
        NBTTagCompound access = new NBTTagCompound();
        access.func_74768_a("accessTicks", this.accessTicks);
        access.func_74768_a("amount", this.amount);
        if (this.aspect != null) {
            access.func_74778_a("aspect", this.aspect.getTag());
        }
        access.func_74768_a("color", this.getSealColor());
        access.func_74778_a("network", this.networkName);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, access);
    }

    public int getAccessTicks() {
        return this.accessTicks;
    }

    public void func_70296_d() {
        super.func_70296_d();
        this.aspectList.remove(this.aspect);
        this.aspectList.add(this.aspect, this.amount);
        AspectList oldAspects = BoundJarNetworkManager.getAspect(this.networkName);
        oldAspects.remove(oldAspects.getAspects()[0]);
        oldAspects.add(this.aspect, this.amount);
        if (!this.field_145850_b.field_72995_K) {
            BoundJarNetworkManager.markDirty(this.networkName);
        }
    }

    public void setColor(int color) {
        this.colour = color;
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound access = pkt.func_148857_g();
        this.accessTicks = access.func_74762_e("accessTicks");
        this.amount = access.func_74762_e("amount");
        if (access.func_74779_i("aspect") != null) {
            this.aspect = Aspect.getAspect((String)access.func_74779_i("aspect"));
        }
        this.setColor(access.func_74762_e("color"));
        this.clientColor = access.func_74762_e("color");
        this.networkName = access.func_74779_i("network");
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.aspect = Aspect.getAspect((String)nbttagcompound.func_74779_i("Aspect"));
        this.amount = nbttagcompound.func_74765_d("Amount");
        this.facing = nbttagcompound.func_74771_c("facing");
        this.networkName = nbttagcompound.func_74779_i("network");
        this.colour = nbttagcompound.func_74762_e("colour");
    }

    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        if (this.aspect != null) {
            nbttagcompound.func_74778_a("Aspect", this.aspect.getTag());
        }
        nbttagcompound.func_74777_a("Amount", (short)this.amount);
        nbttagcompound.func_74774_a("facing", (byte)this.facing);
        nbttagcompound.func_74778_a("network", this.networkName);
        nbttagcompound.func_74768_a("colour", this.getSealColor());
    }

    public int getSealColor() {
        return this.colour;
    }

    public int addToContainer(Aspect tt, int am) {
        this.func_145845_h();
        if (am == 0) {
            return am;
        }
        if (this.amount < this.maxAmount && tt == this.aspect || this.amount == 0) {
            this.aspect = tt;
            int added = Math.min(am, this.maxAmount - this.amount);
            this.amount += added;
            am -= added;
        }
        this.accessTicks = 80;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        return am;
    }

    public boolean takeFromContainer(Aspect tt, int am) {
        this.func_145845_h();
        if (this.amount >= am && tt == this.aspect) {
            this.amount -= am;
            if (this.amount <= 0) {
                this.aspect = null;
                this.amount = 0;
            }
            this.accessTicks = 80;
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            return true;
        }
        return false;
    }

    public void func_145845_h() {
        this.aspectList = BoundJarNetworkManager.getAspect(this.networkName).copy();
        if (!this.field_145850_b.field_72995_K) {
            if (this.accessTicks > 0) {
                --this.accessTicks;
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
            if (this.aspect != this.aspectList.getAspects()[0]) {
                this.accessTicks = 80;
                this.aspect = this.aspectList.getAspects()[0];
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
            if (this.amount != this.aspectList.getAmount(this.aspect)) {
                this.accessTicks = 80;
                this.amount = this.aspectList.getAmount(this.aspect);
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
        super.func_145845_h();
    }

    public void setAspects(AspectList aspects) {
        super.setAspects(aspects);
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }
}

