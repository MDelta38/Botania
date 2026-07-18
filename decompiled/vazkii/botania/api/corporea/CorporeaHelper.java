/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.util.ForgeDirection
 */
package vazkii.botania.api.corporea;

import cpw.mods.fml.common.eventhandler.Event;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.regex.Pattern;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.corporea.CorporeaRequest;
import vazkii.botania.api.corporea.CorporeaRequestEvent;
import vazkii.botania.api.corporea.ICorporeaAutoCompleteController;
import vazkii.botania.api.corporea.ICorporeaInterceptor;
import vazkii.botania.api.corporea.ICorporeaSpark;
import vazkii.botania.api.corporea.IWrappedInventory;

public final class CorporeaHelper {
    private static final List<IInventory> empty = Collections.unmodifiableList(new ArrayList());
    private static final WeakHashMap<List<ICorporeaSpark>, List<IInventory>> cachedNetworks = new WeakHashMap();
    private static final List<ICorporeaAutoCompleteController> autoCompleteControllers = new ArrayList<ICorporeaAutoCompleteController>();
    private static final Pattern patternControlCode = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");
    public static final String[] WILDCARD_STRINGS = new String[]{"...", "~", "+", "?", "*"};
    public static int lastRequestMatches = 0;
    public static int lastRequestExtractions = 0;

    public static List<IInventory> getInventoriesOnNetwork(ICorporeaSpark spark) {
        List<IInventory> cache;
        ICorporeaSpark master = spark.getMaster();
        if (master == null) {
            return empty;
        }
        List<ICorporeaSpark> network = master.getConnections();
        if (cachedNetworks.containsKey(network) && (cache = cachedNetworks.get(network)) != null) {
            return cache;
        }
        ArrayList<IInventory> inventories = new ArrayList<IInventory>();
        if (network != null) {
            for (ICorporeaSpark otherSpark : network) {
                IInventory inv;
                if (otherSpark == null || (inv = otherSpark.getInventory()) == null) continue;
                inventories.add(inv);
            }
        }
        cachedNetworks.put(network, inventories);
        return inventories;
    }

    public static int getCountInNetwork(ItemStack stack, ICorporeaSpark spark, boolean checkNBT) {
        List<IInventory> inventories = CorporeaHelper.getInventoriesOnNetwork(spark);
        return CorporeaHelper.getCountInNetwork(stack, inventories, checkNBT);
    }

    public static int getCountInNetwork(ItemStack stack, List<IInventory> inventories, boolean checkNBT) {
        Map<IInventory, Integer> map = CorporeaHelper.getInventoriesWithItemInNetwork(stack, inventories, checkNBT);
        return CorporeaHelper.getCountInNetwork(stack, map, checkNBT);
    }

    public static int getCountInNetwork(ItemStack stack, Map<IInventory, Integer> inventories, boolean checkNBT) {
        int count = 0;
        for (IInventory inv : inventories.keySet()) {
            count += inventories.get(inv).intValue();
        }
        return count;
    }

    public static Map<IInventory, Integer> getInventoriesWithItemInNetwork(ItemStack stack, ICorporeaSpark spark, boolean checkNBT) {
        List<IInventory> inventories = CorporeaHelper.getInventoriesOnNetwork(spark);
        return CorporeaHelper.getInventoriesWithItemInNetwork(stack, inventories, checkNBT);
    }

    public static Map<IInventory, Integer> getInventoriesWithItemInNetwork(ItemStack stack, List<IInventory> inventories, boolean checkNBT) {
        HashMap<IInventory, Integer> countMap = new HashMap<IInventory, Integer>();
        List<IWrappedInventory> wrappedInventories = BotaniaAPI.internalHandler.wrapInventory(inventories);
        for (IWrappedInventory inv : wrappedInventories) {
            CorporeaRequest request = new CorporeaRequest(stack, checkNBT, -1);
            inv.countItems(request);
            if (request.foundItems <= 0) continue;
            countMap.put(inv.getWrappedObject(), request.foundItems);
        }
        return countMap;
    }

    public static List<ItemStack> requestItem(ItemStack stack, ICorporeaSpark spark, boolean checkNBT, boolean doit) {
        return CorporeaHelper.requestItem(stack, stack.field_77994_a, spark, checkNBT, doit);
    }

    public static List<ItemStack> requestItem(String name, int count, ICorporeaSpark spark, boolean doit) {
        return CorporeaHelper.requestItem(name, count, spark, false, doit);
    }

    public static List<ItemStack> requestItem(Object matcher, int itemCount, ICorporeaSpark spark, boolean checkNBT, boolean doit) {
        ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
        CorporeaRequestEvent event = new CorporeaRequestEvent(matcher, itemCount, spark, checkNBT, doit);
        if (MinecraftForge.EVENT_BUS.post((Event)event)) {
            return stacks;
        }
        List<IInventory> inventories = CorporeaHelper.getInventoriesOnNetwork(spark);
        List<IWrappedInventory> inventoriesW = BotaniaAPI.internalHandler.wrapInventory(inventories);
        HashMap<ICorporeaInterceptor, ICorporeaSpark> interceptors = new HashMap<ICorporeaInterceptor, ICorporeaSpark>();
        CorporeaRequest request = new CorporeaRequest(matcher, checkNBT, itemCount);
        for (IWrappedInventory inv : inventoriesW) {
            ICorporeaSpark invSpark = inv.getSpark();
            IInventory originalInventory = inv.getWrappedObject();
            if (originalInventory instanceof ICorporeaInterceptor) {
                ICorporeaInterceptor interceptor = (ICorporeaInterceptor)originalInventory;
                interceptor.interceptRequest(matcher, itemCount, invSpark, spark, stacks, inventories, doit);
                interceptors.put(interceptor, invSpark);
            }
            if (doit) {
                stacks.addAll(inv.extractItems(request));
                continue;
            }
            stacks.addAll(inv.countItems(request));
        }
        lastRequestMatches = request.foundItems;
        lastRequestExtractions = request.extractedItems;
        for (ICorporeaInterceptor interceptor : interceptors.keySet()) {
            interceptor.interceptRequestLast(matcher, itemCount, (ICorporeaSpark)interceptors.get(interceptor), spark, stacks, inventories, doit);
        }
        return stacks;
    }

    public static ICorporeaSpark getSparkForInventory(IInventory inv) {
        if (!(inv instanceof TileEntity)) {
            return null;
        }
        TileEntity tile = (TileEntity)inv;
        return CorporeaHelper.getSparkForBlock(tile.func_145831_w(), tile.field_145851_c, tile.field_145848_d, tile.field_145849_e);
    }

    public static ICorporeaSpark getSparkForBlock(World world, int x, int y, int z) {
        List sparks = world.func_72872_a(ICorporeaSpark.class, AxisAlignedBB.func_72330_a((double)x, (double)(y + 1), (double)z, (double)(x + 1), (double)(y + 2), (double)(z + 1)));
        return sparks.isEmpty() ? null : (ICorporeaSpark)sparks.get(0);
    }

    public static boolean doesBlockHaveSpark(World world, int x, int y, int z) {
        return CorporeaHelper.getSparkForBlock(world, x, y, z) != null;
    }

    public static boolean isValidSlot(IInventory inv, int slot) {
        return !(inv instanceof ISidedInventory) || CorporeaHelper.arrayHas(((ISidedInventory)inv).func_94128_d(ForgeDirection.UP.ordinal()), slot) && ((ISidedInventory)inv).func_102008_b(slot, inv.func_70301_a(slot), ForgeDirection.UP.ordinal());
    }

    public static boolean stacksMatch(ItemStack stack1, ItemStack stack2, boolean checkNBT) {
        return stack1 != null && stack2 != null && stack1.func_77969_a(stack2) && (!checkNBT || ItemStack.func_77970_a((ItemStack)stack1, (ItemStack)stack2));
    }

    public static boolean stacksMatch(ItemStack stack, String s) {
        String name;
        if (stack == null) {
            return false;
        }
        boolean contains = false;
        for (String wc : WILDCARD_STRINGS) {
            if (s.endsWith(wc)) {
                contains = true;
                s = s.substring(0, s.length() - wc.length());
            } else if (s.startsWith(wc)) {
                contains = true;
                s = s.substring(wc.length());
            }
            if (contains) break;
        }
        return CorporeaHelper.equalOrContain(name = CorporeaHelper.stripControlCodes(stack.func_82833_r().toLowerCase().trim()), s, contains) || CorporeaHelper.equalOrContain(name + "s", s, contains) || CorporeaHelper.equalOrContain(name + "es", s, contains) || name.endsWith("y") && CorporeaHelper.equalOrContain(name.substring(0, name.length() - 1) + "ies", s, contains);
    }

    public static void clearCache() {
        cachedNetworks.clear();
    }

    public static boolean arrayHas(int[] arr, int val) {
        for (int element : arr) {
            if (element != val) continue;
            return true;
        }
        return false;
    }

    public static boolean equalOrContain(String s1, String s2, boolean contain) {
        return contain ? s1.contains(s2) : s1.equals(s2);
    }

    public static void registerAutoCompleteController(ICorporeaAutoCompleteController controller) {
        autoCompleteControllers.add(controller);
    }

    public static boolean shouldAutoComplete() {
        for (ICorporeaAutoCompleteController controller : autoCompleteControllers) {
            if (!controller.shouldAutoComplete()) continue;
            return true;
        }
        return false;
    }

    public static String stripControlCodes(String str) {
        return patternControlCode.matcher(str).replaceAll("");
    }
}

