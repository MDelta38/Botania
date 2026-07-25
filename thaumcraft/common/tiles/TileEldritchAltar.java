/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.common.entities.monster.EntityCultist;
import thaumcraft.common.entities.monster.EntityCultistCleric;
import thaumcraft.common.entities.monster.EntityCultistKnight;
import thaumcraft.common.entities.monster.EntityEldritchGuardian;
import thaumcraft.common.lib.world.dim.MazeHandler;
import thaumcraft.common.lib.world.dim.MazeThread;

public class TileEldritchAltar
extends TileThaumcraft {
    private boolean spawner = false;
    private boolean open = false;
    private boolean spawnedClerics = false;
    private byte spawnType = 0;
    private byte eyes = 0;
    private int counter = 0;

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.setEyes(nbttagcompound.func_74771_c("eyes"));
        this.setOpen(nbttagcompound.func_74767_n("open"));
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74774_a("eyes", this.getEyes());
        nbttagcompound.func_74757_a("open", this.isOpen());
    }

    @Override
    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.spawnedClerics = nbttagcompound.func_74767_n("spawnedClerics");
        this.spawner = nbttagcompound.func_74767_n("spawner");
        this.spawnType = nbttagcompound.func_74771_c("spawntype");
    }

    @Override
    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        nbttagcompound.func_74757_a("spawnedClerics", this.spawnedClerics);
        nbttagcompound.func_74757_a("spawner", this.spawner);
        nbttagcompound.func_74774_a("spawntype", this.spawnType);
    }

    public double func_145833_n() {
        return 9216.0;
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1));
    }

    public boolean isSpawner() {
        return this.spawner;
    }

    public void setSpawner(boolean spawner) {
        this.spawner = spawner;
    }

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        if (!this.field_145850_b.field_72995_K && this.isSpawner() && this.counter++ >= 80 && this.counter % 40 == 0) {
            switch (this.spawnType) {
                case 0: {
                    if (!this.spawnedClerics) {
                        this.spawnClerics();
                        break;
                    }
                    this.spawnGuards();
                    break;
                }
                case 1: {
                    this.spawnGuardian();
                }
            }
        }
    }

    private void spawnGuards() {
        List ents = this.field_145850_b.func_72872_a(EntityCultistCleric.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)).func_72314_b(24.0, 16.0, 24.0));
        if (ents.size() < 1) {
            this.setSpawner(false);
            return;
        }
        ents = this.field_145850_b.func_72872_a(EntityCultist.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)).func_72314_b(24.0, 16.0, 24.0));
        if (ents.size() < 8) {
            int k1;
            int j1;
            EntityCultistKnight eg = new EntityCultistKnight(this.field_145850_b);
            int i1 = this.field_145851_c + MathHelper.func_76136_a((Random)this.field_145850_b.field_73012_v, (int)4, (int)10) * MathHelper.func_76136_a((Random)this.field_145850_b.field_73012_v, (int)-1, (int)1);
            if (World.func_147466_a((IBlockAccess)this.field_145850_b, (int)i1, (int)((j1 = this.field_145848_d + MathHelper.func_76136_a((Random)this.field_145850_b.field_73012_v, (int)0, (int)3) * MathHelper.func_76136_a((Random)this.field_145850_b.field_73012_v, (int)-1, (int)1)) - 1), (int)(k1 = this.field_145849_e + MathHelper.func_76136_a((Random)this.field_145850_b.field_73012_v, (int)4, (int)10) * MathHelper.func_76136_a((Random)this.field_145850_b.field_73012_v, (int)-1, (int)1)))) {
                eg.func_70107_b(i1, j1, k1);
                if (this.field_145850_b.func_72855_b(eg.field_70121_D) && this.field_145850_b.func_72945_a((Entity)eg, eg.field_70121_D).isEmpty() && !this.field_145850_b.func_72953_d(eg.field_70121_D)) {
                    eg.func_110161_a(null);
                    eg.func_70656_aK();
                    eg.func_110171_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, 16);
                    this.field_145850_b.func_72838_d((Entity)eg);
                }
            }
        }
    }

    private void spawnGuardian() {
        int k1;
        int j1;
        EntityEldritchGuardian eg = new EntityEldritchGuardian(this.field_145850_b);
        int i1 = this.field_145851_c + MathHelper.func_76136_a((Random)this.field_145850_b.field_73012_v, (int)4, (int)10) * MathHelper.func_76136_a((Random)this.field_145850_b.field_73012_v, (int)-1, (int)1);
        if (World.func_147466_a((IBlockAccess)this.field_145850_b, (int)i1, (int)((j1 = this.field_145848_d + MathHelper.func_76136_a((Random)this.field_145850_b.field_73012_v, (int)0, (int)3) * MathHelper.func_76136_a((Random)this.field_145850_b.field_73012_v, (int)-1, (int)1)) - 1), (int)(k1 = this.field_145849_e + MathHelper.func_76136_a((Random)this.field_145850_b.field_73012_v, (int)4, (int)10) * MathHelper.func_76136_a((Random)this.field_145850_b.field_73012_v, (int)-1, (int)1)))) {
            eg.func_70107_b(i1, j1, k1);
            if (eg.func_70601_bi()) {
                eg.func_110161_a(null);
                eg.func_70656_aK();
                eg.func_110171_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, 16);
                this.field_145850_b.func_72838_d((Entity)eg);
            }
        }
    }

    private void spawnClerics() {
        int success = 0;
        for (int a = 0; a < 4; ++a) {
            int xx = 0;
            int zz = 0;
            switch (a) {
                case 0: {
                    xx = -2;
                    zz = -2;
                    break;
                }
                case 1: {
                    xx = -2;
                    zz = 2;
                    break;
                }
                case 2: {
                    xx = 2;
                    zz = -2;
                    break;
                }
                case 3: {
                    xx = 2;
                    zz = 2;
                }
            }
            EntityCultistCleric cleric = new EntityCultistCleric(this.field_145850_b);
            if (!World.func_147466_a((IBlockAccess)this.field_145850_b, (int)(this.field_145851_c + xx), (int)(this.field_145848_d - 1), (int)(this.field_145849_e + zz))) continue;
            cleric.func_70107_b((double)this.field_145851_c + 0.5 + (double)xx, this.field_145848_d, (double)this.field_145849_e + 0.5 + (double)zz);
            if (!this.field_145850_b.func_72855_b(cleric.field_70121_D) || !this.field_145850_b.func_72945_a((Entity)cleric, cleric.field_70121_D).isEmpty() || this.field_145850_b.func_72953_d(cleric.field_70121_D)) continue;
            cleric.func_110171_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, 8);
            cleric.func_110161_a(null);
            cleric.func_70656_aK();
            if (!this.field_145850_b.func_72838_d((Entity)cleric)) continue;
            ++success;
            cleric.setIsRitualist(true);
        }
        if (success > 2) {
            this.spawnedClerics = true;
            this.func_70296_d();
        }
    }

    public byte getSpawnType() {
        return this.spawnType;
    }

    public void setSpawnType(byte spawnType) {
        this.spawnType = spawnType;
    }

    public byte getEyes() {
        return this.eyes;
    }

    public void setEyes(byte eyes) {
        this.eyes = eyes;
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean checkForMaze() {
        int h;
        int w = 15 + this.field_145850_b.field_73012_v.nextInt(8) * 2;
        if (!MazeHandler.mazesInRange(this.field_145851_c >> 4, this.field_145849_e >> 4, w, h = 15 + this.field_145850_b.field_73012_v.nextInt(8) * 2)) {
            Thread t = new Thread(new MazeThread(this.field_145851_c >> 4, this.field_145849_e >> 4, w, h, this.field_145850_b.field_73012_v.nextLong()));
            t.start();
            return false;
        }
        return true;
    }
}

