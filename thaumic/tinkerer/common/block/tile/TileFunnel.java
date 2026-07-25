/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockHopper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityHopper
 *  net.minecraft.util.Facing
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.aspects.IAspectContainer
 *  thaumcraft.api.aspects.IEssentiaContainerItem
 *  thaumcraft.common.blocks.ItemJarFilled
 *  thaumcraft.common.tiles.TileJarFillable
 *  thaumcraft.common.tiles.TileJarFillableVoid
 */
package thaumic.tinkerer.common.block.tile;

import appeng.api.movable.IMovableTile;
import net.minecraft.block.BlockHopper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.Facing;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.common.blocks.ItemJarFilled;
import thaumcraft.common.tiles.TileJarFillable;
import thaumcraft.common.tiles.TileJarFillableVoid;

public class TileFunnel
extends TileEntity
implements ISidedInventory,
IAspectContainer,
IMovableTile {
    ItemStack[] inventorySlots = new ItemStack[1];

    public void func_145845_h() {
        ItemJarFilled item;
        AspectList aspectList;
        ItemStack jar = this.func_70301_a(0);
        if (jar != null && jar.func_77973_b() instanceof ItemJarFilled && !this.field_145850_b.field_72995_K && (aspectList = (item = (ItemJarFilled)jar.func_77973_b()).getAspects(jar)) != null && aspectList.size() == 1) {
            TileEntity tile1;
            Aspect aspect = aspectList.getAspects()[0];
            TileEntity tile = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
            if (tile != null && tile instanceof TileEntityHopper && (tile1 = this.getHopperFacing(tile.field_145851_c, tile.field_145848_d, tile.field_145849_e, tile.func_145832_p())) instanceof TileJarFillable) {
                TileJarFillable jar1 = (TileJarFillable)tile1;
                boolean voidJar = tile1 instanceof TileJarFillableVoid;
                AspectList aspectList1 = jar1.getAspects();
                if (aspectList1 != null && aspectList1.size() == 0 && (jar1.aspectFilter == null || jar1.aspectFilter == aspect) || aspectList1.getAspects()[0] == aspect && (aspectList1.getAmount(aspectList1.getAspects()[0]) < 64 || voidJar)) {
                    jar1.addToContainer(aspect, 1);
                    item.setAspects(jar, aspectList.remove(aspect, 1));
                }
            }
        }
    }

    public void func_70296_d() {
        super.func_70296_d();
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    private TileEntity getHopperFacing(int x, int y, int z, int meta) {
        int i = BlockHopper.func_149918_b((int)meta);
        return this.field_145850_b.func_147438_o(x + Facing.field_71586_b[i], y + Facing.field_71587_c[i], z + Facing.field_71585_d[i]);
    }

    public void func_145839_a(NBTTagCompound par1NBTTagCompound) {
        super.func_145839_a(par1NBTTagCompound);
        this.readCustomNBT(par1NBTTagCompound);
    }

    public void func_145841_b(NBTTagCompound par1NBTTagCompound) {
        super.func_145841_b(par1NBTTagCompound);
        this.writeCustomNBT(par1NBTTagCompound);
    }

    public void readCustomNBT(NBTTagCompound par1NBTTagCompound) {
        NBTTagList var2 = par1NBTTagCompound.func_150295_c("Items", 10);
        this.inventorySlots = new ItemStack[this.func_70302_i_()];
        for (int var3 = 0; var3 < var2.func_74745_c(); ++var3) {
            NBTTagCompound var4 = var2.func_150305_b(var3);
            byte var5 = var4.func_74771_c("Slot");
            if (var5 < 0 || var5 >= this.inventorySlots.length) continue;
            this.inventorySlots[var5] = ItemStack.func_77949_a((NBTTagCompound)var4);
        }
    }

    public void writeCustomNBT(NBTTagCompound par1NBTTagCompound) {
        NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < this.inventorySlots.length; ++var3) {
            if (this.inventorySlots[var3] == null) continue;
            NBTTagCompound var4 = new NBTTagCompound();
            var4.func_74774_a("Slot", (byte)var3);
            this.inventorySlots[var3].func_77955_b(var4);
            var2.func_74742_a((NBTBase)var4);
        }
        par1NBTTagCompound.func_74782_a("Items", (NBTBase)var2);
    }

    public int func_70302_i_() {
        return this.inventorySlots.length;
    }

    public ItemStack func_70301_a(int i) {
        AspectList aspects = this.getAspects();
        return this.inventorySlots[i];
    }

    public ItemStack func_70298_a(int i, int j) {
        if (this.inventorySlots[i] != null) {
            if (this.inventorySlots[i].field_77994_a <= j) {
                ItemStack stackAt = this.inventorySlots[i];
                this.inventorySlots[i] = null;
                return stackAt;
            }
            ItemStack stackAt = this.inventorySlots[i].func_77979_a(j);
            if (this.inventorySlots[i].field_77994_a == 0) {
                this.inventorySlots[i] = null;
            }
            return stackAt;
        }
        return null;
    }

    public ItemStack func_70304_b(int i) {
        return this.func_70301_a(i);
    }

    public void func_70299_a(int i, ItemStack itemstack) {
        this.inventorySlots[i] = itemstack;
    }

    public String func_145825_b() {
        return "funnel";
    }

    public boolean func_145818_k_() {
        return false;
    }

    public int func_70297_j_() {
        return 1;
    }

    public boolean func_70300_a(EntityPlayer entityplayer) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) == this && entityplayer.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int i, ItemStack itemstack) {
        return true;
    }

    public S35PacketUpdateTileEntity getDescriptionPacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeCustomNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, -999, nbttagcompound);
    }

    public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
        super.onDataPacket(manager, packet);
        this.readCustomNBT(packet.func_148857_g());
    }

    public int[] func_94128_d(int var1) {
        int[] nArray;
        if (var1 == ForgeDirection.DOWN.ordinal()) {
            nArray = new int[]{};
        } else {
            int[] nArray2 = new int[1];
            nArray = nArray2;
            nArray2[0] = 0;
        }
        return nArray;
    }

    public boolean func_102007_a(int i, ItemStack itemstack, int j) {
        return itemstack != null && itemstack.func_77973_b() instanceof ItemJarFilled;
    }

    public boolean func_102008_b(int i, ItemStack itemstack, int j) {
        return j != ForgeDirection.DOWN.ordinal();
    }

    public AspectList getAspects() {
        ItemStack stack = this.inventorySlots[0];
        return stack != null && stack.func_77973_b() instanceof IEssentiaContainerItem ? ((IEssentiaContainerItem)stack.func_77973_b()).getAspects(stack) : null;
    }

    public void setAspects(AspectList paramAspectList) {
    }

    public boolean doesContainerAccept(Aspect paramAspect) {
        return false;
    }

    public int addToContainer(Aspect paramAspect, int paramInt) {
        return 0;
    }

    public boolean takeFromContainer(Aspect paramAspect, int paramInt) {
        return false;
    }

    public boolean takeFromContainer(AspectList paramAspectList) {
        return false;
    }

    public boolean doesContainerContainAmount(Aspect paramAspect, int paramInt) {
        return false;
    }

    public boolean doesContainerContain(AspectList paramAspectList) {
        return false;
    }

    public int containerContains(Aspect paramAspect) {
        return 0;
    }

    @Override
    public boolean prepareToMove() {
        return true;
    }

    @Override
    public void doneMoving() {
    }
}

