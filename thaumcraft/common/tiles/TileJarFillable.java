/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectSource;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.common.tiles.TileJar;

public class TileJarFillable
extends TileJar
implements IAspectSource,
IEssentiaTransport {
    public Aspect aspect = null;
    public Aspect aspectFilter = null;
    public int amount = 0;
    public int maxAmount = 64;
    public int facing = 2;
    public boolean forgeLiquid = false;
    public int lid = 0;
    int count = 0;

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.aspect = Aspect.getAspect(nbttagcompound.func_74779_i("Aspect"));
        this.aspectFilter = Aspect.getAspect(nbttagcompound.func_74779_i("AspectFilter"));
        this.amount = nbttagcompound.func_74765_d("Amount");
        this.facing = nbttagcompound.func_74771_c("facing");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        if (this.aspect != null) {
            nbttagcompound.func_74778_a("Aspect", this.aspect.getTag());
        }
        if (this.aspectFilter != null) {
            nbttagcompound.func_74778_a("AspectFilter", this.aspectFilter.getTag());
        }
        nbttagcompound.func_74777_a("Amount", (short)this.amount);
        nbttagcompound.func_74774_a("facing", (byte)this.facing);
    }

    @Override
    public AspectList getAspects() {
        AspectList al = new AspectList();
        if (this.aspect != null && this.amount > 0) {
            al.add(this.aspect, this.amount);
        }
        return al;
    }

    @Override
    public void setAspects(AspectList aspects) {
    }

    @Override
    public int addToContainer(Aspect tt, int am) {
        if (am == 0) {
            return am;
        }
        if (this.amount < this.maxAmount && tt == this.aspect || this.amount == 0) {
            this.aspect = tt;
            int added = Math.min(am, this.maxAmount - this.amount);
            this.amount += added;
            am -= added;
        }
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        this.func_70296_d();
        return am;
    }

    @Override
    public boolean takeFromContainer(Aspect tt, int am) {
        if (this.amount >= am && tt == this.aspect) {
            this.amount -= am;
            if (this.amount <= 0) {
                this.aspect = null;
                this.amount = 0;
            }
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
            return true;
        }
        return false;
    }

    @Override
    public boolean takeFromContainer(AspectList ot) {
        return false;
    }

    @Override
    public boolean doesContainerContainAmount(Aspect tag, int amt) {
        return this.amount >= amt && tag == this.aspect;
    }

    @Override
    public boolean doesContainerContain(AspectList ot) {
        for (Aspect tt : ot.getAspects()) {
            if (this.amount <= 0 || tt != this.aspect) continue;
            return true;
        }
        return false;
    }

    @Override
    public int containerContains(Aspect tag) {
        return 0;
    }

    @Override
    public boolean doesContainerAccept(Aspect tag) {
        return this.aspectFilter != null ? tag.equals(this.aspectFilter) : true;
    }

    @Override
    public boolean isConnectable(ForgeDirection face) {
        return face == ForgeDirection.UP;
    }

    @Override
    public boolean canInputFrom(ForgeDirection face) {
        return face == ForgeDirection.UP;
    }

    @Override
    public boolean canOutputTo(ForgeDirection face) {
        return face == ForgeDirection.UP;
    }

    @Override
    public void setSuction(Aspect aspect, int amount) {
    }

    @Override
    public boolean renderExtendedTube() {
        return true;
    }

    @Override
    public int getMinimumSuction() {
        return this.aspectFilter != null ? 64 : 32;
    }

    @Override
    public Aspect getSuctionType(ForgeDirection loc) {
        return this.aspectFilter != null ? this.aspectFilter : this.aspect;
    }

    @Override
    public int getSuctionAmount(ForgeDirection loc) {
        if (this.amount < this.maxAmount) {
            if (this.aspectFilter != null) {
                return 64;
            }
            return 32;
        }
        return 0;
    }

    @Override
    public Aspect getEssentiaType(ForgeDirection loc) {
        return this.aspect;
    }

    @Override
    public int getEssentiaAmount(ForgeDirection loc) {
        return this.amount;
    }

    @Override
    public int takeEssentia(Aspect aspect, int amount, ForgeDirection face) {
        return this.canOutputTo(face) && this.takeFromContainer(aspect, amount) ? amount : 0;
    }

    @Override
    public int addEssentia(Aspect aspect, int amount, ForgeDirection face) {
        return this.canInputFrom(face) ? amount - this.addToContainer(aspect, amount) : 0;
    }

    @Override
    public void func_145845_h() {
        super.func_145845_h();
        if (!this.field_145850_b.field_72995_K && ++this.count % 5 == 0 && this.amount < this.maxAmount) {
            this.fillJar();
        }
    }

    void fillJar() {
        TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, ForgeDirection.UP);
        if (te != null) {
            IEssentiaTransport ic = (IEssentiaTransport)te;
            if (!ic.canOutputTo(ForgeDirection.DOWN)) {
                return;
            }
            Aspect ta = null;
            if (this.aspectFilter != null) {
                ta = this.aspectFilter;
            } else if (this.aspect != null && this.amount > 0) {
                ta = this.aspect;
            } else if (ic.getEssentiaAmount(ForgeDirection.DOWN) > 0 && ic.getSuctionAmount(ForgeDirection.DOWN) < this.getSuctionAmount(ForgeDirection.UP) && this.getSuctionAmount(ForgeDirection.UP) >= ic.getMinimumSuction()) {
                ta = ic.getEssentiaType(ForgeDirection.DOWN);
            }
            if (ta != null && ic.getSuctionAmount(ForgeDirection.DOWN) < this.getSuctionAmount(ForgeDirection.UP)) {
                this.addToContainer(ta, ic.takeEssentia(ta, 1, ForgeDirection.DOWN));
            }
        }
    }
}

