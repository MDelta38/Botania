/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  thaumcraft.api.aspects.Aspect
 */
package witchinggadgets.common.util.handler;

import java.util.HashMap;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import witchinggadgets.common.util.Utilities;

public class InfusedGemHandler {
    public static HashMap<String, Aspect[]> naturalAffinities = new HashMap();
    public static HashMap<String, Aspect[]> naturalAversions = new HashMap();

    public static void registerAffinities(Object o, Aspect ... aspects) {
        if (o instanceof String) {
            naturalAffinities.put((String)o, aspects);
        } else if (o instanceof ItemStack) {
            naturalAffinities.put(Utilities.getItemStackString((ItemStack)o), aspects);
        }
    }

    public static void removeAffinities(Object o) {
        if (o instanceof String) {
            naturalAffinities.remove((String)o);
        } else if (o instanceof ItemStack) {
            naturalAffinities.remove(Utilities.getItemStackString((ItemStack)o));
        }
    }

    public static void registerAversions(Object o, Aspect ... aspects) {
        if (o instanceof String) {
            naturalAversions.put((String)o, aspects);
        } else if (o instanceof ItemStack) {
            naturalAversions.put(Utilities.getItemStackString((ItemStack)o), aspects);
        }
    }

    public static void removeAversions(Object o) {
        if (o instanceof String) {
            naturalAversions.remove((String)o);
        } else if (o instanceof ItemStack) {
            naturalAversions.remove(Utilities.getItemStackString((ItemStack)o));
        }
    }

    public static Aspect[] getNaturalAffinities(ItemStack gem) {
        String s0 = Utilities.getItemStackString(gem);
        if (naturalAffinities.containsKey(s0)) {
            return naturalAffinities.get(s0);
        }
        for (String s1 : naturalAffinities.keySet()) {
            if (!Utilities.compareToOreName(gem, s1)) continue;
            return naturalAffinities.get(s1);
        }
        return new Aspect[0];
    }

    public static Aspect[] getNaturalAversions(ItemStack gem) {
        String s0 = Utilities.getItemStackString(gem);
        if (naturalAversions.containsKey(s0)) {
            return naturalAversions.get(s0);
        }
        for (String s1 : naturalAversions.keySet()) {
            if (!Utilities.compareToOreName(gem, s1)) continue;
            return naturalAversions.get(s1);
        }
        return new Aspect[0];
    }

    public static boolean isGem(ItemStack stack) {
        String s0 = Utilities.getItemStackString(stack);
        if (naturalAffinities.containsKey(s0) || naturalAversions.containsKey(s0)) {
            return true;
        }
        for (String s : naturalAffinities.keySet()) {
            if (!Utilities.compareToOreName(stack, s)) continue;
            return true;
        }
        for (String s : naturalAversions.keySet()) {
            if (!Utilities.compareToOreName(stack, s)) continue;
            return true;
        }
        return false;
    }

    static {
        InfusedGemHandler.registerAffinities("gemDiamond", Aspect.ORDER, Aspect.ENTROPY);
        InfusedGemHandler.registerAffinities("gemEmerald", Aspect.ORDER, Aspect.EARTH);
        InfusedGemHandler.registerAversions("gemEmerald", Aspect.WATER, Aspect.ENTROPY);
        InfusedGemHandler.registerAffinities("crystalNetherQuartz", Aspect.ENTROPY, Aspect.FIRE);
        InfusedGemHandler.registerAversions("crystalNetherQuartz", Aspect.ORDER, Aspect.WATER);
        InfusedGemHandler.registerAffinities("crystalCertusQuartz", Aspect.ORDER, Aspect.AIR);
        InfusedGemHandler.registerAversions("crystalCertusQuartz", Aspect.ENTROPY, Aspect.FIRE);
        InfusedGemHandler.registerAffinities("gemTopaz", Aspect.AIR, Aspect.FIRE);
        InfusedGemHandler.registerAversions("gemTopaz", Aspect.ENTROPY, Aspect.WATER);
        InfusedGemHandler.registerAffinities("gemAmber", Aspect.AIR, Aspect.EARTH);
        InfusedGemHandler.registerAversions("gemAmber", Aspect.ENTROPY);
        InfusedGemHandler.registerAffinities("gemPeridot", Aspect.EARTH);
        InfusedGemHandler.registerAversions("gemPeridot", Aspect.ENTROPY, Aspect.FIRE);
        InfusedGemHandler.registerAffinities("gemMalachite", Aspect.EARTH, Aspect.WATER);
        InfusedGemHandler.registerAversions("gemMalachite", Aspect.ENTROPY, Aspect.FIRE);
        InfusedGemHandler.registerAffinities("gemRuby", Aspect.FIRE);
        InfusedGemHandler.registerAversions("gemRuby", Aspect.ENTROPY, Aspect.WATER);
        InfusedGemHandler.registerAffinities("gemSapphire", Aspect.WATER);
        InfusedGemHandler.registerAversions("gemSapphire", Aspect.ENTROPY, Aspect.FIRE);
        InfusedGemHandler.registerAffinities("gemTanzanite", Aspect.WATER);
        InfusedGemHandler.registerAversions("gemTanzanite", Aspect.ENTROPY, Aspect.FIRE);
        InfusedGemHandler.registerAffinities("gemPrimordial", Aspect.AIR, Aspect.EARTH, Aspect.FIRE, Aspect.WATER, Aspect.ORDER, Aspect.ENTROPY);
    }
}

