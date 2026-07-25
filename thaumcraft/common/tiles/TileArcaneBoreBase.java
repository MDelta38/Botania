/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.api.wands.IWandable;

public class TileArcaneBoreBase
extends TileThaumcraft
implements IWandable,
IEssentiaTransport {
    public ForgeDirection orientation = ForgeDirection.getOrientation((int)2);

    public boolean canUpdate() {
        return false;
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.orientation = ForgeDirection.getOrientation((int)nbttagcompound.func_74762_e("orientation"));
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74768_a("orientation", this.orientation.ordinal());
    }

    @Override
    public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
        this.orientation = ForgeDirection.getOrientation((int)side);
        player.field_70170_p.func_72980_b((double)x + 0.5, (double)y + 0.5, (double)z + 0.5, "thaumcraft:tool", 0.3f, 1.9f + player.field_70170_p.field_73012_v.nextFloat() * 0.2f, false);
        player.func_71038_i();
        this.func_70296_d();
        return 0;
    }

    @Override
    public ItemStack onWandRightClick(World world, ItemStack wandstack, EntityPlayer player) {
        return null;
    }

    @Override
    public void onUsingWandTick(ItemStack wandstack, EntityPlayer player, int count) {
    }

    @Override
    public void onWandStoppedUsing(ItemStack wandstack, World world, EntityPlayer player, int count) {
    }

    boolean drawEssentia() {
        for (ForgeDirection facing : ForgeDirection.VALID_DIRECTIONS) {
            TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, facing);
            if (te == null) continue;
            IEssentiaTransport ic = (IEssentiaTransport)te;
            if (!ic.canOutputTo(facing.getOpposite())) {
                return false;
            }
            if (ic.getSuctionAmount(facing.getOpposite()) >= this.getSuctionAmount(facing) || ic.takeEssentia(Aspect.ENTROPY, 1, facing.getOpposite()) != 1) continue;
            return true;
        }
        return false;
    }

    @Override
    public boolean isConnectable(ForgeDirection face) {
        return true;
    }

    @Override
    public boolean canInputFrom(ForgeDirection face) {
        return true;
    }

    @Override
    public boolean canOutputTo(ForgeDirection face) {
        return false;
    }

    @Override
    public void setSuction(Aspect aspect, int amount) {
    }

    @Override
    public Aspect getSuctionType(ForgeDirection face) {
        return Aspect.ENTROPY;
    }

    @Override
    public int getSuctionAmount(ForgeDirection face) {
        return face != this.orientation ? 128 : 0;
    }

    @Override
    public int takeEssentia(Aspect aspect, int amount, ForgeDirection face) {
        return 0;
    }

    @Override
    public int addEssentia(Aspect aspect, int amount, ForgeDirection face) {
        return 0;
    }

    @Override
    public Aspect getEssentiaType(ForgeDirection face) {
        return null;
    }

    @Override
    public int getEssentiaAmount(ForgeDirection face) {
        return 0;
    }

    @Override
    public int getMinimumSuction() {
        return 0;
    }

    @Override
    public boolean renderExtendedTube() {
        return true;
    }
}

