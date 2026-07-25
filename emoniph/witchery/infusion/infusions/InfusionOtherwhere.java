/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.infusion.infusions;

import com.emoniph.witchery.brewing.potions.PotionEnderInhibition;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.DimensionalLocation;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.List;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class InfusionOtherwhere
extends Infusion {
    private static final String RECALL_LOCATON_KEY = "WITCRecall";
    private static final int SAVE_RECALL_POINT_THRESHOLD = 60;

    public InfusionOtherwhere(int infusionID) {
        super(infusionID);
    }

    @Override
    public IIcon getPowerBarIcon(EntityPlayer player, int index) {
        return Blocks.field_150427_aO.func_149691_a(0, 0);
    }

    @Override
    public void onLeftClickEntity(ItemStack itemstack, World world, EntityPlayer player, Entity otherEntity) {
        if (world.field_72995_K) {
            return;
        }
        if (otherEntity instanceof EntityLivingBase) {
            EntityLivingBase otherLivingEntity = (EntityLivingBase)otherEntity;
            if (player.func_70093_af()) {
                DimensionalLocation recallLocation = this.recallLocation(InfusionOtherwhere.getNBT((Entity)player), RECALL_LOCATON_KEY);
                if (recallLocation != null && recallLocation.dimension != Config.instance().dimensionDreamID && recallLocation.dimension != Config.instance().dimensionTormentID && recallLocation.dimension != Config.instance().dimensionMirrorID && world.field_73011_w.field_76574_g != Config.instance().dimensionDreamID && world.field_73011_w.field_76574_g != Config.instance().dimensionTormentID && world.field_73011_w.field_76574_g != Config.instance().dimensionMirrorID && !PotionEnderInhibition.isActive((Entity)player, 2) && this.consumeCharges(world, player, 4, false)) {
                    if (player instanceof EntityPlayerMP && !InfusionOtherwhere.isConnectionClosed((EntityPlayerMP)player)) {
                        player.field_70143_R = 0.0f;
                        ItemGeneral.teleportToLocation(world, recallLocation.posX, recallLocation.posY, recallLocation.posZ, recallLocation.dimension, (Entity)player, true);
                        otherLivingEntity.field_70143_R = 0.0f;
                        if (!PotionEnderInhibition.isActive((Entity)otherLivingEntity, 2)) {
                            ItemGeneral.teleportToLocation(world, recallLocation.posX, recallLocation.posY, recallLocation.posZ, recallLocation.dimension, (Entity)otherLivingEntity, true);
                        }
                    }
                } else {
                    world.func_72956_a((Entity)player, "note.snare", 0.5f, 0.4f / ((float)Math.random() * 0.4f + 0.8f));
                }
            } else if (!PotionEnderInhibition.isActive((Entity)player, 2) && this.consumeCharges(world, player, 2, true)) {
                double hikeModified2;
                double HIKE_HEIGHT = 8.0;
                MovingObjectPosition hitMOP = InfusionOtherwhere.raytraceUpBlocks(world, (EntityLivingBase)player, true, 8.0);
                double hikeModified = hitMOP == null ? 8.0 : Math.min((double)hitMOP.field_72312_c - otherLivingEntity.field_70163_u - 2.0, 8.0);
                MovingObjectPosition hitMOP2 = InfusionOtherwhere.raytraceUpBlocks(world, otherLivingEntity, true, 8.0);
                double d = hikeModified2 = hitMOP2 == null ? 8.0 : Math.min((double)hitMOP2.field_72312_c - otherLivingEntity.field_70163_u - 2.0, 8.0);
                if (player instanceof EntityPlayerMP && !InfusionOtherwhere.isConnectionClosed((EntityPlayerMP)player) && hikeModified > 0.0 && hikeModified2 > 0.0) {
                    ItemGeneral.teleportToLocation(world, player.field_70165_t, player.field_70163_u + hikeModified, player.field_70161_v, player.field_71093_bK, (Entity)player, true);
                    if (!PotionEnderInhibition.isActive((Entity)otherLivingEntity, 2)) {
                        ItemGeneral.teleportToLocation(world, otherLivingEntity.field_70165_t, otherLivingEntity.field_70163_u + hikeModified2, otherLivingEntity.field_70161_v, otherLivingEntity.field_71093_bK, (Entity)otherLivingEntity, true);
                    }
                }
            }
        }
    }

    @Override
    public void onUsingItemTick(ItemStack itemstack, World world, EntityPlayer player, int countdown) {
        int elapsedTicks = this.getMaxItemUseDuration(itemstack) - countdown;
        if (player.func_70093_af() && elapsedTicks == 60) {
            if (!world.field_72995_K) {
                ChatUtil.sendTranslated(EnumChatFormatting.GRAY, (ICommandSender)player, "witchery.infuse.cansetrecall", new Object[0]);
            }
            player.field_70170_p.func_72956_a((Entity)player, "note.pling", 0.5f, 0.4f / ((float)Math.random() * 0.4f + 0.8f));
        } else if (!player.func_70093_af() && elapsedTicks > 0 && elapsedTicks % 20 == 0) {
            int MAX_TELEPORT_DISTANCE = 40 + 20 * (elapsedTicks / 20);
            MovingObjectPosition hitMOP = InfusionOtherwhere.doCustomRayTrace(world, player, true, MAX_TELEPORT_DISTANCE);
            if (hitMOP != null) {
                player.field_70170_p.func_72956_a((Entity)player, "random.orb", 0.5f, 0.4f / ((float)Math.random() * 0.4f + 0.8f));
                if (!world.field_72995_K) {
                    ChatUtil.sendTranslated(EnumChatFormatting.GRAY, (ICommandSender)player, "witchery.infuse.canteleport", new Object[0]);
                }
            } else {
                player.field_70170_p.func_72956_a((Entity)player, "random.pop", 0.5f, 0.4f / ((float)Math.random() * 0.4f + 0.8f));
            }
        }
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int countdown) {
        if (world.field_72995_K) {
            return;
        }
        int elapsedTicks = this.getMaxItemUseDuration(itemstack) - countdown;
        if (player.func_70093_af() && elapsedTicks >= 60) {
            this.storeLocation(InfusionOtherwhere.getNBT((Entity)player), RECALL_LOCATON_KEY, player);
            SoundEffect.RANDOM_FIZZ.playAtPlayer(world, player);
        } else if (player.func_70093_af()) {
            DimensionalLocation recallLocation = this.recallLocation(InfusionOtherwhere.getNBT((Entity)player), RECALL_LOCATON_KEY);
            if (recallLocation != null && recallLocation.dimension != Config.instance().dimensionDreamID && recallLocation.dimension != Config.instance().dimensionTormentID && recallLocation.dimension != Config.instance().dimensionMirrorID && world.field_73011_w.field_76574_g != Config.instance().dimensionDreamID && world.field_73011_w.field_76574_g != Config.instance().dimensionTormentID && world.field_73011_w.field_76574_g != Config.instance().dimensionMirrorID && !PotionEnderInhibition.isActive((Entity)player, 2) && this.consumeCharges(world, player, 2, false)) {
                if (player instanceof EntityPlayerMP && !InfusionOtherwhere.isConnectionClosed((EntityPlayerMP)player)) {
                    player.field_70143_R = 0.0f;
                    ItemGeneral.teleportToLocation(world, recallLocation.posX, recallLocation.posY, recallLocation.posZ, recallLocation.dimension, (Entity)player, true);
                    Infusion.setCooldown(world, itemstack, 1500);
                }
            } else {
                world.func_72956_a((Entity)player, "note.snare", 0.5f, 0.4f / ((float)Math.random() * 0.4f + 0.8f));
            }
        } else {
            int MAX_TELEPORT_DISTANCE = 40 + 20 * (elapsedTicks / 20);
            MovingObjectPosition hitMOP = InfusionOtherwhere.doCustomRayTrace(world, player, true, MAX_TELEPORT_DISTANCE);
            if (hitMOP != null && !PotionEnderInhibition.isActive((Entity)player, 2) && this.consumeCharges(world, player, 1, false)) {
                ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, (Entity)player, 0.5, 2.0, 16);
                InfusionOtherwhere.teleportEntity(player, hitMOP);
                ParticleEffect.PORTAL.send(SoundEffect.MOB_ENDERMEN_PORTAL, (Entity)player, 0.5, 2.0, 16);
                Infusion.setCooldown(world, itemstack, 1500);
            } else {
                world.func_72956_a((Entity)player, "note.snare", 0.5f, 0.4f / ((float)Math.random() * 0.4f + 0.8f));
                if (hitMOP == null && !world.field_72995_K) {
                    ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)player, "witchery.infuse.cannotteleport", new Object[0]);
                }
            }
        }
    }

    private void storeLocation(NBTTagCompound nbt, String key, EntityPlayer player) {
        DimensionalLocation location = new DimensionalLocation((Entity)player);
        location.saveToNBT(nbt, key);
        if (!player.field_70170_p.field_72995_K) {
            ChatUtil.sendTranslated(EnumChatFormatting.GRAY, (ICommandSender)player, "witchery.infuse.setrecall", player.field_70170_p.field_73011_w.func_80007_l(), Integer.valueOf(MathHelper.func_76128_c((double)location.posX)).toString(), Integer.valueOf(MathHelper.func_76128_c((double)location.posY)).toString(), Integer.valueOf(MathHelper.func_76128_c((double)location.posZ)).toString());
        }
    }

    private DimensionalLocation recallLocation(NBTTagCompound nbtTag, String key) {
        DimensionalLocation location = new DimensionalLocation(nbtTag, key);
        if (!location.isValid) {
            return null;
        }
        return location;
    }

    public static void teleportEntity(EntityPlayer entityPlayer, MovingObjectPosition hitMOP) {
        EntityPlayerMP player;
        if (hitMOP != null && entityPlayer instanceof EntityPlayerMP && !InfusionOtherwhere.isConnectionClosed(player = (EntityPlayerMP)entityPlayer)) {
            switch (hitMOP.field_72313_a) {
                case ENTITY: {
                    player.func_70634_a(hitMOP.field_72307_f.field_72450_a, hitMOP.field_72307_f.field_72448_b, hitMOP.field_72307_f.field_72449_c);
                    break;
                }
                case BLOCK: {
                    double hitx = hitMOP.field_72307_f.field_72450_a;
                    double hity = hitMOP.field_72307_f.field_72448_b;
                    double hitz = hitMOP.field_72307_f.field_72449_c;
                    switch (hitMOP.field_72310_e) {
                        case 0: {
                            hity -= 2.0;
                            break;
                        }
                        case 1: {
                            break;
                        }
                        case 2: {
                            hitz -= 0.5;
                            break;
                        }
                        case 3: {
                            hitz += 0.5;
                            break;
                        }
                        case 4: {
                            hitx -= 0.5;
                            break;
                        }
                        case 5: {
                            hitx += 0.5;
                        }
                    }
                    player.field_70143_R = 0.0f;
                    player.func_70634_a(hitx, hity, hitz);
                    break;
                }
            }
        }
    }

    public static MovingObjectPosition doCustomRayTrace(World world, EntityPlayer player, boolean collisionFlag, double reachDistance) {
        MovingObjectPosition pickedBlock = InfusionOtherwhere.raytraceBlocks(world, player, collisionFlag, reachDistance);
        MovingObjectPosition pickedEntity = InfusionOtherwhere.raytraceEntities(world, player, collisionFlag, reachDistance);
        if (pickedBlock == null) {
            return pickedEntity;
        }
        if (pickedEntity == null) {
            return pickedBlock;
        }
        Vec3 playerPosition = Vec3.func_72443_a((double)player.field_70165_t, (double)(player.field_70163_u + (double)player.func_70047_e()), (double)player.field_70161_v);
        double dBlock = pickedBlock.field_72307_f.func_72438_d(playerPosition);
        double dEntity = pickedEntity.field_72307_f.func_72438_d(playerPosition);
        if (dEntity < dBlock) {
            return pickedEntity;
        }
        return pickedBlock;
    }

    public static MovingObjectPosition raytraceEntities(World world, EntityPlayer player, boolean collisionFlag, double reachDistance) {
        MovingObjectPosition pickedEntity = null;
        Vec3 playerPosition = Vec3.func_72443_a((double)player.field_70165_t, (double)(player.field_70163_u + (double)player.func_70047_e()), (double)player.field_70161_v);
        Vec3 playerLook = player.func_70040_Z();
        Vec3 playerViewOffset = Vec3.func_72443_a((double)(playerPosition.field_72450_a + playerLook.field_72450_a * reachDistance), (double)(playerPosition.field_72448_b + playerLook.field_72448_b * reachDistance), (double)(playerPosition.field_72449_c + playerLook.field_72449_c * reachDistance));
        double playerBorder = 1.1 * reachDistance;
        AxisAlignedBB boxToScan = player.field_70121_D.func_72314_b(playerBorder, playerBorder, playerBorder);
        List entitiesHit = world.func_72839_b((Entity)player, boxToScan);
        double closestEntity = reachDistance;
        if (entitiesHit == null || entitiesHit.isEmpty()) {
            return null;
        }
        for (Entity entityHit : entitiesHit) {
            float border;
            AxisAlignedBB aabb;
            MovingObjectPosition hitMOP;
            if (entityHit == null || !entityHit.func_70067_L() || entityHit.field_70121_D == null || (hitMOP = (aabb = entityHit.field_70121_D.func_72314_b((double)(border = entityHit.func_70111_Y()), (double)border, (double)border)).func_72327_a(playerPosition, playerViewOffset)) == null) continue;
            if (aabb.func_72318_a(playerPosition)) {
                if (!(0.0 < closestEntity) && closestEntity != 0.0 || (pickedEntity = new MovingObjectPosition(entityHit)) == null) continue;
                pickedEntity.field_72307_f = hitMOP.field_72307_f;
                closestEntity = 0.0;
                continue;
            }
            double distance = playerPosition.func_72438_d(hitMOP.field_72307_f);
            if (!(distance < closestEntity) && closestEntity != 0.0) continue;
            pickedEntity = new MovingObjectPosition(entityHit);
            pickedEntity.field_72307_f = hitMOP.field_72307_f;
            closestEntity = distance;
        }
        return pickedEntity;
    }

    private static boolean isConnectionClosed(EntityPlayerMP player) {
        return !player.field_71135_a.field_147371_a.func_150724_d();
    }

    public static MovingObjectPosition raytraceBlocks(World world, EntityPlayer player, boolean collisionFlag, double reachDistance) {
        Vec3 playerPosition = Vec3.func_72443_a((double)player.field_70165_t, (double)(player.field_70163_u + (double)player.func_70047_e()), (double)player.field_70161_v);
        Vec3 playerLook = player.func_70040_Z();
        Vec3 playerViewOffset = Vec3.func_72443_a((double)(playerPosition.field_72450_a + playerLook.field_72450_a * reachDistance), (double)(playerPosition.field_72448_b + playerLook.field_72448_b * reachDistance), (double)(playerPosition.field_72449_c + playerLook.field_72449_c * reachDistance));
        return world.func_147447_a(playerPosition, playerViewOffset, collisionFlag, !collisionFlag, false);
    }

    private static MovingObjectPosition raytraceUpBlocks(World world, EntityLivingBase player, boolean collisionFlag, double reachDistance) {
        Vec3 playerPosition = Vec3.func_72443_a((double)player.field_70165_t, (double)(player.field_70163_u + (double)player.func_70047_e()), (double)player.field_70161_v);
        Vec3 playerUp = Vec3.func_72443_a((double)0.0, (double)1.0, (double)0.0);
        Vec3 playerViewOffset = Vec3.func_72443_a((double)(playerPosition.field_72450_a + playerUp.field_72450_a * reachDistance), (double)(playerPosition.field_72448_b + playerUp.field_72448_b * reachDistance), (double)(playerPosition.field_72449_c + playerUp.field_72449_c * reachDistance));
        return world.func_147447_a(playerPosition, playerViewOffset, collisionFlag, !collisionFlag, false);
    }
}

