/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  codechicken.lib.raytracer.RayTracer
 *  codechicken.lib.vec.BlockCoord
 *  codechicken.multipart.MultiPartRegistry
 *  codechicken.multipart.MultiPartRegistry$IPartConverter
 *  codechicken.multipart.MultiPartRegistry$IPartFactory
 *  codechicken.multipart.MultipartGenerator
 *  codechicken.multipart.TMultiPart
 *  codechicken.multipart.TileMultipart
 *  codechicken.multipart.minecraft.McMetaPart
 *  cpw.mods.fml.common.eventhandler.Event$Result
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent
 *  net.minecraftforge.event.entity.player.PlayerInteractEvent$Action
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.tiles.TileTube
 *  thaumcraft.common.tiles.TileTubeBuffer
 *  thaumcraft.common.tiles.TileTubeFilter
 *  thaumcraft.common.tiles.TileTubeValve
 */
package witchinggadgets.common.util.handler;

import codechicken.lib.raytracer.RayTracer;
import codechicken.lib.vec.BlockCoord;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.MultipartGenerator;
import codechicken.multipart.TMultiPart;
import codechicken.multipart.TileMultipart;
import codechicken.multipart.minecraft.McMetaPart;
import cpw.mods.fml.common.eventhandler.Event;
import java.util.HashSet;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileTube;
import thaumcraft.common.tiles.TileTubeBuffer;
import thaumcraft.common.tiles.TileTubeFilter;
import thaumcraft.common.tiles.TileTubeValve;
import witchinggadgets.common.blocks.tiles.MultipartEssentiaBuffer;
import witchinggadgets.common.blocks.tiles.MultipartEssentiaTube;
import witchinggadgets.common.blocks.tiles.MultipartEssentiaTube_Filtered;
import witchinggadgets.common.blocks.tiles.MultipartEssentiaTube_Valve;

public class WGMultiPartHandler
implements MultiPartRegistry.IPartFactory,
MultiPartRegistry.IPartConverter {
    public static WGMultiPartHandler instance = new WGMultiPartHandler();
    public static RayTracer rayTracer = new RayTracer();

    public void init() {
        MultiPartRegistry.registerConverter((MultiPartRegistry.IPartConverter)this);
        MultiPartRegistry.registerParts((MultiPartRegistry.IPartFactory)this, (String[])new String[]{"witchingGadgets:essentia_tube", "witchingGadgets:essentia_tube_valve", "witchingGadgets:essentia_tube_filtered", "witchingGadgets:essentia_buffer"});
        MultipartGenerator.registerPassThroughInterface((String)"thaumcraft.api.aspects.IEssentiaTransport", (boolean)true, (boolean)true);
        MultipartGenerator.registerPassThroughInterface((String)"thaumcraft.api.wands.IWandable", (boolean)true, (boolean)true);
    }

    public Iterable<Block> blockTypes() {
        HashSet<Block> set = new HashSet<Block>();
        set.add(ConfigBlocks.blockTube);
        return set;
    }

    public TMultiPart convert(World world, BlockCoord coord) {
        Block b = world.func_147439_a(coord.x, coord.y, coord.z);
        int meta = world.func_72805_g(coord.x, coord.y, coord.z);
        if (b.equals(ConfigBlocks.blockTube)) {
            if (world.func_147438_o(coord.x, coord.y, coord.z) instanceof TileTubeValve) {
                return new MultipartEssentiaTube_Valve(meta);
            }
            if (world.func_147438_o(coord.x, coord.y, coord.z) instanceof TileTubeFilter) {
                return new MultipartEssentiaTube_Filtered(meta);
            }
            if (world.func_147438_o(coord.x, coord.y, coord.z) instanceof TileTubeBuffer) {
                return new MultipartEssentiaBuffer(meta);
            }
            return new MultipartEssentiaTube(meta);
        }
        return null;
    }

    public TMultiPart createPart(String name, boolean client) {
        if (name.equals("witchingGadgets:essentia_tube")) {
            return new MultipartEssentiaTube(0);
        }
        if (name.equals("witchingGadgets:essentia_tube_valve")) {
            return new MultipartEssentiaTube_Valve(0);
        }
        if (name.equals("witchingGadgets:essentia_tube_filtered")) {
            return new MultipartEssentiaTube_Filtered(0);
        }
        if (name.equals("witchingGadgets:essentia_buffer")) {
            return new MultipartEssentiaBuffer(0);
        }
        return null;
    }

    public static boolean tileIsEssentiaTube(TileEntity tile) {
        if (tile instanceof TileTube) {
            return true;
        }
        if (tile instanceof TileMultipart) {
            for (TMultiPart part : ((TileMultipart)tile).jPartList()) {
                if (!(part instanceof MultipartEssentiaTube)) continue;
                return true;
            }
        }
        return false;
    }

    public static void handleWorldInteraction(PlayerInteractEvent event) {
        if (event.entityPlayer.func_71045_bC() != null && Block.func_149634_a((Item)event.entityPlayer.func_71045_bC().func_77973_b()) == ConfigBlocks.blockTube && event.entityPlayer.func_71045_bC().func_77960_j() != 7 && event.entityPlayer.func_71045_bC().func_77960_j() != 2 && event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK && event.world.func_147438_o(event.x, event.y, event.z) instanceof TileMultipart) {
            MultipartEssentiaTube part;
            TileMultipart mp = (TileMultipart)event.world.func_147438_o(event.x, event.y, event.z);
            int meta = event.entityPlayer.func_71045_bC().func_77960_j();
            McMetaPart mcMetaPart = meta == 1 ? new MultipartEssentiaTube_Valve(meta) : (meta == 3 ? new MultipartEssentiaTube_Filtered(meta) : (part = meta == 4 ? new MultipartEssentiaBuffer(meta) : new MultipartEssentiaTube(meta)));
            if (mp.canAddPart((TMultiPart)part) && !event.world.field_72995_K) {
                TileMultipart.addPart((World)event.world, (BlockCoord)new BlockCoord(event.x, event.y, event.z), (TMultiPart)part);
                event.useItem = Event.Result.DENY;
                if (!event.entityPlayer.field_71075_bZ.field_75098_d) {
                    --event.entityPlayer.func_71045_bC().field_77994_a;
                }
            }
        }
    }
}

