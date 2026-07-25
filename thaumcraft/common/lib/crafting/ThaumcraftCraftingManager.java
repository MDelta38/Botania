/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemEnchantedBook
 *  net.minecraft.item.ItemHoe
 *  net.minecraft.item.ItemPickaxe
 *  net.minecraft.item.ItemPotion
 *  net.minecraft.item.ItemShears
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.item.ItemTool
 *  net.minecraft.item.crafting.CraftingManager
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.item.crafting.ShapedRecipes
 *  net.minecraft.item.crafting.ShapelessRecipes
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraftforge.oredict.ShapedOreRecipe
 *  net.minecraftforge.oredict.ShapelessOreRecipe
 */
package thaumcraft.common.lib.crafting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import thaumcraft.common.config.Config;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.utils.Utils;

public class ThaumcraftCraftingManager {
    public static ShapedRecipes createFakeRecipe(ItemStack par1ItemStack, Object ... par2ArrayOfObj) {
        int var9;
        String var3 = "";
        int var4 = 0;
        int var5 = 0;
        int var6 = 0;
        if (par2ArrayOfObj[var4] instanceof String[]) {
            String[] var7;
            String[] var8 = var7 = (String[])par2ArrayOfObj[var4++];
            var9 = var7.length;
            for (int var10 = 0; var10 < var9; ++var10) {
                String var11 = var8[var10];
                ++var6;
                var5 = var11.length();
                var3 = var3 + var11;
            }
        } else {
            while (par2ArrayOfObj[var4] instanceof String) {
                String var13 = (String)par2ArrayOfObj[var4++];
                ++var6;
                var5 = var13.length();
                var3 = var3 + var13;
            }
        }
        HashMap<Character, ItemStack> var14 = new HashMap<Character, ItemStack>();
        while (var4 < par2ArrayOfObj.length) {
            Character var16 = (Character)par2ArrayOfObj[var4];
            ItemStack var17 = null;
            if (par2ArrayOfObj[var4 + 1] instanceof Item) {
                var17 = new ItemStack((Item)par2ArrayOfObj[var4 + 1]);
            } else if (par2ArrayOfObj[var4 + 1] instanceof Block) {
                var17 = new ItemStack((Block)par2ArrayOfObj[var4 + 1]);
            } else if (par2ArrayOfObj[var4 + 1] instanceof ItemStack) {
                var17 = (ItemStack)par2ArrayOfObj[var4 + 1];
            }
            var14.put(var16, var17);
            var4 += 2;
        }
        ItemStack[] var15 = new ItemStack[var5 * var6];
        for (var9 = 0; var9 < var5 * var6; ++var9) {
            char var18 = var3.charAt(var9);
            var15[var9] = var14.containsKey(Character.valueOf(var18)) ? ((ItemStack)var14.get(Character.valueOf(var18))).func_77946_l() : null;
        }
        return new ShapedRecipes(var5, var6, var15, par1ItemStack);
    }

    public static CrucibleRecipe findMatchingCrucibleRecipe(String username, AspectList aspects, ItemStack lastDrop) {
        int highest = 0;
        int index = -1;
        for (int a = 0; a < ThaumcraftApi.getCraftingRecipes().size(); ++a) {
            int result;
            if (!(ThaumcraftApi.getCraftingRecipes().get(a) instanceof CrucibleRecipe)) continue;
            CrucibleRecipe recipe = (CrucibleRecipe)ThaumcraftApi.getCraftingRecipes().get(a);
            ItemStack temp = lastDrop.func_77946_l();
            temp.field_77994_a = 1;
            if (!ResearchManager.isResearchComplete(username, recipe.key) || !recipe.matches(aspects, temp) || (result = recipe.aspects.size()) <= highest) continue;
            highest = result;
            index = a;
        }
        if (index < 0) {
            return null;
        }
        AspectList output = new AspectList();
        return (CrucibleRecipe)ThaumcraftApi.getCraftingRecipes().get(index);
    }

    public static ItemStack findMatchingArcaneRecipe(IInventory awb, EntityPlayer player) {
        int var2 = 0;
        ItemStack var3 = null;
        ItemStack var4 = null;
        for (int var5 = 0; var5 < 9; ++var5) {
            ItemStack var6 = awb.func_70301_a(var5);
            if (var6 == null) continue;
            if (var2 == 0) {
                var3 = var6;
            }
            if (var2 == 1) {
                var4 = var6;
            }
            ++var2;
        }
        IArcaneRecipe var13 = null;
        for (Object var11 : ThaumcraftApi.getCraftingRecipes()) {
            if (!(var11 instanceof IArcaneRecipe) || !((IArcaneRecipe)var11).matches(awb, player.field_70170_p, player)) continue;
            var13 = (IArcaneRecipe)var11;
            break;
        }
        return var13 == null ? null : var13.getCraftingResult(awb);
    }

    public static AspectList findMatchingArcaneRecipeAspects(IInventory awb, EntityPlayer player) {
        int var2 = 0;
        ItemStack var3 = null;
        ItemStack var4 = null;
        for (int var5 = 0; var5 < 9; ++var5) {
            ItemStack var6 = awb.func_70301_a(var5);
            if (var6 == null) continue;
            if (var2 == 0) {
                var3 = var6;
            }
            if (var2 == 1) {
                var4 = var6;
            }
            ++var2;
        }
        IArcaneRecipe var13 = null;
        for (Object var11 : ThaumcraftApi.getCraftingRecipes()) {
            if (!(var11 instanceof IArcaneRecipe) || !((IArcaneRecipe)var11).matches(awb, player.field_70170_p, player)) continue;
            var13 = (IArcaneRecipe)var11;
            break;
        }
        return var13 == null ? new AspectList() : (var13.getAspects() != null ? var13.getAspects() : var13.getAspects(awb));
    }

    public static InfusionRecipe findMatchingInfusionRecipe(ArrayList<ItemStack> items, ItemStack input, EntityPlayer player) {
        InfusionRecipe var13 = null;
        for (Object var11 : ThaumcraftApi.getCraftingRecipes()) {
            if (!(var11 instanceof InfusionRecipe) || !((InfusionRecipe)var11).matches(items, input, player.field_70170_p, player)) continue;
            var13 = (InfusionRecipe)var11;
            break;
        }
        return var13;
    }

    public static InfusionEnchantmentRecipe findMatchingInfusionEnchantmentRecipe(ArrayList<ItemStack> items, ItemStack input, EntityPlayer player) {
        InfusionEnchantmentRecipe var13 = null;
        for (Object var11 : ThaumcraftApi.getCraftingRecipes()) {
            if (!(var11 instanceof InfusionEnchantmentRecipe) || !((InfusionEnchantmentRecipe)var11).matches(items, input, player.field_70170_p, player)) continue;
            var13 = (InfusionEnchantmentRecipe)var11;
            break;
        }
        return var13;
    }

    public static AspectList getObjectTags(ItemStack itemstack) {
        int meta;
        Item item;
        try {
            item = itemstack.func_77973_b();
            meta = itemstack.func_77960_j();
        }
        catch (Exception e) {
            return null;
        }
        AspectList tmp = ThaumcraftApi.objectTags.get(Arrays.asList(item, meta));
        if (tmp == null) {
            Set col = ThaumcraftApi.objectTags.keySet();
            for (List l : col) {
                if ((Item)l.get(0) != item || !(l.get(1) instanceof int[])) continue;
                int[] range = (int[])l.get(1);
                Arrays.sort(range);
                if (Arrays.binarySearch(range, meta) < 0) continue;
                tmp = ThaumcraftApi.objectTags.get(Arrays.asList(item, range));
                return tmp;
            }
            tmp = ThaumcraftApi.objectTags.get(Arrays.asList(item, Short.MAX_VALUE));
            if (tmp == null && tmp == null) {
                if (meta == Short.MAX_VALUE && tmp == null) {
                    int index = 0;
                    do {
                        tmp = ThaumcraftApi.objectTags.get(Arrays.asList(item, index));
                    } while (++index < 16 && tmp == null);
                }
                if (tmp == null) {
                    tmp = ThaumcraftCraftingManager.generateTags(item, meta);
                }
            }
        }
        if (itemstack.func_77973_b() instanceof ItemWandCasting) {
            ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
            if (tmp == null) {
                tmp = new AspectList();
            }
            tmp.merge(Aspect.MAGIC, (wand.getRod(itemstack).getCraftCost() + wand.getCap(itemstack).getCraftCost()) / 2);
            tmp.merge(Aspect.TOOL, (wand.getRod(itemstack).getCraftCost() + wand.getCap(itemstack).getCraftCost()) / 3);
        }
        if (item != null && item == Items.field_151068_bn) {
            if (tmp == null) {
                tmp = new AspectList();
            }
            tmp.merge(Aspect.WATER, 1);
            ItemPotion ip = (ItemPotion)item;
            List effects = ip.func_77834_f(itemstack.func_77960_j());
            if (effects != null) {
                if (ItemPotion.func_77831_g((int)itemstack.func_77960_j())) {
                    tmp.merge(Aspect.ENTROPY, 2);
                }
                for (PotionEffect var6 : effects) {
                    tmp.merge(Aspect.MAGIC, (var6.func_76458_c() + 1) * 2);
                    if (var6.func_76456_a() == Potion.field_76440_q.field_76415_H) {
                        tmp.merge(Aspect.DARKNESS, (var6.func_76458_c() + 1) * 3);
                        continue;
                    }
                    if (var6.func_76456_a() == Potion.field_76431_k.field_76415_H) {
                        tmp.merge(Aspect.ELDRITCH, (var6.func_76458_c() + 1) * 3);
                        continue;
                    }
                    if (var6.func_76456_a() == Potion.field_76420_g.field_76415_H) {
                        tmp.merge(Aspect.WEAPON, (var6.func_76458_c() + 1) * 3);
                        continue;
                    }
                    if (var6.func_76456_a() == Potion.field_76419_f.field_76415_H) {
                        tmp.merge(Aspect.TRAP, (var6.func_76458_c() + 1) * 3);
                        continue;
                    }
                    if (var6.func_76456_a() == Potion.field_76422_e.field_76415_H) {
                        tmp.merge(Aspect.TOOL, (var6.func_76458_c() + 1) * 3);
                        continue;
                    }
                    if (var6.func_76456_a() == Potion.field_76426_n.field_76415_H) {
                        tmp.merge(Aspect.ARMOR, var6.func_76458_c() + 1);
                        tmp.merge(Aspect.FIRE, (var6.func_76458_c() + 1) * 2);
                        continue;
                    }
                    if (var6.func_76456_a() == Potion.field_76433_i.field_76415_H) {
                        tmp.merge(Aspect.DEATH, (var6.func_76458_c() + 1) * 3);
                        continue;
                    }
                    if (var6.func_76456_a() == Potion.field_76432_h.field_76415_H) {
                        tmp.merge(Aspect.HEAL, (var6.func_76458_c() + 1) * 3);
                        continue;
                    }
                    if (var6.func_76456_a() == Potion.field_76438_s.field_76415_H) {
                        tmp.merge(Aspect.DEATH, (var6.func_76458_c() + 1) * 3);
                        continue;
                    }
                    if (var6.func_76456_a() == Potion.field_76441_p.field_76415_H) {
                        tmp.merge(Aspect.SENSES, (var6.func_76458_c() + 1) * 3);
                        continue;
                    }
                    if (var6.func_76456_a() == Potion.field_76430_j.field_76415_H) {
                        tmp.merge(Aspect.FLIGHT, (var6.func_76458_c() + 1) * 3);
                        continue;
                    }
                    if (var6.func_76456_a() == Potion.field_76421_d.field_76415_H) {
                        tmp.merge(Aspect.TRAP, (var6.func_76458_c() + 1) * 3);
                        continue;
                    }
                    if (var6.func_76456_a() == Potion.field_76424_c.field_76415_H) {
                        tmp.merge(Aspect.MOTION, (var6.func_76458_c() + 1) * 3);
                        continue;
                    }
                    if (var6.func_76456_a() == Potion.field_76439_r.field_76415_H) {
                        tmp.merge(Aspect.SENSES, (var6.func_76458_c() + 1) * 3);
                        continue;
                    }
                    if (var6.func_76456_a() == Potion.field_76436_u.field_76415_H) {
                        tmp.merge(Aspect.POISON, (var6.func_76458_c() + 1) * 3);
                        continue;
                    }
                    if (var6.func_76456_a() == Potion.field_76428_l.field_76415_H) {
                        tmp.merge(Aspect.HEAL, (var6.func_76458_c() + 1) * 3);
                        continue;
                    }
                    if (var6.func_76456_a() == Potion.field_76429_m.field_76415_H) {
                        tmp.merge(Aspect.ARMOR, (var6.func_76458_c() + 1) * 3);
                        continue;
                    }
                    if (var6.func_76456_a() == Potion.field_76427_o.field_76415_H) {
                        tmp.merge(Aspect.AIR, (var6.func_76458_c() + 1) * 3);
                        continue;
                    }
                    if (var6.func_76456_a() != Potion.field_76437_t.field_76415_H) continue;
                    tmp.merge(Aspect.DEATH, (var6.func_76458_c() + 1) * 3);
                }
            }
        }
        return ThaumcraftCraftingManager.capAspects(tmp, 64);
    }

    private static AspectList capAspects(AspectList sourcetags, int amount) {
        if (sourcetags == null) {
            return sourcetags;
        }
        AspectList out = new AspectList();
        for (Aspect aspect : sourcetags.getAspects()) {
            out.merge(aspect, Math.min(amount, sourcetags.getAmount(aspect)));
        }
        return out;
    }

    public static AspectList getBonusTags(ItemStack itemstack, AspectList sourcetags) {
        AspectList tmp = new AspectList();
        Item item = itemstack.func_77973_b();
        if (item != null && item instanceof IEssentiaContainerItem && (tmp = ((IEssentiaContainerItem)item).getAspects(itemstack)) != null && tmp.size() > 0) {
            for (Aspect tag : tmp.copy().getAspects()) {
                if (tmp.getAmount(tag) > 0) continue;
                tmp.remove(tag);
            }
        }
        if (tmp == null) {
            tmp = new AspectList();
        }
        if (sourcetags != null) {
            for (Aspect tag : sourcetags.getAspects()) {
                if (tag == null) continue;
                tmp.add(tag, sourcetags.getAmount(tag));
            }
        }
        int id = Item.func_150891_b((Item)itemstack.func_77973_b());
        int meta = itemstack.func_77960_j();
        if (item != null && (tmp != null || item == Items.field_151068_bn)) {
            if (item instanceof ItemArmor) {
                tmp.merge(Aspect.ARMOR, ((ItemArmor)item).field_77879_b);
            } else if (item instanceof ItemSword && ((ItemSword)item).func_150931_i() + 1.0f > 0.0f) {
                tmp.merge(Aspect.WEAPON, (int)(((ItemSword)item).func_150931_i() + 1.0f));
            } else if (item instanceof ItemBow) {
                tmp.merge(Aspect.WEAPON, 3).merge(Aspect.FLIGHT, 1);
            } else if (item instanceof ItemPickaxe) {
                String mat = ((ItemTool)item).func_77861_e();
                for (Item.ToolMaterial tm : Item.ToolMaterial.values()) {
                    if (!tm.toString().equals(mat)) continue;
                    tmp.merge(Aspect.MINE, tm.func_77996_d() + 1);
                }
            } else if (item instanceof ItemTool) {
                String mat = ((ItemTool)item).func_77861_e();
                for (Item.ToolMaterial tm : Item.ToolMaterial.values()) {
                    if (!tm.toString().equals(mat)) continue;
                    tmp.merge(Aspect.TOOL, tm.func_77996_d() + 1);
                }
            } else if (item instanceof ItemShears || item instanceof ItemHoe) {
                if (item.func_77612_l() <= Item.ToolMaterial.WOOD.func_77997_a()) {
                    tmp.merge(Aspect.HARVEST, 1);
                } else if (item.func_77612_l() <= Item.ToolMaterial.STONE.func_77997_a() || item.func_77612_l() <= Item.ToolMaterial.GOLD.func_77997_a()) {
                    tmp.merge(Aspect.HARVEST, 2);
                } else if (item.func_77612_l() <= Item.ToolMaterial.IRON.func_77997_a()) {
                    tmp.merge(Aspect.HARVEST, 3);
                } else {
                    tmp.merge(Aspect.HARVEST, 4);
                }
            }
            NBTTagList ench = itemstack.func_77986_q();
            if (item instanceof ItemEnchantedBook) {
                ench = ((ItemEnchantedBook)item).func_92110_g(itemstack);
            }
            if (ench != null) {
                int var5 = 0;
                for (int var3 = 0; var3 < ench.func_74745_c(); ++var3) {
                    short eid = ench.func_150305_b(var3).func_74765_d("id");
                    short lvl = ench.func_150305_b(var3).func_74765_d("lvl");
                    if (eid == Enchantment.field_77341_i.field_77352_x) {
                        tmp.merge(Aspect.WATER, lvl);
                    } else if (eid == Enchantment.field_77336_l.field_77352_x) {
                        tmp.merge(Aspect.BEAST, lvl);
                    } else if (eid == Enchantment.field_77327_f.field_77352_x) {
                        tmp.merge(Aspect.ARMOR, lvl);
                    } else if (eid == Enchantment.field_77349_p.field_77352_x) {
                        tmp.merge(Aspect.TOOL, lvl);
                    } else if (eid == Enchantment.field_77330_e.field_77352_x) {
                        tmp.merge(Aspect.FLIGHT, lvl);
                    } else if (eid == Enchantment.field_77334_n.field_77352_x) {
                        tmp.merge(Aspect.FIRE, lvl);
                    } else if (eid == Enchantment.field_77329_d.field_77352_x) {
                        tmp.merge(Aspect.ARMOR, lvl);
                    } else if (eid == Enchantment.field_77343_v.field_77352_x) {
                        tmp.merge(Aspect.FIRE, lvl);
                    } else if (eid == Enchantment.field_77346_s.field_77352_x) {
                        tmp.merge(Aspect.GREED, lvl);
                    } else if (eid == Enchantment.field_77342_w.field_77352_x) {
                        tmp.merge(Aspect.CRAFT, lvl);
                    } else if (eid == Enchantment.field_77337_m.field_77352_x) {
                        tmp.merge(Aspect.AIR, lvl);
                    } else if (eid == Enchantment.field_77335_o.field_77352_x) {
                        tmp.merge(Aspect.GREED, lvl);
                    } else if (eid == Enchantment.field_77345_t.field_77352_x) {
                        tmp.merge(Aspect.WEAPON, lvl);
                    } else if (eid == Enchantment.field_77328_g.field_77352_x) {
                        tmp.merge(Aspect.ARMOR, lvl);
                    } else if (eid == Enchantment.field_77332_c.field_77352_x) {
                        tmp.merge(Aspect.ARMOR, lvl);
                    } else if (eid == Enchantment.field_77344_u.field_77352_x) {
                        tmp.merge(Aspect.AIR, lvl);
                    } else if (eid == Enchantment.field_77340_h.field_77352_x) {
                        tmp.merge(Aspect.AIR, lvl);
                    } else if (eid == Enchantment.field_77338_j.field_77352_x) {
                        tmp.merge(Aspect.WEAPON, lvl);
                    } else if (eid == Enchantment.field_77348_q.field_77352_x) {
                        tmp.merge(Aspect.EXCHANGE, lvl);
                    } else if (eid == Enchantment.field_92091_k.field_77352_x) {
                        tmp.merge(Aspect.WEAPON, lvl);
                    } else if (eid == Enchantment.field_77339_k.field_77352_x) {
                        tmp.merge(Aspect.ENTROPY, lvl);
                    } else if (eid == Enchantment.field_77347_r.field_77352_x) {
                        tmp.merge(Aspect.EARTH, lvl);
                    } else if (eid == Enchantment.field_151370_z.field_77352_x) {
                        tmp.merge(Aspect.GREED, lvl);
                    } else if (eid == Enchantment.field_151369_A.field_77352_x) {
                        tmp.merge(Aspect.BEAST, lvl);
                    } else if (eid == Config.enchHaste.field_77352_x) {
                        tmp.merge(Aspect.MOTION, lvl);
                    } else if (eid == Config.enchRepair.field_77352_x) {
                        tmp.merge(Aspect.TOOL, lvl);
                    }
                    var5 += lvl;
                }
                if (var5 > 0) {
                    tmp.merge(Aspect.MAGIC, var5);
                }
            }
        }
        return ThaumcraftApiHelper.cullTags(tmp);
    }

    public static AspectList generateTags(Item item, int meta) {
        AspectList temp = ThaumcraftCraftingManager.generateTags(item, meta, new ArrayList<List>());
        return temp;
    }

    public static AspectList generateTags(Item item, int meta, ArrayList<List> history) {
        int tmeta = meta;
        try {
            tmeta = new ItemStack(item, 1, meta).func_77973_b().func_77645_m() || !new ItemStack(item, 1, meta).func_77973_b().func_77614_k() ? Short.MAX_VALUE : meta;
        }
        catch (Exception e) {
            // empty catch block
        }
        if (ThaumcraftApi.exists(item, tmeta)) {
            return ThaumcraftCraftingManager.getObjectTags(new ItemStack(item, 1, tmeta));
        }
        if (history.contains(Arrays.asList(item, tmeta))) {
            return null;
        }
        history.add(Arrays.asList(item, tmeta));
        if (history.size() >= 100) {
            return null;
        }
        AspectList ret = ThaumcraftCraftingManager.generateTagsFromRecipes(item, tmeta == Short.MAX_VALUE ? 0 : meta, history);
        ret = ThaumcraftCraftingManager.capAspects(ret, 64);
        ThaumcraftApi.registerObjectTag(new ItemStack(item, 1, tmeta), ret);
        return ret;
    }

    private static AspectList generateTagsFromCrucibleRecipes(Item item, int meta, ArrayList<List> history) {
        CrucibleRecipe cr = ThaumcraftApi.getCrucibleRecipe(new ItemStack(item, 1, meta));
        if (cr != null) {
            AspectList ot = cr.aspects.copy();
            int ss = cr.getRecipeOutput().field_77994_a;
            ItemStack cat = null;
            if (cr.catalyst instanceof ItemStack) {
                cat = (ItemStack)cr.catalyst;
            } else if (cr.catalyst instanceof ArrayList && ((ArrayList)cr.catalyst).size() > 0) {
                cat = (ItemStack)((ArrayList)cr.catalyst).get(0);
            }
            AspectList ot2 = ThaumcraftCraftingManager.generateTags(cat.func_77973_b(), cat.func_77960_j());
            AspectList out = new AspectList();
            if (ot2 != null && ot2.size() > 0) {
                for (Aspect tt : ot2.getAspects()) {
                    out.add(tt, ot2.getAmount(tt));
                }
            }
            for (Aspect tt : ot.getAspects()) {
                int amt = (int)(Math.sqrt(ot.getAmount(tt)) / (double)ss);
                out.add(tt, amt);
            }
            for (Aspect as : out.getAspects()) {
                if (out.getAmount(as) > 0) continue;
                out.remove(as);
            }
            return out;
        }
        return null;
    }

    private static AspectList generateTagsFromArcaneRecipes(Item item, int meta, ArrayList<List> history) {
        AspectList ret = null;
        boolean value = false;
        List recipeList = ThaumcraftApi.getCraftingRecipes();
        block2: for (int q = 0; q < recipeList.size(); ++q) {
            int idS;
            IArcaneRecipe recipe;
            if (!(recipeList.get(q) instanceof IArcaneRecipe) || (recipe = (IArcaneRecipe)recipeList.get(q)).getRecipeOutput() == null) continue;
            int idR = recipe.getRecipeOutput().func_77960_j() == Short.MAX_VALUE ? 0 : recipe.getRecipeOutput().func_77960_j();
            int n = idS = meta < 0 ? 0 : meta;
            if (recipe.getRecipeOutput().func_77973_b() != item || idR != idS) continue;
            ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
            AspectList ph = new AspectList();
            boolean cval = false;
            try {
                if (recipeList.get(q) instanceof ShapedArcaneRecipe) {
                    int width = ((ShapedArcaneRecipe)recipeList.get((int)q)).width;
                    int height = ((ShapedArcaneRecipe)recipeList.get((int)q)).height;
                    Object[] items = ((ShapedArcaneRecipe)recipeList.get(q)).getInput();
                    for (int i = 0; i < width && i < 3; ++i) {
                        block4: for (int j = 0; j < height && j < 3; ++j) {
                            if (items[i + j * width] == null) continue;
                            if (items[i + j * width] instanceof ArrayList) {
                                for (ItemStack it : (ArrayList)items[i + j * width]) {
                                    if (Utils.isEETransmutionItem(it.func_77973_b())) continue block2;
                                    AspectList obj = ThaumcraftCraftingManager.generateTags(it.func_77973_b(), it.func_77960_j(), history);
                                    if (obj == null || obj.size() <= 0) continue;
                                    ItemStack is = it.func_77946_l();
                                    is.field_77994_a = 1;
                                    ingredients.add(is);
                                    continue block4;
                                }
                                continue;
                            }
                            ItemStack it = (ItemStack)items[i + j * width];
                            if (Utils.isEETransmutionItem(it.func_77973_b())) continue block2;
                            ItemStack is = it.func_77946_l();
                            is.field_77994_a = 1;
                            ingredients.add(is);
                        }
                    }
                } else if (recipeList.get(q) instanceof ShapelessArcaneRecipe) {
                    ArrayList items = ((ShapelessArcaneRecipe)recipeList.get(q)).getInput();
                    block6: for (int i = 0; i < items.size() && i < 9; ++i) {
                        if (items.get(i) == null) continue;
                        if (items.get(i) instanceof ArrayList) {
                            for (ItemStack it : (ArrayList)items.get(i)) {
                                if (Utils.isEETransmutionItem(it.func_77973_b())) continue block2;
                                AspectList obj = ThaumcraftCraftingManager.generateTags(it.func_77973_b(), it.func_77960_j(), history);
                                if (obj == null || obj.size() <= 0) continue;
                                ItemStack is = it.func_77946_l();
                                is.field_77994_a = 1;
                                ingredients.add(is);
                                continue block6;
                            }
                            continue;
                        }
                        ItemStack it = (ItemStack)items.get(i);
                        if (Utils.isEETransmutionItem(it.func_77973_b())) continue block2;
                        ItemStack is = it.func_77946_l();
                        is.field_77994_a = 1;
                        ingredients.add(is);
                    }
                }
                ph = ThaumcraftCraftingManager.getAspectsFromIngredients(ingredients, recipe.getRecipeOutput(), history);
                if (recipe.getAspects() != null) {
                    for (Aspect a : recipe.getAspects().getAspects()) {
                        ph.add(a, (int)(Math.sqrt(recipe.getAspects().getAmount(a)) / (double)recipe.getRecipeOutput().field_77994_a));
                    }
                }
                for (Aspect as : ph.copy().getAspects()) {
                    if (ph.getAmount(as) > 0) continue;
                    ph.remove(as);
                }
                if (cval < value) continue;
                ret = ph;
                value = cval;
                continue;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    private static AspectList generateTagsFromInfusionRecipes(Item item, int meta, ArrayList<List> history) {
        InfusionRecipe cr = ThaumcraftApi.getInfusionRecipe(new ItemStack(item, 1, meta));
        if (cr != null) {
            AspectList ot = cr.getAspects().copy();
            ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
            ItemStack is = cr.getRecipeInput().func_77946_l();
            is.field_77994_a = 1;
            ingredients.add(is);
            for (ItemStack cat : cr.getComponents()) {
                ItemStack is2 = cat.func_77946_l();
                is2.field_77994_a = 1;
                ingredients.add(is2);
            }
            AspectList out = new AspectList();
            AspectList ot2 = ThaumcraftCraftingManager.getAspectsFromIngredients(ingredients, (ItemStack)cr.getRecipeOutput(), history);
            for (Aspect tt : ot2.getAspects()) {
                out.add(tt, ot2.getAmount(tt));
            }
            for (Aspect tt : ot.getAspects()) {
                int amt = (int)(Math.sqrt(ot.getAmount(tt)) / (double)((ItemStack)cr.getRecipeOutput()).field_77994_a);
                out.add(tt, amt);
            }
            for (Aspect as : out.getAspects()) {
                if (out.getAmount(as) > 0) continue;
                out.remove(as);
            }
            return out;
        }
        return null;
    }

    private static AspectList generateTagsFromCraftingRecipes(Item item, int meta, ArrayList<List> history) {
        AspectList ret = null;
        int value = Integer.MAX_VALUE;
        List recipeList = CraftingManager.func_77594_a().func_77592_b();
        block2: for (int q = 0; q < recipeList.size(); ++q) {
            int idS;
            IRecipe recipe = (IRecipe)recipeList.get(q);
            if (recipe == null || recipe.func_77571_b() == null || Item.func_150891_b((Item)recipe.func_77571_b().func_77973_b()) <= 0 || recipe.func_77571_b().func_77973_b() == null) continue;
            int idR = recipe.func_77571_b().func_77960_j() == Short.MAX_VALUE ? 0 : recipe.func_77571_b().func_77960_j();
            int n = idS = meta == Short.MAX_VALUE ? 0 : meta;
            if (recipe.func_77571_b().func_77973_b() != item || idR != idS) continue;
            ArrayList<ItemStack> ingredients = new ArrayList<ItemStack>();
            AspectList ph = new AspectList();
            boolean cval = false;
            try {
                ItemStack is;
                if (recipeList.get(q) instanceof ShapedRecipes) {
                    int width = ((ShapedRecipes)recipeList.get((int)q)).field_77576_b;
                    int height = ((ShapedRecipes)recipeList.get((int)q)).field_77577_c;
                    ItemStack[] items = ((ShapedRecipes)recipeList.get((int)q)).field_77574_d;
                    for (int i = 0; i < width && i < 3; ++i) {
                        for (int j = 0; j < height && j < 3; ++j) {
                            if (items[i + j * width] == null) continue;
                            if (Utils.isEETransmutionItem(items[i + j * width].func_77973_b())) continue block2;
                            is = items[i + j * width].func_77946_l();
                            is.field_77994_a = 1;
                            ingredients.add(is);
                        }
                    }
                } else if (recipeList.get(q) instanceof ShapelessRecipes) {
                    List items = ((ShapelessRecipes)recipeList.get((int)q)).field_77579_b;
                    for (int i = 0; i < items.size() && i < 9; ++i) {
                        if (items.get(i) == null) continue;
                        if (Utils.isEETransmutionItem(((ItemStack)items.get(i)).func_77973_b())) continue block2;
                        ItemStack is2 = ((ItemStack)items.get(i)).func_77946_l();
                        is2.field_77994_a = 1;
                        ingredients.add(is2);
                    }
                } else if (recipeList.get(q) instanceof ShapedOreRecipe) {
                    int size = ((ShapedOreRecipe)recipeList.get(q)).func_77570_a();
                    Object[] items = ((ShapedOreRecipe)recipeList.get(q)).getInput();
                    block6: for (int i = 0; i < size && i < 9; ++i) {
                        if (items[i] == null) continue;
                        if (items[i] instanceof ArrayList) {
                            for (ItemStack it : (ArrayList)items[i]) {
                                if (Utils.isEETransmutionItem(it.func_77973_b())) continue block2;
                                AspectList obj = ThaumcraftCraftingManager.generateTags(it.func_77973_b(), it.func_77960_j(), history);
                                if (obj == null || obj.size() <= 0) continue;
                                ItemStack is3 = it.func_77946_l();
                                is3.field_77994_a = 1;
                                ingredients.add(is3);
                                continue block6;
                            }
                            continue;
                        }
                        ItemStack it = (ItemStack)items[i];
                        if (Utils.isEETransmutionItem(it.func_77973_b())) continue block2;
                        ItemStack is4 = it.func_77946_l();
                        is4.field_77994_a = 1;
                        ingredients.add(is4);
                    }
                } else if (recipeList.get(q) instanceof ShapelessOreRecipe) {
                    ArrayList items = ((ShapelessOreRecipe)recipeList.get(q)).getInput();
                    block8: for (int i = 0; i < items.size() && i < 9; ++i) {
                        if (items.get(i) == null) continue;
                        if (items.get(i) instanceof ArrayList) {
                            for (ItemStack it : (ArrayList)items.get(i)) {
                                if (Utils.isEETransmutionItem(it.func_77973_b())) continue block2;
                                AspectList obj = ThaumcraftCraftingManager.generateTags(it.func_77973_b(), it.func_77960_j(), history);
                                if (obj == null || obj.size() <= 0) continue;
                                is = it.func_77946_l();
                                is.field_77994_a = 1;
                                ingredients.add(is);
                                continue block8;
                            }
                            continue;
                        }
                        ItemStack it = (ItemStack)items.get(i);
                        if (Utils.isEETransmutionItem(it.func_77973_b())) continue block2;
                        ItemStack is5 = it.func_77946_l();
                        is5.field_77994_a = 1;
                        ingredients.add(is5);
                    }
                }
                ph = ThaumcraftCraftingManager.getAspectsFromIngredients(ingredients, recipe.func_77571_b(), history);
                for (Aspect as : ph.copy().getAspects()) {
                    if (ph.getAmount(as) > 0) continue;
                    ph.remove(as);
                }
                if (ph.visSize() >= value || ph.visSize() <= 0) continue;
                ret = ph;
                value = ph.visSize();
                continue;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    private static AspectList getAspectsFromIngredients(ArrayList<ItemStack> ingredients, ItemStack recipeOut, ArrayList<List> history) {
        AspectList out = new AspectList();
        AspectList mid = new AspectList();
        for (ItemStack is : ingredients) {
            AspectList obj = ThaumcraftCraftingManager.generateTags(is.func_77973_b(), is.func_77960_j(), history);
            if (is.func_77973_b().func_77668_q() != null) {
                if (is.func_77973_b().func_77668_q() == is.func_77973_b()) continue;
                AspectList objC = ThaumcraftCraftingManager.generateTags(is.func_77973_b().func_77668_q(), Short.MAX_VALUE, history);
                for (Aspect as : objC.getAspects()) {
                    out.reduce(as, objC.getAmount(as));
                }
            }
            if (obj == null) continue;
            for (Aspect as : obj.getAspects()) {
                if (as == null) continue;
                mid.add(as, obj.getAmount(as));
            }
        }
        for (Aspect as : mid.getAspects()) {
            if (as == null) continue;
            out.add(as, (int)((float)mid.getAmount(as) * 0.75f / (float)recipeOut.field_77994_a));
        }
        for (Aspect as : out.getAspects()) {
            if (out.getAmount(as) > 0) continue;
            out.remove(as);
        }
        return out;
    }

    private static AspectList generateTagsFromRecipes(Item item, int meta, ArrayList<List> history) {
        AspectList ret = null;
        boolean value = false;
        ret = ThaumcraftCraftingManager.generateTagsFromCrucibleRecipes(item, meta, history);
        if (ret != null) {
            return ret;
        }
        ret = ThaumcraftCraftingManager.generateTagsFromArcaneRecipes(item, meta, history);
        if (ret != null) {
            return ret;
        }
        ret = ThaumcraftCraftingManager.generateTagsFromInfusionRecipes(item, meta, history);
        if (ret != null) {
            return ret;
        }
        ret = ThaumcraftCraftingManager.generateTagsFromCraftingRecipes(item, meta, history);
        return ret;
    }
}

