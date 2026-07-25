/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  mcp.mobius.waila.api.IWailaConfigHandler
 *  mcp.mobius.waila.api.IWailaDataAccessor
 *  mcp.mobius.waila.api.IWailaDataProvider
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
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumic.tinkerer.common.block.tile.TileMagnet;
import thaumic.tinkerer.common.block.tile.TileMobMagnet;
import thaumic.tinkerer.common.item.ItemSoulMould;

public class MagnetProvider
implements IWailaDataProvider {
    public ItemStack getWailaStack(IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return null;
    }

    public List<String> getWailaHead(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currenttip;
    }

    public List<String> getWailaBody(ItemStack itemStack, List<String> currenttip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        boolean isPulling;
        boolean mobMagnet = accessor.getTileEntity() instanceof TileMobMagnet;
        TileMagnet tileMagnet = (TileMagnet)accessor.getTileEntity();
        boolean bl = isPulling = (tileMagnet.func_145832_p() & 1) == 0;
        if (isPulling) {
            currenttip.add(StatCollector.func_74838_a((String)"ttwaila.pullingMode"));
        } else {
            currenttip.add(StatCollector.func_74838_a((String)"ttwaila.pushingMode"));
        }
        if (mobMagnet) {
            TileMobMagnet tileMob = (TileMobMagnet)tileMagnet;
            if (tileMob.func_70301_a(0) == null) {
                if (isPulling) {
                    if (tileMob.adult) {
                        currenttip.add(StatCollector.func_74838_a((String)"ttwaila.pullingAdult"));
                    } else {
                        currenttip.add(StatCollector.func_74838_a((String)"ttwaila.pullingChild"));
                    }
                } else if (tileMob.adult) {
                    currenttip.add(StatCollector.func_74838_a((String)"ttwaila.pushingAdult"));
                } else {
                    currenttip.add(StatCollector.func_74838_a((String)"ttwaila.pushingChild"));
                }
            } else {
                String name = ItemSoulMould.getPatternName(tileMob.func_70301_a(0));
                name = StatCollector.func_74838_a((String)("entity." + name + ".name"));
                if (isPulling) {
                    if (tileMob.adult) {
                        currenttip.add(StatCollector.func_74837_a((String)"ttwaila.pullingAdultType", (Object[])new Object[]{name}));
                    } else {
                        currenttip.add(StatCollector.func_74837_a((String)"ttwaila.pullingChildType", (Object[])new Object[]{name}));
                    }
                } else if (tileMob.adult) {
                    currenttip.add(StatCollector.func_74837_a((String)"ttwaila.pushingAdultType", (Object[])new Object[]{name}));
                } else {
                    currenttip.add(StatCollector.func_74837_a((String)"ttwaila.pushingChildType", (Object[])new Object[]{name}));
                }
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

