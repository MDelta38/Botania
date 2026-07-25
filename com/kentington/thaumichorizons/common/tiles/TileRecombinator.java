/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.lib.network.PacketHandler
 *  thaumcraft.common.lib.network.fx.PacketFXBlockZap
 *  thaumcraft.common.lib.research.ResearchManager
 *  thaumcraft.common.tiles.TileNode
 */
package com.kentington.thaumichorizons.common.tiles;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockZap;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.tiles.TileNode;

public class TileRecombinator
extends TileThaumcraft {
    public int count = -1;
    public boolean activated = false;
    public boolean shouldActivate = false;
    boolean fireOnce = false;

    public void func_145845_h() {
        super.func_145845_h();
        if (!this.fireOnce) {
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
            this.activated = true;
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        } else if (!this.shouldActivate && this.activated) {
            this.activated = false;
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
        if (!this.field_145850_b.field_72995_K && this.activated && this.count > 50 && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) instanceof TileNode) {
            TileEntity te;
            TileNode tile = (TileNode)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
            int x = this.field_145850_b.field_73012_v.nextInt(5) - this.field_145850_b.field_73012_v.nextInt(5);
            int y = this.field_145850_b.field_73012_v.nextInt(5) - this.field_145850_b.field_73012_v.nextInt(5) - 1;
            int z = this.field_145850_b.field_73012_v.nextInt(5) - this.field_145850_b.field_73012_v.nextInt(5);
            if ((x != 0 || y != -1 || z != 0) && (te = this.field_145850_b.func_147438_o(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z)) != null && te instanceof TileNode && this.field_145850_b.func_147439_a(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z) == ConfigBlocks.blockAiry) {
                if (te instanceof TileNode && ((TileNode)te).getLock() > 0) {
                    return;
                }
                TileNode nd = (TileNode)te;
                if (nd.getAspects().size() == 0) {
                    return;
                }
                this.processCombos(nd, tile, x, y, z);
            }
        }
    }

    void processCombos(TileNode nd, TileNode tile, int x, int y, int z) {
        AspectList possibleCombos = new AspectList();
        for (Aspect asp : tile.getAspectsBase().getAspects()) {
            if (!asp.isPrimal()) continue;
            for (Aspect asp2 : nd.getAspectsBase().getAspects()) {
                if (!asp2.isPrimal() || ResearchManager.getCombinationResult((Aspect)asp, (Aspect)asp2) == null) continue;
                possibleCombos.add(ResearchManager.getCombinationResult((Aspect)asp, (Aspect)asp2), 1);
            }
        }
        if (possibleCombos.size() > 0 && possibleCombos.getAspects()[0] != null) {
            this.doMerge(possibleCombos, nd, tile, x, y, z);
            return;
        }
        for (Aspect asp : tile.getAspectsBase().getAspects()) {
            if (!asp.isPrimal()) continue;
            for (Aspect asp2 : nd.getAspectsBase().getAspects()) {
                if (ResearchManager.getCombinationResult((Aspect)asp, (Aspect)asp2) == null) continue;
                possibleCombos.add(ResearchManager.getCombinationResult((Aspect)asp, (Aspect)asp2), 1);
            }
        }
        if (possibleCombos.size() > 0 && possibleCombos.getAspects()[0] != null) {
            this.doMerge(possibleCombos, nd, tile, x, y, z);
            return;
        }
        for (Aspect asp : tile.getAspectsBase().getAspects()) {
            for (Aspect asp2 : nd.getAspectsBase().getAspects()) {
                if (!asp2.isPrimal() || ResearchManager.getCombinationResult((Aspect)asp, (Aspect)asp2) == null) continue;
                possibleCombos.add(ResearchManager.getCombinationResult((Aspect)asp, (Aspect)asp2), 1);
            }
        }
        if (possibleCombos.size() > 0 && possibleCombos.getAspects()[0] != null) {
            this.doMerge(possibleCombos, nd, tile, x, y, z);
            return;
        }
        for (Aspect asp : tile.getAspectsBase().getAspects()) {
            for (Aspect asp2 : nd.getAspectsBase().getAspects()) {
                if (ResearchManager.getCombinationResult((Aspect)asp, (Aspect)asp2) == null) continue;
                possibleCombos.add(ResearchManager.getCombinationResult((Aspect)asp, (Aspect)asp2), 1);
            }
        }
        if (possibleCombos.size() > 0 && possibleCombos.getAspects()[0] != null) {
            this.doMerge(possibleCombos, nd, tile, x, y, z);
            return;
        }
    }

    public void doMerge(AspectList possibleCombos, TileNode nd, TileNode tile, int x, int y, int z) {
        Aspect aspB;
        Aspect aspA;
        int which = this.field_145850_b.field_73012_v.nextInt(possibleCombos.getAspects().length);
        Aspect toAdd = possibleCombos.getAspects()[which];
        tile.getAspectsBase().add(toAdd, 1);
        tile.getAspects().add(toAdd, 1);
        if (tile.getAspectsBase().getAmount(toAdd.getComponents()[0]) > 0) {
            aspA = toAdd.getComponents()[0];
            aspB = toAdd.getComponents()[1];
        } else {
            aspA = toAdd.getComponents()[1];
            aspB = toAdd.getComponents()[0];
        }
        tile.getAspectsBase().remove(aspA, 1);
        tile.getAspects().remove(aspA, 1);
        nd.getAspects().remove(aspB, 1);
        if (this.field_145850_b.field_73012_v.nextInt(3) == 0) {
            nd.setNodeVisBase(aspB, (short)(nd.getNodeVisBase(aspB) - 1));
        }
        this.field_145850_b.func_147471_g(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z);
        nd.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
        tile.func_70296_d();
        PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXBlockZap((float)(this.field_145851_c + x) + 0.5f, (float)(this.field_145848_d + y) + 0.5f, (float)(this.field_145849_e + z) + 0.5f, (float)this.field_145851_c + 0.5f, (float)this.field_145848_d + 0.5f, (float)this.field_145849_e + 0.5f), new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, (double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, 32.0));
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.func_74757_a("active", this.activated);
        nbttagcompound.func_74757_a("shouldactivate", this.shouldActivate);
        nbttagcompound.func_74768_a("count", this.count);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.activated = nbttagcompound.func_74767_n("active");
        this.shouldActivate = nbttagcompound.func_74767_n("shouldactivate");
        this.count = nbttagcompound.func_74762_e("count");
    }
}

