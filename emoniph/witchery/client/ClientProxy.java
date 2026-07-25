/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  cpw.mods.fml.common.registry.VillagerRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelCreeper
 *  net.minecraft.client.model.ModelOcelot
 *  net.minecraft.client.model.ModelSpider
 *  net.minecraft.client.model.ModelZombie
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.particle.EntitySmokeFX
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderBat
 *  net.minecraft.client.renderer.entity.RenderVillager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.MinecraftForgeClient
 *  net.minecraftforge.common.MinecraftForge
 */
package com.emoniph.witchery.client;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockAlluringSkull;
import com.emoniph.witchery.blocks.BlockAltar;
import com.emoniph.witchery.blocks.BlockAltarGUI;
import com.emoniph.witchery.blocks.BlockAreaMarker;
import com.emoniph.witchery.blocks.BlockBeartrap;
import com.emoniph.witchery.blocks.BlockBloodCrucible;
import com.emoniph.witchery.blocks.BlockBrazier;
import com.emoniph.witchery.blocks.BlockCandelabra;
import com.emoniph.witchery.blocks.BlockChalice;
import com.emoniph.witchery.blocks.BlockCoffin;
import com.emoniph.witchery.blocks.BlockCrystalBall;
import com.emoniph.witchery.blocks.BlockDemonHeart;
import com.emoniph.witchery.blocks.BlockDistillery;
import com.emoniph.witchery.blocks.BlockDistilleryGUI;
import com.emoniph.witchery.blocks.BlockDreamCatcher;
import com.emoniph.witchery.blocks.BlockFetish;
import com.emoniph.witchery.blocks.BlockFumeFunnel;
import com.emoniph.witchery.blocks.BlockGarlicGarland;
import com.emoniph.witchery.blocks.BlockGrassper;
import com.emoniph.witchery.blocks.BlockKettle;
import com.emoniph.witchery.blocks.BlockLeechChest;
import com.emoniph.witchery.blocks.BlockMirror;
import com.emoniph.witchery.blocks.BlockPlacedItem;
import com.emoniph.witchery.blocks.BlockPoppetShelf;
import com.emoniph.witchery.blocks.BlockSilverVat;
import com.emoniph.witchery.blocks.BlockSpinningWheel;
import com.emoniph.witchery.blocks.BlockSpinningWheelGUI;
import com.emoniph.witchery.blocks.BlockStatueGoddess;
import com.emoniph.witchery.blocks.BlockStatueOfWorship;
import com.emoniph.witchery.blocks.BlockStatueWerewolf;
import com.emoniph.witchery.blocks.BlockWitchesOven;
import com.emoniph.witchery.blocks.BlockWitchesOvenGUI;
import com.emoniph.witchery.blocks.BlockWolfHead;
import com.emoniph.witchery.brewing.EntityBrew;
import com.emoniph.witchery.brewing.EntityDroplet;
import com.emoniph.witchery.brewing.EntitySplatter;
import com.emoniph.witchery.brewing.RenderBrew;
import com.emoniph.witchery.brewing.RenderBrewGas;
import com.emoniph.witchery.brewing.RenderBrewLiquid;
import com.emoniph.witchery.brewing.RenderCauldron;
import com.emoniph.witchery.brewing.RenderDroplet;
import com.emoniph.witchery.brewing.RenderSplatter;
import com.emoniph.witchery.brewing.RenderWitchVine;
import com.emoniph.witchery.brewing.TileEntityCauldron;
import com.emoniph.witchery.brewing.potions.WitcheryPotions;
import com.emoniph.witchery.client.ClientEvents;
import com.emoniph.witchery.client.gui.GuiScreenBiomeBook;
import com.emoniph.witchery.client.gui.GuiScreenMarkupBook;
import com.emoniph.witchery.client.gui.GuiScreenWitchcraftBook;
import com.emoniph.witchery.client.model.ModelDemon;
import com.emoniph.witchery.client.model.ModelEnt;
import com.emoniph.witchery.client.model.ModelFamiliarPig;
import com.emoniph.witchery.client.model.ModelGoblin;
import com.emoniph.witchery.client.model.ModelGoblinGulg;
import com.emoniph.witchery.client.model.ModelGoblinMog;
import com.emoniph.witchery.client.model.ModelHellhound;
import com.emoniph.witchery.client.model.ModelHornedAvatar;
import com.emoniph.witchery.client.model.ModelLeonard;
import com.emoniph.witchery.client.model.ModelLilith;
import com.emoniph.witchery.client.model.ModelMandrake;
import com.emoniph.witchery.client.model.ModelMonkey;
import com.emoniph.witchery.client.model.ModelOwl;
import com.emoniph.witchery.client.model.ModelToad;
import com.emoniph.witchery.client.model.ModelTreefyd;
import com.emoniph.witchery.client.model.ModelWolfman;
import com.emoniph.witchery.client.particle.NaturePowerFX;
import com.emoniph.witchery.client.renderer.RenderAlluringSkull;
import com.emoniph.witchery.client.renderer.RenderBabaYaga;
import com.emoniph.witchery.client.renderer.RenderBanshee;
import com.emoniph.witchery.client.renderer.RenderBeartrap;
import com.emoniph.witchery.client.renderer.RenderBlockItem;
import com.emoniph.witchery.client.renderer.RenderBloodCrucible;
import com.emoniph.witchery.client.renderer.RenderBolt;
import com.emoniph.witchery.client.renderer.RenderBrazier;
import com.emoniph.witchery.client.renderer.RenderBrewBottle;
import com.emoniph.witchery.client.renderer.RenderBroom;
import com.emoniph.witchery.client.renderer.RenderCandelabra;
import com.emoniph.witchery.client.renderer.RenderCaneSword;
import com.emoniph.witchery.client.renderer.RenderChalice;
import com.emoniph.witchery.client.renderer.RenderCoffin;
import com.emoniph.witchery.client.renderer.RenderCorpse;
import com.emoniph.witchery.client.renderer.RenderCovenWitch;
import com.emoniph.witchery.client.renderer.RenderCrystalBall;
import com.emoniph.witchery.client.renderer.RenderDarkMark;
import com.emoniph.witchery.client.renderer.RenderDeath;
import com.emoniph.witchery.client.renderer.RenderDeathsHand;
import com.emoniph.witchery.client.renderer.RenderDemon;
import com.emoniph.witchery.client.renderer.RenderDemonHeart;
import com.emoniph.witchery.client.renderer.RenderDistillery;
import com.emoniph.witchery.client.renderer.RenderDreamCatcher;
import com.emoniph.witchery.client.renderer.RenderEnt;
import com.emoniph.witchery.client.renderer.RenderFamiliar;
import com.emoniph.witchery.client.renderer.RenderFetish;
import com.emoniph.witchery.client.renderer.RenderFollower;
import com.emoniph.witchery.client.renderer.RenderFumeFunnel;
import com.emoniph.witchery.client.renderer.RenderGarlicGarland;
import com.emoniph.witchery.client.renderer.RenderGoblin;
import com.emoniph.witchery.client.renderer.RenderGoblinGulg;
import com.emoniph.witchery.client.renderer.RenderGoblinMog;
import com.emoniph.witchery.client.renderer.RenderGoddess;
import com.emoniph.witchery.client.renderer.RenderGrassper;
import com.emoniph.witchery.client.renderer.RenderGrenade;
import com.emoniph.witchery.client.renderer.RenderHandBow;
import com.emoniph.witchery.client.renderer.RenderHellhound;
import com.emoniph.witchery.client.renderer.RenderHornedAvatar;
import com.emoniph.witchery.client.renderer.RenderHuntsmanSpear;
import com.emoniph.witchery.client.renderer.RenderIllusion;
import com.emoniph.witchery.client.renderer.RenderImp;
import com.emoniph.witchery.client.renderer.RenderKettle;
import com.emoniph.witchery.client.renderer.RenderLeechChest;
import com.emoniph.witchery.client.renderer.RenderLeonard;
import com.emoniph.witchery.client.renderer.RenderLilith;
import com.emoniph.witchery.client.renderer.RenderLordOfTorment;
import com.emoniph.witchery.client.renderer.RenderMandrake;
import com.emoniph.witchery.client.renderer.RenderMindrake;
import com.emoniph.witchery.client.renderer.RenderMirror;
import com.emoniph.witchery.client.renderer.RenderMirrorFace;
import com.emoniph.witchery.client.renderer.RenderMysticBranch;
import com.emoniph.witchery.client.renderer.RenderNightmare;
import com.emoniph.witchery.client.renderer.RenderOwl;
import com.emoniph.witchery.client.renderer.RenderParasyticLouse;
import com.emoniph.witchery.client.renderer.RenderPitGrass;
import com.emoniph.witchery.client.renderer.RenderPlacedItem;
import com.emoniph.witchery.client.renderer.RenderPoltergeist;
import com.emoniph.witchery.client.renderer.RenderPoppetChest;
import com.emoniph.witchery.client.renderer.RenderReflection;
import com.emoniph.witchery.client.renderer.RenderSilverVat;
import com.emoniph.witchery.client.renderer.RenderSpectre;
import com.emoniph.witchery.client.renderer.RenderSpellEffect;
import com.emoniph.witchery.client.renderer.RenderSpinningWheel;
import com.emoniph.witchery.client.renderer.RenderSpirit;
import com.emoniph.witchery.client.renderer.RenderStatueMandrake;
import com.emoniph.witchery.client.renderer.RenderStatueOfWorship;
import com.emoniph.witchery.client.renderer.RenderStatueWerewolf;
import com.emoniph.witchery.client.renderer.RenderStatueWolf;
import com.emoniph.witchery.client.renderer.RenderStockade;
import com.emoniph.witchery.client.renderer.RenderToad;
import com.emoniph.witchery.client.renderer.RenderTreefyd;
import com.emoniph.witchery.client.renderer.RenderVampire;
import com.emoniph.witchery.client.renderer.RenderVillageGuard;
import com.emoniph.witchery.client.renderer.RenderWingedMonkey;
import com.emoniph.witchery.client.renderer.RenderWitchCat;
import com.emoniph.witchery.client.renderer.RenderWitchHand;
import com.emoniph.witchery.client.renderer.RenderWitchHunter;
import com.emoniph.witchery.client.renderer.RenderWitchProjectile;
import com.emoniph.witchery.client.renderer.RenderWitchesOven;
import com.emoniph.witchery.client.renderer.RenderWolfHead;
import com.emoniph.witchery.client.renderer.RenderWolfman;
import com.emoniph.witchery.common.CommonProxy;
import com.emoniph.witchery.entity.EntityAttackBat;
import com.emoniph.witchery.entity.EntityBabaYaga;
import com.emoniph.witchery.entity.EntityBanshee;
import com.emoniph.witchery.entity.EntityBolt;
import com.emoniph.witchery.entity.EntityBroom;
import com.emoniph.witchery.entity.EntityCorpse;
import com.emoniph.witchery.entity.EntityCovenWitch;
import com.emoniph.witchery.entity.EntityDarkMark;
import com.emoniph.witchery.entity.EntityDeath;
import com.emoniph.witchery.entity.EntityDemon;
import com.emoniph.witchery.entity.EntityEnt;
import com.emoniph.witchery.entity.EntityFamiliar;
import com.emoniph.witchery.entity.EntityFollower;
import com.emoniph.witchery.entity.EntityGoblin;
import com.emoniph.witchery.entity.EntityGoblinGulg;
import com.emoniph.witchery.entity.EntityGoblinMog;
import com.emoniph.witchery.entity.EntityGrenade;
import com.emoniph.witchery.entity.EntityHellhound;
import com.emoniph.witchery.entity.EntityHornedHuntsman;
import com.emoniph.witchery.entity.EntityIllusionCreeper;
import com.emoniph.witchery.entity.EntityIllusionSpider;
import com.emoniph.witchery.entity.EntityIllusionZombie;
import com.emoniph.witchery.entity.EntityImp;
import com.emoniph.witchery.entity.EntityLeonard;
import com.emoniph.witchery.entity.EntityLilith;
import com.emoniph.witchery.entity.EntityLordOfTorment;
import com.emoniph.witchery.entity.EntityLostSoul;
import com.emoniph.witchery.entity.EntityMandrake;
import com.emoniph.witchery.entity.EntityMindrake;
import com.emoniph.witchery.entity.EntityMirrorFace;
import com.emoniph.witchery.entity.EntityNightmare;
import com.emoniph.witchery.entity.EntityOwl;
import com.emoniph.witchery.entity.EntityParasyticLouse;
import com.emoniph.witchery.entity.EntityPoltergeist;
import com.emoniph.witchery.entity.EntityReflection;
import com.emoniph.witchery.entity.EntitySpectre;
import com.emoniph.witchery.entity.EntitySpellEffect;
import com.emoniph.witchery.entity.EntitySpirit;
import com.emoniph.witchery.entity.EntityToad;
import com.emoniph.witchery.entity.EntityTreefyd;
import com.emoniph.witchery.entity.EntityVampire;
import com.emoniph.witchery.entity.EntityVillageGuard;
import com.emoniph.witchery.entity.EntityVillagerWere;
import com.emoniph.witchery.entity.EntityWingedMonkey;
import com.emoniph.witchery.entity.EntityWitchCat;
import com.emoniph.witchery.entity.EntityWitchHunter;
import com.emoniph.witchery.entity.EntityWitchProjectile;
import com.emoniph.witchery.entity.EntityWolfman;
import com.emoniph.witchery.item.ItemBrewBag;
import com.emoniph.witchery.item.ItemBrewBagGUI;
import com.emoniph.witchery.item.ItemEarmuffs;
import com.emoniph.witchery.item.ItemLeonardsUrn;
import com.emoniph.witchery.item.ItemLeonardsUrnGUI;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.model.ModelOcelot;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderBat;
import net.minecraft.client.renderer.entity.RenderVillager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

@SideOnly(value=Side.CLIENT)
public class ClientProxy
extends CommonProxy {
    public static int RENDER_ID;
    private static final int STOCKADE_RENDER_ID;
    private static final int GAS_RENDER_ID;
    private static final int BREW_LIQUID_RENDER_ID;
    private static final int VINE_RENDER_ID;
    private static final int PITGRASS_RENDER_ID;
    public static final ResourceLocation APOTHECARY_TEXTURE;

    @Override
    public void registerRenderers() {
        RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
        MinecraftForgeClient.registerItemRenderer((Item)Witchery.Items.WITCH_HAND, (IItemRenderer)new RenderWitchHand());
        MinecraftForgeClient.registerItemRenderer((Item)Witchery.Items.DEATH_HAND, (IItemRenderer)new RenderDeathsHand());
        MinecraftForgeClient.registerItemRenderer((Item)Witchery.Items.BREW_BAG, (IItemRenderer)new RenderBrewBottle());
        MinecraftForgeClient.registerItemRenderer((Item)Witchery.Items.HUNTSMANS_SPEAR, (IItemRenderer)new RenderHuntsmanSpear());
        MinecraftForgeClient.registerItemRenderer((Item)Witchery.Items.MYSTIC_BRANCH, (IItemRenderer)new RenderMysticBranch());
        MinecraftForgeClient.registerItemRenderer((Item)Witchery.Items.CROSSBOW_PISTOL, (IItemRenderer)new RenderHandBow());
        MinecraftForgeClient.registerItemRenderer((Item)Witchery.Items.CANE_SWORD, (IItemRenderer)new RenderCaneSword());
        RenderingRegistry.registerEntityRenderingHandler(EntityDemon.class, (Render)new RenderDemon(new ModelDemon(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityBroom.class, (Render)new RenderBroom());
        RenderingRegistry.registerEntityRenderingHandler(EntityWitchProjectile.class, (Render)new RenderWitchProjectile(Witchery.Items.GENERIC));
        RenderingRegistry.registerEntityRenderingHandler(EntityFamiliar.class, (Render)new RenderFamiliar((ModelBase)new ModelFamiliarPig(), 0.8f));
        RenderingRegistry.registerEntityRenderingHandler(EntityMandrake.class, (Render)new RenderMandrake(new ModelMandrake(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityTreefyd.class, (Render)new RenderTreefyd(new ModelTreefyd(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityHornedHuntsman.class, (Render)new RenderHornedAvatar(new ModelHornedAvatar(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntitySpellEffect.class, (Render)new RenderSpellEffect(0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityEnt.class, (Render)new RenderEnt(new ModelEnt(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityIllusionCreeper.class, (Render)new RenderIllusion((ModelBase)new ModelCreeper(), new ResourceLocation("textures/entity/creeper/creeper.png")));
        RenderingRegistry.registerEntityRenderingHandler(EntityIllusionSpider.class, (Render)new RenderIllusion((ModelBase)new ModelSpider(), new ResourceLocation("textures/entity/spider/spider.png")));
        RenderingRegistry.registerEntityRenderingHandler(EntityIllusionZombie.class, (Render)new RenderIllusion((ModelBase)new ModelZombie(), new ResourceLocation("textures/entity/zombie/zombie.png")));
        RenderingRegistry.registerEntityRenderingHandler(EntityOwl.class, (Render)new RenderOwl(new ModelOwl(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityToad.class, (Render)new RenderToad(new ModelToad(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityWitchCat.class, (Render)new RenderWitchCat((ModelBase)new ModelOcelot(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityParasyticLouse.class, (Render)new RenderParasyticLouse());
        RenderingRegistry.registerEntityRenderingHandler(EntityBabaYaga.class, (Render)new RenderBabaYaga());
        RenderingRegistry.registerEntityRenderingHandler(EntityCovenWitch.class, (Render)new RenderCovenWitch());
        RenderingRegistry.registerEntityRenderingHandler(EntityCorpse.class, (Render)new RenderCorpse());
        RenderingRegistry.registerEntityRenderingHandler(EntityNightmare.class, (Render)new RenderNightmare());
        RenderingRegistry.registerEntityRenderingHandler(EntitySpectre.class, (Render)new RenderSpectre());
        RenderingRegistry.registerEntityRenderingHandler(EntityPoltergeist.class, (Render)new RenderPoltergeist());
        RenderingRegistry.registerEntityRenderingHandler(EntityBanshee.class, (Render)new RenderBanshee());
        RenderingRegistry.registerEntityRenderingHandler(EntitySpirit.class, (Render)new RenderSpirit());
        RenderingRegistry.registerEntityRenderingHandler(EntityDeath.class, (Render)new RenderDeath());
        RenderingRegistry.registerEntityRenderingHandler(EntityBolt.class, (Render)new RenderBolt());
        RenderingRegistry.registerEntityRenderingHandler(EntityWitchHunter.class, (Render)new RenderWitchHunter());
        RenderingRegistry.registerEntityRenderingHandler(EntityLordOfTorment.class, (Render)new RenderLordOfTorment());
        RenderingRegistry.registerEntityRenderingHandler(EntityImp.class, (Render)new RenderImp());
        RenderingRegistry.registerEntityRenderingHandler(EntityDarkMark.class, (Render)new RenderDarkMark());
        RenderingRegistry.registerEntityRenderingHandler(EntityMindrake.class, (Render)new RenderMindrake());
        RenderingRegistry.registerEntityRenderingHandler(EntityGoblin.class, (Render)new RenderGoblin(new ModelGoblin(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityGoblinMog.class, (Render)new RenderGoblinMog(new ModelGoblinMog(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityGoblinGulg.class, (Render)new RenderGoblinGulg(new ModelGoblinGulg(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityBrew.class, (Render)new RenderBrew(Witchery.Items.BREW));
        RenderingRegistry.registerEntityRenderingHandler(EntityDroplet.class, (Render)new RenderDroplet(Witchery.Items.BREW));
        RenderingRegistry.registerEntityRenderingHandler(EntitySplatter.class, (Render)new RenderSplatter(Witchery.Items.BREW));
        RenderingRegistry.registerEntityRenderingHandler(EntityLeonard.class, (Render)new RenderLeonard(new ModelLeonard(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityLostSoul.class, (Render)new RenderSpirit());
        RenderingRegistry.registerEntityRenderingHandler(EntityWolfman.class, (Render)new RenderWolfman(new ModelWolfman(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityHellhound.class, (Render)new RenderHellhound(new ModelHellhound(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityVillagerWere.class, (Render)new RenderVillager());
        RenderingRegistry.registerEntityRenderingHandler(EntityVillageGuard.class, (Render)new RenderVillageGuard());
        RenderingRegistry.registerEntityRenderingHandler(EntityVampire.class, (Render)new RenderVampire());
        RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, (Render)new RenderGrenade(Witchery.Items.SUN_GRENADE));
        RenderingRegistry.registerEntityRenderingHandler(EntityLilith.class, (Render)new RenderLilith(new ModelLilith(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityFollower.class, (Render)new RenderFollower(new ModelBiped()));
        RenderingRegistry.registerEntityRenderingHandler(EntityWingedMonkey.class, (Render)new RenderWingedMonkey(new ModelMonkey(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityAttackBat.class, (Render)new RenderBat());
        RenderingRegistry.registerEntityRenderingHandler(EntityMirrorFace.class, (Render)new RenderMirrorFace());
        RenderingRegistry.registerEntityRenderingHandler(EntityReflection.class, (Render)new RenderReflection());
        this.bindRenderer(BlockPoppetShelf.TileEntityPoppetShelf.class, new RenderPoppetChest(), Item.func_150898_a((Block)Witchery.Blocks.POPPET_SHELF));
        this.bindRenderer(BlockGrassper.TileEntityGrassper.class, new RenderGrassper(), Item.func_150898_a((Block)Witchery.Blocks.GRASSPER));
        this.bindRenderer(BlockDistillery.TileEntityDistillery.class, new RenderDistillery(), Item.func_150898_a((Block)Witchery.Blocks.DISTILLERY_IDLE));
        this.bindRenderer(BlockWitchesOven.TileEntityWitchesOven.class, new RenderWitchesOven(), Item.func_150898_a((Block)Witchery.Blocks.OVEN_IDLE));
        this.bindRenderer(BlockDreamCatcher.TileEntityDreamCatcher.class, new RenderDreamCatcher(), Item.func_150898_a((Block)Witchery.Blocks.DREAM_CATCHER));
        this.bindRenderer(BlockChalice.TileEntityChalice.class, new RenderChalice(), Item.func_150898_a((Block)Witchery.Blocks.CHALICE));
        this.bindRenderer(BlockCandelabra.TileEntityCandelabra.class, new RenderCandelabra(), Item.func_150898_a((Block)Witchery.Blocks.CANDELABRA));
        this.bindRenderer(BlockCrystalBall.TileEntityCrystalBall.class, new RenderCrystalBall(), Item.func_150898_a((Block)Witchery.Blocks.CRYSTAL_BALL));
        this.bindRenderer(BlockKettle.TileEntityKettle.class, new RenderKettle(), Item.func_150898_a((Block)Witchery.Blocks.KETTLE));
        this.bindRenderer(BlockLeechChest.TileEntityLeechChest.class, new RenderLeechChest(), Item.func_150898_a((Block)Witchery.Blocks.LEECH_CHEST));
        this.bindRenderer(BlockStatueGoddess.TileEntityStatueGoddess.class, new RenderGoddess(), Item.func_150898_a((Block)Witchery.Blocks.STATUE_GODDESS));
        this.bindRenderer(BlockSpinningWheel.TileEntitySpinningWheel.class, new RenderSpinningWheel(), Item.func_150898_a((Block)Witchery.Blocks.SPINNING_WHEEL));
        this.bindRenderer(BlockBrazier.TileEntityBrazier.class, new RenderBrazier(), Item.func_150898_a((Block)Witchery.Blocks.BRAZIER));
        this.bindRenderer(BlockAreaMarker.TileEntityAreaCurseProtect.class, new RenderStatueWolf(), Item.func_150898_a((Block)Witchery.Blocks.DECURSE_DIRECTED));
        this.bindRenderer(BlockAreaMarker.TileEntityAreaTeleportPullProtect.class, new RenderStatueMandrake(), Item.func_150898_a((Block)Witchery.Blocks.DECURSE_TELEPORT));
        this.bindRenderer(BlockStatueOfWorship.TileEntityStatueOfWorship.class, new RenderStatueOfWorship(), Item.func_150898_a((Block)Witchery.Blocks.STATUE_OF_WORSHIP));
        this.bindRenderer(BlockPlacedItem.TileEntityPlacedItem.class, new RenderPlacedItem(), new Item[0]);
        this.bindRenderer(BlockAlluringSkull.TileEntityAlluringSkull.class, new RenderAlluringSkull(), new Item[0]);
        this.bindRenderer(BlockDemonHeart.TileEntityDemonHeart.class, new RenderDemonHeart(), new Item[0]);
        this.bindRenderer(TileEntityCauldron.class, new RenderCauldron(), Item.func_150898_a((Block)Witchery.Blocks.CAULDRON));
        this.bindRenderer(BlockStatueWerewolf.TileEntityStatueWerewolf.class, new RenderStatueWerewolf(), Item.func_150898_a((Block)Witchery.Blocks.WOLF_ALTAR));
        this.bindRenderer(BlockSilverVat.TileEntitySilverVat.class, new RenderSilverVat(), Item.func_150898_a((Block)Witchery.Blocks.SILVER_VAT));
        this.bindRenderer(BlockBeartrap.TileEntityBeartrap.class, new RenderBeartrap(), Item.func_150898_a((Block)Witchery.Blocks.BEARTRAP), Item.func_150898_a((Block)Witchery.Blocks.WOLFTRAP));
        this.bindRenderer(BlockWolfHead.TileEntityWolfHead.class, new RenderWolfHead(), new Item[0]);
        this.bindRenderer(BlockCoffin.TileEntityCoffin.class, new RenderCoffin(), new Item[0]);
        this.bindRenderer(BlockGarlicGarland.TileEntityGarlicGarland.class, new RenderGarlicGarland(), new Item[0]);
        this.bindRenderer(BlockBloodCrucible.TileEntityBloodCrucible.class, new RenderBloodCrucible(), Item.func_150898_a((Block)Witchery.Blocks.BLOOD_CRUCIBLE), Item.func_150898_a((Block)Witchery.Blocks.BLOOD_CRUCIBLE));
        this.bindRenderer(BlockMirror.TileEntityMirror.class, new RenderMirror(), new Item[0]);
        RenderFumeFunnel funnelRenderer = new RenderFumeFunnel(false);
        this.bindRenderer(BlockFumeFunnel.TileEntityFumeFunnel.class, funnelRenderer, new Item[0]);
        BlockFumeFunnel.TileEntityFumeFunnel dummyFunnelTile = new BlockFumeFunnel.TileEntityFumeFunnel();
        MinecraftForgeClient.registerItemRenderer((Item)Item.func_150898_a((Block)Witchery.Blocks.OVEN_FUMEFUNNEL), (IItemRenderer)new RenderBlockItem(funnelRenderer, dummyFunnelTile));
        MinecraftForgeClient.registerItemRenderer((Item)Item.func_150898_a((Block)Witchery.Blocks.OVEN_FUMEFUNNEL_FILTERED), (IItemRenderer)new RenderBlockItem(funnelRenderer, new BlockFumeFunnel.TileEntityFumeFunnel()));
        RenderFetish fetishRenderer = new RenderFetish();
        this.bindRenderer(BlockFetish.TileEntityFetish.class, fetishRenderer, new Item[0]);
        BlockFetish.TileEntityFetish dummyFetishTile = new BlockFetish.TileEntityFetish();
        MinecraftForgeClient.registerItemRenderer((Item)Item.func_150898_a((Block)Witchery.Blocks.FETISH_SCARECROW), (IItemRenderer)new RenderFetish.RenderFetishBlockItem(Witchery.Blocks.FETISH_SCARECROW, fetishRenderer, dummyFetishTile));
        MinecraftForgeClient.registerItemRenderer((Item)Item.func_150898_a((Block)Witchery.Blocks.FETISH_TREANT_IDOL), (IItemRenderer)new RenderFetish.RenderFetishBlockItem(Witchery.Blocks.FETISH_TREANT_IDOL, fetishRenderer, dummyFetishTile));
        RenderingRegistry.registerBlockHandler((int)STOCKADE_RENDER_ID, (ISimpleBlockRenderingHandler)new RenderStockade());
        RenderingRegistry.registerBlockHandler((int)GAS_RENDER_ID, (ISimpleBlockRenderingHandler)new RenderBrewGas());
        RenderingRegistry.registerBlockHandler((int)BREW_LIQUID_RENDER_ID, (ISimpleBlockRenderingHandler)new RenderBrewLiquid());
        RenderingRegistry.registerBlockHandler((int)VINE_RENDER_ID, (ISimpleBlockRenderingHandler)new RenderWitchVine());
        RenderingRegistry.registerBlockHandler((int)PITGRASS_RENDER_ID, (ISimpleBlockRenderingHandler)new RenderPitGrass());
    }

    @Override
    public int getStockageRenderId() {
        return STOCKADE_RENDER_ID;
    }

    @Override
    public int getPitGrassRenderId() {
        return PITGRASS_RENDER_ID;
    }

    @Override
    public int getGasRenderId() {
        return GAS_RENDER_ID;
    }

    @Override
    public int getBrewLiquidRenderId() {
        return BREW_LIQUID_RENDER_ID;
    }

    @Override
    public int getVineRenderId() {
        return VINE_RENDER_ID;
    }

    private void bindRenderer(Class<? extends TileEntity> clazz, TileEntitySpecialRenderer render, Item ... items) {
        ClientRegistry.bindTileEntitySpecialRenderer(clazz, (TileEntitySpecialRenderer)render);
        for (Item item : items) {
            if (item == null) continue;
            try {
                MinecraftForgeClient.registerItemRenderer((Item)item, (IItemRenderer)new RenderBlockItem(render, clazz.newInstance()));
            }
            catch (IllegalAccessException ex) {
            }
            catch (InstantiationException ex) {
                // empty catch block
            }
        }
    }

    @Override
    public void registerHandlers() {
        super.registerHandlers();
    }

    @Override
    public void registerEvents() {
        super.registerEvents();
        MinecraftForge.EVENT_BUS.register((Object)new ClientEvents());
        MinecraftForge.EVENT_BUS.register((Object)new WitcheryPotions.ClientEventHooks());
        MinecraftForge.EVENT_BUS.register((Object)new ItemEarmuffs.ClientEventHooks());
    }

    @Override
    public void postInit() {
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 0: {
                return new BlockAltarGUI((BlockAltar.TileEntityAltar)world.func_147438_o(x, y, z));
            }
            case 1: {
                return new GuiScreenWitchcraftBook(player, player.func_70694_bm());
            }
            case 2: {
                return new BlockWitchesOvenGUI(player.field_71071_by, (BlockWitchesOven.TileEntityWitchesOven)world.func_147438_o(x, y, z));
            }
            case 3: {
                return new BlockDistilleryGUI(player.field_71071_by, (BlockDistillery.TileEntityDistillery)world.func_147438_o(x, y, z));
            }
            case 4: {
                return new BlockSpinningWheelGUI(player.field_71071_by, (BlockSpinningWheel.TileEntitySpinningWheel)world.func_147438_o(x, y, z));
            }
            case 5: {
                return new ItemBrewBagGUI((IInventory)player.field_71071_by, (IInventory)new ItemBrewBag.InventoryBrewBag(player));
            }
            case 6: {
                return new GuiScreenBiomeBook(player, player.func_70694_bm());
            }
            case 7: {
                return new GuiScreenMarkupBook(player, player.func_70694_bm());
            }
            case 8: {
                return new ItemLeonardsUrnGUI((IInventory)player.field_71071_by, (IInventory)new ItemLeonardsUrn.InventoryLeonardsUrn(player));
            }
        }
        return null;
    }

    @Override
    public boolean getGraphicsLevel() {
        return Minecraft.func_71410_x().field_71474_y.field_74347_j;
    }

    @Override
    public void registerVillagers() {
        super.registerVillagers();
        if (Config.instance().generateApothecaries) {
            VillagerRegistry.instance().registerVillagerSkin(Config.instance().apothecaryID, APOTHECARY_TEXTURE);
        }
    }

    @Override
    public void generateParticle(World worldObj, double posX, double posY, double posZ, float r, float g, float b, int ttl, float gravity) {
        if (worldObj.field_72995_K) {
            NaturePowerFX sparkle = new NaturePowerFX(worldObj, posX, posY, posZ);
            sparkle.setMaxAge(ttl);
            sparkle.field_70145_X = true;
            sparkle.func_70538_b(r, g, b);
            sparkle.setGravity(gravity);
            Minecraft.func_71410_x().field_71452_i.func_78873_a((EntityFX)sparkle);
        }
    }

    @Override
    public EntityPlayer getPlayer(MessageContext ctx) {
        if (ctx.side == Side.SERVER) {
            return ctx.getServerHandler().field_147369_b;
        }
        return Minecraft.func_71410_x().field_71439_g;
    }

    @Override
    public void showParticleEffect(World world, double x, double y, double z, double width, double height, SoundEffect sound, int color, ParticleEffect particle) {
        if (sound != SoundEffect.NONE) {
            world.func_72980_b(x, y, z, sound.toString(), 0.5f, 0.4f / ((float)world.field_73012_v.nextDouble() * 0.4f + 0.8f), false);
        }
        int effectCount = Math.min(MathHelper.func_76143_f((double)(Math.max(width, 1.0) * 20.0)), 300);
        for (int i = 0; i < effectCount; ++i) {
            double d0 = world.field_73012_v.nextGaussian() * 0.02;
            double d1 = world.field_73012_v.nextGaussian() * 0.02;
            double d2 = world.field_73012_v.nextGaussian() * 0.02;
            if (particle == ParticleEffect.SPELL_COLORED) {
                EntitySmokeFX sparkle = new EntitySmokeFX(world, x + world.field_73012_v.nextDouble() * width * 2.0 - width, y + world.field_73012_v.nextDouble() * height, z + (double)world.field_73012_v.nextFloat() * width * 2.0 - width, 0.0, 0.0, 0.0);
                sparkle.field_70145_X = true;
                float red = (float)(color >>> 16 & 0xFF) / 256.0f;
                float green = (float)(color >>> 8 & 0xFF) / 256.0f;
                float blue = (float)(color & 0xFF) / 256.0f;
                sparkle.func_70538_b(red, green, blue);
                Minecraft.func_71410_x().field_71452_i.func_78873_a((EntityFX)sparkle);
                continue;
            }
            world.func_72869_a(particle.toString(), x + world.field_73012_v.nextDouble() * width * 2.0 - width, y + world.field_73012_v.nextDouble() * height, z + (double)world.field_73012_v.nextFloat() * width * 2.0 - width, 0.0, 0.0, 0.0);
        }
    }

    static {
        STOCKADE_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
        GAS_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
        BREW_LIQUID_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
        VINE_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
        PITGRASS_RENDER_ID = RenderingRegistry.getNextAvailableRenderId();
        APOTHECARY_TEXTURE = new ResourceLocation("witchery:textures/entities/apothecary.png");
    }
}

