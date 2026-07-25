/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.lib.network.PacketHandler
 *  thaumcraft.common.lib.network.fx.PacketFXBlockZap
 *  thaumcraft.common.tiles.TileNode
 *  thaumcraft.common.tiles.TileNodeConverter
 *  thaumcraft.common.tiles.TileNodeEnergized
 */
package com.kentington.thaumichorizons.common.tiles;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.blocks.BlockTransductionAmplifier;
import com.kentington.thaumichorizons.common.tiles.TileVortex;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockZap;
import thaumcraft.common.tiles.TileNode;
import thaumcraft.common.tiles.TileNodeConverter;
import thaumcraft.common.tiles.TileNodeEnergized;

public class TileTransductionAmplifier
extends TileThaumcraft {
    public int count = -1;
    public byte direction = (byte)-1;
    public boolean activated = false;
    public boolean shouldActivate = false;
    boolean lastActivated;
    boolean fireOnce = false;

    public void func_145845_h() {
        ForgeDirection dir;
        super.func_145845_h();
        if (!this.fireOnce) {
            this.direction = (byte)this.func_145832_p();
            this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e).func_149695_a(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, null);
            this.fireOnce = true;
        }
        if (this.activated) {
            ++this.count;
        } else if (!this.activated && this.count > 0) {
            if (this.count > 50) {
                this.count = 50;
            }
            --this.count;
        }
        if (this.shouldActivate && !this.activated) {
            if (this.direction == -1) {
                this.direction = (byte)this.func_145832_p();
            }
            if ((dir = ForgeDirection.getOrientation((int)this.direction)) == ForgeDirection.UP && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) instanceof TileNodeEnergized) {
                this.boostNode(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
            } else if (dir == ForgeDirection.DOWN && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) instanceof TileNodeEnergized) {
                this.boostNode(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
            } else if (dir == ForgeDirection.NORTH && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) instanceof TileNodeEnergized) {
                this.boostNode(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1);
            } else if (dir == ForgeDirection.SOUTH && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) instanceof TileNodeEnergized) {
                this.boostNode(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1);
            } else if (dir == ForgeDirection.WEST && this.field_145850_b.func_147438_o(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) instanceof TileNodeEnergized) {
                this.boostNode(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e);
            } else if (dir == ForgeDirection.EAST && this.field_145850_b.func_147438_o(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) instanceof TileNodeEnergized) {
                this.boostNode(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e);
            } else {
                ((BlockTransductionAmplifier)ThaumicHorizons.blockTransducer).killMe(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, true);
                this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
            this.activated = true;
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        } else if (!this.shouldActivate && this.activated) {
            if (this.direction == -1) {
                this.direction = (byte)this.func_145832_p();
            }
            if ((dir = ForgeDirection.getOrientation((int)this.direction)) == ForgeDirection.UP && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) instanceof TileNodeEnergized) {
                this.unboostNode(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
            } else if (dir == ForgeDirection.DOWN && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) instanceof TileNodeEnergized) {
                this.unboostNode(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
            } else if (dir == ForgeDirection.NORTH && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) instanceof TileNodeEnergized) {
                this.unboostNode(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1);
            } else if (dir == ForgeDirection.SOUTH && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) instanceof TileNodeEnergized) {
                this.unboostNode(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1);
            } else if (dir == ForgeDirection.WEST && this.field_145850_b.func_147438_o(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) instanceof TileNodeEnergized) {
                this.unboostNode(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e);
            } else if (dir == ForgeDirection.EAST && this.field_145850_b.func_147438_o(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) instanceof TileNodeEnergized) {
                this.unboostNode(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e);
            } else {
                ((BlockTransductionAmplifier)ThaumicHorizons.blockTransducer).killMe(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, true);
                this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
            this.activated = false;
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
        if (!this.field_145850_b.field_72995_K && this.activated && this.count % 10 == 0) {
            List targets;
            int dz;
            int dy;
            int dx;
            int ecks = this.field_145851_c;
            int why = this.field_145848_d;
            int zee = this.field_145849_e;
            ForgeDirection dir2 = ForgeDirection.getOrientation((int)this.direction);
            if (dir2 == ForgeDirection.NORTH && (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) instanceof TileNodeEnergized || this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) instanceof TileNode)) {
                ++zee;
            } else if (dir2 == ForgeDirection.SOUTH && (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) instanceof TileNodeEnergized || this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) instanceof TileNode)) {
                --zee;
            } else if (dir2 == ForgeDirection.WEST && (this.field_145850_b.func_147438_o(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) instanceof TileNodeEnergized || this.field_145850_b.func_147438_o(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) instanceof TileNode)) {
                ++ecks;
            } else if (dir2 == ForgeDirection.EAST && (this.field_145850_b.func_147438_o(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) instanceof TileNodeEnergized || this.field_145850_b.func_147438_o(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) instanceof TileNode)) {
                --ecks;
            }
            int transducers = 0;
            if (this.field_145850_b.func_147439_a(ecks + 1, why, zee) == ThaumicHorizons.blockTransducer && ((TileTransductionAmplifier)this.field_145850_b.func_147438_o((int)(ecks + 1), (int)why, (int)zee)).activated) {
                ++transducers;
            }
            if (this.field_145850_b.func_147439_a(ecks - 1, why, zee) == ThaumicHorizons.blockTransducer && ((TileTransductionAmplifier)this.field_145850_b.func_147438_o((int)(ecks - 1), (int)why, (int)zee)).activated) {
                ++transducers;
            }
            if (this.field_145850_b.func_147439_a(ecks, why, zee + 1) == ThaumicHorizons.blockTransducer && ((TileTransductionAmplifier)this.field_145850_b.func_147438_o((int)ecks, (int)why, (int)(zee + 1))).activated) {
                ++transducers;
            }
            if (this.field_145850_b.func_147439_a(ecks, why, zee - 1) == ThaumicHorizons.blockTransducer && ((TileTransductionAmplifier)this.field_145850_b.func_147438_o((int)ecks, (int)why, (int)(zee - 1))).activated) {
                ++transducers;
            }
            if (transducers > 3 && this.count % 50 == 0) {
                this.unboostNode(ecks, why, zee);
                ((TileNodeConverter)this.field_145850_b.func_147438_o((int)ecks, (int)(why + 1), (int)zee)).status = -1;
                AspectList aspects = ((TileNodeEnergized)this.field_145850_b.func_147438_o(ecks, why, zee)).getAuraBase().copy();
                this.field_145850_b.func_147465_d(ecks, why, zee, ThaumicHorizons.blockVortex, 0, 3);
                ((TileVortex)this.field_145850_b.func_147438_o((int)ecks, (int)why, (int)zee)).aspects = aspects;
            }
            if (transducers > 2 && this.field_145850_b.field_73012_v.nextInt(4) == 2 && this.count % 50 == 0 && this.field_145850_b.func_147437_c(this.field_145851_c + (dx = this.field_145850_b.field_73012_v.nextInt(16) - 8), this.field_145848_d + (dy = this.field_145850_b.field_73012_v.nextInt(16) - 8), this.field_145849_e + (dz = this.field_145850_b.field_73012_v.nextInt(16) - 8))) {
                PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlockZap((float)this.field_145851_c + 0.5f, (float)this.field_145848_d + 0.5f, (float)this.field_145849_e + 0.5f, (float)this.field_145851_c + 0.5f + (float)dx, (float)this.field_145848_d + 0.5f + (float)dy, (float)this.field_145849_e + 0.5f + (float)dz), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, (double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, 32.0));
                if (dy > 0) {
                    this.field_145850_b.func_147449_b(this.field_145851_c + dx, this.field_145848_d + dy, this.field_145849_e + dz, ConfigBlocks.blockFluxGas);
                } else {
                    this.field_145850_b.func_147449_b(this.field_145851_c + dx, this.field_145848_d + dy, this.field_145849_e + dz, ConfigBlocks.blockFluxGoo);
                }
            }
            if (transducers > 1 && this.count % 50 == 0 && (targets = this.field_145850_b.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)).func_72314_b(10.0, 10.0, 10.0))) != null && targets.size() > 0) {
                for (Entity target : targets) {
                    PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlockZap((float)this.field_145851_c + 0.5f, (float)this.field_145848_d + 0.5f, (float)this.field_145849_e + 0.5f, (float)target.field_70165_t, (float)target.field_70163_u + target.field_70131_O / 2.0f, (float)target.field_70161_v), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, (double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, 32.0));
                    target.func_70097_a(DamageSource.field_76376_m, (float)(4 + this.field_145850_b.field_73012_v.nextInt(1)));
                }
            }
            if (this.field_145850_b.func_147438_o(ecks, why, zee) instanceof TileNode) {
                for (int i = 0; i < transducers; ++i) {
                    this.unboostNode(ecks, why, zee);
                }
                this.activated = false;
                this.func_70296_d();
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }
    }

    void boostNode(int x, int y, int z) {
        TileEntity tyle = this.field_145850_b.func_147438_o(x, y, z);
        if (tyle instanceof TileNodeEnergized) {
            TileNodeEnergized node = (TileNodeEnergized)tyle;
            AspectList baseVis = node.getAuraBase();
            for (Aspect asp : baseVis.getAspects()) {
                baseVis.add(asp, 10);
            }
            node.setAspects(baseVis);
            node.setupNode();
        }
    }

    public void unBoostNode(int x, int y, int z) {
        ForgeDirection dir = ForgeDirection.getOrientation((int)this.direction);
        if (dir == ForgeDirection.UP && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) instanceof TileNodeEnergized) {
            this.unboostNode(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
        } else if (dir == ForgeDirection.DOWN && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) instanceof TileNodeEnergized) {
            this.unboostNode(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
        } else if (dir == ForgeDirection.NORTH && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) instanceof TileNodeEnergized) {
            this.unboostNode(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1);
        } else if (dir == ForgeDirection.SOUTH && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) instanceof TileNodeEnergized) {
            this.unboostNode(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1);
        } else if (dir == ForgeDirection.WEST && this.field_145850_b.func_147438_o(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) instanceof TileNodeEnergized) {
            this.unboostNode(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e);
        } else if (dir == ForgeDirection.EAST && this.field_145850_b.func_147438_o(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) instanceof TileNodeEnergized) {
            this.unboostNode(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e);
        }
    }

    void unboostNode(int x, int y, int z) {
        TileEntity tyle = this.field_145850_b.func_147438_o(x, y, z);
        if (tyle instanceof TileNodeEnergized) {
            TileNodeEnergized node = (TileNodeEnergized)tyle;
            AspectList baseVis = node.getAuraBase();
            for (Aspect asp : baseVis.getAspects()) {
                baseVis.remove(asp, 10);
            }
            node.setAspects(baseVis);
            node.setupNode();
        } else if (tyle instanceof TileNode) {
            TileNode node = (TileNode)tyle;
            AspectList baseVis = node.getAspectsBase();
            for (Aspect asp : baseVis.getAspects()) {
                baseVis.remove(asp, 10);
            }
            node.setAspects(baseVis);
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.func_74757_a("active", this.activated);
        nbttagcompound.func_74774_a("dir", this.direction);
        nbttagcompound.func_74757_a("shouldactivate", this.shouldActivate);
        nbttagcompound.func_74768_a("count", this.count);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.activated = nbttagcompound.func_74767_n("active");
        this.direction = nbttagcompound.func_74771_c("dir");
        this.shouldActivate = nbttagcompound.func_74767_n("shouldactivate");
        this.count = nbttagcompound.func_74762_e("count");
    }
}

