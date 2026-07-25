/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.monster.EntityEldritchCrab;

public class TileEldritchCrabSpawner
extends TileThaumcraft {
    public int count = 150;
    public int ticks = 0;
    int venting = 0;
    byte facing = 0;

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        super.func_145845_h();
        if (this.ticks == 0) {
            this.ticks = this.field_145850_b.field_73012_v.nextInt(500);
        }
        ++this.ticks;
        if (!this.field_145850_b.field_72995_K) {
            --this.count;
            if (this.count < 0) {
                this.count = 50 + this.field_145850_b.field_73012_v.nextInt(50);
            } else {
                if (this.count == 15 && this.isActivated() && !this.maxEntitiesReached()) {
                    this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.func_145838_q(), 1, 0);
                    this.field_145850_b.func_72908_a((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "random.fizz", 0.5f, 1.0f);
                }
                if (this.count <= 0 && this.isActivated() && !this.maxEntitiesReached()) {
                    this.count = 150 + this.field_145850_b.field_73012_v.nextInt(100);
                    this.spawnCrab();
                    this.field_145850_b.func_72908_a((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "thaumcraft:gore", 0.5f, 1.0f);
                }
            }
        } else if (this.venting > 0) {
            --this.venting;
            for (int a = 0; a < 3; ++a) {
                this.drawVent();
            }
        } else if (this.field_145850_b.field_73012_v.nextInt(20) == 0) {
            this.drawVent();
        }
    }

    void drawVent() {
        ForgeDirection dir = ForgeDirection.getOrientation((int)this.facing);
        float fx = 0.15f - this.field_145850_b.field_73012_v.nextFloat() * 0.3f;
        float fz = 0.15f - this.field_145850_b.field_73012_v.nextFloat() * 0.3f;
        float fy = 0.15f - this.field_145850_b.field_73012_v.nextFloat() * 0.3f;
        float fx2 = 0.1f - this.field_145850_b.field_73012_v.nextFloat() * 0.2f;
        float fz2 = 0.1f - this.field_145850_b.field_73012_v.nextFloat() * 0.2f;
        float fy2 = 0.1f - this.field_145850_b.field_73012_v.nextFloat() * 0.2f;
        Thaumcraft.proxy.drawVentParticles(this.field_145850_b, (float)this.field_145851_c + 0.5f + fx + (float)dir.offsetX / 2.1f, (float)this.field_145848_d + 0.5f + fy + (float)dir.offsetY / 2.1f, (float)this.field_145849_e + 0.5f + fz + (float)dir.offsetZ / 2.1f, (float)dir.offsetX / 3.0f + fx2, (float)dir.offsetY / 3.0f + fy2, (float)dir.offsetZ / 3.0f + fz2, 0x9988AA, 2.0f);
    }

    public boolean func_145842_c(int i, int j) {
        if (i == 1) {
            this.venting = 20;
            return true;
        }
        return super.func_145842_c(i, j);
    }

    private boolean maxEntitiesReached() {
        List ents = this.field_145850_b.func_72872_a(EntityEldritchCrab.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)((double)this.field_145851_c + 1.0), (double)((double)this.field_145848_d + 1.0), (double)((double)this.field_145849_e + 1.0)).func_72314_b(32.0, 32.0, 32.0));
        return ents.size() > 5;
    }

    public boolean isActivated() {
        return this.field_145850_b.func_72977_a((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, 16.0) != null;
    }

    private void spawnCrab() {
        ForgeDirection dir = ForgeDirection.getOrientation((int)this.facing);
        EntityEldritchCrab crab = new EntityEldritchCrab(this.field_145850_b);
        double x = this.field_145851_c + dir.offsetX;
        double y = this.field_145848_d + dir.offsetY;
        double z = this.field_145849_e + dir.offsetZ;
        crab.func_70012_b(x + 0.5, y + 0.5, z + 0.5, 0.0f, 0.0f);
        crab.func_110161_a(null);
        crab.setHelm(false);
        crab.field_70159_w = (float)dir.offsetX * 0.2f;
        crab.field_70181_x = (float)dir.offsetY * 0.2f;
        crab.field_70179_y = (float)dir.offsetZ * 0.2f;
        this.field_145850_b.func_72838_d((Entity)crab);
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)(this.field_145851_c - 1), (double)(this.field_145848_d - 1), (double)(this.field_145849_e - 1), (double)(this.field_145851_c + 2), (double)(this.field_145848_d + 2), (double)(this.field_145849_e + 2));
    }

    public byte getFacing() {
        return this.facing;
    }

    public void setFacing(byte face) {
        this.facing = face;
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        this.func_70296_d();
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.facing = nbttagcompound.func_74771_c("facing");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74774_a("facing", this.facing);
    }
}

