/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 */
package witchinggadgets.common.blocks.tiles;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import witchinggadgets.common.blocks.tiles.TileEntityWGBase;

public class TileEntityWallMirror
extends TileEntityWGBase {
    public int activationAnimation = 0;
    public int animation = 0;
    public boolean isActive = false;
    public boolean temp_isActivating = false;
    public boolean temp_isDeActivating = false;
    public int facing = 0;
    public boolean isDummy = false;

    public void func_145845_h() {
        super.func_145845_h();
        if (this.isDummy) {
            return;
        }
        if (this.temp_isActivating) {
            if (this.activationAnimation < 31) {
                ++this.activationAnimation;
            } else {
                this.isActive = true;
                this.temp_isActivating = false;
            }
            this.animation = 0;
        }
        if (this.temp_isDeActivating) {
            if (this.activationAnimation > 0) {
                if (this.activationAnimation < 17) {
                    --this.activationAnimation;
                }
                --this.activationAnimation;
            } else {
                this.isActive = false;
                this.temp_isDeActivating = false;
            }
            this.animation = 0;
        }
    }

    public void toggleState() {
        if (this.isActive && !this.temp_isDeActivating) {
            this.temp_isDeActivating = true;
        } else if (!this.isActive && !this.temp_isActivating) {
            this.temp_isActivating = true;
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound tags) {
        this.isActive = tags.func_74767_n("isActive");
        this.isDummy = tags.func_74767_n("isDummy");
        this.facing = tags.func_74762_e("facing");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound tags) {
        tags.func_74757_a("isActive", this.isActive);
        tags.func_74757_a("isDummy", this.isDummy);
        tags.func_74768_a("facing", this.facing);
    }

    public List<EntityPlayer> getMirroredPlayers() {
        double minZ;
        double maxX;
        double minX;
        double d = this.facing == 2 || this.facing == 3 ? -2.0 : (minX = this.facing == 4 ? -8.0 : -0.5);
        double d2 = this.facing == 2 || this.facing == 3 ? 2.0 : (maxX = this.facing == 4 ? 0.5 : 8.0);
        double d3 = this.facing == 4 || this.facing == 5 ? -2.0 : (minZ = this.facing == 2 ? -8.0 : -0.5);
        double maxZ = this.facing == 4 || this.facing == 5 ? 2.0 : (this.facing == 2 ? 0.5 : 8.0);
        AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)minX, (double)-1.0, (double)minZ, (double)maxX, (double)2.0, (double)maxZ);
        aabb = aabb.func_72325_c((double)this.field_145851_c + 0.5, (double)(this.field_145848_d + 1), (double)this.field_145849_e + 0.5);
        return this.field_145850_b.func_72872_a(EntityPlayer.class, aabb);
    }
}

