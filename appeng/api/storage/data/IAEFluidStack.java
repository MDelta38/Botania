/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fluids.Fluid
 *  net.minecraftforge.fluids.FluidStack
 */
package appeng.api.storage.data;

import appeng.api.storage.data.IAEStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public interface IAEFluidStack
extends IAEStack<IAEFluidStack> {
    public FluidStack getFluidStack();

    @Override
    public IAEFluidStack copy();

    @Override
    public void add(IAEFluidStack var1);

    public Fluid getFluid();
}

