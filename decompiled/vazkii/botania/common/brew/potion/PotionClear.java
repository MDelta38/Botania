/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.brew.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import vazkii.botania.common.brew.potion.PotionMod;
import vazkii.botania.common.core.handler.ConfigHandler;

public class PotionClear
extends PotionMod {
    public PotionClear() {
        super(ConfigHandler.potionIDClear, "clear", false, 0xFFFFFF, 0);
    }

    public boolean func_76403_b() {
        return true;
    }

    public void func_76402_a(EntityLivingBase e, EntityLivingBase e1, int t, double d) {
        e1.curePotionEffects(new ItemStack(Items.field_151117_aB));
    }
}

