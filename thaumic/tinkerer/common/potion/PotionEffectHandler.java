/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$ServerTickEvent
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraftforge.event.entity.living.LivingAttackEvent
 */
package thaumic.tinkerer.common.potion;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.BlockForcefield;
import thaumic.tinkerer.common.potion.ModPotions;

public class PotionEffectHandler {
    public static HashMap<Entity, Long> airPotionHit = new HashMap();
    public static HashMap<Entity, Long> firePotionHit = new HashMap();

    @SubscribeEvent
    public void onLivingHurt(LivingAttackEvent e) {
        if (e.source.func_76364_f() instanceof EntityPlayer) {
            EntityPlayer p = (EntityPlayer)e.source.func_76364_f();
            if (p.func_70644_a(ModPotions.potionAir) && !p.field_70170_p.field_72995_K) {
                airPotionHit.put(e.entity, e.entity.field_70170_p.func_82737_E());
            }
            if (p.func_70644_a(ModPotions.potionFire) && !p.field_70170_p.field_72995_K) {
                firePotionHit.put(e.entity, e.entity.field_70170_p.func_82737_E());
            }
            if (p.func_70644_a(ModPotions.potionEarth) && !p.field_70170_p.field_72995_K) {
                boolean xAxis = Math.abs(e.entity.field_70161_v - p.field_70161_v) < Math.abs(e.entity.field_70165_t - p.field_70165_t);
                int centerX = (int)((e.entity.field_70165_t + p.field_70165_t) / 2.0);
                int centerY = (int)(p.field_70163_u + 2.0);
                int centerZ = (int)((e.entity.field_70161_v + p.field_70161_v) / 2.0);
                for (int i = -2; i < 3; ++i) {
                    for (int j = -2; j < 3; ++j) {
                        if (xAxis) {
                            if (!p.field_70170_p.func_147437_c(centerX, centerY + i, centerZ + j)) continue;
                            p.field_70170_p.func_147449_b(centerX, centerY + i, centerZ + j, ThaumicTinkerer.registry.getFirstBlockFromClass(BlockForcefield.class));
                            ThaumicTinkerer.tcProxy.blockSparkle(p.field_70170_p, centerX, centerY + i, centerZ + j, 100, 100);
                            continue;
                        }
                        if (!p.field_70170_p.func_147437_c(centerX + j, centerY + i, centerZ)) continue;
                        p.field_70170_p.func_147449_b(centerX + j, centerY + i, centerZ, ThaumicTinkerer.registry.getFirstBlockFromClass(BlockForcefield.class));
                        ThaumicTinkerer.tcProxy.blockSparkle(p.field_70170_p, centerX + j, centerY + i, centerZ, 100, 100);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent e) {
        if (e.player.func_70644_a(ModPotions.potionWater)) {
            int x = (int)(e.player.field_70165_t - 2.0);
            while ((double)x < e.player.field_70165_t + 2.0) {
                int y = (int)(e.player.field_70163_u - 2.0);
                while ((double)y < e.player.field_70163_u + 2.0) {
                    int z = (int)(e.player.field_70161_v - 2.0);
                    while ((double)z < e.player.field_70161_v + 2.0) {
                        if (e.player.field_70170_p.func_147439_a(x, y, z) == Blocks.field_150353_l || e.player.field_70170_p.func_147439_a(x, y, z) == Blocks.field_150356_k) {
                            e.player.field_70170_p.func_147449_b(x, y, z, Blocks.field_150343_Z);
                            ThaumicTinkerer.tcProxy.burst(e.player.field_70170_p, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, 1.2f);
                        }
                        ++z;
                    }
                    ++y;
                }
                ++x;
            }
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ServerTickEvent e) {
        Random rand;
        Entity target;
        Iterator<Entity> iter = airPotionHit.keySet().iterator();
        while (iter.hasNext()) {
            target = iter.next();
            if (target.func_70089_S() && target.field_70170_p.func_82737_E() % 5L == 0L) {
                rand = new Random();
                target.func_70016_h((double)rand.nextFloat() - 0.5, (double)rand.nextFloat(), (double)rand.nextFloat() - 0.5);
                ThaumicTinkerer.tcProxy.burst(target.field_70170_p, target.field_70165_t, target.field_70163_u, target.field_70161_v, 0.5f);
            }
            if (target.field_70170_p.func_82737_E() <= airPotionHit.get(target) + 20L) continue;
            iter.remove();
        }
        iter = firePotionHit.keySet().iterator();
        while (iter.hasNext()) {
            target = iter.next();
            if (target.func_70089_S() && target.field_70170_p.func_82737_E() % 5L == 0L) {
                rand = new Random();
                target.func_70015_d(6);
                for (int i = 0; i < 30; ++i) {
                    double theta = (double)(rand.nextFloat() * 2.0f) * Math.PI;
                    double phi = (double)(rand.nextFloat() * 2.0f) * Math.PI;
                    double r = 2.5;
                    double x = r * Math.sin(theta) * Math.cos(phi);
                    double y = r * Math.sin(theta) * Math.sin(phi);
                    double z = r * Math.cos(theta);
                    ThaumicTinkerer.tcProxy.wispFX2(target.field_70170_p, target.field_70165_t + x, target.field_70163_u + y + 1.0, target.field_70161_v + z, 0.1f, 4, true, true, 1.0f);
                }
            }
            if (target.field_70170_p.func_82737_E() <= firePotionHit.get(target) + 6000L) continue;
            iter.remove();
        }
    }
}

