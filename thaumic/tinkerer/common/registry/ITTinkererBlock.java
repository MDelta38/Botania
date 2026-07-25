/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.tileentity.TileEntity
 */
package thaumic.tinkerer.common.registry;

import java.util.ArrayList;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import thaumic.tinkerer.common.registry.ITTinkererRegisterable;

public interface ITTinkererBlock
extends ITTinkererRegisterable {
    public ArrayList<Object> getSpecialParameters();

    public String getBlockName();

    public boolean shouldRegister();

    public boolean shouldDisplayInTab();

    public Class<? extends ItemBlock> getItemBlock();

    public Class<? extends TileEntity> getTileEntity();
}

