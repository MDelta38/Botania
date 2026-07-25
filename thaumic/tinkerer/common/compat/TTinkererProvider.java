/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mcp.mobius.waila.api.IWailaConfigHandler
 *  mcp.mobius.waila.api.IWailaDataAccessor
 *  mcp.mobius.waila.api.IWailaDataProvider
 *  mcp.mobius.waila.api.IWailaRegistrar
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package thaumic.tinkerer.common.compat;

import java.util.List;
import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.BlockAnimationTablet;
import thaumic.tinkerer.common.block.BlockMagnet;
import thaumic.tinkerer.common.block.BlockRepairer;
import thaumic.tinkerer.common.block.kami.BlockWarpGate;
import thaumic.tinkerer.common.block.tile.TileRepairer;
import thaumic.tinkerer.common.block.tile.kami.TileWarpGate;
import thaumic.tinkerer.common.block.tile.tablet.TileAnimationTablet;
import thaumic.tinkerer.common.block.tile.transvector.TileTransvectorInterface;
import thaumic.tinkerer.common.block.transvector.BlockTransvectorInterface;
import thaumic.tinkerer.common.compat.MagnetProvider;

public class TTinkererProvider
implements IWailaDataProvider {
    public static void callbackRegister(IWailaRegistrar registrar) {
        registrar.registerBodyProvider((IWailaDataProvider)new TTinkererProvider(), BlockAnimationTablet.class);
        registrar.registerBodyProvider((IWailaDataProvider)new TTinkererProvider(), BlockTransvectorInterface.class);
        registrar.registerBodyProvider((IWailaDataProvider)new TTinkererProvider(), BlockRepairer.class);
        registrar.registerBodyProvider((IWailaDataProvider)new TTinkererProvider(), BlockWarpGate.class);
        registrar.registerBodyProvider((IWailaDataProvider)new MagnetProvider(), BlockMagnet.class);
    }

    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return null;
    }

    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        TileRepairer tileRepair;
        ItemStack item;
        if (accessor.getBlock() == ThaumicTinkerer.registry.getFirstBlockFromClass(BlockAnimationTablet.class)) {
            TileAnimationTablet tileAn = (TileAnimationTablet)accessor.getTileEntity();
            ItemStack stack = tileAn.func_70301_a(0);
            String currentTool = stack == null ? StatCollector.func_74838_a((String)"ttwaila.nothing") : stack.func_82833_r();
            currenttip.add(StatCollector.func_74837_a((String)"ttwaila.currentTool", (Object[])new Object[]{currentTool}));
            if (stack != null) {
                if (tileAn.leftClick) {
                    currenttip.add(StatCollector.func_74838_a((String)"ttwaila.leftClick"));
                } else {
                    currenttip.add(StatCollector.func_74838_a((String)"ttwaila.rightClick"));
                }
                if (tileAn.redstone) {
                    currenttip.add(StatCollector.func_74838_a((String)"ttwaila.redstone"));
                } else {
                    currenttip.add(StatCollector.func_74838_a((String)"ttwaila.autonomous"));
                }
            }
        }
        if (accessor.getBlock() == ThaumicTinkerer.registry.getFirstBlockFromClass(BlockTransvectorInterface.class)) {
            TileTransvectorInterface tileTrans = (TileTransvectorInterface)accessor.getTileEntity();
            TileEntity tile = tileTrans.getTile();
            String currentBlock = tile == null ? StatCollector.func_74838_a((String)"ttwaila.nothing") : tile.func_145838_q().func_149732_F();
            currenttip.add(StatCollector.func_74837_a((String)"ttwaila.connected", (Object[])new Object[]{currentBlock}));
            if (tile != null) {
                currenttip.add(String.format("x: %d y: %d z: %d", tile.field_145851_c, tile.field_145848_d, tile.field_145849_e));
            }
        }
        if (accessor.getBlock() == ThaumicTinkerer.registry.getFirstBlockFromClass(BlockRepairer.class) && (item = (tileRepair = (TileRepairer)accessor.getTileEntity()).func_70301_a(0)) != null) {
            if (item.func_77960_j() > 0) {
                currenttip.add(StatCollector.func_74837_a((String)"ttwaila.repairing", (Object[])new Object[]{item.func_82833_r()}));
            } else {
                currenttip.add(StatCollector.func_74837_a((String)"ttwaila.finishedRepairing", (Object[])new Object[]{item.func_82833_r()}));
            }
        }
        if (accessor.getBlock() == ThaumicTinkerer.registry.getFirstBlockFromClass(BlockWarpGate.class)) {
            TileWarpGate tileWarp = (TileWarpGate)accessor.getTileEntity();
            if (tileWarp.locked) {
                currenttip.add(StatCollector.func_74838_a((String)"ttwaila.allowIncoming"));
            } else {
                currenttip.add(StatCollector.func_74838_a((String)"ttwaila.disallowIncoming"));
            }
        }
        return currenttip;
    }

    public List<String> getWailaTail(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    public NBTTagCompound getNBTData(EntityPlayerMP player, TileEntity te, NBTTagCompound tag, World world, int x, int y, int z) {
        return tag;
    }
}

