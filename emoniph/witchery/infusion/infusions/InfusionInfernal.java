/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIAttackOnCollide
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.monster.EntityGhast
 *  net.minecraft.entity.monster.EntitySpider
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingFallEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 */
package com.emoniph.witchery.infusion.infusions;

import com.emoniph.witchery.brewing.potions.PotionEnslaved;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.infusion.infusions.InfusionOtherwhere;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePower;
import com.emoniph.witchery.util.BlockSide;
import com.emoniph.witchery.util.EntityUtil;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.lang.reflect.Field;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class InfusionInfernal
extends Infusion {
    private static final int MAX_CHARGES = 20;

    public InfusionInfernal(int infusionID) {
        super(infusionID);
    }

    @Override
    public IIcon getPowerBarIcon(EntityPlayer player, int index) {
        return Blocks.field_150424_aL.func_149691_a(0, 0);
    }

    @Override
    public void onLeftClickEntity(ItemStack itemstack, World world, EntityPlayer player, Entity otherEntity) {
        if (!world.field_72995_K && otherEntity instanceof EntityLivingBase) {
            EntityLivingBase entityLivingBase = (EntityLivingBase)otherEntity;
            if (player.func_70093_af()) {
                if (PotionEnslaved.canCreatureBeEnslaved(entityLivingBase)) {
                    EntityLiving entityLiving = (EntityLiving)entityLivingBase;
                    if (PotionEnslaved.isMobEnslavedBy(entityLiving, player)) {
                        if (this.consumeCharges(world, player, 1, true)) {
                            this.trySacrificeCreature(world, player, entityLiving);
                        }
                    } else if (this.consumeCharges(world, player, 5, true)) {
                        PotionEnslaved.setEnslaverForMob(entityLiving, player);
                        EntityUtil.dropAttackTarget((EntityLiving)otherEntity);
                        ParticleEffect.SPELL.send(SoundEffect.MOB_ZOMBIE_INFECT, (Entity)entityLiving, 1.0, 2.0, 16);
                    }
                } else {
                    SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
                }
            } else {
                int r = 50;
                if (this.consumeCharges(world, player, 1, true)) {
                    int minionCount = 0;
                    AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(player.field_70165_t - 50.0), (double)(player.field_70163_u - 15.0), (double)(player.field_70161_v - 50.0), (double)(player.field_70165_t + 50.0), (double)(player.field_70163_u + 15.0), (double)(player.field_70161_v + 50.0));
                    for (Object obj : world.func_72872_a(EntityLiving.class, bounds)) {
                        EntityLiving nearbyLivingEntity = (EntityLiving)obj;
                        if (!PotionEnslaved.isMobEnslavedBy(nearbyLivingEntity, player)) continue;
                        ++minionCount;
                        nearbyLivingEntity.func_70624_b(entityLivingBase);
                        if (nearbyLivingEntity instanceof EntityGhast) {
                            try {
                                EntityGhast ghastEntity = (EntityGhast)nearbyLivingEntity;
                                Field[] fields = EntityGhast.class.getDeclaredFields();
                                Field fieldTargetedEntity = fields[4];
                                fieldTargetedEntity.setAccessible(true);
                                fieldTargetedEntity.set(ghastEntity, entityLivingBase);
                                Field fieldAggroCooldown = fields[5];
                                fieldAggroCooldown.setAccessible(true);
                                fieldAggroCooldown.set(ghastEntity, 20000);
                            }
                            catch (IllegalAccessException e) {
                                Log.instance().warning(e, "Exception occurred setting ghast target.");
                            }
                            catch (Exception e) {
                                Log.instance().debug(String.format("Exception occurred setting ghast target. %s", e.toString()));
                            }
                        }
                        if (!(nearbyLivingEntity instanceof EntityCreature)) continue;
                        EntityCreature nearbyCreatureEntity = (EntityCreature)obj;
                        nearbyCreatureEntity.func_70784_b((Entity)entityLivingBase);
                        nearbyCreatureEntity.func_70604_c(entityLivingBase);
                        if (!(nearbyCreatureEntity instanceof EntityZombie) && !(nearbyCreatureEntity instanceof EntityCreeper)) continue;
                        nearbyCreatureEntity.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackOnCollide(nearbyCreatureEntity, entityLivingBase.getClass(), 1.0, false));
                    }
                    if (minionCount > 0) {
                        ParticleEffect.CRIT.send(SoundEffect.RANDOM_BREATH, (Entity)entityLivingBase, 0.5, 2.0, 16);
                    }
                }
            }
        }
    }

    private void trySacrificeCreature(World world, EntityPlayer player, EntityLiving creature) {
        CreaturePower power = CreaturePower.Registry.instance().get(creature);
        if (power != null) {
            int currentCreaturePowerID = CreaturePower.getCreaturePowerID(player);
            if (currentCreaturePowerID == power.getCreaturePowerID()) {
                int currentCharges = CreaturePower.getCreaturePowerCharges(player);
                CreaturePower.setCreaturePowerCharges(player, MathHelper.func_76128_c((double)Math.min(currentCharges + power.getChargesPerSacrifice(), 20)));
            } else {
                CreaturePower.setCreaturePowerID(player, power.getCreaturePowerID(), power.getChargesPerSacrifice());
            }
            InfusionInfernal.syncPlayer(world, player);
            creature.func_70097_a(DamageSource.func_76354_b((Entity)player, null), creature.func_110143_aJ() + 1.0f);
        } else {
            this.playFailSound(world, player);
        }
    }

    @Override
    public void onHurt(World worldObj, EntityPlayer player, LivingHurtEvent event) {
        int creaturePowerID = CreaturePower.getCreaturePowerID(player);
        if (creaturePowerID > 0) {
            CreaturePower.Registry.instance().get(creaturePowerID).onDamage(player.field_70170_p, player, event);
        }
    }

    @Override
    public void onFalling(World world, EntityPlayer player, LivingFallEvent event) {
        int creaturePowerID = CreaturePower.getCreaturePowerID(player);
        if (creaturePowerID > 0) {
            CreaturePower.Registry.instance().get(creaturePowerID).onFalling(world, player, event);
        }
    }

    @Override
    public void onUsingItemTick(ItemStack itemstack, World world, EntityPlayer player, int countdown) {
        int elapsedTicks = this.getMaxItemUseDuration(itemstack) - countdown;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int countdown) {
        block12: {
            MovingObjectPosition mop;
            int elapsedTicks;
            block13: {
                block14: {
                    if (world.field_72995_K) break block12;
                    elapsedTicks = this.getMaxItemUseDuration(itemstack) - countdown;
                    double MAX_TARGET_RANGE = 15.0;
                    mop = InfusionOtherwhere.doCustomRayTrace(world, player, true, 15.0);
                    if (!player.func_70093_af()) break block13;
                    if (mop == null) break block14;
                    switch (mop.field_72313_a) {
                        case ENTITY: {
                            this.playFailSound(world, player);
                            break;
                        }
                        case BLOCK: {
                            if (!BlockSide.TOP.isEqual(mop.field_72310_e)) break;
                            int minionCount = 0;
                            int r = 50;
                            AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(player.field_70165_t - 50.0), (double)(player.field_70163_u - 15.0), (double)(player.field_70161_v - 50.0), (double)(player.field_70165_t + 50.0), (double)(player.field_70163_u + 15.0), (double)(player.field_70161_v + 50.0));
                            for (Object obj : world.func_72872_a(EntityLiving.class, bounds)) {
                                EntityCreature creature2;
                                EntityLiving creature = (EntityLiving)obj;
                                EntityCreature entityCreature = creature2 = creature instanceof EntityCreature ? (EntityCreature)creature : null;
                                if (!PotionEnslaved.isMobEnslavedBy(creature, player)) continue;
                                ++minionCount;
                                creature.func_70624_b(null);
                                creature.func_70604_c(null);
                                if (creature2 != null) {
                                    creature2.func_70784_b(null);
                                }
                                if (!(creature instanceof EntitySpider) && creature.func_70661_as().func_75492_a((double)mop.field_72311_b, (double)(mop.field_72312_c + 1), (double)mop.field_72309_d, 1.0) || creature2 == null) continue;
                                creature2.func_70778_a(world.func_72844_a((Entity)creature, mop.field_72311_b, mop.field_72312_c + 1, mop.field_72309_d, 10.0f, true, false, false, true));
                            }
                            if (minionCount > 0) {
                                ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_POP, world, mop.field_72311_b, mop.field_72312_c + 1, mop.field_72309_d, 0.5, 2.0, 16);
                                break;
                            } else {
                                break;
                            }
                        }
                    }
                    break block12;
                }
                this.playFailSound(world, player);
                break block12;
            }
            int beastPowerID = CreaturePower.getCreaturePowerID(player);
            if (beastPowerID > 0) {
                CreaturePower power = CreaturePower.Registry.instance().get(beastPowerID);
                int chargesRequired = power.activateCost(world, player, elapsedTicks, mop);
                int currentCharges = CreaturePower.getCreaturePowerCharges(player);
                if (currentCharges - chargesRequired >= 0 && this.consumeCharges(world, player, 1, true)) {
                    power.onActivate(world, player, elapsedTicks, mop);
                    if (!player.field_71075_bZ.field_75098_d) {
                        CreaturePower.setCreaturePowerCharges(player, currentCharges - chargesRequired);
                        InfusionInfernal.syncPlayer(world, player);
                    }
                } else {
                    this.playFailSound(world, player);
                }
            } else {
                this.playFailSound(world, player);
            }
        }
    }
}

