/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.FurnaceRecipes
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.visnet.VisNetHandler;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileBellows;

public class TileArcaneFurnace
extends TileThaumcraft {
    private ItemStack[] furnaceItemStacks = new ItemStack[32];
    public int furnaceCookTime = 0;
    public int furnaceMaxCookTime = 0;
    public int speedyTime = 0;
    public int facingX = -5;
    public int facingZ = -5;

    public int getSizeInventory() {
        return this.furnaceItemStacks.length;
    }

    public ItemStack getStackInSlot(int i) {
        return this.furnaceItemStacks[i];
    }

    public ItemStack decrStackSize(int i, int j) {
        if (this.furnaceItemStacks[i] != null) {
            if (this.furnaceItemStacks[i].field_77994_a <= j) {
                ItemStack itemstack = this.furnaceItemStacks[i];
                this.furnaceItemStacks[i] = null;
                this.func_70296_d();
                return itemstack;
            }
            ItemStack itemstack1 = this.furnaceItemStacks[i].func_77979_a(j);
            if (this.furnaceItemStacks[i].field_77994_a == 0) {
                this.furnaceItemStacks[i] = null;
            }
            this.func_70296_d();
            return itemstack1;
        }
        return null;
    }

    public void setInventorySlotContents(int i, ItemStack itemstack) {
        this.furnaceItemStacks[i] = itemstack;
        if (itemstack != null && itemstack.field_77994_a > this.getInventoryStackLimit()) {
            itemstack.field_77994_a = this.getInventoryStackLimit();
        }
        this.func_70296_d();
    }

    private int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
        this.furnaceItemStacks = new ItemStack[this.getSizeInventory()];
        for (int i = 0; i < nbttaglist.func_74745_c(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
            byte byte0 = nbttagcompound1.func_74771_c("Slot");
            if (byte0 < 0 || byte0 >= this.furnaceItemStacks.length) continue;
            this.furnaceItemStacks[byte0] = ItemStack.func_77949_a((NBTTagCompound)nbttagcompound1);
        }
        this.furnaceCookTime = nbttagcompound.func_74765_d("CookTime");
        this.speedyTime = nbttagcompound.func_74765_d("SpeedyTime");
    }

    @Override
    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        nbttagcompound.func_74777_a("CookTime", (short)this.furnaceCookTime);
        nbttagcompound.func_74777_a("SpeedyTime", (short)this.speedyTime);
        NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.furnaceItemStacks.length; ++i) {
            if (this.furnaceItemStacks[i] == null) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.func_74774_a("Slot", (byte)i);
            this.furnaceItemStacks[i].func_77955_b(nbttagcompound1);
            nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
        }
        nbttagcompound.func_74782_a("Items", (NBTBase)nbttaglist);
    }

    public void func_145845_h() {
        super.func_145845_h();
        if (this.facingX == -5) {
            this.getFacing();
        }
        if (!this.field_145850_b.field_72995_K) {
            int a;
            boolean cookedflag = false;
            if (this.furnaceCookTime > 0) {
                --this.furnaceCookTime;
                cookedflag = true;
            }
            if (cookedflag && this.speedyTime > 0) {
                --this.speedyTime;
            }
            if (this.speedyTime <= 0) {
                this.speedyTime = VisNetHandler.drainVis(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, Aspect.FIRE, 5);
            }
            if (this.furnaceMaxCookTime == 0) {
                this.furnaceMaxCookTime = this.calcCookTime();
            }
            if (this.furnaceCookTime > this.furnaceMaxCookTime) {
                this.furnaceCookTime = this.furnaceMaxCookTime;
            }
            if (this.furnaceCookTime == 0 && cookedflag) {
                for (a = 0; a < this.getSizeInventory(); ++a) {
                    ItemStack itemstack;
                    if (this.furnaceItemStacks[a] == null || (itemstack = FurnaceRecipes.func_77602_a().func_151395_a(this.furnaceItemStacks[a])) == null) continue;
                    this.ejectItem(itemstack.func_77946_l(), this.furnaceItemStacks[a]);
                    this.field_145850_b.func_147452_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, ConfigBlocks.blockArcaneFurnace, 3, 0);
                    --this.furnaceItemStacks[a].field_77994_a;
                    if (this.furnaceItemStacks[a].field_77994_a > 0) break;
                    this.furnaceItemStacks[a] = null;
                    break;
                }
            }
            if (this.furnaceCookTime == 0 && !cookedflag) {
                for (a = 0; a < this.getSizeInventory(); ++a) {
                    if (this.furnaceItemStacks[a] == null || !this.canSmelt(a)) continue;
                    this.furnaceCookTime = this.furnaceMaxCookTime = this.calcCookTime();
                    break;
                }
            }
        }
    }

    private int getBellows() {
        int bellows = 0;
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            int zz;
            int yy;
            int xx;
            TileEntity tile;
            if (dir == ForgeDirection.UP || (tile = this.field_145850_b.func_147438_o(xx = this.field_145851_c + dir.offsetX * 2, yy = this.field_145848_d + dir.offsetY * 2, zz = this.field_145849_e + dir.offsetZ * 2)) == null || !(tile instanceof TileBellows) || ((TileBellows)tile).orientation != dir.getOpposite().ordinal() || this.field_145850_b.func_72864_z(xx, yy, zz)) continue;
            ++bellows;
        }
        return Math.min(3, bellows);
    }

    private int calcCookTime() {
        return (this.speedyTime > 0 ? 80 : 140) - 20 * this.getBellows();
    }

    public boolean addItemsToInventory(ItemStack items) {
        for (int a = 0; a < this.getSizeInventory(); ++a) {
            if (this.furnaceItemStacks[a] != null && this.furnaceItemStacks[a].func_77969_a(items) && this.furnaceItemStacks[a].field_77994_a + items.field_77994_a <= items.func_77976_d()) {
                this.furnaceItemStacks[a].field_77994_a += items.field_77994_a;
                if (!this.canSmelt(a)) {
                    this.destroyItem(a);
                }
                this.func_70296_d();
                return true;
            }
            if (this.furnaceItemStacks[a] != null) continue;
            this.setInventorySlotContents(a, items);
            if (!this.canSmelt(a)) {
                this.destroyItem(a);
            }
            this.func_70296_d();
            return true;
        }
        return false;
    }

    private void destroyItem(int slot) {
        this.furnaceItemStacks[slot] = null;
        this.field_145850_b.func_72980_b((double)((float)this.field_145851_c + 0.5f), (double)((float)this.field_145848_d + 0.5f), (double)((float)this.field_145849_e + 0.5f), "random.fizz", 0.3f, 2.6f + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.8f, false);
        double var21 = (float)this.field_145851_c + this.field_145850_b.field_73012_v.nextFloat();
        double var22 = this.field_145848_d + 1;
        double var23 = (float)this.field_145849_e + this.field_145850_b.field_73012_v.nextFloat();
        this.field_145850_b.func_72869_a("lava", var21, var22, var23, 0.0, 0.0, 0.0);
    }

    private void getFacing() {
        this.facingX = 0;
        this.facingZ = 0;
        if (this.field_145850_b.func_147439_a(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) == ConfigBlocks.blockArcaneFurnace && this.field_145850_b.func_72805_g(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) == 10) {
            this.facingX = -1;
        } else if (this.field_145850_b.func_147439_a(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) == ConfigBlocks.blockArcaneFurnace && this.field_145850_b.func_72805_g(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) == 10) {
            this.facingX = 1;
        } else {
            this.facingZ = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) == ConfigBlocks.blockArcaneFurnace && this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) == 10 ? -1 : 1;
        }
    }

    public void ejectItem(ItemStack items, ItemStack furnaceItemStack) {
        int var4;
        if (items == null) {
            return;
        }
        ItemStack bit = items.func_77946_l();
        int bellows = this.getBellows();
        float lx = 0.5f;
        float lz = 0.5f;
        float mx = this.facingX == 0 ? (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.03f : (float)this.facingX * 0.13f;
        float mz = this.facingZ == 0 ? (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.03f : (float)this.facingZ * 0.13f;
        EntityItem entityitem = new EntityItem(this.field_145850_b, (double)((float)this.field_145851_c + (lx += (float)this.facingX * 1.2f)), (double)((float)this.field_145848_d + 0.4f), (double)((float)this.field_145849_e + (lz += (float)this.facingZ * 1.2f)), items);
        entityitem.field_70159_w = mx;
        entityitem.field_70179_y = mz;
        entityitem.field_70181_x = 0.0;
        this.field_145850_b.func_72838_d((Entity)entityitem);
        if (ThaumcraftApi.getSmeltingBonus(furnaceItemStack) != null) {
            ItemStack bonus = ThaumcraftApi.getSmeltingBonus(furnaceItemStack).func_77946_l();
            if (bonus != null) {
                if (bellows == 0) {
                    if (this.field_145850_b.field_73012_v.nextInt(4) == 0) {
                        ++bonus.field_77994_a;
                    }
                } else {
                    for (int a = 0; a < bellows; ++a) {
                        if (!(this.field_145850_b.field_73012_v.nextFloat() < 0.44f)) continue;
                        ++bonus.field_77994_a;
                    }
                }
            }
            if (bonus != null && bonus.field_77994_a > 0) {
                mx = this.facingX == 0 ? (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.03f : (float)this.facingX * 0.13f;
                mz = this.facingZ == 0 ? (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.03f : (float)this.facingZ * 0.13f;
                EntityItem entityitem2 = new EntityItem(this.field_145850_b, (double)((float)this.field_145851_c + lx), (double)((float)this.field_145848_d + 0.4f), (double)((float)this.field_145849_e + lz), bonus);
                entityitem2.field_70159_w = mx;
                entityitem2.field_70179_y = mz;
                entityitem2.field_70181_x = 0.0;
                this.field_145850_b.func_72838_d((Entity)entityitem2);
            }
        }
        int var2 = items.field_77994_a;
        float var3 = FurnaceRecipes.func_77602_a().func_151398_b(bit);
        if (var3 == 0.0f) {
            var2 = 0;
        } else if (var3 < 1.0f) {
            var4 = MathHelper.func_76141_d((float)((float)var2 * var3));
            if (var4 < MathHelper.func_76123_f((float)((float)var2 * var3)) && (float)Math.random() < (float)var2 * var3 - (float)var4) {
                ++var4;
            }
            var2 = var4;
        }
        while (var2 > 0) {
            var4 = EntityXPOrb.func_70527_a((int)var2);
            var2 -= var4;
            EntityXPOrb xp = new EntityXPOrb(this.field_145850_b, (double)((float)this.field_145851_c + lx), (double)((float)this.field_145848_d + 0.4f), (double)((float)this.field_145849_e + lz), var4);
            mx = this.facingX == 0 ? (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.025f : (float)this.facingX * 0.13f;
            mz = this.facingZ == 0 ? (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.025f : (float)this.facingZ * 0.13f;
            xp.field_70159_w = mx;
            xp.field_70179_y = mz;
            xp.field_70181_x = 0.0;
            this.field_145850_b.func_72838_d((Entity)xp);
        }
    }

    private boolean canSmelt(int slotIn) {
        if (this.furnaceItemStacks[slotIn] == null) {
            return false;
        }
        ItemStack itemstack = FurnaceRecipes.func_77602_a().func_151395_a(this.furnaceItemStacks[slotIn]);
        return itemstack != null;
    }

    public boolean func_145842_c(int i, int j) {
        if (i == 3) {
            if (this.field_145850_b.field_72995_K) {
                for (int a = 0; a < 5; ++a) {
                    Thaumcraft.proxy.furnaceLavaFx(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.facingX, this.facingZ);
                    this.field_145850_b.func_72980_b((double)((float)this.field_145851_c + 0.5f), (double)((float)this.field_145848_d + 0.5f), (double)((float)this.field_145849_e + 0.5f), "liquid.lavapop", 0.1f + this.field_145850_b.field_73012_v.nextFloat() * 0.1f, 0.9f + this.field_145850_b.field_73012_v.nextFloat() * 0.15f, false);
                }
            }
            return true;
        }
        return super.func_145842_c(i, j);
    }
}

