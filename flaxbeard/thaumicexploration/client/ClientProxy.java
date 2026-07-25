/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.world.World
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.MinecraftForgeClient
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.client.fx.ParticleEngine
 *  thaumcraft.client.fx.bolt.FXLightningBolt
 *  thaumcraft.client.fx.particles.FXBoreParticles
 *  thaumcraft.client.fx.particles.FXBoreSparkle
 *  thaumcraft.client.fx.particles.FXEssentiaTrail
 *  thaumcraft.client.fx.particles.FXWisp
 */
package flaxbeard.thaumicexploration.client;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.client.render.BlockCrucibleSoulsRenderer;
import flaxbeard.thaumicexploration.client.render.BlockEverfullUrnRenderer;
import flaxbeard.thaumicexploration.client.render.BlockFloatyCandleRenderer;
import flaxbeard.thaumicexploration.client.render.BlockReplicatorRenderer;
import flaxbeard.thaumicexploration.client.render.BlockSoulBrazierRenderer;
import flaxbeard.thaumicexploration.client.render.BlockTrashJarRenderer;
import flaxbeard.thaumicexploration.client.render.ItemRenderThinkTank;
import flaxbeard.thaumicexploration.client.render.TileEntityBoundChestRender;
import flaxbeard.thaumicexploration.client.render.TileEntityBoundJarRender;
import flaxbeard.thaumicexploration.client.render.TileEntityFloatyCandleRender;
import flaxbeard.thaumicexploration.client.render.TileEntityRenderCrucibleSouls;
import flaxbeard.thaumicexploration.client.render.TileEntityReplicatorRender;
import flaxbeard.thaumicexploration.client.render.TileEntitySoulBrazierRenderer;
import flaxbeard.thaumicexploration.client.render.TileEntityThinkTankRender;
import flaxbeard.thaumicexploration.client.render.TileEntityTrashJarRenderer;
import flaxbeard.thaumicexploration.common.CommonProxy;
import flaxbeard.thaumicexploration.packet.TXClientPacketHandler;
import flaxbeard.thaumicexploration.tile.TileEntityBoundChest;
import flaxbeard.thaumicexploration.tile.TileEntityBoundJar;
import flaxbeard.thaumicexploration.tile.TileEntityCrucibleSouls;
import flaxbeard.thaumicexploration.tile.TileEntityFloatyCandle;
import flaxbeard.thaumicexploration.tile.TileEntityReplicator;
import flaxbeard.thaumicexploration.tile.TileEntitySoulBrazier;
import flaxbeard.thaumicexploration.tile.TileEntityThinkTank;
import flaxbeard.thaumicexploration.tile.TileEntityTrashJar;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.bolt.FXLightningBolt;
import thaumcraft.client.fx.particles.FXBoreParticles;
import thaumcraft.client.fx.particles.FXBoreSparkle;
import thaumcraft.client.fx.particles.FXEssentiaTrail;
import thaumcraft.client.fx.particles.FXWisp;

public class ClientProxy
extends CommonProxy {
    @Override
    public boolean isServer() {
        return false;
    }

    @Override
    public void registerRenderers() {
        ThaumicExploration.channel.register((Object)new TXClientPacketHandler());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBoundJar.class, (TileEntitySpecialRenderer)new TileEntityBoundJarRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBoundChest.class, (TileEntitySpecialRenderer)new TileEntityBoundChestRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrucibleSouls.class, (TileEntitySpecialRenderer)new TileEntityRenderCrucibleSouls());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityReplicator.class, (TileEntitySpecialRenderer)new TileEntityReplicatorRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFloatyCandle.class, (TileEntitySpecialRenderer)new TileEntityFloatyCandleRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntitySoulBrazier.class, (TileEntitySpecialRenderer)new TileEntitySoulBrazierRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityTrashJar.class, (TileEntitySpecialRenderer)new TileEntityTrashJarRenderer());
        TileEntityThinkTankRender renderThinkTank = new TileEntityThinkTankRender();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityThinkTank.class, (TileEntitySpecialRenderer)renderThinkTank);
        RenderingRegistry.registerBlockHandler((int)ThaumicExploration.floatCandleRenderID, (ISimpleBlockRenderingHandler)new BlockFloatyCandleRenderer());
        RenderingRegistry.registerBlockHandler((int)ThaumicExploration.soulBrazierRenderID, (ISimpleBlockRenderingHandler)new BlockSoulBrazierRenderer());
        RenderingRegistry.registerBlockHandler((int)ThaumicExploration.everfullUrnRenderID, (ISimpleBlockRenderingHandler)new BlockEverfullUrnRenderer());
        RenderingRegistry.registerBlockHandler((int)ThaumicExploration.replicatorRenderID, (ISimpleBlockRenderingHandler)new BlockReplicatorRenderer());
        RenderingRegistry.registerBlockHandler((int)ThaumicExploration.crucibleSoulsRenderID, (ISimpleBlockRenderingHandler)new BlockCrucibleSoulsRenderer());
        RenderingRegistry.registerBlockHandler((int)ThaumicExploration.trashJarRenderID, (ISimpleBlockRenderingHandler)new BlockTrashJarRenderer());
        MinecraftForgeClient.registerItemRenderer((Item)Item.func_150898_a((Block)ThaumicExploration.thinkTankJar), (IItemRenderer)new ItemRenderThinkTank(renderThinkTank, new TileEntityThinkTank()));
    }

    @Override
    public void setUnicode() {
        Minecraft.func_71410_x().field_71466_p.func_78264_a(false);
    }

    @Override
    public void spawnWaterOnPlayer(World worldObj, int xCoord, int yCoord, int zCoord, EntityPlayer player) {
        FXEssentiaTrail fx = new FXEssentiaTrail(worldObj, (double)((float)xCoord + 0.5f), (double)((float)yCoord + 1.1f), (double)((float)zCoord + 0.5f), player.field_70165_t, player.field_70163_u, player.field_70161_v, 5, Aspect.TOOL.getColor(), 1.0f);
        ParticleEngine.instance.addEffect(worldObj, (EntityFX)fx);
    }

    @Override
    public void spawnLightningBolt(World worldObj, double xCoord, double yCoord, double zCoord, double dX, double dY, double dZ) {
        FXLightningBolt bolt = new FXLightningBolt(worldObj, xCoord, yCoord, zCoord, dX, dY, dZ, worldObj.field_73012_v.nextLong(), 6, 0.5f, 5);
        bolt.defaultFractal();
        bolt.setType(5);
        bolt.setWidth(0.068f);
        bolt.finalizeBolt();
    }

    @Override
    public void spawnRandomWaterFountain(World worldObj, int xCoord, int yCoord, int zCoord) {
        FXEssentiaTrail fx = new FXEssentiaTrail(worldObj, (double)((float)xCoord + 0.5f), (double)((float)yCoord + 1.1f), (double)((float)zCoord + 0.5f), (double)((float)xCoord + 0.5f) + (Math.random() - 0.5), (double)((float)yCoord + 2.1f), (double)((float)zCoord + 0.5f) + (Math.random() - 0.5), 5, Aspect.TOOL.getColor(), 1.0f);
        ParticleEngine.instance.addEffect(worldObj, (EntityFX)fx);
    }

    @Override
    public void spawnWaterAtLocation(World worldObj, double xCoord, double yCoord, double zCoord, double dX, double dY, double dZ) {
        FXEssentiaTrail fx = new FXEssentiaTrail(worldObj, xCoord, yCoord, zCoord, dX, dY, dZ, 5, Aspect.TOOL.getColor(), 1.0f);
        ParticleEngine.instance.addEffect(worldObj, (EntityFX)fx);
    }

    @Override
    public void spawnEssentiaAtLocation(World worldObj, double xCoord, double yCoord, double zCoord, double dX, double dY, double dZ, int size, int color) {
        FXEssentiaTrail fx = new FXEssentiaTrail(worldObj, xCoord, yCoord, zCoord, dX, dY, dZ, size, color, 1.0f);
        ParticleEngine.instance.addEffect(worldObj, (EntityFX)fx);
    }

    @Override
    public void spawnActiveBrazierParticle(World worldObj, int xCoord, int yCoord, int zCoord) {
        float offsetY = 0.0f;
        float offsetZ = 0.0f;
        float offsetX = 0.0f;
        if (worldObj.func_147438_o(xCoord, yCoord, zCoord) != null && worldObj.func_147438_o(xCoord, yCoord, zCoord) instanceof TileEntitySoulBrazier) {
            TileEntitySoulBrazier brazier = (TileEntitySoulBrazier)worldObj.func_147438_o(xCoord, yCoord, zCoord);
            offsetY = (float)(Math.sin(Math.toRadians((float)brazier.count * 1.0f)) / 4.0);
            offsetZ = (float)(Math.sin(Math.toRadians((float)brazier.count * 3.0f)) / 4.0);
            offsetX = (float)(Math.cos(Math.toRadians((float)brazier.count * 3.0f)) / 4.0);
        }
        FXWisp ef = new FXWisp(worldObj, (double)((float)xCoord + 0.55f + offsetX), (double)((float)yCoord + 1.5f + offsetY), (double)((float)zCoord + 0.55f + offsetZ), (float)Math.random() / 1.125f, 0.69803923f, 0.0f, 1.0f);
        ef.setGravity(0.0f);
        ef.shrink = false;
        ef.field_70145_X = true;
        ef.blendmode = 770;
        ParticleEngine.instance.addEffect(worldObj, (EntityFX)ef);
        ef = new FXWisp(worldObj, (double)((float)xCoord + 0.55f + offsetX), (double)((float)yCoord + 1.5f + offsetY), (double)((float)zCoord + 0.55f + offsetZ), (float)Math.random() / 1.5f, 0.1f, 0.1f, 0.1f);
        ef.setGravity(0.0f);
        ef.shrink = false;
        ef.field_70145_X = true;
        ef.blendmode = 770;
        ParticleEngine.instance.addEffect(worldObj, (EntityFX)ef);
    }

    @Override
    public void spawnBoreSparkle(World worldObj, double xCoord, double yCoord, double zCoord, double x2, double y2, double z2) {
        FXBoreSparkle fb = new FXBoreSparkle(worldObj, xCoord, yCoord, zCoord, x2, y2, z2);
        fb.func_70538_b(0.4f + worldObj.field_73012_v.nextFloat() * 0.2f, 0.2f, 0.6f + worldObj.field_73012_v.nextFloat() * 0.3f);
        ParticleEngine.instance.addEffect(worldObj, (EntityFX)fb);
    }

    @Override
    public void spawnHarvestParticle(World worldObj, double xCoord, double yCoord, double zCoord, double x2, double y2, double z2) {
        FXBoreParticles fb = new FXBoreParticles(worldObj, xCoord, yCoord, zCoord, x2, y2, z2, Blocks.field_150402_ci, worldObj.field_73012_v.nextInt(6), 3);
        fb.func_82338_g(0.3f);
        fb.field_70159_w = (float)worldObj.field_73012_v.nextGaussian() * 0.03f;
        fb.field_70181_x = (float)worldObj.field_73012_v.nextGaussian() * 0.03f;
        fb.field_70179_y = (float)worldObj.field_73012_v.nextGaussian() * 0.03f;
        ParticleEngine.instance.addEffect(worldObj, (EntityFX)fb);
    }

    @Override
    public void spawnFragmentParticle(World worldObj, double xCoord, double yCoord, double zCoord, double x2, double y2, double z2, Block block, int id) {
        FXBoreParticles fb = new FXBoreParticles(worldObj, xCoord, yCoord, zCoord, x2, y2, z2, block, worldObj.field_73012_v.nextInt(6), id);
        fb.func_82338_g(0.3f);
        fb.field_70159_w = (float)worldObj.field_73012_v.nextGaussian() * 0.03f;
        fb.field_70181_x = (float)worldObj.field_73012_v.nextGaussian() * 0.03f;
        fb.field_70179_y = (float)worldObj.field_73012_v.nextGaussian() * 0.03f;
        ParticleEngine.instance.addEffect(worldObj, (EntityFX)fb);
    }

    @Override
    public boolean getIsReadyForWisp() {
        return FMLClientHandler.instance().getClient().field_71451_h != null;
    }
}

