/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.block.material.Material
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingJumpEvent
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  net.minecraftforge.event.entity.living.LivingFallEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$BreakSpeed
 *  net.minecraftforge.event.world.BlockEvent$BreakEvent
 */
package thaumic.tinkerer.common.enchantment;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import thaumic.tinkerer.common.lib.LibEnchantIDs;

public class ModEnchantmentHandler {
    public final String NBTLastTarget = "TTEnchantLastTarget";
    public final String NBTSuccessiveStrike = "TTEnchantSuccessiveStrike";
    public final String NBTTunnelDirection = "TTEnchantTunnelDir";

    @SubscribeEvent
    public void onEntityDamaged(LivingHurtEvent event) {
        if (event.source.func_76346_g() instanceof EntityLivingBase) {
            int vampirism;
            int valiance;
            Random rand;
            int finalStrike;
            EntityPlayer player;
            ItemStack legs;
            int pounce;
            EntityLivingBase attacker = (EntityLivingBase)event.source.func_76346_g();
            ItemStack heldItem = attacker.func_70694_bm();
            if (heldItem == null) {
                return;
            }
            if (attacker instanceof EntityPlayer && (pounce = EnchantmentHelper.func_77506_a((int)LibEnchantIDs.pounce, (ItemStack)(legs = (player = (EntityPlayer)attacker).func_82169_q(1)))) > 0 && player.field_70170_p.func_147439_a((int)Math.floor(player.field_70165_t), (int)Math.floor(player.field_70163_u) - 1, (int)Math.floor(player.field_70161_v)) == Blocks.field_150350_a) {
                event.ammount = (float)((double)event.ammount * (1.0 + 0.25 * (double)pounce));
            }
            if ((finalStrike = EnchantmentHelper.func_77506_a((int)LibEnchantIDs.finalStrike, (ItemStack)heldItem)) > 0 && (rand = new Random()).nextInt(20 - finalStrike) == 0) {
                event.ammount *= 3.0f;
            }
            if ((valiance = EnchantmentHelper.func_77506_a((int)LibEnchantIDs.valiance, (ItemStack)heldItem)) > 0 && attacker.func_110143_aJ() / attacker.func_110138_aP() < 0.5f) {
                event.ammount = (float)((double)event.ammount * (1.0 + 0.1 * (double)valiance));
            }
            int focusedStrikes = EnchantmentHelper.func_77506_a((int)LibEnchantIDs.focusedStrike, (ItemStack)heldItem);
            int dispersedStrikes = EnchantmentHelper.func_77506_a((int)LibEnchantIDs.dispersedStrikes, (ItemStack)heldItem);
            if (focusedStrikes > 0 || dispersedStrikes > 0) {
                if (heldItem.field_77990_d == null) {
                    heldItem.field_77990_d = new NBTTagCompound();
                }
                int lastTarget = heldItem.field_77990_d.func_74762_e("TTEnchantLastTarget");
                int successiveStrikes = heldItem.field_77990_d.func_74762_e("TTEnchantSuccessiveStrike");
                int entityId = event.entityLiving.func_145782_y();
                if (lastTarget != entityId) {
                    heldItem.field_77990_d.func_74768_a("TTEnchantSuccessiveStrike", 0);
                    successiveStrikes = 0;
                } else {
                    heldItem.field_77990_d.func_74768_a("TTEnchantSuccessiveStrike", successiveStrikes + 1);
                    successiveStrikes = 1;
                }
                if (focusedStrikes > 0) {
                    event.ammount /= 2.0f;
                    event.ammount = (float)((double)event.ammount + 0.5 * (double)successiveStrikes * (double)event.ammount * (double)focusedStrikes);
                }
                if (dispersedStrikes > 0) {
                    event.ammount *= (float)(1 + successiveStrikes / 5);
                    event.ammount /= (float)(1 + successiveStrikes * 2);
                }
                heldItem.field_77990_d.func_74768_a("TTEnchantLastTarget", entityId);
            }
            if ((vampirism = EnchantmentHelper.func_77506_a((int)LibEnchantIDs.idVampirism, (ItemStack)heldItem)) > 0) {
                attacker.func_70691_i((float)vampirism);
                event.entityLiving.field_70170_p.func_72956_a((Entity)event.entityLiving, "thaumcraft:zap", 0.6f, 1.0f);
            }
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
        double min = -0.0784000015258789;
        if (event.entityLiving instanceof EntityPlayer) {
            ItemStack heldItem;
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            int slowfall = EnchantmentHelper.func_77511_a((int)LibEnchantIDs.idSlowFall, (ItemStack[])player.field_71071_by.field_70460_b);
            if (slowfall > 0 && !event.entityLiving.func_70093_af() && event.entityLiving.field_70181_x < -0.0784000015258789 && (double)event.entityLiving.field_70143_R >= 2.9) {
                event.entityLiving.field_70181_x /= (double)(1.0f + (float)slowfall * 0.33f);
                event.entityLiving.field_70143_R = Math.max(2.9f, player.field_70143_R - (float)slowfall / 3.0f);
                player.field_70170_p.func_72869_a("cloud", player.field_70165_t + 0.25, player.field_70163_u - 1.0, player.field_70161_v + 0.25, -player.field_70159_w, player.field_70181_x, -player.field_70179_y);
            }
            if ((heldItem = player.func_70694_bm()) == null) {
                return;
            }
            int quickDraw = EnchantmentHelper.func_77506_a((int)LibEnchantIDs.idQuickDraw, (ItemStack)heldItem);
            ItemStack usingItem = player.field_71074_e;
            int time = player.field_71072_f;
            if (quickDraw > 0 && usingItem != null && usingItem.func_77973_b() instanceof ItemBow && (usingItem.func_77973_b().func_77626_a(usingItem) - time) % (6 - quickDraw) == 0) {
                player.field_71072_f = time - 1;
            }
        }
    }

    @SubscribeEvent
    public void onPlayerJump(LivingEvent.LivingJumpEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            int boost = EnchantmentHelper.func_77511_a((int)LibEnchantIDs.idAscentBoost, (ItemStack[])player.field_71071_by.field_70460_b);
            if (boost >= 1 && !player.func_70093_af()) {
                player.field_70181_x *= (double)(boost + 2) / 2.0;
            }
        }
    }

    @SubscribeEvent(priority=EventPriority.LOW)
    public void onFall(LivingFallEvent event) {
        ItemStack boots;
        int shockwave;
        if (event.entityLiving instanceof EntityPlayer && (shockwave = EnchantmentHelper.func_77506_a((int)LibEnchantIDs.shockwave, (ItemStack)(boots = ((EntityPlayer)event.entityLiving).func_82169_q(0)))) > 0) {
            for (EntityLivingBase target : event.entity.field_70170_p.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a((double)(event.entity.field_70165_t - 10.0), (double)(event.entity.field_70163_u - 10.0), (double)(event.entity.field_70161_v - 10.0), (double)(event.entity.field_70165_t + 10.0), (double)(event.entity.field_70163_u + 10.0), (double)(event.entity.field_70161_v + 10.0)))) {
                if (target == event.entity || !(event.distance > 3.0f)) continue;
                target.func_70097_a(DamageSource.field_76379_h, 0.1f * (float)shockwave * event.distance);
            }
        }
    }

    @SubscribeEvent
    public void onBreakBlock(BlockEvent.BreakEvent event) {
        ItemStack item = event.getPlayer().func_71045_bC();
        int tunnel = EnchantmentHelper.func_77506_a((int)LibEnchantIDs.tunnel, (ItemStack)item);
        if (tunnel > 0) {
            if (item.field_77990_d == null) {
                item.field_77990_d = new NBTTagCompound();
            }
            float dir = event.getPlayer().field_70177_z;
            item.field_77990_d.func_74776_a("TTEnchantTunnelDir", dir);
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void onGetHarvestSpeed(PlayerEvent.BreakSpeed event) {
        boolean autoSmeltApplies;
        int tunnel;
        ItemStack heldItem = event.entityPlayer.func_70694_bm();
        if (heldItem == null) {
            return;
        }
        int shatter = EnchantmentHelper.func_77506_a((int)LibEnchantIDs.shatter, (ItemStack)heldItem);
        if (shatter > 0) {
            event.newSpeed = event.block.func_149712_f(event.entityPlayer.field_70170_p, 0, 0, 0) > 20.0f ? (event.newSpeed *= (float)(3 * shatter)) : (float)((double)event.newSpeed * 0.8);
        }
        if ((tunnel = EnchantmentHelper.func_77506_a((int)LibEnchantIDs.tunnel, (ItemStack)heldItem)) > 0) {
            float dir = event.entityPlayer.field_70177_z;
            if (heldItem.field_77990_d != null && heldItem.field_77990_d.func_74764_b("TTEnchantTunnelDir")) {
                float oldDir = heldItem.field_77990_d.func_74760_g("TTEnchantTunnelDir");
                float dif = Math.abs(oldDir - dir);
                event.newSpeed = dif < 50.0f ? (float)((double)event.newSpeed * (1.0 + 0.2 * (double)tunnel)) : (float)((double)event.newSpeed * 0.3);
            }
        }
        int desintegrate = EnchantmentHelper.func_77506_a((int)LibEnchantIDs.idDesintegrate, (ItemStack)heldItem);
        int autoSmelt = EnchantmentHelper.func_77506_a((int)LibEnchantIDs.idAutoSmelt, (ItemStack)heldItem);
        boolean desintegrateApplies = desintegrate > 0 && event.block.field_149782_v <= 1.5f;
        boolean bl = autoSmeltApplies = autoSmelt > 0 && event.block.func_149688_o() == Material.field_151575_d;
        if (desintegrateApplies || autoSmeltApplies) {
            heldItem.func_77972_a(1, (EntityLivingBase)event.entityPlayer);
            event.newSpeed = Float.MAX_VALUE;
        } else if (desintegrate > 0 || autoSmelt > 0) {
            event.setCanceled(true);
        }
    }
}

