/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package thaumcraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import thaumcraft.common.container.InventoryHoverHarness;

public class ContainerHoverHarness
extends Container {
    private World worldObj;
    private int posX;
    private int posY;
    private int posZ;
    public IInventory input = new InventoryHoverHarness(this);
    ItemStack armor = null;
    EntityPlayer player = null;
    private int blockSlot;

    public ContainerHoverHarness(InventoryPlayer iinventory, World par2World, int par3, int par4, int par5) {
        this.worldObj = par2World;
        this.posX = par3;
        this.posY = par4;
        this.posZ = par5;
        this.player = iinventory.field_70458_d;
        this.armor = iinventory.func_70448_g();
        this.blockSlot = iinventory.field_70461_c + 28;
        this.func_75146_a(new Slot(this.input, 0, 80, 32));
        this.bindPlayerInventory(iinventory);
        if (!par2World.field_72995_K) {
            try {
                ItemStack jar = ItemStack.func_77949_a((NBTTagCompound)this.armor.field_77990_d.func_74775_l("jar"));
                this.input.func_70299_a(0, jar);
            }
            catch (Exception e) {
                // empty catch block
            }
        }
        this.func_75130_a(this.input);
    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.func_75146_a(new Slot((IInventory)inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.func_75146_a(new Slot((IInventory)inventoryPlayer, i, 8 + i * 18, 142));
        }
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot) {
        if (slot == this.blockSlot) {
            return null;
        }
        ItemStack stack = null;
        Slot slotObject = (Slot)this.field_75151_b.get(slot);
        if (slotObject != null && slotObject.func_75216_d()) {
            ItemStack stackInSlot = slotObject.func_75211_c();
            stack = stackInSlot.func_77946_l();
            if (slot == 0 ? !this.input.func_94041_b(slot, stackInSlot) || !this.mergeItemStack(stackInSlot, 1, this.field_75151_b.size(), true, 64) : !this.input.func_94041_b(slot, stackInSlot) || !this.mergeItemStack(stackInSlot, 0, 1, false, 1)) {
                return null;
            }
            if (stackInSlot.field_77994_a == 0) {
                slotObject.func_75215_d(null);
            } else {
                slotObject.func_75218_e();
            }
        }
        return stack;
    }

    public ItemStack func_75144_a(int par1, int par2, int par3, EntityPlayer par4EntityPlayer) {
        if (par1 == this.blockSlot) {
            return null;
        }
        InventoryPlayer inventoryplayer = par4EntityPlayer.field_71071_by;
        if (par1 != 0 || this.input.func_94041_b(par1, inventoryplayer.func_70445_o()) || par1 == 0 && inventoryplayer.func_70445_o() == null) {
            return super.func_75144_a(par1, par2, par3, par4EntityPlayer);
        }
        return null;
    }

    public boolean func_75145_c(EntityPlayer var1) {
        return true;
    }

    public void func_75141_a(int par1, ItemStack par2ItemStack) {
        if (this.input.func_94041_b(par1, par2ItemStack)) {
            super.func_75141_a(par1, par2ItemStack);
        }
    }

    public void func_75134_a(EntityPlayer par1EntityPlayer) {
        if (!this.worldObj.field_72995_K) {
            ItemStack var3 = this.input.func_70304_b(0);
            if (var3 != null) {
                NBTTagCompound var4 = new NBTTagCompound();
                var3.func_77955_b(var4);
                this.armor.func_77983_a("jar", (NBTBase)var4);
            } else {
                this.armor.func_77983_a("jar", (NBTBase)new NBTTagCompound());
            }
            if (this.player == null) {
                return;
            }
            if (this.player.func_70694_bm() != null && this.player.func_70694_bm().func_77969_a(this.armor)) {
                this.player.func_70062_b(0, this.armor);
            }
            this.player.field_71071_by.func_70296_d();
        }
    }

    protected boolean mergeItemStack(ItemStack par1ItemStack, int par2, int par3, boolean par4, int limit) {
        ItemStack var8;
        Slot var7;
        boolean var5 = false;
        int var6 = par2;
        if (par4) {
            var6 = par3 - 1;
        }
        if (par1ItemStack.func_77985_e()) {
            while (par1ItemStack.field_77994_a > 0 && (!par4 && var6 < par3 || par4 && var6 >= par2)) {
                var7 = (Slot)this.field_75151_b.get(var6);
                var8 = var7.func_75211_c();
                if (var8 != null && var8.func_77973_b() == par1ItemStack.func_77973_b() && (!par1ItemStack.func_77981_g() || par1ItemStack.func_77960_j() == var8.func_77960_j()) && ItemStack.func_77970_a((ItemStack)par1ItemStack, (ItemStack)var8)) {
                    int var9 = var8.field_77994_a + par1ItemStack.field_77994_a;
                    if (var9 <= Math.min(par1ItemStack.func_77976_d(), limit)) {
                        par1ItemStack.field_77994_a = 0;
                        var8.field_77994_a = var9;
                        var7.func_75218_e();
                        var5 = true;
                    } else if (var8.field_77994_a < Math.min(par1ItemStack.func_77976_d(), limit)) {
                        par1ItemStack.field_77994_a -= Math.min(par1ItemStack.func_77976_d(), limit) - var8.field_77994_a;
                        var8.field_77994_a = Math.min(par1ItemStack.func_77976_d(), limit);
                        var7.func_75218_e();
                        var5 = true;
                    }
                }
                if (par4) {
                    --var6;
                    continue;
                }
                ++var6;
            }
        }
        if (par1ItemStack.field_77994_a > 0) {
            var6 = par4 ? par3 - 1 : par2;
            while (!par4 && var6 < par3 || par4 && var6 >= par2) {
                var7 = (Slot)this.field_75151_b.get(var6);
                var8 = var7.func_75211_c();
                if (var8 == null) {
                    ItemStack res = par1ItemStack.func_77946_l();
                    res.field_77994_a = Math.min(res.field_77994_a, limit);
                    var7.func_75215_d(res);
                    var7.func_75218_e();
                    par1ItemStack.field_77994_a -= res.field_77994_a;
                    var5 = true;
                    break;
                }
                if (par4) {
                    --var6;
                    continue;
                }
                ++var6;
            }
        }
        return var5;
    }
}

