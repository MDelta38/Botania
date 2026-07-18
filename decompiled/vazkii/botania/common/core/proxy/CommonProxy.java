/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.FMLLog
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLInterModComms
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.event.FMLServerAboutToStartEvent
 *  cpw.mods.fml.common.event.FMLServerStartingEvent
 *  cpw.mods.fml.common.network.IGuiHandler
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.command.ICommand
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.boss.EntityDragon
 *  net.minecraft.entity.boss.EntityDragonPart
 *  net.minecraft.entity.boss.EntityWither
 *  net.minecraft.entity.item.EntityEnderCrystal
 *  net.minecraft.entity.item.EntityItemFrame
 *  net.minecraft.entity.item.EntityPainting
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemRecord
 *  net.minecraft.item.ItemStack
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  org.apache.logging.log4j.Level
 */
package vazkii.botania.common.core.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.block.Block;
import net.minecraft.command.ICommand;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Level;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.ITwoNamedPage;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.lexicon.LexiconPage;
import vazkii.botania.common.Botania;
import vazkii.botania.common.achievement.ModAchievements;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.ModMultiblocks;
import vazkii.botania.common.block.subtile.generating.SubTileNarslimmus;
import vazkii.botania.common.block.tile.TileLightRelay;
import vazkii.botania.common.block.tile.corporea.TileCorporeaIndex;
import vazkii.botania.common.brew.ModBrews;
import vazkii.botania.common.brew.ModPotions;
import vazkii.botania.common.core.command.CommandDownloadLatest;
import vazkii.botania.common.core.command.CommandOpen;
import vazkii.botania.common.core.command.CommandShare;
import vazkii.botania.common.core.command.CommandSkyblockSpread;
import vazkii.botania.common.core.handler.BiomeDecorationHandler;
import vazkii.botania.common.core.handler.ChestGenHandler;
import vazkii.botania.common.core.handler.CommonTickHandler;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.handler.InternalMethodHandler;
import vazkii.botania.common.core.handler.ManaNetworkHandler;
import vazkii.botania.common.core.handler.PixieHandler;
import vazkii.botania.common.core.handler.SheddingHandler;
import vazkii.botania.common.core.handler.SpawnerChangingHandler;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.crafting.ModBrewRecipes;
import vazkii.botania.common.crafting.ModCraftingRecipes;
import vazkii.botania.common.crafting.ModElvenTradeRecipes;
import vazkii.botania.common.crafting.ModManaAlchemyRecipes;
import vazkii.botania.common.crafting.ModManaConjurationRecipes;
import vazkii.botania.common.crafting.ModManaInfusionRecipes;
import vazkii.botania.common.crafting.ModPetalRecipes;
import vazkii.botania.common.crafting.ModPureDaisyRecipes;
import vazkii.botania.common.crafting.ModRuneRecipes;
import vazkii.botania.common.entity.EntityCorporeaSpark;
import vazkii.botania.common.entity.EntityDoppleganger;
import vazkii.botania.common.entity.EntityFlameRing;
import vazkii.botania.common.entity.EntityMagicLandmine;
import vazkii.botania.common.entity.EntityMagicMissile;
import vazkii.botania.common.entity.EntityManaBurst;
import vazkii.botania.common.entity.EntityPinkWither;
import vazkii.botania.common.entity.EntitySignalFlare;
import vazkii.botania.common.entity.EntitySpark;
import vazkii.botania.common.entity.ModEntities;
import vazkii.botania.common.integration.buildcraft.StatementAPIPlugin;
import vazkii.botania.common.integration.etfuturum.ModBanners;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.network.GuiHandler;
import vazkii.botania.common.world.SkyblockWorldEvents;
import vazkii.botania.common.world.WorldTypeSkyblock;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        BotaniaAPI.internalHandler = new InternalMethodHandler();
        ConfigHandler.loadConfig(event.getSuggestedConfigurationFile());
        ModBlocks.init();
        ModItems.init();
        ModEntities.init();
        ModPotions.init();
        ModBrews.init();
        ModCraftingRecipes.init();
        ModPetalRecipes.init();
        ModPureDaisyRecipes.init();
        ModRuneRecipes.init();
        ModManaAlchemyRecipes.init();
        ModManaConjurationRecipes.init();
        ModManaInfusionRecipes.init();
        ModElvenTradeRecipes.init();
        ModBrewRecipes.init();
        ModAchievements.init();
        ModMultiblocks.init();
        if (Botania.etFuturumLoaded) {
            ModBanners.init();
        }
        ChestGenHandler.init();
        LexiconData.init();
        if (Botania.gardenOfGlassLoaded) {
            new WorldTypeSkyblock();
        }
    }

    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)Botania.instance, (IGuiHandler)new GuiHandler());
        MinecraftForge.TERRAIN_GEN_BUS.register((Object)new BiomeDecorationHandler());
        MinecraftForge.EVENT_BUS.register((Object)ManaNetworkHandler.instance);
        MinecraftForge.EVENT_BUS.register((Object)new PixieHandler());
        MinecraftForge.EVENT_BUS.register((Object)new SheddingHandler());
        MinecraftForge.EVENT_BUS.register((Object)new SpawnerChangingHandler());
        MinecraftForge.EVENT_BUS.register((Object)new SubTileNarslimmus.SpawnIntercepter());
        MinecraftForge.EVENT_BUS.register((Object)TileCorporeaIndex.getInputHandler());
        if (Botania.gardenOfGlassLoaded) {
            MinecraftForge.EVENT_BUS.register((Object)new SkyblockWorldEvents());
        }
        FMLCommonHandler.instance().bus().register((Object)new CommonTickHandler());
        FMLInterModComms.sendMessage((String)"ProjectE", (String)"interdictionblacklist", (String)EntityManaBurst.class.getCanonicalName());
        if (Botania.bcTriggersLoaded) {
            new StatementAPIPlugin();
        }
    }

    public void postInit(FMLPostInitializationEvent event) {
        if (Botania.thaumcraftLoaded) {
            ModBrews.initTC();
            ModBrewRecipes.initTC();
        }
        ModBlocks.addDispenserBehaviours();
        ModBlocks.registerMultiparts();
        ConfigHandler.loadPostInit();
        LexiconData.postInit();
        this.registerNEIStuff();
        int words = 0;
        for (LexiconEntry entry : BotaniaAPI.getAllEntries()) {
            for (LexiconPage page : entry.pages) {
                words += this.countWords(page.getUnlocalizedName());
                if (!(page instanceof ITwoNamedPage)) continue;
                words += this.countWords(((ITwoNamedPage)((Object)page)).getSecondUnlocalizedName());
            }
        }
        FMLLog.log((Level)Level.INFO, (String)"[Botania] The Lexica Botania has %d words.", (Object[])new Object[]{words});
        this.registerDefaultEntityBlacklist();
    }

    private int countWords(String s) {
        String s1 = StatCollector.func_74838_a((String)s);
        return s1.split(" ").length;
    }

    private void registerDefaultEntityBlacklist() {
        BotaniaAPI.blacklistEntityFromGravityRod(EntityDragon.class);
        BotaniaAPI.blacklistEntityFromGravityRod(EntityDragonPart.class);
        BotaniaAPI.blacklistEntityFromGravityRod(EntityWither.class);
        BotaniaAPI.blacklistEntityFromGravityRod(EntityItemFrame.class);
        BotaniaAPI.blacklistEntityFromGravityRod(EntityEnderCrystal.class);
        BotaniaAPI.blacklistEntityFromGravityRod(EntityPainting.class);
        BotaniaAPI.blacklistEntityFromGravityRod(EntityCorporeaSpark.class);
        BotaniaAPI.blacklistEntityFromGravityRod(EntityDoppleganger.class);
        BotaniaAPI.blacklistEntityFromGravityRod(EntityFlameRing.class);
        BotaniaAPI.blacklistEntityFromGravityRod(EntityMagicLandmine.class);
        BotaniaAPI.blacklistEntityFromGravityRod(EntityMagicMissile.class);
        BotaniaAPI.blacklistEntityFromGravityRod(EntityManaBurst.class);
        BotaniaAPI.blacklistEntityFromGravityRod(EntityPinkWither.class);
        BotaniaAPI.blacklistEntityFromGravityRod(EntitySignalFlare.class);
        BotaniaAPI.blacklistEntityFromGravityRod(EntitySpark.class);
        BotaniaAPI.blacklistEntityFromGravityRod(TileLightRelay.EntityPlayerMover.class);
    }

    public void serverAboutToStart(FMLServerAboutToStartEvent event) {
        String expect;
        String clname = BotaniaAPI.internalHandler.getClass().getName();
        if (!clname.equals(expect = "vazkii.botania.common.core.handler.InternalMethodHandler")) {
            new IllegalAccessError("The Botania API internal method handler has been overriden. This will cause crashes and compatibility issues, and that's why it's marked as \"Do not Override\". Whoever had the brilliant idea of overriding it needs to go back to elementary school and learn to read. (Expected classname: " + expect + ", Actual classname: " + clname + ")").printStackTrace();
            FMLCommonHandler.instance().exitJava(1, true);
        }
    }

    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand((ICommand)new CommandDownloadLatest());
        event.registerServerCommand((ICommand)new CommandShare());
        event.registerServerCommand((ICommand)new CommandOpen());
        if (Botania.gardenOfGlassLoaded) {
            event.registerServerCommand((ICommand)new CommandSkyblockSpread());
        }
    }

    public void registerNEIStuff() {
    }

    public void setEntryToOpen(LexiconEntry entry) {
    }

    public void setToTutorialIfFirstLaunch() {
    }

    public void setLexiconStack(ItemStack stack) {
    }

    public boolean isTheClientPlayer(EntityLivingBase entity) {
        return false;
    }

    public boolean isClientPlayerWearingMonocle() {
        return false;
    }

    public String getLastVersion() {
        return "249";
    }

    public void setExtraReach(EntityLivingBase entity, float reach) {
        if (entity instanceof EntityPlayerMP) {
            ((EntityPlayerMP)entity).field_71134_c.setBlockReachDistance(Math.max(5.0, ((EntityPlayerMP)entity).field_71134_c.getBlockReachDistance() + (double)reach));
        }
    }

    public boolean openWikiPage(World world, Block block, MovingObjectPosition pos) {
        return false;
    }

    public void playRecordClientSided(World world, int x, int y, int z, ItemRecord record) {
    }

    public void setMultiblock(World world, int x, int y, int z, double radius, Block block) {
    }

    public void removeSextantMultiblock() {
    }

    public long getWorldElapsedTicks() {
        return MinecraftServer.func_71276_C().field_71305_c[0].func_82737_E();
    }

    public void setSparkleFXNoClip(boolean noclip) {
    }

    public void setSparkleFXCorrupt(boolean corrupt) {
    }

    public void sparkleFX(World world, double x, double y, double z, float r, float g, float b, float size, int m) {
        this.sparkleFX(world, x, y, z, r, g, b, size, m, false);
    }

    public void sparkleFX(World world, double x, double y, double z, float r, float g, float b, float size, int m, boolean fake) {
    }

    public void setWispFXDistanceLimit(boolean limit) {
    }

    public void setWispFXDepthTest(boolean depth) {
    }

    public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size) {
        this.wispFX(world, x, y, z, r, g, b, size, 0.0f);
    }

    public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float gravity) {
        this.wispFX(world, x, y, z, r, g, b, size, gravity, 1.0f);
    }

    public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float gravity, float maxAgeMul) {
        this.wispFX(world, x, y, z, r, g, b, size, 0.0f, -gravity, 0.0f, maxAgeMul);
    }

    public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float motionx, float motiony, float motionz) {
        this.wispFX(world, x, y, z, r, g, b, size, motionx, motiony, motionz, 1.0f);
    }

    public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float motionx, float motiony, float motionz, float maxAgeMul) {
    }

    public void lightningFX(World world, Vector3 vectorStart, Vector3 vectorEnd, float ticksPerMeter, int colorOuter, int colorInner) {
        this.lightningFX(world, vectorStart, vectorEnd, ticksPerMeter, System.nanoTime(), colorOuter, colorInner);
    }

    public void lightningFX(World world, Vector3 vectorStart, Vector3 vectorEnd, float ticksPerMeter, long seed, int colorOuter, int colorInner) {
    }
}

