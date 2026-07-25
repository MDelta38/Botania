/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.api.wands.IWandable;
import thaumcraft.common.config.ConfigBlocks;

public class TileAlembic
extends TileThaumcraft
implements IAspectContainer,
IWandable,
IEssentiaTransport {
    public Aspect aspect;
    public Aspect aspectFilter = null;
    public int amount = 0;
    public int maxAmount = 32;
    public int facing = 2;
    public boolean aboveAlembic = false;
    public boolean aboveFurnace = false;
    ForgeDirection fd = null;

    @Override
    public AspectList getAspects() {
        return this.aspect != null ? new AspectList().add(this.aspect, this.amount) : new AspectList();
    }

    @Override
    public void setAspects(AspectList aspects) {
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)(this.field_145851_c - 1), (double)this.field_145848_d, (double)(this.field_145849_e - 1), (double)(this.field_145851_c + 2), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 2));
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.facing = nbttagcompound.func_74771_c("facing");
        this.aspectFilter = Aspect.getAspect(nbttagcompound.func_74779_i("AspectFilter"));
        String tag = nbttagcompound.func_74779_i("aspect");
        if (tag != null) {
            this.aspect = Aspect.getAspect(tag);
        }
        this.amount = nbttagcompound.func_74765_d("amount");
        this.fd = ForgeDirection.getOrientation((int)this.facing);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        if (this.aspect != null) {
            nbttagcompound.func_74778_a("aspect", this.aspect.getTag());
        }
        if (this.aspectFilter != null) {
            nbttagcompound.func_74778_a("AspectFilter", this.aspectFilter.getTag());
        }
        nbttagcompound.func_74777_a("amount", (short)this.amount);
        nbttagcompound.func_74774_a("facing", (byte)this.facing);
    }

    public boolean canUpdate() {
        return false;
    }

    @Override
    public int addToContainer(Aspect tt, int am) {
        if (this.amount < this.maxAmount && tt == this.aspect || this.amount == 0) {
            this.aspect = tt;
            int added = Math.min(am, this.maxAmount - this.amount);
            this.amount += added;
            am -= added;
        }
        this.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        return am;
    }

    @Override
    public boolean takeFromContainer(Aspect tt, int am) {
        if (this.amount == 0 || this.aspect == null) {
            this.aspect = null;
            this.amount = 0;
        }
        if (this.aspect != null && this.amount >= am && tt == this.aspect) {
            this.amount -= am;
            if (this.amount <= 0) {
                this.aspect = null;
                this.amount = 0;
            }
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            return true;
        }
        return false;
    }

    @Override
    public boolean doesContainerContain(AspectList ot) {
        return this.amount > 0 && this.aspect != null && ot.getAmount(this.aspect) > 0;
    }

    @Override
    public boolean doesContainerContainAmount(Aspect tt, int am) {
        return this.amount >= am && tt == this.aspect;
    }

    @Override
    public int containerContains(Aspect tt) {
        return tt == this.aspect ? this.amount : 0;
    }

    @Override
    public boolean doesContainerAccept(Aspect tag) {
        return true;
    }

    @Override
    public boolean takeFromContainer(AspectList ot) {
        return false;
    }

    public void getAppearance() {
        this.aboveAlembic = false;
        this.aboveFurnace = false;
        if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == ConfigBlocks.blockStoneDevice && this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == 0) {
            this.aboveFurnace = true;
        }
        if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == ConfigBlocks.blockMetalDevice && this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == this.func_145832_p()) {
            this.aboveAlembic = true;
        }
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        this.getAppearance();
    }

    @Override
    public int onWandRightClick(World world, ItemStack wandstack, EntityPlayer player, int x, int y, int z, int side, int md) {
        if (side <= 1) {
            return 0;
        }
        this.facing = side;
        this.fd = ForgeDirection.getOrientation((int)this.facing);
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
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

    @Override
    public boolean isConnectable(ForgeDirection face) {
        return face != ForgeDirection.getOrientation((int)this.facing) && face != ForgeDirection.DOWN;
    }

    @Override
    public boolean canInputFrom(ForgeDirection face) {
        return false;
    }

    @Override
    public boolean canOutputTo(ForgeDirection face) {
        return face != ForgeDirection.getOrientation((int)this.facing) && face != ForgeDirection.DOWN;
    }

    @Override
    public void setSuction(Aspect aspect, int amount) {
    }

    @Override
    public Aspect getSuctionType(ForgeDirection loc) {
        return null;
    }

    @Override
    public int getSuctionAmount(ForgeDirection loc) {
        return 0;
    }

    @Override
    public Aspect getEssentiaType(ForgeDirection loc) {
        return this.aspect;
    }

    @Override
    public int getEssentiaAmount(ForgeDirection loc) {
        return this.amount;
    }

    @Override
    public int takeEssentia(Aspect aspect, int amount, ForgeDirection face) {
        return this.canOutputTo(face) && this.takeFromContainer(aspect, amount) ? amount : 0;
    }

    @Override
    public int addEssentia(Aspect aspect, int amount, ForgeDirection loc) {
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

