/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.renderer.RenderBlocks
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package appeng.api.parts;

import appeng.api.networking.IGridNode;
import appeng.api.parts.BusSupport;
import appeng.api.parts.IBoxProvider;
import appeng.api.parts.IPartHost;
import appeng.api.parts.IPartRenderHelper;
import appeng.api.parts.PartItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public interface IPart
extends IBoxProvider {
    public ItemStack getItemStack(PartItemStack var1);

    @SideOnly(value=Side.CLIENT)
    public void renderInventory(IPartRenderHelper var1, RenderBlocks var2);

    @SideOnly(value=Side.CLIENT)
    public void renderStatic(int var1, int var2, int var3, IPartRenderHelper var4, RenderBlocks var5);

    @SideOnly(value=Side.CLIENT)
    public void renderDynamic(double var1, double var3, double var5, IPartRenderHelper var7, RenderBlocks var8);

    @SideOnly(value=Side.CLIENT)
    public IIcon getBreakingTexture();

    public boolean requireDynamicRender();

    public boolean isSolid();

    public boolean canConnectRedstone();

    public void writeToNBT(NBTTagCompound var1);

    public void readFromNBT(NBTTagCompound var1);

    public int getLightLevel();

    public boolean isLadder(EntityLivingBase var1);

    public void onNeighborChanged();

    public int isProvidingStrongPower();

    public int isProvidingWeakPower();

    public void writeToStream(ByteBuf var1) throws IOException;

    public boolean readFromStream(ByteBuf var1) throws IOException;

    public IGridNode getGridNode();

    public void onEntityCollision(Entity var1);

    public void removeFromWorld();

    public void addToWorld();

    public IGridNode getExternalFacingNode();

    public void setPartHostInfo(ForgeDirection var1, IPartHost var2, TileEntity var3);

    public boolean onActivate(EntityPlayer var1, Vec3 var2);

    public boolean onShiftActivate(EntityPlayer var1, Vec3 var2);

    public void getDrops(List<ItemStack> var1, boolean var2);

    public int cableConnectionRenderTo();

    public void randomDisplayTick(World var1, int var2, int var3, int var4, Random var5);

    public void onPlacement(EntityPlayer var1, ItemStack var2, ForgeDirection var3);

    public boolean canBePlacedOn(BusSupport var1);
}

