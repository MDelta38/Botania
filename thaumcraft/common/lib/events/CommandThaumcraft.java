/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.command.CommandBase
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 */
package thaumcraft.common.lib.events;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketSyncAspects;
import thaumcraft.common.lib.network.playerdata.PacketSyncResearch;
import thaumcraft.common.lib.network.playerdata.PacketSyncWarp;
import thaumcraft.common.lib.network.playerdata.PacketWarpMessage;
import thaumcraft.common.lib.research.ResearchManager;

public class CommandThaumcraft
extends CommandBase {
    private List aliases = new ArrayList();

    public CommandThaumcraft() {
        this.aliases.add("thaumcraft");
        this.aliases.add("thaum");
        this.aliases.add("tc");
    }

    public String func_71517_b() {
        return "thaumcraft";
    }

    public String func_71518_a(ICommandSender icommandsender) {
        return "/thaumcraft <action> [<player> [<params>]]";
    }

    public List func_71514_a() {
        return this.aliases;
    }

    public int func_82362_a() {
        return 2;
    }

    public List func_71516_a(ICommandSender icommandsender, String[] astring) {
        return null;
    }

    public boolean func_82358_a(String[] astring, int i) {
        return i == 1;
    }

    public void func_71515_b(ICommandSender icommandsender, String[] astring) {
        if (astring.length == 0) {
            icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a7cInvalid arguments", new Object[0]));
            icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a7cUse /thaumcraft help to get help", new Object[0]));
            return;
        }
        if (astring[0].equalsIgnoreCase("help")) {
            icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a73You can also use /thaum or /tc instead of /thaumcraft.", new Object[0]));
            icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a73Use this to give research to a player.", new Object[0]));
            icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("  /thaumcraft research <list|player> <all|reset|<research>>", new Object[0]));
            icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a73Use this to give aspect research points to a player.", new Object[0]));
            icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("  /thaumcraft aspect <player> <aspect|all> <amount>", new Object[0]));
            icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a73Use this to give set a players warp level.", new Object[0]));
            icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("  /thaumcraft warp <player> <add|set> <amount> <PERM|TEMP>", new Object[0]));
            icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("  not specifying perm or temp will just add normal warp", new Object[0]));
        } else if (astring.length >= 2) {
            if (astring[0].equalsIgnoreCase("research") && astring[1].equalsIgnoreCase("list")) {
                this.listResearch(icommandsender);
            } else {
                EntityPlayerMP entityplayermp = CommandThaumcraft.func_82359_c((ICommandSender)icommandsender, (String)astring[1]);
                if (astring[0].equalsIgnoreCase("research")) {
                    if (astring.length == 3) {
                        if (astring[2].equalsIgnoreCase("all")) {
                            this.giveAllResearch(icommandsender, entityplayermp);
                        } else if (astring[2].equalsIgnoreCase("reset")) {
                            this.resetResearch(icommandsender, entityplayermp);
                        } else {
                            this.giveResearch(icommandsender, entityplayermp, astring[2]);
                        }
                    } else {
                        icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a7cInvalid arguments", new Object[0]));
                        icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a7cUse /thaumcraft research <list|player> <all|reset|<research>>", new Object[0]));
                    }
                } else if (astring[0].equalsIgnoreCase("aspect")) {
                    if (astring.length == 4) {
                        int i = CommandThaumcraft.func_71528_a((ICommandSender)icommandsender, (String)astring[3], (int)1);
                        this.giveAspect(icommandsender, entityplayermp, astring[2], i);
                    } else {
                        icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a7cInvalid arguments", new Object[0]));
                        icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a7cUse /thaumcraft aspect <player> <aspect|all> <amount>", new Object[0]));
                    }
                } else if (astring[0].equalsIgnoreCase("warp")) {
                    if (astring.length >= 4 && astring[2].equalsIgnoreCase("set")) {
                        int i = CommandThaumcraft.func_71528_a((ICommandSender)icommandsender, (String)astring[3], (int)0);
                        this.setWarp(icommandsender, entityplayermp, i, astring.length == 5 ? astring[4] : "");
                    } else if (astring.length >= 4 && astring[2].equalsIgnoreCase("add")) {
                        int i = CommandThaumcraft.func_71532_a((ICommandSender)icommandsender, (String)astring[3], (int)-100, (int)100);
                        this.addWarp(icommandsender, entityplayermp, i, astring.length == 5 ? astring[4] : "");
                    } else {
                        icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a7cInvalid arguments", new Object[0]));
                        icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a7cUse /thaumcraft warp <player> <add|set> <amount> <PERM|TEMP>", new Object[0]));
                    }
                } else {
                    icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a7cInvalid arguments", new Object[0]));
                    icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a7cUse /thaumcraft help to get help", new Object[0]));
                }
            }
        } else {
            icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a7cInvalid arguments", new Object[0]));
            icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a7cUse /thaumcraft help to get help", new Object[0]));
        }
    }

    private void giveAspect(ICommandSender icommandsender, EntityPlayerMP player, String string, int i) {
        if (string.equalsIgnoreCase("all")) {
            for (Aspect aspect : Aspect.aspects.values()) {
                Thaumcraft.proxy.playerKnowledge.addAspectPool(player.func_70005_c_(), aspect, (short)i);
            }
            ResearchManager.scheduleSave((EntityPlayer)player);
            player.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a75" + icommandsender.func_70005_c_() + " gave you " + i + " of all the aspects.", new Object[0]));
            icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a75Success!", new Object[0]));
            PacketHandler.INSTANCE.sendTo((IMessage)new PacketSyncAspects((EntityPlayer)player), player);
        } else {
            Aspect aspect = Aspect.getAspect(string);
            if (aspect == null) {
                for (Aspect a : Aspect.aspects.values()) {
                    if (!string.equalsIgnoreCase(a.getName())) continue;
                    aspect = a;
                    break;
                }
            }
            if (aspect != null) {
                Thaumcraft.proxy.playerKnowledge.addAspectPool(player.func_70005_c_(), aspect, (short)i);
                ResearchManager.scheduleSave((EntityPlayer)player);
                PacketHandler.INSTANCE.sendTo((IMessage)new PacketSyncAspects((EntityPlayer)player), player);
                player.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a75" + icommandsender.func_70005_c_() + " gave you " + i + " " + aspect.getName(), new Object[0]));
                icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a75Success!", new Object[0]));
            } else {
                icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a7cAspect does not exist.", new Object[0]));
            }
        }
    }

    private void setWarp(ICommandSender icommandsender, EntityPlayerMP player, int i, String type) {
        if (type.equalsIgnoreCase("PERM")) {
            Thaumcraft.proxy.playerKnowledge.setWarpPerm(player.func_70005_c_(), i);
            ResearchManager.scheduleSave((EntityPlayer)player);
            PacketHandler.INSTANCE.sendTo((IMessage)new PacketSyncWarp((EntityPlayer)player, 0), player);
        } else if (type.equalsIgnoreCase("TEMP")) {
            Thaumcraft.proxy.playerKnowledge.setWarpTemp(player.func_70005_c_(), i);
            ResearchManager.scheduleSave((EntityPlayer)player);
            PacketHandler.INSTANCE.sendTo((IMessage)new PacketSyncWarp((EntityPlayer)player, 2), player);
        } else {
            Thaumcraft.proxy.playerKnowledge.setWarpSticky(player.func_70005_c_(), i);
            ResearchManager.scheduleSave((EntityPlayer)player);
            PacketHandler.INSTANCE.sendTo((IMessage)new PacketSyncWarp((EntityPlayer)player, 1), player);
        }
        player.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a75" + icommandsender.func_70005_c_() + " set your warp to " + i, new Object[0]));
        icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a75Success!", new Object[0]));
    }

    private void addWarp(ICommandSender icommandsender, EntityPlayerMP player, int i, String type) {
        if (type.equalsIgnoreCase("PERM")) {
            Thaumcraft.proxy.playerKnowledge.addWarpPerm(player.func_70005_c_(), i);
            ResearchManager.scheduleSave((EntityPlayer)player);
            PacketHandler.INSTANCE.sendTo((IMessage)new PacketSyncWarp((EntityPlayer)player, 0), player);
            PacketHandler.INSTANCE.sendTo((IMessage)new PacketWarpMessage((EntityPlayer)player, 0, i), player);
        } else if (type.equalsIgnoreCase("TEMP")) {
            Thaumcraft.proxy.playerKnowledge.addWarpTemp(player.func_70005_c_(), i);
            ResearchManager.scheduleSave((EntityPlayer)player);
            PacketHandler.INSTANCE.sendTo((IMessage)new PacketSyncWarp((EntityPlayer)player, 2), player);
            PacketHandler.INSTANCE.sendTo((IMessage)new PacketWarpMessage((EntityPlayer)player, 2, i), player);
        } else {
            Thaumcraft.proxy.playerKnowledge.addWarpSticky(player.func_70005_c_(), i);
            ResearchManager.scheduleSave((EntityPlayer)player);
            PacketHandler.INSTANCE.sendTo((IMessage)new PacketSyncWarp((EntityPlayer)player, 1), player);
            PacketHandler.INSTANCE.sendTo((IMessage)new PacketWarpMessage((EntityPlayer)player, 1, i), player);
        }
        player.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a75" + icommandsender.func_70005_c_() + " added " + i + " warp to your total.", new Object[0]));
        icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a75Success!", new Object[0]));
    }

    private void listResearch(ICommandSender icommandsender) {
        Collection<ResearchCategoryList> rc = ResearchCategories.researchCategories.values();
        for (ResearchCategoryList cat : rc) {
            Collection<ResearchItem> rl = cat.research.values();
            for (ResearchItem ri : rl) {
                icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a75" + ri.key, new Object[0]));
            }
        }
    }

    void giveResearch(ICommandSender icommandsender, EntityPlayerMP player, String research) {
        if (ResearchCategories.getResearch(research) != null) {
            this.giveRecursiveResearch(player, research);
            PacketHandler.INSTANCE.sendTo((IMessage)new PacketSyncResearch((EntityPlayer)player), player);
            player.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a75" + icommandsender.func_70005_c_() + " gave you " + research + " research and its requisites.", new Object[0]));
            icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a75Success!", new Object[0]));
        } else {
            icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a7cResearch does not exist.", new Object[0]));
        }
    }

    void giveRecursiveResearch(EntityPlayerMP player, String research) {
        if (!ResearchManager.isResearchComplete(player.func_70005_c_(), research)) {
            Thaumcraft.proxy.getResearchManager().completeResearch((EntityPlayer)player, research);
            if (ResearchCategories.getResearch((String)research).parents != null) {
                for (String rsi : ResearchCategories.getResearch((String)research).parents) {
                    this.giveRecursiveResearch(player, rsi);
                }
            }
            if (ResearchCategories.getResearch((String)research).parentsHidden != null) {
                for (String rsi : ResearchCategories.getResearch((String)research).parentsHidden) {
                    this.giveRecursiveResearch(player, rsi);
                }
            }
            if (ResearchCategories.getResearch((String)research).siblings != null) {
                for (String rsi : ResearchCategories.getResearch((String)research).siblings) {
                    this.giveRecursiveResearch(player, rsi);
                }
            }
        }
    }

    void giveAllResearch(ICommandSender icommandsender, EntityPlayerMP player) {
        Collection<ResearchCategoryList> rc = ResearchCategories.researchCategories.values();
        for (ResearchCategoryList cat : rc) {
            Collection<ResearchItem> rl = cat.research.values();
            for (ResearchItem ri : rl) {
                if (ResearchManager.isResearchComplete(player.func_70005_c_(), ri.key)) continue;
                Thaumcraft.proxy.getResearchManager().completeResearch((EntityPlayer)player, ri.key);
            }
        }
        player.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a75" + icommandsender.func_70005_c_() + " has given you all research.", new Object[0]));
        icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a75Success!", new Object[0]));
        PacketHandler.INSTANCE.sendTo((IMessage)new PacketSyncResearch((EntityPlayer)player), player);
    }

    void resetResearch(ICommandSender icommandsender, EntityPlayerMP player) {
        Thaumcraft.proxy.getPlayerKnowledge().researchCompleted.remove(player.func_70005_c_());
        Collection<ResearchCategoryList> rc = ResearchCategories.researchCategories.values();
        for (ResearchCategoryList cat : rc) {
            Collection<ResearchItem> res = cat.research.values();
            for (ResearchItem ri : res) {
                if (!ri.isAutoUnlock()) continue;
                Thaumcraft.proxy.getResearchManager().completeResearch((EntityPlayer)player, ri.key);
            }
        }
        player.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a75" + icommandsender.func_70005_c_() + " has reset you research.", new Object[0]));
        icommandsender.func_145747_a((IChatComponent)new ChatComponentTranslation("\u00a75Success!", new Object[0]));
        PacketHandler.INSTANCE.sendTo((IMessage)new PacketSyncResearch((EntityPlayer)player), player);
    }
}

