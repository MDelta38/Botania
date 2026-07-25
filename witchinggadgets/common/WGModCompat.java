/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.fluids.FluidRegistry
 *  net.minecraftforge.fluids.FluidStack
 *  net.minecraftforge.oredict.OreDictionary
 *  thaumcraft.api.ThaumcraftApi
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 */
package witchinggadgets.common;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import witchinggadgets.common.minetweaker.WGMinetweaker;

public class WGModCompat {
    public static Item tConResource;
    public static Item tfRavensFeather;
    public static Item tfMagicMapFocus;
    public static Block tfTowerWood;
    public static boolean loaded_TCon;
    public static boolean loaded_Twilight;
    public static boolean loaded_Enviromine;
    public static boolean loaded_Railcraft;
    public static boolean loaded_TT;
    static Class smeltery;
    static Method addMelting;
    static Class dryingRack;
    static Method addDryingRecipe;
    static Class enviro_DataTracker;
    static Method enviro_lookupTracker;
    static Field enviro_temperatue;
    static Field enviro_hydration;
    static Field enviro_sanity;
    static Method enviro_dehydrate;
    static final float SANITYBUFF = 0.02f;
    static Method railcraft_isSubBlockEnabled;
    static Method thaumtink_registerExponentialCostData;

    public static void init() {
        tfRavensFeather = GameRegistry.findItem((String)"TwilightForest", (String)"item.tfFeather");
        tfMagicMapFocus = GameRegistry.findItem((String)"TwilightForest", (String)"item.magicMapFocus");
        tfTowerWood = GameRegistry.findBlock((String)"TwilightForest", (String)"tile.TFTowerStone");
        tConResource = GameRegistry.findItem((String)"TConstruct", (String)"materials");
        loaded_TCon = Loader.isModLoaded((String)"TConstruct");
        loaded_Twilight = Loader.isModLoaded((String)"TwilightForest");
        loaded_Enviromine = Loader.isModLoaded((String)"enviromine");
        loaded_Railcraft = Loader.isModLoaded((String)"Railcraft");
        loaded_TT = Loader.isModLoaded((String)"ThaumicTinkerer");
        if (Loader.isModLoaded((String)"MineTweaker3")) {
            WGMinetweaker.init();
        }
    }

    public static void addTags() {
        WGModCompat.registerOreDictAspects("nuggetAluminum", new AspectList().add(Aspect.METAL, 1));
        WGModCompat.registerOreDictAspects("ingotAluminum", new AspectList().add(Aspect.METAL, 3).add(Aspect.EXCHANGE, 1));
        WGModCompat.registerOreDictAspects("dustAluminum", new AspectList().add(Aspect.METAL, 2).add(Aspect.ENTROPY, 1).add(Aspect.EXCHANGE, 1));
        WGModCompat.registerOreDictAspects("oreAluminum", new AspectList().add(Aspect.METAL, 2).add(Aspect.EARTH, 1).add(Aspect.EXCHANGE, 1));
        WGModCompat.registerOreDictAspects("nuggetAluminium", new AspectList().add(Aspect.METAL, 1));
        WGModCompat.registerOreDictAspects("ingotAluminium", new AspectList().add(Aspect.METAL, 3).add(Aspect.EXCHANGE, 1));
        WGModCompat.registerOreDictAspects("dustAluminium", new AspectList().add(Aspect.METAL, 2).add(Aspect.ENTROPY, 1).add(Aspect.EXCHANGE, 1));
        WGModCompat.registerOreDictAspects("oreAluminium", new AspectList().add(Aspect.METAL, 2).add(Aspect.EARTH, 1).add(Aspect.EXCHANGE, 1));
        WGModCompat.registerOreDictAspects("nuggetAluminumBrass", new AspectList().add(Aspect.METAL, 1));
        WGModCompat.registerOreDictAspects("ingotAluminumBrass", new AspectList().add(Aspect.METAL, 3).add(Aspect.EXCHANGE, 2));
        WGModCompat.registerOreDictAspects("dustAluminumBrass", new AspectList().add(Aspect.METAL, 2).add(Aspect.ENTROPY, 1).add(Aspect.EXCHANGE, 2));
        WGModCompat.registerOreDictAspects("oreAluminumBrass", new AspectList().add(Aspect.METAL, 2).add(Aspect.EARTH, 1).add(Aspect.EXCHANGE, 2));
        WGModCompat.registerOreDictAspects("nuggetAluminiumBrass", new AspectList().add(Aspect.METAL, 1));
        WGModCompat.registerOreDictAspects("ingotAluminiumBrass", new AspectList().add(Aspect.METAL, 3).add(Aspect.EXCHANGE, 2));
        WGModCompat.registerOreDictAspects("dustAluminiumBrass", new AspectList().add(Aspect.METAL, 2).add(Aspect.ENTROPY, 1).add(Aspect.EXCHANGE, 2));
        WGModCompat.registerOreDictAspects("oreAluminiumBrass", new AspectList().add(Aspect.METAL, 2).add(Aspect.EARTH, 1).add(Aspect.EXCHANGE, 2));
        WGModCompat.registerOreDictAspects("nuggetCobalt", new AspectList().add(Aspect.METAL, 1));
        WGModCompat.registerOreDictAspects("ingotCobalt", new AspectList().add(Aspect.METAL, 3).add(Aspect.FIRE, 1).add(Aspect.MOTION, 1));
        WGModCompat.registerOreDictAspects("dustCobalt", new AspectList().add(Aspect.METAL, 2).add(Aspect.ENTROPY, 1).add(Aspect.FIRE, 1).add(Aspect.MOTION, 1));
        WGModCompat.registerOreDictAspects("oreCobalt", new AspectList().add(Aspect.METAL, 2).add(Aspect.EARTH, 1).add(Aspect.FIRE, 1).add(Aspect.MOTION, 1));
        WGModCompat.registerOreDictAspects("nuggetArdite", new AspectList().add(Aspect.METAL, 1));
        WGModCompat.registerOreDictAspects("ingotArdite", new AspectList().add(Aspect.METAL, 3).add(Aspect.FIRE, 1).add(Aspect.EARTH, 1));
        WGModCompat.registerOreDictAspects("dustArdite", new AspectList().add(Aspect.METAL, 2).add(Aspect.ENTROPY, 1).add(Aspect.FIRE, 1).add(Aspect.EARTH, 1));
        WGModCompat.registerOreDictAspects("oreArdite", new AspectList().add(Aspect.METAL, 2).add(Aspect.EARTH, 1).add(Aspect.FIRE, 1).add(Aspect.EARTH, 1));
        WGModCompat.registerOreDictAspects("nuggetManyullyn", new AspectList().add(Aspect.METAL, 1));
        WGModCompat.registerOreDictAspects("ingotManyullyn", new AspectList().add(Aspect.METAL, 3).add(Aspect.FIRE, 2).add(Aspect.MAGIC, 2));
        WGModCompat.registerOreDictAspects("dustManyullyn", new AspectList().add(Aspect.METAL, 2).add(Aspect.ENTROPY, 1).add(Aspect.FIRE, 1).add(Aspect.MAGIC, 1));
        WGModCompat.registerOreDictAspects("nuggetPigIron", new AspectList().add(Aspect.METAL, 1));
        WGModCompat.registerOreDictAspects("ingotPigIron", new AspectList().add(Aspect.METAL, 3).add(Aspect.FLESH, 1));
        if (tConResource != null) {
            ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack(tConResource, 1, 8), (AspectList)new AspectList().add(Aspect.DEATH, 4).add(Aspect.UNDEAD, 2).add(Aspect.HUNGER, 2));
            ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack(tConResource, 1, 8), (AspectList)new AspectList().add(Aspect.DEATH, 4).add(Aspect.UNDEAD, 2).add(Aspect.HUNGER, 2));
        }
    }

    private static void registerOreDictAspects(String oreName, AspectList aspects) {
        if (!OreDictionary.getOres((String)oreName).isEmpty()) {
            ThaumcraftApi.registerObjectTag((String)oreName, (AspectList)aspects);
        }
    }

    public static void addTConSmelteryRecipe(String oreName, String blockName, int temperature, String fluidName, int fluidAmount) {
        if (!OreDictionary.getOres((String)blockName).isEmpty()) {
            ItemStack blockStack = (ItemStack)OreDictionary.getOres((String)blockName).get(0);
            if (blockStack == null || Block.func_149634_a((Item)blockStack.func_77973_b()) == null) {
                blockStack = new ItemStack(Blocks.field_150339_S);
            }
            Block b = Block.func_149634_a((Item)blockStack.func_77973_b());
            if (!OreDictionary.getOres((String)oreName).isEmpty()) {
                for (ItemStack oreStack : OreDictionary.getOres((String)oreName)) {
                    if (oreStack == null) continue;
                    WGModCompat.addTConSmelteryRecipe(oreStack, b, blockStack.func_77960_j(), temperature, fluidName, fluidAmount);
                }
            }
        }
    }

    public static void addTConSmelteryRecipe(ItemStack ore, Block block, int blockMeta, int temperature, String fluidName, int fluidAmount) {
        if (!loaded_TCon || FluidRegistry.getFluid((String)fluidName) == null) {
            return;
        }
        try {
            FluidStack fluid = new FluidStack(FluidRegistry.getFluid((String)fluidName), fluidAmount);
            if (smeltery == null) {
                smeltery = Class.forName("tconstruct.library.crafting.Smeltery");
            }
            if (addMelting == null) {
                addMelting = smeltery.getDeclaredMethod("addMelting", ItemStack.class, Block.class, Integer.TYPE, Integer.TYPE, FluidStack.class);
            }
            addMelting.invoke(null, ore, block, blockMeta, temperature, fluid);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addTConDryingRecipe(Object input, int time, Object output) {
        if (!loaded_TCon) {
            return;
        }
        try {
            if (dryingRack == null) {
                dryingRack = Class.forName("tconstruct.library.crafting.DryingRackRecipes");
            }
            if (addDryingRecipe == null) {
                addDryingRecipe = dryingRack.getDeclaredMethod("addDryingRecipe", Object.class, Integer.TYPE, Object.class);
            }
            addDryingRecipe.invoke(null, input, time, output);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void enviromineDoSaunaStuff(EntityLivingBase player, float deh, float temp) {
        if (!loaded_Enviromine) {
            return;
        }
        try {
            float curSane;
            if (enviro_DataTracker == null) {
                enviro_DataTracker = Class.forName("enviromine.trackers.EnviroDataTracker");
            }
            if (enviro_lookupTracker == null) {
                Class<?> c_EM_StatusManager = Class.forName("enviromine.handlers.EM_StatusManager");
                enviro_lookupTracker = c_EM_StatusManager.getDeclaredMethod("lookupTracker", EntityLivingBase.class);
            }
            if (enviro_temperatue == null) {
                enviro_temperatue = enviro_DataTracker.getField("bodyTemp");
            }
            if (enviro_hydration == null) {
                enviro_hydration = enviro_DataTracker.getField("hydration");
            }
            if (enviro_sanity == null) {
                enviro_sanity = enviro_DataTracker.getField("sanity");
            }
            if (enviro_dehydrate == null) {
                enviro_dehydrate = enviro_DataTracker.getMethod("dehydrate", Float.TYPE);
            }
            Object[] objectArray = new Object[]{player};
            Object tracker = enviro_lookupTracker.invoke(null, objectArray);
            float curTemp = enviro_temperatue.getFloat(tracker);
            if (curTemp + temp < 37.5f) {
                enviro_temperatue.set(tracker, Float.valueOf(curTemp + temp));
            }
            if ((curSane = enviro_sanity.getFloat(tracker)) + 0.02f <= 100.0f) {
                enviro_sanity.set(tracker, Float.valueOf(curSane + 0.02f));
            }
            float curHyd = enviro_hydration.getFloat(tracker);
            enviro_dehydrate.invoke(tracker, Float.valueOf(curHyd > 80.0f ? deh : 0.0f));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean railcraftAllowBlastFurnace() {
        if (!loaded_Railcraft) {
            return false;
        }
        try {
            if (railcraft_isSubBlockEnabled == null) {
                Class<?> c_RailcraftConfig = Class.forName("mods.railcraft.common.core.RailcraftConfig");
                railcraft_isSubBlockEnabled = c_RailcraftConfig.getMethod("isSubBlockEnabled", String.class);
            }
            boolean enabled = (Boolean)railcraft_isSubBlockEnabled.invoke(null, "machine.alpha.blast.furnace");
            boolean block = GameRegistry.findBlock((String)"Railcraft", (String)"brick.infernal") != null;
            boolean stair = GameRegistry.findBlock((String)"Railcraft", (String)"stair") != null;
            return enabled && block && stair;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void thaumicTinkererRegisterEnchantment(Enchantment enchantment, String texture, AspectList aspects, String research) {
        if (!loaded_TT) {
            return;
        }
        try {
            if (thaumtink_registerExponentialCostData == null) {
                Class<?> c_EnchantmentManager = Class.forName("thaumic.tinkerer.common.enchantment.core.EnchantmentManager");
                thaumtink_registerExponentialCostData = c_EnchantmentManager.getMethod("registerExponentialCostData", Enchantment.class, String.class, Boolean.TYPE, AspectList.class, String.class);
            }
            thaumtink_registerExponentialCostData.invoke(null, enchantment, texture, false, aspects, research);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static {
        smeltery = null;
        addMelting = null;
        dryingRack = null;
        addDryingRecipe = null;
        enviro_DataTracker = null;
        enviro_lookupTracker = null;
        enviro_temperatue = null;
        enviro_hydration = null;
        enviro_sanity = null;
        enviro_dehydrate = null;
        railcraft_isSubBlockEnabled = null;
        thaumtink_registerExponentialCostData = null;
    }
}

