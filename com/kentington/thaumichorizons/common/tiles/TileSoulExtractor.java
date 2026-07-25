/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.ISidedInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 *  thaumcraft.common.lib.utils.InventoryUtils
 *  thaumcraft.common.tiles.TileJarBrain
 */
package com.kentington.thaumichorizons.common.tiles;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.ISoulReceiver;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.visnet.TileVisNode;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.tiles.TileJarBrain;

public class TileSoulExtractor
extends TileVisNode
implements ISidedInventory {
    public ItemStack soulsand = null;
    public int ticksLeft = 0;
    public boolean extracting = false;
    public static final int MAX_TICKS = 1200;
    public int sieveMotion = 0;

    public int func_70302_i_() {
        return 1;
    }

    public ItemStack func_70301_a(int p_70301_1_) {
        return this.soulsand;
    }

    public ItemStack func_70298_a(int p_70298_1_, int p_70298_2_) {
        int oldsize = this.soulsand.field_77994_a;
        this.soulsand.field_77994_a -= p_70298_2_;
        if (this.soulsand.field_77994_a <= 0) {
            this.soulsand = null;
        }
        return new ItemStack(Blocks.field_150425_aM, Math.min(p_70298_2_, oldsize));
    }

    public ItemStack func_70304_b(int p_70304_1_) {
        return null;
    }

    public void func_70299_a(int p_70299_1_, ItemStack p_70299_2_) {
        this.soulsand = p_70299_2_;
    }

    public String func_145825_b() {
        return "container.soulsieve";
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
        return p_94041_2_.func_77969_a(new ItemStack(Blocks.field_150425_aM));
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.func_74768_a("ticks", this.ticksLeft);
        nbttagcompound.func_74757_a("extracting", this.extracting);
        NBTTagList nbttaglist = new NBTTagList();
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        if (this.soulsand != null) {
            this.soulsand.func_77955_b(nbttagcompound1);
        }
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
        nbttagcompound.func_74782_a("Items", (NBTBase)nbttaglist);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.ticksLeft = nbttagcompound.func_74762_e("ticks");
        this.extracting = nbttagcompound.func_74767_n("extracting");
        NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
        NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(0);
        this.soulsand = ItemStack.func_77949_a((NBTTagCompound)nbttagcompound1);
    }

    public int[] func_94128_d(int p_94128_1_) {
        return new int[]{0};
    }

    public boolean func_102007_a(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
        return this.func_94041_b(0, p_102007_2_);
    }

    public boolean func_102008_b(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return false;
    }

    @Override
    public void func_145845_h() {
        TileEntity above;
        super.func_145845_h();
        this.extracting = false;
        if (this.ticksLeft <= 0) {
            if (this.soulsand != null) {
                --this.soulsand.field_77994_a;
                if (this.soulsand.field_77994_a <= 0) {
                    this.soulsand = null;
                }
                this.ticksLeft = 1200;
            } else {
                return;
            }
        }
        if ((above = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e)) != null) {
            if (!(above instanceof TileJarBrain && ((TileJarBrain)above).xp < ((TileJarBrain)above).xpMax || above instanceof ISoulReceiver && ((ISoulReceiver)above).canAcceptSouls())) {
                return;
            }
        } else {
            return;
        }
        this.extracting = true;
        if (!this.field_145850_b.field_72995_K && this.ticksLeft > 0) {
            int visBoost = VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, Aspect.AIR, 10);
            this.ticksLeft -= 1 + visBoost;
            if (above instanceof TileJarBrain) {
                for (int i = 0; i < 1 + visBoost; ++i) {
                    if (!(Math.random() > 0.99)) continue;
                    ++((TileJarBrain)above).xp;
                    if (((TileJarBrain)above).xp < ((TileJarBrain)above).xpMax) continue;
                    ((TileJarBrain)above).xp = ((TileJarBrain)above).xpMax;
                }
            } else {
                ((ISoulReceiver)above).addSoulBits(1 + visBoost);
            }
            if (this.ticksLeft <= 0) {
                TileEntity below = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
                ItemStack sand = new ItemStack((Block)Blocks.field_150354_m);
                if (below != null && below instanceof ISidedInventory) {
                    int[] slots;
                    for (int i : slots = ((ISidedInventory)below).func_94128_d(1)) {
                        if (!((ISidedInventory)below).func_102007_a(i, sand, 1)) continue;
                        InventoryUtils.placeItemStackIntoInventory((ItemStack)sand, (IInventory)((ISidedInventory)below), (int)1, (boolean)true);
                        break;
                    }
                } else if (below != null && below instanceof IInventory) {
                    int slots = ((IInventory)below).func_70302_i_();
                    for (int i = 0; i < slots; ++i) {
                        if (((IInventory)below).func_70301_a(i) != null && ((IInventory)below).func_70301_a(i).func_77973_b() != sand.func_77973_b()) continue;
                        InventoryUtils.placeItemStackIntoInventory((ItemStack)sand, (IInventory)((IInventory)below), (int)1, (boolean)true);
                        break;
                    }
                } else {
                    EntityItem fallenSand = new EntityItem(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d, (double)this.field_145849_e + 0.5, sand);
                    this.field_145850_b.func_72838_d((Entity)fallenSand);
                }
            }
            this.func_70296_d();
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        } else if (this.ticksLeft > 0) {
            ++this.sieveMotion;
            if (this.sieveMotion >= 360) {
                this.sieveMotion -= 360;
                this.field_145850_b.func_72980_b((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "dig.sand", 1.0f, 0.0f, false);
                ThaumicHorizons.proxy.soulParticles(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145850_b);
            }
        }
    }

    @Override
    public int getRange() {
        return 8;
    }

    @Override
    public boolean isSource() {
        return false;
    }

    public boolean isExtracting() {
        return this.extracting;
    }

    @SideOnly(value=Side.CLIENT)
    public int getTimeRemainingScaled(int p_145955_1_) {
        return this.ticksLeft * p_145955_1_ / 1200;
    }
}

