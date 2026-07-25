/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import java.awt.Color;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectSource;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.api.wands.IWandable;

public class TileEssentiaReservoir
extends TileThaumcraft
implements IAspectSource,
IWandable,
IEssentiaTransport {
    public AspectList essentia = new AspectList();
    public int maxAmount = 256;
    public ForgeDirection facing = ForgeDirection.DOWN;
    int count = 0;
    float tr = 1.0f;
    float tri = 0.0f;
    float tg = 1.0f;
    float tgi = 0.0f;
    float tb = 1.0f;
    float tbi = 0.0f;
    public float cr = 1.0f;
    public float cg = 1.0f;
    public float cb = 1.0f;
    public Aspect displayAspect = null;

    public boolean canUpdate() {
        return true;
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.essentia.readFromNBT(nbttagcompound);
        if (this.essentia.visSize() > this.maxAmount) {
            this.essentia = new AspectList();
        }
        this.facing = ForgeDirection.getOrientation((int)nbttagcompound.func_74771_c("face"));
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        this.essentia.writeToNBT(nbttagcompound);
        nbttagcompound.func_74774_a("face", (byte)this.facing.ordinal());
    }

    @Override
    public AspectList getAspects() {
        return this.essentia;
    }

    @Override
    public void setAspects(AspectList aspects) {
        this.essentia = aspects.copy();
    }

    @Override
    public int addToContainer(Aspect tt, int am) {
        if (am == 0) {
            return am;
        }
        int space = this.maxAmount - this.essentia.visSize();
        if (space >= am) {
            this.essentia.add(tt, am);
            am = 0;
        } else {
            this.essentia.add(tt, space);
            am -= space;
        }
        if (space > 0) {
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
        }
        return am;
    }

    @Override
    public boolean takeFromContainer(Aspect tt, int am) {
        if (this.essentia.getAmount(tt) >= am) {
            this.essentia.remove(tt, am);
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
        return this.essentia.getAmount(tag) >= amt;
    }

    @Override
    public boolean doesContainerContain(AspectList ot) {
        for (Aspect tt : ot.getAspects()) {
            if (this.essentia.getAmount(tt) >= ot.getAmount(tt)) continue;
            return false;
        }
        return true;
    }

    @Override
    public int containerContains(Aspect tag) {
        return this.essentia.getAmount(tag);
    }

    @Override
    public boolean doesContainerAccept(Aspect tag) {
        return true;
    }

    @Override
    public boolean isConnectable(ForgeDirection face) {
        return face == this.facing;
    }

    @Override
    public boolean canInputFrom(ForgeDirection face) {
        return face == this.facing;
    }

    @Override
    public boolean canOutputTo(ForgeDirection face) {
        return face == this.facing;
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
        return 24;
    }

    @Override
    public Aspect getSuctionType(ForgeDirection loc) {
        return null;
    }

    @Override
    public int getSuctionAmount(ForgeDirection loc) {
        return this.essentia.visSize() < this.maxAmount ? 24 : 0;
    }

    @Override
    public Aspect getEssentiaType(ForgeDirection loc) {
        return this.essentia.visSize() > 0 && loc == ForgeDirection.UNKNOWN ? this.essentia.getAspects()[0] : null;
    }

    @Override
    public int getEssentiaAmount(ForgeDirection loc) {
        return this.essentia.visSize();
    }

    @Override
    public int takeEssentia(Aspect aspect, int amount, ForgeDirection face) {
        return this.canOutputTo(face) && this.takeFromContainer(aspect, amount) ? amount : 0;
    }

    @Override
    public int addEssentia(Aspect aspect, int amount, ForgeDirection face) {
        return this.canInputFrom(face) ? amount - this.addToContainer(aspect, amount) : 0;
    }

    public void func_145845_h() {
        int vs;
        super.func_145845_h();
        ++this.count;
        if (!this.field_145850_b.field_72995_K && this.count % 5 == 0 && this.essentia.visSize() < this.maxAmount) {
            this.fillReservoir();
        }
        if (this.field_145850_b.field_72995_K && (vs = this.essentia.visSize()) > 0) {
            if (this.field_145850_b.field_73012_v.nextInt(500 - vs) == 0) {
                this.field_145850_b.func_72980_b((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "thaumcraft:creak", 1.0f, 1.4f + this.field_145850_b.field_73012_v.nextFloat() * 0.2f, false);
            }
            if (this.count % 20 == 0 && this.essentia.size() > 0) {
                this.displayAspect = this.essentia.getAspects()[this.count / 20 % this.essentia.size()];
                Color c = new Color(this.displayAspect.getColor());
                this.tr = (float)c.getRed() / 255.0f;
                this.tg = (float)c.getGreen() / 255.0f;
                this.tb = (float)c.getBlue() / 255.0f;
                this.tri = (this.cr - this.tr) / 20.0f;
                this.tgi = (this.cg - this.tg) / 20.0f;
                this.tbi = (this.cb - this.tb) / 20.0f;
            }
            if (this.displayAspect == null) {
                this.tb = 1.0f;
                this.tg = 1.0f;
                this.tr = 1.0f;
                this.tbi = 0.0f;
                this.tgi = 0.0f;
                this.tri = 0.0f;
            } else {
                this.cr -= this.tri;
                this.cg -= this.tgi;
                this.cb -= this.tbi;
            }
        }
    }

    void fillReservoir() {
        TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.facing);
        if (te != null) {
            IEssentiaTransport ic = (IEssentiaTransport)te;
            if (!ic.canOutputTo(this.facing.getOpposite())) {
                return;
            }
            Aspect ta = null;
            if (ic.getEssentiaAmount(this.facing.getOpposite()) > 0 && ic.getSuctionAmount(this.facing.getOpposite()) < this.getSuctionAmount(this.facing) && this.getSuctionAmount(this.facing) >= ic.getMinimumSuction()) {
                ta = ic.getEssentiaType(this.facing.getOpposite());
            }
            if (ta != null && ic.getSuctionAmount(this.facing.getOpposite()) < this.getSuctionAmount(this.facing)) {
                this.addToContainer(ta, ic.takeEssentia(ta, 1, this.facing.getOpposite()));
            }
        }
    }

    @Override
    public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
        this.facing = player.func_70093_af() ? ForgeDirection.getOrientation((int)side) : ForgeDirection.getOrientation((int)side).getOpposite();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
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
}

