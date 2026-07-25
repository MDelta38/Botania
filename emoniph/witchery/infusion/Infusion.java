/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event$Result
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.boss.IBossDisplayData
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.monster.EntityGolem
 *  net.minecraft.entity.monster.EntityWitch
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemDye
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.event.ServerChatEvent
 *  net.minecraftforge.event.entity.living.EnderTeleportEvent
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  net.minecraftforge.event.entity.living.LivingFallEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent
 *  net.minecraftforge.event.entity.player.FillBucketEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent
 *  net.minecraftforge.event.world.BlockEvent$HarvestDropsEvent
 */
package com.emoniph.witchery.infusion;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
import com.emoniph.witchery.dimension.WorldProviderTorment;
import com.emoniph.witchery.entity.EntityCovenWitch;
import com.emoniph.witchery.entity.EntityDemon;
import com.emoniph.witchery.entity.EntityIllusion;
import com.emoniph.witchery.entity.EntityIllusionCreeper;
import com.emoniph.witchery.entity.EntityIllusionSpider;
import com.emoniph.witchery.entity.EntityIllusionZombie;
import com.emoniph.witchery.entity.EntityNightmare;
import com.emoniph.witchery.entity.EntityVillageGuard;
import com.emoniph.witchery.entity.EntityWitchHunter;
import com.emoniph.witchery.entity.ai.EntityAIDigBlocks;
import com.emoniph.witchery.familiar.Familiar;
import com.emoniph.witchery.infusion.InfusedBrewEffect;
import com.emoniph.witchery.infusion.PlayerEffects;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePower;
import com.emoniph.witchery.item.ItemGeneral;
import com.emoniph.witchery.item.ItemHunterClothes;
import com.emoniph.witchery.network.PacketPlayerStyle;
import com.emoniph.witchery.network.PacketPlayerSync;
import com.emoniph.witchery.predictions.PredictionManager;
import com.emoniph.witchery.ritual.rites.RiteProtectionCircleRepulsive;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.Dye;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TimeUtil;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;

public class Infusion {
    public static final Infusion DEFUSED = new Infusion(0);
    public static final String INFUSION_CHARGES_KEY = "witcheryInfusionCharges";
    public static final String INFUSION_ID_KEY = "witcheryInfusionID";
    public static final String INFUSION_MAX_CHARGES_KEY = "witcheryInfusionChargesMax";
    public static final String INFUSION_NEXTSYNC = "WITCResyncLook";
    public static final String INFUSION_GROTESQUE = "witcheryGrotesque";
    public static final String INFUSION_DEPTHS = "witcheryDepths";
    public static final String INFUSION_CURSED = "witcheryCursed";
    public static final String INFUSION_INSANITY = "witcheryInsanity";
    public static final String INFUSION_SINKING = "witcherySinking";
    public static final String INFUSION_OVERHEAT = "witcheryOverheating";
    public static final String INFUSION_NIGHTMARE = "witcheryWakingNightmare";
    public final int infusionID;
    protected static final int DEFAULT_CHARGE_COST = 1;

    public static EntityItem dropEntityItemWithRandomChoice(EntityLivingBase entity, ItemStack par1ItemStack, boolean par2) {
        if (par1ItemStack == null || entity == null) {
            return null;
        }
        if (par1ItemStack.field_77994_a == 0) {
            return null;
        }
        EntityItem entityitem = new EntityItem(entity.field_70170_p, entity.field_70165_t, entity.field_70163_u - (double)0.3f + (double)entity.func_70047_e(), entity.field_70161_v, par1ItemStack);
        entityitem.field_145804_b = 40;
        float f = 0.1f;
        if (par2) {
            float f1 = entity.field_70170_p.field_73012_v.nextFloat() * 0.5f;
            float f2 = entity.field_70170_p.field_73012_v.nextFloat() * (float)Math.PI * 2.0f;
            entityitem.field_70159_w = -MathHelper.func_76126_a((float)f2) * f1;
            entityitem.field_70179_y = MathHelper.func_76134_b((float)f2) * f1;
            entityitem.field_70181_x = 0.2f;
        } else {
            f = 0.3f;
            entityitem.field_70159_w = -MathHelper.func_76126_a((float)(entity.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(entity.field_70125_A / 180.0f * (float)Math.PI)) * f;
            entityitem.field_70179_y = MathHelper.func_76134_b((float)(entity.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(entity.field_70125_A / 180.0f * (float)Math.PI)) * f;
            entityitem.field_70181_x = -MathHelper.func_76126_a((float)(entity.field_70125_A / 180.0f * (float)Math.PI)) * f + 0.1f;
            f = 0.02f;
            float f1 = entity.field_70170_p.field_73012_v.nextFloat() * (float)Math.PI * 2.0f;
            entityitem.field_70159_w += Math.cos(f1) * (double)(f *= entity.field_70170_p.field_73012_v.nextFloat());
            entityitem.field_70181_x += (double)((entity.field_70170_p.field_73012_v.nextFloat() - entity.field_70170_p.field_73012_v.nextFloat()) * 0.1f);
            entityitem.field_70179_y += Math.sin(f1) * (double)f;
        }
        entity.field_70170_p.func_72838_d((Entity)entityitem);
        return entityitem;
    }

    public static EntityCreature spawnCreature(World world, Class<? extends EntityCreature> creatureType, EntityLivingBase victim, int minRange, int maxRange, ParticleEffect effect, SoundEffect effectSound) {
        int x = MathHelper.func_76128_c((double)victim.field_70165_t);
        int y = MathHelper.func_76128_c((double)victim.field_70163_u);
        int z = MathHelper.func_76128_c((double)victim.field_70161_v);
        return Infusion.spawnCreature(world, creatureType, x, y, z, victim, minRange, maxRange, effect, effectSound);
    }

    public static EntityCreature spawnCreature(World world, Class<? extends EntityCreature> creatureType, int x, int y, int z, EntityPlayer victim, int minRange, int maxRange) {
        return Infusion.spawnCreature(world, creatureType, x, y, z, (EntityLivingBase)victim, minRange, maxRange, null, SoundEffect.NONE);
    }

    public static EntityCreature spawnCreature(World world, Class<? extends EntityCreature> creatureType, int x, int y, int z, EntityLivingBase victim, int minRange, int maxRange, ParticleEffect effect, SoundEffect effectSound) {
        if (!world.field_72995_K) {
            int hy;
            int ny;
            int activeRadius = maxRange - minRange;
            int ax = world.field_73012_v.nextInt(activeRadius * 2 + 1);
            if (ax > activeRadius) {
                ax += minRange * 2;
            }
            int nx = x - maxRange + ax;
            int az = world.field_73012_v.nextInt(activeRadius * 2 + 1);
            if (az > activeRadius) {
                az += minRange * 2;
            }
            int nz = z - maxRange + az;
            for (ny = y; !world.func_147437_c(nx, ny, nz) && ny < y + 8; ++ny) {
            }
            while (world.func_147437_c(nx, ny, nz) && ny > 0) {
                --ny;
            }
            for (hy = 0; world.func_147437_c(nx, ny + hy + 1, nz) && hy < 6; ++hy) {
            }
            Log.instance().debug("Creature: hy: " + hy + " (" + nx + "," + ny + "," + nz + ")");
            if (hy >= 2) {
                try {
                    Constructor<? extends EntityCreature> ctor = creatureType.getConstructor(World.class);
                    EntityCreature creature = ctor.newInstance(world);
                    if (victim instanceof EntityPlayer) {
                        EntityPlayer player = (EntityPlayer)victim;
                        if (creature instanceof EntityIllusion) {
                            ((EntityIllusion)creature).setVictim(player.func_70005_c_());
                        } else if (creature instanceof EntityNightmare) {
                            ((EntityNightmare)creature).setVictim(player.func_70005_c_());
                            creature.func_70624_b(victim);
                        }
                    }
                    creature.func_70012_b(0.5 + (double)nx, 0.05 + (double)ny + 1.0, 0.5 + (double)nz, 0.0f, 0.0f);
                    world.func_72838_d((Entity)creature);
                    if (effect != null) {
                        effect.send(effectSound, world, 0.5 + (double)nx, 0.05 + (double)ny + 1.0, 0.5 + (double)nz, 1.0, creature.field_70131_O, 16);
                    }
                    return creature;
                }
                catch (NoSuchMethodException ex) {
                }
                catch (InvocationTargetException ex) {
                }
                catch (InstantiationException ex) {
                }
                catch (IllegalAccessException ex) {
                    // empty catch block
                }
            }
        }
        return null;
    }

    public static boolean isOnCooldown(World world, ItemStack stack) {
        long currentTime;
        NBTTagCompound nbtTag;
        return !world.field_72995_K && (nbtTag = stack.func_77978_p()) != null && nbtTag.func_74764_b("WITCCooldown") && (currentTime = MinecraftServer.func_130071_aq()) < nbtTag.func_74763_f("WITCCooldown");
    }

    public static void setCooldown(World world, ItemStack stack, int milliseconds) {
        if (!world.field_72995_K) {
            NBTTagCompound nbtTag;
            if (!stack.func_77942_o()) {
                stack.func_77982_d(new NBTTagCompound());
            }
            if ((nbtTag = stack.func_77978_p()) != null) {
                long currentTime = MinecraftServer.func_130071_aq();
                nbtTag.func_74772_a("WITCCooldown", currentTime + (long)milliseconds);
            }
        }
    }

    public Infusion(int infusionID) {
        this.infusionID = infusionID;
    }

    public void onHurt(World worldObj, EntityPlayer player, LivingHurtEvent event) {
    }

    public void onFalling(World world, EntityPlayer player, LivingFallEvent event) {
    }

    public IIcon getPowerBarIcon(EntityPlayer player, int index) {
        return Blocks.field_150344_f.func_149691_a(0, 0);
    }

    protected boolean consumeCharges(World world, EntityPlayer player, int cost, boolean playFailSound) {
        if (player.field_71075_bZ.field_75098_d) {
            return true;
        }
        int charges = Infusion.getCurrentEnergy(player);
        if (charges - cost < 0) {
            world.func_72956_a((Entity)player, "note.snare", 0.5f, 0.4f / ((float)Math.random() * 0.4f + 0.8f));
            this.clearInfusion(player);
            return false;
        }
        Infusion.setCurrentEnergy(player, charges - cost);
        return true;
    }

    public void onUpdate(ItemStack itemstack, World world, EntityPlayer player, int par4, boolean par5) {
    }

    public void onLeftClickEntity(ItemStack itemstack, World world, EntityPlayer player, Entity otherEntity) {
        if (!world.field_72995_K) {
            world.func_72956_a((Entity)player, "note.snare", 0.5f, 0.4f / ((float)Math.random() * 0.4f + 0.8f));
        }
    }

    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 400;
    }

    public void onUsingItemTick(ItemStack itemstack, World world, EntityPlayer player, int countdown) {
    }

    public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer player, int countdown) {
        if (!world.field_72995_K) {
            world.func_72956_a((Entity)player, "note.snare", 0.5f, 0.4f / ((float)Math.random() * 0.4f + 0.8f));
        }
    }

    public void playSound(World world, EntityPlayer player, String sound) {
        world.func_72956_a((Entity)player, sound, 0.5f, 0.4f / ((float)world.field_73012_v.nextDouble() * 0.4f + 0.8f));
    }

    public void playFailSound(World world, EntityPlayer player) {
        this.playSound(world, player, "note.snare");
    }

    public static NBTTagCompound getNBT(Entity player) {
        NBTTagCompound entityData = player.getEntityData();
        if (player.field_70170_p.field_72995_K) {
            return entityData;
        }
        NBTTagCompound persistedData = entityData.func_74775_l("PlayerPersisted");
        if (!entityData.func_74764_b("PlayerPersisted")) {
            entityData.func_74782_a("PlayerPersisted", (NBTBase)persistedData);
        }
        return persistedData;
    }

    public void infuse(EntityPlayer player, int charges) {
        if (!player.field_70170_p.field_72995_K) {
            NBTTagCompound nbt = Infusion.getNBT((Entity)player);
            nbt.func_74768_a(INFUSION_ID_KEY, this.infusionID);
            nbt.func_74768_a(INFUSION_CHARGES_KEY, charges);
            nbt.func_74768_a(INFUSION_MAX_CHARGES_KEY, charges);
            CreaturePower.setCreaturePowerID(player, 0, 0);
            Infusion.syncPlayer(player.field_70170_p, player);
        }
    }

    private void clearInfusion(EntityPlayer player) {
        if (!player.field_70170_p.field_72995_K) {
            NBTTagCompound nbt = Infusion.getNBT((Entity)player);
            nbt.func_82580_o(INFUSION_CHARGES_KEY);
            Infusion.syncPlayer(player.field_70170_p, player);
        }
    }

    public static void setCurrentEnergy(EntityPlayer player, int currentEnergy) {
        if (!player.field_70170_p.field_72995_K) {
            NBTTagCompound nbt = Infusion.getNBT((Entity)player);
            nbt.func_74768_a(INFUSION_CHARGES_KEY, currentEnergy);
            Infusion.syncPlayer(player.field_70170_p, player);
        }
    }

    public static void syncPlayer(World world, EntityPlayer player) {
        if (!world.field_72995_K) {
            Witchery.packetPipeline.sendTo((IMessage)new PacketPlayerSync(player), player);
        }
    }

    public static int getInfusionID(EntityPlayer player) {
        NBTTagCompound nbt = Infusion.getNBT((Entity)player);
        return nbt.func_74764_b(INFUSION_ID_KEY) ? nbt.func_74762_e(INFUSION_ID_KEY) : 0;
    }

    public static int getCurrentEnergy(EntityPlayer player) {
        NBTTagCompound nbt = Infusion.getNBT((Entity)player);
        return nbt.func_74764_b(INFUSION_CHARGES_KEY) ? nbt.func_74762_e(INFUSION_CHARGES_KEY) : 0;
    }

    public static int getMaxEnergy(EntityPlayer player) {
        NBTTagCompound nbt = Infusion.getNBT((Entity)player);
        return nbt.func_74764_b(INFUSION_MAX_CHARGES_KEY) ? nbt.func_74762_e(INFUSION_MAX_CHARGES_KEY) : 0;
    }

    public static void setEnergy(EntityPlayer player, int infusionID, int currentEnergy, int maxEnergy) {
        if (player.field_70170_p.field_72995_K) {
            NBTTagCompound nbt = Infusion.getNBT((Entity)player);
            nbt.func_74768_a(INFUSION_ID_KEY, infusionID);
            nbt.func_74768_a(INFUSION_CHARGES_KEY, currentEnergy);
            nbt.func_74768_a(INFUSION_MAX_CHARGES_KEY, maxEnergy);
        }
    }

    public static void setSinkingCurseLevel(EntityPlayer playerEntity, int sinkingLevel) {
        if (playerEntity.field_70170_p.field_72995_K) {
            NBTTagCompound nbt = Infusion.getNBT((Entity)playerEntity);
            if (nbt.func_74764_b(INFUSION_SINKING) && sinkingLevel <= 0) {
                nbt.func_82580_o(INFUSION_SINKING);
            }
            nbt.func_74768_a(INFUSION_SINKING, sinkingLevel);
        }
    }

    public static int getSinkingCurseLevel(EntityPlayer player) {
        NBTTagCompound nbtTag = Infusion.getNBT((Entity)player);
        return nbtTag.func_74764_b(INFUSION_SINKING) ? nbtTag.func_74762_e(INFUSION_SINKING) : 0;
    }

    public static boolean aquireEnergy(World world, EntityPlayer player, int cost, boolean showMessages) {
        NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
        if (nbtPlayer != null) {
            return Infusion.aquireEnergy(world, player, nbtPlayer, cost, showMessages);
        }
        return false;
    }

    public static boolean aquireEnergy(World world, EntityPlayer player, NBTTagCompound nbtPlayer, int cost, boolean showMessages) {
        if (nbtPlayer != null && nbtPlayer.func_74764_b(INFUSION_ID_KEY) && nbtPlayer.func_74764_b(INFUSION_CHARGES_KEY)) {
            if (player.field_71075_bZ.field_75098_d || nbtPlayer.func_74762_e(INFUSION_CHARGES_KEY) >= cost) {
                if (!player.field_71075_bZ.field_75098_d) {
                    Infusion.setCurrentEnergy(player, nbtPlayer.func_74762_e(INFUSION_CHARGES_KEY) - cost);
                }
                return true;
            }
            if (showMessages) {
                ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)player, "witchery.infuse.nocharges", new Object[0]);
                SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
            }
            return false;
        }
        if (showMessages) {
            ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)player, "witchery.infuse.infusionrequired", new Object[0]);
            SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
        }
        return false;
    }

    public static class Registry {
        private static final Registry INSTANCE = new Registry();
        private final ArrayList<Infusion> registry = new ArrayList();

        public static Registry instance() {
            return INSTANCE;
        }

        private Registry() {
        }

        public void add(Infusion infusion) {
            if (infusion.infusionID == this.registry.size() + 1) {
                this.registry.add(infusion);
            } else if (infusion.infusionID > this.registry.size() + 1) {
                for (int i = this.registry.size(); i < infusion.infusionID; ++i) {
                    this.registry.add(null);
                }
                this.registry.add(infusion);
            } else {
                Infusion existingInfusion = this.registry.get(infusion.infusionID);
                if (existingInfusion != null) {
                    Log.instance().warning(String.format("Creature power %s at id %d is being overwritten by another creature power %s.", existingInfusion, infusion.infusionID, infusion));
                }
                this.registry.set(infusion.infusionID, infusion);
            }
        }

        public Infusion get(EntityPlayer player) {
            int infusionID = Infusion.getInfusionID(player);
            return infusionID > 0 ? this.registry.get(infusionID - 1) : DEFUSED;
        }

        public Infusion get(int infusionID) {
            return infusionID > 0 ? this.registry.get(infusionID - 1) : DEFUSED;
        }
    }

    public static class EventHooks {
        private boolean isBannedSpiritObject(ItemStack stack) {
            if (stack != null) {
                Item item = stack.func_77973_b();
                return item == Items.field_151079_bi || item == Items.field_151065_br;
            }
            return false;
        }

        @SubscribeEvent(priority=EventPriority.NORMAL)
        public void onEnderTeleport(EnderTeleportEvent event) {
            if (!event.isCanceled() && event.entityLiving != null && !event.entityLiving.field_70170_p.field_72995_K && event.entityLiving instanceof EntityPlayer && ItemHunterClothes.isFullSetWorn(event.entityLiving, false)) {
                event.setCanceled(true);
            }
        }

        @SubscribeEvent(priority=EventPriority.NORMAL)
        public void FillBucket(FillBucketEvent event) {
            ItemStack result = this.attemptFill(event.world, event.target);
            if (result != null) {
                event.result = result;
                event.setResult(Event.Result.ALLOW);
            }
        }

        private ItemStack attemptFill(World world, MovingObjectPosition p) {
            Block id = world.func_147439_a(p.field_72311_b, p.field_72312_c, p.field_72309_d);
            if (id == Witchery.Blocks.FLOWING_SPIRIT) {
                if (world.func_72805_g(p.field_72311_b, p.field_72312_c, p.field_72309_d) == 0) {
                    world.func_147449_b(p.field_72311_b, p.field_72312_c, p.field_72309_d, Blocks.field_150350_a);
                    return new ItemStack(Witchery.Items.BUCKET_FLOWINGSPIRIT);
                }
            } else if (id == Witchery.Blocks.HOLLOW_TEARS && world.func_72805_g(p.field_72311_b, p.field_72312_c, p.field_72309_d) == 0) {
                world.func_147449_b(p.field_72311_b, p.field_72312_c, p.field_72309_d, Blocks.field_150350_a);
                return new ItemStack(Witchery.Items.BUCKET_HOLLOWTEARS);
            }
            return null;
        }

        @SubscribeEvent
        public void onLivingDamage(LivingHurtEvent event) {
            if (event.entityLiving != null && event.entityLiving.field_70170_p != null && !event.entityLiving.field_70170_p.field_72995_K && event.entityLiving instanceof EntityPlayer && !event.isCanceled()) {
                EntityPlayer player = (EntityPlayer)event.entityLiving;
                PredictionManager.instance().checkIfFulfilled(player, event);
            }
        }

        @SubscribeEvent
        public void onServerChat(ServerChatEvent event) {
            if (event.player != null && !event.isCanceled() && !event.player.field_70170_p.field_72995_K && event.message != null) {
                Witchery.Items.RUBY_SLIPPERS.trySayTheresNoPlaceLikeHome((EntityPlayer)event.player, event.message);
            }
        }

        @SubscribeEvent
        public void onHarvestDrops(BlockEvent.HarvestDropsEvent event) {
            if (event.harvester != null && event.harvester.field_70170_p != null && !event.harvester.field_70170_p.field_72995_K) {
                PredictionManager.instance().checkIfFulfilled(event.harvester, event);
                PlayerEffects.onHarvestDrops(event.harvester, event);
                EntityAIDigBlocks.onHarvestDrops(event.harvester, event);
            }
            if (!event.world.field_72995_K && event.world.field_73011_w.field_76574_g == Config.instance().dimensionDreamID && !event.isCanceled()) {
                Iterator iterator = event.drops.iterator();
                while (iterator.hasNext()) {
                    ItemStack stack = (ItemStack)iterator.next();
                    if (stack == null || !this.isBannedSpiritObject(stack)) continue;
                    iterator.remove();
                }
            }
        }

        @SubscribeEvent
        public void onPlayerInteract(PlayerInteractEvent event) {
            if (event.entityLiving != null && event.entityLiving.field_70170_p != null && !event.entityLiving.field_70170_p.field_72995_K && event.entityLiving instanceof EntityPlayer && !event.isCanceled()) {
                EntityPlayer player = (EntityPlayer)event.entityLiving;
                PredictionManager.instance().checkIfFulfilled(player, event);
                PlayerEffects.onInteract(player, event);
            }
        }

        @SubscribeEvent
        public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
            Block blockID;
            ItemStack belt;
            long counter = event.entityLiving.field_70170_p.func_82737_E();
            if (event.entityLiving instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer)event.entityLiving;
                if (!event.entityLiving.field_70170_p.field_72995_K) {
                    long time = TimeUtil.getServerTimeInTicks();
                    if (counter % 4L == 0L) {
                        NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
                        this.handleBrewGrotesqueEffect(player, nbtPlayer);
                        WorldProviderDreamWorld.updatePlayerEffects(player.field_70170_p, player, nbtPlayer, time, counter);
                        WorldProviderTorment.updatePlayerEffects(player.field_70170_p, player, nbtPlayer, time, counter);
                        if (counter % 20L == 0L) {
                            this.handleSyncEffects(player, nbtPlayer);
                            this.handleBrewDepthsEffect(player, nbtPlayer);
                            this.handleCurseEffects((EntityLivingBase)player, nbtPlayer);
                            this.handleSeepingShoesEffect(player, nbtPlayer);
                            InfusedBrewEffect.checkActiveEffects(player.field_70170_p, player, nbtPlayer, counter % 1200L == 0L, time);
                        }
                        if (counter % 100L == 0L && !event.isCanceled()) {
                            NBTTagList nbtCovenList;
                            ChunkCoordinates coords;
                            PredictionManager.instance().checkIfFulfilled(player, event);
                            if (Config.instance().allowCovenWitchVisits && nbtPlayer.func_74764_b("WITCCoven") && player.field_70170_p.field_73012_v.nextInt(20) == 0 && (coords = player.getBedLocation(player.field_71093_bK)) != null && coords.func_71569_e((int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v) < 256.0f && (nbtCovenList = nbtPlayer.func_150295_c("WITCCoven", 10)).func_74745_c() > 0) {
                                EntityCovenWitch.summonCovenMember(player.field_70170_p, player, 90);
                            }
                        }
                    }
                    PlayerEffects.onUpdate(player, time);
                    if (counter % 100L == 1L) {
                        EntityWitchHunter.handleWitchHunterEffects(player, time);
                    }
                }
                this.handleIcySlippersEffect(player);
                this.handleFamiliarFollowerSync(player);
            } else if (!event.entityLiving.field_70170_p.field_72995_K && counter % 20L == 0L) {
                this.handleCurseEffects(event.entityLiving, event.entityLiving.getEntityData());
            }
            if (counter % 100L == 0L && (belt = event.entityLiving.func_71124_b(2)) != null && belt.func_77973_b() == Witchery.Items.BARK_BELT && ((blockID = event.entityLiving.field_70170_p.func_147439_a(MathHelper.func_76128_c((double)event.entityLiving.field_70165_t), MathHelper.func_76128_c((double)event.entityLiving.field_70163_u) - 1, MathHelper.func_76128_c((double)event.entityLiving.field_70161_v))) == Blocks.field_150349_c || blockID == Blocks.field_150391_bh)) {
                int maxChargeLevel = Witchery.Items.BARK_BELT.getMaxChargeLevel(event.entityLiving);
                int currentChargeLevel = Witchery.Items.BARK_BELT.getChargeLevel(belt);
                if (currentChargeLevel < maxChargeLevel) {
                    Witchery.Items.BARK_BELT.setChargeLevel(belt, Math.min(currentChargeLevel + 1, maxChargeLevel));
                    event.entityLiving.field_70170_p.func_72956_a((Entity)event.entityLiving, "witchery:random.wood_creak", 0.5f, (float)(0.8 + 2.0 * event.entityLiving.field_70170_p.field_73012_v.nextGaussian()));
                }
            }
        }

        private void handleSeepingShoesEffect(EntityPlayer player, NBTTagCompound nbtTag) {
            if (!player.field_70122_E) {
                return;
            }
            if (!player.func_70644_a(Potion.field_76436_u) && !player.func_70644_a(Potion.field_82731_v)) {
                return;
            }
            ItemStack shoes = player.func_71124_b(1);
            if (shoes == null || shoes.func_77973_b() != Witchery.Items.SEEPING_SHOES) {
                return;
            }
            boolean poisonRemoved = false;
            if (player.func_70644_a(Potion.field_76436_u)) {
                player.func_82170_o(Potion.field_76436_u.field_76415_H);
                poisonRemoved = true;
            }
            if (player.func_70644_a(Potion.field_82731_v)) {
                player.func_82170_o(Potion.field_82731_v.field_76415_H);
                poisonRemoved = true;
            }
            if (poisonRemoved) {
                int x = MathHelper.func_76128_c((double)player.field_70165_t);
                int z = MathHelper.func_76128_c((double)player.field_70161_v);
                int y = MathHelper.func_76128_c((double)player.field_70163_u);
                int RADIUS = 3;
                int RADIUS_SQ = 9;
                for (int dx = x - 3; dx <= x + 3; ++dx) {
                    for (int dz = z - 3; dz <= z + 3; ++dz) {
                        for (int dy = y - 1; dy <= y + 1; ++dy) {
                            if (!(Coord.distanceSq(dx, 1.0, dy, x, 1.0, dy) <= 9.0) || !player.field_70170_p.func_147437_c(dx, dy + 1, dz) || player.field_70170_p.func_147437_c(dx, dy, dz)) continue;
                            ItemDye.applyBonemeal((ItemStack)Dye.BONE_MEAL.createStack(), (World)player.field_70170_p, (int)dx, (int)dy, (int)dz, (EntityPlayer)player);
                        }
                    }
                }
            }
        }

        private void handleSyncEffects(EntityPlayer player, NBTTagCompound nbtPlayer) {
            long nextSync;
            if (!player.field_70170_p.field_72995_K && nbtPlayer.func_74764_b(Infusion.INFUSION_NEXTSYNC) && (nextSync = nbtPlayer.func_74763_f(Infusion.INFUSION_NEXTSYNC)) <= MinecraftServer.func_130071_aq()) {
                nbtPlayer.func_82580_o(Infusion.INFUSION_NEXTSYNC);
                Witchery.packetPipeline.sendToDimension(new PacketPlayerStyle(player), player.field_71093_bK);
            }
        }

        private void handleFamiliarFollowerSync(EntityPlayer player) {
            if (!player.field_70170_p.field_72995_K) {
                NBTTagCompound compound = player.getEntityData();
                if (compound.func_74764_b("WITC_LASTPOS")) {
                    NBTTagCompound pos = compound.func_74775_l("WITC_LASTPOS");
                    int lastDimension = pos.func_74762_e("D");
                    if (lastDimension != player.field_71093_bK || Math.abs(pos.func_74769_h("X") - player.field_70165_t) > 32.0 || Math.abs(pos.func_74769_h("Z") - player.field_70161_v) > 32.0) {
                        EntityTameable familiar;
                        if (lastDimension != player.field_71093_bK && player.field_71093_bK == -1 || lastDimension == -1) {
                            NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
                            nbtPlayer.func_74757_a("WITCVisitedNether", true);
                        }
                        if (Familiar.hasActiveFamiliar(player) && (familiar = Familiar.getFamiliarEntity(player)) != null && !familiar.func_70906_o()) {
                            int ipx = MathHelper.func_76128_c((double)player.field_70165_t) - 2;
                            int j = MathHelper.func_76128_c((double)player.field_70161_v) - 2;
                            int k = MathHelper.func_76128_c((double)player.field_70121_D.field_72338_b) - 2;
                            boolean done = false;
                            for (int l = 0; l <= 4 && !done; ++l) {
                                for (int i1 = 0; i1 <= 4 && !done; ++i1) {
                                    for (int dy = 0; dy <= 4 && !done; ++dy) {
                                        if (!player.field_70170_p.func_147439_a(ipx + l, k + dy - 1, j + i1).isSideSolid((IBlockAccess)player.field_70170_p, ipx + l, k + dy - 1, j + i1, ForgeDirection.UP) || player.field_70170_p.func_147439_a(ipx + l, k + dy, j + i1).func_149721_r() || player.field_70170_p.func_147439_a(ipx + l, k + dy + 1, j + i1).func_149721_r()) continue;
                                        ItemGeneral cfr_ignored_0 = Witchery.Items.GENERIC;
                                        ItemGeneral.teleportToLocation(player.field_70170_p, 0.5 + (double)ipx + (double)l, k + dy, 0.5 + (double)j + (double)i1, player.field_71093_bK, (Entity)familiar, true);
                                        done = true;
                                    }
                                }
                            }
                        }
                    }
                    pos.func_74780_a("X", player.field_70165_t);
                    pos.func_74780_a("Z", player.field_70161_v);
                    pos.func_74768_a("D", player.field_71093_bK);
                } else {
                    NBTTagCompound pos = new NBTTagCompound();
                    pos.func_74780_a("X", player.field_70165_t);
                    pos.func_74780_a("Z", player.field_70161_v);
                    pos.func_74768_a("D", player.field_71093_bK);
                    pos.func_74757_a("visitedNether", player.field_71093_bK == -1);
                }
            }
        }

        private void handleIcySlippersEffect(EntityPlayer player) {
            ItemStack shoes = player.func_82169_q(0);
            if (shoes != null && shoes.func_77973_b() == Witchery.Items.ICY_SLIPPERS) {
                int k = MathHelper.func_76128_c((double)(player.field_70163_u - 1.0));
                for (int i = 0; i < 4; ++i) {
                    int l;
                    int j = MathHelper.func_76128_c((double)(player.field_70165_t + (double)((float)(i % 2 * 2 - 1) * 0.5f)));
                    Block blockID = player.field_70170_p.func_147439_a(j, k, l = MathHelper.func_76128_c((double)(player.field_70161_v + (double)((float)(i / 2 % 2 * 2 - 1) * 0.5f))));
                    if (blockID == Blocks.field_150358_i || blockID == Blocks.field_150355_j) {
                        player.field_70170_p.func_147449_b(j, k, l, Blocks.field_150432_aD);
                        continue;
                    }
                    if (blockID != Blocks.field_150356_k && blockID != Blocks.field_150353_l) continue;
                    player.field_70170_p.func_147449_b(j, k, l, Blocks.field_150343_Z);
                    if (player.field_70170_p.field_73012_v.nextInt(10) != 0) continue;
                    shoes.func_77972_a(1, (EntityLivingBase)player);
                }
            }
        }

        private void handleBrewDepthsEffect(EntityPlayer player, NBTTagCompound nbtTag) {
            if (nbtTag.func_74764_b(Infusion.INFUSION_DEPTHS)) {
                int timeLeft = nbtTag.func_74762_e(Infusion.INFUSION_DEPTHS);
                if (timeLeft > 0) {
                    if (!player.func_70644_a(Potion.field_76427_o)) {
                        player.func_70690_d(new PotionEffect(Potion.field_76427_o.field_76415_H, 6000));
                    }
                    if (!player.func_70055_a(Material.field_151586_h)) {
                        if (!player.func_70644_a(Potion.field_82731_v)) {
                            player.func_70690_d(new PotionEffect(Potion.field_82731_v.field_76415_H, 100, 1));
                        }
                    } else if (player.func_70644_a(Potion.field_82731_v)) {
                        player.func_82170_o(Potion.field_82731_v.field_76415_H);
                    }
                }
                if (--timeLeft <= 0) {
                    nbtTag.func_82580_o(Infusion.INFUSION_DEPTHS);
                    if (player.func_70644_a(Potion.field_76427_o)) {
                        player.func_82170_o(Potion.field_76427_o.field_76415_H);
                    }
                    if (player.func_70644_a(Potion.field_76436_u)) {
                        player.func_82170_o(Potion.field_76436_u.field_76415_H);
                    }
                } else {
                    nbtTag.func_74768_a(Infusion.INFUSION_DEPTHS, timeLeft);
                }
            }
        }

        private void handleBrewGrotesqueEffect(EntityPlayer player, NBTTagCompound nbtTag) {
            if (nbtTag.func_74764_b(Infusion.INFUSION_GROTESQUE)) {
                int timeLeft = nbtTag.func_74762_e(Infusion.INFUSION_GROTESQUE);
                if (timeLeft > 0) {
                    float radius = 4.0f;
                    AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(player.field_70165_t - 4.0), (double)(player.field_70163_u - 4.0), (double)(player.field_70161_v - 4.0), (double)(player.field_70165_t + 4.0), (double)(player.field_70163_u + 4.0), (double)(player.field_70161_v + 4.0));
                    List list = player.field_70170_p.func_72872_a(EntityLiving.class, bounds);
                    for (EntityLiving entity : list) {
                        boolean victim = !(entity instanceof EntityDemon) && !(entity instanceof IBossDisplayData) && !(entity instanceof EntityGolem) && !(entity instanceof EntityWitch);
                        if (!victim || !(Coord.distance(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, player.field_70165_t, player.field_70163_u, player.field_70161_v) < 4.0)) continue;
                        RiteProtectionCircleRepulsive.push(player.field_70170_p, (Entity)entity, player.field_70165_t, player.field_70163_u, player.field_70161_v);
                    }
                }
                if (--timeLeft <= 0) {
                    nbtTag.func_82580_o(Infusion.INFUSION_GROTESQUE);
                    Witchery.packetPipeline.sendToDimension(new PacketPlayerStyle(player), player.field_71093_bK);
                } else {
                    nbtTag.func_74768_a(Infusion.INFUSION_GROTESQUE, timeLeft);
                }
            }
        }

        /*
         * Unable to fully structure code
         * Could not resolve type clashes
         */
        private void handleCurseEffects(EntityLivingBase entity, NBTTagCompound nbtTag) {
            block39: {
                block40: {
                    block41: {
                        if (entity == null || nbtTag == null) break block39;
                        if (!(entity instanceof EntityPlayer) && nbtTag.func_74764_b("witcherySinking")) {
                            level = nbtTag.func_74762_e("witcherySinking");
                            if (level > 0) {
                                if (entity.func_70090_H() || entity instanceof EntityPlayer && !entity.field_70122_E) {
                                    if (entity.field_70181_x < 0.0) {
                                        entity.field_70181_x *= 1.0 + Math.min(0.1 * (double)level, 0.4);
                                    } else if (entity.field_70181_x > 0.0) {
                                        entity.field_70181_x *= 1.0 - Math.min(0.1 * (double)level, 0.4);
                                    }
                                }
                            } else {
                                nbtTag.func_82580_o("witcherySinking");
                            }
                        }
                        if (!nbtTag.func_74764_b("witcheryCursed")) break block40;
                        level = nbtTag.func_74762_e("witcheryCursed");
                        if (level <= 0) break block41;
                        if (entity.func_82165_m(Potion.field_76440_q.field_76415_H) || entity.func_82165_m(Potion.field_76437_t.field_76415_H) || entity.func_82165_m(Potion.field_76419_f.field_76415_H) || entity.func_82165_m(Potion.field_76421_d.field_76415_H) || entity.func_82165_m(Potion.field_76436_u.field_76415_H) || entity.field_70170_p.field_73012_v.nextInt(20) != 0) break block40;
                        switch (entity.field_70170_p.field_73012_v.nextInt(level >= 5 ? 6 : (level >= 4 ? 5 : (level >= 3 ? 4 : (level >= 2 ? 3 : 2))))) {
                            case 0: {
                                entity.func_70690_d(new PotionEffect(Potion.field_76419_f.field_76415_H, 600, Math.min(level - 1, 4)));
                                break;
                            }
                            case 1: {
                                entity.func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 600, Math.min(level - 1, 4)));
                                break;
                            }
                            case 2: {
                                entity.func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, (13 + 2 * level) * 20, Math.min(level - 2, 4)));
                                break;
                            }
                            case 3: {
                                entity.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, 5 * level * 20));
                                if (level <= 5) break;
                                entity.func_70690_d(new PotionEffect(Potion.field_76439_r.field_76415_H, 5 * level * 20));
                                break;
                            }
                            case 5: {
                                if (!(entity instanceof EntityPlayer)) ** GOTO lbl42
                                player = (EntityPlayer)entity;
                                heldItemIndex = player.field_71071_by.field_70461_c;
                                if (player.field_71071_by.field_70462_a[heldItemIndex] != null) {
                                    player.func_71019_a(player.field_71071_by.field_70462_a[heldItemIndex], true);
                                    player.field_71071_by.field_70462_a[heldItemIndex] = null;
                                    break;
                                }
                                break block40;
lbl42:
                                // 1 sources

                                heldItem = entity.func_70694_bm();
                                if (heldItem == null) break;
                                Infusion.dropEntityItemWithRandomChoice(entity, heldItem, true);
                                entity.func_70062_b(0, null);
                            }
                        }
                        break block40;
                    }
                    nbtTag.func_82580_o("witcheryCursed");
                }
                if (nbtTag.func_74764_b("witcheryOverheating")) {
                    level = nbtTag.func_74762_e("witcheryOverheating");
                    if (level > 0) {
                        world = entity.field_70170_p;
                        if (!entity.func_70027_ad() && world.field_73012_v.nextInt(level > 2 ? 20 : (level > 1 ? 25 : 30)) == 0) {
                            x = MathHelper.func_76128_c((double)entity.field_70165_t);
                            z = MathHelper.func_76128_c((double)entity.field_70161_v);
                            biome = world.func_72807_a(x, z);
                            if (!(!((double)biome.field_76750_F >= 1.5) || biome.func_76738_d() && world.func_72896_J() || entity.func_70090_H())) {
                                entity.func_70015_d(Math.min(world.field_73012_v.nextInt(level < 4 ? 2 : level - 1) + 1, 4));
                            }
                        }
                    } else {
                        nbtTag.func_82580_o("witcheryOverheating");
                    }
                }
                if (nbtTag.func_74764_b("witcheryWakingNightmare") && entity instanceof EntityPlayer) {
                    player = (EntityPlayer)entity;
                    level = nbtTag.func_74762_e("witcheryWakingNightmare");
                    if (level > 0 && player.field_71093_bK != Config.instance().dimensionDreamID) {
                        world = player.field_70170_p;
                        if (world.field_73012_v.nextInt(level > 4 ? 30 : (level > 2 ? 60 : 180)) == 0) {
                            R = 16.0;
                            H = 8.0;
                            bounds = AxisAlignedBB.func_72330_a((double)(entity.field_70165_t - 16.0), (double)(entity.field_70163_u - 8.0), (double)(entity.field_70161_v - 16.0), (double)(entity.field_70165_t + 16.0), (double)(entity.field_70163_u + 8.0), (double)(entity.field_70161_v + 16.0));
                            entities = world.func_72872_a(EntityNightmare.class, bounds);
                            doNothing = false;
                            for (E obj : entities) {
                                nightmare = (EntityNightmare)obj;
                                if (!nightmare.getVictimName().equalsIgnoreCase(player.func_70005_c_())) continue;
                                doNothing = true;
                                break;
                            }
                            if (!doNothing) {
                                Infusion.spawnCreature(world, EntityNightmare.class, MathHelper.func_76128_c((double)player.field_70165_t), MathHelper.func_76128_c((double)player.field_70163_u), MathHelper.func_76128_c((double)player.field_70161_v), player, 2, 6);
                            }
                        }
                    } else {
                        nbtTag.func_82580_o("witcheryWakingNightmare");
                    }
                }
                if (entity instanceof EntityPlayer && nbtTag.func_74764_b("witcheryInsanity")) {
                    level = nbtTag.func_74762_e("witcheryInsanity");
                    if (level > 0) {
                        world = entity.field_70170_p;
                        x = MathHelper.func_76128_c((double)entity.field_70165_t);
                        y = MathHelper.func_76128_c((double)entity.field_70163_u);
                        z = MathHelper.func_76128_c((double)entity.field_70161_v);
                        if (world.field_73012_v.nextInt(level > 2 ? 25 : (level > 1 ? 30 : 35)) == 0) {
                            creatureType /* !! */  = null;
                            switch (world.field_73012_v.nextInt(3)) {
                                default: {
                                    creatureType /* !! */  = EntityIllusionCreeper.class;
                                    break;
                                }
                                case 1: {
                                    creatureType /* !! */  = EntityIllusionSpider.class;
                                    break;
                                }
                                case 2: {
                                    creatureType /* !! */  = EntityIllusionZombie.class;
                                }
                            }
                            MAX_DISTANCE = 9;
                            MIN_DISTANCE = 4;
                            Infusion.spawnCreature(world, creatureType /* !! */ , x, y, z, (EntityPlayer)entity, 4, 9);
                        } else if (level >= 4 && world.field_73012_v.nextInt(20) == 0) {
                            sound = SoundEffect.NONE;
                            switch (world.field_73012_v.nextInt(3)) {
                                default: {
                                    sound = SoundEffect.RANDOM_EXPLODE;
                                    break;
                                }
                                case 1: {
                                    sound = SoundEffect.MOB_ENDERMAN_IDLE;
                                }
                            }
                            sound.playOnlyTo((EntityPlayer)entity, 1.0f, 1.0f);
                        }
                    } else {
                        nbtTag.func_82580_o("witcheryInsanity");
                    }
                }
            }
        }

        @SubscribeEvent(priority=EventPriority.HIGH)
        public void onLivingDeath(LivingDeathEvent event) {
            if (!event.entityLiving.field_70170_p.field_72995_K && !event.isCanceled()) {
                if (event.entityLiving instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer)event.entity;
                    World world = player.field_70170_p;
                    NBTTagCompound nbtTag = Infusion.getNBT((Entity)player);
                    if (nbtTag.func_74764_b(Infusion.INFUSION_DEPTHS)) {
                        nbtTag.func_82580_o(Infusion.INFUSION_DEPTHS);
                    }
                    PlayerEffects.onDeath(player);
                }
                Familiar.handleLivingDeath(event);
            }
        }

        @SubscribeEvent
        public void onLivingSetAttackTarget(LivingSetAttackTargetEvent event) {
            if (event.target != null && event.entityLiving instanceof EntityLiving) {
                EntityLiving aggressorEntity = (EntityLiving)event.entityLiving;
                if (event.target instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer)event.target;
                    if (player.func_82150_aj()) {
                        if (aggressorEntity.field_70170_p.func_72846_b(aggressorEntity.field_70165_t, aggressorEntity.field_70163_u, aggressorEntity.field_70161_v, 16.0) != event.target) {
                            aggressorEntity.func_70624_b(null);
                        }
                    } else if (aggressorEntity.func_70644_a(Potion.field_76440_q)) {
                        aggressorEntity.func_70624_b(null);
                    } else if (aggressorEntity instanceof EntityCreeper) {
                        ItemStack stack = player.field_71071_by.func_70440_f(2);
                        if (stack != null && stack.func_77973_b() == Witchery.Items.WITCH_ROBES) {
                            aggressorEntity.func_70624_b(null);
                        }
                    } else if (aggressorEntity.func_70662_br()) {
                        if (aggressorEntity instanceof EntityZombie && ExtendedPlayer.get(player).getVampireLevel() >= 10) {
                            aggressorEntity.func_70624_b(null);
                        } else {
                            ItemStack stack = player.field_71071_by.func_70440_f(2);
                            if (stack != null && stack.func_77973_b() == Witchery.Items.NECROMANCERS_ROBES) {
                                aggressorEntity.func_70624_b(null);
                            }
                        }
                    }
                }
                if (event.target instanceof EntityVillageGuard && event.entityLiving instanceof EntityGolem) {
                    aggressorEntity.func_70624_b(null);
                } else if (Config.instance().isZombeIgnoreVillagerActive() && event.target instanceof EntityVillager && event.entityLiving instanceof EntityZombie) {
                    aggressorEntity.func_70624_b(null);
                }
            }
        }

        @SubscribeEvent
        public void onLivingFall(LivingFallEvent event) {
            if (event.entityLiving instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer)event.entityLiving;
                Registry.instance().get(player).onFalling(player.field_70170_p, player, event);
            }
        }

        @SubscribeEvent
        public void onLivingHurt(LivingHurtEvent event) {
            if (event.entityLiving instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer)event.entityLiving;
                if (event.source.func_76347_k() && event.isCancelable() && !event.isCanceled() && player.func_82169_q(2) != null && player.func_82169_q(2).func_77973_b() == Witchery.Items.DEATH_ROBE) {
                    if (!player.func_82165_m(Potion.field_76426_n.field_76415_H)) {
                        player.func_70690_d(new PotionEffect(Potion.field_76426_n.field_76415_H, 100, 0));
                    }
                    event.setCanceled(true);
                }
                if (!event.isCanceled()) {
                    Registry.instance().get(player).onHurt(player.field_70170_p, player, event);
                }
            }
        }
    }
}

