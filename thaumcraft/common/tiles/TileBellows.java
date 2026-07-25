/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityFurnace
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.TileThaumcraft;

public class TileBellows
extends TileThaumcraft {
    public float inflation = 1.0f;
    boolean direction = false;
    boolean firstrun = true;
    public byte orientation = 0;
    public boolean onVanillaFurnace = false;
    public int delay = 0;

    public void func_145845_h() {
        if (this.field_145850_b.field_72995_K) {
            if (!this.gettingPower()) {
                if (this.firstrun) {
                    this.inflation = 0.35f + this.field_145850_b.field_73012_v.nextFloat() * 0.55f;
                }
                this.firstrun = false;
                if (this.inflation > 0.35f && !this.direction) {
                    this.inflation -= 0.075f;
                }
                if (this.inflation <= 0.35f && !this.direction) {
                    this.direction = true;
                }
                if (this.inflation < 1.0f && this.direction) {
                    this.inflation += 0.025f;
                }
                if (this.inflation >= 1.0f && this.direction) {
                    this.direction = false;
                    this.field_145850_b.func_72980_b((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "mob.ghast.fireball", 0.01f, 0.5f + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.2f, false);
                }
            }
        } else if (this.onVanillaFurnace && !this.gettingPower()) {
            ++this.delay;
            if (this.delay >= 2) {
                this.delay = 0;
                ForgeDirection dir = ForgeDirection.getOrientation((int)this.orientation);
                TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c + dir.offsetX, this.field_145848_d, this.field_145849_e + dir.offsetZ);
                if (tile != null && tile instanceof TileEntityFurnace) {
                    TileEntityFurnace tf = (TileEntityFurnace)tile;
                    if (tf.field_145961_j > 0 && tf.field_145961_j < 199) {
                        ++tf.field_145961_j;
                    }
                }
            }
        }
    }

    public boolean gettingPower() {
        return this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public static int getBellows(World world, int x, int y, int z, ForgeDirection[] directions) {
        int bellows = 0;
        for (ForgeDirection dir : directions) {
            int xx = x + dir.offsetX;
            int yy = y + dir.offsetY;
            int zz = z + dir.offsetZ;
            TileEntity tile = world.func_147438_o(xx, yy, zz);
            if (tile == null || !(tile instanceof TileBellows) || ((TileBellows)tile).orientation != dir.getOpposite().ordinal() || world.func_72864_z(xx, yy, zz)) continue;
            ++bellows;
        }
        return bellows;
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.orientation = nbttagcompound.func_74771_c("orientation");
        this.onVanillaFurnace = nbttagcompound.func_74767_n("onVanillaFurnace");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74774_a("orientation", this.orientation);
        nbttagcompound.func_74757_a("onVanillaFurnace", this.onVanillaFurnace);
    }
}

