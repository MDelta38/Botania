/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaublesApi
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package thaumcraft.common.lib;

import baubles.api.BaublesApi;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.monster.EntityEldritchGuardian;
import thaumcraft.common.entities.monster.EntityMindSpider;
import thaumcraft.common.items.armor.ItemFortressArmor;
import thaumcraft.common.lib.events.EventHandlerRunic;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.misc.PacketMiscEvent;
import thaumcraft.common.lib.network.playerdata.PacketAspectPool;
import thaumcraft.common.lib.network.playerdata.PacketResearchComplete;
import thaumcraft.common.lib.network.playerdata.PacketSyncWarp;
import thaumcraft.common.lib.network.playerdata.PacketWarpMessage;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.utils.EntityUtils;

public class WarpEvents {
    public static void checkWarpEvent(EntityPlayer player) {
        int warp = Thaumcraft.proxy.getPlayerKnowledge().getWarpTotal(player.func_70005_c_());
        int actualwarp = Thaumcraft.proxy.getPlayerKnowledge().getWarpPerm(player.func_70005_c_()) + Thaumcraft.proxy.getPlayerKnowledge().getWarpSticky(player.func_70005_c_());
        int warpCounter = Thaumcraft.proxy.getPlayerKnowledge().getWarpCounter(player.func_70005_c_());
        int r = player.field_70170_p.field_73012_v.nextInt(100);
        if (warpCounter > 0 && (warp += WarpEvents.getWarpFromGear(player)) > 0 && (double)r <= Math.sqrt(warpCounter)) {
            warp = Math.min(100, (warp + warp + warpCounter) / 3);
            warpCounter = (int)((double)warpCounter - Math.max(5.0, Math.sqrt(warpCounter) * 2.0));
            Thaumcraft.proxy.getPlayerKnowledge().setWarpCounter(player.func_70005_c_(), warpCounter);
            int eff = player.field_70170_p.field_73012_v.nextInt(warp);
            ItemStack helm = player.field_71071_by.field_70460_b[3];
            if (helm != null && helm.func_77973_b() instanceof ItemFortressArmor && helm.func_77942_o() && helm.field_77990_d.func_74764_b("mask") && helm.field_77990_d.func_74762_e("mask") == 0) {
                eff -= 2 + player.field_70170_p.field_73012_v.nextInt(4);
            }
            PacketHandler.INSTANCE.sendTo((IMessage)new PacketMiscEvent(0), (EntityPlayerMP)player);
            if (eff > 0) {
                if (eff <= 4) {
                    WarpEvents.grantResearch(player, 1);
                    player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"warp.text.3")));
                } else if (eff > 8) {
                    if (eff <= 12) {
                        player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"warp.text.11")));
                    } else if (eff <= 16) {
                        PotionEffect pe = new PotionEffect(Config.potionVisExhaustID, 5000, Math.min(3, warp / 15), true);
                        pe.getCurativeItems().clear();
                        try {
                            player.func_70690_d(pe);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"warp.text.1")));
                    } else if (eff <= 20) {
                        PotionEffect pe = new PotionEffect(Config.potionThaumarhiaID, Math.min(32000, 10 * warp), 0, true);
                        pe.getCurativeItems().clear();
                        try {
                            player.func_70690_d(pe);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"warp.text.15")));
                    } else if (eff <= 24) {
                        PotionEffect pe = new PotionEffect(Config.potionUnHungerID, 5000, Math.min(3, warp / 15), true);
                        pe.getCurativeItems().clear();
                        pe.addCurativeItem(new ItemStack(Items.field_151078_bh));
                        pe.addCurativeItem(new ItemStack(ConfigItems.itemZombieBrain));
                        try {
                            player.func_70690_d(pe);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"warp.text.2")));
                    } else if (eff <= 28) {
                        player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"warp.text.12")));
                    } else if (eff <= 32) {
                        WarpEvents.spawnMist(player, warp, 1);
                    } else if (eff <= 36) {
                        try {
                            player.func_70690_d(new PotionEffect(Config.potionBlurredID, Math.min(32000, 10 * warp), 0, true));
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (eff <= 40) {
                        PotionEffect pe = new PotionEffect(Config.potionSunScornedID, 5000, Math.min(3, warp / 15), true);
                        pe.getCurativeItems().clear();
                        try {
                            player.func_70690_d(pe);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"warp.text.5")));
                    } else if (eff <= 44) {
                        try {
                            player.func_70690_d(new PotionEffect(Potion.field_76419_f.field_76415_H, 1200, Math.min(3, warp / 15), true));
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"warp.text.9")));
                    } else if (eff <= 48) {
                        PotionEffect pe = new PotionEffect(Config.potionInfVisExhaustID, 6000, Math.min(3, warp / 15), false);
                        pe.getCurativeItems().clear();
                        try {
                            player.func_70690_d(pe);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"warp.text.1")));
                    } else if (eff <= 52) {
                        player.func_70690_d(new PotionEffect(Potion.field_76439_r.field_76415_H, Math.min(40 * warp, 6000), 0, true));
                        player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"warp.text.10")));
                    } else if (eff <= 56) {
                        PotionEffect pe = new PotionEffect(Config.potionDeathGazeID, 6000, Math.min(3, warp / 15), true);
                        pe.getCurativeItems().clear();
                        try {
                            player.func_70690_d(pe);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"warp.text.4")));
                    } else if (eff <= 60) {
                        WarpEvents.suddenlySpiders(player, warp, false);
                    } else if (eff <= 64) {
                        player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"warp.text.13")));
                    } else if (eff <= 68) {
                        WarpEvents.spawnMist(player, warp, warp / 30);
                    } else if (eff <= 72) {
                        try {
                            player.func_70690_d(new PotionEffect(Potion.field_76440_q.field_76415_H, Math.min(32000, 5 * warp), 0, true));
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (eff == 76) {
                        if (Thaumcraft.proxy.getPlayerKnowledge().getWarpSticky(player.func_70005_c_()) > 0) {
                            Thaumcraft.proxy.getPlayerKnowledge().addWarpSticky(player.func_70005_c_(), -1);
                            PacketHandler.INSTANCE.sendTo((IMessage)new PacketSyncWarp(player, 1), (EntityPlayerMP)player);
                            PacketHandler.INSTANCE.sendTo((IMessage)new PacketWarpMessage(player, 1, -1), (EntityPlayerMP)player);
                        }
                        player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"warp.text.14")));
                    } else if (eff <= 80) {
                        PotionEffect pe = new PotionEffect(Config.potionUnHungerID, 6000, Math.min(3, warp / 15), true);
                        pe.getCurativeItems().clear();
                        pe.addCurativeItem(new ItemStack(Items.field_151078_bh));
                        pe.addCurativeItem(new ItemStack(ConfigItems.itemZombieBrain));
                        try {
                            player.func_70690_d(pe);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                        player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"warp.text.2")));
                    } else if (eff <= 84) {
                        WarpEvents.grantResearch(player, warp / 10);
                        player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"warp.text.3")));
                    } else if (eff > 88) {
                        if (eff <= 92) {
                            WarpEvents.suddenlySpiders(player, warp, true);
                        } else {
                            WarpEvents.spawnMist(player, warp, warp / 15);
                        }
                    }
                }
            }
            if (actualwarp > 10 && !ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), "BATHSALTS") && !ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), "@BATHSALTS")) {
                player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"warp.text.8")));
                PacketHandler.INSTANCE.sendTo((IMessage)new PacketResearchComplete("@BATHSALTS"), (EntityPlayerMP)player);
                Thaumcraft.proxy.getResearchManager().completeResearch(player, "@BATHSALTS");
            }
            if (actualwarp > 25 && !ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), "ELDRITCHMINOR")) {
                WarpEvents.grantResearch(player, 10);
                PacketHandler.INSTANCE.sendTo((IMessage)new PacketResearchComplete("ELDRITCHMINOR"), (EntityPlayerMP)player);
                Thaumcraft.proxy.getResearchManager().completeResearch(player, "ELDRITCHMINOR");
            }
            if (actualwarp > 50 && !ThaumcraftApiHelper.isResearchComplete(player.func_70005_c_(), "ELDRITCHMAJOR")) {
                WarpEvents.grantResearch(player, 20);
                PacketHandler.INSTANCE.sendTo((IMessage)new PacketResearchComplete("ELDRITCHMAJOR"), (EntityPlayerMP)player);
                Thaumcraft.proxy.getResearchManager().completeResearch(player, "ELDRITCHMAJOR");
            }
        }
        Thaumcraft.proxy.getPlayerKnowledge().addWarpTemp(player.func_70005_c_(), -1);
        PacketHandler.INSTANCE.sendTo((IMessage)new PacketSyncWarp(player, 2), (EntityPlayerMP)player);
    }

    private static void spawnMist(EntityPlayer player, int warp, int guardian) {
        PacketHandler.INSTANCE.sendTo((IMessage)new PacketMiscEvent(1), (EntityPlayerMP)player);
        if (guardian > 0) {
            guardian = Math.min(8, guardian);
            for (int a = 0; a < guardian; ++a) {
                WarpEvents.spawnGuardian(player);
            }
        }
        player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"warp.text.6")));
    }

    private static void grantResearch(EntityPlayer player, int times) {
        int amt = 1 + player.field_70170_p.field_73012_v.nextInt(times);
        for (int a = 0; a < amt; ++a) {
            Aspect aspect = Aspect.getPrimalAspects().get(player.field_70170_p.field_73012_v.nextInt(6));
            Thaumcraft.proxy.playerKnowledge.addAspectPool(player.func_70005_c_(), aspect, (short)1);
            PacketHandler.INSTANCE.sendTo((IMessage)new PacketAspectPool(aspect.getTag(), (short)1, Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.func_70005_c_(), aspect)), (EntityPlayerMP)player);
        }
        ResearchManager.scheduleSave(player);
    }

    private static void spawnGuardian(EntityPlayer player) {
        EntityEldritchGuardian eg = new EntityEldritchGuardian(player.field_70170_p);
        int i = MathHelper.func_76128_c((double)player.field_70165_t);
        int j = MathHelper.func_76128_c((double)player.field_70163_u);
        int k = MathHelper.func_76128_c((double)player.field_70161_v);
        for (int l = 0; l < 50; ++l) {
            int k1;
            int j1;
            int i1 = i + MathHelper.func_76136_a((Random)player.field_70170_p.field_73012_v, (int)7, (int)24) * MathHelper.func_76136_a((Random)player.field_70170_p.field_73012_v, (int)-1, (int)1);
            if (!World.func_147466_a((IBlockAccess)player.field_70170_p, (int)i1, (int)((j1 = j + MathHelper.func_76136_a((Random)player.field_70170_p.field_73012_v, (int)7, (int)24) * MathHelper.func_76136_a((Random)player.field_70170_p.field_73012_v, (int)-1, (int)1)) - 1), (int)(k1 = k + MathHelper.func_76136_a((Random)player.field_70170_p.field_73012_v, (int)7, (int)24) * MathHelper.func_76136_a((Random)player.field_70170_p.field_73012_v, (int)-1, (int)1)))) continue;
            eg.func_70107_b(i1, j1, k1);
            if (!player.field_70170_p.func_72855_b(eg.field_70121_D) || !player.field_70170_p.func_72945_a((Entity)eg, eg.field_70121_D).isEmpty() || player.field_70170_p.func_72953_d(eg.field_70121_D)) continue;
            eg.func_70784_b((Entity)player);
            eg.func_70624_b((EntityLivingBase)player);
            player.field_70170_p.func_72838_d((Entity)eg);
            break;
        }
    }

    private static void suddenlySpiders(EntityPlayer player, int warp, boolean real) {
        int spawns = Math.min(50, warp);
        for (int a = 0; a < spawns; ++a) {
            EntityMindSpider spider = new EntityMindSpider(player.field_70170_p);
            int i = MathHelper.func_76128_c((double)player.field_70165_t);
            int j = MathHelper.func_76128_c((double)player.field_70163_u);
            int k = MathHelper.func_76128_c((double)player.field_70161_v);
            boolean success = false;
            for (int l = 0; l < 50; ++l) {
                int k1;
                int j1;
                int i1 = i + MathHelper.func_76136_a((Random)player.field_70170_p.field_73012_v, (int)7, (int)24) * MathHelper.func_76136_a((Random)player.field_70170_p.field_73012_v, (int)-1, (int)1);
                if (!World.func_147466_a((IBlockAccess)player.field_70170_p, (int)i1, (int)((j1 = j + MathHelper.func_76136_a((Random)player.field_70170_p.field_73012_v, (int)7, (int)24) * MathHelper.func_76136_a((Random)player.field_70170_p.field_73012_v, (int)-1, (int)1)) - 1), (int)(k1 = k + MathHelper.func_76136_a((Random)player.field_70170_p.field_73012_v, (int)7, (int)24) * MathHelper.func_76136_a((Random)player.field_70170_p.field_73012_v, (int)-1, (int)1)))) continue;
                spider.func_70107_b(i1, j1, k1);
                if (!player.field_70170_p.func_72855_b(spider.field_70121_D) || !player.field_70170_p.func_72945_a((Entity)spider, spider.field_70121_D).isEmpty() || player.field_70170_p.func_72953_d(spider.field_70121_D)) continue;
                success = true;
                break;
            }
            if (!success) continue;
            spider.func_70784_b((Entity)player);
            spider.func_70624_b((EntityLivingBase)player);
            if (!real) {
                spider.setViewer(player.func_70005_c_());
                spider.setHarmless(true);
            }
            player.field_70170_p.func_72838_d((Entity)spider);
        }
        player.func_145747_a((IChatComponent)new ChatComponentText("\u00a75\u00a7o" + StatCollector.func_74838_a((String)"warp.text.7")));
    }

    public static void checkDeathGaze(EntityPlayer player) {
        PotionEffect pe = player.func_70660_b(Potion.field_76425_a[Config.potionDeathGazeID]);
        if (pe == null) {
            return;
        }
        int level = pe.func_76458_c();
        int range = Math.min(8 + level * 3, 24);
        List list = player.field_70170_p.func_72839_b((Entity)player, player.field_70121_D.func_72314_b((double)range, (double)range, (double)range));
        for (int i = 0; i < list.size(); ++i) {
            Entity entity = (Entity)list.get(i);
            if (!entity.func_70067_L() || !(entity instanceof EntityLivingBase) || !((EntityLivingBase)entity).func_70089_S() || !EntityUtils.isVisibleTo(0.75f, (Entity)player, entity, range) || entity == null || !player.func_70685_l(entity) || entity instanceof EntityPlayer && !MinecraftServer.func_71276_C().func_71219_W() || ((EntityLivingBase)entity).func_82165_m(Potion.field_82731_v.func_76396_c())) continue;
            ((EntityLivingBase)entity).func_70604_c((EntityLivingBase)player);
            ((EntityLivingBase)entity).func_130011_c((Entity)player);
            if (entity instanceof EntityCreature) {
                ((EntityCreature)entity).func_70784_b((Entity)player);
            }
            ((EntityLivingBase)entity).func_70690_d(new PotionEffect(Potion.field_82731_v.func_76396_c(), 80));
        }
    }

    private static int getWarpFromGear(EntityPlayer player) {
        int w = EventHandlerRunic.getFinalWarp(player.func_71045_bC(), player);
        for (int a = 0; a < 4; ++a) {
            w += EventHandlerRunic.getFinalWarp(player.field_71071_by.func_70440_f(a), player);
        }
        IInventory baubles = BaublesApi.getBaubles((EntityPlayer)player);
        for (int a = 0; a < 4; ++a) {
            w += EventHandlerRunic.getFinalWarp(baubles.func_70301_a(a), player);
        }
        return w;
    }
}

