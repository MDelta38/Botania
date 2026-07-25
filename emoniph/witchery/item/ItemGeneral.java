/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockColored
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.command.ICommandSender
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.EnumCreatureAttribute
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.monster.EntityBlaze
 *  net.minecraft.entity.monster.EntitySpider
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemDoor
 *  net.minecraft.item.ItemDye
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S14PacketEntity$S17PacketEntityLookMove
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.Teleporter
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.blocks.BlockChalice;
import com.emoniph.witchery.blocks.BlockCircle;
import com.emoniph.witchery.blocks.BlockCrystalBall;
import com.emoniph.witchery.blocks.BlockDreamCatcher;
import com.emoniph.witchery.blocks.BlockPlacedItem;
import com.emoniph.witchery.blocks.BlockWickerBundle;
import com.emoniph.witchery.brewing.BrewItemKey;
import com.emoniph.witchery.brewing.potions.PotionEnslaved;
import com.emoniph.witchery.common.ExtendedPlayer;
import com.emoniph.witchery.common.Shapeshift;
import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
import com.emoniph.witchery.entity.EntityBroom;
import com.emoniph.witchery.entity.EntityCovenWitch;
import com.emoniph.witchery.entity.EntityDeathsHorse;
import com.emoniph.witchery.entity.EntityEye;
import com.emoniph.witchery.entity.EntityLordOfTorment;
import com.emoniph.witchery.entity.EntitySpirit;
import com.emoniph.witchery.entity.EntitySummonedUndead;
import com.emoniph.witchery.entity.EntityTreefyd;
import com.emoniph.witchery.entity.EntityWitchProjectile;
import com.emoniph.witchery.familiar.Familiar;
import com.emoniph.witchery.infusion.InfusedBrew;
import com.emoniph.witchery.infusion.InfusedBrewEffect;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.infusion.PlayerEffects;
import com.emoniph.witchery.infusion.infusions.InfusionInfernal;
import com.emoniph.witchery.infusion.infusions.InfusionOtherwhere;
import com.emoniph.witchery.infusion.infusions.symbols.EffectRegistry;
import com.emoniph.witchery.infusion.infusions.symbols.SymbolEffect;
import com.emoniph.witchery.item.ItemBase;
import com.emoniph.witchery.item.ItemGeneralContract;
import com.emoniph.witchery.item.brew.BrewFluid;
import com.emoniph.witchery.item.brew.BrewSolidifySpirit;
import com.emoniph.witchery.item.brew.BrewSoul;
import com.emoniph.witchery.network.PacketCamPos;
import com.emoniph.witchery.network.PacketParticles;
import com.emoniph.witchery.network.PacketPlayerStyle;
import com.emoniph.witchery.util.BlockProtect;
import com.emoniph.witchery.util.BlockSide;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.ChatUtil;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Coord;
import com.emoniph.witchery.util.CreatureUtil;
import com.emoniph.witchery.util.EffectSpiral;
import com.emoniph.witchery.util.EntityUtil;
import com.emoniph.witchery.util.ISpiralBlockAction;
import com.emoniph.witchery.util.MutableBlock;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TargetPointUtil;
import com.emoniph.witchery.util.TimeUtil;
import com.emoniph.witchery.util.TransformCreature;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.command.ICommandSender;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S14PacketEntity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class ItemGeneral
extends ItemBase {
    public final ArrayList<SubItem> subItems = new ArrayList();
    public final ArrayList<DreamWeave> weaves = new ArrayList();
    public final SubItem itemCandelabra = SubItem.access$000(new SubItem(0, "candelabra"), this.subItems);
    public final SubItem itemChaliceEmpty = SubItem.access$000(new SubItem(1, "chalice"), this.subItems);
    public final SubItem itemChaliceFull = SubItem.access$000(new SubItem(2, "chaliceFull"), this.subItems);
    public final DreamWeave itemDreamMove = DreamWeave.access$200(new DreamWeave(3, 0, "weaveMoveFast", Potion.field_76424_c, Potion.field_76421_d, 7200, 0, 17, 10), this.subItems, this.weaves);
    public final DreamWeave itemDreamDig = DreamWeave.access$200(new DreamWeave(4, 1, "weaveDigFast", Potion.field_76422_e, Potion.field_76419_f, 7200, 0, 17, 4), this.subItems, this.weaves);
    public final DreamWeave itemDreamEat = DreamWeave.access$200(new DreamWeave(5, 2, "weaveSaturation", Potion.field_76443_y, Potion.field_76438_s, 4800, 0, 17, 16), this.subItems, this.weaves);
    public final DreamWeave itemDreamNightmare = DreamWeave.access$200(new DreamWeave(6, 3, "weaveNightmares", Potion.field_76437_t, Potion.field_76440_q, 1200, 0, 4, 4), this.subItems, this.weaves);
    public final SubItem itemBoneNeedle = SubItem.access$000(new SubItem(7, "boneNeedle"), this.subItems);
    public final SubItem itemBroom = SubItem.access$000(new SubItem(8, "broom"), this.subItems);
    public final SubItem itemBroomEnchanted = SubItem.access$000(new SubItem(9, "broomEnchanted", 3).setEnchanted(true), this.subItems);
    public final SubItem itemAttunedStone = SubItem.access$000(new SubItem(10, "attunedStone"), this.subItems);
    public final SubItem itemAttunedStoneCharged = SubItem.access$000(new SubItem(11, "attunedStoneCharged").setEnchanted(true), this.subItems);
    public final SubItem itemWaystone = SubItem.access$000(new SubItem(12, "waystone"), this.subItems);
    public final SubItem itemWaystoneBound = SubItem.access$000(new SubItem(13, "waystoneBound", 1, false), this.subItems);
    public final SubItem itemMutandis = SubItem.access$000(new SubItem(14, "mutandis"), this.subItems);
    public final SubItem itemMutandisExtremis = SubItem.access$000(new SubItem(15, "mutandisExtremis"), this.subItems);
    public final SubItem itemQuicklime = SubItem.access$000(new SubItem(16, "quicklime"), this.subItems);
    public final SubItem itemGypsum = SubItem.access$000(new SubItem(17, "gypsum"), this.subItems);
    public final SubItem itemAshWood = SubItem.access$000(new SubItem(18, "ashWood"), this.subItems);
    public final SubItem itemBelladonnaFlower = SubItem.access$000(new SubItem(21, "belladonna"), this.subItems);
    public final SubItem itemMandrakeRoot = SubItem.access$000(new SubItem(22, "mandrakeRoot"), this.subItems);
    private static final int DEMON_FOOD_DURATION = 2400;
    public final SubItem itemDemonHeart;
    public final SubItem itemBatWool;
    public final SubItem itemDogTongue;
    public final SubItem itemSoftClayJar;
    public final SubItem itemEmptyClayJar;
    public final SubItem itemFoulFume;
    public final SubItem itemDiamondVapour;
    public final SubItem itemOilOfVitriol;
    public final SubItem itemExhaleOfTheHornedOne;
    public final SubItem itemBreathOfTheGoddess;
    public final SubItem itemHintOfRebirth;
    public final SubItem itemWhiffOfMagic;
    public final SubItem itemReekOfMisfortune;
    public final SubItem itemOdourOfPurity;
    public final SubItem itemTearOfTheGoddess;
    public final SubItem itemRefinedEvil;
    public final SubItem itemDropOfLuck;
    public final SubItem itemRedstoneSoup;
    public final SubItem itemFlyingOintment;
    public final SubItem itemGhostOfTheLight;
    public final SubItem itemSoulOfTheWorld;
    public final SubItem itemSpiritOfOtherwhere;
    public final SubItem itemInfernalAnimus;
    public final SubItem itemBookOven;
    public final SubItem itemBookDistilling;
    public final SubItem itemBookCircleMagic;
    public final SubItem itemBookInfusions;
    public final SubItem itemOddPorkRaw;
    public final SubItem itemOddPorkCooked;
    public final SubItem itemDoorRowan;
    public final SubItem itemDoorAlder;
    public final SubItem itemDoorKey;
    public final SubItem itemRock;
    public final SubItem itemWeb;
    public final SubItem itemBrewOfVines;
    public final SubItem itemBrewOfWebs;
    public final SubItem itemBrewOfThorns;
    public final SubItem itemBrewOfInk;
    public final SubItem itemBrewOfSprouting;
    public final SubItem itemBrewOfErosion;
    public final SubItem itemRowanBerries;
    public final SubItem itemNecroStone;
    public final SubItem itemBrewOfRaising;
    public final SubItem itemSpectralDust;
    public final SubItem itemEnderDew;
    public final SubItem itemArtichoke;
    public final SubItem itemSeedsTreefyd;
    public final SubItem itemBrewGrotesque;
    public final SubItem itemImpregnatedLeather;
    public final SubItem itemFumeFilter;
    public final SubItem itemCreeperHeart;
    public final SubItem itemBrewOfLove;
    public final SubItem itemBrewOfIce;
    public final SubItem itemBrewOfTheDepths;
    public final SubItem itemIcyNeedle;
    public final SubItem itemFrozenHeart;
    public final SubItem itemInfernalBlood;
    public final SubItem itemBookHerbology;
    public final SubItem itemBranchEnt;
    public final SubItem itemMysticUnguent;
    public final SubItem itemDoorKeyring;
    public final SubItem itemBrewOfFrogsTongue;
    public final SubItem itemBrewOfCursedLeaping;
    public final SubItem itemBrewOfHitchcock;
    public final SubItem itemBrewOfInfection;
    public final SubItem itemOwletsWing;
    public final SubItem itemToeOfFrog;
    public final SubItem itemWormyApple;
    public final SubItem itemQuartzSphere;
    public final SubItem itemHappenstanceOil;
    public final SubItem itemSeerStone;
    public final SubItem itemBrewOfSleeping;
    public final SubItem itemBrewOfFlowingSpirit;
    public final SubItem itemBrewOfWasting;
    public final SubItem itemSleepingApple;
    public final SubItem itemDisturbedCotton;
    public final SubItem itemFancifulThread;
    public final SubItem itemTormentedTwine;
    public final SubItem itemGoldenThread;
    public final SubItem itemMellifluousHunger;
    public final DreamWeave itemDreamIntensity;
    public final SubItem itemPurifiedMilk;
    public final SubItem itemBookBiomes;
    public final SubItem itemBookWands;
    public final SubItem itemBatBall;
    public final SubItem itemBrewOfBats;
    public final SubItem itemCharmOfDisruptedDreams;
    public final SubItem itemWormwood;
    public final SubItem itemSubduedSpirit;
    public final SubItem itemFocusedWill;
    public final SubItem itemCondensedFear;
    public final SubItem itemBrewOfHollowTears;
    public final SubItem itemBrewOfSolidRock;
    public final SubItem itemBrewOfSolidDirt;
    public final SubItem itemBrewOfSolidSand;
    public final SubItem itemBrewOfSolidSandstone;
    public final SubItem itemBrewOfSolidErosion;
    public final SubItem itemInfusionBase;
    public final SubItem itemBrewOfSoaring;
    public final SubItem itemBrewGrave;
    public final SubItem itemBrewRevealing;
    public final SubItem itemBrewSubstitution;
    public final SubItem itemCongealedSpirit;
    public final SubItem itemBookBurning;
    public final SubItem itemGraveyardDust;
    public final SubItem itemBinkyHead;
    public final SubItem itemNullCatalyst;
    public final SubItem itemNullifiedLeather;
    public final SubItem itemBoltStake;
    public final SubItem itemBoltAntiMagic;
    public final SubItem itemBoltHoly;
    public final SubItem itemBoltSplitting;
    public final SubItem itemBrewSoulHunger;
    public final SubItem itemBrewSoulAnguish;
    public final SubItem itemBrewSoulFear;
    public final SubItem itemBrewSoulTorment;
    public final SubItem itemContractOwnership;
    public final SubItem itemContractTorment;
    public final SubItem itemContractBlaze;
    public final SubItem itemContractResistFire;
    public final SubItem itemContractEvaporate;
    public final SubItem itemContractFieryTouch;
    public final SubItem itemContractSmelting;
    public final SubItem itemWaystonePlayerBound;
    public final SubItem itemKobolditeDust;
    public final SubItem itemKobolditeNugget;
    public final SubItem itemKobolditeIngot;
    public final SubItem itemKobolditePentacle;
    public final SubItem itemDoorIce;
    public final SubItem itemAnnointingPaste;
    public final SubItem itemSubduedSpiritVillage;
    public final SubItem itemBoltSilver;
    public final SubItem itemWolfsbane;
    public final SubItem itemSilverDust;
    public final SubItem itemMuttonRaw;
    public final SubItem itemMuttonCooked;
    public final SubItem itemVampireBookPage;
    public final SubItem itemDarkCloth;
    public final SubItem itemWoodenStake;
    public final SubItem itemBloodWarm;
    public final SubItem itemBloodLiliths;
    public final SubItem itemHeartOfGold;
    @SideOnly(value=Side.CLIENT)
    private IIcon overlayGenericIcon;
    @SideOnly(value=Side.CLIENT)
    private IIcon overlayBroomIcon;
    @SideOnly(value=Side.CLIENT)
    private IIcon overlaySolidifierIcon;
    @SideOnly(value=Side.CLIENT)
    private IIcon overlayInfusedBrewIcon;

    public ItemGeneral() {
        this.itemDemonHeart = SubItem.register((SubItem)new Drinkable(23, "demonHeart", 2, EnumAction.eat, new PotionEffect(Potion.field_76434_w.field_76415_H, 2400, 4), new PotionEffect(Potion.field_76428_l.field_76415_H, 2400, 1), new PotionEffect(Potion.field_76420_g.field_76415_H, 2400, 2), new PotionEffect(Potion.field_76424_c.field_76415_H, 2400, 2), new PotionEffect(Potion.field_76426_n.field_76415_H, 2400, 2), new PotionEffect(Potion.field_76431_k.field_76415_H, 2400), new PotionEffect(Potion.field_76438_s.field_76415_H, 3600, 1)), this.subItems);
        this.itemBatWool = SubItem.register(new SubItem(24, "batWool"), this.subItems);
        this.itemDogTongue = SubItem.register(new SubItem(25, "dogTongue"), this.subItems);
        this.itemSoftClayJar = SubItem.register(new SubItem(26, "clayJarSoft"), this.subItems);
        this.itemEmptyClayJar = SubItem.register(new SubItem(27, "clayJar"), this.subItems);
        this.itemFoulFume = SubItem.register(new SubItem(28, "foulFume"), this.subItems);
        this.itemDiamondVapour = SubItem.register(new SubItem(29, "diamondVapour"), this.subItems);
        this.itemOilOfVitriol = SubItem.register(new SubItem(30, "oilOfVitriol"), this.subItems);
        this.itemExhaleOfTheHornedOne = SubItem.register(new SubItem(31, "exhaleOfTheHornedOne"), this.subItems);
        this.itemBreathOfTheGoddess = SubItem.register(new SubItem(32, "breathOfTheGoddess"), this.subItems);
        this.itemHintOfRebirth = SubItem.register(new SubItem(33, "hintOfRebirth"), this.subItems);
        this.itemWhiffOfMagic = SubItem.register(new SubItem(34, "whiffOfMagic"), this.subItems);
        this.itemReekOfMisfortune = SubItem.register(new SubItem(35, "reekOfMisfortune"), this.subItems);
        this.itemOdourOfPurity = SubItem.register(new SubItem(36, "odourOfPurity"), this.subItems);
        this.itemTearOfTheGoddess = SubItem.register(new SubItem(37, "tearOfTheGoddess"), this.subItems);
        this.itemRefinedEvil = SubItem.register(new SubItem(38, "refinedEvil"), this.subItems);
        this.itemDropOfLuck = SubItem.register(new SubItem(39, "dropOfLuck"), this.subItems);
        this.itemRedstoneSoup = SubItem.register((SubItem)new Drinkable(40, "redstoneSoup", 1, new PotionEffect(Potion.field_76434_w.field_76415_H, 2400, 1)), this.subItems);
        this.itemFlyingOintment = SubItem.register((SubItem)new Drinkable(41, "flyingOintment", 2, new PotionEffect(Potion.field_76436_u.field_76415_H, 1200, 2)), this.subItems);
        this.itemGhostOfTheLight = SubItem.register((SubItem)new Drinkable(42, "ghostOfTheLight", 2, new PotionEffect(Potion.field_76436_u.field_76415_H, 1200, 1)), this.subItems);
        this.itemSoulOfTheWorld = SubItem.register((SubItem)new Drinkable(43, "soulOfTheWorld", 2, new PotionEffect(Potion.field_76436_u.field_76415_H, 1200, 1)), this.subItems);
        this.itemSpiritOfOtherwhere = SubItem.register((SubItem)new Drinkable(44, "spiritOfOtherwhere", 2, new PotionEffect(Potion.field_76436_u.field_76415_H, 1200, 1)), this.subItems);
        this.itemInfernalAnimus = SubItem.register((SubItem)new Drinkable(45, "infernalAnimus", 2, new PotionEffect(Potion.field_76436_u.field_76415_H, 1200, 1), new PotionEffect(Potion.field_82731_v.field_76415_H, 3600, 2)), this.subItems);
        this.itemBookOven = SubItem.register(new SubItem(46, "bookOven"), this.subItems);
        this.itemBookDistilling = SubItem.register(new SubItem(47, "bookDistilling"), this.subItems);
        this.itemBookCircleMagic = SubItem.register(new SubItem(48, "bookCircleMagic"), this.subItems);
        this.itemBookInfusions = SubItem.register(new SubItem(49, "bookInfusions"), this.subItems);
        this.itemOddPorkRaw = SubItem.register((SubItem)new Edible(50, "oddPorkchopRaw", 3, 0.3f, true), this.subItems);
        this.itemOddPorkCooked = SubItem.register((SubItem)new Edible(51, "oddPorkchopCooked", 8, 0.8f, true), this.subItems);
        this.itemDoorRowan = SubItem.register(new SubItem(52, "doorRowan"), this.subItems);
        this.itemDoorAlder = SubItem.register(new SubItem(53, "doorAlder"), this.subItems);
        this.itemDoorKey = SubItem.register(new SubItem(54, "doorKey"), this.subItems);
        this.itemRock = SubItem.register(new SubItem(55, "rock"), this.subItems);
        this.itemWeb = SubItem.register(new SubItem(56, "web"), this.subItems);
        this.itemBrewOfVines = SubItem.register((SubItem)new Brew(57, "brewVines"), this.subItems);
        this.itemBrewOfWebs = SubItem.register((SubItem)new Brew(58, "brewWeb"), this.subItems);
        this.itemBrewOfThorns = SubItem.register((SubItem)new Brew(59, "brewThorns"), this.subItems);
        this.itemBrewOfInk = SubItem.register((SubItem)new Brew(60, "brewInk"), this.subItems);
        this.itemBrewOfSprouting = SubItem.register((SubItem)new Brew(61, "brewSprouting"), this.subItems);
        this.itemBrewOfErosion = SubItem.register((SubItem)new Brew(62, "brewErosion"), this.subItems);
        this.itemRowanBerries = SubItem.register((SubItem)new Edible(63, "berriesRowan", 1, 6.0f, false), this.subItems);
        this.itemNecroStone = SubItem.register(new SubItem(64, "necroStone", 1).setEnchanted(true), this.subItems);
        this.itemBrewOfRaising = SubItem.register((SubItem)new Brew(65, "brewRaising"), this.subItems);
        this.itemSpectralDust = SubItem.register(new SubItem(66, "spectralDust"), this.subItems);
        this.itemEnderDew = SubItem.register(new SubItem(67, "enderDew"), this.subItems);
        this.itemArtichoke = SubItem.register((SubItem)new Edible(69, "artichoke", 20, 0.0f, false), this.subItems);
        this.itemSeedsTreefyd = SubItem.register(new SubItem(70, "seedsTreefyd"), this.subItems);
        this.itemBrewGrotesque = SubItem.register(new Drinkable(71, "brewGrotesque", 1, new PotionEffect[0]).setPotion(true), this.subItems);
        this.itemImpregnatedLeather = SubItem.register(new SubItem(72, "impregnatedLeather"), this.subItems);
        this.itemFumeFilter = SubItem.register(new SubItem(73, "fumeFilter"), this.subItems);
        this.itemCreeperHeart = SubItem.register((SubItem)new Drinkable(74, "creeperHeart", 1, EnumAction.eat, new PotionEffect(Potion.field_76426_n.field_76415_H, 20, 0)), this.subItems);
        this.itemBrewOfLove = SubItem.register((SubItem)new Brew(75, "brewLove"), this.subItems);
        this.itemBrewOfIce = SubItem.register((SubItem)new Brew(76, "brewIce"), this.subItems);
        this.itemBrewOfTheDepths = SubItem.register(new Drinkable(77, "brewDepths", 1, new PotionEffect[0]).setPotion(true), this.subItems);
        this.itemIcyNeedle = SubItem.register(new SubItem(78, "icyNeedle"), this.subItems);
        this.itemFrozenHeart = SubItem.register((SubItem)new Drinkable(79, "frozenHeart", 1, EnumAction.eat, new PotionEffect(Potion.field_76426_n.field_76415_H, 20, 0)), this.subItems);
        this.itemInfernalBlood = SubItem.register(new SubItem(80, "infernalBlood"), this.subItems);
        this.itemBookHerbology = SubItem.register(new SubItem(81, "bookHerbology"), this.subItems);
        this.itemBranchEnt = SubItem.register(new SubItem(82, "entbranch"), this.subItems);
        this.itemMysticUnguent = SubItem.register((SubItem)new Drinkable(83, "mysticunguent", 2, new PotionEffect(Potion.field_76437_t.field_76415_H, 1200, 1)), this.subItems);
        this.itemDoorKeyring = SubItem.register(new SubItem(84, "doorKeyring"), this.subItems);
        this.itemBrewOfFrogsTongue = SubItem.register((SubItem)new Brew(85, "brewFrogsTongue"), this.subItems);
        this.itemBrewOfCursedLeaping = SubItem.register((SubItem)new Brew(86, "brewCursedLeaping"), this.subItems);
        this.itemBrewOfHitchcock = SubItem.register((SubItem)new Brew(87, "brewHitchcock"), this.subItems);
        this.itemBrewOfInfection = SubItem.register((SubItem)new Brew(88, "brewInfection"), this.subItems);
        this.itemOwletsWing = SubItem.register(new SubItem(89, "owletsWing"), this.subItems);
        this.itemToeOfFrog = SubItem.register(new SubItem(90, "toeOfFrog"), this.subItems);
        this.itemWormyApple = SubItem.register((SubItem)new Drinkable(91, "appleWormy", 0, EnumAction.eat, new PotionEffect(Potion.field_76436_u.field_76415_H, 60)), this.subItems);
        this.itemQuartzSphere = SubItem.register(new SubItem(92, "quartzSphere"), this.subItems);
        this.itemHappenstanceOil = SubItem.register((SubItem)new Drinkable(93, "happenstanceOil", 1, new PotionEffect(Potion.field_76439_r.field_76415_H, 1200)), this.subItems);
        this.itemSeerStone = SubItem.register(new SubItem(94, "seerStone", 1).setEnchanted(true), this.subItems);
        this.itemBrewOfSleeping = SubItem.register(new Drinkable(95, "brewSleep", 1, new PotionEffect[0]).setPotion(true), this.subItems);
        this.itemBrewOfFlowingSpirit = SubItem.register((SubItem)new BrewFluid(96, "brewFlowingSpirit", Witchery.Fluids.FLOWING_SPIRIT), this.subItems);
        this.itemBrewOfWasting = SubItem.register((SubItem)new Brew(97, "brewWasting"), this.subItems);
        this.itemSleepingApple = SubItem.register((SubItem)new Edible(98, "sleepingApple", 3, 3.0f, false, true), this.subItems);
        this.itemDisturbedCotton = SubItem.register(new SubItem(99, "disturbedCotton"), this.subItems);
        this.itemFancifulThread = SubItem.register(new SubItem(100, "fancifulThread"), this.subItems);
        this.itemTormentedTwine = SubItem.register(new SubItem(101, "tormentedTwine"), this.subItems);
        this.itemGoldenThread = SubItem.register(new SubItem(102, "goldenThread"), this.subItems);
        this.itemMellifluousHunger = SubItem.register(new SubItem(103, "mellifluousHunger"), this.subItems);
        this.itemDreamIntensity = DreamWeave.register(new DreamWeave(104, 4, "weaveIntensity", Potion.field_76439_r, Potion.field_76440_q, 300, 0, 17, 22), this.subItems, this.weaves);
        this.itemPurifiedMilk = SubItem.register((SubItem)new Drinkable(105, "purifiedMilk", 1, new PotionEffect[0]), this.subItems);
        this.itemBookBiomes = SubItem.register(new SubItem(106, "bookBiomes"), this.subItems);
        this.itemBookWands = SubItem.register(new SubItem(107, "bookWands"), this.subItems);
        this.itemBatBall = SubItem.register(new SubItem(108, "batBall"), this.subItems);
        this.itemBrewOfBats = SubItem.register((SubItem)new Brew(109, "brewBats"), this.subItems);
        this.itemCharmOfDisruptedDreams = SubItem.register(new SubItem(110, "charmDisruptedDreams"), this.subItems);
        this.itemWormwood = SubItem.register(new SubItem(111, "wormwood"), this.subItems);
        this.itemSubduedSpirit = SubItem.register(new SubItem(112, "subduedSpirit"), this.subItems);
        this.itemFocusedWill = SubItem.register(new SubItem(113, "focusedWill"), this.subItems);
        this.itemCondensedFear = SubItem.register(new SubItem(114, "condensedFear"), this.subItems);
        this.itemBrewOfHollowTears = SubItem.register((SubItem)new BrewFluid(115, "brewHollowTears", Witchery.Fluids.HOLLOW_TEARS), this.subItems);
        this.itemBrewOfSolidRock = SubItem.register((SubItem)new BrewSolidifySpirit(116, "brewSolidStone", Blocks.field_150348_b), this.subItems);
        this.itemBrewOfSolidDirt = SubItem.register((SubItem)new BrewSolidifySpirit(117, "brewSolidDirt", Blocks.field_150346_d), this.subItems);
        this.itemBrewOfSolidSand = SubItem.register((SubItem)new BrewSolidifySpirit(118, "brewSolidSand", (Block)Blocks.field_150354_m), this.subItems);
        this.itemBrewOfSolidSandstone = SubItem.register((SubItem)new BrewSolidifySpirit(119, "brewSolidSandstone", Blocks.field_150322_A), this.subItems);
        this.itemBrewOfSolidErosion = SubItem.register((SubItem)new BrewSolidifySpirit(120, "brewSolidErosion", null), this.subItems);
        this.itemInfusionBase = SubItem.register((SubItem)new Drinkable(121, "infusionBase", 2, new PotionEffect(Potion.field_82731_v.field_76415_H, 200, 3)), this.subItems);
        this.itemBrewOfSoaring = SubItem.register((SubItem)new InfusedBrew(122, "brewSoaring", InfusedBrewEffect.Soaring), this.subItems);
        this.itemBrewGrave = SubItem.register((SubItem)new InfusedBrew(123, "brewGrave", InfusedBrewEffect.Grave), this.subItems);
        this.itemBrewRevealing = SubItem.register((SubItem)new Brew(124, "brewRevealing"){

            @Override
            public Brew.BrewResult onImpact(World world, EntityLivingBase thrower, MovingObjectPosition mop, boolean enhanced, double brewX, double brewY, double brewZ, AxisAlignedBB brewBounds) {
                double RADIUS = enhanced ? 8.0 : 5.0;
                double RADIUS_SQ = RADIUS * RADIUS;
                AxisAlignedBB areaOfEffect = brewBounds.func_72314_b(RADIUS, RADIUS, RADIUS);
                List entities = world.func_72872_a(EntityLivingBase.class, areaOfEffect);
                if (entities != null && !entities.isEmpty()) {
                    for (EntityLivingBase entityLiving : entities) {
                        EntityPlayer player;
                        ExtendedPlayer playerEx;
                        double distanceSq = entityLiving.func_70092_e(brewX, brewY, brewZ);
                        if (!(distanceSq <= RADIUS_SQ)) continue;
                        double scalingFactor = 1.0 - Math.sqrt(distanceSq) / RADIUS;
                        if (entityLiving == mop.field_72308_g) {
                            scalingFactor = 1.0;
                        }
                        if (entityLiving.func_70644_a(Potion.field_76441_p)) {
                            entityLiving.func_82170_o(Potion.field_76441_p.field_76415_H);
                        }
                        if (entityLiving instanceof EntityPlayerMP && entityLiving.func_82150_aj()) {
                            entityLiving.func_82142_c(false);
                        }
                        if (entityLiving instanceof EntityPlayer && (playerEx = ExtendedPlayer.get(player = (EntityPlayer)entityLiving)) != null && playerEx.getCreatureType() == TransformCreature.PLAYER) {
                            ParticleEffect.SMOKE.send(SoundEffect.WITCHERY_RANDOM_POOF, (Entity)player, 0.5, 2.0, 16);
                            Shapeshift.INSTANCE.shiftTo(player, TransformCreature.NONE);
                        }
                        if (!(entityLiving instanceof EntitySummonedUndead) || !((EntitySummonedUndead)entityLiving).isObscured()) continue;
                        ((EntitySummonedUndead)entityLiving).setObscured(false);
                    }
                }
                return Brew.BrewResult.SHOW_EFFECT;
            }
        }, this.subItems);
        this.itemBrewSubstitution = SubItem.register((SubItem)new Brew(125, "brewSubstitution"){

            @Override
            public Brew.BrewResult onImpact(World world, EntityLivingBase thrower, final MovingObjectPosition mop, boolean enhanced, double brewX, double brewY, double brewZ, AxisAlignedBB brewBounds) {
                if (mop == null || mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) {
                    return Brew.BrewResult.DROP_ITEM;
                }
                int RADIUS = enhanced ? 6 : 4;
                final double RADIUS_SQ = RADIUS * RADIUS;
                AxisAlignedBB areaOfEffect = brewBounds.func_72314_b((double)RADIUS, (double)RADIUS, (double)RADIUS);
                List entities = world.func_72872_a(EntityItem.class, areaOfEffect);
                if (entities != null && !entities.isEmpty()) {
                    final ArrayList<EntityItem> items = new ArrayList<EntityItem>();
                    for (EntityItem item : entities) {
                        ItemStack stack;
                        double distanceSq = item.func_70092_e(brewX, brewY, brewZ);
                        if (!(distanceSq <= RADIUS_SQ) || !((stack = item.func_92059_d()).func_77973_b() instanceof ItemBlock)) continue;
                        items.add(item);
                    }
                    final Block refBlock = BlockUtil.getBlock(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                    final int refMeta = BlockUtil.getBlockMetadata(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d);
                    if (items.size() > 0 && refBlock != null && BlockProtect.canBreak(refBlock, world)) {
                        new EffectSpiral(new ISpiralBlockAction(){
                            int stackIndex = 0;
                            int subCount = 0;

                            @Override
                            public void onSpiralActionStart(World world, int posX, int posY, int posZ) {
                            }

                            @Override
                            public boolean onSpiralBlockAction(World world, int posX, int posY, int posZ) {
                                if (Coord.distanceSq(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, posX, posY, posZ) < RADIUS_SQ) {
                                    boolean found = false;
                                    if (BlockUtil.getBlock(world, posX, posY, posZ) == refBlock && BlockUtil.isReplaceableBlock(world, posX, posY + 1, posZ)) {
                                        found = true;
                                    } else if (BlockUtil.getBlock(world, posX, posY + 1, posZ) == refBlock && BlockUtil.isReplaceableBlock(world, posX, posY + 2, posZ)) {
                                        ++posY;
                                        found = true;
                                    } else if (BlockUtil.getBlock(world, posX, posY - 1, posZ) == refBlock && BlockUtil.isReplaceableBlock(world, posX, posY, posZ)) {
                                        --posY;
                                        found = true;
                                    } else if (BlockUtil.getBlock(world, posX, posY + 2, posZ) == refBlock && BlockUtil.isReplaceableBlock(world, posX, posY + 3, posZ)) {
                                        posY += 2;
                                        found = true;
                                    } else if (BlockUtil.getBlock(world, posX, posY - 2, posZ) == refBlock && BlockUtil.isReplaceableBlock(world, posX, posY - 1, posZ)) {
                                        posY -= 2;
                                        found = true;
                                    }
                                    if (found) {
                                        ++this.subCount;
                                        ItemStack stack = ((EntityItem)items.get(this.stackIndex)).func_92059_d();
                                        BlockUtil.setBlock(world, posX, posY, posZ, (ItemBlock)stack.func_77973_b(), stack.func_77960_j(), 3);
                                        ParticleEffect.INSTANT_SPELL.send(SoundEffect.NONE, world, posX, posY, posZ, 1.0, 1.0, 16);
                                        if (--stack.field_77994_a == 0) {
                                            ((EntityItem)items.get(this.stackIndex)).func_70106_y();
                                            ++this.stackIndex;
                                        }
                                    }
                                }
                                return this.stackIndex < items.size();
                            }

                            @Override
                            public void onSpiralActionStop(World world, int posX, int posY, int posZ) {
                                while (this.subCount > 0) {
                                    int quantity = this.subCount > 64 ? 64 : this.subCount;
                                    this.subCount -= quantity;
                                    world.func_72838_d((Entity)new EntityItem(world, 0.5 + (double)posX, 1.5 + (double)posY, 0.5 + (double)posZ, new ItemStack(refBlock, quantity, refMeta)));
                                }
                            }
                        }).apply(world, mop.field_72311_b, mop.field_72312_c, mop.field_72309_d, RADIUS * 2, RADIUS * 2);
                        return Brew.BrewResult.SHOW_EFFECT;
                    }
                }
                return Brew.BrewResult.DROP_ITEM;
            }
        }, this.subItems);
        this.itemCongealedSpirit = SubItem.register(new Drinkable(126, "brewCongealedSpirit", 1, new PotionEffect(Potion.field_76439_r.field_76415_H, TimeUtil.secsToTicks(30), 1)).setPotion(true), this.subItems);
        this.itemBookBurning = SubItem.register(new SubItem(127, "bookBurning"), this.subItems);
        this.itemGraveyardDust = SubItem.register(new SubItem(128, "graveyardDust"), this.subItems);
        this.itemBinkyHead = SubItem.register((SubItem)new BoltType(129, "binkyhead"), this.subItems);
        this.itemNullCatalyst = SubItem.register((SubItem)new BoltType(130, "nullcatalyst"), this.subItems);
        this.itemNullifiedLeather = SubItem.register((SubItem)new BoltType(131, "nullifiedleather"), this.subItems);
        this.itemBoltStake = SubItem.register((SubItem)new BoltType(132, "boltStake"), this.subItems);
        this.itemBoltAntiMagic = SubItem.register((SubItem)new BoltType(133, "boltAntiMagic"), this.subItems);
        this.itemBoltHoly = SubItem.register((SubItem)new BoltType(134, "boltHoly"), this.subItems);
        this.itemBoltSplitting = SubItem.register((SubItem)new BoltType(135, "boltSplitting"), this.subItems);
        this.itemBrewSoulHunger = SubItem.register((SubItem)new BrewSoul(136, "brewSoulHunger", EffectRegistry.CarnosaDiem), this.subItems);
        this.itemBrewSoulAnguish = SubItem.register((SubItem)new BrewSoul(137, "brewSoulAnguish", EffectRegistry.Ignianima), this.subItems);
        this.itemBrewSoulFear = SubItem.register((SubItem)new BrewSoul(138, "brewSoulFear", EffectRegistry.MORSMORDRE), this.subItems);
        this.itemBrewSoulTorment = SubItem.register((SubItem)new BrewSoul(139, "brewSoulTorment", EffectRegistry.Tormentum), this.subItems);
        this.itemContractOwnership = SubItem.register(new SubItem(140, "contract"), this.subItems);
        this.itemContractTorment = SubItem.register(new SubItem(141, "contractTorment"), this.subItems);
        this.itemContractBlaze = SubItem.register((SubItem)new ItemGeneralContract(142, "contractBlaze"){

            @Override
            public boolean activate(ItemStack stack, EntityLivingBase targetEntity) {
                EntityCreature blaze = InfusionInfernal.spawnCreature(targetEntity.field_70170_p, EntityBlaze.class, targetEntity, 1, 2, ParticleEffect.FLAME, SoundEffect.RANDOM_FIZZ);
                if (blaze != null) {
                    blaze.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50.0);
                    blaze.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(7.0);
                    return true;
                }
                return false;
            }
        }, this.subItems);
        this.itemContractResistFire = SubItem.register((SubItem)new ItemGeneralContract(143, "contractResistFire"){

            @Override
            public boolean activate(ItemStack stack, EntityLivingBase targetEntity) {
                targetEntity.func_70690_d(new PotionEffect(Potion.field_76426_n.field_76415_H, TimeUtil.minsToTicks(15)));
                return true;
            }
        }, this.subItems);
        this.itemContractEvaporate = SubItem.register((SubItem)new ItemGeneralContract(144, "contractEvaporate"){

            @Override
            public boolean activate(ItemStack stack, EntityLivingBase targetEntity) {
                if (targetEntity instanceof EntityPlayer) {
                    PlayerEffects.IMP_EVAPORATION.applyTo((EntityPlayer)targetEntity, TimeUtil.minsToTicks(10));
                    return true;
                }
                return false;
            }
        }, this.subItems);
        this.itemContractFieryTouch = SubItem.register((SubItem)new ItemGeneralContract(145, "contractFieryTouch"){

            @Override
            public boolean activate(ItemStack stack, EntityLivingBase targetEntity) {
                if (targetEntity instanceof EntityPlayer) {
                    PlayerEffects.IMP_FIRE_TOUCH.applyTo((EntityPlayer)targetEntity, TimeUtil.minsToTicks(10));
                    return true;
                }
                return false;
            }
        }, this.subItems);
        this.itemContractSmelting = SubItem.register((SubItem)new ItemGeneralContract(146, "contractSmelting"){

            @Override
            public boolean activate(ItemStack stack, EntityLivingBase targetEntity) {
                if (targetEntity instanceof EntityPlayer) {
                    PlayerEffects.IMP_METLING_TOUCH.applyTo((EntityPlayer)targetEntity, TimeUtil.minsToTicks(10));
                    return true;
                }
                return false;
            }
        }, this.subItems);
        this.itemWaystonePlayerBound = SubItem.register(new SubItem(147, "waystoneCreatureBound", 1, false), this.subItems);
        this.itemKobolditeDust = SubItem.register(new SubItem(148, "kobolditedust"), this.subItems);
        this.itemKobolditeNugget = SubItem.register(new SubItem(149, "kobolditenugget"), this.subItems);
        this.itemKobolditeIngot = SubItem.register(new SubItem(150, "kobolditeingot"), this.subItems);
        this.itemKobolditePentacle = SubItem.register(new SubItem(151, "pentacle"), this.subItems);
        this.itemDoorIce = SubItem.register(new SubItem(152, "doorIce"), this.subItems);
        this.itemAnnointingPaste = SubItem.register(new SubItem(153, "annointingPaste"), this.subItems);
        this.itemSubduedSpiritVillage = SubItem.register(new SubItem(154, "subduedSpiritVillage"), this.subItems);
        this.itemBoltSilver = SubItem.register((SubItem)new BoltType(155, "boltSilver"), this.subItems);
        this.itemWolfsbane = SubItem.register(new SubItem(156, "wolfsbane"), this.subItems);
        this.itemSilverDust = SubItem.register(new SubItem(157, "silverdust"), this.subItems);
        this.itemMuttonRaw = SubItem.register((SubItem)new Edible(158, "muttonraw", 2, 0.2f, true), this.subItems);
        this.itemMuttonCooked = SubItem.register((SubItem)new Edible(159, "muttoncooked", 6, 0.8f, true), this.subItems);
        this.itemVampireBookPage = SubItem.register(new SubItem(160, "vbookPage"), this.subItems);
        this.itemDarkCloth = SubItem.register(new SubItem(161, "darkCloth"), this.subItems);
        this.itemWoodenStake = SubItem.register(new SubItem(162, "stake"), this.subItems);
        this.itemBloodWarm = SubItem.register((SubItem)new Drinkable(163, "warmBlood", 1, new PotionEffect[0]){

            @Override
            public void onDrunk(World world, EntityPlayer player, ItemStack itemstack) {
                if (!world.field_72995_K) {
                    ExtendedPlayer playerEx = ExtendedPlayer.get(player);
                    if (playerEx.isVampire()) {
                        playerEx.increaseBloodPower(500);
                    } else {
                        player.func_70690_d(new PotionEffect(Potion.field_76438_s.field_76415_H, TimeUtil.secsToTicks(6)));
                    }
                }
            }
        }, this.subItems);
        this.itemBloodLiliths = SubItem.register((SubItem)new Drinkable(164, "lilithsBlood", 2, new PotionEffect[0]){

            @Override
            public void onDrunk(World world, EntityPlayer player, ItemStack itemstack) {
                if (!world.field_72995_K) {
                    ExtendedPlayer playerEx = ExtendedPlayer.get(player);
                    int level = playerEx.getVampireLevel();
                    if (level == 10) {
                        playerEx.increaseBloodPower(2000);
                    } else {
                        playerEx.increaseVampireLevel();
                    }
                }
            }
        }, this.subItems);
        this.itemHeartOfGold = SubItem.register(new SubItem(165, "heartofgold"), this.subItems);
        this.func_77656_e(0);
        this.func_77625_d(64);
        this.func_77627_a(true);
    }

    public boolean isBrew(int itemDamage) {
        return this.subItems.get(itemDamage) instanceof Brew;
    }

    public boolean isBrew(ItemStack stack) {
        return stack != null && stack.func_77973_b() == this && this.isBrew(stack.func_77960_j());
    }

    public String func_77667_c(ItemStack itemStack) {
        return this.func_77658_a() + "." + this.subItems.get(itemStack.func_77960_j()).unlocalizedName;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_94581_a(IIconRegister iconRegister) {
        String defaultIconName = this.func_111208_A();
        for (SubItem subItem : this.subItems) {
            if (subItem == null) continue;
            subItem.registerIcon(iconRegister, this);
        }
        this.overlayGenericIcon = iconRegister.func_94245_a(defaultIconName + ".genericoverlay");
        this.overlayBroomIcon = iconRegister.func_94245_a(defaultIconName + ".broomOverlay");
        this.overlaySolidifierIcon = iconRegister.func_94245_a(defaultIconName + ".brewSolidOverlay");
        this.overlayInfusedBrewIcon = iconRegister.func_94245_a(defaultIconName + ".brewInfusedOverlay");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77617_a(int damage) {
        return this.subItems.get(Math.max(damage, 0)).icon;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon func_77618_c(int damage, int pass) {
        if (pass == 0) {
            return super.func_77618_c(damage, pass);
        }
        if (this.subItems.get(damage).isSolidifier()) {
            return this.overlaySolidifierIcon;
        }
        if (this.subItems.get(damage).isInfused()) {
            return this.overlayInfusedBrewIcon;
        }
        if (this.subItems.get(damage).isPotion()) {
            return Items.field_151068_bn.func_77618_c(this.subItems.get(damage) instanceof Brew ? 16384 : 0, pass);
        }
        if (this.itemBroomEnchanted.damageValue == damage) {
            return this.overlayBroomIcon;
        }
        return this.overlayGenericIcon;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_82790_a(ItemStack stack, int pass) {
        if (!this.itemBroomEnchanted.isMatch(stack) || pass == 0) {
            return super.func_82790_a(stack, pass);
        }
        int j = this.getBroomItemColor(stack);
        if (j > 15) {
            return super.func_82790_a(stack, pass);
        }
        if (j < 0) {
            j = 12;
        }
        return ItemDye.field_150922_c[BlockColored.func_150031_c((int)j)];
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_77623_v() {
        return true;
    }

    public boolean hasEffect(ItemStack stack, int pass) {
        return pass == 0 && this.subItems.get(stack.func_77960_j()).isEnchanted() || this.itemBroomEnchanted.isMatch(stack) || this.itemSubduedSpirit.isMatch(stack) || this.itemSubduedSpiritVillage.isMatch(stack);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_150895_a(Item item, CreativeTabs tab, List itemList) {
        for (SubItem subItem : this.subItems) {
            if (subItem == null || !subItem.showInCreativeTab) continue;
            itemList.add(subItem.createStack());
        }
    }

    @SideOnly(value=Side.CLIENT)
    public EnumRarity func_77613_e(ItemStack itemstack) {
        return EnumRarity.values()[this.subItems.get(itemstack.func_77960_j()).rarity];
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void func_77624_a(ItemStack stack, EntityPlayer player, List list, boolean includeHandlers) {
        String localText;
        NBTTagList keyList;
        NBTTagCompound nbtTag;
        String taglock;
        String location = this.getBoundDisplayName(stack);
        if (location != null && !location.isEmpty()) {
            list.add(location);
        }
        if (!(taglock = Witchery.Items.TAGLOCK_KIT.getBoundEntityDisplayName(stack, 1)).isEmpty()) {
            list.add(String.format(Witchery.resource("item.witcheryTaglockKit.boundto"), taglock));
        }
        if (this.itemDoorKey.isMatch(stack)) {
            nbtTag = stack.func_77978_p();
            if (nbtTag != null && nbtTag.func_74764_b("doorX") && nbtTag.func_74764_b("doorY") && nbtTag.func_74764_b("doorZ")) {
                int doorX = nbtTag.func_74762_e("doorX");
                int doorY = nbtTag.func_74762_e("doorY");
                int doorZ = nbtTag.func_74762_e("doorZ");
                if (nbtTag.func_74764_b("doorD") && nbtTag.func_74764_b("doorDN")) {
                    list.add(String.format("%s: %d, %d, %d", nbtTag.func_74779_i("doorDN"), doorX, doorY, doorZ));
                } else {
                    list.add(String.format("%d, %d, %d", doorX, doorY, doorZ));
                }
            }
        } else if (this.itemDoorKeyring.isMatch(stack) && (nbtTag = stack.func_77978_p()) != null && nbtTag.func_74764_b("doorKeys") && (keyList = nbtTag.func_150295_c("doorKeys", 10)) != null) {
            for (int i = 0; i < keyList.func_74745_c(); ++i) {
                NBTTagCompound keyTag = keyList.func_150305_b(i);
                if (keyTag == null || !keyTag.func_74764_b("doorX") || !keyTag.func_74764_b("doorY") || !keyTag.func_74764_b("doorZ")) continue;
                int doorX = keyTag.func_74762_e("doorX");
                int doorY = keyTag.func_74762_e("doorY");
                int doorZ = keyTag.func_74762_e("doorZ");
                if (keyTag.func_74764_b("doorD") && keyTag.func_74764_b("doorDN")) {
                    list.add(String.format("%s: %d, %d, %d", keyTag.func_74779_i("doorDN"), doorX, doorY, doorZ));
                    continue;
                }
                list.add(String.format("%d, %d, %d", doorX, doorY, doorZ));
            }
        }
        if (stack.func_77960_j() == this.itemContractTorment.damageValue && (localText = Witchery.resource("item.witchery:ingredient.contractTorment.tip")) != null) {
            for (String s : localText.split("\n")) {
                if (s.isEmpty()) continue;
                list.add(s);
            }
        }
    }

    public String getBoundDisplayName(ItemStack itemstack) {
        NBTTagCompound tag = itemstack.func_77978_p();
        if (tag != null && tag.func_74764_b("PosX") && tag.func_74764_b("PosY") && tag.func_74764_b("PosZ") && tag.func_74764_b("NameD")) {
            return String.format("%s: %d, %d, %d", tag.func_74779_i("NameD"), tag.func_74762_e("PosX"), tag.func_74762_e("PosY"), tag.func_74762_e("PosZ"));
        }
        return "";
    }

    public void bindToLocation(World world, int posX, int posY, int posZ, int dimension, String dimensionName, ItemStack itemstack) {
        if (!itemstack.func_77942_o()) {
            itemstack.func_77982_d(new NBTTagCompound());
        }
        itemstack.func_77978_p().func_74768_a("PosX", posX);
        itemstack.func_77978_p().func_74768_a("PosY", posY);
        itemstack.func_77978_p().func_74768_a("PosZ", posZ);
        itemstack.func_77978_p().func_74768_a("PosD", dimension);
        itemstack.func_77978_p().func_74778_a("NameD", dimensionName);
    }

    public boolean hasLocationBinding(ItemStack itemstack) {
        if (itemstack.func_77942_o()) {
            NBTTagCompound nbtTag = itemstack.func_77978_p();
            return nbtTag.func_74764_b("PosX") && nbtTag.func_74764_b("PosY") && nbtTag.func_74764_b("PosZ") && nbtTag.func_74764_b("PosD") && nbtTag.func_74764_b("NameD");
        }
        return false;
    }

    public void copyLocationBinding(ItemStack from, ItemStack to) {
        if (this.hasLocationBinding(from)) {
            NBTTagCompound nbtTagFrom = from.func_77978_p();
            if (!to.func_77942_o()) {
                to.func_77982_d(new NBTTagCompound());
            }
            NBTTagCompound nbtTagTo = to.func_77978_p();
            nbtTagTo.func_74768_a("PosX", nbtTagFrom.func_74762_e("PosX"));
            nbtTagTo.func_74768_a("PosY", nbtTagFrom.func_74762_e("PosY"));
            nbtTagTo.func_74768_a("PosZ", nbtTagFrom.func_74762_e("PosZ"));
            nbtTagTo.func_74768_a("PosD", nbtTagFrom.func_74762_e("PosD"));
            nbtTagTo.func_74778_a("NameD", nbtTagFrom.func_74779_i("NameD"));
            if (from.func_82837_s()) {
                to.func_151001_c(from.func_82833_r());
            }
        }
    }

    public boolean copyLocationBinding(World world, ItemStack from, BlockCircle.TileEntityCircle.ActivatedRitual to) {
        if (!this.hasLocationBinding(from)) {
            return false;
        }
        NBTTagCompound nbtTagFrom = from.func_77978_p();
        if (nbtTagFrom.func_74762_e("PosD") != world.field_73011_w.field_76574_g) {
            return false;
        }
        Coord coord = new Coord(nbtTagFrom.func_74762_e("PosX"), nbtTagFrom.func_74762_e("PosY"), nbtTagFrom.func_74762_e("PosZ"));
        to.setLocation(coord);
        return true;
    }

    public ItemStack func_77654_b(ItemStack itemstack, World world, EntityPlayer player) {
        SubItem subItem = this.subItems.get(itemstack.func_77960_j());
        if (this.itemWaystoneBound.isMatch(itemstack)) {
            if (!world.field_72995_K && player instanceof EntityPlayerMP) {
                Witchery.packetPipeline.sendTo((IMessage)new PacketCamPos(false, false, null), (EntityPlayerMP)player);
            }
            return itemstack;
        }
        if (subItem instanceof Edible) {
            if (!player.field_71075_bZ.field_75098_d) {
                --itemstack.field_77994_a;
                if (itemstack.field_77994_a <= 0) {
                    itemstack.field_77994_a = 0;
                    player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
                }
            }
            Edible edible = (Edible)subItem;
            if (subItem == this.itemArtichoke) {
                int foodLevel = player.func_71024_bL().func_75116_a();
                player.func_71024_bL().func_75122_a(edible.healAmount, edible.saturationModifier);
                int healed = player.func_71024_bL().func_75116_a() - foodLevel;
                player.func_70690_d(new PotionEffect(Potion.field_76438_s.field_76415_H, 3 * healed * 20, 2));
            } else if (subItem == this.itemSleepingApple) {
                player.func_71024_bL().func_75122_a(edible.healAmount, edible.saturationModifier);
                if (player.field_71093_bK == 0 && !world.field_72995_K && !WorldProviderDreamWorld.getPlayerIsGhost(player)) {
                    WorldProviderDreamWorld.sendPlayerToSpiritWorld(player, 1.0);
                    itemstack.field_77994_a = 0;
                    world.func_72956_a((Entity)player, "random.burp", 0.5f, world.field_73012_v.nextFloat() * 0.1f + 0.9f);
                    return player.func_71045_bC() != null ? player.func_71045_bC() : itemstack;
                }
            } else {
                player.func_71024_bL().func_75122_a(edible.healAmount, edible.saturationModifier);
            }
            world.func_72956_a((Entity)player, "random.burp", 0.5f, world.field_73012_v.nextFloat() * 0.1f + 0.9f);
        } else if (subItem instanceof Drinkable) {
            if (this.itemDemonHeart.isMatch(itemstack) && player.func_70093_af()) {
                return itemstack;
            }
            if (!player.field_71075_bZ.field_75098_d) {
                --itemstack.field_77994_a;
                if (itemstack.field_77994_a <= 0) {
                    itemstack.field_77994_a = 0;
                    player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
                }
            }
            Drinkable drinkable = (Drinkable)subItem;
            for (PotionEffect effect : drinkable.effects) {
                player.func_70690_d(new PotionEffect(effect));
            }
            if (this.itemDemonHeart.isMatch(itemstack)) {
                player.func_70015_d(2640);
            } else if (this.itemBrewGrotesque.isMatch(itemstack)) {
                if (!world.field_72995_K) {
                    Infusion.getNBT((Entity)player).func_74768_a("witcheryGrotesque", 1200);
                    Witchery.packetPipeline.sendToDimension(new PacketPlayerStyle(player), player.field_71093_bK);
                }
            } else if (this.itemBrewOfSleeping.isMatch(itemstack)) {
                if (player.field_71093_bK == 0 && !world.field_72995_K && !WorldProviderDreamWorld.getPlayerIsGhost(player)) {
                    WorldProviderDreamWorld.sendPlayerToSpiritWorld(player, 0.998);
                    itemstack.field_77994_a = 0;
                    world.func_72956_a((Entity)player, "random.burp", 0.5f, world.field_73012_v.nextFloat() * 0.1f + 0.9f);
                    return player.func_71045_bC() != null ? player.func_71045_bC() : itemstack;
                }
            } else if (this.itemPurifiedMilk.isMatch(itemstack)) {
                Collection c;
                if (!world.field_72995_K && world.field_73012_v.nextInt(2) == 0 && (c = player.func_70651_bq()) != null && !c.isEmpty()) {
                    PotionEffect effect;
                    Object[] objs = c.toArray();
                    int itemIndex = world.field_73012_v.nextInt(c.size());
                    effect = (PotionEffect)objs[itemIndex];
                    player.func_82170_o(effect.func_76456_a());
                }
            } else if (this.itemBrewOfTheDepths.isMatch(itemstack)) {
                if (!world.field_72995_K) {
                    Infusion.getNBT((Entity)player).func_74768_a("witcheryDepths", 300);
                }
            } else if (this.itemCreeperHeart.isMatch(itemstack)) {
                if (!world.field_72995_K) {
                    if (Config.instance().allowExplodingCreeperHearts) {
                        world.func_72876_a((Entity)player, player.field_70165_t, player.field_70163_u, player.field_70161_v, 3.0f, true);
                    } else {
                        world.func_72876_a((Entity)player, player.field_70165_t, player.field_70163_u, player.field_70161_v, 1.0f, false);
                    }
                }
            } else if (this.itemFrozenHeart.isMatch(itemstack)) {
                if (!world.field_72995_K) {
                    PlayerEffects.onDeath(player);
                }
            } else {
                drinkable.onDrunk(world, player, itemstack);
            }
            world.func_72956_a((Entity)player, "random.burp", 0.5f, world.field_73012_v.nextFloat() * 0.1f + 0.9f);
        }
        return itemstack;
    }

    public int func_77626_a(ItemStack itemstack) {
        SubItem subItem = this.subItems.get(itemstack.func_77960_j());
        if (subItem instanceof Edible || subItem instanceof Drinkable) {
            return 32;
        }
        if (this.itemWaystoneBound.isMatch(itemstack)) {
            return 1200;
        }
        if (this.itemContractTorment.isMatch(itemstack)) {
            return 1200;
        }
        if (this.itemSeerStone.isMatch(itemstack)) {
            return 1200;
        }
        return super.func_77626_a(itemstack);
    }

    public EnumAction func_77661_b(ItemStack itemstack) {
        SubItem subItem = this.subItems.get(itemstack.func_77960_j());
        if (subItem instanceof Edible) {
            return EnumAction.eat;
        }
        if (subItem instanceof Drinkable) {
            return ((Drinkable)subItem).useAction;
        }
        if (this.itemContractTorment.isMatch(itemstack)) {
            return EnumAction.bow;
        }
        if (this.itemSeerStone.isMatch(itemstack)) {
            return EnumAction.bow;
        }
        return super.func_77661_b(itemstack);
    }

    public void onUsingTick(ItemStack stack, EntityPlayer player, int countdown) {
        World world = player.field_70170_p;
        int elapsedTicks = this.func_77626_a(stack) - countdown;
        if (!world.field_72995_K && player instanceof EntityPlayerMP) {
            if (this.itemWaystoneBound.isMatch(stack)) {
                if (elapsedTicks % 20 == 0) {
                    if (elapsedTicks == 0) {
                        NBTTagCompound tag = stack.func_77978_p();
                        if (tag != null && tag.func_74764_b("PosX") && tag.func_74764_b("PosY") && tag.func_74764_b("PosZ") && tag.func_74764_b("PosD")) {
                            int newX = tag.func_74762_e("PosX");
                            int newY = tag.func_74762_e("PosY");
                            int newZ = tag.func_74762_e("PosZ");
                            int newD = tag.func_74762_e("PosD");
                            EntityEye eye = new EntityEye(world);
                            eye.func_70012_b(newX, newY, newZ, player.field_70177_z, 90.0f);
                            world.func_72838_d((Entity)eye);
                            Witchery.packetPipeline.sendTo((IMessage)new PacketCamPos(true, elapsedTicks == 0, (Entity)eye), (EntityPlayerMP)player);
                        }
                    } else {
                        Witchery.packetPipeline.sendTo((IMessage)new PacketCamPos(true, false, null), (EntityPlayerMP)player);
                    }
                }
            } else if (this.itemContractTorment.isMatch(stack)) {
                if (!world.field_72995_K) {
                    if (elapsedTicks == 0 || elapsedTicks == 40) {
                        if (Infusion.aquireEnergy(world, player, 10, true)) {
                            if (elapsedTicks > 0 || this.circleNear(world, player)) {
                                SoundEffect.MOB_BLAZE_DEATH.playAtPlayer(world, player);
                            } else {
                                SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
                                ChatUtil.sendTranslated(EnumChatFormatting.RED, (ICommandSender)player, "item.witchery:ingredient.contractTorment.nostones", new Object[0]);
                                player.func_71041_bz();
                            }
                        }
                    } else if (elapsedTicks == 80 || elapsedTicks == 120) {
                        if (Infusion.aquireEnergy(world, player, 10, true)) {
                            ParticleEffect.MOB_SPELL.send(SoundEffect.MOB_BLAZE_DEATH, (Entity)player, 1.0, 2.0, 16);
                        }
                    } else if (elapsedTicks == 160 || elapsedTicks == 200 || elapsedTicks == 240) {
                        if (Infusion.aquireEnergy(world, player, 10, true)) {
                            ParticleEffect.MOB_SPELL.send(SoundEffect.MOB_BLAZE_DEATH, (Entity)player, 1.0, 2.0, 16);
                            ParticleEffect.FLAME.send(SoundEffect.NONE, (Entity)player, 1.0, 2.0, 16);
                        }
                    } else if (elapsedTicks == 280 && Infusion.aquireEnergy(world, player, 10, true)) {
                        if (this.circleNear(world, player)) {
                            ParticleEffect.MOB_SPELL.send(SoundEffect.NONE, (Entity)player, 1.0, 2.0, 16);
                            ParticleEffect.FLAME.send(SoundEffect.NONE, (Entity)player, 1.0, 2.0, 16);
                            ParticleEffect.FLAME.send(SoundEffect.NONE, (Entity)player, 1.0, 2.0, 16);
                            player.func_71041_bz();
                            EntityCreature living = InfusionInfernal.spawnCreature(world, EntityLordOfTorment.class, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v, null, 2, 4, ParticleEffect.FLAME, SoundEffect.MOB_ENDERDRAGON_GROWL);
                            if (living != null) {
                                if (!player.field_71075_bZ.field_75098_d) {
                                    --stack.field_77994_a;
                                    if (stack.field_77994_a <= 0) {
                                        player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
                                    }
                                }
                                living.func_110163_bv();
                                world.func_72885_a((Entity)living, living.field_70165_t, living.field_70163_u + (double)living.func_70047_e(), living.field_70161_v, 7.0f, false, world.func_82736_K().func_82766_b("mobGriefing"));
                            } else {
                                SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
                            }
                        } else {
                            SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
                            player.func_71041_bz();
                        }
                    }
                }
            } else if (this.itemSeerStone.isMatch(stack)) {
                EntityCovenWitch.summonCoven(world, player, new Coord((Entity)player), elapsedTicks);
            }
        }
    }

    private boolean circleNear(World world, EntityPlayer player) {
        int midX = MathHelper.func_76128_c((double)player.field_70165_t);
        int midY = MathHelper.func_76128_c((double)player.field_70163_u);
        int midZ = MathHelper.func_76128_c((double)player.field_70161_v);
        int[][] PATTERN = new int[][]{{0, 0, 0, 0, 4, 3, 4, 0, 0, 0, 0}, {0, 0, 4, 3, 1, 1, 1, 3, 4, 0, 0}, {0, 4, 1, 1, 1, 1, 1, 1, 1, 4, 0}, {0, 3, 1, 1, 1, 1, 1, 1, 1, 3, 0}, {4, 1, 1, 1, 2, 2, 2, 1, 1, 1, 4}, {3, 1, 1, 1, 2, 1, 2, 1, 1, 1, 3}, {4, 1, 1, 1, 2, 2, 2, 1, 1, 1, 4}, {0, 3, 1, 1, 1, 1, 1, 1, 1, 3, 0}, {0, 4, 1, 1, 1, 1, 1, 1, 1, 4, 0}, {0, 0, 4, 3, 1, 1, 1, 3, 4, 0, 0}, {0, 0, 0, 0, 4, 3, 4, 0, 0, 0, 0}};
        int offsetZ = (PATTERN.length - 1) / 2;
        for (int z = 0; z < PATTERN.length - 1; ++z) {
            int worldZ = midZ - offsetZ + z;
            int offsetX = (PATTERN[z].length - 1) / 2;
            for (int x = 0; x < PATTERN[z].length; ++x) {
                int worldX = midX - offsetX + x;
                int value = PATTERN[PATTERN.length - 1 - z][x];
                if (value == 0 || this.isPost(world, worldX, midY, worldZ, value == 2 || value == 4, value == 4, value == 3 || value == 4)) continue;
                return false;
            }
        }
        return true;
    }

    private boolean isPost(World world, int x, int y, int z, boolean bottomSolid, boolean midSolid, boolean topSolid) {
        Block blockBelow = BlockUtil.getBlock(world, x, y - 1, z);
        Block blockBottom = BlockUtil.getBlock(world, x, y, z);
        Block blockMid = BlockUtil.getBlock(world, x, y + 1, z);
        Block blockTop = BlockUtil.getBlock(world, x, y + 2, z);
        Block blockAbove = BlockUtil.getBlock(world, x, y + 3, z);
        if (blockBelow == null || !blockBelow.func_149688_o().func_76220_a()) {
            return false;
        }
        if (bottomSolid ? blockBottom == null || !blockBottom.func_149688_o().func_76220_a() : blockBottom != null && blockBottom.func_149688_o().func_76220_a()) {
            return false;
        }
        if (midSolid ? blockMid == null || !blockMid.func_149688_o().func_76220_a() : blockMid != null && blockMid.func_149688_o().func_76220_a()) {
            return false;
        }
        if (topSolid ? blockTop == null || !blockTop.func_149688_o().func_76220_a() : blockTop != null && blockTop.func_149688_o().func_76220_a()) {
            return false;
        }
        return blockAbove == null || !blockAbove.func_149688_o().func_76220_a();
    }

    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        Block block = BlockUtil.getBlock(world, x, y, z);
        if (this.itemWaystoneBound.isMatch(stack) && block == Witchery.Blocks.CRYSTAL_BALL) {
            if (!world.field_72995_K && BlockCrystalBall.tryConsumePower(world, player, x, y, z)) {
                NBTTagCompound tag = stack.func_77978_p();
                if (tag != null && tag.func_74764_b("PosX") && tag.func_74764_b("PosY") && tag.func_74764_b("PosZ") && tag.func_74764_b("PosD")) {
                    int newX = tag.func_74762_e("PosX");
                    int newY = tag.func_74762_e("PosY");
                    int newZ = tag.func_74762_e("PosZ");
                    int newD = tag.func_74762_e("PosD");
                    double MAX_DISTANCE = 22500.0;
                    if (newD == player.field_71093_bK && player.func_70092_e((double)newX, (double)newY, (double)newZ) <= 22500.0) {
                        player.func_71008_a(stack, this.func_77626_a(stack));
                    } else {
                        SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
                    }
                } else {
                    SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
                }
            } else if (world.field_72995_K) {
                player.func_71008_a(stack, this.func_77626_a(stack));
            }
            return !world.field_72995_K;
        }
        return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }

    public void func_77615_a(ItemStack stack, World world, EntityPlayer player, int countdown) {
        if (!world.field_72995_K && this.itemWaystoneBound.isMatch(stack) && player instanceof EntityPlayerMP) {
            Witchery.packetPipeline.sendTo((IMessage)new PacketCamPos(false, false, null), (EntityPlayerMP)player);
        }
    }

    public boolean isBook(ItemStack itemstack) {
        return this.itemBookOven.isMatch(itemstack) || this.itemBookDistilling.isMatch(itemstack) || this.itemBookCircleMagic.isMatch(itemstack) || this.itemBookInfusions.isMatch(itemstack) || this.itemBookHerbology.isMatch(itemstack) || this.itemBookBiomes.isMatch(itemstack) || this.itemBookWands.isMatch(itemstack) || this.itemBookBurning.isMatch(itemstack);
    }

    public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer player) {
        SubItem subItem = this.subItems.get(itemstack.func_77960_j());
        if (this.isBook(itemstack)) {
            this.openWitchcraftBook(world, player, itemstack);
        } else if (this.itemWolfsbane.isMatch(itemstack)) {
            MovingObjectPosition mop;
            if (!world.field_72995_K && (mop = InfusionOtherwhere.raytraceEntities(world, player, true, 2.0)) != null && mop.field_72308_g != null) {
                if (CreatureUtil.isWerewolf(mop.field_72308_g, true)) {
                    ParticleEffect.FLAME.send(SoundEffect.WITCHERY_MOB_WOLFMAN_HOWL, mop.field_72308_g, 0.5, 1.5, 16);
                } else {
                    SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
                }
                --itemstack.field_77994_a;
                if (itemstack.field_77994_a <= 0) {
                    itemstack.field_77994_a = 0;
                    player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
                }
            }
        } else if (subItem instanceof Edible) {
            if (player.func_71043_e(false) || ((Edible)subItem).eatAnyTime) {
                player.func_71008_a(itemstack, this.func_77626_a(itemstack));
            }
        } else if (subItem instanceof Drinkable) {
            player.func_71008_a(itemstack, this.func_77626_a(itemstack));
        } else if (this.itemContractTorment.isMatch(itemstack)) {
            player.func_71008_a(itemstack, this.func_77626_a(itemstack));
        } else if (subItem instanceof Brew) {
            this.throwBrew(itemstack, world, player, subItem);
        } else if (this.itemQuicklime.isMatch(itemstack)) {
            this.throwBrew(itemstack, world, player, this.itemQuicklime);
        } else if (this.itemNecroStone.isMatch(itemstack)) {
            this.useNecroStone(world, player, itemstack);
        } else if (this.itemBatBall.isMatch(itemstack)) {
            --itemstack.field_77994_a;
            if (itemstack.field_77994_a <= 0) {
                player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
            }
            if (!world.field_72995_K) {
                EntityItem item = new EntityItem(world, player.field_70165_t, player.field_70163_u + 1.3, player.field_70161_v, this.itemBatBall.createStack());
                item.field_145804_b = 5;
                item.func_70012_b(player.field_70165_t, player.field_70163_u + (double)player.func_70047_e(), player.field_70161_v, player.field_70177_z, player.field_70125_A);
                item.field_70165_t -= (double)(MathHelper.func_76134_b((float)(item.field_70177_z / 180.0f * (float)Math.PI)) * 0.16f);
                item.field_70163_u -= (double)0.1f;
                item.field_70161_v -= (double)(MathHelper.func_76126_a((float)(item.field_70177_z / 180.0f * (float)Math.PI)) * 0.16f);
                item.func_70107_b(item.field_70165_t, item.field_70163_u, item.field_70161_v);
                item.field_70129_M = 0.0f;
                float f = 0.4f;
                item.field_70159_w = -MathHelper.func_76126_a((float)(item.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(item.field_70125_A / 180.0f * (float)Math.PI)) * f;
                item.field_70179_y = MathHelper.func_76134_b((float)(item.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(item.field_70125_A / 180.0f * (float)Math.PI)) * f;
                item.field_70181_x = -MathHelper.func_76126_a((float)((item.field_70125_A + 0.0f) / 180.0f * (float)Math.PI)) * f;
                this.setThrowableHeading(item, item.field_70159_w, item.field_70181_x, item.field_70179_y, 1.0f, 1.0f);
                world.func_72838_d((Entity)item);
            }
        } else if (this.itemSeerStone.isMatch(itemstack)) {
            this.useSeerStone(world, player, itemstack);
        } else if (this.itemIcyNeedle.isMatch(itemstack)) {
            this.useIcyNeedle(world, player, itemstack);
            return player.func_71045_bC() != null ? player.func_71045_bC() : itemstack;
        }
        return super.func_77659_a(itemstack, world, player);
    }

    public void setThrowableHeading(EntityItem item, double par1, double par3, double par5, float par7, float par8) {
        float f2 = MathHelper.func_76133_a((double)(par1 * par1 + par3 * par3 + par5 * par5));
        par1 /= (double)f2;
        par3 /= (double)f2;
        par5 /= (double)f2;
        par1 += item.field_70170_p.field_73012_v.nextGaussian() * (double)0.0075f * (double)par8;
        par3 += item.field_70170_p.field_73012_v.nextGaussian() * (double)0.0075f * (double)par8;
        par5 += item.field_70170_p.field_73012_v.nextGaussian() * (double)0.0075f * (double)par8;
        item.field_70159_w = par1 *= (double)par7;
        item.field_70181_x = par3 *= (double)par7;
        item.field_70179_y = par5 *= (double)par7;
        float f3 = MathHelper.func_76133_a((double)(par1 * par1 + par5 * par5));
        item.field_70126_B = item.field_70177_z = (float)(Math.atan2(par1, par5) * 180.0 / Math.PI);
        item.field_70127_C = item.field_70125_A = (float)(Math.atan2(par3, f3) * 180.0 / Math.PI);
    }

    private void useIcyNeedle(World world, EntityPlayer player, ItemStack itemstack) {
        if (!player.field_71075_bZ.field_75098_d) {
            --itemstack.field_77994_a;
            if (itemstack.field_77994_a <= 0) {
                itemstack.field_77994_a = 0;
                player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
            }
        }
        if (world.field_73011_w.field_76574_g == Config.instance().dimensionDreamID) {
            WorldProviderDreamWorld.returnPlayerToOverworld(player);
            itemstack.field_77994_a = 0;
        } else if (WorldProviderDreamWorld.getPlayerIsGhost(player)) {
            WorldProviderDreamWorld.returnGhostPlayerToSpiritWorld(player);
            itemstack.field_77994_a = 0;
        } else {
            player.func_70097_a(DamageSource.func_76365_a((EntityPlayer)player), 1.0f);
        }
    }

    public void throwBrew(ItemStack itemstack, World world, EntityPlayer player) {
        if (itemstack != null && itemstack.func_77973_b() == this) {
            SubItem subItem = this.subItems.get(itemstack.func_77960_j());
            this.throwBrew(itemstack, world, player, subItem);
        }
    }

    private void throwBrew(ItemStack itemstack, World world, EntityPlayer player, SubItem item) {
        if (!player.field_71075_bZ.field_75098_d) {
            --itemstack.field_77994_a;
        }
        world.func_72956_a((Entity)player, "random.bow", 0.5f, 0.4f / (field_77697_d.nextFloat() * 0.4f + 0.8f));
        if (!world.field_72995_K) {
            world.func_72838_d((Entity)new EntityWitchProjectile(world, (EntityLivingBase)player, item));
        }
    }

    private void openWitchcraftBook(World world, EntityPlayer player, ItemStack itemstack) {
        int posX = MathHelper.func_76128_c((double)player.field_70165_t);
        int posY = MathHelper.func_76128_c((double)player.field_70163_u);
        int posZ = MathHelper.func_76128_c((double)player.field_70161_v);
        player.openGui((Object)Witchery.instance, 1, world, posX, posY, posZ);
    }

    public boolean func_77648_a(ItemStack itemstack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float par8, float par9, float par10) {
        if (this.itemMutandis.isMatch(itemstack)) {
            return this.useMutandis(false, itemstack, player, world, posX, posY, posZ);
        }
        if (this.itemAnnointingPaste.isMatch(itemstack)) {
            return this.useAnnointingPaste(itemstack, player, world, posX, posY, posZ);
        }
        if (this.itemKobolditePentacle.isMatch(itemstack)) {
            if (world.func_147439_a(posX, posY, posZ) == Witchery.Blocks.ALTAR && side == 1 && world.func_147439_a(posX, posY + 1, posZ) == Blocks.field_150350_a) {
                BlockPlacedItem.placeItemInWorld(itemstack, player, world, posX, posY + 1, posZ);
                player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
                return !world.field_72995_K;
            }
            return super.func_77648_a(itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
        }
        if (this.itemMutandisExtremis.isMatch(itemstack)) {
            return this.useMutandis(true, itemstack, player, world, posX, posY, posZ);
        }
        if (this.itemChaliceEmpty.isMatch(itemstack) || this.itemChaliceFull.isMatch(itemstack)) {
            return this.placeBlock(Witchery.Blocks.CHALICE, itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
        }
        if (this.itemCandelabra.isMatch(itemstack)) {
            return this.placeBlock(Witchery.Blocks.CANDELABRA, itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
        }
        if (this.itemBroomEnchanted.isMatch(itemstack)) {
            return this.placeBroom(itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
        }
        if (this.subItems.get(itemstack.func_77960_j()) instanceof DreamWeave) {
            return this.placeDreamCatcher(world, player, itemstack, posX, posY, posZ, side);
        }
        if (this.itemDoorRowan.isMatch(itemstack)) {
            return this.placeDoor(world, player, itemstack, posX, posY, posZ, side, Witchery.Blocks.DOOR_ROWAN);
        }
        if (this.itemDoorAlder.isMatch(itemstack)) {
            return this.placeDoor(world, player, itemstack, posX, posY, posZ, side, Witchery.Blocks.DOOR_ALDER);
        }
        if (this.itemDoorIce.isMatch(itemstack)) {
            return this.placeDoor(world, player, itemstack, posX, posY, posZ, side, Witchery.Blocks.PERPETUAL_ICE_DOOR);
        }
        if (this.itemSubduedSpirit.isMatch(itemstack) || this.itemSubduedSpiritVillage.isMatch(itemstack)) {
            return this.useSubduedSpirit(world, player, itemstack, posX, posY, posZ, side);
        }
        if (this.itemSeedsTreefyd.isMatch(itemstack)) {
            return this.placeTreefyd(world, player, itemstack, posX, posY, posZ, side);
        }
        if (this.itemBinkyHead.isMatch(itemstack)) {
            return this.placeBinky(world, player, itemstack, posX, posY, posZ, side);
        }
        if (this.itemInfernalBlood.isMatch(itemstack)) {
            return this.placeInfernalBlood(world, player, itemstack, posX, posY, posZ, side);
        }
        if (this.itemDemonHeart.isMatch(itemstack) && player.func_70093_af()) {
            return this.placeBlock(Witchery.Blocks.DEMON_HEART, itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
        }
        if (this.itemBoneNeedle.isMatch(itemstack) && ExtendedPlayer.get(player).isVampire()) {
            ExtendedPlayer playerEx;
            Block block = world.func_147439_a(posX, posY, posZ);
            if (block == Blocks.field_150325_L && world.func_72805_g(posX, posY, posZ) == 0 && (playerEx = ExtendedPlayer.get(player)).getVampireLevel() >= 4 && playerEx.decreaseBloodPower(125, true)) {
                world.func_147449_b(posX, posY, posZ, Witchery.Blocks.BLOODED_WOOL);
                ParticleEffect.REDDUST.send(SoundEffect.WITCHERY_RANDOM_DRINK, world, (double)posX + 0.5, (double)posY + 0.5, (double)posZ + 0.5, 1.0, 1.0, 16);
                return true;
            }
            SoundEffect.NOTE_SNARE.playOnlyTo(player);
            return true;
        }
        return super.func_77648_a(itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
    }

    private boolean placeBinky(World world, EntityPlayer player, ItemStack itemstack, int posX, int posY, int posZ, int side) {
        Material material;
        if (side != 1) {
            return false;
        }
        if ((material = world.func_147439_a(posX, ++posY, posZ).func_149688_o()) == null || !material.func_76220_a()) {
            if (!world.field_72995_K) {
                EntityDeathsHorse horse = new EntityDeathsHorse(world);
                horse.func_110234_j(true);
                horse.func_110214_p(4);
                horse.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.3);
                horse.func_110163_bv();
                horse.func_94058_c(Witchery.resource("item.witchery.horseofdeath.customname"));
                horse.func_70012_b(0.5 + (double)posX, 0.01 + (double)posY, 0.5 + (double)posZ, 0.0f, 0.0f);
                NBTTagCompound nbtHorse = horse.getEntityData();
                if (nbtHorse != null) {
                    nbtHorse.func_74757_a("WITCIsBinky", true);
                }
                ParticleEffect.INSTANT_SPELL.send(SoundEffect.NONE, world, 0.5 + (double)posX, posY, 0.5 + (double)posZ, 1.0, 1.0, 16);
                world.func_72838_d((Entity)horse);
            }
            --itemstack.field_77994_a;
        }
        return true;
    }

    private boolean placeInfernalBlood(World world, EntityPlayer player, ItemStack itemstack, int posX, int posY, int posZ, int side) {
        Block block = world.func_147439_a(posX, posY, posZ);
        int meta = world.func_72805_g(posX, posY, posZ);
        if (block == Witchery.Blocks.WICKER_BUNDLE && BlockWickerBundle.limitToValidMetadata(meta) == 0) {
            if (!world.field_72995_K) {
                int uses = 5;
                for (int y = posY - 1; y <= posY + 1 && uses > 0; ++y) {
                    for (int x = posX - 1; x <= posX + 1 && uses > 0; ++x) {
                        for (int z = posZ - 1; z <= posZ + 1 && uses > 0; ++z) {
                            Block b = world.func_147439_a(x, y, z);
                            int m = world.func_72805_g(x, y, z);
                            if (b != Witchery.Blocks.WICKER_BUNDLE || BlockWickerBundle.limitToValidMetadata(m) != 0) continue;
                            world.func_147465_d(x, y, z, b, m | 1, 3);
                            --uses;
                        }
                    }
                }
            }
            --itemstack.field_77994_a;
            return true;
        }
        return false;
    }

    private boolean placeTreefyd(World world, EntityPlayer player, ItemStack itemstack, int posX, int posY, int posZ, int side) {
        if (side != 1) {
            return false;
        }
        Material material = world.func_147439_a(posX, ++posY, posZ).func_149688_o();
        if (Blocks.field_150329_H.func_149718_j(world, posX, posY, posZ) && (material == null || !material.func_76220_a())) {
            if (!world.field_72995_K) {
                world.func_147465_d(posX, posY, posZ, (Block)Blocks.field_150329_H, 1, 3);
                EntityTreefyd entity = new EntityTreefyd(world);
                entity.func_70012_b(0.5 + (double)posX, posY, 0.5 + (double)posZ, 0.0f, 0.0f);
                entity.func_110161_a(null);
                entity.func_110163_bv();
                entity.setOwner(player.func_70005_c_());
                world.func_72838_d((Entity)entity);
                ParticleEffect.SLIME.send(SoundEffect.MOB_SILVERFISH_KILL, (Entity)entity, 1.0, 2.0, 16);
                ParticleEffect.EXPLODE.send(SoundEffect.NONE, (Entity)entity, 1.0, 2.0, 16);
            }
            --itemstack.field_77994_a;
        }
        return true;
    }

    private boolean useSeerStone(World world, EntityPlayer player, ItemStack stack) {
        block5: {
            block4: {
                block6: {
                    if (!player.func_70093_af()) break block4;
                    if (world.field_72995_K) break block5;
                    double MAX_TARGET_RANGE = 3.0;
                    MovingObjectPosition mop = InfusionOtherwhere.doCustomRayTrace(world, player, true, 3.0);
                    if (mop == null) break block6;
                    switch (mop.field_72313_a) {
                        case ENTITY: {
                            if (mop.field_72308_g instanceof EntityPlayer) {
                                this.readPlayer(player, (EntityPlayer)mop.field_72308_g);
                                return true;
                            }
                            break block5;
                        }
                        default: {
                            this.readPlayer(player, player);
                            break;
                        }
                    }
                    break block5;
                }
                this.readPlayer(player, player);
                break block5;
            }
            player.func_71008_a(stack, this.func_77626_a(stack));
        }
        return true;
    }

    private void readPlayer(EntityPlayer player, EntityPlayer targetPlayer) {
        int timeRemaining;
        String reading = "";
        NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)targetPlayer);
        reading = nbtPlayer != null && nbtPlayer.func_74764_b("WITCManifestDuration") ? ((timeRemaining = nbtPlayer.func_74762_e("WITCManifestDuration")) > 0 ? reading + String.format(Witchery.resource("item.witchery:ingredient.seerstone.manifestationtime"), Integer.valueOf(timeRemaining).toString()) + " " : reading + Witchery.resource("item.witchery:ingredient.seerstone.nomanifestationtime") + " ") : reading + Witchery.resource("item.witchery:ingredient.seerstone.nomanifestationtime") + " ";
        String familiarName = Familiar.getFamiliarName(targetPlayer);
        reading = familiarName != null && !familiarName.isEmpty() ? reading + String.format(Witchery.resource("item.witchery:ingredient.seerstone.familiar"), familiarName) + " " : reading + Witchery.resource("item.witchery:ingredient.seerstone.nofamiliar") + " ";
        int covenSize = EntityCovenWitch.getCovenSize(targetPlayer);
        reading = covenSize > 0 ? reading + String.format(Witchery.resource("item.witchery:ingredient.seerstone.covensize"), Integer.valueOf(covenSize).toString()) + " " : reading + Witchery.resource("item.witchery:ingredient.seerstone.nocoven") + " ";
        String spellKnowledge = SymbolEffect.getKnowledge(targetPlayer);
        reading = !spellKnowledge.isEmpty() ? reading + String.format(Witchery.resource("item.witchery:ingredient.seerstone.knownspells"), spellKnowledge) + " " : reading + Witchery.resource("item.witchery:ingredient.seerstone.nospells") + " ";
        ExtendedPlayer playerEx = ExtendedPlayer.get(targetPlayer);
        if (playerEx != null) {
            int bottlingSkill = playerEx.getSkillPotionBottling();
            reading = reading + String.format(Witchery.resource("item.witchery:ingredient.seerstone.bottlingskill"), Integer.valueOf(bottlingSkill).toString()) + " ";
        }
        if (nbtPlayer != null && (nbtPlayer.func_74764_b("witcheryCursed") || nbtPlayer.func_74764_b("witcheryInsanity") || nbtPlayer.func_74764_b("witcherySinking") || nbtPlayer.func_74764_b("witcheryOverheating") || nbtPlayer.func_74764_b("witcheryWakingNightmare"))) {
            int level;
            if (nbtPlayer.func_74764_b("witcheryCursed")) {
                level = nbtPlayer.func_74762_e("witcheryCursed");
                if (!reading.isEmpty()) {
                    reading = reading + ", ";
                }
                reading = reading + String.format(Witchery.resource("witchery.item.seerstone.misfortune"), level);
            }
            if (nbtPlayer.func_74764_b("witcheryInsanity")) {
                level = nbtPlayer.func_74762_e("witcheryInsanity");
                if (!reading.isEmpty()) {
                    reading = reading + ", ";
                }
                reading = reading + String.format(Witchery.resource("witchery.item.seerstone.insanity"), level);
            }
            if (nbtPlayer.func_74764_b("witcherySinking")) {
                level = nbtPlayer.func_74762_e("witcherySinking");
                if (!reading.isEmpty()) {
                    reading = reading + ", ";
                }
                reading = reading + String.format(Witchery.resource("witchery.item.seerstone.sinking"), level);
            }
            if (nbtPlayer.func_74764_b("witcheryOverheating")) {
                level = nbtPlayer.func_74762_e("witcheryOverheating");
                if (!reading.isEmpty()) {
                    reading = reading + ", ";
                }
                reading = reading + String.format(Witchery.resource("witchery.item.seerstone.overheating"), level);
            }
            if (nbtPlayer.func_74764_b("witcheryWakingNightmare")) {
                level = nbtPlayer.func_74762_e("witcheryWakingNightmare");
                if (!reading.isEmpty()) {
                    reading = reading + ", ";
                }
                reading = reading + String.format(Witchery.resource("witchery.item.seerstone.nightmare"), level);
            }
        } else {
            reading = reading + Witchery.resource("witchery.item.seerstone.notcursed");
        }
        ChatUtil.sendPlain(EnumChatFormatting.BLUE, (ICommandSender)player, reading);
    }

    private boolean useSubduedSpirit(World world, EntityPlayer player, ItemStack itemstack, int x, int y, int z, int side) {
        if (!world.field_72995_K) {
            EntityCreature creature = Infusion.spawnCreature(world, EntitySpirit.class, x, y, z, null, 0, 0, ParticleEffect.INSTANT_SPELL, null);
            if (creature != null) {
                EntitySpirit spirit = (EntitySpirit)creature;
                creature.func_110163_bv();
                if (this.itemSubduedSpiritVillage.isMatch(itemstack)) {
                    spirit.setTarget("Village", 1);
                }
                if (!player.field_71075_bZ.field_75098_d && --itemstack.field_77994_a == 0) {
                    player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
                    if (player instanceof EntityPlayerMP) {
                        ((EntityPlayerMP)player).func_71120_a(player.field_71069_bz);
                    }
                }
            }
            return true;
        }
        return false;
    }

    private boolean useNecroStone(World world, EntityPlayer player, ItemStack itemstack) {
        if (world.field_72995_K) {
            return false;
        }
        double MAX_TARGET_RANGE = 15.0;
        MovingObjectPosition mop = InfusionOtherwhere.doCustomRayTrace(world, player, true, 15.0);
        if (mop != null) {
            switch (mop.field_72313_a) {
                case ENTITY: {
                    if (!(mop.field_72308_g instanceof EntityLivingBase)) break;
                    if (!player.func_70093_af()) {
                        EntityLivingBase targetEntityLivingBase = (EntityLivingBase)mop.field_72308_g;
                        int r = 50;
                        int minionCount = 0;
                        AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(player.field_70165_t - 50.0), (double)(player.field_70163_u - 15.0), (double)(player.field_70161_v - 50.0), (double)(player.field_70165_t + 50.0), (double)(player.field_70163_u + 15.0), (double)(player.field_70161_v + 50.0));
                        for (Object obj : world.func_72872_a(EntityLiving.class, bounds)) {
                            EntityLiving nearbyLivingEntity = (EntityLiving)obj;
                            if (nearbyLivingEntity.func_70668_bt() != EnumCreatureAttribute.UNDEAD || !PotionEnslaved.isMobEnslavedBy(nearbyLivingEntity, player)) continue;
                            ++minionCount;
                            EntityUtil.setTarget(nearbyLivingEntity, targetEntityLivingBase);
                        }
                        if (minionCount <= 0) break;
                        Witchery.packetPipeline.sendToAllAround(new PacketParticles(ParticleEffect.CRIT, SoundEffect.MOB_ZOMBIE_DEATH, (Entity)targetEntityLivingBase, 0.5, 2.0), TargetPointUtil.from((Entity)targetEntityLivingBase, 16.0));
                        return true;
                    }
                    if (InfusedBrewEffect.Grave.isActive(player) && InfusedBrewEffect.Grave.tryUseEffect(player, mop)) {
                        Witchery.packetPipeline.sendToAllAround(new PacketParticles(ParticleEffect.MOB_SPELL, SoundEffect.MOB_ZOMBIE_INFECT, mop.field_72308_g, 1.0, 1.0), TargetPointUtil.from(mop.field_72308_g, 16.0));
                        return true;
                    }
                    Witchery.packetPipeline.sendToAllAround(new PacketParticles(ParticleEffect.SMOKE, SoundEffect.NOTE_SNARE, mop.field_72308_g, 1.0, 1.0), TargetPointUtil.from(mop.field_72308_g, 16.0));
                    break;
                }
                case BLOCK: {
                    if (world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d) == Witchery.Blocks.ALLURING_SKULL) {
                        return false;
                    }
                    if (!BlockSide.TOP.isEqual(mop.field_72310_e)) break;
                    int minionCount = 0;
                    int r = 50;
                    AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(player.field_70165_t - 50.0), (double)(player.field_70163_u - 15.0), (double)(player.field_70161_v - 50.0), (double)(player.field_70165_t + 50.0), (double)(player.field_70163_u + 15.0), (double)(player.field_70161_v + 50.0));
                    for (Object obj : world.func_72872_a(EntityLiving.class, bounds)) {
                        EntityCreature creature2;
                        EntityLiving creature = (EntityLiving)obj;
                        EntityCreature entityCreature = creature2 = creature instanceof EntityCreature ? (EntityCreature)creature : null;
                        if (creature.func_70668_bt() != EnumCreatureAttribute.UNDEAD || !PotionEnslaved.isMobEnslavedBy(creature, player)) continue;
                        ++minionCount;
                        creature.func_70624_b(null);
                        creature.func_70604_c(null);
                        if (creature2 != null) {
                            creature2.func_70784_b(null);
                        }
                        if (!(creature instanceof EntitySpider) && creature.func_70661_as().func_75492_a((double)mop.field_72311_b, (double)(mop.field_72312_c + 1), (double)mop.field_72309_d, 1.0) || creature2 == null) continue;
                        creature2.func_70778_a(world.func_72844_a((Entity)creature, mop.field_72311_b, mop.field_72312_c + 1, mop.field_72309_d, 10.0f, true, false, false, true));
                    }
                    if (minionCount <= 0) break;
                    ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_POP, world, 0.5 + (double)mop.field_72311_b, mop.field_72312_c + 1, 0.5 + (double)mop.field_72309_d, 1.0, 1.0, 16);
                    return true;
                }
            }
        }
        SoundEffect.NOTE_SNARE.playAtPlayer(world, player);
        return false;
    }

    private boolean placeDoor(World world, EntityPlayer player, ItemStack itemstack, int posX, int posY, int posZ, int side, Block block) {
        if (side != 1) {
            return false;
        }
        if (player.func_82247_a(posX, ++posY, posZ, side, itemstack) && player.func_82247_a(posX, posY + 1, posZ, side, itemstack)) {
            if (!block.func_149742_c(world, posX, posY, posZ)) {
                return false;
            }
            int i1 = MathHelper.func_76128_c((double)((double)((player.field_70177_z + 180.0f) * 4.0f / 360.0f) - 0.5)) & 3;
            ItemDoor.func_150924_a((World)world, (int)posX, (int)posY, (int)posZ, (int)i1, (Block)block);
            if (!world.field_72995_K && this.itemDoorRowan.isMatch(itemstack)) {
                ItemStack keyStack = Witchery.Items.GENERIC.itemDoorKey.createStack();
                if (!keyStack.func_77942_o()) {
                    keyStack.func_77982_d(new NBTTagCompound());
                }
                NBTTagCompound nbtTag = keyStack.func_77978_p();
                nbtTag.func_74768_a("doorX", posX);
                nbtTag.func_74768_a("doorY", posY);
                nbtTag.func_74768_a("doorZ", posZ);
                nbtTag.func_74768_a("doorD", world.field_73011_w.field_76574_g);
                nbtTag.func_74778_a("doorDN", world.field_73011_w.func_80007_l());
                world.func_72838_d((Entity)new EntityItem(world, player.field_70165_t, player.field_70163_u + 0.5, player.field_70161_v, keyStack));
            }
            --itemstack.field_77994_a;
            return true;
        }
        return false;
    }

    public static void setBlockToClay(World world, int x, int y, int z) {
        Block block = world.func_147439_a(x, y, z);
        Block blockAbove = world.func_147439_a(x, y + 1, z);
        if (block == Blocks.field_150346_d && (blockAbove == Blocks.field_150355_j || blockAbove == Blocks.field_150358_i)) {
            world.func_147449_b(x, y, z, Blocks.field_150435_aG);
            if (!world.field_72995_K) {
                ParticleEffect.INSTANT_SPELL.send(SoundEffect.MOB_SLIME_BIG, world, 0.5 + (double)x, 1.5 + (double)y, 0.5 + (double)z, 1.0, 1.0, 16);
            }
        }
    }

    private boolean useAnnointingPaste(ItemStack stack, EntityPlayer player, World world, int x, int y, int z) {
        if (!world.field_72995_K) {
            Block block = world.func_147439_a(x, y, z);
            int meta = world.func_72805_g(x, y, z);
            if (block == Blocks.field_150383_bp) {
                world.func_147449_b(x, y, z, (Block)Witchery.Blocks.CAULDRON);
                --stack.field_77994_a;
                ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_FIZZ, world, x, y, z, 1.0, 1.0, 16);
                ParticleEffect.LARGE_EXPLODE.send(SoundEffect.RANDOM_LEVELUP, world, x, y, z, 1.0, 1.0, 16);
            }
        }
        return true;
    }

    private boolean useMutandis(boolean extremis, ItemStack itemstack, EntityPlayer player, World world, int posX, int posY, int posZ) {
        if (!world.field_72995_K) {
            Block block = world.func_147439_a(posX, posY, posZ);
            Block blockAbove = world.func_147439_a(posX, posY + 1, posZ);
            if (extremis && (block == Blocks.field_150349_c || block == Blocks.field_150391_bh)) {
                if (world.field_73012_v.nextInt(2) == 0) {
                    world.func_147449_b(posX, posY, posZ, (Block)(block == Blocks.field_150349_c ? Blocks.field_150391_bh : Blocks.field_150349_c));
                }
                ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_FIZZ, world, posX, posY + 1, posZ, 1.0, 1.0, 16);
                --itemstack.field_77994_a;
            } else if (extremis && block == Blocks.field_150346_d && (blockAbove == Blocks.field_150355_j || blockAbove == Blocks.field_150358_i)) {
                if (world.field_73012_v.nextInt(2) == 0) {
                    ItemGeneral.setBlockToClay(world, posX, posY, posZ);
                    ItemGeneral.setBlockToClay(world, posX + 1, posY, posZ);
                    ItemGeneral.setBlockToClay(world, posX - 1, posY, posZ);
                    ItemGeneral.setBlockToClay(world, posX, posY, posZ + 1);
                    ItemGeneral.setBlockToClay(world, posX, posY, posZ - 1);
                } else {
                    ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_FIZZ, world, posX, posY + 1, posZ, 1.0, 1.0, 16);
                }
                --itemstack.field_77994_a;
            } else {
                ArrayList<MutableBlock> list;
                MutableBlock[] blocks;
                int metadata = world.func_72805_g(posX, posY, posZ);
                if (block == Blocks.field_150457_bL && metadata > 0) {
                    blocks = new MutableBlock[]{new MutableBlock(Blocks.field_150457_bL, 1), new MutableBlock(Blocks.field_150457_bL, 2), new MutableBlock(Blocks.field_150457_bL, 3), new MutableBlock(Blocks.field_150457_bL, 4), new MutableBlock(Blocks.field_150457_bL, 5), new MutableBlock(Blocks.field_150457_bL, 6), new MutableBlock(Blocks.field_150457_bL, 7), new MutableBlock(Blocks.field_150457_bL, 8), new MutableBlock(Blocks.field_150457_bL, 9), new MutableBlock(Blocks.field_150457_bL, 10), new MutableBlock(Blocks.field_150457_bL, 11)};
                    list = new ArrayList<MutableBlock>(Arrays.asList(blocks));
                } else {
                    blocks = new MutableBlock[]{new MutableBlock(Blocks.field_150345_g, 0), new MutableBlock(Blocks.field_150345_g, 1), new MutableBlock(Blocks.field_150345_g, 2), new MutableBlock(Blocks.field_150345_g, 3), new MutableBlock(Blocks.field_150345_g, 4), new MutableBlock(Blocks.field_150345_g, 5), new MutableBlock(Witchery.Blocks.SAPLING, 0), new MutableBlock(Witchery.Blocks.SAPLING, 1), new MutableBlock(Witchery.Blocks.SAPLING, 2), new MutableBlock(Witchery.Blocks.EMBER_MOSS, 0), new MutableBlock((Block)Blocks.field_150329_H, 1), new MutableBlock(Blocks.field_150392_bi), new MutableBlock((Block)Blocks.field_150338_P), new MutableBlock((Block)Blocks.field_150337_Q), new MutableBlock((Block)Blocks.field_150328_O, 0), new MutableBlock((Block)Blocks.field_150327_N), new MutableBlock(Witchery.Blocks.SPANISH_MOSS, 1)};
                    list = new ArrayList<MutableBlock>(Arrays.asList(blocks));
                    for (String extra : Config.instance().mutandisExtras) {
                        try {
                            list.add(new MutableBlock(extra));
                        }
                        catch (Throwable ex) {
                            // empty catch block
                        }
                    }
                    if (extremis) {
                        MutableBlock[] extremisBlocks = new MutableBlock[]{new MutableBlock(Blocks.field_150459_bM, -1, Math.min(metadata, 7)), new MutableBlock(Blocks.field_150469_bN, -1, Math.min(metadata, 7)), new MutableBlock(Blocks.field_150464_aj, -1, Math.min(metadata, 7)), new MutableBlock(Blocks.field_150436_aH, -1, Math.min(metadata, 7)), new MutableBlock((Block)Witchery.Blocks.CROP_BELLADONNA, -1, Math.min(metadata, Witchery.Blocks.CROP_BELLADONNA.getNumGrowthStages())), new MutableBlock((Block)Witchery.Blocks.CROP_MANDRAKE, -1, Math.min(metadata, Witchery.Blocks.CROP_MANDRAKE.getNumGrowthStages())), new MutableBlock((Block)Witchery.Blocks.CROP_ARTICHOKE, -1, Math.min(metadata, Witchery.Blocks.CROP_ARTICHOKE.getNumGrowthStages())), new MutableBlock(Blocks.field_150393_bb, -1, Math.min(metadata, 7)), new MutableBlock(Blocks.field_150434_aF), new MutableBlock(Blocks.field_150394_bc, -1, Math.min(metadata, 7)), new MutableBlock(Blocks.field_150388_bm, -1, Math.min(metadata, 3))};
                        list.addAll(Arrays.asList(extremisBlocks));
                    } else if (player.field_71093_bK == Config.instance().dimensionDreamID) {
                        MutableBlock[] spiritBlocks = new MutableBlock[]{new MutableBlock(Blocks.field_150388_bm, -1, 3)};
                        list.addAll(Arrays.asList(spiritBlocks));
                    }
                }
                MutableBlock mutableBlock = new MutableBlock(block, metadata, 0);
                int index = list.indexOf(mutableBlock);
                if (index != -1) {
                    list.remove(index);
                    list.get(world.field_73012_v.nextInt(list.size())).mutate(world, posX, posY, posZ);
                    ParticleEffect.INSTANT_SPELL.send(SoundEffect.RANDOM_FIZZ, world, posX, posY, posZ, 1.0, 1.0, 16);
                    --itemstack.field_77994_a;
                }
            }
        }
        return true;
    }

    private boolean placeBroom(ItemStack itemstack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float par8, float par9, float par10) {
        int i;
        float f = 1.0f;
        float f1 = player.field_70127_C + (player.field_70125_A - player.field_70127_C) * 1.0f;
        float f2 = player.field_70126_B + (player.field_70177_z - player.field_70126_B) * 1.0f;
        double d0 = player.field_70169_q + (player.field_70165_t - player.field_70169_q) * 1.0;
        double d1 = player.field_70167_r + (player.field_70163_u - player.field_70167_r) * 1.0 + 1.62 - (double)player.field_70129_M;
        double d2 = player.field_70166_s + (player.field_70161_v - player.field_70166_s) * 1.0;
        Vec3 vec3 = Vec3.func_72443_a((double)d0, (double)d1, (double)d2);
        float f3 = MathHelper.func_76134_b((float)(-f2 * ((float)Math.PI / 180) - (float)Math.PI));
        float f4 = MathHelper.func_76126_a((float)(-f2 * ((float)Math.PI / 180) - (float)Math.PI));
        float f5 = -MathHelper.func_76134_b((float)(-f1 * ((float)Math.PI / 180)));
        float f6 = MathHelper.func_76126_a((float)(-f1 * ((float)Math.PI / 180)));
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5.0;
        Vec3 vec31 = vec3.func_72441_c((double)f7 * 5.0, (double)f6 * 5.0, (double)f8 * 5.0);
        MovingObjectPosition movingobjectposition = world.func_72901_a(vec3, vec31, true);
        if (movingobjectposition == null) {
            return super.func_77648_a(itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
        }
        Vec3 vec32 = player.func_70676_i(1.0f);
        boolean flag = false;
        float f9 = 1.0f;
        List list = world.func_72839_b((Entity)player, player.field_70121_D.func_72321_a(vec32.field_72450_a * 5.0, vec32.field_72448_b * 5.0, vec32.field_72449_c * 5.0).func_72314_b(1.0, 1.0, 1.0));
        for (i = 0; i < list.size(); ++i) {
            float f10;
            AxisAlignedBB axisalignedbb;
            Entity entity = (Entity)list.get(i);
            if (!entity.func_70067_L() || !(axisalignedbb = entity.field_70121_D.func_72314_b((double)(f10 = entity.func_70111_Y()), (double)f10, (double)f10)).func_72318_a(vec3)) continue;
            flag = true;
        }
        if (flag) {
            return super.func_77648_a(itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
        }
        if (movingobjectposition.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            i = movingobjectposition.field_72311_b;
            int j = movingobjectposition.field_72312_c;
            int k = movingobjectposition.field_72309_d;
            if (world.func_147439_a(i, j, k) == Blocks.field_150433_aE) {
                --j;
            }
            EntityBroom broomEntity = new EntityBroom(world, (float)i + 0.5f, (float)j + 1.0f, (float)k + 0.5f);
            if (itemstack.func_82837_s()) {
                broomEntity.setCustomNameTag(itemstack.func_82833_r());
            }
            this.setBroomEntityColor(broomEntity, itemstack);
            broomEntity.field_70177_z = player.field_70177_z;
            if (!world.func_72945_a((Entity)broomEntity, broomEntity.field_70121_D.func_72314_b(-0.1, -0.1, -0.1)).isEmpty()) {
                super.func_77648_a(itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
            }
            broomEntity.field_70177_z = ((MathHelper.func_76128_c((double)((double)(player.field_70177_z * 4.0f / 360.0f) + 0.5)) & 3) - 1) * 90;
            if (!world.field_72995_K) {
                world.func_72838_d((Entity)broomEntity);
                int l = MathHelper.func_76141_d((float)(broomEntity.field_70177_z * 256.0f / 360.0f));
                Witchery.packetPipeline.sendToAllAround((Packet)new S14PacketEntity.S17PacketEntityLookMove(broomEntity.func_145782_y(), 0, 0, 0, (byte)Math.max(Math.min(l, 255), 0), 0), world, TargetPointUtil.from(broomEntity, 128.0));
            }
            if (!player.field_71075_bZ.field_75098_d) {
                --itemstack.field_77994_a;
            }
        }
        return super.func_77648_a(itemstack, player, world, posX, posY, posZ, side, par8, par9, par10);
    }

    private void setBroomEntityColor(EntityBroom broomEntity, ItemStack itemstack) {
        broomEntity.setBrushColor(this.getBroomItemColor(itemstack));
    }

    public void setBroomItemColor(ItemStack itemstack, int brushColor) {
        if (brushColor >= 0 && brushColor <= 15) {
            if (!itemstack.func_77942_o()) {
                itemstack.func_77982_d(new NBTTagCompound());
            }
            NBTTagCompound nbtTag = itemstack.func_77978_p();
            nbtTag.func_74774_a("BrushColor", Byte.valueOf((byte)brushColor).byteValue());
        }
    }

    public int getBroomItemColor(ItemStack stack) {
        NBTTagCompound nbtTag = stack.func_77978_p();
        if (nbtTag != null && nbtTag.func_74764_b("BrushColor")) {
            return nbtTag.func_74771_c("BrushColor") & 0xF;
        }
        return -1;
    }

    private boolean placeBlock(Block spawnBlock, ItemStack itemstack, EntityPlayer player, World world, int posX, int posY, int posZ, int side, float par8, float par9, float par10) {
        int j1;
        Block block = world.func_147439_a(posX, posY, posZ);
        if (block == Blocks.field_150433_aE && (world.func_72805_g(posX, posY, posZ) & 7) < 1) {
            side = 1;
        } else if (block != Blocks.field_150395_bd && block != Blocks.field_150329_H && block != Blocks.field_150330_I) {
            if (side == 0) {
                --posY;
            } else if (side == 1) {
                ++posY;
            } else if (side == 2) {
                --posZ;
            } else if (side == 3) {
                ++posZ;
            } else if (side == 4) {
                --posX;
            } else if (side == 5) {
                ++posX;
            }
        }
        if (!player.func_82247_a(posX, posY, posZ, side, itemstack)) {
            return false;
        }
        if (itemstack.field_77994_a == 0) {
            return false;
        }
        if (world.func_147472_a(spawnBlock, posX, posY, posZ, false, side, (Entity)null, itemstack) && world.func_147465_d(posX, posY, posZ, spawnBlock, j1 = spawnBlock.func_149660_a(world, posX, posY, posZ, side, par8, par9, par10, 0), 3)) {
            if (world.func_147439_a(posX, posY, posZ) == spawnBlock) {
                BlockChalice.TileEntityChalice tileEntity;
                spawnBlock.func_149689_a(world, posX, posY, posZ, (EntityLivingBase)player, itemstack);
                spawnBlock.func_149714_e(world, posX, posY, posZ, j1);
                if (spawnBlock == Witchery.Blocks.CHALICE && (tileEntity = (BlockChalice.TileEntityChalice)world.func_147438_o(posX, posY, posZ)) != null) {
                    tileEntity.setFilled(this.itemChaliceFull.isMatch(itemstack));
                }
            }
            world.func_72908_a((double)((float)posX + 0.5f), (double)((float)posY + 0.5f), (double)((float)posZ + 0.5f), block.field_149762_H.func_150496_b(), (block.field_149762_H.func_150497_c() + 1.0f) / 2.0f, block.field_149762_H.func_150494_d() * 0.8f);
            --itemstack.field_77994_a;
        }
        return true;
    }

    private boolean placeDreamCatcher(World world, EntityPlayer player, ItemStack itemstack, int posX, int posY, int posZ, int side) {
        if (side == 0) {
            return false;
        }
        if (!world.func_147439_a(posX, posY, posZ).func_149688_o().func_76220_a()) {
            return false;
        }
        if (side == 1) {
            ++posY;
        } else if (side == 2) {
            --posZ;
        } else if (side == 3) {
            ++posZ;
        } else if (side == 4) {
            --posX;
        } else if (side == 5) {
            ++posX;
        }
        if (!player.func_82247_a(posX, posY, posZ, side, itemstack)) {
            return false;
        }
        if (!Witchery.Blocks.DREAM_CATCHER.func_149742_c(world, posX, posY, posZ)) {
            return false;
        }
        if (world.field_72995_K) {
            return true;
        }
        if (side != 1) {
            world.func_147465_d(posX, posY, posZ, (Block)Witchery.Blocks.DREAM_CATCHER, side, 3);
            --itemstack.field_77994_a;
            BlockDreamCatcher.TileEntityDreamCatcher tileEntity = (BlockDreamCatcher.TileEntityDreamCatcher)world.func_147438_o(posX, posY, posZ);
            if (tileEntity != null) {
                DreamWeave weave = (DreamWeave)this.subItems.get(itemstack.func_77960_j());
                weave.setEffect(tileEntity);
            }
        }
        return true;
    }

    public static boolean isWaystoneBound(ItemStack stack) {
        NBTTagCompound tag = stack.func_77978_p();
        return tag != null && tag.func_74764_b("PosX") && tag.func_74764_b("PosY") && tag.func_74764_b("PosZ") && tag.func_74764_b("PosD");
    }

    public static int getWaystoneDimension(ItemStack stack) {
        NBTTagCompound tag = stack.func_77978_p();
        if (tag != null && tag.func_74764_b("PosX") && tag.func_74764_b("PosY") && tag.func_74764_b("PosZ") && tag.func_74764_b("PosD")) {
            int newX = tag.func_74762_e("PosX");
            int newY = tag.func_74762_e("PosY");
            int newZ = tag.func_74762_e("PosZ");
            int newD = tag.func_74762_e("PosD");
            return newD;
        }
        return 0;
    }

    private boolean isRestrictedTeleportTarget(int source, int target) {
        if (source == target) {
            return false;
        }
        return source == Config.instance().dimensionDreamID || source == Config.instance().dimensionMirrorID || target == Config.instance().dimensionDreamID || target == Config.instance().dimensionMirrorID;
    }

    public boolean teleportToLocation(World world, ItemStack itemstack, Entity entity, int radius, boolean presetPosition) {
        NBTTagCompound tag = itemstack.func_77978_p();
        if (tag != null && tag.func_74764_b("PosX") && tag.func_74764_b("PosY") && tag.func_74764_b("PosZ") && tag.func_74764_b("PosD")) {
            int newX = tag.func_74762_e("PosX") - radius + world.field_73012_v.nextInt(radius * 2 + 1);
            int newY = tag.func_74762_e("PosY");
            int newZ = tag.func_74762_e("PosZ") - radius + world.field_73012_v.nextInt(radius * 2 + 1);
            int newD = tag.func_74762_e("PosD");
            if (!this.isRestrictedTeleportTarget(entity.field_71093_bK, newD)) {
                ItemGeneral.teleportToLocation(world, newX, newY, newZ, newD, entity, presetPosition);
                return true;
            }
        } else if (tag != null) {
            EntityLivingBase target = Witchery.Items.TAGLOCK_KIT.getBoundEntity(world, null, itemstack, 1);
            if (entity != null && target != null && !this.isRestrictedTeleportTarget(entity.field_71093_bK, target.field_71093_bK)) {
                ItemGeneral.teleportToLocation(world, target.field_70165_t, target.field_70163_u, target.field_70161_v, target.field_71093_bK, entity, presetPosition);
                return true;
            }
        }
        return false;
    }

    public static boolean teleportToLocationSafely(World world, double posX, double posY, double posZ, int dimension, Entity entity, boolean presetPosition) {
        WorldServer targetWorld = MinecraftServer.func_71276_C().func_71218_a(dimension);
        int x = MathHelper.func_76128_c((double)posX);
        int y = MathHelper.func_76128_c((double)posY);
        int z = MathHelper.func_76128_c((double)posZ);
        for (int i = 0; i < 16; ++i) {
            int dy = y + i;
            if (dy < 250 && !BlockUtil.isReplaceableBlock((World)targetWorld, x, dy, z) && BlockUtil.isReplaceableBlock((World)targetWorld, x, dy + 1, z) && BlockUtil.isReplaceableBlock((World)targetWorld, x, dy + 2, z)) {
                ItemGeneral.teleportToLocation(world, x, dy + 1, z, dimension, entity, presetPosition);
                return true;
            }
            dy = y - i;
            if (i <= 0 || dy <= 1 || BlockUtil.isReplaceableBlock((World)targetWorld, x, dy, z) || !BlockUtil.isReplaceableBlock((World)targetWorld, x, dy + 1, z) || !BlockUtil.isReplaceableBlock((World)targetWorld, x, dy + 2, z)) continue;
            ItemGeneral.teleportToLocation(world, x, dy + 1, z, dimension, entity, presetPosition);
            return true;
        }
        return false;
    }

    public static void teleportToLocation(World world, double posX, double posY, double posZ, int dimension, Entity entity, boolean presetPosition) {
        ItemGeneral.teleportToLocation(world, posX, posY, posZ, dimension, entity, presetPosition, ParticleEffect.PORTAL, SoundEffect.MOB_ENDERMEN_PORTAL);
    }

    public static void teleportToLocation(World world, double posX, double posY, double posZ, int dimension, Entity entity, boolean presetPosition, ParticleEffect particle, SoundEffect sound) {
        boolean isVampire = CreatureUtil.isVampire(entity);
        if (isVampire) {
            Witchery.packetPipeline.sendToAllAround(new PacketParticles(ParticleEffect.SMOKE, SoundEffect.WITCHERY_RANDOM_POOF, entity, 0.5, 2.0), TargetPointUtil.from(entity, 16.0));
        } else {
            Witchery.packetPipeline.sendToAllAround(new PacketParticles(particle, sound, entity, 0.5, 2.0), TargetPointUtil.from(entity, 16.0));
        }
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entity;
            if (entity.field_71093_bK != dimension) {
                if (presetPosition) {
                    player.func_70107_b(posX, posY, posZ);
                }
                ItemGeneral.travelToDimension(player, dimension);
            }
            player.func_70634_a(posX, posY, posZ);
        } else if (entity instanceof EntityLiving) {
            if (entity.field_71093_bK != dimension) {
                ItemGeneral.travelToDimension(entity, dimension, posX, posY, posZ);
            } else {
                entity.func_70012_b(posX, posY, posZ, entity.field_70177_z, entity.field_70125_A);
            }
        } else if (entity.field_71093_bK != dimension) {
            ItemGeneral.travelToDimension(entity, dimension, posX, posY, posZ);
        } else {
            entity.func_70012_b(posX, posY, posZ, entity.field_70177_z, entity.field_70125_A);
        }
        if (isVampire) {
            Witchery.packetPipeline.sendToAllAround(new PacketParticles(ParticleEffect.SMOKE, SoundEffect.WITCHERY_RANDOM_POOF, entity, 0.5, 2.0), TargetPointUtil.from(entity, 16.0));
        } else {
            Witchery.packetPipeline.sendToAllAround(new PacketParticles(particle, sound, entity, 0.5, 2.0), TargetPointUtil.from(entity, 16.0));
        }
    }

    public static void travelToDimension(EntityPlayer player, int dimension) {
        if (!player.field_70170_p.field_72995_K & player instanceof EntityPlayerMP) {
            MinecraftServer server = MinecraftServer.func_71276_C();
            WorldServer newWorldServer = server.func_71218_a(dimension);
            server.func_71203_ab().transferPlayerToDimension((EntityPlayerMP)player, dimension, (Teleporter)new Teleporter2(newWorldServer));
        }
    }

    private static Entity travelToDimension(Entity thisE, int par1, double posX, double posY, double posZ) {
        if (!thisE.field_70170_p.field_72995_K && !thisE.field_70128_L) {
            thisE.field_70170_p.field_72984_F.func_76320_a("changeDimension");
            MinecraftServer minecraftserver = MinecraftServer.func_71276_C();
            int j = thisE.field_71093_bK;
            WorldServer worldserver = minecraftserver.func_71218_a(j);
            WorldServer worldserver1 = minecraftserver.func_71218_a(par1);
            thisE.field_71093_bK = par1;
            if (j == 1 && par1 == 1) {
                worldserver1 = minecraftserver.func_71218_a(0);
                thisE.field_71093_bK = 0;
            }
            thisE.field_70170_p.func_72900_e(thisE);
            thisE.field_70128_L = false;
            thisE.field_70170_p.field_72984_F.func_76320_a("reposition");
            minecraftserver.func_71203_ab().transferEntityToWorld(thisE, j, worldserver, worldserver1, (Teleporter)new Teleporter2(worldserver1));
            thisE.field_70170_p.field_72984_F.func_76318_c("reloading");
            Entity entity = EntityList.func_75620_a((String)EntityList.func_75621_b((Entity)thisE), (World)worldserver1);
            if (entity != null) {
                entity.func_82141_a(thisE, true);
                entity.func_70012_b(posX, posY, posZ, entity.field_70177_z, entity.field_70125_A);
                worldserver1.func_72838_d(entity);
            }
            thisE.field_70128_L = true;
            thisE.field_70170_p.field_72984_F.func_76319_b();
            worldserver.func_82742_i();
            worldserver1.func_82742_i();
            thisE.field_70170_p.field_72984_F.func_76319_b();
            return entity;
        }
        return null;
    }

    public static class DreamWeave
    extends SubItem {
        public final int weaveID;
        public final int textureOffsetX;
        public final int textureOffsetY;
        private final Potion potionDream;
        private final Potion potionNightmare;
        private final int duration;
        private final int amplifier;

        private static DreamWeave register(DreamWeave subItem, ArrayList<SubItem> subItems, ArrayList<DreamWeave> weaves) {
            weaves.add((DreamWeave)SubItem.register((SubItem)subItem, subItems));
            return subItem;
        }

        private DreamWeave(int damageValue, int weaveID, String unlocalizedName, Potion potionDream, Potion potionNightmare, int duration, int amplifier, int textureX, int textureY) {
            super(damageValue, unlocalizedName, 1);
            this.potionDream = potionDream;
            this.potionNightmare = potionNightmare;
            this.duration = duration;
            this.amplifier = amplifier;
            this.textureOffsetX = textureX;
            this.textureOffsetY = textureY;
            this.weaveID = weaveID;
        }

        public void setEffect(BlockDreamCatcher.TileEntityDreamCatcher dreamCatcherEntity) {
            dreamCatcherEntity.setEffect(this);
        }

        public void applyEffect(EntityPlayer player, boolean isDream, boolean isEnhanced) {
            if (isDream) {
                player.func_70690_d(new PotionEffect(this.potionDream.func_76396_c(), isEnhanced && this.potionDream.field_76415_H == Potion.field_76443_y.field_76415_H ? this.duration + 2400 : (isEnhanced ? this.duration - 2400 : this.duration), isEnhanced && this.potionDream.field_76415_H != Potion.field_76443_y.field_76415_H ? this.amplifier + 1 : this.amplifier));
            } else {
                player.func_70690_d(new PotionEffect(this.potionNightmare.func_76396_c(), this.duration, isEnhanced ? this.amplifier + 1 : this.amplifier));
            }
        }
    }

    public static class Drinkable
    extends SubItem {
        protected PotionEffect[] effects;
        protected EnumAction useAction;

        protected Drinkable(int damageValue, String unlocalizedName, int rarity, PotionEffect ... effects) {
            this(damageValue, unlocalizedName, rarity, EnumAction.drink, effects);
        }

        protected Drinkable(int damageValue, String unlocalizedName, int rarity, EnumAction useAction, PotionEffect ... effects) {
            super(damageValue, unlocalizedName, rarity);
            this.effects = effects;
            this.useAction = useAction;
        }

        public void onDrunk(World world, EntityPlayer player, ItemStack itemstack) {
        }
    }

    public static class Brew
    extends SubItem {
        public Brew(int damageValue, String unlocalizedName) {
            super(damageValue, unlocalizedName);
            this.setPotion(true);
        }

        public BrewResult onImpact(World world, EntityLivingBase thrower, MovingObjectPosition mop, boolean enhanced, double brewX, double brewY, double brewZ, AxisAlignedBB brewBounds) {
            return BrewResult.SHOW_EFFECT;
        }

        protected static boolean setBlockIfNotSolid(World world, int x, int y, int z, Block block) {
            return Brew.setBlockIfNotSolid(world, x, y, z, block, 0);
        }

        protected static boolean setBlockIfNotSolid(World world, int x, int y, int z, Block block, int metadata) {
            if (!world.func_147439_a(x, y, z).func_149688_o().func_76220_a() || block == Blocks.field_150321_G && BlockUtil.getBlock(world, x, y, z) == Blocks.field_150433_aE) {
                BlockUtil.setBlock(world, x, y, z, block, metadata, 3);
                ParticleEffect.EXPLODE.send(SoundEffect.NONE, world, 0.5 + (double)x, 0.5 + (double)y, 0.5 + (double)z, 1.0, 1.0, 16);
                return true;
            }
            return false;
        }

        public static enum BrewResult {
            DROP_ITEM,
            SHOW_EFFECT,
            HIDE_EFFECT;

        }
    }

    public static class Edible
    extends SubItem {
        public final boolean eatAnyTime;
        private final int healAmount;
        private final float saturationModifier;
        private final boolean wolfsFavorite;

        private Edible(int damageValue, String unlocalizedName, int healAmount, float saturationModifier, boolean wolfsFavorite) {
            this(damageValue, unlocalizedName, healAmount, saturationModifier, wolfsFavorite, false);
        }

        private Edible(int damageValue, String unlocalizedName, int healAmount, float saturationModifier, boolean wolfsFavorite, boolean eatAnyTime) {
            super(damageValue, unlocalizedName);
            this.healAmount = healAmount;
            this.saturationModifier = saturationModifier;
            this.wolfsFavorite = wolfsFavorite;
            this.eatAnyTime = eatAnyTime;
        }
    }

    public static class BoltType
    extends SubItem {
        private BoltType(int damageValue, String unlocalizedName) {
            super(damageValue, unlocalizedName);
        }

        public static BoltType getBolt(ItemStack stack) {
            SubItem item;
            if (stack != null && stack.func_77973_b() == Witchery.Items.GENERIC && (item = Witchery.Items.GENERIC.subItems.get(stack.func_77960_j())) instanceof BoltType) {
                return (BoltType)item;
            }
            return null;
        }
    }

    public static class SubItem {
        public final int damageValue;
        private final String unlocalizedName;
        private final int rarity;
        private final boolean showInCreativeTab;
        protected boolean enchanted;
        protected boolean potion;
        @SideOnly(value=Side.CLIENT)
        private IIcon icon;

        private static <T extends SubItem> T register(T subItem, ArrayList<SubItem> subItems) {
            assert (subItems.size() == subItem.damageValue) : "Misalignement with subItem registration";
            while (subItems.size() <= subItem.damageValue) {
                subItems.add(null);
            }
            subItems.set(subItem.damageValue, subItem);
            return subItem;
        }

        public boolean isSolidifier() {
            return false;
        }

        public boolean isInfused() {
            return false;
        }

        public SubItem(int damageValue, String unlocalizedName) {
            this(damageValue, unlocalizedName, 0, true);
        }

        private SubItem(int damageValue, String unlocalizedName, int rarity) {
            this(damageValue, unlocalizedName, rarity, true);
        }

        private SubItem(int damageValue, String unlocalizedName, int rarity, boolean showInCreativeTab) {
            this.damageValue = damageValue;
            this.unlocalizedName = unlocalizedName;
            this.rarity = rarity;
            this.showInCreativeTab = showInCreativeTab;
            this.enchanted = false;
            this.potion = false;
        }

        @SideOnly(value=Side.CLIENT)
        private void registerIcon(IIconRegister iconRegister, ItemGeneral itemIngredient) {
            this.icon = iconRegister.func_94245_a(itemIngredient.func_111208_A() + "." + this.unlocalizedName);
        }

        public boolean isMatch(ItemStack itemstack) {
            return itemstack != null && Witchery.Items.GENERIC == itemstack.func_77973_b() && itemstack.func_77960_j() == this.damageValue;
        }

        public ItemStack createStack(int stackSize) {
            return new ItemStack((Item)Witchery.Items.GENERIC, stackSize, this.damageValue);
        }

        public ItemStack createStack() {
            return this.createStack(1);
        }

        public boolean isItemInInventory(InventoryPlayer inventory) {
            return this.getItemSlotFromInventory(inventory) != -1;
        }

        public int getItemSlotFromInventory(InventoryPlayer inventory) {
            for (int k = 0; k < inventory.field_70462_a.length; ++k) {
                if (inventory.field_70462_a[k] == null || inventory.field_70462_a[k].func_77973_b() != Witchery.Items.GENERIC || inventory.field_70462_a[k].func_77960_j() != this.damageValue) continue;
                return k;
            }
            return -1;
        }

        public boolean consumeItemFromInventory(InventoryPlayer inventory) {
            int j = this.getItemSlotFromInventory(inventory);
            if (j < 0) {
                return false;
            }
            if (--inventory.field_70462_a[j].field_77994_a <= 0) {
                inventory.field_70462_a[j] = null;
            }
            return true;
        }

        public boolean isEnchanted() {
            return this.enchanted || this.potion;
        }

        public SubItem setEnchanted(boolean enchanted) {
            this.enchanted = enchanted;
            return this;
        }

        public SubItem setPotion(boolean potion) {
            this.potion = potion;
            return this;
        }

        public boolean isPotion() {
            return this.potion;
        }

        public BrewItemKey getBrewItemKey() {
            return new BrewItemKey(Witchery.Items.GENERIC, this.damageValue);
        }
    }

    private static class Teleporter2
    extends Teleporter {
        public Teleporter2(WorldServer server) {
            super(server);
        }

        public boolean func_85188_a(Entity par1Entity) {
            return false;
        }

        public boolean func_77184_b(Entity par1Entity, double par2, double par4, double par6, float par8) {
            return false;
        }

        public void func_77185_a(Entity par1Entity, double par2, double par4, double par6, float par8) {
        }

        public void func_85189_a(long par1) {
        }
    }
}

