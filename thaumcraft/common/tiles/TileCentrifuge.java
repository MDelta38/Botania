/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;

public class TileCentrifuge
extends TileThaumcraft
implements IAspectContainer,
IEssentiaTransport {
    public Aspect aspectOut = null;
    public Aspect aspectIn = null;
    public ForgeDirection facing = ForgeDirection.NORTH;
    int count = 0;
    int process = 0;
    float rotationSpeed = 0.0f;
    public float rotation = 0.0f;

    public boolean canUpdate() {
        return true;
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.aspectIn = Aspect.getAspect(nbttagcompound.func_74779_i("aspectIn"));
        this.aspectOut = Aspect.getAspect(nbttagcompound.func_74779_i("aspectOut"));
        this.facing = ForgeDirection.getOrientation((int)nbttagcompound.func_74762_e("facing"));
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        if (this.aspectIn != null) {
            nbttagcompound.func_74778_a("aspectIn", this.aspectIn.getTag());
        }
        if (this.aspectOut != null) {
            nbttagcompound.func_74778_a("aspectOut", this.aspectOut.getTag());
        }
        nbttagcompound.func_74768_a("facing", this.facing.ordinal());
    }

    @Override
    public AspectList getAspects() {
        AspectList al = new AspectList();
        if (this.aspectOut != null) {
            al.add(this.aspectOut, 1);
        }
        return al;
    }

    @Override
    public int addToContainer(Aspect tt, int am) {
        if (am > 0 && this.aspectOut == null) {
            this.aspectOut = tt;
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            --am;
        }
        return am;
    }

    @Override
    public boolean takeFromContainer(Aspect tt, int am) {
        if (this.aspectOut != null && tt == this.aspectOut) {
            this.aspectOut = null;
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
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
        return amt == 1 && tag == this.aspectOut;
    }

    @Override
    public boolean doesContainerContain(AspectList ot) {
        for (Aspect tt : ot.getAspects()) {
            if (tt != this.aspectOut) continue;
            return true;
        }
        return false;
    }

    @Override
    public int containerContains(Aspect tag) {
        return tag == this.aspectOut ? 1 : 0;
    }

    @Override
    public boolean doesContainerAccept(Aspect tag) {
        return true;
    }

    @Override
    public boolean isConnectable(ForgeDirection face) {
        return face == ForgeDirection.UP || face == ForgeDirection.DOWN;
    }

    @Override
    public boolean canInputFrom(ForgeDirection face) {
        return face == ForgeDirection.DOWN;
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
        return false;
    }

    @Override
    public int getMinimumSuction() {
        return 0;
    }

    @Override
    public Aspect getSuctionType(ForgeDirection face) {
        return null;
    }

    @Override
    public int getSuctionAmount(ForgeDirection face) {
        return face == ForgeDirection.DOWN ? (this.gettingPower() ? 0 : (this.aspectIn == null ? 128 : 64)) : 0;
    }

    @Override
    public Aspect getEssentiaType(ForgeDirection loc) {
        return this.aspectOut;
    }

    @Override
    public int getEssentiaAmount(ForgeDirection loc) {
        return this.aspectOut != null ? 1 : 0;
    }

    @Override
    public int takeEssentia(Aspect aspect, int amount, ForgeDirection face) {
        return this.canOutputTo(face) && this.takeFromContainer(aspect, amount) ? amount : 0;
    }

    @Override
    public int addEssentia(Aspect aspect, int amount, ForgeDirection face) {
        if (this.aspectIn == null && !aspect.isPrimal()) {
            this.aspectIn = aspect;
            this.process = 39;
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            return 1;
        }
        return 0;
    }

    public void func_145845_h() {
        super.func_145845_h();
        if (!this.field_145850_b.field_72995_K) {
            if (!this.gettingPower()) {
                if (this.aspectOut == null && this.aspectIn == null && ++this.count % 5 == 0) {
                    this.drawEssentia();
                }
                if (this.process > 0) {
                    --this.process;
                }
                if (this.aspectOut == null && this.aspectIn != null && this.process == 0) {
                    this.processEssentia();
                }
            }
        } else {
            if (this.aspectIn != null && !this.gettingPower() && this.rotationSpeed < 20.0f) {
                this.rotationSpeed += 2.0f;
            }
            if ((this.aspectIn == null || this.gettingPower()) && this.rotationSpeed > 0.0f) {
                this.rotationSpeed -= 0.5f;
            }
            int pr = (int)this.rotation;
            this.rotation += this.rotationSpeed;
            if (this.rotation % 180.0f <= 20.0f && pr % 180 >= 160 && this.rotationSpeed > 0.0f) {
                this.field_145850_b.func_72980_b((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "thaumcraft:pump", 1.0f, 1.0f, false);
            }
        }
    }

    void processEssentia() {
        Aspect[] comps = this.aspectIn.getComponents();
        this.aspectOut = comps[this.field_145850_b.field_73012_v.nextInt(2)];
        this.aspectIn = null;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    void drawEssentia() {
        TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, ForgeDirection.DOWN);
        if (te != null) {
            IEssentiaTransport ic = (IEssentiaTransport)te;
            if (!ic.canOutputTo(ForgeDirection.UP)) {
                return;
            }
            Aspect ta = null;
            if (ic.getEssentiaAmount(ForgeDirection.UP) > 0 && ic.getSuctionAmount(ForgeDirection.UP) < this.getSuctionAmount(ForgeDirection.DOWN) && this.getSuctionAmount(ForgeDirection.DOWN) >= ic.getMinimumSuction()) {
                ta = ic.getEssentiaType(ForgeDirection.UP);
            }
            if (ta != null && !ta.isPrimal() && ic.getSuctionAmount(ForgeDirection.UP) < this.getSuctionAmount(ForgeDirection.DOWN) && ic.takeEssentia(ta, 1, ForgeDirection.UP) == 1) {
                this.aspectIn = ta;
                this.process = 39;
                this.func_70296_d();
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }
    }

    @Override
    public void setAspects(AspectList aspects) {
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)(this.field_145851_c - 1), (double)(this.field_145848_d - 1), (double)(this.field_145849_e - 1), (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1));
    }

    public boolean gettingPower() {
        return this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }
}

