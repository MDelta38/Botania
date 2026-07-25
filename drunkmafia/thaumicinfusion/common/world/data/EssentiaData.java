/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  thaumcraft.api.WorldCoordinates
 *  thaumcraft.api.aspects.Aspect
 */
package drunkmafia.thaumicinfusion.common.world.data;

import drunkmafia.thaumicinfusion.common.world.data.BlockSavable;
import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.WorldCoordinates;
import thaumcraft.api.aspects.Aspect;

public class EssentiaData
extends BlockSavable {
    private Aspect aspect;

    public EssentiaData() {
    }

    public EssentiaData(WorldCoordinates coordinates, Aspect aspect) {
        super(coordinates);
        this.aspect = aspect;
    }

    public Aspect getAspect() {
        return this.aspect;
    }

    @Override
    public void readNBT(NBTTagCompound tagCompound) {
        super.readNBT(tagCompound);
        this.aspect = Aspect.getAspect((String)tagCompound.func_74779_i("aspectTag"));
    }

    @Override
    public void writeNBT(NBTTagCompound tagCompound) {
        super.writeNBT(tagCompound);
        tagCompound.func_74778_a("aspectTag", this.aspect.getTag());
    }
}

