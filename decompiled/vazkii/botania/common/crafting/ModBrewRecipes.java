/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipeBrew;
import vazkii.botania.common.brew.ModBrews;
import vazkii.botania.common.item.ModItems;

public class ModBrewRecipes {
    public static RecipeBrew speedBrew;
    public static RecipeBrew strengthBrew;
    public static RecipeBrew hasteBrew;
    public static RecipeBrew healingBrew;
    public static RecipeBrew jumpBoostBrew;
    public static RecipeBrew regenerationBrew;
    public static RecipeBrew weakRegenerationBrew;
    public static RecipeBrew resistanceBrew;
    public static RecipeBrew fireResistanceBrew;
    public static RecipeBrew waterBreathingBrew;
    public static RecipeBrew invisibilityBrew;
    public static RecipeBrew nightVisionBrew;
    public static RecipeBrew absorptionBrew;
    public static RecipeBrew overloadBrew;
    public static RecipeBrew soulCrossBrew;
    public static RecipeBrew featherFeetBrew;
    public static RecipeBrew emptinessBrew;
    public static RecipeBrew bloodthirstBrew;
    public static RecipeBrew allureBrew;
    public static RecipeBrew clearBrew;
    public static RecipeBrew warpWardBrew;

    public static void init() {
        speedBrew = BotaniaAPI.registerBrewRecipe(ModBrews.speed, new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151102_aT), new ItemStack(Items.field_151137_ax));
        strengthBrew = BotaniaAPI.registerBrewRecipe(ModBrews.strength, new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151065_br), new ItemStack(Items.field_151114_aO));
        hasteBrew = BotaniaAPI.registerBrewRecipe(ModBrews.haste, new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151102_aT), new ItemStack(Items.field_151074_bl));
        healingBrew = BotaniaAPI.registerBrewRecipe(ModBrews.healing, new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151060_bw), new ItemStack(Items.field_151174_bG));
        jumpBoostBrew = BotaniaAPI.registerBrewRecipe(ModBrews.jumpBoost, new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151008_G), new ItemStack(Items.field_151172_bF));
        regenerationBrew = BotaniaAPI.registerBrewRecipe(ModBrews.regen, new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151073_bk), new ItemStack(Items.field_151114_aO));
        weakRegenerationBrew = BotaniaAPI.registerBrewRecipe(ModBrews.regenWeak, new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151073_bk), new ItemStack(Items.field_151137_ax));
        resistanceBrew = BotaniaAPI.registerBrewRecipe(ModBrews.resistance, new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151042_j), new ItemStack(Items.field_151116_aA));
        fireResistanceBrew = BotaniaAPI.registerBrewRecipe(ModBrews.fireResistance, new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151064_bs), new ItemStack(Blocks.field_150424_aL));
        waterBreathingBrew = BotaniaAPI.registerBrewRecipe(ModBrews.waterBreathing, new ItemStack(Items.field_151075_bm), new ItemStack(ModItems.manaResource, 1, 10), new ItemStack(Items.field_151114_aO));
        invisibilityBrew = BotaniaAPI.registerBrewRecipe(ModBrews.invisibility, new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151126_ay), new ItemStack(Items.field_151114_aO));
        nightVisionBrew = BotaniaAPI.registerBrewRecipe(ModBrews.nightVision, new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151070_bp), new ItemStack(Items.field_151150_bK));
        absorptionBrew = BotaniaAPI.registerBrewRecipe(ModBrews.absorption, new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151153_ao), new ItemStack(Items.field_151174_bG));
        overloadBrew = BotaniaAPI.registerBrewRecipe(ModBrews.overload, new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151065_br), new ItemStack(Items.field_151102_aT), new ItemStack(Items.field_151114_aO), new ItemStack(ModItems.manaResource), new ItemStack(Items.field_151070_bp));
        soulCrossBrew = BotaniaAPI.registerBrewRecipe(ModBrews.soulCross, new ItemStack(Items.field_151075_bm), new ItemStack(Blocks.field_150425_aM), new ItemStack(Items.field_151121_aF), new ItemStack(Items.field_151034_e), new ItemStack(Items.field_151103_aS));
        featherFeetBrew = BotaniaAPI.registerBrewRecipe(ModBrews.featherfeet, new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151008_G), new ItemStack(Items.field_151116_aA), new ItemStack(Blocks.field_150325_L, 1, -1));
        emptinessBrew = BotaniaAPI.registerBrewRecipe(ModBrews.emptiness, new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151016_H), new ItemStack(Items.field_151078_bh), new ItemStack(Items.field_151103_aS), new ItemStack(Items.field_151007_F), new ItemStack(Items.field_151079_bi));
        bloodthirstBrew = BotaniaAPI.registerBrewRecipe(ModBrews.bloodthirst, new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151071_bq), new ItemStack(Items.field_151100_aR, 1, 4), new ItemStack(Items.field_151059_bz), new ItemStack(Items.field_151042_j));
        allureBrew = BotaniaAPI.registerBrewRecipe(ModBrews.allure, new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151115_aP), new ItemStack(Items.field_151128_bU), new ItemStack(Items.field_151150_bK));
        clearBrew = BotaniaAPI.registerBrewRecipe(ModBrews.clear, new ItemStack(Items.field_151075_bm), new ItemStack(Items.field_151128_bU), new ItemStack(Items.field_151166_bC), new ItemStack(Items.field_151127_ba));
    }

    public static void initTC() {
        Item resource = (Item)Item.field_150901_e.func_82594_a("Thaumcraft:ItemResource");
        Item bathSalts = (Item)Item.field_150901_e.func_82594_a("Thaumcraft:ItemBathSalts");
        warpWardBrew = BotaniaAPI.registerBrewRecipe(ModBrews.warpWard, new ItemStack(Items.field_151075_bm), new ItemStack(resource, 1, 14), new ItemStack(bathSalts), new ItemStack(resource, 1, 6));
    }
}

