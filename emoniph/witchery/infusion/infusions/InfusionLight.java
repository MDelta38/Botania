/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.infusion.infusions;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockBarrier;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.infusion.infusions.InfusionOtherwhere;
import com.emoniph.witchery.util.BlockSide;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.EntityUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class InfusionLight
extends Infusion {
    private static final int BARRIER_RADIUS = 2;
    private static final int BARRIER_HEIGHT = 3;
    private static final boolean BARRIER_BLOCKS_PLAYERS = true;
    private static final int AGGRO_DROP_RADIUS = 20;
    protected static final int BARRIER_TICKS_TO_LIVE_ = 200;

    public InfusionLight(int infusionID) {
        super(infusionID);
    }

    @Override
    public IIcon getPowerBarIcon(EntityPlayer player, int index) {
        return Blocks.field_150433_aE.func_149691_a(0, 0);
    }

    @Override
    public void onLeftClickEntity(ItemStack itemstack, World world, EntityPlayer player, Entity otherEntity) {
        if (world.field_72995_K) {
            return;
        }
        if (otherEntity instanceof EntityLivingBase) {
            EntityLivingBase otherLivingEntity = (EntityLivingBase)otherEntity;
            int UPSHIFT = 4;
            int posX = (int)otherEntity.field_70165_t;
            int posY = (int)otherEntity.field_70163_u + 4;
            int posZ = (int)otherEntity.field_70161_v;
            if (world.func_147437_c(posX, posY, posZ) && world.func_147437_c(posX, posY + 1, posZ) && world.func_147437_c(posX, posY + 2, posZ) && world.func_147437_c(posX + 1, posY, posZ) && world.func_147437_c(posX + 1, posY + 1, posZ) && world.func_147437_c(posX + 1, posY + 2, posZ) && world.func_147437_c(posX, posY, posZ + 1) && world.func_147437_c(posX, posY + 1, posZ + 1) && world.func_147437_c(posX, posY + 2, posZ + 1) && world.func_147437_c(posX - 1, posY, posZ) && world.func_147437_c(posX - 1, posY + 1, posZ) && world.func_147437_c(posX - 1, posY + 2, posZ) && world.func_147437_c(posX, posY, posZ - 1) && world.func_147437_c(posX, posY + 1, posZ - 1) && world.func_147437_c(posX, posY + 2, posZ - 1) && this.consumeCharges(world, player, 5, true)) {
                this.drawFilledCircle(world, posX, posZ, posY - 1, 2, null);
                for (int y = posY; y < posY + 3; ++y) {
                    this.drawCircle(world, posX, posZ, y, 2, null);
                }
                this.drawFilledCircle(world, posX, posZ, posY + 3, 2, null);
                otherLivingEntity.func_70634_a((double)posX, (double)posY, (double)posZ);
            }
        }
    }

    @Override
    public void onUsingItemTick(ItemStack itemstack, World world, EntityPlayer player, int countdown) {
        if (world.field_72995_K) {
            if (!player.func_70115_ae()) {
                int var7;
                int var6;
                int var5 = MathHelper.func_76128_c((double)player.field_70165_t);
                if (world.func_147439_a(var5, var6 = MathHelper.func_76128_c((double)(player.field_70163_u - 2.0)), var7 = MathHelper.func_76128_c((double)player.field_70161_v)) != Blocks.field_150432_aD) {
                    if (player.field_70122_E) {
                        if (!player.func_70090_H()) {
                            player.field_70159_w *= 1.6500000476837158;
                            player.field_70179_y *= 1.6500000476837158;
                        } else {
                            player.field_70159_w *= (double)1.1f;
                            player.field_70179_y *= (double)1.1f;
                        }
                    }
                } else {
                    player.field_70159_w *= (double)1.1f;
                    player.field_70179_y *= (double)1.1f;
                }
            }
            return;
        }
        int elapsedTicks = this.getMaxItemUseDuration(itemstack) - countdown;
        if (elapsedTicks % 30 == 0 && elapsedTicks > 19) {
            if (this.consumeCharges(world, player, 1, true)) {
                this.bendLightAroundPlayer(world, player, true);
            } else {
                this.bendLightAroundPlayer(world, player, false);
            }
        }
    }

    protected void bendLightAroundPlayer(World world, EntityPlayer player, boolean active) {
        if (active) {
            player.func_70690_d(new PotionEffect(Potion.field_76441_p.field_76415_H, 30, 0, true));
            int r = 20;
            AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(player.field_70165_t - 20.0), (double)player.field_70163_u, (double)(player.field_70161_v - 20.0), (double)(player.field_70165_t + 20.0), (double)(player.field_70163_u + 2.0), (double)(player.field_70161_v + 20.0));
            for (Object obj : world.func_72872_a(EntityLiving.class, bounds)) {
                EntityLiving entity = (EntityLiving)obj;
                if (entity.func_70638_az() != player || !(Coord.distance(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, player.field_70165_t, player.field_70163_u, player.field_70161_v) <= 20.0)) continue;
                EntityUtil.dropAttackTarget(entity);
            }
        } else {
            player.func_82170_o(Potion.field_76441_p.field_76415_H);
        }
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int countdown) {
        MovingObjectPosition hitMOP;
        if (world.field_72995_K) {
            return;
        }
        this.bendLightAroundPlayer(world, player, false);
        int elapsedTicks = this.getMaxItemUseDuration(itemstack) - countdown;
        if (elapsedTicks < 20 && player.func_70093_af() && (hitMOP = InfusionOtherwhere.doCustomRayTrace(world, player, true, 16.0)) != null) {
            switch (hitMOP.field_72313_a) {
                case ENTITY: {
                    if (!(hitMOP.field_72308_g instanceof EntityLivingBase)) break;
                    EntityLivingBase otherLivingEntity = (EntityLivingBase)hitMOP.field_72308_g;
                    if (!this.consumeCharges(world, player, 3, true)) break;
                    int posX = (int)otherLivingEntity.field_70165_t;
                    int posY = (int)otherLivingEntity.field_70163_u;
                    int posZ = (int)otherLivingEntity.field_70161_v;
                    this.drawFilledCircle(world, posX, posZ, posY - 1, 1, player);
                    for (int y = posY; y < posY + 3; ++y) {
                        this.drawCircle(world, posX, posZ, y, 2, player);
                    }
                    this.drawFilledCircle(world, posX, posZ, posY + 3, 2, player);
                    break;
                }
                case BLOCK: {
                    int z;
                    int y;
                    int x;
                    int i;
                    int dy;
                    int dx;
                    if (BlockSide.TOP.isEqual(hitMOP.field_72310_e) && this.consumeCharges(world, player, 3, true)) {
                        InfusionLight.placeBarrierShield(world, player, hitMOP);
                        break;
                    }
                    if (BlockSide.TOP.isEqual(hitMOP.field_72310_e) || BlockSide.TOP.isEqual(hitMOP.field_72310_e) || !this.consumeCharges(world, player, 3, true)) break;
                    int b0 = 0;
                    switch (hitMOP.field_72310_e) {
                        case 0: 
                        case 1: {
                            b0 = 0;
                            break;
                        }
                        case 2: 
                        case 3: {
                            b0 = 8;
                            break;
                        }
                        case 4: 
                        case 5: {
                            b0 = 4;
                        }
                    }
                    int n = hitMOP.field_72310_e == 5 ? 1 : (dx = hitMOP.field_72310_e == 4 ? -1 : 0);
                    int n2 = hitMOP.field_72310_e == 0 ? -1 : (dy = hitMOP.field_72310_e == 1 ? 1 : 0);
                    int dz = hitMOP.field_72310_e == 3 ? 1 : (hitMOP.field_72310_e == 2 ? -1 : 0);
                    int sproutExtent = 16;
                    boolean isInitialBlockSolid = world.func_147439_a(hitMOP.field_72311_b, hitMOP.field_72312_c, hitMOP.field_72309_d).func_149688_o().func_76220_a();
                    int n3 = i = hitMOP.field_72310_e == 1 && !isInitialBlockSolid ? 0 : 1;
                    while (i < 16 && InfusionLight.setBlockIfNotSolid(world, x = hitMOP.field_72311_b + i * dx, y = hitMOP.field_72312_c + i * dy, z = hitMOP.field_72309_d + i * dz)) {
                        ++i;
                    }
                    break;
                }
            }
        }
    }

    public static void placeBarrierShield(World world, EntityPlayer player, MovingObjectPosition hitMOP) {
        double f1 = MathHelper.func_76134_b((float)(-player.field_70177_z * ((float)Math.PI / 180) - (float)Math.PI));
        double f2 = MathHelper.func_76126_a((float)(-player.field_70177_z * ((float)Math.PI / 180) - (float)Math.PI));
        Vec3 loc = Vec3.func_72443_a((double)f2, (double)0.0, (double)f1);
        Material material = world.func_147439_a(hitMOP.field_72311_b, hitMOP.field_72312_c, hitMOP.field_72309_d).func_149688_o();
        int yPlus = 1;
        if (material != null && !material.func_76220_a()) {
            yPlus = 0;
        }
        InfusionLight.drawBarrierBlockColumn(world, player, hitMOP.field_72311_b, hitMOP.field_72312_c + yPlus, hitMOP.field_72309_d, 3);
        loc.func_72442_b((float)Math.toRadians(90.0));
        int newX = MathHelper.func_76128_c((double)((double)hitMOP.field_72311_b + 0.5 + loc.field_72450_a * 1.0));
        int newZ = MathHelper.func_76128_c((double)((double)hitMOP.field_72309_d + 0.5 + loc.field_72449_c * 1.0));
        InfusionLight.drawBarrierBlockColumn(world, player, newX, hitMOP.field_72312_c + yPlus, newZ, 3);
        loc.func_72442_b((float)Math.toRadians(180.0));
        newX = MathHelper.func_76128_c((double)((double)hitMOP.field_72311_b + 0.5 + loc.field_72450_a * 1.0));
        newZ = MathHelper.func_76128_c((double)((double)hitMOP.field_72309_d + 0.5 + loc.field_72449_c * 1.0));
        InfusionLight.drawBarrierBlockColumn(world, player, newX, hitMOP.field_72312_c + yPlus, newZ, 3);
    }

    private static boolean setBlockIfNotSolid(World world, int x, int y, int z) {
        if (world.func_147439_a(x, y, z).func_149688_o().func_76222_j()) {
            BlockBarrier.setBlock(world, x, y, z, 200, true, null);
            return true;
        }
        return false;
    }

    private static void drawBarrierBlockColumn(World world, EntityPlayer player, int posX, int posY, int posZ, int height) {
        for (int offsetPosY = posY; offsetPosY < posY + height; ++offsetPosY) {
            Material material = world.func_147439_a(posX, offsetPosY, posZ).func_149688_o();
            Block blockID = world.func_147439_a(posX, offsetPosY, posZ);
            if (!material.func_76222_j() && blockID != Witchery.Blocks.BARRIER) continue;
            BlockBarrier.setBlock(world, posX, offsetPosY, posZ, 200, true, player);
        }
    }

    protected void drawCircle(World world, int x0, int z0, int y, int radius, EntityPlayer player) {
        int x = radius;
        int radiusError = 1 - x;
        for (int z = 0; x >= z; ++z) {
            this.drawPixel(world, x + x0, z + z0, y, player);
            this.drawPixel(world, z + x0, x + z0, y, player);
            this.drawPixel(world, -x + x0, z + z0, y, player);
            this.drawPixel(world, -z + x0, x + z0, y, player);
            this.drawPixel(world, -x + x0, -z + z0, y, player);
            this.drawPixel(world, -z + x0, -x + z0, y, player);
            this.drawPixel(world, x + x0, -z + z0, y, player);
            this.drawPixel(world, z + x0, -x + z0, y, player);
            if (radiusError < 0) {
                radiusError += 2 * z + 1;
                continue;
            }
            radiusError += 2 * (z - --x + 1);
        }
    }

    protected void drawFilledCircle(World world, int x0, int z0, int y, int radius, EntityPlayer player) {
        int x = radius;
        int radiusError = 1 - x;
        for (int z = 0; x >= z; ++z) {
            this.drawLine(world, -x + x0, x + x0, z + z0, y, player);
            this.drawLine(world, -z + x0, z + x0, x + z0, y, player);
            this.drawLine(world, -x + x0, x + x0, -z + z0, y, player);
            this.drawLine(world, -z + x0, z + x0, -x + z0, y, player);
            if (radiusError < 0) {
                radiusError += 2 * z + 1;
                continue;
            }
            radiusError += 2 * (z - --x + 1);
        }
    }

    protected void drawLine(World world, int x1, int x2, int z, int y, EntityPlayer player) {
        for (int x = x1; x <= x2; ++x) {
            this.drawPixel(world, x, z, y, player);
        }
    }

    protected void drawPixel(World world, int x, int z, int y, EntityPlayer player) {
        Block blockID = world.func_147439_a(x, y, z);
        Material material = world.func_147439_a(x, y, z).func_149688_o();
        if (!material.func_76220_a() || blockID == Witchery.Blocks.BARRIER || material == Material.field_151579_a) {
            BlockBarrier.setBlock(world, x, y, z, 200, true, player);
        }
    }
}

