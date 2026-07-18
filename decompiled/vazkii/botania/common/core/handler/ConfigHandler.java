/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.event.ConfigChangedEvent$OnConfigChangedEvent
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChatStyle
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.common.config.Property
 */
package vazkii.botania.common.core.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.handler.SheddingHandler;

public final class ConfigHandler {
    public static Configuration config;
    public static ConfigAdaptor adaptor;
    private static final String CATEGORY_POTIONS = "potions";
    public static int hardcorePassiveGeneration;
    public static boolean useAdaptativeConfig;
    public static boolean useShaders;
    public static boolean lexiconRotatingItems;
    public static boolean lexiconJustifiedText;
    public static boolean subtlePowerSystem;
    public static boolean staticWandBeam;
    public static boolean boundBlockWireframe;
    public static boolean lexicon3dModel;
    public static boolean oldPylonModel;
    public static double flowerParticleFrequency;
    public static boolean blockBreakParticles;
    public static boolean blockBreakParticlesTool;
    public static boolean elfPortalParticlesEnabled;
    public static boolean chargingAnimationEnabled;
    public static boolean useVanillaParticleLimiter;
    public static boolean silentSpreaders;
    public static boolean renderBaubles;
    public static boolean enableSeasonalFeatures;
    public static boolean useShiftForQuickLookup;
    public static boolean enableArmorModels;
    public static boolean enableFancySkybox;
    public static boolean enableFancySkyboxInNormalWorlds;
    public static int manaBarHeight;
    public static int flightBarHeight;
    public static int flightBarBreathHeight;
    public static int glSecondaryTextureUnit;
    public static boolean altFlowerTextures;
    public static boolean matrixMode;
    public static boolean referencesEnabled;
    public static boolean versionCheckEnabled;
    public static int spreaderPositionShift;
    public static boolean flowerForceCheck;
    public static boolean enderPickpocketEnabled;
    public static boolean fallenKanadeEnabled;
    public static boolean darkQuartzEnabled;
    public static boolean enchanterEnabled;
    public static boolean fluxfieldEnabled;
    public static boolean relicsEnabled;
    public static boolean stones18Enabled;
    public static boolean ringOfOdinFireResist;
    public static boolean enderStuff19Enabled;
    public static boolean invertMagnetRing;
    public static boolean enableThaumcraftStablizers;
    public static int harvestLevelWeight;
    public static int harvestLevelBore;
    public static int flowerQuantity;
    public static int flowerDensity;
    public static int flowerPatchSize;
    public static int flowerPatchChance;
    public static double flowerTallChance;
    public static int mushroomQuantity;
    private static boolean verifiedPotionArray;
    private static int potionArrayLimit;
    public static int potionIDSoulCross;
    public static int potionIDFeatherfeet;
    public static int potionIDEmptiness;
    public static int potionIDBloodthirst;
    public static int potionIDAllure;
    public static int potionIDClear;

    public static void loadConfig(File configFile) {
        config = new Configuration(configFile);
        config.load();
        ConfigHandler.load();
        FMLCommonHandler.instance().bus().register((Object)new ChangeListener());
    }

    public static void load() {
        String desc = "Set this to false to disable the Adaptative Config. Adaptative Config changes any default config values from old versions to the new defaults to make sure you aren't missing out on changes because of old configs. It will not touch any values that were changed manually.";
        useAdaptativeConfig = ConfigHandler.loadPropBool("adaptativeConfig.enabled", desc, useAdaptativeConfig);
        adaptor = new ConfigAdaptor(useAdaptativeConfig);
        desc = "Set this to false to disable the use of shaders for some of the mod's renders.";
        useShaders = ConfigHandler.loadPropBool("shaders.enabled", desc, useShaders);
        desc = "Set this to false to disable the rotating items in the petal and rune entries in the Lexica Botania.";
        lexiconRotatingItems = ConfigHandler.loadPropBool("lexicon.enable.rotatingItems", desc, lexiconRotatingItems);
        desc = "Set this to true to enable justified text in the Lexica Botania's text pages.";
        lexiconJustifiedText = ConfigHandler.loadPropBool("lexicon.enable.justifiedText", desc, lexiconJustifiedText);
        desc = "Set this to true to set the power system's particles to be a lot more subtle. Good for low-end systems, if the particles are causing lag.";
        subtlePowerSystem = ConfigHandler.loadPropBool("powerSystem.subtle", desc, subtlePowerSystem);
        desc = "Set this to true to use a static wand beam that shows every single position of the burst, similar to the way it used to work on old Botania versions. Warning: Disabled by default because it may be laggy.";
        staticWandBeam = ConfigHandler.loadPropBool("wandBeam.static", desc, staticWandBeam);
        desc = "Set this to false to disable the wireframe when looking a block bound to something (spreaders, flowers, etc).";
        boundBlockWireframe = ConfigHandler.loadPropBool("boundBlock.wireframe.enabled", desc, boundBlockWireframe);
        desc = "Set this to false to disable the animated 3D render for the Lexica Botania.";
        lexicon3dModel = ConfigHandler.loadPropBool("lexicon.render.3D", desc, lexicon3dModel);
        desc = "Set this to true to use the old (non-.obj, pre beta18) pylon model";
        oldPylonModel = ConfigHandler.loadPropBool("pylonModel.old", desc, oldPylonModel);
        desc = "The frequency in which particles spawn from normal (worldgen) mystical flowers";
        flowerParticleFrequency = ConfigHandler.loadPropDouble("flowerParticles.frequency", desc, flowerParticleFrequency);
        desc = "Set this to false to remove the block breaking particles from the flowers and other items in the mod.";
        blockBreakParticles = ConfigHandler.loadPropBool("blockBreakingParticles.enabled", desc, blockBreakParticles);
        desc = "Set this to false to remove the block breaking particles from the Mana Shatterer, as there can be a good amount in higher levels.";
        blockBreakParticlesTool = ConfigHandler.loadPropBool("blockBreakingParticlesTool.enabled", desc, blockBreakParticlesTool);
        desc = "Set this to false to disable the particles in the elven portal.";
        elfPortalParticlesEnabled = ConfigHandler.loadPropBool("elfPortal.particles.enabled", desc, elfPortalParticlesEnabled);
        desc = "Set this to false to disable the animation when an item is charging on top of a mana pool.";
        chargingAnimationEnabled = ConfigHandler.loadPropBool("chargeAnimation.enabled", desc, chargingAnimationEnabled);
        desc = "Set this to false to always display all particles regardless of the \"Particles\" setting in the Vanilla options menu.";
        useVanillaParticleLimiter = ConfigHandler.loadPropBool("vanillaParticleConfig.enabled", desc, useVanillaParticleLimiter);
        desc = "Set this to true to disable the mana spreader shooting sound.";
        silentSpreaders = ConfigHandler.loadPropBool("manaSpreaders.silent", desc, silentSpreaders);
        desc = "Set this to false to disable rendering of baubles in the player.";
        renderBaubles = ConfigHandler.loadPropBool("baubleRender.enabled", desc, renderBaubles);
        desc = "Set this to false to disable seasonal features, such as halloween and christmas.";
        enableSeasonalFeatures = ConfigHandler.loadPropBool("seasonalFeatures.enabled", desc, enableSeasonalFeatures);
        desc = "Set this to true to use Shift instead of Ctrl for the inventory lexica botania quick lookup feature.";
        useShiftForQuickLookup = ConfigHandler.loadPropBool("quickLookup.useShift", desc, useShiftForQuickLookup);
        desc = "Set this to false to disable custom armor models.";
        enableArmorModels = ConfigHandler.loadPropBool("armorModels.enable", desc, enableArmorModels);
        desc = "Set this to false to disable the fancy skybox in Garden of Glass.";
        enableFancySkybox = ConfigHandler.loadPropBool("fancySkybox.enable", desc, enableFancySkybox);
        desc = "Set this to true to enable the fancy skybox in non Garden of Glass worlds. (Does not require Garden of Glass loaded to use, needs 'fancySkybox.enable' to be true as well)";
        enableFancySkyboxInNormalWorlds = ConfigHandler.loadPropBool("fancySkybox.normalWorlds", desc, enableFancySkyboxInNormalWorlds);
        desc = "The height of the mana display bar in above the XP bar. You can change this if you have a mod that changes where the XP bar is.";
        manaBarHeight = ConfigHandler.loadPropInt("manaBar.height", desc, manaBarHeight);
        desc = "The height of the Flugel Tiara flight bar. You can change this if you have a mod that adds a bar in that spot.";
        flightBarHeight = ConfigHandler.loadPropInt("flightBar.height", desc, flightBarHeight);
        desc = "The height of the Flugel Tiara flight bar if your breath bar is shown. You can change this if you have a mod that adds a bar in that spot.";
        flightBarBreathHeight = ConfigHandler.loadPropInt("flightBarBreath.height", desc, flightBarBreathHeight);
        desc = "The GL Texture Unit to use for the secondary sampler passed in to the Lexica Botania's category button shader. DO NOT TOUCH THIS IF YOU DON'T KNOW WHAT YOU'RE DOING";
        glSecondaryTextureUnit = ConfigHandler.loadPropInt("shaders.secondaryUnit", desc, glSecondaryTextureUnit);
        desc = "Set this to true to use alternate flower textures by Futureazoo, not all flowers are textured. http://redd.it/2b3o3f";
        altFlowerTextures = ConfigHandler.loadPropBool("flowerTextures.alt", desc, altFlowerTextures);
        desc = "Set this to true if you are the chosen one. For lovers of glitch art and just general mad people.";
        matrixMode = ConfigHandler.loadPropBool("matrixMode.enabled", desc, matrixMode);
        desc = "Set this to false to disable the references in the flower tooltips. (You monster D:)";
        referencesEnabled = ConfigHandler.loadPropBool("references.enabled", desc, referencesEnabled);
        desc = "Set this to false to disable checking and alerting when new Botania versions come out. (keywords for noobs: update notification message)";
        versionCheckEnabled = ConfigHandler.loadPropBool("versionChecking.enabled", desc, versionCheckEnabled);
        desc = "Do not ever touch this value if not asked to. Possible symptoms of doing so include your head turning backwards, the appearance of Titans near the walls or you being trapped in a game of Sword Art Online.";
        spreaderPositionShift = ConfigHandler.loadPropInt("spreader.posShift", desc, spreaderPositionShift);
        desc = "Turn this off ONLY IF you're on an extremely large world with an exaggerated count of Mana Spreaders/Mana Pools and are experiencing TPS lag. This toggles whether flowers are strict with their checking for connecting to pools/spreaders or just check whenever possible.";
        flowerForceCheck = ConfigHandler.loadPropBool("flower.forceCheck", desc, flowerForceCheck);
        desc = "Set to false to disable the ability for the Hand of Ender to pickpocket other players' ender chests.";
        enderPickpocketEnabled = ConfigHandler.loadPropBool("enderPickpocket.enabled", desc, enderPickpocketEnabled);
        desc = "Set this to false to disable the Fallen Kanade flower (gives Regeneration). This config option is here for those using Blood Magic. Note: Turning this off will not remove ones already in the world, it'll simply prevent the crafting.";
        fallenKanadeEnabled = ConfigHandler.loadPropBool("fallenKanade.enabled", desc, fallenKanadeEnabled);
        desc = "Set this to false to disable the Smokey Quartz blocks. This config option is here for those using Thaumic Tinkerer";
        darkQuartzEnabled = ConfigHandler.loadPropBool("darkQuartz.enabled", desc, darkQuartzEnabled);
        desc = "Set this to false to disable the Mana Enchanter. Since some people find it OP or something. This only disables the entry and creation. Old ones that are already in the world will stay.";
        enchanterEnabled = ConfigHandler.loadPropBool("manaEnchanter.enabled", desc, enchanterEnabled);
        desc = "Set this to false to disable the Mana Fluxfield (generates RF from mana). This only disables the entry and creation. Old ones that are already in the world will stay.";
        fluxfieldEnabled = ConfigHandler.loadPropBool("manaFluxfield.enabled", desc, fluxfieldEnabled);
        desc = "Set this to false to disable the Relic System. This only disables the entries, drops and achievements. Old ones that are already in the world will stay.";
        relicsEnabled = ConfigHandler.loadPropBool("relics.enabled", desc, relicsEnabled);
        desc = "Set this to false to disable the 1.8 Stones available as mana alchemy recipes. This only disables the recipes and entries. Old ones that are already in the world will stay.";
        stones18Enabled = ConfigHandler.loadPropBool("18stones.enabled", desc, stones18Enabled);
        desc = "Set this to false to make the Ring of Odin not apply fire resistance. Mostly for people who use Witchery transformations.";
        ringOfOdinFireResist = ConfigHandler.loadPropBool("ringOfOdin.fireResist", desc, ringOfOdinFireResist);
        desc = "Set this to false to disable the 1.9 Ender features available as recipes. This only disables the recipes and entries. Old ones that are already in the world will stay.";
        enderStuff19Enabled = ConfigHandler.loadPropBool("19enderStuff.enabled", desc, enderStuff19Enabled);
        desc = "Set this to true to invert the Ring of Magnetization's controls (from shift to stop to shift to work)";
        invertMagnetRing = ConfigHandler.loadPropBool("magnetRing.invert", desc, invertMagnetRing);
        desc = "Set this to false to disable Thaumcraft Infusion Stabilizing in botania blocks";
        enableThaumcraftStablizers = ConfigHandler.loadPropBool("thaumraftStabilizers.enabled", desc, enableThaumcraftStablizers);
        desc = "The harvest level of the Mana Lens: Weight. 3 is diamond level. Defaults to 2 (iron level)";
        harvestLevelWeight = ConfigHandler.loadPropInt("harvestLevel.weightLens", desc, harvestLevelWeight);
        desc = "The harvest level of the Mana Lens: Bore. 3 is diamond level. Defaults to 3";
        harvestLevelBore = ConfigHandler.loadPropInt("harvestLevel.boreLens", desc, harvestLevelBore);
        desc = "The quantity of Botania flower patches to generate in the world, defaults to 2, the lower the number the less patches generate.";
        flowerQuantity = ConfigHandler.loadPropInt("worldgen.flower.quantity", desc, flowerQuantity);
        desc = "The amount of time it takes a Passive flower to decay and turn into a dead bush. Defaults to 72000, 60 minutes. Setting this to -1 disables the feature altogether.";
        hardcorePassiveGeneration = ConfigHandler.loadPropInt("passiveDecay.time", desc, hardcorePassiveGeneration);
        desc = "The density of each Botania flower patch generated, defaults to 2, the lower the number, the less each patch will have.";
        adaptor.addMappingInt(0, "worldgen.flower.density", 16);
        adaptor.addMappingInt(238, "worldgen.flower.density", 2);
        flowerDensity = ConfigHandler.loadPropInt("worldgen.flower.density", desc, flowerDensity);
        desc = "The size of each Botania flower patch, defaults to 6. The larger this is the farther the each patch can spread";
        flowerPatchSize = ConfigHandler.loadPropInt("worldgen.flower.patchSize", desc, flowerPatchSize);
        desc = "The inverse chance for a Botania flower patch to be generated, defaults to 16. The higher this value is the less patches will exist and the more flower each will have.";
        adaptor.addMappingInt(0, "worldgen.flower.patchChance", 4);
        adaptor.addMappingInt(238, "worldgen.flower.patchChance", 16);
        flowerPatchChance = ConfigHandler.loadPropInt("worldgen.flower.patchChance", desc, flowerPatchChance);
        desc = "The chance for a Botania flower generated in a patch to be a tall flower. 0.1 is 10%, 1 is 100%. Defaults to 0.05";
        adaptor.addMappingDouble(0, "worldgen.flower.tallChance", 0.1);
        adaptor.addMappingDouble(238, "worldgen.flower.tallChance", 0.05);
        flowerTallChance = ConfigHandler.loadPropDouble("worldgen.flower.tallChance", desc, flowerTallChance);
        desc = "The quantity of Botania mushrooms to generate underground, in the world, defaults to 40, the lower the number the less patches generate.";
        mushroomQuantity = ConfigHandler.loadPropInt("worldgen.mushroom.quantity", desc, mushroomQuantity);
        potionIDSoulCross = ConfigHandler.loadPropPotionId("soulCross", potionIDSoulCross);
        potionIDFeatherfeet = ConfigHandler.loadPropPotionId("featherFeet", potionIDFeatherfeet);
        potionIDEmptiness = ConfigHandler.loadPropPotionId("emptiness", potionIDEmptiness);
        potionIDBloodthirst = ConfigHandler.loadPropPotionId("bloodthirst", potionIDBloodthirst);
        potionIDAllure = ConfigHandler.loadPropPotionId("allure", potionIDAllure);
        potionIDClear = ConfigHandler.loadPropPotionId("clear", potionIDClear);
        if (config.hasChanged()) {
            config.save();
        }
    }

    public static void loadPostInit() {
        SheddingHandler.loadFromConfig(config);
        if (config.hasChanged()) {
            config.save();
        }
    }

    public static int loadPropInt(String propName, String desc, int default_) {
        Property prop = config.get("general", propName, default_);
        prop.comment = desc;
        if (adaptor != null) {
            adaptor.adaptPropertyInt(prop, prop.getInt(default_));
        }
        return prop.getInt(default_);
    }

    public static double loadPropDouble(String propName, String desc, double default_) {
        Property prop = config.get("general", propName, default_);
        prop.comment = desc;
        if (adaptor != null) {
            adaptor.adaptPropertyDouble(prop, prop.getDouble(default_));
        }
        return prop.getDouble(default_);
    }

    public static boolean loadPropBool(String propName, String desc, boolean default_) {
        Property prop = config.get("general", propName, default_);
        prop.comment = desc;
        if (adaptor != null) {
            adaptor.adaptPropertyBool(prop, prop.getBoolean(default_));
        }
        return prop.getBoolean(default_);
    }

    public static int loadPropPotionId(String propName, int default_) {
        Property prop;
        int val;
        if (!verifiedPotionArray) {
            ConfigHandler.verifyPotionArray();
        }
        if ((val = (prop = config.get(CATEGORY_POTIONS, propName, default_)).getInt(default_)) > potionArrayLimit) {
            val = default_;
            prop.set(default_);
        }
        return val;
    }

    private static void verifyPotionArray() {
        potionArrayLimit = Loader.isModLoaded((String)"DragonAPI") ? Potion.field_76425_a.length : 127;
        verifiedPotionArray = true;
    }

    static {
        hardcorePassiveGeneration = 72000;
        useAdaptativeConfig = true;
        useShaders = true;
        lexiconRotatingItems = true;
        lexiconJustifiedText = false;
        subtlePowerSystem = false;
        staticWandBeam = false;
        boundBlockWireframe = true;
        lexicon3dModel = true;
        oldPylonModel = false;
        flowerParticleFrequency = 0.75;
        blockBreakParticles = true;
        blockBreakParticlesTool = true;
        elfPortalParticlesEnabled = true;
        chargingAnimationEnabled = true;
        useVanillaParticleLimiter = true;
        silentSpreaders = false;
        renderBaubles = true;
        enableSeasonalFeatures = true;
        useShiftForQuickLookup = false;
        enableArmorModels = true;
        enableFancySkybox = true;
        enableFancySkyboxInNormalWorlds = false;
        manaBarHeight = 29;
        flightBarHeight = 49;
        flightBarBreathHeight = 59;
        glSecondaryTextureUnit = 7;
        altFlowerTextures = false;
        matrixMode = false;
        referencesEnabled = true;
        versionCheckEnabled = true;
        spreaderPositionShift = 1;
        flowerForceCheck = true;
        enderPickpocketEnabled = true;
        fallenKanadeEnabled = true;
        darkQuartzEnabled = true;
        enchanterEnabled = true;
        fluxfieldEnabled = true;
        relicsEnabled = true;
        stones18Enabled = true;
        ringOfOdinFireResist = true;
        enderStuff19Enabled = true;
        invertMagnetRing = false;
        enableThaumcraftStablizers = true;
        harvestLevelWeight = 2;
        harvestLevelBore = 3;
        flowerQuantity = 2;
        flowerDensity = 2;
        flowerPatchSize = 6;
        flowerPatchChance = 16;
        flowerTallChance = 0.05;
        mushroomQuantity = 40;
        verifiedPotionArray = false;
        potionArrayLimit = 0;
        potionIDSoulCross = 91;
        potionIDFeatherfeet = 92;
        potionIDEmptiness = 93;
        potionIDBloodthirst = 94;
        potionIDAllure = 95;
        potionIDClear = 96;
    }

    public static class ChangeListener {
        @SubscribeEvent
        public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
            if (eventArgs.modID.equals("Botania")) {
                ConfigHandler.load();
            }
        }
    }

    public static class ConfigAdaptor {
        private boolean enabled;
        private int lastBuild;
        private int currentBuild;
        private Map<String, List<AdaptableValue>> adaptableValues = new HashMap<String, List<AdaptableValue>>();
        private List<String> changes = new ArrayList<String>();

        public ConfigAdaptor(boolean enabled) {
            this.enabled = enabled;
            String lastVersion = Botania.proxy.getLastVersion();
            try {
                this.lastBuild = Integer.parseInt(lastVersion);
                this.currentBuild = Integer.parseInt("249");
            }
            catch (NumberFormatException e) {
                this.enabled = false;
            }
        }

        public <T> void adaptProperty(Property prop, T val) {
            if (!this.enabled) {
                return;
            }
            String name = prop.getName();
            if (!this.adaptableValues.containsKey(name)) {
                return;
            }
            AdaptableValue bestValue = null;
            for (AdaptableValue value : this.adaptableValues.get(name)) {
                if (value.version >= this.lastBuild || bestValue != null && value.version <= bestValue.version) continue;
                bestValue = value;
            }
            if (bestValue != null) {
                Object expected = bestValue.value;
                String def = prop.getDefault();
                if (this.areEqualNumbers(val, expected) && !this.areEqualNumbers(val, def)) {
                    prop.setValue(def.toString());
                    this.changes.add(" " + prop.getName() + ": " + val + " -> " + def);
                }
            }
        }

        public <T> void addMapping(int version, String key, T val) {
            List<Object> list;
            if (!this.enabled) {
                return;
            }
            AdaptableValue<T> adapt = new AdaptableValue<T>(version, val);
            if (!this.adaptableValues.containsKey(key)) {
                list = new ArrayList();
                this.adaptableValues.put(key, list);
            }
            list = this.adaptableValues.get(key);
            list.add(adapt);
        }

        public boolean areEqualNumbers(Object v1, Object v2) {
            double epsilon = 1.0E-6;
            float v1f = ((Number)v1).floatValue();
            float v2f = v2 instanceof String ? Float.parseFloat((String)v2) : ((Number)v2).floatValue();
            return (double)Math.abs(v1f - v2f) < epsilon;
        }

        public void tellChanges(EntityPlayer player) {
            if (this.changes.size() == 0) {
                return;
            }
            player.func_146105_b(new ChatComponentTranslation("botaniamisc.adaptativeConfigChanges", new Object[0]).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.GOLD)));
            for (String change : this.changes) {
                player.func_145747_a(new ChatComponentText(change).func_150255_a(new ChatStyle().func_150238_a(EnumChatFormatting.LIGHT_PURPLE)));
            }
        }

        public void addMappingInt(int version, String key, int val) {
            this.addMapping(version, key, val);
        }

        public void addMappingDouble(int version, String key, double val) {
            this.addMapping(version, key, val);
        }

        public void addMappingBool(int version, String key, boolean val) {
            this.addMapping(version, key, val);
        }

        public void adaptPropertyInt(Property prop, int val) {
            this.adaptProperty(prop, val);
        }

        public void adaptPropertyDouble(Property prop, double val) {
            this.adaptProperty(prop, val);
        }

        public void adaptPropertyBool(Property prop, boolean val) {
            this.adaptProperty(prop, val);
        }

        public static class AdaptableValue<T> {
            public final int version;
            public final T value;
            public final Class<? extends T> valueType;

            public AdaptableValue(int version, T value) {
                this.version = version;
                this.value = value;
                this.valueType = value.getClass();
            }
        }
    }
}

