/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.ObfuscationReflectionHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.FoodStats
 *  net.minecraft.world.World
 *  thaumcraft.common.config.ConfigItems
 */
package flaxbeard.thaumicexploration.item;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import flaxbeard.thaumicexploration.interop.AppleCoreInterop;
import flaxbeard.thaumicexploration.misc.FakePlayerPotion;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.util.FoodStats;
import net.minecraft.world.World;
import thaumcraft.common.config.ConfigItems;

public class ItemFoodTalisman
extends Item {
    public static List<String> foodBlacklist = new ArrayList<String>();
    public static Map<String, Boolean> foodCache = new HashMap<String, Boolean>();

    public ItemFoodTalisman(int par1) {
        this.field_77777_bU = 1;
        foodBlacklist.add(ConfigItems.itemManaBean.func_77658_a());
        foodBlacklist.add(ConfigItems.itemZombieBrain.func_77658_a());
        foodBlacklist.add("item.foodstuff.0.name");
    }

    public void func_77624_a(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        if (!par1ItemStack.func_77942_o()) {
            par1ItemStack.func_77982_d(new NBTTagCompound());
        }
        if (!par1ItemStack.field_77990_d.func_74764_b("saturation")) {
            par1ItemStack.field_77990_d.func_74776_a("saturation", 0.0f);
        }
        if (!par1ItemStack.field_77990_d.func_74764_b("food")) {
            par1ItemStack.field_77990_d.func_74776_a("food", 0.0f);
        }
        par3List.add("Currently holds " + (int)par1ItemStack.field_77990_d.func_74760_g("food") + " food points and " + (int)par1ItemStack.field_77990_d.func_74760_g("saturation") + " saturation points.");
    }

    public void func_77663_a(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        if (par3Entity instanceof EntityPlayer && !par2World.field_72995_K && par3Entity.field_70173_aa % 20 == 0) {
            EntityPlayer player = (EntityPlayer)par3Entity;
            if (!par1ItemStack.func_77942_o()) {
                par1ItemStack.func_77982_d(new NBTTagCompound());
            }
            if (!par1ItemStack.field_77990_d.func_74764_b("saturation")) {
                par1ItemStack.field_77990_d.func_74776_a("saturation", 0.0f);
            }
            if (!par1ItemStack.field_77990_d.func_74764_b("food")) {
                par1ItemStack.field_77990_d.func_74776_a("food", 0.0f);
            }
            for (int i = 0; i < 10; ++i) {
                float heal;
                float sat;
                ItemStack food;
                if (player.field_71071_by.func_70301_a(i) == null || !this.isEdible(food = player.field_71071_by.func_70301_a(i), player)) continue;
                if (Loader.isModLoaded((String)"AppleCore")) {
                    sat = AppleCoreInterop.getSaturation(food) * 2.0f;
                    heal = AppleCoreInterop.getHeal(food);
                } else {
                    sat = ((ItemFood)food.func_77973_b()).func_150906_h(food) * 2.0f;
                    heal = ((ItemFood)food.func_77973_b()).func_150905_g(food);
                }
                if (!(par1ItemStack.field_77990_d.func_74760_g("food") + (float)((int)heal) < 100.0f)) continue;
                if (par1ItemStack.field_77990_d.func_74760_g("saturation") + sat <= 100.0f) {
                    par1ItemStack.field_77990_d.func_74776_a("saturation", par1ItemStack.field_77990_d.func_74760_g("saturation") + sat);
                } else {
                    par1ItemStack.field_77990_d.func_74776_a("saturation", 100.0f);
                }
                if (food.field_77994_a <= 1) {
                    player.field_71071_by.func_70299_a(i, null);
                }
                player.field_71071_by.func_70298_a(i, 1);
                player.func_85030_a("random.eat", 0.5f + 0.5f * (float)player.field_70170_p.field_73012_v.nextInt(2), (player.field_70170_p.field_73012_v.nextFloat() - player.field_70170_p.field_73012_v.nextFloat()) * 0.2f + 1.0f);
                par1ItemStack.field_77990_d.func_74776_a("food", par1ItemStack.field_77990_d.func_74760_g("food") + (float)((int)heal));
            }
            if (player.func_71024_bL().func_75116_a() < 20 && 100.0f - par1ItemStack.field_77990_d.func_74760_g("food") > 0.0f) {
                float sat = par1ItemStack.field_77990_d.func_74760_g("food");
                float finalSat = 0.0f;
                if ((float)(20 - player.func_71024_bL().func_75116_a()) < sat) {
                    finalSat = sat - (float)(20 - player.func_71024_bL().func_75116_a());
                    sat = 20 - player.func_71024_bL().func_75116_a();
                }
                if (Loader.isModLoaded((String)"AppleCore")) {
                    AppleCoreInterop.setHunger((int)sat, player);
                } else {
                    ObfuscationReflectionHelper.setPrivateValue(FoodStats.class, (Object)player.func_71024_bL(), (Object)((int)((float)player.func_71024_bL().func_75116_a() + sat)), (String[])new String[]{"field_75127_a", "foodLevel"});
                }
                par1ItemStack.field_77990_d.func_74776_a("food", finalSat);
                par1ItemStack.func_77964_b(par1ItemStack.func_77960_j());
            }
            if (player.func_71024_bL().func_75115_e() < (float)player.func_71024_bL().func_75116_a() && par1ItemStack.field_77990_d.func_74760_g("saturation") > 0.0f) {
                float sat = par1ItemStack.field_77990_d.func_74760_g("saturation");
                float finalSat = 0.0f;
                if ((float)player.func_71024_bL().func_75116_a() - player.func_71024_bL().func_75115_e() < sat) {
                    finalSat = sat - ((float)player.func_71024_bL().func_75116_a() - player.func_71024_bL().func_75115_e());
                    sat = (float)player.func_71024_bL().func_75116_a() - player.func_71024_bL().func_75115_e();
                }
                if (Loader.isModLoaded((String)"AppleCore")) {
                    AppleCoreInterop.setSaturation(sat, player);
                } else {
                    ObfuscationReflectionHelper.setPrivateValue(FoodStats.class, (Object)player.func_71024_bL(), (Object)Float.valueOf((float)player.func_71024_bL().func_75116_a() + sat), (String[])new String[]{"field_75125_b", "foodSaturationLevel"});
                }
                par1ItemStack.field_77990_d.func_74776_a("saturation", finalSat);
                par1ItemStack.func_77964_b(par1ItemStack.func_77960_j());
            }
        }
    }

    private boolean isEdible(ItemStack food, EntityPlayer player) {
        String foodName = food.func_77977_a();
        if (foodCache.containsKey(foodName.toLowerCase())) {
            return foodCache.get(foodName.toLowerCase());
        }
        for (String item : foodBlacklist) {
            if (!item.equalsIgnoreCase(foodName)) continue;
            foodCache.put(foodName.toLowerCase(), false);
            return false;
        }
        if (food.func_77973_b() instanceof ItemFood) {
            try {
                for (int i = 1; i < 25; ++i) {
                    FakePlayerPotion fakePlayer = new FakePlayerPotion(player.field_70170_p, new GameProfile(null, "foodTabletPlayer"));
                    fakePlayer.func_70107_b(0.0, 999.0, 0.0);
                    ((ItemFood)food.func_77973_b()).func_77654_b(food.func_77946_l(), player.field_70170_p, (EntityPlayer)fakePlayer);
                    if (Loader.isModLoaded((String)"HungerOverhaul")) {
                        Class<?> clazz;
                        Field fields;
                        Potion effect;
                        if (fakePlayer.func_70651_bq().size() > 1) {
                            foodCache.put(foodName.toLowerCase(), false);
                            return false;
                        }
                        if (fakePlayer.func_70651_bq().size() != 1 || (effect = (Potion)(fields = (clazz = Class.forName("iguanaman.hungeroverhaul.HungerOverhaul")).getField("potionWellFed")).get(null)) == null || fakePlayer.func_70660_b(effect) != null) continue;
                        foodCache.put(foodName.toLowerCase(), false);
                        return false;
                    }
                    if (fakePlayer.func_70651_bq().size() <= 0) continue;
                    foodCache.put(foodName.toLowerCase(), false);
                    return false;
                }
                foodCache.put(foodName.toLowerCase(), true);
                return true;
            }
            catch (Exception e) {
                foodCache.put(foodName.toLowerCase(), false);
                return false;
            }
        }
        foodCache.put(foodName.toLowerCase(), false);
        return false;
    }
}

