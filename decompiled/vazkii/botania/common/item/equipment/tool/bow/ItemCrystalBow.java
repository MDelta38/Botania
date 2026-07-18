/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.equipment.tool.bow;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.tool.bow.ItemLivingwoodBow;

public class ItemCrystalBow
extends ItemLivingwoodBow {
    private final int ARROW_COST = 200;

    public ItemCrystalBow() {
        super("crystalBow");
    }

    @Override
    float chargeVelocityMultiplier() {
        return 2.0f;
    }

    @Override
    boolean postsEvent() {
        return false;
    }

    @Override
    boolean canFire(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_) {
        int infinity = EnchantmentHelper.func_77506_a((int)Enchantment.field_77342_w.field_77352_x, (ItemStack)p_77615_1_);
        return ManaItemHandler.requestManaExactForTool(p_77615_1_, p_77615_3_, 200 / (infinity + 1), false);
    }

    @Override
    void onFire(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_, boolean infinity, EntityArrow arrow) {
        arrow.field_70251_a = 2;
        ManaItemHandler.requestManaExactForTool(p_77615_1_, p_77615_3_, 200 / (infinity ? 2 : 1), false);
    }
}

