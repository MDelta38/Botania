/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 *  net.minecraftforge.common.AchievementPage
 */
package vazkii.botania.common.achievement;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import vazkii.botania.common.achievement.AchievementMod;
import vazkii.botania.common.achievement.AchievementTriggerer;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

public final class ModAchievements {
    public static AchievementPage botaniaPage;
    public static int pageIndex;
    public static Achievement flowerPickup;
    public static Achievement lexiconUse;
    public static Achievement daybloomPickup;
    public static Achievement cacophoniumCraft;
    public static Achievement manaPoolPickup;
    public static Achievement endoflamePickup;
    public static Achievement tinyPotatoPet;
    public static Achievement sparkCraft;
    public static Achievement baubleWear;
    public static Achievement manaCookieEat;
    public static Achievement manaweaveArmorCraft;
    public static Achievement craftingHaloCraft;
    public static Achievement manaCartCraft;
    public static Achievement enchanterMake;
    public static Achievement runePickup;
    public static Achievement dirtRodCraft;
    public static Achievement terraformRodCraft;
    public static Achievement manaBlasterShoot;
    public static Achievement pollidisiacPickup;
    public static Achievement brewPickup;
    public static Achievement terrasteelPickup;
    public static Achievement terrasteelWeaponCraft;
    public static Achievement elfPortalOpen;
    public static Achievement kekimurusPickup;
    public static Achievement heiseiDreamPickup;
    public static Achievement bubbellPickup;
    public static Achievement luminizerRide;
    public static Achievement enderAirMake;
    public static Achievement corporeaCraft;
    public static Achievement gaiaGuardianKill;
    public static Achievement spawnerMoverUse;
    public static Achievement tiaraWings;
    public static Achievement manaBombIgnite;
    public static Achievement dandelifeonPickup;
    public static Achievement signalFlareStun;
    public static Achievement l20ShardUse;
    public static Achievement gaiaGuardianNoArmor;
    public static Achievement rankSSPick;
    public static Achievement superCorporeaRequest;
    public static Achievement pinkinator;
    public static Achievement relicInfiniteFruit;
    public static Achievement relicKingKey;
    public static Achievement relicFlugelEye;
    public static Achievement relicThorRing;
    public static Achievement relicOdinRing;
    public static Achievement relicLokiRing;
    public static Achievement relicAesirRing;
    public static Achievement nullFlower;
    public static Achievement desuGun;

    public static void init() {
        flowerPickup = new AchievementMod("flowerPickup", 0, 4, new ItemStack(ModBlocks.flower, 1, 6), null);
        lexiconUse = new AchievementMod("lexiconUse", 1, 5, ModItems.lexicon, flowerPickup);
        daybloomPickup = new AchievementMod("daybloomPickup", 3, 5, ItemBlockSpecialFlower.ofType("daybloom"), lexiconUse);
        cacophoniumCraft = new AchievementMod("cacophoniumCraft", -1, 2, ModItems.cacophonium, flowerPickup);
        manaPoolPickup = new AchievementMod("manaPoolPickup", 3, 2, ModBlocks.pool, daybloomPickup);
        endoflamePickup = new AchievementMod("endoflamePickup", 2, 0, ItemBlockSpecialFlower.ofType("endoflame"), manaPoolPickup);
        tinyPotatoPet = new AchievementMod("tinyPotatoPet", 2, -2, ModBlocks.tinyPotato, manaPoolPickup);
        sparkCraft = new AchievementMod("sparkCraft", 4, -2, ModItems.spark, manaPoolPickup);
        baubleWear = new AchievementMod("baubleWear", 4, 0, ModItems.manaRing, manaPoolPickup);
        manaCookieEat = new AchievementMod("manaCookieEat", 2, -4, ModItems.manaCookie, manaPoolPickup);
        manaweaveArmorCraft = new AchievementMod("manaweaveArmorCraft", 4, -4, ModItems.manaweaveChest, manaPoolPickup);
        craftingHaloCraft = new AchievementMod("craftingHaloCraft", 3, -6, ModItems.craftingHalo, manaPoolPickup);
        manaCartCraft = new AchievementMod("manaCartCraft", 5, 3, ModItems.poolMinecart, manaPoolPickup);
        enchanterMake = new AchievementMod("enchanterMake", 1, 2, ModBlocks.enchanter, manaPoolPickup);
        runePickup = new AchievementMod("runePickup", 6, 2, ModBlocks.runeAltar, manaPoolPickup);
        dirtRodCraft = new AchievementMod("dirtRodCraft", 8, 3, ModItems.dirtRod, runePickup);
        terraformRodCraft = new AchievementMod("terraformRodCraft", 10, 3, ModItems.terraformRod, dirtRodCraft);
        manaBlasterShoot = new AchievementMod("manaBlasterShoot", 8, 1, ModItems.manaGun, runePickup);
        pollidisiacPickup = new AchievementMod("pollidisiacPickup", 8, 5, ItemBlockSpecialFlower.ofType("pollidisiac"), runePickup);
        brewPickup = new AchievementMod("brewPickup", 6, 0, ModBlocks.brewery, runePickup);
        terrasteelPickup = new AchievementMod("terrasteelPickup", 6, 9, new ItemStack(ModItems.manaResource, 1, 4), runePickup).func_75987_b();
        terrasteelWeaponCraft = new AchievementMod("terrasteelWeaponCraft", 8, 10, ModItems.terraSword, terrasteelPickup);
        elfPortalOpen = new AchievementMod("elfPortalOpen", 4, 9, ModBlocks.alfPortal, terrasteelPickup).func_75987_b();
        kekimurusPickup = new AchievementMod("kekimurusPickup", 3, 11, ItemBlockSpecialFlower.ofType("kekimurus"), elfPortalOpen);
        heiseiDreamPickup = new AchievementMod("heiseiDreamPickup", 5, 11, ItemBlockSpecialFlower.ofType("heiseiDream"), elfPortalOpen);
        bubbellPickup = new AchievementMod("bubbellPickup", 6, 12, ItemBlockSpecialFlower.ofType("bubbell"), elfPortalOpen);
        enderAirMake = new AchievementMod("enderAirMake", 4, 14, new ItemStack(ModItems.manaResource, 1, 15), elfPortalOpen);
        corporeaCraft = new AchievementMod("corporeaCraft", 2, 14, ModBlocks.corporeaFunnel, enderAirMake);
        luminizerRide = new AchievementMod("luminizerRide", 6, 14, ModBlocks.lightRelay, enderAirMake);
        gaiaGuardianKill = new AchievementMod("gaiaGuardianKill", 2, 9, new ItemStack(ModItems.manaResource, 1, 5), elfPortalOpen).func_75987_b();
        spawnerMoverUse = new AchievementMod("spawnerMoverUse", -1, 10, ModItems.spawnerMover, gaiaGuardianKill);
        tiaraWings = new AchievementMod("tiaraWings", -1, 8, ModItems.flightTiara, gaiaGuardianKill);
        manaBombIgnite = new AchievementMod("manaBombIgnite", 0, 11, ModBlocks.manaBomb, gaiaGuardianKill);
        dandelifeonPickup = new AchievementMod("dandelifeonPickup", 0, 7, ItemBlockSpecialFlower.ofType("dandelifeon"), gaiaGuardianKill);
        signalFlareStun = new AchievementMod("signalFlareStun", -3, 1, ModItems.signalFlare, null).func_75987_b();
        l20ShardUse = new AchievementMod("l20ShardUse", -5, 3, ModItems.laputaShard, null).func_75987_b();
        gaiaGuardianNoArmor = new AchievementMod("gaiaGuardianNoArmor", -5, 1, new ItemStack(Items.field_151144_bL, 1, 3), null).func_75987_b();
        rankSSPick = new AchievementMod("rankSSPick", -3, 3, ModItems.terraPick, null).func_75987_b();
        superCorporeaRequest = new AchievementMod("superCorporeaRequest", -3, -1, ModBlocks.corporeaIndex, null).func_75987_b();
        pinkinator = new AchievementMod("pinkinator", -5, -1, ModItems.pinkinator, null).func_75987_b();
        if (ConfigHandler.relicsEnabled) {
            relicInfiniteFruit = new AchievementMod("infiniteFruit", -9, 8, ModItems.infiniteFruit, null);
            relicKingKey = new AchievementMod("kingKey", -7, 11, ModItems.kingKey, null);
            relicFlugelEye = new AchievementMod("flugelEye", -5, 8, ModItems.flugelEye, null);
            relicThorRing = new AchievementMod("thorRing", -7, 7, ModItems.thorRing, null);
            relicOdinRing = new AchievementMod("odinRing", -9, 10, ModItems.odinRing, null);
            relicLokiRing = new AchievementMod("lokiRing", -5, 10, ModItems.lokiRing, null);
            relicAesirRing = new AchievementMod("aesirRing", -7, 9, ModItems.aesirRing, null).func_75987_b();
        }
        nullFlower = new AchievementMod("nullFlower", -8, 0, ModBlocks.specialFlower, null).func_75987_b();
        ItemStack desu = new ItemStack(ModItems.manaGun);
        desu.func_151001_c("desu gun");
        desuGun = new AchievementMod("desuGun", -8, 2, desu, null).func_75987_b();
        pageIndex = AchievementPage.getAchievementPages().size();
        botaniaPage = new AchievementPage("Botania", AchievementMod.achievements.toArray(new Achievement[AchievementMod.achievements.size()]));
        AchievementPage.registerAchievementPage((AchievementPage)botaniaPage);
        FMLCommonHandler.instance().bus().register((Object)new AchievementTriggerer());
    }
}

