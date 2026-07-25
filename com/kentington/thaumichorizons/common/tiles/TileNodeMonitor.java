/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.kentington.thaumichorizons.common.tiles;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.blocks.BlockNodeMonitor;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.nodes.INode;

public class TileNodeMonitor
extends TileThaumcraft {
    public byte direction = (byte)this.field_145847_g;
    public boolean activated = false;
    boolean lastActivated = false;
    public int rotation = 0;
    public boolean switchy = false;

    public void func_145845_h() {
        ForgeDirection dir;
        if (!this.activated) {
            this.switchy = false;
            ++this.rotation;
            if (this.rotation > 360) {
                this.rotation -= 360;
            }
        } else if (this.field_145850_b.field_72995_K && Minecraft.func_71410_x().field_71439_g.field_70173_aa % 15 == 0) {
            boolean bl = this.switchy = !this.switchy;
        }
        if (this.direction == -1) {
            this.direction = (byte)this.func_145832_p();
        }
        if ((dir = ForgeDirection.getOrientation((int)this.direction)) == ForgeDirection.UP && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) instanceof INode) {
            this.activated = this.aspectCritical(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
        } else if (dir == ForgeDirection.DOWN && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) instanceof INode) {
            this.activated = this.aspectCritical(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
        } else if (dir == ForgeDirection.NORTH && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) instanceof INode) {
            this.activated = this.aspectCritical(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1);
        } else if (dir == ForgeDirection.SOUTH && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) instanceof INode) {
            this.activated = this.aspectCritical(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1);
        } else if (dir == ForgeDirection.WEST && this.field_145850_b.func_147438_o(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) instanceof INode) {
            this.activated = this.aspectCritical(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e);
        } else if (dir == ForgeDirection.EAST && this.field_145850_b.func_147438_o(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) instanceof INode) {
            this.activated = this.aspectCritical(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e);
        } else {
            ((BlockNodeMonitor)ThaumicHorizons.blockNodeMonitor).killMe(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
        if (this.activated != this.lastActivated) {
            this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, ThaumicHorizons.blockNodeMonitor);
        }
        this.lastActivated = this.activated;
    }

    private boolean aspectCritical(int x, int y, int z) {
        TileEntity node = this.field_145850_b.func_147438_o(x, y, z);
        if (node instanceof INode) {
            for (Aspect asp : ((INode)node).getAspects().getAspects()) {
                if (((INode)node).getAspects().getAmount(asp) > 1) continue;
                return true;
            }
        }
        return false;
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.func_74757_a("active", this.activated);
        nbttagcompound.func_74757_a("lastactive", this.lastActivated);
        nbttagcompound.func_74774_a("dir", this.direction);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.activated = nbttagcompound.func_74767_n("active");
        this.lastActivated = nbttagcompound.func_74767_n("lastactive");
        this.direction = nbttagcompound.func_74771_c("dir");
    }
}

