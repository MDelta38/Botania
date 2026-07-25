/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package com.kentington.thaumichorizons.common.tiles;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileVat;
import net.minecraft.entity.player.EntityPlayer;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;

public class TileVatSlave
extends TileThaumcraft
implements IAspectContainer {
    boolean bossFound;
    int bossX;
    int bossY;
    int bossZ;
    int renderMe;

    public boolean activate(EntityPlayer player) {
        TileVat boss = this.getBoss(-1);
        if (boss != null) {
            if (this.field_145847_g != 0) {
                return boss.activate(player, false);
            }
            return boss.activate(player, true);
        }
        return false;
    }

    public TileVat getBoss(int mdOverride) {
        if (!this.bossFound) {
            int md = mdOverride;
            if (md == -1) {
                md = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
            if (md == 0) {
                this.bossX = this.field_145851_c;
                this.bossZ = this.field_145849_e;
                if (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) instanceof TileVat) {
                    this.bossY = this.field_145848_d + 1;
                    this.bossFound = true;
                } else if (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 2, this.field_145849_e) instanceof TileVat) {
                    this.bossY = this.field_145848_d + 2;
                    this.bossFound = true;
                }
            } else if (md == 10) {
                for (int x = -1; x < 2; ++x) {
                    for (int z = -1; z < 2; ++z) {
                        if (this.field_145850_b.func_147439_a(this.field_145851_c + x, this.field_145848_d, this.field_145849_e + z) != ThaumicHorizons.blockVatInterior || this.field_145850_b.func_72805_g(this.field_145851_c + x, this.field_145848_d, this.field_145849_e + z) != 0 || !(this.field_145850_b.func_147438_o(this.field_145851_c + x, this.field_145848_d, this.field_145849_e + z) instanceof TileVatSlave)) continue;
                        TileVat boss = ((TileVatSlave)this.field_145850_b.func_147438_o(this.field_145851_c + x, this.field_145848_d, this.field_145849_e + z)).getBoss(-1);
                        if (boss != null) {
                            this.bossX = boss.field_145851_c;
                            this.bossY = boss.field_145848_d;
                            this.bossZ = boss.field_145849_e;
                            this.bossFound = true;
                        }
                        return boss;
                    }
                }
            } else if (md == 4) {
                if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) == ThaumicHorizons.blockVat && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) instanceof TileVatSlave) {
                    TileVat boss = ((TileVatSlave)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e)).getBoss(-1);
                    if (boss != null) {
                        this.bossX = boss.field_145851_c;
                        this.bossY = boss.field_145848_d;
                        this.bossZ = boss.field_145849_e;
                        this.bossFound = true;
                    }
                    return boss;
                }
            } else if (md == 5) {
                if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) == ThaumicHorizons.blockVat && this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) == 10 && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) instanceof TileVatSlave) {
                    TileVat boss = ((TileVatSlave)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e)).getBoss(-1);
                    if (boss != null) {
                        this.bossX = boss.field_145851_c;
                        this.bossY = boss.field_145848_d;
                        this.bossZ = boss.field_145849_e;
                        this.bossFound = true;
                    }
                    return boss;
                }
                if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == ThaumicHorizons.blockVat && this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == 10 && this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) instanceof TileVatSlave) {
                    TileVat boss = ((TileVatSlave)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e)).getBoss(-1);
                    if (boss != null) {
                        this.bossX = boss.field_145851_c;
                        this.bossY = boss.field_145848_d;
                        this.bossZ = boss.field_145849_e;
                        this.bossFound = true;
                    }
                    return boss;
                }
            } else if (md == 6) {
                this.bossX = this.field_145851_c;
                this.bossZ = this.field_145849_e;
                if (this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 3, this.field_145849_e) instanceof TileVat) {
                    this.bossY = this.field_145848_d + 3;
                    this.bossFound = true;
                }
            }
        }
        if (this.field_145850_b.func_147438_o(this.bossX, this.bossY, this.bossZ) instanceof TileVat) {
            return (TileVat)this.field_145850_b.func_147438_o(this.bossX, this.bossY, this.bossZ);
        }
        return null;
    }

    public void killMyBoss(int mdOverride) {
        TileVat boss = this.getBoss(mdOverride);
        if (boss != null) {
            boss.killMe();
        }
    }

    @Override
    public AspectList getAspects() {
        TileVat boss = this.getBoss(-1);
        if (boss != null) {
            return boss.getAspects();
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

