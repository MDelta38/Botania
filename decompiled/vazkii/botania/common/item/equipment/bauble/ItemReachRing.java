/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.item.equipment.bauble;

import baubles.api.BaubleType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import vazkii.botania.common.Botania;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class ItemReachRing
extends ItemBauble {
    public ItemReachRing() {
        super("reachRing");
    }

    @Override
    public void onEquippedOrLoadedIntoWorld(ItemStack stack, EntityLivingBase player) {
        Botania.proxy.setExtraReach(player, 3.5f);
    }

    @Override
    public void onUnequipped(ItemStack stack, EntityLivingBase player) {
        Botania.proxy.setExtraReach(player, -3.5f);
    }

    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.RING;
    }
}

