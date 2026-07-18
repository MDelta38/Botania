/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package vazkii.botania.common.item.equipment.armor.manaweave;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import vazkii.botania.api.item.IManaProficiencyArmor;
import vazkii.botania.api.mana.IManaDiscountArmor;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.armor.manaweave.ItemManaweaveArmor;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

public class ItemManaweaveHelm
extends ItemManaweaveArmor
implements IManaDiscountArmor,
IManaProficiencyArmor {
    private static final int MANA_PER_DAMAGE = 30;

    public ItemManaweaveHelm() {
        super(0, "manaweaveHelm");
    }

    @Override
    public float getDiscount(ItemStack stack, int slot, EntityPlayer player) {
        return this.hasArmorSet(player) ? 0.35f : 0.0f;
    }

    @Override
    public boolean shouldGiveProficiency(ItemStack stack, int slot, EntityPlayer player) {
        return this.hasArmorSet(player);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        if (!world.field_72995_K && stack.func_77960_j() > 0 && ManaItemHandler.requestManaExact(stack, player, 60, true)) {
            stack.func_77964_b(stack.func_77960_j() - 1);
        }
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
        ToolCommons.damageItem(stack, damage, entity, 30);
    }
}

