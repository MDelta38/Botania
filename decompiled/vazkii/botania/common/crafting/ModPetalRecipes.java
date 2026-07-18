/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.crafting;

import java.util.Arrays;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;
import vazkii.botania.common.lib.LibOreDict;

public final class ModPetalRecipes {
    public static final String white = LibOreDict.PETAL[0];
    public static final String orange = LibOreDict.PETAL[1];
    public static final String magenta = LibOreDict.PETAL[2];
    public static final String lightBlue = LibOreDict.PETAL[3];
    public static final String yellow = LibOreDict.PETAL[4];
    public static final String lime = LibOreDict.PETAL[5];
    public static final String pink = LibOreDict.PETAL[6];
    public static final String gray = LibOreDict.PETAL[7];
    public static final String lightGray = LibOreDict.PETAL[8];
    public static final String cyan = LibOreDict.PETAL[9];
    public static final String purple = LibOreDict.PETAL[10];
    public static final String blue = LibOreDict.PETAL[11];
    public static final String brown = LibOreDict.PETAL[12];
    public static final String green = LibOreDict.PETAL[13];
    public static final String red = LibOreDict.PETAL[14];
    public static final String black = LibOreDict.PETAL[15];
    public static final String runeWater = LibOreDict.RUNE[0];
    public static final String runeFire = LibOreDict.RUNE[1];
    public static final String runeEarth = LibOreDict.RUNE[2];
    public static final String runeAir = LibOreDict.RUNE[3];
    public static final String runeSpring = LibOreDict.RUNE[4];
    public static final String runeSummer = LibOreDict.RUNE[5];
    public static final String runeAutumn = LibOreDict.RUNE[6];
    public static final String runeWinter = LibOreDict.RUNE[7];
    public static final String runeMana = LibOreDict.RUNE[8];
    public static final String runeLust = LibOreDict.RUNE[9];
    public static final String runeGluttony = LibOreDict.RUNE[10];
    public static final String runeGreed = LibOreDict.RUNE[11];
    public static final String runeSloth = LibOreDict.RUNE[12];
    public static final String runeWrath = LibOreDict.RUNE[13];
    public static final String runeEnvy = LibOreDict.RUNE[14];
    public static final String runePride = LibOreDict.RUNE[15];
    public static final String redstoneRoot = "redstoneRoot";
    public static final String pixieDust = "elvenPixieDust";
    public static final String gaiaSpirit = "eternalLifeEssence";
    public static final String manaPowder = "powderMana";
    public static RecipePetals pureDaisyRecipe;
    public static RecipePetals manastarRecipe;
    public static RecipePetals daybloomRecipe;
    public static RecipePetals nightshadeRecipe;
    public static RecipePetals endoflameRecipe;
    public static RecipePetals hydroangeasRecipe;
    public static RecipePetals thermalilyRecipe;
    public static RecipePetals arcaneRoseRecipe;
    public static RecipePetals munchdewRecipe;
    public static RecipePetals entropinnyumRecipe;
    public static RecipePetals kekimurusRecipe;
    public static RecipePetals gourmaryllisRecipe;
    public static RecipePetals narslimmusRecipe;
    public static RecipePetals spectrolusRecipe;
    public static RecipePetals rafflowsiaRecipe;
    public static RecipePetals dandelifeonRecipe;
    public static RecipePetals jadedAmaranthusRecipe;
    public static RecipePetals bellethorneRecipe;
    public static RecipePetals dreadthorneRecipe;
    public static RecipePetals heiseiDreamRecipe;
    public static RecipePetals tigerseyeRecipe;
    public static RecipePetals orechidRecipe;
    public static RecipePetals orechidIgnemRecipe;
    public static RecipePetals fallenKanadeRecipe;
    public static RecipePetals exoflameRecipe;
    public static RecipePetals agricarnationRecipe;
    public static RecipePetals hopperhockRecipe;
    public static RecipePetals tangleberrieRecipe;
    public static RecipePetals jiyuuliaRecipe;
    public static RecipePetals rannuncarpusRecipe;
    public static RecipePetals hyacidusRecipe;
    public static RecipePetals pollidisiacRecipe;
    public static RecipePetals clayconiaRecipe;
    public static RecipePetals looniumRecipe;
    public static RecipePetals daffomillRecipe;
    public static RecipePetals vinculotusRecipe;
    public static RecipePetals spectranthemumRecipe;
    public static RecipePetals medumoneRecipe;
    public static RecipePetals marimorphosisRecipe;
    public static RecipePetals bubbellRecipe;
    public static RecipePetals solegnoliaRecipe;

    public static void init() {
        pureDaisyRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("puredaisy"), white, white, white, white);
        manastarRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("manastar"), lightBlue, green, red, cyan);
        daybloomRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("daybloom"), yellow, yellow, orange, lightBlue);
        nightshadeRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("nightshade"), black, black, purple, gray);
        endoflameRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("endoflame"), brown, brown, red, lightGray, manaPowder);
        hydroangeasRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("hydroangeas"), blue, blue, cyan, cyan, manaPowder);
        thermalilyRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("thermalily"), red, orange, orange, runeEarth, runeFire);
        arcaneRoseRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("arcanerose"), pink, pink, purple, purple, lime, runeMana);
        munchdewRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("munchdew"), lime, lime, red, red, green, runeGluttony);
        entropinnyumRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("entropinnyum"), red, red, gray, gray, white, white, runeWrath, runeFire);
        kekimurusRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("kekimurus"), white, white, orange, orange, brown, brown, runeGluttony, pixieDust);
        gourmaryllisRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("gourmaryllis"), lightGray, lightGray, yellow, yellow, red, runeFire, runeSummer);
        narslimmusRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("narslimmus"), lime, lime, green, green, black, runeSummer, runeWater);
        spectrolusRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("spectrolus"), red, red, green, green, blue, blue, white, white, runeWinter, runeAir, pixieDust);
        rafflowsiaRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("rafflowsia"), purple, purple, green, green, black, runeEarth, runePride, pixieDust);
        dandelifeonRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("dandelifeon"), purple, purple, lime, green, runeWater, runeFire, runeEarth, runeAir, gaiaSpirit);
        jadedAmaranthusRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("jadedAmaranthus"), purple, lime, green, runeSpring, redstoneRoot);
        bellethorneRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("bellethorn"), red, red, red, cyan, cyan, redstoneRoot);
        dreadthorneRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("dreadthorn"), black, black, black, cyan, cyan, redstoneRoot);
        heiseiDreamRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("heiseiDream"), magenta, magenta, purple, pink, runeWrath, pixieDust);
        tigerseyeRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("tigerseye"), yellow, brown, orange, lime, runeAutumn);
        orechidRecipe = Botania.gardenOfGlassLoaded ? BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("orechid"), gray, gray, yellow, yellow, green, green, red, red) : BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("orechid"), gray, gray, yellow, green, red, runePride, runeGreed, redstoneRoot, pixieDust);
        orechidIgnemRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("orechidIgnem"), red, red, white, white, pink, runePride, runeGreed, redstoneRoot, pixieDust);
        if (ConfigHandler.fallenKanadeEnabled) {
            fallenKanadeRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("fallenKanade"), white, white, yellow, yellow, orange, runeSpring);
        }
        exoflameRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("exoflame"), red, red, gray, lightGray, runeFire, runeSummer);
        agricarnationRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("agricarnation"), lime, lime, green, yellow, runeSpring, redstoneRoot);
        hopperhockRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("hopperhock"), gray, gray, lightGray, lightGray, runeAir, redstoneRoot);
        tangleberrieRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("tangleberrie"), cyan, cyan, gray, lightGray, runeAir, runeEarth);
        jiyuuliaRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("jiyuulia"), pink, pink, purple, lightGray, runeWater, runeAir);
        rannuncarpusRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("rannuncarpus"), orange, orange, yellow, runeEarth, redstoneRoot);
        hyacidusRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("hyacidus"), purple, purple, magenta, magenta, green, runeWater, runeAutumn, redstoneRoot);
        pollidisiacRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("pollidisiac"), red, red, pink, pink, orange, runeLust, runeFire);
        clayconiaRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("clayconia"), lightGray, lightGray, gray, cyan, runeEarth);
        looniumRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("loonium"), green, green, green, green, gray, runeSloth, runeGluttony, runeEnvy, redstoneRoot, pixieDust);
        daffomillRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("daffomill"), white, white, brown, yellow, runeAir, redstoneRoot);
        vinculotusRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("vinculotus"), black, black, purple, purple, green, runeWater, runeSloth, runeLust, redstoneRoot);
        spectranthemumRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("spectranthemum"), white, white, lightGray, lightGray, cyan, runeEnvy, runeWater, redstoneRoot, pixieDust);
        medumoneRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("medumone"), brown, brown, gray, gray, runeEarth, redstoneRoot);
        marimorphosisRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("marimorphosis"), gray, yellow, green, red, runeEarth, runeFire, redstoneRoot);
        bubbellRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("bubbell"), cyan, cyan, lightBlue, lightBlue, blue, blue, runeWater, runeSummer, pixieDust);
        solegnoliaRecipe = BotaniaAPI.registerPetalRecipe(ItemBlockSpecialFlower.ofType("solegnolia"), brown, brown, red, blue, redstoneRoot);
        ItemStack stack = new ItemStack(Items.field_151144_bL, 1, 3);
        ItemNBTHelper.setString(stack, "SkullOwner", "Vazkii");
        Object[] inputs = new Object[16];
        Arrays.fill(inputs, pink);
        BotaniaAPI.registerPetalRecipe(stack, inputs);
    }
}

