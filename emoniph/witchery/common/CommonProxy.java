/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.IGuiHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 */
package com.emoniph.witchery.common;

import com.emoniph.witchery.blocks.BlockAreaMarker;
import com.emoniph.witchery.blocks.BlockDistillery;
import com.emoniph.witchery.blocks.BlockSpinningWheel;
import com.emoniph.witchery.blocks.BlockWitchesOven;
import com.emoniph.witchery.brewing.DispersalTriggered;
import com.emoniph.witchery.brewing.potions.WitcheryPotions;
import com.emoniph.witchery.common.GenericEvents;
import com.emoniph.witchery.entity.EntityBroom;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.item.ItemBrewBag;
import com.emoniph.witchery.item.ItemGoblinClothes;
import com.emoniph.witchery.item.ItemLeonardsUrn;
import com.emoniph.witchery.item.ItemPoppet;
import com.emoniph.witchery.item.ItemWitchHand;
import com.emoniph.witchery.ritual.rites.RitePriorIncarnation;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.worldgen.WorldHandlerVillageDistrict;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy
implements IGuiHandler {
    private static final Map<String, NBTTagCompound> extendedEntityData = new HashMap<String, NBTTagCompound>();

    public static void storeEntityData(String name, NBTTagCompound compound) {
        extendedEntityData.put(name, compound);
    }

    public static NBTTagCompound getEntityData(String name) {
        return extendedEntityData.remove(name);
    }

    public void preInit() {
    }

    public void registerEvents() {
        MinecraftForge.EVENT_BUS.register((Object)new ItemPoppet.PoppetEventHooks());
        MinecraftForge.EVENT_BUS.register((Object)new Infusion.EventHooks());
        MinecraftForge.EVENT_BUS.register((Object)new ItemWitchHand.EventHooks());
        MinecraftForge.EVENT_BUS.register((Object)new EntityBroom.EventHooks());
        MinecraftForge.EVENT_BUS.register((Object)new RitePriorIncarnation.EventHooks());
        MinecraftForge.EVENT_BUS.register((Object)new BlockAreaMarker.AreaMarkerEventHooks());
        MinecraftForge.EVENT_BUS.register((Object)new GenericEvents());
        MinecraftForge.EVENT_BUS.register((Object)new ItemGoblinClothes.EventHooks());
        MinecraftForge.EVENT_BUS.register((Object)new WitcheryPotions.EventHooks());
        MinecraftForge.EVENT_BUS.register((Object)new DispersalTriggered.EventHooks());
        MinecraftForge.TERRAIN_GEN_BUS.register((Object)new WorldHandlerVillageDistrict.EventHooks());
    }

    public void registerRenderers() {
    }

    public void registerServerHandlers() {
    }

    public void registerHandlers() {
    }

    public void postInit() {
    }

    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 0: {
                return null;
            }
            case 2: {
                return new BlockWitchesOven.ContainerWitchesOven(player.field_71071_by, (BlockWitchesOven.TileEntityWitchesOven)world.func_147438_o(x, y, z));
            }
            case 3: {
                return new BlockDistillery.ContainerDistillery(player.field_71071_by, (BlockDistillery.TileEntityDistillery)world.func_147438_o(x, y, z));
            }
            case 4: {
                return new BlockSpinningWheel.ContainerSpinningWheel(player.field_71071_by, (BlockSpinningWheel.TileEntitySpinningWheel)world.func_147438_o(x, y, z));
            }
            case 5: {
                return new ItemBrewBag.ContainerBrewBag((IInventory)player.field_71071_by, (IInventory)new ItemBrewBag.InventoryBrewBag(player), player.func_70694_bm());
            }
            case 8: {
                return new ItemLeonardsUrn.ContainerLeonardsUrn((IInventory)player.field_71071_by, (IInventory)new ItemLeonardsUrn.InventoryLeonardsUrn(player), player.func_70694_bm());
            }
        }
        return null;
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    public boolean getGraphicsLevel() {
        return false;
    }

    public int getStockageRenderId() {
        return 0;
    }

    public int getGasRenderId() {
        return 0;
    }

    public int getPitGrassRenderId() {
        return 0;
    }

    public int getBrewLiquidRenderId() {
        return 0;
    }

    public void registerVillagers() {
    }

    public void generateParticle(World worldObj, double posX, double posY, double posZ, float f, float g, float h, int i, float j) {
    }

    public EntityPlayer getPlayer(MessageContext ctx) {
        if (ctx.side == Side.SERVER) {
            return ctx.getServerHandler().field_147369_b;
        }
        return null;
    }

    public int getVineRenderId() {
        return 0;
    }

    public void showParticleEffect(World world, double x, double y, double z, double width, double height, SoundEffect sound, int color, ParticleEffect particle) {
    }
}

