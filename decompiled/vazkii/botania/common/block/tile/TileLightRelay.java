/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityEnderPearl
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.stats.StatBase
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.tile;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import vazkii.botania.api.wand.IWandBindable;
import vazkii.botania.common.Botania;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileMod;
import vazkii.botania.common.core.helper.Vector3;

public class TileLightRelay
extends TileMod
implements IWandBindable {
    private static final int MAX_DIST = 20;
    private static final String TAG_BIND_X = "bindX";
    private static final String TAG_BIND_Y = "bindY";
    private static final String TAG_BIND_Z = "bindZ";
    int bindX;
    int bindY = -1;
    int bindZ;
    int ticksElapsed = 0;

    public void mountEntity(Entity e) {
        if (e.field_70154_o != null || this.field_145850_b.field_72995_K || this.bindY == -1 || !this.isValidBinding()) {
            return;
        }
        EntityPlayerMover mover = new EntityPlayerMover(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.bindX, this.bindY, this.bindZ);
        this.field_145850_b.func_72838_d((Entity)mover);
        e.func_70078_a((Entity)mover);
        if (!(e instanceof EntityItem)) {
            this.field_145850_b.func_72956_a((Entity)mover, "botania:lightRelay", 0.2f, (float)Math.random() * 0.3f + 0.7f);
            if (e instanceof EntityPlayer) {
                ((EntityPlayer)e).func_71064_a((StatBase)ModAchievements.luminizerRide, 1);
            }
        }
    }

    public void func_145845_h() {
        ++this.ticksElapsed;
        if (this.bindY > -1 && this.isValidBinding()) {
            Vector3 vec = this.getMovementVector();
            double dist = 0.1;
            int size = (int)(vec.mag() / dist);
            int count = 10;
            int start = this.ticksElapsed % size;
            Vector3 vecMag = vec.copy().normalize().multiply(dist);
            Vector3 vecTip = vecMag.copy().multiply(start).add((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5);
            double radPer = 0.19634954084936207;
            float mul = 0.5f;
            float mulPer = 0.4f;
            float maxMul = 2.0f;
            for (int i = start; i < start + count; ++i) {
                mul = Math.min(maxMul, mul + mulPer);
                double rad = radPer * ((double)i + (double)this.ticksElapsed * 0.4);
                Vector3 vecRot = vecMag.copy().crossProduct(Vector3.one).multiply(mul).rotate(rad, vecMag).add(vecTip);
                Botania.proxy.wispFX(this.field_145850_b, vecRot.x, vecRot.y, vecRot.z, 0.4f, 0.4f, 1.0f, 0.1f, (float)(-vecMag.x), (float)(-vecMag.y), (float)(-vecMag.z), 1.0f);
                vecTip.add(vecMag);
            }
            ChunkCoordinates endpoint = this.getEndpoint();
            if (endpoint != null && !this.field_145850_b.field_72995_K) {
                float range = 0.5f;
                List enderPearls = this.field_145850_b.func_72872_a(EntityEnderPearl.class, AxisAlignedBB.func_72330_a((double)((float)this.field_145851_c - range), (double)((float)this.field_145848_d - range), (double)((float)this.field_145849_e - range), (double)((float)(this.field_145851_c + 1) + range), (double)((float)(this.field_145848_d + 1) + range), (double)((float)(this.field_145849_e + 1) + range)));
                for (EntityEnderPearl pearl : enderPearls) {
                    pearl.field_70165_t = (double)endpoint.field_71574_a + pearl.field_70165_t - (double)this.field_145851_c;
                    pearl.field_70163_u = (double)endpoint.field_71572_b + pearl.field_70163_u - (double)this.field_145848_d;
                    pearl.field_70161_v = (double)endpoint.field_71573_c + pearl.field_70161_v - (double)this.field_145849_e;
                }
            }
        }
    }

    public boolean isValidBinding() {
        Block block = this.field_145850_b.func_147439_a(this.bindX, this.bindY, this.bindZ);
        return block == ModBlocks.lightRelay;
    }

    public ChunkCoordinates getEndpoint() {
        ArrayList<TileLightRelay> pointsPassed = new ArrayList<TileLightRelay>();
        TileLightRelay relay = this;
        ChunkCoordinates lastCoords = null;
        boolean run = true;
        while (run) {
            if (pointsPassed.contains(relay)) {
                return null;
            }
            pointsPassed.add(relay);
            ChunkCoordinates coords = relay.getBinding();
            if (coords == null) {
                return lastCoords;
            }
            TileEntity tile = this.field_145850_b.func_147438_o(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c);
            if (tile == null || !(tile instanceof TileLightRelay)) {
                return lastCoords;
            }
            relay = (TileLightRelay)tile;
            lastCoords = coords;
        }
        return null;
    }

    public Vector3 getMovementVector() {
        return new Vector3(this.bindX - this.field_145851_c, this.bindY - this.field_145848_d, this.bindZ - this.field_145849_e);
    }

    @Override
    public ChunkCoordinates getBinding() {
        return this.bindY == -1 ? null : new ChunkCoordinates(this.bindX, this.bindY, this.bindZ);
    }

    @Override
    public boolean canSelect(EntityPlayer player, ItemStack wand, int x, int y, int z, int side) {
        return true;
    }

    @Override
    public boolean bindTo(EntityPlayer player, ItemStack wand, int x, int y, int z, int side) {
        if (player.field_70170_p.func_147439_a(x, y, z) != ModBlocks.lightRelay || vazkii.botania.common.core.helper.MathHelper.pointDistanceSpace(x, y, z, this.field_145851_c, this.field_145848_d, this.field_145849_e) > 20.0f) {
            return false;
        }
        this.bindX = x;
        this.bindY = y;
        this.bindZ = z;
        return true;
    }

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        this.bindX = cmp.func_74762_e(TAG_BIND_X);
        this.bindY = cmp.func_74762_e(TAG_BIND_Y);
        this.bindZ = cmp.func_74762_e(TAG_BIND_Z);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound cmp) {
        cmp.func_74768_a(TAG_BIND_X, this.bindX);
        cmp.func_74768_a(TAG_BIND_Y, this.bindY);
        cmp.func_74768_a(TAG_BIND_Z, this.bindZ);
    }

    public static class EntityPlayerMover
    extends Entity {
        private static final String TAG_EXIT_X = "exitX";
        private static final String TAG_EXIT_Y = "exitY";
        private static final String TAG_EXIT_Z = "exitZ";

        public EntityPlayerMover(World world) {
            super(world);
        }

        public EntityPlayerMover(World world, int x, int y, int z, int exitX, int exitY, int exitZ) {
            this(world);
            this.func_70107_b((double)x + 0.5, (double)y + 0.5, (double)z + 0.5);
            this.setExit(exitX, exitY, exitZ);
        }

        protected void func_70088_a() {
            this.func_70105_a(0.0f, 0.0f);
            this.field_70145_X = true;
            this.field_70180_af.func_75682_a(20, (Object)0);
            this.field_70180_af.func_75682_a(21, (Object)0);
            this.field_70180_af.func_75682_a(22, (Object)0);
            this.field_70180_af.func_82708_h(20);
            this.field_70180_af.func_82708_h(21);
            this.field_70180_af.func_82708_h(22);
        }

        public void func_70071_h_() {
            super.func_70071_h_();
            if (this.field_70153_n == null && !this.field_70170_p.field_72995_K) {
                this.func_70106_y();
                return;
            }
            boolean isItem = this.field_70153_n instanceof EntityItem;
            if (!isItem && this.field_70173_aa % 30 == 0) {
                this.field_70170_p.func_72956_a((Entity)this, "botania:lightRelay", 0.05f, (float)Math.random() * 0.3f + 0.7f);
            }
            int exitX = this.getExitX();
            int exitY = this.getExitY();
            int exitZ = this.getExitZ();
            int x = MathHelper.func_76128_c((double)this.field_70165_t);
            int y = MathHelper.func_76128_c((double)this.field_70163_u);
            int z = MathHelper.func_76128_c((double)this.field_70161_v);
            if (x == exitX && y == exitY && z == exitZ) {
                TileEntity tile = this.field_70170_p.func_147438_o(x, y, z);
                if (tile != null && tile instanceof TileLightRelay) {
                    TileLightRelay relay;
                    ChunkCoordinates bind;
                    int meta = this.field_70170_p.func_72805_g(x, y, z);
                    if (meta > 0) {
                        this.field_70170_p.func_72921_c(x, y, z, meta | 8, 3);
                        this.field_70170_p.func_147464_a(x, y, z, tile.func_145838_q(), tile.func_145838_q().func_149738_a(this.field_70170_p));
                    }
                    if ((bind = (relay = (TileLightRelay)tile).getBinding()) != null && relay.isValidBinding()) {
                        this.setExit(bind.field_71574_a, bind.field_71572_b, bind.field_71573_c);
                        return;
                    }
                }
                this.field_70163_u += 1.5;
                this.func_70106_y();
            } else {
                Vector3 thisVec = Vector3.fromEntity(this);
                Vector3 motVec = thisVec.negate().add((double)exitX + 0.5, (double)exitY + 0.5, (double)exitZ + 0.5).normalize().multiply(0.5);
                int count = 4;
                for (int i = 0; i < count; ++i) {
                    Color color = Color.getHSBColor((float)this.field_70173_aa / 36.0f + 1.0f / (float)count * (float)i, 1.0f, 1.0f);
                    double rad = Math.PI * 2 / (double)count * (double)i + (double)this.field_70173_aa / Math.PI;
                    double cos = Math.cos(rad);
                    double sin = Math.sin(rad);
                    double s = 0.4;
                    Botania.proxy.sparkleFX(this.field_70170_p, this.field_70165_t + cos * s, this.field_70163_u - 0.5, this.field_70161_v + sin * s, (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, 1.2f, 10);
                }
                this.field_70165_t += motVec.x;
                this.field_70163_u += motVec.y;
                this.field_70161_v += motVec.z;
            }
        }

        public boolean shouldRiderSit() {
            return false;
        }

        public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
            return false;
        }

        protected void func_70037_a(NBTTagCompound cmp) {
            this.setExit(cmp.func_74762_e(TAG_EXIT_X), cmp.func_74762_e(TAG_EXIT_Y), cmp.func_74762_e(TAG_EXIT_Z));
        }

        protected void func_70014_b(NBTTagCompound cmp) {
            cmp.func_74768_a(TAG_EXIT_X, this.getExitX());
            cmp.func_74768_a(TAG_EXIT_Y, this.getExitY());
            cmp.func_74768_a(TAG_EXIT_Z, this.getExitZ());
        }

        public int getExitX() {
            return this.field_70180_af.func_75679_c(20);
        }

        public int getExitY() {
            return this.field_70180_af.func_75679_c(21);
        }

        public int getExitZ() {
            return this.field_70180_af.func_75679_c(22);
        }

        public void setExit(int x, int y, int z) {
            this.field_70180_af.func_75692_b(20, (Object)x);
            this.field_70180_af.func_75692_b(21, (Object)y);
            this.field_70180_af.func_75692_b(22, (Object)z);
        }
    }
}

