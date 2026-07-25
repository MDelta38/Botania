/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 */
package thaumcraft.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.common.config.ConfigBlocks;

public class TileChestHungry
extends TileEntity
implements IInventory {
    private ItemStack[] chestContents = new ItemStack[36];
    public float lidAngle;
    public float prevLidAngle;
    public int numUsingPlayers;
    private int ticksSinceSync;

    public int func_70302_i_() {
        return 27;
    }

    public ItemStack func_70301_a(int par1) {
        return this.chestContents[par1];
    }

    public ItemStack func_70298_a(int par1, int par2) {
        if (this.chestContents[par1] != null) {
            ItemStack var3 = this.chestContents[par1].func_77979_a(par2);
            if (this.chestContents[par1].field_77994_a == 0) {
                this.chestContents[par1] = null;
            }
            this.func_70296_d();
            return var3;
        }
        return null;
    }

    public ItemStack func_70304_b(int par1) {
        if (this.chestContents[par1] != null) {
            ItemStack var2 = this.chestContents[par1];
            this.chestContents[par1] = null;
            return var2;
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

    public String func_145825_b() {
        return "Hungry Chest";
    }

    public void func_145839_a(NBTTagCompound par1NBTTagCompound) {
        super.func_145839_a(par1NBTTagCompound);
        NBTTagList var2 = par1NBTTagCompound.func_150295_c("Items", 10);
        this.chestContents = new ItemStack[this.func_70302_i_()];
        for (int var3 = 0; var3 < var2.func_74745_c(); ++var3) {
            NBTTagCompound var4 = var2.func_150305_b(var3);
            int var5 = var4.func_74771_c("Slot") & 0xFF;
            if (var5 < 0 || var5 >= this.chestContents.length) continue;
            this.chestContents[var5] = ItemStack.func_77949_a((NBTTagCompound)var4);
        }
    }

    public void func_145841_b(NBTTagCompound par1NBTTagCompound) {
        super.func_145841_b(par1NBTTagCompound);
        NBTTagList var2 = new NBTTagList();
        for (int var3 = 0; var3 < this.chestContents.length; ++var3) {
            if (this.chestContents[var3] == null) continue;
            NBTTagCompound var4 = new NBTTagCompound();
            var4.func_74774_a("Slot", (byte)var3);
            this.chestContents[var3].func_77955_b(var4);
            var2.func_74742_a((NBTBase)var4);
        }
        par1NBTTagCompound.func_74782_a("Items", (NBTBase)var2);
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer par1EntityPlayer) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this ? false : par1EntityPlayer.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

    public void func_145836_u() {
        super.func_145836_u();
    }

    public void func_145845_h() {
        double var4;
        super.func_145845_h();
        if (++this.ticksSinceSync % 20 * 4 == 0) {
            // empty if block
        }
        this.prevLidAngle = this.lidAngle;
        float var1 = 0.1f;
        if (this.numUsingPlayers > 0 && this.lidAngle == 0.0f) {
            double var2 = (double)this.field_145851_c + 0.5;
            var4 = (double)this.field_145849_e + 0.5;
            this.field_145850_b.func_72908_a(var2, (double)this.field_145848_d + 0.5, var4, "random.chestopen", 0.5f, this.field_145850_b.field_73012_v.nextFloat() * 0.1f + 0.9f);
        }
        if (this.numUsingPlayers == 0 && this.lidAngle > 0.0f || this.numUsingPlayers > 0 && this.lidAngle < 1.0f) {
            float var3;
            float var8 = this.lidAngle;
            this.lidAngle = this.numUsingPlayers > 0 ? (this.lidAngle += var1) : (this.lidAngle -= var1);
            if (this.lidAngle > 1.0f) {
                this.lidAngle = 1.0f;
            }
            if (this.lidAngle < (var3 = 0.5f) && var8 >= var3) {
                var4 = (double)this.field_145851_c + 0.5;
                double var6 = (double)this.field_145849_e + 0.5;
                this.field_145850_b.func_72908_a(var4, (double)this.field_145848_d + 0.5, var6, "random.chestclosed", 0.5f, this.field_145850_b.field_73012_v.nextFloat() * 0.1f + 0.9f);
            }
            if (this.lidAngle < 0.0f) {
                this.lidAngle = 0.0f;
            }
        }
    }

    public boolean func_145842_c(int par1, int par2) {
        if (par1 == 1) {
            this.numUsingPlayers = par2;
            return true;
        }
        if (par1 == 2) {
            if (this.lidAngle < (float)par2 / 10.0f) {
                this.lidAngle = (float)par2 / 10.0f;
            }
            return true;
        }
        return this.field_145846_f;
    }

    public void func_70295_k_() {
        ++this.numUsingPlayers;
        this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockChestHungry, 1, this.numUsingPlayers);
    }

    public void func_70305_f() {
        --this.numUsingPlayers;
        this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockChestHungry, 1, this.numUsingPlayers);
    }

    public void func_145843_s() {
        this.func_145836_u();
        super.func_145843_s();
    }

    public boolean func_145818_k_() {
        return false;
    }

    public boolean func_94041_b(int i, ItemStack itemstack) {
        return true;
    }
}

