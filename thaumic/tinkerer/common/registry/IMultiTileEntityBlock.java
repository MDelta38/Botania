/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 */
package thaumic.tinkerer.common.registry;

import java.util.HashMap;
import net.minecraft.tileentity.TileEntity;
import thaumic.tinkerer.common.registry.ITTinkererBlock;

public interface IMultiTileEntityBlock
extends ITTinkererBlock {
    public HashMap<Class<? extends TileEntity>, String> getAdditionalTileEntities();
}

