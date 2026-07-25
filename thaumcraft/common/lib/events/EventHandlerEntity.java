/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.io.Files
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.item.EntityEnderPearl
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.passive.EntityChicken
 *  net.minecraft.entity.passive.EntityCow
 *  net.minecraft.entity.passive.EntityPig
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.common.BiomeDictionary
 *  net.minecraftforge.common.BiomeDictionary$Type
 *  net.minecraftforge.common.util.FakePlayer
 *  net.minecraftforge.event.entity.EntityEvent$EntityConstructing
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.event.entity.item.ItemExpireEvent
 *  net.minecraftforge.event.entity.item.ItemTossEvent
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.entity.living.LivingDropsEvent
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingJumpEvent
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 *  net.minecraftforge.event.entity.player.ArrowLooseEvent
 *  net.minecraftforge.event.entity.player.ArrowNockEvent
 *  net.minecraftforge.event.entity.player.EntityInteractEvent
 *  net.minecraftforge.event.entity.player.EntityItemPickupEvent
 *  net.minecraftforge.event.entity.player.PlayerEvent$BreakSpeed
 *  net.minecraftforge.event.entity.player.PlayerEvent$LoadFromFile
 *  net.minecraftforge.event.entity.player.PlayerEvent$SaveToFile
 *  net.minecraftforge.event.entity.player.PlayerUseItemEvent$Finish
 */
package thaumcraft.common.lib.events;

import com.google.common.io.Files;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRepairableExtended;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.damagesource.DamageSourceThaumcraft;
import thaumcraft.api.entities.ITaintedMob;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigEntities;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.EntityAspectOrb;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.entities.monster.EntityBrainyZombie;
import thaumcraft.common.entities.monster.EntityTaintChicken;
import thaumcraft.common.entities.monster.EntityTaintCow;
import thaumcraft.common.entities.monster.EntityTaintCreeper;
import thaumcraft.common.entities.monster.EntityTaintPig;
import thaumcraft.common.entities.monster.EntityTaintSheep;
import thaumcraft.common.entities.monster.EntityTaintVillager;
import thaumcraft.common.entities.monster.EntityThaumicSlime;
import thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss;
import thaumcraft.common.entities.monster.mods.ChampionModifier;
import thaumcraft.common.entities.projectile.EntityPrimalArrow;
import thaumcraft.common.items.ItemBathSalts;
import thaumcraft.common.items.ItemCrystalEssence;
import thaumcraft.common.items.armor.Hover;
import thaumcraft.common.items.armor.ItemHoverHarness;
import thaumcraft.common.items.equipment.ItemBowBone;
import thaumcraft.common.items.wands.WandManager;
import thaumcraft.common.lib.WarpEvents;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.research.ScanManager;
import thaumcraft.common.lib.utils.EntityUtils;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.lib.world.dim.Cell;
import thaumcraft.common.lib.world.dim.CellLoc;
import thaumcraft.common.lib.world.dim.MazeHandler;
import thaumcraft.common.tiles.TileOwned;

public class EventHandlerEntity {
    public HashMap<Integer, Float> prevStep = new HashMap();
    public static HashMap<String, ArrayList<WeakReference<Entity>>> linkedEntities = new HashMap();

    @SubscribeEvent
    public void droppedItem(ItemTossEvent event) {
        NBTTagCompound itemData = event.entityItem.getEntityData();
        itemData.func_74778_a("thrower", event.player.func_70005_c_());
    }

    @SubscribeEvent
    public void playerLoad(PlayerEvent.LoadFromFile event) {
        EntityPlayer p = event.entityPlayer;
        Thaumcraft.proxy.getPlayerKnowledge().wipePlayerKnowledge(p.func_70005_c_());
        File file1 = this.getPlayerFile("thaum", event.playerDirectory, p.func_70005_c_());
        boolean legacy = false;
        if (!file1.exists()) {
            File filep = event.getPlayerFile("thaum");
            if (filep.exists()) {
                try {
                    Files.copy((File)filep, (File)file1);
                    Thaumcraft.log.info("Using and converting UUID Thaumcraft savefile for " + p.func_70005_c_());
                    legacy = true;
                    filep.delete();
                    File fb = event.getPlayerFile("thaumback");
                    if (fb.exists()) {
                        fb.delete();
                    }
                }
                catch (IOException e) {}
            } else {
                File filet = this.getLegacyPlayerFile(p);
                if (filet.exists()) {
                    try {
                        Files.copy((File)filet, (File)file1);
                        Thaumcraft.log.info("Using pre MC 1.7.10 Thaumcraft savefile for " + p.func_70005_c_());
                        legacy = true;
                    }
                    catch (IOException e) {
                        // empty catch block
                    }
                }
            }
        }
        ResearchManager.loadPlayerData(p, file1, this.getPlayerFile("thaumback", event.playerDirectory, p.func_70005_c_()), legacy);
        Collection<ResearchCategoryList> rc = ResearchCategories.researchCategories.values();
        for (ResearchCategoryList cat : rc) {
            Collection<ResearchItem> res = cat.research.values();
            for (ResearchItem ri : res) {
                if (!ri.isAutoUnlock()) continue;
                Thaumcraft.proxy.getResearchManager().completeResearch(p, ri.key);
            }
        }
    }

    public File getLegacyPlayerFile(EntityPlayer player) {
        try {
            File playersDirectory = new File(player.field_70170_p.func_72860_G().func_75765_b(), "players");
            return new File(playersDirectory, player.func_70005_c_() + ".thaum");
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public File getPlayerFile(String suffix, File playerDirectory, String playername) {
        if ("dat".equals(suffix)) {
            throw new IllegalArgumentException("The suffix 'dat' is reserved");
        }
        return new File(playerDirectory, playername + "." + suffix);
    }

    @SubscribeEvent
    public void playerSave(PlayerEvent.SaveToFile event) {
        EntityPlayer p = event.entityPlayer;
        ResearchManager.savePlayerData(p, this.getPlayerFile("thaum", event.playerDirectory, p.func_70005_c_()), this.getPlayerFile("thaumback", event.playerDirectory, p.func_70005_c_()));
    }

    public static void doRepair(ItemStack is, EntityPlayer player) {
        AspectList cost;
        int level = EnchantmentHelper.func_77506_a((int)Config.enchRepair.field_77352_x, (ItemStack)is);
        if (level <= 0) {
            return;
        }
        if (level > 2) {
            level = 2;
        }
        if ((cost = ThaumcraftCraftingManager.getObjectTags(is)) == null || cost.size() == 0) {
            return;
        }
        cost = ResearchManager.reduceToPrimals(cost);
        AspectList finalCost = new AspectList();
        for (Aspect a : cost.getAspects()) {
            if (a == null) continue;
            finalCost.merge(a, (int)Math.sqrt(cost.getAmount(a) * 2) * level);
        }
        if (is.func_77973_b() instanceof IRepairableExtended) {
            if (((IRepairableExtended)is.func_77973_b()).doRepair(is, player, level) && WandManager.consumeVisFromInventory(player, finalCost)) {
                is.func_77972_a(-level, (EntityLivingBase)player);
            }
        } else if (WandManager.consumeVisFromInventory(player, finalCost)) {
            is.func_77972_a(-level, (EntityLivingBase)player);
        }
    }

    @SubscribeEvent
    public void livingTick(LivingEvent.LivingUpdateEvent event) {
        EntityMob mob;
        int t;
        if (event.entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.entity;
            if (event.entity.field_70170_p.field_73011_w.field_76574_g == Config.dimensionOuterId && !player.field_71075_bZ.field_75098_d && player.field_70173_aa % 20 == 0 && (player.field_71075_bZ.field_75100_b || Hover.getHover(player.func_145782_y()))) {
                player.field_71075_bZ.field_75100_b = false;
                Hover.setHover(player.func_145782_y(), false);
                player.func_145747_a((IChatComponent)new ChatComponentText(EnumChatFormatting.ITALIC + "" + EnumChatFormatting.GRAY + StatCollector.func_74838_a((String)"tc.break.fly")));
            }
            if (Hover.getHover(player.func_145782_y()) && (player.field_71071_by.func_70440_f(2) == null || !(player.field_71071_by.func_70440_f(2).func_77973_b() instanceof ItemHoverHarness))) {
                Hover.setHover(player.func_145782_y(), false);
                player.field_71075_bZ.field_75100_b = false;
            }
            if (!event.entity.field_70170_p.field_72995_K) {
                if (!Config.wuss && player.field_70173_aa > 0 && player.field_70173_aa % 2000 == 0 && !player.func_82165_m(Config.potionWarpWardID)) {
                    WarpEvents.checkWarpEvent(player);
                }
                if (player.field_70173_aa % 10 == 0 && player.func_82165_m(Config.potionDeathGazeID)) {
                    WarpEvents.checkDeathGaze(player);
                }
                if (player.field_70173_aa % 40 == 0) {
                    ItemStack is;
                    int a = 0;
                    while (true) {
                        EntityPlayer entityPlayer = player;
                        if (a >= entityPlayer.field_71071_by.func_70451_h()) break;
                        if (player.field_71071_by.field_70462_a[a] != null && (is = player.field_71071_by.field_70462_a[a]).func_77960_j() > 0 && is.func_77973_b() instanceof IRepairable && !player.field_71075_bZ.field_75098_d && !(is.func_77973_b() instanceof ItemHoverHarness)) {
                            EventHandlerEntity.doRepair(is, player);
                        }
                        ++a;
                    }
                    for (a = 0; a < 4; ++a) {
                        if (player.field_71071_by.func_70440_f(a) == null || (is = player.field_71071_by.func_70440_f(a)).func_77960_j() <= 0 || !(is.func_77973_b() instanceof IRepairable) || player.field_71075_bZ.field_75098_d) continue;
                        EventHandlerEntity.doRepair(is, player);
                    }
                }
            }
            this.updateSpeed(player);
            if (player.field_70170_p.field_72995_K && (player.func_70093_af() || player.field_71071_by.func_70440_f(0) == null || player.field_71071_by.func_70440_f(0).func_77973_b() != ConfigItems.itemBootsTraveller) && this.prevStep.containsKey(player.func_145782_y())) {
                player.field_70138_W = this.prevStep.get(player.func_145782_y()).floatValue();
                this.prevStep.remove(player.func_145782_y());
            }
        }
        if (event.entity instanceof EntityMob && !event.entity.field_70128_L && (t = (int)(mob = (EntityMob)event.entity).func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e()) >= 0 && ChampionModifier.mods[t].type == 0) {
            ChampionModifier.mods[t].effect.performEffect((EntityLivingBase)mob, null, null, 0.0f);
        }
    }

    private void updateSpeed(EntityPlayer player) {
        try {
            int haste;
            if (!player.field_71075_bZ.field_75100_b && player.field_71071_by.func_70440_f(0) != null && player.field_70701_bs > 0.0f && (haste = EnchantmentHelper.func_77506_a((int)Config.enchHaste.field_77352_x, (ItemStack)player.field_71071_by.func_70440_f(0))) > 0) {
                float bonus = (float)haste * 0.015f;
                if (player.field_70160_al) {
                    bonus /= 2.0f;
                }
                if (player.func_70090_H()) {
                    bonus /= 2.0f;
                }
                player.func_70060_a(0.0f, 1.0f, bonus);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @SubscribeEvent
    public void playerJumps(LivingEvent.LivingJumpEvent event) {
        if (event.entity instanceof EntityPlayer && ((EntityPlayer)event.entity).field_71071_by.func_70440_f(0) != null && ((EntityPlayer)event.entity).field_71071_by.func_70440_f(0).func_77973_b() == ConfigItems.itemBootsTraveller) {
            event.entityLiving.field_70181_x += (double)0.275f;
        }
    }

    @SubscribeEvent
    public void playerInteract(EntityInteractEvent event) {
        if (event.target instanceof EntityGolemBase && ((EntityGolemBase)event.target).getOwnerName().length() > 0 && !((EntityGolemBase)event.target).getOwnerName().equals(event.entityPlayer.func_70005_c_())) {
            if (!event.entityPlayer.field_70170_p.field_72995_K) {
                event.entityPlayer.func_145747_a((IChatComponent)new ChatComponentTranslation("You are not my Master!", new Object[0]));
            }
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void entitySpawns(EntityJoinWorldEvent event) {
        if (!event.world.field_72995_K) {
            EntityMob mob;
            if (event.entity instanceof EntityEnderPearl) {
                int x = MathHelper.func_76128_c((double)event.entity.field_70165_t);
                int y = MathHelper.func_76128_c((double)event.entity.field_70163_u);
                int z = MathHelper.func_76128_c((double)event.entity.field_70161_v);
                block0: for (int xx = -5; xx <= 5; ++xx) {
                    for (int yy = -5; yy <= 5; ++yy) {
                        for (int zz = -5; zz <= 5; ++zz) {
                            TileEntity tile = event.world.func_147438_o(x + xx, y + yy, z + zz);
                            if (tile == null || !(tile instanceof TileOwned)) continue;
                            if (((EntityEnderPearl)event.entity).func_85052_h() instanceof EntityPlayer) {
                                ((EntityPlayer)((EntityEnderPearl)event.entity).func_85052_h()).func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7oThe magic of a nearby warded object destroys the ender pearl."));
                            }
                            event.entity.func_70106_y();
                            break block0;
                        }
                    }
                }
            }
            if (event.entity instanceof EntityPlayer) {
                ArrayList<WeakReference<Entity>> dudes = linkedEntities.get(event.entity.func_70005_c_());
                if (dudes != null) {
                    for (WeakReference<Entity> dude : dudes) {
                        if (dude.get() == null || ((Entity)dude.get()).field_71088_bW != 0) continue;
                        ((Entity)dude.get()).field_71088_bW = ((Entity)dude.get()).func_82147_ab();
                        ((Entity)dude.get()).func_71027_c(event.world.field_73011_w.field_76574_g);
                    }
                }
            } else if (event.entity instanceof EntityMob && (mob = (EntityMob)event.entity).func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e() < -1.0) {
                BiomeGenBase bg;
                int c = event.world.field_73012_v.nextInt(100);
                if (event.world.field_73013_u == EnumDifficulty.EASY || !Config.championMobs) {
                    c += 2;
                }
                if (event.world.field_73013_u == EnumDifficulty.HARD) {
                    c -= Config.championMobs ? 2 : 0;
                }
                if (event.world.field_73011_w.field_76574_g == Config.dimensionOuterId) {
                    c -= 3;
                }
                if (BiomeDictionary.isBiomeOfType((BiomeGenBase)(bg = mob.field_70170_p.func_72807_a(MathHelper.func_76143_f((double)mob.field_70165_t), MathHelper.func_76143_f((double)mob.field_70161_v))), (BiomeDictionary.Type)BiomeDictionary.Type.SPOOKY) || BiomeDictionary.isBiomeOfType((BiomeGenBase)bg, (BiomeDictionary.Type)BiomeDictionary.Type.NETHER) || BiomeDictionary.isBiomeOfType((BiomeGenBase)bg, (BiomeDictionary.Type)BiomeDictionary.Type.END)) {
                    c -= Config.championMobs ? 2 : 1;
                }
                if (this.isDangerousLocation(mob.field_70170_p, MathHelper.func_76143_f((double)mob.field_70165_t), MathHelper.func_76143_f((double)mob.field_70163_u), MathHelper.func_76143_f((double)mob.field_70161_v))) {
                    c -= Config.championMobs ? 10 : 3;
                }
                int cc = 0;
                boolean whitelisted = false;
                for (Class clazz : ConfigEntities.championModWhitelist.keySet()) {
                    if (!clazz.isAssignableFrom(event.entity.getClass())) continue;
                    whitelisted = true;
                    if (!Config.championMobs && !(event.entity instanceof EntityThaumcraftBoss)) continue;
                    cc = Math.max(cc, ConfigEntities.championModWhitelist.get(clazz) - 1);
                }
                if (whitelisted && (c -= cc) <= 0 && mob.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() >= 10.0) {
                    EntityUtils.makeChampion(mob, false);
                } else {
                    IAttributeInstance modai = mob.func_110148_a(EntityUtils.CHAMPION_MOD);
                    modai.func_111124_b(ChampionModifier.ATTRIBUTE_MOD_NONE);
                    modai.func_111121_a(ChampionModifier.ATTRIBUTE_MOD_NONE);
                }
            }
        }
    }

    private boolean isDangerousLocation(World world, int x, int y, int z) {
        int zz;
        int xx;
        Cell c;
        return world.field_73011_w.field_76574_g == Config.dimensionOuterId && (c = MazeHandler.getFromHashMap(new CellLoc(xx = x >> 4, zz = z >> 4))) != null && (c.feature == 6 || c.feature == 8);
    }

    @SubscribeEvent
    public void entityConstuct(EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityMob) {
            EntityMob mob = (EntityMob)event.entity;
            mob.func_110140_aT().func_111150_b(EntityUtils.CHAMPION_MOD).func_111128_a(-2.0);
        }
    }

    @SubscribeEvent
    public void itemPickup(EntityItemPickupEvent event) {
        if (event.entityPlayer.func_70005_c_().startsWith("FakeThaumcraft")) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void livingDrops(LivingDropsEvent event) {
        AspectList aspects;
        boolean fakeplayer;
        boolean bl = fakeplayer = event.source.func_76346_g() != null && event.source.func_76346_g() instanceof FakePlayer;
        if (!event.entity.field_70170_p.field_72995_K && event.recentlyHit && !fakeplayer && event.entity instanceof EntityMob && !(event.entity instanceof EntityThaumcraftBoss) && ((EntityMob)event.entity).func_110148_a(EntityUtils.CHAMPION_MOD).func_111126_e() >= 0.0) {
            int j;
            for (int i = 5 + event.entity.field_70170_p.field_73012_v.nextInt(3); i > 0; i -= j) {
                j = EntityXPOrb.func_70527_a((int)i);
                event.entity.field_70170_p.func_72838_d((Entity)new EntityXPOrb(event.entity.field_70170_p, event.entity.field_70165_t, event.entity.field_70163_u, event.entity.field_70161_v, j));
            }
            int lb = Math.min(2, MathHelper.func_76141_d((float)((float)(event.entity.field_70170_p.field_73012_v.nextInt(9) + event.lootingLevel) / 5.0f)));
            event.drops.add(new EntityItem(event.entity.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + (double)event.entityLiving.func_70047_e(), event.entityLiving.field_70161_v, new ItemStack(ConfigItems.itemLootbag, 1, lb)));
        }
        if (event.entityLiving instanceof EntityZombie && !(event.entityLiving instanceof EntityBrainyZombie) && event.recentlyHit && event.entity.field_70170_p.field_73012_v.nextInt(10) - event.lootingLevel < 1) {
            event.drops.add(new EntityItem(event.entity.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + (double)event.entityLiving.func_70047_e(), event.entityLiving.field_70161_v, new ItemStack(ConfigItems.itemZombieBrain)));
        }
        if (event.entityLiving instanceof EntityVillager && event.entity.field_70170_p.field_73012_v.nextInt(10) - event.lootingLevel < 1) {
            event.drops.add(new EntityItem(event.entity.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + (double)event.entityLiving.func_70047_e(), event.entityLiving.field_70161_v, new ItemStack(ConfigItems.itemResource, 1, 18)));
        }
        if (event.source == DamageSourceThaumcraft.dissolve && (aspects = ScanManager.generateEntityAspects((Entity)event.entityLiving)) != null && aspects.size() > 0) {
            for (Aspect aspect : aspects.getAspects()) {
                if (event.entity.field_70170_p.field_73012_v.nextBoolean()) continue;
                int size = 1 + event.entity.field_70170_p.field_73012_v.nextInt(aspects.getAmount(aspect));
                size = Math.max(1, size / 2);
                ItemStack stack = new ItemStack(ConfigItems.itemCrystalEssence, size, 0);
                ((ItemCrystalEssence)stack.func_77973_b()).setAspects(stack, new AspectList().add(aspect, 1));
                event.drops.add(new EntityItem(event.entity.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u + (double)event.entityLiving.func_70047_e(), event.entityLiving.field_70161_v, stack));
            }
        }
    }

    @SubscribeEvent
    public void livingTick(LivingDeathEvent event) {
        AspectList aspectsCompound;
        if (!event.entityLiving.field_70170_p.field_72995_K && !(event.entityLiving instanceof ITaintedMob) && event.entityLiving.func_82165_m(Config.potionTaintPoisonID)) {
            EntityMob entity = null;
            if (event.entityLiving instanceof EntityCreeper) {
                entity = new EntityTaintCreeper(event.entityLiving.field_70170_p);
            } else if (event.entityLiving instanceof EntitySheep) {
                entity = new EntityTaintSheep(event.entityLiving.field_70170_p);
            } else if (event.entityLiving instanceof EntityCow) {
                entity = new EntityTaintCow(event.entityLiving.field_70170_p);
            } else if (event.entityLiving instanceof EntityPig) {
                entity = new EntityTaintPig(event.entityLiving.field_70170_p);
            } else if (event.entityLiving instanceof EntityChicken) {
                entity = new EntityTaintChicken(event.entityLiving.field_70170_p);
            } else if (event.entityLiving instanceof EntityVillager) {
                entity = new EntityTaintVillager(event.entityLiving.field_70170_p);
            } else {
                entity = new EntityThaumicSlime(event.entityLiving.field_70170_p);
                if (entity != null) {
                    ((EntityThaumicSlime)entity).setSlimeSize((int)(1.0f + Math.min(event.entityLiving.func_110138_aP() / 10.0f, 6.0f)));
                }
            }
            if (entity != null) {
                entity.func_70012_b(event.entityLiving.field_70165_t, event.entityLiving.field_70163_u, event.entityLiving.field_70161_v, event.entityLiving.field_70177_z, 0.0f);
                event.entityLiving.field_70170_p.func_72838_d((Entity)entity);
                event.entityLiving.func_70106_y();
            }
        } else if (!event.entityLiving.field_70170_p.field_72995_K && EntityUtils.getRecentlyHit(event.entityLiving) > 0 && (aspectsCompound = ScanManager.generateEntityAspects((Entity)event.entityLiving)) != null && aspectsCompound.size() > 0) {
            AspectList aspects = ResearchManager.reduceToPrimals(aspectsCompound);
            for (Aspect aspect : aspects.getAspects()) {
                if (!event.entityLiving.field_70170_p.field_73012_v.nextBoolean()) continue;
                EntityAspectOrb orb = new EntityAspectOrb(event.entityLiving.field_70170_p, event.entityLiving.field_70165_t, event.entityLiving.field_70163_u, event.entityLiving.field_70161_v, aspect, 1 + event.entityLiving.field_70170_p.field_73012_v.nextInt(aspects.getAmount(aspect)));
                event.entityLiving.field_70170_p.func_72838_d((Entity)orb);
            }
        }
    }

    @SubscribeEvent
    public void bowNocked(ArrowNockEvent event) {
        if (event.entityPlayer.field_71071_by.func_146028_b(ConfigItems.itemPrimalArrow)) {
            event.entityPlayer.func_71008_a(event.result, event.result.func_77973_b().func_77626_a(event.result));
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void bowShot(ArrowLooseEvent event) {
        if (event.entityPlayer.field_71071_by.func_146028_b(ConfigItems.itemPrimalArrow)) {
            float f = 0.0f;
            float dam = 2.0f;
            if (event.bow.func_77973_b() instanceof ItemBowBone) {
                f = (float)event.charge / 10.0f;
                if ((double)(f = (f * f + f * 2.0f) / 3.0f) < 0.1) {
                    return;
                }
                dam = 2.5f;
            } else {
                f = (float)event.charge / 20.0f;
                if ((double)(f = (f * f + f * 2.0f) / 3.0f) < 0.1) {
                    return;
                }
            }
            if (f > 1.0f) {
                f = 1.0f;
            }
            int type = 0;
            for (int j = 0; j < event.entityPlayer.field_71071_by.field_70462_a.length; ++j) {
                if (event.entityPlayer.field_71071_by.field_70462_a[j] == null || event.entityPlayer.field_71071_by.field_70462_a[j].func_77973_b() != ConfigItems.itemPrimalArrow) continue;
                type = event.entityPlayer.field_71071_by.field_70462_a[j].func_77960_j();
                break;
            }
            EntityPrimalArrow entityarrow = new EntityPrimalArrow(event.entityPlayer.field_70170_p, (EntityLivingBase)event.entityPlayer, f * dam, type);
            if (event.bow.func_77973_b() instanceof ItemBowBone) {
                entityarrow.func_70239_b(entityarrow.func_70242_d() + 0.5);
            } else if (f == 1.0f) {
                entityarrow.func_70243_d(true);
            }
            int k = EnchantmentHelper.func_77506_a((int)Enchantment.field_77345_t.field_77352_x, (ItemStack)event.bow);
            if (k > 0) {
                entityarrow.func_70239_b(entityarrow.func_70242_d() + (double)k * 0.5 + 0.5);
            }
            int l = EnchantmentHelper.func_77506_a((int)Enchantment.field_77344_u.field_77352_x, (ItemStack)event.bow);
            if (type == 3) {
                ++l;
            }
            if (l > 0) {
                entityarrow.func_70240_a(l);
            }
            if (EnchantmentHelper.func_77506_a((int)Enchantment.field_77343_v.field_77352_x, (ItemStack)event.bow) > 0) {
                entityarrow.func_70015_d(100);
            }
            event.bow.func_77972_a(1, (EntityLivingBase)event.entityPlayer);
            event.entityPlayer.field_70170_p.func_72956_a((Entity)event.entityPlayer, "random.bow", 1.0f, 1.0f / (event.entityPlayer.field_70170_p.field_73012_v.nextFloat() * 0.4f + 1.2f) + f * 0.5f);
            boolean flag = false;
            if (EnchantmentHelper.func_77506_a((int)Enchantment.field_77342_w.field_77352_x, (ItemStack)event.bow) > 0 && event.entityPlayer.field_70170_p.field_73012_v.nextFloat() < 0.33f) {
                flag = true;
            }
            if (!event.entityPlayer.field_71075_bZ.field_75098_d || !flag) {
                InventoryUtils.consumeInventoryItem(event.entityPlayer, ConfigItems.itemPrimalArrow, type);
            }
            if (!event.entityPlayer.field_70170_p.field_72995_K) {
                event.entityPlayer.field_70170_p.func_72838_d((Entity)entityarrow);
            }
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void finishedUsingItem(PlayerUseItemEvent.Finish event) {
        if (!event.entity.field_70170_p.field_72995_K && event.entityPlayer.func_82165_m(Config.potionUnHungerID)) {
            if (event.item.func_77969_a(new ItemStack(Items.field_151078_bh)) || event.item.func_77969_a(new ItemStack(ConfigItems.itemZombieBrain))) {
                PotionEffect pe = event.entityPlayer.func_70660_b(Potion.field_76425_a[Config.potionUnHungerID]);
                int amp = pe.func_76458_c() - 1;
                int duration = pe.func_76459_b() - 600;
                event.entityPlayer.func_82170_o(Config.potionUnHungerID);
                if (duration > 0 && amp >= 0) {
                    pe = new PotionEffect(Config.potionUnHungerID, duration, amp, true);
                    pe.getCurativeItems().clear();
                    pe.addCurativeItem(new ItemStack(Items.field_151078_bh));
                    event.entityPlayer.func_70690_d(pe);
                }
                event.entityPlayer.func_145747_a((IChatComponent)new ChatComponentText("\u00a72\u00a7o" + StatCollector.func_74838_a((String)"warp.text.hunger.2")));
            } else if (event.item.func_77973_b() instanceof ItemFood) {
                event.entityPlayer.func_145747_a((IChatComponent)new ChatComponentText("\u00a74\u00a7o" + StatCollector.func_74838_a((String)"warp.text.hunger.1")));
            }
        }
    }

    @SubscribeEvent
    public void itemExpire(ItemExpireEvent event) {
        int z;
        int y;
        int x;
        if (event.entityItem.func_92059_d() != null && event.entityItem.func_92059_d().func_77973_b() != null && event.entityItem.func_92059_d().func_77973_b() instanceof ItemBathSalts && event.entityItem.field_70170_p.func_147439_a(x = MathHelper.func_76128_c((double)event.entityItem.field_70165_t), y = MathHelper.func_76128_c((double)event.entityItem.field_70163_u), z = MathHelper.func_76128_c((double)event.entityItem.field_70161_v)) == Blocks.field_150355_j && event.entityItem.field_70170_p.func_72805_g(x, y, z) == 0) {
            event.entityItem.field_70170_p.func_147449_b(x, y, z, ConfigBlocks.blockFluidPure);
        }
    }

    @SubscribeEvent
    public void breakSpeedEvent(PlayerEvent.BreakSpeed event) {
        if (!event.entityPlayer.field_70122_E && Hover.getHover(event.entityPlayer.func_145782_y())) {
            event.newSpeed = event.originalSpeed * 5.0f;
        }
    }
}

