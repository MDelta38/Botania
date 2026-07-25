/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.codechicken.lib.raytracer.RayTracer;
import thaumcraft.common.tiles.TileTube;

public class TileTubeValve
extends TileTube {
    public boolean allowFlow = true;
    boolean wasPoweredLastTick = false;
    public float rotation = 0.0f;

    @Override
    public void func_145845_h() {
        if (!this.field_145850_b.field_72995_K && this.count % 5 == 0) {
            boolean gettingPower = this.gettingPower();
            if (this.wasPoweredLastTick && !gettingPower && !this.allowFlow) {
                this.allowFlow = true;
                this.field_145850_b.func_72908_a((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "thaumcraft:squeek", 0.7f, 0.9f + this.field_145850_b.field_73012_v.nextFloat() * 0.2f);
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.func_70296_d();
            }
            if (!this.wasPoweredLastTick && gettingPower && this.allowFlow) {
                this.allowFlow = false;
                this.field_145850_b.func_72908_a((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "thaumcraft:squeek", 0.7f, 0.9f + this.field_145850_b.field_73012_v.nextFloat() * 0.2f);
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                this.func_70296_d();
            }
            this.wasPoweredLastTick = gettingPower;
        }
        if (this.field_145850_b.field_72995_K) {
            if (!this.allowFlow && this.rotation < 360.0f) {
                this.rotation += 20.0f;
            } else if (this.allowFlow && this.rotation > 0.0f) {
                this.rotation -= 20.0f;
            }
        }
        super.func_145845_h();
    }

    @Override
    public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
        MovingObjectPosition hit = RayTracer.retraceBlock(world, player, x, y, z);
        if (hit == null) {
            return 0;
        }
        if (hit.subHit >= 0 && hit.subHit < 6) {
            player.field_70170_p.func_72980_b((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:tool", 0.5f, 0.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
            player.func_71038_i();
            this.func_70296_d();
            world.func_147471_g(x, y, z);
            this.openSides[hit.subHit] = !this.openSides[hit.subHit];
            ForgeDirection dir = ForgeDirection.getOrientation((int)hit.subHit);
            TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ);
            if (tile != null && tile instanceof TileTube) {
                ((TileTube)tile).openSides[dir.getOpposite().ordinal()] = this.openSides[hit.subHit];
                world.func_147471_g(this.field_145851_c + dir.offsetX, this.field_145848_d + dir.offsetY, this.field_145849_e + dir.offsetZ);
                tile.func_70296_d();
            }
        }
        if (hit.subHit == 6) {
            player.field_70170_p.func_72980_b((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:tool", 0.5f, 0.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
            player.func_71038_i();
            int a = this.facing.ordinal();
            this.func_70296_d();
            while (++a < 20) {
                if (this.canConnectSide(ForgeDirection.getOrientation((int)(a % 6)).ordinal())) continue;
                this.facing = ForgeDirection.getOrientation((int)(a %= 6));
                world.func_147471_g(x, y, z);
                break;
            }
        }
        return 0;
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.allowFlow = nbttagcompound.func_74767_n("flow");
        this.wasPoweredLastTick = nbttagcompound.func_74767_n("hadpower");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.func_74757_a("flow", this.allowFlow);
        nbttagcompound.func_74757_a("hadpower", this.wasPoweredLastTick);
    }

    @Override
    public boolean isConnectable(ForgeDirection face) {
        return face != this.facing && super.isConnectable(face);
    }

    @Override
    public void setSuction(Aspect aspect, int amount) {
        if (this.allowFlow) {
            super.setSuction(aspect, amount);
        }
    }

    public boolean gettingPower() {
        return this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }
}

