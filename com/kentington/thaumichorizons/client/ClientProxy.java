/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  cpw.mods.fml.common.FMLCommonHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelChicken
 *  net.minecraft.client.model.ModelCow
 *  net.minecraft.client.model.ModelHorse
 *  net.minecraft.client.model.ModelOcelot
 *  net.minecraft.client.model.ModelPig
 *  net.minecraft.client.model.ModelSlime
 *  net.minecraft.client.model.ModelWolf
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.particle.EntityFlameFX
 *  net.minecraft.client.particle.EntitySpellParticleFX
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.world.World
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.MinecraftForgeClient
 *  net.minecraftforge.common.MinecraftForge
 *  thaumcraft.client.fx.ParticleEngine
 *  thaumcraft.client.fx.particles.FXBurst
 *  thaumcraft.client.fx.particles.FXSparkle
 *  thaumcraft.client.fx.particles.FXWisp
 *  thaumcraft.client.renderers.item.ItemWandRenderer
 *  thaumcraft.common.Thaumcraft
 */
package com.kentington.thaumichorizons.client;

import com.kentington.thaumichorizons.client.gui.GuiBloodInfuser;
import com.kentington.thaumichorizons.client.gui.GuiCase;
import com.kentington.thaumichorizons.client.gui.GuiFingers;
import com.kentington.thaumichorizons.client.gui.GuiInjector;
import com.kentington.thaumichorizons.client.gui.GuiInspiratron;
import com.kentington.thaumichorizons.client.gui.GuiSoulExtractor;
import com.kentington.thaumichorizons.client.gui.GuiSoulforge;
import com.kentington.thaumichorizons.client.gui.GuiVat;
import com.kentington.thaumichorizons.client.gui.GuiVisDynamo;
import com.kentington.thaumichorizons.client.renderer.block.BlockBloodInfuserRender;
import com.kentington.thaumichorizons.client.renderer.block.BlockEssentiaDynamoRender;
import com.kentington.thaumichorizons.client.renderer.block.BlockInspiratronRender;
import com.kentington.thaumichorizons.client.renderer.block.BlockJarTHRenderer;
import com.kentington.thaumichorizons.client.renderer.block.BlockNodeMonitorRender;
import com.kentington.thaumichorizons.client.renderer.block.BlockRecombinatorRender;
import com.kentington.thaumichorizons.client.renderer.block.BlockSlotRender;
import com.kentington.thaumichorizons.client.renderer.block.BlockSoulBeaconRender;
import com.kentington.thaumichorizons.client.renderer.block.BlockSoulSieveRender;
import com.kentington.thaumichorizons.client.renderer.block.BlockSoulforgeRender;
import com.kentington.thaumichorizons.client.renderer.block.BlockSpikeRenderer;
import com.kentington.thaumichorizons.client.renderer.block.BlockSyntheticNodeRender;
import com.kentington.thaumichorizons.client.renderer.block.BlockTransductionAmplifierRender;
import com.kentington.thaumichorizons.client.renderer.block.BlockVatInteriorRender;
import com.kentington.thaumichorizons.client.renderer.block.BlockVatMatrixRender;
import com.kentington.thaumichorizons.client.renderer.block.BlockVatRender;
import com.kentington.thaumichorizons.client.renderer.block.BlockVatSolidRender;
import com.kentington.thaumichorizons.client.renderer.block.BlockVisDynamoRender;
import com.kentington.thaumichorizons.client.renderer.block.BlockVortexStabilizerRender;
import com.kentington.thaumichorizons.client.renderer.entity.BlastPhialRender;
import com.kentington.thaumichorizons.client.renderer.entity.RenderAlchemitePrimed;
import com.kentington.thaumichorizons.client.renderer.entity.RenderBoatGreatwood;
import com.kentington.thaumichorizons.client.renderer.entity.RenderBoatThaumium;
import com.kentington.thaumichorizons.client.renderer.entity.RenderChocolateCow;
import com.kentington.thaumichorizons.client.renderer.entity.RenderEndersteed;
import com.kentington.thaumichorizons.client.renderer.entity.RenderFamiliar;
import com.kentington.thaumichorizons.client.renderer.entity.RenderGoldChicken;
import com.kentington.thaumichorizons.client.renderer.entity.RenderGolemTH;
import com.kentington.thaumichorizons.client.renderer.entity.RenderGravekeeper;
import com.kentington.thaumichorizons.client.renderer.entity.RenderGuardianPanther;
import com.kentington.thaumichorizons.client.renderer.entity.RenderLightningBoltFinite;
import com.kentington.thaumichorizons.client.renderer.entity.RenderLunarWolf;
import com.kentington.thaumichorizons.client.renderer.entity.RenderMeatSlime;
import com.kentington.thaumichorizons.client.renderer.entity.RenderMedSlime;
import com.kentington.thaumichorizons.client.renderer.entity.RenderMercurialSlime;
import com.kentington.thaumichorizons.client.renderer.entity.RenderNetherHound;
import com.kentington.thaumichorizons.client.renderer.entity.RenderNightmare;
import com.kentington.thaumichorizons.client.renderer.entity.RenderOreBoar;
import com.kentington.thaumichorizons.client.renderer.entity.RenderScholarChicken;
import com.kentington.thaumichorizons.client.renderer.entity.RenderSeawolf;
import com.kentington.thaumichorizons.client.renderer.entity.RenderSheeder;
import com.kentington.thaumichorizons.client.renderer.entity.RenderSoul;
import com.kentington.thaumichorizons.client.renderer.entity.RenderSyringe;
import com.kentington.thaumichorizons.client.renderer.entity.RenderTaintfeeder;
import com.kentington.thaumichorizons.client.renderer.entity.RenderVoltSlime;
import com.kentington.thaumichorizons.client.renderer.item.ItemCorpseEffigyRender;
import com.kentington.thaumichorizons.client.renderer.item.ItemInjectorRender;
import com.kentington.thaumichorizons.client.renderer.item.ItemSyringeRender;
import com.kentington.thaumichorizons.client.renderer.model.ModelFamiliar;
import com.kentington.thaumichorizons.client.renderer.model.ModelGolemTH;
import com.kentington.thaumichorizons.client.renderer.tile.ItemJarTHRenderer;
import com.kentington.thaumichorizons.client.renderer.tile.TileBloodInfuserRender;
import com.kentington.thaumichorizons.client.renderer.tile.TileCloudRender;
import com.kentington.thaumichorizons.client.renderer.tile.TileEssentiaDynamoRender;
import com.kentington.thaumichorizons.client.renderer.tile.TileEtherealShardRender;
import com.kentington.thaumichorizons.client.renderer.tile.TileInspiratronRender;
import com.kentington.thaumichorizons.client.renderer.tile.TileJarTHRenderer;
import com.kentington.thaumichorizons.client.renderer.tile.TileNodeMonitorRender;
import com.kentington.thaumichorizons.client.renderer.tile.TileRecombinatorRender;
import com.kentington.thaumichorizons.client.renderer.tile.TileSlotRender;
import com.kentington.thaumichorizons.client.renderer.tile.TileSoulBeaconRender;
import com.kentington.thaumichorizons.client.renderer.tile.TileSoulSieveRender;
import com.kentington.thaumichorizons.client.renderer.tile.TileSoulforgeRender;
import com.kentington.thaumichorizons.client.renderer.tile.TileSpikeRender;
import com.kentington.thaumichorizons.client.renderer.tile.TileTransductionAmplifierRender;
import com.kentington.thaumichorizons.client.renderer.tile.TileVatMatrixRender;
import com.kentington.thaumichorizons.client.renderer.tile.TileVatSlaveRender;
import com.kentington.thaumichorizons.client.renderer.tile.TileVisDynamoRender;
import com.kentington.thaumichorizons.client.renderer.tile.TileVortexRender;
import com.kentington.thaumichorizons.client.renderer.tile.TileVortexStabilizerRender;
import com.kentington.thaumichorizons.common.CommonProxy;
import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityAlchemitePrimed;
import com.kentington.thaumichorizons.common.entities.EntityBlastPhial;
import com.kentington.thaumichorizons.common.entities.EntityBoatGreatwood;
import com.kentington.thaumichorizons.common.entities.EntityBoatThaumium;
import com.kentington.thaumichorizons.common.entities.EntityChocolateCow;
import com.kentington.thaumichorizons.common.entities.EntityEndersteed;
import com.kentington.thaumichorizons.common.entities.EntityFamiliar;
import com.kentington.thaumichorizons.common.entities.EntityGoldChicken;
import com.kentington.thaumichorizons.common.entities.EntityGolemTH;
import com.kentington.thaumichorizons.common.entities.EntityGravekeeper;
import com.kentington.thaumichorizons.common.entities.EntityGuardianPanther;
import com.kentington.thaumichorizons.common.entities.EntityLightningBoltFinite;
import com.kentington.thaumichorizons.common.entities.EntityLunarWolf;
import com.kentington.thaumichorizons.common.entities.EntityMeatSlime;
import com.kentington.thaumichorizons.common.entities.EntityMedSlime;
import com.kentington.thaumichorizons.common.entities.EntityMercurialSlime;
import com.kentington.thaumichorizons.common.entities.EntityNetherHound;
import com.kentington.thaumichorizons.common.entities.EntityNightmare;
import com.kentington.thaumichorizons.common.entities.EntityOrePig;
import com.kentington.thaumichorizons.common.entities.EntityScholarChicken;
import com.kentington.thaumichorizons.common.entities.EntitySeawolf;
import com.kentington.thaumichorizons.common.entities.EntitySheeder;
import com.kentington.thaumichorizons.common.entities.EntitySoul;
import com.kentington.thaumichorizons.common.entities.EntitySyringe;
import com.kentington.thaumichorizons.common.entities.EntityTaintPig;
import com.kentington.thaumichorizons.common.entities.EntityVoltSlime;
import com.kentington.thaumichorizons.common.items.WandManagerTH;
import com.kentington.thaumichorizons.common.lib.THKeyHandler;
import com.kentington.thaumichorizons.common.tiles.TileBloodInfuser;
import com.kentington.thaumichorizons.common.tiles.TileCloud;
import com.kentington.thaumichorizons.common.tiles.TileEssentiaDynamo;
import com.kentington.thaumichorizons.common.tiles.TileInspiratron;
import com.kentington.thaumichorizons.common.tiles.TileNodeMonitor;
import com.kentington.thaumichorizons.common.tiles.TileRecombinator;
import com.kentington.thaumichorizons.common.tiles.TileSlot;
import com.kentington.thaumichorizons.common.tiles.TileSoulBeacon;
import com.kentington.thaumichorizons.common.tiles.TileSoulExtractor;
import com.kentington.thaumichorizons.common.tiles.TileSoulJar;
import com.kentington.thaumichorizons.common.tiles.TileSoulforge;
import com.kentington.thaumichorizons.common.tiles.TileSpike;
import com.kentington.thaumichorizons.common.tiles.TileSyntheticNode;
import com.kentington.thaumichorizons.common.tiles.TileTransductionAmplifier;
import com.kentington.thaumichorizons.common.tiles.TileVat;
import com.kentington.thaumichorizons.common.tiles.TileVatMatrix;
import com.kentington.thaumichorizons.common.tiles.TileVatSlave;
import com.kentington.thaumichorizons.common.tiles.TileVisDynamo;
import com.kentington.thaumichorizons.common.tiles.TileVortex;
import com.kentington.thaumichorizons.common.tiles.TileVortexStabilizer;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import java.awt.Color;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelHorse;
import net.minecraft.client.model.ModelOcelot;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.particle.EntitySpellParticleFX;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import thaumcraft.api.wands.IWandTriggerManager;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXBurst;
import thaumcraft.client.fx.particles.FXSparkle;
import thaumcraft.client.fx.particles.FXWisp;
import thaumcraft.client.renderers.item.ItemWandRenderer;
import thaumcraft.common.Thaumcraft;

public class ClientProxy
extends CommonProxy {
    public IWandTriggerManager wandManager = new WandManagerTH();

    @Override
    public void registerHandlers() {
        MinecraftForge.EVENT_BUS.register((Object)ThaumicHorizons.instance.renderEventHandler);
    }

    @Override
    public void registerKeyBindings() {
        FMLCommonHandler.instance().bus().register((Object)new THKeyHandler());
    }

    @Override
    public void registerRenderers() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileNodeMonitor.class, (TileEntitySpecialRenderer)new TileNodeMonitorRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileSyntheticNode.class, (TileEntitySpecialRenderer)new TileEtherealShardRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileVisDynamo.class, (TileEntitySpecialRenderer)new TileVisDynamoRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEssentiaDynamo.class, (TileEntitySpecialRenderer)new TileEssentiaDynamoRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileSoulExtractor.class, (TileEntitySpecialRenderer)new TileSoulSieveRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileInspiratron.class, (TileEntitySpecialRenderer)new TileInspiratronRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileSoulforge.class, (TileEntitySpecialRenderer)new TileSoulforgeRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileVatSlave.class, (TileEntitySpecialRenderer)new TileVatSlaveRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileVatMatrix.class, (TileEntitySpecialRenderer)new TileVatMatrixRender(0));
        ClientRegistry.bindTileEntitySpecialRenderer(TileBloodInfuser.class, (TileEntitySpecialRenderer)new TileBloodInfuserRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileSoulBeacon.class, (TileEntitySpecialRenderer)new TileSoulBeaconRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileTransductionAmplifier.class, (TileEntitySpecialRenderer)new TileTransductionAmplifierRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileRecombinator.class, (TileEntitySpecialRenderer)new TileRecombinatorRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileVortexStabilizer.class, (TileEntitySpecialRenderer)new TileVortexStabilizerRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileVortex.class, (TileEntitySpecialRenderer)new TileVortexRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileSpike.class, (TileEntitySpecialRenderer)new TileSpikeRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileCloud.class, (TileEntitySpecialRenderer)new TileCloudRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileSlot.class, (TileEntitySpecialRenderer)new TileSlotRender());
        RenderingRegistry.registerEntityRenderingHandler(EntityAlchemitePrimed.class, (Render)new RenderAlchemitePrimed());
        RenderingRegistry.registerEntityRenderingHandler(EntitySyringe.class, (Render)new RenderSyringe());
        RenderingRegistry.registerEntityRenderingHandler(EntityBlastPhial.class, (Render)new BlastPhialRender());
        RenderingRegistry.registerEntityRenderingHandler(EntityChocolateCow.class, (Render)new RenderChocolateCow((ModelBase)new ModelCow(), 0.7f));
        RenderingRegistry.registerEntityRenderingHandler(EntityOrePig.class, (Render)new RenderOreBoar((ModelBase)new ModelPig(), (ModelBase)new ModelPig(0.5f), 0.7f));
        RenderingRegistry.registerEntityRenderingHandler(EntityGuardianPanther.class, (Render)new RenderGuardianPanther((ModelBase)new ModelOcelot(), 1.0f));
        RenderingRegistry.registerEntityRenderingHandler(EntityFamiliar.class, (Render)new RenderFamiliar(new ModelFamiliar(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityGravekeeper.class, (Render)new RenderGravekeeper((ModelBase)new ModelOcelot(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityGoldChicken.class, (Render)new RenderGoldChicken((ModelBase)new ModelChicken(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityScholarChicken.class, (Render)new RenderScholarChicken((ModelBase)new ModelChicken(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityTaintPig.class, (Render)new RenderTaintfeeder((ModelBase)new ModelPig(), (ModelBase)new ModelPig(0.5f), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityNetherHound.class, (Render)new RenderNetherHound((ModelBase)new ModelWolf(), (ModelBase)new ModelWolf(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntitySeawolf.class, (Render)new RenderSeawolf((ModelBase)new ModelWolf(), (ModelBase)new ModelWolf(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityLunarWolf.class, (Render)new RenderLunarWolf((ModelBase)new ModelWolf(), (ModelBase)new ModelWolf(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityGolemTH.class, (Render)new RenderGolemTH((ModelBase)new ModelGolemTH(false)));
        RenderingRegistry.registerEntityRenderingHandler(EntityEndersteed.class, (Render)new RenderEndersteed((ModelBase)new ModelHorse(), 0.75f));
        RenderingRegistry.registerEntityRenderingHandler(EntityNightmare.class, (Render)new RenderNightmare((ModelBase)new ModelHorse(), 0.75f));
        RenderingRegistry.registerEntityRenderingHandler(EntityBoatGreatwood.class, (Render)new RenderBoatGreatwood());
        RenderingRegistry.registerEntityRenderingHandler(EntityBoatThaumium.class, (Render)new RenderBoatThaumium());
        RenderingRegistry.registerEntityRenderingHandler(EntityMeatSlime.class, (Render)new RenderMeatSlime((ModelBase)new ModelSlime(16), (ModelBase)new ModelSlime(0), 0.25f));
        RenderingRegistry.registerEntityRenderingHandler(EntityMercurialSlime.class, (Render)new RenderMercurialSlime((ModelBase)new ModelSlime(16), (ModelBase)new ModelSlime(0), 0.25f));
        RenderingRegistry.registerEntityRenderingHandler(EntityVoltSlime.class, (Render)new RenderVoltSlime((ModelBase)new ModelSlime(16), (ModelBase)new ModelSlime(0), 0.25f));
        RenderingRegistry.registerEntityRenderingHandler(EntityMedSlime.class, (Render)new RenderMedSlime((ModelBase)new ModelSlime(16), (ModelBase)new ModelSlime(0), 0.25f));
        RenderingRegistry.registerEntityRenderingHandler(EntitySheeder.class, (Render)new RenderSheeder());
        RenderingRegistry.registerEntityRenderingHandler(EntitySoul.class, (Render)new RenderSoul());
        RenderingRegistry.registerEntityRenderingHandler(EntityLightningBoltFinite.class, (Render)new RenderLightningBoltFinite());
        MinecraftForgeClient.registerItemRenderer((Item)ThaumicHorizons.itemSyringeBloodSample, (IItemRenderer)new ItemSyringeRender());
        MinecraftForgeClient.registerItemRenderer((Item)ThaumicHorizons.itemSyringeHuman, (IItemRenderer)new ItemSyringeRender());
        MinecraftForgeClient.registerItemRenderer((Item)ThaumicHorizons.itemSyringeEmpty, (IItemRenderer)new ItemSyringeRender());
        MinecraftForgeClient.registerItemRenderer((Item)ThaumicHorizons.itemSyringeInjection, (IItemRenderer)new ItemSyringeRender());
        MinecraftForgeClient.registerItemRenderer((Item)ThaumicHorizons.itemCorpseEffigy, (IItemRenderer)new ItemCorpseEffigyRender());
        MinecraftForgeClient.registerItemRenderer((Item)ThaumicHorizons.itemInjector, (IItemRenderer)new ItemInjectorRender());
        MinecraftForgeClient.registerItemRenderer((Item)ThaumicHorizons.itemWandCastingDisposable, (IItemRenderer)new ItemWandRenderer());
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (world instanceof WorldClient) {
            switch (ID) {
                default: {
                    break;
                }
                case 1: {
                    return new GuiVisDynamo(player, (TileVisDynamo)world.func_147438_o(x, y, z));
                }
                case 2: {
                    return new GuiSoulExtractor(player.field_71071_by, (TileSoulExtractor)world.func_147438_o(x, y, z));
                }
                case 3: {
                    return new GuiInspiratron(player.field_71071_by, (TileInspiratron)world.func_147438_o(x, y, z));
                }
                case 4: {
                    return new GuiSoulforge(player, (TileSoulforge)world.func_147438_o(x, y, z));
                }
                case 5: {
                    return new GuiBloodInfuser(player, (TileBloodInfuser)world.func_147438_o(x, y, z));
                }
                case 6: {
                    return new GuiInjector(player);
                }
                case 7: {
                    return new GuiVat(player, (TileVat)world.func_147438_o(x, y, z));
                }
                case 8: {
                    return new GuiCase(player.field_71071_by, world, x, y, z);
                }
                case 9: {
                    return new GuiFingers(player.field_71071_by);
                }
            }
        }
        return null;
    }

    @Override
    public void registerDisplayInformation() {
        ThaumicHorizons.blockJarRI = RenderingRegistry.getNextAvailableRenderId();
        MinecraftForgeClient.registerItemRenderer((Item)Item.func_150898_a((Block)ThaumicHorizons.blockJar), (IItemRenderer)new ItemJarTHRenderer());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockJarTHRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileSoulJar.class, (TileEntitySpecialRenderer)new TileJarTHRenderer());
        ThaumicHorizons.blockSyntheticNodeRI = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockSyntheticNodeRender());
        ThaumicHorizons.blockNodeMonRI = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockNodeMonitorRender());
        ThaumicHorizons.blockVisDynamoRI = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockVisDynamoRender());
        ThaumicHorizons.blockEssentiaDynamoRI = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockEssentiaDynamoRender());
        ThaumicHorizons.blockSoulSieveRI = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockSoulSieveRender());
        ThaumicHorizons.blockInspiratronRI = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockInspiratronRender());
        ThaumicHorizons.blockSoulforgeRI = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockSoulforgeRender());
        ThaumicHorizons.blockVatRI = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockVatRender());
        ThaumicHorizons.blockVatSolidRI = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockVatSolidRender());
        ThaumicHorizons.blockVatInteriorRI = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockVatInteriorRender());
        ThaumicHorizons.blockVatMatrixRI = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockVatMatrixRender());
        ThaumicHorizons.blockBloodInfuserRI = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockBloodInfuserRender());
        ThaumicHorizons.blockSoulBeaconRI = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockSoulBeaconRender());
        ThaumicHorizons.blockTransducerRI = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockTransductionAmplifierRender());
        ThaumicHorizons.blockRecombinatorRI = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockRecombinatorRender());
        ThaumicHorizons.blockVortexStabilizerRI = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockVortexStabilizerRender());
        ThaumicHorizons.blockSpikeRI = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockSpikeRenderer());
        ThaumicHorizons.blockSlotRI = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockSlotRender());
    }

    @Override
    public void disintegrateFX(double blockX, double blockY, double blockZ, EntityPlayer p, int howMany, boolean enlarged) {
        if (enlarged) {
            for (int x = -1; x < 2; ++x) {
                for (int y = -1; y < 2; ++y) {
                    for (int z = -1; z < 2; ++z) {
                        for (int i = 0; i < howMany; ++i) {
                            FXSparkle fx = new FXSparkle(p.field_70170_p, blockX + 0.5, blockY + 0.5, blockZ + 0.5, 1.0f, 0, 6);
                            fx.field_70159_w = (p.field_70170_p.field_73012_v.nextDouble() - 0.5) / 4.0;
                            fx.field_70181_x = (p.field_70170_p.field_73012_v.nextDouble() - 0.5) / 4.0;
                            fx.field_70179_y = (p.field_70170_p.field_73012_v.nextDouble() - 0.5) / 4.0;
                            fx.field_70145_X = true;
                            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fx);
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < howMany; ++i) {
                FXSparkle fx = new FXSparkle(p.field_70170_p, blockX + 0.5, blockY + 0.5, blockZ + 0.5, 1.0f, 0, 6);
                fx.field_70159_w = (p.field_70170_p.field_73012_v.nextDouble() - 0.5) / 4.0;
                fx.field_70181_x = (p.field_70170_p.field_73012_v.nextDouble() - 0.5) / 4.0;
                fx.field_70179_y = (p.field_70170_p.field_73012_v.nextDouble() - 0.5) / 4.0;
                fx.field_70145_X = true;
                FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fx);
            }
        }
    }

    @Override
    public void smeltFX(double blockX, double blockY, double blockZ, World w, int howMany, boolean enlarged) {
        if (enlarged) {
            for (int x = -1; x < 2; ++x) {
                for (int y = -1; y < 2; ++y) {
                    for (int z = -1; z < 2; ++z) {
                        for (int i = 0; i < howMany; ++i) {
                            EntityFlameFX fx = new EntityFlameFX(w, blockX + 0.5 + (double)x, blockY + 0.5 + (double)y, blockZ + 0.5 + (double)z, (w.field_73012_v.nextDouble() - 0.5) * 0.25, (w.field_73012_v.nextDouble() - 0.5) * 0.25, (w.field_73012_v.nextDouble() - 0.5) * 0.25);
                            fx.field_70145_X = true;
                            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fx);
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < howMany; ++i) {
                EntityFlameFX fx = new EntityFlameFX(w, blockX + 0.5, blockY + 0.5, blockZ + 0.5, (w.field_73012_v.nextDouble() - 0.5) * 0.25, (w.field_73012_v.nextDouble() - 0.5) * 0.25, (w.field_73012_v.nextDouble() - 0.5) * 0.25);
                fx.field_70145_X = true;
                FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fx);
            }
        }
    }

    @Override
    public void soulParticles(int blockX, int blockY, int blockZ, World world) {
        for (int i = 0; i < 10; ++i) {
            EntitySpellParticleFX fx = new EntitySpellParticleFX(world, (double)blockX + 0.5 + (world.field_73012_v.nextDouble() - 0.5) * 0.8, (double)blockY + 0.8, (double)blockZ + 0.5 + (world.field_73012_v.nextDouble() - 0.5) * 0.8, 0.0, world.field_73012_v.nextDouble() * 0.25, 0.0);
            fx.field_70145_X = true;
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fx);
        }
    }

    @Override
    public void containmentFX(double blockX, double blockY, double blockZ, EntityPlayer p, Entity ent, int times) {
        double xSize = ent.field_70121_D.field_72336_d - ent.field_70121_D.field_72340_a;
        double ySize = ent.field_70121_D.field_72337_e - ent.field_70121_D.field_72338_b;
        double zSize = ent.field_70121_D.field_72334_f - ent.field_70121_D.field_72339_c;
        double radius = xSize > ySize ? (xSize > zSize ? xSize : zSize) : (ySize > zSize ? ySize : zSize);
        double xCenter = (ent.field_70121_D.field_72336_d + ent.field_70121_D.field_72340_a) / 2.0;
        double yCenter = (ent.field_70121_D.field_72337_e + ent.field_70121_D.field_72338_b) / 2.0;
        double zCenter = (ent.field_70121_D.field_72334_f + ent.field_70121_D.field_72339_c) / 2.0;
        for (int i = 0; i < times; ++i) {
            double theta = p.field_70170_p.field_73012_v.nextDouble() * Math.PI * 2.0;
            double phi = p.field_70170_p.field_73012_v.nextDouble() * Math.PI * 2.0;
            double z1 = zCenter + radius * Math.cos(phi);
            double y1 = yCenter + radius * Math.sin(phi) * Math.sin(theta);
            double x1 = xCenter + radius * Math.sin(phi) * Math.cos(theta);
            theta = p.field_70170_p.field_73012_v.nextDouble() * Math.PI * 2.0;
            phi = p.field_70170_p.field_73012_v.nextDouble() * Math.PI * 2.0;
            double z2 = zCenter + radius * Math.cos(phi);
            double y2 = yCenter + radius * Math.sin(phi) * Math.sin(theta);
            double x2 = xCenter + radius * Math.sin(phi) * Math.cos(theta);
            Thaumcraft.proxy.arcLightning(p.field_70170_p, x1, y1, z1, x2, y2, z2, p.field_70170_p.field_73012_v.nextFloat() * 0.1f, p.field_70170_p.field_73012_v.nextFloat() * 0.2f, p.field_70170_p.field_73012_v.nextFloat() * 0.8f, p.field_70170_p.field_73012_v.nextFloat());
        }
    }

    @Override
    public void disintegrateExplodeFX(World worldObj, double posX, double posY, double posZ) {
        FXBurst ef = new FXBurst(worldObj, posX, posY, posZ, 1.0f);
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)ef);
    }

    @Override
    public void illuminationFX(World world, int xCoord, int yCoord, int zCoord, int md, Color col) {
        if (world.field_73012_v.nextInt(9 - Thaumcraft.proxy.particleCount(2)) == 0) {
            FXWisp ef = new FXWisp(world, (double)((float)xCoord + 0.55f), (double)((float)yCoord + 0.5f), (double)((float)zCoord + 0.55f), 0.5f, (float)col.getRed() / 255.0f + 0.01f, (float)col.getGreen() / 255.0f, (float)col.getBlue() / 255.0f);
            ef.setGravity(0.0f);
            ef.shrink = false;
            if (md == 0) {
                ef.blendmode = 0;
            }
            ParticleEngine.instance.addEffect(world, (EntityFX)ef);
        }
    }

    @Override
    public void blockSplosionFX(int x, int y, int z, Block block, int md) {
        Minecraft.func_71410_x().field_71452_i.func_147215_a(x, y, z, block, md);
    }

    @Override
    public void alchemiteFX(World worldObj, double x, double y, double z) {
        FXBurst ef = new FXBurst(worldObj, x, y, z, 10.0f);
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)ef);
    }

    @Override
    public boolean readyToRender() {
        return FMLClientHandler.instance().getClient().field_71451_h != null;
    }

    @Override
    public void addEffect(Entity entity) {
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)entity);
    }

    @Override
    public void lightningBolt(World worldObj, double x, double y, double z, int boltLength) {
        Thaumcraft.proxy.arcLightning(worldObj, x + (double)worldObj.field_73012_v.nextFloat() - 0.5, y + (double)boltLength + 0.5, z + (double)worldObj.field_73012_v.nextFloat() - 0.5, x + (double)worldObj.field_73012_v.nextFloat() - 0.5, y + 1.0, z + (double)worldObj.field_73012_v.nextFloat() - 0.5, 0.8f, 1.0f, 1.0f, 0.1f);
    }
}

