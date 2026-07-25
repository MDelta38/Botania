/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event$Result
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.block.Block
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityBlaze
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.monster.EntityPigZombie
 *  net.minecraft.entity.monster.EntitySkeleton
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.monster.IMob
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityBat
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.entity.passive.IAnimals
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayer$EnumStatus
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.event.ClickEvent
 *  net.minecraft.event.ClickEvent$Action
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.pathfinding.PathEntity
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.scoreboard.ScorePlayerTeam
 *  net.minecraft.scoreboard.Team
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EntityDamageSource
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.village.Village
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.ForgeHooks
 *  net.minecraftforge.event.ServerChatEvent
 *  net.minecraftforge.event.entity.EntityEvent$EntityConstructing
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.event.entity.item.ItemExpireEvent
 *  net.minecraftforge.event.entity.item.ItemTossEvent
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.entity.living.LivingDropsEvent
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  net.minecraftforge.event.entity.living.LivingFallEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.entity.player.EntityInteractEvent
 *  net.minecraftforge.event.entity.player.PlayerDropsEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$Clone
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$Action
 *  net.minecraftforge.event.entity.player.PlayerSleepInBedEvent
 *  net.minecraftforge.event.entity.player.PlayerWakeUpEvent
 *  net.minecraftforge.event.world.BlockEvent$HarvestDropsEvent
 */
package com.emoniph.witchery.common;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockVoidBramble;
import com.emoniph.witchery.brewing.potions.PotionEnslaved;
import com.emoniph.witchery.brewing.potions.PotionParalysis;
import com.emoniph.witchery.brewing.potions.PotionResizing;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.common.ExtendedVillager;
import com.emoniph.witchery.common.Shapeshift;
import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
import com.emoniph.witchery.entity.EntityDemon;
import com.emoniph.witchery.entity.EntityFollower;
import com.emoniph.witchery.entity.EntityGoblin;
import com.emoniph.witchery.entity.EntityHornedHuntsman;
import com.emoniph.witchery.entity.EntityItemWaystone;
import com.emoniph.witchery.entity.EntityMindrake;
import com.emoniph.witchery.entity.EntityOwl;
import com.emoniph.witchery.entity.EntitySummonedUndead;
import com.emoniph.witchery.entity.EntityToad;
import com.emoniph.witchery.entity.EntityVillageGuard;
import com.emoniph.witchery.entity.EntityVillagerWere;
import com.emoniph.witchery.entity.EntityWitchHunter;
import com.emoniph.witchery.entity.EntityWolfman;
import com.emoniph.witchery.entity.ai.EntityAIDigBlocks;
import com.emoniph.witchery.entity.ai.EntityAISleep;
import com.emoniph.witchery.familiar.Familiar;
import com.emoniph.witchery.infusion.InfusedBrewEffect;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.item.ItemDeathsClothes;
import com.emoniph.witchery.item.ItemHunterClothes;
import com.emoniph.witchery.item.ItemMoonCharm;
import com.emoniph.witchery.item.ItemPoppet;
import com.emoniph.witchery.item.ItemVampireClothes;
import com.emoniph.witchery.network.PacketExtendedEntityRequestSyncToClient;
import com.emoniph.witchery.network.PacketHowl;
import com.emoniph.witchery.network.PacketParticles;
import com.emoniph.witchery.network.PacketPlayerStyle;
import com.emoniph.witchery.network.PacketSelectPlayerAbility;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.BoltDamageSource;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.CreatureUtil;
import com.emoniph.witchery.util.EntityUtil;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TameableUtil;
import com.emoniph.witchery.util.TargetPointUtil;
import com.emoniph.witchery.util.TimeUtil;
import com.emoniph.witchery.util.TransformCreature;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.IEntitySelector;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.ClickEvent;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.village.Village;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;
import net.minecraftforge.event.world.BlockEvent;

public class GenericEvents {
    @SubscribeEvent
    public void onServerChat(ServerChatEvent event) {
        boolean chatMasqueradeAllowed = Config.instance().allowChatMasquerading;
        ExtendedPlayer playerEx = ExtendedPlayer.get((EntityPlayer)event.player);
        if (playerEx != null && chatMasqueradeAllowed && playerEx.getCreatureType() == TransformCreature.PLAYER && playerEx.getOtherPlayerSkin() != null && !playerEx.getOtherPlayerSkin().isEmpty()) {
            ChatComponentTranslation comp;
            String disguise = playerEx.getOtherPlayerSkin();
            event.component = comp = new ChatComponentTranslation("chat.type.text", new Object[]{this.getPlayerChatName(event.player, disguise), ForgeHooks.newChatWithLinks((String)event.message)});
            if (!event.player.field_70170_p.field_72995_K) {
                for (Object otherPlayerObj : event.player.field_70170_p.field_73010_i) {
                    EntityPlayer otherPlayer = (EntityPlayer)otherPlayerObj;
                    if (!otherPlayer.field_71075_bZ.field_75098_d || !MinecraftServer.func_71276_C().func_71203_ab().func_152596_g(otherPlayer.func_146103_bH())) continue;
                    ChatUtil.sendTranslated(EnumChatFormatting.GOLD, (ICommandSender)otherPlayer, "witchery.rite.mirrormirror.opchatreveal", disguise, event.player.func_70005_c_());
                }
            }
        }
    }

    private IChatComponent getPlayerChatName(EntityPlayerMP player, String otherName) {
        ChatComponentText chatcomponenttext = new ChatComponentText(ScorePlayerTeam.func_96667_a((Team)player.func_96124_cp(), (String)otherName));
        chatcomponenttext.func_150256_b().func_150241_a(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + player.func_70005_c_() + " "));
        return chatcomponenttext;
    }

    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityPlayer && ExtendedPlayer.get((EntityPlayer)event.entity) == null) {
            ExtendedPlayer.register((EntityPlayer)event.entity);
        } else if (event.entity instanceof EntityVillager && ExtendedVillager.get((EntityVillager)event.entity) == null) {
            ExtendedVillager.register((EntityVillager)event.entity);
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGH)
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        EntityZombie creature;
        if (event.entity instanceof EntityLivingBase) {
            NBTTagCompound nbtData = event.entity.getEntityData();
            nbtData.func_74776_a("WITCInitialWidth", event.entity.field_70130_N);
            nbtData.func_74776_a("WITCInitialHeight", event.entity.field_70131_O);
        }
        if (!event.entity.field_70170_p.field_72995_K && event.entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.entity;
            ExtendedPlayer.loadProxyData(player);
            Shapeshift.INSTANCE.initCurrentShift(player);
            Infusion.syncPlayer(event.world, player);
            for (Object obj : event.world.field_73010_i) {
                EntityPlayer otherPlayer = (EntityPlayer)obj;
                if (otherPlayer == player) continue;
                Witchery.packetPipeline.sendTo((IMessage)new PacketPlayerStyle(otherPlayer), player);
            }
            Witchery.packetPipeline.sendToDimension(new PacketPlayerStyle(player), event.world.field_73011_w.field_76574_g);
            if (player.field_71093_bK != Config.instance().dimensionDreamID && WorldProviderDreamWorld.getPlayerIsSpiritWalking(player) && !WorldProviderDreamWorld.getPlayerIsGhost(player)) {
                WorldProviderDreamWorld.setPlayerMustAwaken(player, true);
            } else if (player.field_71093_bK == Config.instance().dimensionDreamID && !WorldProviderDreamWorld.getPlayerIsSpiritWalking(player)) {
                WorldProviderDreamWorld.changeDimension(player, 0);
                WorldProviderDreamWorld.findTopAndSetPosition(player.field_70170_p, player);
            }
        } else if (event.world.field_73011_w.field_76574_g == Config.instance().dimensionDreamID && GenericEvents.isDisallowedEntity(event.entity)) {
            event.setCanceled(true);
        }
        if (event.entity instanceof EntityVillager && !(event.entity instanceof EntityVillagerWere) && !(event.entity instanceof EntityVillageGuard)) {
            EntityVillager villager = (EntityVillager)event.entity;
            villager.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISleep(villager));
        } else if (event.entity instanceof EntityZombie) {
            creature = (EntityZombie)event.entity;
            creature.field_70715_bh.func_75776_a(3, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)creature, EntityFollower.class, 0, false, false, new IEntitySelector(){

                public boolean func_82704_a(Entity entity) {
                    return entity instanceof EntityFollower && ((EntityFollower)entity).getFollowerType() == 0;
                }
            }));
        } else if (event.entity instanceof EntitySkeleton) {
            creature = (EntitySkeleton)event.entity;
            creature.field_70715_bh.func_75776_a(3, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)creature, EntityFollower.class, 0, true, false, new IEntitySelector(){

                public boolean func_82704_a(Entity entity) {
                    return entity instanceof EntityFollower && ((EntityFollower)entity).getFollowerType() == 0;
                }
            }));
        }
        if (event.entity.field_70170_p.field_72995_K && event.entity instanceof EntityPlayer) {
            Witchery.packetPipeline.sendToServer(new PacketExtendedEntityRequestSyncToClient((EntityLivingBase)event.entity));
        }
    }

    @SubscribeEvent
    public void onPlayerCloneEvent(PlayerEvent.Clone event) {
        NBTTagCompound oldPlayerNBT = new NBTTagCompound();
        ExtendedPlayer oldPlayerEx = ExtendedPlayer.get(event.original);
        oldPlayerEx.saveNBTData(oldPlayerNBT);
        ExtendedPlayer newPlayerEx = ExtendedPlayer.get(event.entityPlayer);
        newPlayerEx.loadNBTData(oldPlayerNBT);
        newPlayerEx.restorePlayerInventoryFrom(oldPlayerEx);
    }

    private static boolean isDisallowedEntity(Entity entity) {
        if (entity instanceof EntityLiving) {
            Class<?> cls = entity.getClass();
            String packageName = cls.getCanonicalName();
            if (packageName.startsWith("net.minecraft.entity") || packageName.startsWith("com.emoniph.witchery")) {
                return entity instanceof EntityEnderman;
            }
            return true;
        }
        return false;
    }

    @SubscribeEvent
    public void onPlayerWakeUpEvent(PlayerWakeUpEvent event) {
        int z;
        int y;
        int x;
        EntityPlayer player;
        ExtendedPlayer playerEx;
        World world = event.entityPlayer.field_70170_p;
        if (!world.field_72995_K && (playerEx = ExtendedPlayer.get(player = event.entityPlayer)).isVampire() && player.func_71026_bH() && world.func_147439_a(x = MathHelper.func_76128_c((double)player.field_70165_t), y = MathHelper.func_76128_c((double)player.field_70163_u), z = MathHelper.func_76128_c((double)player.field_70161_v)) == Witchery.Blocks.COFFIN) {
            EntityPlayer entityplayer;
            Iterator iterator = world.field_73010_i.iterator();
            do {
                if (iterator.hasNext()) continue;
                long currentTime = world.func_72820_D() - 11000L;
                world.func_72877_b(currentTime);
                break;
            } while ((entityplayer = (EntityPlayer)iterator.next()).func_71026_bH());
        }
    }

    @SubscribeEvent
    public void onPlayerSleepInBed(PlayerSleepInBedEvent event) {
        World world = event.entityPlayer.field_70170_p;
        EntityPlayer player = event.entityPlayer;
        if (CreatureUtil.isWerewolf((Entity)event.entityPlayer)) {
            if (!world.field_72995_K) {
                ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)event.entityPlayer, "witchery.nosleep.wolf", new Object[0]);
                event.result = EntityPlayer.EnumStatus.OTHER_PROBLEM;
            }
        } else if (event.entityPlayer.func_70644_a(Witchery.Potions.RESIZING)) {
            if (!world.field_72995_K) {
                ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)event.entityPlayer, "witchery.nosleep.resized", new Object[0]);
                event.result = EntityPlayer.EnumStatus.OTHER_PROBLEM;
            }
        } else if (ExtendedPlayer.get(event.entityPlayer).isVampire() && world.func_147439_a(event.x, event.y, event.z) == Witchery.Blocks.COFFIN) {
            if (!event.entityPlayer.field_70170_p.func_72935_r()) {
                if (!world.field_72995_K) {
                    ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)event.entityPlayer, "witchery.nosleep.dayonly", new Object[0]);
                    event.result = EntityPlayer.EnumStatus.OTHER_PROBLEM;
                }
            } else {
                if (!world.field_72995_K) {
                    if (player.func_70608_bn() || !player.func_70089_S()) {
                        return;
                    }
                    if (!world.field_73011_w.func_76569_d()) {
                        return;
                    }
                    if (!world.func_72935_r()) {
                        event.result = EntityPlayer.EnumStatus.OTHER_PROBLEM;
                        return;
                    }
                    if (Math.abs(player.field_70165_t - (double)event.x) > 3.0 || Math.abs(player.field_70163_u - (double)event.y) > 2.0 || Math.abs(player.field_70161_v - (double)event.z) > 3.0) {
                        event.result = EntityPlayer.EnumStatus.TOO_FAR_AWAY;
                        return;
                    }
                    double d0 = 8.0;
                    double d1 = 5.0;
                    List list = world.func_72872_a(EntityMob.class, AxisAlignedBB.func_72330_a((double)((double)event.x - d0), (double)((double)event.y - d1), (double)((double)event.z - d0), (double)((double)event.x + d0), (double)((double)event.y + d1), (double)((double)event.z + d0)));
                    if (!list.isEmpty()) {
                        event.result = EntityPlayer.EnumStatus.NOT_SAFE;
                        return;
                    }
                }
                if (player.func_70115_ae()) {
                    player.func_70078_a((Entity)null);
                }
                PotionResizing.setEntitySize((Entity)player, 0.2f, 0.2f);
                player.field_70129_M = 0.2f;
                if (world.func_72899_e(event.x, event.y, event.z)) {
                    int l = world.func_147439_a(event.x, event.y, event.z).getBedDirection((IBlockAccess)world, event.x, event.y, event.z);
                    float f1 = 0.5f;
                    float f = 0.5f;
                    switch (l) {
                        case 0: {
                            f = 0.9f;
                            break;
                        }
                        case 1: {
                            f1 = 0.1f;
                            break;
                        }
                        case 2: {
                            f = 0.1f;
                            break;
                        }
                        case 3: {
                            f1 = 0.9f;
                        }
                    }
                    player.field_71079_bU = 0.0f;
                    player.field_71089_bV = 0.0f;
                    switch (l) {
                        case 0: {
                            player.field_71089_bV = -1.8f;
                            break;
                        }
                        case 1: {
                            player.field_71079_bU = 1.8f;
                            break;
                        }
                        case 2: {
                            player.field_71089_bV = 1.8f;
                            break;
                        }
                        case 3: {
                            player.field_71079_bU = -1.8f;
                        }
                    }
                    player.func_70107_b((double)((float)event.x + f1), (double)((float)event.y + 0.9375f), (double)((float)event.z + f));
                } else {
                    player.func_70107_b((double)((float)event.x + 0.5f), (double)((float)event.y + 0.9375f), (double)((float)event.z + 0.5f));
                }
                player.field_71083_bS = true;
                player.field_71076_b = 0;
                player.field_71081_bT = new ChunkCoordinates(event.x, event.y, event.z);
                player.field_70181_x = 0.0;
                player.field_70179_y = 0.0;
                player.field_70159_w = 0.0;
                if (!world.field_72995_K) {
                    world.func_72854_c();
                }
                event.result = EntityPlayer.EnumStatus.OK;
                return;
            }
        }
    }

    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        if (!event.isCanceled() && event.entityLiving != null && !event.entityLiving.field_70170_p.field_72995_K) {
            EntityHorse horse;
            NBTTagCompound nbtHorse;
            if (event.entityLiving instanceof EntityLiving && EntityUtil.isNoDrops((EntityLivingBase)((EntityLiving)event.entityLiving))) {
                event.setCanceled(true);
                return;
            }
            if (event.entityLiving instanceof EntityHorse && (nbtHorse = (horse = (EntityHorse)event.entityLiving).getEntityData()) != null && nbtHorse.func_74767_n("WITCIsBinky")) {
                event.drops.clear();
                event.drops.add(new EntityItem(horse.field_70170_p, horse.field_70165_t, horse.field_70163_u, horse.field_70161_v, Witchery.Items.GENERIC.itemBinkyHead.createStack()));
            }
        }
    }

    @SubscribeEvent
    public void onItemToss(ItemTossEvent event) {
        if (!event.isCanceled() && !event.player.field_70170_p.field_72995_K && event.entityItem != null && event.entityItem.func_92059_d() != null) {
            if (event.entityItem.func_92059_d().func_77973_b() == Witchery.Items.SEEDS_MINDRAKE) {
                event.entityItem.lifespan = TimeUtil.secsToTicks(3);
                NBTTagCompound nbtItem = event.entityItem.getEntityData();
                nbtItem.func_74778_a("WITCThrower", event.player.func_70005_c_());
            } else if (Witchery.Items.GENERIC.itemWaystone.isMatch(event.entityItem.func_92059_d()) || Witchery.Items.GENERIC.itemWaystoneBound.isMatch(event.entityItem.func_92059_d()) || Witchery.Items.GENERIC.itemAttunedStone.isMatch(event.entityItem.func_92059_d()) || Witchery.Items.GENERIC.itemSubduedSpirit.isMatch(event.entityItem.func_92059_d()) || Witchery.Items.GENERIC.itemWaystonePlayerBound.isMatch(event.entityItem.func_92059_d())) {
                EntityUtil.spawnEntityInWorld(event.entity.field_70170_p, (Entity)new EntityItemWaystone(event.entityItem));
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void onItemExpireEvent(ItemExpireEvent event) {
        if (!event.isCanceled() && !event.entityItem.field_70170_p.field_72995_K && event.entityItem != null && event.entityItem.func_92059_d() != null && event.entityItem.func_92059_d().func_77973_b() == Witchery.Items.SEEDS_MINDRAKE) {
            for (int i = 0; i < event.entityItem.func_92059_d().field_77994_a; ++i) {
                String thrower;
                EntityMindrake mindrake = new EntityMindrake(event.entityItem.field_70170_p);
                mindrake.func_70012_b(event.entityItem.field_70165_t, event.entityItem.field_70163_u, event.entityItem.field_70161_v, 0.0f, 0.0f);
                NBTTagCompound nbtItem = event.entityItem.getEntityData();
                if (nbtItem.func_74764_b("WITCThrower") && (thrower = nbtItem.func_74779_i("WITCThrower")) != null && !thrower.isEmpty()) {
                    mindrake.func_110163_bv();
                    mindrake.func_70903_f(true);
                    TameableUtil.setOwnerByUsername(mindrake, thrower);
                }
                ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_POP, (Entity)mindrake, 1.0, 1.0, 16);
                event.entityItem.field_70170_p.func_72838_d((Entity)mindrake);
            }
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGH)
    public void onPlayerDrops(PlayerDropsEvent event) {
        if (!event.entityPlayer.field_70170_p.field_72995_K && !event.isCanceled() && ExtendedPlayer.get(event.entityPlayer).isVampire()) {
            int ticks = TimeUtil.minsToTicks(MathHelper.func_76125_a((int)Config.instance().vampireDeathItemKeepAliveMins, (int)5, (int)30));
            for (EntityItem item : event.drops) {
                item.lifespan = ticks;
            }
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGH)
    public void onEntityInteract(EntityInteractEvent event) {
        ItemStack heldStack;
        PotionEffect effect = event.entityPlayer.func_70660_b(Witchery.Potions.PARALYSED);
        if (effect != null && effect.func_76458_c() >= 4) {
            event.setCanceled(true);
            return;
        }
        ExtendedPlayer playerEx = ExtendedPlayer.get(event.entityPlayer);
        ExtendedPlayer.VampirePower power = playerEx.getSelectedVampirePower();
        if (power != ExtendedPlayer.VampirePower.NONE) {
            if (power == ExtendedPlayer.VampirePower.DRINK && event.target instanceof EntityLivingBase) {
                if (!event.entityPlayer.field_70170_p.field_72995_K) {
                    float RANGE;
                    float f = RANGE = ((EntityLivingBase)event.target).func_70644_a(Witchery.Potions.PARALYSED) ? 2.1f : 1.3f;
                    if (event.target.func_70092_e(event.entityPlayer.field_70165_t, event.target.field_70163_u, event.entityPlayer.field_70161_v) <= (double)(RANGE * RANGE)) {
                        EntityVillageGuard target;
                        int drinkAmount;
                        int n = drinkAmount = ItemVampireClothes.isDrinkBoostActive((EntityLivingBase)event.entityPlayer) ? 15 : 10;
                        if (CreatureUtil.isWerewolf(event.target, true)) {
                            event.entityPlayer.func_70097_a((DamageSource)new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), (Entity)event.entityPlayer), 4.0f);
                            ParticleEffect.FLAME.send(SoundEffect.WITCHERY_RANDOM_DRINK, event.entityPlayer.field_70170_p, event.target.field_70165_t, event.target.field_70163_u + (double)event.target.field_70131_O * 0.8, event.target.field_70161_v, 0.5, 0.2, 16);
                        } else if (event.target instanceof EntityVillageGuard) {
                            target = (EntityVillageGuard)event.target;
                            playerEx.increaseBloodPower(target.takeBlood(playerEx.getCreatureType() == TransformCreature.NONE ? drinkAmount : 2, (EntityLivingBase)event.entityPlayer));
                            ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, event.entityPlayer.field_70170_p, target.field_70165_t, target.field_70163_u + (double)target.field_70131_O * 0.8, target.field_70161_v, 0.5, 0.2, 16);
                            this.checkForBloodDrinkingWitnesses(event.entityPlayer, (EntityLivingBase)target);
                        } else if (event.target instanceof EntityVillager) {
                            target = (EntityVillager)event.target;
                            ExtendedVillager villagerEx = ExtendedVillager.get((EntityVillager)target);
                            playerEx.increaseBloodPower(villagerEx.takeBlood(playerEx.getCreatureType() == TransformCreature.NONE ? drinkAmount : 2, (EntityLivingBase)event.entityPlayer));
                            ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, event.entityPlayer.field_70170_p, ((EntityVillager)target).field_70165_t, ((EntityVillager)target).field_70163_u + (double)((EntityVillager)target).field_70131_O * 0.8, ((EntityVillager)target).field_70161_v, 0.5, 0.2, 16);
                            this.checkForBloodDrinkingWitnesses(event.entityPlayer, (EntityLivingBase)target);
                            if (playerEx.getVampireLevel() == 2) {
                                if (Config.instance().allowVampireQuests && villagerEx.getBlood() >= 250 && villagerEx.getBlood() <= 280) {
                                    if (playerEx.getVampireQuestCounter() >= 5) {
                                        playerEx.increaseVampireLevel();
                                    } else {
                                        SoundEffect.NOTE_PLING.playOnlyTo(event.entityPlayer, 1.0f, 1.0f);
                                        playerEx.increaseVampireQuestCounter();
                                    }
                                } else if (villagerEx.getBlood() < 240) {
                                    playerEx.resetVampireQuestCounter();
                                }
                            } else if (playerEx.getVampireLevel() == 8 && playerEx.canIncreaseVampireLevel() && this.villagerIsInCage((EntityVillager)target)) {
                                if (villagerEx.getBlood() >= 250 && villagerEx.getBlood() <= 280) {
                                    if (playerEx.getVampireQuestCounter() >= 5) {
                                        playerEx.increaseVampireLevel();
                                    } else {
                                        SoundEffect.NOTE_PLING.playOnlyTo(event.entityPlayer, 1.0f, 1.0f);
                                        playerEx.increaseVampireQuestCounter();
                                    }
                                } else if (villagerEx.getBlood() < 240) {
                                    playerEx.resetVampireQuestCounter();
                                }
                            }
                        } else if (event.target instanceof EntityPlayer) {
                            target = (EntityPlayer)event.target;
                            playerEx.increaseBloodPower(ExtendedPlayer.get((EntityPlayer)target).takeHumanBlood(playerEx.getCreatureType() == TransformCreature.NONE ? drinkAmount : 2, (EntityLivingBase)event.entityPlayer));
                            ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, event.entityPlayer.field_70170_p, ((EntityPlayer)target).field_70165_t, ((EntityPlayer)target).field_70163_u + (double)((EntityPlayer)target).field_70131_O * 0.8, ((EntityPlayer)target).field_70161_v, 0.5, 0.2, 16);
                        } else if (event.target instanceof IAnimals) {
                            target = (EntityLivingBase)event.target;
                            target.func_70097_a((DamageSource)new EntityDamageSource(DamageSource.field_76376_m.func_76355_l(), (Entity)event.entityPlayer), 1.0f);
                            playerEx.increaseBloodPower(2, (int)Math.ceil((float)playerEx.getMaxBloodPower() * 0.25f));
                            ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, event.entityPlayer.field_70170_p, ((EntityLivingBase)target).field_70165_t, ((EntityLivingBase)target).field_70163_u + (double)((EntityLivingBase)target).field_70131_O * 0.8, ((EntityLivingBase)target).field_70161_v, 0.5, 0.2, 16);
                        }
                    }
                    event.setCanceled(true);
                }
            } else if (power == ExtendedPlayer.VampirePower.MESMERIZE) {
                if (!event.entityPlayer.field_70170_p.field_72995_K) {
                    if (event.entityPlayer.func_70093_af() && playerEx.getVampireLevel() >= 2) {
                        playerEx.toggleVampireVision();
                    } else if (playerEx.getCreatureType() == TransformCreature.NONE && playerEx.getVampireLevel() >= 2) {
                        if (event.target instanceof EntityVillager && !(event.target instanceof EntityVillagerWere) || event.target instanceof EntityPlayer || event.target instanceof EntityVillageGuard) {
                            EntityLivingBase victim = (EntityLivingBase)event.target;
                            if (!victim.func_70644_a(Witchery.Potions.PARALYSED)) {
                                if (playerEx.decreaseBloodPower(ExtendedPlayer.VampirePower.MESMERIZE.INITIAL_COST, true)) {
                                    victim.func_70690_d(new PotionEffect(Witchery.Potions.PARALYSED.field_76415_H, TimeUtil.secsToTicks(5 + playerEx.getVampireLevel() / 2 + Math.max(0, (playerEx.getVampireLevel() - 4) / 2) + (ItemVampireClothes.isMezmeriseBoostActive((EntityLivingBase)event.entityPlayer) ? 3 : 0)), playerEx.getVampireLevel() >= 8 ? 5 : 4));
                                    SoundEffect.WITCHERY_RANDOM_HYPNOSIS.playAtPlayer(event.entity.field_70170_p, event.entityPlayer, 0.5f, 1.0f);
                                } else {
                                    SoundEffect.NOTE_SNARE.playOnlyTo(event.entityPlayer, 1.0f, 0.5f);
                                }
                            } else {
                                SoundEffect.NOTE_SNARE.playOnlyTo(event.entityPlayer, 1.0f, 0.5f);
                            }
                        } else {
                            SoundEffect.NOTE_SNARE.playOnlyTo(event.entityPlayer, 1.0f, 0.5f);
                        }
                    } else {
                        SoundEffect.NOTE_SNARE.playOnlyTo(event.entityPlayer, 1.0f, 0.5f);
                    }
                    event.setCanceled(true);
                }
            } else {
                event.setCanceled(true);
            }
        }
        if (event.isCanceled()) {
            return;
        }
        if (event.target != null && !event.target.field_70170_p.field_72995_K && event.target instanceof EntityLiving && PotionEnslaved.isMobEnslavedBy((EntityLiving)event.target, event.entityPlayer)) {
            EntityPlayer player = event.entityPlayer;
            EntityLiving creature = (EntityLiving)event.target;
            ItemStack heldObject = player.func_70694_bm();
            if (Witchery.Items.GENERIC.itemGraveyardDust.isMatch(heldObject) && creature instanceof EntitySummonedUndead) {
                IAttributeInstance attribute;
                float maxHealth = creature.func_110138_aP() + 2.0f;
                if (maxHealth <= 50.0f && (attribute = creature.func_110148_a(SharedMonsterAttributes.field_111267_a)) != null) {
                    attribute.func_111128_a((double)maxHealth);
                    creature.func_70606_j(maxHealth);
                    creature.func_110163_bv();
                    Witchery.packetPipeline.sendToAllAround(new PacketParticles(ParticleEffect.INSTANT_SPELL, SoundEffect.MOB_SILVERFISH_KILL, (Entity)creature, 0.5, 1.0), TargetPointUtil.from((Entity)creature, 16.0));
                    if (!player.field_71075_bZ.field_75098_d) {
                        --heldObject.field_77994_a;
                        if (player instanceof EntityPlayerMP) {
                            ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
                        }
                    }
                }
            } else if (heldObject != null && (creature instanceof EntityZombie || creature instanceof EntityPigZombie || creature instanceof EntitySkeleton)) {
                if (heldObject.func_77973_b() instanceof ItemArmor) {
                    ItemArmor armor = (ItemArmor)heldObject.func_77973_b();
                    if (creature.func_71124_b(4 - armor.field_77881_a) == null) {
                        creature.func_70062_b(4 - armor.field_77881_a, heldObject.func_77979_a(1));
                        creature.func_110163_bv();
                        if (player instanceof EntityPlayerMP) {
                            ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
                        }
                    }
                } else if (heldObject.func_77973_b() instanceof ItemSword && creature.func_71124_b(0) == null) {
                    creature.func_70062_b(0, heldObject.func_77979_a(1));
                    creature.func_110163_bv();
                    if (player instanceof EntityPlayerMP) {
                        ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
                    }
                }
            }
        }
        if (event.target != null && !event.target.field_70170_p.field_72995_K && event.target instanceof EntityVillager) {
            Village village;
            EntityVillager villager = (EntityVillager)event.target;
            ItemStack heldObject = event.entityPlayer.func_70694_bm();
            if (!villager.func_70631_g_() && heldObject != null && heldObject.func_77973_b() == Items.field_151027_R && event.entityPlayer.func_70093_af() && (village = villager.field_70954_d) != null) {
                int rep = village.func_82684_a(event.entityPlayer.func_70005_c_());
                if (rep >= 10) {
                    if (village.func_75562_e() > 8) {
                        List list = event.entity.field_70170_p.func_72872_a(EntityVillageGuard.class, AxisAlignedBB.func_72330_a((double)(village.func_75577_a().field_71574_a - village.func_75568_b()), (double)(village.func_75577_a().field_71572_b - 4), (double)(village.func_75577_a().field_71573_c - village.func_75568_b()), (double)(village.func_75577_a().field_71574_a + village.func_75568_b()), (double)(village.func_75577_a().field_71572_b + 4), (double)(village.func_75577_a().field_71573_c + village.func_75568_b())));
                        int numGuards = list.size();
                        if (numGuards < MathHelper.func_76128_c((double)((double)village.func_75562_e() * 0.25))) {
                            int villagerNumTrades;
                            int n = villagerNumTrades = villager.field_70963_i == null ? 1 : villager.field_70963_i.size();
                            if (!CreatureUtil.isWerewolf(event.target, true) && event.target.field_70170_p.field_73012_v.nextInt(villagerNumTrades * 2 + 1) == 0) {
                                villager.func_85030_a("mob.villager.yew", 1.0f, (villager.field_70170_p.field_73012_v.nextFloat() - villager.field_70170_p.field_73012_v.nextFloat()) * 0.2f + 1.0f);
                                ChatUtil.sendTranslated(EnumChatFormatting.GREEN, (ICommandSender)event.entityPlayer, "witchery.village.villageracceptsguardduty", new Object[0]);
                                EntityVillageGuard.createFrom(villager);
                            } else {
                                ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)event.entityPlayer, "witchery.village.villagerrefusesguardduty", new Object[0]);
                                villager.func_85030_a("mob.villager.no", 1.0f, (villager.field_70170_p.field_73012_v.nextFloat() - villager.field_70170_p.field_73012_v.nextFloat()) * 0.2f + 1.0f);
                            }
                        } else {
                            ChatUtil.sendTranslated(EnumChatFormatting.BLUE, (ICommandSender)event.entityPlayer, "witchery.village.toomanyguards", new Object[0]);
                            SoundEffect.NOTE_SNARE.playOnlyTo(event.entityPlayer);
                        }
                    } else {
                        ChatUtil.sendTranslated(EnumChatFormatting.BLUE, (ICommandSender)event.entityPlayer, "witchery.village.villagetoosmall", new Object[0]);
                        SoundEffect.NOTE_SNARE.playOnlyTo(event.entityPlayer);
                    }
                } else {
                    ChatUtil.sendTranslated(EnumChatFormatting.BLUE, (ICommandSender)event.entityPlayer, "witchery.village.reptoolow", new Object[0]);
                    SoundEffect.NOTE_SNARE.playOnlyTo(event.entityPlayer);
                }
            }
        }
        if (!event.entity.field_70170_p.field_72995_K && event.target != null && event.target instanceof EntityWolf) {
            EntityWolf wolf = (EntityWolf)event.target;
            if (playerEx.getWerewolfLevel() == 7 && playerEx.getWolfmanQuestState() == ExtendedPlayer.QuestState.STARTED && playerEx.getCreatureType() == TransformCreature.WOLF && !wolf.func_70909_n() && !wolf.func_70919_bu()) {
                if (wolf.field_70170_p.field_73012_v.nextInt(3) == 0) {
                    wolf.func_70903_f(true);
                    wolf.func_70778_a((PathEntity)null);
                    wolf.func_70624_b((EntityLivingBase)null);
                    wolf.func_70907_r().func_75270_a(true);
                    wolf.func_70606_j(20.0f);
                    wolf.func_152115_b(event.entityPlayer.func_110124_au().toString());
                    this.playTameEffect((EntityTameable)wolf, true);
                    wolf.field_70170_p.func_72960_a((Entity)wolf, (byte)7);
                    playerEx.increaseWolfmanQuestCounter();
                } else {
                    this.playTameEffect((EntityTameable)wolf, false);
                    wolf.field_70170_p.func_72960_a((Entity)wolf, (byte)6);
                    if (wolf.field_70170_p.field_73012_v.nextInt(10) == 0) {
                        wolf.func_70916_h(true);
                        wolf.func_70624_b((EntityLivingBase)event.entityPlayer);
                    }
                }
            }
        }
        if ((heldStack = event.entityPlayer.func_70694_bm()) != null) {
            EntityPlayer victim;
            if (heldStack.func_77973_b() == Witchery.Items.TAGLOCK_KIT) {
                Witchery.Items.TAGLOCK_KIT.onEntityInteract(event.entityPlayer.field_70170_p, event.entityPlayer, heldStack, event);
                if (event.isCanceled()) {
                    return;
                }
            }
            if (heldStack.func_77973_b() == Witchery.Items.BLOOD_GOBLET) {
                Witchery.Items.BLOOD_GOBLET.onEntityInteract(event.entityPlayer.field_70170_p, event.entityPlayer, heldStack, event);
                if (event.isCanceled()) {
                    return;
                }
            }
            if (Witchery.Items.GENERIC.itemWoodenStake.isMatch(heldStack) && Config.instance().allowStakingVampires && event.target instanceof EntityPlayer && ExtendedPlayer.get(victim = (EntityPlayer)event.target).isVampire() && victim.field_71083_bS) {
                ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, victim.field_70170_p, event.target.field_70165_t, event.target.field_70163_u, event.target.field_70161_v, event.target.field_70130_N, event.target.field_70131_O, 16);
                EntityUtil.instantDeath((EntityLivingBase)victim, (EntityLivingBase)event.entityPlayer);
                if (!event.entityPlayer.field_71075_bZ.field_75098_d) {
                    --heldStack.field_77994_a;
                }
                event.setCanceled(true);
                return;
            }
        }
    }

    private boolean villagerIsInCage(EntityVillager target) {
        int ogZ;
        int ogY;
        int ogX = MathHelper.func_76128_c((double)target.field_70165_t);
        if (this.isCaged(target.field_70170_p, ogX, ogY = MathHelper.func_76128_c((double)target.field_70163_u), ogZ = MathHelper.func_76128_c((double)target.field_70161_v))) {
            return true;
        }
        if (this.isCaged(target.field_70170_p, ogX + 1, ogY, ogZ)) {
            return true;
        }
        if (this.isCaged(target.field_70170_p, ogX, ogY, ogZ + 1)) {
            return true;
        }
        if (this.isCaged(target.field_70170_p, ogX - 1, ogY, ogZ)) {
            return true;
        }
        if (this.isCaged(target.field_70170_p, ogX, ogY, ogZ - 1)) {
            return true;
        }
        if (this.isCaged(target.field_70170_p, ogX + 1, ogY, ogZ + 1)) {
            return true;
        }
        if (this.isCaged(target.field_70170_p, ogX + 1, ogY, ogZ - 1)) {
            return true;
        }
        if (this.isCaged(target.field_70170_p, ogX - 1, ogY, ogZ + 1)) {
            return true;
        }
        return this.isCaged(target.field_70170_p, ogX - 1, ogY, ogZ - 1);
    }

    private boolean isCaged(World world, int x, int y, int z) {
        int count = 0;
        Block bars = Blocks.field_150411_aY;
        count += world.func_147439_a(x + 1, y, z) == bars ? 1 : 0;
        count += world.func_147439_a(x, y, z + 1) == bars ? 1 : 0;
        count += world.func_147439_a(x - 1, y, z) == bars ? 1 : 0;
        count += world.func_147439_a(x, y, z - 1) == bars ? 1 : 0;
        count += world.func_147439_a(x + 1, y, z + 1) == bars ? 1 : 0;
        count += world.func_147439_a(x - 1, y, z + 1) == bars ? 1 : 0;
        count += world.func_147439_a(x + 1, y, z - 1) == bars ? 1 : 0;
        count += world.func_147439_a(x - 1, y, z - 1) == bars ? 1 : 0;
        count += world.func_147439_a(x + 1, ++y, z) == bars ? 1 : 0;
        count += world.func_147439_a(x, y, z + 1) == bars ? 1 : 0;
        count += world.func_147439_a(x - 1, y, z) == bars ? 1 : 0;
        count += world.func_147439_a(x, y, z - 1) == bars ? 1 : 0;
        count += world.func_147439_a(x + 1, y, z + 1) == bars ? 1 : 0;
        count += world.func_147439_a(x - 1, y, z + 1) == bars ? 1 : 0;
        count += world.func_147439_a(x + 1, y, z - 1) == bars ? 1 : 0;
        if ((count += world.func_147439_a(x - 1, y, z - 1) == bars ? 1 : 0) < 15) {
            return false;
        }
        count = 0;
        count += !BlockUtil.isReplaceableBlock(world, x + 1, ++y, z) ? 1 : 0;
        count += !BlockUtil.isReplaceableBlock(world, x, y, z + 1) ? 1 : 0;
        count += !BlockUtil.isReplaceableBlock(world, x - 1, y, z) ? 1 : 0;
        count += !BlockUtil.isReplaceableBlock(world, x, y, z - 1) ? 1 : 0;
        count += !BlockUtil.isReplaceableBlock(world, x + 1, y, z + 1) ? 1 : 0;
        count += !BlockUtil.isReplaceableBlock(world, x - 1, y, z + 1) ? 1 : 0;
        count += !BlockUtil.isReplaceableBlock(world, x + 1, y, z - 1) ? 1 : 0;
        count += !BlockUtil.isReplaceableBlock(world, x - 1, y, z - 1) ? 1 : 0;
        return (count += !BlockUtil.isReplaceableBlock(world, x, y, z) ? 1 : 0) >= 9;
    }

    private void checkForBloodDrinkingWitnesses(EntityPlayer player, EntityLivingBase victim) {
        AxisAlignedBB bounds = victim.field_70121_D.func_72314_b(16.0, 8.0, 16.0);
        List guards = victim.field_70170_p.func_72872_a(EntityVillageGuard.class, bounds);
        for (EntityVillageGuard guard : guards) {
            if (guard.func_70644_a(Witchery.Potions.PARALYSED) || !guard.func_70635_at().func_75522_a((Entity)victim)) continue;
            guard.func_70624_b((EntityLivingBase)player);
        }
    }

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) {
        PotionEffect effect = event.entityPlayer.func_70660_b(Witchery.Potions.PARALYSED);
        if (effect != null && effect.func_76458_c() >= 4) {
            event.setCanceled(true);
            return;
        }
        ExtendedPlayer playerEx = ExtendedPlayer.get(event.entityPlayer);
        if (playerEx.getSelectedVampirePower() != ExtendedPlayer.VampirePower.NONE) {
            if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR || event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
                switch (playerEx.getSelectedVampirePower()) {
                    case MESMERIZE: 
                    case SPEED: 
                    case BAT: 
                    case ULTIMATE: {
                        Witchery.packetPipeline.sendToServer(new PacketSelectPlayerAbility(playerEx, true));
                        break;
                    }
                }
                event.setCanceled(true);
            }
        } else if (event.entityPlayer.field_70170_p.field_72995_K) {
            if ((event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR || event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) && event.entityPlayer.field_70125_A == -90.0f && event.entityPlayer.func_70093_af()) {
                Witchery.packetPipeline.sendToServer(new PacketHowl());
            }
        } else if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK && !event.entityPlayer.field_71075_bZ.field_75098_d) {
            Block block;
            if (playerEx.isVampire() && event.world.func_147439_a(event.x, event.y, event.z) == Witchery.Blocks.GARLIC_GARLAND) {
                event.entityPlayer.func_70015_d(1);
                event.setCanceled(true);
            } else if (playerEx.getCreatureType() == TransformCreature.WOLF && playerEx.getWerewolfLevel() >= 3 && event.entityPlayer.func_70093_af()) {
                Block block2 = event.world.func_147439_a(event.x, event.y, event.z);
                if (block2 == Blocks.field_150349_c || block2 == Blocks.field_150354_m || block2 == Blocks.field_150346_d || block2 == Blocks.field_150391_bh || block2 == Blocks.field_150351_n) {
                    EntityAIDigBlocks.tryHarvestBlock(event.world, event.x, event.y, event.z, (EntityLivingBase)event.entityPlayer, event.entityPlayer);
                    event.setCanceled(true);
                }
            } else if (!(playerEx.getVampireLevel() < 6 || playerEx.getCreatureType() != TransformCreature.NONE || !event.entityPlayer.func_70093_af() || event.entityPlayer.func_70694_bm() != null && event.entityPlayer.func_70694_bm().func_77973_b().func_150897_b(Blocks.field_150348_b) || event.entityPlayer.func_71024_bL().func_75116_a() <= 0 || (block = event.world.func_147439_a(event.x, event.y, event.z)) != Blocks.field_150348_b && block != Blocks.field_150424_aL && block != Blocks.field_150347_e)) {
                EntityAIDigBlocks.tryHarvestBlock(event.world, event.x, event.y, event.z, (EntityLivingBase)event.entityPlayer, event.entityPlayer);
                event.entityPlayer.func_71020_j(10.0f);
                event.setCanceled(true);
            }
        }
    }

    private void playTameEffect(EntityTameable entity, boolean tamed) {
        String s = "heart";
        if (!tamed) {
            s = "smoke";
        }
        for (int i = 0; i < 7; ++i) {
            double d0 = entity.field_70170_p.field_73012_v.nextGaussian() * 0.02;
            double d1 = entity.field_70170_p.field_73012_v.nextGaussian() * 0.02;
            double d2 = entity.field_70170_p.field_73012_v.nextGaussian() * 0.02;
            entity.field_70170_p.func_72869_a(s, entity.field_70165_t + (double)(entity.field_70170_p.field_73012_v.nextFloat() * entity.field_70130_N * 2.0f) - (double)entity.field_70130_N, entity.field_70163_u + 0.5 + (double)(entity.field_70170_p.field_73012_v.nextFloat() * entity.field_70131_O), entity.field_70161_v + (double)(entity.field_70170_p.field_73012_v.nextFloat() * entity.field_70130_N * 2.0f) - (double)entity.field_70130_N, d0, d1, d2);
        }
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if (!event.entity.field_70170_p.field_72995_K && event.entity instanceof EntityPlayer) {
            int hunger;
            int prevHunger;
            EntityPlayer player = (EntityPlayer)event.entity;
            ExtendedPlayer playerEx = ExtendedPlayer.get(player);
            Shapeshift.INSTANCE.updatePlayerState(player, playerEx);
            playerEx.tick();
            if (playerEx.isVampire() && (prevHunger = player.func_71024_bL().field_75124_e) < (hunger = player.func_71024_bL().func_75116_a())) {
                player.func_71024_bL().func_75122_a(-player.func_71024_bL().func_75116_a(), 0.0f);
            }
            if (event.entity.field_70173_aa % 40 == 1) {
                if (playerEx.getWerewolfLevel() > 0) {
                    boolean isFullMoon = CreatureUtil.isFullMoon(player.field_70170_p);
                    switch (playerEx.getCreatureType()) {
                        case WOLF: 
                        case WOLFMAN: {
                            boolean isWolfman;
                            boolean bl = isWolfman = playerEx.getCreatureType() == TransformCreature.WOLFMAN;
                            if (!(isFullMoon || player.field_71071_by.func_146028_b(Witchery.Items.MOON_CHARM) || ItemMoonCharm.isWolfsbaneActive(player, playerEx))) {
                                Shapeshift.INSTANCE.shiftTo(player, TransformCreature.NONE);
                                ParticleEffect.EXPLODE.send(SoundEffect.RANDOM_FIZZ, (Entity)player, 1.5, 1.5, 16);
                                break;
                            }
                            GenericEvents.updateWerewolfEffects(player, isWolfman);
                            break;
                        }
                        case NONE: {
                            if (!isFullMoon || player.field_71071_by.func_146028_b(Witchery.Items.MOON_CHARM) || ItemMoonCharm.isWolfsbaneActive(player, playerEx)) break;
                            Shapeshift.INSTANCE.shiftTo(player, TransformCreature.WOLF);
                            ParticleEffect.EXPLODE.send(SoundEffect.WITCHERY_MOB_WOLFMAN_HOWL, (Entity)player, 1.5, 1.5, 16);
                            GenericEvents.updateWerewolfEffects(player, false);
                            break;
                        }
                    }
                }
                if (playerEx.isVampire()) {
                    Village closestVillage;
                    if (player.func_70090_H()) {
                        player.func_70050_g(300);
                    }
                    if (playerEx.getCreatureType() == TransformCreature.BAT && !playerEx.decreaseBloodPower(ExtendedPlayer.VampirePower.BAT.UPKEEP_COST, true)) {
                        Shapeshift.INSTANCE.shiftTo(player, TransformCreature.NONE);
                    }
                    if (playerEx.getVampireLevel() == 3 && !player.field_70170_p.func_72935_r()) {
                        if (playerEx.getVampireQuestCounter() >= 300 || playerEx.getVampireQuestCounter() >= 10 && player.field_71075_bZ.field_75098_d) {
                            if (playerEx.canIncreaseVampireLevel()) {
                                playerEx.increaseVampireLevel();
                            }
                        } else if (Config.instance().allowVampireQuests) {
                            playerEx.increaseVampireQuestCounter();
                        }
                    }
                    if (playerEx.getVampireLevel() == 7 && playerEx.canIncreaseVampireLevel() && (closestVillage = player.field_70170_p.field_72982_D.func_75550_a(MathHelper.func_76128_c((double)player.field_70165_t), MathHelper.func_76128_c((double)player.field_70163_u), MathHelper.func_76128_c((double)player.field_70161_v), 32)) != null && playerEx.storeVampireQuestChunk(closestVillage.func_75577_a().field_71574_a >> 4, closestVillage.func_75577_a().field_71573_c >> 4)) {
                        if (playerEx.getVampireQuestCounter() >= 3) {
                            playerEx.increaseVampireLevel();
                        } else {
                            playerEx.increaseVampireQuestCounter();
                            SoundEffect.NOTE_PLING.playOnlyTo(player, 1.0f, 1.0f);
                        }
                    }
                    if (playerEx.isVampireVisionActive()) {
                        player.func_70690_d(new PotionEffect(Potion.field_76439_r.field_76415_H, 400, 0, true));
                    }
                    if (player.func_70644_a(Potion.field_76436_u)) {
                        player.func_82170_o(Potion.field_76436_u.field_76415_H);
                    }
                    if (player.func_70027_ad() && player.func_70644_a(Potion.field_76426_n)) {
                        player.func_70097_a((DamageSource)EntityUtil.DamageSourceVampireFire.SOURCE, 2.0f);
                    }
                    while (player.func_71024_bL().func_75116_a() < 20 && playerEx.decreaseBloodPower(5, true)) {
                        player.func_71024_bL().func_75122_a(1, 4.0f);
                    }
                    if (playerEx.getBloodPower() == 0 && player.func_71024_bL().func_75116_a() == 0) {
                        player.func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, TimeUtil.secsToTicks(10), 8, true));
                        player.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, TimeUtil.secsToTicks(10), 1, true));
                        player.func_70690_d(new PotionEffect(Potion.field_76419_f.field_76415_H, TimeUtil.secsToTicks(10), 1, true));
                    }
                    if (CreatureUtil.isInSunlight((EntityLivingBase)player) && !player.field_71075_bZ.field_75098_d) {
                        if (playerEx.getBloodPower() == 0 && player.field_70173_aa > 400) {
                            EntityUtil.instantDeath((EntityLivingBase)player, null);
                        }
                        if (playerEx.getVampireLevel() >= 5) {
                            playerEx.decreaseBloodPower(60, false);
                            player.func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, TimeUtil.secsToTicks(10), 3, false));
                            player.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, TimeUtil.secsToTicks(10), 0, true));
                            player.func_70690_d(new PotionEffect(Potion.field_76419_f.field_76415_H, TimeUtil.secsToTicks(10), 0, true));
                        } else {
                            playerEx.setBloodPower(0);
                        }
                        if (playerEx.getBloodPower() == 0) {
                            player.func_70015_d(5);
                        }
                    }
                } else {
                    playerEx.giveHumanBlood(2);
                }
            }
        }
    }

    public static void updateWerewolfEffects(EntityPlayer player, boolean isWolfman) {
        int slot;
        player.func_70690_d(new PotionEffect(Potion.field_76439_r.field_76415_H, 400, 0, true));
        if (player.func_70644_a(Potion.field_76436_u)) {
            player.func_82170_o(Potion.field_76436_u.field_76415_H);
        }
        int n = slot = isWolfman ? 0 : 1;
        while (slot <= 4) {
            ItemStack stack = player.func_71124_b(slot);
            if (stack != null && stack.func_77973_b() != Witchery.Items.MOON_CHARM && (player.field_71070_bA == null || player.field_71070_bA.field_75152_c == 0 || slot != 0)) {
                player.func_70099_a(stack, 1.0f);
                player.func_70062_b(slot, null);
            }
            ++slot;
        }
    }

    @SubscribeEvent
    public void onLivingFall(LivingFallEvent event) {
        if (!event.entity.field_70170_p.field_72995_K && event.entity instanceof EntityPlayer) {
            event.distance = Shapeshift.INSTANCE.updateFallState((EntityPlayer)event.entity, event.distance);
        }
    }

    @SubscribeEvent
    public void onHarvestDrops(BlockEvent.HarvestDropsEvent event) {
        if (!event.world.field_72995_K && event.harvester != null && !event.isCanceled()) {
            ExtendedPlayer playerEx = ExtendedPlayer.get(event.harvester);
            Shapeshift.INSTANCE.processDigging(event, event.harvester, playerEx);
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void onLivingHurt(LivingHurtEvent event) {
        block66: {
            block67: {
                boolean ignoreProtection;
                float healthAfterDamage;
                boolean wolfForm;
                ExtendedPlayer playerEx;
                EntityPlayer player;
                block68: {
                    PotionEffect effect;
                    List list;
                    ItemStack stack;
                    PotionEffect effect2;
                    EntityLivingBase living;
                    int currentLevel;
                    ItemStack hat;
                    if (event.entityLiving.field_70170_p.field_72995_K || event.isCanceled()) break block66;
                    this.checkForChargeDamage(event);
                    if (!(event.entityLiving instanceof EntityPlayer)) break block67;
                    player = (EntityPlayer)event.entityLiving;
                    float playerHealth = player.func_110143_aJ();
                    playerEx = ExtendedPlayer.get(player);
                    if (event.source == DamageSource.field_76369_e && playerEx.isVampire()) {
                        event.setCanceled(true);
                        return;
                    }
                    boolean bl = wolfForm = playerEx.getWerewolfLevel() > 0 && (playerEx.getCreatureType() == TransformCreature.WOLF || playerEx.getCreatureType() == TransformCreature.WOLFMAN);
                    if (wolfForm && event.source != DamageSource.field_76380_i && event.source != DamageSource.field_76368_d && event.source != DamageSource.field_76369_e && event.source != DamageSource.field_76379_h) {
                        if (!event.source.func_76347_k()) {
                            float damageReduction = Shapeshift.INSTANCE.getResistance(player, playerEx);
                            event.ammount = Math.max(0.0f, event.ammount - damageReduction);
                        }
                        if (!CreatureUtil.isWerewolf(event.source.func_76364_f())) {
                            event.ammount = !CreatureUtil.isSilverDamage(event.source) ? Math.max(Math.min(event.ammount, Shapeshift.INSTANCE.getDamageCap(player, playerEx)), 0.5f) : (event.ammount += 5.0f);
                        }
                        if (event.ammount <= 0.0f) {
                            event.setCanceled(true);
                            return;
                        }
                    }
                    if (ItemDeathsClothes.isFullSetWorn((EntityLivingBase)player) && player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() == Witchery.Items.DEATH_HAND) {
                        event.ammount = Math.min(event.ammount, 7.0f);
                    }
                    healthAfterDamage = EntityUtil.getHealthAfterDamage(event, playerHealth, (EntityLivingBase)player);
                    if ((player.field_71093_bK == Config.instance().dimensionDreamID || WorldProviderDreamWorld.getPlayerIsGhost(player)) && healthAfterDamage <= 0.0f && !player.field_71075_bZ.field_75098_d) {
                        event.setCanceled(true);
                        event.setResult(Event.Result.DENY);
                        WorldProviderDreamWorld.setPlayerMustAwaken(player, true);
                        return;
                    }
                    GenericEvents.dropItemsOnHit(player);
                    ignoreProtection = wolfForm || event.source instanceof BoltDamageSource && ((BoltDamageSource)event.source).isPoweredDraining;
                    boolean hasHunterSet = ItemHunterClothes.isFullSetWorn((EntityLivingBase)player, false);
                    if (hasHunterSet && event.source.func_82725_o() && player.field_70170_p.field_73012_v.nextDouble() < 0.25) {
                        event.setCanceled(true);
                        return;
                    }
                    if ((event.source instanceof EntityDamageSource || event.source.func_94541_c()) && !ignoreProtection && (hat = player.func_71124_b(4)) != null && hat.func_77973_b() == Witchery.Items.BABAS_HAT && player.field_71093_bK != Config.instance().dimensionTormentID) {
                        int TELEPORT_COST = 5;
                        double TELEPORT_CHANCE = 0.25;
                        int TELEPORT_DISTANCE = 6;
                        if (player.field_70170_p.field_73012_v.nextDouble() < 0.25 && Infusion.aquireEnergy(player.field_70170_p, player, 5, true)) {
                            BlockVoidBramble.teleportRandomly(player.field_70170_p, MathHelper.func_76128_c((double)player.field_70165_t), MathHelper.func_76128_c((double)player.field_70163_u), MathHelper.func_76128_c((double)player.field_70161_v), (Entity)player, 6);
                            event.setCanceled(true);
                            return;
                        }
                    }
                    if (!(event.source instanceof EntityDamageSource)) break block68;
                    EntityDamageSource entitySource = (EntityDamageSource)event.source;
                    ItemStack belt = player.func_71124_b(2);
                    if (belt != null && belt.func_77973_b() == Witchery.Items.BARK_BELT && !CreatureUtil.isWoodenDamage(event.source) && (currentLevel = Math.min(Witchery.Items.BARK_BELT.getChargeLevel(belt), Witchery.Items.BARK_BELT.getMaxChargeLevel((EntityLivingBase)player))) > 0) {
                        World world = player.field_70170_p;
                        Random rand = world.field_73012_v;
                        int cost = currentLevel > 1 && rand.nextDouble() < 0.25 ? 2 : 1;
                        Witchery.Items.BARK_BELT.setChargeLevel(belt, Math.max(currentLevel - cost, 0));
                        event.setCanceled(true);
                        for (int i = 0; i < cost; ++i) {
                            double dx = 1.0 * (double)(rand.nextInt(2) == 0 ? -1 : 1);
                            double dy = 1.0 * (double)(rand.nextInt(2) == 0 ? -1 : 1);
                            EntityItem item = new EntityItem(world, player.field_70165_t + dx, player.field_70163_u + 1.5, player.field_70161_v + dy, new ItemStack(Items.field_151055_y));
                            item.field_145804_b = 60;
                            item.lifespan = 60;
                            world.func_72838_d((Entity)item);
                        }
                        return;
                    }
                    double MOB_SPAWN_CHANCE = 0.25;
                    if (player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() == Witchery.Items.HUNTSMANS_SPEAR && player.func_70632_aY() && player.field_70170_p.field_73012_v.nextDouble() < 0.25 && entitySource.func_76346_g() != null && entitySource.func_76346_g() instanceof EntityLivingBase && (living = (EntityLivingBase)entitySource.func_76346_g()).func_70089_S()) {
                        EntityWolf wolf = new EntityWolf(player.field_70170_p);
                        wolf.func_70012_b(player.field_70165_t, player.field_70163_u, player.field_70161_v, player.field_70125_A, player.field_70759_as);
                        wolf.func_70624_b(living);
                        wolf.func_70784_b((Entity)living);
                        wolf.func_70916_h(true);
                        wolf.func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, 12000, 1));
                        player.field_70170_p.func_72838_d((Entity)wolf);
                    }
                    boolean louseUsed = false;
                    int i = 0;
                    while (true) {
                        block69: {
                            ItemStack stack2;
                            block70: {
                                block72: {
                                    block71: {
                                        EntityPlayer entityPlayer = player;
                                        if (i >= entityPlayer.field_71071_by.func_70451_h()) break;
                                        stack2 = player.field_71071_by.func_70301_a(i);
                                        if (stack2 == null || stack2.func_77973_b() != Witchery.Items.PARASYTIC_LOUSE || stack2.func_77960_j() <= 0) break block69;
                                        List list2 = Items.field_151068_bn.func_77834_f(stack2.func_77960_j());
                                        if (list2 == null || list2.isEmpty()) break block70;
                                        effect2 = new PotionEffect((PotionEffect)list2.get(0));
                                        if (!GenericEvents.isPotionAggressive(effect2.func_76456_a()) || event.source.func_76346_g() == null || !(event.source.func_76346_g() instanceof EntityLivingBase)) break block71;
                                        ((EntityLivingBase)event.source.func_76346_g()).func_70690_d(effect2);
                                        break block72;
                                    }
                                    if (effect2.func_76456_a() != Potion.field_76428_l.field_76415_H) break block69;
                                    player.func_70690_d(effect2);
                                }
                                player.func_70097_a(DamageSource.field_76376_m, 1.0f);
                                stack2.func_77964_b(0);
                                louseUsed = true;
                                break;
                            }
                            stack2.func_77964_b(0);
                        }
                        ++i;
                    }
                    if (!louseUsed && Witchery.Items.BITING_BELT.isBeltWorn(player) && (stack = player.field_71071_by.func_70440_f(1)) != null && stack.func_77942_o()) {
                        int potion;
                        boolean done = false;
                        if (stack.func_77978_p().func_74764_b("WITCPotion") && (list = Items.field_151068_bn.func_77834_f(potion = stack.func_77978_p().func_74762_e("WITCPotion"))) != null && !list.isEmpty() && !player.func_82165_m((effect = new PotionEffect((PotionEffect)list.get(0))).func_76456_a()) && effect.func_76456_a() != Potion.field_76428_l.field_76415_H) {
                            done = true;
                            if (GenericEvents.isPotionAggressive(effect.func_76456_a()) && event.source.func_76346_g() != null && event.source.func_76346_g() instanceof EntityLivingBase) {
                                ((EntityLivingBase)event.source.func_76346_g()).func_70690_d(effect);
                            } else {
                                player.func_70690_d(effect);
                            }
                            player.func_70097_a(DamageSource.field_76376_m, 1.0f);
                            stack.func_77978_p().func_82580_o("WITCPotion");
                            if (stack.func_77978_p().func_82582_d()) {
                                stack.func_77982_d(null);
                            }
                        }
                        if (!done && stack.func_77978_p().func_74764_b("WITCPotion2") && (list = Items.field_151068_bn.func_77834_f(potion = stack.func_77978_p().func_74762_e("WITCPotion2"))) != null && !list.isEmpty() && !player.func_82165_m((effect = new PotionEffect((PotionEffect)list.get(0))).func_76456_a()) && effect.func_76456_a() != Potion.field_76428_l.field_76415_H) {
                            if (GenericEvents.isPotionAggressive(effect.func_76456_a()) && event.source.func_76346_g() != null && event.source.func_76346_g() instanceof EntityLivingBase) {
                                ((EntityLivingBase)event.source.func_76346_g()).func_70690_d(effect);
                            } else {
                                player.func_70690_d(effect);
                            }
                            player.func_70097_a(DamageSource.field_76376_m, 1.0f);
                            stack.func_77978_p().func_82580_o("WITCPotion2");
                            if (stack.func_77978_p().func_82582_d()) {
                                stack.func_77982_d(null);
                            }
                        }
                    }
                    this.checkForRendArmor(event);
                    if (!ignoreProtection && !playerEx.isVampire()) {
                        ItemPoppet cfr_ignored_0 = Witchery.Items.POPPET;
                        ItemStack vampiricPoppetStack = ItemPoppet.findBoundPoppetInWorld(Witchery.Items.POPPET.vampiricPoppet, player, 66, true, false);
                        if (vampiricPoppetStack != null) {
                            EntityWitchHunter.blackMagicPerformed(player);
                            EntityLivingBase targetEntity = Witchery.Items.TAGLOCK_KIT.getBoundEntity(player.field_70170_p, (Entity)player, vampiricPoppetStack, 2);
                            if (targetEntity != null && !Witchery.Items.POPPET.voodooProtectionActivated(player, vampiricPoppetStack, targetEntity, true, false) && !ItemHunterClothes.isFullSetWorn(targetEntity, false)) {
                                if (targetEntity instanceof EntityPlayer) {
                                    targetEntity.func_70097_a(event.source, event.ammount);
                                    event.setCanceled(true);
                                } else if (targetEntity instanceof EntityLiving && targetEntity.func_70089_S()) {
                                    targetEntity.func_70097_a(event.source, Math.min(event.ammount, 15.0f));
                                    if (!targetEntity.func_70089_S()) {
                                        Witchery.Items.TAGLOCK_KIT.clearTaglock(vampiricPoppetStack, 2);
                                    }
                                    event.setCanceled(true);
                                }
                                return;
                            }
                        }
                    }
                    if (!louseUsed) {
                        i = 0;
                        while (true) {
                            EntityPlayer entityPlayer = player;
                            if (i >= entityPlayer.field_71071_by.func_70451_h()) break;
                            ItemStack stack3 = player.field_71071_by.func_70301_a(i);
                            if (stack3 != null && stack3.func_77973_b() == Witchery.Items.PARASYTIC_LOUSE && stack3.func_77960_j() > 0) {
                                List list3 = Items.field_151068_bn.func_77834_f(stack3.func_77960_j());
                                if (list3 != null && !list3.isEmpty()) {
                                    effect2 = new PotionEffect((PotionEffect)list3.get(0));
                                    if (effect2.func_76456_a() == Potion.field_76428_l.field_76415_H) {
                                        player.func_70690_d(effect2);
                                    }
                                    player.func_70097_a(DamageSource.field_76376_m, 1.0f);
                                    stack3.func_77964_b(0);
                                    louseUsed = true;
                                    break;
                                }
                                stack3.func_77964_b(0);
                            }
                            ++i;
                        }
                    }
                    if (!louseUsed && Witchery.Items.BITING_BELT.isBeltWorn(player) && (stack = player.field_71071_by.func_70440_f(1)) != null && stack.func_77942_o()) {
                        int potion;
                        boolean done = false;
                        if (stack.func_77978_p().func_74764_b("WITCPotion") && (list = Items.field_151068_bn.func_77834_f(potion = stack.func_77978_p().func_74762_e("WITCPotion"))) != null && !list.isEmpty() && !player.func_82165_m((effect = new PotionEffect((PotionEffect)list.get(0))).func_76456_a()) && effect.func_76456_a() == Potion.field_76428_l.field_76415_H) {
                            done = true;
                            player.func_70690_d(effect);
                            player.func_70097_a(DamageSource.field_76376_m, 1.0f);
                            stack.func_77978_p().func_82580_o("WITCPotion");
                            if (stack.func_77978_p().func_82582_d()) {
                                stack.func_77982_d(null);
                            }
                        }
                        if (!done && stack.func_77978_p().func_74764_b("WITCPotion2") && (list = Items.field_151068_bn.func_77834_f(potion = stack.func_77978_p().func_74762_e("WITCPotion2"))) != null && !list.isEmpty() && !player.func_82165_m((effect = new PotionEffect((PotionEffect)list.get(0))).func_76456_a()) && effect.func_76456_a() == Potion.field_76428_l.field_76415_H) {
                            player.func_70690_d(effect);
                            player.func_70097_a(DamageSource.field_76376_m, 1.0f);
                            stack.func_77978_p().func_82580_o("WITCPotion2");
                            if (stack.func_77978_p().func_82582_d()) {
                                stack.func_77982_d(null);
                            }
                        }
                    }
                }
                if (healthAfterDamage <= 0.0f && !wolfForm && !playerEx.isVampire()) {
                    Log.instance().debug(String.format("player terminal damage", new Object[0]));
                    if (event.source.field_76373_n.equals(DamageSource.field_76379_h.field_76373_n) || event.source.field_76373_n.equals(DamageSource.field_82729_p.field_76373_n)) {
                        Witchery.Items.POPPET.cancelEventIfPoppetFound(player, Witchery.Items.POPPET.earthPoppet, event, false);
                    } else if (event.source.func_76347_k() || event.source.func_94541_c()) {
                        Witchery.Items.POPPET.cancelEventIfPoppetFound(player, Witchery.Items.POPPET.firePoppet, event, true);
                        if (event.isCanceled()) {
                            player.func_70690_d(new PotionEffect(Potion.field_76426_n.field_76415_H, 60, 0));
                        }
                    } else if (event.source.field_76373_n.equals(DamageSource.field_76369_e.field_76373_n)) {
                        Witchery.Items.POPPET.cancelEventIfPoppetFound(player, Witchery.Items.POPPET.waterPoppet, event, true);
                        if (event.isCanceled()) {
                            player.func_70690_d(new PotionEffect(Potion.field_76427_o.field_76415_H, 60, 0));
                        }
                    } else if (event.source.field_76373_n.equals(DamageSource.field_76366_f.field_76373_n)) {
                        Witchery.Items.POPPET.cancelEventIfPoppetFound(player, Witchery.Items.POPPET.foodPoppet, event, true);
                        if (event.isCanceled()) {
                            player.func_70690_d(new PotionEffect(Potion.field_76443_y.field_76415_H, 60, 0));
                        }
                    }
                    if (!event.isCanceled()) {
                        Witchery.Items.POPPET.cancelEventIfPoppetFound(player, Witchery.Items.POPPET.deathPoppet, event, true, ignoreProtection);
                        if (event.isCanceled()) {
                            if (player.func_70027_ad() || event.source.func_76347_k() || event.source.func_94541_c()) {
                                player.func_70690_d(new PotionEffect(Potion.field_76426_n.field_76415_H, 120, 0));
                            } else if (event.source.field_76373_n.equals(DamageSource.field_76369_e.field_76373_n)) {
                                player.func_70690_d(new PotionEffect(Potion.field_76427_o.field_76415_H, 120, 0));
                            } else if (event.source.field_76373_n.equals(DamageSource.field_76366_f.field_76373_n)) {
                                player.func_70690_d(new PotionEffect(Potion.field_76443_y.field_76415_H, 120, 0));
                            }
                        }
                    }
                }
                if (!event.isCanceled() && healthAfterDamage <= 2.0f && event.source.field_76373_n.equals(DamageSource.field_76366_f.field_76373_n)) {
                    Witchery.Items.POPPET.cancelEventIfPoppetFound(player, Witchery.Items.POPPET.foodPoppet, event, true);
                    if (event.isCanceled()) {
                        player.func_70690_d(new PotionEffect(Potion.field_76443_y.field_76415_H, 60, 0));
                    }
                }
                Familiar.handlePlayerHurt(event, player);
                this.checkForWolfInfection(event, healthAfterDamage);
                Witchery.Items.POPPET.checkForArmorProtection(player);
                break block66;
            }
            if (event.entityLiving instanceof EntityGoblin && event.source == DamageSource.field_76379_h) {
                event.setCanceled(true);
                return;
            }
            if (event.entityLiving instanceof EntityVillager && event.source != null && event.source.func_76346_g() != null && (event.source.func_76346_g() instanceof EntityVillageGuard || event.source.func_76346_g() instanceof EntityWitchHunter)) {
                event.setCanceled(true);
                return;
            }
            if (Config.instance().isReduceZombeVillagerDamageActive() && event.entityLiving instanceof EntityVillager && event.source.func_76346_g() != null && event.source.func_76346_g() instanceof EntityZombie) {
                event.ammount = 0.5f;
            }
            this.checkForRendArmor(event);
            this.checkForWolfInfection(event, EntityUtil.getHealthAfterDamage(event, event.entityLiving.func_110143_aJ(), event.entityLiving));
        }
    }

    public void checkForRendArmor(LivingHurtEvent event) {
        if (event.source.field_76373_n.equals("player") && event.source.func_76346_g() != null && event.source.func_76346_g() instanceof EntityPlayer) {
            EntityPlayer attackingPlayer = (EntityPlayer)event.source.func_76346_g();
            ExtendedPlayer playerEx = ExtendedPlayer.get(attackingPlayer);
            Shapeshift.INSTANCE.rendArmor(event.entityLiving, attackingPlayer, playerEx);
        }
    }

    public void checkForWolfInfection(LivingHurtEvent event, float health) {
        if (!event.isCanceled()) {
            if (event.source.field_76373_n.equals("player") && event.source.func_76364_f() != null && event.source.func_76364_f() instanceof EntityPlayer) {
                EntityPlayer attackingPlayer = (EntityPlayer)event.source.func_76346_g();
                ExtendedPlayer playerEx = ExtendedPlayer.get(attackingPlayer);
                Shapeshift.INSTANCE.processWolfInfection(event.entityLiving, attackingPlayer, playerEx, health);
            } else if (event.source.field_76373_n.equals("mob") && event.source.func_76364_f() instanceof EntityWolfman) {
                Shapeshift.INSTANCE.processWolfInfection(event.entityLiving, (EntityWolfman)event.source.func_76364_f(), health);
            }
        }
    }

    public void checkForChargeDamage(LivingHurtEvent event) {
        if (event.source.field_76373_n.equals("player") && event.source.func_76346_g() != null && event.source.func_76346_g() instanceof EntityPlayer) {
            EntityPlayer attackingPlayer = (EntityPlayer)event.source.func_76346_g();
            ExtendedPlayer playerEx = ExtendedPlayer.get(attackingPlayer);
            Shapeshift.INSTANCE.updateChargeDamage(event, attackingPlayer, playerEx);
        }
    }

    private static boolean isPotionAggressive(int potionID) {
        return potionID == Potion.field_76419_f.field_76415_H || potionID == Potion.field_76421_d.field_76415_H || potionID == Potion.field_76436_u.field_76415_H || potionID == Potion.field_82731_v.field_76415_H || potionID == Potion.field_76437_t.field_76415_H || potionID == Potion.field_76438_s.field_76415_H;
    }

    private static void dropItemsOnHit(EntityPlayer player) {
        for (int i = 0; i < player.field_71071_by.field_70462_a.length; ++i) {
            ItemStack stack = player.field_71071_by.field_70462_a[i];
            if (!Witchery.Items.GENERIC.itemBatBall.isMatch(stack)) continue;
            player.func_71019_a(stack, true);
            player.field_71071_by.field_70462_a[i] = null;
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGH)
    public void onLivingDeath(LivingDeathEvent event) {
        if (event.entityLiving.field_70170_p.field_72995_K && !event.isCanceled()) {
            EntityPlayer player;
            ExtendedPlayer playerEx;
            if (event.entityLiving instanceof EntityPlayer && (playerEx = ExtendedPlayer.get(player = (EntityPlayer)event.entityLiving)).isVampire()) {
                event.setCanceled(true);
                player.func_70606_j(1.0f);
                return;
            }
        } else if (!(event.entityLiving.field_70170_p.field_72995_K || event.isCancelable() && event.isCanceled())) {
            Entity entitySource;
            EntityPlayer player;
            ExtendedPlayer playerEx;
            if (event.entityLiving instanceof EntityPlayer && (playerEx = ExtendedPlayer.get(player = (EntityPlayer)event.entityLiving)).isVampire()) {
                if (player.func_110143_aJ() > 0.0f) {
                    event.setCanceled(true);
                    return;
                }
                if (!CreatureUtil.checkForVampireDeath((EntityLivingBase)player, event.source)) {
                    event.setCanceled(true);
                    return;
                }
            }
            this.dropExtraItemsFromNBT(event);
            Entity attacker = event.source.func_76346_g();
            if (attacker != null && attacker instanceof EntityPlayer) {
                EntityPlayer player2 = (EntityPlayer)attacker;
                ExtendedPlayer playerEx2 = ExtendedPlayer.get(player2);
                if (event.entity instanceof EntityHornedHuntsman && playerEx2.getWolfmanQuestState() == ExtendedPlayer.QuestState.STARTED) {
                    playerEx2.setWolfmanQuestState(ExtendedPlayer.QuestState.COMPLETE);
                }
                if (playerEx2.hasVampireBook()) {
                    boolean dropPage;
                    boolean bl = dropPage = event.entityLiving instanceof IBossDisplayData || (event.entityLiving instanceof EntityPigZombie || event.entityLiving instanceof EntityEnderman) && player2.field_70170_p.field_73012_v.nextDouble() < 0.09 || PotionParalysis.isVillager((Entity)event.entityLiving) && player2.field_70170_p.field_73012_v.nextDouble() < 0.1 || event.entityLiving.func_70662_br() && player2.field_70170_p.field_73012_v.nextDouble() < 0.02;
                    if (dropPage) {
                        EntityItem entityItem = new EntityItem(event.entityLiving.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + 1.0, event.entityLiving.field_70161_v, Witchery.Items.GENERIC.itemVampireBookPage.createStack());
                        event.entityLiving.field_70170_p.func_72838_d((Entity)entityItem);
                    }
                }
            }
            if ((entitySource = event.source.func_76364_f()) != null && entitySource instanceof EntityPlayer) {
                boolean allowDrops;
                EntityPlayer player3 = (EntityPlayer)entitySource;
                ExtendedPlayer playerEx3 = ExtendedPlayer.get(player3);
                boolean hasArthana = player3.field_71071_by.func_70448_g() != null && player3.field_71071_by.func_70448_g().func_77973_b() == Witchery.Items.ARTHANA;
                boolean hasCaneSword = player3.field_71071_by.func_70448_g() != null && player3.field_71071_by.func_70448_g().func_77973_b() == Witchery.Items.CANE_SWORD && Witchery.Items.CANE_SWORD.isDrawn((EntityLivingBase)player3) && playerEx3.isVampire();
                ItemStack itemstack = null;
                Shapeshift.INSTANCE.processCreatureKilled(event, player3, playerEx3);
                if (playerEx3.getWerewolfLevel() == 5 && Shapeshift.INSTANCE.isWolfAnimalForm(playerEx3) && playerEx3.getWolfmanQuestState() == ExtendedPlayer.QuestState.STARTED) {
                    if (event.entity instanceof IMob && !player3.field_70122_E) {
                        playerEx3.increaseWolfmanQuestCounter();
                    }
                } else if (playerEx3.getWerewolfLevel() == 8 && playerEx3.getCreatureType() == TransformCreature.WOLFMAN && playerEx3.getWolfmanQuestState() == ExtendedPlayer.QuestState.STARTED) {
                    if (event.entity instanceof EntityPigZombie) {
                        playerEx3.increaseWolfmanQuestCounter();
                    }
                } else if (playerEx3.getWerewolfLevel() == 9 && Shapeshift.INSTANCE.isWolfAnimalForm(playerEx3) && playerEx3.getWolfmanQuestState() == ExtendedPlayer.QuestState.STARTED && (event.entity instanceof EntityVillager || event.entity instanceof EntityPlayer)) {
                    playerEx3.increaseWolfmanQuestCounter();
                }
                if (playerEx3.getVampireLevel() == 5 && playerEx3.canIncreaseVampireLevel() && event.entity instanceof EntityBlaze) {
                    if (playerEx3.getVampireQuestCounter() >= 19) {
                        playerEx3.increaseVampireLevel();
                    } else {
                        playerEx3.increaseVampireQuestCounter();
                    }
                }
                int baseLooting = EnchantmentHelper.func_77519_f((EntityLivingBase)player3);
                double lootingFactor = 1.0 + (double)baseLooting;
                double halfLooting = 1.0 + (double)(baseLooting / 2);
                if (InfusedBrewEffect.getActiveBrew(player3) == InfusedBrewEffect.Grave) {
                    float maxHealth = player3.func_110138_aP();
                    if (event.entityLiving instanceof EntityPlayer) {
                        player3.func_71024_bL().func_75122_a(20, 0.9f);
                        player3.func_70691_i(maxHealth * 0.6f);
                    } else if (event.entityLiving instanceof EntityVillager) {
                        player3.func_71024_bL().func_75122_a(20, 0.9f);
                        player3.func_70691_i(maxHealth * 0.4f);
                    } else if (event.entityLiving instanceof EntityAnimal) {
                        player3.func_71024_bL().func_75122_a(8, 0.8f);
                        player3.func_70691_i(maxHealth * 0.1f);
                    }
                }
                Witchery.Items.BLOOD_GOBLET.handleCreatureDeath(player3.field_70170_p, player3, event.entityLiving);
                boolean bl = allowDrops = !EntityUtil.isNoDrops(event.entityLiving);
                if (allowDrops) {
                    if (event.entityLiving instanceof EntityVillager) {
                        ExtendedVillager villagerEx = ExtendedVillager.get((EntityVillager)event.entityLiving);
                        playerEx3.fillBloodReserve(villagerEx.getBlood());
                    } else if (event.entityLiving instanceof EntityVillageGuard) {
                        EntityVillageGuard guard = (EntityVillageGuard)event.entityLiving;
                        playerEx3.fillBloodReserve(guard.getBlood());
                    } else if (event.entityLiving instanceof EntityPlayer) {
                        ExtendedPlayer targetEx = ExtendedPlayer.get((EntityPlayer)event.entityLiving);
                        playerEx3.fillBloodReserve(targetEx.getHumanBlood());
                    } else if (event.entityLiving instanceof EntitySkeleton) {
                        EntitySkeleton skeleton = (EntitySkeleton)event.entityLiving;
                        if (hasArthana && skeleton.func_82202_m() == 0 && event.entityLiving.field_70170_p.field_73012_v.nextDouble() <= Math.min(0.05 * lootingFactor, 1.0)) {
                            itemstack = new ItemStack(Items.field_151144_bL, 1, 0);
                        } else if (hasArthana && event.entityLiving.field_70170_p.field_73012_v.nextDouble() <= Math.min(0.04 * lootingFactor, 1.0)) {
                            itemstack = Witchery.Items.GENERIC.itemSpectralDust.createStack();
                        }
                    } else if (event.entityLiving instanceof EntityZombie) {
                        if (hasArthana && event.entityLiving.field_70170_p.field_73012_v.nextDouble() <= Math.min(0.02 * lootingFactor, 1.0)) {
                            itemstack = new ItemStack(Items.field_151144_bL, 1, 2);
                        } else if (hasArthana && event.entityLiving.field_70170_p.field_73012_v.nextDouble() <= Math.min(0.03 * lootingFactor, 1.0)) {
                            itemstack = Witchery.Items.GENERIC.itemSpectralDust.createStack();
                        }
                    } else if (event.entityLiving instanceof EntityCreeper) {
                        if (hasArthana && event.entityLiving.field_70170_p.field_73012_v.nextDouble() <= Math.min(0.01 * lootingFactor, 1.0)) {
                            itemstack = new ItemStack(Items.field_151144_bL, 1, 4);
                        } else {
                            double d = event.entityLiving.field_70170_p.field_73012_v.nextDouble();
                            double d2 = hasArthana ? 0.08 : 0.02;
                            if (d <= Math.min(d2 * lootingFactor, 1.0)) {
                                itemstack = Witchery.Items.GENERIC.itemCreeperHeart.createStack();
                            }
                        }
                    } else if (event.entityLiving instanceof EntityDemon) {
                        if (hasArthana && event.entityLiving.field_70170_p.field_73012_v.nextDouble() <= Math.min(0.33 * halfLooting, 1.0)) {
                            itemstack = Witchery.Items.GENERIC.itemDemonHeart.createStack();
                        }
                    } else if (event.entityLiving instanceof EntityPlayer) {
                        if (hasArthana && event.entityLiving.field_70170_p.field_73012_v.nextDouble() <= Math.min(0.1 * halfLooting, 1.0)) {
                            EntityPlayer victim = (EntityPlayer)event.entityLiving;
                            itemstack = new ItemStack(Items.field_151144_bL, 1, 3);
                            NBTTagCompound tag = itemstack.func_77978_p();
                            if (tag == null) {
                                tag = new NBTTagCompound();
                                itemstack.func_77982_d(tag);
                            }
                            tag.func_74778_a("SkullOwner", victim.func_70005_c_());
                        }
                    } else if (event.entityLiving instanceof EntityBat) {
                        double d = player3.field_70170_p.field_73012_v.nextDouble();
                        double d3 = hasArthana ? (baseLooting > 0 ? 1.0 : 0.75) : 0.33;
                        if (d <= d3) {
                            itemstack = Witchery.Items.GENERIC.itemBatWool.createStack();
                        }
                    } else if (event.entityLiving instanceof EntityWolf) {
                        double d = player3.field_70170_p.field_73012_v.nextDouble();
                        double d4 = hasArthana ? (baseLooting > 0 ? 1.0 : 0.75) : 0.33;
                        if (d <= d4) {
                            itemstack = Witchery.Items.GENERIC.itemDogTongue.createStack();
                        }
                        if (player3.field_70170_p.field_73012_v.nextInt(12) <= Math.min(baseLooting, 3)) {
                            event.entityLiving.func_70099_a(new ItemStack(Witchery.Blocks.WOLFHEAD, 1, 0), 0.0f);
                        }
                    } else if (event.entityLiving instanceof EntityOwl) {
                        if (!((EntityOwl)event.entityLiving).isTemp()) {
                            double d = player3.field_70170_p.field_73012_v.nextDouble();
                            double d5 = hasArthana ? (baseLooting > 0 ? 1.0 : 0.5) : 0.2;
                            if (d <= d5) {
                                itemstack = Witchery.Items.GENERIC.itemOwletsWing.createStack();
                            }
                        }
                    } else if (event.entityLiving instanceof EntitySheep) {
                        if (CreatureUtil.isWerewolf(entitySource, false) && !((EntitySheep)event.entityLiving).func_70631_g_() && event.entityLiving.field_70170_p.field_73012_v.nextInt(4) != 0) {
                            itemstack = Witchery.Items.GENERIC.itemMuttonRaw.createStack();
                        }
                    } else if (event.entityLiving instanceof EntityToad) {
                        if (!((EntityToad)event.entityLiving).isTemp()) {
                            double d = player3.field_70170_p.field_73012_v.nextDouble();
                            double d6 = hasArthana ? (baseLooting > 0 ? 1.0 : 0.5) : 0.2;
                            if (d <= d6) {
                                itemstack = Witchery.Items.GENERIC.itemToeOfFrog.createStack();
                            }
                        }
                    } else {
                        try {
                            String name;
                            Class<?> theClass = event.entityLiving.getClass();
                            if (theClass != null && (name = theClass.getSimpleName()) != null && !name.isEmpty()) {
                                String upperName = name.toUpperCase(Locale.ROOT);
                                if (upperName.contains("WOLF") || name.contains("Dog") || name.contains("Fox")) {
                                    double d = player3.field_70170_p.field_73012_v.nextDouble();
                                    double d7 = hasArthana ? (baseLooting > 0 ? 1.0 : 0.75) : 0.33;
                                    if (d <= d7) {
                                        itemstack = Witchery.Items.GENERIC.itemDogTongue.createStack();
                                    }
                                    if ((upperName.contains("WOLF") || name.contains("Dog")) && player3.field_70170_p.field_73012_v.nextInt(12) <= Math.min(baseLooting, 3)) {
                                        event.entityLiving.func_70099_a(new ItemStack(Witchery.Blocks.WOLFHEAD, 1, 0), 0.0f);
                                    }
                                } else if (upperName.contains("FIREBAT") || name.contains("Bat")) {
                                    double d = player3.field_70170_p.field_73012_v.nextDouble();
                                    double d8 = hasArthana ? (baseLooting > 0 ? 1.0 : 0.75) : 0.33;
                                    if (d <= d8) {
                                        itemstack = Witchery.Items.GENERIC.itemBatWool.createStack();
                                    }
                                }
                            }
                        }
                        catch (Exception e) {
                            Log.instance().debug(String.format("Exception occurred while determining dead creature type: %s", e.toString()));
                        }
                    }
                }
                if (itemstack != null) {
                    EntityItem entityItem = new EntityItem(event.entityLiving.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + 1.0, event.entityLiving.field_70161_v, itemstack);
                    event.entityLiving.field_70170_p.func_72838_d((Entity)entityItem);
                }
            }
        }
    }

    private void dropExtraItemsFromNBT(LivingDeathEvent event) {
        NBTTagCompound nbtEntityData;
        if (!event.entityLiving.field_70170_p.field_72995_K && (nbtEntityData = event.entityLiving.getEntityData()).func_74764_b("WITCExtraDrops")) {
            NBTTagList nbtExtraDrops = nbtEntityData.func_150295_c("WITCExtraDrops", 10);
            for (int i = 0; i < nbtExtraDrops.func_74745_c(); ++i) {
                NBTTagCompound nbtTag = nbtExtraDrops.func_150305_b(i);
                ItemStack extraStack = ItemStack.func_77949_a((NBTTagCompound)nbtTag);
                if (extraStack == null) continue;
                EntityItem entityItem = new EntityItem(event.entityLiving.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + 1.0, event.entityLiving.field_70161_v, extraStack);
                event.entityLiving.field_70170_p.func_72838_d((Entity)entityItem);
            }
        }
    }
}

