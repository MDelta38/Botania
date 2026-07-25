/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.fluids.FluidStack
 */
package appeng.api.storage;

import appeng.api.networking.crafting.ICraftingLink;
import appeng.api.networking.crafting.ICraftingRequester;
import appeng.api.networking.energy.IEnergySource;
import appeng.api.networking.security.BaseActionSource;
import appeng.api.storage.IMEInventory;
import appeng.api.storage.data.IAEFluidStack;
import appeng.api.storage.data.IAEItemStack;
import appeng.api.storage.data.IItemList;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;

public interface IStorageHelper {
    public ICraftingLink loadCraftingLink(NBTTagCompound var1, ICraftingRequester var2);

    public IAEItemStack createItemStack(ItemStack var1);

    public IAEFluidStack createFluidStack(FluidStack var1);

    public IItemList<IAEItemStack> createItemList();

    public IItemList<IAEFluidStack> createFluidList();

    public IAEItemStack readItemFromPacket(ByteBuf var1) throws IOException;

    public IAEFluidStack readFluidFromPacket(ByteBuf var1) throws IOException;

    public IAEItemStack poweredExtraction(IEnergySource var1, IMEInventory<IAEItemStack> var2, IAEItemStack var3, BaseActionSource var4);

    public IAEItemStack poweredInsert(IEnergySource var1, IMEInventory<IAEItemStack> var2, IAEItemStack var3, BaseActionSource var4);
}

