/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentDurability
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.tileentity.TileEntityHopper
 *  net.minecraft.world.World
 *  net.minecraftforge.oredict.OreDictionary
 */
package thaumcraft.common.lib.utils;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDurability;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.tiles.TileArcaneWorkbench;
import thaumcraft.common.tiles.TileResearchTable;

public class InventoryUtils {
    public static ItemStack placeItemStackIntoInventory(ItemStack stack, IInventory inventory, int side, boolean doit) {
        ItemStack itemstack = stack.func_77946_l();
        ItemStack itemstack1 = InventoryUtils.insertStack(inventory, itemstack, side, doit);
        if (itemstack1 == null || itemstack1.field_77994_a == 0) {
            if (doit) {
                inventory.func_70296_d();
            }
            return null;
        }
        stack = itemstack1;
        return stack.func_77946_l();
    }

    public static ItemStack insertStack(IInventory inventory, ItemStack stack1, int side, boolean doit) {
        if (inventory instanceof ISidedInventory && side > -1) {
            int j;
            ISidedInventory isidedinventory = (ISidedInventory)inventory;
            int[] aint = isidedinventory.func_94128_d(side);
            if (aint != null) {
                for (j = 0; j < aint.length && stack1 != null && stack1.field_77994_a > 0; ++j) {
                    if (inventory.func_70301_a(aint[j]) != null && inventory.func_70301_a(aint[j]).func_77969_a(stack1)) {
                        stack1 = InventoryUtils.attemptInsertion(inventory, stack1, aint[j], side, doit);
                    }
                    if (stack1 == null || stack1.field_77994_a == 0) break;
                }
            }
            if (aint != null && stack1 != null && stack1.field_77994_a > 0) {
                for (j = 0; j < aint.length && stack1 != null && stack1.field_77994_a > 0 && (stack1 = InventoryUtils.attemptInsertion(inventory, stack1, aint[j], side, doit)) != null && stack1.field_77994_a != 0; ++j) {
                }
            }
        } else {
            int k = inventory.func_70302_i_();
            for (int l = 0; l < k && stack1 != null && stack1.field_77994_a > 0; ++l) {
                if (inventory.func_70301_a(l) != null && inventory.func_70301_a(l).func_77969_a(stack1)) {
                    stack1 = InventoryUtils.attemptInsertion(inventory, stack1, l, side, doit);
                }
                if (stack1 == null || stack1.field_77994_a == 0) break;
            }
            if (stack1 != null && stack1.field_77994_a > 0) {
                int l;
                int k2;
                TileEntityChest dc = null;
                if (inventory instanceof TileEntity && (dc = InventoryUtils.getDoubleChest((TileEntity)inventory)) != null) {
                    k2 = dc.func_70302_i_();
                    for (l = 0; l < k2 && stack1 != null && stack1.field_77994_a > 0; ++l) {
                        if (dc.func_70301_a(l) != null && dc.func_70301_a(l).func_77969_a(stack1)) {
                            stack1 = InventoryUtils.attemptInsertion((IInventory)dc, stack1, l, side, doit);
                        }
                        if (stack1 == null || stack1.field_77994_a == 0) break;
                    }
                }
                if (stack1 != null && stack1.field_77994_a > 0) {
                    for (int l2 = 0; l2 < k && stack1 != null && stack1.field_77994_a > 0 && (stack1 = InventoryUtils.attemptInsertion(inventory, stack1, l2, side, doit)) != null && stack1.field_77994_a != 0; ++l2) {
                    }
                    if (stack1 != null && stack1.field_77994_a > 0 && dc != null) {
                        k2 = dc.func_70302_i_();
                        for (l = 0; l < k2 && stack1 != null && stack1.field_77994_a > 0; ++l) {
                            if (dc.func_70301_a(l) != null && dc.func_70301_a(l).func_77969_a(stack1)) {
                                stack1 = InventoryUtils.attemptInsertion((IInventory)dc, stack1, l, side, doit);
                            }
                            if (stack1 != null && stack1.field_77994_a != 0) {
                                continue;
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (stack1 != null && stack1.field_77994_a == 0) {
            stack1 = null;
        }
        return stack1;
    }

    private static ItemStack attemptInsertion(IInventory inventory, ItemStack stack, int slot, int side, boolean doit) {
        ItemStack slotStack = inventory.func_70301_a(slot);
        if (InventoryUtils.canInsertItemToInventory(inventory, stack, slot, side)) {
            boolean flag = false;
            if (slotStack == null) {
                if (inventory.func_70297_j_() < stack.field_77994_a) {
                    ItemStack in = stack.func_77979_a(inventory.func_70297_j_());
                    if (doit) {
                        inventory.func_70299_a(slot, in);
                    }
                } else {
                    if (doit) {
                        inventory.func_70299_a(slot, stack);
                    }
                    stack = null;
                }
                flag = true;
            } else if (InventoryUtils.areItemStacksEqualStrict(slotStack, stack)) {
                int k = Math.min(inventory.func_70297_j_() - slotStack.field_77994_a, stack.func_77976_d() - slotStack.field_77994_a);
                int l = Math.min(stack.field_77994_a, k);
                stack.field_77994_a -= l;
                if (doit) {
                    slotStack.field_77994_a += l;
                }
                boolean bl = flag = l > 0;
            }
            if (flag && doit) {
                if (inventory instanceof TileEntityHopper) {
                    ((TileEntityHopper)inventory).func_145896_c(8);
                    inventory.func_70296_d();
                }
                inventory.func_70296_d();
            }
        }
        return stack;
    }

    public static ItemStack getFirstItemInInventory(IInventory inventory, int size, int side, boolean doit) {
        ItemStack stack1 = null;
        if (inventory instanceof ISidedInventory && side > -1) {
            ISidedInventory isidedinventory = (ISidedInventory)inventory;
            int[] aint = isidedinventory.func_94128_d(side);
            for (int j = 0; j < aint.length; ++j) {
                if (stack1 == null && inventory.func_70301_a(aint[j]) != null) {
                    stack1 = inventory.func_70301_a(aint[j]).func_77946_l();
                    stack1.field_77994_a = size;
                }
                if (stack1 != null) {
                    stack1 = InventoryUtils.attemptExtraction(inventory, stack1, aint[j], side, false, false, false, doit);
                }
                if (stack1 == null) {
                    continue;
                }
                break;
            }
        } else {
            int k = inventory.func_70302_i_();
            for (int l = 0; l < k; ++l) {
                if (stack1 == null && inventory.func_70301_a(l) != null) {
                    stack1 = inventory.func_70301_a(l).func_77946_l();
                    stack1.field_77994_a = size;
                }
                if (stack1 != null) {
                    stack1 = InventoryUtils.attemptExtraction(inventory, stack1, l, side, false, false, false, doit);
                }
                if (stack1 == null) {
                    continue;
                }
                break;
            }
        }
        if (stack1 == null || stack1.field_77994_a == 0) {
            if (doit) {
                inventory.func_70296_d();
            }
            return null;
        }
        return stack1.func_77946_l();
    }

    public static boolean inventoryContains(IInventory inventory, ItemStack stack, int side, boolean useOre, boolean ignoreDamage, boolean ignoreNBT) {
        return InventoryUtils.extractStack(inventory, stack, side, useOre, ignoreDamage, ignoreNBT, false) != null;
    }

    public static ItemStack extractStack(IInventory inventory, ItemStack stack1, int side, boolean useOre, boolean ignoreDamage, boolean ignoreNBT, boolean doit) {
        ItemStack outStack = null;
        if (inventory instanceof ISidedInventory && side > -1) {
            ISidedInventory isidedinventory = (ISidedInventory)inventory;
            int[] aint = isidedinventory.func_94128_d(side);
            for (int j = 0; j < aint.length && stack1 != null && stack1.field_77994_a > 0 && outStack == null; ++j) {
                outStack = InventoryUtils.attemptExtraction(inventory, stack1, aint[j], side, useOre, ignoreDamage, ignoreNBT, doit);
            }
        } else {
            int k = inventory.func_70302_i_();
            for (int l = 0; l < k && stack1 != null && stack1.field_77994_a > 0 && outStack == null; ++l) {
                outStack = InventoryUtils.attemptExtraction(inventory, stack1, l, side, useOre, ignoreDamage, ignoreNBT, doit);
            }
        }
        if (outStack == null || outStack.field_77994_a == 0) {
            return null;
        }
        return outStack.func_77946_l();
    }

    public static ItemStack attemptExtraction(IInventory inventory, ItemStack stack, int slot, int side, boolean useOre, boolean ignoreDamage, boolean ignoreNBT, boolean doit) {
        ItemStack slotStack = inventory.func_70301_a(slot);
        ItemStack outStack = stack.func_77946_l();
        if (InventoryUtils.canExtractItemFromInventory(inventory, slotStack, slot, side)) {
            boolean flag = false;
            if (InventoryUtils.areItemStacksEqual(slotStack, stack, useOre, ignoreDamage, ignoreNBT)) {
                outStack = slotStack.func_77946_l();
                outStack.field_77994_a = stack.field_77994_a;
                int k = stack.field_77994_a - slotStack.field_77994_a;
                if (k >= 0) {
                    outStack.field_77994_a -= k;
                    if (doit) {
                        slotStack = null;
                        inventory.func_70299_a(slot, null);
                    }
                } else if (doit) {
                    slotStack.field_77994_a -= outStack.field_77994_a;
                    inventory.func_70299_a(slot, slotStack);
                }
            } else {
                return null;
            }
            flag = true;
            if (flag && doit) {
                inventory.func_70296_d();
            }
        } else {
            return null;
        }
        return outStack;
    }

    public static boolean canInsertItemToInventory(IInventory inventory, ItemStack stack1, int par2, int par3) {
        return stack1 != null && inventory.func_94041_b(par2, stack1) && (!(inventory instanceof ISidedInventory) || ((ISidedInventory)inventory).func_102007_a(par2, stack1, par3));
    }

    public static boolean canExtractItemFromInventory(IInventory inventory, ItemStack stack1, int par2, int par3) {
        return stack1 != null && (!(inventory instanceof ISidedInventory) || ((ISidedInventory)inventory).func_102008_b(par2, stack1, par3));
    }

    public static boolean compareMultipleItems(ItemStack c1, ItemStack[] c2) {
        if (c1 == null || c1.field_77994_a <= 0) {
            return false;
        }
        for (ItemStack is : c2) {
            if (is == null || !c1.func_77969_a(is) || !ItemStack.func_77970_a((ItemStack)c1, (ItemStack)is)) continue;
            return true;
        }
        return false;
    }

    public static boolean areItemStacksEqualStrict(ItemStack stack0, ItemStack stack1) {
        return InventoryUtils.areItemStacksEqual(stack0, stack1, false, false, false);
    }

    public static boolean areItemStacksEqualForCrafting(ItemStack stack0, ItemStack stack1, boolean useOre, boolean ignoreDamage, boolean ignoreNBT) {
        boolean t2;
        ItemStack[] ores;
        int od;
        if (stack0 == null && stack1 != null) {
            return false;
        }
        if (stack0 != null && stack1 == null) {
            return false;
        }
        if (stack0 == null && stack1 == null) {
            return true;
        }
        if (useOre && (od = OreDictionary.getOreID((ItemStack)stack0)) != -1 && ThaumcraftApiHelper.containsMatch(false, new ItemStack[]{stack1}, ores = OreDictionary.getOres((Integer)od).toArray(new ItemStack[0]))) {
            return true;
        }
        boolean t1 = true;
        if (!ignoreNBT) {
            t1 = ThaumcraftApiHelper.areItemStackTagsEqualForCrafting(stack0, stack1);
        }
        boolean bl = t2 = stack0.func_77960_j() != stack1.func_77960_j();
        if (ignoreDamage && stack0.func_77984_f() && stack1.func_77984_f()) {
            t2 = false;
        }
        if (t2 && ignoreDamage && (stack0.func_77960_j() == Short.MAX_VALUE || stack1.func_77960_j() == Short.MAX_VALUE)) {
            t2 = false;
        }
        return stack0.func_77973_b() != stack1.func_77973_b() ? false : (t2 ? false : (stack0.field_77994_a > stack0.func_77976_d() ? false : t1));
    }

    public static boolean areItemStacksEqual(ItemStack stack0, ItemStack stack1, boolean useOre, boolean ignoreDamage, boolean ignoreNBT) {
        boolean t2;
        ItemStack[] ores;
        int od;
        if (stack0 == null && stack1 != null) {
            return false;
        }
        if (stack0 != null && stack1 == null) {
            return false;
        }
        if (stack0 == null && stack1 == null) {
            return true;
        }
        if (useOre && (od = OreDictionary.getOreID((ItemStack)stack0)) != -1 && ThaumcraftApiHelper.containsMatch(false, new ItemStack[]{stack1}, ores = OreDictionary.getOres((Integer)od).toArray(new ItemStack[0]))) {
            return true;
        }
        boolean t1 = true;
        if (!ignoreNBT) {
            t1 = ItemStack.func_77970_a((ItemStack)stack0, (ItemStack)stack1);
        }
        boolean bl = t2 = stack0.func_77960_j() != stack1.func_77960_j();
        if (ignoreDamage && stack0.func_77984_f() && stack1.func_77984_f()) {
            t2 = false;
        }
        if (t2 && ignoreDamage && (stack0.func_77960_j() == Short.MAX_VALUE || stack1.func_77960_j() == Short.MAX_VALUE)) {
            t2 = false;
        }
        return stack0.func_77973_b() != stack1.func_77973_b() ? false : (t2 ? false : t1);
    }

    public static boolean consumeInventoryItem(EntityPlayer player, Item item, int md) {
        for (int var2 = 0; var2 < player.field_71071_by.field_70462_a.length; ++var2) {
            if (player.field_71071_by.field_70462_a[var2] == null || player.field_71071_by.field_70462_a[var2].func_77973_b() != item || player.field_71071_by.field_70462_a[var2].func_77960_j() != md) continue;
            if (--player.field_71071_by.field_70462_a[var2].field_77994_a <= 0) {
                player.field_71071_by.field_70462_a[var2] = null;
            }
            return true;
        }
        return false;
    }

    public static void dropItems(World world, int x, int y, int z) {
        Random rand = new Random();
        int md = world.func_72805_g(x, y, z);
        TileEntity tileEntity = world.func_147438_o(x, y, z);
        if (!(tileEntity instanceof IInventory)) {
            return;
        }
        IInventory inventory = (IInventory)tileEntity;
        for (int i = 0; i < inventory.func_70302_i_(); ++i) {
            ItemStack item;
            if (tileEntity instanceof TileResearchTable && md == 15 && i == 9 || tileEntity instanceof TileArcaneWorkbench && i == 9 || (item = inventory.func_70301_a(i)) == null || item.field_77994_a <= 0) continue;
            float rx = rand.nextFloat() * 0.8f + 0.1f;
            float ry = rand.nextFloat() * 0.8f + 0.1f;
            float rz = rand.nextFloat() * 0.8f + 0.1f;
            EntityItem entityItem = new EntityItem(world, (double)((float)x + rx), (double)((float)y + ry), (double)((float)z + rz), new ItemStack(item.func_77973_b(), item.field_77994_a, item.func_77960_j()));
            if (item.func_77942_o()) {
                entityItem.func_92059_d().func_77982_d((NBTTagCompound)item.func_77978_p().func_74737_b());
            }
            float factor = 0.05f;
            entityItem.field_70159_w = rand.nextGaussian() * (double)factor;
            entityItem.field_70181_x = rand.nextGaussian() * (double)factor + (double)0.2f;
            entityItem.field_70179_y = rand.nextGaussian() * (double)factor;
            world.func_72838_d((Entity)entityItem);
            inventory.func_70299_a(i, null);
        }
    }

    public static void dropItemsAtEntity(World world, int x, int y, int z, Entity entity) {
        Random rand = new Random();
        int md = world.func_72805_g(x, y, z);
        TileEntity tileEntity = world.func_147438_o(x, y, z);
        if (!(tileEntity instanceof IInventory)) {
            return;
        }
        IInventory inventory = (IInventory)tileEntity;
        for (int i = 0; i < inventory.func_70302_i_(); ++i) {
            ItemStack item;
            if (tileEntity instanceof TileResearchTable && md == 15 && i == 9 || tileEntity instanceof TileArcaneWorkbench && i == 9 || (item = inventory.func_70301_a(i)) == null || item.field_77994_a <= 0) continue;
            EntityItem entityItem = new EntityItem(world, entity.field_70165_t, entity.field_70163_u + (double)(entity.func_70047_e() / 2.0f), entity.field_70161_v, item.func_77946_l());
            world.func_72838_d((Entity)entityItem);
            inventory.func_70299_a(i, null);
        }
    }

    public static int isWandInHotbarWithRoom(Aspect aspect, int amount, EntityPlayer player) {
        int var2 = 0;
        while (true) {
            ItemWandCasting wand;
            EntityPlayer entityPlayer = player;
            if (var2 >= entityPlayer.field_71071_by.func_70451_h()) break;
            if (player.field_71071_by.field_70462_a[var2] != null && player.field_71071_by.field_70462_a[var2].func_77973_b() instanceof ItemWandCasting && (wand = (ItemWandCasting)player.field_71071_by.field_70462_a[var2].func_77973_b()).addVis(player.field_71071_by.field_70462_a[var2], aspect, amount, false) < amount) {
                return var2;
            }
            ++var2;
        }
        return -1;
    }

    public static int isPlayerCarrying(EntityPlayer player, ItemStack stack) {
        for (int var2 = 0; var2 < player.field_71071_by.field_70462_a.length; ++var2) {
            if (player.field_71071_by.field_70462_a[var2] == null || !player.field_71071_by.field_70462_a[var2].func_77969_a(stack)) continue;
            return var2;
        }
        return -1;
    }

    public static ItemStack damageItem(int par1, ItemStack stack, World worldObj) {
        if (stack.func_77984_f()) {
            if (par1 > 0) {
                int var3 = EnchantmentHelper.func_77506_a((int)Enchantment.field_77347_r.field_77352_x, (ItemStack)stack);
                int var4 = 0;
                for (int var5 = 0; var3 > 0 && var5 < par1; ++var5) {
                    if (!EnchantmentDurability.func_92097_a((ItemStack)stack, (int)var3, (Random)worldObj.field_73012_v)) continue;
                    ++var4;
                }
                if ((par1 -= var4) <= 0) {
                    return stack;
                }
            }
            stack.func_77964_b(stack.func_77960_j() + par1);
            if (stack.func_77960_j() > stack.func_77958_k()) {
                --stack.field_77994_a;
                if (stack.field_77994_a < 0) {
                    stack.field_77994_a = 0;
                }
                stack.func_77964_b(0);
            }
        }
        return stack;
    }

    public static void dropItemsWithChance(World world, int x, int y, int z, float chance, int fortune, ArrayList<ItemStack> items) {
        for (ItemStack item : items) {
            if (!(world.field_73012_v.nextFloat() <= chance) || item.field_77994_a <= 0 || world.field_72995_K || !world.func_82736_K().func_82766_b("doTileDrops")) continue;
            float var6 = 0.7f;
            double var7 = (double)(world.field_73012_v.nextFloat() * var6) + (double)(1.0f - var6) * 0.5;
            double var9 = (double)(world.field_73012_v.nextFloat() * var6) + (double)(1.0f - var6) * 0.5;
            double var11 = (double)(world.field_73012_v.nextFloat() * var6) + (double)(1.0f - var6) * 0.5;
            EntityItem var13 = new EntityItem(world, (double)x + var7, (double)y + var9, (double)z + var11, item);
            var13.field_145804_b = 10;
            world.func_72838_d((Entity)var13);
        }
    }

    public static TileEntityChest getDoubleChest(TileEntity tile) {
        if (tile != null && tile instanceof TileEntityChest) {
            if (((TileEntityChest)tile).field_145991_k != null) {
                return ((TileEntityChest)tile).field_145991_k;
            }
            if (((TileEntityChest)tile).field_145990_j != null) {
                return ((TileEntityChest)tile).field_145990_j;
            }
            if (((TileEntityChest)tile).field_145992_i != null) {
                return ((TileEntityChest)tile).field_145992_i;
            }
            if (((TileEntityChest)tile).field_145988_l != null) {
                return ((TileEntityChest)tile).field_145988_l;
            }
        }
        return null;
    }

    public static ItemStack cycleItemStack(Object input) {
        ArrayList q;
        ItemStack it = null;
        if (input instanceof ItemStack) {
            it = (ItemStack)input;
            if (it.func_77960_j() == Short.MAX_VALUE && it.func_77973_b().func_77614_k()) {
                ArrayList q2 = new ArrayList();
                it.func_77973_b().func_150895_a(it.func_77973_b(), it.func_77973_b().func_77640_w(), q2);
                if (q2 != null && q2.size() > 0) {
                    int md = (int)(System.currentTimeMillis() / 1000L % (long)q2.size());
                    ItemStack it2 = new ItemStack(it.func_77973_b(), 1, md);
                    it2.func_77982_d(it.func_77978_p());
                    it = it2;
                }
            } else if (it.func_77960_j() == Short.MAX_VALUE && it.func_77984_f()) {
                int md = (int)(System.currentTimeMillis() / 10L % (long)it.func_77958_k());
                ItemStack it2 = new ItemStack(it.func_77973_b(), 1, md);
                it2.func_77982_d(it.func_77978_p());
                it = it2;
            }
        } else if (input instanceof ArrayList) {
            ArrayList q3 = (ArrayList)input;
            if (q3 != null && q3.size() > 0) {
                int idx = (int)(System.currentTimeMillis() / 1000L % (long)q3.size());
                it = InventoryUtils.cycleItemStack(q3.get(idx));
            }
        } else if (input instanceof String && (q = OreDictionary.getOres((String)((String)input))) != null && q.size() > 0) {
            int idx = (int)(System.currentTimeMillis() / 1000L % (long)q.size());
            it = InventoryUtils.cycleItemStack(q.get(idx));
        }
        return it;
    }
}

