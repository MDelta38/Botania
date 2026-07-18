/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.FMLLog
 *  cpw.mods.fml.common.Optional$Method
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.relauncher.ReflectionHelper
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.multiplayer.PlayerControllerMP
 *  net.minecraft.client.network.NetHandlerPlayClient
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderSnowball
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemRecord
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntitySkull
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldSettings$GameType
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.MinecraftForgeClient
 *  net.minecraftforge.common.MinecraftForge
 */
package vazkii.botania.client.core.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Calendar;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import vazkii.botania.api.item.IExtendedPlayerController;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.lexicon.multiblock.IMultiblockRenderHook;
import vazkii.botania.api.lexicon.multiblock.Multiblock;
import vazkii.botania.api.lexicon.multiblock.MultiblockSet;
import vazkii.botania.api.lexicon.multiblock.component.AnyComponent;
import vazkii.botania.api.wiki.IWikiProvider;
import vazkii.botania.api.wiki.WikiHooks;
import vazkii.botania.client.challenge.ModChallenges;
import vazkii.botania.client.core.handler.BaubleRenderHandler;
import vazkii.botania.client.core.handler.BotaniaPlayerController;
import vazkii.botania.client.core.handler.BoundTileRenderer;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.core.handler.CorporeaAutoCompleteHandler;
import vazkii.botania.client.core.handler.DebugHandler;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.client.core.handler.LightningHandler;
import vazkii.botania.client.core.handler.MultiblockRenderHandler;
import vazkii.botania.client.core.handler.PersistentVariableHelper;
import vazkii.botania.client.core.handler.SubTileRadiusRenderHandler;
import vazkii.botania.client.core.handler.TooltipHandler;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.client.fx.FXSparkle;
import vazkii.botania.client.fx.FXWisp;
import vazkii.botania.client.gui.lexicon.GuiLexicon;
import vazkii.botania.client.gui.lexicon.GuiLexiconEntry;
import vazkii.botania.client.gui.lexicon.GuiLexiconIndex;
import vazkii.botania.client.integration.nei.NEIGuiHooks;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.client.render.block.RenderAltar;
import vazkii.botania.client.render.block.RenderAvatar;
import vazkii.botania.client.render.block.RenderBellows;
import vazkii.botania.client.render.block.RenderBrewery;
import vazkii.botania.client.render.block.RenderCocoon;
import vazkii.botania.client.render.block.RenderCorporeaCrystalCube;
import vazkii.botania.client.render.block.RenderCorporeaIndex;
import vazkii.botania.client.render.block.RenderDoubleFlower;
import vazkii.botania.client.render.block.RenderFloatingFlower;
import vazkii.botania.client.render.block.RenderHourglass;
import vazkii.botania.client.render.block.RenderIncensePlate;
import vazkii.botania.client.render.block.RenderPool;
import vazkii.botania.client.render.block.RenderPump;
import vazkii.botania.client.render.block.RenderPylon;
import vazkii.botania.client.render.block.RenderSpawnerClaw;
import vazkii.botania.client.render.block.RenderSpecialFlower;
import vazkii.botania.client.render.block.RenderSpreader;
import vazkii.botania.client.render.block.RenderTeruTeruBozu;
import vazkii.botania.client.render.block.RenderTinyPotato;
import vazkii.botania.client.render.entity.RenderBabylonWeapon;
import vazkii.botania.client.render.entity.RenderCorporeaSpark;
import vazkii.botania.client.render.entity.RenderDoppleganger;
import vazkii.botania.client.render.entity.RenderManaStorm;
import vazkii.botania.client.render.entity.RenderPinkWither;
import vazkii.botania.client.render.entity.RenderPixie;
import vazkii.botania.client.render.entity.RenderPoolMinecart;
import vazkii.botania.client.render.entity.RenderSpark;
import vazkii.botania.client.render.entity.RenderThornChakram;
import vazkii.botania.client.render.item.RenderBow;
import vazkii.botania.client.render.item.RenderFloatingFlowerItem;
import vazkii.botania.client.render.item.RenderLens;
import vazkii.botania.client.render.item.RenderLexicon;
import vazkii.botania.client.render.item.RenderTransparentItem;
import vazkii.botania.client.render.tile.RenderTileAlfPortal;
import vazkii.botania.client.render.tile.RenderTileAltar;
import vazkii.botania.client.render.tile.RenderTileAvatar;
import vazkii.botania.client.render.tile.RenderTileBellows;
import vazkii.botania.client.render.tile.RenderTileBrewery;
import vazkii.botania.client.render.tile.RenderTileCocoon;
import vazkii.botania.client.render.tile.RenderTileCorporeaCrystalCube;
import vazkii.botania.client.render.tile.RenderTileCorporeaIndex;
import vazkii.botania.client.render.tile.RenderTileEnchanter;
import vazkii.botania.client.render.tile.RenderTileFloatingFlower;
import vazkii.botania.client.render.tile.RenderTileHourglass;
import vazkii.botania.client.render.tile.RenderTileIncensePlate;
import vazkii.botania.client.render.tile.RenderTileLightRelay;
import vazkii.botania.client.render.tile.RenderTilePool;
import vazkii.botania.client.render.tile.RenderTilePrism;
import vazkii.botania.client.render.tile.RenderTilePump;
import vazkii.botania.client.render.tile.RenderTilePylon;
import vazkii.botania.client.render.tile.RenderTileRedString;
import vazkii.botania.client.render.tile.RenderTileRuneAltar;
import vazkii.botania.client.render.tile.RenderTileSkullOverride;
import vazkii.botania.client.render.tile.RenderTileSparkChanger;
import vazkii.botania.client.render.tile.RenderTileSpawnerClaw;
import vazkii.botania.client.render.tile.RenderTileSpreader;
import vazkii.botania.client.render.tile.RenderTileStarfield;
import vazkii.botania.client.render.tile.RenderTileTerraPlate;
import vazkii.botania.client.render.tile.RenderTileTeruTeruBozu;
import vazkii.botania.client.render.tile.RenderTileTinyPotato;
import vazkii.botania.client.render.world.SkyblockRenderEvents;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileAlfPortal;
import vazkii.botania.common.block.tile.TileAltar;
import vazkii.botania.common.block.tile.TileAvatar;
import vazkii.botania.common.block.tile.TileBrewery;
import vazkii.botania.common.block.tile.TileCocoon;
import vazkii.botania.common.block.tile.TileEnchanter;
import vazkii.botania.common.block.tile.TileFloatingFlower;
import vazkii.botania.common.block.tile.TileFloatingSpecialFlower;
import vazkii.botania.common.block.tile.TileGaiaHead;
import vazkii.botania.common.block.tile.TileHourglass;
import vazkii.botania.common.block.tile.TileIncensePlate;
import vazkii.botania.common.block.tile.TileLightRelay;
import vazkii.botania.common.block.tile.TilePylon;
import vazkii.botania.common.block.tile.TileRuneAltar;
import vazkii.botania.common.block.tile.TileSparkChanger;
import vazkii.botania.common.block.tile.TileSpawnerClaw;
import vazkii.botania.common.block.tile.TileStarfield;
import vazkii.botania.common.block.tile.TileTerraPlate;
import vazkii.botania.common.block.tile.TileTeruTeruBozu;
import vazkii.botania.common.block.tile.TileTinyPotato;
import vazkii.botania.common.block.tile.corporea.TileCorporeaCrystalCube;
import vazkii.botania.common.block.tile.corporea.TileCorporeaIndex;
import vazkii.botania.common.block.tile.mana.TileBellows;
import vazkii.botania.common.block.tile.mana.TilePool;
import vazkii.botania.common.block.tile.mana.TilePrism;
import vazkii.botania.common.block.tile.mana.TilePump;
import vazkii.botania.common.block.tile.mana.TileSpreader;
import vazkii.botania.common.block.tile.string.TileRedString;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.helper.MathHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.core.proxy.CommonProxy;
import vazkii.botania.common.core.version.AdaptorNotifier;
import vazkii.botania.common.core.version.VersionChecker;
import vazkii.botania.common.entity.EntityBabylonWeapon;
import vazkii.botania.common.entity.EntityCorporeaSpark;
import vazkii.botania.common.entity.EntityDoppleganger;
import vazkii.botania.common.entity.EntityEnderAirBottle;
import vazkii.botania.common.entity.EntityManaStorm;
import vazkii.botania.common.entity.EntityPinkWither;
import vazkii.botania.common.entity.EntityPixie;
import vazkii.botania.common.entity.EntityPoolMinecart;
import vazkii.botania.common.entity.EntitySpark;
import vazkii.botania.common.entity.EntityThornChakram;
import vazkii.botania.common.entity.EntityVineBall;
import vazkii.botania.common.item.ItemSextant;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.bauble.ItemMonocle;
import vazkii.botania.common.lexicon.LexiconData;
import vazkii.botania.common.lib.LibObfuscation;

public class ClientProxy
extends CommonProxy {
    public static boolean jingleTheBells = false;
    public static boolean dootDoot = false;
    private static boolean noclipEnabled = false;
    private static boolean corruptSparkle = false;
    private static boolean distanceLimit = true;
    private static boolean depthTest = true;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        PersistentVariableHelper.setCacheFile(new File(Minecraft.func_71410_x().field_71412_D, "BotaniaVars.dat"));
        try {
            PersistentVariableHelper.load();
            PersistentVariableHelper.save();
        }
        catch (IOException e) {
            FMLLog.severe((String)"Botania's persistent Variables couldn't load!", (Object[])new Object[0]);
            e.printStackTrace();
        }
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        ModChallenges.init();
        FMLCommonHandler.instance().bus().register((Object)new ClientTickHandler());
        MinecraftForge.EVENT_BUS.register((Object)new HUDHandler());
        MinecraftForge.EVENT_BUS.register((Object)new LightningHandler());
        if (ConfigHandler.boundBlockWireframe) {
            MinecraftForge.EVENT_BUS.register((Object)new BoundTileRenderer());
        }
        MinecraftForge.EVENT_BUS.register((Object)new TooltipHandler());
        MinecraftForge.EVENT_BUS.register((Object)new BaubleRenderHandler());
        MinecraftForge.EVENT_BUS.register((Object)new DebugHandler());
        MinecraftForge.EVENT_BUS.register((Object)new SubTileRadiusRenderHandler());
        MinecraftForge.EVENT_BUS.register((Object)new MultiblockRenderHandler());
        MinecraftForge.EVENT_BUS.register((Object)new SkyblockRenderEvents());
        FMLCommonHandler.instance().bus().register((Object)new CorporeaAutoCompleteHandler());
        if (ConfigHandler.useAdaptativeConfig) {
            FMLCommonHandler.instance().bus().register((Object)new AdaptorNotifier());
        }
        if (ConfigHandler.versionCheckEnabled) {
            new VersionChecker().init();
        }
        if (ConfigHandler.enableSeasonalFeatures) {
            Calendar calendar = Calendar.getInstance();
            if (calendar.get(2) == 11 && calendar.get(5) >= 16 || calendar.get(2) == 0 && calendar.get(5) <= 2) {
                jingleTheBells = true;
            }
            if (calendar.get(2) == 9) {
                dootDoot = true;
            }
        }
        this.initRenderers();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        CorporeaAutoCompleteHandler.updateItemList();
    }

    private void initRenderers() {
        LibRenderIDs.idAltar = RenderingRegistry.getNextAvailableRenderId();
        LibRenderIDs.idSpecialFlower = RenderingRegistry.getNextAvailableRenderId();
        LibRenderIDs.idSpreader = RenderingRegistry.getNextAvailableRenderId();
        LibRenderIDs.idPool = RenderingRegistry.getNextAvailableRenderId();
        LibRenderIDs.idPylon = RenderingRegistry.getNextAvailableRenderId();
        LibRenderIDs.idMiniIsland = RenderingRegistry.getNextAvailableRenderId();
        LibRenderIDs.idTinyPotato = RenderingRegistry.getNextAvailableRenderId();
        LibRenderIDs.idSpawnerClaw = RenderingRegistry.getNextAvailableRenderId();
        LibRenderIDs.idBrewery = RenderingRegistry.getNextAvailableRenderId();
        LibRenderIDs.idCorporeaIndex = RenderingRegistry.getNextAvailableRenderId();
        LibRenderIDs.idPump = RenderingRegistry.getNextAvailableRenderId();
        LibRenderIDs.idDoubleFlower = RenderingRegistry.getNextAvailableRenderId();
        LibRenderIDs.idCorporeaCrystalCybe = RenderingRegistry.getNextAvailableRenderId();
        LibRenderIDs.idIncensePlate = RenderingRegistry.getNextAvailableRenderId();
        LibRenderIDs.idHourglass = RenderingRegistry.getNextAvailableRenderId();
        LibRenderIDs.idCocoon = RenderingRegistry.getNextAvailableRenderId();
        LibRenderIDs.idLightRelay = RenderingRegistry.getNextAvailableRenderId();
        LibRenderIDs.idBellows = RenderingRegistry.getNextAvailableRenderId();
        LibRenderIDs.idTeruTeruBozu = RenderingRegistry.getNextAvailableRenderId();
        LibRenderIDs.idAvatar = RenderingRegistry.getNextAvailableRenderId();
        RenderSpecialFlower specialFlowerRender = new RenderSpecialFlower(LibRenderIDs.idSpecialFlower);
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderAltar());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)specialFlowerRender);
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderSpreader());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderPool());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderPylon());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderFloatingFlower());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderTinyPotato());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderSpawnerClaw());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderBrewery());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderCorporeaIndex());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderPump());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderDoubleFlower());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderCorporeaCrystalCube());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderIncensePlate());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderHourglass());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderCocoon());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderBellows());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderTeruTeruBozu());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderAvatar());
        IMultiblockRenderHook.renderHooks.put(ModBlocks.flower, specialFlowerRender);
        IMultiblockRenderHook.renderHooks.put(ModBlocks.shinyFlower, specialFlowerRender);
        RenderTransparentItem renderTransparentItem = new RenderTransparentItem();
        RenderFloatingFlowerItem renderFloatingFlower = new RenderFloatingFlowerItem();
        RenderBow renderBow = new RenderBow();
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.lens, (IItemRenderer)new RenderLens());
        if (ConfigHandler.lexicon3dModel) {
            MinecraftForgeClient.registerItemRenderer((Item)ModItems.lexicon, (IItemRenderer)new RenderLexicon());
        }
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.glassPick, (IItemRenderer)renderTransparentItem);
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.spark, (IItemRenderer)renderTransparentItem);
        MinecraftForgeClient.registerItemRenderer((Item)Item.func_150898_a((Block)ModBlocks.floatingFlower), (IItemRenderer)renderFloatingFlower);
        MinecraftForgeClient.registerItemRenderer((Item)Item.func_150898_a((Block)ModBlocks.floatingSpecialFlower), (IItemRenderer)renderFloatingFlower);
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.livingwoodBow, (IItemRenderer)renderBow);
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.crystalBow, (IItemRenderer)renderBow);
        RenderTileFloatingFlower renderTileFloatingFlower = new RenderTileFloatingFlower();
        ClientRegistry.bindTileEntitySpecialRenderer(TileAltar.class, (TileEntitySpecialRenderer)new RenderTileAltar());
        ClientRegistry.bindTileEntitySpecialRenderer(TileSpreader.class, (TileEntitySpecialRenderer)new RenderTileSpreader());
        ClientRegistry.bindTileEntitySpecialRenderer(TilePool.class, (TileEntitySpecialRenderer)new RenderTilePool());
        ClientRegistry.bindTileEntitySpecialRenderer(TileRuneAltar.class, (TileEntitySpecialRenderer)new RenderTileRuneAltar());
        ClientRegistry.bindTileEntitySpecialRenderer(TilePylon.class, (TileEntitySpecialRenderer)new RenderTilePylon());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEnchanter.class, (TileEntitySpecialRenderer)new RenderTileEnchanter());
        ClientRegistry.bindTileEntitySpecialRenderer(TileAlfPortal.class, (TileEntitySpecialRenderer)new RenderTileAlfPortal());
        ClientRegistry.bindTileEntitySpecialRenderer(TileFloatingFlower.class, (TileEntitySpecialRenderer)renderTileFloatingFlower);
        ClientRegistry.bindTileEntitySpecialRenderer(TileFloatingSpecialFlower.class, (TileEntitySpecialRenderer)renderTileFloatingFlower);
        ClientRegistry.bindTileEntitySpecialRenderer(TileTinyPotato.class, (TileEntitySpecialRenderer)new RenderTileTinyPotato());
        ClientRegistry.bindTileEntitySpecialRenderer(TileSpawnerClaw.class, (TileEntitySpecialRenderer)new RenderTileSpawnerClaw());
        ClientRegistry.bindTileEntitySpecialRenderer(TileStarfield.class, (TileEntitySpecialRenderer)new RenderTileStarfield());
        ClientRegistry.bindTileEntitySpecialRenderer(TileBrewery.class, (TileEntitySpecialRenderer)new RenderTileBrewery());
        ClientRegistry.bindTileEntitySpecialRenderer(TileTerraPlate.class, (TileEntitySpecialRenderer)new RenderTileTerraPlate());
        ClientRegistry.bindTileEntitySpecialRenderer(TileRedString.class, (TileEntitySpecialRenderer)new RenderTileRedString());
        ClientRegistry.bindTileEntitySpecialRenderer(TilePrism.class, (TileEntitySpecialRenderer)new RenderTilePrism());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCorporeaIndex.class, (TileEntitySpecialRenderer)new RenderTileCorporeaIndex());
        ClientRegistry.bindTileEntitySpecialRenderer(TilePump.class, (TileEntitySpecialRenderer)new RenderTilePump());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCorporeaCrystalCube.class, (TileEntitySpecialRenderer)new RenderTileCorporeaCrystalCube());
        ClientRegistry.bindTileEntitySpecialRenderer(TileIncensePlate.class, (TileEntitySpecialRenderer)new RenderTileIncensePlate());
        ClientRegistry.bindTileEntitySpecialRenderer(TileHourglass.class, (TileEntitySpecialRenderer)new RenderTileHourglass());
        ClientRegistry.bindTileEntitySpecialRenderer(TileSparkChanger.class, (TileEntitySpecialRenderer)new RenderTileSparkChanger());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCocoon.class, (TileEntitySpecialRenderer)new RenderTileCocoon());
        ClientRegistry.bindTileEntitySpecialRenderer(TileLightRelay.class, (TileEntitySpecialRenderer)new RenderTileLightRelay());
        ClientRegistry.bindTileEntitySpecialRenderer(TileBellows.class, (TileEntitySpecialRenderer)new RenderTileBellows());
        ClientRegistry.bindTileEntitySpecialRenderer(TileGaiaHead.class, (TileEntitySpecialRenderer)new RenderTileSkullOverride());
        ClientRegistry.bindTileEntitySpecialRenderer(TileTeruTeruBozu.class, (TileEntitySpecialRenderer)new RenderTileTeruTeruBozu());
        ClientRegistry.bindTileEntitySpecialRenderer(TileAvatar.class, (TileEntitySpecialRenderer)new RenderTileAvatar());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySkull.class, (TileEntitySpecialRenderer)new RenderTileSkullOverride());
        RenderingRegistry.registerEntityRenderingHandler(EntityPixie.class, (Render)new RenderPixie());
        RenderingRegistry.registerEntityRenderingHandler(EntityVineBall.class, (Render)new RenderSnowball(ModItems.vineBall));
        RenderingRegistry.registerEntityRenderingHandler(EntityDoppleganger.class, (Render)new RenderDoppleganger());
        RenderingRegistry.registerEntityRenderingHandler(EntitySpark.class, (Render)new RenderSpark());
        RenderingRegistry.registerEntityRenderingHandler(EntityThornChakram.class, (Render)new RenderThornChakram());
        RenderingRegistry.registerEntityRenderingHandler(EntityCorporeaSpark.class, (Render)new RenderCorporeaSpark());
        RenderingRegistry.registerEntityRenderingHandler(EntityEnderAirBottle.class, (Render)new RenderSnowball(ModItems.manaResource, 15));
        RenderingRegistry.registerEntityRenderingHandler(EntityPoolMinecart.class, (Render)new RenderPoolMinecart());
        RenderingRegistry.registerEntityRenderingHandler(EntityPinkWither.class, (Render)new RenderPinkWither());
        RenderingRegistry.registerEntityRenderingHandler(EntityManaStorm.class, (Render)new RenderManaStorm());
        RenderingRegistry.registerEntityRenderingHandler(EntityBabylonWeapon.class, (Render)new RenderBabylonWeapon());
        ShaderHelper.initShaders();
    }

    @Override
    @Optional.Method(modid="NotEnoughItems")
    public void registerNEIStuff() {
        NEIGuiHooks.init();
    }

    @Override
    public void setEntryToOpen(LexiconEntry entry) {
        GuiLexicon.currentOpenLexicon = new GuiLexiconEntry(entry, new GuiLexiconIndex(entry.category));
    }

    @Override
    public void setToTutorialIfFirstLaunch() {
        if (PersistentVariableHelper.firstLoad) {
            GuiLexicon.currentOpenLexicon = new GuiLexiconEntry(LexiconData.welcome, new GuiLexiconEntry(LexiconData.tutorial, new GuiLexicon())).setFirstEntry();
        }
    }

    @Override
    public void setLexiconStack(ItemStack stack) {
        GuiLexicon.stackUsed = stack;
    }

    @Override
    public boolean isTheClientPlayer(EntityLivingBase entity) {
        return entity == Minecraft.func_71410_x().field_71439_g;
    }

    @Override
    public boolean isClientPlayerWearingMonocle() {
        return ItemMonocle.hasMonocle((EntityPlayer)Minecraft.func_71410_x().field_71439_g);
    }

    @Override
    public void setExtraReach(EntityLivingBase entity, float reach) {
        super.setExtraReach(entity, reach);
        Minecraft mc = Minecraft.func_71410_x();
        EntityClientPlayerMP player = mc.field_71439_g;
        if (entity == player) {
            if (!(mc.field_71442_b instanceof IExtendedPlayerController)) {
                WorldSettings.GameType type = (WorldSettings.GameType)ReflectionHelper.getPrivateValue(PlayerControllerMP.class, (Object)mc.field_71442_b, (String[])LibObfuscation.CURRENT_GAME_TYPE);
                NetHandlerPlayClient net = (NetHandlerPlayClient)ReflectionHelper.getPrivateValue(PlayerControllerMP.class, (Object)mc.field_71442_b, (String[])LibObfuscation.NET_CLIENT_HANDLER);
                BotaniaPlayerController controller = new BotaniaPlayerController(mc, net);
                boolean isFlying = player.field_71075_bZ.field_75100_b;
                boolean allowFlying = player.field_71075_bZ.field_75101_c;
                controller.func_78746_a(type);
                player.field_71075_bZ.field_75100_b = isFlying;
                player.field_71075_bZ.field_75101_c = allowFlying;
                mc.field_71442_b = controller;
            }
            ((IExtendedPlayerController)mc.field_71442_b).setReachDistanceExtension(Math.max(0.0f, ((IExtendedPlayerController)mc.field_71442_b).getReachDistanceExtension() + reach));
        }
    }

    @Override
    public boolean openWikiPage(World world, Block block, MovingObjectPosition pos) {
        IWikiProvider wiki = WikiHooks.getWikiFor(block);
        String url = wiki.getWikiURL(world, pos);
        if (url != null && !url.isEmpty()) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            }
            catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public String getLastVersion() {
        String s = PersistentVariableHelper.lastBotaniaVersion;
        if (s == null) {
            return "N/A";
        }
        if (s.indexOf("-") > 0) {
            return s.split("-")[1];
        }
        return s;
    }

    @Override
    public void playRecordClientSided(World world, int x, int y, int z, ItemRecord record) {
        Minecraft mc = Minecraft.func_71410_x();
        if (record == null) {
            world.func_72889_a(null, 1005, x, y, z, 0);
        } else {
            world.func_72889_a(null, 1005, x, y, z, Item.func_150891_b((Item)record));
            mc.field_71456_v.func_73833_a(record.func_150927_i());
        }
    }

    @Override
    public long getWorldElapsedTicks() {
        return ClientTickHandler.ticksInGame;
    }

    @Override
    public void setMultiblock(World world, int x, int y, int z, double radius, Block block) {
        ItemSextant.MultiblockSextant mb = new ItemSextant.MultiblockSextant();
        int iradius = (int)radius + 1;
        for (int i = 0; i < iradius * 2 + 1; ++i) {
            for (int j = 0; j < iradius * 2 + 1; ++j) {
                int xp = x + i - iradius;
                int zp = z + j - iradius;
                if ((int)Math.floor(MathHelper.pointDistancePlane(xp, zp, x, z)) != iradius - 1) continue;
                mb.addComponent(new AnyComponent(new ChunkCoordinates(xp - x, 1, zp - z), block, 0));
            }
        }
        MultiblockRenderHandler.setMultiblock(mb.makeSet());
        MultiblockRenderHandler.anchor = new ChunkCoordinates(x, y, z);
    }

    @Override
    public void removeSextantMultiblock() {
        Multiblock mb;
        MultiblockSet set = MultiblockRenderHandler.currentMultiblock;
        if (set != null && (mb = set.getForIndex(0)) instanceof ItemSextant.MultiblockSextant) {
            MultiblockRenderHandler.setMultiblock(null);
        }
    }

    @Override
    public void setSparkleFXNoClip(boolean noclip) {
        noclipEnabled = noclip;
    }

    @Override
    public void setSparkleFXCorrupt(boolean corrupt) {
        corruptSparkle = corrupt;
    }

    @Override
    public void sparkleFX(World world, double x, double y, double z, float r, float g, float b, float size, int m, boolean fake) {
        if (!this.doParticle(world) && !fake) {
            return;
        }
        FXSparkle sparkle = new FXSparkle(world, x, y, z, size, r, g, b, m);
        sparkle.fake = sparkle.field_70145_X = fake;
        if (noclipEnabled) {
            sparkle.field_70145_X = true;
        }
        if (corruptSparkle) {
            sparkle.corrupt = true;
        }
        Minecraft.func_71410_x().field_71452_i.func_78873_a((EntityFX)sparkle);
    }

    @Override
    public void setWispFXDistanceLimit(boolean limit) {
        distanceLimit = limit;
    }

    @Override
    public void setWispFXDepthTest(boolean test) {
        depthTest = test;
    }

    @Override
    public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float motionx, float motiony, float motionz, float maxAgeMul) {
        if (!this.doParticle(world)) {
            return;
        }
        FXWisp wisp = new FXWisp(world, x, y, z, size, r, g, b, distanceLimit, depthTest, maxAgeMul);
        wisp.field_70159_w = motionx;
        wisp.field_70181_x = motiony;
        wisp.field_70179_y = motionz;
        Minecraft.func_71410_x().field_71452_i.func_78873_a((EntityFX)wisp);
    }

    private boolean doParticle(World world) {
        if (!world.field_72995_K) {
            return false;
        }
        if (!ConfigHandler.useVanillaParticleLimiter) {
            return true;
        }
        float chance = 1.0f;
        if (Minecraft.func_71410_x().field_71474_y.field_74362_aa == 1) {
            chance = 0.6f;
        } else if (Minecraft.func_71410_x().field_71474_y.field_74362_aa == 2) {
            chance = 0.2f;
        }
        return chance == 1.0f || Math.random() < (double)chance;
    }

    @Override
    public void lightningFX(World world, Vector3 vectorStart, Vector3 vectorEnd, float ticksPerMeter, long seed, int colorOuter, int colorInner) {
        LightningHandler.spawnLightningBolt(world, vectorStart, vectorEnd, ticksPerMeter, seed, colorOuter, colorInner);
    }
}

