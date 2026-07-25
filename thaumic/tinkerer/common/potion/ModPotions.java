/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  net.minecraft.potion.Potion
 *  net.minecraftforge.common.MinecraftForge
 */
package thaumic.tinkerer.common.potion;

import cpw.mods.fml.common.FMLCommonHandler;
import java.lang.reflect.Field;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.core.handler.ConfigHandler;
import thaumic.tinkerer.common.potion.DummyPotions;
import thaumic.tinkerer.common.potion.PotionEffectHandler;

public final class ModPotions {
    public static Potion potionFire;
    public static Potion potionWater;
    public static Potion potionEarth;
    public static Potion potionAir;

    public static void initPotions() {
        MinecraftForge.EVENT_BUS.register((Object)new PotionEffectHandler());
        FMLCommonHandler.instance().bus().register((Object)new PotionEffectHandler());
        Potion[] potionTypes = null;
        for (Field f : Potion.class.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (!f.getName().equals("potionTypes") && !f.getName().equals("field_76425_a")) continue;
                Field modfield = Field.class.getDeclaredField("modifiers");
                modfield.setAccessible(true);
                modfield.setInt(f, f.getModifiers() & 0xFFFFFFEF);
                potionTypes = (Potion[])f.get(null);
                if (potionTypes.length >= 256) continue;
                Potion[] newPotionTypes = new Potion[256];
                System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                f.set(null, newPotionTypes);
            }
            catch (Exception e) {
                ThaumicTinkerer.log.error("Severe error, please report this to the mod author:", (Throwable)e);
            }
        }
        potionFire = new DummyPotions(ConfigHandler.potionFireId, true, 0).func_76399_b(0, 0).func_76390_b("Fire Imbued");
        potionWater = new DummyPotions(ConfigHandler.potionWaterId, true, 0).func_76399_b(0, 0).func_76390_b("Wated Imbued");
        potionEarth = new DummyPotions(ConfigHandler.potionEarthId, true, 0).func_76399_b(0, 0).func_76390_b("Earth Imbued");
        potionAir = new DummyPotions(ConfigHandler.potionAirId, true, 0).func_76399_b(0, 0).func_76390_b("Air Imbued");
    }
}

