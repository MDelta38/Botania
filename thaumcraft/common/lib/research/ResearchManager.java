/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Charsets
 *  com.google.common.io.Files
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.common.ObfuscationReflectionHelper
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagInt
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.server.management.ItemInWorldManager
 *  net.minecraft.world.World
 *  net.minecraft.world.storage.IPlayerFileData
 *  net.minecraft.world.storage.SaveHandler
 */
package thaumcraft.common.lib.research;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ItemInWorldManager;
import net.minecraft.world.World;
import net.minecraft.world.storage.IPlayerFileData;
import net.minecraft.world.storage.SaveHandler;
import thaumcraft.api.IScribeTools;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketResearchComplete;
import thaumcraft.common.lib.research.ResearchNoteData;
import thaumcraft.common.lib.utils.HexUtils;
import thaumcraft.common.lib.utils.InventoryUtils;

public class ResearchManager {
    static ArrayList<ResearchItem> allHiddenResearch = null;
    static ArrayList<ResearchItem> allValidResearch = null;
    private static final String RESEARCH_TAG = "THAUMCRAFT.RESEARCH";
    private static final String ASPECT_TAG = "THAUMCRAFT.ASPECTS";
    private static final String SCANNED_OBJ_TAG = "THAUMCRAFT.SCAN.OBJECTS";
    private static final String SCANNED_ENT_TAG = "THAUMCRAFT.SCAN.ENTITIES";
    private static final String SCANNED_PHE_TAG = "THAUMCRAFT.SCAN.PHENOMENA";

    public static boolean createClue(World world, EntityPlayer player, Object clue, AspectList aspects) {
        ArrayList<String> keys = new ArrayList<String>();
        for (ResearchCategoryList rcl : ResearchCategories.researchCategories.values()) {
            block1: for (ResearchItem ri : rcl.research.values()) {
                boolean valid = ri.tags != null && ri.tags.size() > 0 && (ri.isLost() || ri.isHidden()) && !ResearchManager.isResearchComplete(player.func_70005_c_(), ri.key) && !ResearchManager.isResearchComplete(player.func_70005_c_(), "@" + ri.key);
                if (!valid) continue;
                if (clue instanceof ItemStack && ri.getItemTriggers() != null && ri.getItemTriggers().length > 0) {
                    for (ItemStack itemStack : ri.getItemTriggers()) {
                        if (!InventoryUtils.areItemStacksEqual(itemStack, (ItemStack)clue, true, true, false)) continue;
                        keys.add(ri.key);
                        continue block1;
                    }
                } else if (clue instanceof String && ri.getEntityTriggers() != null && ri.getEntityTriggers().length > 0) {
                    for (String string : ri.getEntityTriggers()) {
                        if (!clue.equals(string)) continue;
                        keys.add(ri.key);
                        continue block1;
                    }
                }
                if (aspects == null || aspects.size() <= 0 || ri.getAspectTriggers() == null || ri.getAspectTriggers().length <= 0) continue;
                for (Aspect aspect : ri.getAspectTriggers()) {
                    if (aspects.getAmount(aspect) <= 0) continue;
                    keys.add(ri.key);
                    continue block1;
                }
            }
        }
        if (keys.size() > 0) {
            String key = (String)keys.get(world.field_73012_v.nextInt(keys.size()));
            PacketHandler.INSTANCE.sendTo((IMessage)new PacketResearchComplete("@" + key), (EntityPlayerMP)player);
            Thaumcraft.proxy.getResearchManager().completeResearch(player, "@" + key);
            return true;
        }
        return false;
    }

    public static ItemStack createResearchNoteForPlayer(World world, EntityPlayer player, String key) {
        ItemStack note = null;
        boolean addslot = false;
        int slot = ResearchManager.getResearchSlot(player, key);
        if (slot >= 0) {
            note = player.field_71071_by.func_70301_a(slot);
        } else if (ResearchManager.consumeInkFromPlayer(player, false) && player.field_71071_by.func_146026_a(Items.field_151121_aF)) {
            ResearchManager.consumeInkFromPlayer(player, true);
            note = ResearchManager.createNote(new ItemStack(ConfigItems.itemResearchNotes), key, world);
            if (!player.field_71071_by.func_70441_a(note)) {
                player.func_71019_a(note, false);
            }
            player.field_71069_bz.func_75142_b();
        }
        return note;
    }

    public static String findHiddenResearch(EntityPlayer player) {
        if (allHiddenResearch == null) {
            allHiddenResearch = new ArrayList();
            Collection<ResearchCategoryList> rc = ResearchCategories.researchCategories.values();
            for (ResearchCategoryList cat : rc) {
                Collection<ResearchItem> rl = cat.research.values();
                for (ResearchItem ri : rl) {
                    if (!ri.isHidden() || ri.tags == null || ri.tags.size() <= 0) continue;
                    allHiddenResearch.add(ri);
                }
            }
        }
        ArrayList<String> keys = new ArrayList<String>();
        for (ResearchItem research : allHiddenResearch) {
            if (ResearchManager.isResearchComplete(player.func_70005_c_(), research.key) || !ResearchManager.doesPlayerHaveRequisites(player.func_70005_c_(), research.key) || research.getItemTriggers() == null && research.getEntityTriggers() == null && research.getAspectTriggers() == null) continue;
            keys.add(research.key);
        }
        Random rand = new Random(player.field_70170_p.func_72820_D() / 10L / 5L);
        if (keys.size() > 0) {
            int r = rand.nextInt(keys.size());
            return (String)keys.get(r);
        }
        return "FAIL";
    }

    public static String findMatchingResearch(EntityPlayer player, Aspect aspect) {
        String randomMatch = null;
        if (allValidResearch == null) {
            allValidResearch = new ArrayList();
            Collection<ResearchCategoryList> rc = ResearchCategories.researchCategories.values();
            for (ResearchCategoryList cat : rc) {
                Collection<ResearchItem> rl = cat.research.values();
                for (ResearchItem ri : rl) {
                    boolean secondary = ri.isSecondary() && Config.researchDifficulty == 0 || Config.researchDifficulty == -1;
                    if (secondary || ri.isHidden() || ri.isLost() || ri.isAutoUnlock() || ri.isVirtual() || ri.isStub()) continue;
                    allValidResearch.add(ri);
                }
            }
        }
        ArrayList<String> keys = new ArrayList<String>();
        for (ResearchItem research : allValidResearch) {
            if (ResearchManager.isResearchComplete(player.func_70005_c_(), research.key) || !ResearchManager.doesPlayerHaveRequisites(player.func_70005_c_(), research.key) || research.tags.getAmount(aspect) <= 0) continue;
            keys.add(research.key);
        }
        if (keys.size() > 0) {
            randomMatch = (String)keys.get(player.field_70170_p.field_73012_v.nextInt(keys.size()));
        }
        return randomMatch;
    }

    public static int getResearchSlot(EntityPlayer player, String key) {
        ItemStack[] inv = player.field_71071_by.field_70462_a;
        if (inv == null || inv.length == 0) {
            return -1;
        }
        for (int a = 0; a < inv.length; ++a) {
            if (inv[a] == null || inv[a].func_77973_b() == null || inv[a].func_77973_b() != ConfigItems.itemResearchNotes || ResearchManager.getData(inv[a]) == null || !ResearchManager.getData((ItemStack)inv[a]).key.equals(key)) continue;
            return a;
        }
        return -1;
    }

    public static boolean consumeInkFromPlayer(EntityPlayer player, boolean doit) {
        ItemStack[] inv = player.field_71071_by.field_70462_a;
        for (int a = 0; a < inv.length; ++a) {
            if (inv[a] == null || !(inv[a].func_77973_b() instanceof IScribeTools) || inv[a].func_77960_j() >= inv[a].func_77958_k()) continue;
            if (doit) {
                inv[a].func_77972_a(1, (EntityLivingBase)player);
            }
            return true;
        }
        return false;
    }

    public static boolean consumeInkFromTable(ItemStack stack, boolean doit) {
        if (stack != null && stack.func_77973_b() instanceof IScribeTools && stack.func_77960_j() < stack.func_77958_k()) {
            if (doit) {
                stack.func_77964_b(stack.func_77960_j() + 1);
            }
            return true;
        }
        return false;
    }

    public static boolean checkResearchCompletion(ItemStack contents, ResearchNoteData note, String username) {
        ArrayList<String> checked = new ArrayList<String>();
        ArrayList<String> main = new ArrayList<String>();
        ArrayList<String> remains = new ArrayList<String>();
        for (HexUtils.Hex hex : note.hexes.values()) {
            if (note.hexEntries.get((Object)hex.toString()).type != 1) continue;
            main.add(hex.toString());
        }
        for (HexUtils.Hex hex : note.hexes.values()) {
            if (note.hexEntries.get((Object)hex.toString()).type != 1) continue;
            main.remove(hex.toString());
            ResearchManager.checkConnections(note, hex, checked, main, remains, username);
            break;
        }
        if (main.size() == 0) {
            ArrayList<String> remove = new ArrayList<String>();
            for (HexUtils.Hex hex : note.hexes.values()) {
                if (note.hexEntries.get((Object)hex.toString()).type == 1 || remains.contains(hex.toString())) continue;
                remove.add(hex.toString());
            }
            for (String s : remove) {
                note.hexEntries.remove(s);
                note.hexes.remove(s);
            }
            note.complete = true;
            ResearchManager.updateData(contents, note);
            return true;
        }
        return false;
    }

    private static void checkConnections(ResearchNoteData note, HexUtils.Hex hex, ArrayList<String> checked, ArrayList<String> main, ArrayList<String> remains, String username) {
        checked.add(hex.toString());
        for (int a = 0; a < 6; ++a) {
            HexUtils.Hex target = hex.getNeighbour(a);
            if (checked.contains(target.toString()) || !note.hexEntries.containsKey(target.toString()) || note.hexEntries.get((Object)target.toString()).type < 1) continue;
            Aspect aspect1 = note.hexEntries.get((Object)hex.toString()).aspect;
            Aspect aspect2 = note.hexEntries.get((Object)target.toString()).aspect;
            if (!Thaumcraft.proxy.getPlayerKnowledge().hasDiscoveredAspect(username, aspect1) || !Thaumcraft.proxy.getPlayerKnowledge().hasDiscoveredAspect(username, aspect2) || (aspect1.isPrimal() || aspect1.getComponents()[0] != aspect2 && aspect1.getComponents()[1] != aspect2) && (aspect2.isPrimal() || aspect2.getComponents()[0] != aspect1 && aspect2.getComponents()[1] != aspect1)) continue;
            remains.add(target.toString());
            if (note.hexEntries.get((Object)target.toString()).type == 1) {
                main.remove(target.toString());
            }
            ResearchManager.checkConnections(note, target, checked, main, remains, username);
        }
    }

    public static ItemStack createNote(ItemStack stack, String key, World world) {
        ResearchItem rr = ResearchCategories.getResearch(key);
        Aspect primaryaspect = rr.getResearchPrimaryTag();
        if (primaryaspect == null) {
            return null;
        }
        if (stack.field_77990_d == null) {
            stack.func_77982_d(new NBTTagCompound());
        }
        stack.field_77990_d.func_74778_a("key", key);
        stack.field_77990_d.func_74768_a("color", primaryaspect.getColor());
        stack.field_77990_d.func_74757_a("complete", false);
        stack.field_77990_d.func_74768_a("copies", 0);
        int radius = 1 + Math.min(3, rr.getComplexity());
        HashMap<String, HexUtils.Hex> hexLocs = HexUtils.generateHexes(radius);
        ArrayList<HexUtils.Hex> outerRing = HexUtils.distributeRingRandomly(radius, rr.tags.size(), world.field_73012_v);
        HashMap<String, HexEntry> hexEntries = new HashMap<String, HexEntry>();
        HashMap<String, HexUtils.Hex> hexes = new HashMap<String, HexUtils.Hex>();
        for (HexUtils.Hex hex : hexLocs.values()) {
            hexes.put(hex.toString(), hex);
            hexEntries.put(hex.toString(), new HexEntry(null, 0));
        }
        int count = 0;
        for (HexUtils.Hex hex : outerRing) {
            hexes.put(hex.toString(), hex);
            hexEntries.put(hex.toString(), new HexEntry(rr.tags.getAspects()[count], 1));
            ++count;
        }
        if (rr.getComplexity() > 1) {
            int blanks = rr.getComplexity() * 2;
            HexUtils.Hex[] temp = hexes.values().toArray(new HexUtils.Hex[0]);
            while (blanks > 0) {
                int indx = world.field_73012_v.nextInt(temp.length);
                if (hexEntries.get(temp[indx].toString()) == null || ((HexEntry)hexEntries.get((Object)temp[indx].toString())).type != 0) continue;
                boolean gtg = true;
                for (int n = 0; n < 6; ++n) {
                    HexUtils.Hex neighbour = temp[indx].getNeighbour(n);
                    if (!hexes.containsKey(neighbour.toString()) || ((HexEntry)hexEntries.get((Object)neighbour.toString())).type != 1) continue;
                    int cc = 0;
                    for (int q = 0; q < 6; ++q) {
                        if (hexes.containsKey(((HexUtils.Hex)hexes.get(neighbour.toString())).getNeighbour(q).toString())) {
                            ++cc;
                        }
                        if (cc >= 2) break;
                    }
                    if (cc >= 2) continue;
                    gtg = false;
                    break;
                }
                if (!gtg) continue;
                hexes.remove(temp[indx].toString());
                hexEntries.remove(temp[indx].toString());
                temp = hexes.values().toArray(new HexUtils.Hex[0]);
                --blanks;
            }
        }
        NBTTagList gridtag = new NBTTagList();
        for (HexUtils.Hex hex : hexes.values()) {
            NBTTagCompound gt = new NBTTagCompound();
            gt.func_74774_a("hexq", (byte)hex.q);
            gt.func_74774_a("hexr", (byte)hex.r);
            gt.func_74774_a("type", (byte)((HexEntry)hexEntries.get((Object)hex.toString())).type);
            if (((HexEntry)hexEntries.get((Object)hex.toString())).aspect != null) {
                gt.func_74778_a("aspect", ((HexEntry)hexEntries.get((Object)hex.toString())).aspect.getTag());
            }
            gridtag.func_74742_a((NBTBase)gt);
        }
        stack.field_77990_d.func_74782_a("hexgrid", (NBTBase)gridtag);
        return stack;
    }

    public static ResearchNoteData getData(ItemStack stack) {
        if (stack == null) {
            return null;
        }
        ResearchNoteData data = new ResearchNoteData();
        if (stack.field_77990_d == null) {
            return null;
        }
        data.key = stack.field_77990_d.func_74779_i("key");
        data.color = stack.field_77990_d.func_74762_e("color");
        data.complete = stack.field_77990_d.func_74767_n("complete");
        data.copies = stack.field_77990_d.func_74762_e("copies");
        NBTTagList grid = stack.field_77990_d.func_150295_c("hexgrid", 10);
        data.hexEntries = new HashMap();
        for (int x = 0; x < grid.func_74745_c(); ++x) {
            NBTTagCompound nbt = grid.func_150305_b(x);
            byte q = nbt.func_74771_c("hexq");
            byte r = nbt.func_74771_c("hexr");
            byte type = nbt.func_74771_c("type");
            String tag = nbt.func_74779_i("aspect");
            Aspect aspect = tag != null ? Aspect.getAspect(tag) : null;
            HexUtils.Hex hex = new HexUtils.Hex(q, r);
            data.hexEntries.put(hex.toString(), new HexEntry(aspect, type));
            data.hexes.put(hex.toString(), hex);
        }
        return data;
    }

    public static void updateData(ItemStack stack, ResearchNoteData data) {
        if (stack.field_77990_d == null) {
            stack.func_77982_d(new NBTTagCompound());
        }
        stack.field_77990_d.func_74778_a("key", data.key);
        stack.field_77990_d.func_74768_a("color", data.color);
        stack.field_77990_d.func_74757_a("complete", data.complete);
        stack.field_77990_d.func_74768_a("copies", data.copies);
        NBTTagList gridtag = new NBTTagList();
        for (HexUtils.Hex hex : data.hexes.values()) {
            NBTTagCompound gt = new NBTTagCompound();
            gt.func_74774_a("hexq", (byte)hex.q);
            gt.func_74774_a("hexr", (byte)hex.r);
            gt.func_74774_a("type", (byte)data.hexEntries.get((Object)hex.toString()).type);
            if (data.hexEntries.get((Object)hex.toString()).aspect != null) {
                gt.func_74778_a("aspect", data.hexEntries.get((Object)hex.toString()).aspect.getTag());
            }
            gridtag.func_74742_a((NBTBase)gt);
        }
        stack.field_77990_d.func_74782_a("hexgrid", (NBTBase)gridtag);
    }

    public static boolean isResearchComplete(String playername, String key) {
        if (!key.startsWith("@") && ResearchCategories.getResearch(key) == null) {
            return false;
        }
        ArrayList<String> completed = ResearchManager.getResearchForPlayer(playername);
        if (completed != null && completed.size() > 0) {
            return completed.contains(key);
        }
        return false;
    }

    public static ArrayList<String> getResearchForPlayer(String playername) {
        ArrayList<String> out = Thaumcraft.proxy.getCompletedResearch().get(playername);
        try {
            if (out == null && Thaumcraft.proxy.getClientWorld() == null && MinecraftServer.func_71276_C() != null) {
                Thaumcraft.proxy.getCompletedResearch().put(playername, new ArrayList());
                UUID id = UUID.nameUUIDFromBytes(("OfflinePlayer:" + playername).getBytes(Charsets.UTF_8));
                EntityPlayerMP entityplayermp = new EntityPlayerMP(MinecraftServer.func_71276_C(), MinecraftServer.func_71276_C().func_71218_a(0), new GameProfile(id, playername), new ItemInWorldManager((World)MinecraftServer.func_71276_C().func_71218_a(0)));
                if (entityplayermp != null) {
                    IPlayerFileData playerNBTManagerObj = MinecraftServer.func_71276_C().func_71218_a(0).func_72860_G().func_75756_e();
                    SaveHandler sh = (SaveHandler)playerNBTManagerObj;
                    File dir = (File)ObfuscationReflectionHelper.getPrivateValue(SaveHandler.class, (Object)sh, (String[])new String[]{"playersDirectory", "field_75771_c"});
                    File file1 = new File(dir, id + ".thaum");
                    File file2 = new File(dir, id + ".thaumbak");
                    ResearchManager.loadPlayerData((EntityPlayer)entityplayermp, file1, file2, false);
                }
                out = Thaumcraft.proxy.getCompletedResearch().get(playername);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return out;
    }

    public static ArrayList<String> getResearchForPlayerSafe(String playername) {
        return Thaumcraft.proxy.getCompletedResearch().get(playername);
    }

    public static boolean doesPlayerHaveRequisites(String playername, String key) {
        ArrayList<String> completed;
        boolean out = true;
        String[] parents = ResearchCategories.getResearch((String)key).parents;
        if (parents != null && parents.length > 0) {
            out = false;
            completed = ResearchManager.getResearchForPlayer(playername);
            if (completed != null && completed.size() > 0) {
                out = true;
                for (String item : parents) {
                    if (completed.contains(item)) continue;
                    return false;
                }
            }
        }
        if ((parents = ResearchCategories.getResearch((String)key).parentsHidden) != null && parents.length > 0) {
            out = false;
            completed = ResearchManager.getResearchForPlayer(playername);
            if (completed != null && completed.size() > 0) {
                out = true;
                for (String item : parents) {
                    if (completed.contains(item)) continue;
                    return false;
                }
            }
        }
        return out;
    }

    public static Aspect getCombinationResult(Aspect aspect1, Aspect aspect2) {
        Collection<Aspect> aspects = Aspect.aspects.values();
        for (Aspect aspect : aspects) {
            if (aspect.getComponents() == null || (aspect.getComponents()[0] != aspect1 || aspect.getComponents()[1] != aspect2) && (aspect.getComponents()[0] != aspect2 || aspect.getComponents()[1] != aspect1)) continue;
            return aspect;
        }
        return null;
    }

    public static AspectList reduceToPrimals(AspectList al) {
        return ResearchManager.reduceToPrimals(al, false);
    }

    public static AspectList reduceToPrimals(AspectList al, boolean merge) {
        AspectList out = new AspectList();
        for (Aspect aspect : al.getAspects()) {
            if (aspect == null) continue;
            if (aspect.isPrimal()) {
                if (merge) {
                    out.merge(aspect, al.getAmount(aspect));
                    continue;
                }
                out.add(aspect, al.getAmount(aspect));
                continue;
            }
            AspectList send = new AspectList();
            send.add(aspect.getComponents()[0], al.getAmount(aspect));
            send.add(aspect.getComponents()[1], al.getAmount(aspect));
            send = ResearchManager.reduceToPrimals(send, merge);
            for (Aspect a : send.getAspects()) {
                if (merge) {
                    out.merge(a, send.getAmount(a));
                    continue;
                }
                out.add(a, send.getAmount(a));
            }
        }
        return out;
    }

    public static boolean completeResearchUnsaved(String username, String key) {
        ArrayList<String> completed = ResearchManager.getResearchForPlayerSafe(username);
        if (completed == null || !completed.contains(key)) {
            if (completed == null) {
                completed = new ArrayList();
            }
            completed.add(key);
            Thaumcraft.proxy.getCompletedResearch().put(username, completed);
            return true;
        }
        return false;
    }

    public void completeResearch(EntityPlayer player, String key) {
        if (ResearchManager.completeResearchUnsaved(player.func_70005_c_(), key)) {
            int warp = ThaumcraftApi.getWarp(key);
            if (warp > 0 && !Config.wuss && !player.field_70170_p.field_72995_K) {
                if (warp > 1) {
                    int w2 = warp / 2;
                    if (warp - w2 > 0) {
                        Thaumcraft.addWarpToPlayer(player, warp - w2, false);
                    }
                    if (w2 > 0) {
                        Thaumcraft.addStickyWarpToPlayer(player, w2);
                    }
                } else {
                    Thaumcraft.addWarpToPlayer(player, warp, false);
                }
            }
            ResearchManager.scheduleSave(player);
        }
    }

    public static boolean completeAspectUnsaved(String username, Aspect aspect, short amount) {
        if (aspect == null) {
            return false;
        }
        Thaumcraft.proxy.getPlayerKnowledge().addDiscoveredAspect(username, aspect);
        Thaumcraft.proxy.getPlayerKnowledge().setAspectPool(username, aspect, amount);
        return true;
    }

    public void completeAspect(EntityPlayer player, Aspect aspect, short amount) {
        if (ResearchManager.completeAspectUnsaved(player.func_70005_c_(), aspect, amount)) {
            ResearchManager.scheduleSave(player);
        }
    }

    public static boolean completeScannedObjectUnsaved(String username, String object) {
        ArrayList<String> completed = Thaumcraft.proxy.getScannedObjects().get(username);
        if (completed == null) {
            completed = new ArrayList();
        }
        if (!completed.contains(object)) {
            completed.add(object);
            String t = object.replaceFirst("#", "@");
            if (!object.startsWith("#") || !completed.contains(t) || completed.remove(t)) {
                // empty if block
            }
            Thaumcraft.proxy.getScannedObjects().put(username, completed);
        }
        return true;
    }

    public static boolean completeScannedEntityUnsaved(String username, String key) {
        ArrayList<String> completed = Thaumcraft.proxy.getScannedEntities().get(username);
        if (completed == null) {
            completed = new ArrayList();
        }
        if (!completed.contains(key)) {
            completed.add(key);
            String t = key.replaceFirst("#", "@");
            if (!key.startsWith("#") || !completed.contains(t) || completed.remove(t)) {
                // empty if block
            }
            Thaumcraft.proxy.getScannedEntities().put(username, completed);
        }
        return true;
    }

    public static boolean completeScannedPhenomenaUnsaved(String username, String key) {
        ArrayList<String> completed = Thaumcraft.proxy.getScannedPhenomena().get(username);
        if (completed == null) {
            completed = new ArrayList();
        }
        if (!completed.contains(key)) {
            completed.add(key);
            String t = key.replaceFirst("#", "@");
            if (!key.startsWith("#") || !completed.contains(t) || completed.remove(t)) {
                // empty if block
            }
            Thaumcraft.proxy.getScannedPhenomena().put(username, completed);
        }
        return true;
    }

    public void completeScannedObject(EntityPlayer player, String object) {
        if (ResearchManager.completeScannedObjectUnsaved(player.func_70005_c_(), object)) {
            ResearchManager.scheduleSave(player);
        }
    }

    public void completeScannedEntity(EntityPlayer player, String key) {
        if (ResearchManager.completeScannedEntityUnsaved(player.func_70005_c_(), key)) {
            ResearchManager.scheduleSave(player);
        }
    }

    public void completeScannedPhenomena(EntityPlayer player, String key) {
        if (ResearchManager.completeScannedPhenomenaUnsaved(player.func_70005_c_(), key)) {
            ResearchManager.scheduleSave(player);
        }
    }

    public static void loadPlayerData(EntityPlayer player, File file1, File file2, boolean legacy) {
        try {
            FileInputStream fileinputstream;
            NBTTagCompound data = null;
            if (file1 != null && file1.exists()) {
                try {
                    fileinputstream = new FileInputStream(file1);
                    data = CompressedStreamTools.func_74796_a((InputStream)fileinputstream);
                    fileinputstream.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (file1 == null || !file1.exists() || data == null || data.func_82582_d()) {
                Thaumcraft.log.warn("Thaumcraft data not found for " + player.func_70005_c_() + ". Trying to load backup Thaumcraft data.");
                if (file2 != null && file2.exists()) {
                    try {
                        fileinputstream = new FileInputStream(file2);
                        data = CompressedStreamTools.func_74796_a((InputStream)fileinputstream);
                        fileinputstream.close();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (data != null) {
                ResearchManager.loadResearchNBT(data, player);
                ResearchManager.loadAspectNBT(data, player);
                ResearchManager.loadScannedNBT(data, player);
                if (data.func_74764_b("Thaumcraft.shielding")) {
                    Thaumcraft.instance.runicEventHandler.runicCharge.put(player.func_145782_y(), data.func_74762_e("Thaumcraft.shielding"));
                    Thaumcraft.instance.runicEventHandler.isDirty = true;
                }
                if (data.func_74764_b("Thaumcraft.eldritch")) {
                    int warp = data.func_74762_e("Thaumcraft.eldritch");
                    if (legacy && !data.func_74764_b("Thaumcraft.eldritch.sticky")) {
                        Thaumcraft.proxy.getPlayerKnowledge().setWarpSticky(player.func_70005_c_(), warp /= 2);
                    }
                    Thaumcraft.proxy.getPlayerKnowledge().setWarpPerm(player.func_70005_c_(), warp);
                }
                if (data.func_74764_b("Thaumcraft.eldritch.temp")) {
                    Thaumcraft.proxy.getPlayerKnowledge().setWarpTemp(player.func_70005_c_(), data.func_74762_e("Thaumcraft.eldritch.temp"));
                }
                if (data.func_74764_b("Thaumcraft.eldritch.sticky")) {
                    Thaumcraft.proxy.getPlayerKnowledge().setWarpSticky(player.func_70005_c_(), data.func_74762_e("Thaumcraft.eldritch.sticky"));
                }
                if (data.func_74764_b("Thaumcraft.eldritch.counter")) {
                    Thaumcraft.proxy.getPlayerKnowledge().setWarpCounter(player.func_70005_c_(), data.func_74762_e("Thaumcraft.eldritch.counter"));
                } else {
                    Thaumcraft.proxy.getPlayerKnowledge().setWarpCounter(player.func_70005_c_(), 0);
                }
            } else {
                Collection<Aspect> aspects = Aspect.aspects.values();
                for (Aspect aspect : aspects) {
                    if (aspect.getComponents() != null) continue;
                    Thaumcraft.proxy.getResearchManager();
                    ResearchManager.completeAspectUnsaved(player.func_70005_c_(), aspect, (short)(15 + player.field_70170_p.field_73012_v.nextInt(5)));
                }
                ResearchManager.scheduleSave(player);
                Thaumcraft.log.info("Assigning initial aspects to " + player.func_70005_c_());
            }
        }
        catch (Exception exception1) {
            exception1.printStackTrace();
            Thaumcraft.log.fatal("Error loading Thaumcraft data");
        }
    }

    public static void loadResearchNBT(NBTTagCompound entityData, EntityPlayer player) {
        NBTTagList tagList = entityData.func_150295_c(RESEARCH_TAG, 10);
        for (int j = 0; j < tagList.func_74745_c(); ++j) {
            NBTTagCompound rs = tagList.func_150305_b(j);
            if (!rs.func_74764_b("key")) continue;
            ResearchManager.completeResearchUnsaved(player.func_70005_c_(), rs.func_74779_i("key"));
        }
    }

    public static void loadAspectNBT(NBTTagCompound entityData, EntityPlayer player) {
        if (entityData.func_74764_b(ASPECT_TAG)) {
            NBTTagList tagList = entityData.func_150295_c(ASPECT_TAG, 10);
            for (int j = 0; j < tagList.func_74745_c(); ++j) {
                NBTTagCompound rs = tagList.func_150305_b(j);
                if (!rs.func_74764_b("key")) continue;
                Aspect aspect = Aspect.getAspect(rs.func_74779_i("key"));
                short amount = rs.func_74765_d("amount");
                if (aspect == null) continue;
                ResearchManager.completeAspectUnsaved(player.func_70005_c_(), aspect, amount);
            }
        }
    }

    public static void loadScannedNBT(NBTTagCompound entityData, EntityPlayer player) {
        NBTTagCompound rs;
        int j;
        NBTTagList tagList = entityData.func_150295_c(SCANNED_OBJ_TAG, 10);
        for (j = 0; j < tagList.func_74745_c(); ++j) {
            rs = tagList.func_150305_b(j);
            if (!rs.func_74764_b("key")) continue;
            ResearchManager.completeScannedObjectUnsaved(player.func_70005_c_(), rs.func_74779_i("key"));
        }
        tagList = entityData.func_150295_c(SCANNED_ENT_TAG, 10);
        for (j = 0; j < tagList.func_74745_c(); ++j) {
            rs = tagList.func_150305_b(j);
            if (!rs.func_74764_b("key")) continue;
            ResearchManager.completeScannedEntityUnsaved(player.func_70005_c_(), rs.func_74779_i("key"));
        }
        tagList = entityData.func_150295_c(SCANNED_PHE_TAG, 10);
        for (j = 0; j < tagList.func_74745_c(); ++j) {
            rs = tagList.func_150305_b(j);
            if (!rs.func_74764_b("key")) continue;
            ResearchManager.completeScannedPhenomenaUnsaved(player.func_70005_c_(), rs.func_74779_i("key"));
        }
    }

    public static void scheduleSave(EntityPlayer player) {
        if (player.field_70170_p.field_72995_K) {
            return;
        }
    }

    public static boolean savePlayerData(EntityPlayer player, File file1, File file2) {
        boolean success = true;
        try {
            NBTTagCompound data = new NBTTagCompound();
            ResearchManager.saveResearchNBT(data, player);
            ResearchManager.saveAspectNBT(data, player);
            ResearchManager.saveScannedNBT(data, player);
            if (Thaumcraft.instance.runicEventHandler.runicCharge.containsKey(player.func_145782_y())) {
                data.func_74782_a("Thaumcraft.shielding", (NBTBase)new NBTTagInt(Thaumcraft.instance.runicEventHandler.runicCharge.get(player.func_145782_y()).intValue()));
            }
            data.func_74782_a("Thaumcraft.eldritch", (NBTBase)new NBTTagInt(Thaumcraft.proxy.getPlayerKnowledge().getWarpPerm(player.func_70005_c_())));
            data.func_74782_a("Thaumcraft.eldritch.temp", (NBTBase)new NBTTagInt(Thaumcraft.proxy.getPlayerKnowledge().getWarpTemp(player.func_70005_c_())));
            data.func_74782_a("Thaumcraft.eldritch.sticky", (NBTBase)new NBTTagInt(Thaumcraft.proxy.getPlayerKnowledge().getWarpSticky(player.func_70005_c_())));
            data.func_74782_a("Thaumcraft.eldritch.counter", (NBTBase)new NBTTagInt(Thaumcraft.proxy.getPlayerKnowledge().getWarpCounter(player.func_70005_c_())));
            if (file1 != null && file1.exists()) {
                try {
                    Files.copy((File)file1, (File)file2);
                }
                catch (Exception e) {
                    Thaumcraft.log.error("Could not backup old research file for player " + player.func_70005_c_());
                }
            }
            try {
                if (file1 != null) {
                    FileOutputStream fileoutputstream = new FileOutputStream(file1);
                    CompressedStreamTools.func_74799_a((NBTTagCompound)data, (OutputStream)fileoutputstream);
                    fileoutputstream.close();
                }
            }
            catch (Exception e) {
                Thaumcraft.log.error("Could not save research file for player " + player.func_70005_c_());
                if (file1.exists()) {
                    try {
                        file1.delete();
                    }
                    catch (Exception e2) {
                        // empty catch block
                    }
                }
                success = false;
            }
        }
        catch (Exception exception1) {
            exception1.printStackTrace();
            Thaumcraft.log.fatal("Error saving Thaumcraft data");
            success = false;
        }
        return success;
    }

    public static void saveResearchNBT(NBTTagCompound entityData, EntityPlayer player) {
        NBTTagList tagList = new NBTTagList();
        ArrayList<String> res = ResearchManager.getResearchForPlayer(player.func_70005_c_());
        if (res != null && res.size() > 0) {
            for (Object e : res) {
                if ((String)e == null || !((String)e).startsWith("@") && ResearchCategories.getResearch((String)e) == null) continue;
                if (((String)e).startsWith("@")) {
                    String k = ((String)e).substring(1);
                    if (ResearchManager.isResearchComplete(player.func_70005_c_(), k)) continue;
                }
                if (ResearchCategories.getResearch((String)e) != null && ResearchCategories.getResearch((String)e).isAutoUnlock()) continue;
                NBTTagCompound f = new NBTTagCompound();
                f.func_74778_a("key", (String)e);
                tagList.func_74742_a((NBTBase)f);
            }
        }
        entityData.func_74782_a(RESEARCH_TAG, (NBTBase)tagList);
    }

    public static void saveAspectNBT(NBTTagCompound entityData, EntityPlayer player) {
        NBTTagList tagList = new NBTTagList();
        AspectList res = Thaumcraft.proxy.getKnownAspects().get(player.func_70005_c_());
        if (res != null && res.size() > 0) {
            for (Aspect aspect : res.getAspects()) {
                if (aspect == null) continue;
                NBTTagCompound f = new NBTTagCompound();
                f.func_74778_a("key", aspect.getTag());
                f.func_74777_a("amount", (short)res.getAmount(aspect));
                tagList.func_74742_a((NBTBase)f);
            }
        }
        entityData.func_74782_a(ASPECT_TAG, (NBTBase)tagList);
    }

    public static void saveScannedNBT(NBTTagCompound entityData, EntityPlayer player) {
        NBTTagList tagList = new NBTTagList();
        List obj = Thaumcraft.proxy.getScannedObjects().get(player.func_70005_c_());
        if (obj != null && obj.size() > 0) {
            for (String object : obj) {
                if (object == null) continue;
                NBTTagCompound f = new NBTTagCompound();
                f.func_74778_a("key", object);
                tagList.func_74742_a((NBTBase)f);
            }
        }
        entityData.func_74782_a(SCANNED_OBJ_TAG, (NBTBase)tagList);
        tagList = new NBTTagList();
        List ent = Thaumcraft.proxy.getScannedEntities().get(player.func_70005_c_());
        if (ent != null && ent.size() > 0) {
            for (String key : ent) {
                if (key == null) continue;
                NBTTagCompound f = new NBTTagCompound();
                f.func_74778_a("key", key);
                tagList.func_74742_a((NBTBase)f);
            }
        }
        entityData.func_74782_a(SCANNED_ENT_TAG, (NBTBase)tagList);
        tagList = new NBTTagList();
        List phe = Thaumcraft.proxy.getScannedPhenomena().get(player.func_70005_c_());
        if (phe != null && phe.size() > 0) {
            for (String key : phe) {
                if (key == null) continue;
                NBTTagCompound f = new NBTTagCompound();
                f.func_74778_a("key", key);
                tagList.func_74742_a((NBTBase)f);
            }
        }
        entityData.func_74782_a(SCANNED_PHE_TAG, (NBTBase)tagList);
    }

    public static class HexEntry {
        public Aspect aspect;
        public int type;

        public HexEntry(Aspect aspect, int type) {
            this.aspect = aspect;
            this.type = type;
        }
    }
}

