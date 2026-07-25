/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.world.World
 *  net.minecraftforge.client.EnumHelperClient
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.MinecraftForgeClient
 *  net.minecraftforge.common.MinecraftForge
 *  thaumcraft.client.fx.ParticleEngine
 */
package thaumic.tinkerer.client.core.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import thaumcraft.client.fx.ParticleEngine;
import thaumic.tinkerer.client.core.handler.ClientTickHandler;
import thaumic.tinkerer.client.core.handler.GemArmorKeyHandler;
import thaumic.tinkerer.client.core.handler.HUDHandler;
import thaumic.tinkerer.client.core.handler.kami.KamiArmorClientHandler;
import thaumic.tinkerer.client.core.handler.kami.PlacementMirrorPredictionRenderer;
import thaumic.tinkerer.client.core.handler.kami.SoulHeartClientHandler;
import thaumic.tinkerer.client.core.handler.kami.ToolModeHUDHandler;
import thaumic.tinkerer.client.core.helper.ClientHelper;
import thaumic.tinkerer.client.lib.LibRenderIDs;
import thaumic.tinkerer.client.render.block.RenderInfusedCrops;
import thaumic.tinkerer.client.render.block.RenderMagnet;
import thaumic.tinkerer.client.render.block.RenderRepairer;
import thaumic.tinkerer.client.render.block.kami.RenderWarpGate;
import thaumic.tinkerer.client.render.item.RenderGenericSeeds;
import thaumic.tinkerer.client.render.item.RenderMobDisplay;
import thaumic.tinkerer.client.render.item.kami.RenderPlacementMirror;
import thaumic.tinkerer.client.render.tile.RenderTileAnimationTablet;
import thaumic.tinkerer.client.render.tile.RenderTileEnchanter;
import thaumic.tinkerer.client.render.tile.RenderTileFunnel;
import thaumic.tinkerer.client.render.tile.RenderTileMagnet;
import thaumic.tinkerer.client.render.tile.RenderTileRepairer;
import thaumic.tinkerer.client.render.tile.kami.RenderTileWarpGate;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.tile.TileEnchanter;
import thaumic.tinkerer.common.block.tile.TileFunnel;
import thaumic.tinkerer.common.block.tile.TileMagnet;
import thaumic.tinkerer.common.block.tile.TileRepairer;
import thaumic.tinkerer.common.block.tile.kami.TileWarpGate;
import thaumic.tinkerer.common.block.tile.tablet.TileAnimationTablet;
import thaumic.tinkerer.common.compat.FumeTool;
import thaumic.tinkerer.common.core.handler.ConfigHandler;
import thaumic.tinkerer.common.core.proxy.TTCommonProxy;
import thaumic.tinkerer.common.item.ItemInfusedSeeds;
import thaumic.tinkerer.common.item.ItemMobDisplay;
import thaumic.tinkerer.common.item.kami.ItemPlacementMirror;
import thaumic.tinkerer.common.item.kami.foci.ItemFocusShadowbeam;

public class TTClientProxy
extends TTCommonProxy {
    public static EntityPlayer getPlayer() {
        return Minecraft.func_71410_x().field_71439_g;
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        if (Loader.isModLoaded((String)"ComputerCraft")) {
            MinecraftForge.EVENT_BUS.register((Object)new FumeTool());
        }
        if (ConfigHandler.enableKami) {
            kamiRarity = (EnumRarity)EnumHelperClient.addEnum((Class[][])new Class[][]{{EnumRarity.class, EnumChatFormatting.class, String.class}}, EnumRarity.class, (String)"KAMI", (Object[])new Object[]{EnumChatFormatting.LIGHT_PURPLE, "Kami"});
        }
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        MinecraftForge.EVENT_BUS.register((Object)new HUDHandler());
        ClientTickHandler cthandler = new ClientTickHandler();
        FMLCommonHandler.instance().bus().register((Object)cthandler);
        MinecraftForge.EVENT_BUS.register((Object)cthandler);
        MinecraftForge.EVENT_BUS.register((Object)new GemArmorKeyHandler());
        this.registerTiles();
        this.registerRenderIDs();
        if (ConfigHandler.enableKami) {
            MinecraftForge.EVENT_BUS.register((Object)new SoulHeartClientHandler());
            MinecraftForge.EVENT_BUS.register((Object)new ToolModeHUDHandler());
            if (ConfigHandler.showPlacementMirrorBlocks) {
                MinecraftForge.EVENT_BUS.register((Object)new PlacementMirrorPredictionRenderer());
            }
        }
    }

    private void registerTiles() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileAnimationTablet.class, (TileEntitySpecialRenderer)new RenderTileAnimationTablet());
        ClientRegistry.bindTileEntitySpecialRenderer(TileMagnet.class, (TileEntitySpecialRenderer)new RenderTileMagnet());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEnchanter.class, (TileEntitySpecialRenderer)new RenderTileEnchanter());
        ClientRegistry.bindTileEntitySpecialRenderer(TileFunnel.class, (TileEntitySpecialRenderer)new RenderTileFunnel());
        ClientRegistry.bindTileEntitySpecialRenderer(TileRepairer.class, (TileEntitySpecialRenderer)new RenderTileRepairer());
        LibRenderIDs.idGrain = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((int)LibRenderIDs.idGrain, (ISimpleBlockRenderingHandler)new RenderInfusedCrops());
        if (ConfigHandler.enableKami) {
            ClientRegistry.bindTileEntitySpecialRenderer(TileWarpGate.class, (TileEntitySpecialRenderer)new RenderTileWarpGate());
        }
    }

    private void registerRenderIDs() {
        LibRenderIDs.idMagnet = RenderingRegistry.getNextAvailableRenderId();
        LibRenderIDs.idRepairer = RenderingRegistry.getNextAvailableRenderId();
        LibRenderIDs.idFire = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderMagnet());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderRepairer());
        MinecraftForgeClient.registerItemRenderer((Item)ThaumicTinkerer.registry.getFirstItemFromClass(ItemMobDisplay.class), (IItemRenderer)new RenderMobDisplay());
        MinecraftForgeClient.registerItemRenderer((Item)ThaumicTinkerer.registry.getFirstItemFromClass(ItemInfusedSeeds.class), (IItemRenderer)new RenderGenericSeeds());
        if (ConfigHandler.enableKami) {
            MinecraftForgeClient.registerItemRenderer((Item)ThaumicTinkerer.registry.getFirstItemFromClass(ItemPlacementMirror.class), (IItemRenderer)new RenderPlacementMirror());
            LibRenderIDs.idWarpGate = RenderingRegistry.getNextAvailableRenderId();
            RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderWarpGate());
        }
    }

    @Override
    public void shadowSparkle(World world, float x, float y, float z, int size) {
        ItemFocusShadowbeam.Particle fx = new ItemFocusShadowbeam.Particle(world, x, y, z, size, 0.001f, 0.001f, 0.001f, 5);
        ParticleEngine.instance.addEffect(world, (EntityFX)fx);
    }

    @Override
    protected void initCCPeripherals() {
        try {
            super.initCCPeripherals();
        }
        catch (Throwable e) {
            ThaumicTinkerer.log.info("Thaumic Tinkerer: ComputerCraft not found.");
        }
    }

    @Override
    public boolean isClient() {
        return true;
    }

    @Override
    public boolean armorStatus(EntityPlayer player) {
        return KamiArmorClientHandler.ArmorEnabled;
    }

    @Override
    public void setArmor(EntityPlayer player, boolean status) {
        super.setArmor(player, status);
        if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
            KamiArmorClientHandler.ArmorEnabled = status;
        }
    }

    @Override
    public EntityPlayer getClientPlayer() {
        return ClientHelper.clientPlayer();
    }
}

