/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.IBlockAccess
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.config.ConfigItems
 */
package flaxbeard.thaumicexploration.tile;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.thaumicexploration.block.BlockThinkTank;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigItems;

public class TileEntityThinkTank
extends TileEntity
implements ISidedInventory {
    private static final int[] slots_top = new int[]{0};
    private static final int[] slots_bottom = new int[]{1};
    private static final int[] slots_sides = new int[]{1};
    long lastsigh = System.currentTimeMillis() + 1500L;
    protected static Random rand = new Random();
    private ItemStack[] furnaceItemStacks = new ItemStack[2];
    public int furnaceBurnTime = 55;
    public int currentItemBurnTime;
    public int furnaceCookTime;
    private String field_94130_e = "thaumicExploration.test";
    private boolean canSpaz;
    public float pageFlipPrev;
    public float pageFlip;
    public float field_70374_e;
    public int space = 1;
    public int warmedUpNumber;
    public int rotationTicks;

    public int func_70302_i_() {
        return this.furnaceItemStacks.length;
    }

    public ItemStack func_70301_a(int par1) {
        return this.furnaceItemStacks[par1];
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)(this.field_145851_c - 1 - this.space), (double)(this.field_145848_d - 1), (double)(this.field_145849_e - 1 - this.space), (double)(this.field_145851_c + 1 + this.space), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1 + this.space));
    }

    public ItemStack func_70298_a(int par1, int par2) {
        if (this.furnaceItemStacks[par1] != null) {
            if (this.furnaceItemStacks[par1].field_77994_a <= par2) {
                ItemStack itemstack = this.furnaceItemStacks[par1];
                this.furnaceItemStacks[par1] = null;
                return itemstack;
            }
            ItemStack itemstack = this.furnaceItemStacks[par1].func_77979_a(par2);
            if (this.furnaceItemStacks[par1].field_77994_a == 0) {
                this.furnaceItemStacks[par1] = null;
            }
            return itemstack;
        }
        return null;
    }

    public ItemStack func_70304_b(int par1) {
        if (this.furnaceItemStacks[par1] != null) {
            ItemStack itemstack = this.furnaceItemStacks[par1];
            this.furnaceItemStacks[par1] = null;
            return itemstack;
        }
        return null;
    }

    public void func_70299_a(int par1, ItemStack par2ItemStack) {
        this.furnaceItemStacks[par1] = par2ItemStack;
        if (par2ItemStack != null && par2ItemStack.field_77994_a > this.func_70297_j_()) {
            par2ItemStack.field_77994_a = this.func_70297_j_();
        }
    }

    public String getInvName() {
        return this.isInvNameLocalized() ? this.field_94130_e : "thaumicExploration:guiThinkTank";
    }

    public boolean isInvNameLocalized() {
        return this.field_94130_e != null && this.field_94130_e.length() > 0;
    }

    public void setGuiDisplayName(String par1Str) {
        this.field_94130_e = par1Str;
    }

    public void func_145839_a(NBTTagCompound par1NBTTagCompound) {
        super.func_145839_a(par1NBTTagCompound);
        NBTTagList nbttaglist = (NBTTagList)par1NBTTagCompound.func_74781_a("Items");
        this.furnaceItemStacks = new ItemStack[this.func_70302_i_()];
        for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
            byte b0 = nbttagcompound1.func_74771_c("Slot");
            if (b0 < 0 || b0 >= this.furnaceItemStacks.length) continue;
            this.furnaceItemStacks[b0] = ItemStack.func_77949_a((NBTTagCompound)nbttagcompound1);
        }
        this.furnaceBurnTime = par1NBTTagCompound.func_74765_d("BurnTime");
        this.furnaceCookTime = par1NBTTagCompound.func_74765_d("CookTime");
        if (par1NBTTagCompound.func_74764_b("CustomName")) {
            this.field_94130_e = par1NBTTagCompound.func_74779_i("CustomName");
        }
        if (par1NBTTagCompound.func_74764_b("WarmUpTicks")) {
            this.warmedUpNumber = par1NBTTagCompound.func_74765_d("WarmUpTicks");
        }
    }

    public Packet func_145844_m() {
        super.func_145844_m();
        NBTTagCompound access = new NBTTagCompound();
        access.func_74768_a("warmedUpNumber", this.warmedUpNumber);
        access.func_74768_a("rotationTicks", this.rotationTicks);
        access.func_74768_a("space", this.space);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, access);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        NBTTagCompound access = pkt.func_148857_g();
        this.warmedUpNumber = access.func_74762_e("warmedUpNumber");
        this.rotationTicks = access.func_74762_e("rotationTicks");
        this.space = access.func_74762_e("space");
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    public void func_145841_b(NBTTagCompound par1NBTTagCompound) {
        super.func_145841_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74777_a("BurnTime", (short)this.furnaceBurnTime);
        par1NBTTagCompound.func_74777_a("WarmUpTicks", (short)this.warmedUpNumber);
        par1NBTTagCompound.func_74777_a("CookTime", (short)this.furnaceCookTime);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.furnaceItemStacks.length; ++i) {
            if (this.furnaceItemStacks[i] == null) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.func_74774_a("Slot", (byte)i);
            this.furnaceItemStacks[i].func_77955_b(nbttagcompound1);
            nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
        }
        par1NBTTagCompound.func_74782_a("Items", (NBTBase)nbttaglist);
        if (this.isInvNameLocalized()) {
            par1NBTTagCompound.func_74778_a("CustomName", this.field_94130_e);
        }
    }

    public int func_70297_j_() {
        return 64;
    }

    @SideOnly(value=Side.CLIENT)
    public int getCookProgressScaled(int par1) {
        return this.furnaceCookTime * par1 / 200;
    }

    @SideOnly(value=Side.CLIENT)
    public int getBurnTimeRemainingScaled(int par1) {
        if (this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = 200;
        }
        return this.furnaceBurnTime * par1 / this.currentItemBurnTime;
    }

    public boolean isBurning() {
        return true;
    }

    public void func_145845_h() {
        boolean flag = this.furnaceBurnTime > 0;
        boolean flag1 = false;
        if (!this.field_145850_b.field_72995_K) {
            if (this.canSmelt() && this.warmedUpNumber < 40) {
                ++this.warmedUpNumber;
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
            if (!this.canSmelt() && this.warmedUpNumber > 0) {
                --this.warmedUpNumber;
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }
        if (this.warmedUpNumber > 0) {
            ++this.rotationTicks;
        }
        if (!this.field_145850_b.field_72995_K) {
            if (this.isBurning() && this.canSmelt()) {
                ++this.furnaceCookTime;
                if (this.furnaceCookTime == 200) {
                    this.furnaceCookTime = 0;
                    this.smeltItem();
                    flag1 = true;
                }
            } else {
                this.furnaceCookTime = 0;
            }
            if (flag != this.furnaceBurnTime > 0) {
                flag1 = true;
                BlockThinkTank.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }
        if (this.field_145850_b.field_72995_K) {
            this.canSpaz = true;
            EntityPlayer entity = null;
            if (entity == null && (entity = this.field_145850_b.func_72977_a((double)((float)this.field_145851_c + 0.5f), (double)((float)this.field_145848_d + 0.5f), (double)((float)this.field_145849_e + 0.5f), 6.0)) != null && this.lastsigh < System.currentTimeMillis()) {
                this.field_145850_b.func_72980_b((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "thaumcraft:brain", 0.15f, 0.8f + this.field_145850_b.field_73012_v.nextFloat() * 0.4f, false);
                this.lastsigh = System.currentTimeMillis() + 5000L + (long)this.field_145850_b.field_73012_v.nextInt(25000);
            }
        }
        if (flag1) {
            this.func_70296_d();
        }
    }

    private boolean canSmelt() {
        if (!this.enoughSpace()) {
            return false;
        }
        if (this.furnaceItemStacks[0] == null) {
            return false;
        }
        if (this.furnaceItemStacks[0].func_77973_b() == Items.field_151122_aG) {
            ItemStack itemstack = new ItemStack(ConfigItems.itemResource, 1, 9);
            if (this.furnaceItemStacks[1] == null) {
                return true;
            }
            if (!this.furnaceItemStacks[1].func_77969_a(itemstack)) {
                return false;
            }
            int result = this.furnaceItemStacks[1].field_77994_a + itemstack.field_77994_a;
            return result <= this.func_70297_j_() && result <= itemstack.func_77976_d();
        }
        if (this.furnaceItemStacks[0].func_77973_b() == Items.field_151134_bR) {
            ItemStack itemstack = new ItemStack(ConfigItems.itemResource, 2, 9);
            if (this.furnaceItemStacks[1] == null) {
                return true;
            }
            if (!this.furnaceItemStacks[1].func_77969_a(itemstack)) {
                return false;
            }
            int result = this.furnaceItemStacks[1].field_77994_a + itemstack.field_77994_a;
            return result <= this.func_70297_j_() && result <= itemstack.func_77976_d();
        }
        return false;
    }

    public boolean enoughSpace() {
        boolean enoughSpace = true;
        boolean muchSpace = true;
        for (int x = -2; x < 3; ++x) {
            for (int z = -2; z < 3; ++z) {
                for (int y = -1; y < 2; ++y) {
                    if (this.field_145850_b.func_147439_a(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z).func_149688_o() == Config.airyMaterial || this.field_145850_b.func_147439_a(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z).func_149688_o() == Material.field_151579_a || this.field_145850_b.func_147439_a(this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z).isReplaceable((IBlockAccess)this.field_145850_b, this.field_145851_c + x, this.field_145848_d + y, this.field_145849_e + z) || this.field_145851_c + x == this.field_145851_c && this.field_145849_e + z == this.field_145849_e && (this.field_145848_d + y == this.field_145848_d || this.field_145848_d + y == this.field_145848_d - 1)) continue;
                    if (Math.abs(x) > 1 || Math.abs(z) > 1) {
                        muchSpace = false;
                        continue;
                    }
                    enoughSpace = false;
                }
            }
        }
        if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) != Blocks.field_150342_X) {
            enoughSpace = false;
        }
        int oldSpace = this.space;
        int n = this.space = muchSpace ? 1 : 0;
        if (this.space != oldSpace) {
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
        return enoughSpace;
    }

    public void smeltItem() {
        if (this.canSmelt()) {
            double rand = Math.random();
            boolean genFrag = false;
            int numberFrags = 0;
            if (this.furnaceItemStacks[0].func_77973_b() == Items.field_151134_bR && rand > 0.33) {
                genFrag = true;
                int n = numberFrags = rand > 0.66 ? 1 : 2;
            }
            if (this.furnaceItemStacks[0].func_77973_b() == Items.field_151122_aG && rand <= 0.1) {
                genFrag = true;
                numberFrags = 1;
            }
            if (genFrag) {
                ItemStack itemstack = new ItemStack(ConfigItems.itemResource, numberFrags, 9);
                if (this.furnaceItemStacks[1] == null) {
                    this.furnaceItemStacks[1] = itemstack.func_77946_l();
                } else if (this.furnaceItemStacks[1].func_77969_a(itemstack)) {
                    this.furnaceItemStacks[1].field_77994_a += itemstack.field_77994_a;
                }
            }
            --this.furnaceItemStacks[0].field_77994_a;
            if (this.furnaceItemStacks[0].field_77994_a <= 0) {
                this.furnaceItemStacks[0] = null;
            }
        }
    }

    public boolean func_70300_a(EntityPlayer par1EntityPlayer) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this ? false : par1EntityPlayer.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

    public void openChest() {
    }

    public void closeChest() {
    }

    public boolean func_94041_b(int par1, ItemStack par2ItemStack) {
        return par1 == 2 ? false : par1 != 1;
    }

    public int[] func_94128_d(int par1) {
        return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_sides);
    }

    public boolean func_102007_a(int par1, ItemStack par2ItemStack, int par3) {
        return this.func_94041_b(par1, par2ItemStack);
    }

    public boolean func_102008_b(int par1, ItemStack par2ItemStack, int par3) {
        return par3 != 0 || par1 != 1 || par2ItemStack.func_77973_b() == Items.field_151133_ar;
    }

    public String func_145825_b() {
        return null;
    }

    public boolean func_145818_k_() {
        return false;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }
}

