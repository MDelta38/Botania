/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.Vec3
 *  net.minecraftforge.common.util.ForgeDirection
 */
package appeng.api.parts;

import appeng.api.parts.IFacadeContainer;
import appeng.api.parts.IPart;
import appeng.api.parts.LayerFlags;
import appeng.api.parts.SelectedPart;
import appeng.api.util.AEColor;
import appeng.api.util.DimensionalCoord;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.ForgeDirection;

public interface IPartHost {
    public IFacadeContainer getFacadeContainer();

    public boolean canAddPart(ItemStack var1, ForgeDirection var2);

    public ForgeDirection addPart(ItemStack var1, ForgeDirection var2, EntityPlayer var3);

    public IPart getPart(ForgeDirection var1);

    public void removePart(ForgeDirection var1, boolean var2);

    public void markForUpdate();

    public DimensionalCoord getLocation();

    public TileEntity getTile();

    public AEColor getColor();

    public void clearContainer();

    public boolean isBlocked(ForgeDirection var1);

    public SelectedPart selectPart(Vec3 var1);

    public void markForSave();

    public void partChanged();

    public boolean hasRedstone(ForgeDirection var1);

    public boolean isEmpty();

    public Set<LayerFlags> getLayerFlags();

    public void cleanup();

    public void notifyNeighbors();

    public boolean isInWorld();
}

