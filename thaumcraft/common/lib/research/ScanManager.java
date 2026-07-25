/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.common.registry.GameRegistry$UniqueIdentifier
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityCreeper
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package thaumcraft.common.lib.research;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.research.IScanEventHandler;
import thaumcraft.api.research.ScanResult;
import thaumcraft.client.lib.PlayerNotifications;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketAspectDiscovery;
import thaumcraft.common.lib.network.playerdata.PacketAspectPool;
import thaumcraft.common.lib.research.PlayerKnowledge;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.tiles.TileNode;

public class ScanManager
implements IScanEventHandler {
    @Override
    public ScanResult scanPhenomena(ItemStack stack, World world, EntityPlayer player) {
        return null;
    }

    private static int generateEntityHash(Entity entity) {
        EntityLivingBase le;
        String hash = EntityList.func_75621_b((Entity)entity);
        if (hash == null) {
            hash = "generic";
        }
        if (entity instanceof EntityPlayer) {
            hash = "player_" + ((EntityPlayer)entity).func_70005_c_();
        }
        block4: for (ThaumcraftApi.EntityTags et : ThaumcraftApi.scanEntities) {
            Class<?> c;
            Object val;
            if (!et.entityName.equals(hash) || et.nbts == null || et.nbts.length == 0) continue;
            NBTTagCompound tc = new NBTTagCompound();
            entity.func_70109_d(tc);
            for (ThaumcraftApi.EntityTagsNBT nbt : et.nbts) {
                if (!tc.func_74764_b(nbt.name)) continue block4;
                val = Utils.getNBTDataFromId(tc, tc.func_150299_b(nbt.name), nbt.name);
                c = val.getClass();
                try {
                    if (c.cast(val).equals(c.cast(nbt.value))) continue;
                }
                catch (Exception e) {}
                continue block4;
            }
            for (ThaumcraftApi.EntityTagsNBT nbt : et.nbts) {
                val = Utils.getNBTDataFromId(tc, tc.func_150299_b(nbt.name), nbt.name);
                c = val.getClass();
                try {
                    hash = hash + nbt.name + c.cast(nbt.value);
                }
                catch (Exception e) {
                    // empty catch block
                }
            }
        }
        if (entity instanceof EntityLivingBase && (le = (EntityLivingBase)entity).func_70631_g_()) {
            hash = hash + "CHILD";
        }
        if (entity instanceof EntityZombie && ((EntityZombie)entity).func_82231_m()) {
            hash = hash + "VILLAGER";
        }
        if (entity instanceof EntityCreeper) {
            if (((EntityCreeper)entity).func_70832_p() == 1) {
                hash = hash + "FLASHING";
            }
            if (((EntityCreeper)entity).func_70830_n()) {
                hash = hash + "POWERED";
            }
        }
        if (entity instanceof EntityGolemBase) {
            hash = hash + "" + ((EntityGolemBase)entity).getGolemType().name();
        }
        return hash.hashCode();
    }

    public static int generateItemHash(Item item, int meta) {
        String hash;
        ItemStack t = new ItemStack(item, 1, meta);
        try {
            if (t.func_77984_f() || !t.func_77981_g()) {
                meta = -1;
            }
        }
        catch (Exception e) {
            // empty catch block
        }
        if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(item, meta))) {
            meta = ThaumcraftApi.groupedObjectTags.get(Arrays.asList(item, meta))[0];
        }
        try {
            GameRegistry.UniqueIdentifier ui = GameRegistry.findUniqueIdentifierFor((Item)item);
            hash = ui != null ? ui.toString() + ":" + meta : t.func_77977_a() + ":" + meta;
        }
        catch (Exception e) {
            hash = "oops:" + meta;
        }
        if (!ThaumcraftApi.objectTags.containsKey(Arrays.asList(item, meta))) {
            Set col = ThaumcraftApi.objectTags.keySet();
            for (List l : col) {
                String name = ((Item)l.get(0)).func_77658_a();
                if (Item.field_150901_e.func_82594_a(name) != item && Block.field_149771_c.func_82594_a(name) != Block.func_149634_a((Item)item) || !(l.get(1) instanceof int[])) continue;
                int[] range = (int[])l.get(1);
                Arrays.sort(range);
                if (Arrays.binarySearch(range, meta) < 0) continue;
                GameRegistry.UniqueIdentifier ui = GameRegistry.findUniqueIdentifierFor((Item)item);
                hash = ui != null ? ui.toString() : "" + t.func_77977_a();
                for (int r : range) {
                    hash = hash + ":" + r;
                }
                return hash.hashCode();
            }
            if (!ThaumcraftApi.objectTags.containsKey(Arrays.asList(item, -1)) && meta == -1) {
                int index = 0;
                boolean found = false;
                do {
                    found = ThaumcraftApi.objectTags.containsKey(Arrays.asList(item, index));
                } while (++index < 16 && !found);
                if (found) {
                    GameRegistry.UniqueIdentifier ui = GameRegistry.findUniqueIdentifierFor((Item)item);
                    hash = ui != null ? ui.toString() + ":" + index : t.func_77977_a() + ":" + index;
                }
            }
        }
        return hash.hashCode();
    }

    public static AspectList generateEntityAspects(Entity entity) {
        AspectList tags = null;
        String s = null;
        try {
            s = EntityList.func_75621_b((Entity)entity);
        }
        catch (Throwable e) {
            try {
                s = entity.func_70005_c_();
            }
            catch (Throwable e2) {
                // empty catch block
            }
        }
        if (s == null) {
            s = "generic";
        }
        if (entity instanceof EntityPlayer) {
            s = "player_" + ((EntityPlayer)entity).func_70005_c_();
            tags = new AspectList();
            tags.add(Aspect.MAN, 4);
            if (((EntityPlayer)entity).func_70005_c_().equalsIgnoreCase("azanor")) {
                tags.add(Aspect.ELDRITCH, 20);
            } else if (((EntityPlayer)entity).func_70005_c_().equalsIgnoreCase("direwolf20")) {
                tags.add(Aspect.BEAST, 20);
            } else if (((EntityPlayer)entity).func_70005_c_().equalsIgnoreCase("pahimar")) {
                tags.add(Aspect.EXCHANGE, 20);
            } else {
                Random rand = new Random(s.hashCode());
                Aspect[] posa = Aspect.aspects.values().toArray(new Aspect[0]);
                tags.add(posa[rand.nextInt(posa.length)], 4);
                tags.add(posa[rand.nextInt(posa.length)], 4);
                tags.add(posa[rand.nextInt(posa.length)], 4);
            }
        } else {
            block4: for (ThaumcraftApi.EntityTags et : ThaumcraftApi.scanEntities) {
                if (!et.entityName.equals(s)) continue;
                if (et.nbts == null || et.nbts.length == 0) {
                    tags = et.aspects;
                    continue;
                }
                NBTTagCompound tc = new NBTTagCompound();
                entity.func_70109_d(tc);
                for (ThaumcraftApi.EntityTagsNBT nbt : et.nbts) {
                    if (!tc.func_74764_b(nbt.name) || !Utils.getNBTDataFromId(tc, tc.func_150299_b(nbt.name), nbt.name).equals(nbt.value)) continue block4;
                }
                tags = et.aspects;
            }
        }
        return tags;
    }

    private static AspectList generateNodeAspects(World world, String node) {
        AspectList tags = new AspectList();
        ArrayList<Integer> loc = TileNode.locations.get(node);
        if (loc != null && loc.size() > 0) {
            TileEntity tnb;
            int dim = loc.get(0);
            int x = loc.get(1);
            int y = loc.get(2);
            int z = loc.get(3);
            if (dim == world.field_73011_w.field_76574_g && (tnb = world.func_147438_o(x, y, z)) != null && tnb instanceof INode) {
                AspectList ta = ((INode)tnb).getAspects();
                for (Aspect a : ta.getAspectsSorted()) {
                    tags.merge(a, Math.max(4, ta.getAmount(a) / 10));
                }
                switch (((INode)tnb).getNodeType()) {
                    case UNSTABLE: {
                        tags.merge(Aspect.ENTROPY, 4);
                        break;
                    }
                    case HUNGRY: {
                        tags.merge(Aspect.HUNGER, 4);
                        break;
                    }
                    case TAINTED: {
                        tags.merge(Aspect.TAINT, 4);
                        break;
                    }
                    case PURE: {
                        tags.merge(Aspect.HEAL, 2);
                        tags.add(Aspect.ORDER, 2);
                        break;
                    }
                    case DARK: {
                        tags.merge(Aspect.DEATH, 2);
                        tags.add(Aspect.DARKNESS, 2);
                    }
                }
            }
        }
        return tags.size() > 0 ? tags : null;
    }

    public static boolean isValidScanTarget(EntityPlayer player, ScanResult scan, String prefix) {
        List list;
        if (scan == null) {
            return false;
        }
        if (prefix.equals("@") && !ScanManager.isValidScanTarget(player, scan, "#")) {
            return false;
        }
        if (scan.type == 1) {
            List list2;
            if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(Item.func_150899_d((int)scan.id), scan.meta))) {
                scan.meta = ThaumcraftApi.groupedObjectTags.get(Arrays.asList(Item.func_150899_d((int)scan.id), scan.meta))[0];
            }
            if ((list2 = (List)Thaumcraft.proxy.getScannedObjects().get(player.func_70005_c_())) != null && list2.contains(prefix + ScanManager.generateItemHash(Item.func_150899_d((int)scan.id), scan.meta))) {
                return false;
            }
        } else if (scan.type == 2) {
            if (scan.entity instanceof EntityItem) {
                List list3;
                EntityItem item = (EntityItem)scan.entity;
                ItemStack t = item.func_92059_d().func_77946_l();
                t.field_77994_a = 1;
                if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(t.func_77973_b(), t.func_77960_j()))) {
                    t.func_77964_b(ThaumcraftApi.groupedObjectTags.get(Arrays.asList(t.func_77973_b(), t.func_77960_j()))[0]);
                }
                if ((list3 = (List)Thaumcraft.proxy.getScannedObjects().get(player.func_70005_c_())) != null && list3.contains(prefix + ScanManager.generateItemHash(t.func_77973_b(), t.func_77960_j()))) {
                    return false;
                }
            } else {
                List list4 = Thaumcraft.proxy.getScannedEntities().get(player.func_70005_c_());
                if (list4 != null && list4.contains(prefix + ScanManager.generateEntityHash(scan.entity))) {
                    return false;
                }
            }
        } else if (scan.type == 3 && (list = (List)Thaumcraft.proxy.getScannedPhenomena().get(player.func_70005_c_())) != null && list.contains(prefix + scan.phenomena)) {
            return false;
        }
        return true;
    }

    public static boolean hasBeenScanned(EntityPlayer player, ScanResult scan) {
        List list;
        if (scan.type == 1) {
            List list2;
            if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(Item.func_150899_d((int)scan.id), scan.meta))) {
                scan.meta = ThaumcraftApi.groupedObjectTags.get(Arrays.asList(Item.func_150899_d((int)scan.id), scan.meta))[0];
            }
            if ((list2 = (List)Thaumcraft.proxy.getScannedObjects().get(player.func_70005_c_())) != null && (list2.contains("@" + ScanManager.generateItemHash(Item.func_150899_d((int)scan.id), scan.meta)) || list2.contains("#" + ScanManager.generateItemHash(Item.func_150899_d((int)scan.id), scan.meta)))) {
                return true;
            }
        } else if (scan.type == 2) {
            if (scan.entity instanceof EntityItem) {
                List list3;
                EntityItem item = (EntityItem)scan.entity;
                ItemStack t = item.func_92059_d().func_77946_l();
                t.field_77994_a = 1;
                if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(t.func_77973_b(), t.func_77960_j()))) {
                    t.func_77964_b(ThaumcraftApi.groupedObjectTags.get(Arrays.asList(t.func_77973_b(), t.func_77960_j()))[0]);
                }
                if ((list3 = (List)Thaumcraft.proxy.getScannedObjects().get(player.func_70005_c_())) != null && (list3.contains("@" + ScanManager.generateItemHash(t.func_77973_b(), t.func_77960_j())) || list3.contains("#" + ScanManager.generateItemHash(t.func_77973_b(), t.func_77960_j())))) {
                    return true;
                }
            } else {
                List list4 = Thaumcraft.proxy.getScannedEntities().get(player.func_70005_c_());
                if (list4 != null && (list4.contains("@" + ScanManager.generateEntityHash(scan.entity)) || list4.contains("#" + ScanManager.generateEntityHash(scan.entity)))) {
                    return true;
                }
            }
        } else if (scan.type == 3 && (list = (List)Thaumcraft.proxy.getScannedPhenomena().get(player.func_70005_c_())) != null && (list.contains("@" + scan.phenomena) || list.contains("#" + scan.phenomena))) {
            return true;
        }
        return false;
    }

    public static boolean completeScan(EntityPlayer player, ScanResult scan, String prefix) {
        AspectList aspects = null;
        PlayerKnowledge rp = Thaumcraft.proxy.getPlayerKnowledge();
        boolean ret = false;
        boolean scannedByThaumometer = prefix.equals("#") && !ScanManager.isValidScanTarget(player, scan, "@");
        String clue = null;
        if (scan.type == 1) {
            if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(Item.func_150899_d((int)scan.id), scan.meta))) {
                scan.meta = ThaumcraftApi.groupedObjectTags.get(Arrays.asList(Item.func_150899_d((int)scan.id), scan.meta))[0];
            }
            aspects = ThaumcraftCraftingManager.getObjectTags(new ItemStack(Item.func_150899_d((int)scan.id), 1, scan.meta));
            aspects = ThaumcraftCraftingManager.getBonusTags(new ItemStack(Item.func_150899_d((int)scan.id), 1, scan.meta), aspects);
            if ((aspects == null || aspects.size() == 0) && scan.id > 0) {
                aspects = ThaumcraftCraftingManager.getObjectTags(new ItemStack(Item.func_150899_d((int)scan.id), 1, scan.meta));
                aspects = ThaumcraftCraftingManager.getBonusTags(new ItemStack(Item.func_150899_d((int)scan.id), 1, scan.meta), aspects);
            }
            if (ScanManager.validScan(aspects, player)) {
                clue = new ItemStack(Item.func_150899_d((int)scan.id), 1, scan.meta);
                Thaumcraft.proxy.getResearchManager().completeScannedObject(player, prefix + ScanManager.generateItemHash(Item.func_150899_d((int)scan.id), scan.meta));
                ret = true;
            }
        } else if (scan.type == 2) {
            if (scan.entity instanceof EntityItem) {
                EntityItem item = (EntityItem)scan.entity;
                ItemStack t = item.func_92059_d().func_77946_l();
                t.field_77994_a = 1;
                if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(t.func_77973_b(), t.func_77960_j()))) {
                    t.func_77964_b(ThaumcraftApi.groupedObjectTags.get(Arrays.asList(t.func_77973_b(), t.func_77960_j()))[0]);
                }
                aspects = ThaumcraftCraftingManager.getObjectTags(t);
                if (ScanManager.validScan(aspects = ThaumcraftCraftingManager.getBonusTags(t, aspects), player)) {
                    clue = item.func_92059_d();
                    Thaumcraft.proxy.getResearchManager().completeScannedObject(player, prefix + ScanManager.generateItemHash(item.func_92059_d().func_77973_b(), item.func_92059_d().func_77960_j()));
                    ret = true;
                }
            } else {
                aspects = ScanManager.generateEntityAspects(scan.entity);
                if (ScanManager.validScan(aspects, player)) {
                    clue = EntityList.func_75621_b((Entity)scan.entity);
                    Thaumcraft.proxy.getResearchManager().completeScannedEntity(player, prefix + ScanManager.generateEntityHash(scan.entity));
                    ret = true;
                }
            }
        } else if (scan.type == 3 && scan.phenomena.startsWith("NODE") && ScanManager.validScan(aspects = ScanManager.generateNodeAspects(player.field_70170_p, scan.phenomena.replace("NODE", "")), player)) {
            Thaumcraft.proxy.getResearchManager().completeScannedPhenomena(player, prefix + scan.phenomena);
            ret = true;
        }
        if (!player.field_70170_p.field_72995_K && ret && aspects != null) {
            AspectList aspectsFinal = new AspectList();
            for (Aspect aspect : aspects.getAspects()) {
                int a;
                if (!rp.hasDiscoveredParentAspects(player.func_70005_c_(), aspect)) continue;
                int amt = aspects.getAmount(aspect);
                if (scannedByThaumometer) {
                    amt = 0;
                }
                if (prefix.equals("#")) {
                    ++amt;
                }
                if ((a = ScanManager.checkAndSyncAspectKnowledge(player, aspect, amt)) <= 0) continue;
                aspectsFinal.merge(aspect, a);
            }
            if (clue != null) {
                ResearchManager.createClue(player.field_70170_p, player, clue, aspectsFinal);
            }
        }
        return ret;
    }

    public static int checkAndSyncAspectKnowledge(EntityPlayer player, Aspect aspect, int amount) {
        PlayerKnowledge rp = Thaumcraft.proxy.getPlayerKnowledge();
        int save = 0;
        if (!rp.hasDiscoveredAspect(player.func_70005_c_(), aspect)) {
            PacketHandler.INSTANCE.sendTo((IMessage)new PacketAspectDiscovery(aspect.getTag()), (EntityPlayerMP)player);
            save = amount += 2;
        }
        if (rp.getAspectPoolFor(player.func_70005_c_(), aspect) >= Config.aspectTotalCap) {
            amount = (int)Math.sqrt(amount);
        }
        if (amount > 1 && (float)rp.getAspectPoolFor(player.func_70005_c_(), aspect) >= (float)Config.aspectTotalCap * 1.25f) {
            amount = 1;
        }
        if (rp.addAspectPool(player.func_70005_c_(), aspect, (short)amount)) {
            PacketHandler.INSTANCE.sendTo((IMessage)new PacketAspectPool(aspect.getTag(), (short)amount, rp.getAspectPoolFor(player.func_70005_c_(), aspect)), (EntityPlayerMP)player);
            save = amount;
        }
        if (save > 0) {
            Thaumcraft.proxy.getResearchManager().completeAspect(player, aspect, rp.getAspectPoolFor(player.func_70005_c_(), aspect));
        }
        return save;
    }

    public static boolean validScan(AspectList aspects, EntityPlayer player) {
        PlayerKnowledge rp = Thaumcraft.proxy.getPlayerKnowledge();
        if (aspects != null && aspects.size() > 0) {
            for (Aspect aspect : aspects.getAspects()) {
                if (aspect == null || aspect.isPrimal() || rp.hasDiscoveredParentAspects(player.func_70005_c_(), aspect)) continue;
                if (player.field_70170_p.field_72995_K) {
                    for (Aspect parent : aspect.getComponents()) {
                        if (rp.hasDiscoveredAspect(player.func_70005_c_(), parent)) continue;
                        PlayerNotifications.addNotification(new ChatComponentTranslation(StatCollector.func_74838_a((String)"tc.discoveryerror"), new Object[]{StatCollector.func_74838_a((String)("tc.aspect.help." + parent.getTag()))}).func_150260_c());
                        break;
                    }
                }
                return false;
            }
        } else {
            if (player.field_70170_p.field_72995_K) {
                PlayerNotifications.addNotification(StatCollector.func_74838_a((String)"tc.unknownobject"));
            }
            return false;
        }
        return true;
    }

    public static AspectList getScanAspects(ScanResult scan, World world) {
        AspectList aspects = new AspectList();
        boolean ret = false;
        if (scan.type == 1) {
            if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(Item.func_150899_d((int)scan.id), scan.meta))) {
                scan.meta = ThaumcraftApi.groupedObjectTags.get(Arrays.asList(Item.func_150899_d((int)scan.id), scan.meta))[0];
            }
            aspects = ThaumcraftCraftingManager.getObjectTags(new ItemStack(Item.func_150899_d((int)scan.id), 1, scan.meta));
            aspects = ThaumcraftCraftingManager.getBonusTags(new ItemStack(Item.func_150899_d((int)scan.id), 1, scan.meta), aspects);
            if ((aspects == null || aspects.size() == 0) && scan.id > 0) {
                aspects = ThaumcraftCraftingManager.getObjectTags(new ItemStack(Item.func_150899_d((int)scan.id), 1, scan.meta));
                aspects = ThaumcraftCraftingManager.getBonusTags(new ItemStack(Item.func_150899_d((int)scan.id), 1, scan.meta), aspects);
            }
        } else if (scan.type == 2) {
            if (scan.entity instanceof EntityItem && ((EntityItem)scan.entity).func_92059_d() != null) {
                EntityItem item = (EntityItem)scan.entity;
                ItemStack t = item.func_92059_d().func_77946_l();
                t.field_77994_a = 1;
                if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(t.func_77973_b(), t.func_77960_j()))) {
                    t.func_77964_b(ThaumcraftApi.groupedObjectTags.get(Arrays.asList(t.func_77973_b(), t.func_77960_j()))[0]);
                }
                aspects = ThaumcraftCraftingManager.getObjectTags(t);
                aspects = ThaumcraftCraftingManager.getBonusTags(t, aspects);
            } else {
                aspects = ScanManager.generateEntityAspects(scan.entity);
            }
        } else if (scan.type == 3 && scan.phenomena.startsWith("NODE")) {
            aspects = ScanManager.generateNodeAspects(world, scan.phenomena.replace("NODE", ""));
        }
        return aspects;
    }
}

