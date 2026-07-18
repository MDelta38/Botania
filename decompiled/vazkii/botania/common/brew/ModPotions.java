/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.potion.Potion
 */
package vazkii.botania.common.brew;

import java.lang.reflect.Field;
import net.minecraft.potion.Potion;
import vazkii.botania.common.brew.potion.PotionAllure;
import vazkii.botania.common.brew.potion.PotionBloodthirst;
import vazkii.botania.common.brew.potion.PotionClear;
import vazkii.botania.common.brew.potion.PotionEmptiness;
import vazkii.botania.common.brew.potion.PotionFeatherfeet;
import vazkii.botania.common.brew.potion.PotionSoulCross;

public class ModPotions {
    public static Potion soulCross;
    public static Potion featherfeet;
    public static Potion emptiness;
    public static Potion bloodthrst;
    public static Potion allure;
    public static Potion clear;

    public static void init() {
        if (Potion.field_76425_a.length < 256) {
            ModPotions.extendPotionArray();
        }
        soulCross = new PotionSoulCross();
        featherfeet = new PotionFeatherfeet();
        emptiness = new PotionEmptiness();
        bloodthrst = new PotionBloodthirst();
        allure = new PotionAllure();
        clear = new PotionClear();
    }

    private static void extendPotionArray() {
        Potion[] potionTypes = null;
        for (Field f : Potion.class.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (!f.getName().equals("potionTypes") && !f.getName().equals("field_76425_a")) continue;
                Field modfield = Field.class.getDeclaredField("modifiers");
                modfield.setAccessible(true);
                modfield.setInt(f, f.getModifiers() & 0xFFFFFFEF);
                potionTypes = (Potion[])f.get(null);
                Potion[] newPotionTypes = new Potion[256];
                System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                f.set(null, newPotionTypes);
            }
            catch (Exception e) {
                System.err.println("Severe error, please report this to the mod author:");
                System.err.println(e);
            }
        }
    }
}

