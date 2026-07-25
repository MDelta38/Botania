/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ContainerChest
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.InventoryLargeChest
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 */
package flaxbeard.thaumicexploration.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.thaumicexploration.block.BlockBoundChest;
import flaxbeard.thaumicexploration.data.BoundChestWorldData;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityBoundChest
extends TileEntity
implements IInventory {
    private ItemStack[] chestContents = new ItemStack[36];
    public BoundChestWorldData myChest;
    public int accessTicks = 0;
    public int id = 0;
    public int clientColor = 0;
    public boolean adjacentChestChecked;
    public float lidAngle;
    public float prevLidAngle;
    public int numUsingPlayers;
    private int ticksSinceSync;
    private int cachedChestType;
    private String customName;

    public TileEntityBoundChest() {
        this.cachedChestType = -1;
    }

    @SideOnly(value=Side.CLIENT)
    public TileEntityBoundChest(int par1) {
        this.cachedChestType = par1;
    }

    public int func_70302_i_() {
        return 27;
    }

    public ItemStack func_70301_a(int par1) {
        return this.chestContents[par1];
    }

    public ItemStack func_70298_a(int par1, int par2) {
        if (this.chestContents[par1] != null) {
            if (this.chestContents[par1].field_77994_a <= par2) {
                ItemStack itemstack = this.chestContents[par1];
                this.chestContents[par1] = null;
                this.func_70296_d();
                return itemstack;
            }
            ItemStack itemstack = this.chestContents[par1].func_77979_a(par2);
            if (this.chestContents[par1].field_77994_a == 0) {
                this.chestContents[par1] = null;
            }
            this.func_70296_d();
            return itemstack;
        }
        return null;
    }

    public ItemStack func_70304_b(int par1) {
        if (this.chestContents[par1] != null) {
            ItemStack itemstack = this.chestContents[par1];
            this.chestContents[par1] = null;
            return itemstack;
        }
        return null;
    }

    public void func_70299_a(int par1, ItemStack par2ItemStack) {
        this.chestContents[par1] = par2ItemStack;
        if (par2ItemStack != null && par2ItemStack.field_77994_a > this.func_70297_j_()) {
            par2ItemStack.field_77994_a = this.func_70297_j_();
        }
        this.func_70296_d();
    }

    public void func_70296_d() {
        if (this.myChest != null) {
            this.myChest.updateChestContents(this.chestContents);
        }
        this.accessTicks = 80;
        this.updateAccessTicks();
        super.func_70296_d();
    }

    public String func_145825_b() {
        return this.func_145818_k_() ? this.customName : "container.chest";
    }

    public boolean func_145818_k_() {
        return this.customName != null && this.customName.length() > 0;
    }

    public void setChestGuiName(String par1Str) {
        this.customName = par1Str;
    }

    public void func_145839_a(NBTTagCompound par1NBTTagCompound) {
        super.func_145839_a(par1NBTTagCompound);
        NBTTagList nbttaglist = par1NBTTagCompound.func_150295_c("Items", 10);
        this.chestContents = new ItemStack[this.func_70302_i_()];
        if (par1NBTTagCompound.func_74764_b("chestID")) {
            this.id = par1NBTTagCompound.func_74762_e("chestID");
        }
        if (par1NBTTagCompound.func_74764_b("myAccessTick")) {
            this.accessTicks = par1NBTTagCompound.func_74762_e("myAccessTick");
        }
        if (par1NBTTagCompound.func_74764_b("CustomName")) {
            this.customName = par1NBTTagCompound.func_74779_i("CustomName");
        }
        for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
            int j = nbttagcompound1.func_74771_c("Slot") & 0xFF;
            if (j < 0 || j >= this.chestContents.length) continue;
            this.chestContents[j] = ItemStack.func_77949_a((NBTTagCompound)nbttagcompound1);
        }
    }

    public void func_145841_b(NBTTagCompound par1NBTTagCompound) {
        super.func_145841_b(par1NBTTagCompound);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.chestContents.length; ++i) {
            if (this.chestContents[i] == null) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.func_74774_a("Slot", (byte)i);
            this.chestContents[i].func_77955_b(nbttagcompound1);
            nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
        }
        par1NBTTagCompound.func_74782_a("Items", (NBTBase)nbttaglist);
        if (this.func_145818_k_()) {
            par1NBTTagCompound.func_74778_a("CustomName", this.customName);
        }
        par1NBTTagCompound.func_74768_a("myAccessTick", this.accessTicks);
        par1NBTTagCompound.func_74768_a("chestID", this.id);
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer par1EntityPlayer) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this ? false : par1EntityPlayer.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

    public void func_145836_u() {
        super.func_145836_u();
        this.adjacentChestChecked = true;
    }

    public void func_145845_h() {
        float f;
        super.func_145845_h();
        ++this.ticksSinceSync;
        if (this.accessTicks > 0 && !this.field_145850_b.field_72995_K) {
            --this.accessTicks;
            this.updateAccessTicks();
        }
        if (this.id > 0) {
            this.myChest = BoundChestWorldData.get(this.field_145850_b, "id" + this.id, 0);
        }
        if (this.myChest != null) {
            if (this.chestContents != this.myChest.getChestContents()) {
                this.accessTicks = 80;
                this.updateAccessTicks();
            }
            this.chestContents = this.myChest.getChestContents();
        }
        if (!this.field_145850_b.field_72995_K && this.numUsingPlayers != 0 && (this.ticksSinceSync + this.field_145851_c + this.field_145848_d + this.field_145849_e) % 200 == 0) {
            this.numUsingPlayers = 0;
            f = 5.0f;
            List list = this.field_145850_b.func_72872_a(EntityPlayer.class, AxisAlignedBB.func_72330_a((double)((float)this.field_145851_c - f), (double)((float)this.field_145848_d - f), (double)((float)this.field_145849_e - f), (double)((float)(this.field_145851_c + 1) + f), (double)((float)(this.field_145848_d + 1) + f), (double)((float)(this.field_145849_e + 1) + f)));
            for (EntityPlayer entityplayer : list) {
                IInventory iinventory;
                if (!(entityplayer.field_71070_bA instanceof ContainerChest) || (iinventory = ((ContainerChest)entityplayer.field_71070_bA).func_85151_d()) != this && (!(iinventory instanceof InventoryLargeChest) || !((InventoryLargeChest)iinventory).func_90010_a((IInventory)this))) continue;
                ++this.numUsingPlayers;
            }
        }
        this.prevLidAngle = this.lidAngle;
        f = 0.1f;
        if (this.numUsingPlayers > 0 && this.lidAngle == 0.0f) {
            double d1 = (double)this.field_145851_c + 0.5;
            double d0 = (double)this.field_145849_e + 0.5;
            this.field_145850_b.func_72908_a(d1, (double)this.field_145848_d + 0.5, d0, "random.chestopen", 0.5f, this.field_145850_b.field_73012_v.nextFloat() * 0.1f + 0.9f);
        }
        if (this.numUsingPlayers == 0 && this.lidAngle > 0.0f || this.numUsingPlayers > 0 && this.lidAngle < 1.0f) {
            float f2;
            float f1 = this.lidAngle;
            this.lidAngle = this.numUsingPlayers > 0 ? (this.lidAngle += f) : (this.lidAngle -= f);
            if (this.lidAngle > 1.0f) {
                this.lidAngle = 1.0f;
            }
            if (this.lidAngle < (f2 = 0.5f) && f1 >= f2) {
                double d0 = (double)this.field_145851_c + 0.5;
                double d2 = (double)this.field_145849_e + 0.5;
                this.field_145850_b.func_72908_a(d0, (double)this.field_145848_d + 0.5, d2, "random.chestclosed", 0.5f, this.field_145850_b.field_73012_v.nextFloat() * 0.1f + 0.9f);
            }
            if (this.lidAngle < 0.0f) {
                this.lidAngle = 0.0f;
            }
        }
    }

    public Packet func_145844_m() {
        NBTTagCompound access = new NBTTagCompound();
        access.func_74768_a("accessTicks", this.accessTicks);
        access.func_74768_a("color", this.getSealColor());
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, access);
    }

    public void setColor(int color) {
        if (this.id > 0) {
            this.myChest = BoundChestWorldData.get(this.field_145850_b, "id" + this.id, 0);
        }
        if (this.myChest != null) {
            this.myChest.setSealColor(color);
        }
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        NBTTagCompound access = pkt.func_148857_g();
        this.accessTicks = access.func_74762_e("accessTicks");
        this.setColor(access.func_74762_e("color"));
        this.clientColor = access.func_74762_e("color");
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    private void updateAccessTicks() {
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public boolean func_145842_c(int par1, int par2) {
        if (par1 == 1) {
            this.numUsingPlayers = par2;
            return true;
        }
        return super.func_145842_c(par1, par2);
    }

    public boolean func_94041_b(int par1, ItemStack par2ItemStack) {
        return true;
    }

    public void func_145843_s() {
        super.func_145843_s();
        this.func_145836_u();
    }

    public int getChestType() {
        if (this.cachedChestType == -1) {
            if (this.field_145850_b == null || !(this.func_145838_q() instanceof BlockBoundChest)) {
                return 0;
            }
            this.cachedChestType = ((BlockBoundChest)this.func_145838_q()).chestType;
        }
        return this.cachedChestType;
    }

    public int getSealColor() {
        if (this.myChest != null) {
            return this.myChest.getSealColor();
        }
        return 0;
    }

    public int getAccessTicks() {
        if (this.field_145850_b.field_72995_K) {
            // empty if block
        }
        return this.accessTicks;
    }

    public void setAccessTicks(int tick) {
        this.accessTicks = tick;
    }

    public void func_70295_k_() {
        if (this.numUsingPlayers < 0) {
            this.numUsingPlayers = 0;
        }
        ++this.numUsingPlayers;
        this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.func_145838_q(), 1, this.numUsingPlayers);
        this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.func_145838_q());
        this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e, this.func_145838_q());
    }

    public void func_70305_f() {
        if (this.func_145838_q() != null && this.func_145838_q() instanceof BlockBoundChest) {
            --this.numUsingPlayers;
            this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.func_145838_q(), 1, this.numUsingPlayers);
            this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.func_145838_q());
            this.field_145850_b.func_147459_d(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e, this.func_145838_q());
        }
    }
}

