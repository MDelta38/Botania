/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingFallEvent
 */
package com.emoniph.witchery.infusion.infusions;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.ServerTickEvents;
import com.emoniph.witchery.entity.EntityWitchProjectile;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.infusion.infusions.InfusionOtherwhere;
import com.emoniph.witchery.network.PacketPushTarget;
import com.emoniph.witchery.ritual.rites.RiteProtectionCircleRepulsive;
import com.emoniph.witchery.util.BlockProtect;
import com.emoniph.witchery.util.BlockSide;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.EarthItems;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingFallEvent;

public class InfusionOverworld
extends Infusion {
    public InfusionOverworld(int infusionID) {
        super(infusionID);
    }

    @Override
    public IIcon getPowerBarIcon(EntityPlayer player, int index) {
        return Blocks.field_150346_d.func_149691_a(0, 0);
    }

    @Override
    public void onFalling(World world, EntityPlayer player, LivingFallEvent event) {
        int blockZ;
        int blockY;
        int blockX;
        Block blockID;
        if (event.distance > 3.0f && ((blockID = world.func_147439_a(blockX = MathHelper.func_76128_c((double)player.field_70165_t), blockY = MathHelper.func_76128_c((double)player.field_70163_u) - 1, blockZ = MathHelper.func_76128_c((double)player.field_70161_v))) == Blocks.field_150349_c || blockID == Blocks.field_150349_c || blockID == Blocks.field_150391_bh || blockID == Blocks.field_150351_n || blockID == Blocks.field_150354_m || blockID == Blocks.field_150433_aE)) {
            if (player.func_70093_af()) {
                if (this.consumeCharges(world, player, 10, true)) {
                    event.distance = 0.0f;
                    int EXPLOSION_STRENGTH = 3;
                    world.func_72876_a((Entity)player, player.field_70165_t, (double)blockY + 0.5, player.field_70161_v, 3.0f, true);
                }
            } else if (this.consumeCharges(world, player, 5, true)) {
                event.distance = 0.0f;
                world.func_147468_f(blockX, blockY, blockZ);
                ItemStack itemstack = new ItemStack(blockID, 1, 0);
                EntityItem blockEntity = new EntityItem(world, (double)blockX, (double)blockY, (double)blockZ, itemstack);
                world.func_72838_d((Entity)blockEntity);
            }
        }
    }

    @Override
    public void onLeftClickEntity(ItemStack itemstack, World world, EntityPlayer player, Entity otherEntity) {
        if (world.field_72995_K) {
            return;
        }
        if (otherEntity instanceof EntityLivingBase) {
            EntityLivingBase otherLivingEntity = (EntityLivingBase)otherEntity;
            int posX = MathHelper.func_76128_c((double)player.field_70165_t);
            int posY = MathHelper.func_76128_c((double)player.field_70163_u);
            int posZ = MathHelper.func_76128_c((double)player.field_70161_v);
            boolean isWearingMetalArmour = false;
            for (int i = 0; i < 5; ++i) {
                ItemStack heldStack = otherLivingEntity.func_71124_b(i);
                if (!EarthItems.instance().isMatch(heldStack)) continue;
                isWearingMetalArmour = true;
                break;
            }
            if (isWearingMetalArmour) {
                double ACCELERATION = 3.0;
                if (player.func_70093_af()) {
                    if (this.consumeCharges(world, player, 4, true)) {
                        Vec3 look = player.func_70040_Z();
                        double motionX = look.field_72450_a * 0.8 * 3.0;
                        double motionY = 1.5;
                        double motionZ = look.field_72449_c * 0.8 * 3.0;
                        if (otherLivingEntity instanceof EntityPlayer) {
                            EntityPlayer targetPlayer = (EntityPlayer)otherLivingEntity;
                            Witchery.packetPipeline.sendTo((IMessage)new PacketPushTarget(motionX, 1.5, motionZ), targetPlayer);
                        } else {
                            otherLivingEntity.field_70159_w = motionX;
                            otherLivingEntity.field_70181_x = 1.5;
                            otherLivingEntity.field_70179_y = motionZ;
                        }
                    }
                } else if (this.consumeCharges(world, player, 2, true)) {
                    Vec3 look = player.func_70040_Z();
                    double motionX = look.field_72450_a * 0.8 * 3.0;
                    double motionY = 0.30000000000000004;
                    double motionZ = look.field_72449_c * 0.8 * 3.0;
                    if (otherLivingEntity instanceof EntityPlayer) {
                        EntityPlayer targetPlayer = (EntityPlayer)otherLivingEntity;
                        Witchery.packetPipeline.sendTo((IMessage)new PacketPushTarget(motionX, 0.30000000000000004, motionZ), targetPlayer);
                    } else {
                        otherLivingEntity.field_70159_w = motionX;
                        otherLivingEntity.field_70181_x = 0.30000000000000004;
                        otherLivingEntity.field_70179_y = motionZ;
                    }
                }
            }
        }
    }

    @Override
    public void onUsingItemTick(ItemStack itemstack, World world, EntityPlayer player, int countdown) {
        if (!world.field_72995_K) {
            int elapsedTicks = this.getMaxItemUseDuration(itemstack) - countdown;
            int seconds = elapsedTicks / 20;
            if (player.func_70093_af()) {
                if (seconds >= 2 && elapsedTicks % 4 == 0 && this.consumeCharges(world, player, 1, true)) {
                    int AreaOfEffect = 6;
                    List entities = world.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a((double)(player.field_70165_t - 6.0), (double)(player.field_70163_u - 6.0), (double)(player.field_70161_v - 6.0), (double)(player.field_70165_t + 6.0), (double)(player.field_70163_u + 6.0), (double)(player.field_70161_v + 6.0)));
                    for (int i = 0; i < entities.size(); ++i) {
                        EntityItem entity = (EntityItem)entities.get(i);
                        if (!EarthItems.instance().isMatch(entity.func_92059_d())) continue;
                        double d0 = 8.0;
                        double motionX = 0.0;
                        double motionY = 0.0;
                        double motionZ = 0.0;
                        double d1 = (player.field_70165_t - entity.field_70165_t) / d0;
                        double d2 = (player.field_70163_u + (double)player.func_70047_e() - entity.field_70163_u) / d0;
                        double d3 = (player.field_70161_v - entity.field_70161_v) / d0;
                        double d4 = Math.sqrt(d1 * d1 + d2 * d2 + d3 * d3);
                        double d5 = 2.0;
                        if (d5 > 0.0) {
                            d5 *= d5;
                            motionX += d1 / Math.max(Math.abs(d1), 0.0) * 1.0;
                            motionY += d2 / Math.max(Math.abs(d1), 0.0) * 1.0;
                            motionZ += d3 / Math.max(Math.abs(d1), 0.0) * 1.0;
                        }
                        boolean oldClip = entity.field_70145_X;
                        entity.field_70145_X = true;
                        entity.func_70091_d(motionX, motionY, motionZ);
                        entity.field_70145_X = oldClip;
                    }
                    int AreaOfEffect2 = 6;
                    for (int x = (int)player.field_70165_t - 6; x <= (int)player.field_70165_t + 6; ++x) {
                        for (int y = (int)player.field_70163_u - 3; y <= (int)player.field_70163_u + 3; ++y) {
                            for (int z = (int)player.field_70161_v - 6; z <= (int)player.field_70161_v + 6; ++z) {
                                Item ingot;
                                Block id = world.func_147439_a(x, y, z);
                                if (id == Blocks.field_150350_a || (ingot = EarthItems.instance().oreToIngot(id)) == null || world.field_72995_K || !this.consumeCharges(world, player, 2, true)) continue;
                                world.func_147465_d(x, y, z, Blocks.field_150348_b, 0, 3);
                                world.func_72838_d((Entity)new EntityItem(world, (double)x, (double)y, (double)z, new ItemStack(ingot)));
                            }
                        }
                    }
                }
            } else if (seconds >= 2 && elapsedTicks % 20 == 0) {
                this.playSound(world, player, "random.orb");
            }
        }
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int countdown) {
        int seconds;
        if (world.field_72995_K) {
            return;
        }
        int elapsedTicks = this.getMaxItemUseDuration(itemstack) - countdown;
        MovingObjectPosition hit = InfusionOtherwhere.doCustomRayTrace(world, player, true, 4.0);
        if (hit != null) {
            switch (hit.field_72313_a) {
                case ENTITY: {
                    if (player.func_70093_af() || !(hit.field_72308_g instanceof EntityLiving) || !this.consumeCharges(world, player, 2, true)) break;
                    EntityLiving entity = (EntityLiving)hit.field_72308_g;
                    ItemStack heldItem = entity.func_70694_bm();
                    if (heldItem != null && EarthItems.instance().isMatch(heldItem) && !world.field_72995_K) {
                        entity.func_70099_a(heldItem, 2.0f);
                        entity.func_70062_b(0, null);
                    }
                    return;
                }
                case BLOCK: {
                    int DEPTH = 3;
                    if (!player.func_70093_af() && BlockSide.TOP.isEqual(hit.field_72310_e) && world.func_147439_a(hit.field_72311_b, hit.field_72312_c - 9 - 1, hit.field_72309_d).func_149688_o().func_76220_a() && this.consumeCharges(world, player, 2, true)) {
                        for (int h = 0; h < 6; ++h) {
                            int originY = hit.field_72312_c - h;
                            Block blockID = world.func_147439_a(hit.field_72311_b, originY, hit.field_72309_d);
                            if (!BlockProtect.canBreak(blockID, world)) continue;
                            int blockMetadata = world.func_72805_g(hit.field_72311_b, originY, hit.field_72309_d);
                            world.func_147468_f(hit.field_72311_b, originY, hit.field_72309_d);
                            if (BlockProtect.canBreak(hit.field_72311_b, originY + 3, hit.field_72309_d, world)) {
                                world.func_147465_d(hit.field_72311_b, originY + 3, hit.field_72309_d, blockID, blockMetadata, 3);
                            }
                            AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)hit.field_72311_b, (double)hit.field_72312_c, (double)hit.field_72309_d, (double)(hit.field_72311_b + 1), (double)(hit.field_72312_c + 2), (double)(hit.field_72309_d + 1));
                            for (Object obj : world.func_72872_a(Entity.class, bounds)) {
                                Entity entity = (Entity)obj;
                                if (entity instanceof EntityLivingBase) {
                                    ((EntityLivingBase)entity).func_70634_a(entity.field_70165_t, entity.field_70163_u + 3.0, entity.field_70161_v);
                                    continue;
                                }
                                entity.func_70107_b(entity.field_70165_t, entity.field_70163_u + 3.0, entity.field_70161_v);
                            }
                        }
                    } else if (!(player.func_70093_af() || BlockSide.BOTTOM.isEqual(hit.field_72310_e) || BlockSide.TOP.isEqual(hit.field_72310_e))) {
                        if (this.isThrowableRock(world, hit.field_72311_b, hit.field_72312_c, hit.field_72309_d, hit.field_72310_e) && this.consumeCharges(world, player, 3, true)) {
                            world.func_147468_f(hit.field_72311_b, hit.field_72312_c, hit.field_72309_d);
                            ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_EXPLODE, world, hit.field_72311_b, hit.field_72312_c, hit.field_72309_d, 0.5, 0.5, 8);
                            EntityWitchProjectile rockEntity = new EntityWitchProjectile(world, (EntityLivingBase)player, Witchery.Items.GENERIC.itemRock);
                            rockEntity.func_70107_b((double)hit.field_72311_b + 0.5, (double)hit.field_72312_c + 0.5, (double)hit.field_72309_d + 0.5);
                            world.func_72838_d((Entity)rockEntity);
                        }
                    } else if (player.func_70093_af() && this.consumeCharges(world, player, 2, true)) {
                        Block blockID = world.func_147439_a(hit.field_72311_b, hit.field_72312_c, hit.field_72309_d);
                        Item ingot = EarthItems.instance().oreToIngot(blockID);
                        if (ingot != null) {
                            world.func_147465_d(hit.field_72311_b, hit.field_72312_c, hit.field_72309_d, Blocks.field_150348_b, 0, 3);
                            if (!world.field_72995_K) {
                                world.func_72838_d((Entity)new EntityItem(world, (double)hit.field_72311_b, (double)hit.field_72312_c, (double)hit.field_72309_d, new ItemStack(ingot, 2, 0)));
                            }
                        }
                    }
                    return;
                }
            }
        }
        if ((seconds = elapsedTicks / 20) >= 2 && !player.func_70093_af() && this.consumeCharges(world, player, 6 * seconds, true)) {
            ServerTickEvents.TASKS.add(new ShockwaveTask(player, 2 * seconds));
        } else {
            this.playFailSound(world, player);
        }
    }

    private boolean isThrowableRock(World world, int blockX, int blockY, int blockZ, int sideHit) {
        Block[] blocks = new Block[]{Blocks.field_150346_d, Blocks.field_150349_c, Blocks.field_150348_b, Blocks.field_150347_e, Blocks.field_150354_m, Blocks.field_150351_n, Blocks.field_150322_A, Blocks.field_150333_U, Blocks.field_150336_V, Blocks.field_150341_Y, Blocks.field_150349_c, Blocks.field_150446_ar, Blocks.field_150435_aG, Blocks.field_150425_aM, Blocks.field_150417_aV, Blocks.field_150389_bf, Blocks.field_150390_bg, Blocks.field_150391_bh, Blocks.field_150385_bj, Blocks.field_150387_bl, Blocks.field_150372_bz, Blocks.field_150405_ch, Blocks.field_150402_ci, Blocks.field_150424_aL};
        Block blockID = world.func_147439_a(blockX, blockY, blockZ);
        if (!Arrays.asList(blocks).contains(blockID)) {
            return false;
        }
        boolean northValid = BlockSide.NORTH.isEqual(sideHit) && !world.func_147439_a(blockX + 1, blockY, blockZ).func_149688_o().func_76220_a();
        boolean southValid = BlockSide.SOUTH.isEqual(sideHit) && !world.func_147439_a(blockX - 1, blockY, blockZ).func_149688_o().func_76220_a();
        boolean eastValid = BlockSide.EAST.isEqual(sideHit) && !world.func_147439_a(blockX, blockY, blockZ + 1).func_149688_o().func_76220_a();
        boolean westValid = BlockSide.WEST.isEqual(sideHit) && !world.func_147439_a(blockX, blockY, blockZ - 1).func_149688_o().func_76220_a();
        return northValid || southValid || eastValid || westValid;
    }

    private static class ShockwaveTask
    extends ServerTickEvents.ServerTickTask {
        final Coord center;
        final EntityPlayer creator;
        final int maxRadius;
        final int MIN_RADIUS = 2;
        int stage = 0;

        public ShockwaveTask(EntityPlayer creator, int maxRadius) {
            super(creator.field_70170_p);
            this.center = new Coord((int)creator.field_70165_t, (int)creator.field_70163_u - 1, (int)creator.field_70161_v);
            this.creator = creator;
            this.maxRadius = maxRadius + 2;
        }

        @Override
        public boolean process() {
            ++this.stage;
            Block centerBlock = this.center.getBlock(this.world);
            if (this.stage == 1) {
                this.drawCircle(this.world, this.center.x, this.center.y, this.center.z, this.stage + 2, 2, 1);
            } else {
                this.drawCircle(this.world, this.center.x, this.center.y + 2, this.center.z, this.stage + 2, 2, -1);
                this.drawCircle(this.world, this.center.x, this.center.y + 1, this.center.z, this.stage + 2 - 1, 2, -1);
            }
            if (this.stage < this.maxRadius) {
                this.drawCircle(this.world, this.center.x, this.center.y, this.center.z, this.stage + 2 + 1, 2, 2);
            } else {
                this.drawCircle(this.world, this.center.x, this.center.y + 1, this.center.z, this.stage + 2, 2, -1);
            }
            int r = this.stage + 2;
            AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(this.center.x - r), (double)(this.center.y + 1), (double)(this.center.z - r), (double)(this.center.x + r), (double)(this.center.y + 3), (double)(this.center.z + r));
            for (Object obj : this.world.func_72872_a(EntityLivingBase.class, bounds)) {
                EntityLivingBase entity = (EntityLivingBase)obj;
                Coord position = new Coord((Entity)entity);
                double dist = this.center.distanceTo(position);
                if (!(dist <= (double)(r + 1)) || !(dist >= (double)r)) continue;
                entity.func_70097_a(DamageSource.func_76365_a((EntityPlayer)this.creator), 8.0f);
                RiteProtectionCircleRepulsive.push(this.world, (Entity)entity, this.center.x, this.center.y, this.center.z);
            }
            return this.stage == this.maxRadius;
        }

        protected void drawCircle(World world, int x0, int y0, int z0, int radius, int blocksToMove, int direction) {
            int x = radius;
            int radiusError = 1 - x;
            for (int z = 0; x >= z; ++z) {
                this.drawPixel(world, x + x0, y0, z + z0, blocksToMove, direction);
                this.drawPixel(world, z + x0, y0, x + z0, blocksToMove, direction);
                this.drawPixel(world, -x + x0, y0, z + z0, blocksToMove, direction);
                this.drawPixel(world, -z + x0, y0, x + z0, blocksToMove, direction);
                this.drawPixel(world, -x + x0, y0, -z + z0, blocksToMove, direction);
                this.drawPixel(world, -z + x0, y0, -x + z0, blocksToMove, direction);
                this.drawPixel(world, x + x0, y0, -z + z0, blocksToMove, direction);
                this.drawPixel(world, z + x0, y0, -x + z0, blocksToMove, direction);
                if (radiusError < 0) {
                    radiusError += 2 * z + 1;
                    continue;
                }
                radiusError += 2 * (z - --x + 1);
            }
        }

        protected void drawPixel(World world, int x, int y, int z, int blocksToMove, int direction) {
            if (direction > 0) {
                if (world.func_147437_c(x, y - blocksToMove + 1, z) || world.func_147439_a(x, y + 1, z).func_149688_o().func_76220_a()) {
                    return;
                }
                for (int i = 0; i < blocksToMove; ++i) {
                    Block blockID = world.func_147439_a(x, y - i, z);
                    int blockMetadata = world.func_72805_g(x, y - i, z);
                    if (BlockProtect.canBreak(blockID, world)) {
                        world.func_147468_f(x, y - i, z);
                    }
                    if (!BlockProtect.canBreak(x, y - i + direction, z, world)) continue;
                    world.func_147465_d(x, y - i + direction, z, blockID, blockMetadata, 3);
                }
            } else {
                if (world.func_147437_c(x, y, z) || world.func_147439_a(x, y + direction - 1, z).func_149688_o().func_76220_a()) {
                    return;
                }
                for (int i = blocksToMove - 1; i >= 0; --i) {
                    Block blockID = world.func_147439_a(x, y - i, z);
                    int blockMetadata = world.func_72805_g(x, y - i, z);
                    if (BlockProtect.canBreak(blockID, world)) {
                        world.func_147468_f(x, y - i, z);
                    }
                    if (!BlockProtect.canBreak(x, y - i + direction, z, world)) continue;
                    world.func_147465_d(x, y - i + direction, z, blockID, blockMetadata, 3);
                }
            }
        }
    }
}

