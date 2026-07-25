/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 */
package appeng.api.parts;

import appeng.api.parts.IPart;
import appeng.api.parts.LayerFlags;
import java.util.Set;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class LayerBase
extends TileEntity {
    public IPart getPart(ForgeDirection side) {
        return null;
    }

    public void notifyNeighbors() {
    }

    public void partChanged() {
    }

    public Set<LayerFlags> getLayerFlags() {
        return null;
    }

    public void markForSave() {
    }
}

