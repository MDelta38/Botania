/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraftforge.common.util.EnumHelper
 */
package vazkii.botania.common.integration.etfuturum;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ModItems;

public final class ModBanners {
    public static void init() {
        try {
            Class<Enum<?>> clazz = Class.forName("ganymedes01.etfuturum.tileentities.TileEntityBanner$EnumBannerPattern");
            ModBanners.addPattern(clazz, "flower", "flr", new ItemStack(ModItems.manaResource, 1, 3));
            ModBanners.addPattern(clazz, "lexicon", "lex", new ItemStack(ModItems.lexicon));
            ModBanners.addPattern(clazz, "logo", "lgo", new ItemStack(ModItems.manaResource, 1, 4));
            ModBanners.addPattern(clazz, "sapling", "spl", new ItemStack(ModItems.manaResource, 1, 13));
            ModBanners.addPattern(clazz, "tiny_potato", "tpt", new ItemStack(ModBlocks.tinyPotato));
            ModBanners.addPattern(clazz, "spark_dispersive", "sds", new ItemStack(ModItems.sparkUpgrade, 1, 0));
            ModBanners.addPattern(clazz, "spark_dominant", "sdm", new ItemStack(ModItems.sparkUpgrade, 1, 1));
            ModBanners.addPattern(clazz, "spark_recessive", "src", new ItemStack(ModItems.sparkUpgrade, 1, 2));
            ModBanners.addPattern(clazz, "spark_isolated", "sis", new ItemStack(ModItems.sparkUpgrade, 1, 3));
            ModBanners.addPattern(clazz, "fish", "fis", new ItemStack(Items.field_151115_aP));
            ModBanners.addPattern(clazz, "axe", "axe", new ItemStack(Items.field_151036_c));
            ModBanners.addPattern(clazz, "hoe", "hoe", new ItemStack(Items.field_151019_K));
            ModBanners.addPattern(clazz, "pickaxe", "pik", new ItemStack(Items.field_151035_b));
            ModBanners.addPattern(clazz, "shovel", "shv", new ItemStack(Items.field_151037_a));
            ModBanners.addPattern(clazz, "sword", "srd", new ItemStack(Items.field_151040_l));
        }
        catch (ClassNotFoundException classNotFoundException) {
            // empty catch block
        }
    }

    public static void addPattern(Class<Enum<?>> clazz, String name, String id, ItemStack craftingItem) {
        name = "botania_" + name;
        id = "bt_" + id;
        EnumHelper.addEnum(clazz, (String)name.toUpperCase(), (Class[])new Class[]{String.class, String.class, ItemStack.class}, (Object[])new Object[]{name, id, craftingItem});
    }
}

