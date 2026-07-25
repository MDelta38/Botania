/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaublesApi
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILeapAtTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAIMate
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks$EntityAITaskEntry
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.IExtendedEntityProperties
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.EntityEvent$EntityConstructing
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.event.entity.living.EnderTeleportEvent
 *  net.minecraftforge.event.entity.living.LivingAttackEvent
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.entity.player.EntityInteractEvent
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.entities.EntityAspectOrb
 *  thaumcraft.common.entities.EntityFollowingItem
 *  thaumcraft.common.items.relics.ItemHandMirror
 *  thaumcraft.common.lib.network.PacketHandler
 *  thaumcraft.common.lib.network.fx.PacketFXShield
 *  thaumcraft.common.lib.utils.EntityUtils
 */
package com.kentington.thaumichorizons.common.lib;

import baubles.api.BaublesApi;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityGolemTH;
import com.kentington.thaumichorizons.common.entities.EntityMeatSlime;
import com.kentington.thaumichorizons.common.entities.EntityMercurialSlime;
import com.kentington.thaumichorizons.common.entities.EntityNightmare;
import com.kentington.thaumichorizons.common.entities.EntityVoltSlime;
import com.kentington.thaumichorizons.common.entities.ai.EntityAIAttackOnCollideTH;
import com.kentington.thaumichorizons.common.entities.ai.EntityAIFollowOwnerTH;
import com.kentington.thaumichorizons.common.entities.ai.EntityAIHurtByTargetTH;
import com.kentington.thaumichorizons.common.entities.ai.EntityAIOwnerHurtByTargetTH;
import com.kentington.thaumichorizons.common.entities.ai.EntityAIOwnerHurtTargetTH;
import com.kentington.thaumichorizons.common.entities.ai.EntityAISitTH;
import com.kentington.thaumichorizons.common.entities.ai.EntityAIWanderTH;
import com.kentington.thaumichorizons.common.items.ItemAmuletMirror;
import com.kentington.thaumichorizons.common.items.ItemFocusContainment;
import com.kentington.thaumichorizons.common.lib.EntityInfusionProperties;
import com.kentington.thaumichorizons.common.lib.PacketFXContainment;
import com.kentington.thaumichorizons.common.lib.PacketHandler;
import com.kentington.thaumichorizons.common.lib.PacketNoMoreItems;
import com.kentington.thaumichorizons.common.lib.PacketPlayerInfusionSync;
import com.kentington.thaumichorizons.common.tiles.TileSoulBeacon;
import com.kentington.thaumichorizons.common.tiles.TileVat;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.damagesource.DamageSourceThaumcraft;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.EntityAspectOrb;
import thaumcraft.common.entities.EntityFollowingItem;
import thaumcraft.common.items.relics.ItemHandMirror;
import thaumcraft.common.lib.network.fx.PacketFXShield;
import thaumcraft.common.lib.utils.EntityUtils;

public class EventHandlerEntity {
    @SideOnly(value=Side.CLIENT)
    public static int clientNightmareID;
    @SideOnly(value=Side.CLIENT)
    public static int clientPlayerID;

    @SubscribeEvent
    public void ConstructEntity(EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityLivingBase && event.entity.getExtendedProperties("CreatureInfusion") == null) {
            EntityInfusionProperties prop = new EntityInfusionProperties();
            prop.entity = event.entity;
            event.entity.registerExtendedProperties("CreatureInfusion", (IExtendedEntityProperties)prop);
        }
    }

    @SubscribeEvent
    public void EntityJoinWorld(EntityJoinWorldEvent event) {
        if (event.entity instanceof EntityLivingBase) {
            this.applyInfusions((EntityLivingBase)event.entity);
        }
        if (event.world.field_72995_K && event.entity instanceof EntityNightmare && event.entity.func_145782_y() == clientNightmareID) {
            event.entity.field_70170_p.func_73045_a((int)EventHandlerEntity.clientPlayerID).field_70154_o = event.entity;
            event.entity.field_70153_n = event.entity.field_70170_p.func_73045_a(clientPlayerID);
            clientNightmareID = -2;
            clientPlayerID = -2;
        }
    }

    public void applyInfusions(EntityLivingBase entity) {
        StackTraceElement[] above = Thread.currentThread().getStackTrace();
        if (entity instanceof EntityPlayer) {
            int[] nArray = ((EntityInfusionProperties)entity.getExtendedProperties("CreatureInfusion")).getPlayerInfusions();
            for (int i = 0; i < nArray.length; ++i) {
                if (nArray[i] == 0 || nArray[i] != 8 || entity.field_70170_p.field_72995_K) continue;
                this.warpTumor((EntityPlayer)entity, 50 - ((EntityInfusionProperties)entity.getExtendedProperties((String)"CreatureInfusion")).tumorWarpPermanent - ((EntityInfusionProperties)entity.getExtendedProperties((String)"CreatureInfusion")).tumorWarp - ((EntityInfusionProperties)entity.getExtendedProperties((String)"CreatureInfusion")).tumorWarpTemp);
            }
            this.applyPlayerPotionInfusions((EntityPlayer)entity, nArray, ((EntityInfusionProperties)entity.getExtendedProperties((String)"CreatureInfusion")).toggleInvisible);
            if (!entity.field_70170_p.field_72995_K) {
                PacketHandler.INSTANCE.sendToAll((IMessage)new PacketPlayerInfusionSync(entity.func_70005_c_(), nArray));
            }
        } else {
            StackTraceElement[] stackTraceElementArray = above;
            int i = stackTraceElementArray.length;
            for (int j = 0; j < i; ++j) {
                StackTraceElement el = stackTraceElementArray[j];
                if (!el.getMethodName().equals("applyNewAI")) continue;
                return;
            }
            int[] nArray = ((EntityInfusionProperties)entity.getExtendedProperties("CreatureInfusion")).getInfusions();
            for (i = 0; i < 12; ++i) {
                if (nArray[i] == 0) continue;
                if (nArray[i] == 1) {
                    PotionEffect effect = new PotionEffect(Potion.field_76430_j.field_76415_H, Integer.MAX_VALUE, 0, true);
                    effect.setCurativeItems(new ArrayList());
                    entity.func_70690_d(effect);
                    continue;
                }
                if (nArray[i] == 3) {
                    PotionEffect effect = new PotionEffect(Potion.field_76428_l.field_76415_H, Integer.MAX_VALUE, 0, true);
                    effect.setCurativeItems(new ArrayList());
                    entity.func_70690_d(effect);
                    continue;
                }
                if (nArray[i] == 4) {
                    PotionEffect effect = new PotionEffect(Potion.field_76429_m.field_76415_H, Integer.MAX_VALUE, 0, true);
                    effect.setCurativeItems(new ArrayList());
                    entity.func_70690_d(effect);
                    ThaumicHorizons.instance.renderEventHandler.thingsThatSparkle.add(entity);
                    continue;
                }
                if (nArray[i] == 8 && !entity.getEntityData().func_74764_b("runicCharge")) {
                    entity.getEntityData().func_74768_a("runicCharge", 6);
                    continue;
                }
                if (nArray[i] != 7) continue;
                this.applyNewAI((EntityLiving)entity);
            }
        }
    }

    public void applyPlayerPotionInfusions(EntityPlayer entity, int[] infusions, boolean toggled) {
        for (int i = 0; i < infusions.length; ++i) {
            PotionEffect effect;
            if (infusions[i] == 1) {
                effect = new PotionEffect(Potion.field_76430_j.field_76415_H, Integer.MAX_VALUE, 0, true);
                effect.setCurativeItems(new ArrayList());
                entity.func_70690_d(effect);
                effect = new PotionEffect(Potion.field_76424_c.field_76415_H, Integer.MAX_VALUE, 0, true);
                effect.setCurativeItems(new ArrayList());
                entity.func_70690_d(effect);
                continue;
            }
            if (infusions[i] == 3) {
                effect = new PotionEffect(Potion.field_76428_l.field_76415_H, Integer.MAX_VALUE, 0, true);
                effect.setCurativeItems(new ArrayList());
                entity.func_70690_d(effect);
                continue;
            }
            if (infusions[i] == 4) {
                effect = new PotionEffect(Potion.field_76429_m.field_76415_H, Integer.MAX_VALUE, 0, true);
                effect.setCurativeItems(new ArrayList());
                entity.func_70690_d(effect);
                continue;
            }
            if (infusions[i] != 10 || toggled) continue;
            effect = new PotionEffect(Potion.field_76441_p.field_76415_H, Integer.MAX_VALUE, 0, true);
            effect.setCurativeItems(new ArrayList());
            entity.func_70690_d(effect);
            entity.func_82142_c(true);
        }
    }

    public void warpTumor(EntityPlayer entity, int capacity) {
        if (capacity <= 0) {
            return;
        }
        int warpPermanent = Thaumcraft.proxy.getPlayerKnowledge().getWarpPerm(entity.func_70005_c_());
        int warp = Thaumcraft.proxy.getPlayerKnowledge().getWarpSticky(entity.func_70005_c_());
        int tempWarp = Thaumcraft.proxy.getPlayerKnowledge().getWarpTemp(entity.func_70005_c_());
        if (warpPermanent > capacity) {
            Thaumcraft.proxy.getPlayerKnowledge().addWarpPerm(entity.func_70005_c_(), -capacity);
            ((EntityInfusionProperties)entity.getExtendedProperties((String)"CreatureInfusion")).tumorWarpPermanent += capacity;
            capacity = 0;
        } else {
            Thaumcraft.proxy.getPlayerKnowledge().addWarpPerm(entity.func_70005_c_(), -warpPermanent);
            ((EntityInfusionProperties)entity.getExtendedProperties((String)"CreatureInfusion")).tumorWarpPermanent += warpPermanent;
            if (warp > (capacity -= warpPermanent)) {
                Thaumcraft.proxy.getPlayerKnowledge().addWarpSticky(entity.func_70005_c_(), -capacity);
                ((EntityInfusionProperties)entity.getExtendedProperties((String)"CreatureInfusion")).tumorWarp += capacity;
                capacity = 0;
            } else {
                Thaumcraft.proxy.getPlayerKnowledge().addWarpSticky(entity.func_70005_c_(), -warp);
                ((EntityInfusionProperties)entity.getExtendedProperties((String)"CreatureInfusion")).tumorWarp += warp;
                if (tempWarp > (capacity -= warp)) {
                    Thaumcraft.proxy.getPlayerKnowledge().addWarpTemp(entity.func_70005_c_(), -capacity);
                    ((EntityInfusionProperties)entity.getExtendedProperties((String)"CreatureInfusion")).tumorWarpTemp += capacity;
                    capacity = 0;
                } else {
                    capacity -= tempWarp;
                    Thaumcraft.proxy.getPlayerKnowledge().addWarpTemp(entity.func_70005_c_(), -tempWarp);
                    ((EntityInfusionProperties)entity.getExtendedProperties((String)"CreatureInfusion")).tumorWarpTemp += tempWarp;
                }
            }
        }
    }

    public void applyNewAI(EntityLiving entity) {
        if (entity.field_70714_bg.field_75782_a.size() == 0) {
            // empty if block
        }
        ArrayList tasks = new ArrayList();
        Iterator it = entity.field_70714_bg.field_75782_a.iterator();
        while (it.hasNext()) {
            tasks.add(it.next());
        }
        it = tasks.iterator();
        while (it.hasNext()) {
            entity.field_70714_bg.func_85156_a(((EntityAITasks.EntityAITaskEntry)it.next()).field_75733_a);
        }
        tasks = new ArrayList();
        it = entity.field_70715_bh.field_75782_a.iterator();
        while (it.hasNext()) {
            tasks.add(it.next());
        }
        it = tasks.iterator();
        while (it.hasNext()) {
            entity.field_70715_bh.func_85156_a(((EntityAITasks.EntityAITaskEntry)it.next()).field_75733_a);
        }
        entity.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming(entity));
        entity.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAISitTH(entity));
        entity.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAILeapAtTarget(entity, 0.4f));
        entity.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIAttackOnCollideTH(entity, 1.0, true));
        entity.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIFollowOwnerTH(entity, 1.0, 10.0f, 2.0f));
        if (entity instanceof EntityAnimal) {
            entity.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIMate((EntityAnimal)entity, 1.0));
        }
        entity.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWanderTH(entity, 1.0));
        entity.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIWatchClosest(entity, EntityPlayer.class, 8.0f));
        entity.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAILookIdle(entity));
        entity.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIOwnerHurtByTargetTH(entity));
        entity.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAIOwnerHurtTargetTH(entity));
        entity.field_70715_bh.func_75776_a(3, (EntityAIBase)new EntityAIHurtByTargetTH(entity, true));
    }

    @SubscribeEvent
    public void livingTick(LivingEvent.LivingUpdateEvent event) {
        ArrayList stuff;
        EntityLivingBase target;
        int boost;
        ArrayList stuff2;
        boolean runic;
        EntityPlayer player;
        if (event.entity instanceof EntityPlayer) {
            EntityInfusionProperties prop;
            player = (EntityPlayer)event.entity;
            String pp = "R" + player.getDisplayName();
            if (ItemFocusContainment.hitCritters.containsKey(pp)) {
                ItemFocusContainment.contain.put(pp, Float.valueOf(ItemFocusContainment.contain.get(pp).floatValue() - 1.0f));
                if (ItemFocusContainment.contain.get(pp).floatValue() > 0.0f) {
                    Entity ent = ItemFocusContainment.hitCritters.get(pp);
                    ThaumicHorizons.proxy.containmentFX(ent.field_70165_t, ent.field_70163_u, ent.field_70161_v, player, ent, (int)(ItemFocusContainment.contain.get(pp).floatValue() / ((EntityLiving)ent).func_110143_aJ()) / 3 + 1);
                } else {
                    ItemFocusContainment.contain.remove(pp);
                    ItemFocusContainment.hitCritters.remove(pp);
                }
            }
            if ((prop = (EntityInfusionProperties)player.getExtendedProperties("CreatureInfusion")).hasPlayerInfusion(5) && (player.func_70660_b(Potion.field_76436_u) != null || player.func_70660_b(Potion.field_82731_v) != null || player.func_70660_b(Potion.field_76425_a[Config.potionInfVisExhaustID]) != null || player.func_70660_b(Potion.field_76425_a[Config.potionVisExhaustID]) != null || player.func_70660_b(Potion.field_76425_a[Config.potionThaumarhiaID]) != null || player.func_70660_b(Potion.field_76425_a[Config.potionTaintPoisonID]) != null)) {
                Collection col = event.entityLiving.func_70651_bq();
                Iterator it = col.iterator();
                ArrayList<PotionEffect> toAdd = new ArrayList<PotionEffect>();
                while (it.hasNext()) {
                    PotionEffect pot = (PotionEffect)it.next();
                    if (pot.func_76456_a() == Potion.field_76436_u.field_76415_H || pot.func_76456_a() == Potion.field_82731_v.field_76415_H || pot.func_76456_a() == Config.potionInfVisExhaustID || pot.func_76456_a() == Config.potionTaintPoisonID || pot.func_76456_a() == Config.potionVisExhaustID || pot.func_76456_a() == Config.potionThaumarhiaID) {
                        int id = pot.func_76456_a();
                        int amplifier = 0;
                        int duration = pot.func_76459_b() - 1;
                        toAdd.add(new PotionEffect(id, duration, amplifier, false));
                        continue;
                    }
                    toAdd.add(pot);
                }
                event.entityLiving.func_70674_bp();
                for (PotionEffect effect : toAdd) {
                    event.entityLiving.func_70690_d(effect);
                }
            }
            if (prop.hasPlayerInfusion(6) && ((EntityLivingBase)event.entity).field_70173_aa % 200 == 0 && player.field_70170_p.func_72935_r() && player.field_70170_p.func_72937_j(MathHelper.func_76128_c((double)player.field_70165_t), MathHelper.func_76128_c((double)player.field_70163_u), MathHelper.func_76128_c((double)player.field_70161_v))) {
                player.func_71024_bL().func_75122_a(1, 0.0f);
            }
            if (prop.hasPlayerInfusion(7) && player.func_70090_H()) {
                player.func_70050_g(300);
            }
            if (event.entityLiving.field_70173_aa % 30 == 0) {
                if (prop.hasPlayerInfusion(8)) {
                    this.warpTumor((EntityPlayer)event.entityLiving, 50 - prop.tumorWarpPermanent - prop.tumorWarp - prop.tumorWarpTemp);
                }
                this.applyPlayerPotionInfusions(player, prop.playerInfusions, prop.toggleInvisible);
            }
            if (prop.hasPlayerInfusion(9) && !prop.toggleClimb) {
                if (event.entityLiving.field_70123_F) {
                    event.entityLiving.field_70181_x = 0.2;
                    if (event.entityLiving.func_70093_af()) {
                        event.entityLiving.field_70181_x = 0.0;
                    }
                    event.entity.field_70143_R = 0.0f;
                } else {
                    List listy = event.entityLiving.field_70170_p.func_147461_a(AxisAlignedBB.func_72330_a((double)(event.entityLiving.field_70165_t - (double)event.entityLiving.field_70130_N / 1.5), (double)event.entityLiving.field_70163_u, (double)(event.entityLiving.field_70161_v - (double)event.entityLiving.field_70130_N / 1.5), (double)(event.entityLiving.field_70165_t + (double)event.entityLiving.field_70130_N / 1.5), (double)(event.entityLiving.field_70163_u + (double)event.entityLiving.field_70131_O * 0.9), (double)(event.entityLiving.field_70161_v + (double)event.entityLiving.field_70130_N / 1.5)));
                    if (listy.size() > 0) {
                        event.entityLiving.field_70181_x = event.entityLiving.func_70093_af() ? 0.0 : -0.15;
                        event.entity.field_70143_R = 0.0f;
                    }
                }
            }
        }
        if (event.entityLiving.field_70173_aa % 30 == 0) {
            boolean shock = ((EntityInfusionProperties)event.entityLiving.getExtendedProperties("CreatureInfusion")).hasInfusion(6);
            if (shock && event.entityLiving.func_70643_av() != null) {
                event.entityLiving.func_70643_av().func_70097_a(DamageSource.field_76376_m, 1.0f);
                Thaumcraft.proxy.arcLightning(event.entityLiving.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + (double)(event.entityLiving.field_70131_O / 2.0f), event.entityLiving.field_70161_v, event.entityLiving.func_70643_av().field_70165_t, event.entityLiving.func_70643_av().field_70163_u + (double)(event.entityLiving.func_70643_av().field_70131_O / 2.0f), event.entityLiving.func_70643_av().field_70161_v, 0.2f, 0.8f, 0.8f, 1.0f);
                event.entityLiving.field_70170_p.func_72956_a((Entity)event.entityLiving, "thaumcraft:zap", 1.0f, 1.0f + (event.entityLiving.field_70170_p.field_73012_v.nextFloat() - event.entityLiving.field_70170_p.field_73012_v.nextFloat()) * 0.2f);
            } else if (shock && event.entityLiving instanceof EntityLiving && ((EntityLiving)event.entityLiving).func_70638_az() != null) {
                ((EntityLiving)event.entityLiving).func_70638_az().func_70097_a(DamageSource.field_76376_m, 1.0f);
                Thaumcraft.proxy.arcLightning(event.entityLiving.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + (double)(event.entityLiving.field_70131_O / 2.0f), event.entityLiving.field_70161_v, ((EntityLiving)event.entityLiving).func_70638_az().field_70165_t, ((EntityLiving)event.entityLiving).func_70638_az().field_70163_u + (double)(((EntityLiving)event.entityLiving).func_70638_az().field_70131_O / 2.0f), ((EntityLiving)event.entityLiving).func_70638_az().field_70161_v, 0.2f, 0.8f, 0.8f, 1.0f);
                event.entityLiving.field_70170_p.func_72956_a((Entity)event.entityLiving, "thaumcraft:zap", 1.0f, 1.0f + (event.entityLiving.field_70170_p.field_73012_v.nextFloat() - event.entityLiving.field_70170_p.field_73012_v.nextFloat()) * 0.2f);
            }
        }
        if (event.entityLiving instanceof EntityVoltSlime && event.entityLiving.field_70173_aa % 2 == 0 && event.entityLiving.func_70643_av() != null) {
            event.entityLiving.func_70643_av().func_70097_a(DamageSource.field_76376_m, 0.5f);
            Thaumcraft.proxy.arcLightning(event.entityLiving.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + (double)(event.entityLiving.field_70131_O / 2.0f), event.entityLiving.field_70161_v, event.entityLiving.func_70643_av().field_70165_t, event.entityLiving.func_70643_av().field_70163_u + (double)(event.entityLiving.func_70643_av().field_70131_O / 2.0f), event.entityLiving.func_70643_av().field_70161_v, 0.2f, 0.8f, 0.8f, 1.0f);
            event.entityLiving.field_70170_p.func_72956_a((Entity)event.entityLiving, "thaumcraft:zap", 1.0f, 1.0f + (event.entityLiving.field_70170_p.field_73012_v.nextFloat() - event.entityLiving.field_70170_p.field_73012_v.nextFloat()) * 0.2f);
        }
        if (event.entityLiving.field_70173_aa % 100 == 0 && (runic = ((EntityInfusionProperties)event.entityLiving.getExtendedProperties("CreatureInfusion")).hasInfusion(8))) {
            int charge = event.entityLiving.getEntityData().func_74762_e("runicCharge") + 1;
            if (charge > 6) {
                charge = 6;
            }
            event.entityLiving.getEntityData().func_74768_a("runicCharge", charge);
        }
        if (((EntityLivingBase)event.entity).func_82165_m(ThaumicHorizons.potionShockID) && (stuff2 = EntityUtils.getEntitiesInRange((World)player.field_70170_p, (double)player.field_70165_t, (double)player.field_70163_u, (double)player.field_70161_v, (Entity)(player = (EntityLivingBase)event.entity), EntityLivingBase.class, (double)10.0)) != null && stuff2.size() > 0) {
            int boost2 = player.func_70660_b(Potion.field_76425_a[ThaumicHorizons.potionShockID]).func_76458_c();
            for (Entity e : stuff2) {
                int r = player.field_70170_p.field_73012_v.nextInt(1000);
                if (r >= 20 * (1 + boost2) || e.field_70128_L || !(e instanceof EntityLivingBase)) continue;
                if (player instanceof EntityPlayer) {
                    e.func_70097_a(DamageSource.func_76365_a((EntityPlayer)player), 1.0f);
                } else {
                    e.func_70097_a(DamageSource.field_76376_m, 1.0f);
                }
                Thaumcraft.proxy.arcLightning(player.field_70170_p, player.field_70165_t, player.field_70163_u + (double)(player.field_70131_O / 2.0f), player.field_70161_v, e.field_70165_t, e.field_70163_u + (double)(e.field_70131_O / 2.0f), e.field_70161_v, 0.2f, 0.8f, 0.8f, 1.0f);
                player.field_70170_p.func_72956_a((Entity)player, "thaumcraft:zap", 1.0f, 1.0f + (player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.2f);
            }
        }
        if (((EntityLivingBase)event.entity).func_82165_m(ThaumicHorizons.potionVisRegenID) && ((EntityLivingBase)event.entity).field_70173_aa % (20 - 3 * (boost = (target = (EntityLivingBase)event.entity).func_70660_b(Potion.field_76425_a[ThaumicHorizons.potionVisRegenID]).func_76458_c())) == 0) {
            Aspect aspect = null;
            switch (target.field_70170_p.field_73012_v.nextInt(6)) {
                case 0: {
                    aspect = Aspect.AIR;
                    break;
                }
                case 1: {
                    aspect = Aspect.EARTH;
                    break;
                }
                case 2: {
                    aspect = Aspect.FIRE;
                    break;
                }
                case 3: {
                    aspect = Aspect.WATER;
                    break;
                }
                case 4: {
                    aspect = Aspect.ORDER;
                    break;
                }
                case 5: {
                    aspect = Aspect.ENTROPY;
                }
            }
            if (aspect != null) {
                EntityAspectOrb orb = new EntityAspectOrb(target.field_70170_p, target.field_70165_t, target.field_70163_u, target.field_70161_v, aspect, 1);
                target.field_70170_p.func_72838_d((Entity)orb);
            }
        }
        if (((EntityLivingBase)event.entity).func_82165_m(ThaumicHorizons.potionVacuumID) && (stuff = EntityUtils.getEntitiesInRange((World)player.field_70170_p, (double)player.field_70165_t, (double)player.field_70163_u, (double)player.field_70161_v, (Entity)(player = (EntityLivingBase)event.entity), EntityItem.class, (double)(10.0 + (double)(2 * (boost = player.func_70660_b(Potion.field_76425_a[ThaumicHorizons.potionVacuumID]).func_76458_c()))))) != null && stuff.size() > 0) {
            for (Entity e : stuff) {
                if (e instanceof EntityFollowingItem && ((EntityFollowingItem)e).target != null || e.field_70128_L || !(e instanceof EntityItem)) continue;
                double d6 = e.field_70165_t - player.field_70165_t;
                double d8 = e.field_70163_u - player.field_70163_u + (double)(player.field_70131_O / 2.0f);
                double d10 = e.field_70161_v - player.field_70161_v;
                double d11 = MathHelper.func_76133_a((double)(d6 * d6 + d8 * d8 + d10 * d10));
                double d13 = 0.1;
                e.field_70159_w -= (d6 /= d11) * d13;
                e.field_70181_x -= (d8 /= d11) * d13;
                e.field_70179_y -= (d10 /= d11) * d13;
                if (e.field_70159_w > 0.35) {
                    e.field_70159_w = 0.35;
                }
                if (e.field_70159_w < -0.35) {
                    e.field_70159_w = -0.35;
                }
                if (e.field_70181_x > 0.35) {
                    e.field_70181_x = 0.35;
                }
                if (e.field_70181_x < -0.35) {
                    e.field_70181_x = -0.35;
                }
                if (e.field_70179_y > 0.35) {
                    e.field_70179_y = 0.35;
                }
                if (e.field_70179_y < -0.35) {
                    e.field_70179_y = -0.35;
                }
                Thaumcraft.proxy.spark((float)e.field_70165_t + (player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.125f, (float)e.field_70163_u + (player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.125f, (float)e.field_70161_v + (player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.125f, 1.0f, 0.25f, 0.25f, 0.25f, 1.0f);
            }
        }
        if (((EntityLivingBase)event.entity).func_82165_m(ThaumicHorizons.potionSynthesisID) && ((EntityLivingBase)event.entity).field_70173_aa % (15 - 2 * (boost = (player = (EntityLivingBase)event.entity).func_70660_b(Potion.field_76425_a[ThaumicHorizons.potionSynthesisID]).func_76458_c())) == 0 && player.field_70170_p.func_72935_r() && player.field_70170_p.func_72937_j(MathHelper.func_76128_c((double)player.field_70165_t), MathHelper.func_76128_c((double)player.field_70163_u), MathHelper.func_76128_c((double)player.field_70161_v))) {
            player.func_70691_i(0.5f);
            if (player instanceof EntityPlayer) {
                player.func_71024_bL().func_75122_a(1, 0.5f);
            }
        }
    }

    @SubscribeEvent
    public void livingDeath(LivingDeathEvent event) {
        if (!event.entityLiving.field_70170_p.field_72995_K) {
            if (event.entityLiving instanceof EntityMeatSlime && ((EntityMeatSlime)event.entityLiving).func_70809_q() == 1) {
                switch (event.entityLiving.field_70170_p.field_73012_v.nextInt(5)) {
                    case 0: {
                        event.entityLiving.func_70099_a(new ItemStack(Items.field_151082_bd), 0.0f);
                        break;
                    }
                    case 1: {
                        event.entityLiving.func_70099_a(new ItemStack(Items.field_151147_al), 0.0f);
                        break;
                    }
                    case 2: {
                        event.entityLiving.func_70099_a(new ItemStack(Items.field_151076_bf), 0.0f);
                        break;
                    }
                    case 3: {
                        event.entityLiving.func_70099_a(new ItemStack(Items.field_151115_aP), 0.0f);
                        break;
                    }
                    default: {
                        event.entityLiving.func_70099_a(new ItemStack(Items.field_151078_bh), 0.0f);
                        break;
                    }
                }
            } else if (event.entityLiving instanceof EntityMercurialSlime && ((EntityMercurialSlime)event.entityLiving).func_70809_q() == 1) {
                event.entityLiving.func_70099_a(new ItemStack(ConfigItems.itemResource, 1, 3), 0.0f);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerHurt(LivingHurtEvent event) {
        EntityPlayer player;
        EntityInfusionProperties prop = (EntityInfusionProperties)event.entity.getExtendedProperties("CreatureInfusion");
        if (prop.hasPlayerInfusion(5) && event.source == DamageSourceThaumcraft.taint) {
            event.setCanceled(true);
            event.ammount = 0.0f;
            return;
        }
        if (!event.entity.field_70170_p.field_72995_K && event.entity instanceof EntityPlayer && event.entityLiving.func_110143_aJ() - event.ammount <= 0.0f) {
            player = (EntityPlayer)event.entity;
            if (prop.tumorWarp > 0 || prop.tumorWarpTemp > 0) {
                Thaumcraft.proxy.getPlayerKnowledge().addWarpPerm(event.entity.func_70005_c_(), prop.tumorWarpPermanent);
                Thaumcraft.proxy.getPlayerKnowledge().addWarpSticky(event.entity.func_70005_c_(), prop.tumorWarp);
                Thaumcraft.proxy.getPlayerKnowledge().addWarpTemp(event.entity.func_70005_c_(), prop.tumorWarpTemp);
            }
            prop.resetPlayerInfusions();
            IInventory baubles = BaublesApi.getBaubles((EntityPlayer)player);
            for (int a = 0; a < 4; ++a) {
                ItemStack item;
                int i;
                ItemStack amulet = baubles.func_70301_a(a);
                if (amulet == null || !(amulet.func_77973_b() instanceof ItemAmuletMirror)) continue;
                boolean transportedSomething = false;
                for (i = 0; i < player.field_71071_by.field_70460_b.length; ++i) {
                    item = player.field_71071_by.field_70460_b[i];
                    if (item == null || !ItemHandMirror.transport((ItemStack)amulet, (ItemStack)item, (EntityPlayer)player, (World)player.field_70170_p)) continue;
                    transportedSomething = true;
                    player.field_71071_by.field_70460_b[i] = null;
                }
                for (i = 0; i < player.field_71071_by.field_70462_a.length; ++i) {
                    item = player.field_71071_by.field_70462_a[i];
                    if (item == null || !ItemHandMirror.transport((ItemStack)amulet, (ItemStack)item, (EntityPlayer)player, (World)player.field_70170_p)) continue;
                    transportedSomething = true;
                    player.field_71071_by.field_70462_a[i] = null;
                }
                for (int b = 0; b < 4; ++b) {
                    if (a == b || baubles.func_70301_a(b) == null || !ItemHandMirror.transport((ItemStack)amulet, (ItemStack)baubles.func_70301_a(b), (EntityPlayer)player, (World)player.field_70170_p)) continue;
                    transportedSomething = true;
                    baubles.func_70299_a(b, null);
                }
                if (!transportedSomething) break;
                PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXContainment(player.field_70165_t, player.field_70163_u + (double)player.func_70047_e(), player.field_70161_v), new NetworkRegistry.TargetPoint(player.field_70170_p.field_73011_w.field_76574_g, player.field_70165_t, player.field_70163_u, player.field_70161_v, 32.0));
                player.field_70170_p.func_72908_a(player.field_70165_t, player.field_70163_u + (double)player.func_70047_e(), player.field_70161_v, "thaumcraft:craftfail", 1.0f, 1.0f);
                baubles.func_70299_a(a, null);
                player.field_71071_by.func_70296_d();
                baubles.func_70296_d();
                ItemStack droppedPearl = new ItemStack(ConfigItems.itemEldritchObject, 1, 3);
                EntityItem drop = new EntityItem(player.field_70170_p, player.field_70165_t, player.field_70163_u, player.field_70161_v, droppedPearl);
                player.field_70170_p.func_72838_d((Entity)drop);
                break;
            }
        }
        if (!event.entity.field_70170_p.field_72995_K && event.entityLiving instanceof EntityPlayer && event.entityLiving.func_110143_aJ() - event.ammount <= 0.0f && event.entityLiving.getEntityData().func_74767_n("soulBeacon")) {
            int z;
            int y;
            int x;
            player = (EntityPlayer)event.entity;
            int dim = player.getEntityData().func_74762_e("soulBeaconDim");
            WorldServer world = MinecraftServer.func_71276_C().func_71218_a(dim);
            if (world.func_147438_o(x = player.getEntityData().func_74759_k("soulBeaconCoords")[0], y = player.getEntityData().func_74759_k("soulBeaconCoords")[1], z = player.getEntityData().func_74759_k("soulBeaconCoords")[2]) instanceof TileSoulBeacon && world.func_147438_o(x, y - 1, z) instanceof TileVat && ((TileVat)world.func_147438_o((int)x, (int)(y - 1), (int)z)).mode == 4) {
                event.setCanceled(true);
                if (!world.field_72995_K) {
                    world.func_72876_a(null, player.field_70165_t, player.field_70163_u + (double)player.func_70047_e(), player.field_70161_v, 0.0f, false);
                    for (int a = 0; a < 25; ++a) {
                        int zz;
                        int yy;
                        int xx = (int)player.field_70165_t + world.field_73012_v.nextInt(8) - world.field_73012_v.nextInt(8);
                        if (!world.func_147437_c(xx, yy = (int)player.field_70163_u + world.field_73012_v.nextInt(8) - world.field_73012_v.nextInt(8), zz = (int)player.field_70161_v + world.field_73012_v.nextInt(8) - world.field_73012_v.nextInt(8))) continue;
                        if (yy <= (int)player.field_70163_u + 1) {
                            world.func_147465_d(xx, yy, zz, ConfigBlocks.blockFluxGoo, 8, 3);
                            continue;
                        }
                        world.func_147465_d(xx, yy, zz, ConfigBlocks.blockFluxGas, 8, 3);
                    }
                }
                player.field_71071_by.func_70436_m();
                IInventory baubles = BaublesApi.getBaubles((EntityPlayer)player);
                for (int i = 0; i < 4; ++i) {
                    if (baubles.func_70301_a(i) == null) continue;
                    EntityItem bauble = new EntityItem((World)world, player.field_70165_t, player.field_70163_u, player.field_70161_v, baubles.func_70301_a(i));
                    world.func_72838_d((Entity)bauble);
                    baubles.func_70299_a(i, null);
                }
                baubles.func_70296_d();
                player.field_71071_by.func_70296_d();
                PacketHandler.INSTANCE.sendTo((IMessage)new PacketNoMoreItems(), (EntityPlayerMP)player);
                player.curePotionEffects(new ItemStack(Items.field_151117_aB));
                player.func_70691_i(999.0f);
                if (dim != player.field_70170_p.field_73011_w.field_76574_g) {
                    player.func_71027_c(dim);
                }
                player.func_70634_a((double)x + 0.5, (double)y - 2.5, (double)z + 0.5);
                Thaumcraft.proxy.blockSparkle((World)world, x, y - 2, z, 0xFFFFFF, 20);
                Thaumcraft.proxy.blockSparkle((World)world, x, y - 3, z, 0xFFFFFF, 20);
                world.func_72908_a((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:whispers", 1.0f, world.field_73012_v.nextFloat());
                this.applyPlayerInfusions(player, (TileVat)world.func_147438_o(x, y - 1, z));
                ((TileVat)world.func_147438_o((int)x, (int)(y - 1), (int)z)).selfInfusions = new int[12];
                ((TileVat)world.func_147438_o((int)x, (int)(y - 1), (int)z)).mode = 0;
                ((TileVat)world.func_147438_o(x, y - 1, z)).setEntityContained((EntityLivingBase)player);
                ((TileVat)world.func_147438_o(x, y - 1, z)).func_70296_d();
                player.field_70170_p.func_147471_g(x, y - 1, z);
            }
        } else if (event.entity.field_70170_p.field_72995_K && event.entityLiving instanceof EntityPlayer && event.entityLiving.func_110143_aJ() - event.ammount <= 0.0f && event.entityLiving.getEntityData().func_74767_n("soulBeacon")) {
            int i;
            player = (EntityPlayer)event.entity;
            for (i = 0; i < player.field_71071_by.field_70462_a.length; ++i) {
                player.field_71071_by.field_70462_a[i] = null;
            }
            for (i = 0; i < player.field_71071_by.field_70460_b.length; ++i) {
                player.field_71071_by.field_70460_b[i] = null;
            }
            IInventory baubles = BaublesApi.getBaubles((EntityPlayer)player);
            baubles.func_70299_a(0, null);
            baubles.func_70299_a(1, null);
            baubles.func_70299_a(2, null);
            baubles.func_70299_a(3, null);
        }
    }

    void applyPlayerInfusions(EntityPlayer player, TileVat tile) {
        EntityInfusionProperties prop = (EntityInfusionProperties)player.getExtendedProperties("CreatureInfusion");
        for (int i = 0; i < tile.selfInfusions.length; ++i) {
            if (tile.selfInfusions[i] == 0) continue;
            prop.addPlayerInfusion(tile.selfInfusions[i]);
        }
        this.applyInfusions((EntityLivingBase)player);
    }

    @SubscribeEvent
    public void onAttack(LivingAttackEvent event) {
        boolean poison;
        if (event.source.func_76346_g() != null && event.source.func_76346_g() instanceof EntityLivingBase && (poison = ((EntityInfusionProperties)event.entityLiving.getExtendedProperties("CreatureInfusion")).hasInfusion(9))) {
            event.entityLiving.func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, 40, 0, false));
        }
        if (!event.source.func_76352_a()) {
            return;
        }
        boolean ender = ((EntityInfusionProperties)event.entityLiving.getExtendedProperties("CreatureInfusion")).hasInfusion(5);
        if (!ender) {
            return;
        }
        event.setCanceled(true);
        this.teleport(event.entityLiving);
    }

    public void teleport(EntityLivingBase entity) {
        int k;
        int j;
        EnderTeleportEvent event = new EnderTeleportEvent(entity, entity.field_70165_t + (entity.field_70170_p.field_73012_v.nextDouble() - 0.5) * 64.0, entity.field_70163_u + (double)(entity.field_70170_p.field_73012_v.nextInt(64) - 32), entity.field_70161_v + (entity.field_70170_p.field_73012_v.nextDouble() - 0.5) * 64.0, 0.0f);
        if (MinecraftForge.EVENT_BUS.post((Event)event)) {
            return;
        }
        double d3 = entity.field_70165_t;
        double d4 = entity.field_70163_u;
        double d5 = entity.field_70161_v;
        entity.field_70165_t = event.targetX;
        entity.field_70163_u = event.targetY;
        entity.field_70161_v = event.targetZ;
        boolean flag = false;
        int i = MathHelper.func_76128_c((double)entity.field_70165_t);
        if (entity.field_70170_p.func_72899_e(i, j = MathHelper.func_76128_c((double)entity.field_70163_u), k = MathHelper.func_76128_c((double)entity.field_70161_v))) {
            boolean flag1 = false;
            while (!flag1 && j > 0) {
                Block block = entity.field_70170_p.func_147439_a(i, j - 1, k);
                if (block.func_149688_o().func_76230_c()) {
                    flag1 = true;
                    continue;
                }
                entity.field_70163_u -= 1.0;
                --j;
            }
            if (flag1) {
                entity.func_70107_b(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
                if (entity.field_70170_p.func_72945_a((Entity)entity, entity.field_70121_D).isEmpty() && !entity.field_70170_p.func_72953_d(entity.field_70121_D)) {
                    flag = true;
                }
            }
        }
        if (!flag) {
            entity.func_70107_b(d3, d4, d5);
            return;
        }
        int short1 = 128;
        for (int l = 0; l < short1; ++l) {
            double d6 = (double)l / ((double)short1 - 1.0);
            float f = (entity.field_70170_p.field_73012_v.nextFloat() - 0.5f) * 0.2f;
            float f1 = (entity.field_70170_p.field_73012_v.nextFloat() - 0.5f) * 0.2f;
            float f2 = (entity.field_70170_p.field_73012_v.nextFloat() - 0.5f) * 0.2f;
            double d7 = d3 + (entity.field_70165_t - d3) * d6 + (entity.field_70170_p.field_73012_v.nextDouble() - 0.5) * (double)entity.field_70130_N * 2.0;
            double d8 = d4 + (entity.field_70163_u - d4) * d6 + entity.field_70170_p.field_73012_v.nextDouble() * (double)entity.field_70131_O;
            double d9 = d5 + (entity.field_70161_v - d5) * d6 + (entity.field_70170_p.field_73012_v.nextDouble() - 0.5) * (double)entity.field_70130_N * 2.0;
            entity.field_70170_p.func_72869_a("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
        }
        entity.field_70170_p.func_72908_a(d3, d4, d5, "mob.endermen.portal", 1.0f, 1.0f);
        entity.func_85030_a("mob.endermen.portal", 1.0f, 1.0f);
    }

    @SubscribeEvent
    public void entityHurt(LivingHurtEvent event) {
        boolean runic = ((EntityInfusionProperties)event.entityLiving.getExtendedProperties("CreatureInfusion")).hasInfusion(8);
        if (runic) {
            int charge = event.entityLiving.getEntityData().func_74762_e("runicCharge");
            if (charge <= 0 || event.source == DamageSource.field_76369_e || event.source == DamageSource.field_82727_n || event.source == DamageSource.field_76380_i || event.source == DamageSource.field_76366_f) {
                return;
            }
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
            thaumcraft.common.lib.network.PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXShield(event.entity.func_145782_y(), target), new NetworkRegistry.TargetPoint(event.entity.field_70170_p.field_73011_w.field_76574_g, event.entity.field_70165_t, event.entity.field_70163_u, event.entity.field_70161_v, 64.0));
            if ((float)charge > event.ammount) {
                charge = (int)((float)charge - event.ammount);
                event.ammount = 0.0f;
            } else {
                event.ammount -= (float)charge;
                charge = 0;
            }
            event.entityLiving.getEntityData().func_74768_a("runicCharge", charge);
        }
    }

    @SubscribeEvent
    public void golemDies(LivingDeathEvent event) {
        if (event.entity instanceof EntityGolemTH) {
            ((EntityGolemTH)event.entity).die();
        }
    }

    @SubscribeEvent
    public void sitStay(EntityInteractEvent event) {
        if (event.target.getExtendedProperties("CreatureInfusion") != null) {
            EntityInfusionProperties prop = (EntityInfusionProperties)event.target.getExtendedProperties("CreatureInfusion");
            if (prop.hasInfusion(10) && event.entityPlayer.func_70694_bm() != null && event.entityPlayer.func_70694_bm().func_77973_b() == ConfigItems.itemWandCasting) {
                ItemStack jar = new ItemStack(ThaumicHorizons.blockJar);
                NBTTagCompound entityData = new NBTTagCompound();
                entityData.func_74778_a("id", EntityList.func_75621_b((Entity)event.target));
                event.target.func_70109_d(entityData);
                jar.func_77982_d(entityData);
                jar.func_77978_p().func_74778_a("jarredCritterName", event.target.func_70005_c_());
                jar.func_77978_p().func_74757_a("isSoul", false);
                event.target.func_70099_a(jar, 1.0f);
                if (!event.target.field_70170_p.field_72995_K) {
                    PacketHandler.INSTANCE.sendToAllAround((IMessage)new PacketFXContainment(event.target.field_70165_t, event.target.field_70163_u + (double)(event.target.field_70131_O / 2.0f), event.target.field_70161_v), new NetworkRegistry.TargetPoint(event.target.field_70170_p.field_73011_w.field_76574_g, event.target.field_70165_t, event.target.field_70163_u + (double)(event.target.field_70131_O / 2.0f), event.target.field_70161_v, 32.0));
                }
                event.target.field_70170_p.func_72900_e(event.target);
            } else if (prop.hasInfusion(7) && event.entityPlayer.func_70005_c_().equals(prop.getOwner())) {
                prop.setSitting(!prop.isSitting());
                if (event.target.field_70170_p.field_72995_K) {
                    if (prop.isSitting()) {
                        event.entityPlayer.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.ITALIC + "" + EnumChatFormatting.GRAY + event.target.func_70005_c_() + " is waiting."));
                    } else {
                        event.entityPlayer.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.ITALIC + "" + EnumChatFormatting.GRAY + event.target.func_70005_c_() + " will follow you."));
                    }
                }
            }
        }
    }
}

