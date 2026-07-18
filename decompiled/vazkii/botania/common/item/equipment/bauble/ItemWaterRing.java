/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 */
package vazkii.botania.common.item.equipment.bauble;

import baubles.api.BaubleType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class ItemWaterRing
extends ItemBauble
implements IManaUsingItem {
    public ItemWaterRing() {
        super("waterRing");
    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase player) {
        super.onWornTick(stack, player);
        if (player.func_70055_a(Material.field_151586_h)) {
            int mana;
            PotionEffect effect;
            boolean changeZ;
            double motionX = player.field_70159_w * 1.2;
            double motionY = player.field_70181_x * 1.2;
            double motionZ = player.field_70179_y * 1.2;
            boolean changeX = Math.min(1.3, Math.abs(motionX)) == Math.abs(motionX);
            boolean changeY = Math.min(1.3, Math.abs(motionY)) == Math.abs(motionY);
            boolean bl = changeZ = Math.min(1.3, Math.abs(motionZ)) == Math.abs(motionZ);
            if (player instanceof EntityPlayer && ((EntityPlayer)player).field_71075_bZ.field_75100_b) {
                changeZ = false;
                changeY = false;
                changeX = false;
            }
            if (changeX) {
                player.field_70159_w = motionX;
            }
            if (changeY) {
                player.field_70181_x = motionY;
            }
            if (changeZ) {
                player.field_70179_y = motionZ;
            }
            if ((effect = player.func_70660_b(Potion.field_76439_r)) == null) {
                PotionEffect neweffect = new PotionEffect(Potion.field_76439_r.field_76415_H, Integer.MAX_VALUE, -42, true);
                player.func_70690_d(neweffect);
            }
            if (player.func_70086_ai() <= 1 && player instanceof EntityPlayer && (mana = ManaItemHandler.requestMana(stack, (EntityPlayer)player, 300, true)) > 0) {
                player.func_70050_g(mana);
            }
        } else {
            this.onUnequipped(stack, player);
        }
    }

    @Override
    public void onUnequipped(ItemStack stack, EntityLivingBase player) {
        PotionEffect effect = player.func_70660_b(Potion.field_76439_r);
        if (effect != null && effect.func_76458_c() == -42) {
            player.func_82170_o(Potion.field_76439_r.field_76415_H);
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

