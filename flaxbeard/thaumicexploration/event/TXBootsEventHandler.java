/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.Vec3
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingJumpEvent
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.nodes.INode
 *  thaumcraft.api.potions.PotionFluxTaint
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.items.wands.ItemWandCasting
 *  thaumcraft.common.lib.world.ThaumcraftWorldGenerator
 *  thaumcraft.common.tiles.TileNode
 */
package flaxbeard.thaumicexploration.event;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.event.DamageSourceTX;
import flaxbeard.thaumicexploration.integration.TTIntegration;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.potions.PotionFluxTaint;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
import thaumcraft.common.tiles.TileNode;

public class TXBootsEventHandler {
    public static final PlayerCapabilities genericPlayerCapabilities = new PlayerCapabilities();
    HashMap<Integer, Float> prevStep = new HashMap();

    @SubscribeEvent
    public void livingTick(LivingEvent.LivingUpdateEvent event) {
        if (event.entity instanceof EntityPlayer) {
            // empty if block
        }
        if (event.entity instanceof EntityPlayer) {
            boolean wasTainted;
            EntityPlayer player = (EntityPlayer)event.entity;
            this.checkAir(player);
            if (player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() instanceof ItemWandCasting) {
                int nodeTicks;
                ItemWandCasting wand = (ItemWandCasting)player.func_70694_bm().func_77973_b();
                if (wand.getObjectInUse(player.func_70694_bm(), player.field_70170_p) != null && wand.getCap(player.func_70694_bm()) == ThaumicExploration.WAND_CAP_MECHANIST && wand.getObjectInUse(player.func_70694_bm(), player.field_70170_p) instanceof INode) {
                    if (!player.func_70694_bm().func_77942_o()) {
                        player.func_70694_bm().func_77982_d(new NBTTagCompound());
                    }
                    if (!player.func_70694_bm().field_77990_d.func_74764_b("nodeTicks")) {
                        player.func_70694_bm().field_77990_d.func_74768_a("nodeTicks", 0);
                    }
                    nodeTicks = player.func_70694_bm().field_77990_d.func_74762_e("nodeTicks");
                    TileNode node = (TileNode)wand.getObjectInUse(player.func_70694_bm(), player.field_70170_p);
                    node.onUsingWandTick(player.func_70694_bm(), player, ++nodeTicks);
                    if (nodeTicks % 3 == 0) {
                        if (player.field_70170_p.field_73012_v.nextBoolean()) {
                            player.field_70170_p.func_72980_b((double)node.field_145851_c, (double)node.field_145848_d, (double)node.field_145849_e, "tile.piston.in", 0.1f + (float)(0.5 * Math.random()), 0.75f, false);
                        } else {
                            player.field_70170_p.func_72980_b((double)node.field_145851_c, (double)node.field_145848_d, (double)node.field_145849_e, "tile.piston.out", 0.1f + (float)(0.5 * Math.random()), 0.75f, false);
                        }
                    }
                    player.func_70694_bm().field_77990_d.func_74768_a("nodeTicks", nodeTicks);
                }
                if (wand.getCap(player.func_70694_bm()) == ThaumicExploration.WAND_CAP_SOJOURNER && !player.field_70170_p.field_72995_K) {
                    if (!player.func_70694_bm().func_77942_o()) {
                        player.func_70694_bm().func_77982_d(new NBTTagCompound());
                    }
                    if (!player.func_70694_bm().field_77990_d.func_74764_b("nodeTicks")) {
                        player.func_70694_bm().field_77990_d.func_74768_a("nodeTicks", 0);
                        player.func_70694_bm().field_77990_d.func_74768_a("drainX", 0);
                        player.func_70694_bm().field_77990_d.func_74768_a("drainY", 0);
                        player.func_70694_bm().field_77990_d.func_74768_a("drainZ", 0);
                    }
                    nodeTicks = player.func_70694_bm().field_77990_d.func_74762_e("nodeTicks");
                    if (++nodeTicks >= 20) {
                        AspectList emptyAspects = new AspectList();
                        for (Aspect aspect : Aspect.getPrimalAspects()) {
                            if (wand.getVis(player.func_70694_bm(), aspect) >= wand.getMaxVis(player.func_70694_bm())) continue;
                            emptyAspects.add(aspect, 1);
                        }
                        ArrayList<ChunkCoordinates> nodes = new ArrayList<ChunkCoordinates>();
                        for (int xx = -8; xx <= 8; ++xx) {
                            for (int yy = -8; yy <= 8; ++yy) {
                                for (int zz = -8; zz <= 8; ++zz) {
                                    Aspect[] te = player.field_70170_p.func_147438_o((int)player.field_70165_t + xx, (int)player.field_70163_u + yy, (int)player.field_70161_v + zz);
                                    if (!(te instanceof INode) || emptyAspects.size() <= 0) continue;
                                    boolean canAdd = false;
                                    for (Aspect aspect : emptyAspects.getAspects()) {
                                        if (((INode)te).getAspects().getAmount(aspect) <= 1) continue;
                                        canAdd = true;
                                    }
                                    if (!canAdd) continue;
                                    nodes.add(new ChunkCoordinates((int)player.field_70165_t + xx, (int)player.field_70163_u + yy, (int)player.field_70161_v + zz));
                                }
                            }
                        }
                        if (nodes.size() != 0) {
                            ChunkCoordinates randNode;
                            if (!nodes.contains(new ChunkCoordinates(player.func_70694_bm().field_77990_d.func_74762_e("drainX"), player.func_70694_bm().field_77990_d.func_74762_e("drainY"), player.func_70694_bm().field_77990_d.func_74762_e("drainZ")))) {
                                randNode = (ChunkCoordinates)nodes.get(player.field_70170_p.field_73012_v.nextInt(nodes.size()));
                                player.func_70694_bm().field_77990_d.func_74768_a("drainX", randNode.field_71574_a);
                                player.func_70694_bm().field_77990_d.func_74768_a("drainY", randNode.field_71572_b);
                                player.func_70694_bm().field_77990_d.func_74768_a("drainZ", randNode.field_71573_c);
                            } else {
                                randNode = new ChunkCoordinates(new ChunkCoordinates(player.func_70694_bm().field_77990_d.func_74762_e("drainX"), player.func_70694_bm().field_77990_d.func_74762_e("drainY"), player.func_70694_bm().field_77990_d.func_74762_e("drainZ")));
                            }
                            INode node = (INode)player.field_70170_p.func_147438_o(randNode.field_71574_a, randNode.field_71572_b, randNode.field_71573_c);
                            AspectList possibleAspects = new AspectList();
                            for (Aspect aspect : emptyAspects.getAspects()) {
                                if (node.getAspects().getAmount(aspect) <= 1) continue;
                                possibleAspects.add(aspect, 1);
                            }
                            Aspect takeAspect = possibleAspects.getAspects()[player.field_70170_p.field_73012_v.nextInt(possibleAspects.getAspects().length)];
                            node.takeFromContainer(takeAspect, 1);
                            player.field_70170_p.func_147471_g(randNode.field_71574_a, randNode.field_71572_b, randNode.field_71573_c);
                            wand.addVis(player.func_70694_bm(), takeAspect, 1, true);
                        }
                        nodeTicks = 0;
                    }
                    player.func_70694_bm().field_77990_d.func_74768_a("nodeTicks", nodeTicks);
                }
            }
            boolean isTainted = false;
            for (int i = 0; i < 10; ++i) {
                if (player.field_71071_by.func_70301_a(i) == null || player.field_71071_by.func_70301_a(i).func_77973_b() != ThaumicExploration.charmTaint) continue;
                isTainted = true;
                break;
            }
            if (!player.getEntityData().func_74764_b("tainted")) {
                player.getEntityData().func_74757_a("tainted", isTainted);
            }
            if ((wasTainted = player.getEntityData().func_74767_n("tainted")) && !isTainted && !player.field_71075_bZ.field_75102_a) {
                player.func_70097_a(DamageSourceTX.noTaint, 999.0f);
            }
            if (!wasTainted && isTainted) {
                player.field_70170_p.func_72908_a(player.field_70165_t, player.field_70163_u, player.field_70161_v, "thaumcraft:zap", 1.0f, 1.0f);
            }
            player.getEntityData().func_74757_a("tainted", isTainted);
            if (player.field_70170_p.func_72807_a((int)player.field_70165_t, (int)player.field_70161_v) != ThaumcraftWorldGenerator.biomeTaint) {
                if (isTainted) {
                    if (!player.getEntityData().func_74764_b("taintGracePeriod")) {
                        player.getEntityData().func_74768_a("taintGracePeriod", 0);
                    }
                    int taintGP = player.getEntityData().func_74762_e("taintGracePeriod");
                    player.getEntityData().func_74768_a("taintGracePeriod", ++taintGP);
                    if (player.func_70660_b(ThaumicExploration.potionTaintWithdrawl) == null && taintGP > 100) {
                        for (int i = 0; i < 10; ++i) {
                            if (player.field_71071_by.func_70301_a(i) == null || player.field_71071_by.func_70301_a(i).func_77973_b() != ConfigItems.itemResource || player.field_71071_by.func_70301_a(i).func_77960_j() != 11 && player.field_71071_by.func_70301_a(i).func_77960_j() != 12) continue;
                            player.field_71071_by.func_70298_a(i, 1);
                            taintGP = 0;
                            player.getEntityData().func_74768_a("taintGracePeriod", 0);
                            break;
                        }
                        if (taintGP > 100) {
                            player.func_70690_d(new PotionEffect(ThaumicExploration.potionTaintWithdrawl.field_76415_H, 100, 1));
                        }
                    }
                }
            } else if (!player.field_70170_p.field_72995_K && isTainted) {
                player.getEntityData().func_74768_a("taintGracePeriod", 0);
                if (player.func_71024_bL().func_75116_a() < 4) {
                    if (!player.getEntityData().func_74764_b("taintFoodBuff")) {
                        player.getEntityData().func_74768_a("taintFoodBuff", 0);
                    }
                    int taint = player.getEntityData().func_74762_e("taintFoodBuff");
                    if (++taint > 80) {
                        player.func_71024_bL().func_75122_a(1, 0.0f);
                        taint = 0;
                        if (player.field_70170_p.field_72995_K) {
                            Thaumcraft.proxy.swarmParticleFX(player.field_70170_p, (Entity)player, 0.1f, 10.0f, 0.0f);
                        }
                    }
                    player.getEntityData().func_74768_a("taintFoodBuff", taint);
                } else {
                    if (!player.getEntityData().func_74764_b("taintFoodBuff")) {
                        player.getEntityData().func_74768_a("taintFoodBuff", 0);
                    }
                    player.getEntityData().func_74768_a("taintFoodBuff", 0);
                }
            }
            if (player.func_70660_b((Potion)PotionFluxTaint.instance) != null) {
                for (int i = 0; i < 10; ++i) {
                    if (player.field_71071_by.func_70301_a(i) == null || player.field_71071_by.func_70301_a(i).func_77973_b() != ThaumicExploration.charmTaint) continue;
                    player.func_82170_o(PotionFluxTaint.instance.field_76415_H);
                    break;
                }
            }
        }
        if (event.entity.field_70170_p.field_72995_K && this.prevStep.containsKey(event.entity.func_145782_y()) && (((EntityPlayer)event.entity).field_71071_by.func_70440_f(0) == null || ((EntityPlayer)event.entity).field_71071_by.func_70440_f(0).func_77973_b() != ThaumicExploration.bootsMeteor && ((EntityPlayer)event.entity).field_71071_by.func_70440_f(0).func_77973_b() != ThaumicExploration.bootsComet)) {
            event.entity.field_70138_W = this.prevStep.get(event.entity.func_145782_y()).floatValue();
            this.prevStep.remove(event.entity.func_145782_y());
        }
    }

    @SubscribeEvent
    public void joinWorld(EntityJoinWorldEvent event) {
        if (event.entity instanceof EntityPlayer) {
            EntityPlayer entityPlayer = (EntityPlayer)event.entity;
        }
    }

    @SubscribeEvent
    public void playerJumps(LivingEvent.LivingJumpEvent event) {
        if (event.entity instanceof EntityPlayer && ((EntityPlayer)event.entity).field_71071_by.func_70440_f(0) != null && ((EntityPlayer)event.entity).field_71071_by.func_70440_f(0).func_77973_b() == ThaumicExploration.bootsMeteor) {
            if (((EntityPlayer)event.entity).func_70093_af()) {
                Vec3 vector = event.entityLiving.func_70676_i(0.5f);
                double total = Math.abs(vector.field_72449_c + vector.field_72450_a);
                EntityPlayer player = (EntityPlayer)event.entity;
                double jump = 0.0;
                if (Loader.isModLoaded((String)"ThaumicTinkerer")) {
                    jump = TTIntegration.getAscentLevel((EntityPlayer)event.entity);
                }
                if (jump >= 1.0) {
                    jump = (jump + 2.0) / 4.0;
                }
                if (vector.field_72448_b < total) {
                    vector.field_72448_b = total;
                }
                event.entityLiving.field_70181_x += (jump + 1.0) * vector.field_72448_b / 1.5;
                event.entityLiving.field_70179_y += (jump + 1.0) * vector.field_72449_c * 4.0;
                event.entityLiving.field_70159_w += (jump + 1.0) * vector.field_72450_a * 4.0;
            } else {
                event.entityLiving.field_70181_x += (double)0.275f;
            }
        } else if (event.entity instanceof EntityPlayer && ((EntityPlayer)event.entity).field_71071_by.func_70440_f(0) != null && ((EntityPlayer)event.entity).field_71071_by.func_70440_f(0).func_77973_b() == ThaumicExploration.bootsComet) {
            event.entityLiving.field_70181_x += (double)0.275f;
        }
    }

    public void checkAir(EntityPlayer player) {
        if (player.field_71071_by.func_70440_f(0) != null && player.field_71071_by.func_70440_f(0).func_77973_b() == ThaumicExploration.bootsMeteor) {
            Vec3 vector = player.func_70676_i(1.0f);
            ItemStack item = player.field_71071_by.func_70440_f(0);
            if (!item.func_77942_o()) {
                NBTTagCompound par1NBTTagCompound = new NBTTagCompound();
                item.func_77982_d(par1NBTTagCompound);
                item.field_77990_d.func_74757_a("IsSmashing", false);
                item.field_77990_d.func_74768_a("smashTicks", 0);
                item.field_77990_d.func_74768_a("airTicks", 0);
            }
            boolean smashing = item.field_77990_d.func_74767_n("IsSmashing");
            int ticks = item.field_77990_d.func_74762_e("smashTicks");
            int ticksAir = item.field_77990_d.func_74762_e("airTicks");
            if (player.field_70122_E || player.func_70617_f_()) {
                int size = 0;
                if (ticks > 5) {
                    size = 1;
                }
                if (ticks > 10) {
                    size = 2;
                }
                if (ticks > 15) {
                    size = 3;
                }
                smashing = false;
                ticks = 0;
                ticksAir = 0;
                if (size > 0) {
                    player.field_70170_p.func_72876_a((Entity)player, player.field_70165_t, player.field_70163_u, player.field_70161_v, (float)size, false);
                }
            }
            if (player.field_71075_bZ.field_75100_b) {
                smashing = false;
                ticks = 0;
                ticksAir = 0;
            }
            if (smashing) {
                player.field_70170_p.func_72869_a("flame", player.field_70165_t + Math.random() - 0.5, player.field_70163_u + Math.random() - 0.5, player.field_70161_v + Math.random() - 0.5, 0.0, 0.0, 0.0);
                player.field_70170_p.func_72869_a("smoke", player.field_70165_t + Math.random() - 0.5, player.field_70163_u + Math.random() - 0.5, player.field_70161_v + Math.random() - 0.5, 0.0, 0.0, 0.0);
                player.field_70170_p.func_72869_a("flame", player.field_70165_t + Math.random() - 0.5, player.field_70163_u + Math.random() - 0.5, player.field_70161_v + Math.random() - 0.5, 0.0, 0.0, 0.0);
                player.field_70181_x -= (double)0.1f;
                ++ticks;
            } else {
                double motion = Math.abs(player.field_70159_w) + Math.abs(player.field_70179_y) + Math.abs(0.5 * player.field_70181_x);
                if (!player.func_70026_G() && motion > (double)0.1f) {
                    player.field_70170_p.func_72869_a("flame", player.field_70165_t + Math.random() - 0.5, player.field_70121_D.field_72338_b + 0.25 + (Math.random() - 0.5) * 0.25, player.field_70161_v + Math.random() - 0.5, 0.0, 0.025, 0.0);
                    player.field_70170_p.func_72869_a("flame", player.field_70165_t + Math.random() - 0.5, player.field_70121_D.field_72338_b + 0.25 + (Math.random() - 0.5) * 0.25, player.field_70161_v + Math.random() - 0.5, 0.0, 0.025, 0.0);
                }
            }
            item.field_77990_d.func_74757_a("IsSmashing", smashing);
            item.field_77990_d.func_74768_a("smashTicks", ticks);
            item.field_77990_d.func_74768_a("airTicks", ticksAir);
        } else if (player.field_71071_by.func_70440_f(0) != null && player.field_71071_by.func_70440_f(0).func_77973_b() == ThaumicExploration.bootsComet) {
            Vec3 vector = player.func_70676_i(1.0f);
            ItemStack item = player.field_71071_by.func_70440_f(0);
            if (!item.func_77942_o()) {
                NBTTagCompound par1NBTTagCompound = new NBTTagCompound();
                item.func_77982_d(par1NBTTagCompound);
                item.field_77990_d.func_74768_a("runTicks", 0);
            }
            for (int x = -5; x < 6; ++x) {
                for (int z = -5; z < 6; ++z) {
                    if (player.field_70170_p.func_147439_a((int)(player.field_70165_t + (double)x), (int)(player.field_70163_u - 1.0), (int)(player.field_70161_v + (double)z)) != Blocks.field_150355_j && player.field_70170_p.func_147439_a((int)(player.field_70165_t + (double)x), (int)(player.field_70163_u - 1.0), (int)(player.field_70161_v + (double)z)) != Blocks.field_150355_j || player.field_70170_p.func_147439_a((int)(player.field_70165_t + (double)x), (int)player.field_70163_u - 1, (int)(player.field_70161_v + (double)z)).func_149688_o() != Material.field_151586_h || player.field_70170_p.func_72805_g((int)(player.field_70165_t + (double)x), (int)player.field_70163_u - 1, (int)(player.field_70161_v + (double)z)) != 0 || player.func_70090_H() || Math.abs(x) + Math.abs(z) >= 8) continue;
                    player.field_70170_p.func_147449_b((int)(player.field_70165_t + (double)x), (int)player.field_70163_u - 1, (int)(player.field_70161_v + (double)z), ThaumicExploration.meltyIce);
                    player.field_70170_p.func_72869_a("snowballpoof", (double)((int)(player.field_70165_t + (double)x)), (double)((int)player.field_70163_u), (double)((int)(player.field_70161_v + (double)z)), 0.0, 0.025, 0.0);
                }
            }
            int ticks = item.field_77990_d.func_74762_e("runTicks");
            double motion = Math.abs(player.field_70159_w) + Math.abs(player.field_70179_y) + Math.abs(player.field_70181_x);
            if (motion > (double)0.1f || !player.field_70122_E || player.func_70617_f_()) {
                if (ticks < 100) {
                    ++ticks;
                }
            } else {
                ticks = 0;
            }
            if (!player.func_70026_G() && motion > (double)0.1f) {
                player.field_70170_p.func_72869_a("snowballpoof", player.field_70165_t + Math.random() - 0.5, player.field_70121_D.field_72338_b + 0.25 + (Math.random() - 0.5) * 0.25, player.field_70161_v + Math.random() - 0.5, 0.0, 0.025, 0.0);
                player.field_70170_p.func_72869_a("snowballpoof", player.field_70165_t + Math.random() - 0.5, player.field_70121_D.field_72338_b + 0.25 + (Math.random() - 0.5) * 0.25, player.field_70161_v + Math.random() - 0.5, 0.0, 0.025, 0.0);
            }
            item.field_77990_d.func_74768_a("runTicks", ticks);
        }
    }
}

