/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityFurnace
 *  net.minecraft.world.EnumSkyBlock
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.EnumSkyBlock;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumcraft.common.tiles.TileAlembic;
import thaumcraft.common.tiles.TileBellows;

public class TileAlchemyFurnace
extends TileThaumcraft
implements ISidedInventory {
    private static final int[] slots_bottom = new int[]{1};
    private static final int[] slots_top = new int[0];
    private static final int[] slots_sides = new int[]{0};
    public AspectList aspects = new AspectList();
    public int vis;
    private int maxVis = 50;
    public int smeltTime = 100;
    int bellows = -1;
    boolean speedBoost = false;
    private ItemStack[] furnaceItemStacks = new ItemStack[2];
    public int furnaceBurnTime;
    public int currentItemBurnTime;
    public int furnaceCookTime;
    private String customName;
    int count = 0;

    public int func_70302_i_() {
        return this.furnaceItemStacks.length;
    }

    public ItemStack func_70301_a(int par1) {
        return this.furnaceItemStacks[par1];
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

    public String func_145825_b() {
        return this.func_145818_k_() ? this.customName : "container.alchemyfurnace";
    }

    public boolean func_145818_k_() {
        return this.customName != null && this.customName.length() > 0;
    }

    public void setGuiDisplayName(String par1Str) {
        this.customName = par1Str;
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.furnaceBurnTime = nbttagcompound.func_74765_d("BurnTime");
        this.vis = nbttagcompound.func_74765_d("Vis");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74777_a("BurnTime", (short)this.furnaceBurnTime);
        nbttagcompound.func_74777_a("Vis", (short)this.vis);
    }

    @Override
    public void func_145839_a(NBTTagCompound nbtCompound) {
        super.func_145839_a(nbtCompound);
        NBTTagList nbttaglist = nbtCompound.func_150295_c("Items", 10);
        this.furnaceItemStacks = new ItemStack[this.func_70302_i_()];
        for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
            byte b0 = nbttagcompound1.func_74771_c("Slot");
            if (b0 < 0 || b0 >= this.furnaceItemStacks.length) continue;
            this.furnaceItemStacks[b0] = ItemStack.func_77949_a((NBTTagCompound)nbttagcompound1);
        }
        this.speedBoost = nbtCompound.func_74767_n("speedBoost");
        this.furnaceCookTime = nbtCompound.func_74765_d("CookTime");
        this.currentItemBurnTime = TileEntityFurnace.func_145952_a((ItemStack)this.furnaceItemStacks[1]);
        if (nbtCompound.func_74764_b("CustomName")) {
            this.customName = nbtCompound.func_74779_i("CustomName");
        }
        this.aspects.readFromNBT(nbtCompound);
        this.vis = this.aspects.visSize();
    }

    @Override
    public void func_145841_b(NBTTagCompound nbtCompound) {
        super.func_145841_b(nbtCompound);
        nbtCompound.func_74757_a("speedBoost", this.speedBoost);
        nbtCompound.func_74777_a("CookTime", (short)this.furnaceCookTime);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.furnaceItemStacks.length; ++i) {
            if (this.furnaceItemStacks[i] == null) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.func_74774_a("Slot", (byte)i);
            this.furnaceItemStacks[i].func_77955_b(nbttagcompound1);
            nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
        }
        nbtCompound.func_74782_a("Items", (NBTBase)nbttaglist);
        if (this.func_145818_k_()) {
            nbtCompound.func_74778_a("CustomName", this.customName);
        }
        this.aspects.writeToNBT(nbtCompound);
    }

    public int func_70297_j_() {
        return 64;
    }

    @SideOnly(value=Side.CLIENT)
    public int getCookProgressScaled(int par1) {
        if (this.smeltTime <= 0) {
            this.smeltTime = 1;
        }
        return this.furnaceCookTime * par1 / this.smeltTime;
    }

    @SideOnly(value=Side.CLIENT)
    public int getContentsScaled(int par1) {
        return this.vis * par1 / this.maxVis;
    }

    @SideOnly(value=Side.CLIENT)
    public int getBurnTimeRemainingScaled(int par1) {
        if (this.currentItemBurnTime == 0) {
            this.currentItemBurnTime = 200;
        }
        return this.furnaceBurnTime * par1 / this.currentItemBurnTime;
    }

    public boolean isBurning() {
        return this.furnaceBurnTime > 0;
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        if (this.field_145850_b != null) {
            this.field_145850_b.func_147463_c(EnumSkyBlock.Block, this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        boolean flag = this.furnaceBurnTime > 0;
        boolean flag1 = false;
        ++this.count;
        if (this.furnaceBurnTime > 0) {
            --this.furnaceBurnTime;
        }
        if (!this.field_145850_b.field_72995_K) {
            if (this.bellows < 0) {
                this.getBellows();
            }
            if (this.count % (this.speedBoost ? 20 : 40) == 0 && this.aspects.size() > 0) {
                TileAlembic alembic;
                AspectList exlude = new AspectList();
                int deep = 0;
                TileEntity tile = null;
                while (deep < 5 && (tile = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + ++deep, this.field_145849_e)) instanceof TileAlembic) {
                    alembic = (TileAlembic)tile;
                    if (alembic.aspect != null && alembic.amount < alembic.maxAmount && this.aspects.getAmount(alembic.aspect) > 0) {
                        this.takeFromContainer(alembic.aspect, 1);
                        alembic.addToContainer(alembic.aspect, 1);
                        exlude.merge(alembic.aspect, 1);
                        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d + deep, this.field_145849_e);
                    }
                    tile = null;
                }
                deep = 0;
                while (deep < 5 && (tile = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + ++deep, this.field_145849_e)) instanceof TileAlembic) {
                    alembic = (TileAlembic)tile;
                    if (alembic.aspect != null && alembic.amount != 0) continue;
                    Aspect as = null;
                    if (alembic.aspectFilter == null) {
                        as = this.takeRandomAspect(exlude);
                    } else if (this.takeFromContainer(alembic.aspectFilter, 1)) {
                        as = alembic.aspectFilter;
                    }
                    if (as == null) continue;
                    alembic.addToContainer(as, 1);
                    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
                    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d + deep, this.field_145849_e);
                    break;
                }
            }
            if (this.furnaceBurnTime == 0 && this.canSmelt()) {
                this.currentItemBurnTime = this.furnaceBurnTime = TileEntityFurnace.func_145952_a((ItemStack)this.furnaceItemStacks[1]);
                if (this.furnaceBurnTime > 0) {
                    flag1 = true;
                    this.speedBoost = false;
                    if (this.furnaceItemStacks[1] != null) {
                        if (this.furnaceItemStacks[1].func_77969_a(new ItemStack(ConfigItems.itemResource, 1, 0))) {
                            this.speedBoost = true;
                        }
                        --this.furnaceItemStacks[1].field_77994_a;
                        if (this.furnaceItemStacks[1].field_77994_a == 0) {
                            this.furnaceItemStacks[1] = this.furnaceItemStacks[1].func_77973_b().getContainerItem(this.furnaceItemStacks[1]);
                        }
                    }
                }
            }
            if (this.isBurning() && this.canSmelt()) {
                ++this.furnaceCookTime;
                if (this.furnaceCookTime >= this.smeltTime) {
                    this.furnaceCookTime = 0;
                    this.smeltItem();
                    flag1 = true;
                }
            } else {
                this.furnaceCookTime = 0;
            }
            if (flag != this.furnaceBurnTime > 0) {
                flag1 = true;
                this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            }
        }
        if (flag1) {
            this.func_70296_d();
        }
    }

    private boolean canSmelt() {
        if (this.furnaceItemStacks[0] == null) {
            return false;
        }
        AspectList al = ThaumcraftCraftingManager.getObjectTags(this.furnaceItemStacks[0]);
        if ((al = ThaumcraftCraftingManager.getBonusTags(this.furnaceItemStacks[0], al)) == null || al.size() == 0) {
            return false;
        }
        int vs = al.visSize();
        if (vs > this.maxVis - this.vis) {
            return false;
        }
        this.smeltTime = (int)((float)(vs * 10) * (1.0f - 0.125f * (float)this.bellows));
        return true;
    }

    public void getBellows() {
        this.bellows = TileBellows.getBellows(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, ForgeDirection.VALID_DIRECTIONS);
    }

    public void smeltItem() {
        if (this.canSmelt()) {
            AspectList al = ThaumcraftCraftingManager.getObjectTags(this.furnaceItemStacks[0]);
            al = ThaumcraftCraftingManager.getBonusTags(this.furnaceItemStacks[0], al);
            for (Aspect a : al.getAspects()) {
                this.aspects.add(a, al.getAmount(a));
            }
            this.vis = this.aspects.visSize();
            --this.furnaceItemStacks[0].field_77994_a;
            if (this.furnaceItemStacks[0].field_77994_a <= 0) {
                this.furnaceItemStacks[0] = null;
            }
        }
    }

    public static boolean isItemFuel(ItemStack par0ItemStack) {
        return TileEntityFurnace.func_145952_a((ItemStack)par0ItemStack) > 0;
    }

    public boolean func_70300_a(EntityPlayer par1EntityPlayer) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this ? false : par1EntityPlayer.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int par1, ItemStack par2ItemStack) {
        if (par1 == 0) {
            AspectList al = ThaumcraftCraftingManager.getObjectTags(par2ItemStack);
            if ((al = ThaumcraftCraftingManager.getBonusTags(par2ItemStack, al)) != null && al.size() > 0) {
                return true;
            }
        }
        return par1 == 1 ? TileAlchemyFurnace.isItemFuel(par2ItemStack) : false;
    }

    public int[] func_94128_d(int par1) {
        return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_sides);
    }

    public boolean func_102007_a(int par1, ItemStack par2ItemStack, int par3) {
        return par3 == 1 ? false : this.func_94041_b(par1, par2ItemStack);
    }

    public boolean func_102008_b(int par1, ItemStack par2ItemStack, int par3) {
        return par3 != 0 || par1 != 1 || par2ItemStack.func_77973_b() == Items.field_151133_ar;
    }

    public Aspect takeRandomAspect(AspectList exlude) {
        if (this.aspects.size() > 0) {
            AspectList temp = this.aspects.copy();
            if (exlude.size() > 0) {
                for (Aspect a : exlude.getAspects()) {
                    temp.remove(a);
                }
            }
            if (temp.size() > 0) {
                Aspect tag = temp.getAspects()[this.field_145850_b.field_73012_v.nextInt(temp.getAspects().length)];
                this.aspects.remove(tag, 1);
                --this.vis;
                return tag;
            }
        }
        return null;
    }

    public boolean takeFromContainer(Aspect tag, int amount) {
        if (this.aspects != null && this.aspects.getAmount(tag) >= amount) {
            this.aspects.remove(tag, amount);
            this.vis -= amount;
            return true;
        }
        return false;
    }
}

