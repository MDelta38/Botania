/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.common.Thaumcraft
 */
package com.kentington.thaumichorizons.common.tiles;

import com.kentington.thaumichorizons.client.fx.FXSonic;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileVortex;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.Thaumcraft;

public class TileVortexStabilizer
extends TileThaumcraft
implements IWandable {
    public boolean hasTarget;
    public int prevType;
    public int xTarget = Integer.MAX_VALUE;
    public int yTarget = Integer.MAX_VALUE;
    public int zTarget = Integer.MAX_VALUE;
    public TileEntity target = null;
    public int direction;
    boolean fireOnce = false;
    public boolean redstoned;
    public ForgeDirection dir;
    public Object theBeam = null;
    public Entity[] sonicFX = null;

    public void func_145845_h() {
        super.func_145845_h();
        if (!this.fireOnce) {
            ThaumicHorizons.blockVortexStabilizer.func_149695_a(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, ThaumicHorizons.blockVortexStabilizer);
            this.direction = (byte)this.func_145832_p();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.dir = ForgeDirection.getOrientation((int)this.direction);
            if (this.target == null) {
                this.target = this.field_145850_b.func_147438_o(this.xTarget, this.yTarget, this.zTarget);
            }
            this.fireOnce = true;
        }
        if (this.field_145850_b.func_72820_D() % 5L == 0L) {
            MovingObjectPosition mop = null;
            if (this.redstoned) {
                mop = this.field_145850_b.func_72933_a(Vec3.func_72443_a((double)((double)(this.field_145851_c + this.dir.offsetX) + 0.75), (double)((double)(this.field_145848_d + this.dir.offsetY) + 0.75), (double)((double)(this.field_145849_e + this.dir.offsetZ) + 0.75)), Vec3.func_72443_a((double)((double)(this.field_145851_c + this.dir.offsetX * 10) + 0.5), (double)((double)(this.field_145848_d + this.dir.offsetY * 10) + 0.5), (double)((double)(this.field_145849_e + this.dir.offsetZ * 10) + 0.5)));
            }
            if (mop != null) {
                if (mop.field_72311_b != this.xTarget || mop.field_72312_c != this.yTarget || mop.field_72309_d != this.zTarget) {
                    if (this.hasTarget) {
                        this.reHungrifyTarget();
                        this.hasTarget = false;
                    } else if (!this.hasTarget && this.field_145850_b.func_147438_o(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) instanceof INode) {
                        this.hasTarget = true;
                        this.target = this.field_145850_b.func_147438_o(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                        this.prevType = ((INode)this.field_145850_b.func_147438_o(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d)).getNodeType().ordinal();
                        this.deHungrifyTarget();
                    } else if (!this.hasTarget && this.field_145850_b.func_147438_o(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) instanceof TileVortex) {
                        this.hasTarget = true;
                        this.target = this.field_145850_b.func_147438_o(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                        this.deHungrifyTarget();
                    }
                    this.xTarget = mop.field_72311_b;
                    this.yTarget = mop.field_72312_c;
                    this.zTarget = mop.field_72309_d;
                    this.func_70296_d();
                    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                }
            } else {
                if (this.hasTarget) {
                    this.reHungrifyTarget();
                    this.hasTarget = false;
                    this.func_70296_d();
                    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                }
                this.xTarget = this.field_145851_c + this.dir.offsetX * 10;
                this.yTarget = this.field_145848_d + this.dir.offsetY * 10;
                this.zTarget = this.field_145849_e + this.dir.offsetZ * 10;
                this.target = null;
            }
        }
        if (this.field_145850_b.field_72995_K && this.redstoned && ThaumicHorizons.proxy.readyToRender() && this.xTarget != Integer.MAX_VALUE && this.yTarget != Integer.MAX_VALUE && this.zTarget != Integer.MAX_VALUE) {
            if (this.sonicFX == null) {
                this.sonicFX = new Entity[3];
            }
            for (int i = 0; i < 3; ++i) {
                if (this.sonicFX[i] != null && !this.sonicFX[i].field_70128_L) continue;
                this.sonicFX[i] = new FXSonic(Thaumcraft.proxy.getClientWorld(), (double)this.xTarget + 0.5, (double)this.yTarget + 0.5, (double)this.zTarget + 0.5, 10, this.direction);
                ThaumicHorizons.proxy.addEffect(this.sonicFX[i]);
                break;
            }
            this.theBeam = Thaumcraft.proxy.beamBore(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, (double)this.xTarget + 0.5 - (double)this.dir.offsetX, (double)this.yTarget + 0.5 - (double)this.dir.offsetY, (double)this.zTarget + 0.5 - (double)this.dir.offsetZ, 1, 33023, false, 2.0f, this.theBeam, 1);
        } else if (this.sonicFX != null) {
            for (int i = 0; i < 3; ++i) {
                if (this.sonicFX[i] == null) continue;
                this.sonicFX[i].func_70106_y();
                this.sonicFX[i] = null;
            }
        }
    }

    public void reHungrifyTarget() {
        if (this.target instanceof INode) {
            ((INode)this.target).setNodeType(NodeType.values()[this.prevType]);
        } else if (this.target instanceof TileVortex) {
            --((TileVortex)this.target).beams;
        }
        if (this.target != null) {
            this.target.func_70296_d();
            this.field_145850_b.func_147471_g(this.target.field_145851_c, this.target.field_145848_d, this.target.field_145849_e);
        }
    }

    void deHungrifyTarget() {
        if (this.target instanceof INode) {
            ((INode)this.target).setNodeType(NodeType.NORMAL);
        } else if (this.target instanceof TileVortex) {
            ++((TileVortex)this.target).beams;
        }
        if (this.target != null) {
            this.target.func_70296_d();
            this.field_145850_b.func_147471_g(this.target.field_145851_c, this.target.field_145848_d, this.target.field_145849_e);
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.func_74768_a("xT", this.xTarget);
        nbttagcompound.func_74768_a("yT", this.yTarget);
        nbttagcompound.func_74768_a("zT", this.zTarget);
        nbttagcompound.func_74768_a("direction", this.direction);
        nbttagcompound.func_74757_a("hasTarget", this.hasTarget);
        nbttagcompound.func_74757_a("active", this.redstoned);
        nbttagcompound.func_74768_a("prevType", this.prevType);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.xTarget = nbttagcompound.func_74762_e("xT");
        this.yTarget = nbttagcompound.func_74762_e("yT");
        this.zTarget = nbttagcompound.func_74762_e("zT");
        this.direction = nbttagcompound.func_74762_e("direction");
        this.hasTarget = nbttagcompound.func_74767_n("hasTarget");
        this.redstoned = nbttagcompound.func_74767_n("active");
        this.prevType = nbttagcompound.func_74762_e("prevType");
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return INFINITE_EXTENT_AABB;
    }

    @Override
    public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
        this.dir = ForgeDirection.getOrientation((int)side);
        this.direction = side;
        world.func_72921_c(x, y, z, side, 3);
        player.field_70170_p.func_72980_b((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:tool", 0.5f, 0.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
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

