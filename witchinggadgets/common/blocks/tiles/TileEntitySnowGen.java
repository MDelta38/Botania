/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.common.lib.utils.InventoryUtils
 */
package witchinggadgets.common.blocks.tiles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.lib.utils.InventoryUtils;
import witchinggadgets.common.blocks.tiles.TileEntityWGBase;

public class TileEntitySnowGen
extends TileEntityWGBase {
    public ForgeDirection facing = ForgeDirection.getOrientation((int)2);
    public int tick = 0;
    int tickGoal = 40;

    public void func_145845_h() {
        super.func_145845_h();
        if (this.canWork()) {
            ++this.tick;
            if (this.tick == 32 && this.field_145850_b.field_72995_K) {
                this.field_145850_b.func_72908_a((double)((float)this.field_145851_c + 0.5f), (double)((float)this.field_145848_d + 0.5f), (double)((float)this.field_145849_e + 0.5f), "random.fizz", 0.5f, 2.6f + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.8f);
            }
            if (this.tick >= this.tickGoal) {
                if (!this.field_145850_b.field_72995_K) {
                    this.createSnow();
                } else {
                    this.field_145850_b.func_72908_a((double)((float)this.field_145851_c + 0.5f), (double)((float)this.field_145848_d + 0.5f), (double)((float)this.field_145849_e + 0.5f), "dig.stone", 0.5f, 2.6f + (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.8f);
                }
                this.tick = 0;
            }
        } else if (this.tick > 0) {
            this.tick = 0;
        }
    }

    private void createSnow() {
        ItemStack snow = new ItemStack(Items.field_151126_ay);
        TileEntity inventory = this.field_145850_b.func_147438_o(this.field_145851_c + this.facing.offsetX, this.field_145848_d, this.field_145849_e + this.facing.offsetZ);
        if (inventory != null && inventory instanceof IInventory) {
            snow = InventoryUtils.placeItemStackIntoInventory((ItemStack)snow, (IInventory)((IInventory)inventory), (int)this.facing.getOpposite().ordinal(), (boolean)true);
        }
        if (snow != null) {
            if (this.facing.equals((Object)ForgeDirection.UP) || this.facing.equals((Object)ForgeDirection.DOWN)) {
                EntityItem ei = new EntityItem(this.field_145850_b, (double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5 + (double)this.facing.offsetY * 0.66, (double)this.field_145849_e + 0.5, snow.func_77946_l());
                ei.field_70159_w = 0.025000000372529;
                ei.field_70181_x = 0.075f * (float)this.facing.offsetY;
                ei.field_70179_y = 0.025000000372529;
                this.field_145850_b.func_72838_d((Entity)ei);
            } else {
                EntityItem ei = new EntityItem(this.field_145850_b, (double)this.field_145851_c + 0.5 + (double)this.facing.offsetX * 0.66, (double)this.field_145848_d + 0.4 + (double)this.facing.getOpposite().offsetY, (double)this.field_145849_e + 0.5 + (double)this.facing.offsetZ * 0.66, snow.func_77946_l());
                ei.field_70159_w = 0.075f * (float)this.facing.offsetX;
                ei.field_70181_x = 0.025000000372529;
                ei.field_70179_y = 0.075f * (float)this.facing.offsetZ;
                this.field_145850_b.func_72838_d((Entity)ei);
            }
        }
    }

    private boolean canOutput() {
        TileEntity inventory = this.field_145850_b.func_147438_o(this.field_145851_c + this.facing.offsetX, this.field_145848_d, this.field_145849_e + this.facing.offsetZ);
        if (inventory != null && inventory instanceof IInventory) {
            return InventoryUtils.insertStack((IInventory)((IInventory)inventory), (ItemStack)new ItemStack(Items.field_151126_ay), (int)this.facing.getOpposite().ordinal(), (boolean)false) == null;
        }
        return true;
    }

    public boolean canWork() {
        return this.canOutput() && this.field_145850_b.func_94577_B(this.field_145851_c, this.field_145848_d, this.field_145849_e) <= 0 && !this.field_145850_b.func_72864_z(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound tag) {
        tag.func_74768_a("facing", this.facing.ordinal());
    }

    @Override
    public void readCustomNBT(NBTTagCompound tag) {
        this.facing = ForgeDirection.getOrientation((int)tag.func_74762_e("facing"));
    }
}

