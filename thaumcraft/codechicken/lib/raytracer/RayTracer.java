/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package thaumcraft.codechicken.lib.raytracer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thaumcraft.codechicken.lib.math.MathHelper;
import thaumcraft.codechicken.lib.raytracer.ExtendedMOP;
import thaumcraft.codechicken.lib.raytracer.IndexedCuboid6;
import thaumcraft.codechicken.lib.vec.BlockCoord;
import thaumcraft.codechicken.lib.vec.Cuboid6;
import thaumcraft.codechicken.lib.vec.Vector3;

public class RayTracer {
    private Vector3 vec = new Vector3();
    private Vector3 vec2 = new Vector3();
    private Vector3 s_vec = new Vector3();
    private double s_dist;
    private int s_side;
    private IndexedCuboid6 c_cuboid;
    private static ThreadLocal<RayTracer> t_inst = new ThreadLocal();

    public static RayTracer instance() {
        RayTracer inst = t_inst.get();
        if (inst == null) {
            inst = new RayTracer();
            t_inst.set(inst);
        }
        return inst;
    }

    private void traceSide(int side, Vector3 start, Vector3 end, Cuboid6 cuboid) {
        this.vec.set(start);
        Vector3 hit = null;
        switch (side) {
            case 0: {
                hit = this.vec.XZintercept(end, cuboid.min.y);
                break;
            }
            case 1: {
                hit = this.vec.XZintercept(end, cuboid.max.y);
                break;
            }
            case 2: {
                hit = this.vec.XYintercept(end, cuboid.min.z);
                break;
            }
            case 3: {
                hit = this.vec.XYintercept(end, cuboid.max.z);
                break;
            }
            case 4: {
                hit = this.vec.YZintercept(end, cuboid.min.x);
                break;
            }
            case 5: {
                hit = this.vec.YZintercept(end, cuboid.max.x);
            }
        }
        if (hit == null) {
            return;
        }
        switch (side) {
            case 0: 
            case 1: {
                if (MathHelper.between(cuboid.min.x, hit.x, cuboid.max.x) && MathHelper.between(cuboid.min.z, hit.z, cuboid.max.z)) break;
                return;
            }
            case 2: 
            case 3: {
                if (MathHelper.between(cuboid.min.x, hit.x, cuboid.max.x) && MathHelper.between(cuboid.min.y, hit.y, cuboid.max.y)) break;
                return;
            }
            case 4: 
            case 5: {
                if (MathHelper.between(cuboid.min.y, hit.y, cuboid.max.y) && MathHelper.between(cuboid.min.z, hit.z, cuboid.max.z)) break;
                return;
            }
        }
        double dist = this.vec2.set(hit).subtract(start).magSquared();
        if (dist < this.s_dist) {
            this.s_side = side;
            this.s_dist = dist;
            this.s_vec.set(this.vec);
        }
    }

    public MovingObjectPosition rayTraceCuboid(Vector3 start, Vector3 end, Cuboid6 cuboid) {
        this.s_dist = Double.MAX_VALUE;
        this.s_side = -1;
        for (int i = 0; i < 6; ++i) {
            this.traceSide(i, start, end, cuboid);
        }
        if (this.s_side < 0) {
            return null;
        }
        MovingObjectPosition mop = new MovingObjectPosition(0, 0, 0, this.s_side, this.s_vec.toVec3D());
        mop.field_72313_a = null;
        return mop;
    }

    public MovingObjectPosition rayTraceCuboid(Vector3 start, Vector3 end, Cuboid6 cuboid, BlockCoord pos) {
        MovingObjectPosition mop = this.rayTraceCuboid(start, end, cuboid);
        if (mop != null) {
            mop.field_72313_a = MovingObjectPosition.MovingObjectType.BLOCK;
            mop.field_72311_b = pos.x;
            mop.field_72312_c = pos.y;
            mop.field_72309_d = pos.z;
        }
        return mop;
    }

    public MovingObjectPosition rayTraceCuboid(Vector3 start, Vector3 end, Cuboid6 cuboid, Entity e) {
        MovingObjectPosition mop = this.rayTraceCuboid(start, end, cuboid);
        if (mop != null) {
            mop.field_72313_a = MovingObjectPosition.MovingObjectType.ENTITY;
            mop.field_72308_g = e;
        }
        return mop;
    }

    public MovingObjectPosition rayTraceCuboids(Vector3 start, Vector3 end, List<IndexedCuboid6> cuboids) {
        double c_dist = Double.MAX_VALUE;
        MovingObjectPosition c_hit = null;
        for (IndexedCuboid6 cuboid : cuboids) {
            MovingObjectPosition mop = this.rayTraceCuboid(start, end, cuboid);
            if (mop == null || !(this.s_dist < c_dist)) continue;
            mop = new ExtendedMOP(mop, cuboid.data, this.s_dist);
            c_dist = this.s_dist;
            c_hit = mop;
            this.c_cuboid = cuboid;
        }
        return c_hit;
    }

    public MovingObjectPosition rayTraceCuboids(Vector3 start, Vector3 end, List<IndexedCuboid6> cuboids, BlockCoord pos, Block block) {
        MovingObjectPosition mop = this.rayTraceCuboids(start, end, cuboids);
        if (mop != null) {
            mop.field_72313_a = MovingObjectPosition.MovingObjectType.BLOCK;
            mop.field_72311_b = pos.x;
            mop.field_72312_c = pos.y;
            mop.field_72309_d = pos.z;
            if (block != null) {
                this.c_cuboid.add(new Vector3(-pos.x, -pos.y, -pos.z)).setBlockBounds(block);
            }
        }
        return mop;
    }

    public void rayTraceCuboids(Vector3 start, Vector3 end, List<IndexedCuboid6> cuboids, BlockCoord pos, Block block, List<ExtendedMOP> hitList) {
        for (IndexedCuboid6 cuboid : cuboids) {
            MovingObjectPosition mop = this.rayTraceCuboid(start, end, cuboid);
            if (mop == null) continue;
            ExtendedMOP emop = new ExtendedMOP(mop, cuboid.data, this.s_dist);
            emop.field_72313_a = MovingObjectPosition.MovingObjectType.BLOCK;
            emop.field_72311_b = pos.x;
            emop.field_72312_c = pos.y;
            emop.field_72309_d = pos.z;
            hitList.add(emop);
        }
    }

    public static MovingObjectPosition retraceBlock(World world, EntityPlayer player, int x, int y, int z) {
        Block block = world.func_147439_a(x, y, z);
        Vec3 headVec = RayTracer.getCorrectedHeadVec(player);
        Vec3 lookVec = player.func_70676_i(1.0f);
        double reach = RayTracer.getBlockReachDistance(player);
        Vec3 endVec = headVec.func_72441_c(lookVec.field_72450_a * reach, lookVec.field_72448_b * reach, lookVec.field_72449_c * reach);
        return block.func_149731_a(world, x, y, z, headVec, endVec);
    }

    private static double getBlockReachDistance_server(EntityPlayerMP player) {
        return player.field_71134_c.getBlockReachDistance();
    }

    @SideOnly(value=Side.CLIENT)
    private static double getBlockReachDistance_client() {
        return Minecraft.func_71410_x().field_71442_b.func_78757_d();
    }

    public static MovingObjectPosition reTrace(World world, EntityPlayer player) {
        return RayTracer.reTrace(world, player, RayTracer.getBlockReachDistance(player));
    }

    public static MovingObjectPosition reTrace(World world, EntityPlayer player, double reach) {
        Vec3 headVec = RayTracer.getCorrectedHeadVec(player);
        Vec3 lookVec = player.func_70676_i(1.0f);
        Vec3 endVec = headVec.func_72441_c(lookVec.field_72450_a * reach, lookVec.field_72448_b * reach, lookVec.field_72449_c * reach);
        return world.func_147447_a(headVec, endVec, true, false, true);
    }

    public static Vec3 getCorrectedHeadVec(EntityPlayer player) {
        Vec3 v = Vec3.func_72443_a((double)player.field_70165_t, (double)player.field_70163_u, (double)player.field_70161_v);
        if (player.field_70170_p.field_72995_K) {
            v.field_72448_b += (double)(player.func_70047_e() - player.getDefaultEyeHeight());
        } else {
            v.field_72448_b += (double)player.func_70047_e();
            if (player instanceof EntityPlayerMP && player.func_70093_af()) {
                v.field_72448_b -= 0.08;
            }
        }
        return v;
    }

    public static Vec3 getStartVec(EntityPlayer player) {
        return RayTracer.getCorrectedHeadVec(player);
    }

    public static double getBlockReachDistance(EntityPlayer player) {
        return player.field_70170_p.field_72995_K ? RayTracer.getBlockReachDistance_client() : (player instanceof EntityPlayerMP ? RayTracer.getBlockReachDistance_server((EntityPlayerMP)player) : 5.0);
    }

    public static Vec3 getEndVec(EntityPlayer player) {
        Vec3 headVec = RayTracer.getCorrectedHeadVec(player);
        Vec3 lookVec = player.func_70676_i(1.0f);
        double reach = RayTracer.getBlockReachDistance(player);
        return headVec.func_72441_c(lookVec.field_72450_a * reach, lookVec.field_72448_b * reach, lookVec.field_72449_c * reach);
    }
}

