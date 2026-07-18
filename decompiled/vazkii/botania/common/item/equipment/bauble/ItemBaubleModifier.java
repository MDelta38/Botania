/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.HashMultimap
 *  com.google.common.collect.Multimap
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.item.equipment.bauble;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public abstract class ItemBaubleModifier
extends ItemBauble {
    Multimap<String, AttributeModifier> attributes = HashMultimap.create();

    public ItemBaubleModifier(String name) {
        super(name);
    }

    @Override
    public void onEquippedOrLoadedIntoWorld(ItemStack stack, EntityLivingBase player) {
        this.attributes.clear();
        this.fillModifiers(this.attributes, stack);
        player.func_110140_aT().func_111147_b(this.attributes);
    }

    @Override
    public void onUnequipped(ItemStack stack, EntityLivingBase player) {
        this.attributes.clear();
        this.fillModifiers(this.attributes, stack);
        player.func_110140_aT().func_111148_a(this.attributes);
    }

    abstract void fillModifiers(Multimap<String, AttributeModifier> var1, ItemStack var2);
}

