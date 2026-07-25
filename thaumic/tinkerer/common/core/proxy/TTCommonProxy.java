/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.Optional$Method
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLInterModComms
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.network.IGuiHandler
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.relauncher.Side
 *  dan200.computercraft.api.ComputerCraftAPI
 *  dan200.computercraft.api.turtle.ITurtleUpgrade
 *  li.cil.oc.api.Driver
 *  li.cil.oc.api.driver.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.util.EnumHelper
 *  thaumcraft.api.aspects.IEssentiaTransport
 *  thaumcraft.api.wands.WandCap
 *  thaumcraft.api.wands.WandRod
 *  thaumcraft.common.tiles.TileAlembic
 *  thaumcraft.common.tiles.TileArcaneBore
 *  thaumcraft.common.tiles.TileCentrifuge
 *  thaumcraft.common.tiles.TileCrucible
 *  thaumcraft.common.tiles.TileDeconstructionTable
 *  thaumcraft.common.tiles.TileInfusionMatrix
 *  thaumcraft.common.tiles.TileJarBrain
 *  thaumcraft.common.tiles.TileJarFillable
 *  thaumcraft.common.tiles.TileJarNode
 *  thaumcraft.common.tiles.TileNode
 *  thaumcraft.common.tiles.TileSensor
 *  thaumcraft.common.tiles.TileTubeFilter
 *  thaumcraft.common.tiles.TileWandPedestal
 */
package thaumic.tinkerer.common.core.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.turtle.ITurtleUpgrade;
import li.cil.oc.api.Driver;
import li.cil.oc.api.driver.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.tiles.TileAlembic;
import thaumcraft.common.tiles.TileArcaneBore;
import thaumcraft.common.tiles.TileCentrifuge;
import thaumcraft.common.tiles.TileCrucible;
import thaumcraft.common.tiles.TileDeconstructionTable;
import thaumcraft.common.tiles.TileInfusionMatrix;
import thaumcraft.common.tiles.TileJarBrain;
import thaumcraft.common.tiles.TileJarFillable;
import thaumcraft.common.tiles.TileJarNode;
import thaumcraft.common.tiles.TileNode;
import thaumcraft.common.tiles.TileSensor;
import thaumcraft.common.tiles.TileTubeFilter;
import thaumcraft.common.tiles.TileWandPedestal;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.tile.TileFunnel;
import thaumic.tinkerer.common.block.tile.TileRepairer;
import thaumic.tinkerer.common.block.tile.transvector.TileTransvectorInterface;
import thaumic.tinkerer.common.compat.FumeTool;
import thaumic.tinkerer.common.core.handler.ConfigHandler;
import thaumic.tinkerer.common.core.handler.ModCreativeTab;
import thaumic.tinkerer.common.core.handler.kami.DimensionalShardDropHandler;
import thaumic.tinkerer.common.core.handler.kami.KamiArmorHandler;
import thaumic.tinkerer.common.core.handler.kami.KamiDimensionHandler;
import thaumic.tinkerer.common.core.handler.kami.SoulHeartHandler;
import thaumic.tinkerer.common.core.helper.AspectCropLootManager;
import thaumic.tinkerer.common.core.helper.BonemealEventHandler;
import thaumic.tinkerer.common.core.helper.NumericAspectHelper;
import thaumic.tinkerer.common.enchantment.ModEnchantments;
import thaumic.tinkerer.common.enchantment.core.EnchantmentManager;
import thaumic.tinkerer.common.item.SpellClothCraftingHandler;
import thaumic.tinkerer.common.item.foci.ItemFocusDeflect;
import thaumic.tinkerer.common.item.kami.wand.CapIchor;
import thaumic.tinkerer.common.item.kami.wand.RodIchorcloth;
import thaumic.tinkerer.common.network.GuiHandler;
import thaumic.tinkerer.common.network.PlayerTracker;
import thaumic.tinkerer.common.network.packet.PacketEnchanterAddEnchant;
import thaumic.tinkerer.common.network.packet.PacketEnchanterStartWorking;
import thaumic.tinkerer.common.network.packet.PacketMobMagnetButton;
import thaumic.tinkerer.common.network.packet.PacketPlacerButton;
import thaumic.tinkerer.common.network.packet.PacketTabletButton;
import thaumic.tinkerer.common.network.packet.kami.PacketSoulHearts;
import thaumic.tinkerer.common.network.packet.kami.PacketToggleArmor;
import thaumic.tinkerer.common.network.packet.kami.PacketWarpGateButton;
import thaumic.tinkerer.common.network.packet.kami.PacketWarpGateTeleport;
import thaumic.tinkerer.common.peripheral.OpenComputers.DriverArcaneBore;
import thaumic.tinkerer.common.peripheral.OpenComputers.DriverArcaneEar;
import thaumic.tinkerer.common.peripheral.OpenComputers.DriverBrainInAJar;
import thaumic.tinkerer.common.peripheral.OpenComputers.DriverDeconstructor;
import thaumic.tinkerer.common.peripheral.OpenComputers.DriverEssentiaTransport;
import thaumic.tinkerer.common.peripheral.OpenComputers.DriverIAspectContainer;
import thaumic.tinkerer.common.peripheral.PeripheralHandler;
import thaumic.tinkerer.common.potion.ModPotions;
import thaumic.tinkerer.common.research.ResearchHelper;

public class TTCommonProxy {
    public static EnumRarity kamiRarity;
    public WandCap capIchor;
    public WandRod rodIchor;
    public Item.ToolMaterial toolMaterialIchor;

    public void preInit(FMLPreInitializationEvent event) {
        this.toolMaterialIchor = EnumHelper.addToolMaterial((String)"ICHOR", (int)4, (int)-1, (float)10.0f, (float)5.0f, (int)25);
        ModCreativeTab.INSTANCE = new ModCreativeTab();
        ConfigHandler.loadConfig(event.getSuggestedConfigurationFile());
        NumericAspectHelper.init();
        ThaumicTinkerer.registry.preInit();
        this.capIchor = new CapIchor();
        this.rodIchor = new RodIchorcloth();
        ModCreativeTab.INSTANCE.addWand();
        if (Loader.isModLoaded((String)"ComputerCraft")) {
            this.initCCPeripherals();
        }
        this.registerVersionChecker();
        kamiRarity = (EnumRarity)EnumHelper.addEnum((Class[][])new Class[][]{{EnumRarity.class, EnumChatFormatting.class, String.class}}, EnumRarity.class, (String)"KAMI", (Object[])new Object[]{EnumChatFormatting.LIGHT_PURPLE, "Kami"});
    }

    public void init(FMLInitializationEvent event) {
        ModEnchantments.initEnchantments();
        EnchantmentManager.initEnchantmentData();
        ModPotions.initPotions();
        ThaumicTinkerer.registry.init();
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)ThaumicTinkerer.instance, (IGuiHandler)new GuiHandler());
        this.registerPackets();
        FMLCommonHandler.instance().bus().register((Object)new PlayerTracker());
        MinecraftForge.EVENT_BUS.register((Object)new BonemealEventHandler());
        FMLCommonHandler.instance().bus().register((Object)new SpellClothCraftingHandler());
        if (ConfigHandler.enableKami) {
            MinecraftForge.EVENT_BUS.register((Object)new DimensionalShardDropHandler());
            MinecraftForge.EVENT_BUS.register((Object)new KamiDimensionHandler());
            MinecraftForge.EVENT_BUS.register((Object)new SoulHeartHandler());
        }
        if (Loader.isModLoaded((String)"OpenComputers")) {
            this.initOpenCDrivers();
        }
        if (Loader.isModLoaded((String)"ForgeMultipart")) {
            ThaumicTinkerer.log.trace("Attempting to load Multiparts");
            try {
                Class<?> clazz = Class.forName("thaumic.tinkerer.common.multipart.MultipartHandler");
                clazz.newInstance();
            }
            catch (Throwable e) {
                ThaumicTinkerer.log.error("Error registering multiparts", e);
            }
        } else {
            ThaumicTinkerer.log.info("Skipping TC Multipart integration");
        }
    }

    protected void registerPackets() {
        ThaumicTinkerer.netHandler.registerMessage(PacketSoulHearts.class, PacketSoulHearts.class, 142, Side.CLIENT);
        ThaumicTinkerer.netHandler.registerMessage(PacketToggleArmor.class, PacketToggleArmor.class, 143, Side.CLIENT);
        ThaumicTinkerer.netHandler.registerMessage(PacketToggleArmor.class, PacketToggleArmor.class, 144, Side.SERVER);
        ThaumicTinkerer.netHandler.registerMessage(PacketWarpGateButton.class, PacketWarpGateButton.class, 145, Side.SERVER);
        ThaumicTinkerer.netHandler.registerMessage(PacketWarpGateTeleport.class, PacketWarpGateTeleport.class, 146, Side.SERVER);
        ThaumicTinkerer.netHandler.registerMessage(PacketEnchanterAddEnchant.class, PacketEnchanterAddEnchant.class, 147, Side.SERVER);
        ThaumicTinkerer.netHandler.registerMessage(PacketEnchanterStartWorking.class, PacketEnchanterStartWorking.class, 148, Side.SERVER);
        ThaumicTinkerer.netHandler.registerMessage(PacketMobMagnetButton.class, PacketMobMagnetButton.class, 149, Side.SERVER);
        ThaumicTinkerer.netHandler.registerMessage(PacketTabletButton.class, PacketTabletButton.class, 150, Side.SERVER);
        ThaumicTinkerer.netHandler.registerMessage(PacketPlacerButton.class, PacketPlacerButton.class, 151, Side.SERVER);
    }

    public void registerVersionChecker() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.func_74778_a("curseProjectName", "75598-thaumic-tinkerer");
        compound.func_74778_a("curseFilenameParser", "ThaumicTinkerer-[].jar");
        compound.func_74778_a("modDisplayName", "Thaumic Tinkerer");
        FMLInterModComms.sendRuntimeMessage((Object)"ThaumicTinkerer", (String)"VersionChecker", (String)"addCurseCheck", (NBTTagCompound)compound);
    }

    public void postInit(FMLPostInitializationEvent event) {
        ResearchHelper.initResearch();
        ThaumicTinkerer.registry.postInit();
        AspectCropLootManager.populateLootMap();
        ItemFocusDeflect.setupBlackList();
    }

    @Optional.Method(modid="ComputerCraft")
    protected void initCCPeripherals() {
        PeripheralHandler handler = new PeripheralHandler();
        Class[] peripheralClasses = new Class[]{TileAlembic.class, TileCentrifuge.class, TileCrucible.class, TileFunnel.class, TileInfusionMatrix.class, TileJarFillable.class, TileJarNode.class, TileNode.class, TileRepairer.class, TileTubeFilter.class, TileTransvectorInterface.class, TileWandPedestal.class, TileDeconstructionTable.class, TileJarBrain.class, TileSensor.class, TileArcaneBore.class, IEssentiaTransport.class};
        handler.registerPeripheralProvider();
        ComputerCraftAPI.registerTurtleUpgrade((ITurtleUpgrade)new FumeTool());
    }

    @Optional.Method(modid="OpenComputers")
    public void initOpenCDrivers() {
        Driver.add((Block)new DriverIAspectContainer());
        Driver.add((Block)new DriverArcaneEar());
        Driver.add((Block)new DriverBrainInAJar());
        Driver.add((Block)new DriverDeconstructor());
        Driver.add((Block)new DriverEssentiaTransport());
        Driver.add((Block)new DriverArcaneBore());
    }

    public boolean isClient() {
        return false;
    }

    public boolean armorStatus(EntityPlayer player) {
        return KamiArmorHandler.getArmorStatus(player);
    }

    public void setArmor(EntityPlayer player, boolean status) {
        KamiArmorHandler.setArmorStatus(player, status);
    }

    public EntityPlayer getClientPlayer() {
        return null;
    }

    public void shadowSparkle(World world, float x, float y, float z, int size) {
    }
}

