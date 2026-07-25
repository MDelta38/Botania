/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  baubles.api.IBauble
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package flaxbeard.thaumicexploration.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemBauble
extends Item
implements IBauble {
    public BaubleType baubleType;

    public ItemBauble(BaubleType t) {
        this.baubleType = t;
        this.field_77777_bU = 1;
    }

    public BaubleType getBaubleType(ItemStack arg0) {
        return this.baubleType;
    }

    public void onEquipped(ItemStack arg0, EntityLivingBase arg1) {
    }

    public void onUnequipped(ItemStack arg0, EntityLivingBase arg1) {
    }

    public void onWornTick(ItemStack arg0, EntityLivingBase arg1) {
    }

    public boolean canEquip(ItemStack arg0, EntityLivingBase arg1) {
        return true;
    }

    public boolean canUnequip(ItemStack arg0, EntityLivingBase arg1) {
        return true;
    }
}

