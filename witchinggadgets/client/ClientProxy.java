/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaublesApi
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  cpw.mods.fml.common.FMLCommonHandler
 *  net.minecraft.block.Block
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.particle.EntityLavaFX
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.MinecraftForgeClient
 *  net.minecraftforge.client.model.IModelCustom
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.client.fx.ParticleEngine
 *  thaumcraft.client.fx.particles.FXEssentiaTrail
 *  thaumcraft.client.fx.particles.FXWisp
 *  travellersgear.api.TravellersGearAPI
 */
package witchinggadgets.client;

import baubles.api.BaublesApi;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.block.Block;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityLavaFX;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXEssentiaTrail;
import thaumcraft.client.fx.particles.FXWisp;
import travellersgear.api.TravellersGearAPI;
import witchinggadgets.asm.pouch.GuiPatchedFocusPouch;
import witchinggadgets.client.ClientEventHandler;
import witchinggadgets.client.ClientTickHandler;
import witchinggadgets.client.ClientUtilities;
import witchinggadgets.client.ThaumonomiconIndexSearcher;
import witchinggadgets.client.fx.EntityFXSweat;
import witchinggadgets.client.gui.GuiBag;
import witchinggadgets.client.gui.GuiCloakBag;
import witchinggadgets.client.gui.GuiCuttingTable;
import witchinggadgets.client.gui.GuiLabelLibrary;
import witchinggadgets.client.gui.GuiMagicalTileLock;
import witchinggadgets.client.gui.GuiPrimordialGlove;
import witchinggadgets.client.gui.GuiSpinningWheel;
import witchinggadgets.client.gui.GuiVoidBag;
import witchinggadgets.client.render.BlockRenderMetalDevice;
import witchinggadgets.client.render.BlockRenderRoseVine;
import witchinggadgets.client.render.BlockRenderStoneDevice;
import witchinggadgets.client.render.BlockRenderWoodenDevice;
import witchinggadgets.client.render.EntityRenderReforming;
import witchinggadgets.client.render.ItemRenderCapsule;
import witchinggadgets.client.render.ItemRenderMagicalBaubles;
import witchinggadgets.client.render.ItemRenderMaterial;
import witchinggadgets.client.render.ItemRenderPrimordialGauntlet;
import witchinggadgets.client.render.ItemRenderScanCamera;
import witchinggadgets.client.render.ItemRenderWallMirror;
import witchinggadgets.client.render.TileRenderCobbleGen;
import witchinggadgets.client.render.TileRenderCuttingTable;
import witchinggadgets.client.render.TileRenderEssentiaPump;
import witchinggadgets.client.render.TileRenderLabelLibrary;
import witchinggadgets.client.render.TileRenderMagicalTileLock;
import witchinggadgets.client.render.TileRenderSarcophagus;
import witchinggadgets.client.render.TileRenderSaunaStove;
import witchinggadgets.client.render.TileRenderSnowGen;
import witchinggadgets.client.render.TileRenderSpinningWheel;
import witchinggadgets.client.render.TileRenderTerraformFocus;
import witchinggadgets.client.render.TileRenderTerraformer;
import witchinggadgets.client.render.TileRenderWallMirror;
import witchinggadgets.common.CommonProxy;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.blocks.tiles.TileEntityCobbleGen;
import witchinggadgets.common.blocks.tiles.TileEntityCuttingTable;
import witchinggadgets.common.blocks.tiles.TileEntityEssentiaPump;
import witchinggadgets.common.blocks.tiles.TileEntityLabelLibrary;
import witchinggadgets.common.blocks.tiles.TileEntityMagicalTileLock;
import witchinggadgets.common.blocks.tiles.TileEntitySarcophagus;
import witchinggadgets.common.blocks.tiles.TileEntitySaunaStove;
import witchinggadgets.common.blocks.tiles.TileEntitySnowGen;
import witchinggadgets.common.blocks.tiles.TileEntitySpinningWheel;
import witchinggadgets.common.blocks.tiles.TileEntityTerraformFocus;
import witchinggadgets.common.blocks.tiles.TileEntityTerraformer;
import witchinggadgets.common.blocks.tiles.TileEntityWallMirror;
import witchinggadgets.common.items.EntityItemReforming;
import witchinggadgets.common.util.WGKeyHandler;

public class ClientProxy
extends CommonProxy {
    public static IModelCustom eliteArmorModel;
    public static IModelCustom cameraModel;
    public static IModelCustom gauntletModel;
    public static IModelCustom gemModel;
    public static IModelCustom terraformerModel;

    @Override
    public void registerRenders() {
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockRenderRoseVine());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockRenderWoodenDevice());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockRenderStoneDevice());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new BlockRenderMetalDevice());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityWallMirror.class, (TileEntitySpecialRenderer)new TileRenderWallMirror());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySpinningWheel.class, (TileEntitySpecialRenderer)new TileRenderSpinningWheel());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySnowGen.class, (TileEntitySpecialRenderer)new TileRenderSnowGen());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCobbleGen.class, (TileEntitySpecialRenderer)new TileRenderCobbleGen());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMagicalTileLock.class, (TileEntitySpecialRenderer)new TileRenderMagicalTileLock());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySarcophagus.class, (TileEntitySpecialRenderer)new TileRenderSarcophagus());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCuttingTable.class, (TileEntitySpecialRenderer)new TileRenderCuttingTable());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLabelLibrary.class, (TileEntitySpecialRenderer)new TileRenderLabelLibrary());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySaunaStove.class, (TileEntitySpecialRenderer)new TileRenderSaunaStove());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTerraformer.class, (TileEntitySpecialRenderer)new TileRenderTerraformer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTerraformFocus.class, (TileEntitySpecialRenderer)new TileRenderTerraformFocus());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEssentiaPump.class, (TileEntitySpecialRenderer)new TileRenderEssentiaPump());
        eliteArmorModel = ClientUtilities.bindModel("witchinggadgets", "models/EliteRunicArmor.obj");
        cameraModel = ClientUtilities.bindModel("witchinggadgets", "models/ScanCamera.obj");
        gauntletModel = ClientUtilities.bindModel("witchinggadgets", "models/gauntlet.obj");
        gemModel = ClientUtilities.bindModel("witchinggadgets", "models/gems.obj");
        terraformerModel = ClientUtilities.bindModel("witchinggadgets", "models/terraformer.obj");
        MinecraftForgeClient.registerItemRenderer((Item)Item.func_150898_a((Block)WGContent.BlockWallMirror), (IItemRenderer)new ItemRenderWallMirror());
        MinecraftForgeClient.registerItemRenderer((Item)WGContent.ItemMaterial, (IItemRenderer)new ItemRenderMaterial());
        MinecraftForgeClient.registerItemRenderer((Item)WGContent.ItemScanCamera, (IItemRenderer)new ItemRenderScanCamera());
        MinecraftForgeClient.registerItemRenderer((Item)WGContent.ItemPrimordialGlove, (IItemRenderer)new ItemRenderPrimordialGauntlet());
        MinecraftForgeClient.registerItemRenderer((Item)WGContent.ItemMagicalBaubles, (IItemRenderer)new ItemRenderMagicalBaubles());
        MinecraftForgeClient.registerItemRenderer((Item)WGContent.ItemCapsule, (IItemRenderer)new ItemRenderCapsule());
        RenderingRegistry.registerEntityRenderingHandler(EntityItemReforming.class, (Render)new EntityRenderReforming());
    }

    @Override
    public void registerHandlers() {
        MinecraftForge.EVENT_BUS.register((Object)new ClientEventHandler());
        FMLCommonHandler.instance().bus().register((Object)new WGKeyHandler());
        FMLCommonHandler.instance().bus().register((Object)new ClientTickHandler());
        ThaumonomiconIndexSearcher.init();
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.func_147438_o(x, y, z);
        if (ID == 0) {
            return new GuiSpinningWheel(player.field_71071_by, (TileEntitySpinningWheel)tile);
        }
        if (ID == 3) {
            return new GuiBag(player.field_71071_by, world);
        }
        if (ID == 4 || ID == 5) {
            return new GuiCloakBag(player.field_71071_by, world, ID == 4 ? TravellersGearAPI.getExtendedInventory((EntityPlayer)player)[0] : BaublesApi.getBaubles((EntityPlayer)player).func_70301_a(3));
        }
        if (ID == 6) {
            return new GuiPatchedFocusPouch(player.field_71071_by, world, x, y, z);
        }
        if (ID == 7) {
            return new GuiPrimordialGlove(player.field_71071_by, world, x, y, z);
        }
        if (ID == 8) {
            return new GuiLabelLibrary(player.field_71071_by, (TileEntityLabelLibrary)tile);
        }
        if (ID == 9) {
            return new GuiCuttingTable(player.field_71071_by, (TileEntityCuttingTable)tile);
        }
        if (ID == 10) {
            return new GuiMagicalTileLock((TileEntityMagicalTileLock)tile);
        }
        if (ID == 11) {
            return new GuiVoidBag(player.field_71071_by, world);
        }
        return null;
    }

    @Override
    public void createEssentiaTrailFx(World worldObj, int x, int y, int z, int tx, int ty, int tz, int count, int colour, float scale) {
        FXEssentiaTrail fx = new FXEssentiaTrail(worldObj, (double)x + 0.5, (double)(y + 1), (double)z + 0.5, (double)tx + 0.5, (double)ty + 0.5, (double)tz + 0.5, count, colour, scale);
        ParticleEngine.instance.addEffect(worldObj, (EntityFX)fx);
    }

    @Override
    public void createTargetedWispFx(World worldObj, double x, double y, double z, double tx, double ty, double tz, int colour, float scale, float gravity, boolean tinkle, boolean noClip) {
        FXWisp fx = new FXWisp(worldObj, x, y, z, tx, ty, tz, scale, 0);
        fx.func_82338_g(1.0f);
        fx.tinkle = tinkle;
        fx.field_70145_X = noClip;
        fx.func_70538_b((float)(colour >> 16 & 0xFF) / 255.0f, (float)(colour >> 8 & 0xFF) / 255.0f, (float)(colour & 0xFF) / 255.0f);
        fx.setGravity(0.0f);
        ParticleEngine.instance.addEffect(worldObj, (EntityFX)fx);
    }

    @Override
    public void createSweatFx(EntityPlayer player) {
        EntityFXSweat fx = new EntityFXSweat(player);
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fx);
    }

    @Override
    public void createFurnaceOutputBlobFx(World worldObj, int x, int y, int z, ForgeDirection facing) {
        float xx = (float)x + 0.5f + (float)facing.offsetX * 1.66f + worldObj.field_73012_v.nextFloat() * 0.3f;
        float zz = (float)z + 0.5f + (float)facing.offsetZ * 1.66f + worldObj.field_73012_v.nextFloat() * 0.3f;
        EntityLavaFX fb = new EntityLavaFX(worldObj, (double)xx, (double)((float)y + 1.3f), (double)zz);
        fb.field_70181_x = 0.2f * worldObj.field_73012_v.nextFloat();
        float mx = facing.offsetX != 0 ? (worldObj.field_73012_v.nextFloat() - worldObj.field_73012_v.nextFloat()) * 0.5f : (float)facing.offsetX * worldObj.field_73012_v.nextFloat();
        float mz = facing.offsetZ != 0 ? (worldObj.field_73012_v.nextFloat() - worldObj.field_73012_v.nextFloat()) * 0.5f : (float)facing.offsetZ * worldObj.field_73012_v.nextFloat();
        fb.field_70159_w = 0.15f * mx;
        fb.field_70179_y = 0.15f * mz;
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fb);
    }

    @Override
    public void createFurnaceDestructionBlobFx(World worldObj, int x, int y, int z) {
        float xx = (float)x + 0.5f + worldObj.field_73012_v.nextFloat() * 0.3f;
        float zz = (float)z + 0.5f + worldObj.field_73012_v.nextFloat() * 0.3f;
        EntityLavaFX fb = new EntityLavaFX(worldObj, (double)xx, (double)((float)y + 1.5f), (double)zz);
        fb.field_70181_x = 0.2f;
        fb.field_70159_w = (worldObj.field_73012_v.nextFloat() - worldObj.field_73012_v.nextFloat()) * 0.5f * 0.15f;
        fb.field_70179_y = (worldObj.field_73012_v.nextFloat() - worldObj.field_73012_v.nextFloat()) * 0.5f * 0.15f;
        FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fb);
    }
}

