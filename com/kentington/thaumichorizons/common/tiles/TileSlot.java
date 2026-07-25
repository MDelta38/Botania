/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.NBTTagCompound
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.items.wands.ItemWandCasting
 */
package com.kentington.thaumichorizons.common.tiles;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.lib.PocketPlaneData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.wands.ItemWandCasting;

public class TileSlot
extends TileThaumcraft {
    public boolean hasKeystone = false;
    public boolean portalOpen = false;
    public int pocketID = 0;
    public int which = 0;
    public boolean xAligned = false;

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.func_74768_a("pocketPlaneID", this.pocketID);
        nbttagcompound.func_74768_a("which", this.which);
        nbttagcompound.func_74757_a("hasKeystone", this.hasKeystone);
        nbttagcompound.func_74757_a("portalOpen", this.portalOpen);
        nbttagcompound.func_74757_a("aligned", this.xAligned);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.pocketID = nbttagcompound.func_74762_e("pocketPlaneID");
        this.which = nbttagcompound.func_74762_e("which");
        this.hasKeystone = nbttagcompound.func_74767_n("hasKeystone");
        this.portalOpen = nbttagcompound.func_74767_n("portalOpen");
        this.xAligned = nbttagcompound.func_74767_n("aligned");
    }

    public void destroyPortal() {
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        if (this.xAligned) {
            int y;
            int x;
            this.field_145850_b.func_147449_b(this.field_145851_c - 2, this.field_145848_d, this.field_145849_e, Blocks.field_150368_y);
            this.field_145850_b.func_147449_b(this.field_145851_c + 2, this.field_145848_d, this.field_145849_e, Blocks.field_150368_y);
            this.field_145850_b.func_147449_b(this.field_145851_c - 2, this.field_145848_d - 4, this.field_145849_e, Blocks.field_150368_y);
            this.field_145850_b.func_147449_b(this.field_145851_c + 2, this.field_145848_d - 4, this.field_145849_e, Blocks.field_150368_y);
            this.field_145850_b.func_147465_d(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockCosmeticSolid, 6, 3);
            this.field_145850_b.func_147465_d(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockCosmeticSolid, 6, 3);
            this.field_145850_b.func_147465_d(this.field_145851_c - 1, this.field_145848_d - 4, this.field_145849_e, ConfigBlocks.blockCosmeticSolid, 6, 3);
            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d - 4, this.field_145849_e, ConfigBlocks.blockCosmeticSolid, 6, 3);
            this.field_145850_b.func_147465_d(this.field_145851_c + 1, this.field_145848_d - 4, this.field_145849_e, ConfigBlocks.blockCosmeticSolid, 6, 3);
            this.field_145850_b.func_147465_d(this.field_145851_c - 2, this.field_145848_d - 1, this.field_145849_e, ConfigBlocks.blockCosmeticSolid, 6, 3);
            this.field_145850_b.func_147465_d(this.field_145851_c - 2, this.field_145848_d - 2, this.field_145849_e, ConfigBlocks.blockCosmeticSolid, 6, 3);
            this.field_145850_b.func_147465_d(this.field_145851_c - 2, this.field_145848_d - 3, this.field_145849_e, ConfigBlocks.blockCosmeticSolid, 6, 3);
            this.field_145850_b.func_147465_d(this.field_145851_c + 2, this.field_145848_d - 1, this.field_145849_e, ConfigBlocks.blockCosmeticSolid, 6, 3);
            this.field_145850_b.func_147465_d(this.field_145851_c + 2, this.field_145848_d - 2, this.field_145849_e, ConfigBlocks.blockCosmeticSolid, 6, 3);
            this.field_145850_b.func_147465_d(this.field_145851_c + 2, this.field_145848_d - 3, this.field_145849_e, ConfigBlocks.blockCosmeticSolid, 6, 3);
            for (x = -1; x <= 1; ++x) {
                for (y = -1; y >= -3; --y) {
                    this.field_145850_b.func_147468_f(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e);
                }
            }
            for (x = -2; x <= 2; ++x) {
                for (y = 0; y >= -4; --y) {
                    this.field_145850_b.func_147471_g(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e);
                }
            }
        } else {
            int y;
            int z;
            this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d, this.field_145849_e - 2, Blocks.field_150368_y);
            this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d, this.field_145849_e + 2, Blocks.field_150368_y);
            this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - 4, this.field_145849_e - 2, Blocks.field_150368_y);
            this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d - 4, this.field_145849_e + 2, Blocks.field_150368_y);
            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1, ConfigBlocks.blockCosmeticSolid, 6, 3);
            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1, ConfigBlocks.blockCosmeticSolid, 6, 3);
            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d - 4, this.field_145849_e - 1, ConfigBlocks.blockCosmeticSolid, 6, 3);
            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d - 4, this.field_145849_e, ConfigBlocks.blockCosmeticSolid, 6, 3);
            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d - 4, this.field_145849_e + 1, ConfigBlocks.blockCosmeticSolid, 6, 3);
            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e - 2, ConfigBlocks.blockCosmeticSolid, 6, 3);
            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e - 2, ConfigBlocks.blockCosmeticSolid, 6, 3);
            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d - 3, this.field_145849_e - 2, ConfigBlocks.blockCosmeticSolid, 6, 3);
            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e + 2, ConfigBlocks.blockCosmeticSolid, 6, 3);
            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e + 2, ConfigBlocks.blockCosmeticSolid, 6, 3);
            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d - 3, this.field_145849_e + 2, ConfigBlocks.blockCosmeticSolid, 6, 3);
            for (z = -1; z <= 1; ++z) {
                for (y = -1; y >= -3; --y) {
                    this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d + y, this.field_145849_e + z);
                }
            }
            for (z = -2; z <= 2; ++z) {
                for (y = 0; y >= -4; --y) {
                    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d + y, this.field_145849_e + z);
                }
            }
        }
        PocketPlaneData.destroyPortal(this.pocketID, this.which);
        this.field_145850_b.func_72908_a((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "thaumcraft:craftfail", 1.0f, 1.0f);
        this.portalOpen = false;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void makePortal(EntityPlayer player) {
        if (this.field_145850_b.field_72995_K) {
            return;
        }
        int portalNum = PocketPlaneData.firstAvailablePortal(this.pocketID);
        ItemWandCasting wand = (ItemWandCasting)player.func_70694_bm().func_77973_b();
        if (portalNum != 0 && wand.consumeAllVisCrafting(player.func_70694_bm(), player, new AspectList().add(Aspect.WATER, 100).add(Aspect.EARTH, 100).add(Aspect.ORDER, 100).add(Aspect.FIRE, 100).add(Aspect.AIR, 100).add(Aspect.ENTROPY, 100), false)) {
            boolean madePortal = false;
            if (this.checkPortalX()) {
                this.xAligned = true;
                int md = 0;
                for (int x = -2; x <= 2; ++x) {
                    for (int y = 0; y >= -4; --y) {
                        if (!(this.field_145850_b.func_147437_c(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e) || x == 0 && y == 0)) {
                            this.field_145850_b.func_147465_d(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e, ThaumicHorizons.blockGateway, md, 3);
                            ++md;
                            continue;
                        }
                        if (x == 0 && y == 0) continue;
                        this.field_145850_b.func_147465_d(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e, ThaumicHorizons.blockPortal, 0, 3);
                    }
                }
                madePortal = true;
            } else if (this.checkPortalZ()) {
                this.xAligned = false;
                int md = 0;
                for (int z = -2; z <= 2; ++z) {
                    for (int y = 0; y >= -4; --y) {
                        if (!(this.field_145850_b.func_147437_c(this.field_145851_c, this.field_145848_d + y, this.field_145849_e + z) || z == 0 && y == 0)) {
                            this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + y, this.field_145849_e + z, ThaumicHorizons.blockGateway, md, 3);
                            ++md;
                            continue;
                        }
                        if (z == 0 && y == 0) continue;
                        this.field_145850_b.func_147465_d(this.field_145851_c, this.field_145848_d + y, this.field_145849_e + z, ThaumicHorizons.blockPortal, 0, 3);
                    }
                }
                madePortal = true;
            }
            if (madePortal) {
                switch (portalNum) {
                    case 1: {
                        PocketPlaneData.planes.get((int)this.pocketID).portalA[0] = this.field_145851_c;
                        PocketPlaneData.planes.get((int)this.pocketID).portalA[1] = this.field_145848_d;
                        PocketPlaneData.planes.get((int)this.pocketID).portalA[2] = this.field_145849_e;
                        break;
                    }
                    case 2: {
                        PocketPlaneData.planes.get((int)this.pocketID).portalB[0] = this.field_145851_c;
                        PocketPlaneData.planes.get((int)this.pocketID).portalB[1] = this.field_145848_d;
                        PocketPlaneData.planes.get((int)this.pocketID).portalB[2] = this.field_145849_e;
                        break;
                    }
                    case 3: {
                        PocketPlaneData.planes.get((int)this.pocketID).portalC[0] = this.field_145851_c;
                        PocketPlaneData.planes.get((int)this.pocketID).portalC[1] = this.field_145848_d;
                        PocketPlaneData.planes.get((int)this.pocketID).portalC[2] = this.field_145849_e;
                        break;
                    }
                    case 4: {
                        PocketPlaneData.planes.get((int)this.pocketID).portalD[0] = this.field_145851_c;
                        PocketPlaneData.planes.get((int)this.pocketID).portalD[1] = this.field_145848_d;
                        PocketPlaneData.planes.get((int)this.pocketID).portalD[2] = this.field_145849_e;
                    }
                }
                PocketPlaneData.makePortal(this.pocketID, portalNum, this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.which = portalNum;
                this.portalOpen = true;
                wand.consumeAllVisCrafting(player.func_70694_bm(), player, new AspectList().add(Aspect.WATER, 100).add(Aspect.EARTH, 100).add(Aspect.ORDER, 100).add(Aspect.FIRE, 100).add(Aspect.AIR, 100).add(Aspect.ENTROPY, 100), true);
                this.field_145850_b.func_72908_a((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "thaumcraft:wand", 1.0f, 1.0f);
                this.func_70296_d();
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                return;
            }
        }
    }

    boolean checkPortalX() {
        for (int x = -1; x <= 1; ++x) {
            for (int y = -1; y >= -3; --y) {
                if (this.field_145850_b.func_147437_c(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e)) continue;
                return false;
            }
        }
        if (this.field_145850_b.func_147439_a(this.field_145851_c - 2, this.field_145848_d, this.field_145849_e) != Blocks.field_150368_y || this.field_145850_b.func_147439_a(this.field_145851_c + 2, this.field_145848_d, this.field_145849_e) != Blocks.field_150368_y || this.field_145850_b.func_147439_a(this.field_145851_c - 2, this.field_145848_d - 4, this.field_145849_e) != Blocks.field_150368_y || this.field_145850_b.func_147439_a(this.field_145851_c + 2, this.field_145848_d - 4, this.field_145849_e) != Blocks.field_150368_y) {
            return false;
        }
        return this.isArcStone(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) && this.isArcStone(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) && this.isArcStone(this.field_145851_c - 2, this.field_145848_d - 1, this.field_145849_e) && this.isArcStone(this.field_145851_c - 2, this.field_145848_d - 2, this.field_145849_e) && this.isArcStone(this.field_145851_c - 2, this.field_145848_d - 3, this.field_145849_e) && this.isArcStone(this.field_145851_c + 2, this.field_145848_d - 1, this.field_145849_e) && this.isArcStone(this.field_145851_c + 2, this.field_145848_d - 2, this.field_145849_e) && this.isArcStone(this.field_145851_c + 2, this.field_145848_d - 3, this.field_145849_e) && this.isArcStone(this.field_145851_c - 1, this.field_145848_d - 4, this.field_145849_e) && this.isArcStone(this.field_145851_c, this.field_145848_d - 4, this.field_145849_e) && this.isArcStone(this.field_145851_c + 1, this.field_145848_d - 4, this.field_145849_e);
    }

    boolean checkPortalZ() {
        for (int z = -1; z <= 1; ++z) {
            for (int y = -1; y >= -3; --y) {
                if (this.field_145850_b.func_147437_c(this.field_145851_c, this.field_145848_d + y, this.field_145849_e + z)) continue;
                return false;
            }
        }
        if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e - 2) != Blocks.field_150368_y || this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e + 2) != Blocks.field_150368_y || this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 4, this.field_145849_e - 2) != Blocks.field_150368_y || this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 4, this.field_145849_e + 2) != Blocks.field_150368_y) {
            return false;
        }
        return this.isArcStone(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) && this.isArcStone(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) && this.isArcStone(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e - 2) && this.isArcStone(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e - 2) && this.isArcStone(this.field_145851_c, this.field_145848_d - 3, this.field_145849_e - 2) && this.isArcStone(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e + 2) && this.isArcStone(this.field_145851_c, this.field_145848_d - 2, this.field_145849_e + 2) && this.isArcStone(this.field_145851_c, this.field_145848_d - 3, this.field_145849_e + 2) && this.isArcStone(this.field_145851_c, this.field_145848_d - 4, this.field_145849_e - 1) && this.isArcStone(this.field_145851_c, this.field_145848_d - 4, this.field_145849_e) && this.isArcStone(this.field_145851_c, this.field_145848_d - 4, this.field_145849_e + 1);
    }

    boolean isArcStone(int x, int y, int z) {
        return this.field_145850_b.func_147439_a(x, y, z) == ConfigBlocks.blockCosmeticSolid && this.field_145850_b.func_72805_g(x, y, z) == 6;
    }

    public void insertKeystone(int pocketNum) {
        this.hasKeystone = true;
        this.pocketID = pocketNum;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public int removeKeystone() {
        this.hasKeystone = false;
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        return this.pocketID;
    }
}

