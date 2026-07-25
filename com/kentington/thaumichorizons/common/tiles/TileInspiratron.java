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
 *  thaumcraft.common.config.ConfigItems
 */
package com.kentington.thaumichorizons.common.tiles;

import com.kentington.thaumichorizons.common.tiles.ISoulReceiver;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.common.config.ConfigItems;

public class TileInspiratron
extends TileThaumcraft
implements ISoulReceiver,
ISidedInventory {
    public ItemStack paper;
    public ItemStack knowledge;
    public int progress;
    private final int PROGRESS_MAX = 100;
    public float rota;
    public float rotb;
    public float field_40063_b;
    public float field_40061_d;
    public float field_40059_f;
    public float field_40066_q;

    public void func_145845_h() {
        super.func_145845_h();
        if (this.field_145850_b.field_72995_K) {
            float f;
            EntityPlayer entity = null;
            this.rotb = this.rota;
            if (entity == null) {
                entity = this.field_145850_b.func_72977_a((double)((float)this.field_145851_c + 0.5f), (double)((float)this.field_145848_d + 0.5f), (double)((float)this.field_145849_e + 0.5f), 6.0);
            }
            if (entity != null) {
                double d = entity.field_70165_t - (double)((float)this.field_145851_c + 0.5f);
                double d1 = entity.field_70161_v - (double)((float)this.field_145849_e + 0.5f);
                this.field_40066_q = (float)Math.atan2(d1, d);
                this.field_40059_f += 0.1f;
                if (this.field_40059_f < 0.5f || entity.field_70170_p.field_73012_v.nextInt(40) == 0) {
                    float f3 = this.field_40061_d;
                    do {
                        this.field_40061_d += (float)(entity.field_70170_p.field_73012_v.nextInt(4) - entity.field_70170_p.field_73012_v.nextInt(4));
                    } while (f3 == this.field_40061_d);
                }
            } else {
                this.field_40066_q += 0.01f;
            }
            while (this.rota >= 3.141593f) {
                this.rota -= 6.283185f;
            }
            while (this.rota < -3.141593f) {
                this.rota += 6.283185f;
            }
            while (this.field_40066_q >= 3.141593f) {
                this.field_40066_q -= 6.283185f;
            }
            while (this.field_40066_q < -3.141593f) {
                this.field_40066_q += 6.283185f;
            }
            for (f = this.field_40066_q - this.rota; f < -3.141593f; f += 6.283185f) {
            }
            this.rota += f * 0.04f;
        }
    }

    @Override
    public void addSoulBits(int bits) {
        for (int i = 0; i < bits; ++i) {
            if (!(Math.random() >= 0.97)) continue;
            this.field_145850_b.func_72908_a((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "thaumcraft:write", 0.2f, this.field_145850_b.field_73012_v.nextFloat());
            ++this.progress;
        }
        if (this.progress >= 100) {
            this.progress -= 100;
            if (this.knowledge == null) {
                this.knowledge = new ItemStack(ConfigItems.itemResource, 1, 9);
            } else {
                ++this.knowledge.field_77994_a;
            }
            --this.paper.field_77994_a;
            if (this.paper.field_77994_a <= 0) {
                this.paper = null;
            }
        }
    }

    @Override
    public boolean canAcceptSouls() {
        return this.paper != null && this.paper.field_77994_a > 0 && (this.knowledge == null || this.knowledge.field_77994_a < 64);
    }

    public int func_70302_i_() {
        return 2;
    }

    public ItemStack func_70301_a(int p_70301_1_) {
        if (p_70301_1_ == 0) {
            return this.paper;
        }
        if (p_70301_1_ == 1) {
            return this.knowledge;
        }
        return null;
    }

    public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_) {
        if (p_70298_1_ == 0) {
            int oldsize = this.paper.field_77994_a;
            this.paper.field_77994_a -= p_70298_2_;
            if (this.paper.field_77994_a <= 0) {
                this.paper = null;
            }
            return new ItemStack(Items.field_151121_aF, Math.min(p_70298_2_, oldsize));
        }
        if (p_70298_1_ == 1) {
            int oldsize = this.knowledge.field_77994_a;
            this.knowledge.field_77994_a -= p_70298_2_;
            if (this.knowledge.field_77994_a <= 0) {
                this.knowledge = null;
            }
            return new ItemStack(ConfigItems.itemResource, Math.min(p_70298_2_, oldsize), 9);
        }
        return null;
    }

    public ItemStack func_70304_b(int p_70304_1_) {
        return null;
    }

    public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_) {
        if (p_70299_1_ == 0) {
            this.paper = p_70299_2_;
        } else if (p_70299_1_ == 1) {
            this.knowledge = p_70299_2_;
        }
    }

    public String func_145825_b() {
        return "container.inspiratron";
    }

    public boolean func_145818_k_() {
        return false;
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer p_70300_1_) {
        return this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e) != this ? false : p_70300_1_.func_70092_e((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5) <= 64.0;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
        if (p_94041_1_ == 0) {
            return p_94041_2_.func_77969_a(new ItemStack(Items.field_151121_aF));
        }
        if (p_94041_1_ == 1) {
            return p_94041_2_.func_77969_a(new ItemStack(ConfigItems.itemResource, 1, 9));
        }
        return false;
    }

    public int[] func_94128_d(int p_94128_1_) {
        return new int[]{0, 1};
    }

    public boolean func_102007_a(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
        if (p_102007_1_ == 1) {
            return false;
        }
        return this.func_94041_b(p_102007_1_, p_102007_2_);
    }

    public boolean func_102008_b(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return p_102008_1_ == 1 && this.knowledge != null;
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.func_74768_a("progress", this.progress);
        NBTTagList nbttaglist = new NBTTagList();
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        if (this.paper != null) {
            this.paper.func_77955_b(nbttagcompound1);
        }
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
        NBTTagCompound nbttagcompound2 = new NBTTagCompound();
        if (this.knowledge != null) {
            this.knowledge.func_77955_b(nbttagcompound2);
        }
        nbttaglist.func_74742_a((NBTBase)nbttagcompound2);
        nbttagcompound.func_74782_a("Items", (NBTBase)nbttaglist);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.progress = nbttagcompound.func_74762_e("progress");
        NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
        NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(0);
        this.paper = ItemStack.func_77949_a((NBTTagCompound)nbttagcompound1);
        nbttagcompound1 = nbttaglist.func_150305_b(1);
        this.knowledge = ItemStack.func_77949_a((NBTTagCompound)nbttagcompound1);
    }

    @SideOnly(value=Side.CLIENT)
    public int getTimeRemainingScaled(int p_145955_1_) {
        return this.progress * p_145955_1_ / this.PROGRESS_MAX;
    }
}

