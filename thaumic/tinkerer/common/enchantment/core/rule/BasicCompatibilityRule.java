/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 */
package thaumic.tinkerer.common.enchantment.core.rule;

import java.util.List;
import net.minecraft.enchantment.Enchantment;
import thaumic.tinkerer.common.enchantment.core.IEnchantmentRule;

public class BasicCompatibilityRule
implements IEnchantmentRule {
    Enchantment illegal;

    public BasicCompatibilityRule(Enchantment illegal) {
        this.illegal = illegal;
    }

    @Override
    public boolean cantApplyAlongside(List<Integer> enchantments) {
        return enchantments.contains(this.illegal.field_77352_x);
    }
}

