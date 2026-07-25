/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cofh.api.energy.IEnergyHandler
 *  cofh.api.energy.IEnergyProvider
 *  cofh.api.energy.IEnergyReceiver
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.Optional$Interface
 *  cpw.mods.fml.common.Optional$InterfaceList
 *  cpw.mods.fml.common.Optional$Method
 *  dan200.computercraft.api.lua.ILuaContext
 *  dan200.computercraft.api.lua.LuaException
 *  dan200.computercraft.api.peripheral.IComputerAccess
 *  dan200.computercraft.api.peripheral.IPeripheral
 *  ic2.api.energy.tile.IEnergyAcceptor
 *  ic2.api.energy.tile.IEnergySink
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 *  net.minecraftforge.fluids.Fluid
 *  net.minecraftforge.fluids.FluidStack
 *  net.minecraftforge.fluids.FluidTankInfo
 *  net.minecraftforge.fluids.IFluidHandler
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.aspects.IAspectContainer
 *  thaumcraft.api.aspects.IEssentiaTransport
 */
package thaumic.tinkerer.common.block.tile.transvector;

import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergySink;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumic.tinkerer.common.block.tile.transvector.TileTransvector;
import thaumic.tinkerer.common.compat.IndustrialcraftUnloadHelper;

@Optional.InterfaceList(value={@Optional.Interface(iface="dan200.computercraft.api.peripheral.IPeripheral", modid="ComputerCraft"), @Optional.Interface(iface="cofh.api.energy.IEnergyHandler", modid="CoFHCore"), @Optional.Interface(iface="cofh.api.energy.IEnergyReceiver", modid="CoFHCore"), @Optional.Interface(iface="cofh.api.energy.IEnergyProvider", modid="CoFHCore"), @Optional.Interface(iface="ic2.api.energy.tile.IEnergySink", modid="IC2")})
public class TileTransvectorInterface
extends TileTransvector
implements ISidedInventory,
IEnergySink,
IFluidHandler,
IEnergyHandler,
IEnergyReceiver,
IAspectContainer,
IEssentiaTransport,
IPeripheral,
IEnergyProvider {
    public boolean addedToICEnergyNet = false;

    public static int[] buildSlotsForLinearInventory(IInventory inv) {
        int[] slots = new int[inv.func_70302_i_()];
        for (int i = 0; i < slots.length; ++i) {
            slots[i] = i;
        }
        return slots;
    }

    public void func_145845_h() {
        if (this.field_145850_b.func_82737_E() % 100L == 0L) {
            this.field_145850_b.func_147444_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e));
        }
        if (!this.addedToICEnergyNet && !this.field_145850_b.field_72995_K && Loader.isModLoaded((String)"IC2")) {
            IndustrialcraftUnloadHelper.addToIC2EnergyNet(this);
            this.addedToICEnergyNet = true;
        }
    }

    @Override
    public int getMaxDistance() {
        return 4;
    }

    public void func_145843_s() {
        if (Loader.isModLoaded((String)"IC2")) {
            IndustrialcraftUnloadHelper.removeFromIC2EnergyNet(this);
        }
        super.func_145843_s();
    }

    public void onChunkUnload() {
        if (Loader.isModLoaded((String)"IC2")) {
            IndustrialcraftUnloadHelper.removeFromIC2EnergyNet(this);
        }
    }

    public void func_70296_d() {
        super.func_70296_d();
        TileEntity tile = this.getTile();
        if (tile != null) {
            tile.func_70296_d();
        }
    }

    public int func_70302_i_() {
        TileEntity tile = this.getTile();
        return tile instanceof IInventory ? ((IInventory)tile).func_70302_i_() : 0;
    }

    public ItemStack func_70301_a(int i) {
        TileEntity tile = this.getTile();
        return tile instanceof IInventory ? ((IInventory)tile).func_70301_a(i) : null;
    }

    public ItemStack func_70298_a(int i, int j) {
        TileEntity tile = this.getTile();
        return tile instanceof IInventory ? ((IInventory)tile).func_70298_a(i, j) : null;
    }

    public ItemStack func_70304_b(int i) {
        TileEntity tile = this.getTile();
        return tile instanceof IInventory ? ((IInventory)tile).func_70304_b(i) : null;
    }

    public void func_70299_a(int i, ItemStack itemstack) {
        TileEntity tile = this.getTile();
        if (tile instanceof IInventory) {
            ((IInventory)tile).func_70299_a(i, itemstack);
        }
    }

    public String func_145825_b() {
        TileEntity tile = this.getTile();
        return tile instanceof IInventory ? ((IInventory)tile).func_145825_b() : "";
    }

    public boolean func_145818_k_() {
        TileEntity tile = this.getTile();
        return tile instanceof IInventory && ((IInventory)tile).func_145818_k_();
    }

    public int func_70297_j_() {
        TileEntity tile = this.getTile();
        return tile instanceof IInventory ? ((IInventory)tile).func_70297_j_() : 0;
    }

    public boolean func_70300_a(EntityPlayer entityplayer) {
        TileEntity tile = this.getTile();
        return tile instanceof IInventory && ((IInventory)tile).func_70300_a(entityplayer);
    }

    public void func_70295_k_() {
        TileEntity tile = this.getTile();
        if (tile instanceof IInventory) {
            ((IInventory)tile).func_70295_k_();
        }
    }

    public void func_70305_f() {
        TileEntity tile = this.getTile();
        if (tile instanceof IInventory) {
            ((IInventory)tile).func_70305_f();
        }
    }

    public boolean func_94041_b(int i, ItemStack itemstack) {
        TileEntity tile = this.getTile();
        return tile instanceof IInventory && ((IInventory)tile).func_94041_b(i, itemstack);
    }

    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        TileEntity tile = this.getTile();
        return tile instanceof IFluidHandler ? ((IFluidHandler)tile).fill(from, resource, doFill) : 0;
    }

    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        TileEntity tile = this.getTile();
        return tile instanceof IFluidHandler ? ((IFluidHandler)tile).drain(from, resource, doDrain) : null;
    }

    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        TileEntity tile = this.getTile();
        return tile instanceof IFluidHandler ? ((IFluidHandler)tile).drain(from, maxDrain, doDrain) : null;
    }

    public boolean canFill(ForgeDirection from, Fluid fluid) {
        TileEntity tile = this.getTile();
        return tile instanceof IFluidHandler && ((IFluidHandler)tile).canFill(from, fluid);
    }

    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        TileEntity tile = this.getTile();
        return tile instanceof IFluidHandler && ((IFluidHandler)tile).canDrain(from, fluid);
    }

    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        TileEntity tile = this.getTile();
        return tile instanceof IFluidHandler ? ((IFluidHandler)tile).getTankInfo(from) : null;
    }

    public int[] func_94128_d(int var1) {
        TileEntity tile = this.getTile();
        return tile instanceof ISidedInventory ? ((ISidedInventory)tile).func_94128_d(var1) : (tile instanceof IInventory ? TileTransvectorInterface.buildSlotsForLinearInventory((IInventory)tile) : new int[]{});
    }

    public boolean func_102007_a(int i, ItemStack itemstack, int j) {
        TileEntity tile = this.getTile();
        return tile instanceof ISidedInventory ? ((ISidedInventory)tile).func_102007_a(i, itemstack, j) : tile instanceof IInventory;
    }

    public boolean func_102008_b(int i, ItemStack itemstack, int j) {
        TileEntity tile = this.getTile();
        return tile instanceof ISidedInventory ? ((ISidedInventory)tile).func_102008_b(i, itemstack, j) : tile instanceof IInventory;
    }

    @Optional.Method(modid="IC2")
    public double getDemandedEnergy() {
        TileEntity tile = this.getTile();
        return tile instanceof IEnergySink ? ((IEnergySink)tile).getDemandedEnergy() : 0.0;
    }

    @Optional.Method(modid="IC2")
    public double injectEnergy(ForgeDirection directionFrom, double amount, double voltage) {
        TileEntity tile = this.getTile();
        return tile instanceof IEnergySink ? ((IEnergySink)tile).injectEnergy(directionFrom, amount, voltage) : 0.0;
    }

    @Optional.Method(modid="IC2")
    public int getSinkTier() {
        TileEntity tile = this.getTile();
        return tile instanceof IEnergySink ? ((IEnergySink)tile).getSinkTier() : 0;
    }

    @Optional.Method(modid="CoFHLib")
    public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
        TileEntity tile = this.getTile();
        return tile instanceof IEnergyHandler ? ((IEnergyHandler)tile).receiveEnergy(from, maxReceive, simulate) : 0;
    }

    @Optional.Method(modid="CoFHLib")
    public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
        TileEntity tile = this.getTile();
        return tile instanceof IEnergyHandler ? ((IEnergyHandler)tile).extractEnergy(from, maxExtract, simulate) : 0;
    }

    @Optional.Method(modid="CoFHLib")
    public int getEnergyStored(ForgeDirection from) {
        TileEntity tile = this.getTile();
        return tile instanceof IEnergyHandler ? ((IEnergyHandler)tile).getEnergyStored(from) : 0;
    }

    @Optional.Method(modid="CoFHLib")
    public int getMaxEnergyStored(ForgeDirection from) {
        TileEntity tile = this.getTile();
        return tile instanceof IEnergyHandler ? ((IEnergyHandler)tile).getMaxEnergyStored(from) : 0;
    }

    @Optional.Method(modid="CoFHLib")
    public boolean canConnectEnergy(ForgeDirection from) {
        TileEntity tile = this.getTile();
        return tile instanceof IEnergyHandler ? ((IEnergyHandler)tile).canConnectEnergy(from) : false;
    }

    public AspectList getAspects() {
        TileEntity tile = this.getTile();
        return tile instanceof IAspectContainer ? ((IAspectContainer)tile).getAspects() : null;
    }

    public void setAspects(AspectList paramAspectList) {
        TileEntity tile = this.getTile();
        if (tile != null) {
            ((IAspectContainer)tile).setAspects(paramAspectList);
        }
    }

    public boolean doesContainerAccept(Aspect paramAspect) {
        TileEntity tile = this.getTile();
        return tile instanceof IAspectContainer && ((IAspectContainer)tile).doesContainerAccept(paramAspect);
    }

    public int addToContainer(Aspect paramAspect, int paramInt) {
        TileEntity tile = this.getTile();
        return tile instanceof IAspectContainer ? ((IAspectContainer)tile).addToContainer(paramAspect, paramInt) : 0;
    }

    public boolean takeFromContainer(Aspect paramAspect, int paramInt) {
        TileEntity tile = this.getTile();
        return tile instanceof IAspectContainer && ((IAspectContainer)tile).takeFromContainer(paramAspect, paramInt);
    }

    public boolean takeFromContainer(AspectList paramAspectList) {
        TileEntity tile = this.getTile();
        return tile instanceof IAspectContainer && ((IAspectContainer)tile).takeFromContainer(paramAspectList);
    }

    public boolean doesContainerContainAmount(Aspect paramAspect, int paramInt) {
        TileEntity tile = this.getTile();
        return tile instanceof IAspectContainer && ((IAspectContainer)tile).doesContainerContainAmount(paramAspect, paramInt);
    }

    public boolean doesContainerContain(AspectList paramAspectList) {
        TileEntity tile = this.getTile();
        return tile instanceof IAspectContainer && ((IAspectContainer)tile).doesContainerContain(paramAspectList);
    }

    public int containerContains(Aspect paramAspect) {
        TileEntity tile = this.getTile();
        return tile instanceof IAspectContainer ? ((IAspectContainer)tile).containerContains(paramAspect) : 0;
    }

    public boolean isConnectable(ForgeDirection forgeDirection) {
        return true;
    }

    public boolean canInputFrom(ForgeDirection forgeDirection) {
        TileEntity tile = this.getTile();
        return tile instanceof IEssentiaTransport && ((IEssentiaTransport)tile).canInputFrom(forgeDirection);
    }

    public boolean canOutputTo(ForgeDirection forgeDirection) {
        TileEntity tile = this.getTile();
        return tile instanceof IEssentiaTransport && ((IEssentiaTransport)tile).canOutputTo(forgeDirection);
    }

    public void setSuction(Aspect paramAspect, int paramInt) {
        TileEntity tile = this.getTile();
        if (tile instanceof IEssentiaTransport) {
            ((IEssentiaTransport)tile).setSuction(paramAspect, paramInt);
        }
    }

    public Aspect getSuctionType(ForgeDirection forgeDirection) {
        TileEntity tile = this.getTile();
        if (tile instanceof IEssentiaTransport) {
            return ((IEssentiaTransport)tile).getSuctionType(forgeDirection);
        }
        return null;
    }

    public int getSuctionAmount(ForgeDirection forgeDirection) {
        TileEntity tile = this.getTile();
        if (tile instanceof IEssentiaTransport) {
            return ((IEssentiaTransport)tile).getSuctionAmount(forgeDirection);
        }
        return 0;
    }

    public int takeEssentia(Aspect paramAspect, int paramInt, ForgeDirection forgeDirection) {
        TileEntity tile = this.getTile();
        return tile instanceof IEssentiaTransport ? ((IEssentiaTransport)tile).takeEssentia(paramAspect, paramInt, forgeDirection) : 0;
    }

    public int getMinimumSuction() {
        TileEntity tile = this.getTile();
        return tile instanceof IEssentiaTransport ? ((IEssentiaTransport)tile).getMinimumSuction() : 0;
    }

    public boolean renderExtendedTube() {
        TileEntity tile = this.getTile();
        return tile instanceof IEssentiaTransport && ((IEssentiaTransport)tile).renderExtendedTube();
    }

    public int addEssentia(Aspect arg0, int arg1, ForgeDirection forgeDirection) {
        TileEntity tile = this.getTile();
        return tile instanceof IEssentiaTransport ? ((IEssentiaTransport)tile).addEssentia(arg0, arg1, forgeDirection) : 0;
    }

    public Aspect getEssentiaType(ForgeDirection forgeDirection) {
        TileEntity tile = this.getTile();
        return tile instanceof IEssentiaTransport ? ((IEssentiaTransport)tile).getEssentiaType(forgeDirection) : null;
    }

    public int getEssentiaAmount(ForgeDirection forgeDirection) {
        TileEntity tile = this.getTile();
        return tile instanceof IEssentiaTransport ? ((IEssentiaTransport)tile).getEssentiaAmount(forgeDirection) : 0;
    }

    @Optional.Method(modid="ComputerCraft")
    public String getType() {
        return this.getTile() instanceof IPeripheral ? ((IPeripheral)this.getTile()).getType() : "Transvector Interface Unconnected Peripherad";
    }

    @Optional.Method(modid="ComputerCraft")
    public String[] getMethodNames() {
        return this.getTile() instanceof IPeripheral ? ((IPeripheral)this.getTile()).getMethodNames() : new String[]{};
    }

    @Optional.Method(modid="ComputerCraft")
    public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) throws LuaException, InterruptedException {
        return this.getTile() instanceof IPeripheral ? ((IPeripheral)this.getTile()).callMethod(computer, context, method, arguments) : new Object[]{};
    }

    public void attach(IComputerAccess computer) {
        if (this.getTile() instanceof IPeripheral) {
            ((IPeripheral)this.getTile()).attach(computer);
        }
    }

    @Optional.Method(modid="ComputerCraft")
    public void detach(IComputerAccess computer) {
        if (this.getTile() instanceof IPeripheral) {
            ((IPeripheral)this.getTile()).detach(computer);
        }
    }

    @Optional.Method(modid="ComputerCraft")
    public boolean equals(IPeripheral other) {
        return this.equals((Object)other);
    }

    @Optional.Method(modid="IC2")
    public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {
        TileEntity tile = this.getTile();
        return tile instanceof IEnergyAcceptor ? ((IEnergySink)tile).acceptsEnergyFrom(emitter, direction) : false;
    }
}

