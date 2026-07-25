/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.fluids.Fluid
 *  net.minecraftforge.fluids.FluidRegistry
 *  net.minecraftforge.fluids.FluidStack
 *  net.minecraftforge.fluids.FluidTankInfo
 *  net.minecraftforge.fluids.IFluidHandler
 *  net.minecraftforge.fluids.IFluidTank
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.common.tiles.TileVisRelay
 */
package flaxbeard.thaumicexploration.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.tiles.TileVisRelay;

public class TileEntityEverburnUrn
extends TileVisRelay
implements IFluidTank,
IFluidHandler {
    private int ticks = 0;
    private int drainTicks = 0;
    public float ignisVis;
    private int dX;
    private int dY;
    private int dZ;
    private int excessTicks = 0;
    private int drainType = 0;
    private float distance = 0.0f;
    private int range = 3;
    private int yRange = 2;
    private EntityPlayer burningPlayer;
    public static int CONVERSION_FACTOR = 250;

    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.ignisVis = nbttagcompound.func_74760_g("ignisVis");
    }

    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.func_74776_a("ignisVis", this.ignisVis);
    }

    public FluidStack getFluid() {
        return new FluidStack(FluidRegistry.LAVA, (int)Math.floor(this.ignisVis * (float)CONVERSION_FACTOR));
    }

    public int getFluidAmount() {
        return (int)Math.floor(this.ignisVis * (float)CONVERSION_FACTOR);
    }

    public int getCapacity() {
        return 4 * CONVERSION_FACTOR;
    }

    public FluidTankInfo getInfo() {
        return new FluidTankInfo((IFluidTank)this);
    }

    public int fill(FluidStack resource, boolean doFill) {
        return 0;
    }

    public FluidStack drain(int maxDrain, boolean doDrain) {
        float drained = maxDrain;
        if ((float)this.getFluidAmount() < drained) {
            drained = this.getFluidAmount();
        }
        if (doDrain) {
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.ignisVis -= drained / 250.0f;
        }
        FluidStack stack = new FluidStack(FluidRegistry.LAVA, (int)drained);
        return stack;
    }

    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        return this.fill(resource, doFill);
    }

    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        if (!resource.isFluidEqual(new FluidStack(FluidRegistry.LAVA, 1)) || from != ForgeDirection.UP) {
            return null;
        }
        if (doDrain) {
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
        return this.drain(resource.amount, doDrain);
    }

    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        if (from == ForgeDirection.UP) {
            if (doDrain) {
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
            return this.drain(maxDrain, doDrain);
        }
        FluidStack stack = new FluidStack(FluidRegistry.LAVA, 0);
        return stack;
    }

    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return false;
    }

    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return from == ForgeDirection.UP;
    }

    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[]{this.getInfo()};
    }

    public void func_145845_h() {
        super.func_145845_h();
        ++this.ticks;
        if (this.ticks == 10) {
            if (this.ignisVis < 16.0f) {
                this.ignisVis += (float)this.consumeVis(Aspect.FIRE, 1);
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
            this.ticks = 0;
        }
    }
}

