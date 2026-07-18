/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.item.equipment.bauble;

import baubles.api.BaubleType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.mana.IManaGivingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class ItemAuraRing
extends ItemBauble
implements IManaGivingItem {
    public ItemAuraRing(String name) {
        super(name);
    }

    public ItemAuraRing() {
        this("auraRing");
    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase player) {
        super.onWornTick(stack, player);
        if (player instanceof EntityPlayer && player.field_70173_aa % this.getDelay() == 0) {
            ManaItemHandler.dispatchManaExact(stack, (EntityPlayer)player, 1, true);
        }
    }

    int getDelay() {
        return 10;
    }

    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.RING;
    }
}

