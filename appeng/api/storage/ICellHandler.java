/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package appeng.api.storage;

import appeng.api.implementations.tiles.IChestOrDrive;
import appeng.api.storage.IMEInventory;
import appeng.api.storage.IMEInventoryHandler;
import appeng.api.storage.ISaveProvider;
import appeng.api.storage.StorageChannel;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public interface ICellHandler {
    public boolean isCell(ItemStack var1);

    public IMEInventoryHandler getCellInventory(ItemStack var1, ISaveProvider var2, StorageChannel var3);

    @SideOnly(value=Side.CLIENT)
    public IIcon getTopTexture_Light();

    @SideOnly(value=Side.CLIENT)
    public IIcon getTopTexture_Medium();

    @SideOnly(value=Side.CLIENT)
    public IIcon getTopTexture_Dark();

    public void openChestGui(EntityPlayer var1, IChestOrDrive var2, ICellHandler var3, IMEInventoryHandler var4, ItemStack var5, StorageChannel var6);

    public int getStatusForCell(ItemStack var1, IMEInventory var2);

    public double cellIdleDrain(ItemStack var1, IMEInventory var2);
}

