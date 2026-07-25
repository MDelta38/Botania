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
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumcraft.common.lib.research.ResearchManager;

public class TileDeconstructionTable
extends TileThaumcraft
implements ISidedInventory {
    public Aspect aspect;
    public int breaktime;
    private ItemStack[] itemStacks = new ItemStack[1];
    private String customName;
    private static final int[] sides = new int[]{0};

    public int func_70302_i_() {
        return 1;
    }

    public ItemStack func_70301_a(int par1) {
        return this.itemStacks[par1];
    }

    public ItemStack func_70298_a(int par1, int par2) {
        if (this.itemStacks[par1] != null) {
            if (this.itemStacks[par1].field_77994_a <= par2) {
                ItemStack itemstack = this.itemStacks[par1];
                this.itemStacks[par1] = null;
                this.func_70296_d();
                return itemstack;
            }
            ItemStack itemstack = this.itemStacks[par1].func_77979_a(par2);
            if (this.itemStacks[par1].field_77994_a == 0) {
                this.itemStacks[par1] = null;
            }
            this.func_70296_d();
            return itemstack;
        }
        return null;
    }

    public ItemStack func_70304_b(int par1) {
        if (this.itemStacks[par1] != null) {
            ItemStack itemstack = this.itemStacks[par1];
            this.itemStacks[par1] = null;
            this.func_70296_d();
            return itemstack;
        }
        return null;
    }

    public void func_70299_a(int par1, ItemStack par2ItemStack) {
        this.itemStacks[par1] = par2ItemStack;
        if (par2ItemStack != null && par2ItemStack.field_77994_a > this.func_70297_j_()) {
            par2ItemStack.field_77994_a = this.func_70297_j_();
        }
        this.func_70296_d();
    }

    public String func_145825_b() {
        return this.func_145818_k_() ? this.customName : "container.decontable";
    }

    public boolean func_145818_k_() {
        return this.customName != null && this.customName.length() > 0;
    }

    public void setGuiDisplayName(String par1Str) {
        this.customName = par1Str;
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.aspect = Aspect.getAspect(nbttagcompound.func_74779_i("Aspect"));
        NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
        this.itemStacks = new ItemStack[this.func_70302_i_()];
        for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
            byte b0 = nbttagcompound1.func_74771_c("Slot");
            if (b0 < 0 || b0 >= this.itemStacks.length) continue;
            this.itemStacks[b0] = ItemStack.func_77949_a((NBTTagCompound)nbttagcompound1);
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        if (this.aspect != null) {
            nbttagcompound.func_74778_a("Aspect", this.aspect.getTag());
        }
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.itemStacks.length; ++i) {
            if (this.itemStacks[i] == null) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.func_74774_a("Slot", (byte)i);
            this.itemStacks[i].func_77955_b(nbttagcompound1);
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
        return 64;
    }

    @SideOnly(value=Side.CLIENT)
    public int getBreakTimeScaled(int par1) {
        return this.breaktime * par1 / 40;
    }

    public boolean canUpdate() {
        return true;
    }

    public void func_145845_h() {
        boolean flag1 = false;
        if (!this.field_145850_b.field_72995_K) {
            if (this.breaktime == 0 && this.canBreak()) {
                this.breaktime = 40;
                flag1 = true;
            }
            if (this.breaktime > 0 && this.canBreak()) {
                --this.breaktime;
                if (this.breaktime == 0) {
                    this.breaktime = 0;
                    this.breakItem();
                    flag1 = true;
                }
            } else {
                this.breaktime = 0;
            }
        }
        if (flag1) {
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
        }
    }

    private boolean canBreak() {
        if (this.itemStacks[0] == null || this.aspect != null) {
            return false;
        }
        AspectList al = ThaumcraftCraftingManager.getObjectTags(this.itemStacks[0]);
        return (al = ThaumcraftCraftingManager.getBonusTags(this.itemStacks[0], al)) != null && al.size() != 0;
    }

    public void breakItem() {
        if (this.canBreak()) {
            AspectList al = ThaumcraftCraftingManager.getObjectTags(this.itemStacks[0]);
            al = ThaumcraftCraftingManager.getBonusTags(this.itemStacks[0], al);
            AspectList primals = ResearchManager.reduceToPrimals(al);
            if (this.field_145850_b.field_73012_v.nextInt(80) < primals.visSize()) {
                this.aspect = primals.getAspects()[this.field_145850_b.field_73012_v.nextInt(primals.getAspects().length)];
            }
            --this.itemStacks[0].field_77994_a;
            if (this.itemStacks[0].field_77994_a <= 0) {
                this.itemStacks[0] = null;
            }
        }
    }

    public boolean func_70300_a(EntityPlayer par1EntityPlayer) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this ? false : par1EntityPlayer.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int par1, ItemStack par2ItemStack) {
        AspectList al = ThaumcraftCraftingManager.getObjectTags(par2ItemStack);
        return (al = ThaumcraftCraftingManager.getBonusTags(par2ItemStack, al)) != null && al.size() > 0;
    }

    public int[] func_94128_d(int par1) {
        return par1 != 1 ? sides : new int[]{};
    }

    public boolean func_102007_a(int par1, ItemStack par2ItemStack, int par3) {
        return par3 == 1 ? false : this.func_94041_b(par1, par2ItemStack);
    }

    public boolean func_102008_b(int par1, ItemStack par2ItemStack, int par3) {
        return true;
    }
}

