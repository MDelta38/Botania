/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.registry.VillagerRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelChicken
 *  net.minecraft.client.model.ModelCow
 *  net.minecraft.client.model.ModelPig
 *  net.minecraft.client.model.ModelSlime
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.particle.EntityDiggingFX
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.particle.EntityLavaFX
 *  net.minecraft.client.renderer.RenderGlobal
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.entity.RenderSnowball
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.MinecraftForgeClient
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.client;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.VillagerRegistry;
import java.awt.Color;
import java.util.HashMap;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityLavaFX;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.beams.FXArc;
import thaumcraft.client.fx.beams.FXBeam;
import thaumcraft.client.fx.beams.FXBeamBore;
import thaumcraft.client.fx.beams.FXBeamPower;
import thaumcraft.client.fx.beams.FXBeamWand;
import thaumcraft.client.fx.bolt.FXLightningBolt;
import thaumcraft.client.fx.other.FXBlockWard;
import thaumcraft.client.fx.particles.FXBlockRunes;
import thaumcraft.client.fx.particles.FXBoreParticles;
import thaumcraft.client.fx.particles.FXBoreSparkle;
import thaumcraft.client.fx.particles.FXBreaking;
import thaumcraft.client.fx.particles.FXBubble;
import thaumcraft.client.fx.particles.FXBurst;
import thaumcraft.client.fx.particles.FXDrop;
import thaumcraft.client.fx.particles.FXEssentiaTrail;
import thaumcraft.client.fx.particles.FXGeneric;
import thaumcraft.client.fx.particles.FXSmokeSpiral;
import thaumcraft.client.fx.particles.FXSmokeTrail;
import thaumcraft.client.fx.particles.FXSpark;
import thaumcraft.client.fx.particles.FXSparkle;
import thaumcraft.client.fx.particles.FXSparkleTrail;
import thaumcraft.client.fx.particles.FXSwarm;
import thaumcraft.client.fx.particles.FXVent;
import thaumcraft.client.fx.particles.FXWisp;
import thaumcraft.client.fx.particles.FXWispArcing;
import thaumcraft.client.fx.particles.FXWispEG;
import thaumcraft.client.gui.GuiAlchemyFurnace;
import thaumcraft.client.gui.GuiArcaneBore;
import thaumcraft.client.gui.GuiArcaneWorkbench;
import thaumcraft.client.gui.GuiDeconstructionTable;
import thaumcraft.client.gui.GuiFocalManipulator;
import thaumcraft.client.gui.GuiFocusPouch;
import thaumcraft.client.gui.GuiGolem;
import thaumcraft.client.gui.GuiHandMirror;
import thaumcraft.client.gui.GuiHoverHarness;
import thaumcraft.client.gui.GuiMagicBox;
import thaumcraft.client.gui.GuiPech;
import thaumcraft.client.gui.GuiResearchBrowser;
import thaumcraft.client.gui.GuiResearchTable;
import thaumcraft.client.gui.GuiSpa;
import thaumcraft.client.gui.GuiThaumatorium;
import thaumcraft.client.gui.GuiTravelingTrunk;
import thaumcraft.client.lib.ClientTickEventsFML;
import thaumcraft.client.renderers.block.BlockArcaneFurnaceRenderer;
import thaumcraft.client.renderers.block.BlockCandleRenderer;
import thaumcraft.client.renderers.block.BlockChestHungryRenderer;
import thaumcraft.client.renderers.block.BlockCosmeticOpaqueRenderer;
import thaumcraft.client.renderers.block.BlockCrystalRenderer;
import thaumcraft.client.renderers.block.BlockCustomOreRenderer;
import thaumcraft.client.renderers.block.BlockEldritchRenderer;
import thaumcraft.client.renderers.block.BlockEssentiaReservoirRenderer;
import thaumcraft.client.renderers.block.BlockGasRenderer;
import thaumcraft.client.renderers.block.BlockJarRenderer;
import thaumcraft.client.renderers.block.BlockLifterRenderer;
import thaumcraft.client.renderers.block.BlockLootCrateRenderer;
import thaumcraft.client.renderers.block.BlockLootUrnRenderer;
import thaumcraft.client.renderers.block.BlockMetalDeviceRenderer;
import thaumcraft.client.renderers.block.BlockStoneDeviceRenderer;
import thaumcraft.client.renderers.block.BlockTableRenderer;
import thaumcraft.client.renderers.block.BlockTaintFibreRenderer;
import thaumcraft.client.renderers.block.BlockTaintRenderer;
import thaumcraft.client.renderers.block.BlockTubeRenderer;
import thaumcraft.client.renderers.block.BlockWardedRenderer;
import thaumcraft.client.renderers.block.BlockWoodenDeviceRenderer;
import thaumcraft.client.renderers.entity.RenderAlumentum;
import thaumcraft.client.renderers.entity.RenderAspectOrb;
import thaumcraft.client.renderers.entity.RenderBrainyZombie;
import thaumcraft.client.renderers.entity.RenderCultist;
import thaumcraft.client.renderers.entity.RenderCultistPortal;
import thaumcraft.client.renderers.entity.RenderDart;
import thaumcraft.client.renderers.entity.RenderEldritchCrab;
import thaumcraft.client.renderers.entity.RenderEldritchGolem;
import thaumcraft.client.renderers.entity.RenderEldritchGuardian;
import thaumcraft.client.renderers.entity.RenderEldritchOrb;
import thaumcraft.client.renderers.entity.RenderElectricOrb;
import thaumcraft.client.renderers.entity.RenderEmber;
import thaumcraft.client.renderers.entity.RenderExplosiveOrb;
import thaumcraft.client.renderers.entity.RenderFallingTaint;
import thaumcraft.client.renderers.entity.RenderFireBat;
import thaumcraft.client.renderers.entity.RenderFollowingItem;
import thaumcraft.client.renderers.entity.RenderFrostShard;
import thaumcraft.client.renderers.entity.RenderGolemBase;
import thaumcraft.client.renderers.entity.RenderGolemBobber;
import thaumcraft.client.renderers.entity.RenderInhabitedZombie;
import thaumcraft.client.renderers.entity.RenderMindSpider;
import thaumcraft.client.renderers.entity.RenderPech;
import thaumcraft.client.renderers.entity.RenderPechBlast;
import thaumcraft.client.renderers.entity.RenderPrimalArrow;
import thaumcraft.client.renderers.entity.RenderPrimalOrb;
import thaumcraft.client.renderers.entity.RenderSpecialItem;
import thaumcraft.client.renderers.entity.RenderTaintChicken;
import thaumcraft.client.renderers.entity.RenderTaintCow;
import thaumcraft.client.renderers.entity.RenderTaintCreeper;
import thaumcraft.client.renderers.entity.RenderTaintPig;
import thaumcraft.client.renderers.entity.RenderTaintSheep;
import thaumcraft.client.renderers.entity.RenderTaintSpider;
import thaumcraft.client.renderers.entity.RenderTaintSpore;
import thaumcraft.client.renderers.entity.RenderTaintSporeSwarmer;
import thaumcraft.client.renderers.entity.RenderTaintSwarm;
import thaumcraft.client.renderers.entity.RenderTaintVillager;
import thaumcraft.client.renderers.entity.RenderTaintacle;
import thaumcraft.client.renderers.entity.RenderThaumicSlime;
import thaumcraft.client.renderers.entity.RenderTravelingTrunk;
import thaumcraft.client.renderers.entity.RenderWisp;
import thaumcraft.client.renderers.item.ItemBannerRenderer;
import thaumcraft.client.renderers.item.ItemBowBoneRenderer;
import thaumcraft.client.renderers.item.ItemThaumometerRenderer;
import thaumcraft.client.renderers.item.ItemTrunkSpawnerRenderer;
import thaumcraft.client.renderers.item.ItemWandRenderer;
import thaumcraft.client.renderers.models.entities.ModelEldritchGolem;
import thaumcraft.client.renderers.models.entities.ModelEldritchGuardian;
import thaumcraft.client.renderers.models.entities.ModelGolem;
import thaumcraft.client.renderers.models.entities.ModelPech;
import thaumcraft.client.renderers.models.entities.ModelTaintSheep1;
import thaumcraft.client.renderers.models.entities.ModelTaintSheep2;
import thaumcraft.client.renderers.models.entities.ModelTrunk;
import thaumcraft.client.renderers.tile.ItemJarFilledRenderer;
import thaumcraft.client.renderers.tile.ItemJarNodeRenderer;
import thaumcraft.client.renderers.tile.ItemNodeRenderer;
import thaumcraft.client.renderers.tile.TileAlchemyFurnaceAdvancedRenderer;
import thaumcraft.client.renderers.tile.TileAlembicRenderer;
import thaumcraft.client.renderers.tile.TileArcaneBoreBaseRenderer;
import thaumcraft.client.renderers.tile.TileArcaneBoreRenderer;
import thaumcraft.client.renderers.tile.TileArcaneLampRenderer;
import thaumcraft.client.renderers.tile.TileArcaneWorkbenchRenderer;
import thaumcraft.client.renderers.tile.TileBannerRenderer;
import thaumcraft.client.renderers.tile.TileBellowsRenderer;
import thaumcraft.client.renderers.tile.TileCentrifugeRenderer;
import thaumcraft.client.renderers.tile.TileChestHungryRenderer;
import thaumcraft.client.renderers.tile.TileCrucibleRenderer;
import thaumcraft.client.renderers.tile.TileCrystalRenderer;
import thaumcraft.client.renderers.tile.TileDeconstructionTableRenderer;
import thaumcraft.client.renderers.tile.TileEldritchCapRenderer;
import thaumcraft.client.renderers.tile.TileEldritchCrabSpawnerRenderer;
import thaumcraft.client.renderers.tile.TileEldritchCrystalRenderer;
import thaumcraft.client.renderers.tile.TileEldritchLockRenderer;
import thaumcraft.client.renderers.tile.TileEldritchNothingRenderer;
import thaumcraft.client.renderers.tile.TileEldritchObeliskRenderer;
import thaumcraft.client.renderers.tile.TileEldritchPortalRenderer;
import thaumcraft.client.renderers.tile.TileEssentiaCrystalizerRenderer;
import thaumcraft.client.renderers.tile.TileEssentiaReservoirRenderer;
import thaumcraft.client.renderers.tile.TileEtherealBloomRenderer;
import thaumcraft.client.renderers.tile.TileFluxScrubberRenderer;
import thaumcraft.client.renderers.tile.TileFocalManipulatorRenderer;
import thaumcraft.client.renderers.tile.TileHoleRenderer;
import thaumcraft.client.renderers.tile.TileInfusionPillarRenderer;
import thaumcraft.client.renderers.tile.TileJarRenderer;
import thaumcraft.client.renderers.tile.TileMagicWorkbenchChargerRenderer;
import thaumcraft.client.renderers.tile.TileManaPodRenderer;
import thaumcraft.client.renderers.tile.TileMirrorRenderer;
import thaumcraft.client.renderers.tile.TileNodeConverterRenderer;
import thaumcraft.client.renderers.tile.TileNodeEnergizedRenderer;
import thaumcraft.client.renderers.tile.TileNodeRenderer;
import thaumcraft.client.renderers.tile.TileNodeStabilizerRenderer;
import thaumcraft.client.renderers.tile.TilePedestalRenderer;
import thaumcraft.client.renderers.tile.TileResearchTableRenderer;
import thaumcraft.client.renderers.tile.TileRunicMatrixRenderer;
import thaumcraft.client.renderers.tile.TileTableRenderer;
import thaumcraft.client.renderers.tile.TileThaumatoriumRenderer;
import thaumcraft.client.renderers.tile.TileTubeBufferRenderer;
import thaumcraft.client.renderers.tile.TileTubeOnewayRenderer;
import thaumcraft.client.renderers.tile.TileTubeValveRenderer;
import thaumcraft.client.renderers.tile.TileVisRelayRenderer;
import thaumcraft.client.renderers.tile.TileWandPedestalRenderer;
import thaumcraft.client.renderers.tile.TileWardedRenderer;
import thaumcraft.common.CommonProxy;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigEntities;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.EntityAspectOrb;
import thaumcraft.common.entities.EntityFallingTaint;
import thaumcraft.common.entities.EntityFollowingItem;
import thaumcraft.common.entities.EntityItemGrate;
import thaumcraft.common.entities.EntityPermanentItem;
import thaumcraft.common.entities.EntitySpecialItem;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.entities.golems.EntityGolemBobber;
import thaumcraft.common.entities.golems.EntityTravelingTrunk;
import thaumcraft.common.entities.monster.EntityBrainyZombie;
import thaumcraft.common.entities.monster.EntityCultistCleric;
import thaumcraft.common.entities.monster.EntityCultistKnight;
import thaumcraft.common.entities.monster.EntityEldritchCrab;
import thaumcraft.common.entities.monster.EntityEldritchGuardian;
import thaumcraft.common.entities.monster.EntityFireBat;
import thaumcraft.common.entities.monster.EntityGiantBrainyZombie;
import thaumcraft.common.entities.monster.EntityInhabitedZombie;
import thaumcraft.common.entities.monster.EntityMindSpider;
import thaumcraft.common.entities.monster.EntityPech;
import thaumcraft.common.entities.monster.EntityTaintChicken;
import thaumcraft.common.entities.monster.EntityTaintCow;
import thaumcraft.common.entities.monster.EntityTaintCreeper;
import thaumcraft.common.entities.monster.EntityTaintPig;
import thaumcraft.common.entities.monster.EntityTaintSheep;
import thaumcraft.common.entities.monster.EntityTaintSpider;
import thaumcraft.common.entities.monster.EntityTaintSpore;
import thaumcraft.common.entities.monster.EntityTaintSporeSwarmer;
import thaumcraft.common.entities.monster.EntityTaintSwarm;
import thaumcraft.common.entities.monster.EntityTaintVillager;
import thaumcraft.common.entities.monster.EntityTaintacle;
import thaumcraft.common.entities.monster.EntityTaintacleSmall;
import thaumcraft.common.entities.monster.EntityThaumicSlime;
import thaumcraft.common.entities.monster.EntityWisp;
import thaumcraft.common.entities.monster.boss.EntityCultistLeader;
import thaumcraft.common.entities.monster.boss.EntityCultistPortal;
import thaumcraft.common.entities.monster.boss.EntityEldritchGolem;
import thaumcraft.common.entities.monster.boss.EntityEldritchWarden;
import thaumcraft.common.entities.monster.boss.EntityTaintacleGiant;
import thaumcraft.common.entities.projectile.EntityAlumentum;
import thaumcraft.common.entities.projectile.EntityBottleTaint;
import thaumcraft.common.entities.projectile.EntityDart;
import thaumcraft.common.entities.projectile.EntityEldritchOrb;
import thaumcraft.common.entities.projectile.EntityEmber;
import thaumcraft.common.entities.projectile.EntityExplosiveOrb;
import thaumcraft.common.entities.projectile.EntityFrostShard;
import thaumcraft.common.entities.projectile.EntityGolemOrb;
import thaumcraft.common.entities.projectile.EntityPechBlast;
import thaumcraft.common.entities.projectile.EntityPrimalArrow;
import thaumcraft.common.entities.projectile.EntityPrimalOrb;
import thaumcraft.common.entities.projectile.EntityShockOrb;
import thaumcraft.common.items.wands.WandManager;
import thaumcraft.common.lib.events.KeyHandler;
import thaumcraft.common.lib.research.PlayerKnowledge;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.tiles.TileAlchemyFurnace;
import thaumcraft.common.tiles.TileAlchemyFurnaceAdvanced;
import thaumcraft.common.tiles.TileAlembic;
import thaumcraft.common.tiles.TileArcaneBore;
import thaumcraft.common.tiles.TileArcaneBoreBase;
import thaumcraft.common.tiles.TileArcaneLamp;
import thaumcraft.common.tiles.TileArcaneLampFertility;
import thaumcraft.common.tiles.TileArcaneLampGrowth;
import thaumcraft.common.tiles.TileArcaneWorkbench;
import thaumcraft.common.tiles.TileBanner;
import thaumcraft.common.tiles.TileBellows;
import thaumcraft.common.tiles.TileCentrifuge;
import thaumcraft.common.tiles.TileChestHungry;
import thaumcraft.common.tiles.TileCrucible;
import thaumcraft.common.tiles.TileCrystal;
import thaumcraft.common.tiles.TileDeconstructionTable;
import thaumcraft.common.tiles.TileEldritchAltar;
import thaumcraft.common.tiles.TileEldritchCap;
import thaumcraft.common.tiles.TileEldritchCrabSpawner;
import thaumcraft.common.tiles.TileEldritchCrystal;
import thaumcraft.common.tiles.TileEldritchLock;
import thaumcraft.common.tiles.TileEldritchNothing;
import thaumcraft.common.tiles.TileEldritchObelisk;
import thaumcraft.common.tiles.TileEldritchPortal;
import thaumcraft.common.tiles.TileEssentiaCrystalizer;
import thaumcraft.common.tiles.TileEssentiaReservoir;
import thaumcraft.common.tiles.TileEtherealBloom;
import thaumcraft.common.tiles.TileFluxScrubber;
import thaumcraft.common.tiles.TileFocalManipulator;
import thaumcraft.common.tiles.TileHole;
import thaumcraft.common.tiles.TileInfusionMatrix;
import thaumcraft.common.tiles.TileInfusionPillar;
import thaumcraft.common.tiles.TileJar;
import thaumcraft.common.tiles.TileMagicBox;
import thaumcraft.common.tiles.TileMagicWorkbenchCharger;
import thaumcraft.common.tiles.TileManaPod;
import thaumcraft.common.tiles.TileMirror;
import thaumcraft.common.tiles.TileMirrorEssentia;
import thaumcraft.common.tiles.TileNode;
import thaumcraft.common.tiles.TileNodeConverter;
import thaumcraft.common.tiles.TileNodeEnergized;
import thaumcraft.common.tiles.TileNodeStabilizer;
import thaumcraft.common.tiles.TilePedestal;
import thaumcraft.common.tiles.TileResearchTable;
import thaumcraft.common.tiles.TileSpa;
import thaumcraft.common.tiles.TileTable;
import thaumcraft.common.tiles.TileThaumatorium;
import thaumcraft.common.tiles.TileTubeBuffer;
import thaumcraft.common.tiles.TileTubeOneway;
import thaumcraft.common.tiles.TileTubeValve;
import thaumcraft.common.tiles.TileVisRelay;
import thaumcraft.common.tiles.TileWandPedestal;
import thaumcraft.common.tiles.TileWarded;

public class ClientProxy
extends CommonProxy {
    protected PlayerKnowledge playerResearch = new PlayerKnowledge();
    protected ResearchManager researchManager = new ResearchManager();
    public WandManager wandManager = new WandManager();
    private HashMap<String, IIcon> customIcons = new HashMap();

    @Override
    public void registerHandlers() {
        FMLCommonHandler.instance().bus().register((Object)new ClientTickEventsFML());
        MinecraftForge.EVENT_BUS.register((Object)Thaumcraft.instance.renderEventHandler);
        MinecraftForge.EVENT_BUS.register((Object)ConfigBlocks.blockTube);
        MinecraftForge.EVENT_BUS.register((Object)ParticleEngine.instance);
        FMLCommonHandler.instance().bus().register((Object)ParticleEngine.instance);
    }

    @Override
    public void registerKeyBindings() {
        FMLCommonHandler.instance().bus().register((Object)new KeyHandler());
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (world instanceof WorldClient) {
            switch (ID) {
                case 0: {
                    return new GuiGolem(player, (EntityGolemBase)((WorldClient)world).func_73045_a(x));
                }
                case 1: {
                    return new GuiPech(player.field_71071_by, world, (EntityPech)((WorldClient)world).func_73045_a(x));
                }
                case 2: {
                    return new GuiTravelingTrunk(player, (EntityTravelingTrunk)((WorldClient)world).func_73045_a(x));
                }
                case 9: {
                    return new GuiAlchemyFurnace(player.field_71071_by, (TileAlchemyFurnace)world.func_147438_o(x, y, z));
                }
                case 3: {
                    return new GuiThaumatorium(player.field_71071_by, (TileThaumatorium)world.func_147438_o(x, y, z));
                }
                case 10: {
                    return new GuiResearchTable(player, (TileResearchTable)world.func_147438_o(x, y, z));
                }
                case 12: {
                    return new GuiResearchBrowser();
                }
                case 13: {
                    return new GuiArcaneWorkbench(player.field_71071_by, (TileArcaneWorkbench)world.func_147438_o(x, y, z));
                }
                case 8: {
                    return new GuiDeconstructionTable(player.field_71071_by, (TileDeconstructionTable)world.func_147438_o(x, y, z));
                }
                case 15: {
                    return new GuiArcaneBore(player.field_71071_by, (TileArcaneBore)world.func_147438_o(x, y, z));
                }
                case 16: {
                    return new GuiHandMirror(player.field_71071_by, world, x, y, z);
                }
                case 17: {
                    return new GuiHoverHarness(player.field_71071_by, world, x, y, z);
                }
                case 5: {
                    return new GuiFocusPouch(player.field_71071_by, world, x, y, z);
                }
                case 18: {
                    return new GuiMagicBox((IInventory)player.field_71071_by, (TileMagicBox)world.func_147438_o(x, y, z));
                }
                case 19: {
                    return new GuiSpa(player.field_71071_by, (TileSpa)world.func_147438_o(x, y, z));
                }
                case 20: {
                    return new GuiFocalManipulator(player.field_71071_by, (TileFocalManipulator)world.func_147438_o(x, y, z));
                }
            }
        }
        return null;
    }

    @Override
    public void registerDisplayInformation() {
        Thaumcraft.instance.aspectShift = FMLClientHandler.instance().hasOptifine();
        if (Loader.isModLoaded((String)"NotEnoughItems")) {
            Thaumcraft.instance.aspectShift = true;
        }
        this.setupItemRenderers();
        this.setupEntityRenderers();
        this.setupBlockRenderers();
        this.setupTileRenderers();
    }

    private void setupItemRenderers() {
        MinecraftForgeClient.registerItemRenderer((Item)ConfigItems.itemJarFilled, (IItemRenderer)new ItemJarFilledRenderer());
        MinecraftForgeClient.registerItemRenderer((Item)ConfigItems.itemJarNode, (IItemRenderer)new ItemJarNodeRenderer());
        MinecraftForgeClient.registerItemRenderer((Item)Item.func_150898_a((Block)ConfigBlocks.blockAiry), (IItemRenderer)new ItemNodeRenderer());
        MinecraftForgeClient.registerItemRenderer((Item)ConfigItems.itemThaumometer, (IItemRenderer)new ItemThaumometerRenderer());
        MinecraftForgeClient.registerItemRenderer((Item)ConfigItems.itemWandCasting, (IItemRenderer)new ItemWandRenderer());
        MinecraftForgeClient.registerItemRenderer((Item)ConfigItems.itemTrunkSpawner, (IItemRenderer)new ItemTrunkSpawnerRenderer());
        MinecraftForgeClient.registerItemRenderer((Item)ConfigItems.itemBowBone, (IItemRenderer)new ItemBowBoneRenderer());
        MinecraftForgeClient.registerItemRenderer((Item)Item.func_150898_a((Block)ConfigBlocks.blockWoodenDevice), (IItemRenderer)new ItemBannerRenderer());
    }

    private void setupEntityRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityItemGrate.class, (Render)new RenderItem());
        RenderingRegistry.registerEntityRenderingHandler(EntitySpecialItem.class, (Render)new RenderSpecialItem());
        RenderingRegistry.registerEntityRenderingHandler(EntityFollowingItem.class, (Render)new RenderFollowingItem());
        RenderingRegistry.registerEntityRenderingHandler(EntityPermanentItem.class, (Render)new RenderSpecialItem());
        RenderingRegistry.registerEntityRenderingHandler(EntityAspectOrb.class, (Render)new RenderAspectOrb());
        RenderingRegistry.registerEntityRenderingHandler(EntityGolemBobber.class, (Render)new RenderGolemBobber());
        RenderingRegistry.registerEntityRenderingHandler(EntityGolemBase.class, (Render)new RenderGolemBase(new ModelGolem(false)));
        RenderingRegistry.registerEntityRenderingHandler(EntityWisp.class, (Render)new RenderWisp());
        RenderingRegistry.registerEntityRenderingHandler(EntityAlumentum.class, (Render)new RenderAlumentum());
        RenderingRegistry.registerEntityRenderingHandler(EntityPrimalOrb.class, (Render)new RenderPrimalOrb());
        RenderingRegistry.registerEntityRenderingHandler(EntityEldritchOrb.class, (Render)new RenderEldritchOrb());
        RenderingRegistry.registerEntityRenderingHandler(EntityGolemOrb.class, (Render)new RenderElectricOrb());
        RenderingRegistry.registerEntityRenderingHandler(EntityEmber.class, (Render)new RenderEmber());
        RenderingRegistry.registerEntityRenderingHandler(EntityShockOrb.class, (Render)new RenderElectricOrb());
        RenderingRegistry.registerEntityRenderingHandler(EntityExplosiveOrb.class, (Render)new RenderExplosiveOrb());
        RenderingRegistry.registerEntityRenderingHandler(EntityPechBlast.class, (Render)new RenderPechBlast());
        RenderingRegistry.registerEntityRenderingHandler(EntityBrainyZombie.class, (Render)new RenderBrainyZombie());
        RenderingRegistry.registerEntityRenderingHandler(EntityInhabitedZombie.class, (Render)new RenderInhabitedZombie());
        RenderingRegistry.registerEntityRenderingHandler(EntityGiantBrainyZombie.class, (Render)new RenderBrainyZombie());
        RenderingRegistry.registerEntityRenderingHandler(EntityPech.class, (Render)new RenderPech(new ModelPech(), 0.25f));
        RenderingRegistry.registerEntityRenderingHandler(EntityFireBat.class, (Render)new RenderFireBat());
        RenderingRegistry.registerEntityRenderingHandler(EntityFrostShard.class, (Render)new RenderFrostShard());
        RenderingRegistry.registerEntityRenderingHandler(EntityDart.class, (Render)new RenderDart());
        RenderingRegistry.registerEntityRenderingHandler(EntityPrimalArrow.class, (Render)new RenderPrimalArrow());
        RenderingRegistry.registerEntityRenderingHandler(EntityFallingTaint.class, (Render)new RenderFallingTaint());
        RenderingRegistry.registerEntityRenderingHandler(EntityThaumicSlime.class, (Render)new RenderThaumicSlime((ModelBase)new ModelSlime(16), (ModelBase)new ModelSlime(0), 0.25f));
        RenderingRegistry.registerEntityRenderingHandler(EntityTaintSpider.class, (Render)new RenderTaintSpider());
        RenderingRegistry.registerEntityRenderingHandler(EntityTaintacle.class, (Render)new RenderTaintacle(0.6f, 10));
        RenderingRegistry.registerEntityRenderingHandler(EntityTaintacleSmall.class, (Render)new RenderTaintacle(0.2f, 6));
        RenderingRegistry.registerEntityRenderingHandler(EntityTaintacleGiant.class, (Render)new RenderTaintacle(1.0f, 14));
        RenderingRegistry.registerEntityRenderingHandler(EntityTaintSpore.class, (Render)new RenderTaintSpore());
        RenderingRegistry.registerEntityRenderingHandler(EntityTaintSporeSwarmer.class, (Render)new RenderTaintSporeSwarmer());
        RenderingRegistry.registerEntityRenderingHandler(EntityTaintSwarm.class, (Render)new RenderTaintSwarm());
        RenderingRegistry.registerEntityRenderingHandler(EntityTaintChicken.class, (Render)new RenderTaintChicken((ModelBase)new ModelChicken(), 0.3f));
        RenderingRegistry.registerEntityRenderingHandler(EntityTaintCow.class, (Render)new RenderTaintCow((ModelBase)new ModelCow(), 0.7f));
        RenderingRegistry.registerEntityRenderingHandler(EntityTaintCreeper.class, (Render)new RenderTaintCreeper());
        RenderingRegistry.registerEntityRenderingHandler(EntityTaintPig.class, (Render)new RenderTaintPig((ModelBase)new ModelPig(), 0.7f));
        RenderingRegistry.registerEntityRenderingHandler(EntityTaintSheep.class, (Render)new RenderTaintSheep((ModelBase)new ModelTaintSheep2(), (ModelBase)new ModelTaintSheep1(), 0.7f));
        RenderingRegistry.registerEntityRenderingHandler(EntityTaintVillager.class, (Render)new RenderTaintVillager());
        RenderingRegistry.registerEntityRenderingHandler(EntityTravelingTrunk.class, (Render)new RenderTravelingTrunk(new ModelTrunk(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityMindSpider.class, (Render)new RenderMindSpider());
        RenderingRegistry.registerEntityRenderingHandler(EntityEldritchGuardian.class, (Render)new RenderEldritchGuardian(new ModelEldritchGuardian(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityEldritchWarden.class, (Render)new RenderEldritchGuardian(new ModelEldritchGuardian(), 0.6f));
        RenderingRegistry.registerEntityRenderingHandler(EntityCultistPortal.class, (Render)new RenderCultistPortal());
        RenderingRegistry.registerEntityRenderingHandler(EntityCultistKnight.class, (Render)new RenderCultist());
        RenderingRegistry.registerEntityRenderingHandler(EntityCultistLeader.class, (Render)new RenderCultist());
        RenderingRegistry.registerEntityRenderingHandler(EntityCultistCleric.class, (Render)new RenderCultist());
        RenderingRegistry.registerEntityRenderingHandler(EntityEldritchGolem.class, (Render)new RenderEldritchGolem(new ModelEldritchGolem(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityBottleTaint.class, (Render)new RenderSnowball(ConfigItems.itemBottleTaint, 0));
        RenderingRegistry.registerEntityRenderingHandler(EntityEldritchCrab.class, (Render)new RenderEldritchCrab());
        VillagerRegistry.instance().registerVillagerSkin(ConfigEntities.entWizardId, new ResourceLocation("thaumcraft", "textures/models/wizard.png"));
        VillagerRegistry.instance().registerVillagerSkin(ConfigEntities.entBankerId, new ResourceLocation("thaumcraft", "textures/models/moneychanger.png"));
    }

    void setupTileRenderers() {
        this.registerTileEntitySpecialRenderer(TileAlembic.class, new TileAlembicRenderer());
        this.registerTileEntitySpecialRenderer(TileArcaneBore.class, new TileArcaneBoreRenderer());
        this.registerTileEntitySpecialRenderer(TileArcaneBoreBase.class, new TileArcaneBoreBaseRenderer());
        this.registerTileEntitySpecialRenderer(TileArcaneLamp.class, new TileArcaneLampRenderer());
        this.registerTileEntitySpecialRenderer(TileArcaneLampGrowth.class, new TileArcaneLampRenderer());
        this.registerTileEntitySpecialRenderer(TileArcaneLampFertility.class, new TileArcaneLampRenderer());
        this.registerTileEntitySpecialRenderer(TileArcaneWorkbench.class, new TileArcaneWorkbenchRenderer());
        this.registerTileEntitySpecialRenderer(TileBanner.class, new TileBannerRenderer());
        this.registerTileEntitySpecialRenderer(TileBellows.class, new TileBellowsRenderer());
        this.registerTileEntitySpecialRenderer(TileCentrifuge.class, new TileCentrifugeRenderer());
        this.registerTileEntitySpecialRenderer(TileChestHungry.class, new TileChestHungryRenderer());
        this.registerTileEntitySpecialRenderer(TileCrucible.class, new TileCrucibleRenderer());
        this.registerTileEntitySpecialRenderer(TileCrystal.class, new TileCrystalRenderer());
        this.registerTileEntitySpecialRenderer(TileEldritchCrystal.class, new TileEldritchCrystalRenderer());
        this.registerTileEntitySpecialRenderer(TileDeconstructionTable.class, new TileDeconstructionTableRenderer());
        this.registerTileEntitySpecialRenderer(TileEldritchAltar.class, new TileEldritchCapRenderer("textures/models/obelisk_cap_altar.png"));
        this.registerTileEntitySpecialRenderer(TileEldritchCap.class, new TileEldritchCapRenderer("textures/models/obelisk_cap.png"));
        this.registerTileEntitySpecialRenderer(TileEldritchCrabSpawner.class, new TileEldritchCrabSpawnerRenderer());
        this.registerTileEntitySpecialRenderer(TileEldritchNothing.class, new TileEldritchNothingRenderer());
        this.registerTileEntitySpecialRenderer(TileEldritchObelisk.class, new TileEldritchObeliskRenderer());
        this.registerTileEntitySpecialRenderer(TileEldritchPortal.class, new TileEldritchPortalRenderer());
        this.registerTileEntitySpecialRenderer(TileEldritchLock.class, new TileEldritchLockRenderer());
        this.registerTileEntitySpecialRenderer(TileEssentiaCrystalizer.class, new TileEssentiaCrystalizerRenderer());
        this.registerTileEntitySpecialRenderer(TileEssentiaReservoir.class, new TileEssentiaReservoirRenderer());
        this.registerTileEntitySpecialRenderer(TileEtherealBloom.class, new TileEtherealBloomRenderer());
        this.registerTileEntitySpecialRenderer(TileHole.class, new TileHoleRenderer());
        this.registerTileEntitySpecialRenderer(TileInfusionMatrix.class, new TileRunicMatrixRenderer(0));
        this.registerTileEntitySpecialRenderer(TileInfusionPillar.class, new TileInfusionPillarRenderer());
        this.registerTileEntitySpecialRenderer(TileJar.class, new TileJarRenderer());
        this.registerTileEntitySpecialRenderer(TileMagicWorkbenchCharger.class, new TileMagicWorkbenchChargerRenderer());
        this.registerTileEntitySpecialRenderer(TileManaPod.class, new TileManaPodRenderer());
        TileMirrorRenderer tmr = new TileMirrorRenderer();
        this.registerTileEntitySpecialRenderer(TileMirror.class, tmr);
        this.registerTileEntitySpecialRenderer(TileMirrorEssentia.class, tmr);
        this.registerTileEntitySpecialRenderer(TileNode.class, new TileNodeRenderer());
        this.registerTileEntitySpecialRenderer(TileNodeEnergized.class, new TileNodeEnergizedRenderer());
        this.registerTileEntitySpecialRenderer(TileNodeConverter.class, new TileNodeConverterRenderer());
        this.registerTileEntitySpecialRenderer(TileNodeStabilizer.class, new TileNodeStabilizerRenderer());
        this.registerTileEntitySpecialRenderer(TilePedestal.class, new TilePedestalRenderer());
        this.registerTileEntitySpecialRenderer(TileResearchTable.class, new TileResearchTableRenderer());
        this.registerTileEntitySpecialRenderer(TileTable.class, new TileTableRenderer());
        this.registerTileEntitySpecialRenderer(TileThaumatorium.class, new TileThaumatoriumRenderer());
        this.registerTileEntitySpecialRenderer(TileTubeBuffer.class, new TileTubeBufferRenderer());
        this.registerTileEntitySpecialRenderer(TileTubeOneway.class, new TileTubeOnewayRenderer());
        this.registerTileEntitySpecialRenderer(TileTubeValve.class, new TileTubeValveRenderer());
        this.registerTileEntitySpecialRenderer(TileVisRelay.class, new TileVisRelayRenderer());
        this.registerTileEntitySpecialRenderer(TileWandPedestal.class, new TileWandPedestalRenderer());
        this.registerTileEntitySpecialRenderer(TileWarded.class, new TileWardedRenderer());
        this.registerTileEntitySpecialRenderer(TileFocalManipulator.class, new TileFocalManipulatorRenderer());
        this.registerTileEntitySpecialRenderer(TileAlchemyFurnaceAdvanced.class, new TileAlchemyFurnaceAdvancedRenderer());
        this.registerTileEntitySpecialRenderer(TileFluxScrubber.class, new TileFluxScrubberRenderer());
    }

    void setupBlockRenderers() {
        ConfigBlocks.blockFluxGasRI = RenderingRegistry.getNextAvailableRenderId();
        this.registerBlockRenderer(new BlockGasRenderer());
        ConfigBlocks.blockArcaneFurnaceRI = RenderingRegistry.getNextAvailableRenderId();
        this.registerBlockRenderer(new BlockArcaneFurnaceRenderer());
        ConfigBlocks.blockMetalDeviceRI = RenderingRegistry.getNextAvailableRenderId();
        this.registerBlockRenderer(new BlockMetalDeviceRenderer());
        ConfigBlocks.blockStoneDeviceRI = RenderingRegistry.getNextAvailableRenderId();
        this.registerBlockRenderer(new BlockStoneDeviceRenderer());
        ConfigBlocks.blockTaintRI = RenderingRegistry.getNextAvailableRenderId();
        this.registerBlockRenderer(new BlockTaintRenderer());
        ConfigBlocks.blockCosmeticOpaqueRI = RenderingRegistry.getNextAvailableRenderId();
        this.registerBlockRenderer(new BlockCosmeticOpaqueRenderer());
        ConfigBlocks.blockTubeRI = RenderingRegistry.getNextAvailableRenderId();
        this.registerBlockRenderer(new BlockTubeRenderer());
        ConfigBlocks.blockTaintFibreRI = RenderingRegistry.getNextAvailableRenderId();
        this.registerBlockRenderer(new BlockTaintFibreRenderer());
        ConfigBlocks.blockJarRI = RenderingRegistry.getNextAvailableRenderId();
        this.registerBlockRenderer(new BlockJarRenderer());
        ConfigBlocks.blockCustomOreRI = RenderingRegistry.getNextAvailableRenderId();
        this.registerBlockRenderer(new BlockCustomOreRenderer());
        ConfigBlocks.blockChestHungryRI = RenderingRegistry.getNextAvailableRenderId();
        this.registerBlockRenderer(new BlockChestHungryRenderer());
        ConfigBlocks.blockTableRI = RenderingRegistry.getNextAvailableRenderId();
        this.registerBlockRenderer(new BlockTableRenderer());
        ConfigBlocks.blockCandleRI = RenderingRegistry.getNextAvailableRenderId();
        this.registerBlockRenderer(new BlockCandleRenderer());
        ConfigBlocks.blockWoodenDeviceRI = RenderingRegistry.getNextAvailableRenderId();
        this.registerBlockRenderer(new BlockWoodenDeviceRenderer());
        ConfigBlocks.blockLifterRI = RenderingRegistry.getNextAvailableRenderId();
        this.registerBlockRenderer(new BlockLifterRenderer());
        ConfigBlocks.blockCrystalRI = RenderingRegistry.getNextAvailableRenderId();
        this.registerBlockRenderer(new BlockCrystalRenderer());
        ConfigBlocks.blockWardedRI = RenderingRegistry.getNextAvailableRenderId();
        this.registerBlockRenderer(new BlockWardedRenderer());
        ConfigBlocks.blockEldritchRI = RenderingRegistry.getNextAvailableRenderId();
        this.registerBlockRenderer(new BlockEldritchRenderer());
        ConfigBlocks.blockEssentiaReservoirRI = RenderingRegistry.getNextAvailableRenderId();
        this.registerBlockRenderer(new BlockEssentiaReservoirRenderer());
        ConfigBlocks.blockLootUrnRI = RenderingRegistry.getNextAvailableRenderId();
        this.registerBlockRenderer(new BlockLootUrnRenderer());
        ConfigBlocks.blockLootCrateRI = RenderingRegistry.getNextAvailableRenderId();
        this.registerBlockRenderer(new BlockLootCrateRenderer());
    }

    public void registerTileEntitySpecialRenderer(Class tile, TileEntitySpecialRenderer renderer) {
        ClientRegistry.bindTileEntitySpecialRenderer((Class)tile, (TileEntitySpecialRenderer)renderer);
    }

    public void registerBlockRenderer(ISimpleBlockRenderingHandler renderer) {
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)renderer);
    }

    @Override
    public World getClientWorld() {
        return FMLClientHandler.instance().getClient().field_71441_e;
    }

    @Override
    public void blockSparkle(World world, int x, int y, int z, int c, int count) {
        Color color = new Color(c);
        float r = (float)color.getRed() / 255.0f;
        float g = (float)color.getGreen() / 255.0f;
        float b = (float)color.getBlue() / 255.0f;
        for (int a = 0; a < Thaumcraft.proxy.particleCount(count); ++a) {
            if (c == -9999) {
                r = 0.33f + world.field_73012_v.nextFloat() * 0.67f;
                g = 0.33f + world.field_73012_v.nextFloat() * 0.67f;
                b = 0.33f + world.field_73012_v.nextFloat() * 0.67f;
            }
            Thaumcraft.proxy.drawGenericParticles(world, (float)x - 0.1f + world.field_73012_v.nextFloat() * 1.2f, (float)y - 0.1f + world.field_73012_v.nextFloat() * 1.2f, (float)z - 0.1f + world.field_73012_v.nextFloat() * 1.2f, 0.0, (double)world.field_73012_v.nextFloat() * 0.02, 0.0, r - 0.2f + world.field_73012_v.nextFloat() * 0.4f, g - 0.2f + world.field_73012_v.nextFloat() * 0.4f, b - 0.2f + world.field_73012_v.nextFloat() * 0.4f, 0.9f, false, 112, 9, 1, 5 + world.field_73012_v.nextInt(8), world.field_73012_v.nextInt(10), 0.7f + world.field_73012_v.nextFloat() * 0.4f);
        }
    }

    @Override
    public void sparkle(float x, float y, float z, float size, int color, float gravity) {
        if (this.getClientWorld() != null && this.getClientWorld().field_73012_v.nextInt(6) < this.particleCount(2)) {
            FXSparkle fx = new FXSparkle(this.getClientWorld(), x, y, z, size, color, 6);
            fx.field_70145_X = true;
            fx.setGravity(gravity);
            ParticleEngine.instance.addEffect(this.getClientWorld(), fx);
        }
    }

    @Override
    public void sparkle(float x, float y, float z, int color) {
        if (this.getClientWorld() != null && this.getClientWorld().field_73012_v.nextInt(6) < this.particleCount(2)) {
            FXSparkle fx = new FXSparkle(this.getClientWorld(), x, y, z, 1.5f, color, 6);
            fx.field_70145_X = true;
            ParticleEngine.instance.addEffect(this.getClientWorld(), fx);
        }
    }

    @Override
    public void spark(float x, float y, float z, float size, float r, float g, float b, float a) {
        if (this.getClientWorld() != null) {
            FXSpark fx = new FXSpark(this.getClientWorld(), x, y, z, size);
            fx.func_70538_b(r, g, b);
            fx.func_82338_g(a);
            ParticleEngine.instance.addEffect(this.getClientWorld(), fx);
        }
    }

    @Override
    public void smokeSpiral(World world, double x, double y, double z, float rad, int start, int miny, int color) {
        FXSmokeSpiral fx = new FXSmokeSpiral(this.getClientWorld(), x, y, z, rad, start, miny);
        Color c = new Color(color);
        fx.func_70538_b((float)c.getRed() / 255.0f, (float)c.getGreen() / 255.0f, (float)c.getBlue() / 255.0f);
        ParticleEngine.instance.addEffect(world, fx);
    }

    @Override
    public void crucibleBoilSound(World world, int xCoord, int yCoord, int zCoord) {
        world.func_72980_b((double)((float)xCoord + 0.5f), (double)((float)yCoord + 0.5f), (double)((float)zCoord + 0.5f), "thaumcraft:spill", 0.2f, 1.0f, false);
    }

    @Override
    public void crucibleBoil(World world, int xCoord, int yCoord, int zCoord, TileCrucible tile, int j) {
        for (int a = 0; a < this.particleCount(1); ++a) {
            FXBubble fb = new FXBubble(world, (float)xCoord + 0.2f + world.field_73012_v.nextFloat() * 0.6f, (float)yCoord + 0.1f + tile.getFluidHeight(), (float)zCoord + 0.2f + world.field_73012_v.nextFloat() * 0.6f, 0.0, 0.0, 0.0, 3);
            if (tile.aspects.size() == 0) {
                fb.func_70538_b(1.0f, 1.0f, 1.0f);
            } else {
                Color color = new Color(tile.aspects.getAspects()[world.field_73012_v.nextInt(tile.aspects.getAspects().length)].getColor());
                fb.func_70538_b((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f);
            }
            fb.bubblespeed = 0.003 * (double)j;
            ParticleEngine.instance.addEffect(world, fb);
        }
    }

    @Override
    public void crucibleBubble(World world, float x, float y, float z, float cr, float cg, float cb) {
        FXBubble fb = new FXBubble(world, x, y, z, 0.0, 0.0, 0.0, 1);
        fb.func_70538_b(cr, cg, cb);
        ParticleEngine.instance.addEffect(world, fb);
    }

    @Override
    public void crucibleFroth(World world, float x, float y, float z) {
        FXBubble fb = new FXBubble(world, x, y, z, 0.0, 0.0, 0.0, -4);
        fb.func_70538_b(0.5f, 0.5f, 0.7f);
        fb.setFroth();
        ParticleEngine.instance.addEffect(world, fb);
    }

    @Override
    public void crucibleFrothDown(World world, float x, float y, float z) {
        FXBubble fb = new FXBubble(world, x, y, z, 0.0, 0.0, 0.0, -4);
        fb.func_70538_b(0.5f, 0.5f, 0.7f);
        fb.setFroth2();
        ParticleEngine.instance.addEffect(world, fb);
    }

    @Override
    public void wispFX(World worldObj, double posX, double posY, double posZ, float f, float g, float h, float i) {
        FXWisp ef = new FXWisp(worldObj, posX, posY, posZ, f, g, h, i);
        ef.setGravity(0.02f);
        ParticleEngine.instance.addEffect(worldObj, ef);
    }

    @Override
    public void wispFX2(World worldObj, double posX, double posY, double posZ, float size, int type, boolean shrink, boolean clip, float gravity) {
        FXWisp ef = new FXWisp(worldObj, posX, posY, posZ, size, type);
        ef.setGravity(gravity);
        ef.shrink = shrink;
        ef.field_70145_X = clip;
        ParticleEngine.instance.addEffect(worldObj, ef);
    }

    @Override
    public void wispFXEG(World worldObj, double posX, double posY, double posZ, Entity target) {
        for (int a = 0; a < this.particleCount(1); ++a) {
            FXWispEG ef = new FXWispEG(worldObj, posX, posY, posZ, target);
            ParticleEngine.instance.addEffect(worldObj, ef);
        }
    }

    @Override
    public void wispFX3(World worldObj, double posX, double posY, double posZ, double posX2, double posY2, double posZ2, float size, int type, boolean shrink, float gravity) {
        FXWisp ef = new FXWisp(worldObj, posX, posY, posZ, posX2, posY2, posZ2, size, type);
        ef.setGravity(gravity);
        ef.shrink = shrink;
        ParticleEngine.instance.addEffect(worldObj, ef);
    }

    @Override
    public void wispFX4(World worldObj, double posX, double posY, double posZ, Entity target, int type, boolean shrink, float gravity) {
        FXWisp ef = new FXWisp(worldObj, posX, posY, posZ, target, type);
        ef.setGravity(gravity);
        ef.shrink = shrink;
        ParticleEngine.instance.addEffect(worldObj, ef);
    }

    @Override
    public void burst(World worldObj, double sx, double sy, double sz, float size) {
        FXBurst ef = new FXBurst(worldObj, sx, sy, sz, size);
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)ef);
    }

    @Override
    public void sourceStreamFX(World worldObj, double sx, double sy, double sz, float tx, float ty, float tz, int tagColor) {
        Color c = new Color(tagColor);
        FXWispArcing ef = new FXWispArcing(worldObj, tx, ty, tz, sx, sy, sz, 0.1f, (float)c.getRed() / 255.0f, (float)c.getGreen() / 255.0f, (float)c.getBlue() / 255.0f);
        ef.setGravity(0.0f);
        ParticleEngine.instance.addEffect(worldObj, ef);
    }

    @Override
    public void bolt(World worldObj, Entity sourceEntity, Entity targetedEntity) {
        FXLightningBolt bolt = new FXLightningBolt(worldObj, sourceEntity, targetedEntity, worldObj.field_73012_v.nextLong(), 4);
        bolt.defaultFractal();
        bolt.setType(0);
        bolt.finalizeBolt();
    }

    @Override
    public void nodeBolt(World worldObj, float x, float y, float z, Entity targetedEntity) {
        FXLightningBolt bolt = new FXLightningBolt(worldObj, x, y, z, targetedEntity.field_70165_t, targetedEntity.field_70163_u, targetedEntity.field_70161_v, worldObj.field_73012_v.nextLong(), 10, 4.0f, 5);
        bolt.defaultFractal();
        bolt.setType(3);
        bolt.finalizeBolt();
    }

    @Override
    public void nodeBolt(World worldObj, float x, float y, float z, float x2, float y2, float z2) {
        FXLightningBolt bolt = new FXLightningBolt(worldObj, x, y, z, x2, y2, z2, worldObj.field_73012_v.nextLong(), 10, 4.0f, 5);
        bolt.defaultFractal();
        bolt.setType(0);
        bolt.finalizeBolt();
    }

    @Override
    public void excavateFX(int x, int y, int z, EntityPlayer p, int bi, int md, int progress) {
        RenderGlobal rg = Minecraft.func_71410_x().field_71438_f;
        rg.func_147587_b(p.func_145782_y(), x, y, z, progress);
    }

    @Override
    public void beam(World worldObj, double sx, double sy, double sz, double tx, double ty, double tz, int type, int color, boolean reverse, float endmod, int age) {
        FXBeam beamcon = null;
        Color c = new Color(color);
        beamcon = new FXBeam(worldObj, sx, sy, sz, tx, ty, tz, (float)c.getRed() / 255.0f, (float)c.getGreen() / 255.0f, (float)c.getBlue() / 255.0f, age);
        beamcon.setType(type);
        beamcon.setEndMod(endmod);
        beamcon.setReverse(reverse);
        beamcon.setPulse(false);
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)beamcon);
    }

    @Override
    public Object beamCont(World worldObj, EntityPlayer p, double tx, double ty, double tz, int type, int color, boolean reverse, float endmod, Object input, int impact) {
        FXBeamWand beamcon = null;
        Color c = new Color(color);
        if (input instanceof FXBeamWand) {
            beamcon = (FXBeamWand)((Object)input);
        }
        if (beamcon == null || beamcon.field_70128_L) {
            beamcon = new FXBeamWand(worldObj, p, tx, ty, tz, (float)c.getRed() / 255.0f, (float)c.getGreen() / 255.0f, (float)c.getBlue() / 255.0f, 8);
            beamcon.setType(type);
            beamcon.setEndMod(endmod);
            beamcon.setReverse(reverse);
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)beamcon);
        } else {
            beamcon.updateBeam(tx, ty, tz);
            beamcon.setEndMod(endmod);
            beamcon.impact = impact;
        }
        return beamcon;
    }

    @Override
    public Object beamBore(World worldObj, double px, double py, double pz, double tx, double ty, double tz, int type, int color, boolean reverse, float endmod, Object input, int impact) {
        FXBeamBore beamcon = null;
        Color c = new Color(color);
        if (input instanceof FXBeamBore) {
            beamcon = (FXBeamBore)((Object)input);
        }
        if (beamcon == null || beamcon.field_70128_L) {
            beamcon = new FXBeamBore(worldObj, px, py, pz, tx, ty, tz, (float)c.getRed() / 255.0f, (float)c.getGreen() / 255.0f, (float)c.getBlue() / 255.0f, 8);
            beamcon.setType(type);
            beamcon.setEndMod(endmod);
            beamcon.setReverse(reverse);
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)beamcon);
        } else {
            beamcon.updateBeam(tx, ty, tz);
            beamcon.setEndMod(endmod);
            beamcon.impact = impact;
        }
        return beamcon;
    }

    @Override
    public void boreDigFx(World worldObj, int x, int y, int z, int x2, int y2, int z2, Block bi, int md) {
        if (worldObj.field_73012_v.nextInt(10) == 0) {
            FXBoreSparkle fb = new FXBoreSparkle(worldObj, (float)x + worldObj.field_73012_v.nextFloat(), (float)y + worldObj.field_73012_v.nextFloat(), (float)z + worldObj.field_73012_v.nextFloat(), (double)x2 + 0.5, (double)y2 + 0.5, (double)z2 + 0.5);
            ParticleEngine.instance.addEffect(worldObj, fb);
        } else {
            FXBoreParticles fb = new FXBoreParticles(worldObj, (double)((float)x + worldObj.field_73012_v.nextFloat()), (double)((float)y + worldObj.field_73012_v.nextFloat()), (double)((float)z + worldObj.field_73012_v.nextFloat()), (double)x2 + 0.5, (double)y2 + 0.5, (double)z2 + 0.5, bi, worldObj.field_73012_v.nextInt(6), md).func_70596_a(x, y, z);
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fb);
        }
    }

    @Override
    public void essentiaTrailFx(World worldObj, int x, int y, int z, int x2, int y2, int z2, int count, int color, float scale) {
        FXEssentiaTrail fb = new FXEssentiaTrail(worldObj, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, (double)x2 + 0.5, (double)y2 + 0.5, (double)z2 + 0.5, count, color, scale);
        ParticleEngine.instance.addEffect(worldObj, fb);
    }

    @Override
    public void soulTrail(World world, Entity source, Entity target, float r, float g, float b) {
        for (int a = 0; a < this.particleCount(2); ++a) {
            EntityFX st;
            if (world.field_73012_v.nextInt(10) == 0) {
                st = new FXSparkleTrail(world, source.field_70165_t - (double)(source.field_70130_N / 2.0f) + (double)(world.field_73012_v.nextFloat() * source.field_70130_N), source.field_70163_u + (double)(world.field_73012_v.nextFloat() * source.field_70131_O), source.field_70161_v - (double)(source.field_70130_N / 2.0f) + (double)(world.field_73012_v.nextFloat() * source.field_70130_N), target, r, g, b);
                st.field_70145_X = true;
                ParticleEngine.instance.addEffect(world, st);
                continue;
            }
            st = new FXSmokeTrail(world, source.field_70165_t - (double)(source.field_70130_N / 2.0f) + (double)(world.field_73012_v.nextFloat() * source.field_70130_N), source.field_70163_u + (double)(world.field_73012_v.nextFloat() * source.field_70131_O), source.field_70161_v - (double)(source.field_70130_N / 2.0f) + (double)(world.field_73012_v.nextFloat() * source.field_70130_N), target, r, g, b);
            st.field_70145_X = true;
            ParticleEngine.instance.addEffect(world, st);
        }
    }

    @Override
    public int particleCount(int base) {
        if (FMLClientHandler.instance().getClient().field_71474_y.field_74362_aa == 2) {
            return 0;
        }
        return FMLClientHandler.instance().getClient().field_71474_y.field_74362_aa == 1 ? base * 1 : base * 2;
    }

    @Override
    public void furnaceLavaFx(World worldObj, int x, int y, int z, int facingX, int facingZ) {
        EntityLavaFX fb = new EntityLavaFX(worldObj, (double)((float)x + 0.5f + (worldObj.field_73012_v.nextFloat() - worldObj.field_73012_v.nextFloat()) * 0.3f + (float)facingX * 1.0f), (double)((float)y + 0.3f), (double)((float)z + 0.5f + (worldObj.field_73012_v.nextFloat() - worldObj.field_73012_v.nextFloat()) * 0.3f + (float)facingZ * 1.0f));
        fb.field_70181_x = 0.2f * worldObj.field_73012_v.nextFloat();
        float qx = facingX == 0 ? (worldObj.field_73012_v.nextFloat() - worldObj.field_73012_v.nextFloat()) * 0.5f : (float)facingX * worldObj.field_73012_v.nextFloat();
        float qz = facingZ == 0 ? (worldObj.field_73012_v.nextFloat() - worldObj.field_73012_v.nextFloat()) * 0.5f : (float)facingZ * worldObj.field_73012_v.nextFloat();
        fb.field_70159_w = 0.15f * qx;
        fb.field_70179_y = 0.15f * qz;
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fb);
    }

    @Override
    public void blockRunes(World world, double x, double y, double z, float r, float g, float b, int dur, float grav) {
        FXBlockRunes fb = new FXBlockRunes(world, x + 0.5, y + 0.5, z + 0.5, r, g, b, dur);
        fb.setGravity(grav);
        ParticleEngine.instance.addEffect(world, fb);
    }

    @Override
    public void blockWard(World world, double x, double y, double z, ForgeDirection side, float f, float f1, float f2) {
        FXBlockWard fb = new FXBlockWard(world, x + 0.5, y + 0.5, z + 0.5, side, f, f1, f2);
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fb);
    }

    @Override
    public Object swarmParticleFX(World worldObj, Entity targetedEntity, float f1, float f2, float pg) {
        FXSwarm fx = new FXSwarm(worldObj, targetedEntity.field_70165_t + (double)((worldObj.field_73012_v.nextFloat() - worldObj.field_73012_v.nextFloat()) * 2.0f), targetedEntity.field_70163_u + (double)((worldObj.field_73012_v.nextFloat() - worldObj.field_73012_v.nextFloat()) * 2.0f), targetedEntity.field_70161_v + (double)((worldObj.field_73012_v.nextFloat() - worldObj.field_73012_v.nextFloat()) * 2.0f), targetedEntity, 0.8f + worldObj.field_73012_v.nextFloat() * 0.2f, worldObj.field_73012_v.nextFloat() * 0.4f, 1.0f - worldObj.field_73012_v.nextFloat() * 0.2f, f1, f2, pg);
        ParticleEngine.instance.addEffect(worldObj, fx);
        return fx;
    }

    @Override
    public void splooshFX(Entity e) {
        float f = e.field_70170_p.field_73012_v.nextFloat() * (float)Math.PI * 2.0f;
        float f1 = e.field_70170_p.field_73012_v.nextFloat() * 0.5f + 0.5f;
        float f2 = MathHelper.func_76126_a((float)f) * 2.0f * 0.5f * f1;
        float f3 = MathHelper.func_76134_b((float)f) * 2.0f * 0.5f * f1;
        FXBreaking fx = new FXBreaking(e.field_70170_p, e.field_70165_t + (double)f2, e.field_70163_u + (double)(e.field_70170_p.field_73012_v.nextFloat() * e.field_70131_O), e.field_70161_v + (double)f3, Items.field_151123_aH);
        if (e.field_70170_p.field_73012_v.nextBoolean()) {
            fx.func_70538_b(0.6f, 0.0f, 0.3f);
            fx.func_82338_g(0.4f);
        } else {
            fx.func_70538_b(0.3f, 0.0f, 0.3f);
            fx.func_82338_g(0.6f);
        }
        fx.setParticleMaxAge((int)(66.0f / (e.field_70170_p.field_73012_v.nextFloat() * 0.9f + 0.1f)));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fx);
    }

    @Override
    public void splooshFX(World worldObj, int x, int y, int z) {
        float f = worldObj.field_73012_v.nextFloat() * (float)Math.PI * 2.0f;
        float f1 = worldObj.field_73012_v.nextFloat() * 0.5f + 0.5f;
        float f2 = MathHelper.func_76126_a((float)f) * 2.0f * 0.5f * f1;
        float f3 = MathHelper.func_76134_b((float)f) * 2.0f * 0.5f * f1;
        FXBreaking fx = new FXBreaking(worldObj, (double)x + (double)f2 + 0.5, (float)y + worldObj.field_73012_v.nextFloat(), (double)z + (double)f3 + 0.5, Items.field_151123_aH);
        if (worldObj.field_73012_v.nextBoolean()) {
            fx.func_70538_b(0.6f, 0.0f, 0.3f);
            fx.func_82338_g(0.4f);
        } else {
            fx.func_70538_b(0.3f, 0.0f, 0.3f);
            fx.func_82338_g(0.6f);
        }
        fx.setParticleMaxAge((int)(66.0f / (worldObj.field_73012_v.nextFloat() * 0.9f + 0.1f)));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fx);
    }

    @Override
    public void taintsplosionFX(Entity e) {
        FXBreaking fx = new FXBreaking(e.field_70170_p, e.field_70165_t, e.field_70163_u + (double)(e.field_70170_p.field_73012_v.nextFloat() * e.field_70131_O), e.field_70161_v, Items.field_151123_aH);
        if (e.field_70170_p.field_73012_v.nextBoolean()) {
            fx.func_70538_b(0.6f, 0.0f, 0.3f);
            fx.func_82338_g(0.4f);
        } else {
            fx.func_70538_b(0.3f, 0.0f, 0.3f);
            fx.func_82338_g(0.6f);
        }
        fx.field_70159_w = (float)(Math.random() * 2.0 - 1.0);
        fx.field_70181_x = (float)(Math.random() * 2.0 - 1.0);
        fx.field_70179_y = (float)(Math.random() * 2.0 - 1.0);
        float f = (float)(Math.random() + Math.random() + 1.0) * 0.15f;
        float f1 = MathHelper.func_76133_a((double)(fx.field_70159_w * fx.field_70159_w + fx.field_70181_x * fx.field_70181_x + fx.field_70179_y * fx.field_70179_y));
        fx.field_70159_w = fx.field_70159_w / (double)f1 * (double)f * 0.9640000000596046;
        fx.field_70181_x = fx.field_70181_x / (double)f1 * (double)f * 0.9640000000596046 + (double)0.1f;
        fx.field_70179_y = fx.field_70179_y / (double)f1 * (double)f * 0.9640000000596046;
        fx.setParticleMaxAge((int)(66.0f / (e.field_70170_p.field_73012_v.nextFloat() * 0.9f + 0.1f)));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fx);
    }

    @Override
    public void tentacleAriseFX(Entity e) {
        int xx = MathHelper.func_76128_c((double)e.field_70165_t);
        int yy = MathHelper.func_76128_c((double)e.field_70163_u) - 1;
        int zz = MathHelper.func_76128_c((double)e.field_70161_v);
        int j = 0;
        while ((float)j < 2.0f * e.field_70131_O) {
            float f = e.field_70170_p.field_73012_v.nextFloat() * (float)Math.PI * e.field_70131_O;
            float f1 = e.field_70170_p.field_73012_v.nextFloat() * 0.5f + 0.5f;
            float f2 = MathHelper.func_76126_a((float)f) * e.field_70131_O * 0.25f * f1;
            float f3 = MathHelper.func_76134_b((float)f) * e.field_70131_O * 0.25f * f1;
            FXBreaking fx = new FXBreaking(e.field_70170_p, e.field_70165_t + (double)f2, e.field_70163_u, e.field_70161_v + (double)f3, Items.field_151123_aH);
            fx.func_70538_b(0.4f, 0.0f, 0.4f);
            fx.func_82338_g(0.5f);
            fx.setParticleMaxAge((int)(66.0f / (e.field_70170_p.field_73012_v.nextFloat() * 0.9f + 0.1f)));
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fx);
            if (!e.field_70170_p.func_147437_c(xx, yy, zz)) {
                f = e.field_70170_p.field_73012_v.nextFloat() * (float)Math.PI * e.field_70131_O;
                f1 = e.field_70170_p.field_73012_v.nextFloat() * 0.5f + 0.5f;
                f2 = MathHelper.func_76126_a((float)f) * e.field_70131_O * 0.25f * f1;
                f3 = MathHelper.func_76134_b((float)f) * e.field_70131_O * 0.25f * f1;
                EntityDiggingFX fx2 = new EntityDiggingFX(e.field_70170_p, e.field_70165_t + (double)f2, e.field_70163_u, e.field_70161_v + (double)f3, 0.0, 0.0, 0.0, e.field_70170_p.func_147439_a(xx, yy, zz), e.field_70170_p.func_72805_g(xx, yy, zz), 1).func_70596_a(xx, yy, zz);
                FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fx2);
            }
            ++j;
        }
    }

    @Override
    public void slimeJumpFX(Entity e, int i) {
        float f = e.field_70170_p.field_73012_v.nextFloat() * (float)Math.PI * 2.0f;
        float f1 = e.field_70170_p.field_73012_v.nextFloat() * 0.5f + 0.5f;
        float f2 = MathHelper.func_76126_a((float)f) * (float)i * 0.5f * f1;
        float f3 = MathHelper.func_76134_b((float)f) * (float)i * 0.5f * f1;
        FXBreaking fx = new FXBreaking(e.field_70170_p, e.field_70165_t + (double)f2, (e.field_70121_D.field_72338_b + e.field_70121_D.field_72337_e) / 2.0, e.field_70161_v + (double)f3, Items.field_151123_aH);
        fx.func_70538_b(0.7f, 0.0f, 1.0f);
        fx.func_82338_g(0.4f);
        fx.setParticleMaxAge((int)(66.0f / (e.field_70170_p.field_73012_v.nextFloat() * 0.9f + 0.1f)));
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fx);
    }

    @Override
    public void dropletFX(World world, float i, float j, float k, float r, float g, float b) {
        FXDrop obj = new FXDrop(world, i, j, k, r, g, b);
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)obj);
    }

    @Override
    public void taintLandFX(Entity e) {
        float f = e.field_70170_p.field_73012_v.nextFloat() * (float)Math.PI * 2.0f;
        float f1 = e.field_70170_p.field_73012_v.nextFloat() * 0.5f + 0.5f;
        float f2 = MathHelper.func_76126_a((float)f) * 2.0f * 0.5f * f1;
        float f3 = MathHelper.func_76134_b((float)f) * 2.0f * 0.5f * f1;
        if (e.field_70170_p.field_72995_K) {
            FXBreaking fx = new FXBreaking(e.field_70170_p, e.field_70165_t + (double)f2, (e.field_70121_D.field_72338_b + e.field_70121_D.field_72337_e) / 2.0, e.field_70161_v + (double)f3, Items.field_151123_aH);
            fx.func_70538_b(0.1f, 0.0f, 0.1f);
            fx.func_82338_g(0.4f);
            fx.setParticleMaxAge((int)(66.0f / (e.field_70170_p.field_73012_v.nextFloat() * 0.9f + 0.1f)));
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fx);
        }
    }

    @Override
    public void hungryNodeFX(World worldObj, int sourceX, int sourceY, int sourceZ, int targetX, int targetY, int targetZ, Block block, int md) {
        FXBoreParticles fb = new FXBoreParticles(worldObj, (double)((float)sourceX + worldObj.field_73012_v.nextFloat()), (double)((float)sourceY + worldObj.field_73012_v.nextFloat()), (double)((float)sourceZ + worldObj.field_73012_v.nextFloat()), (double)targetX + 0.5, (double)targetY + 0.5, (double)targetZ + 0.5, block, worldObj.field_73012_v.nextInt(6), md).func_70596_a(sourceX, sourceY, sourceZ);
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fb);
    }

    @Override
    public void drawInfusionParticles1(World worldObj, double x, double y, double z, int x2, int y2, int z2, Item id, int md) {
        FXBoreParticles fb = new FXBoreParticles(worldObj, x, y, z, (double)x2 + 0.5, (double)y2 - 0.5, (double)z2 + 0.5, id, worldObj.field_73012_v.nextInt(6), md).func_70596_a(x2, y2, z2);
        fb.func_82338_g(0.3f);
        fb.field_70159_w = (float)worldObj.field_73012_v.nextGaussian() * 0.03f;
        fb.field_70181_x = (float)worldObj.field_73012_v.nextGaussian() * 0.03f;
        fb.field_70179_y = (float)worldObj.field_73012_v.nextGaussian() * 0.03f;
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fb);
    }

    @Override
    public void drawInfusionParticles2(World worldObj, double x, double y, double z, int x2, int y2, int z2, Block id, int md) {
        FXBoreParticles fb = new FXBoreParticles(worldObj, x, y, z, (double)x2 + 0.5, (double)y2 - 0.5, (double)z2 + 0.5, id, worldObj.field_73012_v.nextInt(6), md).func_70596_a(x2, y2, z2);
        fb.func_82338_g(0.3f);
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fb);
    }

    @Override
    public void drawInfusionParticles3(World worldObj, double x, double y, double z, int x2, int y2, int z2) {
        FXBoreSparkle fb = new FXBoreSparkle(worldObj, x, y, z, (double)x2 + 0.5, (double)y2 - 0.5, (double)z2 + 0.5);
        fb.func_70538_b(0.4f + worldObj.field_73012_v.nextFloat() * 0.2f, 0.2f, 0.6f + worldObj.field_73012_v.nextFloat() * 0.3f);
        ParticleEngine.instance.addEffect(worldObj, fb);
    }

    @Override
    public void drawInfusionParticles4(World worldObj, double x, double y, double z, int x2, int y2, int z2) {
        FXBoreSparkle fb = new FXBoreSparkle(worldObj, x, y, z, (double)x2 + 0.5, (double)y2 - 0.5, (double)z2 + 0.5);
        fb.func_70538_b(0.2f, 0.6f + worldObj.field_73012_v.nextFloat() * 0.3f, 0.3f);
        ParticleEngine.instance.addEffect(worldObj, fb);
    }

    @Override
    public void drawVentParticles(World worldObj, double x, double y, double z, double x2, double y2, double z2, int color) {
        FXVent fb = new FXVent(worldObj, x, y, z, x2, y2, z2, color);
        fb.func_82338_g(0.4f);
        ParticleEngine.instance.addEffect(worldObj, fb);
    }

    @Override
    public void drawGenericParticles(World worldObj, double x, double y, double z, double x2, double y2, double z2, float r, float g, float b, float alpha, boolean loop, int start, int num, int inc, int age, int delay, float scale) {
        FXGeneric fb = new FXGeneric(worldObj, x, y, z, x2, y2, z2);
        fb.setMaxAge(age, delay);
        fb.func_70538_b(r, g, b);
        fb.func_82338_g(alpha);
        fb.setLoop(loop);
        fb.setParticles(start, num, inc);
        fb.setScale(scale);
        ParticleEngine.instance.addEffect(worldObj, fb);
    }

    @Override
    public void drawVentParticles(World worldObj, double x, double y, double z, double x2, double y2, double z2, int color, float scale) {
        FXVent fb = new FXVent(worldObj, x, y, z, x2, y2, z2, color);
        fb.func_82338_g(0.4f);
        fb.setScale(scale);
        ParticleEngine.instance.addEffect(worldObj, fb);
    }

    @Override
    public Object beamPower(World worldObj, double px, double py, double pz, double tx, double ty, double tz, float r, float g, float b, boolean pulse, Object input) {
        FXBeamPower beamcon = null;
        if (input instanceof FXBeamPower) {
            beamcon = (FXBeamPower)((Object)input);
        }
        if (beamcon == null || beamcon.field_70128_L) {
            beamcon = new FXBeamPower(worldObj, px, py, pz, tx, ty, tz, r, g, b, 8);
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)beamcon);
        } else {
            beamcon.updateBeam(px, py, pz, tx, ty, tz);
            beamcon.setPulse(pulse, r, g, b);
        }
        return beamcon;
    }

    @Override
    public boolean isShiftKeyDown() {
        return GuiScreen.func_146272_n();
    }

    @Override
    public void bottleTaintBreak(World world, double x, double y, double z) {
        String s = "iconcrack_" + Item.func_150891_b((Item)ConfigItems.itemBottleTaint) + "_" + 0;
        for (int k1 = 0; k1 < 8; ++k1) {
            Minecraft.func_71410_x().field_71438_f.func_72708_a(s, x, y, z, world.field_73012_v.nextGaussian() * 0.15, world.field_73012_v.nextDouble() * 0.2, world.field_73012_v.nextGaussian() * 0.15);
        }
        world.func_72980_b(x, y, z, "game.potion.smash", 1.0f, world.field_73012_v.nextFloat() * 0.1f + 0.9f, false);
    }

    @Override
    public void arcLightning(World world, double x, double y, double z, double tx, double ty, double tz, float r, float g, float b, float h) {
        FXSparkle ef2 = new FXSparkle(world, tx, ty, tz, tx, ty, tz, 3.0f, 6, 2);
        ef2.setGravity(0.0f);
        ef2.field_70145_X = true;
        ef2.func_70538_b(r, g, b);
        ParticleEngine.instance.addEffect(world, ef2);
        FXArc efa = new FXArc(world, x, y, z, tx, ty, tz, r, g, b, h);
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)efa);
    }
}

