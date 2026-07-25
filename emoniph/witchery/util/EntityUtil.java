/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EntityTracker
 *  net.minecraft.entity.EntityTrackerEntry
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAITasks$EntityAITaskEntry
 *  net.minecraft.entity.boss.EntityDragon
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.monster.EntityGhast
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EntityDamageSource
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.util.FakePlayerFactory
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 */
package com.emoniph.witchery.util;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.entity.EntityHornedHuntsman;
import com.emoniph.witchery.entity.ai.EntityAIAttackOnCollide2;
import com.emoniph.witchery.network.PacketPushTarget;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.EntityPosition;
import com.emoniph.witchery.util.IHandleDT;
import com.emoniph.witchery.util.Log;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.ReflectionHelper;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.EntityTrackerEntry;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class EntityUtil {
    private static Field fieldTrackedEntities = null;
    public static Field fieldGhastTargetedEntity;
    public static Field fieldGhastAggroCooldown;

    private EntityUtil() {
    }

    public static EntityPlayer playerOrFake(World world, String thrower) {
        return EntityUtil.playerOrFake(world, world != null ? world.func_72924_a(thrower) : null);
    }

    public static EntityPlayer playerOrFake(World world, EntityLivingBase entity) {
        if (entity != null && entity instanceof EntityPlayer) {
            return (EntityPlayer)entity;
        }
        if (world == null || !(world instanceof WorldServer)) {
            return null;
        }
        return FakePlayerFactory.getMinecraft((WorldServer)((WorldServer)world));
    }

    public static <T extends Entity> T findNearestEntityWithinAABB(World world, Class<T> clazz, AxisAlignedBB bounds, Entity entity) {
        Entity foundEntity = world.func_72857_a(clazz, bounds, entity);
        if (foundEntity != null) {
            return (T)foundEntity;
        }
        return null;
    }

    public static void spawnEntityInWorld(World world, Entity entity) {
        if (entity != null && world != null && !world.field_72995_K) {
            world.func_72838_d(entity);
        }
    }

    public static void correctProjectileTrackerSync(World world, Entity projectile) {
        block6: {
            if (!world.field_72995_K && world instanceof WorldServer) {
                try {
                    if (fieldTrackedEntities == null) {
                        fieldTrackedEntities = ReflectionHelper.findField(EntityTracker.class, (String[])new String[]{"trackedEntities", "field_72793_b", "b"});
                    }
                    if (fieldTrackedEntities == null) break block6;
                    EntityTracker tracker = ((WorldServer)world).func_73039_n();
                    Set trackedEntities = (Set)fieldTrackedEntities.get(tracker);
                    for (EntityTrackerEntry next : trackedEntities) {
                        if (next.field_73132_a != projectile) continue;
                        next.field_73136_m = 1;
                        break;
                    }
                }
                catch (IllegalAccessException e) {
                    Log.instance().warning(e, "Exception occurred setting entity tracking for bolt.");
                }
                catch (Exception e) {
                    Log.instance().debug(String.format("Exception occurred setting entity tracking for bolt. %s", e.toString()));
                }
            }
        }
    }

    public static void push(World world, Entity entity, EntityPosition position, double power) {
        Entity entity2 = entity;
        double d = position.x - entity2.field_70165_t;
        double d1 = position.y - entity2.field_70163_u;
        double d2 = position.z - entity2.field_70161_v;
        double d4 = d * d + d1 * d1 + d2 * d2;
        if ((d4 *= d4) <= Math.pow(6.0, 4.0)) {
            double d5 = -(d * 0.01999999955296516 / d4) * Math.pow(6.0, 3.0);
            double d6 = -(d1 * 0.01999999955296516 / d4) * Math.pow(6.0, 3.0);
            double d7 = -(d2 * 0.01999999955296516 / d4) * Math.pow(6.0, 3.0);
            if (d5 > 0.0) {
                d5 = 0.22;
            } else if (d5 < 0.0) {
                d5 = -0.22;
            }
            if (d6 > 0.2) {
                d6 = 0.12;
            } else if (d6 < -0.1) {
                d6 = 0.12;
            }
            if (d7 > 0.0) {
                d7 = 0.22;
            } else if (d7 < 0.0) {
                d7 = -0.22;
            }
            entity2.field_70159_w += d5 * power;
            entity2.field_70181_x += d6 * (power / 3.0);
            entity2.field_70179_y += d7 * power;
        }
    }

    public static void pullTowards(World world, Entity entity, EntityPosition target, double dy, double yy) {
        if (entity instanceof EntityDragon || entity instanceof EntityHornedHuntsman || target.occupiedBy(entity)) {
            return;
        }
        double d = target.x - entity.field_70165_t;
        double d1 = target.y - entity.field_70163_u;
        double d2 = target.z - entity.field_70161_v;
        float distance = MathHelper.func_76133_a((double)(d * d + d1 * d1 + d2 * d2));
        if ((double)distance < 0.01) {
            return;
        }
        float f2 = 0.1f + (float)dy;
        double mx = d / (double)distance * (double)f2 * (double)distance;
        double my = yy == 0.0 ? 0.4 : d1 / (double)distance * (double)distance * 0.2 + 0.2 + yy;
        double mz = d2 / (double)distance * (double)f2 * (double)distance;
        if (entity instanceof EntityLivingBase) {
            ((EntityLivingBase)entity).func_70690_d(new PotionEffect(Potion.field_76430_j.field_76415_H, 20, 1));
        }
        if (entity instanceof EntityPlayer) {
            Witchery.packetPipeline.sendTo((IMessage)new PacketPushTarget(mx, my, mz), (EntityPlayer)entity);
        } else {
            entity.field_70159_w = mx;
            entity.field_70181_x = my;
            entity.field_70179_y = mz;
        }
    }

    public static void pushback(World world, Entity entity, EntityPosition hit, double xyScale, double ySpeed) {
        double d = hit.x - entity.field_70165_t;
        double d1 = hit.y - entity.field_70163_u;
        double d2 = hit.z - entity.field_70161_v;
        Vec3 vec = Vec3.func_72443_a((double)d, (double)d1, (double)d2).func_72432_b();
        double dx = -vec.field_72450_a * xyScale;
        double dy = Math.max(-vec.field_72448_b, ySpeed);
        double dz = -vec.field_72449_c * xyScale;
        if (entity instanceof EntityPlayer) {
            Witchery.packetPipeline.sendTo((IMessage)new PacketPushTarget(dx, dy, dz), (EntityPlayer)entity);
        } else {
            entity.field_70159_w = dx;
            entity.field_70181_x = dy;
            entity.field_70179_y = dz;
        }
    }

    public static <T extends Entity> List<T> getEntitiesInRadius(Class<T> clazz, TileEntity tile, double radius) {
        return EntityUtil.getEntitiesInRadius(clazz, tile.func_145831_w(), 0.5 + (double)tile.field_145851_c, 0.5 + (double)tile.field_145848_d, 0.5 + (double)tile.field_145849_e, radius);
    }

    public static <T extends Entity> List<T> getEntitiesInRadius(Class<T> clazz, World world, double x, double y, double z, double radius) {
        AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(x - radius), (double)(y - radius), (double)(z - radius), (double)(x + radius), (double)(y + radius), (double)(z + radius));
        List entities = world.func_72872_a(clazz, bounds);
        ArrayList<Entity> nearbyEntities = new ArrayList<Entity>();
        double radiusSq = radius * radius;
        for (Entity entity : entities) {
            if (!(entity.func_70092_e(x, entity.field_70163_u, z) <= radiusSq)) continue;
            nearbyEntities.add(entity);
        }
        return nearbyEntities;
    }

    public static void setTarget(EntityLiving attacker, EntityLivingBase victim) {
        attacker.func_70624_b(victim);
        if (attacker instanceof EntityGhast) {
            try {
                EntityGhast ghastEntity = (EntityGhast)attacker;
                if (fieldGhastTargetedEntity == null) {
                    fieldGhastTargetedEntity = ReflectionHelper.findField(EntityGhast.class, (String[])new String[]{"targetedEntity", "field_70792_g", "g"});
                }
                fieldGhastTargetedEntity.set(ghastEntity, victim);
                if (fieldGhastAggroCooldown == null) {
                    fieldGhastAggroCooldown = ReflectionHelper.findField(EntityGhast.class, (String[])new String[]{"aggroCooldown", "field_70798_h", "h"});
                }
                fieldGhastAggroCooldown.set(ghastEntity, 20000);
            }
            catch (IllegalAccessException e) {
                Log.instance().warning(e, "Exception occurred setting ghast target.");
            }
            catch (Exception e) {
                Log.instance().debug(String.format("Exception occurred setting ghast target. %s", e.toString()));
            }
        }
        if (attacker instanceof EntityCreature) {
            EntityCreature attackerCreature = (EntityCreature)attacker;
            attackerCreature.func_70784_b((Entity)victim);
            attackerCreature.func_70604_c(victim);
            if (attackerCreature instanceof EntityZombie || attackerCreature instanceof EntityCreeper) {
                boolean found = false;
                Class<?> victimClass = victim.getClass();
                for (Object obj : attackerCreature.field_70715_bh.field_75782_a) {
                    EntityAITasks.EntityAITaskEntry task = (EntityAITasks.EntityAITaskEntry)obj;
                    if (!(task.field_75733_a instanceof EntityAIAttackOnCollide2)) continue;
                    EntityAIAttackOnCollide2 ai = (EntityAIAttackOnCollide2)task.field_75733_a;
                    if (ai == null || !ai.appliesToClass(victimClass)) break;
                    found = true;
                    break;
                }
                if (!found) {
                    attacker.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackOnCollide2(attackerCreature, victimClass, 1.0, false));
                }
            }
        }
    }

    public static void dropAttackTarget(EntityLiving entity) {
        entity.func_70624_b(null);
        if (entity instanceof EntityCreature) {
            EntityCreature creatureEntity = (EntityCreature)entity;
            creatureEntity.func_70784_b(null);
            creatureEntity.func_70604_c(null);
        }
    }

    public static void syncInventory(EntityPlayer player) {
        if (player instanceof EntityPlayerMP) {
            ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
        }
    }

    public static void persistanceRequired(EntityLiving entity) {
        entity.func_110163_bv();
    }

    public static void setNoDrops(EntityLiving entity) {
        if (entity != null) {
            NBTTagCompound nbtEntity = entity.getEntityData();
            nbtEntity.func_74757_a("WITCNoDrops", true);
        }
    }

    public static boolean isNoDrops(EntityLivingBase entity) {
        if (entity == null || entity instanceof EntityPlayer) {
            return false;
        }
        NBTTagCompound nbtEntity = entity.getEntityData();
        return nbtEntity.func_74767_n("WITCNoDrops");
    }

    public static float getHealthAfterDamage(LivingHurtEvent event, float currentHealth, EntityLivingBase entity) {
        float j;
        if (event.source.func_76363_c()) {
            return currentHealth - event.ammount;
        }
        float damage = event.ammount;
        int i = 25 - entity.func_70658_aO();
        float f1 = damage * (float)i;
        damage = f1 / 25.0f;
        if (entity.func_70644_a(Potion.field_76429_m) && event.source != DamageSource.field_76380_i) {
            i = (entity.func_70660_b(Potion.field_76429_m).func_76458_c() + 1) * 5;
            j = 25 - i;
            f1 = damage * j;
            damage = f1 / 25.0f;
        }
        if (damage <= 0.0f) {
            damage = 0.0f;
        } else {
            i = EnchantmentHelper.func_77508_a((ItemStack[])entity.func_70035_c(), (DamageSource)event.source);
            if (i > 20) {
                i = 20;
            }
            if (i > 0 && i <= 20) {
                j = 25 - i;
                f1 = damage * j;
                damage = f1 / 25.0f;
            }
        }
        return currentHealth - damage;
    }

    public static void instantDeath(EntityLivingBase entity, EntityLivingBase attacker) {
        if (entity != null && entity.field_70170_p != null && !entity.field_70170_p.field_72995_K) {
            if (entity instanceof EntityLiving) {
                entity.func_70606_j(0.0f);
                if (attacker == null) {
                    entity.func_70645_a(DamageSource.field_76376_m);
                } else {
                    entity.func_70645_a((DamageSource)new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), (Entity)attacker));
                }
                entity.func_70106_y();
            } else if (entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer)entity;
                if (!player.field_71075_bZ.field_75098_d) {
                    if (player.func_70608_bn()) {
                        player.func_70999_a(true, true, false);
                    }
                    entity.func_70606_j(0.0f);
                    if (ExtendedPlayer.get(player).isVampire()) {
                        entity.func_70645_a((DamageSource)(attacker == null ? DamageSourceSunlight.SUN : new DamageSourceSunlight((Entity)attacker)));
                    } else {
                        entity.func_70645_a((DamageSource)new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), (Entity)attacker));
                    }
                }
            }
        }
    }

    public static boolean touchOfDeath(Entity victim, EntityLivingBase attacker, float damage) {
        if (victim != null && victim.func_85032_ar()) {
            return false;
        }
        if (victim != null && victim.field_70170_p != null && !victim.field_70170_p.field_72995_K) {
            if (victim instanceof EntityLiving) {
                EntityDamageSource source = new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), (Entity)attacker);
                EntityLiving creature = (EntityLiving)victim;
                float cap = 10000.0f;
                if (victim instanceof IHandleDT) {
                    cap = ((IHandleDT)victim).getCapDT((DamageSource)source, damage);
                    if (cap <= 0.0f) {
                        return false;
                    }
                    if (attacker instanceof EntityLiving) {
                        cap = Math.min(6.0f, cap);
                    }
                }
                creature.func_70097_a((DamageSource)source, 0.0f);
                creature.func_70606_j(Math.max(creature.func_110143_aJ() - Math.min(damage, cap), 0.0f));
                creature.func_70097_a((DamageSource)source, 0.0f);
            } else if (victim instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer)victim;
                if (!player.field_71075_bZ.field_75098_d) {
                    player.func_70606_j(Math.max(player.func_110143_aJ() - damage, 0.0f));
                    if (player.func_110143_aJ() <= 0.0f) {
                        if (attacker == null) {
                            player.func_70645_a(DamageSource.field_76376_m);
                        } else {
                            player.func_70645_a((DamageSource)new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), (Entity)attacker));
                        }
                    } else {
                        player.func_70097_a((DamageSource)new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), (Entity)attacker), 0.0f);
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean moveToBlockPositionAndUpdate(EntityLiving entity, int x, int y, int z, int maxDY) {
        World world = entity.field_70170_p;
        boolean done = false;
        int mod = 0;
        int sign = -1;
        while (!done && mod <= 2 * maxDY && y < 250 && y > 2) {
            if (BlockUtil.isNormalCube(world.func_147439_a(x, y, z)) && world.func_147437_c(x, y + 1, z) && world.func_147437_c(x, y + 2, z)) {
                done = true;
                continue;
            }
            y += ++mod * (sign *= -1);
        }
        if (done) {
            entity.func_70634_a(0.5 + (double)x, 1.05 + (double)y, 0.5 + (double)z);
        }
        return done;
    }

    public static class DamageSourceVampireFire
    extends DamageSource {
        public static final DamageSourceVampireFire SOURCE = new DamageSourceVampireFire();

        public DamageSourceVampireFire() {
            super("onFire");
            this.func_76348_h();
            this.func_82726_p();
        }
    }

    public static class DamageSourceSunlight
    extends EntityDamageSource {
        public static final DamageSourceSunlight SUN = new DamageSourceSunlight(null);

        public DamageSourceSunlight(Entity attacker) {
            super("sun", attacker);
            this.func_76348_h();
            this.func_82726_p();
        }

        public IChatComponent func_151519_b(EntityLivingBase p_151519_1_) {
            EntityLivingBase entitylivingbase1 = p_151519_1_.func_94060_bK();
            String s = "witchery:death.attack." + this.field_76373_n;
            String s1 = s + ".player";
            return entitylivingbase1 != null && StatCollector.func_94522_b((String)s1) ? new ChatComponentTranslation(s1, new Object[]{p_151519_1_.func_145748_c_(), entitylivingbase1.func_145748_c_()}) : new ChatComponentTranslation(s, new Object[]{p_151519_1_.func_145748_c_()});
        }
    }
}

