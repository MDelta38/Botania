/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package flaxbeard.thaumicexploration.item;

import baubles.api.BaubleType;
import flaxbeard.thaumicexploration.item.ItemBauble;
import java.util.UUID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class ItemStabilizerBelt
extends ItemBauble {
    private static final UUID uuid = UUID.fromString("0d40c1fa-b89c-4f74-8295-74d484fa8c24");
    private static final AttributeModifier knockbackBoost = new AttributeModifier(uuid, "KBRESIST", 1.0, 0).func_111168_a(true);

    public ItemStabilizerBelt() {
        super(BaubleType.BELT);
    }

    @Override
    public void onEquipped(ItemStack arg0, EntityLivingBase arg1) {
        EntityPlayer player = (EntityPlayer)arg1;
        if (player.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111127_a(uuid) == null) {
            player.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111121_a(knockbackBoost);
        }
    }

    @Override
    public void onUnequipped(ItemStack arg0, EntityLivingBase arg1) {
        EntityPlayer player = (EntityPlayer)arg1;
        if (player.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111127_a(uuid) != null) {
            player.func_110148_a(SharedMonsterAttributes.field_111266_c).func_111124_b(knockbackBoost);
        }
    }
}

