/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package thaumcraft.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.common.tiles.TileTube;

public class TileTubeFilter
extends TileTube
implements IAspectContainer {
    public Aspect aspectFilter = null;

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.aspectFilter = Aspect.getAspect(nbttagcompound.func_74779_i("AspectFilter"));
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        if (this.aspectFilter != null) {
            nbttagcompound.func_74778_a("AspectFilter", this.aspectFilter.getTag());
        }
    }

    @Override
    void calculateSuction(Aspect filter, boolean restrict, boolean dir) {
        super.calculateSuction(this.aspectFilter, restrict, dir);
    }

    @Override
    public AspectList getAspects() {
        if (this.aspectFilter != null) {
            return new AspectList().add(this.aspectFilter, -1);
        }
        return null;
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

