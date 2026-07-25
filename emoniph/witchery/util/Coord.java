/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package com.emoniph.witchery.util;

import com.emoniph.witchery.common.INullSource;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.EntityPosition;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public final class Coord {
    public final int x;
    public final int y;
    public final int z;

    public Coord(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Coord(int x, int y, int z, ForgeDirection side) {
        this.x = x + side.offsetX;
        this.y = y + side.offsetY;
        this.z = z + side.offsetZ;
    }

    public Coord(TileEntity tileEntity) {
        this(tileEntity.field_145851_c, tileEntity.field_145848_d, tileEntity.field_145849_e);
    }

    public Coord(Entity entity) {
        this(MathHelper.func_76128_c((double)entity.field_70165_t), MathHelper.func_76128_c((double)entity.field_70163_u), MathHelper.func_76128_c((double)entity.field_70161_v));
    }

    public Coord(INullSource entity) {
        this(entity.getPosX(), entity.getPosY(), entity.getPosZ());
    }

    public Coord(MovingObjectPosition mop, EntityPosition alternativePos, boolean before) {
        if (mop != null) {
            switch (mop.field_72313_a) {
                case BLOCK: {
                    if (before) {
                        this.x = mop.field_72311_b + (mop.field_72310_e == 4 ? -1 : (mop.field_72310_e == 5 ? 1 : 0));
                        this.y = mop.field_72312_c + (mop.field_72310_e == 0 ? -1 : (mop.field_72310_e == 1 ? 1 : 0));
                        this.z = mop.field_72309_d + (mop.field_72310_e == 2 ? -1 : (mop.field_72310_e == 3 ? 1 : 0));
                        break;
                    }
                    this.x = mop.field_72311_b;
                    this.y = mop.field_72312_c;
                    this.z = mop.field_72309_d;
                    break;
                }
                case ENTITY: {
                    this.x = MathHelper.func_76128_c((double)mop.field_72308_g.field_70165_t);
                    this.y = MathHelper.func_76128_c((double)mop.field_72308_g.field_70163_u);
                    this.z = MathHelper.func_76128_c((double)mop.field_72308_g.field_70161_v);
                    break;
                }
                default: {
                    if (alternativePos != null) {
                        this.x = MathHelper.func_76128_c((double)alternativePos.x);
                        this.y = MathHelper.func_76128_c((double)alternativePos.y);
                        this.z = MathHelper.func_76128_c((double)alternativePos.z);
                        break;
                    }
                    this.x = 0;
                    this.y = 0;
                    this.z = 0;
                    break;
                }
            }
        } else if (alternativePos != null) {
            this.x = MathHelper.func_76128_c((double)alternativePos.x);
            this.y = MathHelper.func_76128_c((double)alternativePos.y);
            this.z = MathHelper.func_76128_c((double)alternativePos.z);
        } else {
            this.x = 0;
            this.y = 0;
            this.z = 0;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Coord other = (Coord)obj;
        return this.x == other.x && this.y == other.y && this.z == other.z;
    }

    public boolean isAtPosition(TileEntity tileEntity) {
        return tileEntity != null && this.x == tileEntity.field_145851_c && this.y == tileEntity.field_145848_d && this.z == tileEntity.field_145849_e;
    }

    public Coord north() {
        return this.north(1);
    }

    public Coord north(int n) {
        return new Coord(this.x, this.y, this.z - n);
    }

    public Coord south() {
        return this.south(1);
    }

    public Coord south(int n) {
        return new Coord(this.x, this.y, this.z + n);
    }

    public Coord east() {
        return this.east(1);
    }

    public Coord east(int n) {
        return new Coord(this.x + n, this.y, this.z);
    }

    public Coord west() {
        return this.west(1);
    }

    public Coord west(int n) {
        return new Coord(this.x - n, this.y, this.z);
    }

    public Coord northEast() {
        return new Coord(this.x + 1, this.y, this.z - 1);
    }

    public Coord northWest() {
        return new Coord(this.x - 1, this.y, this.z - 1);
    }

    public Coord southEast() {
        return new Coord(this.x + 1, this.y, this.z + 1);
    }

    public Coord southWest() {
        return new Coord(this.x - 1, this.y, this.z + 1);
    }

    public Block getBlock(World world) {
        return this.getBlock(world, 0, 0, 0);
    }

    public Block getBlock(World world, int offsetX, int offsetY, int offsetZ) {
        return world.func_147439_a(this.x + offsetX, this.y + offsetY, this.z + offsetZ);
    }

    public TileEntity getBlockTileEntity(World world) {
        return this.getBlockTileEntity(world, 0, 0, 0);
    }

    public TileEntity getBlockTileEntity(World world, int offsetX, int offsetY, int offsetZ) {
        return world.func_147438_o(this.x + offsetX, this.y + offsetY, this.z + offsetZ);
    }

    public <T> T getTileEntity(IBlockAccess world, Class<T> clazz) {
        return BlockUtil.getTileEntity(world, this.x, this.y, this.z, clazz);
    }

    public int getBlockMetadata(World world) {
        return this.getBlockMetadata(world, 0, 0, 0);
    }

    public int getBlockMetadata(World world, int offsetX, int offsetY, int offsetZ) {
        return world.func_72805_g(this.x + offsetX, this.y + offsetY, this.z + offsetZ);
    }

    public void setNBT(NBTTagCompound nbtTag, String key) {
        nbtTag.func_74768_a(key + "X", this.x);
        nbtTag.func_74768_a(key + "Y", this.y);
        nbtTag.func_74768_a(key + "Z", this.z);
    }

    public static double distance(Coord first, Coord second) {
        double dX = first.x - second.x;
        double dY = first.y - second.y;
        double dZ = first.z - second.z;
        return Math.sqrt(dX * dX + dY * dY + dZ * dZ);
    }

    public static double distance(double firstX, double firstY, double firstZ, double secondX, double secondY, double secondZ) {
        double dX = firstX - secondX;
        double dY = firstY - secondY;
        double dZ = firstZ - secondZ;
        return Math.sqrt(dX * dX + dY * dY + dZ * dZ);
    }

    public static double distanceSq(double firstX, double firstY, double firstZ, double secondX, double secondY, double secondZ) {
        double dX = firstX - secondX;
        double dY = firstY - secondY;
        double dZ = firstZ - secondZ;
        return dX * dX + dY * dY + dZ * dZ;
    }

    public double distanceTo(Coord other) {
        double dX = other.x - this.x;
        double dY = other.y - this.y;
        double dZ = other.z - this.z;
        return Math.sqrt(dX * dX + dY * dY + dZ * dZ);
    }

    public double distanceSqTo(Coord other) {
        double dX = other.x - this.x;
        double dY = other.y - this.y;
        double dZ = other.z - this.z;
        return dX * dX + dY * dY + dZ * dZ;
    }

    public double distanceSqTo(int x, int y, int z) {
        double dX = x - this.x;
        double dY = y - this.y;
        double dZ = z - this.z;
        return dX * dX + dY * dY + dZ * dZ;
    }

    public static Coord createFrom(NBTTagCompound nbtTag, String key) {
        if (nbtTag.func_74764_b(key + "X") && nbtTag.func_74764_b(key + "Y") && nbtTag.func_74764_b(key + "Z")) {
            return new Coord(nbtTag.func_74762_e(key + "X"), nbtTag.func_74762_e(key + "Y"), nbtTag.func_74762_e(key + "Z"));
        }
        return null;
    }

    public boolean isWestOf(Coord coord) {
        return this.x < coord.x;
    }

    public boolean isNorthOf(Coord coord) {
        return this.z < coord.z;
    }

    public boolean setBlock(World world, Block block) {
        return world.func_147449_b(this.x, this.y, this.z, block);
    }

    public boolean setBlock(World world, Block block, int metadata, int flags) {
        return world.func_147465_d(this.x, this.y, this.z, block, metadata, flags);
    }

    public void setAir(World world) {
        world.func_147468_f(this.x, this.y, this.z);
    }

    public void markBlockForUpdate(World world) {
        world.func_147471_g(this.x, this.y, this.z);
    }

    public int getHeading(Coord destination) {
        double dX = this.x - destination.x;
        double dZ = this.z - destination.z;
        double yaw = Math.atan2(dZ, dX);
        double PI8 = 0.39269908169872414;
        double PI2 = 1.5707963267948966;
        if (yaw > -0.39269908169872414 && yaw <= 0.39269908169872414) {
            return 6;
        }
        if (yaw > 0.39269908169872414 && yaw <= 1.1780972450961724) {
            return 7;
        }
        if (yaw > 1.1780972450961724 && yaw <= 1.9634954084936207) {
            return 0;
        }
        if (yaw > 1.9634954084936207 && yaw <= 2.748893571891069) {
            return 1;
        }
        if (yaw > 2.748893571891069 || yaw <= -2.748893571891069) {
            return 2;
        }
        if (yaw > -2.748893571891069 && yaw <= -1.9634954084936207) {
            return 3;
        }
        if (yaw > -1.9634954084936207 && yaw <= -1.1780972450961724) {
            return 4;
        }
        return 5;
    }

    public String toString() {
        return String.format("%d, %d, %d", this.x, this.y, this.z);
    }

    public NBTTagCompound toTagNBT() {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.func_74768_a("posX", this.x);
        nbt.func_74768_a("posY", this.y);
        nbt.func_74768_a("posZ", this.z);
        return nbt;
    }

    public static Coord fromTagNBT(NBTTagCompound nbt) {
        if (nbt.func_74764_b("posX") && nbt.func_74764_b("posY") && nbt.func_74764_b("posZ")) {
            return new Coord(nbt.func_74762_e("posX"), nbt.func_74762_e("posY"), nbt.func_74762_e("posZ"));
        }
        return null;
    }

    public boolean isMatch(int x, int y, int z) {
        return this.x == x && this.y == y && this.z == z;
    }
}

