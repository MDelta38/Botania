/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaublesApi
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.items.lenses;

import baubles.api.BaublesApi;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.items.lenses.ILens;
import com.kentington.thaumichorizons.common.items.lenses.ItemLensCase;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.nodes.IRevealer;

public class LensManager {
    public static long nightVisionOffTime = 0L;

    public static void changeLens(ItemStack is, World w, EntityPlayer player, String lens) {
        int pouchslot;
        IRevealer goggles = (IRevealer)is.func_77973_b();
        TreeMap<String, Integer> lenses = new TreeMap<String, Integer>();
        HashMap<Integer, Integer> pouches = new HashMap<Integer, Integer>();
        int pouchcount = 0;
        ItemStack item = null;
        IInventory baubles = BaublesApi.getBaubles((EntityPlayer)player);
        for (int a = 0; a < 4; ++a) {
            if (baubles.func_70301_a(a) == null || !(baubles.func_70301_a(a).func_77973_b() instanceof ItemLensCase)) continue;
            item = baubles.func_70301_a(a);
            pouches.put(++pouchcount, a - 4);
            ItemStack[] inv = ((ItemLensCase)item.func_77973_b()).getInventory(item);
            for (int q = 0; q < inv.length; ++q) {
                item = inv[q];
                if (item == null || !(item.func_77973_b() instanceof ILens)) continue;
                lenses.put(((ILens)item.func_77973_b()).lensName(), q + pouchcount * 1000);
            }
        }
        for (int newkey = 0; newkey < 36; ++newkey) {
            item = player.field_71071_by.field_70462_a[newkey];
            if (item != null && item.func_77973_b() instanceof ILens) {
                lenses.put(((ILens)item.func_77973_b()).lensName(), newkey);
            }
            if (item == null || !(item.func_77973_b() instanceof ItemLensCase)) continue;
            pouches.put(++pouchcount, newkey);
            ItemStack[] pid = ((ItemLensCase)item.func_77973_b()).getInventory(item);
            for (pouchslot = 0; pouchslot < pid.length; ++pouchslot) {
                item = pid[pouchslot];
                if (item == null || !(item.func_77973_b() instanceof ILens)) continue;
                lenses.put(((ILens)item.func_77973_b()).lensName(), pouchslot + pouchcount * 1000);
            }
        }
        ItemStack oldLens = null;
        if (!lens.equals("REMOVE") && lenses.size() != 0) {
            if (lenses != null && lenses.size() > 0 && lens != null) {
                String var13 = lens;
                if (lenses.get(lens) == null) {
                    var13 = lenses.higherKey(lens);
                }
                if (var13 == null || lenses.get(var13) == null) {
                    var13 = (String)lenses.firstKey();
                }
                if ((Integer)lenses.get(var13) < 1000) {
                    item = player.field_71071_by.field_70462_a[(Integer)lenses.get(var13)].func_77946_l();
                } else {
                    int var14 = (Integer)lenses.get(var13) / 1000;
                    if (pouches.containsKey(var14)) {
                        pouchslot = pouches.get(var14);
                        int lensSlot = (Integer)lenses.get(var13) - var14 * 1000;
                        ItemStack tmp = pouchslot >= 0 ? player.field_71071_by.field_70462_a[pouchslot].func_77946_l() : baubles.func_70301_a(pouchslot + 4).func_77946_l();
                        item = LensManager.fetchLensFromPouch(player, lensSlot, tmp, pouchslot);
                    }
                }
                if (item == null) {
                    return;
                }
                if ((Integer)lenses.get(var13) < 1000) {
                    player.field_71071_by.func_70299_a(((Integer)lenses.get(var13)).intValue(), (ItemStack)null);
                }
                w.func_72956_a((Entity)player, "thaumcraft:cameraticks", 0.3f, 1.0f);
                String currentLens = "";
                if (is.field_77990_d != null) {
                    currentLens = is.field_77990_d.func_74779_i("Lens");
                }
                oldLens = LensManager.getLensItem(currentLens);
                if (!currentLens.equals("") && (LensManager.addLensToPouch(player, oldLens, pouches) || player.field_71071_by.func_70441_a(oldLens))) {
                    LensManager.setLensItem(is, item);
                } else if (currentLens.equals("")) {
                    LensManager.setLensItem(is, item);
                } else if (!LensManager.addLensToPouch(player, item, pouches)) {
                    player.field_71071_by.func_70441_a(item);
                }
            }
        } else {
            String currentLens = "";
            if (is.field_77990_d != null) {
                currentLens = is.field_77990_d.func_74779_i("Lens");
            }
            oldLens = LensManager.getLensItem(currentLens);
            if (!currentLens.equals("") && (LensManager.addLensToPouch(player, oldLens, pouches) || player.field_71071_by.func_70441_a(oldLens))) {
                LensManager.setLensItem(is, null);
                w.func_72956_a((Entity)player, "thaumcraft:cameraticks", 0.3f, 0.9f);
            }
        }
        if (oldLens != null) {
            ((ILens)oldLens.func_77973_b()).handleRemoval(player);
        }
    }

    private static ItemStack fetchLensFromPouch(EntityPlayer player, int lensid, ItemStack pouch, int pouchslot) {
        ItemStack lens = null;
        ItemStack[] inv = ((ItemLensCase)pouch.func_77973_b()).getInventory(pouch);
        ItemStack contents = inv[lensid];
        if (contents != null && contents.func_77973_b() instanceof ILens) {
            lens = contents.func_77946_l();
            inv[lensid] = null;
            ((ItemLensCase)pouch.func_77973_b()).setInventory(pouch, inv);
            if (pouchslot >= 0) {
                player.field_71071_by.func_70299_a(pouchslot, pouch);
                player.field_71071_by.func_70296_d();
            } else {
                IInventory baubles = BaublesApi.getBaubles((EntityPlayer)player);
                baubles.func_70299_a(pouchslot + 4, pouch);
                baubles.func_70296_d();
            }
        }
        return lens;
    }

    private static boolean addLensToPouch(EntityPlayer player, ItemStack lens, HashMap<Integer, Integer> pouches) {
        Iterator<Integer> i$ = pouches.values().iterator();
        while (i$.hasNext()) {
            IInventory baubles = BaublesApi.getBaubles((EntityPlayer)player);
            Integer pouchslot = i$.next();
            ItemStack pouch = pouchslot >= 0 ? player.field_71071_by.field_70462_a[pouchslot] : baubles.func_70301_a(pouchslot + 4);
            ItemStack[] inv = ((ItemLensCase)pouch.func_77973_b()).getInventory(pouch);
            for (int q = 0; q < inv.length; ++q) {
                ItemStack contents = inv[q];
                if (contents != null) continue;
                inv[q] = lens.func_77946_l();
                ((ItemLensCase)pouch.func_77973_b()).setInventory(pouch, inv);
                if (pouchslot >= 0) {
                    player.field_71071_by.func_70299_a(pouchslot.intValue(), pouch);
                    player.field_71071_by.func_70296_d();
                } else {
                    baubles.func_70299_a(pouchslot + 4, pouch);
                    baubles.func_70296_d();
                }
                player.field_71071_by.func_70296_d();
                return true;
            }
        }
        return false;
    }

    public static ItemStack getLensItem(String lens) {
        if (LensManager.getLens(lens) != null) {
            return new ItemStack(LensManager.getLens(lens));
        }
        return null;
    }

    public static Item getLens(String lens) {
        if (lens.equals("LensFire")) {
            return ThaumicHorizons.itemLensFire;
        }
        if (lens.equals("LensWater")) {
            return ThaumicHorizons.itemLensWater;
        }
        if (lens.equals("LensEarth")) {
            return ThaumicHorizons.itemLensEarth;
        }
        if (lens.equals("LensAir")) {
            return ThaumicHorizons.itemLensAir;
        }
        if (lens.equals("LensOrderEntropy")) {
            return ThaumicHorizons.itemLensOrderEntropy;
        }
        return null;
    }

    public static void setLensItem(ItemStack goggles, ItemStack lens) {
        if (!goggles.func_77942_o()) {
            goggles.field_77990_d = new NBTTagCompound();
        }
        int lensIndex = goggles.field_77990_d.func_74762_e("LensIndex");
        NBTTagList lore = null;
        if (goggles.field_77990_d != null && goggles.field_77990_d.func_74775_l("display") != null) {
            lore = goggles.field_77990_d.func_74775_l("display").func_150295_c("Lore", 8);
        }
        if (lore == null || lore.func_74745_c() == 0) {
            if (goggles.field_77990_d == null) {
                goggles.field_77990_d = new NBTTagCompound();
            }
            if (goggles.field_77990_d.func_74775_l("display").func_82582_d()) {
                goggles.field_77990_d.func_74782_a("display", (NBTBase)new NBTTagCompound());
            }
            if (goggles.field_77990_d.func_74775_l("display").func_150295_c("Lore", 8).func_74745_c() == 0) {
                goggles.field_77990_d.func_74775_l("display").func_74782_a("Lore", (NBTBase)new NBTTagList());
            }
            lore = goggles.field_77990_d.func_74775_l("display").func_150295_c("Lore", 8);
            lensIndex = 0;
        }
        if (lens == null) {
            goggles.field_77990_d.func_82580_o("Lens");
            if (lensIndex >= 0 && lore.func_74745_c() > lensIndex) {
                lore.func_74744_a(lensIndex);
            }
            goggles.field_77990_d.func_74768_a("LensIndex", -1);
        } else {
            goggles.field_77990_d.func_82580_o("Lens");
            goggles.field_77990_d.func_74778_a("Lens", ((ILens)lens.func_77973_b()).lensName());
            if (lensIndex != -1 && lore.func_74745_c() > lensIndex) {
                lore.func_74744_a(lensIndex);
            }
            goggles.field_77990_d.func_74768_a("LensIndex", lore.func_74745_c());
            lore.func_74742_a((NBTBase)new NBTTagString(StatCollector.func_74838_a((String)("item." + ((ILens)lens.func_77973_b()).lensName() + ".name"))));
        }
    }
}

