/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;

public class TileBanner
extends TileThaumcraft {
    private byte facing = 0;
    private byte color = (byte)-1;
    private Aspect aspect = null;
    private boolean onWall = false;

    public boolean canUpdate() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 2), (double)(this.field_145849_e + 1));
    }

    public byte getFacing() {
        return this.facing;
    }

    public void setFacing(byte face) {
        this.facing = face;
        this.func_70296_d();
    }

    public boolean getWall() {
        return this.onWall;
    }

    public void setWall(boolean b) {
        this.onWall = b;
        this.func_70296_d();
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.facing = nbttagcompound.func_74771_c("facing");
        this.setColor(nbttagcompound.func_74771_c("color"));
        String as = nbttagcompound.func_74779_i("aspect");
        if (as != null && as.length() > 0) {
            this.setAspect(Aspect.getAspect(as));
        } else {
            this.aspect = null;
        }
        this.onWall = nbttagcompound.func_74767_n("wall");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74774_a("facing", this.facing);
        nbttagcompound.func_74774_a("color", this.getColor());
        nbttagcompound.func_74778_a("aspect", this.getAspect() == null ? "" : this.getAspect().getTag());
        nbttagcompound.func_74757_a("wall", this.onWall);
    }

    public Aspect getAspect() {
        return this.aspect;
    }

    public void setAspect(Aspect aspect) {
        this.aspect = aspect;
    }

    public byte getColor() {
        return this.color;
    }

    public void setColor(byte color) {
        this.color = color;
    }
}

