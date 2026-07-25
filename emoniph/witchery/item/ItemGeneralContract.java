/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.item;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.item.ItemGeneral;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGeneralContract
extends ItemGeneral.SubItem {
    public ItemGeneralContract(int damageValue, String unlocalizedName) {
        super(damageValue, unlocalizedName);
    }

    public static boolean isBoundContract(ItemStack stack) {
        if (stack.func_77973_b() == Witchery.Items.GENERIC) {
            ItemGeneral.SubItem subItem = Witchery.Items.GENERIC.subItems.get(Math.max(stack.func_77960_j(), 0));
            if (subItem instanceof ItemGeneralContract) {
                return Witchery.Items.TAGLOCK_KIT.isTaglockPresent(stack, 1);
            }
            return false;
        }
        return false;
    }

    public static EntityLivingBase getBoundEntity(World world, EntityPlayer player, ItemStack stack) {
        EntityLivingBase boundEntity = Witchery.Items.TAGLOCK_KIT.getBoundEntity(world, (Entity)player, stack, 1);
        return boundEntity;
    }

    public static ItemGeneralContract getContract(ItemStack stack) {
        ItemGeneral.SubItem subItem = Witchery.Items.GENERIC.subItems.get(stack.func_77960_j());
        if (subItem instanceof ItemGeneralContract) {
            return (ItemGeneralContract)subItem;
        }
        return null;
    }

    public boolean activate(ItemStack stack, EntityLivingBase targetEntity) {
        return false;
    }
}

