/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 */
package thaumcraft.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.BlockAiry;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileNode;
import thaumcraft.common.tiles.TileNodeEnergized;
import thaumcraft.common.tiles.TileNodeStabilizer;

public class TileNodeConverter
extends TileThaumcraft {
    public int count = -1;
    public int status = 0;

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        TileEntity tilenew;
        NodeModifier mod;
        NodeType type;
        AspectList base;
        TileEntity tile;
        super.func_145845_h();
        if (this.count == -1) {
            this.checkStatus();
        }
        if (this.status == 1 && !this.field_145850_b.field_72995_K && this.count >= 1000 && (tile = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e)) != null && tile instanceof TileNode) {
            base = ((TileNode)tile).getAspectsBase();
            type = ((TileNode)tile).getNodeType();
            mod = ((TileNode)tile).getNodeModifier();
            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e, ConfigBlocks.blockAiry, 5, 3);
            tilenew = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
            if (tilenew != null && tilenew instanceof TileNodeEnergized) {
                ((TileNodeEnergized)tilenew).setNodeModifier(mod);
                ((TileNodeEnergized)tilenew).setNodeType(type);
                ((TileNodeEnergized)tilenew).setAspects(base.copy());
                ((TileNodeEnergized)tilenew).setupNode();
            }
            this.checkStatus();
            this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.func_145838_q(), 10, 10);
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
        }
        if (this.status == 2 && !this.field_145850_b.field_72995_K && this.count <= 50 && (tile = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e)) != null && tile instanceof TileNodeEnergized) {
            base = ((TileNodeEnergized)tile).getAuraBase();
            type = ((TileNodeEnergized)tile).getNodeType();
            mod = ((TileNodeEnergized)tile).getNodeModifier();
            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e, ConfigBlocks.blockAiry, 0, 3);
            tilenew = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
            if (tilenew != null && tilenew instanceof TileNode) {
                ((TileNode)tilenew).setNodeModifier(mod);
                ((TileNode)tilenew).setNodeType(type);
                ((TileNode)tilenew).setAspects(base.copy());
                for (Aspect a : ((TileNode)tilenew).getAspects().getAspects()) {
                    ((TileNode)tilenew).takeFromContainer(a, ((TileNode)tilenew).getAspects().getAmount(a));
                }
            }
            this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.func_145838_q(), 10, 10);
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
            this.status = 0;
        }
        if (this.status == 0 || !this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e)) {
            if (this.count > 0) {
                --this.count;
                if (this.count > 50 && this.field_145850_b.field_72995_K) {
                    if (this.field_145850_b.field_73012_v.nextBoolean()) {
                        Thaumcraft.proxy.nodeBolt(this.field_145850_b, (float)this.field_145851_c + 0.25f + this.field_145850_b.field_73012_v.nextFloat() * 0.5f, (float)this.field_145848_d + 0.5f, (float)this.field_145849_e + 0.25f + this.field_145850_b.field_73012_v.nextFloat() * 0.5f, (float)this.field_145851_c + 0.5f, (float)this.field_145848_d - 0.5f, (float)this.field_145849_e + 0.5f);
                    }
                    if (this.field_145850_b.field_73012_v.nextBoolean() && this.hasStabilizer()) {
                        Thaumcraft.proxy.nodeBolt(this.field_145850_b, (float)this.field_145851_c + 0.25f + this.field_145850_b.field_73012_v.nextFloat() * 0.5f, (float)this.field_145848_d - 1.5f, (float)this.field_145849_e + 0.25f + this.field_145850_b.field_73012_v.nextFloat() * 0.5f, (float)this.field_145851_c + 0.5f, (float)this.field_145848_d - 0.5f, (float)this.field_145849_e + 0.5f);
                    }
                }
            }
        } else if (this.count < 1000) {
            TileNode nd;
            AspectList al;
            TileEntity tilenew2;
            ++this.count;
            if (!this.field_145850_b.field_72995_K && (tilenew2 = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e)) != null && tilenew2 instanceof TileNode && (al = (nd = (TileNode)tilenew2).getAspects()).getAspects().length > 0) {
                nd.takeFromContainer(al.getAspects()[this.field_145850_b.field_73012_v.nextInt(al.getAspects().length)], 1);
                if (this.count % 5 == 0 || nd.getAspects().visSize() == 0) {
                    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
                }
            }
            if (this.count > 50 && this.field_145850_b.field_72995_K) {
                if (this.field_145850_b.field_73012_v.nextBoolean()) {
                    Thaumcraft.proxy.nodeBolt(this.field_145850_b, (float)this.field_145851_c + 0.25f + this.field_145850_b.field_73012_v.nextFloat() * 0.5f, (float)this.field_145848_d + 0.5f, (float)this.field_145849_e + 0.25f + this.field_145850_b.field_73012_v.nextFloat() * 0.5f, (float)this.field_145851_c + 0.5f, (float)this.field_145848_d - 0.5f, (float)this.field_145849_e + 0.5f);
                }
                if (this.field_145850_b.field_73012_v.nextBoolean() && this.hasStabilizer()) {
                    Thaumcraft.proxy.nodeBolt(this.field_145850_b, (float)this.field_145851_c + 0.25f + this.field_145850_b.field_73012_v.nextFloat() * 0.5f, (float)this.field_145848_d - 1.5f, (float)this.field_145849_e + 0.25f + this.field_145850_b.field_73012_v.nextFloat() * 0.5f, (float)this.field_145851_c + 0.5f, (float)this.field_145848_d - 0.5f, (float)this.field_145849_e + 0.5f);
                }
            }
        }
        if (this.count > 1000) {
            this.count = 1000;
        }
    }

    private boolean hasStabilizer() {
        TileEntity te = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e);
        return !this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e) && te != null && te instanceof TileNodeStabilizer;
    }

    public void checkStatus() {
        if (this.count == -1) {
            this.count = 0;
        }
        if (!(this.status != 2 || this.count <= 50 || this.hasStabilizer() && this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == ConfigBlocks.blockAiry && this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == 5)) {
            BlockAiry.explodify(this.func_145831_w(), this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
            this.status = 0;
            this.count = 50;
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        } else if (this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e) && this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == ConfigBlocks.blockAiry && this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == 0 && this.hasStabilizer()) {
            this.status = 1;
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        } else if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == ConfigBlocks.blockAiry && this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == 5) {
            this.status = 2;
            this.count = 1000;
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        } else {
            this.status = 0;
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.status = nbttagcompound.func_74762_e("status");
        this.count = nbttagcompound.func_74762_e("count");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.func_74768_a("status", this.status);
        nbttagcompound.func_74768_a("count", this.count);
    }

    public boolean func_145842_c(int i, int j) {
        if (i == 10 && j == 10) {
            if (this.field_145850_b.field_72995_K) {
                Thaumcraft.proxy.burst(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d - 0.5, (double)this.field_145849_e + 0.5, 1.0f);
                this.field_145850_b.func_72980_b((double)this.field_145851_c + 0.5, (double)this.field_145848_d - 0.5, (double)this.field_145849_e + 0.5, "thaumcraft:craftfail", 0.5f, 1.0f, false);
            }
            return true;
        }
        return super.func_145842_c(i, j);
    }
}

