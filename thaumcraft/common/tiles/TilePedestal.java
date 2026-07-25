/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.util.AxisAlignedBB
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.common.Thaumcraft;

public class TilePedestal
extends TileThaumcraft
implements ISidedInventory {
    private static final int[] slots = new int[]{0};
    private ItemStack[] inventory = new ItemStack[1];
    private String customName;

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 2), (double)(this.field_145849_e + 1));
    }

    public int func_70302_i_() {
        return 1;
    }

    public ItemStack func_70301_a(int par1) {
        return this.inventory[par1];
    }

    public ItemStack func_70298_a(int par1, int par2) {
        if (this.inventory[par1] != null) {
            if (!this.field_145850_b.field_72995_K) {
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
            if (this.inventory[par1].field_77994_a <= par2) {
                ItemStack itemstack = this.inventory[par1];
                this.inventory[par1] = null;
                this.func_70296_d();
                return itemstack;
            }
            ItemStack itemstack = this.inventory[par1].func_77979_a(par2);
            if (this.inventory[par1].field_77994_a == 0) {
                this.inventory[par1] = null;
            }
            this.func_70296_d();
            return itemstack;
        }
        return null;
    }

    public ItemStack func_70304_b(int par1) {
        if (this.inventory[par1] != null) {
            ItemStack itemstack = this.inventory[par1];
            this.inventory[par1] = null;
            return itemstack;
        }
        return null;
    }

    public void func_70299_a(int par1, ItemStack par2ItemStack) {
        this.inventory[par1] = par2ItemStack;
        if (par2ItemStack != null && par2ItemStack.field_77994_a > this.func_70297_j_()) {
            par2ItemStack.field_77994_a = this.func_70297_j_();
        }
        this.func_70296_d();
        if (!this.field_145850_b.field_72995_K) {
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }

    public void setInventorySlotContentsFromInfusion(int par1, ItemStack par2ItemStack) {
        this.inventory[par1] = par2ItemStack;
        this.func_70296_d();
        if (!this.field_145850_b.field_72995_K) {
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }

    public String func_145825_b() {
        return this.func_145818_k_() ? this.customName : "container.pedestal";
    }

    public boolean func_145818_k_() {
        return this.customName != null && this.customName.length() > 0;
    }

    public void setGuiDisplayName(String par1Str) {
        this.customName = par1Str;
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
        this.inventory = new ItemStack[this.func_70302_i_()];
        for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
            byte b0 = nbttagcompound1.func_74771_c("Slot");
            if (b0 < 0 || b0 >= this.inventory.length) continue;
            this.inventory[b0] = ItemStack.func_77949_a((NBTTagCompound)nbttagcompound1);
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.inventory.length; ++i) {
            if (this.inventory[i] == null) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.func_74774_a("Slot", (byte)i);
            this.inventory[i].func_77955_b(nbttagcompound1);
            nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
        }
        nbttagcompound.func_74782_a("Items", (NBTBase)nbttaglist);
    }

    @Override
    public void func_145839_a(NBTTagCompound nbtCompound) {
        super.func_145839_a(nbtCompound);
        if (nbtCompound.func_74764_b("CustomName")) {
            this.customName = nbtCompound.func_74779_i("CustomName");
        }
    }

    @Override
    public void func_145841_b(NBTTagCompound nbtCompound) {
        super.func_145841_b(nbtCompound);
        if (this.func_145818_k_()) {
            nbtCompound.func_74778_a("CustomName", this.customName);
        }
    }

    public int func_70297_j_() {
        return 1;
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
    }

    public boolean canUpdate() {
        return false;
    }

    public boolean func_70300_a(EntityPlayer par1EntityPlayer) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this ? false : par1EntityPlayer.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int par1, ItemStack par2ItemStack) {
        return true;
    }

    public int[] func_94128_d(int par1) {
        return slots;
    }

    public boolean func_102007_a(int par1, ItemStack par2ItemStack, int par3) {
        return this.func_70301_a(par1) == null;
    }

    public boolean func_102008_b(int par1, ItemStack par2ItemStack, int par3) {
        return true;
    }

    public boolean func_145842_c(int i, int j) {
        if (i == 11) {
            if (this.field_145850_b.field_72995_K) {
                for (int a = 0; a < Thaumcraft.proxy.particleCount(5); ++a) {
                    Thaumcraft.proxy.blockSparkle(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, 0xC000C0, 2);
                }
            }
            return true;
        }
        if (i == 12) {
            if (this.field_145850_b.field_72995_K) {
                for (int a = 0; a < Thaumcraft.proxy.particleCount(10); ++a) {
                    Thaumcraft.proxy.blockSparkle(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e, -9999, 2);
                }
            }
            return true;
        }
        return super.func_145842_c(i, j);
    }
}

