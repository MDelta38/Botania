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
package flaxbeard.thaumicexploration.interop;

import flaxbeard.thaumicexploration.tile.TileEntitySoulBrazier;
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

public class SoulBrazierProvider
implements IWailaDataProvider {
    public ItemStack getWailaStack(IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        return null;
    }

    public List<String> getWailaHead(ItemStack itemStack, List<String> list, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        return list;
    }

    public List<String> getWailaBody(ItemStack itemStack, List<String> list, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        if (!(iWailaDataAccessor.getTileEntity() instanceof TileEntitySoulBrazier)) {
            return list;
        }
        TileEntitySoulBrazier brazier = (TileEntitySoulBrazier)iWailaDataAccessor.getTileEntity();
        list.add(StatCollector.func_74837_a((String)"ttwaila.soulBrazier.essentia", (Object[])new Object[]{brazier.currentEssentia}));
        list.add(StatCollector.func_74837_a((String)"ttwaila.soulBrazier.vis", (Object[])new Object[]{brazier.currentVis}));
        if (brazier.active) {
            list.add(StatCollector.func_74838_a((String)"ttwaila.soulBrazier.active"));
            list.add(StatCollector.func_74837_a((String)"ttwaila.soulBrazier.warp", (Object[])new Object[]{brazier.storedWarp}));
        } else {
            list.add(StatCollector.func_74838_a((String)"ttwaila.soulBrazier.notActive"));
        }
        list.add(StatCollector.func_74837_a((String)"ttwaila.soulBrazier.owner", (Object[])new Object[]{brazier.owner.getName()}));
        return list;
    }

    public List<String> getWailaTail(ItemStack itemStack, List<String> list, IWailaDataAccessor iWailaDataAccessor, IWailaConfigHandler iWailaConfigHandler) {
        return list;
    }

    public NBTTagCompound getNBTData(EntityPlayerMP entityPlayerMP, TileEntity tileEntity, NBTTagCompound nbtTagCompound, World world, int i, int i1, int i2) {
        return nbtTagCompound;
    }
}

