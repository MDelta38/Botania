/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaublesApi
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.entity.player.ItemTooltipEvent
 */
package thaumcraft.common.lib.events;

import baubles.api.BaublesApi;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.HashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IWarpingGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.entities.IEldritchMob;
import thaumcraft.common.config.Config;
import thaumcraft.common.entities.monster.mods.ChampionModifier;
import thaumcraft.common.items.armor.ItemFortressArmor;
import thaumcraft.common.items.baubles.ItemAmuletRunic;
import thaumcraft.common.items.baubles.ItemGirdleRunic;
import thaumcraft.common.items.baubles.ItemRingRunic;
import thaumcraft.common.items.wands.WandManager;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXShield;
import thaumcraft.common.lib.network.playerdata.PacketRunicCharge;
import thaumcraft.common.lib.utils.EntityUtils;

public class EventHandlerRunic {
    public HashMap<Integer, Integer> runicCharge = new HashMap();
    private HashMap<Integer, Long> nextCycle = new HashMap();
    private HashMap<Integer, Integer> lastCharge = new HashMap();
    public HashMap<Integer, Integer[]> runicInfo = new HashMap();
    private HashMap<String, Long> upgradeCooldown = new HashMap();
    public boolean isDirty = true;
    private int rechargeDelay = 0;

    @SubscribeEvent
    public void livingTick(LivingEvent.LivingUpdateEvent event) {
        if (!event.entity.field_70170_p.field_72995_K && event.entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.entity;
            if (this.isDirty || player.field_70173_aa % 40 == 0) {
                int max = 0;
                int charged = 0;
                int kinetic = 0;
                int healing = 0;
                int emergency = 0;
                this.isDirty = false;
                for (int a = 0; a < 4; ++a) {
                    if (player.field_71071_by.func_70440_f(a) == null || !(player.field_71071_by.func_70440_f(a).func_77973_b() instanceof IRunicArmor)) continue;
                    int amount = EventHandlerRunic.getFinalCharge(player.field_71071_by.func_70440_f(a));
                    max += amount;
                }
                IInventory baubles = BaublesApi.getBaubles((EntityPlayer)player);
                for (int a = 0; a < 4; ++a) {
                    if (baubles.func_70301_a(a) == null || !(baubles.func_70301_a(a).func_77973_b() instanceof IRunicArmor)) continue;
                    int amount = EventHandlerRunic.getFinalCharge(baubles.func_70301_a(a));
                    if (baubles.func_70301_a(a).func_77973_b() instanceof ItemRingRunic) {
                        switch (baubles.func_70301_a(a).func_77960_j()) {
                            case 2: {
                                ++charged;
                                break;
                            }
                            case 3: {
                                ++healing;
                            }
                        }
                    } else if (baubles.func_70301_a(a).func_77973_b() instanceof ItemAmuletRunic && baubles.func_70301_a(a).func_77960_j() == 1) {
                        ++emergency;
                    } else if (baubles.func_70301_a(a).func_77973_b() instanceof ItemGirdleRunic && baubles.func_70301_a(a).func_77960_j() == 1) {
                        ++kinetic;
                    }
                    max += amount;
                }
                if (max > 0) {
                    int charge;
                    this.runicInfo.put(player.func_145782_y(), new Integer[]{max, charged, kinetic, healing, emergency});
                    if (this.runicCharge.containsKey(player.func_145782_y()) && (charge = this.runicCharge.get(player.func_145782_y()).intValue()) > max) {
                        this.runicCharge.put(player.func_145782_y(), max);
                        PacketHandler.INSTANCE.sendTo((IMessage)new PacketRunicCharge(player, (short)max, max), (EntityPlayerMP)player);
                    }
                } else {
                    this.runicInfo.remove(player.func_145782_y());
                    this.runicCharge.put(player.func_145782_y(), 0);
                    PacketHandler.INSTANCE.sendTo((IMessage)new PacketRunicCharge(player, (short)0, 0), (EntityPlayerMP)player);
                }
            }
            if (this.rechargeDelay > 0) {
                --this.rechargeDelay;
            } else if (this.runicInfo.containsKey(player.func_145782_y())) {
                if (!this.lastCharge.containsKey(player.func_145782_y())) {
                    this.lastCharge.put(player.func_145782_y(), -1);
                }
                if (!this.runicCharge.containsKey(player.func_145782_y())) {
                    this.runicCharge.put(player.func_145782_y(), 0);
                }
                if (!this.nextCycle.containsKey(player.func_145782_y())) {
                    this.nextCycle.put(player.func_145782_y(), 0L);
                }
                long time = System.currentTimeMillis();
                int charge = this.runicCharge.get(player.func_145782_y());
                if (charge > this.runicInfo.get(player.func_145782_y())[0]) {
                    charge = this.runicInfo.get(player.func_145782_y())[0];
                } else if (charge < this.runicInfo.get(player.func_145782_y())[0] && this.nextCycle.get(player.func_145782_y()) < time && WandManager.consumeVisFromInventory(player, new AspectList().add(Aspect.AIR, Config.shieldCost).add(Aspect.EARTH, Config.shieldCost))) {
                    long interval = Config.shieldRecharge - this.runicInfo.get(player.func_145782_y())[1] * 500;
                    this.nextCycle.put(player.func_145782_y(), time + interval);
                    this.runicCharge.put(player.func_145782_y(), ++charge);
                }
                if (this.lastCharge.get(player.func_145782_y()) != charge) {
                    PacketHandler.INSTANCE.sendTo((IMessage)new PacketRunicCharge(player, (short)charge, this.runicInfo.get(player.func_145782_y())[0]), (EntityPlayerMP)player);
                    this.lastCharge.put(player.func_145782_y(), charge);
                }
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @SubscribeEvent
    public void entityHurt(LivingHurtEvent event) {
        if (event.source.func_76364_f() != null && event.source.func_76364_f() instanceof EntityPlayer) {
            EntityPlayer leecher = (EntityPlayer)event.source.func_76364_f();
            ItemStack helm = leecher.field_71071_by.field_70460_b[3];
            if (helm != null && helm.func_77973_b() instanceof ItemFortressArmor && helm.func_77942_o() && helm.field_77990_d.func_74764_b("mask") && helm.field_77990_d.func_74762_e("mask") == 2 && leecher.field_70170_p.field_73012_v.nextFloat() < event.ammount / 12.0f) {
                leecher.func_70691_i(1.0f);
            }
        }
        if (event.entity instanceof EntityPlayer) {
            long time = System.currentTimeMillis();
            EntityPlayer player = (EntityPlayer)event.entity;
            if (event.source.func_76364_f() != null && event.source.func_76364_f() instanceof EntityLivingBase) {
                EntityLivingBase attacker = (EntityLivingBase)event.source.func_76364_f();
                ItemStack helm = player.field_71071_by.field_70460_b[3];
                if (helm != null && helm.func_77973_b() instanceof ItemFortressArmor && helm.func_77942_o() && helm.field_77990_d.func_74764_b("mask") && helm.field_77990_d.func_74762_e("mask") == 1 && player.field_70170_p.field_73012_v.nextFloat() < event.ammount / 10.0f) {
                    try {
                        attacker.func_70690_d(new PotionEffect(Potion.field_82731_v.func_76396_c(), 80));
                    }
                    catch (Exception e) {
                        // empty catch block
                    }
                }
            }
            if (event.source == DamageSource.field_76369_e || event.source == DamageSource.field_82727_n || event.source == DamageSource.field_76380_i || event.source == DamageSource.field_76366_f) {
                return;
            }
            if (this.runicInfo.containsKey(player.func_145782_y()) && this.runicCharge.containsKey(player.func_145782_y()) && this.runicCharge.get(player.func_145782_y()) > 0) {
                int target = -1;
                if (event.source.func_76346_g() != null) {
                    target = event.source.func_76346_g().func_145782_y();
                }
                if (event.source == DamageSource.field_76379_h) {
                    target = -2;
                }
                if (event.source == DamageSource.field_82729_p) {
                    target = -3;
                }
                PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXShield(event.entity.func_145782_y(), target), new NetworkRegistry.TargetPoint(event.entity.field_70170_p.field_73011_w.field_76574_g, event.entity.field_70165_t, event.entity.field_70163_u, event.entity.field_70161_v, 64.0));
                int charge = this.runicCharge.get(player.func_145782_y());
                if ((float)charge > event.ammount) {
                    charge = (int)((float)charge - event.ammount);
                    event.ammount = 0.0f;
                } else {
                    event.ammount -= (float)charge;
                    charge = 0;
                }
                String key = player.func_145782_y() + ":" + 2;
                if (!(charge > 0 || this.runicInfo.get(player.func_145782_y())[2] <= 0 || this.upgradeCooldown.containsKey(key) && this.upgradeCooldown.get(key) >= time)) {
                    this.upgradeCooldown.put(key, time + 20000L);
                    player.field_70170_p.func_72885_a((Entity)player, player.field_70165_t, player.field_70163_u + (double)(player.field_70131_O / 2.0f), player.field_70161_v, 1.5f + (float)this.runicInfo.get(player.func_145782_y())[2].intValue() * 0.5f, false, false);
                }
                key = player.func_145782_y() + ":" + 3;
                if (!(charge > 0 || this.runicInfo.get(player.func_145782_y())[3] <= 0 || this.upgradeCooldown.containsKey(key) && this.upgradeCooldown.get(key) >= time)) {
                    this.upgradeCooldown.put(key, time + 20000L);
                    EntityPlayer entityPlayer = player;
                    synchronized (entityPlayer) {
                        try {
                            player.func_70690_d(new PotionEffect(Potion.field_76428_l.field_76415_H, 240, this.runicInfo.get(player.func_145782_y())[3].intValue()));
                        }
                        catch (Exception e) {
                            // empty catch block
                        }
                    }
                    player.field_70170_p.func_72956_a((Entity)player, "thaumcraft:runicShieldEffect", 1.0f, 1.0f);
                }
                key = player.func_145782_y() + ":" + 4;
                if (!(charge > 0 || this.runicInfo.get(player.func_145782_y())[4] <= 0 || this.upgradeCooldown.containsKey(key) && this.upgradeCooldown.get(key) >= time)) {
                    this.upgradeCooldown.put(key, time + 60000L);
                    int t = 8 * this.runicInfo.get(player.func_145782_y())[4];
                    charge = Math.min(this.runicInfo.get(player.func_145782_y())[0], t);
                    this.isDirty = true;
                    player.field_70170_p.func_72956_a((Entity)player, "thaumcraft:runicShieldCharge", 1.0f, 1.0f);
                }
                if (charge <= 0) {
                    this.rechargeDelay = Config.shieldWait;
                }
                this.runicCharge.put(player.func_145782_y(), charge);
                PacketHandler.INSTANCE.sendTo((IMessage)new PacketRunicCharge(player, (short)charge, this.runicInfo.get(player.func_145782_y())[0]), (EntityPlayerMP)player);
            }
        } else if (event.entity instanceof EntityMob && (((EntityMob)event.entity).func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e() >= 0.0 || event.entity instanceof IEldritchMob)) {
            EntityMob mob = (EntityMob)event.entity;
            int t = (int)((EntityMob)event.entity).func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e();
            if ((t == 5 || event.entity instanceof IEldritchMob) && mob.func_110139_bj() > 0.0f) {
                int target = -1;
                if (event.source.func_76346_g() != null) {
                    target = event.source.func_76346_g().func_145782_y();
                }
                if (event.source == DamageSource.field_76379_h) {
                    target = -2;
                }
                if (event.source == DamageSource.field_82729_p) {
                    target = -3;
                }
                PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXShield(mob.func_145782_y(), target), new NetworkRegistry.TargetPoint(event.entity.field_70170_p.field_73011_w.field_76574_g, event.entity.field_70165_t, event.entity.field_70163_u, event.entity.field_70161_v, 32.0));
                event.entity.field_70170_p.func_72908_a(event.entity.field_70165_t, event.entity.field_70163_u, event.entity.field_70161_v, "thaumcraft:runicShieldEffect", 0.66f, 1.1f + event.entity.field_70170_p.field_73012_v.nextFloat() * 0.1f);
            } else if (t >= 0 && ChampionModifier.mods[t].type == 2 && event.source.func_76364_f() != null && event.source.func_76364_f() instanceof EntityLivingBase) {
                EntityLivingBase attacker = (EntityLivingBase)event.source.func_76364_f();
                event.ammount = ChampionModifier.mods[t].effect.performEffect((EntityLivingBase)mob, attacker, event.source, event.ammount);
            }
        }
        if (event.ammount > 0.0f && event.source.func_76364_f() != null && event.entity instanceof EntityLivingBase && event.source.func_76364_f() instanceof EntityMob && ((EntityMob)event.source.func_76364_f()).func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e() >= 0.0) {
            EntityMob mob = (EntityMob)event.source.func_76364_f();
            int t = (int)mob.func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e();
            if (ChampionModifier.mods[t].type == 1) {
                event.ammount = ChampionModifier.mods[t].effect.performEffect((EntityLivingBase)mob, (EntityLivingBase)event.entity, event.source, event.ammount);
            }
        }
    }

    @SubscribeEvent
    public void tooltipEvent(ItemTooltipEvent event) {
        int warp;
        int charge = EventHandlerRunic.getFinalCharge(event.itemStack);
        if (charge > 0) {
            event.toolTip.add(EnumChatFormatting.GOLD + StatCollector.func_74838_a((String)"item.runic.charge") + " +" + charge);
        }
        if ((warp = EventHandlerRunic.getFinalWarp(event.itemStack, event.entityPlayer)) > 0) {
            event.toolTip.add(EnumChatFormatting.DARK_PURPLE + StatCollector.func_74838_a((String)"item.warping") + " " + warp);
        }
    }

    public static int getFinalCharge(ItemStack stack) {
        if (!(stack.func_77973_b() instanceof IRunicArmor)) {
            return 0;
        }
        IRunicArmor armor = (IRunicArmor)stack.func_77973_b();
        int base = armor.getRunicCharge(stack);
        if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("RS.HARDEN")) {
            base += stack.field_77990_d.func_74771_c("RS.HARDEN");
        }
        return base;
    }

    public static int getFinalWarp(ItemStack stack, EntityPlayer player) {
        if (stack == null || !(stack.func_77973_b() instanceof IWarpingGear)) {
            return 0;
        }
        IWarpingGear armor = (IWarpingGear)stack.func_77973_b();
        return armor.getWarp(stack, player);
    }

    public static int getHardening(ItemStack stack) {
        if (!(stack.func_77973_b() instanceof IRunicArmor)) {
            return 0;
        }
        int base = 0;
        if (stack.func_77942_o() && stack.field_77990_d.func_74764_b("RS.HARDEN")) {
            base += stack.field_77990_d.func_74771_c("RS.HARDEN");
        }
        return base;
    }
}

