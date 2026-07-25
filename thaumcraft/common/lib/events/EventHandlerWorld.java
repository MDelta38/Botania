/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.IFuelHandler
 *  cpw.mods.fml.common.eventhandler.Event$Result
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.PlayerEvent$ItemCraftedEvent
 *  net.minecraft.block.Block
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.ChunkCoordIntPair
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.event.entity.player.FillBucketEvent
 *  net.minecraftforge.event.world.BlockEvent$HarvestDropsEvent
 *  net.minecraftforge.event.world.BlockEvent$MultiPlaceEvent
 *  net.minecraftforge.event.world.BlockEvent$PlaceEvent
 *  net.minecraftforge.event.world.ChunkDataEvent$Load
 *  net.minecraftforge.event.world.ChunkDataEvent$Save
 *  net.minecraftforge.event.world.NoteBlockEvent$Play
 *  net.minecraftforge.event.world.WorldEvent$Load
 *  net.minecraftforge.event.world.WorldEvent$Save
 *  net.minecraftforge.event.world.WorldEvent$Unload
 *  org.apache.logging.log4j.Level
 */
package thaumcraft.common.lib.events;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.NoteBlockEvent;
import net.minecraftforge.event.world.WorldEvent;
import org.apache.logging.log4j.Level;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss;
import thaumcraft.common.items.ItemEssence;
import thaumcraft.common.items.equipment.ItemElementalPickaxe;
import thaumcraft.common.items.equipment.ItemPrimalCrusher;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.items.wands.foci.ItemFocusExcavation;
import thaumcraft.common.lib.events.ServerTickEventsFML;
import thaumcraft.common.lib.utils.EntityUtils;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.ChunkLoc;
import thaumcraft.common.lib.world.dim.Cell;
import thaumcraft.common.lib.world.dim.CellLoc;
import thaumcraft.common.lib.world.dim.MazeHandler;
import thaumcraft.common.tiles.TileSensor;

public class EventHandlerWorld
implements IFuelHandler {
    @SubscribeEvent
    public void worldLoad(WorldEvent.Load event) {
        if (!event.world.field_72995_K && event.world.field_73011_w.field_76574_g == 0) {
            MazeHandler.loadMaze(event.world);
        }
    }

    @SubscribeEvent
    public void worldSave(WorldEvent.Save event) {
        if (!event.world.field_72995_K && event.world.field_73011_w.field_76574_g == 0) {
            MazeHandler.saveMaze(event.world);
        }
    }

    @SubscribeEvent
    public void worldUnload(WorldEvent.Unload event) {
        if (event.world.field_72995_K) {
            return;
        }
        VisNetHandler.sources.remove(event.world.field_73011_w.field_76574_g);
        try {
            TileSensor.noteBlockEvents.remove((WorldServer)event.world);
        }
        catch (Exception e) {
            FMLCommonHandler.instance().getFMLLogger().log(Level.WARN, "[Thaumcraft] Error unloading noteblock even handlers.", (Throwable)e);
        }
    }

    @SubscribeEvent
    public void chunkSave(ChunkDataEvent.Save event) {
        NBTTagCompound var4 = new NBTTagCompound();
        event.getData().func_74782_a("Thaumcraft", (NBTBase)var4);
        var4.func_74757_a(Config.regenKey, true);
    }

    @SubscribeEvent
    public void chunkLoad(ChunkDataEvent.Load event) {
        int dim = event.world.field_73011_w.field_76574_g;
        ChunkCoordIntPair loc = event.getChunk().func_76632_l();
        if (!event.getData().func_74775_l("Thaumcraft").func_74764_b(Config.regenKey) && (Config.regenAmber || Config.regenAura || Config.regenCinnibar || Config.regenInfusedStone || Config.regenStructure || Config.regenTrees)) {
            FMLCommonHandler.instance().getFMLLogger().log(Level.WARN, "[Thaumcraft] World gen was never run for chunk at " + event.getChunk().func_76632_l() + ". Adding to queue for regeneration.");
            ArrayList<ChunkLoc> chunks = ServerTickEventsFML.chunksToGenerate.get(dim);
            if (chunks == null) {
                ServerTickEventsFML.chunksToGenerate.put(dim, new ArrayList());
                chunks = ServerTickEventsFML.chunksToGenerate.get(dim);
            }
            if (chunks != null) {
                chunks.add(new ChunkLoc(loc.field_77276_a, loc.field_77275_b));
                ServerTickEventsFML.chunksToGenerate.put(dim, chunks);
            }
        }
    }

    public int getBurnTime(ItemStack fuel) {
        if (fuel.func_77969_a(new ItemStack(ConfigItems.itemResource, 1, 0))) {
            return 6400;
        }
        if (fuel.func_77969_a(new ItemStack(ConfigBlocks.blockMagicalLog))) {
            return 400;
        }
        return 0;
    }

    @SubscribeEvent
    public void onCrafting(PlayerEvent.ItemCraftedEvent event) {
        int warp = ThaumcraftApi.getWarp(event.crafting);
        if (!Config.wuss && warp > 0 && !event.player.field_70170_p.field_72995_K) {
            Thaumcraft.addStickyWarpToPlayer(event.player, warp);
        }
        if (event.crafting.func_77973_b() == ConfigItems.itemResource && event.crafting.func_77960_j() == 13 && event.crafting.func_77942_o()) {
            for (int var2 = 0; var2 < 9; ++var2) {
                ItemStack var3 = event.craftMatrix.func_70301_a(var2);
                if (var3 == null || !(var3.func_77973_b() instanceof ItemEssence)) continue;
                ++var3.field_77994_a;
                event.craftMatrix.func_70299_a(var2, var3);
            }
        }
        if (event.crafting.func_77973_b() == Item.func_150898_a((Block)ConfigBlocks.blockMetalDevice) && event.crafting.func_77960_j() == 3) {
            ItemStack var3 = event.craftMatrix.func_70301_a(4);
            ++var3.field_77994_a;
            event.craftMatrix.func_70299_a(4, var3);
        }
    }

    @SubscribeEvent
    public void harvestEvent(BlockEvent.HarvestDropsEvent event) {
        EntityPlayer player = event.harvester;
        if (event.drops == null || event.drops.size() == 0 || player == null) {
            return;
        }
        ItemStack held = player.field_71071_by.func_70448_g();
        if (held != null && (held.func_77973_b() instanceof ItemElementalPickaxe || held.func_77973_b() instanceof ItemPrimalCrusher || held.func_77973_b() instanceof ItemWandCasting && ((ItemWandCasting)held.func_77973_b()).getFocus(held) != null && ((ItemWandCasting)held.func_77973_b()).getFocus(held).isUpgradedWith(((ItemWandCasting)held.func_77973_b()).getFocusItem(held), ItemFocusExcavation.dowsing))) {
            int fortune = EnchantmentHelper.func_77517_e((EntityLivingBase)player);
            if (held.func_77973_b() instanceof ItemWandCasting) {
                fortune = ((ItemWandCasting)held.func_77973_b()).getFocus(held).getUpgradeLevel(((ItemWandCasting)held.func_77973_b()).getFocusItem(held), FocusUpgradeType.treasure);
            }
            float chance = 0.2f + (float)fortune * 0.075f;
            for (int a = 0; a < event.drops.size(); ++a) {
                ItemStack smr;
                ItemStack is = (ItemStack)event.drops.get(a);
                if (is.func_77969_a(smr = Utils.findSpecialMiningResult(is, chance, event.world.field_73012_v))) continue;
                event.drops.set(a, smr);
                if (event.world.field_72995_K) continue;
                event.world.func_72908_a((double)((float)event.x + 0.5f), (double)((float)event.y + 0.5f), (double)((float)event.z + 0.5f), "random.orb", 0.2f, 0.7f + event.world.field_73012_v.nextFloat() * 0.2f);
            }
        }
    }

    @SubscribeEvent
    public void noteEvent(NoteBlockEvent.Play event) {
        if (event.world.field_72995_K) {
            return;
        }
        if (!TileSensor.noteBlockEvents.containsKey(event.world)) {
            TileSensor.noteBlockEvents.put((WorldServer)event.world, new ArrayList());
        }
        ArrayList<Integer[]> list = TileSensor.noteBlockEvents.get(event.world);
        list.add(new Integer[]{event.x, event.y, event.z, event.instrument.ordinal(), event.getVanillaNoteId()});
        TileSensor.noteBlockEvents.put((WorldServer)event.world, list);
    }

    @SubscribeEvent
    public void fillBucket(FillBucketEvent event) {
        if (event.target.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            if (event.world.func_147439_a(event.target.field_72311_b, event.target.field_72312_c, event.target.field_72309_d) == ConfigBlocks.blockFluidPure && event.world.func_72805_g(event.target.field_72311_b, event.target.field_72312_c, event.target.field_72309_d) == 0) {
                event.world.func_147468_f(event.target.field_72311_b, event.target.field_72312_c, event.target.field_72309_d);
                event.result = new ItemStack(ConfigItems.itemBucketPure);
                event.setResult(Event.Result.ALLOW);
                return;
            }
            if (event.world.func_147439_a(event.target.field_72311_b, event.target.field_72312_c, event.target.field_72309_d) == ConfigBlocks.blockFluidDeath && event.world.func_72805_g(event.target.field_72311_b, event.target.field_72312_c, event.target.field_72309_d) == 3) {
                event.world.func_147468_f(event.target.field_72311_b, event.target.field_72312_c, event.target.field_72309_d);
                event.result = new ItemStack(ConfigItems.itemBucketDeath);
                event.setResult(Event.Result.ALLOW);
                return;
            }
        }
    }

    @SubscribeEvent
    public void placeBlockEvent(BlockEvent.PlaceEvent event) {
        if (this.isNearActiveBoss(event.world, event.player, event.x, event.y, event.z)) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void placeBlockEvent(BlockEvent.MultiPlaceEvent event) {
        if (this.isNearActiveBoss(event.world, event.player, event.x, event.y, event.z)) {
            event.setCanceled(true);
        }
    }

    private boolean isNearActiveBoss(World world, EntityPlayer player, int x, int y, int z) {
        ArrayList<Entity> list;
        int zz;
        int xx;
        Cell c;
        return world.field_73011_w.field_76574_g == Config.dimensionOuterId && player != null && !player.field_71075_bZ.field_75098_d && (c = MazeHandler.getFromHashMap(new CellLoc(xx = x >> 4, zz = z >> 4))) != null && c.feature >= 2 && c.feature <= 5 && (list = EntityUtils.getEntitiesInRange(world, x, y, z, null, EntityThaumcraftBoss.class, 32.0)) != null && list.size() > 0;
    }
}

