/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 */
package vazkii.botania.common.item.equipment.bauble;

import baubles.api.BaubleType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class ItemMiningRing
extends ItemBauble
implements IManaUsingItem {
    public ItemMiningRing() {
        super("miningRing");
    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase player) {
        super.onWornTick(stack, player);
        if (player instanceof EntityPlayer && !player.field_70170_p.field_72995_K) {
            int manaCost = 5;
            boolean hasMana = ManaItemHandler.requestManaExact(stack, (EntityPlayer)player, manaCost, false);
            if (!hasMana) {
                this.onUnequipped(stack, player);
            } else {
                if (player.func_70660_b(Potion.field_76422_e) != null) {
                    player.func_82170_o(Potion.field_76422_e.field_76415_H);
                }
                player.func_70690_d(new PotionEffect(Potion.field_76422_e.field_76415_H, Integer.MAX_VALUE, 1, true));
            }
            if (player.field_70733_aJ == 0.25f) {
                ManaItemHandler.requestManaExact(stack, (EntityPlayer)player, manaCost, true);
            }
        }
    }

    @Override
    public void onUnequipped(ItemStack stack, EntityLivingBase player) {
        PotionEffect effect = player.func_70660_b(Potion.field_76422_e);
        if (effect != null && effect.func_76458_c() == 1) {
            player.func_82170_o(Potion.field_76422_e.field_76415_H);
        }
    }

    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.RING;
    }

    @Override
    public boolean usesMana(ItemStack stack) {
        return true;
    }
}

